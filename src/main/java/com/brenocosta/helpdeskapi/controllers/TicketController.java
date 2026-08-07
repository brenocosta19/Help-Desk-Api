package com.brenocosta.helpdeskapi.controllers;

import com.brenocosta.helpdeskapi.domain.entities.Ticket;
import com.brenocosta.helpdeskapi.dtos.ticket.*;
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

    @PatchMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> updateTicket(@PathVariable Long id, @Valid @RequestBody UpdateTicketDTO ticket) throws Exception {
        Ticket updatedTicket = service.updateTicket(ticket, id);

        return new ResponseEntity<>(mapper.toResponse(updatedTicket), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TicketResponseDTO> updateTicketStatus(@PathVariable Long id, @Valid @RequestBody UpdateStatusTicketDTO ticket) throws Exception {
        Ticket updatedTicketStatus = service.updateStatusTicket(ticket, id);

        return new ResponseEntity<>(mapper.toResponse(updatedTicketStatus), HttpStatus.OK);
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<TicketResponseDTO> updateTicketStatus(@PathVariable Long id, @Valid @RequestBody AssignTechnicianDTO technician) throws Exception {
        Ticket assignedTechnicianTicket = service.assignTechnician(technician, id);

        return new ResponseEntity<>(mapper.toResponse(assignedTechnicianTicket), HttpStatus.OK);
    }
}
