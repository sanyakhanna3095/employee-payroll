package com.bridgelabz.employee_payroll.controller;


import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-payroll-service")
public class EmployeePayrollController {
    @RequestMapping(value = {"", "/", "/home"})
    public ResponseEntity<String> getEmployeePayrollData(){
        return new ResponseEntity<String>("Get call success", HttpStatus.OK);
    }

    @GetMapping("/get/{employeeId}")
    public ResponseEntity<String> getEmployeePayrollData(@PathVariable("employeeId") long employeeId){
        return new ResponseEntity<String>("Get call success " +employeeId, HttpStatus.OK);
    }


    @PostMapping("/post")
    public ResponseEntity<String> addEmployeePayrollData(@RequestBody EmployeeDTO employeeDTO){
        return new ResponseEntity<String>("Created new employee payroll data for: "+ employeeDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateEmployeePayrollData(@RequestBody EmployeeDTO employeeDTO){
        return new ResponseEntity<String>("Updated employee payroll data for: "+ employeeDTO, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployeePayrollData(@PathVariable("employeeId") long employeeId){
        return new ResponseEntity<String>("Delete call success " +employeeId, HttpStatus.OK);
    }

}
