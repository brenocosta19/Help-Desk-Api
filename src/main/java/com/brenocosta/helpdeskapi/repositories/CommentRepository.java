package com.brenocosta.helpdeskapi.repositories;

import com.brenocosta.helpdeskapi.domain.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
