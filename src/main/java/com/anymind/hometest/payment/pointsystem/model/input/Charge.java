package com.anymind.hometest.payment.pointsystem.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Charge extends BaseInput {
  private double price;
  private double priceModifier;
  private PaymentMethod paymentMethod;
  private Date datetime;
}