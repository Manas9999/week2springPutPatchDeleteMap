package com.week2.spirngmvc.SpringMVC.entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;

@Entity
//add lombok dependecny in order to use getter and setter annotations
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private Boolean isActive;
    private LocalDate dateOfJoining;
}
