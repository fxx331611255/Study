package com.fxx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.fxx.service.SocketService.port;

@SpringBootApplication
public class FxxWebApplication {

  public static void main(String[] args) throws IOException {
    SpringApplication.run(FxxWebApplication.class, args);

    BigDecimal a = new BigDecimal("10");
    BigDecimal b = new BigDecimal("5");
    System.out.println(a.subtract(b));

//    //↓↓↓socket↓↓
//    //监听指定端口
//    ServerSocket server = new ServerSocket(port);
//    // server将一直等待连接的到来
//    System.out.println("server将一直等待连接的到来");
//    Socket socket = server.accept();
//    // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
//    InputStream inputStream = socket.getInputStream();
//    byte[] bytes = new byte[1024];
//    int len;
//    StringBuilder sb = new StringBuilder();
//    while ((len = inputStream.read(bytes)) != -1) {
//      //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
//      sb.append(new String(bytes, 0, len, "UTF-8"));
//    }
//    System.out.println("get message from client客户端: " + sb);
//
//    OutputStream outputStream = socket.getOutputStream();
//    outputStream.write("Hello Client客户端,I get the message.".getBytes("UTF-8"));
//
//    inputStream.close();
//    socket.close();
//    server.close();
  }
}
