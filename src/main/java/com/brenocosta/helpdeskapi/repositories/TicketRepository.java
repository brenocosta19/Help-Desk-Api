package com.brenocosta.helpdeskapi.repositories;

import com.brenocosta.helpdeskapi.domain.entities.Ticket;
import com.brenocosta.helpdeskapi.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByClientOrTechnician(User client, User technician);
}
