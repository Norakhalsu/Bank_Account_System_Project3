package com.example.bank_management_project3.Repository;

import com.example.bank_management_project3.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer id);
    Account findAccountsByCustomerId(Integer userId);
}
