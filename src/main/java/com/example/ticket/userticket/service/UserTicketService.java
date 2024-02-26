package com.example.ticket.userticket.service;


import com.example.ticket.exception.BadRequestException;
import com.example.ticket.exception.NotFoundException;
import com.example.ticket.lottery.dto.TicketResponse;
import com.example.ticket.lottery.model.Lottery;
import com.example.ticket.lottery.repository.LotteryRepository;
import com.example.ticket.userticket.dto.UserTicketResponse;
import com.example.ticket.userticket.model.UserTicket;
import com.example.ticket.userticket.repository.UserTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

        if(!userId.matches("\\d{10}"))throw new BadRequestException("userId are numbers only and have 10 digits.");

        if(!ticketId.matches("\\d{6}")) throw new BadRequestException("Ticket numbers are numbers only and have 6 digits.");


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



    @Transactional
    public TicketResponse deleteLotteryUser(String userId, String ticketId) {

        if(!userId.matches("\\d{10}"))throw new BadRequestException("userId are numbers only and have 10 digits.");

        if(!ticketId.matches("\\d{6}")) throw new BadRequestException("Ticket numbers are numbers only and have 6 digits.");

        List<UserTicket> userTicket = userTicketRepository.findLotteriesByUserID(userId, ticketId);

        if(!userTicket.isEmpty()) {
            userTicketRepository.deleteAll(userTicket);
            Optional<Lottery> optionalLottery = lotteryRepository.findById(ticketId);

            if(optionalLottery.isPresent()) {
                Lottery lottery = optionalLottery.get();
                lottery.setAmount(1);
                lotteryRepository.save(lottery);
                return new TicketResponse(ticketId);
            }else{
                throw new NotFoundException("Information not found for this user ID or ticket ID.");
            }
        } else {
            throw new NotFoundException("Information not found for this user ID or ticket ID.");
        }
    }


}
