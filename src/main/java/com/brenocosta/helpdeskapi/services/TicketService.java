package com.brenocosta.helpdeskapi.services;

import com.brenocosta.helpdeskapi.domain.entities.Ticket;
import com.brenocosta.helpdeskapi.domain.entities.User;
import com.brenocosta.helpdeskapi.domain.enums.Role;
import com.brenocosta.helpdeskapi.domain.enums.TicketStatus;
import com.brenocosta.helpdeskapi.dtos.TicketDTO;
import com.brenocosta.helpdeskapi.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketRepository repository;


    public Ticket createTicket(TicketDTO ticket) throws Exception {
        User client = userService.findUserById(ticket.clientId());

        if (client.getRole() != Role.CLIENT) {
            throw new Exception("O usuário informado não é um cliente.");
        }


        Ticket newTicket = new Ticket();

        newTicket.setClient(client);
        newTicket.setTitle(ticket.title());
        newTicket.setDescription(ticket.description());
        newTicket.setStatus(TicketStatus.OPEN);
        newTicket.setPriority(ticket.priority());
        newTicket.setSector(ticket.sector());

        return repository.save(newTicket);
    }

}
