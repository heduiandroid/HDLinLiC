package com.linli.consumer.utils;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by hasee on 2017/3/29.
 */

public class AnimationUtil {
    /**
     * 从控件上方移动到控件所在位置
     *
     * @return
     */
    public static TranslateAnimation moveFromTopToView() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(1000);
        return mHiddenAction;
    }
    /**
     * 从控件所在位置移动到控件上方
     *
     * @return
     */
    public static TranslateAnimation moveFromViewToTop() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
        mHiddenAction.setDuration(1000);
        return mHiddenAction;
    }
    /**
     * 从控件左侧位置移动到控件位置
     *
     * @return
     */
    public static TranslateAnimation moveFromLeftToView() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(1500);
        return mHiddenAction;
    }
    /**
     * 从控件控件位置移动到左侧位置
     *
     * @return
     */
    public static TranslateAnimation moveFromViewToLeft() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(1000);
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public static TranslateAnimation moveToViewLocation() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(1000);
        return mHiddenAction;
    }
}
