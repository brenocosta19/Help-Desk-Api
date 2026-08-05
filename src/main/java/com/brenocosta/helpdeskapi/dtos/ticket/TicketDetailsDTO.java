package com.brenocosta.helpdeskapi.dtos.ticket;

import com.brenocosta.helpdeskapi.domain.enums.Priority;
import com.brenocosta.helpdeskapi.domain.enums.TicketStatus;
import com.brenocosta.helpdeskapi.dtos.comment.CommentDetailsDTO;
import com.brenocosta.helpdeskapi.dtos.user.UserDetailsDTO;
import com.brenocosta.helpdeskapi.dtos.user.UserSummaryDTO;

import java.util.List;

public record TicketDetailsDTO(
        Long id,
        String title,
        String description,
        TicketStatus status,
        Priority priority,
        String sector,
        UserDetailsDTO client,
        UserDetailsDTO technician,
        List<CommentDetailsDTO> comments
) {
}
