package com.hcc.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.enums.AuthorityEnum;
import com.hcc.exceptions.UsernameNotFoundException;
import com.hcc.model.AuthCredentialRequest;
import com.hcc.model.JwtResponse;
import com.hcc.model.MessageResponse;
import com.hcc.model.SignUpRequest;
import com.hcc.repositories.AuthorityRepository;
import com.hcc.repositories.UserRepository;
import com.hcc.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.management.relation.Role;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthCredentialRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Retrieve the User object from the database
            User user = userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userDetails.getUsername()));

            String jwt = jwtUtils.generateToken(user);  // Generate token with User

            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            HttpHeaders headers = new HttpHeaders();
            headers.set("Access-Control-Expose-Headers", "Authorization");
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
            return ResponseEntity.ok().headers(headers).body(user.getId());
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser( @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getCohortStartDate(), signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Authority> roles = new HashSet<>();

        if (strRoles == null) {
            Authority userRole = authorityRepository.findByAuthority(AuthorityEnum.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Authority adminRole = authorityRepository.findByAuthority(AuthorityEnum.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Authority modRole = authorityRepository.findByAuthority(AuthorityEnum.MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Authority userRole = authorityRepository.findByAuthority(AuthorityEnum.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setAuthorities(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!!"));
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
        UserDetails userDetails = userRepository.findByUsername(jwtUtils.getUsernameFromToken(token)).orElse(null);

        boolean isValid = jwtUtils.validateToken(token, userDetails);
        if (isValid) {
            return ResponseEntity.ok("Token is valid!");
        } else {
            return ResponseEntity.status(401).body("Token is invalid!");
        }
    }

}