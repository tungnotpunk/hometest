package com.anymind.hometest.payment.pointsystem.handler;

import java.util.logging.Logger;
import java.util.List;
import io.grpc.stub.StreamObserver;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CompletableFuture;
import com.anymind.hometest.payment.pointsystem.parser.*;
import com.anymind.hometest.payment.pointsystem.grpc.*;
import com.anymind.hometest.payment.pointsystem.service.*;
import com.anymind.hometest.payment.pointsystem.util.*;
import com.anymind.hometest.payment.pointsystem.model.db.*;
import com.anymind.hometest.payment.pointsystem.model.input.*;

@Component
public class ReportHandler {

  private static final Logger logger = Logger.getLogger(ReportHandler.class.getName());
  private static ChargeRecordService chargeRecordService;

  @Autowired
  public void setChargeRecordService(ChargeRecordService chargeRecordService){
      ReportHandler.chargeRecordService = chargeRecordService;
  }

  public static void handle(ReportRequest req, StreamObserver<ReportReply> responseObserver) {
    try {
      Report input = ReportParser.parse(req);
      if (input.getError() != null) {
        throw input.getError();
      }
      CompletableFuture<List<ChargeInHourRecordInterface>> records = chargeRecordService.report(input.getStartDateTime(), input.getEndDateTime());
      //just prepare for if it becomes get multi report future
      CompletableFuture.allOf(records).join();
      ReportReply.Builder replyBuilder = ReportReply.newBuilder();
      for (ChargeInHourRecordInterface record : records.get()) {
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
    } catch (Exception e) {
      responseObserver.onError(Status.UNKNOWN.withDescription("Server Error - " + e.getMessage()).asRuntimeException());
    }
  }
}