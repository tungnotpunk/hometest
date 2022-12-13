package com.anymind.hometest.payment.pointsystem.parser;

import java.util.Date;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import java.text.ParseException;
import com.anymind.hometest.payment.pointsystem.grpc.*;
import com.anymind.hometest.payment.pointsystem.util.*;
import com.anymind.hometest.payment.pointsystem.model.input.*;

public class RecordParser {

  public static Record parse(RecordRequest req) {
    Record input = new Record();
    try {
      input.setStartDateTime(DateUtil.datetimeWithTimezone(req.getStartDateTime()));
    } catch (ParseException e) {
      input.setError(Status.FAILED_PRECONDITION.withDescription("StartDateTime is incorrect").asRuntimeException());
      return input;
    }
    try {
      input.setEndDateTime(DateUtil.datetimeWithTimezone(req.getEndDateTime()));
    } catch (ParseException e) {
      input.setError(Status.FAILED_PRECONDITION.withDescription("EndDateTime is incorrect").asRuntimeException());
    }
    return input;
  }
}