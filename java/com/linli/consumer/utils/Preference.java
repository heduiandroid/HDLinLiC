package com.linli.consumer.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

/**
 * 创建和取出SharedPreference
 * Created by Administrator on 2016/3/28.
 */
public class Preference {
    public static final String MY_USERNAME = "my_username";
    public static final String MY_PASSWORD = "my_password";
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    private Context mContext;

    public final static HashMap<String, String> STRING_PREFS = new HashMap<String, String>(){
        private static final long serialVersionUID = 1L;
        {
        }
    };

    public Preference(Context pContext) {
        this.mContext = pContext;
        mPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
    }
    public void startEditor(){
        mEditor = mPrefs.edit();
    }
    public void endEditor(){
        if (mEditor != null){
            mEditor.commit();
            mEditor = null;
        }
    }

    public void setPreferenceStringValue(String key,String value){
        if (mEditor == null){
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(key,value);
            editor.commit();
        }else {
            mEditor.putString(key,value);
        }
    }
    public String getPreferenceStringValue(String key){
        return gPrefStringValue(mPrefs,key);
    }
    private static String gPrefStringValue(SharedPreferences aPrefs, String key){
        if (aPrefs == null){
            return STRING_PREFS.get(key);
        }
        if (STRING_PREFS.containsKey(key)){
            return aPrefs.getString(key,STRING_PREFS.get(key));
        }
        return aPrefs.getString(key,(String)null);
    }
}










