package com.applyai.backend.service;

import com.applyai.backend.dto.auth.LoginRequest;
import com.applyai.backend.dto.auth.LoginResponse;
import com.applyai.backend.dto.auth.RegisterRequest;
import com.applyai.backend.dto.auth.RegisterResponse;
import com.applyai.backend.entity.User;
import com.applyai.backend.exception.EmailAlreadyExistsException;
import com.applyai.backend.repository.UserRepository;
import com.applyai.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public RegisterResponse register(RegisterRequest dto){

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user= User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        User savedUser=userRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt()
        );
    }

    public LoginResponse login(LoginRequest dto){

       User user=userRepository.findByEmail(dto.getEmail()).orElseThrow(()->new RuntimeException("Invalid email or password"));

       String rawPassword=dto.getPassword();
       String encodedPassword=user.getPassword();

       if(!passwordEncoder.matches(rawPassword,encodedPassword)){
              throw new RuntimeException("Invalid email or password");
       }

       String token=jwtService.generateToken(user);

       return new LoginResponse(token,
               user.getId(),
               user.getFirstName(),
               user.getLastName(),
               user.getEmail()
       );
    }

}
