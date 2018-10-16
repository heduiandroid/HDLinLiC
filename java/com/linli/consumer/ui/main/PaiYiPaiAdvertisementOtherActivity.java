package com.linli.consumer.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.MallShopInfo;
import com.linli.consumer.bean.StoreInfo;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class PaiYiPaiAdvertisementOtherActivity extends Activity implements View.OnClickListener {
    private VideoView vv_advert;
    private TextView tvcache;
    private String remoteUrl;
    private String localUrl;
    private ProgressBar pb_waiting;
    private ImageView iv_replay;
    private static final int READY_BUFF = 2000 * 1024;
    private static final int CACHE_BUFF = 500 * 1024;
    private boolean isready = false;
    private boolean iserror = false;
    private int errorCnt = 0;
    private int curPosition = 0;
    private long mediaLength = 0;
    private long readSize = 0;

    private Button btn_toshopindex;

    private Long id;//广告的ID
    private long merchantId;//代理商ID
    private String named;//广告名称
    private String intro;//z广告详细介绍
    private String createTime;
    private Integer typed;//广告的类型
    private String path;//广告链接
    private final static int VIDEO_STATE_UPDATE = 0;
    private final static int CACHE_VIDEO_READY = 1;
    private final static int CACHE_VIDEO_UPDATE = 2;
    private final static int CACHE_VIDEO_END = 3;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case VIDEO_STATE_UPDATE:
                    double cachepercent = readSize * 100.00 / mediaLength * 1.0;
                    String s = String.format("已加载: (%.2f%%)", cachepercent);

                    if (vv_advert.isPlaying()) {
                        curPosition = vv_advert.getCurrentPosition();
                        int duration = vv_advert.getDuration();
                        duration = duration == 0 ? 1 : duration;

                        double playpercent = curPosition * 100.00 / duration * 1.0;

                        int i = curPosition / 1000;
                        int hour = i / (60 * 60);
                        int minute = i / 60 % 60;
                        int second = i % 60;

                        s = String.format(" 播放: %02d:%02d:%02d", hour,
                                minute, second);
                    }

                    tvcache.setText(s);

                    mHandler.sendEmptyMessageDelayed(VIDEO_STATE_UPDATE, 1000);
                    break;

                case CACHE_VIDEO_READY:
                    isready = true;
                    vv_advert.setVideoPath(localUrl);
                    vv_advert.start();
                    break;

                case CACHE_VIDEO_UPDATE:
                    if (iserror) {
                        vv_advert.setVideoPath(localUrl);
                        vv_advert.start();
                        iserror = false;
                    }
                    break;

                case CACHE_VIDEO_END:
                    if (iserror) {
                        vv_advert.setVideoPath(localUrl);
                        vv_advert.start();
                        iserror = false;
                    }
                    break;
            }

            super.handleMessage(msg);
        }
    };
    private int category;
    private String shopName;//商家名称

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_yi_pai_advertisement_other);
        init();
        Intent intent = getIntent();
        id = intent.getLongExtra("id",0);
        merchantId = intent.getLongExtra("merchantId",0);
        named = intent.getStringExtra("named");
        intro = intent.getStringExtra("intro");
        createTime = intent.getStringExtra("createTime");
        typed = intent.getIntExtra("typed", 0);
        path = intent.getStringExtra("path");
//        if (merchantId == 0 || id == 0){
//            Toast.makeText(PaiYiPaiAdvertisementOtherActivity.this, "还没有商家广告哦~", Toast.LENGTH_SHORT).show();
//            finish();
//        }else {
        request_shopcate();
            show_advertasiment();
            playvideo();
