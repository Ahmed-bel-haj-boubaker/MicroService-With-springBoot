package net.javaguides.employeeservice.mapper;


import net.javaguides.employeeservice.dto.EmployeeDto;
import net.javaguides.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoEmployeeMapper {
AutoEmployeeMapper MAPPER = Mappers.getMapper(AutoEmployeeMapper.class);
   EmployeeDto mapToEmployeeDto(Employee employee);

   Employee mapToEmployee(EmployeeDto employeeDto);


}
