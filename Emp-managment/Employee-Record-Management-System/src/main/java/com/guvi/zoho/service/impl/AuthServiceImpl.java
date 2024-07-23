package com.guvi.zoho.service.impl;

import com.guvi.zoho.dto.JwtAuthResponse;
import com.guvi.zoho.dto.LoginRequestDto;
import com.guvi.zoho.dto.RegisterDto;
import com.guvi.zoho.entities.Role;
import com.guvi.zoho.entities.User;
import com.guvi.zoho.exception.UserApiException;
import com.guvi.zoho.repo.RoleRepo;
import com.guvi.zoho.repo.UserRepo;
import com.guvi.zoho.security.JwtUtil;
import com.guvi.zoho.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Override
    public String register(RegisterDto registerDto) {

        if (userRepo.existsByUsername(registerDto.getUsername())) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        if (userRepo.existsByEmail(registerDto.getEmail())) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepo.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepo.save(user);
        return "User Registered Successfully!!";
    }

    @Override
    public JwtAuthResponse login(LoginRequestDto loginRequestDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generate(loginRequestDto.getUsername());

        Optional<User> userOptional = userRepo.findByUsername(loginRequestDto.getUsername());

        String role = "";
        if (userOptional.isPresent()) {
            User loggedInUser = userOptional.get();
            Optional<Role> optionalRole = loggedInUser.getRoles().stream().findFirst();

            Role userRole = optionalRole.get();
            role = userRole.getName();
        }

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(jwt);
        jwtAuthResponse.setRole(role);

        return jwtAuthResponse;
    }
}
