package com.brenocosta.helpdeskapi.dtos.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCommentDTO(


        @NotBlank
        @Size(min = 2, max = 1000)
        String content
) {
}
