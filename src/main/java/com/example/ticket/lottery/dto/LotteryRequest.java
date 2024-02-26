package com.example.ticket.lottery.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotteryRequest {


    private String ticket;
    private Integer price;
    private Integer amount;

}
