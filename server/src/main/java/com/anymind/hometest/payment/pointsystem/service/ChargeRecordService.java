package com.anymind.hometest.payment.pointsystem.service;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CompletableFuture;
import com.anymind.hometest.payment.pointsystem.repo.*;
import com.anymind.hometest.payment.pointsystem.model.db.*;
import com.anymind.hometest.payment.pointsystem.model.input.*;
import com.anymind.hometest.payment.pointsystem.model.output.*;

@Service
public class ChargeRecordService {

  private static ChargeRecordRepository chargeRecordRepository;

  @Autowired
  public void setChargeRecordRepository(ChargeRecordRepository chargeRecordRepository){
      ChargeRecordService.chargeRecordRepository = chargeRecordRepository;
  }

  @Async
  public void charge(Charge input, Charged output) {
    ChargeRecord chargeRecord = new ChargeRecord(input, output);
    chargeRecordRepository.save(chargeRecord);
  }

  @Async
  public CompletableFuture<List<ChargeInHourRecordInterface>> report(Date startDateTime, Date endDateTime) {
    return CompletableFuture.supplyAsync(() -> {
      return chargeRecordRepository.findGroupByDatetimeInHour(startDateTime, endDateTime);
    });
  }
}
