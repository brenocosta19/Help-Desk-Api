package com.brenocosta.helpdeskapi.services;

import com.brenocosta.helpdeskapi.domain.entities.Ticket;
import com.brenocosta.helpdeskapi.domain.entities.User;
import com.brenocosta.helpdeskapi.domain.enums.TicketStatus;
import com.brenocosta.helpdeskapi.dtos.ticket.AssignTechnicianDTO;
import com.brenocosta.helpdeskapi.dtos.ticket.CreateTicketDTO;
import com.brenocosta.helpdeskapi.dtos.ticket.UpdateStatusTicketDTO;
import com.brenocosta.helpdeskapi.dtos.ticket.UpdateTicketDTO;
import com.brenocosta.helpdeskapi.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final  UserService userService;


    private final TicketRepository repository;

    private final AuthService authService;




    public Ticket createTicket(CreateTicketDTO ticket) throws Exception {

        User client = authService.getAuthenticatedUser();

        Ticket newTicket = new Ticket();

        newTicket.setClient(client);
        newTicket.setTitle(ticket.title());
        newTicket.setDescription(ticket.description());
        newTicket.setStatus(TicketStatus.OPEN);
        newTicket.setPriority(ticket.priority());
        newTicket.setSector(ticket.sector());

        return repository.save(newTicket);
    }

    public List<Ticket> findAll() {
        User user = authService.getAuthenticatedUser();

        boolean isAdmin = user.getRoles().stream()
                .anyMatch(role ->
                        role.getName().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return repository.findAll();
        }

        return repository.findByClientOrTechnician(user, user);
    }

    public Ticket findTicketById(Long id) throws Exception {

        User user = authService.getAuthenticatedUser();

        Ticket ticket = repository.findById(id)
                .orElseThrow(() ->
                        new Exception("Ticket não encontrado"));

        boolean isAdmin = user.getRoles().stream()
                .anyMatch(role ->
                        role.getName().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return ticket;
        }

        boolean isClient = ticket.getClient()
                .getId()
                .equals(user.getId());

        boolean isTechnician = ticket.getTechnician() != null
                && ticket.getTechnician()
                .getId()
                .equals(user.getId());

        if (isClient || isTechnician) {
            return ticket;
        }

        throw new AccessDeniedException(
                "Você não possui acesso a este ticket."
        );
    }

    public TicketStatus verifyTicketStatus(Long id) throws Exception {
        Ticket ticket = this.findTicketById(id);


        TicketStatus status = ticket.getStatus();

        return status;
    }

    public Ticket updateTicket(UpdateTicketDTO dto, Long id) throws Exception{

        Ticket ticket = findTicketById(id);

        if (ticket.getStatus() == TicketStatus.CLOSED) {
            throw new IllegalStateException("Não é possível editar um ticket fechado.");
        }

        if (dto.title() != null) {
            ticket.setTitle(dto.title());
        }

        if (dto.description() != null) {
            ticket.setDescription(dto.description());
        }

        if (dto.sector() != null) {
            ticket.setSector(dto.sector());
        }

        return repository.save(ticket);

    }

    public Ticket updateStatusTicket(UpdateStatusTicketDTO dto, Long id) throws Exception {
        Ticket ticket = findTicketById(id);

        TicketStatus current = ticket.getStatus();
        TicketStatus next = dto.status();

        User user = authService.getAuthenticatedUser();

        if (current == next) {
            throw new IllegalStateException("O ticket já está nesse status.");
        }

        if (current == TicketStatus.CLOSED && next == TicketStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "Primeiro reabra o ticket."
            );
        }

        if (current == TicketStatus.OPEN && next == TicketStatus.CLOSED) {
            throw new IllegalStateException(
                    "O ticket deve estar em andamento antes de ser fechado."
            );
        }

        if (current == TicketStatus.OPEN && next == TicketStatus.IN_PROGRESS && ticket.getTechnician() == null) {
            throw new IllegalStateException("Para colocar o ticket em andamento, é necessário atribuir um técnico.");
        }

        boolean isClient = user.getRoles().stream()
                .anyMatch(role -> role.getName().equals("ROLE_CLIENT"));

        if (current == TicketStatus.IN_PROGRESS
                && next == TicketStatus.CLOSED
                && isClient) {

            throw new AccessDeniedException(
                    "Um cliente não pode fechar o ticket."
            );
        }

        ticket.setStatus(next);

        return repository.save(ticket);
    }

    public Ticket assignTechnician(AssignTechnicianDTO dto, Long id ) throws Exception {
        Ticket ticket = findTicketById(id);

        if (ticket.getStatus() == TicketStatus.CLOSED) {
            throw new IllegalStateException(
                    "Não é possível atribuir um técnico a um ticket fechado."
            );
        }

        if (ticket.getTechnician() != null) {
            throw new IllegalStateException(
                    "Ticket já possui um técnico."
            );
        }

        User user = userService.findUserById(dto.technicianId());

        if (user.getRoles().stream()
                .noneMatch(role -> role.getName().equals("ROLE_TECHNICIAN"))) {

            throw new IllegalStateException(
                    "O usuário informado não é um técnico."
            );
        }


        ticket.setTechnician(user);

        return repository.save(ticket);
    }

    public Ticket assignMeTechnician(Long id) throws Exception {
        User user = authService.getAuthenticatedUser();

        Ticket ticket = repository.findById(id).orElseThrow(() ->
                new Exception("Ticket não encontrado"));

        if (ticket.getStatus() == TicketStatus.CLOSED) {
            throw new IllegalStateException(
                    "Não é possível assumir um ticket fechado."
            );
        }

        if (ticket.getTechnician() != null) {
            throw new IllegalStateException(
                    "Ticket já possui um técnico."
            );
        }

        ticket.setTechnician(user);

        return repository.save(ticket);
    }
}
