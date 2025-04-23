package com.bridgelabz.employee_payroll.services;

import com.bridgelabz.employee_payroll.dto.LoginDTO;
import com.bridgelabz.employee_payroll.dto.RegisterDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public interface UserInterface {
    ResponseDTO<String,String> registerUser(RegisterDTO registerDTO);

    ResponseDTO<String,String> loginUser(LoginDTO loginDTO);

    boolean matchPassword(String rawPassword, String encodedPassword);

    boolean existsByEmail(@NotBlank(message = "Email field can't be empty") @Email String email);

    Optional<User> getUserByEmail(String email);
}
