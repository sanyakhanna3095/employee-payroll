package com.bridgelabz.employee_payroll;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class EmployeePayrollApplication {
	private static final Logger log = LoggerFactory.getLogger(EmployeePayrollApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EmployeePayrollApplication.class, args);
		log.info("Employee Payroll App Started in {} Environment", context.getEnvironment().getProperty("environment"));
		log.info("Employee Payroll DB user is {}", context.getEnvironment().getProperty("spring.datasource.username"));
	}

}
