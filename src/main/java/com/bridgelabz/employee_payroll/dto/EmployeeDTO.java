package com.bridgelabz.employee_payroll.dto;

public class EmployeeDTO {
    private String name;
    private int salary;

    public EmployeeDTO(){}
//    public EmployeeDTO(String name, int salary){
//        this.name=name;
//        this.salary=salary;
//    }

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
    public String toString(){
        return "EmployeeDTO{name='" + name + "', salary=" + salary + "}";
    }
}
