package com.linli.consumer.ui.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.FastTextAdapter;
import com.linli.consumer.adapter.RequermentsAdapter;
import com.linli.consumer.adapter.UserNeedAndFeedbackAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.AddVoice;
import com.linli.consumer.bean.FeedBacks;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.VoiceInfo;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.User;
import com.linli.consumer.domain.UserNeedAndFeedback;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.utils.JsonParser;
import com.linli.consumer.utils.OnStateListener;
import com.linli.consumer.utils.RecordManger;
import com.linli.consumer.utils.TalkNetManager;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class V2VoicePhotoBuyActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private String[] keywords;
    private LinearLayout ll_main_requerments,ll_want_text_requerment,ll_want_other_requerment;
    private ListView lv_fast_text;
    private ListView mlv_requerments;
    private ImageView iv_show_edittext,iv_show_menu;
    private LinearLayout ll_press_record;
    private TextView tv_recording_text;
    private LinearLayout ll_take_picture,ll_show_fasttext,ll_scanning;
    private LinearLayout ll_fast_text;
    private LinearLayout ll_add_fasttext;
    private EditText et_text;
    private boolean isShowingEt = false;//是否正在显示文字需求输入框 默认没有显示
    private boolean isShowingMenu = false;//是否正在显示其他需求的菜单 默认没有显示
    private SharedPreferences preferences;//
    private ArrayList<UserNeedAndFeedback> mymessage = new ArrayList<UserNeedAndFeedback>();
    private RequermentsAdapter adapter;
    private ArrayList<String> strings = new ArrayList<String>();
    private FastTextAdapter stringAdapter;
    private LinearLayout ll_nodata;
    private TextView tv_nofasttext;
    private String imageName;
    private AppContext appContext;
    private User user;
    private City city;
    /** 控制录音和上传 */
    private TalkNetManager talk = null;
    private int countTime = 30;
    /** 显示录音振幅 */
    private ImageView progress;
    private RelativeLayout rl_recording;
    private TextView tv_alert,dialog_title,text_msg;
    /** 显示录音振幅的图片缓存 */
    private Drawable[] progressImg = new Drawable[7];
    private boolean isRecording = true;//是否正在录音
    private boolean uploadAble = false;//是否可上传状态
    /** 显示录音车状态或报错图标 */
    private ImageView mic_icon;
    private String name = null;//语音或者图片路径
    private int type;//0语音  1图片 2文字
    private Handler handler = new Handler();
    private Runnable run = new Runnable() {
        @Override
        public void run() {
            countTime--;
            if (countTime < 0){
                handler.removeCallbacks(run);
                return;
            }
            handler.postDelayed(run,1000);
        }
    };
    public static boolean needToRefeshData = false;//去照相发图像需求 回来是否要刷新需求信息数据 默认false
    private int numPerPage = 0;//每页的数量 默认一个也没有
    private boolean isCanceled = false;//是否主动取消语音录制 默认没有主动取消
    private boolean isTooShortTime = false;//是否时间太短  默认够时长
    private String mytoken = null;
    //有动画效果
    private RecognizerDialog iatDialog;
    private String result = null;//语音转文字结果
    private String audioName;//存入本地的语音名称

    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                Toast.makeText(V2VoicePhotoBuyActivity.this, "初始化失败，错误码：" + code, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_v2_voice_photo_buy;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        city = appContext.getCity();
//        progressImg[0] = this.getResources().getDrawable(R.mipmap.mic_1);//初始化振幅图片
//        progressImg[1] = this.getResources().getDrawable(R.mipmap.mic_2);//初始化振幅图片
//        progressImg[2] = this.getResources().getDrawable(R.mipmap.mic_3);//初始化振幅图片
//        progressImg[3] = this.getResources().getDrawable(R.mipmap.mic_4);//初始化振幅图片
//        progressImg[4] = this.getResources().getDrawable(R.mipmap.mic_5);//初始化振幅图片
//        progressImg[5] = this.getResources().getDrawable(R.mipmap.mic_6);//初始化振幅图片
//        progressImg[6] = this.getResources().getDrawable(R.mipmap.mic_7);//初始化振幅图片
        progress = findView(R.id.sound_progress);//振幅进度条
        rl_recording = findView(R.id.rl_recording);
        tv_alert =  findView(R.id.tv_alert);
        dialog_title =  findView(R.id.title);
        text_msg = findView(R.id.msg);
        mic_icon = (ImageView) findViewById(R.id.mic);
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("发送需求");
        ll_main_requerments = findView(R.id.ll_main_requerments);
        ll_want_text_requerment = findView(R.id.ll_want_text_requerment);
        ll_want_other_requerment = findView(R.id.ll_want_other_requerment);
        mlv_requerments = findView(R.id.mlv_requerments);
        mlv_requerments.setOnItemClickListener(this);
        findViewClick(R.id.tv_sendtext);
        iv_show_edittext = findViewClick(R.id.iv_show_edittext);
        iv_show_menu = findViewClick(R.id.iv_show_menu);
        ll_press_record = findViewClick(R.id.ll_press_record);
//        ll_press_record.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                float downy = 0;//当前按钮按下坐标，控制上划取消录音的上传
//                    if (isShowingMenu) {
//                        isShowingMenu = false;
//                        setDefaultViews();
//                    }
//                    if (event.getAction() == MotionEvent.ACTION_DOWN) {//按下时
//                        tv_recording_text.setText("松开发送");
//                        tv_recording_text.setTextColor(getResources().getColor(R.color.white));
//                        ll_press_record.setBackgroundResource(R.drawable.btn_tuoyuan_bgorange);
//                        mytoken = null;
//                        downy = event.getY();
//                        new CountDownTimer(1 * 1000, 1000) {//
//                            @Override
//                            public void onTick(long millisUntilFinished) {
//                            }
//
//                            @Override
//                            public void onFinish() {
//                                if (isRecording) {
//                                    needToRefeshData = true;//成功开始录音了，之后必须刷新数据的标记
//                                    playSound();
//                                    uploadAble = true;
//                                    request_upload_token();
//
//                                    talk = new TalkNetManager();//初始化一个网络对话管理类
//                                    talk.setUploadFileServerUrl(Common.uploadVoiceUrl);//设置文件上传地址
//                                    talk.setDownloadFileServerUrl(Common.downloadVoiceUrl);//设置文件下载地址
//                                    talk.getRecordManger().setSoundAmplitudeListen(onSoundAmplitudeListen);//设置振幅监听器
//                                    talk.setDownloadFileFileStateListener(onDownloadFileFileStateListener);//设置下载播放状态监听
//                                    talk.setUploadFileStateListener(onUploadFileStateListener);//设置文件上传状态监听器
//                                    talk.startRecord();//开始录音
//                                    rl_recording.setVisibility(View.VISIBLE);
//                                    tv_alert.setText(R.string.say_sth);
//                                    tv_alert.setTextColor(getResources().getColor(R.color.gray));
//                                    dialog_title.setText("正在录音");
//                                    //播放动画
//                                    handler.post(run);
//                                } else {
//                                    isRecording = true;
//                                }
//                            }
//                        }.start();
//
//                    }
//
//                if (event.getAction() == MotionEvent.ACTION_UP) {//松开时
//                    if (countTime > 26){
//                        isTooShortTime = true;
//                        uploadAble = false;
//                    }
//                        tv_recording_text.setText("点击 说话");
//                        tv_recording_text.setTextColor(getResources().getColor(R.color.gray));
//                        ll_press_record.setBackgroundResource(R.drawable.btn_tuoyuan_frameorange);
//                        if (uploadAble) {
//                            uploadAble = false;
//                            countTime = 30;
//                            rl_recording.setVisibility(View.GONE);
//                            dialog_title.setText("声音处理中");
//                            if (numPerPage > 2) {
//                                Toast.makeText(V2VoicePhotoBuyActivity.this,"休息，休息一会儿~",Toast.LENGTH_SHORT).show();//休息一会
//                            }else {
////                                if (countTime > 26){
////                                    talk = null;
////                                    Toast.makeText(V2VoicePhotoBuyActivity.this,"录音时间太短",Toast.LENGTH_SHORT).show();//休息一会
////                                }else {
//                                try {
//                                    talk.stopRecordAndUpload(V2VoicePhotoBuyActivity.this);
//                                }catch (NullPointerException e){
//                                    e.printStackTrace();
//                                }
////                                }
//                            }
//                            stopTime();
//                        } else {
//                            if (talk != null) {
//                                isRecording = true;
//
//                                talk.stopRecord();
//                                stopTime();
//                                if (!isCanceled){
//                                    if (isTooShortTime){
//                                        Toast.makeText(V2VoicePhotoBuyActivity.this, "录音时间太短", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            } else {
//                                Toast.makeText(V2VoicePhotoBuyActivity.this, "录音时间太短", Toast.LENGTH_SHORT).show();
//                                isRecording = false;
//                            }
//                            countTime = 30;
//                            rl_recording.setVisibility(View.GONE);
//                            isTooShortTime = false;
//                            isCanceled = false;
//                        }
//                    }
//                    if (event.getAction() == MotionEvent.ACTION_CANCEL) {
//                        talk.stopRecord();
//                        countTime = 30;
//                        stopTime();
//                    }
//                    if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                        if (uploadAble) {
//                            if (downy - event.getY() > 50) {
//                                isCanceled = true;
//                                Toast.makeText(V2VoicePhotoBuyActivity.this,"松开取消发送",Toast.LENGTH_LONG).show();
//                                uploadAble = false;
//                            }
//                        }
//                    }
//                return true;
//            }
//        });
        tv_recording_text = findView(R.id.tv_recording_text);
        ll_take_picture = findViewClick(R.id.ll_take_picture);
        ll_show_fasttext = findViewClick(R.id.ll_show_fasttext);
        ll_scanning = findViewClick(R.id.ll_scanning);
        ll_fast_text = findView(R.id.ll_fast_text);

        et_text = findView(R.id.et_text);
        lv_fast_text = findView(R.id.lv_fast_text);
        lv_fast_text.setOnItemClickListener(this);
        ll_nodata = findView(R.id.ll_nodata);
        tv_nofasttext = findView(R.id.tv_nofasttext);
        ll_add_fasttext = findViewClick(R.id.ll_add_fasttext);
        stringAdapter = new FastTextAdapter(this,strings);
        lv_fast_text.setAdapter(stringAdapter);
        adapter = new RequermentsAdapter(mymessage,this);
        mlv_requerments.setAdapter(adapter);
        setDefaultViews();
    }

    private void setDefaultViews() {
        ll_fast_text.setVisibility(View.GONE);
        ll_want_other_requerment.setVisibility(View.GONE);
        if (isShowingEt){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et_text.getWindowToken(),0);
        }
    }

    @Override
    protected void initData() {
        //先加载本地fasttext，用于发送快捷短语直接发送文字需求的列表
        get_preference_local();
        if (getIntent().getExtras() != null){
            Intent intent = getIntent();
            name = intent.getStringExtra("voicename");
            type = 0;
            tryToAnalysisVoiceJson(intent.getStringExtra("jsonvoice"));
            request_uploadvoice_to_myserver();
        }else {
            dismiss();
            mlv_requerments.setVisibility(View.GONE);
            ll_nodata.setVisibility(View.VISIBLE);
        }

    }
    private void request_uploadvoice_to_myserver() {
        IntrestBuyNet.addUserVoice(type, user.getId(), name, new HandleSuccess<AddVoice>() {
            @Override
            public void success(AddVoice s) {
                if (s.getStatus() == 1){
                    numPerPage++;
                    request_recently_messages();
                    request_submitsever_topushdata(s.getData().getId());
                    Toast.makeText(V2VoicePhotoBuyActivity.this,"发送成功，请等待反馈信息",Toast.LENGTH_SHORT).show();
                }else {
                    dismiss();
                    Toast.makeText(V2VoicePhotoBuyActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void request_submitsever_topushdata(long id) {
        if (type!=1){
            IntrestBuyNet.userJpush(10, 1, 3d, id, user.getId(), city.getLongitude(), city.getLatitude(),0,keywords[0],keywords[1],keywords[2],
                    new HandleSuccess<Generic>() {
                @Override
                public void success(Generic s) {
                    if (s.getStatus() == 1){
                        Log.i("test","已有商家自动回推");
                    }else if (s.getStatus() == 7){
                        Log.i("test","当前位置暂无可反馈商家");
                    }else{
                        dismiss();
                        Toast.makeText(V2VoicePhotoBuyActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            IntrestBuyNet.jpush(10, 1, 3d, id, user.getId(), city.getLongitude(), city.getLatitude(),0,new HandleSuccess<Generic>() {
                @Override
                public void success(Generic s) {
                    if (s.getStatus() == 1){
                        Log.i("test","需求推送给商家成功，等待回推");
                    }else if (s.getStatus() == 7){
                        Log.i("test","当前位置暂无可反馈商家");
                    }else{
                        dismiss();
                        Toast.makeText(V2VoicePhotoBuyActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
    private void get_preference_local() {
        preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
        String oldtext = preferences.getString("fasttext","");
        if (!oldtext.equals("")){
            Log.i("test",oldtext);
            lv_fast_text.setVisibility(View.VISIBLE);
            tv_nofasttext.setVisibility(View.GONE);
            subStringInflateList(oldtext);
        }else {
            lv_fast_text.setVisibility(View.GONE);
            tv_nofasttext.setVisibility(View.VISIBLE);
        }
    }
    private void subStringInflateList(String ot){
        String str[] = ot.split("&&");
        strings.clear();
        for (int i = str.length-1;i>=0;i--){
            strings.add(str[i]);
            stringAdapter.notifyDataSetChanged();//刷新adapter
        }
    }
    private void request_recently_messages() {
        //成功之后
        IntrestBuyNet.PVMessageData(numPerPage,1,user.getId(),new HandleSuccess<FeedBacks>() {
            @Override
            public void success(FeedBacks s) {
                if (s.getStatus() == 1) {
                    mymessage.clear();//先清空，再重新加载
                    if (s.getData() != null) {
                        for (int i = s.getData().size()-1; i >=0; i--) {
                            UserNeedAndFeedback needAndFeedback = new UserNeedAndFeedback();
                            needAndFeedback.setId(s.getData().get(i).getHdPubVoice().getId());
                            needAndFeedback.setUserHead(user.getHeadPath());
                            needAndFeedback.setDataPath(s.getData().get(i).getHdPubVoice().getPath());
                            needAndFeedback.setType(s.getData().get(i).getHdPubVoice().getType());
                            needAndFeedback.setmText(s.getData().get(i).getHdPubVoice().getName());
                            needAndFeedback.setTime("");
                            mymessage.add(needAndFeedback);
                        }
                    }else {
                        ll_nodata.setVisibility(View.VISIBLE);
                        mlv_requerments.setVisibility(View.GONE);
                    }
                    if (mymessage.size() > 0){//刷新适配器
                        ll_nodata.setVisibility(View.GONE);
                        mlv_requerments.setVisibility(View.VISIBLE);
                        adapter.notifyDataSetChanged();
//                        mlv_requerments.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                mlv_requerments.requestFocusFromTouch();
//                                mlv_requerments.setSelection(adapter.getCount()-1);
//                            }
//                        },500);
//                        mlv_requerments.requestFocusFromTouch();
                        mlv_requerments.setSelection(adapter.getCount()-1);
                        isShowingMenu = false;
                        setDefaultViews();
                        requestVoiceTime();
                        Log.i("test","刷新数据完毕");
                    }else {
                        ll_nodata.setVisibility(View.VISIBLE);
                        mlv_requerments.setVisibility(View.GONE);
                    }
                } else {
                    ll_nodata.setVisibility(View.VISIBLE);
                    mlv_requerments.setVisibility(View.GONE);
                }
                dismiss();
            }
        });
    }

    private void requestVoiceTime() {
        for (int i = 0;i<mymessage.size();i++){
            if (mymessage.get(i).getType() == 0){
                final int finalI = i;
                IntrestBuyNet.getVoiceDuration(mymessage.get(i).getmText() + "?avinfo", new HandleSuccess<VoiceInfo>() {
                    @Override
                    public void success(VoiceInfo s) {
                        double size = 0d;
                        if (s.getFormat().getDuration() != null){
                            size = Double.parseDouble(s.getFormat().getDuration());
                        }
                        String  timelong = (int) Math.ceil(size) + "''";//转换为秒 单位为''
                        mymessage.get(finalI).setTime(timelong);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }

    @Override
    protected void onResume() {
        Log.i("test","onResume excute");
        if (needToRefeshData){
            Log.i("test","开始刷新数据");
            needToRefeshData = false;
            numPerPage++;
            request_recently_messages();
        }
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        if (user.getId() == null){
            Toast.makeText(this,"网络繁忙，请稍后再试",Toast.LENGTH_SHORT).show();
            finish();
        }
        super.onResume();
    }
    @Override
    protected void onStop() {
        if (UserNeedAndFeedbackAdapter.mp != null && UserNeedAndFeedbackAdapter.mp.isPlaying()){
            RequermentsAdapter.resetDataView();
        }
        super.onStop();
    }
    @Override
    public void onHDClick(View v) {
        if (UserNeedAndFeedbackAdapter.mp != null && UserNeedAndFeedbackAdapter.mp.isPlaying()){
            RequermentsAdapter.resetDataView();
        }
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.iv_show_edittext:
                if (isShowingEt){
                    isShowingEt = false;
                    ll_want_text_requerment.setVisibility(View.GONE);
                }else {
                    isShowingEt = true;
                    ll_want_text_requerment.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_show_menu:
                if (isShowingMenu){
                    isShowingMenu = false;
                    setDefaultViews();
                }else {
                    isShowingMenu = true;
                    ll_want_other_requerment.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_take_picture://照相
                if (numPerPage > 2){
                    Toast.makeText(V2VoicePhotoBuyActivity.this,"休息，休息一会儿~",Toast.LENGTH_SHORT).show();//休息一会
                }else {
                    isShowingMenu = false;
                    setDefaultViews();
                    takeANewPicture();
                }
                break;
            case R.id.ll_show_fasttext:
                setDefaultViews();
                ll_fast_text.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_scanning:
                isShowingMenu = false;
                setDefaultViews();
                startActivity(new Intent(V2VoicePhotoBuyActivity.this,ScanningBuyActivity.class));
                break;
            case R.id.ll_add_fasttext:
                startActivityForResult(new Intent(V2VoicePhotoBuyActivity.this,AddFastTextActivity.class),427);
                break;
            case R.id.tv_sendtext:
                if (TextUtils.isEmpty(et_text.getText())){
                    Toast.makeText(V2VoicePhotoBuyActivity.this,"请输入您的文字需求",Toast.LENGTH_SHORT).show();
                }else if (numPerPage > 2){
                    Toast.makeText(V2VoicePhotoBuyActivity.this,"休息，休息一会儿~",Toast.LENGTH_SHORT).show();//休息一会
                }else {
                    keywords = new String[3];
                    String mtext = et_text.getText().toString();
                    keywords[0] = mtext;
                    name = mtext;
                    type = 2;
                    Toast.makeText(V2VoicePhotoBuyActivity.this,"正在发送需求",Toast.LENGTH_SHORT).show();
                    et_text.setText("");
                    request_uploadvoice_to_myserver();
                }
                break;
            case R.id.ll_press_record:
                if (numPerPage > 2) {
                    Toast.makeText(V2VoicePhotoBuyActivity.this,"休息，休息一会儿~",Toast.LENGTH_SHORT).show();//休息一会
                }else {
                    type = 0;
                    result = null;//重置文字翻译结果为空
                    request_upload_token();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        isShowingMenu = false;
        setDefaultViews();
        return super.onTouchEvent(event);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()){
            case R.id.mlv_requerments:
                if (isShowingMenu){
                    isShowingMenu = false;
                }
                setDefaultViews();
                break;
            case R.id.lv_fast_text:
                et_text.setText(strings.get(position));
                isShowingMenu = false;
                setDefaultViews();
                isShowingEt = true;
                ll_want_text_requerment.setVisibility(View.VISIBLE);
                break;
        }
    }
    private void request_upload_token() {
        IntrestBuyNet.getToken(null, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic generic) {
                if (generic.getStatus() == 1){
                    mytoken = generic.getData().toString();
                    startRecordByXunFei();
                    countTime = 30;
                    handler.post(run);
                }else {
                    Toast.makeText(V2VoicePhotoBuyActivity.this,generic.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startRecordByXunFei() {
        tv_recording_text.setText("聆听中...");
        tv_recording_text.setTextColor(getResources().getColor(R.color.white));
        ll_press_record.setBackgroundResource(R.drawable.btn_tuoyuan_bgorange);
        //先给存入本地的文件取名字 拼全连接并存入本地 待取用
        audioName = Common.IMAGE_FILE_NAME+ CommonUtil.getNowTimeString();
        // ②初始化有交互动画的语音识别器
        iatDialog = new RecognizerDialog(V2VoicePhotoBuyActivity.this, mInitListener);
        resetDialogParam(audioName,"10000");//本地录音文件名，后端点静音时间
        //③设置监听，实现听写结果的回调
        iatDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean isLast) {
                System.out.println("-----------------   onResult   -----------------");
                if (!isLast){
                    recordViewsDefalt();
                    String json = recognizerResult.getResultString();
                    result = JsonParser.parseIatResult(json);//语音转成的文字 一定会有用途////////////////////////////////////////////////
                    Log.i("test",json);
                    tryToAnalysisVoiceJson(json);//解析讯飞给的语音翻译json 据为己用
                    if (countTime <= 27){
                        if (result.length() > 1){
                            Toast.makeText(V2VoicePhotoBuyActivity.this,"正在发送需求",Toast.LENGTH_SHORT).show();
                            //调用推送文字信息接口
                            uploadUtilToQiNiu();//*********语音上传至七牛********
                        }else {
                            Toast.makeText(V2VoicePhotoBuyActivity.this,"请说的更详细一些",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(V2VoicePhotoBuyActivity.this,"录音时间太短",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(SpeechError speechError) {
                //自动生成的方法存根
                Log.i("test","onError执行"+speechError.getErrorCode());
                if(speechError.getErrorCode() == 20006){
                    Toast.makeText(V2VoicePhotoBuyActivity.this,"打开录音失败，请检查权限",Toast.LENGTH_LONG).show();
                }else if (speechError.getErrorCode() == 10118){
                    Toast.makeText(V2VoicePhotoBuyActivity.this,"您好像没有说话哦",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(V2VoicePhotoBuyActivity.this,"请说的更详细一些",Toast.LENGTH_LONG).show();
                }
                speechError.getPlainDescription(true);
                //改按钮文字
                recordViewsDefalt();
            }
        });
        iatDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                recordViewsDefalt();
            }
        });
        //开始听写，需将sdk中的assets文件下的文件夹拷入项目的assets文件夹下（没有的话自己新建）
        iatDialog.show();
    }
    /*
    解析语音转文字后给的json串
     */
    private void tryToAnalysisVoiceJson(String json) {
        try {
            JSONObject mTrans = new JSONObject(json);
            JSONArray ms = mTrans.getJSONArray("ws");
            keywords = new String[3];
            int k = 0;
            for (int i = ms.length() - 1;i >= 0;i--){
                JSONObject item = ms.getJSONObject(i).getJSONArray("cw").getJSONObject(0);
                Log.i("test",item.getString("w"));
                if (k < 3 && item.getString("w").length() > 1) {
                    keywords[k] = item.getString("w");
                    k++;
                }
            }
            Log.i("test","keywords:"+keywords[0]+" "+keywords[1]+" "+keywords[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    关闭录音对话框，录音按钮回复默认
     */
    private void recordViewsDefalt() {
        iatDialog.dismiss();
        //改按钮文字，提示正在发送
        tv_recording_text.setText("点击 说话");
        tv_recording_text.setTextColor(getResources().getColor(R.color.gray));
        ll_press_record.setBackgroundResource(R.drawable.btn_tuoyuan_frameorange);
    }

    /**
     * @param audioName 本地录音文件名
     * @param ms  后端点静音时间
     */
    private void resetDialogParam(String audioName, String ms) {
        iatDialog.setParameter(SpeechConstant.VAD_BOS, "10000");// 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理1000~10000
        iatDialog.setParameter(SpeechConstant.VAD_EOS, ms);// 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音0~10000
        iatDialog.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");//格式支持pcm、wav
        if (audioName != null) {
            iatDialog.setParameter(SpeechConstant.ASR_AUDIO_PATH, Common.getWavFilePath(audioName));//语音保存路径
        }
    }
    private void uploadUtilToQiNiu() {
        //先获取本地文件
        File data = new File(Common.getWavFilePath(audioName));
        if (mytoken == null){
            Toast.makeText(V2VoicePhotoBuyActivity.this,"再试一次，说更详细一点吧",Toast.LENGTH_SHORT).show();
            return;
        }
        UploadManager uploadManager = new UploadManager();
        uploadManager.put(data, null, mytoken, new UpCompletionHandler() {
            @Override
            public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
                //得到路径 将路径给服务器存数据库
                System.out.println(jsonObject);
                if (responseInfo.isOK()) {
                    System.out.println(jsonObject);
                    try {
                        name = jsonObject.getString("key");
                        Log.i("rest","上传成功,存储路径为："+Common.getWavFilePath(audioName));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    request_uploadvoice_to_myserver();
                }else {
                    System.out.println("七牛返回失败");
                    Toast.makeText(V2VoicePhotoBuyActivity.this,"服务器忙，请稍后再试",Toast.LENGTH_SHORT).show();
                }
            }
        },null);

    }
    private void takeANewPicture() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        imageName = df.format(calendar.getTime());
        preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("imageName", imageName);
        editor.commit();
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), Common.IMAGE_FILE_NAME + imageName + ".jpg")));
            try {
                startActivityForResult(intentFromCapture, Common.CODE_CAMERA_REQUEST);
            }catch (Exception e){
                Toast.makeText(getApplication(), "未能找到可用相机", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplication(), "检测到您没有SD卡", Toast.LENGTH_SHORT).show();
        }
    }
    //判断SD卡是否可用
    private boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }else {
            return false;
        }
    }
    /** 回调振幅，根据振幅设置图片 */
    private RecordManger.SoundAmplitudeListen onSoundAmplitudeListen = new RecordManger.SoundAmplitudeListen() {

        @Override
        public void amplitude(int amplitude, int db, int value) {
            if (value >= 6) {
                value = 6;
            }
//            progress.setBackgroundDrawable(progressImg[value]);// 显示震幅图片

        }
    };
    /** 下载播放状态监听器 */
    private OnStateListener onDownloadFileFileStateListener = new OnStateListener() {
        @Override
        public void onState(int error, String msg) {
            // TODO Auto-generated method stub

        }
    };
    /** 文件上传状态监听器 */
    private OnStateListener onUploadFileStateListener = new OnStateListener() {

        @Override
        public void onState(int error, String msg) {
            // TODO Auto-generated method stub
            stopTime();
            if (error != 0) {

                progress.setVisibility(View.GONE);
                mic_icon.setBackgroundDrawable(null);
                mic_icon.setImageResource(R.mipmap.voice_search_recognize_error);
                Toast.makeText(V2VoicePhotoBuyActivity.this,"录音失败可能被限制录音权限",Toast.LENGTH_SHORT).show();
                rl_recording.setVisibility(View.GONE);
                return;
            }else{
                Log.i("test","上传服务器成功");
//                request_recently_messages();
                rl_recording.setVisibility(View.GONE);
            }
        }
    };
    private void playSound() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] pattern = {100,100};
        vibrator.vibrate(pattern,-1);
    }
    private void stopTime() {
        handler.removeCallbacks(run);// 移除计时器
        text_msg.setText("");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 427){
            if (data != null){
                //分割字符串 放入list 刷新adapter
                if (data.getStringExtra("finaltext").equals("")){
                    lv_fast_text.setVisibility(View.GONE);
                    tv_nofasttext.setVisibility(View.VISIBLE);
                }else {
                    lv_fast_text.setVisibility(View.VISIBLE);
                    tv_nofasttext.setVisibility(View.GONE);
                    subStringInflateList(data.getStringExtra("finaltext"));
                }
            }
        }
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        switch (requestCode) {
            case Common.CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
                    imageName = preferences.getString("imageName", null);
                    if (imageName != null) {
                        String localPath = Environment.getExternalStorageDirectory() + "/" + Common.IMAGE_FILE_NAME + imageName + ".jpg";
                        Intent intent = new Intent(V2VoicePhotoBuyActivity.this, CropPhotoActivity.class);
                        intent.putExtra("path", localPath);
                        startActivity(intent);
                    } else {
                        Toast.makeText(V2VoicePhotoBuyActivity.this, "未能获取到照片，请重试", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplication(), "没有SD卡", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isShowingMenu){
                isShowingMenu = false;
                setDefaultViews();
            }else {
                V2VoicePhotoBuyActivity.this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
