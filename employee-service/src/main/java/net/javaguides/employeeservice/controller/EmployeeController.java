package net.javaguides.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import net.javaguides.employeeservice.dto.ApiResponseDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.service.ServiceEmployee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Employees")
public class EmployeeController {
    final ServiceEmployee serviceEmployee;

    @PostMapping
    ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){

        EmployeeDto savedEmp = serviceEmployee.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmp, HttpStatus.OK);
    }


 /*   @PutMapping("/updateEmp/{id}")
    ResponseEntity<EmployeeDto>  updateEmployee( @RequestBody EmployeeDto employeeDto,@PathVariable Long id){
        EmployeeDto employeeByEmail = serviceEmployee.getEmployeeById(id);
        employeeDto.setId(employeeByEmail.getId());
        EmployeeDto updatedemployeeDto = serviceEmployee.updateEmployee(employeeDto);
        return new ResponseEntity<>(updatedemployeeDto,HttpStatus.OK);
    }*/

    @GetMapping
    ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employeeDtoList = serviceEmployee.getAllEmployee();
        return new ResponseEntity<>(employeeDtoList,HttpStatus.OK);
    }

    @GetMapping("/getEmployeebById/{idEmp}")
    ResponseEntity<ApiResponseDto> getEmployeebById(@PathVariable  Long idEmp){
        ApiResponseDto apiResponseDto = serviceEmployee.getEmployeeById(idEmp);
        return new ResponseEntity<>(apiResponseDto,HttpStatus.OK);
    }
    @GetMapping("/getEmployeebByDep/{code}")
    ResponseEntity<List<EmployeeDto>> getEmployeebById(@PathVariable String code){
        List<EmployeeDto> employeeDto = serviceEmployee.getEmployeeByDep(code);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }

    @GetMapping("/getEmployeebByEmail/{email}")
    ResponseEntity<EmployeeDto> getEmployeebByEmail(@PathVariable  String email){
       EmployeeDto employeeDto = serviceEmployee.getEmplByEmail(email);
       return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable Long id){
        EmployeeDto employeeDto = serviceEmployee.deleteEmployee(id);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }

}
