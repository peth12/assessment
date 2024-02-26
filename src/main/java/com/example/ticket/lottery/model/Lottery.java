package com.example.ticket.lottery.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lottery")
public class Lottery {

    @Id
    @Column(name = "ticket_id", length = 6)
    @Size(min = 6, max = 6)
    private String ticket;

    private Integer price;

    private Integer amount;

}
