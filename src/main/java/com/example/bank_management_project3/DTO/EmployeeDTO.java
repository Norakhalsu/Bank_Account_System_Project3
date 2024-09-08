package com.example.bank_management_project3.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {


    @NotEmpty(message = "username must be not empty ")
    @Size(min = 4, max = 10,message = "username Length must be between 4 and 10 characters")
    private String username;

    @NotEmpty(message = "password must be not empty")
    @Size(min = 6 , message = "Length must be at least 6 characters")
    private String password;

    @NotEmpty(message = "name must be not empty")
    @Size(min = 2, max = 20 , message = "Length must be between 2 and 20 characters")
    private String name;

    @Email(message = "email must be in valid format")
    private String email;

    @Pattern(regexp="CUSTOMER|EMPLOYEE|ADMIN" , message = "Role must be ' CUSTOMER|EMPLOYEE|ADMIN '  ")
    private String role;

    @NotEmpty(message = "position must be not empty")
    private String position;

    @NotNull(message = "salary must be not null")
    @Positive(message = "Salary must be positive number")
    private int salary;


}
