package com.example.barcoder.sms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageReq {
    @NotNull
    @ApiModelProperty(value = "받는 사람 번호", required = true, example = "01075563379")
    String to;

    @NotNull
    @ApiModelProperty(value = "국가 코드", required = true, example = "82")
    String countryCode;
}
