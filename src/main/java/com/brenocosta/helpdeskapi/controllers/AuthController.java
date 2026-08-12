package com.brenocosta.helpdeskapi.controllers;


import com.brenocosta.helpdeskapi.domain.entities.User;
import com.brenocosta.helpdeskapi.dtos.auth.TokenResponseDTO;
import com.brenocosta.helpdeskapi.dtos.auth.UserAuthResponseDTO;
import com.brenocosta.helpdeskapi.dtos.auth.UserLoginDTO;
import com.brenocosta.helpdeskapi.dtos.auth.UserRegisterDTO;
import com.brenocosta.helpdeskapi.mapper.UserMapper;
import com.brenocosta.helpdeskapi.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<UserAuthResponseDTO> register(@RequestBody @Valid UserRegisterDTO dto) throws Exception{
        User user = authService.register(dto);

        return new ResponseEntity<>(mapper.toResponse(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody @Valid UserLoginDTO dto) throws Exception {
       return authService.login(dto);
    }
}
