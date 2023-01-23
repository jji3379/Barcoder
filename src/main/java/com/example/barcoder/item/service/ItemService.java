package com.example.barcoder.item.service;

import com.example.barcoder.item.domain.repository.ItemRepository;
import com.example.barcoder.item.dto.ItemRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    /**
     * 인기 목록 조회
     */
    public List<ItemRes> getTopItemList() {

        return itemRepository.findTop12ByOrderByScanCountDesc().stream()
                .map(item -> ItemRes.toRes(item)).collect(Collectors.toList());
    }

    /**
     * 검색 조회
     */
    public void getSearchItemList() {

    }
}
