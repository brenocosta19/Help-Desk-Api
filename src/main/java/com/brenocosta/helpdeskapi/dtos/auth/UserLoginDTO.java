package com.brenocosta.helpdeskapi.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO (

        @NotBlank
        String email,

        @NotBlank
        String password
){
}
