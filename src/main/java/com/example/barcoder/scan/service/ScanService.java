package com.example.barcoder.scan.service;

import com.example.barcoder.common.error.BaseCode;
import com.example.barcoder.exception.CustomException;
import com.example.barcoder.item.domain.entity.Item;
import com.example.barcoder.item.domain.repository.ItemRepository;
import com.example.barcoder.scan.domain.entity.Scan;
import com.example.barcoder.scan.domain.repository.ScanRepository;
import com.example.barcoder.scan.dto.ScanReq;
import com.example.barcoder.scan.dto.ScanRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScanService {
    private final ScanRepository scanRepository;
    private final ItemRepository itemRepository;

    /**
     * 스캔
     */
    @Transactional
    public ScanRes saveScan(ScanReq scanReq) {
        Item item = itemRepository.findByBarcodeNumber(scanReq.getBarcodeNumber())
                .orElseThrow(() -> new CustomException(BaseCode.NOT_FOUNT_BARCODE));

        Scan save = scanRepository.save(ScanReq.toEntity(scanReq));

        item.incrScanCount();

        return ScanRes.toRes(save);
    }

    /**
     * 스캔한 목록
     */
    public void getScanItemList() {

    }
}
