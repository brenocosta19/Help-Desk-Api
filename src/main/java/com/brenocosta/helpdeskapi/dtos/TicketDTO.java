package com.brenocosta.helpdeskapi.dtos;

import com.brenocosta.helpdeskapi.domain.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketDTO(

        @NotNull
        Long clientId,

        @NotBlank
        String title,

        String description,

        @NotNull
        Priority priority,

        @NotBlank
        String sector

) {
}