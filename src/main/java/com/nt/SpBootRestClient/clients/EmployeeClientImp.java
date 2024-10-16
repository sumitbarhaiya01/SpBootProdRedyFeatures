package com.nt.SpBootRestClient.clients;

import com.nt.SpBootRestClient.adivce.ApiResponse;
import com.nt.SpBootRestClient.config.RestClientConfig;
import com.nt.SpBootRestClient.dto.EmployeeDTO;
import com.nt.SpBootRestClient.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EmployeeClientImp implements EmployeeClient{

    private final RestClient restClient;

    Logger log= LoggerFactory.getLogger(EmployeeClient.class);
    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("Trying to  retrieve employee with id "+employeeId+" in getEmployeeById");
        try {
            ApiResponse<EmployeeDTO>employeeResponse=restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req,res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw  new ResourceNotFoundException("could not get the employee with id  "+employeeId);
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeResponse.getData();

        }
        catch(Exception e){
            log.error("Exception occur in getEmployeeById()", e);
             throw  new RuntimeException(e);

        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("Trying to create employee with information {}",employeeDTO);
        try{
        ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
             .uri("employees")
             .body(employeeDTO)
             .retrieve()
             .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                 throw new ResourceNotFoundException("could not crete new employee");
             })
             .toEntity(new ParameterizedTypeReference<>(){

        });
        log.trace("successfully  create new Employee with details : {}", employeeDTOApiResponse.getBody());
        return employeeDTOApiResponse.getBody().getData();
    }
    catch(Exception e){
            log.error(" Exception occur in createNewEmployee()");
            throw new RuntimeException(e);
    }

    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.trace("Trying to retrieve all employees in getAllEmployees");
        try {
            log.info("Attempting to call the restClient Method in getAllEmployees");
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrieved the employees in getAllEmployees");
            log.trace("Retrieved employees list in getAllEmployees : {}, {}, {}", employeeDTOList.getData(), "Hello", 5);
            return employeeDTOList.getData();
        } catch (Exception e) {
            log.error("Exception occurred in getAllEmployees", e);
            throw new RuntimeException(e);
        }
    }
}

