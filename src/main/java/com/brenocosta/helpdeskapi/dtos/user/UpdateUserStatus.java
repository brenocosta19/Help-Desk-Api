package com.brenocosta.helpdeskapi.dtos.user;

import jakarta.validation.constraints.NotNull;

public record UpdateUserStatus(@NotNull boolean enabled) {
}
