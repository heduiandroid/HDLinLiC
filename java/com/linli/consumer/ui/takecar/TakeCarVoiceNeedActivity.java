package com.linli.consumer.ui.takecar;

import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.ActivityCollector;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.Generic;
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

public class TakeCarVoiceNeedActivity extends BaseActivity {
    private TextView tv_start_place;
    private RelativeLayout ll_bg_voice;
    private TextView tv_reset_voice;
    private TextView tv_voicetime;
    private ImageView iv_wave;
    private Button btn_cancel,btn_confirm;
    private City startPoint = new City();
    private MediaPlayer player = new MediaPlayer();
    private AnimationDrawable animationDrawable = null;
    private AppContext appContext;
    private User user;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_take_car_voice_need;
    }

    @Override
    protected void initView() {
        dismiss();
        findViewClick(R.id.iv_back);
        TextView header = findViewClick(R.id.tv_head_name);
        header.setText("我的需求");
        findView(R.id.iv_head_right).setVisibility(View.GONE);
        TextView right = findViewClick(R.id.tv_head_right);
        right.setText("我的行程");

        tv_start_place = findView(R.id.tv_start_place);
        ll_bg_voice = findViewClick(R.id.ll_bg_voice);
        tv_reset_voice = findViewClick(R.id.tv_reset_voice);
        tv_voicetime = findView(R.id.tv_voicetime);
        iv_wave = findView(R.id.iv_wave);
        btn_cancel = findViewClick(R.id.btn_cancel);
        btn_confirm = findViewClick(R.id.btn_confirm);
    }

    @Override
    protected void initData() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        startPoint = (City) getIntent().getSerializableExtra("startPoint");
        tv_start_place.setText("起点："+startPoint.getName()+startPoint.getArea()+startPoint.getStreet().replace("null",""));
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                iv_wave.setImageResource(R.drawable.voice_anim);
                animationDrawable = (AnimationDrawable) iv_wave.getDrawable();
                animationDrawable.stop();
                iv_wave.setImageResource(R.mipmap.ic_wave3);
            }
        });
        request_upload_token();
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
            case R.id.ll_bg_voice:
                if (player.getDuration()>0){
                    iv_wave.setImageResource(R.drawable.voice_anim);
                    animationDrawable = (AnimationDrawable)iv_wave.getDrawable();
                    animationDrawable.start();
                    player.start();
                }else {
                    Toast.makeText(this,"请先录制好您的语音",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_reset_voice:
                player.reset();
                request_upload_token();
                break;
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_confirm:
                if (user != null){
                    if (name != null && player.getDuration() > 0) {
                        request_uploadvoice_to_myserver();
                    }else {
                        showToast("请先录制好您的语音");
                    }
                }else {
                    UIHelper.togoLoginActivity(this);
                }
                break;
            default:
                break;
        }

    }

    private void request_uploadvoice_to_myserver() {//需求类型 1-文字2-语音
        showSimpleDialog();
        IntrestBuyNet.addTaxiDemand(2, user.getId(), startPoint.getName()+startPoint.getArea()+startPoint.getStreet().replace("null",""), result,
                startPoint.getLongitude(), startPoint.getLatitude(),
                null, null, name, new HandleSuccess<Generic>() {
                    @Override
                    public void success(Generic s) {
                        dismissSimDialog();
                        if (s.getStatus() == 1){
                            //跳转匹配司机页面
                            UIHelper.togoTakeCarWaitDriverAccept(TakeCarVoiceNeedActivity.this);
                            for(int i = 0; i < ActivityCollector.activities.size() ; i ++){
                                if(ActivityCollector.activities.get(i) instanceof TakeCarIndexActivity){
                                    ActivityCollector.activities.get(i).finish();
                                }
                            }
                            TakeCarVoiceNeedActivity.this.finish();
                        }
                    }
                });

    }

    private String mytoken = null;
    private String audioName;//存入本地的语音名称
    //有动画效果
    private RecognizerDialog iatDialog;
    private String result = null;//语音转文字结果
    private String name = null;//语音或者图片路径
    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                Toast.makeText(TakeCarVoiceNeedActivity.this, "初始化失败，错误码：" + code, Toast.LENGTH_SHORT).show();
            }
        }
    };
    private void request_upload_token() {
        IntrestBuyNet.getToken(null, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic generic) {
                if (generic.getStatus() == 1){
                    mytoken = generic.getData().toString();
                    startRecordByXunFei();
                }else {
                    Toast.makeText(TakeCarVoiceNeedActivity.this,generic.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void startRecordByXunFei() {
        //先给存入本地的文件取名字 拼全连接并存入本地 待取用
        audioName = Common.IMAGE_FILE_NAME+ CommonUtil.getNowTimeString();
        // ②初始化有交互动画的语音识别器
        iatDialog = new RecognizerDialog(TakeCarVoiceNeedActivity.this, mInitListener);
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
                        //调用推送文字信息接口
                        uploadUtilToQiNiu();//*********语音上传至七牛********
                    }else {
                        Toast.makeText(TakeCarVoiceNeedActivity.this,"请说的更详细一些",Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onError(SpeechError speechError) {
                //自动生成的方法存根
                Log.i("test","onError执行"+speechError.getErrorCode());
                if(speechError.getErrorCode() == 20006){
                    Toast.makeText(TakeCarVoiceNeedActivity.this,"打开录音失败，请检查权限",Toast.LENGTH_LONG).show();
                }else if (speechError.getErrorCode() == 10118){
                    Toast.makeText(TakeCarVoiceNeedActivity.this,"您好像没有说话哦",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(TakeCarVoiceNeedActivity.this,"请说的更详细一些",Toast.LENGTH_LONG).show();
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
        tv_reset_voice.setVisibility(View.INVISIBLE);
        tv_voicetime.setText("等待录音");
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
        tv_reset_voice.setVisibility(View.VISIBLE);
        //改按钮文字，提示正在发送
    }
    private void uploadUtilToQiNiu() {
        //先获取本地文件
        File data = new File(Common.getWavFilePath(audioName));
        if (mytoken == null){
            Toast.makeText(TakeCarVoiceNeedActivity.this,"再试一次，说更详细一点吧",Toast.LENGTH_SHORT).show();
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
                        tv_voicetime.setText(Math.round(player.getDuration()/1000)+ "''");//
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else {
                    System.out.println("七牛返回失败");
                    Toast.makeText(TakeCarVoiceNeedActivity.this,"服务器忙，请稍后再试",Toast.LENGTH_SHORT).show();
                }
            }
        },null);

    }

    @Override
    protected void onResume() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player.isPlaying()){
            player.stop();
        }
    }
}
