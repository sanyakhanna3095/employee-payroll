package com.bridgelabz.employee_payroll.utility;

import com.bridgelabz.employee_payroll.services.JwtUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


//how jwt token is being passed
//responsible for validating JWT (JSON Web Tokens) on every HTTP request to ensure proper authentication
//intercepting every request and checking if a valid JWT token is present in the Authorization header
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String authorizationHeader=request.getHeader("Authorization");

        String email=null;
        String jwt=null;

        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            jwt=authorizationHeader.substring(7);
            email=jwtUtility.extractEmail(jwt);
        }


//        SecurityContextHolder contains authentication details of current user
        if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=jwtUserDetailsService.loadUserByUsername(email);
            if(jwtUtility.validateToken(jwt,email)){
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
