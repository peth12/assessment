package com.example.ticket.lottery.controller;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class TicketsResponse {

    private List<String> tickets;
}
