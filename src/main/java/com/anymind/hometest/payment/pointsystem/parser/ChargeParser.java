package com.anymind.hometest.payment.pointsystem.parser;

import java.util.Date;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import com.anymind.hometest.payment.pointsystem.grpc.*;
import com.anymind.hometest.payment.pointsystem.model.input.*;

public class ChargeParser {

  public static Charge parse(ChargeRequest req) {
    return new Charge();
  }
}