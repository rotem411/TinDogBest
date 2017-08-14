package com.rotemarbiv.tin;

import android.app.Application;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by dafnaarbiv on 24/07/2017.
 * this is insted of the server for now
 */

public class MyApp extends Application{
    public static Map<String, String> mGlobalUsers = new HashMap<String, String>();
}
