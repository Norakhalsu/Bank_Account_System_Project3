package com.example.bank_management_project3.Controller;


import com.example.bank_management_project3.DTO.CustomerDTO;
import com.example.bank_management_project3.Model.Customer;
import com.example.bank_management_project3.Model.User;
import com.example.bank_management_project3.Service.CustomerService;
import com.example.bank_management_project3.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

         @PostMapping("/add")// ALL
        public ResponseEntity createCustomer(@RequestBody CustomerDTO customer) {
             customerService.createCustomer(customer);
            return ResponseEntity.status(200).body("Customer created");
        }

        @GetMapping("/get-all")// ADMIN
        public ResponseEntity getAllCustomers() {
            return ResponseEntity.status(200).body(customerService.getAllCustomers());
        }


        @PutMapping("/update")// CUSTOMER
        public ResponseEntity updateCustomer(@AuthenticationPrincipal User user, @RequestBody Customer updatedCustomer) {
           customerService.updateCustomer(user.getId(), updatedCustomer);
            return ResponseEntity.status(200).body("Customer updated");
        }

        @DeleteMapping("/delete")// CUSTOMER
        public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user) {
            customerService.deleteCustomer(user.getId());
            return ResponseEntity.ok("Customer deleted successfully");
        }


       @GetMapping("/customer/{customerId}")//ADMIN
       public ResponseEntity getCustomerById(@PathVariable Integer customerId) {
        return ResponseEntity.status(200).body(customerService.getCustomerById(customerId));
      }

    @GetMapping("/list-accounts")// list user's accounts , CUSTOMER
    public ResponseEntity getCustomerAccounts(@AuthenticationPrincipal User user) {
        Set<Account> customerAccount = customerService.getUserAccounts(user.getId());
        return ResponseEntity.ok(customerAccount);
    }



}
