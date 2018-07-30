package com.fxx.web.controller;

import javax.annotation.Resource;

import com.fxx.service.SocketService;
import com.fxx.service.TestService;
import com.fxx.utils.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by FengXiaoxi on 2018/4/16.
 */
@RestController
@RequestMapping("test")
public class TestController {

  @Resource
  private TestService testService;
  @Resource
  private SocketService socketService;

  @PostMapping("test")
  public ResponseVo test() throws Exception {
    //String a = new String("111");
    //String b = new String(("111"));
    //String c = a.replace("111", "222");
    //
    //System.out.println("a==b = " + a == b);
    //System.out.println("a.eq b = " + a.equals(b));
    //
    //System.out.println("c = " + c);
    //System.out.println("c==a = " + c == a);
    //System.out.println("c.eq a = " + c.equals(a));
    //int x = 1;
    //System.out.println(x == 1 ? true : false);
    //
    //System.out.println("x = " + testService.test());
    //

    //int n = 48;
    //for (int i = 1; i < n; i++) {
    //  System.out.println("第" + (48 - i) + "个月,取1000前的钱" + testService.lixi(i));
    //}

    return null;
  }


}
