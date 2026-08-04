package com.brenocosta.helpdeskapi.dtos;

import com.brenocosta.helpdeskapi.domain.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentDTO(

        @NotNull
        Long ticketId,

        @NotNull
        Long ownerId,

        @NotBlank
        @Size(min = 2, max = 1000)
        String content
) {
}
