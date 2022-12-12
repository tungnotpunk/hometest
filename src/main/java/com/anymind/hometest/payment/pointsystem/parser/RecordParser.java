package com.anymind.hometest.payment.pointsystem.parser;

import java.util.Date;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import com.anymind.hometest.payment.pointsystem.grpc.*;
import com.anymind.hometest.payment.pointsystem.model.input.*;

public class RecordParser {

  public static Record parse(RecordRequest req) {
    return new Record();
  }
}