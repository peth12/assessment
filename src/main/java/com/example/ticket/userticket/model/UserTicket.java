package com.example.ticket.userticket.model;


import com.example.ticket.lottery.model.Lottery;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ticket_id" , referencedColumnName = "ticket_id")
    private Lottery lottery;
}
