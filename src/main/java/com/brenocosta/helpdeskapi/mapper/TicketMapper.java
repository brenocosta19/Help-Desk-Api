package com.brenocosta.helpdeskapi.mapper;


import com.brenocosta.helpdeskapi.domain.entities.Ticket;
import com.brenocosta.helpdeskapi.dtos.ticket.TicketDetailsDTO;
import com.brenocosta.helpdeskapi.dtos.ticket.TicketResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CommentMapper.class})
public interface TicketMapper {

    TicketResponseDTO toResponse(Ticket ticket);

    List<TicketResponseDTO> toResponse(List<Ticket> tickets);

    TicketDetailsDTO toDetails(Ticket ticket);
}
