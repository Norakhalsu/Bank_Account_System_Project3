package com.example.bank_management_project3.Service;

import com.example.bank_management_project3.Api.ApiException;
import com.example.bank_management_project3.DTO.EmployeeDTO;
import com.example.bank_management_project3.Model.Employee;
import com.example.bank_management_project3.Model.User;
import com.example.bank_management_project3.Repository.EmployeeRepository;
import com.example.bank_management_project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;



     // ALL
    public void createEmployee(EmployeeDTO employee) {
        // 2 object [ user , employee]
        User user = new User();
        user.setUsername(employee.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
        user.setEmail(employee.getEmail());
        user.setName(employee.getName());
        user.setRole("EMPLOYEE");
        Employee employee1=new Employee();
        employee1.setPosition(employee.getPosition());
        employee1.setSalary(employee.getSalary());

       // User user=new User(null,employee.getUsername(),new BCryptPasswordEncoder().encode(employee.getPassword()),
      // employee.getName(),employee.getEmail(),"EMPLOYEE",employee1,null);
        employee1.setUser(user);
        userRepository.save(user);
        employeeRepository.save(employee1);
    }


    // spring config , ADMIN
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

   // authentication principle , EMPLOYEE
    public void updateEmployee(Integer userId, EmployeeDTO employee) {
        User user=userRepository.findUserById(userId);
       Employee employee1 = employeeRepository.findEmployeeById(userId);
        if (user == null) {
            throw new ApiException("Employee not found");
        }

        employee.setUsername(user.getUsername());
        employee.setPassword(user.getPassword());
        employee.setName(user.getName());
        employee.setEmail(user.getEmail());
        employee.setPosition(employee1.getPosition());
        employee.setSalary(employee1.getSalary());
        userRepository.save(user);
        employeeRepository.save(employee1);

//        user.setUsername(updatedEmployee.getUsername());
//        user.setPassword(updatedEmployee.getPassword());
//        user.setName(updatedEmployee.getName());
//        user.setEmail(updatedEmployee.getEmail());
//       // user.setSalary(updatedEmployee.getSalary());
//       // user.set
//        userRepository.save(user);

            // Update the employee details with the provided information
      //  employee.setPosition(updatedEmployee.getPosition());
        //employee.setSalary(updatedEmployee.getSalary());
      //  employeeRepository.save(employee);
    }



     // authentication principle -- EMPLOYEE
    public void deleteEmployee(Integer userId) {
        //User user=userRepository.findUserById(userId);

        Employee employee = employeeRepository.findEmployeeById(userId);
        if (employee == null) {
            throw new ApiException("Employee not found");
        }
        employeeRepository.delete(employee);
    }


    // ADMIN
    public Employee getEmployeeById(Integer employeeId) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        if (employee == null) {
            throw new ApiException("Employee not found");
        }
        return employee;
    }


}
