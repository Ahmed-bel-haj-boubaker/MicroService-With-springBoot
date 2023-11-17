package net.javaguides.employeeservice.service.impl;

import lombok.RequiredArgsConstructor;
import net.javaguides.employeeservice.dto.ApiResponseDto;
import net.javaguides.employeeservice.dto.DepartmentDto;
import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import net.javaguides.employeeservice.exception.EmailNotFoundException;
import net.javaguides.employeeservice.exception.ResourceNotFoundException;
import net.javaguides.employeeservice.mapper.AutoEmployeeMapper;
import net.javaguides.employeeservice.repository.EmployeeRepository;
import net.javaguides.employeeservice.service.APIClient;
import net.javaguides.employeeservice.service.ServiceEmployee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ServiceEmployeeImpl implements ServiceEmployee {
     final EmployeeRepository employeeRepository;

  //   final RestTemplate restTemplate;

   // final WebClient  webClient;

    final APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {


        System.out.println(employeeDto.getDepartmentCode());
        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);
        String id = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto).getDepartmentCode();
        System.out.println(id);

        Employee savedEmployee = employeeRepository.save(employee);

        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);



    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);

        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Employee","id", employee.getId())
        );


        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setEmail(employee.getEmail());
        Employee updatedEmp = employeeRepository.save(existingEmployee);

        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(updatedEmp);

    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employee = employeeRepository.findAll();

        return employee.stream().map(
                AutoEmployeeMapper.MAPPER::mapToEmployeeDto
        ).collect(Collectors.toList());

    }

    @Override
    public ApiResponseDto getEmployeeById(Long idEmp) {
           Employee employee  = employeeRepository.findById(idEmp).orElseThrow(
                   ()-> {
                       throw new ResourceNotFoundException("employee", "id", idEmp);
                   }
           );
      //   ResponseEntity<DepartmentDto> responseEntity=  restTemplate.getForEntity("http://localhost:8080/api/Departments/getByCode/"+ employee.getDepartmentCode(), DepartmentDto.class);
       //  DepartmentDto departmentDto = responseEntity.getBody();
        /*   DepartmentDto departmentDto = webClient.get()
                   .uri("http://localhost:8080/api/Departments/getByCode/"+ employee.getDepartmentCode())
                   .retrieve()
                   .bodyToMono(DepartmentDto.class)
                   .block();*/

     DepartmentDto departmentDto =   apiClient.getDepByCode(employee.getDepartmentCode());
         EmployeeDto employeeDto =AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
           return apiResponseDto;

    }

/*
*     EmployeeDto employeeDto = new EmployeeDto(
                 employee.getId(),
                 employee.getFirstName(),
                 employee.getLastName(),
                 employee.getEmail(),
                 employee.getDepartmentCode()
         );
         * */

    @Override
    public EmployeeDto deleteEmployee(Long id) {
        Optional<Employee> department = employeeRepository.findById(id);

        if (department.isPresent()){
            employeeRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException();
        }
        return null;
    }



    @Override
    public EmployeeDto getEmplByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(
                ()-> {
                    throw new EmailNotFoundException("Email not found");
                }

        );

        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
    }

        @Override
        public List<EmployeeDto> getEmployeeByDep(String codeDep) {
            List<Employee> employees = employeeRepository.findEmployeesByDepartmentCode(codeDep);

            if (!employees.isEmpty()) {
               return  employees.stream()
                       .map(emp -> AutoEmployeeMapper.MAPPER.mapToEmployeeDto(emp))
                       .collect(Collectors.toList() );
            } else {
                return null;
            }
        }
}
