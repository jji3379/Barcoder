package com.example.barcoder.scan.controller;

import com.example.barcoder.scan.dto.ScanReq;
import com.example.barcoder.scan.dto.ScanRes;
import com.example.barcoder.scan.service.ScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scans/")
public class ScanController {
    private final ScanService scanService;

    /**
     * 스캔
     */
    @PostMapping("")
    public ResponseEntity<ScanRes> saveScan(@RequestBody ScanReq scanReq) {

        return ResponseEntity.ok(scanService.saveScan(scanReq));
    }

    /**
     * 스캔한 목록
     */
    @GetMapping("/scan")
    public void getScanItemList() {
        scanService.getScanItemList();
    }
}
