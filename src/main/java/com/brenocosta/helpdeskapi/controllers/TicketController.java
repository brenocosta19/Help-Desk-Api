package com.brenocosta.helpdeskapi.controllers;

import com.brenocosta.helpdeskapi.domain.entities.Ticket;
import com.brenocosta.helpdeskapi.dtos.ticket.TicketDTO;
import com.brenocosta.helpdeskapi.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    @GetMapping
    public ResponseEntity<List<Ticket>> findAll() {
        List<Ticket> tickets = service.findAll();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketDTO ticket) throws Exception {
        Ticket newTicket = service.createTicket(ticket);

        return new ResponseEntity<>(newTicket, HttpStatus.OK);
    }
}
