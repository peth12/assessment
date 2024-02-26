package com.example.ticket.userticket.service;


import com.example.ticket.exception.NotFoundException;
import com.example.ticket.lottery.model.Lottery;
import com.example.ticket.lottery.repository.LotteryRepository;
import com.example.ticket.userticket.model.UserTicket;
import com.example.ticket.userticket.repository.UserTicketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTicketService {

    private final UserTicketRepository userTicketRepository;
    private final LotteryRepository lotteryRepository;


    public UserTicketService(UserTicketRepository userTicketRepository, LotteryRepository lotteryRepository) {
        this.userTicketRepository = userTicketRepository;
        this.lotteryRepository = lotteryRepository;
    }

    public Long buyLottery(String userId, String ticketId) {
        UserTicket userTicket = new UserTicket();

        Optional<Lottery> optionalLottery = lotteryRepository.findById(ticketId);
        Lottery lottery = new Lottery();

        if(optionalLottery.isEmpty()){
            throw new NotFoundException("This ticket number was not found.");
        }

    }
}
