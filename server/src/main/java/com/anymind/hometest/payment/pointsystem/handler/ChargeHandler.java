package com.anymind.hometest.payment.pointsystem.handler;

import java.util.logging.Logger;
import io.grpc.stub.StreamObserver;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.anymind.hometest.payment.pointsystem.parser.*;
import com.anymind.hometest.payment.pointsystem.grpc.*;
import com.anymind.hometest.payment.pointsystem.repo.*;
import com.anymind.hometest.payment.pointsystem.model.input.*;
import com.anymind.hometest.payment.pointsystem.model.output.*;
import com.anymind.hometest.payment.pointsystem.model.db.*;

@Component
public class ChargeHandler {

  private static final Logger logger = Logger.getLogger(ChargeHandler.class.getName());
  private static ChargeRecordRepository chargeRecordRepository;

  @Autowired
  public void setChargeRecordRepository(ChargeRecordRepository chargeRecordRepository){
      ChargeHandler.chargeRecordRepository = chargeRecordRepository;
  }

  public static void handle(ChargeRequest req, StreamObserver<ChargeReply> responseObserver) {
    try {
      Charge input = ChargeParser.parse(req);
      if (input.getError() != null) {
        throw input.getError();
      }
      Charged output = new Charged(input.getPrice(), input.getPriceModifier(), input.getPaymentMethod().getPointRate());
      ChargeRecord chargeRecord = new ChargeRecord(input, output);
      chargeRecordRepository.save(chargeRecord);
      ChargeReply reply = ChargeReply.newBuilder()
        .setFinalPrice(output.getFinalPriceInString())
        .setPoints(output.getPoints())
        .build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
    } catch (StatusRuntimeException e) {
      responseObserver.onError(e);
    }
  }
}