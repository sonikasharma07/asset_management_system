package org.example.assetmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.assetmanagementsystem.enums.AssetType;

import java.time.LocalDate;

@Data
public class AssetRequest {

    @NotBlank
    private String assetTag;

    @NotBlank
    private String assetName;

    @NotNull
    private AssetType assetType;

    @NotBlank
    private String serialNumber;

    private LocalDate purchaseDate;
}