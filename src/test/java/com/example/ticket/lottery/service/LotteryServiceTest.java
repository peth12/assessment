package com.example.ticket.lottery.service;

import com.example.ticket.exception.BadRequestException;
import com.example.ticket.exception.NotFoundException;
import com.example.ticket.lottery.dto.LotteryRequest;
import com.example.ticket.lottery.dto.TicketResponse;
import com.example.ticket.lottery.dto.TicketsResponse;
import com.example.ticket.lottery.model.Lottery;
import com.example.ticket.lottery.repository.LotteryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LotteryServiceTest {

    @Mock
    private LotteryRepository lotteryRepository;

    private LotteryService lotteryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        lotteryService = new LotteryService(lotteryRepository);
    }

    @Test
    void createLottery_WithValidRequest_ShouldReturnTicketResponse() {

        String ticketNumber = "123456";
        LotteryRequest lotteryRequest = new LotteryRequest();
        lotteryRequest.setTicket(ticketNumber);
        lotteryRequest.setAmount(1);
        lotteryRequest.setPrice(100);

        when(lotteryRepository.findById(ticketNumber)).thenReturn(Optional.empty());
        when(lotteryRepository.save(any(Lottery.class))).thenAnswer(invocation -> {
            Lottery lottery = invocation.getArgument(0);
            return lottery;
        });


        TicketResponse ticketResponse = lotteryService.createLottery(lotteryRequest);


        assertEquals(ticketNumber, ticketResponse.getTicket());
        verify(lotteryRepository, times(1)).findById(ticketNumber);
        verify(lotteryRepository, times(1)).save(any(Lottery.class));
    }

    @Test
    void createLottery_WithDuplicateTicketNumber_ShouldThrowBadRequestException() {

        String ticketNumber = "123456";
        LotteryRequest lotteryRequest = new LotteryRequest();
        lotteryRequest.setTicket(ticketNumber);

        when(lotteryRepository.findById(ticketNumber)).thenReturn(Optional.of(new Lottery()));


        assertThrows(BadRequestException.class, () -> lotteryService.createLottery(lotteryRequest));
        verify(lotteryRepository, times(1)).findById(ticketNumber);
        verify(lotteryRepository, never()).save(any());
    }



    @Test
    void getLotteries_WithNoExistingLotteries_ShouldThrowNotFoundException() {

        when(lotteryRepository.findAll()).thenReturn(Collections.emptyList());


        assertThrows(NotFoundException.class, () -> lotteryService.getLotteries());
        verify(lotteryRepository, times(1)).findAll();
    }
}
