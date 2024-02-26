package com.example.ticket.lottery.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotteryRequest {
    private String ticket;
    private Integer price;
    private Integer amount;
}
