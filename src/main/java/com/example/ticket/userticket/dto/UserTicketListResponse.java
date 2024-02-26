package com.example.ticket.userticket.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTicketListResponse {

    private List<String> tickets;

    private Integer count;

    private Integer cost;

}
