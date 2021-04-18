package com.hrt.distribute.syn;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumGenerate {

    private static int count = 0;

    private static Object lock = new Object();

    public String getNumber(){
        synchronized (lock){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(new Date()) + "-" + (++count);
        }
    }
}
