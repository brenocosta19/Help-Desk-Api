package com.brenocosta.helpdeskapi.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateRoleDTO(@NotBlank Long roleId) {
}
