package com.codingShuttle.springBoot.week2.SpringBootWeek2.controller;

import com.codingShuttle.springBoot.week2.SpringBootWeek2.dto.EmployeeDTO;
import com.codingShuttle.springBoot.week2.SpringBootWeek2.entity.EmployeeEntity;
import com.codingShuttle.springBoot.week2.SpringBootWeek2.service.EmployeeService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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


@GetMapping(path = "/{employeeId}")
public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
    Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
    return employeeDTO
            .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+id));
}

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age,
                                            @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeEntity inputEmp){
        return new ResponseEntity<>(employeeService.createNewEmployee(inputEmp), HttpStatus.CREATED);
    }



    @PutMapping(path = {"/employeeId"})
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO newEmployeeDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmpById(newEmployeeDTO, employeeId));
    }

    @DeleteMapping(path = {"/employeeId"})
    public ResponseEntity<Boolean> deleteEmp(@PathVariable Long employeeId){
        boolean gotDeleted = employeeService.deleteById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = {"/employeeId"})
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Objects> updates, @PathVariable Long employeeId){
        // Map<String, Objects> updates,
        //we use this is because, if someone wants to update the only some field so the other field may not change to null the stay same as before update
        //here String is the key where we want to update and the Object is the value which is update and all the data for update is in the updates variables.
        EmployeeDTO partialUpdatedEmployee = employeeService.updatePartialEmployeeById(employeeId, updates);
        if(partialUpdatedEmployee != null) return ResponseEntity.ok(partialUpdatedEmployee);
        return ResponseEntity.notFound().build();
    }



}