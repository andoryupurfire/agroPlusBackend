package com.agro.agroplus.controller;


import com.agro.agroplus.dto.AuthResponse;
import com.agro.agroplus.dto.LoginRequest;
import com.agro.agroplus.dto.RegisterRequest;
import com.agro.agroplus.entity.Agricultor;
import com.agro.agroplus.repository.AgricultorRepository;
import com.agro.agroplus.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AgricultorRepository agricultorRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register (@RequestBody RegisterRequest request){
        if (agricultorRepository.existsByUsername(request.username())) {
            return ResponseEntity.badRequest().build();
        }

        Agricultor agricultor = Agricultor.builder()
                .nombre(request.nombre())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .build();

        agricultorRepository.save(agricultor);

        String token = jwtUtil.generateToken(agricultor.getUsername());
        return ResponseEntity.ok(new AuthResponse(token, agricultor.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody LoginRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), request.password()));

        String token = jwtUtil.generateToken(request.username());
        return ResponseEntity.ok(new AuthResponse(token, request.username()));

    }
}
