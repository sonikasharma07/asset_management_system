package org.example.assetmanagementsystem.repository;

import org.example.assetmanagementsystem.entity.AssetHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetHistoryRepository
        extends JpaRepository<AssetHistory, Long> {

}