package com.immoc.order.utils;

import java.util.Random;

/**
 * @author xiaozefeng
 */
public class KeyUtil {

    public static synchronized String genUniqueKey() {
        Random random = new Random();
        int number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
