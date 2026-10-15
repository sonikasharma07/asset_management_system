package org.example.assetmanagementsystem.service;

import org.example.assetmanagementsystem.dto.AssetHistoryResponse;
import org.example.assetmanagementsystem.dto.AssetRequest;
import org.example.assetmanagementsystem.dto.AssetResponse;

import java.util.List;

public interface AssetService {
    AssetResponse createAsset(AssetRequest assetRequest);
    List<AssetResponse> getAllAssets();
    AssetResponse getAssetById(Long id);
    AssetResponse updateAsset(Long id , AssetRequest assetRequest);
    void deleteAsset(Long id);

    AssetResponse assignAsset(Long assetId, Long employeeId);
    AssetResponse returnAsset(Long assetId);
    AssetResponse damageAsset(Long assetId);
    AssetResponse repairAsset(Long assetId);
    AssetResponse retireAsset(Long assetId);
    List<AssetHistoryResponse> getAssetHistory(Long assetId);

}
