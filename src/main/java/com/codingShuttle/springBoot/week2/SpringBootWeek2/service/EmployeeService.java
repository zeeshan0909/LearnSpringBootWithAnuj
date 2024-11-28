package com.codingShuttle.springBoot.week2.SpringBootWeek2.service;


import com.codingShuttle.springBoot.week2.SpringBootWeek2.configs.MapperConfig;
import com.codingShuttle.springBoot.week2.SpringBootWeek2.dto.EmployeeDTO;
import com.codingShuttle.springBoot.week2.SpringBootWeek2.entity.EmployeeEntity;
import com.codingShuttle.springBoot.week2.SpringBootWeek2.repository.EmployeeRepository;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MapperConfig mapperConfig;

    public EmployeeService(EmployeeRepository employeeRepository, MapperConfig mapperConfig) {
        this.employeeRepository = employeeRepository;
        this.mapperConfig = mapperConfig;
    }





    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(employeeEntity -> mapperConfig.getModelMapper().map(employeeEntity, EmployeeDTO.class));
    }





    public List<EmployeeDTO> findAll() {
        List<EmployeeEntity> employeeEntity = employeeRepository.findAll();
        return employeeEntity
                .stream()
                .map(employeeEntity1 -> mapperConfig.getModelMapper().map(employeeEntity1, EmployeeDTO.class))
                .collect(Collectors.toList());

    }

    public EmployeeDTO createNewEmployee(EmployeeEntity inputEmp) {
        EmployeeEntity employeeEntity = mapperConfig.getModelMapper().map(inputEmp, EmployeeEntity.class);
        EmployeeEntity SavedemployeeEntity1 = employeeRepository.save(employeeEntity);
        return mapperConfig.getModelMapper().map(SavedemployeeEntity1, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmpById(EmployeeDTO employeeDTO, Long empId) {
        isExist(empId);
        EmployeeEntity empEntity = mapperConfig.getModelMapper().map(employeeDTO, EmployeeEntity.class);
        empEntity.setId(empId);
        EmployeeEntity savedEmpEntity = employeeRepository.save(empEntity);
        return mapperConfig.getModelMapper().map(savedEmpEntity, EmployeeDTO.class);
    }

    public boolean isExist(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }


    public boolean deleteById(Long empId) {
        if(!isExist(empId)){
            return false;
        }
         employeeRepository.deleteById(empId);
        return true;
    }


    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Objects> updates) {
        if(!isExist(employeeId)){
            return null;
        }
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return mapperConfig.getModelMapper().map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
