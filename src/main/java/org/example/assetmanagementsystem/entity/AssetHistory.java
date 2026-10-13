package org.example.assetmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.assetmanagementsystem.enums.AssetStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "asset_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssetHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private AssetStatus oldStatus;

    @Enumerated(EnumType.STRING)
    private AssetStatus newStatus;

    private String remarks;

    private LocalDateTime changedDate;
}