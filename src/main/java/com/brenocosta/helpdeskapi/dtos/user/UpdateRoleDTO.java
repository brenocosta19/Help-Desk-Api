package com.brenocosta.helpdeskapi.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateRoleDTO(@NotNull Long roleId) {
}
