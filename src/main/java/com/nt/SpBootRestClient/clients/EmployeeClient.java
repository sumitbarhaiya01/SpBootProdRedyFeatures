package com.nt.SpBootRestClient.clients;

import com.nt.SpBootRestClient.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {
    EmployeeDTO getEmployeeById(Long employeeId);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);

    public List<EmployeeDTO> getAllEmployees();
}
