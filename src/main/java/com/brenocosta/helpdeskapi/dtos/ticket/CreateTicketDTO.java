package com.brenocosta.helpdeskapi.dtos.ticket;

import com.brenocosta.helpdeskapi.domain.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTicketDTO(

        @NotNull
        Long clientId,

        @Size(min = 5, max = 100)
        @NotBlank
        String title,

        String description,

        @NotNull
        Priority priority,

        @Size(min = 2, max = 100)
        @NotBlank
        String sector

) {
}