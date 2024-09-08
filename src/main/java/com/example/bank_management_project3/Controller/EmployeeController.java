package com.example.bank_management_project3.Controller;


import com.example.bank_management_project3.DTO.EmployeeDTO;
import com.example.bank_management_project3.Model.Employee;
import com.example.bank_management_project3.Model.User;
import com.example.bank_management_project3.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employee")
public class EmployeeController {

        private final EmployeeService employeeService;

        @PostMapping("/add")// ALL
        public ResponseEntity createEmployee(@RequestBody EmployeeDTO employee) {
            employeeService.createEmployee(employee);
            return ResponseEntity.status(200).body("Employee created successfully");
        }

        @GetMapping("/get-all")// ADMIN
        public ResponseEntity getAllEmployees() {
            List<Employee> employees = employeeService.getAllEmployees();
            return ResponseEntity.status(200).body(employees);
        }

    @PutMapping("/update")// update employee , EMPLOYEE
    public ResponseEntity updateEmployee(@AuthenticationPrincipal User user, @RequestBody EmployeeDTO updatedEmployee) {
        employeeService.updateEmployee(user.getId(),updatedEmployee);
        return ResponseEntity.status(200).body("Employee updated successfully");
    }

    @DeleteMapping("/delete")// delete employee , EMPLOYEE
    public ResponseEntity  deleteEmployee(@AuthenticationPrincipal User employeeId) {
        employeeService.deleteEmployee(employeeId.getId());
        return ResponseEntity.status(200).body("Employee deleted successfully");
    }

        @GetMapping("/get-employee/{employeeId}")// ADMIN
        public ResponseEntity  getEmployeeById(@PathVariable Integer employeeId) {
            return ResponseEntity.status(200).body(employeeService.getEmployeeById(employeeId));
        }






}
