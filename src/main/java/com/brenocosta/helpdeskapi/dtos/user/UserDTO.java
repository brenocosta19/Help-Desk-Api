package com.brenocosta.helpdeskapi.dtos.user;

import com.brenocosta.helpdeskapi.domain.enums.Role;

public record UserDTO(String name, String email, String password, Role role) {
}
