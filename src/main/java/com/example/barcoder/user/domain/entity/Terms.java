package com.example.barcoder.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Terms {

    @Column(name = "terms_of_service")
    private Boolean termsOfService;

    @Column(name = "privacy_policy")
    private Boolean privacyPolicy;

    @Column(name = "international_transfer")
    private Boolean internationalTransfer;
}

