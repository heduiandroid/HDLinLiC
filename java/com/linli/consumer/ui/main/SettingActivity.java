package com.linli.consumer.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.VersionInfo;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.DataCleanManager;
import java.util.List;

public class SettingActivity extends BaseActivity {
    private TextView tv_localcache;
    private Button btn_exit;
    private String cache = "0.0M";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {
        dismiss();
    }

    private void init() {
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("设置");
        findViewClick(R.id.iv_back);
        btn_exit = findViewClick(R.id.btn_exit);
        findViewClick(R.id.ll_message_setting);
        findViewClick(R.id.ll_clean_space);
        findViewClick(R.id.ll_about);
        findViewClick(R.id.ll_update_versions);
        tv_localcache = findView(R.id.tv_localcache);
        try {
            cache = DataCleanManager.getTotalCacheSize(SettingActivity.this);

        } catch (Exception e) {
            e.printStackTrace();
        }
        tv_localcache.setText(cache);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;

            case R.id.ll_message_setting:
//                startActivity(new Intent(SettingActivity.this,SettingMessage.class));
                break;
            case R.id.ll_clean_space:
                if (tv_localcache.getText().toString().equals("0.0M")){
                    Toast.makeText(this,"无可清理数据",Toast.LENGTH_SHORT).show();
                }else {
                    cleanSpace();
                }
                break;
            case R.id.ll_about:
                startActivity(new Intent(SettingActivity.this,AboutUsActivity.class));
                break;
            case R.id.ll_update_versions:
                request_latest_versioncode();
                break;
            case R.id.btn_exit:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("清除登录信息并退出？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(601,new Intent().putExtra("exit",true));
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create();
                builder.show();
                break;
            default:
                break;
        }
    }

    private void request_latest_versioncode() {
        IntrestBuyNet.findAppVersion(1, new HandleSuccess<VersionInfo>() {
            @Override
            public void success(VersionInfo s) {
                if (s.getStatus() == 1){
                    if (s.getData() != null){
                        String versionCode = s.getData();
                        Intent intent = new Intent(SettingActivity.this,CheckVersionActivity.class);
                        if (versionCode.trim().equals(Common.getmVersionCode().trim())){
                            intent.putExtra("isneedupdate",false);
                        }else {
                            intent.putExtra("isneedupdate",true);
                        }
                        startActivity(intent);
                    }else {
                        Toast.makeText(SettingActivity.this,"当前已是最新版本！",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(SettingActivity.this,"当前已是最新版本！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cleanSpace() {
        Toast.makeText(SettingActivity.this,"清理缓存中...",Toast.LENGTH_LONG).show();
        tv_localcache.setText("...");
        //做清理空间的工作
        new Thread(new Runnable() {
            @Override
            public void run() {
                DataCleanManager.clearAllCache(SettingActivity.this);
                List<String> listVoices = DataCleanManager.GetFiles(Environment.getExternalStorageDirectory().getAbsolutePath(), "wav", false);//方法里还添加了删除jpg文件的逻辑
                if (listVoices.size()>0){
                    Log.i("test",listVoices.size()+"个");
                    for (int i = 0;i<listVoices.size();i++){
                        DataCleanManager.deleteFile(listVoices.get(i));
                        Log.i("test",listVoices.get(i)+"Deleted");
                    }
                }else {
                    Log.i("test","nothing to clear");
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SettingActivity.this,"成功清理了"+cache+"缓存数据！",Toast.LENGTH_LONG).show();
                        try {
                            cache = DataCleanManager.getTotalCacheSize(SettingActivity.this);
                            tv_localcache.setText(cache);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        AlertDialog.Builder builder=new AlertDialog.Builder(SettingActivity.this);
//                        builder.setTitle("提示");
//                        builder.setMessage("帮您清理了"+cache+"缓存数据！");
//                        builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                try {
//                                    cache = DataCleanManager.getTotalCacheSize(SettingActivity.this);
//                                    tv_localcache.setText(cache);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//                        builder.create();
//                        builder.show();
                    }
                });
//                new CountDownTimer(2*1000, 1000){//2秒之后
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        //做清理空间的工作
//                        DataCleanManager.clearAllCache(SettingActivity.this);
//                        List<String> listVoices = DataCleanManager.GetFiles("/sdcard/", "amr", true);
//                        if (listVoices.size()>0){
//                            Log.i("test",listVoices.size()+"个");
//                            for (int i = 0;i<listVoices.size();i++){
//                                DataCleanManager.deleteFile(listVoices.get(i));
//                                Log.i("test",listVoices.get(i)+"Deleted");
//                            }
//                        }else {
//                            Log.i("test","nothing to clear");
//                        }
//                    }
//                    @Override
//                    public void onFinish() {
//                        AlertDialog.Builder builder=new AlertDialog.Builder(SettingActivity.this);
//                        builder.setTitle("提示");
//                        builder.setMessage("帮您清理了"+cache+"缓存数据！");
//                        builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                try {
//                                    cache = DataCleanManager.getTotalCacheSize(SettingActivity.this);
//                                    tv_localcache.setText(cache);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//                        builder.create();
//                        builder.show();
//                    }
//                }.start();
            }
        }).start();
    }
}
