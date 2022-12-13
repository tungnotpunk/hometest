package com.anymind.hometest.payment.pointsystem;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.logging.Logger;
import com.anymind.hometest.payment.pointsystem.grpc.*;
import com.anymind.hometest.payment.pointsystem.handler.*;

public class HometestServer {

  private static final Logger logger = Logger.getLogger(HometestServer.class.getName());
  private Server server;

  private void start(int port) throws IOException {
    server = ServerBuilder.forPort(port)
      .addService(new PointSystemImpl())
      .build()
      .start();
    logger.info("Server started, listening on " + port);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        HometestServer.this.stop();
        System.err.println("*** server shut down");
      }
    });
  }

  private void stop() {
    if (server != null) {
      server.shutdown();
    }
  }

  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  public static void run(int port) throws IOException, InterruptedException {
    final HometestServer server = new HometestServer();
    server.start(port);
    server.blockUntilShutdown();
  }
  
  static class PointSystemImpl extends PointSystemGrpc.PointSystemImplBase {

    @Override
    public void charge(ChargeRequest req, StreamObserver<ChargeReply> responseObserver) {
      ChargeHandler.handle(req, responseObserver);
    }

    @Override
    public void report(ReportRequest req, StreamObserver<ReportReply> responseObserver) {
      ReportHandler.handle(req, responseObserver);
    }
  }
}
