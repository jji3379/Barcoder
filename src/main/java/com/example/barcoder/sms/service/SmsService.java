package com.example.barcoder.sms.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.example.barcoder.common.error.BaseCode;
import com.example.barcoder.exception.CustomException;
import com.example.barcoder.sms.dto.MessageReq;
import com.example.barcoder.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SmsService {

    /**
     * AWS SMS
     */
    @Value("${cloud.aws.credentials.sms-access-key}")
    private String smsAccessKey;

    @Value("${cloud.aws.credentials.sms-secret-key}")
    private String smsSecretKey;

    private final UserRepository userRepository;
    /**
     *  AWS SMS
     */
    public int sendAwsSms(MessageReq messageReq) {
        // 핸드폰 번호 중복 확인
        userRepository.findByPhoneNumber(messageReq.getCountryCode() + " " + messageReq.getTo()).ifPresent(exists -> {
            throw new CustomException(BaseCode.EXISTS_PHONE_NUMBER);
        });

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(smsAccessKey, smsSecretKey);
        AmazonSNS snsClient = AmazonSNSClient.builder().withRegion(Regions.AP_NORTHEAST_1).withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
                .withStringValue("Merlin")
                .withDataType("String"));

        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
                .withStringValue("Transactional")
                .withDataType("String"));

        int authCode = generateAuth();
        String message = "[Toyou] verification : " + authCode;

        snsClient.publish(new PublishRequest()
                .withMessage(message)
                .withPhoneNumber("+"+messageReq.getCountryCode()+messageReq.getTo())
                .withMessageAttributes(smsAttributes));

        return authCode;
    }

    /**
     * 6자리 인증키 생성, int 반환
     */
    public int generateAuth() {

        return ThreadLocalRandom.current().nextInt(100000, 1000000);
    }
}

