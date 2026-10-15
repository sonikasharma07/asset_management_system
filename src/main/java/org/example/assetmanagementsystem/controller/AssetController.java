package org.example.assetmanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.assetmanagementsystem.dto.AssetRequest;
import org.example.assetmanagementsystem.dto.AssetResponse;
import org.example.assetmanagementsystem.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;


    @PostMapping
    public ResponseEntity<AssetResponse> createAsset(@Valid @RequestBody AssetRequest assetRequest) {

        AssetResponse assetResponse = assetService.createAsset(assetRequest);

        return new ResponseEntity<>(assetResponse, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<AssetResponse>> getAllAssets() {

        List<AssetResponse> assets = assetService.getAllAssets();

        return new ResponseEntity<>(assets, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<AssetResponse> getAssetById(@PathVariable Long id) {

        AssetResponse assetResponse = assetService.getAssetById(id);

        return new ResponseEntity<>(assetResponse, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AssetResponse> updateAsset(@PathVariable Long id, @Valid @RequestBody AssetRequest assetRequest) {

        AssetResponse assetResponse = assetService.updateAsset(id, assetRequest);

        return new ResponseEntity<>(assetResponse, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {

        assetService.deleteAsset(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{assetId}/assign/{employeeId}")
    public ResponseEntity<AssetResponse> assignAsset(@PathVariable Long assetId, @PathVariable Long employeeId) {

        AssetResponse assetResponse = assetService.assignAsset(assetId, employeeId);

        return new ResponseEntity<>(assetResponse, HttpStatus.OK);
    }


    @PutMapping("/{assetId}/return")
    public ResponseEntity<AssetResponse> returnAsset(@PathVariable Long assetId) {

        AssetResponse assetResponse = assetService.returnAsset(assetId);

        return new ResponseEntity<>(assetResponse, HttpStatus.OK);
    }

    @PutMapping("/{assetId}/damage")
    public ResponseEntity<AssetResponse> damageAsset(@PathVariable Long assetId) {

        AssetResponse assetResponse = assetService.damageAsset(assetId);

        return new ResponseEntity<>(assetResponse, HttpStatus.OK);
    }

    @PutMapping("/{assetId}/repair")
    public ResponseEntity<AssetResponse> repairAsset(@PathVariable Long assetId) {

        AssetResponse assetResponse = assetService.repairAsset(assetId);

        return new ResponseEntity<>(assetResponse, HttpStatus.OK);
    }

    @PutMapping("/{assetId}/retire")
    public ResponseEntity<AssetResponse> retireAsset(@PathVariable Long assetId) {

        AssetResponse assetResponse = assetService.retireAsset(assetId);

        return new ResponseEntity<>(assetResponse, HttpStatus.OK);
    }




}