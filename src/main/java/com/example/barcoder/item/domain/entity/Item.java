package com.example.barcoder.item.domain.entity;

import com.example.barcoder.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "item_name", nullable = true, length = 500)
    private String itemName;
    
    @Column(name = "item_price", nullable = true, length = 100)
    private String itemPrice;
    
    @Column(name = "item_image", nullable = true, length = 500)
    private String itemImage;
    
    @Column(name = "market_url", nullable = true, length = 500)
    private String marketUrl;
    @Column(name = "scan_count", nullable = false)
    private int scanCount;
    
    @Column(name = "barcode_number", nullable = true, length = 500)
    private String barcodeNumber;

    public Item(Long itemId) {
        this.id = itemId;
    }

    public void incrScanCount() {
        this.scanCount += 1;
    }
}
