package com.linli.consumer.mock;

import android.util.Log;

import com.linli.consumer.base.ActivityCollector;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.iface.HandleDataNull;
import com.linli.consumer.iface.HandleError;

/**
 * Created by tomoyo on 2016/11/23.
 *
 * 网络请求处理全局异常
 *
 * 名字得换，不能用这个 TODO
 */

public class Mocking {

    private static Mocking mInstance;



    /**
     * 获得处理全局网络异常的单例
     * */
    public static synchronized Mocking getInstance(){
        if(mInstance == null){
            mInstance = new Mocking();
        }
        return mInstance;
    }


    /**
     * 网络请求失败后由此方法处理
     * */
    public HandleError handleGlobeResponseError(){
        return new HandleError() {
            @Override
            public void error(Throwable t) {
                for(int i = 0;i<ActivityCollector.activities.size();i++){
                    ((BaseActivity)ActivityCollector.activities.get(i)).dismiss();
                }
                if(t != null){
                    try {
                        if(ActivityCollector.activities.size()>0){
                            Log.i("WATER","Error at "+ActivityCollector.activities.get(ActivityCollector.activities.size()-1)
                                    +t.getMessage());
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
//                Toast.makeText(AppContext.getInstance(),"无网络或网络环境较差",Toast.LENGTH_SHORT).show();
            }
        };
    }

    /**
     * 网络请求成功，但是数据为空或者数据响应结果不为200，由此方法处理
     * */
    public HandleDataNull handleGlobeHandleDataNull(){
        return new HandleDataNull() {
            @Override
            public void dataNull() {
                for(int i = 0;i<ActivityCollector.activities.size();i++){
                    ((BaseActivity)ActivityCollector.activities.get(i)).dismiss();
                }
//                Toast.makeText(AppContext.getInstance(),"无网络或网络环境较差",Toast.LENGTH_SHORT).show();
            }
        };
    }


}
