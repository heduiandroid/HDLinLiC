package com.linli.consumer.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomoyo on 2016/11/7.
 * Activity堆栈式管理
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();

    /**
     * 在创建Activity时会把activity放入此管理器中
     * @param activity
     * */
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    /**
     * 在销毁Activity时会把activity从此管理器中移除
     * @param activity
     * */
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
