package com.brenocosta.helpdeskapi.dtos.ticket;

import com.brenocosta.helpdeskapi.domain.enums.TicketStatus;
import jakarta.validation.constraints.NotBlank;

public record UpdateStatusTicketDTO(

        @NotBlank
        TicketStatus status
) {
}
