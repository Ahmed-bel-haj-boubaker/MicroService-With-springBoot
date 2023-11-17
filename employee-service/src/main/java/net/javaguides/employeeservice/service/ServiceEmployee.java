package net.javaguides.employeeservice.service;

import net.javaguides.employeeservice.dto.ApiResponseDto;
import net.javaguides.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface ServiceEmployee {

    EmployeeDto saveEmployee( EmployeeDto employeeDto);

    EmployeeDto updateEmployee( EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployee();

    ApiResponseDto getEmployeeById(Long idEmp);

    EmployeeDto deleteEmployee(Long id);

    EmployeeDto getEmplByEmail(String email);

    List<EmployeeDto> getEmployeeByDep(String codeDep);
}
