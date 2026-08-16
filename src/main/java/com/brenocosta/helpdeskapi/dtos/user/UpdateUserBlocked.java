package com.brenocosta.helpdeskapi.dtos.user;

import jakarta.validation.constraints.NotNull;

public record UpdateUserBlocked(@NotNull boolean blocked) {
}
