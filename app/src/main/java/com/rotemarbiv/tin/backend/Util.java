package com.rotemarbiv.tin.backend;

/**
 * Created by Omri on 9/2/2017
 */

public class Util {

    public static void checkNotNull(Object obj) {
        if (obj == null)
            throw new NullPointerException("obj was null");
    }

    public static void checkNotNull(Object obj, String errMsg) {
        if (obj == null)
            throw new NullPointerException(errMsg);
    }

}
