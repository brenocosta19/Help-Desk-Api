package com.brenocosta.helpdeskapi.dtos.user;

import com.brenocosta.helpdeskapi.domain.enums.Role;

public record UserDetailsDTO(
        Long id,
        String name,
        String email,
        Role role
) {
}
