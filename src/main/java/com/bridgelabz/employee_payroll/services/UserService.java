package com.bridgelabz.employee_payroll.services;


import com.bridgelabz.employee_payroll.dto.LoginDTO;
import com.bridgelabz.employee_payroll.dto.RegisterDTO;
import com.bridgelabz.employee_payroll.dto.ResetPasswordDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.model.User;
import com.bridgelabz.employee_payroll.repository.UserRepository;
import com.bridgelabz.employee_payroll.utility.JwtUtility;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class UserService implements UserInterface{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtility jwtUtility;

    private String otp;
//
//    @Autowired
//    private RabbitMQProducer rabbitMQProducer;

    @Override
    public ResponseDTO<String,String> registerUser(RegisterDTO registerDTO){
        log.info("Registering user:{}", registerDTO.getEmail());
        ResponseDTO<String,String> res=new ResponseDTO<>();
        if(existsByEmail(registerDTO.getEmail())){
            log.warn("Registration failed: User already exists within email {}", registerDTO.getEmail());
            res.setMessage("error");
            res.setData("User already Exists");
            return res;
        }

        User user=new User();
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("User {} registered successfuly!", user.getEmail());
        emailService.sendEmail(user.getEmail(), "Registered in employee-payroll app", "Hi...\nYou have been successfully registered");
        res.setMessage("message");
        res.setData("User registered successfuly!");
        return res;
    }


    @Override
    public ResponseDTO<String,String> loginUser(LoginDTO loginDTO){
        log.info("Login attempt for user:{}", loginDTO.getEmail());
        ResponseDTO<String,String> res=new ResponseDTO<>();
        Optional<User> userExists=getUserByEmail(loginDTO.getEmail());

        if(userExists.isPresent()){
            User user=userExists.get();
            if(matchPassword(loginDTO.getPassword(), user.getPassword())){
                String token=jwtUtility.generateToken(user.getEmail());
                user.setToken(token);
                userRepository.save(user);
                log.debug("Login successful for user: {} - Token generated",user.getEmail());
                emailService.sendEmail(user.getEmail(),"Logged in employee-payroll app", "Hi "+user.getName()+" \nYou have been successfully logged in !\n"+"token: "+token);
                res.setMessage("message");
                res.setData("User logged in successfully with token: " + token);
                return res;
            }
            else {
                log.warn("Invalid credentials for user: {}", loginDTO.getEmail());
                res.setMessage("error");
                res.setData("Invalid Credentials");
                return res;
            }
        }
        else {
            log.error("User not found with email: {}", loginDTO.getEmail());
            res.setMessage("error");
            res.setData("User not found");
            return res;
        }
    }

    @Override
    public boolean matchPassword(String rawPassword, String encodedPassword){
        log.debug("Matching password for login attempt");
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public boolean existsByEmail(@NotBlank(message = "Email field can't be empty") @Email String email) {
        log.debug("Checking if user exists using email:{}", email);
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<User> getUserByEmail(String email){
        log.debug("Fetching user by email: {}", email);
        return userRepository.findByEmail(email);
    }


    public String generateOTP(){
        Random random=new Random();
        int otpGenerated=100000+random.nextInt(900000);
        return String.valueOf(otpGenerated);
    }

    @Override
    public ResponseDTO<String,String> forgetPassword(LoginDTO loginDTO) {
        log.info("Forget password for email: {}", loginDTO.getEmail());
        ResponseDTO<String,String> res=new ResponseDTO<>();
        Optional<User> userExists=getUserByEmail(loginDTO.getEmail());

        if(userExists.isPresent()){
            User user=userExists.get();
//            Generate otp to be sent via email
            otp=generateOTP();

            emailService.sendEmail(user.getEmail(), "OTP Verification Request", "Please enter this OTP to reset your password." + "\nOTP: "+otp);
            res.setMessage("OTP verification");
            res.setData("OTP sent to the user");
        }
        else{
            log.error("User not found with email: {}" , loginDTO.getEmail());
            res.setMessage("error");
            res.setData("User not found");
        }
        return res;
    }



    @Override
    public ResponseDTO<String, String> resetPassword(ResetPasswordDTO resetPasswordDTO) {
        log.info("Attempting to reset password using token: {}", resetPasswordDTO.getEmail());
        ResponseDTO<String, String> res = new ResponseDTO<>();

        try {
//            String email = jwtUtility.extractEmail(token);

            Optional<User> userOptional = getUserByEmail(resetPasswordDTO.getEmail());
            if(userOptional.isPresent()) {
                User user = userOptional.get();

                // Compare otp to stored one for extra validation
                 if (!resetPasswordDTO.getOtp().equals(otp)) {
                     log.debug("OTP mismatch!!");
                     log.warn("Invalid OTP for email: {}", resetPasswordDTO.getEmail());
                     res.setMessage("error");
                     res.setData("Invalid OTP");
                     return res;
                 }
                else{
                    // Encrypt new password and update
                    String encodedPassword = passwordEncoder.encode(resetPasswordDTO.getNewPassword());
                    user.setPassword(encodedPassword);

                    // Clear OTP after use
                    otp = null;
                    userRepository.save(user);


                    emailService.sendEmail(user.getEmail(), "Reset Password confirmation", "Your new password has been set successfully.");
                    res.setMessage("successful");
                    res.setData("Password set successfully.");

                    log.info("Password reset successful for user: {}", resetPasswordDTO.getEmail());
                    res.setMessage("message");
                    res.setData("Password reset successfully.");
                    return res;
                }
            }
            else {
                res.setMessage("error");
                res.setData("User not found.");
                return res;
            }
        } catch (Exception e) {
            log.error("Otp invalid or expired: {}", e.getMessage());
            res.setMessage("error");
            res.setData("Otp is invalid or expired.");
            return res;
        }
    }

}
