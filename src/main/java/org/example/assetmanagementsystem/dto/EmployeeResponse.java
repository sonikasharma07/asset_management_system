package org.example.assetmanagementsystem.dto;

import lombok.Data;

@Data
public class EmployeeResponse {

    private Long id;

    private String employeeId;

    private String name;

    private String email;

    private String department;
}