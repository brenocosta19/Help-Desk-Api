package com.brenocosta.helpdeskapi.services;

import com.brenocosta.helpdeskapi.domain.entities.Comment;
import com.brenocosta.helpdeskapi.domain.entities.Ticket;
import com.brenocosta.helpdeskapi.domain.entities.User;
import com.brenocosta.helpdeskapi.domain.enums.TicketStatus;
import com.brenocosta.helpdeskapi.dtos.comment.CreateCommentDTO;
import com.brenocosta.helpdeskapi.dtos.comment.UpdateCommentRequest;
import com.brenocosta.helpdeskapi.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {


    private final CommentRepository repository;
    private final UserService userService;

    private final AuthService authService;

    private final TicketService ticketService;

    public Comment createComment(CreateCommentDTO comment, Long id) throws Exception {

        User owner = authService.getAuthenticatedUser();

        Ticket ticket = ticketService.findTicketById(id);

        if (ticket.getStatus() == TicketStatus.CLOSED) {
            throw new IllegalStateException("Ticket fechado ! Não é possível fazer comentários");
        }

        Comment newComment = new Comment();

        newComment.setOwner(owner);
        newComment.setTicket(ticket);
        newComment.setContent(comment.content());

        return repository.save(newComment);

    }

    public Comment updateComment(UpdateCommentRequest dto, Long id) throws Exception {
        User user = authService.getAuthenticatedUser();

        Comment comment = repository.findById(id).orElseThrow(() -> new Exception("Comentário não existe"));

        if (comment.getTicket().getStatus() == TicketStatus.CLOSED) {
            throw new IllegalStateException("Ticket está fechado, não é possível alterar o comentário");
        }

        boolean isOwner = comment.getOwner().getId().equals(user.getId());

        if (!isOwner) {
            throw new AccessDeniedException("Você não é o autor deste comentário.");
        }

        comment.setContent(dto.content());

        return repository.save(comment);
    }

    public void deleteComment(Long id) throws Exception {
        User user = authService.getAuthenticatedUser();

        Comment comment = repository.findById(id).orElseThrow(() -> new Exception("Comentário não existe"));

        if (comment.getTicket().getStatus() == TicketStatus.CLOSED) {
            throw new IllegalStateException("Ticket está fechado, não é possível deletar o comentário");
        }

        boolean isOwner = comment.getOwner().getId().equals(user.getId());

        if (!isOwner) {
            throw new AccessDeniedException("Você não é o autor deste comentário.");
        }

        repository.delete(comment);
    }


}
