package com.anymind.hometest;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.anymind.hometest.payment.pointsystem.HometestServer;

@SpringBootApplication
public class HometestApplication {

    private static final Logger logger = Logger.getLogger(HometestApplication.class.getName());
    private static String grpcServerPort;
    @Value("${grpc.server.port}")
    public void setGrpcServerPort(String grpcServerPort){
        HometestApplication.grpcServerPort = grpcServerPort;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(HometestApplication.class, args);
        HometestServer.run(Integer.parseInt(grpcServerPort));
    }

}
