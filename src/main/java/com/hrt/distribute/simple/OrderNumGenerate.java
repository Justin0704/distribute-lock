package com.hrt.distribute.simple;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumGenerate {

    private static int count = 0;

    public String getNumber(){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date()) + "-" + (++count);
    }
}
