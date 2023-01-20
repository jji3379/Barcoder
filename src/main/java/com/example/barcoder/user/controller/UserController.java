package com.example.barcoder.user.controller;

import com.example.barcoder.security.jwt.dto.TokenDto;
import com.example.barcoder.user.dto.*;
import com.example.barcoder.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    /*
    *  회원가입 단계
    * */
    @PostMapping("")
    public ResponseEntity<Long> registerUser(@Valid @RequestBody RegisterUserReq registerUserReq) {
        Long userId = userService.registerUser(registerUserReq);
        return ResponseEntity.created(URI.create("/api/v1/users/" + userId)).build();
    }

    @GetMapping("/{username}/exists")
    public ResponseEntity<Boolean> checkLoginId(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.checkUsername(username));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRes> loginUser(@RequestBody LoginUserReq loginUserReq) {
        return ResponseEntity.ok(userService.loginUser(loginUserReq));
    }

    /*
    * 프로필 상세정보
    * */
    @GetMapping("/{userId}")
    public ResponseEntity<GetUserDetailsRes> getUserDetails(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserDetails(userId));
    }

    /*
     * 계정관리
     * */
    @GetMapping("/me")
    public ResponseEntity<GetAccountDetails> getAccountDetails() {
        return ResponseEntity.ok(userService.getAccountDetails());
    }

    /*
     * 프로필 수정
     * */
    @PutMapping("/me")
    public ResponseEntity<String> updateUserDetails(@RequestBody UpdateUserDetailsReq updateUserDetailsReq) {
        userService.updateUserDetails(updateUserDetailsReq);
        return ResponseEntity.ok("");
    }

    /*
     * 아이디 변경
     * */
    @PutMapping("/username")
    public ResponseEntity<String> updateUserName(@RequestBody UpdateUserNameReq updateUserNameReq) {
        userService.updateUserName(updateUserNameReq);
        return ResponseEntity.ok("");
    }

    /**
     *  비밀번호 변경
     */
    @PutMapping("/password")
    public void newPassword(@RequestBody PasswordReq passwordReq) {
        userService.newPassword(passwordReq);
    }

    /*
     * 토큰 재발급
     * */
    @PostMapping("/token")
    public ResponseEntity<TokenDto> reissueToken(@RequestBody TokenReq tokenReq) {

        return ResponseEntity.ok(userService.reissueToken(tokenReq));
    }

    @PostMapping("/test")
    public ResponseEntity<String> redisTest(@RequestBody TokenReq tokenReq) {

        return ResponseEntity.ok(userService.redisTest(tokenReq));
    }
}

