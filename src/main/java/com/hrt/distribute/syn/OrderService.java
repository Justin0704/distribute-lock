package com.hrt.distribute.syn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class OrderService implements Runnable{

    private OrderNumGenerate orderNumGenerate = new OrderNumGenerate();

    private static CountDownLatch countDownLatch = new CountDownLatch(50);

    private static List<String> retList = new ArrayList<String>();

    public void run() {
        countDownLatch.countDown();
        retList.add(orderNumGenerate.getNumber());
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("-------开始生成唯一订单号------");

        for(int i = 0;i < 50; i++){
            new Thread(new OrderService()).start();
        }
        countDownLatch.await();
        Thread.sleep(3000);
        Collections.sort(retList);
        for(String str : retList){
            System.out.println(str);
        }
        System.out.println("size = " + retList.size());
    }
}
