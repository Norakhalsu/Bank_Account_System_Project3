package com.example.bank_management_project3.Service;

import com.example.bank_management_project3.Api.ApiException;
import com.example.bank_management_project3.Model.Account;
import com.example.bank_management_project3.Model.Customer;
import com.example.bank_management_project3.Model.User;
import com.example.bank_management_project3.Repository.AccountRepository;
import com.example.bank_management_project3.Repository.CustomerRepository;
import com.example.bank_management_project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Set;

@Service @RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;


    // all end point must check if user exist , CUSTOMER
    public void createAccount(Integer customerId,Account account) {
        Customer customer=customerRepository.findCustomerById(customerId);
        if(customer==null) {
            throw new ApiException("Customer not found");
        }
        accountRepository.save(account);
    }


    // ADMIN
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }



    // CUSTOMER
    public void updateAccount(Integer customerId ,Integer accountId, Account updatedAccount) {
        Customer customer=customerRepository.findCustomerById(customerId);
        if(customer==null) {
            throw new ApiException("Customer not found");
        }
        Account account = accountRepository.findAccountById(accountId);
        if(account == null){
            throw new ApiException("Account not found");
        }
        account.setAccountNumber(updatedAccount.getAccountNumber());
        account.setBalance(updatedAccount.getBalance());
       accountRepository.save(account);
    }



    // CUSTOMER
    public void deleteAccount(Integer customerId,Integer accountId) {
        Customer customer=customerRepository.findCustomerById(customerId);
        if(customer==null) {
            throw new ApiException("Customer not found");
        }
        Account account = accountRepository.findAccountById(accountId);
        if(account == null){
            throw new ApiException("Account not found");
        }
        accountRepository.delete(account);
    }


    // Active account , CUSTOMER
    public void activateAccount(Integer customerId,Integer accountId) {
        Customer customer=customerRepository.findCustomerById(customerId);
        if(customer==null) {
            throw new ApiException("Customer not found");
        }
        Account account = accountRepository.findAccountById(accountId);
               if(account == null){
                   throw new ApiException("Account not found");
               }
        if (!account.isActive()) {
            account.setActive(true);
            accountRepository.save(account);
     }


    }


    // get account details , CUSTOMER
    public Account getAccountDetails(Integer customerId,Integer accountId) {
        Customer customer=customerRepository.findCustomerById(customerId);
        if(customer==null) {
            throw new ApiException("Customer not found");
        }
      Account account= accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
            return account;
        }


        // CUSTOMER
      public void deposit(Integer customerId,Integer accountId, double amount) {
        Customer customer=customerRepository.findCustomerById(customerId);
        if(customer==null) {
            throw new ApiException("Customer not found");
        }
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (account.isActive()!=true){
            throw new ApiException("Account is not active");
        }
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        accountRepository.save(account);
    }


    // CUSTOMER
    public void withdraw(Integer customerId,Integer accountId, double amount) {
        Customer customer=customerRepository.findCustomerById(customerId);
        if(customer == null){
            throw new ApiException("Customer not found");
        }
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (account.isActive()!=true){
            throw new ApiException("Account is not active");
        }
        if (account.getBalance() >= amount) {
            double newBalance = account.getBalance() - amount;
            account.setBalance(newBalance);
            accountRepository.save(account);
        }
    }



    // CUSTOMER
    public void transfer(Integer customerId,Integer fromAccountId, Integer toAccountId, double amount) {
        Customer customer=customerRepository.findCustomerById(customerId);
        if(customer==null) {
            throw new ApiException("Customer not found");
        }
        Account fromAccount = accountRepository.findAccountById(fromAccountId);
        Account toAccount = accountRepository.findAccountById(toAccountId);

        if(fromAccount ==null){
            throw new ApiException("Account not found");
        }

        if(toAccount ==null){
            throw new ApiException("Account not found");
        }

        if (fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);

            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);
    }
    }


     // ADMIN
    public void blockAccount(Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        account.setActive(false); // Set the account status to inactive (blocked)
        accountRepository.save(account);
    }

}
