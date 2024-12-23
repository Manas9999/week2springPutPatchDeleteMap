package com.week2.spirngmvc.SpringMVC.controllers;

import com.week2.spirngmvc.SpringMVC.dto.EmployeeDTO;
import com.week2.spirngmvc.SpringMVC.entities.EmployeeEntity;
import com.week2.spirngmvc.SpringMVC.respositories.EmployeeRepository;
import com.week2.spirngmvc.SpringMVC.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping("/getSecreteMessage")
//    public String getMySuperSecretMessage(){
//        return "Secrete message : you are awesome!";
//    }

    //meaningful mapping example
//    @GetMapping("/employees/{employeeID}")
//    public EmployeeDTO getEmployeeByID(@PathVariable Long employeeID){
//        return new EmployeeDTO(employeeID,"Manas","m8487909@gmail.com",24, LocalDate.of(2024,12,12),true);
//    }
//
//    //required false means get request will work if w not provide the input also as we marked it as optional
//    //where as path variable is mandatory
//    @GetMapping(path = "/employees")
//    public String getAllEmployees(@RequestParam(required = false) Integer age,@RequestParam(required = false)String sortBy){
//        return  "Hi all my age is "+age +" "+sortBy;
//    }

    //now we will use requestMapping to map entity in controller
   /* @GetMapping("/{employeeID}")
    public EmployeeDTO getEmployeeByID(@PathVariable Long employeeID){
        return new EmployeeDTO(employeeID,"Manas","m8487909@gmail.com",24, LocalDate.of(2024,12,12),true);
    }

    //required fale means get request will work if w not provide the input also as we marked it as optional
    //where as path variable is mandatory
    @GetMapping
    public String getAllEmployees(@RequestParam(required = false) Integer age,@RequestParam(required = false)String sortBy){
        return  "Hi all my age is "+age +" "+sortBy;
    }

    */

    //now to use different path variable name we have to use name attribute with @pathvariable annotation
//    @GetMapping("/{employeeID}")
//    public EmployeeDTO getEmployeeByID(@PathVariable(name="employeeID") Long ID){
//        return new EmployeeDTO(ID,"Manas","m8487909@gmail.com",24, LocalDate.of(2024,12,12),true);
//    }
//    @GetMapping
//    public String getAllEmployees(@RequestParam(required = false,name = "inputAge") Integer age,@RequestParam(required = false)String sortBy){
//        return  "Hi all my age is "+age +" "+sortBy;
//    }

    //we cannot hit below post request as we cannot do through browser.we can only get get request

    //to do we have to mimmic front end or esle we have postman to make post request
  /*  @PostMapping
    public  String createNewEmployee(){
        return "this is post requesr";
    }

    @PutMapping
    public  String updateEmployeeById(){
        return "updating employee";
    }*/

    //how to pass complete body means employee details to create a new employee
    //goto postman body and select json type and post
//    @PostMapping
//    public  EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//      inputEmployee.setId(102L);
//      return inputEmployee;
//    }


    //now lets perform crud operation using controller . we should not call repository from controller it self as it
    //is not the standard but we can do. we have entity and employee repository so lets perfomr crud operations using controller
    //but leter we will make a serverice layer to interact with repository layer

    private final EmployeeService employeeService;



    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{employeeID}")
    public EmployeeDTO getEmployeeByID(@PathVariable(name="employeeID") Long Id){
        return employeeService.getEmployeeByID(Id);
    }
    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false,name = "inputAge") Integer age, @RequestParam(required = false)String sortBy){
        return employeeService.getAllEmployees();
    }
    @PostMapping
    public  EmployeeDTO createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }

//    @PutMapping
//    public  String updateEmployeeById(){
//        return "updating employee";
//    }

}