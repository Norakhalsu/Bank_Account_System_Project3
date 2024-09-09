package com.example.bank_management_project3.Config;


import com.example.bank_management_project3.Service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

      private final MyUserService myUserService;


        @Bean // Authentication : check [Log in] user in database
        public DaoAuthenticationProvider daoAuthenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(myUserService);
            provider.setPasswordEncoder(new BCryptPasswordEncoder());
            return provider;
        }


        // Autherisation
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .authenticationProvider(daoAuthenticationProvider()).authorizeRequests()


                  
                // user controller
                    .requestMatchers("/api/v1/user/get-all").hasAuthority("ADMIN")
                    .requestMatchers("/api/v1/user/update").hasAuthority("USER")
                    .requestMatchers("/api/v1/user/delete/{userId}").hasAuthority("ADMIN")
                    .requestMatchers("/api/v1/user/get-user/{userId}").hasAuthority("ADMIN")

                    // Employee
                    .requestMatchers("/api/v1/employee/add").permitAll()
                    .requestMatchers("/api/v1/employee/get-all").hasAuthority("ADMIN")// get all
                    .requestMatchers("/api/v1/employee/update/{employeeId}").hasAuthority("EMPLOYEE")
                    .requestMatchers("/api/v1/employee/delete/{employeeId}").hasAuthority("EMPLOYEE")
                    .requestMatchers("/api/v1/employee/get-employee/{employeeId}").hasAuthority("ADMIN")//get one employee

                    // Customer
                    .requestMatchers("/api/v1/customer/add").permitAll()
                    .requestMatchers("/api/v1/customer/get-all").hasAuthority("ADMIN")
                    .requestMatchers("/api/v1/customer/update").hasAuthority("CUSTOMER")
                    .requestMatchers("/api/v1/customer/delete").hasAuthority("Customer")
                    .requestMatchers("/api/v1/customer/{customerId}").hasAuthority("ADMIN")
                    .requestMatchers("api/v1/customer/list-accounts").hasAuthority("CUSTOMER")


                    // Account
                    .requestMatchers("/api/v1/account/add").hasAuthority("CUSTOMER")
                    .requestMatchers("/api/v1/account/get-all").hasAuthority("ADMIN")
                    .requestMatchers("/api/v1/account/update/{accountId}").hasAuthority("CUSTOMER")
                    .requestMatchers("/api/v1/account/delete/{accountId}").hasAuthority("CUSTOMER")
                    .requestMatchers("/api/v1/account/activate/{accountId}").hasAuthority("ADMIN")
                    .requestMatchers("/api/v1/account/details/{accountId}").hasAuthority("CUSTOMER")
                    .requestMatchers("/api/v1/account/deposit/{accountId}").hasAuthority("CUSTOMER")
                    .requestMatchers("/api/v1/account/withdraw/{accountId}").hasAuthority("CUSTOMER")
                    .requestMatchers("/api/v1/account/transfer/{accountId}").hasAuthority("CUSTOMER")
                    .requestMatchers("/api/v1/account/block/{accountId}").hasAuthority( "ADMIN")
                    .anyRequest().authenticated()
                    .and()


                    .logout().logoutUrl("/api/v1/user/logout")
                    .deleteCookies("JSESSIONID").invalidateHttpSession(true)
                    .and()
                    .httpBasic();
            return http.build();
        }



      

    }




