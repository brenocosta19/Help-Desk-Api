package com.brenocosta.helpdeskapi.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterDTO(

        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotBlank
        String name
) {
}
