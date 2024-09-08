package com.example.bank_management_project3.Service;

import com.example.bank_management_project3.Api.ApiException;
import com.example.bank_management_project3.DTO.CustomerDTO;
import com.example.bank_management_project3.Model.Customer;
import com.example.bank_management_project3.Model.Employee;
import com.example.bank_management_project3.Model.User;
import com.example.bank_management_project3.Repository.CustomerRepository;
import com.example.bank_management_project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service@RequiredArgsConstructor
public class CustomerService {



    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    // ALL
        public void createCustomer(CustomerDTO customerDTO) {
            // 2 object [ user , customer]
            User user = new User();
            user.setUsername(customerDTO.getUsername());
            user.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
            user.setEmail(customerDTO.getEmail());
            user.setRole("CUSTOMER");
            user.setName(customerDTO.getName());
            Customer customer=new Customer();
            customer.setPhoneNumber(customerDTO.getPhoneNumber());

            customer.setUser(user);
            userRepository.save(user);
            customerRepository.save(customer);
        }

        // ADMIN
        public List<Customer> getAllCustomers() {
            return customerRepository.findAll();
        }


        // CUSTOMER
        public void updateCustomer(Integer customerId, Customer updatedCustomer) {
            Customer customer = customerRepository.findCustomerById(customerId);
            if(customer == null) {
                throw new ApiException("Customer not found");
            }
            customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            customerRepository.save(customer);
        }

        // CUSTOMER
        public void deleteCustomer(Integer customerId) {
            Customer customer = customerRepository.findCustomerById(customerId);
            if(customer == null) {
                throw new ApiException("Customer not found");
            }
            customerRepository.delete(customer);
        }

    // ADMIN
    public Customer getCustomerById(Integer customerId) {
        Customer customer= customerRepository.findCustomerById(customerId);
        if(customer == null) {
            throw new ApiException("Customer not found");
        }
        return customer;
    }




}
