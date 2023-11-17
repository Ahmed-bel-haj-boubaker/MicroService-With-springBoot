package net.javaguides.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto {
    private EmployeeDto employee;
    private DepartmentDto department;
    private List<EmployeeDto> employeeList;

    public void setEmployee(List<EmployeeDto> employeeDtoList) {
        this.employeeList = employeeDtoList;
    }
}
