package com.anymind.hometest.payment.pointsystem.util;

public class NumberUtil {

  public static String doubleToTextWithDecimals(double number, int dicimals) {
    return String.format("%." + dicimals + "f", number);
  }
}