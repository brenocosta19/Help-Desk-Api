package com.brenocosta.helpdeskapi.dtos.auth;

import lombok.Builder;

@Builder
public record TokenResponseDTO(String token, Long expiration ) {
}
