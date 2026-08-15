package com.brenocosta.helpdeskapi.controllers;


import com.brenocosta.helpdeskapi.domain.entities.Roles;
import com.brenocosta.helpdeskapi.dtos.role.RolesResponseDTO;
import com.brenocosta.helpdeskapi.mapper.RoleMapper;
import com.brenocosta.helpdeskapi.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;

    private final RoleMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<RolesResponseDTO>> findAllRoles() {

        List<Roles> roles = service.findAll();

        return new ResponseEntity<>(mapper.toResponse(roles), HttpStatus.OK);
    }


}
