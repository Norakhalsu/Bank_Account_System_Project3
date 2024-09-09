package com.example.bank_management_project3.Controller;


import com.example.bank_management_project3.Model.Account;
import com.example.bank_management_project3.Model.User;
import com.example.bank_management_project3.Service.AccountService;
import com.example.bank_management_project3.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

        private final UserService userService;
        private final AccountService accountService;


        @GetMapping("/get-all")// ADMIN
        public ResponseEntity getAllUsers() {
            return ResponseEntity.status(200).body(userService.getAllUsers());

        }
    @PutMapping("/update")// USER
    public ResponseEntity updateUser(@AuthenticationPrincipal User user, @RequestBody User updatedUser) {
        userService.updateUser(user.getId(), updatedUser);
        return ResponseEntity.status(200).body("User Updated");
    }

    @DeleteMapping("/delete/{userId}")// ADMIN
    public ResponseEntity deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(200).body("User deleted successfully");
    }

        @GetMapping("/get-user/{userId}")// ADMIN
        public ResponseEntity getUserById(@PathVariable Integer userId) {
            return ResponseEntity.status(200).body(userService.getUserById(userId));
        }

    @GetMapping("/logout")
    public ResponseEntity logout() {
        return ResponseEntity.status(200).body(" logout");
    }

}
