package net.javaguides.employeeservice.repository;

import net.javaguides.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    Optional<Employee> findByEmail(String email);

    void deleteByEmail(String email);

    List<Employee> findEmployeesByDepartmentCode(String code);
}
