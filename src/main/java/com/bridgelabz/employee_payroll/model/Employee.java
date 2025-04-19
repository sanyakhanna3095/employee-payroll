package com.bridgelabz.employee_payroll.model;


import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "payroll_service")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "employeeId")
    private long employeeId;

    private String name;
    private int salary;
    private String gender;
    private String startDate;
    private String note;
    private String profilePic;

    @ElementCollection
    @CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "department")
    private List<String> departments;

    public Employee() {}

    public Employee(EmployeeDTO employeeDTO) {
        this.updateEmployeePayrollData(employeeDTO);
    }

    public void updateEmployeePayrollData(EmployeeDTO employeeDTO) {
        this.name = employeeDTO.getName();
        this.salary = employeeDTO.getSalary();
        this.gender = employeeDTO.getGender();
        this.startDate = String.valueOf(employeeDTO.getStartDate());
        this.note = employeeDTO.getNote();
        this.profilePic = employeeDTO.getProfilePic();
        this.departments = employeeDTO.getDepartment();
    }
}
