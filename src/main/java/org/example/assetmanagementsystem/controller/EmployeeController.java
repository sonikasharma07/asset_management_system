package org.example.assetmanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.assetmanagementsystem.dto.EmployeeRequest;
import org.example.assetmanagementsystem.dto.EmployeeResponse;
import org.example.assetmanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {

        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);

        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {

        List<EmployeeResponse> employees = employeeService.getAllEmployees();

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {

        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);

        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequest employeeRequest) {

        EmployeeResponse employeeResponse = employeeService.updateEmployee(id, employeeRequest);

        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}