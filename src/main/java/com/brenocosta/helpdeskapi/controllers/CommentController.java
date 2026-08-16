package com.brenocosta.helpdeskapi.controllers;


import com.brenocosta.helpdeskapi.domain.entities.Comment;
import com.brenocosta.helpdeskapi.dtos.comment.CommentResponseDTO;
import com.brenocosta.helpdeskapi.dtos.comment.CreateCommentDTO;
import com.brenocosta.helpdeskapi.dtos.comment.UpdateCommentRequest;
import com.brenocosta.helpdeskapi.mapper.CommentMapper;
import com.brenocosta.helpdeskapi.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class CommentController {

    @Autowired
    private CommentService service;

    @Autowired
    private CommentMapper mapper;

    @PostMapping("/tickets/{id}/comments")
    public ResponseEntity<CommentResponseDTO> createComment( @PathVariable Long id, @Valid @RequestBody CreateCommentDTO commentDTO) throws Exception {
        Comment comment = service.createComment(commentDTO, id);
        return new ResponseEntity<>(mapper.toResponse(comment), HttpStatus.CREATED);
    }

    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDTO> updateComment( @PathVariable Long id, @Valid @RequestBody UpdateCommentRequest dto) throws Exception {
        Comment comment = service.updateComment(dto, id);

        return new ResponseEntity<>(mapper.toResponse(comment), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment( @PathVariable Long id) throws Exception {
        service.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
