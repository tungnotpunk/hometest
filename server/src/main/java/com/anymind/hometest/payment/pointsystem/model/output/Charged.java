package com.anymind.hometest.payment.pointsystem.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.anymind.hometest.payment.pointsystem.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Charged {
  public Charged(double price, double priceModifier, double pointRate) {
    this.finalPrice = price * priceModifier;
    this.finalPriceInString = NumberUtil.doubleToTextWithDecimals(this.finalPrice, 2);
    this.points = Math.floor(price * pointRate);
  }
  private double finalPrice;
  private String finalPriceInString;
  private double points;
}