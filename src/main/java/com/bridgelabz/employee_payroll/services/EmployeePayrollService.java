package com.bridgelabz.employee_payroll.services;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeePayrollService implements IEmployeePayrollService{

    public List<Employee> getEmployeePayrollData(){
        List<Employee> empDataList=new ArrayList<>();
        empDataList.add(new Employee(1, new EmployeeDTO("Sanya", 50000)));
        return empDataList;
    }

    public Employee getEmployeePayrollDataById(long employeeId){
        Employee empData=null;
        empData=new Employee(1, new EmployeeDTO("Sanya", 50000));
        return empData;
    }

    public Employee addEmployeePayrollData(EmployeeDTO employeeDTO){
        Employee empData=null;
        empData=new Employee(1,employeeDTO);
        return empData;
    }

    public Employee updateEmployeePayrollData(EmployeeDTO employeeDTO){
        Employee empData=null;
        empData=new Employee(1,employeeDTO);
        return empData;
    }

    public Employee updateEmployeePayrollData(long employeeId, EmployeeDTO employeeDTO){
        Employee empData=null;
        empData=new Employee(1,employeeDTO);
        return empData;
    }

    public void deleteEmployeePayrollData(long employeeId) {
        EmployeeRepository.deleteEmployeePayrollData(employeeId);
    }
}
