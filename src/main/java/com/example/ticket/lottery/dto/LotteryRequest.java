package com.example.ticket.lottery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LotteryRequest {
    private String ticket;
    private Integer price;
    private Integer amount;
}
