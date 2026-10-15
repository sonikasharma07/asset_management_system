package org.example.assetmanagementsystem.dto;

import lombok.Data;
import org.example.assetmanagementsystem.enums.AssetStatus;

import java.time.LocalDateTime;

@Data
public class AssetHistoryResponse {

    private Long id;
    private Long assetId;
    private Long employeeId;
    private AssetStatus oldStatus;
    private AssetStatus newStatus;
    private String remarks;
    private LocalDateTime changedDate;
}