package net.javaguides.departmentservice.service.imp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.javaguides.departmentservice.dto.ApiResponseDto;
import net.javaguides.departmentservice.dto.DepartmentDto;
import net.javaguides.departmentservice.dto.EmployeeDto;
import net.javaguides.departmentservice.entity.Department;
import net.javaguides.departmentservice.exception.ResourceNotFoundException;
import net.javaguides.departmentservice.mapper.AutoDepartmentMapper;
import net.javaguides.departmentservice.repository.DepartmentRepository;
import net.javaguides.departmentservice.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

   final DepartmentRepository departmentRepository;

  final RestTemplate restTemplate;

    final WebClient webClient;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = AutoDepartmentMapper.MAPPER.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto updatedDepartment(DepartmentDto departmentDto) {
        Department department = AutoDepartmentMapper.MAPPER.mapToDepartment(departmentDto);

        Department existingDep = departmentRepository.findById(department.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("department","id",department.getId())
        );


            existingDep.setDepartmentCode(department.getDepartmentCode());
            existingDep.setDepartmentDescription(department.getDepartmentDescription());
            existingDep.setDepartmentName(department.getDepartmentName());
            Department updatedDep = departmentRepository.save(existingDep);

            return AutoDepartmentMapper.MAPPER.mapToDepartmentDto(updatedDep);

    }

    @Override
    public List<DepartmentDto> getAllDepartment() {
      List<Department> departments=  departmentRepository.findAll();
        return departments.stream().map(
                AutoDepartmentMapper.MAPPER::mapToDepartmentDto
        ).collect(Collectors.toList());
    }

    @Override
    public ApiResponseDto getDepartmentByID(Long idDep) {
        Department department = departmentRepository.findById(idDep).orElseThrow(
                () -> new ResourceNotFoundException("department", "id", idDep)
        );
        System.out.println(1);
        ResponseEntity<EmployeeDto[]> responseEntity = restTemplate.getForEntity(
                "http://localhost:8081/api/Employees/getEmployeebByDep/" + department.getDepartmentCode(),
                EmployeeDto[].class
        );
        System.out.println(2);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            EmployeeDto[] employeeDtos = responseEntity.getBody();

            // Map the array of EmployeeDto to a List
            List<EmployeeDto> employeeDtoList = Arrays.asList(employeeDtos);

            // Map Department to DepartmentDto using a mapper (AutoDepartmentMapper.MAPPER)
            DepartmentDto departmentDto = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);

            // Create ApiResponseDto and set the employee list and department details
            ApiResponseDto apiResponseDto = new ApiResponseDto();
            apiResponseDto.setDepartment(departmentDto);
            apiResponseDto.setEmployee(employeeDtoList); // Set the list directly without casting

            return apiResponseDto;
        }else
            return null;

    }
    @Override
    public void DeleteDep(Long idDep) {
       Optional<Department> department = departmentRepository.findById(idDep);

        if (department.isPresent()){
            departmentRepository.deleteById(idDep);
        }else {
            throw new ResourceNotFoundException("department","id",idDep);
        }

    }

    @Override
    public DepartmentDto getDepartmentByCodeDep(String code) {
        Department department = departmentRepository.findDepartmentByDepartmentCode(code);
        return    AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);

    }


}
