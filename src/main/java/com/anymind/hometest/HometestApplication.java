package com.anymind.hometest;

import java.io.IOException;
import com.anymind.hometest.payment.pointsystem.HometestServer;

public class HometestApplication {

  public static void main(String[] args) throws IOException, InterruptedException {
    HometestServer.run();
  }
}
