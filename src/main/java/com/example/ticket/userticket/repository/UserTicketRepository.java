package com.example.ticket.userticket.repository;

import com.example.ticket.userticket.model.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
    List<UserTicket> findByUserId(String userId);
}
