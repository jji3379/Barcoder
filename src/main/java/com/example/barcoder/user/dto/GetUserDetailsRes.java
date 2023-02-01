package com.example.barcoder.user.dto;

import com.example.barcoder.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetUserDetailsRes {

    private Long userId;

    private String username;

    private String email;

    private String name;

    private String phoneNumber;

    public GetUserDetailsRes(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
    }
}
