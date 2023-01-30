package com.example.barcoder.scan.dto;

import com.example.barcoder.scan.domain.entity.Scan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ScanRes {
    private Long scanId;
    private String barcodeNumber;
    private String marketUrl;
    private Long userId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static ScanRes toRes(Scan scan, String marketUrl) {
        return ScanRes.builder()
                .scanId(scan.getId())
                .barcodeNumber(scan.getBarcodeNumber())
                .marketUrl(marketUrl)
                .userId(scan.getUserId().getId())
                .createAt(scan.getCreateAt())
                .updateAt(scan.getUpdateAt())
                .build();
    }
}
