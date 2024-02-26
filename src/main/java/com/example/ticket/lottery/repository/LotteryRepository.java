package com.example.ticket.lottery.repository;

import com.example.ticket.lottery.model.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryRepository extends JpaRepository<Lottery, String> {
}
