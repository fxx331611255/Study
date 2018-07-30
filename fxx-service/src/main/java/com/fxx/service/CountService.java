package com.fxx.service;

import org.springframework.stereotype.Service;

/**
 * Created by FengXiaoxi on 2018/4/19.
 */
@Service
public class CountService {

  /**
   * 题目： 将圆分成 n 个扇形,用 m 种不同颜色染色,并且相邻的扇形不同色,问有多少种着色方法。
   * An=(m−1)n次方+(m−1)∗(−1)n次方
   */

  public long count01(int m, int n) {
    if (n <= 0) {
      return 0;
    } else if (n == 1) {
      return m;
    } else if (n == 2) {
      return m * (m - 1);
    } else {
      long a = m - 1;
      int b = -1;
      for (int i = 1; i < n; i++) {
        a = a * (m - 1);
        b = b * -1;
      }
      return a + (m - 1) * b;
    }
  }

  /**
   * 题目： 将圆分成 n 个扇形,用 m 种不同颜色染色,并且相邻的扇形不同色,问有多少种着色方法。
   * 利用递归实现
   * 若第 nn 个扇形和第一个扇形同色，把这两个扇形合成一块
   * An=m∗(m−1)(n−1)次方−An−1
   */
  public long count01Recursion(int m, int n) {
    if (n <= 0) {
      return 0;
    } else if (n == 1) {
      return m;
    } else if (n == 2) {
      return m * (m - 1);
    } else {
      long a = m - 1;
      for (int i = 1; i < n - 1; i++) {
        a = a * (m - 1);
      }
      return m * a - count01Recursion(m, n - 1);
    }
  }

  /**
   * 斐波那契数列
   * 0,1,1,2,3,5,8``````
   */
  public int count02(int n) {
    if (n < 0) {
      return -1;
    } else if (n == 0) {
      return 0;
    } else if (n == 1 || n == 2) {
      return 1;
    } else {
      return count02(n - 1) + count02(n - 2);
    }
  }

  /**
   * 斐波那契数列
   * 数值过大递归会内存溢出
   * 定义变量
   * 数组也可实现
   */
  public int count02New(int n) {
    if (n < 0) {
      return -1;
    } else if (n == 0) {
      return 0;
    } else if (n == 1 || n == 2) {
      return 1;
    } else {
      int a = 0;
      int b = 1;
      int c = 1;
      for (int i = 3; i <= n; i++) {
        a = b + c;
        b = c;
        c = a;
      }
      return a;
    }
  }


}
