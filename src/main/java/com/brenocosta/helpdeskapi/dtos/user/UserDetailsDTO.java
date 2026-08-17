package com.brenocosta.helpdeskapi.dtos.user;

import com.brenocosta.helpdeskapi.domain.entities.Roles;
import com.brenocosta.helpdeskapi.domain.enums.Role;

import java.util.Set;

public record UserDetailsDTO(
        Long id,
        String name,
        String email,
        Set<Roles> roles
) {
}
