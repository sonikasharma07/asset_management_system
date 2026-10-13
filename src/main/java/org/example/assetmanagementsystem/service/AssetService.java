package org.example.assetmanagementsystem.service;

import org.example.assetmanagementsystem.dto.AssetRequest;
import org.example.assetmanagementsystem.dto.AssetResponse;

import java.util.List;

public interface AssetService {
    List<AssetResponse> getAllAssets();
    AssetResponse getAssetById(Long id);
    AssetResponse updateAsset(Long id , AssetRequest assetRequest);
    void deleteAsset(Long id);
}
