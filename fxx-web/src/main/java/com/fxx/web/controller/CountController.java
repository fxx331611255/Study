package com.fxx.web.controller;

import java.util.Date;

import javax.annotation.Resource;

import com.fxx.service.CountService;
import com.fxx.utils.vo.CountVo;
import com.fxx.utils.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 算法系列
 * Created by FengXiaoxi on 2018/4/19.
 */
@RestController
@RequestMapping("count")
public class CountController {

    @Resource
    private CountService countService;

    /**
     * 题目： 将圆分成 n 个扇形,用 m 种不同颜色染色,并且相邻的扇形不同色,问有多少种着色方法。
     */
    @PostMapping("count01")
    public ResponseVo count01() {
        Date start = new Date();
        long a = countService.count01(13, 13);
        Date dateA = new Date();
        long b = countService.count01Recursion(13, 13);
        Date dateB = new Date();
        System.out.println("a = " + a + ";耗时 = " + (dateA.getTime() - start.getTime()));
        System.out.println("b = " + b + ";耗时 = " + (dateB.getTime() - dateA.getTime()));
        return null;
    }

    /**
     * 斐波那契数列
     * 兔子数列。第n天的总数sum=基数【即n-1天的兔子总数】+新增兔子数量【即第n-2天的兔子总数】
     */

    @PostMapping("count02")
    public ResponseVo count02() {
        Date start = new Date();
        int a = countService.count02(33);
        Date dateA = new Date();
        int b = countService.count02New(33);
        Date dateB = new Date();
        System.out.println("a = " + a + ";耗时 = " + (dateA.getTime() - start.getTime()));
        System.out.println("b = " + b + ";耗时 = " + (dateB.getTime() - dateA.getTime()));
        return null;
    }

    /**
     * 二分查找
     * 查中间,比大小,筛选一半,递归
     * */


    /**
     * 给定数组,给定值target,查target下标,如不存在,返回-1,-1
     * */

    /**
     * 找字符串里的最长连续串
     */
    @PostMapping("count03")
    public ResponseVo count03(@RequestBody CountVo vo) {
        String str = vo.getString().replaceAll("[a-z]", " ");

        return null;
    }
}
