package com.example.barcoder.sms.controller;

import com.example.barcoder.sms.dto.MessageReq;
import com.example.barcoder.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Api(tags = "SMS 문자 API")
public class SmsController {

    private final SmsService smsService;

    /**
     * 인증 문자 보내는 API
     */
    @PostMapping("/send-sms") @ApiOperation(value = "인증 문자 발송 API", notes = "국가 번호와 핸드폰 번호를 입력하여 인증 문자를 발송합니다. <br> (핸드폰 번호는 - 를 포함하지 않습니다.) <br> (국가 코드는 서버에서 + 를 붙여서 호출합니다.)")
    public ResponseEntity<Integer> sendSms(@Valid @RequestBody MessageReq messageReq) {
        int authCode = smsService.sendAwsSms(messageReq);

        return ResponseEntity.ok(authCode);
    }
}

