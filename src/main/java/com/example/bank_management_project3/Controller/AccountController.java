package com.example.bank_management_project3.Controller;

import com.example.bank_management_project3.Api.ApiException;
import com.example.bank_management_project3.Model.Account;
import com.example.bank_management_project3.Model.Customer;
import com.example.bank_management_project3.Model.User;
import com.example.bank_management_project3.Repository.AccountRepository;
import com.example.bank_management_project3.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/add") //Customer
    public ResponseEntity createAccount(@AuthenticationPrincipal User user,@RequestBody Account account) {
        accountService.createAccount(user.getId(),account);
        return ResponseEntity.status(200).body("Account created");
    }

    @GetMapping("/get-all")// ADMIN
    public ResponseEntity getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.status(200).body(accounts);
    }

    @PutMapping("/update/{accountId}")// CUSTOMER
    public ResponseEntity<?> updateAccount(@AuthenticationPrincipal Customer customer, @PathVariable Integer accountId, @RequestBody Account account) {
        accountService.updateAccount(customer.getId(),accountId, account);
        return ResponseEntity.status(200).body("Account updated");
    }

    @DeleteMapping("/delete/{accountId}")// CUSTOMER
    public ResponseEntity<?> deleteAccount(@AuthenticationPrincipal Customer customer,@PathVariable Integer accountId) {
        accountService.deleteAccount(customer.getId(),accountId);
        return ResponseEntity.ok("Account deleted successfully");
    }

    @PutMapping("/activate/{accountId}")// Active a bank account , CUSTOMER
    public ResponseEntity<?> activateAccount(@AuthenticationPrincipal Customer customer,@PathVariable Integer accountId) {
        accountService.activateAccount(customer.getId(),accountId);
        return ResponseEntity.ok("Account activated successfully");
    }


    @GetMapping("/details/{accountId}") //view account details , CUSTOMER
    public ResponseEntity<?> getAccountDetails(@AuthenticationPrincipal Customer customer,@PathVariable Integer accountId) {
        return ResponseEntity.status(200).body(accountService.getAccountDetails(customer.getId(),accountId));
    }

    @PostMapping("/deposit/{accountId}")//Deposit money , CUSTOMER
    public ResponseEntity<String> deposit(@AuthenticationPrincipal Customer customer,@PathVariable Integer accountId, @RequestParam double amount) {
        accountService.deposit(customer.getId(),accountId, amount);
        return ResponseEntity.ok("Deposited " + amount + " successfully");
    }

    @PostMapping("/withdraw/{accountId}")//withdraw money , CUSTOMER
    public ResponseEntity withdraw(@AuthenticationPrincipal Customer customer, @PathVariable Integer accountId, @RequestParam double amount) {
        accountService.withdraw(customer.getId(),accountId, amount);
        return ResponseEntity.ok("Withdrawn " + amount + " successfully");
    }


    @PostMapping("/transfer")//Transfer funds between accounts , CUSTOMER
    public ResponseEntity transferFunds(@AuthenticationPrincipal Customer customer,@RequestBody Integer fromAccountId, @RequestBody Integer toAccountId, @RequestBody double amount) {
        accountService.transfer(customer.getId(),fromAccountId, toAccountId, amount);
        return ResponseEntity.status(200).body("Transfer successfully");
    }


    @PostMapping("/block/{accountId}")// Block bank account , ADMIN
    public ResponseEntity blockAccount(@PathVariable Integer accountId) {
        accountService.blockAccount(accountId);
        return ResponseEntity.ok("Account with ID " + accountId + " has been blocked");
    }



}