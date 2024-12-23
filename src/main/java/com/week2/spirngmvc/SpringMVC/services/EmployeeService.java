package com.week2.spirngmvc.SpringMVC.services;

import com.week2.spirngmvc.SpringMVC.dto.EmployeeDTO;
import com.week2.spirngmvc.SpringMVC.entities.EmployeeEntity;
import com.week2.spirngmvc.SpringMVC.respositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeByID(Long id) {
        EmployeeEntity employeeEntity=employeeRepository.findById(id).orElse(null);

        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity>employeeEntities=employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }
    public  EmployeeDTO createNewEmployee(EmployeeEntity employeeDTO){
        //we have to convert the dto to entity inrder to save in the repository
        //here we can perform authorization like user is admin or not
        //loggin also
        EmployeeEntity employeeEntity=modelMapper.map(employeeDTO,EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity=employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }
}
