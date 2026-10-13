package org.example.assetmanagementsystem.repository;

import org.example.assetmanagementsystem.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository
        extends JpaRepository<Asset, Long> {

}