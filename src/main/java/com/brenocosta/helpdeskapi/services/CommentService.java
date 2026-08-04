package com.brenocosta.helpdeskapi.services;

import com.brenocosta.helpdeskapi.domain.entities.Comment;
import com.brenocosta.helpdeskapi.domain.entities.Ticket;
import com.brenocosta.helpdeskapi.domain.entities.User;
import com.brenocosta.helpdeskapi.domain.enums.TicketStatus;
import com.brenocosta.helpdeskapi.dtos.CommentDTO;
import com.brenocosta.helpdeskapi.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private UserService userService;

    @Autowired TicketService ticketService;

    public Comment makeComment(CommentDTO comment) throws Exception {
        User owner = userService.findUserById(comment.ownerId());

        Ticket ticket = ticketService.findTicketById(comment.ticketId());

        if (ticket.getStatus() == TicketStatus.CLOSED) {
            throw new IllegalStateException("Ticket fechado ! Não é possível fazer comentários");
        }

        Comment newComment = new Comment();

        newComment.setOwner(owner);
        newComment.setTicket(ticket);
        newComment.setContent(comment.content());

        return repository.save(newComment);


    }



}
