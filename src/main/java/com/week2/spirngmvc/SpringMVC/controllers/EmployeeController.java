package com.week2.spirngmvc.SpringMVC.controllers;

import com.week2.spirngmvc.SpringMVC.dto.EmployeeDTO;
import com.week2.spirngmvc.SpringMVC.entities.EmployeeEntity;
import com.week2.spirngmvc.SpringMVC.respositories.EmployeeRepository;
import com.week2.spirngmvc.SpringMVC.services.EmployeeService;
import com.week2.spirngmvc.SpringMVC.services.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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


    //    @GetMapping("/{employeeID}")
//    public Optional<EmployeeDTO> getEmployeeByID(@PathVariable(name="employeeID") Long Id){
//        return employeeService.getEmployeeByID(Id);
//    }
    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    //    @GetMapping
//    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false,name = "inputAge") Integer age, @RequestParam(required = false)String sortBy){
//        return employeeService.getAllEmployees();
//    }
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                             @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee) {
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    @PutMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeID) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeID, employeeDTO));
    }

    @DeleteMapping("/{EmployeeID}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long EmployeeID) {
        boolean gotDeleted= employeeService.deleteEmployeeById(EmployeeID);
        if(gotDeleted) return  ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeID}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                                 @PathVariable Long employeeID) {
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeID, updates);
        if (employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }


}