package com.example.bank_management_project3.Service;

import com.example.bank_management_project3.Api.ApiException;
import com.example.bank_management_project3.Model.Account;
import com.example.bank_management_project3.Model.User;
import com.example.bank_management_project3.Repository.AccountRepository;
import com.example.bank_management_project3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

     // return all users  , ADMIN
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // USER
    public void updateUser(Integer userId, User updatedUser) {
        User user = userRepository.findUserById(userId);
       if(user==null){
           throw new ApiException("User not found");
       }
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());
         userRepository.save(user);
    }

    // ADMIN
    public void deleteUser(Integer userId) {
        User user = userRepository.findUserById(userId);
        if(user==null){
            throw new ApiException("User not found");
        }
        userRepository.delete(user);
    }

    // return user by id , ADMIN
    public User getUserById(Integer userId) {
        User user= userRepository.findUserById(userId);
        if(user==null){
            throw new ApiException("User not found");
        }
        return user;
    }

}
