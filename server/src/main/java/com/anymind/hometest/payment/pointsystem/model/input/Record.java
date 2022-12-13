package com.anymind.hometest.payment.pointsystem.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record extends BaseInput {
  private Date startDateTime;
  private Date endDateTime;
}