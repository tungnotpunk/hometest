package com.anymind.hometest.payment.pointsystem.model.input;

public enum PaymentMethod {
  CASH(0.9, 1, 0.05),
  CASH_ON_DELIVERY(1, 1.02, 0.05),
  VISA(0.95, 1, 0.03),
  MASTERCARD(0.95, 1, 0.03),
  AMEX(0.98, 1.01, 0.02),
  JCB(0.95, 1, 0.05);

  private double minRate;
  private double maxRate;
  private double pointRate;

  PaymentMethod(double minRate, double maxRate, double pointRate) {
    this.minRate = minRate;
    this.maxRate = maxRate;
    this.pointRate = pointRate;
  }

  public static PaymentMethod findByName(String name) {
    for (PaymentMethod paymentMethod : values()) {
      if (paymentMethod.name().equalsIgnoreCase(name)) {
        return paymentMethod;
      }
    }
    return null;
  }
  public double getMinRate() {
    return minRate;
  };
  public double getMaxRate() {
    return maxRate;
  };
  public double getPointRate() {
    return pointRate;
  };
}