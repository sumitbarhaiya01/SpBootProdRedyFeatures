package com.nt.SpBootRestClient.contraollers;

import com.nt.SpBootRestClient.clients.EmployeeClient;
import com.nt.SpBootRestClient.dto.EmployeeDTO;
import com.nt.SpBootRestClient.repositories.EmployeeRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/emp")
public class EmployeeController {

    private final EmployeeClient employeeClient;
    //private final EmployeeRepository employeeRepository;


    public EmployeeController(EmployeeClient employeeClient ) {
        this.employeeClient = employeeClient;
     //   this.employeeRepository = employeeRepository;
    }


    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId) {
        return employeeClient.getEmployeeById(employeeId);
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeClient.createNewEmployee(employeeDTO);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployee(){
        return employeeClient.getAllEmployees();
    }

}
