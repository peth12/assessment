package com.example.ticket.userticket.repository;

import com.example.ticket.userticket.model.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {

}
