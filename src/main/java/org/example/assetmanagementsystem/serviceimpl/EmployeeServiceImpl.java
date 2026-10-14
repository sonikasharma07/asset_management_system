package org.example.assetmanagementsystem.serviceimpl;


import org.example.assetmanagementsystem.dto.EmployeeRequest;
import org.example.assetmanagementsystem.dto.EmployeeResponse;
import org.example.assetmanagementsystem.entity.Employee;
import org.example.assetmanagementsystem.exception.ResourceNotFoundException;
import org.example.assetmanagementsystem.repository.EmployeeRepository;
import org.example.assetmanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;



    private EmployeeResponse convertToResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setEmployeeId(employee.getEmployeeId());
        response.setName(employee.getName());
        response.setEmail(employee.getEmail());
        response.setDepartment(employee.getDepartment());

        return response;
    }



    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee= new Employee();
        employee.setEmployeeId(employeeRequest.getEmployeeId());
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setDepartment(employeeRequest.getDepartment());

        Employee savedEmployee = employeeRepository.save(employee);

        return convertToResponse(savedEmployee);


    }


    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> responses = new ArrayList<>();

        for(Employee employee: employees) {
            responses.add(convertToResponse(employee));
        }

            return responses;

    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Employee not found with id:" +id));

        return convertToResponse(employee);
    }


    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee employee= employeeRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Employee not found with id:" +id));

        employee.setEmployeeId(employeeRequest.getEmployeeId());
        employee.setName(employeeRequest.getName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setDepartment(employeeRequest.getDepartment());

        Employee updatedEmployee = employeeRepository.save(employee);

        return convertToResponse(updatedEmployee);

    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(()
                ->new ResourceNotFoundException("Employee not found with id:" +id));

        employeeRepository.delete(employee);

    }

}
