package org.example.assetmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.assetmanagementsystem.enums.AssetStatus;
import org.example.assetmanagementsystem.enums.AssetType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "assets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String assetTag;

    @Column(nullable = false)
    private String assetName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetType assetType;

    @Column(nullable = false, unique = true)
    private String serialNumber;

    private LocalDate purchaseDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetStatus status;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee assignedEmployee;

    @OneToMany(mappedBy = "asset")
    private List<AssetHistory> assetHistories;
}