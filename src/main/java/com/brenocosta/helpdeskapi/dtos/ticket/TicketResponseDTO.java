package com.brenocosta.helpdeskapi.dtos.ticket;

import com.brenocosta.helpdeskapi.domain.enums.Priority;
import com.brenocosta.helpdeskapi.domain.enums.TicketStatus;
import com.brenocosta.helpdeskapi.dtos.user.UserSummaryDTO;

public record TicketResponseDTO (
        Long id,
        String title,
        String description,
        TicketStatus status,
        Priority priority,
        String sector,
        UserSummaryDTO client,
        UserSummaryDTO technician

) {}
