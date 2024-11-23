package com.codingShuttle.springBoot.week2.SpringBootWeek2.service;


import com.codingShuttle.springBoot.week2.SpringBootWeek2.configs.MapperConfig;
import com.codingShuttle.springBoot.week2.SpringBootWeek2.dto.EmployeeDTO;
import com.codingShuttle.springBoot.week2.SpringBootWeek2.entity.EmployeeEntity;
import com.codingShuttle.springBoot.week2.SpringBootWeek2.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MapperConfig mapperConfig;

    public EmployeeService(EmployeeRepository employeeRepository, MapperConfig mapperConfig) {
        this.employeeRepository = employeeRepository;
        this.mapperConfig = mapperConfig;
    }


    public EmployeeDTO findById(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);
        return mapperConfig.getModelMapper().map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> findAll() {
        List<EmployeeEntity> employeeEntity = employeeRepository.findAll();
        return employeeEntity
                .stream()
                .map(employeeEntity1 -> mapperConfig.getModelMapper().map(employeeEntity1, EmployeeDTO.class))
                .collect(Collectors.toList());

    }

    public EmployeeDTO saveEmp(EmployeeEntity inputEmp) {
        EmployeeEntity employeeEntity = mapperConfig.getModelMapper().map(inputEmp, EmployeeEntity.class);
        EmployeeEntity SavedemployeeEntity1 = employeeRepository.save(employeeEntity);
        return mapperConfig.getModelMapper().map(SavedemployeeEntity1, EmployeeDTO.class);
    }
}
