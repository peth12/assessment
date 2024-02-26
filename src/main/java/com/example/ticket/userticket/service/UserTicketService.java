package com.example.ticket.userticket.service;


import com.example.ticket.exception.NotFoundException;
import com.example.ticket.lottery.dto.TicketResponse;
import com.example.ticket.lottery.model.Lottery;
import com.example.ticket.lottery.repository.LotteryRepository;
import com.example.ticket.userticket.dto.UserTicketResponse;
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

    public UserTicketResponse buyLottery(String userId, String ticketId) {
        UserTicket userTicket = new UserTicket();

        Optional<Lottery> optionalLottery = lotteryRepository.findById(ticketId);
        Lottery lottery;

        if(optionalLottery.isEmpty() || optionalLottery.get().getAmount() < 1){
            throw new NotFoundException("This ticket number was not found.");
        }

        lottery = optionalLottery.get();
        lottery.setTicket(ticketId);

        UserTicket createLotteryOfUser = userTicketRepository.save(UserTicket.builder()
                .userId(userId)
                .lottery(lottery)
                .build()
        );

        lottery.setAmount(0);
        lotteryRepository.save(lottery);
        return new UserTicketResponse(createLotteryOfUser.getId());

    }
}
