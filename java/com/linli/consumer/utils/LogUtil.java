package com.linli.consumer.utils;

import android.util.Log;

/**
 * Created by tomoyo on 2016/11/7.
 */

public class LogUtil {

    public static final boolean isDebug = true;
    private static final String TAG = "WATER";

    private LogUtil(){
        /* this class cannot be instantiated*/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void i(String message){
        if(isDebug){
            Log.i(TAG,message);
        }
    }

    public static void e(String message){
        if(isDebug){
            Log.e(TAG,message);
        }
    }

    public static void v(String message){
        if(isDebug){
            Log.v(TAG,message);
        }
    }


    private static String getClassName(){
        String result = "";
        StackTraceElement thisStackTraceElement =
                (new Exception()).getStackTrace()[2];
        result = thisStackTraceElement.getClassName();
        int lastIndex = result.lastIndexOf(".");
        result = result.substring(lastIndex + 1,result.length());
        return result;
    }

}
