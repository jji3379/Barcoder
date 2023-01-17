package com.example.barcoder.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TokenReq {

    private String accessToken;

    private String refreshToken;
}
