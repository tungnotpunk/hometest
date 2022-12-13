package com.anymind.hometest;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import com.anymind.hometest.payment.pointsystem.HometestServer;

@SpringBootApplication
@EnableAsync
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

  @Bean
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);
    executor.setMaxPoolSize(20);
    executor.setQueueCapacity(500);
    executor.setKeepAliveSeconds(60);
    executor.setThreadNamePrefix("task-");
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
    executor.initialize();
    return executor;
  }   

}
