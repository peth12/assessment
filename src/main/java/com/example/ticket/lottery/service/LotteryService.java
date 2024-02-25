package com.example.ticket.lottery.service;

import com.example.ticket.exception.BadRequestException;
import com.example.ticket.lottery.dto.LotteryRequest;
import com.example.ticket.lottery.model.Lottery;
import com.example.ticket.lottery.repository.LotteryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LotteryService {

    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public String createLottery(LotteryRequest lotteryRequest)  {
        Optional<Lottery> optionalLottery = lotteryRepository.findById(Long.valueOf(lotteryRequest.getTicket()));

        if(optionalLottery.isPresent()){
            throw new BadRequestException("Unable to add duplicate ticket numbers.");
        }else {

            Lottery lottery = new Lottery();
            lottery.setTicket(lotteryRequest.getTicket());
            lottery.setPrice(lotteryRequest.getPrice());
            lottery.setAmount(lottery.getAmount());

            lotteryRepository.save(lottery);

            return lottery.getTicket();
        }
    }


}
