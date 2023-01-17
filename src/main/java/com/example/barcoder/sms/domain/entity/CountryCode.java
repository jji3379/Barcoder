package com.example.barcoder.sms.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class CountryCode {

    @Id
    private Long country_id;
    private String countryNumber;
    private String countryCode;
    private String countryName;
    private String smsFormat;
}