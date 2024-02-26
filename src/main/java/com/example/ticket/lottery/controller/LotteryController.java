package com.example.ticket.lottery.controller;

import com.example.ticket.lottery.dto.LotteryRequest;
import com.example.ticket.lottery.dto.TicketResponse;
import com.example.ticket.lottery.dto.TicketsResponse;
import com.example.ticket.lottery.service.LotteryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LotteryController {

    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @PostMapping("/admin/lotteries")
    public ResponseEntity<TicketResponse> createLottery(@RequestBody LotteryRequest lotteryRequest) {
        return new ResponseEntity<>( lotteryService.createLottery(lotteryRequest), HttpStatus.CREATED);
    }

    @GetMapping("/lotteries")
    public ResponseEntity<TicketsResponse> getAllLotteries(){

        return new ResponseEntity<>(lotteryService.getLotteries(), HttpStatus.OK);
    }
}
