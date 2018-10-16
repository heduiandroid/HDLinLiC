package com.linli.consumer.net;

import com.linli.consumer.common.Common;
import com.linli.consumer.common.FastJsonConverterFactory;
import com.linli.consumer.iface.HandleError;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by tomoyo on 2016/11/4.
 */

public class NetUtil {
    public static final String BASE_URL_V2 = Common.myUrl;     //线上域名地址
    @Deprecated
    private static Converter.Factory fastJsonConverter = FastJsonConverterFactory.create(); //converter转换器
    private static OkHttpClient okHttpClient = null;
    private static Map<String, Object> serviceCache = new HashMap<>();      //api缓存
    private static IFoodsApi iFoodsApi ;    //美食api
    private static IShopApi iShopApi;       //商城api

    private static OkHttpClient httpLog() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder()
                    //.readTimeout(30, TimeUnit.SECONDS)
                    //.writeTimeout(30,TimeUnit.SECONDS)
                    //.connectTimeout(30,TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();
        }
        return okHttpClient;
    }

    private static NetUtil mInstance;   //单例

    public static synchronized NetUtil getInstance() {
        if (mInstance == null) {
            mInstance = new NetUtil();
        }
        return mInstance;
    }

    /**
     * 捕获全局网络异常
     * */
    public HandleError handleGlobeResponseError() {
        return new HandleError() {
            @Override
            public void error(Throwable t) {
//                Toast.makeText(AppContext.getInstance(),"无网络或网络环境较差",Toast.LENGTH_SHORT).show();
                //Log.i("WATER",t.getMessage());
            }
        };
    }

    public static IIntrestBuyApi createVoiceApi(String baseUrl){
        IIntrestBuyApi iIntrestBuy = (IIntrestBuyApi)serviceCache.get(baseUrl);
        if (iIntrestBuy == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(NetUtil.httpLog())
                    .build();
            iIntrestBuy = retrofit.create(IIntrestBuyApi.class);
            serviceCache.put(baseUrl, iIntrestBuy);
        }
        return iIntrestBuy;
    }


    /**
     * 邻里api
     * @param baseUrl 根地址
     * */
    public static CafeNetApi createCnApi(String baseUrl){
        CafeNetApi cafeNetApi = (CafeNetApi)serviceCache.get(baseUrl);
        if(cafeNetApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(NetUtil.httpLog())
                    .build();
            cafeNetApi = retrofit.create(CafeNetApi.class);
            serviceCache.put(baseUrl, cafeNetApi);
        }
        return cafeNetApi;
    }
    /**
     * 趣购api
     * @param baseUrl 根地址
     * */
    public static IIntrestBuyApi createMcApi(String baseUrl){
        IIntrestBuyApi iIntrestBuy = (IIntrestBuyApi)serviceCache.get(baseUrl);
        if(iIntrestBuy == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(NetUtil.httpLog())
                    .build();
            iIntrestBuy = retrofit.create(IIntrestBuyApi.class);
            serviceCache.put(baseUrl, iIntrestBuy);
        }
        return iIntrestBuy;
    }


    /**
     * 返回美食的接口
     * @param baseUrl 地址是IFoodApi中的
     * */
    public static IFoodsApi createFoodApi(String baseUrl){
        //IFoodsApi api = (IFoodsApi)serviceCache.get(baseUrl);
        IFoodsApi api = iFoodsApi;
        if(api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(fastJsonConverter)
                    .client(NetUtil.httpLog())
                    .build();
            api = retrofit.create(IFoodsApi.class);
            //serviceCache.put(baseUrl, api);
        }
        return api;
    }

    /**
     * 返回美食的接口，为了使用restful风格的api，建了两个
     *
     * @param baseUrl 地址是IFoodsApiTenant中的
     * */
    public static IFoodsApiTenant createFoodTenantApi(String baseUrl){
        IFoodsApiTenant api = (IFoodsApiTenant)serviceCache.get(baseUrl);
        if(api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(fastJsonConverter)
                    .client(NetUtil.httpLog())
                    .build();
            api = retrofit.create(IFoodsApiTenant.class);
            serviceCache.put(baseUrl, api);
        }
        return api;
    }

    /**
     * 返回商城的接口
     * @param baseUrl 地址是IFoodApi中的
     * */
    public static IShopApi createShopApi(String baseUrl){
        IShopApi api = iShopApi;
        //IShopApi api = (IShopApi)serviceCache.get(baseUrl);
        if(api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(fastJsonConverter)
                    .client(NetUtil.httpLog())
                    .build();
            api = retrofit.create(IShopApi.class);
            //serviceCache.put(baseUrl, api);
        }
        return api;
    }
}
