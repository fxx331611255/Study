package com.fxx.web.controller;

import javax.annotation.Resource;

import com.fxx.service.SocketService;
import com.fxx.utils.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by FengXiaoxi on 2018/4/24.
 */
@RequestMapping("socket")
@RestController
public class SocketController {

  @Resource
  private SocketService socketService;

  @PostMapping("test")
  public ResponseVo test() throws Exception {
    System.out.println(" socket.test  start!");

    socketService.socket();

    System.out.println(" socket.test end !");
    return null;
  }

}
