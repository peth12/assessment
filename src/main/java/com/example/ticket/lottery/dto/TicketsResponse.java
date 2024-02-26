package com.example.ticket.lottery.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketsResponse {

    private List<String> tickets;
}
