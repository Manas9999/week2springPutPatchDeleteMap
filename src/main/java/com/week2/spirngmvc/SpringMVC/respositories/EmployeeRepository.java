package com.week2.spirngmvc.SpringMVC.respositories;

import com.week2.spirngmvc.SpringMVC.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//all the basic crud operations will be defined by jpa itslef
@Repository
public interface EmployeeRepository extends  JpaRepository<EmployeeEntity, Long> {
}
