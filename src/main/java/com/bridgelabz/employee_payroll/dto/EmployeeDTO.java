package com.bridgelabz.employee_payroll.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDTO {
    @NotEmpty(message = "Employee name can't be null")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid name")
    private String name;

    @Min(value = 500, message = "Min wage should be more than Rs.500")
    private int salary;

    public EmployeeDTO(){}
    public EmployeeDTO(String name, int salary){
        this.name=name;
        this.salary=salary;
    }

    @Override
    public String toString(){
        return "name: "+ name+ ", salary: Rs."+salary;
    }
}
