package com.example.barcoder.item.controller;

import com.example.barcoder.item.dto.ItemRes;
import com.example.barcoder.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<List<ItemRes>> getTopItemList() {

        return ResponseEntity.ok(itemService.getTopItemList());
    }

    /**
     * 검색 조회
     */
    @GetMapping("/search/{condition}")
    public ResponseEntity<List<ItemRes>> getSearchItemList(@PathVariable String condition) {

        return ResponseEntity.ok(itemService.getSearchItemList(condition));
    }
}
