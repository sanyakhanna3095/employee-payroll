package com.bridgelabz.employee_payroll.exception;


import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

//@ControllerAdvice is very useful to handle exceptions all at one place when you have multiple layers doing different work.
//That means when writing any application you encounter exceptions and to handle them at each method level is tedious and not optimal.
//So in order to overcome that, spring has introduced the concept of @ControllerAdvice which will intercept all the controllers and look for the exceptions thrown.
//This is at a global level means you only have one @ControllerAdvice for each application, and it will intercept the exceptions thrown by the controllers.


@ControllerAdvice
@Slf4j
public class EmployeePayrollExceptionHandler {
        private static final String message="Exception while processing REST Request";
        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
            log.error("Invalid date format", exception);
            ResponseDTO responseDTO = new ResponseDTO(message,"Should have date in the format dd-MM-yyyy");
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
        }


        @ExceptionHandler(EmployeePayrollException.class)
        public ResponseEntity<ResponseDTO> handlePayrollException(EmployeePayrollException exception){
            ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST Request", exception.getMessage());
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
        }

       @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
            List<ObjectError> errorList=exception.getBindingResult().getAllErrors();
            List<String> errMsg=errorList.stream().map(objErr->objErr.getDefaultMessage()).collect(Collectors.toList());
            ResponseDTO responseDTO=new ResponseDTO("Exception while processing REST Request", errMsg);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
        }
}
