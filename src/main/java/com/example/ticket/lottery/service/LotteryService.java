package com.example.ticket.lottery.service;

import com.example.ticket.exception.BadRequestException;
import com.example.ticket.exception.NotFoundException;
import com.example.ticket.lottery.dto.LotteryRequest;
import com.example.ticket.lottery.dto.TicketResponse;
import com.example.ticket.lottery.model.Lottery;
import com.example.ticket.lottery.dto.TicketsResponse;
import com.example.ticket.lottery.repository.LotteryRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LotteryService {

    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public TicketResponse createLottery(LotteryRequest lotteryRequest) {
        Optional<Lottery> optionalLottery = lotteryRepository.findById(lotteryRequest.getTicket());


        if(optionalLottery.isPresent()) {
            throw new BadRequestException("Unable to add duplicate ticket numbers.");
        } else {
            if(lotteryRequest.getTicket().length() != 6 || !lotteryRequest.getTicket().matches("\\d{6}")){
                throw new BadRequestException("Ticket numbers are numbers only and have 6 digits.");
            }
            return new TicketResponse(lotteryRepository.save(Lottery.builder()
                    .amount(lotteryRequest.getAmount())
                    .price(lotteryRequest.getPrice())
                    .ticket(lotteryRequest.getTicket())
                    .build()
            ).getTicket());
        }
    }


    public TicketsResponse getLotteries() {
        List<String> optionalLotteries = lotteryRepository.findAll().stream().map(Lottery::getTicket).toList();

        if(optionalLotteries.isEmpty()){
            throw new NotFoundException("There is no ticket in the system.");
        }

        return new TicketsResponse(lotteryRepository.findAll().stream().map(Lottery::getTicket).toList());

    }

}
