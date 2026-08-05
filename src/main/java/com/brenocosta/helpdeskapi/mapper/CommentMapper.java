package com.brenocosta.helpdeskapi.mapper;

import com.brenocosta.helpdeskapi.domain.entities.Comment;
import com.brenocosta.helpdeskapi.dtos.comment.CommentDetailsDTO;
import com.brenocosta.helpdeskapi.dtos.comment.CommentResponseDTO;
import com.brenocosta.helpdeskapi.dtos.comment.CreateCommentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CommentMapper {

    CommentResponseDTO toResponse(Comment comment);

    CreateCommentDTO toEntity(Comment comment);

    List<CommentResponseDTO> toResponse(List<Comment> comments);

    CommentDetailsDTO toDetails(Comment comment);

    List<CommentDetailsDTO> toDetails(List<Comment> comments);
}
