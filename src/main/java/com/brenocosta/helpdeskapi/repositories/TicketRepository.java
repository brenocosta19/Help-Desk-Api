package com.brenocosta.helpdeskapi.repositories;

import com.brenocosta.helpdeskapi.domain.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
