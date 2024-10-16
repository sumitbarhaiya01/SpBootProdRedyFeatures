package com.nt.SpBootRestClient.repositories;

import com.nt.SpBootRestClient.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDTO,Long>{

}
