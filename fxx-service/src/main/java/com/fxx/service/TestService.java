package com.fxx.service;

import org.springframework.stereotype.Service;

/**
 * Created by FengXiaoxi on 2018/4/16.
 */
@Service
public class TestService {


  public int test() {
    int x = 1;
    try {
      return x;
    } finally {
      x++;
      System.out.println("finally输出" + x);
    }
  }

  public double lixi(int n) {
    if (n == 1) {
      return 1000;
    } else {
      return (lixi(n - 1) / (1 + 0.0171 / 12) + 1000);
    }
  }


}
