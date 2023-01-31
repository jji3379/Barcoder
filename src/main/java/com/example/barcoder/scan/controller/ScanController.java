package com.example.barcoder.scan.controller;

import com.example.barcoder.scan.dto.ScanListRes;
import com.example.barcoder.scan.dto.ScanReq;
import com.example.barcoder.scan.dto.ScanRes;
import com.example.barcoder.scan.service.ScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scans")
public class ScanController {
    private final ScanService scanService;

    /**
     * 스캔
     */
    @PostMapping("")
    public ResponseEntity<String> saveScan(@RequestBody ScanReq scanReq) {

        return ResponseEntity.ok(scanService.saveScan(scanReq));
    }

    /**
     * 스캔한 목록
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<ScanListRes>> getScanItemList(@PathVariable Long userId) {

        return ResponseEntity.ok(scanService.getScanItemList(userId));
    }
}
