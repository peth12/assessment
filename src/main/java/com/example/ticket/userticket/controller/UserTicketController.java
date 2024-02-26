package com.example.ticket.userticket.controller;


import com.example.ticket.lottery.dto.TicketResponse;
import com.example.ticket.userticket.dto.UserTicketListResponse;
import com.example.ticket.userticket.dto.UserTicketResponse;
import com.example.ticket.userticket.service.UserTicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserTicketController {

    private final UserTicketService userTicketService;

    public UserTicketController(UserTicketService userTicketService) {
        this.userTicketService = userTicketService;
    }

    @GetMapping("/users/{userId}/lotteries")
    public ResponseEntity<UserTicketListResponse> getLotteriesByUserId(
            @PathVariable("userId")
            String userId) {
        return new ResponseEntity<>(userTicketService.getLotteriesByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/lotteries/{ticketId}")
    public ResponseEntity<UserTicketResponse> buyLottery(
            @PathVariable("userId") String userId, @PathVariable("ticketId") String ticketId
    ){
        return new ResponseEntity<>(userTicketService.buyLottery(userId, ticketId), HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{userId}/lotteries/{ticketId}")
    public ResponseEntity<TicketResponse> deleteLotteryUser(
            @PathVariable("userId") String userId, @PathVariable("ticketId") String ticketId
    ) {
        return new ResponseEntity<>(userTicketService.deleteLotteryUser(userId, ticketId), HttpStatus.CREATED);
    }
}
