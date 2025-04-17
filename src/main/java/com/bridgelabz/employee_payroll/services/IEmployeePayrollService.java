package com.bridgelabz.employee_payroll.services;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.model.Employee;

import java.util.List;

public interface IEmployeePayrollService {
    List<Employee> getEmployeePayrollData();

    Employee getEmployeePayrollDataById(long employeeId);
    Employee addEmployeePayrollData(EmployeeDTO employeeDTO);
    Employee updateEmployeePayrollData(EmployeeDTO employeeDTO);
    void deleteEmployeePayrollData(long employeeId);
}
