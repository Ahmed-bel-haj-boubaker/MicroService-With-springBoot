package net.javaguides.departmentservice.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.javaguides.departmentservice.dto.ApiResponseDto;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.entity.Department;
import net.javaguides.departmentservice.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Departments")
public class DepartmentController {

  final DepartmentService departmentService;

    @PostMapping
    ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
          DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);
          return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<DepartmentDto>> getAllDepartment(){
        List<DepartmentDto> departmentDto = departmentService.getAllDepartment();
        return new ResponseEntity<>(departmentDto,HttpStatus.OK);
    }

    @GetMapping("/getById/{idDep}")
    ResponseEntity<ApiResponseDto> getDepById(@PathVariable Long idDep){
        ApiResponseDto department = departmentService.getDepartmentByID(idDep);
        return new ResponseEntity<>(department,HttpStatus.OK);
    }
    @GetMapping("/getByCode/{code}")
    ResponseEntity<DepartmentDto> getDepByCode(@PathVariable String code){
        DepartmentDto departmentDto = departmentService.getDepartmentByCodeDep(code);
        return new ResponseEntity<>(departmentDto,HttpStatus.OK);
    }

    /*@PutMapping("/updateDep/{idDep}")
    ResponseEntity<DepartmentDto> updateDep(@PathVariable Long idDep,@RequestBody DepartmentDto departmentDto){
             DepartmentDto departmentDto1 = departmentService.getDepartmentByID(idDep).getDepartment();
             departmentDto.setId(departmentDto1.getId());
             DepartmentDto updatedDep = departmentService.updatedDepartment(departmentDto);
             return new ResponseEntity<>(updatedDep,HttpStatus.OK);

    }*/

    @DeleteMapping("/deleteDep/{idDep}")
    ResponseEntity<String> deleteDepartment(@PathVariable Long idDep){
       departmentService.DeleteDep(idDep);
        return new ResponseEntity<>("Department with id = " + idDep + " is deleted", HttpStatus.OK);
    }
}
