package com.example.barcoder.user.service;

import com.example.barcoder.common.error.BaseCode;
import com.example.barcoder.exception.CustomException;
import com.example.barcoder.security.jwt.TokenProvider;
import com.example.barcoder.security.jwt.dto.TokenDto;
import com.example.barcoder.user.domain.entity.Terms;
import com.example.barcoder.user.domain.entity.User;
import com.example.barcoder.user.domain.repository.UserRepository;
import com.example.barcoder.user.dto.*;
import com.example.barcoder.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RedisTemplate redisTemplate;

    @Transactional
    public Long registerUser(RegisterUserReq registerUserReq) {
        // 아이디 중복 확인
        userRepository.findByUsername(registerUserReq.getUsername())
                .ifPresent(exists -> {
                    throw new CustomException(BaseCode.EXISTS_USERNAME);
                });

        // 핸드폰 번호 중복 확인
        String phoneNumber = registerUserReq.getCountryCode() + " " + registerUserReq.getPhoneNumber();
        userRepository.findByPhoneNumber(phoneNumber)
                .ifPresent(exists -> {
                    throw new CustomException(BaseCode.EXISTS_PHONE_NUMBER);
                });

        String encodePassword = passwordEncoder.encode(registerUserReq.getPassword());

        return userRepository.save(User.builder()
                .username(registerUserReq.getUsername())
                .password(encodePassword)
                .name(registerUserReq.getName())
                .email(registerUserReq.getEmail())
                .phoneNumber(phoneNumber)
                .birthDate(registerUserReq.getBirthDate())
                .terms(Terms.builder()
                        .termsOfService(registerUserReq.getTermsOfService())
                        .privacyPolicy(registerUserReq.getPrivacyPolicy())
                        .internationalTransfer(registerUserReq.getInternationalTransfer())
                        .build())
                .roles(Collections.singletonList("ROLE_USER"))
                .build()).getId();
    }

    public boolean checkUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public LoginRes loginUser(LoginUserReq loginUserReq) {
        User user = userRepository.findByUsernameOrPhoneNumber(loginUserReq.getLoginId(), loginUserReq.getCountryCode() + " " + loginUserReq.getLoginId())
                .orElseThrow(() -> new CustomException(BaseCode.UNSIGN_USERNAME_OR_PHONE));

        if (!passwordEncoder.matches(loginUserReq.getPassword(), user.getPassword())) {
            throw new CustomException(BaseCode.WRONG_PASSWORD);
        }

        // 토큰 만들기
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(),"", user.getAuthorities());
        TokenDto token = tokenProvider.generateTokenDto(auth);

        // Refresh Token Redis에 저장
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(user.getId().toString(), token.getRefreshToken());

        return LoginRes.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .registerStatus(token.getRegisterStatus())
                .accessTokenExpiresIn(token.getAccessTokenExpiresIn())
                .grantType(token.getGrantType())
                .userId(user.getId())
                .build();
    }

    /*
     * 토큰 재발급
     * */
    public TokenDto reissueToken(TokenReq tokenReq) {
        //Access Token 추출
        Authentication authentication = tokenProvider.getAuthentication(tokenReq.getAccessToken());

        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new CustomException(BaseCode.UNSIGN_USERNAME_OR_PHONE));

        //토큰 검증
        if (!tokenProvider.validateToken(tokenReq.getRefreshToken())) {
            throw new CustomException(BaseCode.INVALID_REFRESH_TOKEN);
        }

        // 레디스에서 리프레쉬 토큰 가져오기
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String refreshToken = valueOperations.get(user.getId().toString());

        if (!tokenReq.getRefreshToken().equals(refreshToken)) {
            throw new CustomException(BaseCode.INVALID_REFRESH_TOKEN);
        }

        // 새로운 토큰 생성
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(),"", user.getAuthorities());
        TokenDto tokenDto = tokenProvider.generateTokenDto(auth);

        // 저장소 정보 업데이트
        valueOperations.set(user.getId().toString(), tokenDto.getRefreshToken());

        return tokenDto;
    }

    public String redisTest(TokenReq tokenReq) {
        //Access Token 추출
        Authentication authentication = tokenProvider.getAuthentication(tokenReq.getAccessToken());

        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new CustomException(BaseCode.UNSIGN_USERNAME_OR_PHONE));

        // 레디스에서 리프레쉬 토큰 가져오기
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String refreshToken = valueOperations.get(user.getId().toString());

        return refreshToken;
    }

    /*
     * 프로필 상세정보
     * */
    public GetUserDetailsRes getUserDetails(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(BaseCode.UNSIGN_USERNAME_OR_PHONE));

        return new GetUserDetailsRes(user);
    }

    /*
     * 프로필 수정
     * */
    @Transactional
    public void updateUserDetails(UpdateUserDetailsReq updateUserDetailsReq) {
        User user = userRepository.findByUsername(SecurityUtil.getCurrentUserName())
                .orElseThrow(() -> new CustomException(BaseCode.UNSIGN_USERNAME_OR_PHONE));

        user.updateUser(updateUserDetailsReq.getProfileImageUrl(), updateUserDetailsReq.getUserName(), updateUserDetailsReq.getIntro());
    }

    /*
     * 계정관리
     * */
    public GetAccountDetails getAccountDetails() {
        User user = userRepository.findByUsername(SecurityUtil.getCurrentUserName())
                .orElseThrow(() -> new CustomException(BaseCode.UNSIGN_USERNAME_OR_PHONE));

        return new GetAccountDetails(user);
    }

    /**
     * 비밀번호 재설정
     */
    @Transactional
    public void newPassword(PasswordReq passwordReq) {
        User user = userRepository.findByPhoneNumber(passwordReq.getCountryCode() + " " + passwordReq.getPhoneNumber())
                .orElseThrow(() -> new CustomException(BaseCode.UNREGISTERD_PHONE_NUMBER));

        user.newPassword(passwordEncoder.encode(passwordReq.getPassword()));
    }

    /*
     * 아이디 변경
     * */
    @Transactional
    public void updateUserName(UpdateUserNameReq updateUserNameReq) {
        User user = userRepository.findByUsername(SecurityUtil.getCurrentUserName())
                .orElseThrow(() -> new CustomException(BaseCode.UNSIGN_USERNAME_OR_PHONE));

        user.changeUsername(updateUserNameReq.getUsername());
    }
}

