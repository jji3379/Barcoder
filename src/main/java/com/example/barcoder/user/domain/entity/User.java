package com.example.barcoder.user.domain.entity;

import com.example.barcoder.common.BaseEntity;
import com.example.barcoder.scan.domain.entity.Scan;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Where(clause = "status='ACTIVE'")
@Entity(name = "user")
public class User extends BaseEntity implements UserDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Id
    private Long id;

    @Column(length = 20,nullable = false)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(length = 20, name = "phone_number")
    private String phoneNumber;

    @Embedded
    private Terms terms;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    public List<Scan> scanList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    // 계정에 대한 제제 기능이 생길 경우 작성.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /*
    * 회원
    * */
    public void updateUser(String username, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * 비밀번호 변경
     */
    public User newPassword(String password) {
        this.password = password;

        return this;
    }

    /*
     * 아이디 변경
     * */
    public void changeUsername(String username) {
        this.username = username;
    }

    public User(Long userId) {
        this.id = userId;
    }
}

