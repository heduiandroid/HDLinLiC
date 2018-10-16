package com.linli.consumer.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.linli.consumer.R;

/**
 * Created by hasee on 2017/10/20.
 */

public class SoundsUtil {
    // SoundPool对象
    public static SoundPool mSoundPlayer = new SoundPool(10,
            AudioManager.STREAM_SYSTEM, 5);
    public static SoundsUtil soundPlayUtils;
    // 上下文
    static Context mContext;

    /**
     * 初始化
     *
     * @param context
     */
    public static SoundsUtil init(Context context) {
        if (soundPlayUtils == null) {
            soundPlayUtils = new SoundsUtil();
        }

        // 初始化声音
        mContext = context;

        mSoundPlayer.load(mContext, R.raw.papapa, 1);// 1 拍一拍拍击音效

        return soundPlayUtils;
    }

    /**
     * 播放声音
     *
     * @param soundID
     */
    public static void play(int soundID) {
        mSoundPlayer.play(soundID, 1, 1, 0, 0, 1);
    }
}
