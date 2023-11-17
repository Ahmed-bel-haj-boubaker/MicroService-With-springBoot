package net.javaguides.departmentservice.service;

import net.javaguides.departmentservice.dto.ApiResponseDto;
import net.javaguides.departmentservice.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto updatedDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> getAllDepartment();

    ApiResponseDto getDepartmentByID(Long idDep);

    void DeleteDep(Long idDep);

    DepartmentDto getDepartmentByCodeDep(String code);


}
