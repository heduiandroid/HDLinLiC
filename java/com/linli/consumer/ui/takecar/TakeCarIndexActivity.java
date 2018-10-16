package com.linli.consumer.ui.takecar;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.adapter.AreasAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.TakeCarNeedBean;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.utils.JsonParser;
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

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TakeCarIndexActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private SimpleDraweeView sdv_adv1;
    private String urlAdv1 = "http://obqlfysk2.bkt.clouddn.com/taxi_adv3.jpg";
    private LinearLayout ll_searchedareas_start,ll_searchedareas_end;
    private ProgressBar pb_loading_start,pb_loading_end;
    private ListView lv_searchedareas_start,lv_searchedareas_end;
    private EditText et_start_place, text_views_et;
    private TextView btn_call_cars;
    private ImageView iv_voice_need;
    private ArrayList<City> searchedAreasStart = new ArrayList<City>();
    private ArrayList<City> searchedAreasEnd = new ArrayList<City>();
    private AreasAdapter s_adapter;
    private AreasAdapter e_adapter;
    private SuggestionSearch mSuggestionSearchStart,mSuggestionSearchEnd;
    private SuggestionSearchOption option = new SuggestionSearchOption();
    private AppContext appContext;
    private User user;
    private City city;
    private City startPoint = new City();
    private City endPoint = new City();
    private TextWatcher textWatcherStart = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.i("textwatcher","beforeTextChanged"+et_start_place.getText().toString());
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i("textwatcher","onTextChanged"+et_start_place.getText().toString());
        }
        @Override
        public void afterTextChanged(Editable s) {
            if (city == null || mSuggestionSearchStart == null){
                return;
            }
            if (!TextUtils.isEmpty(et_start_place.getText())){
                pb_loading_start.setVisibility(View.VISIBLE);
                mSuggestionSearchStart.requestSuggestion(option.keyword(et_start_place.getText().toString().trim()).city(city.getName()));
            }else {
                searchedAreasStart.clear();
                pb_loading_start.setVisibility(View.GONE);
                s_adapter.notifyDataSetChanged();
            }
            Log.i("textwatcher","afterTextChanged"+et_start_place.getText().toString());//这里写请求搜索的数据
        }
    };
    private TextWatcher textWatcherEnd = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.i("textwatcher","beforeTextChanged"+ text_views_et.getText().toString());
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i("textwatcher","onTextChanged"+ text_views_et.getText().toString());
        }
        @Override
        public void afterTextChanged(Editable s) {
            if (city == null || mSuggestionSearchEnd == null){
                return;
            }
            if (!TextUtils.isEmpty(text_views_et.getText())){
                pb_loading_end.setVisibility(View.VISIBLE);
                mSuggestionSearchEnd.requestSuggestion(option.keyword(text_views_et.getText().toString().trim()).city(city.getName()));
            }else {
                searchedAreasEnd.clear();
                pb_loading_end.setVisibility(View.GONE);
                e_adapter.notifyDataSetChanged();
            }
            Log.i("textwatcher","afterTextChanged"+ text_views_et.getText().toString());//这里写请求搜索的数据
        }
    };
    private ImageView iv_close_voice_views;
    private LinearLayout voice_views_ll;
    private ImageView iv_wave;
    private RelativeLayout ll_bg_voice;
    private TextView tv_voicetime;
    private MediaPlayer player = new MediaPlayer();
    private AnimationDrawable animationDrawable = null;
    private String mytoken = null;
    private String audioName;//存入本地的语音名称
    //有动画效果
    private RecognizerDialog iatDialog;
    private String result = null;//语音转文字结果
    private String name = null;//七牛返回本地语音名字
    private int type = 1;//1文字目的地需求  2语音目的地需求 默认为1文字
    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                Toast.makeText(TakeCarIndexActivity.this, "初始化失败，错误码：" + code, Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_take_car_index;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.iv_back);
        findView(R.id.iv_head_right).setVisibility(View.GONE);
        TextView header = findViewClick(R.id.tv_head_name);
        header.setText("我的需求");
        TextView rightText = findViewClick(R.id.tv_head_right);
        rightText.setText("我的行程");

        btn_call_cars = findViewClick(R.id.btn_call_cars);
        iv_voice_need = findViewClick(R.id.iv_voice_need);
        ll_searchedareas_start = findView(R.id.ll_searchedareas_start);
        ll_searchedareas_end = findView(R.id.ll_searchedareas_end);
        pb_loading_start = findView(R.id.pb_loading_start);
        pb_loading_end = findView(R.id.pb_loading_end);
        lv_searchedareas_start = findView(R.id.lv_searchedareas_start);
        lv_searchedareas_end = findView(R.id.lv_searchedareas_end);
        lv_searchedareas_start.setOnItemClickListener(this);
        lv_searchedareas_end.setOnItemClickListener(this);
        et_start_place = findView(R.id.et_start_place);
        text_views_et = findView(R.id.et_end_place);

        iv_close_voice_views = findViewClick(R.id.iv_close_voice_views);
        voice_views_ll = findView(R.id.voice_views_ll);
        iv_wave = findView(R.id.iv_wave);
        ll_bg_voice = findViewClick(R.id.ll_bg_voice);
        tv_voicetime = findView(R.id.tv_voicetime);
        sdv_adv1 = findView(R.id.sdv_adv1);

        btn_call_cars.setFocusable(true);
        btn_call_cars.setFocusableInTouchMode(true);
        btn_call_cars.requestFocus();
        btn_call_cars.requestFocusFromTouch();

        et_start_place.addTextChangedListener(textWatcherStart);
        text_views_et.addTextChangedListener(textWatcherEnd);
        et_start_place.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    ll_searchedareas_end.setVisibility(View.GONE);
                    ll_searchedareas_start.setVisibility(View.VISIBLE);
                } else {
                    pb_loading_start.setVisibility(View.GONE);
                    ll_searchedareas_start.setVisibility(View.GONE);
                }
            }
        });
        text_views_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    ll_searchedareas_start.setVisibility(View.GONE);
                    ll_searchedareas_end.setVisibility(View.VISIBLE);
                } else {
                    pb_loading_end.setVisibility(View.GONE);
                    ll_searchedareas_end.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void initData() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        city = appContext.getCity();
        if (city != null){
            startPoint = city;
            Log.i("test",startPoint.getName()+startPoint.getArea()+startPoint.getStreet());
            et_start_place.setText(startPoint.getName()+startPoint.getArea()+startPoint.getStreet().replace("null",""));
        }
        s_adapter = new AreasAdapter(searchedAreasStart,this);
        e_adapter = new AreasAdapter(searchedAreasEnd,this);
        lv_searchedareas_start.setAdapter(s_adapter);
        lv_searchedareas_end.setAdapter(e_adapter);
        mSuggestionSearchStart = SuggestionSearch.newInstance();
        mSuggestionSearchStart.setOnGetSuggestionResultListener(listenerStart);
        mSuggestionSearchEnd = SuggestionSearch.newInstance();
        mSuggestionSearchEnd.setOnGetSuggestionResultListener(listenerEnd);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                iv_wave.setImageResource(R.drawable.voice_anim_white);
                animationDrawable = (AnimationDrawable) iv_wave.getDrawable();
                animationDrawable.stop();
                iv_wave.setImageResource(R.mipmap.iv_wave3_white);
            }
        });
        dismiss();
        sdv_adv1.setImageURI(urlAdv1+Common.SMALLERPICPARAM);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_head_right:
                if (user != null) {
                    UIHelper.togoMyTakeTaxiList(this);
                }else {
                    UIHelper.togoLoginActivity(this);
                }
                break;
            case R.id.iv_close_voice_views:
                //不显示语音框views 显示文字信息框views type更改为1 目的地edittext设""
                voice_views_ll.setVisibility(View.GONE);
                text_views_et.setVisibility(View.VISIBLE);
                text_views_et.setText("");
                type = 1;
                break;
            case R.id.ll_bg_voice:
                if (player.getDuration()>0){
                    iv_wave.setImageResource(R.drawable.voice_anim_white);
                    animationDrawable = (AnimationDrawable)iv_wave.getDrawable();
                    animationDrawable.start();
                    player.start();
                }else {
                    Toast.makeText(this,"等待录音中",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_call_cars:
                if (user != null){
                    if (type == 1){
                        if (!TextUtils.isEmpty(et_start_place.getText()) && startPoint != null){
                            request_add_taxi_demand();
                        }else {
                            showToast("请填写选择起点位置");
                        }
                    }else if (type == 2){
                        if (name != null && player.getDuration() > 0) {
                            request_add_taxi_demand();
                        }else {
                            showToast("请重新录制语音");
                        }
                    }
                }else {
                    UIHelper.togoLoginActivity(this);
                }
                break;
            case R.id.iv_voice_need:
                //关闭两个listview 目的地edittext设""
                ll_searchedareas_start.setVisibility(View.GONE);
                ll_searchedareas_end.setVisibility(View.GONE);
                //动画停止
                iv_wave.setImageResource(R.drawable.voice_anim_white);
                animationDrawable = (AnimationDrawable) iv_wave.getDrawable();
                animationDrawable.stop();
                iv_wave.setImageResource(R.mipmap.iv_wave3_white);
                player.reset();
                request_upload_token();
                break;
        }
    }

    /**
     * 用户添加打车需求
     */
    private void request_add_taxi_demand() {//需求类型 1-文字2-语音
        showSimpleDialog();
        String strEnd = text_views_et.getText().toString();
        switch (type){
            case 1:
                if (TextUtils.isEmpty(text_views_et.getText())){
                    strEnd = "未填写";
                }
                break;
            case 2:
                strEnd = result;
                break;
            default:
                return;
        }
        IntrestBuyNet.addTaxiDemand(type,user.getId(), et_start_place.getText().toString(), strEnd, startPoint.getLongitude(),
                startPoint.getLatitude(), endPoint.getLongitude(), endPoint.getLatitude(),name, new HandleSuccess<Generic>() {
                    @Override
                    public void success(Generic s) {
                        dismissSimDialog();
                        if (s.getStatus() == 1){
                            //跳转匹配司机页面
                            UIHelper.togoTakeCarWaitDriverAccept(TakeCarIndexActivity.this);
                            TakeCarIndexActivity.this.finish();
                        }
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()){
            case R.id.lv_searchedareas_start:
                startPoint = searchedAreasStart.get(position);
                et_start_place.setText(startPoint.getStreet().replace("null",""));
                ll_searchedareas_start.setVisibility(View.GONE);
                break;
            case R.id.lv_searchedareas_end:
                endPoint = searchedAreasEnd.get(position);
                text_views_et.setText(endPoint.getStreet().replace("null",""));
                ll_searchedareas_end.setVisibility(View.GONE);
                break;
            default:
                break;
        }

        ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(TakeCarIndexActivity.this.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        btn_call_cars.setFocusable(true);
        btn_call_cars.setFocusableInTouchMode(true);
        btn_call_cars.requestFocus();
        btn_call_cars.requestFocusFromTouch();
    }
    OnGetSuggestionResultListener listenerStart = new OnGetSuggestionResultListener() {
        @Override
        public void onGetSuggestionResult(SuggestionResult suggestionResult) {
            if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
                return;
                //未找到相关结果
            }else{
                searchedAreasStart.clear();
                List<SuggestionResult.SuggestionInfo> resl = suggestionResult.getAllSuggestions();
                for(int i=0;i<resl.size();i++){
                    if (resl.get(i).pt != null){
                        City city = new City();
                        city.setStreet(resl.get(i).key);
                        city.setName(resl.get(i).city);
                        city.setArea(resl.get(i).district);
                        city.setLongitude(resl.get(i).pt.longitude);
                        city.setLatitude(resl.get(i).pt.latitude);
                        searchedAreasStart.add(city);
                        Log.i("result: ", "city" + resl.get(i).city + " dis " + resl.get(i).district + "key " + resl.get(i).key+resl.get(i).pt.longitude+"  "+resl.get(i).pt.latitude);
                    }
                }
                if (searchedAreasStart.size() > 0){
                    if (s_adapter != null){
                        pb_loading_start.setVisibility(View.GONE);
                        s_adapter.notifyDataSetChanged();
                    }
                }
            }
            //获取在线建议检索结果
        }
    };
    OnGetSuggestionResultListener listenerEnd = new OnGetSuggestionResultListener() {
        @Override
        public void onGetSuggestionResult(SuggestionResult suggestionResult) {
            if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
                return;
                //未找到相关结果
            }else{
                searchedAreasEnd.clear();
                List<SuggestionResult.SuggestionInfo> resl = suggestionResult.getAllSuggestions();
                for(int i=0;i<resl.size();i++){
                    if (resl.get(i).pt != null){
                        City city = new City();
                        city.setStreet(resl.get(i).key);
                        city.setName(resl.get(i).city);
                        city.setArea(resl.get(i).district);
                        city.setLongitude(resl.get(i).pt.longitude);
                        city.setLatitude(resl.get(i).pt.latitude);
                        searchedAreasEnd.add(city);
                        Log.i("result: ", "city" + resl.get(i).city + " dis " + resl.get(i).district + "key " + resl.get(i).key+resl.get(i).pt.longitude+"  "+resl.get(i).pt.latitude);
                    }
                }
                if (searchedAreasEnd.size() > 0){
                    if (e_adapter != null){
                        pb_loading_end.setVisibility(View.GONE);
                        e_adapter.notifyDataSetChanged();
                    }
                }
            }
            //获取在线建议检索结果
        }
    };

    @Override
    protected void onResume() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        if (user != null){
            request_userneedlist_takecar();
        }
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player.isPlaying()){
            player.stop();
        }
    }
    //查一下有没有正在进行的订单
    private void request_userneedlist_takecar() {
        IntrestBuyNet.findByDemandId(user.getId(), 0L, new HandleSuccess<TakeCarNeedBean>() {
            @Override
            public void success(TakeCarNeedBean s) {
                if (s.getStatus() == 1 && s.getData() != null){
                    int status = s.getData().getStatus(); //1-等待接单   2-进行中
                    if (status == 1 || status == 2){
                        UIHelper.togoTakeCarWaitDriverAccept(TakeCarIndexActivity.this);
                        TakeCarIndexActivity.this.finish();
                    }
                }
            }
        });
    }
    private void request_upload_token() {
        IntrestBuyNet.getToken(null, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic generic) {
                if (generic.getStatus() == 1){
                    mytoken = generic.getData().toString();
                    startRecordByXunFei();
                }else {
                    Toast.makeText(TakeCarIndexActivity.this,generic.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void startRecordByXunFei() {
        //先给存入本地的文件取名字 拼全连接并存入本地 待取用
        audioName = Common.IMAGE_FILE_NAME+ CommonUtil.getNowTimeString();
        // ②初始化有交互动画的语音识别器
        iatDialog = new RecognizerDialog(TakeCarIndexActivity.this, mInitListener);
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
                    if (result.length() > 1){
                        uploadUtilToQiNiu();//*********语音上传至七牛********
                    }else {
                        Toast.makeText(TakeCarIndexActivity.this,"请说的更详细一些",Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onError(SpeechError speechError) {
                //自动生成的方法存根
                Log.i("test","onError执行"+speechError.getErrorCode());
                if(speechError.getErrorCode() == 20006){
                    Toast.makeText(TakeCarIndexActivity.this,"打开录音失败，请检查权限",Toast.LENGTH_LONG).show();
                }else if (speechError.getErrorCode() == 10118){
                    Toast.makeText(TakeCarIndexActivity.this,"您好像没有说话哦",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(TakeCarIndexActivity.this,"请说的更详细一些",Toast.LENGTH_LONG).show();
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
    /*
  关闭录音对话框，录音按钮回复默认
   */
    private void recordViewsDefalt() {
        iatDialog.dismiss();
        //改按钮文字等
    }
    private void uploadUtilToQiNiu() {
        //先获取本地文件
        File data = new File(Common.getWavFilePath(audioName));
        if (mytoken == null){
            Toast.makeText(TakeCarIndexActivity.this,"再试一次，说更详细一点吧",Toast.LENGTH_SHORT).show();
            return;
        }
        UploadManager uploadManager = new UploadManager();
        uploadManager.put(data, null, mytoken, new UpCompletionHandler() {
            @Override
            public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
                //得到路径 将路径给服务器存数据库
                System.out.println(responseInfo.toString());
                if (responseInfo.isOK()) {
                    System.out.println(jsonObject);
                    try {
                        name = jsonObject.getString("key");
                        Log.i("test","上传成功,存储路径为："+Common.getWavFilePath(audioName));
                        Log.i("test","上传成功,七牛链接为："+name);
                        player.setDataSource(Common.getWavFilePath(audioName));
                        player.prepare();
                        //显示语音信息views 隐藏文字信息views 计算语音时长并显示 type更改为2 button获取焦点
                        voice_views_ll.setVisibility(View.VISIBLE);
                        text_views_et.setText("");
                        text_views_et.setVisibility(View.GONE);
                        tv_voicetime.setText(Math.round(player.getDuration()/1000)+ "''");//
                        btn_call_cars.setFocusable(true);
                        btn_call_cars.setFocusableInTouchMode(true);
                        btn_call_cars.requestFocus();
                        btn_call_cars.requestFocusFromTouch();
                        type = 2;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else {
                    System.out.println("七牛返回失败");
                    Toast.makeText(TakeCarIndexActivity.this,"服务器忙，请稍后再试",Toast.LENGTH_SHORT).show();
                }
            }
        },null);

    }
}
