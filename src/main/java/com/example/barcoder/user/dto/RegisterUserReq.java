package com.example.barcoder.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class RegisterUserReq {

    @Size(max = 20)
    @NotNull
    private String name;

    @Pattern(regexp = "\\w{1,30}")
    @Size(min = 1 ,max = 30)
    @NotNull
    private String username;

    @Size(max = 20)
    @NotNull
    private String password;

    @Email
    @NotNull
    private String email;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private String countryCode;

    @NotNull
    private String phoneNumber;

    @NotNull
    @AssertTrue
    private Boolean termsOfService;

    @NotNull
    @AssertTrue
    private Boolean privacyPolicy;

    @NotNull
    @AssertTrue
    private Boolean internationalTransfer;
}

