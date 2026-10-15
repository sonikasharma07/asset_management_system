package org.example.assetmanagementsystem.repository;

import org.example.assetmanagementsystem.entity.AssetHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetHistoryRepository
        extends JpaRepository<AssetHistory, Long> {

    List<AssetHistory> findByAssetIdOrderByChangedDateDesc(Long assetid);


}