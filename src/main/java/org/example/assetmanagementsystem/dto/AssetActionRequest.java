package org.example.assetmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AssetActionRequest {

    @NotBlank
    private String remarks;
}
