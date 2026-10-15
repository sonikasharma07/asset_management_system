package org.example.assetmanagementsystem.serviceimpl;

import org.example.assetmanagementsystem.dto.AssetRequest;
import org.example.assetmanagementsystem.dto.AssetResponse;
import org.example.assetmanagementsystem.entity.Asset;
import org.example.assetmanagementsystem.entity.AssetHistory;
import org.example.assetmanagementsystem.entity.Employee;
import org.example.assetmanagementsystem.enums.AssetStatus;
import org.example.assetmanagementsystem.exception.BusinessException;
import org.example.assetmanagementsystem.exception.ResourceNotFoundException;
import org.example.assetmanagementsystem.repository.AssetHistoryRepository;
import org.example.assetmanagementsystem.repository.AssetRepository;
import org.example.assetmanagementsystem.repository.EmployeeRepository;
import org.example.assetmanagementsystem.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {



    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AssetHistoryRepository assetHistoryRepository;


    private AssetResponse convertToResponse(Asset asset) {

        AssetResponse response = new AssetResponse();

        response.setId(asset.getId());
        response.setAssetTag(asset.getAssetTag());
        response.setAssetName(asset.getAssetName());
        response.setAssetType(asset.getAssetType());
        response.setSerialNumber(asset.getSerialNumber());
        response.setPurchaseDate(asset.getPurchaseDate());
        response.setStatus(asset.getStatus());
        response.setCreatedDate(asset.getCreatedDate());
        response.setUpdatedDate(asset.getUpdatedDate());

        if (asset.getAssignedEmployee() != null) {
            response.setAssignedEmployeeId(asset.getAssignedEmployee().getId());
        }

        return response;
    }


    @Override
    public AssetResponse createAsset(AssetRequest assetRequest) {

        Asset asset = new Asset();

        asset.setAssetTag(assetRequest.getAssetTag());
        asset.setAssetName(assetRequest.getAssetName());
        asset.setAssetType(assetRequest.getAssetType());
        asset.setSerialNumber(assetRequest.getSerialNumber());
        asset.setPurchaseDate(assetRequest.getPurchaseDate());

        asset.setStatus(AssetStatus.AVAILABLE);

        asset.setCreatedDate(LocalDateTime.now());
        asset.setUpdatedDate(LocalDateTime.now());

        Asset savedAsset = assetRepository.save(asset);

        return convertToResponse(savedAsset);
    }

    @Override
    public List<AssetResponse> getAllAssets() {

        List<Asset> assets = assetRepository.findAll();
        List<AssetResponse> responses = new ArrayList<>();

        for (Asset asset : assets) {
            responses.add(convertToResponse(asset));
        }

        return responses;
    }

    @Override
    public AssetResponse getAssetById(Long id) {

        Asset asset = assetRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found with id: " + id));

        return convertToResponse(asset);
    }

    @Override
    public AssetResponse updateAsset(Long id, AssetRequest assetRequest) {

        Asset asset = assetRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found with id: " + id));

        asset.setAssetTag(assetRequest.getAssetTag());
        asset.setAssetName(assetRequest.getAssetName());
        asset.setAssetType(assetRequest.getAssetType());
        asset.setSerialNumber(assetRequest.getSerialNumber());
        asset.setPurchaseDate(assetRequest.getPurchaseDate());

        asset.setUpdatedDate(LocalDateTime.now());

        Asset updatedAsset = assetRepository.save(asset);

        return convertToResponse(updatedAsset);
    }

    @Override
    public void deleteAsset(Long id) {

        Asset asset = assetRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found with id: " + id));

        assetRepository.delete(asset);
    }

    private void createAssetHistory(Asset asset, Employee employee, AssetStatus oldStatus, AssetStatus newStatus, String remarks) {

        AssetHistory assetHistory = new AssetHistory();

        assetHistory.setAsset(asset);
        assetHistory.setEmployee(employee);
        assetHistory.setOldStatus(oldStatus);
        assetHistory.setNewStatus(newStatus);
        assetHistory.setRemarks(remarks);
        assetHistory.setChangedDate(LocalDateTime.now());

        assetHistoryRepository.save(assetHistory);
    }


    @Transactional
    @Override
    public AssetResponse assignAsset(Long assetId, Long employeeId) {
        Asset asset = assetRepository.findById(assetId).orElseThrow(() ->
                new ResourceNotFoundException("Asset not found with id: "+assetId));

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
                new ResourceNotFoundException("Employee not found with id: "+employeeId));

        if(asset.getStatus()!= AssetStatus.AVAILABLE){
            throw new BusinessException("ONLY AVAILABLE ASSETS CAN BE ASSIGNED");
        }

        AssetStatus oldStatus = asset.getStatus();

        asset.setAssignedEmployee(employee);
        asset.setStatus(AssetStatus.ASSIGNED);
        asset.setUpdatedDate(LocalDateTime.now());

        Asset updatedAsset = assetRepository.save(asset);

        createAssetHistory(updatedAsset,employee,oldStatus,AssetStatus.ASSIGNED, "Asset assigned to employee");

        return convertToResponse(updatedAsset);

    }

    @Transactional
    @Override
    public AssetResponse returnAsset(Long assetId) {

        Asset asset = assetRepository.findById(assetId).orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found with id: " + assetId));

        if (asset.getStatus() != AssetStatus.ASSIGNED) {
            throw new BusinessException("Only ASSIGNED assets can be returned");
        }

        AssetStatus oldStatus = asset.getStatus();

        Employee employee = asset.getAssignedEmployee();

        asset.setStatus(AssetStatus.AVAILABLE);
        asset.setAssignedEmployee(null);
        asset.setUpdatedDate(LocalDateTime.now());

        Asset updatedAsset = assetRepository.save(asset);

        createAssetHistory(updatedAsset, employee, oldStatus, AssetStatus.AVAILABLE, "Asset returned");

        return convertToResponse(updatedAsset);
    }

    @Transactional
    @Override
    public AssetResponse damageAsset(Long assetId) {

        Asset asset = assetRepository.findById(assetId).orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found with id: " + assetId));

        if (asset.getStatus() == AssetStatus.RETIRED) {
            throw new BusinessException("Retired asset cannot be damaged");
        }

        if (asset.getStatus() == AssetStatus.DAMAGED) {
            throw new BusinessException("Asset is already damaged");
        }

        AssetStatus oldStatus = asset.getStatus();

        Employee employee = asset.getAssignedEmployee();

        asset.setStatus(AssetStatus.DAMAGED);
        asset.setUpdatedDate(LocalDateTime.now());

        Asset updatedAsset = assetRepository.save(asset);

        createAssetHistory(updatedAsset, employee, oldStatus, AssetStatus.DAMAGED, "Asset damaged");

        return convertToResponse(updatedAsset);
    }

    @Transactional
    @Override
    public AssetResponse repairAsset(Long assetId) {

        Asset asset = assetRepository.findById(assetId).orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found with id: " + assetId));

        AssetStatus oldStatus = asset.getStatus();
        AssetStatus newStatus;

        if (oldStatus == AssetStatus.DAMAGED) {
            newStatus = AssetStatus.UNDER_REPAIR;
        }
        else if (oldStatus == AssetStatus.UNDER_REPAIR) {
            newStatus = AssetStatus.AVAILABLE;
        }
        else {
            throw new BusinessException("Only DAMAGED or UNDER_REPAIR assets can be repaired");
        }

        asset.setStatus(newStatus);
        asset.setUpdatedDate(LocalDateTime.now());

        Asset updatedAsset = assetRepository.save(asset);

        createAssetHistory(
                updatedAsset,
                updatedAsset.getAssignedEmployee(),
                oldStatus,
                newStatus,
                "Asset repair status updated"
        );

        return convertToResponse(updatedAsset);
    }

    @Transactional
    @Override
    public AssetResponse retireAsset(Long assetId) {

        Asset asset = assetRepository.findById(assetId).orElseThrow(() ->
                        new ResourceNotFoundException("Asset not found with id: " + assetId));

        if (asset.getStatus() == AssetStatus.RETIRED) {
            throw new BusinessException("Asset is already retired");
        }

        AssetStatus oldStatus = asset.getStatus();

        Employee employee = asset.getAssignedEmployee();

        asset.setStatus(AssetStatus.RETIRED);
        asset.setUpdatedDate(LocalDateTime.now());

        Asset retiredAsset = assetRepository.save(asset);

        createAssetHistory(retiredAsset, employee, oldStatus, AssetStatus.RETIRED, "Asset retired");

        return convertToResponse(retiredAsset);
    }


}
