package com.bridgelabz.employee_payroll.dto;

import com.bridgelabz.employee_payroll.model.Employee;
import jakarta.persistence.Entity;

public class ResponseDTO {
    private String message;
    private Object data;

    public ResponseDTO(String message, Object data) {
        this.message=message;
        this.data=data;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message= message;
    }


    public Object getObject(){
        return data;
    }

    public void setObject(Object data){
        this.data= data;
    }

}
