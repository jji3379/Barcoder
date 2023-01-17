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

    private String name;

    private String intro;

    private int followingNum;

    private int followerNum;

    public GetUserDetailsRes(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.name = user.getUsername();
        this.intro = user.getIntro();
    }
}
