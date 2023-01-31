package com.example.barcoder.scan.dto;

import com.example.barcoder.scan.domain.entity.Scan;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScanRes {
    private Long scanId;
    private String barcodeNumber;
    private String marketUrl;
    private Long userId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @Builder
    public ScanRes(Long scanId, String barcodeNumber, String marketUrl, Long userId, LocalDateTime createAt, LocalDateTime updateAt) {
        this.scanId = scanId;
        this.barcodeNumber = barcodeNumber;
        this.marketUrl = marketUrl;
        this.userId = userId;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

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

    public static ScanRes toMarketUrl(String marketUrl) {
        return ScanRes.builder()
                .marketUrl(marketUrl)
                .build();
    }
}
