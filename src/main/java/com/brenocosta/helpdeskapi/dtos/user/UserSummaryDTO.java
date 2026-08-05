package com.brenocosta.helpdeskapi.dtos.user;

import com.brenocosta.helpdeskapi.domain.enums.Role;

public record UserSummaryDTO(

        String name,
        String email

) {
}
