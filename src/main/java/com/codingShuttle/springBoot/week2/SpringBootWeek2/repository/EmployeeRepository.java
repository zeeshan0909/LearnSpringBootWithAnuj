package com.codingShuttle.springBoot.week2.SpringBootWeek2.repository;

import com.codingShuttle.springBoot.week2.SpringBootWeek2.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
