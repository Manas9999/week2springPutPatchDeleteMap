package com.week2.spirngmvc.SpringMVC.entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    @JsonProperty("isActive")
    private Boolean isActive;
    private LocalDate dateOfJoining;
    private String role;
    private Double salary;
//    @Version
//    private Long version;


//    public void setId(Long id) {
//        this.id = id;
//    }
}
