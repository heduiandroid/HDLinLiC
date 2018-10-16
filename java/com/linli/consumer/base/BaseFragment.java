package com.linli.consumer.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linli.consumer.widget.HDDialog;


/**
 * Created by tomoyo on 2016/11/5.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    /**
     * fragment的根布局
     * */
    protected View mRoot;

    /**
     * 宿主Activity
     * */
    protected Activity mActivity;
    private HDDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        if (mRoot != null){
            ViewGroup parent = (ViewGroup)mRoot.getParent();
            if(parent != null){
                parent.removeView(mRoot);
            }
        }else {
            mRoot = inflater.inflate(getLayoutId(),container,false);
            mActivity = getActivity();
            dialog = new HDDialog(mActivity);
            dialog.show();
            initView();
            initData();
        }
        return mRoot;
    }

    /**
     * 获取布局id
     * */
    protected abstract int getLayoutId();

    /**
     * 初始化视图
     * */
    protected abstract void initView();

    /**
     * 初始化数据
     * */
    protected abstract void initData();


    /**
     * 填充数据
     * */
    protected void fillData(){}


    /**
     * 重写findView方法
     * */
    protected <T extends View> T findViewClick(int viewId){
        mRoot.findViewById(viewId).setOnClickListener(this);
        return (T)mRoot.findViewById(viewId);
    }
    /**
     * 重写findView方法
     * */
    protected <T extends View> T findView(int viewId){
        return (T)mRoot.findViewById(viewId);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(dialog.isShowing()){
            dialog.dismiss();
        }
    }
    public void dismiss(){
        dialog.dismiss();
    }
}
