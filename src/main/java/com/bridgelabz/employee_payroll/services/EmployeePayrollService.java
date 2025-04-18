package com.bridgelabz.employee_payroll.services;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.exception.EmployeePayrollException;
import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeePayrollService implements IEmployeePayrollService{
    List<Employee> employeeList=new ArrayList<>();
    public List<Employee> getEmployeePayrollData(){
        return employeeList;
    }

    public Employee getEmployeePayrollDataById(long employeeId){
//        return employeeList.get((int)employeeId-1);
        return employeeList.stream()
                .filter(empData-> empData.getId()==employeeId)
                .findFirst()
                .orElseThrow(()-> new EmployeePayrollException("Employee Not Found"));
    }

    public Employee addEmployeePayrollData(EmployeeDTO employeeDTO){
        Employee empData=null;
        empData=new Employee(employeeList.size()+1, employeeDTO);
        employeeList.add(empData);
        return empData;
    }

//    public Employee updateEmployeePayrollData(EmployeeDTO employeeDTO){
//        Employee empData=null;
//        empData=new Employee(1,employeeDTO);
//        return empData;
//    }

    public Employee updateEmployeePayrollDataById(long employeeId, EmployeeDTO employeeDTO){
        Employee empData=this.getEmployeePayrollDataById(employeeId);
        empData.setName(employeeDTO.getName());
        empData.setSalary(employeeDTO.getSalary());
        employeeList.set((int) (employeeId-1),empData);
        return empData;
    }

    public void deleteEmployeePayrollData(long employeeId) {
//        EmployeeRepository.deleteEmployeePayrollData(employeeId);
//        employeeList.remove(employeeId-1);
        employeeList.removeIf(emp -> emp.getId() == employeeId);
    }
}
