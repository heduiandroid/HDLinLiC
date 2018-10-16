package com.linli.consumer.common;



import com.linli.consumer.mock.Mocking;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by tomoyo on 2016/12/24.
 */

public abstract class HandleSuccess<T> implements Callback<T> {

    abstract public void success(T t);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful() && response.code() == 200) {   //判断为空或size为0
            if (response.body() != null) {
                success(response.body());
            } else {
                Mocking.getInstance().handleGlobeHandleDataNull().dataNull();
            }
        } else {
            Mocking.getInstance().handleGlobeHandleDataNull().dataNull();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        //Log.i("WATER",callMethodAndLine());
        Mocking.getInstance().handleGlobeResponseError().error(t);
    }


    /**
     * 打印异常
     * */
    private  String callMethodAndLine() {
        String result = "at ";
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[1];
        result += thisMethodStack.getClassName()+ "."; //  当前的类名（全名）
        result += thisMethodStack.getMethodName();
        result += "(" + thisMethodStack.getFileName();
        result += ":" + thisMethodStack.getLineNumber() + ")  ";
        return result;
    }
}
