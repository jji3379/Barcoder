package com.example.barcoder.scan.service;

import com.example.barcoder.common.error.BaseCode;
import com.example.barcoder.exception.CustomException;
import com.example.barcoder.item.domain.entity.Item;
import com.example.barcoder.item.domain.repository.ItemRepository;
import com.example.barcoder.scan.domain.entity.Scan;
import com.example.barcoder.scan.domain.repository.ScanRepository;
import com.example.barcoder.scan.dto.ScanListRes;
import com.example.barcoder.scan.dto.ScanReq;
import com.example.barcoder.scan.dto.ScanRes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.barcoder.item.domain.entity.QItem.item;
import static com.example.barcoder.scan.domain.entity.QScan.scan;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScanService {
    private final ScanRepository scanRepository;
    private final ItemRepository itemRepository;
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 스캔
     */
    @Transactional
    public Object saveScan(ScanReq scanReq) {
        Optional<Item> getItem = itemRepository.findByBarcodeNumber(scanReq.getBarcodeNumber());

        if (getItem.isPresent()) {
            Item item = getItem.get();
            item.incrScanCount();

            if (scanReq.getUserId() == 0) {
                return ScanRes.toMarketUrl(scanReq.getBarcodeNumber(), item.getMarketUrl());
            } else {
                Scan save = scanRepository.save(ScanReq.toEntity(scanReq));

                return ScanRes.toRes(save, item.getMarketUrl());
            }
        } else {
            return new char[0];
        }

    }

    /**
     * 스캔한 목록
     */
    public List<ScanListRes> getScanItemList(Long userId) {
        List<ScanListRes> scanList = jpaQueryFactory.select(Projections.fields(ScanListRes.class,
                        item.id.as("itemId"), item.brandName,
                        item.barcodeNumber, item.itemName,
                        item.itemPrice, item.itemImage,
                        item.marketUrl, item.scanCount,
                        scan.userId.id.as("userId"),
                        scan.createAt, scan.updateAt))
                .from(scan)
                .join(item).on(item.barcodeNumber.eq(scan.barcodeNumber))
                .fetchJoin()
                .where(scan.userId.id.eq(userId))
                .groupBy(scan.barcodeNumber)
                .orderBy(scan.createAt.desc())
                .limit(12)
                .fetch();

        return scanList;
    }
}
