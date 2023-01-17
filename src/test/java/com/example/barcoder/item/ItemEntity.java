package com.example.barcoder.item;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item", schema = "barcoder", catalog = "")
public class ItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "item_name")
    private String itemName;
    @Basic
    @Column(name = "item_price")
    private String itemPrice;
    @Basic
    @Column(name = "item_image")
    private String itemImage;
    @Basic
    @Column(name = "market_url")
    private String marketUrl;
    @Basic
    @Column(name = "scan_count")
    private int scanCount;
    @Basic
    @Column(name = "barcode_number")
    private String barcodeNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getMarketUrl() {
        return marketUrl;
    }

    public void setMarketUrl(String marketUrl) {
        this.marketUrl = marketUrl;
    }

    public int getScanCount() {
        return scanCount;
    }

    public void setScanCount(int scanCount) {
        this.scanCount = scanCount;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return id == that.id && scanCount == that.scanCount && Objects.equals(itemName, that.itemName) && Objects.equals(itemPrice, that.itemPrice) && Objects.equals(itemImage, that.itemImage) && Objects.equals(marketUrl, that.marketUrl) && Objects.equals(barcodeNumber, that.barcodeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, itemPrice, itemImage, marketUrl, scanCount, barcodeNumber);
    }
}
