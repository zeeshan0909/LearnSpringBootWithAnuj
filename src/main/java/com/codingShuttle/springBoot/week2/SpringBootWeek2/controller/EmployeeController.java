package com.codingShuttle.springBoot.week2.SpringBootWeek2.controller;

import com.codingShuttle.springBoot.week2.SpringBootWeek2.dto.EmployeeDTO;
import com.codingShuttle.springBoot.week2.SpringBootWeek2.entity.EmployeeEntity;
import com.codingShuttle.springBoot.week2.SpringBootWeek2.repository.EmployeeRepository;
import com.codingShuttle.springBoot.week2.SpringBootWeek2.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

//    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

//    public EmployeeController(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return employeeService.findById(employeeId);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployee(@RequestParam(required = false) Integer age,
                                               @RequestParam(required = false) String sortBy){
        return employeeService.findAll();
    }

    @PostMapping
    public EmployeeDTO getAllEmp(@RequestBody EmployeeEntity inputEmp){
        return employeeService.saveEmp(inputEmp);
    }
    @PutMapping
    public EmployeeDTO updateEmp(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long empId){
        return employeeService.updateEmpById(employeeDTO, empId);
    }

    @DeleteMapping
    public String deleteEmp(){
        return "this is deleteEmp ";
    }
}