//        }
    }
    /*
    请求该shop的类型
     */
    private void request_shopcate() {
        IntrestBuyNet.findShopByshopId(merchantId, new HandleSuccess<MallShopInfo>() {
            @Override
            public void success(MallShopInfo s) {
                if (s.getStatus() == 1){
                    shopName = s.getData().getName();
                    category = s.getData().getCategoryType();//1商城 2餐饮 3服务
                }else if (s.getStatus() == 2){//不是商城商家 就去查订餐商家
                    request_foodshopcate();
                }
            }
        });
    }
    private void request_foodshopcate() {
        IntrestBuyNet.findFoodShopByshopId(merchantId, new HandleSuccess<StoreInfo>() {
            @Override
            public void success(StoreInfo s) {
                if (s.getStatus() == 1){
                    btn_toshopindex.setText("开始点餐");
                    shopName = s.getData().getName();
                    category = s.getData().getCategoryType();
                }
            }
        });
    }
    private void init() {
        findViewById(R.id.ll_advert).setOnClickListener(this);
        pb_waiting = (ProgressBar) findViewById(R.id.pb_waiting);
        findViewById(R.id.iv_close).setOnClickListener(this);
        btn_toshopindex = (Button) findViewById(R.id.btn_toshopindex);
        vv_advert = (VideoView) findViewById(R.id.vv_advert);
        tvcache = (TextView) findViewById(R.id.tvcache);
        iv_replay = (ImageView) findViewById(R.id.iv_replay);
        iv_replay.setOnClickListener(this);
        btn_toshopindex.setOnClickListener(this);
    }
    private void show_advertasiment() {
//        remoteUrl = "http://7xsyky.com1.z0.glb.clouddn.com/VID_20160712_111606.3gp";//测试用视频
        remoteUrl = path;
        localUrl = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/VideoCache/" + System.currentTimeMillis() + ".mp4";
//        Uri uri = Uri.parse("http://7xsyky.com1.z0.glb.clouddn.com/VID_20160712_111606.3gp");
        vv_advert.setMediaController(new MediaController(this));
//        vv_advert.setVideoURI(uri);
//        vv_advert.start();
        vv_advert.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                pb_waiting.setVisibility(View.GONE);
                mp.start();
            }
        });
        vv_advert.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                curPosition = 0;
                vv_advert.pause();
                iv_replay.setVisibility(View.VISIBLE);
                tvcache.setVisibility(View.GONE);
            }
        });
        vv_advert.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                iserror = true;
                errorCnt++;
                vv_advert.pause();
                pb_waiting.setVisibility(View.VISIBLE);
                return true;
            }
        });
    }
    private void playvideo(){
        if (!URLUtil.isNetworkUrl(this.remoteUrl)){
            vv_advert.setVideoPath(this.remoteUrl);
            vv_advert.start();
            return;
        }
        pb_waiting.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileOutputStream out = null;
                InputStream is = null;
                try {
                    URL url = new URL(remoteUrl);
                    HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                    if (localUrl == null){
                        localUrl = Environment.getExternalStorageDirectory()
                                .getAbsolutePath()
                                +"/VideoCache/"
                                + System.currentTimeMillis() + ".mp4";
                    }
                    File cacheFile = new File(localUrl);
                    if (!cacheFile.exists()){
                        cacheFile.getParentFile().mkdirs();
                        cacheFile.createNewFile();
                    }
                    readSize = cacheFile.length();
                    out = new FileOutputStream(cacheFile,true);
                    httpConnection.setRequestProperty("User-Agent", "NetFox");
                    httpConnection.setRequestProperty("RANGE", "bytes="
                            + readSize + "-");
                    is = httpConnection.getInputStream();
                    mediaLength = httpConnection.getContentLength();
                    if (mediaLength == -1){
                        return;
                    }
                    mediaLength += readSize;
                    byte buf[] = new byte[4 * 1024];
                    int size = 0;
                    long lastReadSize = 0;
                    mHandler.sendEmptyMessage(VIDEO_STATE_UPDATE);
                    while ((size = is.read(buf))!=-1){
                        try {
                            out.write(buf,0,size);
                            readSize += size;
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        if (!isready){
                            if ((readSize - lastReadSize) > READY_BUFF){
                                lastReadSize = readSize;
                                mHandler.sendEmptyMessage(CACHE_VIDEO_READY);
                            }
                        }else {
                            if ((readSize - lastReadSize) > CACHE_BUFF * (errorCnt + 1)){
                                lastReadSize = readSize;
                                mHandler.sendEmptyMessage(CACHE_VIDEO_UPDATE);
                            }
                        }
                    }
                    mHandler.sendEmptyMessage(CACHE_VIDEO_END);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (out != null){
                        try {
                            out.close();
                        }catch (IOException e){
                            //
                        }
                    }
                    if (is != null){
                        try {
                            is.close();
                        }catch (IOException e){
                            //
                        }
                    }
                }
            }
        }).start();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                finish();
                break;
            case R.id.ll_advert:
            case R.id.btn_toshopindex:
                switch (category){
                    case 1:
                        UIHelper.togoShopDetailActivity(this,merchantId,shopName,SHOP_MAIN);
                        break;
                    case 2:
                        UIHelper.togoShopDetailActivity(this,merchantId,shopName,FOOD_MAIN);
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
                break;
            case R.id.iv_replay:
                iv_replay.setVisibility(View.GONE);
                tvcache.setVisibility(View.VISIBLE);
                showLocalVideo();
                break;
            default:
                break;
        }
    }

    private void showLocalVideo() {
        MediaController controller = new MediaController(this);
        vv_advert.setMediaController(controller);
        vv_advert.setVideoPath(localUrl);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
}
