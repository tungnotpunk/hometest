package com.anymind.hometest.payment.pointsystem.parser;

import java.util.Date;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import java.text.ParseException;
import com.anymind.hometest.payment.pointsystem.grpc.*;
import com.anymind.hometest.payment.pointsystem.util.*;
import com.anymind.hometest.payment.pointsystem.model.input.*;

public class ChargeParser {

  public static Charge parse(ChargeRequest req) {
    Charge input = new Charge();
    try{
      input.setPrice(Double.parseDouble(req.getPrice()));
      if (input.getPrice() <= 0) {
        throw new NumberFormatException();
      }
    }
    catch(NumberFormatException nfe){
      input.setError(Status.FAILED_PRECONDITION.withDescription("Price is incorrect").asRuntimeException());
      return input;
    }
    PaymentMethod paymentMethod = PaymentMethod.findByName(req.getPaymentMethod());
    if (paymentMethod == null) {
      input.setError(Status.FAILED_PRECONDITION.withDescription("PaymentMethod is not in supported list").asRuntimeException());
      return input;
    }
    try {
      input.setDatetime(DateUtil.datetimeToDate(req.getDatetime()));
    } catch (ParseException e) {
      input.setError(Status.FAILED_PRECONDITION.withDescription("Datetime is incorrect").asRuntimeException());
      return input;
    }
    input.setPaymentMethod(paymentMethod);
    input.setPriceModifier(req.getPriceModifier());
    if (input.getPriceModifier() < paymentMethod.getMinRate() || input.getPriceModifier() > paymentMethod.getMaxRate()) {
      input.setError(Status.FAILED_PRECONDITION.withDescription("PriceModifier is incorrect").asRuntimeException());
    }
    return input;
  }
}