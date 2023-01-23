package com.example.barcoder.item.domain.repository;

import com.example.barcoder.item.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findTop12ByOrderByScanCountDesc();

    Optional<Item> findByBarcodeNumber(String barcodeNumber);
}
