package com.bridgelabz.employee_payroll.model;


import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "payroll_service")
public @Data class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column (name = "employeeId")
    private long employeeId;

    @Column(name="name")
    private String name;
    private int salary;

    private String gender;
    private LocalDate startDate;
    private String note;
    private String profilePic;

    @ElementCollection
    @CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "department")
    private List<String> departments;


    public Employee(){}
    public Employee(EmployeeDTO employeeDTO){
        this.updateEmployeePayrollData(employeeDTO);
    }
    public void updateEmployeePayrollData(EmployeeDTO employeeDTO){
        this.employeeId=employeeId;
        this.name= employeeDTO.name;
        this.salary= employeeDTO.salary;
        this.gender=employeeDTO.gender;
        this.startDate=employeeDTO.startDate;
        this.note=employeeDTO.note;
        this.profilePic=employeeDTO.profilePic;
        this.departments=employeeDTO.department;
    }

    public long getId(){
        return employeeId;
    }

    public void setId(long employeeId){
        this.employeeId= employeeId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name= name;
    }

    public int getSalary(){
        return salary;
    }
    public void setSalary(int salary){
        this.salary= salary;
    }
}
