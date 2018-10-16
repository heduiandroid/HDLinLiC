package com.linli.consumer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.common.Common;
import com.linli.consumer.domain.City;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by tomoyo on 2017/2/13.
 */

public class CommonUtil {

    /**
     * 这两个参数防止点击过快
     * {@link CommonUtil#isDoubleClick()}
     * */
    private static final int MIN_CLICK_TIME = 500;
    private static long lastClickTime = 0;

    public static void fillText(Object object, TextView textView){
        if(object != null){
            textView.setText(object+"");
        }
    }

    /**
     * 计算店铺距离
     * @param city 自己所在的位置
     * @param longitude 经度
     * @param latitude 维度
     * */
    public static String setDistance(City city,double longitude, double latitude){
        double lon = longitude;
        double lat = latitude;
        Double a = LonLat.getDistance(lon,lat,city.getLongitude(),city.getLatitude());
        return String.format("%.1f", a);
    }

    /**
     * activity转换辅助工具
     * @param cont 拿到的上下文环境
     * */
    public static Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());

        return null;
    }

    /**
     * dp to px
     * @param dpValue 大小
     * */
    public static int dip2px(float dpValue,Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    /**
     * 缩放图片
     * @param url 要缩放的地址
     * */
    public static String zoomPic(String url){
        return zoomPic(url,Common.SMALLERPICPARAM);
    }
    /**
     * 缩放图片
     * @param url 要缩放的地址
     * @param param 要缩放的后缀
     * */
    public static String zoomPic(String url,String param){
        return url+ param;
    }

    /**
     * 防止快速点击造成的点击重复
     * */
    public static boolean isDoubleClick(){
        long time = System.currentTimeMillis();
        long timeDur = time - lastClickTime;
        if(0 < timeDur && timeDur < MIN_CLICK_TIME){
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     *
     * 针对食靠谱的图片地址url转化
     * "http://obqlfysk2.bkt.clouddn.com/小笨鸡炖蘑菇.jpg",
     * @param url 图片地址
     *
     * */
    public static String assembleRecipe(String url){
        String chinese = url.replace("http://obqlfysk2.bkt.clouddn.com/","")
                .replace(".jpg","").trim();
        String encodeChinese = null;
        try {
            encodeChinese = URLEncoder.encode(chinese,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String fi = "http://obqlfysk2.bkt.clouddn.com/"+encodeChinese+".jpg";
        return fi;
    }

    /**
     * 广告的分页算法
     * */
    public static int randomPage(int page){
        List<List<Integer>> sectionList = new ArrayList<>();        //存放生成的区间
        int weightLength = (page+1)*page/2;         //总权值(权的长度),随机区间
        int seed = new Random().nextInt(weightLength);     //这里生成随机数
        int l = 0 ;     //page


        /**生成区间，并加入sectionList*/
        for(int i = page ; i > 0 ; i -- ){
            List<Integer> tempList = new ArrayList<>();
            if(i == page){          //数据为第一条
                for(int j = 1 ; j <= i ; j ++){
                    //a[j] = j;
                    tempList.add(j);
                }
                sectionList.add(tempList);
            } else {
                List<Integer> lastElement = sectionList.get(sectionList.size()-1);  //获取最后一个list
                int first = lastElement.get(lastElement.size()-1) +1 ;     //获取最后一个list的最后一个元素，+ 1 作为新区间的第一个元素
                int last = first + i - 1 ;              //新区间的最后一个元素
                for(int j = first ; j <= last ; j ++ ){
                    tempList.add(j);
                }
                sectionList.add(tempList);
            }
        }
        /**判断在哪个区间*/
        /*由于是倒序，所以数据存放位置作为page*/
        for(int i = 0 ; i < sectionList.size() ; i ++ ){
            List<Integer> tempList = sectionList.get(i);
            if(tempList.get(0)<= seed && seed<= tempList.get(tempList.size()-1)){
                l = i + 1;
                break;
            }
        }
        /**打印*/
        /*for(int i = 0 ; i < sectionList.size(); i ++){
            List<Integer> tempList = sectionList.get(i);
            String la = "";
            for(int j = 0 ; j < tempList.size() ; j ++){
                la = la+tempList.get(j)+",";
            }
            Log.i("WATER","权>>  "+tempList.size()+"  数据>>  "+la);
        }
        Log.i("WATER","seed>> "+seed);
        Log.i("WATER","l>> "+l);*/

        return l;
    }

    public static void handlerDefaultBanner(BGABanner banner){
        banner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                ((ImageView) view).setImageResource((int) model);
            }
        });
        banner.setData(Arrays.asList(R.drawable.ic_banner_back_1, R.drawable.ic_banner_back_2, R.drawable.ic_banner_back_3), null);

    }
    /**
     * 获取当前时间字符串，用于存储本地文件，以保持名称的唯一性
     */
    public static String getNowTimeString(){
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String nowtime = format.format(date);
        return nowtime;
    }

    /**
     * 手机号码中间四位变*号
     * @param phone
     * @return
     */
    public static String secretPhone(String phone){
        if (phone == null || phone.length()<11){
            return phone;
        }
        return phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
    }
    /**
     * 时间Long转换成文字
     * @param time
     * @return
     */
    public static String timeLongToString(Long time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return format.format(date);
    }
    /**
     * 获取友好时间文字提示文字
     * @param d 时间
     * @return
     */
    public static String getFriendlytime(Date d){
        long delta = (new Date().getTime()-d.getTime())/1000;
        if(delta<=0)return d.toLocaleString();
        if(delta/(60*60*24*365) > 0) return delta/(60*60*24*365) +"年前";
        if(delta/(60*60*24*30) > 0) return delta/(60*60*24*30) +"个月前";
        if(delta/(60*60*24*7) > 0)return delta/(60*60*24*7) +"周前";
        if(delta/(60*60*24) > 0) return delta/(60*60*24) +"天前";
        if(delta/(60*60) > 0)return delta/(60*60) +"小时前";
        if(delta/(60) > 0)return delta/(60) +"分钟前";
        return "刚刚";
    }
    /**
     * 计算两个Long时间相差的秒数
     * @param timePasted
     * @return
     */
    public static int timeSecondsLongToLong(Long timePasted){
        return (int)((new Date().getTime() - timePasted) / 1000);
    }
    /**
     * 根据距离返回百度地图缩放级别
     * @param dis
     * @return
     */
    public static float getMapLevelByDistance(double dis){
        Log.i("test",dis+"");
        float level = (float) ((dis * 1000) / 8);
        if (level > 1000000 && level < 2000000){
            level = 3f;
        }else if (level > 500000 && level < 1000000){
            level = 3f;
        }else if (level > 200000 && level < 500000){
            level = 4f;
        }else if (level > 100000 && level < 200000){
            level = 5f;
        }else if (level > 50000 && level < 100000){
            level = 6f;
        }else if (level > 25000 && level < 50000){
            level = 7f;
        }else if (level > 20000 && level < 25000){
            level = 8f;
        }else if (level > 10000 && level < 20000){
            level = 9f;
        }else if (level > 5000 && level < 10000){
            level = 10f;
        }else if (level > 2000 && level < 5000){
            level = 11f;
        }else if (level > 1000 && level < 2000){
            level = 12f;
        }else if (level > 500 && level < 1000){
            level = 13f;
        }else if (level > 200 && level < 500){
            level = 14f;
        }else if (level > 100 && level < 200){
            level = 15f;
        }else if (level > 50 && level < 100){
            level = 16f;
        }else if (level > 20 && level < 50){
            level = 17f;
        }else if (level > 10 && level < 20){
            level = 18f;
        }
        Log.i("level",level+"");
        return level;
    }
    /**
     * make true current connect service is wifi
     * @param mContext
     * @return
     */
    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 更新APP逻辑
     * @return
     */
    public static void updateVersion(final Context context){
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String nowtime = format.format(date);
        final String appName = Common.IMAGE_FILE_NAME + nowtime + ".apk";////////////////////////////
        new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(Common.downloadAppUrl);///////////
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    int length = (int) entity.getContentLength();   //获取文件大小
                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;

                    if (is != null) {
                        File file = new File(
                                Environment.getExternalStorageDirectory(),appName);
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[1024];   //这个是缓冲区，即一次读取10个比特，我弄的小了点，因为在本地，所以数值太大一 下就下载完了，看不出progressbar的效果。
                        int ch = -1;
//                        int process = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
//                            process += ch;
//                            pBar.setProgress(process);       //这里就是关键的实时更新进度了！
//                            float all = (float)length/(float)1024/(float)1024;
//                            float percent = (float)process/(float)1024/(float)1024;
//                            pBar.setProgressNumberFormat(String.format("%.1fM/%.1fM", percent, all));
                        }

                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(new File(Environment
                                    .getExternalStorageDirectory(), appName)),
                            "application/vnd.android.package-archive");
                    Log.i("updateapp",Environment.getExternalStorageState());
                    context.startActivity(intent);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }
    /**
     * 计算两个Long时间相差的秒数
     * @return
     */
    public static JSONObject getParamsFromUrl(String url){
        String urlArr[] = url.split("\\?");
        String params = urlArr[1];
        String paramsArr[] = params.split("&");
        JSONObject json = new JSONObject();
        for(int i = 0 ; i <paramsArr.length ; i++){
            String p[] = paramsArr[i].split("=");
            String keyName = p[0];
            String keyValue = p[1];
            try {
                json.accumulate(keyName, keyValue);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return json;
    }
    /*
    存储用户缓存信息
     */
    public static void setCacheDatas(Context context,String key,String value){
        SharedPreferences preferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    /*
存储用户缓存信息
*/
    public static void setCacheDatas(Context context,String key,Long value){
        SharedPreferences preferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }
    /*
 获取用户缓存信息
  */
    public static String getCacheDatas(Context context,String key,String sp_name){
        SharedPreferences preferences = context.getSharedPreferences(sp_name, Context.MODE_PRIVATE);
        return preferences.getString(key, null);
    }
    /*
获取用户缓存信息
*/
    public static Long getCacheDatasLong(Context context,String key,String sp_name){
        SharedPreferences preferences = context.getSharedPreferences(sp_name, Context.MODE_PRIVATE);
        return preferences.getLong(key, 0L);
    }
}
