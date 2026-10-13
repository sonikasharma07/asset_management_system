package org.example.assetmanagementsystem.service;

import org.example.assetmanagementsystem.dto.EmployeeRequest;
import org.example.assetmanagementsystem.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse updateEmployee(Long id , EmployeeRequest employeeRequest);
    void deleteEmployee(Long id);
}
