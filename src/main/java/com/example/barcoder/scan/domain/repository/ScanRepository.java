package com.example.barcoder.scan.domain.repository;

import com.example.barcoder.scan.domain.entity.Scan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanRepository extends JpaRepository<Scan, Long> {
}
