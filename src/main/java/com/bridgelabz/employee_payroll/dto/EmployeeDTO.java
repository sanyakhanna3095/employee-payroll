package com.bridgelabz.employee_payroll.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public @ToString class EmployeeDTO {
//    @NotEmpty(message = "Employee name can't be null")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid name")
    public String name;

    @Min(value = 500, message = "Min wage should be more than Rs.500")
    public int salary;

    @Pattern(regexp = "male|female", message = "Gender needs to be male or female")
    public String gender;

    @JsonFormat(pattern = "dd MM yyyy")
    @NotNull(message = "startDate should not be empty")
    @PastOrPresent(message = "Start date should be a past date or today's date")
    public LocalDate startDate;

    @NotBlank(message = "Note cannot be empty")
    public String note;

    @NotBlank(message = "Profile Pic can't be empty")
    public String profilePic;

    @NotNull(message = "Department should not be empty")
    public List<String> department;



}
