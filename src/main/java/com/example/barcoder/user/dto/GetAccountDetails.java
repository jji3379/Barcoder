package com.example.barcoder.user.dto;

import com.example.barcoder.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetAccountDetails {

    private String countryCode;

    private String phoneNumber;

    private int blockedNum;

    public GetAccountDetails(User user) {
        String[] split = user.getPhoneNumber().split("\\s");
        this.countryCode = split[0];
        this.phoneNumber = split[1];
        this.blockedNum = -1;
    }
}
