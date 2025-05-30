package com.ppi.trackventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.ppi.trackventory.configurations.JwtUtils;
import com.ppi.trackventory.models.JwtRequest;
import com.ppi.trackventory.models.JwtResponse;
import com.ppi.trackventory.services.impl.UserDetailsServiceImpl;

import lombok.extern.log4j.Log4j2;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@Log4j2
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        log.warn("Datos recibidos para login: username='{}', password='{}'", jwtRequest.getUsername(),
                jwtRequest.getPassword());

        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (Exception exception) {
            log.error("Falló la autenticación: {}", exception.getMessage());
            throw new Exception("Usuario o contraseña inválidos.");
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException exception) {
            throw new Exception("USER DISABLED " + exception.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid credentials " + e.getMessage());
        }
    }

    @GetMapping("/actualUser")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        return ResponseEntity.ok(userDetails);
    }

}
