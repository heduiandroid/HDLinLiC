package com.linli.consumer.mock;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.linli.consumer.common.FastJsonConverterFactory;
import com.linli.consumer.common.HandleSuccess;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by tomoyo on 2016/11/23.
 */

public class MockNet<T> {




    public static List<String> getTitle(){
        List<String> list = new ArrayList<>();
        for(int i = 0;i<6;i++){
            String name = "朝花夕拾";
            name = name+i;
            list.add(name);
        }

        return list;
    }

    public static OkHttpClient httpLog(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        return client;
    }

    public static IMocking createMcApi(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(FastJsonConverterFactory.create())
                .client(MockNet.httpLog())
                .build();
        IMocking mcApi = retrofit.create(IMocking.class);
        return mcApi;
    }

    public static void MockData(final HandleSuccess<NewsBean> handleSuccess){
        int i = (int)(1+Math.random()*10);
        String [] BB = {"top","shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};
        /*Call<ResponseBody> call = createMcApi(IMocking.news_base_url).getNewsRequest(BB[i - 1],IMocking.news_key);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    NewsBean bean = JSON.parseObject(response.body().string(),NewsBean.class);
                    //NewsBean bean = gson.fromJson(response.body().string(),NewsBean.class);
                    handleSuccess.success(bean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Mocking.getInstance().handleGlobeResponseError().error(t);
            }
        });*/
        Call<NewsBean> call1 = createMcApi(IMocking.news_base_url).getRequest(BB[i - 1],IMocking.news_key);
        call1.enqueue(handleSuccess);
    }




    /**
     * 美食的测试数据
     * */
    public static final String [] FoodTitle = {"top","shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};
    public static void MockFoodData(final HandleSuccess<NewsBean> handleSuccess,String title){
        int i = (int)(1+Math.random()*10);

        Call<ResponseBody> call = createMcApi(IMocking.news_base_url).getNewsRequest("top",IMocking.news_key);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    NewsBean bean = JSON.parseObject(response.body().string(),NewsBean.class);
                    //NewsBean bean = gson.fromJson(response.body().string(),NewsBean.class);
                    handleSuccess.success(bean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("WATER",t.getMessage());
                Mocking.getInstance().handleGlobeResponseError().error(t);
            }
        });
    }
    public static final String [] FoodName = {"蒸羊羔","蒸熊掌","蒸鹿尾儿",
            "烧花鸭","烧雏鸡儿","烧子鹅"
            ,"卤煮咸鸭","酱鸡","腊肉","松花","小肚儿"
            ,"晾肉","香肠","什锦苏盘",
            "熏鸡","白肚儿","清蒸八宝猪","江米酿鸭子","罐儿野鸡","罐儿鹌鹑","卤什锦","卤子鹅","卤虾"
            ,"烩虾","炝虾仁儿","山鸡","兔脯","菜蟒","银鱼",
            "清蒸哈什蚂","烩鸭腰儿","烩鸭条儿","清拌鸭丝儿","黄心管儿",
            "焖白鳝","焖黄鳝","豆鼓鲇鱼","锅烧鲇鱼","烀皮甲鱼","锅烧鲤鱼","抓炒鲤鱼"  };

    public static void postMessage(final HandleSuccess<MockBean> handleSuccess){
        Call<ResponseBody> call = createMcApi(IMocking.message).getMessage("10","1","69");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    MockBean bean = JSON.parseObject(response.body().string(),MockBean.class);
                    handleSuccess.success(bean);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
