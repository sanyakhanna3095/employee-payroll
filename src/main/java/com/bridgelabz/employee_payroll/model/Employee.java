package com.bridgelabz.employee_payroll.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long employeeId;
    private String name;
    private int salary;

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
