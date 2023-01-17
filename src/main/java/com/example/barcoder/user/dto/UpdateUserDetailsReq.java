package com.example.barcoder.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDetailsReq {

    private String profileImageUrl;

    private String userName;

    private String intro;
}
