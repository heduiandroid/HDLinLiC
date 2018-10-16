package com.linli.consumer.common;

import android.os.Environment;

import io.rong.imlib.model.UserInfo;

/**
 * 公共常量类
 * Created by Administrator on 2016/3/4.
 */
public class Common {

//    public static String myUrl = "http://www.daojihuayun.com/api/";//正式域名服务器地址
    public static String myUrl = "http://123.56.76.187:8766/api/";//线上测试服务器地址
//    public static String myUrl = "http://192.168.1.122:8766/api/";//本地服务器地址


    public static String versionCode = "Bate u_17.1.3.1.0.0";//当前APP版本号 用于与服务端版本号对比->升级 //若更改此项最好同时在gradle文件中更改versionName
    /*
      关于微信 APP_ID 为你的应用从官方网站申请到的合法appId
       */
    public static final String APP_ID = "wx52e75cf83d379a79";
    public static String mIndexH5 = "file:///android_asset/demo.html";//我们需要加载的H5首页地址
    public static String specilAdverUrl = "http://www.zhidaizhijia.com/reg?invite_code=X6GCDR";//特殊赞助广告网站地址
    public static String ADVER_PIC_RIL = "http://obqlfysk2.bkt.clouddn.com/a_special_adver_pic_1.jpg";//特殊赞助广告图片地址
    public static String mUrl = "http://118.190.86.7:8080/mall/api";//正式服务器地址old
    public static String uploadVoiceUrl = "http://118.192.22.177:8085/UploadServlet/UploadServlet?";//*似乎是已经没用的URL*
    public static String downloadVoiceUrl = "http://pd6mcjemz.bkt.clouddn.com/";//七牛服务器保存数据地址//*只是用于录完自己听一下 拼了一下链接，其他没有用到这个*
    public static String downloadAppUrl = "http://downloadserver.sht18.com/shtc.apk";//正式服务器下载链接（直接下载）
    public static String downloadAppUrlWeb = "http://a.app.qq.com/o/simple.jsp?pkgname=com.hd.hdappyzx";//正式服务器下载链接(网页导航)
    public static String downloadSongXiaobaoUrl = "http://downloadpkg.apicloud.com/app/download?path=http://7yusoi.com1.z0.glb.clouddn.com/4a2d209370ed32faa938d3975948e62f_d";//下载送小宝的链接

    public static final String WSMALLERPICPARAM400 = "?imageView2/2/w/410";//宽度固定为410px，高度等比缩小
    public static final String SMALLERPICPARAM = "?imageView2/2/h/200";//高度固定为200px，宽度等比缩小  //TODO
    public static final String WSMALLERPICPARAM = "?imageView2/2/w/200";//宽度固定为200px，高度等比缩小
    public static final String MORESMALLERPICPARAM = "?imageView2/2/h/100";//高度固定为100px，宽度等比缩小
    public static UserInfo userInfo = null;
    public static final String IMAGE_FILE_NAME="zl_";
    public static final int CODE_GALLERY_REQUEST=0xa0;
    public static final int CODE_CAMERA_REQUEST=0xa1;
    public static final int CODE_RESULT_REQUEST=0xa2;

    public static String getmVersionCode(){//获取本地版本号
        return versionCode;
    }

    /**
     * 获取存放语音的路径
     * @return
     */
    public static String getWavFilePath(String fileName){
        String mAudioWavPath = fileName+".wav";
        if(isSdcardExit()){
            String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            mAudioWavPath = fileBasePath+"/"+fileName+".wav";
        }
        return mAudioWavPath;
    }
    /**
     * 判断是否有外部存储设备sdcard
     */
    public static boolean isSdcardExit(){
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }
}
