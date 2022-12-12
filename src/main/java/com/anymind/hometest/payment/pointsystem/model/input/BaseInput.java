package com.anymind.hometest.payment.pointsystem.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.grpc.StatusRuntimeException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseInput {
  private StatusRuntimeException error;
}