package org.example.assetmanagementsystem.dto;

import lombok.Data;
import org.example.assetmanagementsystem.enums.AssetStatus;
import org.example.assetmanagementsystem.enums.AssetType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AssetResponse {

    private Long id;

    private String assetTag;

    private String assetName;

    private AssetType assetType;

    private String serialNumber;

    private LocalDate purchaseDate;

    private AssetStatus status;

    private Long assignedEmployeeId;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}