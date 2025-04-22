package com.bridgelabz.employee_payroll.services;

import com.bridgelabz.employee_payroll.model.User;
import com.bridgelabz.employee_payroll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user=userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with email: "+email));

        return user;
    }
}
