package com.example.ticket.userticket.controller;


import com.example.ticket.userticket.dto.UserTicketResponse;
import com.example.ticket.userticket.service.UserTicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserTicketController {

    private final UserTicketService userTicketService;

    public UserTicketController(UserTicketService userTicketService) {
        this.userTicketService = userTicketService;
    }

    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    public ResponseEntity<UserTicketResponse> buyLottery(
            @PathVariable("userId") String userId, @PathVariable("ticketId") String ticketId
    ){
        return new ResponseEntity<>(userTicketService.buyLottery(userId, ticketId), HttpStatus.CREATED);
    }
}
