package net.javaguides.employeeservice.service;

import net.javaguides.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")//because we have two instance for DEPARTMENT-SERVICE
public interface APIClient {
//(url = "http://localhost:8080" ,value = "DEPARTMENT-SERVICE"
    @GetMapping("api/Departments/getByCode/{code}")
    DepartmentDto getDepByCode(@PathVariable String code);
}
