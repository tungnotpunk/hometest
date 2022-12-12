package com.anymind.hometest.payment.pointsystem.handler;

import java.util.logging.Logger;
import io.grpc.stub.StreamObserver;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import com.anymind.hometest.payment.pointsystem.parser.*;
import com.anymind.hometest.payment.pointsystem.grpc.*;
import com.anymind.hometest.payment.pointsystem.model.input.*;

public class ChargeHandler {

  private static final Logger logger = Logger.getLogger(ChargeHandler.class.getName());


  public static void handle(ChargeRequest req, StreamObserver<ChargeReply> responseObserver) {
    try {
        Charge input = ChargeParser.parse(req);
        if (input.getError() != null) {
            throw input.getError();
        }
        ChargeReply reply = ChargeReply.newBuilder().build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    } catch (StatusRuntimeException e) {
        responseObserver.onError(e);
    }
  }
}