package com.brenocosta.helpdeskapi.dtos.ticket;

import com.brenocosta.helpdeskapi.domain.enums.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateStatusTicketDTO(

        @NotNull
        TicketStatus status
) {
}
