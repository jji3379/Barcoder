package com.example.barcoder.item.dto;

import com.example.barcoder.item.domain.entity.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemRes {
    private long id;
    private String itemName;
    private String itemPrice;
    private String itemImage;
    private String marketUrl;
    private int scanCount;
    private String barcodeNumber;

    @Builder
    public ItemRes(long id, String itemName, String itemPrice, String itemImage, String marketUrl, int scanCount, String barcodeNumber) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
        this.marketUrl = marketUrl;
        this.scanCount = scanCount;
        this.barcodeNumber = barcodeNumber;
    }

    public static ItemRes toRes(Item item) {
        return ItemRes.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .itemPrice(item.getItemPrice())
                .itemImage(item.getItemImage())
                .marketUrl(item.getMarketUrl())
                .scanCount(item.getScanCount())
                .barcodeNumber(item.getBarcodeNumber())
                .build();
    }
}
