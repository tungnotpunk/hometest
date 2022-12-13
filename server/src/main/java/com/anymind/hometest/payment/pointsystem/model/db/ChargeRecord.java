package com.anymind.hometest.payment.pointsystem.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import com.anymind.hometest.payment.pointsystem.util.*;
import com.anymind.hometest.payment.pointsystem.model.input.*;
import com.anymind.hometest.payment.pointsystem.model.output.*;

@Entity(name = "ChargeRecord")
@Table(name = "charge_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargeRecord implements Serializable {

  private static final long serialVersionUID = 1L;

  public ChargeRecord(Charge charge, Charged charged) {
      this.price = charge.getPrice();
      this.priceModifier = charge.getPriceModifier();
      this.paymentMethod = charge.getPaymentMethod().name();
      this.datetime = charge.getDatetime();
      Date date = new Date(charge.getDatetime().getTime());
      date.setMinutes(0);
      date.setSeconds(0);
      this.datetimeInHour = DateUtil.dateToDatetime(date);
      this.finalPrice = charged.getFinalPrice();
      this.points = charged.getPoints();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private double price;
  private double priceModifier;
  private String paymentMethod;
  private double finalPrice;
  private double points;
  private Date datetime;
  private String datetimeInHour;
}
