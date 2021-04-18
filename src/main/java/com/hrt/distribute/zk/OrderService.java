package com.hrt.distribute.zk;

import com.hrt.distribute.simple.OrderNumGenerate;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements Runnable{

    private OrderNumGenerate orderNumGenerate = new OrderNumGenerate();

    private Lock lock = new ZookeeperDistributeLock();

    private static List<String> retList = new ArrayList<String>();

    public void run() {
        getNumber();
    }
    public void getNumber(){
        try{
            lock.getLock();
            String number = orderNumGenerate.getNumber();
            System.out.println(Thread.currentThread().getName() + ",生成订单id：" + number);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unLock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("生成唯一订单号");
        for(int i = 0;i<50;i++){
            new Thread(new OrderService()).start();
        }
    }
}
