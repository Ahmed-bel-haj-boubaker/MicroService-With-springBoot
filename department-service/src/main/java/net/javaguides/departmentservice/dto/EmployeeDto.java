package net.javaguides.departmentservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto {


    Long id;
    String firstName;
    String lastName;
    String email;
    String departmentCode;
}
