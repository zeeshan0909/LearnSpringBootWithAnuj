package com.codingShuttle.springBoot.week2.SpringBootWeek2.dto;

import java.time.LocalDate;
//this is our pojo class.
public class EmployeeDTO {
   private Long id;
   private String name;
   private String email;
   private Integer age;
   private LocalDate dataOfJoining;
   private boolean isActive;

   public EmployeeDTO(){

   }

    public EmployeeDTO(Long id, String name, String email, Integer age, LocalDate dataOfJoining, boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dataOfJoining = dataOfJoining;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDataOfJoining() {
        return dataOfJoining;
    }

    public void setDataOfJoining(LocalDate dataOfJoining) {
        this.dataOfJoining = dataOfJoining;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
