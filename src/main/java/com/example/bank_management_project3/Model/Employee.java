package com.example.bank_management_project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter@Entity@AllArgsConstructor@NoArgsConstructor
public class Employee {

        @Id
       //@GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(columnDefinition = "varchar(30) not null")
        private String position;


        @Column(columnDefinition = "int not null")
        private int salary;


        // ----------------- Relations --------
        @OneToOne
        @JsonIgnore
        private User user;

    }
