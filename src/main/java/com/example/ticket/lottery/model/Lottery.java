package com.example.ticket.lottery.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "lottery")
public class Lottery {

    @Id
    @Column(name = "ticket_id")
    @Size(min = 6, max = 6)
    private String ticket;

    private Integer price;

    private Integer amount;

}
