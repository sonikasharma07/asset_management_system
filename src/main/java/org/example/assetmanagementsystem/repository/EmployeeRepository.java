package org.example.assetmanagementsystem.repository;

import org.example.assetmanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

}