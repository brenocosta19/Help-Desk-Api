package com.brenocosta.helpdeskapi.controllers;


import com.brenocosta.helpdeskapi.domain.entities.Roles;
import com.brenocosta.helpdeskapi.domain.entities.User;
import com.brenocosta.helpdeskapi.dtos.user.UpdateRoleDTO;
import com.brenocosta.helpdeskapi.dtos.user.UserDetailsDTO;
import com.brenocosta.helpdeskapi.mapper.UserMapper;
import com.brenocosta.helpdeskapi.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    private final UserMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDetailsDTO>> findAll() {
        List<User> users = service.findAll();
        return new ResponseEntity<>(mapper.toDetails(users), HttpStatus.OK);
    }

    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> findUserById(@PathVariable Long id) throws Exception {
        User user = service.findUserById(id);

        return new ResponseEntity<>(mapper.toDetails(user), HttpStatus.OK);
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
