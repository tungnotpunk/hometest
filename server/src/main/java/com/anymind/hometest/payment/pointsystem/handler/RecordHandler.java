package com.anymind.hometest.payment.pointsystem.handler;

import java.util.logging.Logger;
import java.util.List;
import java.util.Date;
import io.grpc.stub.StreamObserver;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.anymind.hometest.payment.pointsystem.parser.*;
import com.anymind.hometest.payment.pointsystem.grpc.*;
import com.anymind.hometest.payment.pointsystem.repo.*;
import com.anymind.hometest.payment.pointsystem.util.*;
import com.anymind.hometest.payment.pointsystem.model.db.*;
import com.anymind.hometest.payment.pointsystem.model.input.*;

@Component
public class RecordHandler {

  private static final Logger logger = Logger.getLogger(ChargeHandler.class.getName());
  private static ChargeRecordRepository chargeRecordRepository;

  @Autowired
  public void setChargeRecordRepository(ChargeRecordRepository chargeRecordRepository){
      RecordHandler.chargeRecordRepository = chargeRecordRepository;
  }

  public static void handle(RecordRequest req, StreamObserver<RecordReply> responseObserver) {
    try {
        Record input = RecordParser.parse(req);
        if (input.getError() != null) {
            throw input.getError();
        }
        List<ChargeInHourRecordInterface> records = chargeRecordRepository.findGroupByDatetimeInHour(input.getStartDateTime(), input.getEndDateTime());
        logger.info(String.valueOf(records.size()));
        RecordReply.Builder replyBuilder = RecordReply.newBuilder();
        for (ChargeInHourRecordInterface record : records) {
          ChargeHourRecord sales = ChargeHourRecord.newBuilder()
            .setDatetime(record.getDatetime())
            .setSales(NumberUtil.doubleToTextWithDecimals(record.getSales(), 2))
            .setPoints(record.getPoints())
            .build();
          replyBuilder.addSales(sales);
        }
        responseObserver.onNext(replyBuilder.build());
        responseObserver.onCompleted();
    } catch (StatusRuntimeException e) {
        responseObserver.onError(e);
    }
  }
}