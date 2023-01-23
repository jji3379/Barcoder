package com.example.barcoder.scan.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScanListRes {
    private Long itemId;
    private String barcodeNumber;
    private String itemName;
    private String itemPrice;
    private String itemImage;
    private String marketUrl;
    private int scanCount;
    private Long userId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
