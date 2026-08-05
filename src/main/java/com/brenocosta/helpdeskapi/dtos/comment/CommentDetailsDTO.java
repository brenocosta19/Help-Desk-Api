package com.brenocosta.helpdeskapi.dtos.comment;

import com.brenocosta.helpdeskapi.dtos.user.UserSummaryDTO;

import java.time.LocalDateTime;

public record CommentDetailsDTO (
        Long id,
        UserSummaryDTO owner,
        String content,
        LocalDateTime createdAt
) {
}
