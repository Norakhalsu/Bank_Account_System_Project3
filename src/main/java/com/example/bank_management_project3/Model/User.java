package com.example.bank_management_project3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity@AllArgsConstructor @NoArgsConstructor@Setter @Getter
public class User implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username must be not empty ")
    @Size(min = 4, max = 10,message = "username Length must be between 4 and 10 characters")
    @Column(unique = true)
    private String username;

    @NotEmpty(message = "password must be not empty")
    @Size(min = 6 , message = "Length must be at least 6 characters")
    @Column(unique = true)
    private String password;

    @NotEmpty(message = "name must be not empty")
    @Size(min = 2, max = 20 , message = "Length must be between 2 and 20 characters")
    private String name;


    @Email(message = "email must be in valid format")
    @Column(unique = true)
    private String email;

    @Pattern(regexp="CUSTOMER|EMPLOYEE|ADMIN" , message = "Role must be [ CUSTOMER|EMPLOYEE|ADMIN ] only ")
    private String role;

    // -------------------------- Relations ------------
    @OneToOne(cascade = CascadeType.ALL ,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL ,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;

}
