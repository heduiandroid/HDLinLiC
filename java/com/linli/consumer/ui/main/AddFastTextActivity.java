package com.linli.consumer.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.linli.consumer.R;

public class AddFastTextActivity extends Activity implements View.OnClickListener {
    private EditText et_text;
    private SharedPreferences preferences;

    private boolean isUpdate = false;//是否是来修改文字的
    private String partOldText;//需要修改的字符串

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fast_text);

        if (getIntent().getExtras()!= null){
            isUpdate = getIntent().getBooleanExtra("updatetext",false);
            if (isUpdate){//是否是来修改文字的
                partOldText = getIntent().getStringExtra("updatestr");
                init();
                et_text.setText(partOldText);
            }else {//不是来修改修改文字的 也不是当跳板的 就是来删除的
                //把字符串删掉 finalString返回
                String deleteStr = getIntent().getStringExtra("deletestr");
                String finaltext = IOPreferenceForDelete(deleteStr);
                //成功后
                Toast.makeText(AddFastTextActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                setResult(427,new Intent().putExtra("finaltext",finaltext));
                finish();
            }
        }else {
            init();
        }
    }

    private void init(){
        findViewById(R.id.ll_dialog).setOnClickListener(this);
        findViewById(R.id.btn_finish).setOnClickListener(this);
        et_text = (EditText) findViewById(R.id.et_text);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_dialog:
                break;
            case R.id.btn_finish:
                if (TextUtils.isEmpty(et_text.getText())){
                    Toast.makeText(AddFastTextActivity.this,"请输入文字信息",Toast.LENGTH_SHORT).show();
                }else if (et_text.getText().toString().contains("&&")){
                    Toast.makeText(AddFastTextActivity.this,"包含敏感信息或非法字符",Toast.LENGTH_SHORT).show();
                }else {
                    if (isUpdate){
                        String partNewText = et_text.getText().toString().trim();
                        String finaltext = IOPreferenceForUpdate(partNewText);
                        //成功后
                        Toast.makeText(AddFastTextActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        setResult(427,new Intent().putExtra("finaltext",finaltext));
                        finish();
                    }else {
                        //添加preference到本地
                        String newtext = et_text.getText().toString().trim();
                        String finaltext = IOPreference(newtext);
                        //成功后
                        Toast.makeText(AddFastTextActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                        setResult(427,new Intent().putExtra("finaltext",finaltext));
                        finish();
                    }
                }
                break;
            default:
                break;
        }
    }
    private String IOPreferenceForUpdate(String partNewText){
        preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
        String oldtext = preferences.getString("fasttext","");
        oldtext = oldtext.replace(partOldText,partNewText);
        SharedPreferences.Editor editor=preferences.edit();
        String finaltext = oldtext;
        editor.putString("fasttext", finaltext);
        editor.commit();
        return finaltext;
    }
    private String IOPreferenceForDelete(String deletestr){
        preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
        String oldtext = preferences.getString("fasttext","");
        oldtext = oldtext.replace(deletestr+"&&","");
        SharedPreferences.Editor editor=preferences.edit();
        String finaltext = oldtext;
        editor.putString("fasttext", finaltext);
        editor.commit();
        return finaltext;
    }
    private String IOPreference(String newtext){
        preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
        String oldtext = preferences.getString("fasttext","");
        SharedPreferences.Editor editor=preferences.edit();
        String finaltext = oldtext+newtext+"&&";
        editor.putString("fasttext", finaltext);
        editor.commit();
        return finaltext;
    }

}
