package com.hrt.distribute.lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderNumGenerate {

    public static int count = 0;

    private Lock lock = new ReentrantLock();

    public String getNumber(){
        try{
            lock.lock();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(new Date()) + "-" + (++count);
        }finally {
            lock.unlock();
        }

    }
}
