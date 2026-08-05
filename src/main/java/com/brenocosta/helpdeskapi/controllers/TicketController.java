package com.brenocosta.helpdeskapi.controllers;

import com.brenocosta.helpdeskapi.domain.entities.Ticket;
import com.brenocosta.helpdeskapi.dtos.ticket.CreateTicketDTO;
import com.brenocosta.helpdeskapi.dtos.ticket.TicketDetailsDTO;
import com.brenocosta.helpdeskapi.dtos.ticket.TicketResponseDTO;
import com.brenocosta.helpdeskapi.mapper.TicketMapper;
import com.brenocosta.helpdeskapi.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    @Autowired
    private TicketMapper mapper;

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> findAll() {
        List<Ticket> tickets = service.findAll();
        return new ResponseEntity<>(mapper.toResponse(tickets), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDetailsDTO> listById (@Valid @PathVariable Long id) throws Exception {
        Ticket ticket = service.findTicketById(id);

        return new ResponseEntity<>(mapper.toDetails(ticket), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TicketDetailsDTO> createTicket(@Valid @RequestBody CreateTicketDTO ticket) throws Exception {
        Ticket newTicket = service.createTicket(ticket);

        return new ResponseEntity<>(mapper.toDetails(newTicket), HttpStatus.CREATED);
    }
}
