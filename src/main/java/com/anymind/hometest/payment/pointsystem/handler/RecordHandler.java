package com.anymind.hometest.payment.pointsystem.handler;

import java.util.logging.Logger;
import io.grpc.stub.StreamObserver;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import com.anymind.hometest.payment.pointsystem.parser.*;
import com.anymind.hometest.payment.pointsystem.grpc.*;
import com.anymind.hometest.payment.pointsystem.model.input.*;

public class RecordHandler {

  private static final Logger logger = Logger.getLogger(ChargeHandler.class.getName());


  public static void handle(RecordRequest req, StreamObserver<RecordReply> responseObserver) {
    try {
        Record input = RecordParser.parse(req);
        if (input.getError() != null) {
            throw input.getError();
        }
        RecordReply reply = RecordReply.newBuilder().build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    } catch (StatusRuntimeException e) {
        responseObserver.onError(e);
    }
  }
}