package com.example.barcoder.item.controller;

import com.example.barcoder.item.dto.ItemRes;
import com.example.barcoder.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemController {
    private final ItemService itemService;

    /**
     * 인기 목록 조회
     */
    @GetMapping("/top")
    public List<ItemRes> getTopItemList() {

        return itemService.getTopItemList();
    }

    /**
     * 스캔
     */
    @PostMapping("/scan")
    public void saveScanBarcode() {
        itemService.saveScanBarcode();
    }

    /**
     * 스캔한 목록
     */
    @GetMapping("/scan")
    public void getScanItemList() {
        itemService.getScanItemList();
    }

    /**
     * 검색 조회
     */
    @GetMapping("/search")
    public void getSearchItemList() {
        itemService.getSearchItemList();
    }
}
