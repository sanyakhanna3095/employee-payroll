package com.bridgelabz.employee_payroll.controller;


import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-payroll-service")
public class EmployeePayrollController {
    @RequestMapping(value = {"", "/", "/home"})
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(){
        Employee empData=null;
        empData=new Employee(1, new EmployeeDTO("Sanya", 45000));
        ResponseDTO respDTO=new ResponseDTO("Get call success", empData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{employeeId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollDataById(@PathVariable("employeeId") long employeeId){
        Employee empData=null;
        empData=new Employee(1, new EmployeeDTO("Sanya", 45000));
        ResponseDTO respDTO=new ResponseDTO("Get call for ID success", empData);
        return new ResponseEntity<ResponseDTO>( respDTO,HttpStatus.OK);
    }


    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> addEmployeePayrollData(@RequestBody EmployeeDTO employeeDTO){
        Employee empData=null;
        empData=new Employee(1, employeeDTO);
        ResponseDTO respDTO=new ResponseDTO("Created new employee payroll data for: ", empData);
        return new ResponseEntity<ResponseDTO>( respDTO,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@RequestBody EmployeeDTO employeeDTO){
        Employee empData=null;
        empData=new Employee(1, employeeDTO);
        ResponseDTO respDTO=new ResponseDTO("Updated new employee payroll data for: ", empData);
        return new ResponseEntity<ResponseDTO>( respDTO,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("employeeId") long employeeId){
        ResponseDTO respDTO=new ResponseDTO("Deleted successfully", "Deleted Id: "+ employeeId);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

}
