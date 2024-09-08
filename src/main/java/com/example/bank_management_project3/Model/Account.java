package com.example.bank_management_project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity@Setter@Getter@NoArgsConstructor@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "\\d{4}-\\d{4}-\\d{4}-\\d{4}" , message = "- Must follow a specific format (e.g., 'XXXX-XXXX-XXXX-XXXX' ")
    private String accountNumber;

    @NotNull(message = "Salary must be not null")
    @Positive(message = "Balance must be positive")
    private double balance;

    private boolean isActive = false;

     // ------------------- Relations ---------------
    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
