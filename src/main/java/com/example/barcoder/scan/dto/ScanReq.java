package com.example.barcoder.scan.dto;

import com.example.barcoder.scan.domain.entity.Scan;
import com.example.barcoder.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScanReq {
    private Long userId;
    private String barcodeNumber;

    public static Scan toEntity(ScanReq scanReq) {
        return Scan.builder()
                .userId(new User(scanReq.getUserId()))
                .barcodeNumber(scanReq.getBarcodeNumber())
                .build();
    }

}
