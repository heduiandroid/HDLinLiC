package com.linli.consumer.ui.main;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.common.Common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckVersionActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_confirm;
    private TextView tv_suggestion;
    private TextView tv_update_nexttime;
    private Boolean isNeedUpdate;
    private ProgressDialog pBar;
    private String appName;//APP存储在本地的名字 不能重复
    public static Boolean isBegined = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_version;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {
        dismiss();
        //发现新版本，可以更新。
        get_if_update();
    }

    private void get_if_update() {
       isNeedUpdate = getIntent().getBooleanExtra("isneedupdate",false);
        if (isNeedUpdate == true){
            tv_suggestion.setText("新的安装包已经准备就绪\n请选择升级\n*修复了已知问题");
            btn_confirm.setText("立即升级");
            tv_update_nexttime.setVisibility(View.VISIBLE);
        }
    }

    private void init() {
        tv_suggestion = (TextView) findViewById(R.id.tv_suggestion);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);//confirm button
        btn_confirm.setOnClickListener(this);
        tv_update_nexttime = findViewClick(R.id.tv_update_nexttime);
        findViewClick(R.id.ll);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.btn_confirm:
                if (isNeedUpdate == true){
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//                        if(!isBegined){
//                            //开启service
//                            Intent intent = new Intent(this,UpdateAppService.class);
//                            startService(intent);
//                            finish();
//                        }else {
//                            Toast.makeText(CheckVersionActivity.this,"正在后台更新中，请稍后",Toast.LENGTH_SHORT).show();
//                        }
                        if (!isBegined){
                            isBegined = true;
                            UpdateVersion();//简单但用户体验不高的暂时更新方法
                        }else {
                            Toast.makeText(CheckVersionActivity.this,"正在后台更新中，请稍后",Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        tv_suggestion.setText("SD卡不可用，请插入SD卡");
                        tv_suggestion.setTextColor(getResources().getColor(R.color.gray));
                        Toast.makeText(CheckVersionActivity.this,"SD卡不可用，请插入SD卡",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    finish();
                }
                break;
            case R.id.tv_update_nexttime:
                finish();
                break;
            default:
                break;
        }
    }

    private void UpdateVersion() {
        pBar = new ProgressDialog(CheckVersionActivity.this);
        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pBar.setTitle("正在更新中");
        pBar.setMessage("请稍后...");
        pBar.setProgress(0);
        pBar.show();
        pBar.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                CheckVersionActivity.this.finish();
            }
        });
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String nowtime = format.format(date);
        appName = Common.IMAGE_FILE_NAME + nowtime + ".apk";////////////////////////////
        new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(Common.downloadAppUrl);///////////
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    int length = (int) entity.getContentLength();   //获取文件大小
                    pBar.setMax(length);                            //设置进度条的总长度
                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;

                    if (is != null) {
                        File file = new File(
                                Environment.getExternalStorageDirectory(),appName);
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[1024];   //这个是缓冲区，即一次读取10个比特，我弄的小了点，因为在本地，所以数值太大一 下就下载完了，看不出progressbar的效果。
                        int ch = -1;
                        int process = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            process += ch;
                            pBar.setProgress(process);       //这里就是关键的实时更新进度了！
                            float all = (float)length/(float)1024/(float)1024;
                            float percent = (float)process/(float)1024/(float)1024;
                            pBar.setProgressNumberFormat(String.format("%.1fM/%.1fM", percent, all));
                        }

                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    pBar.cancel();
                    isBegined = false;
                    CheckVersionActivity.this.finish();
                    update();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }

    private void update() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), appName)),
                "application/vnd.android.package-archive");
        Log.i("updateapp",Environment.getExternalStorageState());
        startActivity(intent);
    }
}
