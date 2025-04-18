package com.bridgelabz.employee_payroll.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDTO {
    private String name;
    private int salary;

    public EmployeeDTO(){}
    public EmployeeDTO(String name, int salary){
        this.name=name;
        this.salary=salary;
    }
}
