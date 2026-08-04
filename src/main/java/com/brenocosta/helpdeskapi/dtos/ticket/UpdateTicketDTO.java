package com.brenocosta.helpdeskapi.dtos.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTicketDTO (

        @Size(min = 5, max = 100)
        String title,

        @Size(min = 10, max = 1000)
        String description,

        @Size(min = 3, max = 50)
        String sector
) {
}
