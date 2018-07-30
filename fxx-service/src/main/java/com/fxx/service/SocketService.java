package com.fxx.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.springframework.stereotype.Service;

/**
 * Created by FengXiaoxi on 2018/4/24.
 */
@Service
public class SocketService {

  public final static int port = 55533;

  public void socket() throws Exception {
    // 要连接的服务端IP地址和端口
    String host = "127.0.0.1";
    int port = 55533;
    // 与服务端建立连接
    Socket socket = new Socket(host, port);
    // 建立连接后获得输出流
    OutputStream outputStream = socket.getOutputStream();
    String message="你好  服务端";
    socket.getOutputStream().write(message.getBytes("UTF-8"));

    //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
    socket.shutdownOutput();

    InputStream inputStream = socket.getInputStream();
    byte[] bytes = new byte[1024];
    int len;
    StringBuilder sb = new StringBuilder();
    while ((len = inputStream.read(bytes)) != -1) {
      //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
      sb.append(new String(bytes, 0, len,"UTF-8"));
    }
    System.out.println("get message from server服务端: " + sb);


    outputStream.close();
    socket.close();
  }

}
