package com.bridgelabz.employee_payroll.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IJwtUserDetailsService {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
