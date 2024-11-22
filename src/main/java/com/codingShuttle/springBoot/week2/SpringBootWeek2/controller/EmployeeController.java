package com.codingShuttle.springBoot.week2.SpringBootWeek2.controller;

import com.codingShuttle.springBoot.week2.SpringBootWeek2.entity.EmployeeEntity;
import com.codingShuttle.springBoot.week2.SpringBootWeek2.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable Long employeeId){
        return employeeRepository.findById(employeeId).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployee(@RequestParam(required = false) Integer age,
                                               @RequestParam(required = false) String sortBy){
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity getAllEmp(@RequestBody EmployeeEntity inputEmp){
        return employeeRepository.save(inputEmp);
    }
    @PutMapping
    public String updateEmp(){
        return "this is put and updateEmp";
    }

    @DeleteMapping
    public String deleteEmp(){
        return "this is deleteEmp ";
    }
}
