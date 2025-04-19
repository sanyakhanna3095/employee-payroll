package com.bridgelabz.employee_payroll.controller;


import com.bridgelabz.employee_payroll.dto.EmployeeDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.model.Employee;
import com.bridgelabz.employee_payroll.services.IEmployeePayrollService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee-payroll-service")
public class EmployeePayrollController {

    @Autowired
    private IEmployeePayrollService employeePayrollService;


    @RequestMapping(value = {"", "/", "/home"})
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(){
        List<Employee> empDataList=null;
        empDataList=employeePayrollService.getEmployeePayrollData();
        ResponseDTO respDTO=new ResponseDTO("Get call success", empDataList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{employeeId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("employeeId") long employeeId){
        Employee empData=null;
        empData=employeePayrollService.getEmployeePayrollDataById(employeeId);
        ResponseDTO respDTO=new ResponseDTO("Get call for ID success", empData);
        return new ResponseEntity<ResponseDTO>( respDTO,HttpStatus.OK);
    }


    @GetMapping("/department/{department}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("department") String department){
        List<Employee> employeeList =null;
        employeeList = employeePayrollService.getEmployeesByDepartment(department);
        ResponseDTO respDTO = new ResponseDTO("Get call for department successful", employeeList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> addEmployeePayrollData(@Valid @RequestBody EmployeeDTO employeeDTO){
        Employee empData=null;
        empData=employeePayrollService.addEmployeePayrollData(employeeDTO);
        ResponseDTO respDTO=new ResponseDTO("Created new employee payroll data successfully ", empData);
        return new ResponseEntity<ResponseDTO>( respDTO,HttpStatus.OK);
    }

//    @PutMapping("/update")
//    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@RequestBody EmployeeDTO employeeDTO){
//        Employee empData=null;
//        empData=employeePayrollService.updateEmployeePayrollData(employeeDTO);
//        ResponseDTO respDTO=new ResponseDTO("Updated employee payroll data successfully: ", empData);
//        return new ResponseEntity<ResponseDTO>( respDTO,HttpStatus.OK);
//    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("employeeId") long employeeId,@RequestBody EmployeeDTO employeeDTO){
        Employee empData=null;
        empData=employeePayrollService.updateEmployeePayrollDataById(employeeId, employeeDTO);
        ResponseDTO respDTO=new ResponseDTO("Updated employee payroll data successfully: ", empData);
        return new ResponseEntity<ResponseDTO>( respDTO,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("employeeId") long employeeId){
        employeePayrollService.deleteEmployeePayrollData(employeeId);
        ResponseDTO respDTO=new ResponseDTO("Deleted successfully", "Deleted Id: "+ employeeId);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

}
