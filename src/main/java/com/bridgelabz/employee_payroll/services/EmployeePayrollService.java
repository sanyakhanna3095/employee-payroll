package com.bridgelabz.employee_payroll.services;

import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.exception.EmployeePayrollException;
import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeePayrollService implements IEmployeePayrollService{
    @Autowired
    private EmployeeRepository employeeRepository;

//    private List<Employee> employeeList=new ArrayList<>();
    public List<Employee> getEmployeePayrollData(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeePayrollDataById(long employeeId){
//        return employeeList.get((int)employeeId-1);

//        return employeeList.stream()
//                .filter(empData-> empData.getId()==employeeId)
//                .findFirst()
//                .orElseThrow(()-> new EmployeePayrollException("Employee Not Found"));
        return employeeRepository
                .findById(employeeId)
                .orElseThrow(()->new EmployeePayrollException("Employee with employee id "+employeeId+" does not exist!!"));
    }


    @Override
    public List<Employee> getEmployeesByDepartment(String department){
        return employeeRepository.findEmployeesByDepartment(department);
    }
    public Employee addEmployeePayrollData(EmployeeDTO employeeDTO){
        Employee empData=null;
        empData=new Employee(employeeDTO);
        log.debug("Emp data: "+empData.toString());
//        employeeList.add(empData);
        return employeeRepository.save(empData);
    }

//    public Employee updateEmployeePayrollData(EmployeeDTO employeeDTO){
//        Employee empData=null;
//        empData=new Employee(1,employeeDTO);
//        return empData;
//    }

    public Employee updateEmployeePayrollDataById(long employeeId, EmployeeDTO employeeDTO){
        Employee empData=this.getEmployeePayrollDataById(employeeId);
//        empData.setName(employeeDTO.getName());
//        empData.setSalary(employeeDTO.getSalary());
//        employeeList.set((int) (employeeId-1),empData);
        empData.updateEmployeePayrollData(employeeDTO);
        return employeeRepository.save(empData);
    }

    public void deleteEmployeePayrollData(long employeeId) {
//        EmployeeRepository.deleteEmployeePayrollData(employeeId);
//        employeeList.remove(employeeId-1);
//        employeeList.removeIf(emp -> emp.getId() == employeeId);

        Employee empData=this.getEmployeePayrollDataById(employeeId);
        employeeRepository.delete(empData);
    }
}
