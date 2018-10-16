package com.linli.consumer.widget.wrilayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linli.consumer.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by zhy on 15/9/10.
 */
public class TagFlowLayout extends FlowLayout implements TagAdapter.OnDataChangedListener {
    private static final String TAG = "TagFlowLayout";
    private static final String KEY_CHOOSE_POS = "key_choose_pos";
    private static final String KEY_DEFAULT = "key_default";
    public int selectedIndex = -1;
    private TagAdapter mTagAdapter;
    private boolean mAutoSelectEffect = true;
    private int mSelectedMax = -1;//-1为不限制数量
    private MotionEvent mMotionEvent;
    private Set<Integer> mSelectedView = new HashSet<Integer>();
    private OnSelectListener mOnSelectListener;
    private List<String> lockList = new ArrayList<>();     //需要锁住list

    public TagFlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TagFlowLayout);
        mAutoSelectEffect = ta.getBoolean(R.styleable.TagFlowLayout_auto_select_effect, true);
        mSelectedMax = ta.getInt(R.styleable.TagFlowLayout_max_select, -1);
        ta.recycle();

        if (mAutoSelectEffect) {
            setClickable(true);
        }
    }

    public TagFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagFlowLayout(Context context) {
        this(context, null);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int cCount = getChildCount();

        for (int i = 0; i < cCount; i++) {
            TagView tagView = (TagView) getChildAt(i);
            if (tagView.getVisibility() == View.GONE) continue;
            if (tagView.getTagView().getVisibility() == View.GONE) {
                tagView.setVisibility(View.GONE);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
        if (mOnSelectListener != null) setClickable(true);
    }


    private void changeAdapter() {
        removeAllViews();
        TagAdapter adapter = mTagAdapter;
        TagView tagViewContainer = null;
        HashSet preCheckedList = mTagAdapter.getPreCheckedList();
        for (int i = 0; i < adapter.getCount(); i++) {
            View tagView = adapter.getView(this, i, adapter.getItem(i));

            tagViewContainer = new TagView(getContext());
            tagView.setDuplicateParentStateEnabled(true);
            if (tagView.getLayoutParams() != null) {
                tagViewContainer.setLayoutParams(tagView.getLayoutParams());
            } else {
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(dip2px(getContext(), 5),
                        dip2px(getContext(), 5),
                        dip2px(getContext(), 5),
                        dip2px(getContext(), 5));
                tagViewContainer.setLayoutParams(lp);
            }
            tagViewContainer.addView(tagView);
            addView(tagViewContainer);


            if (preCheckedList.contains(i)) {
                tagViewContainer.setChecked(true);
            }

            if (mTagAdapter.setSelected(i, adapter.getItem(i))) {
                mSelectedView.add(i);
                tagViewContainer.setChecked(true);
            }
        }
        mSelectedView.addAll(preCheckedList);

    }

    public void setUnLock(List<String> lockList) {
        this.lockList = lockList;
        if(this.lockList.size() > 0){
            for(int i = 0 ; i < this.getChildCount(); i ++){
                TagView parent = (TagView)this.getChildAt(i);
                TextView textView = (TextView)parent.getChildAt(0);
                boolean isExist = false;
                for(int j = 0 ; j < this.lockList.size() ; j ++){
                    if(textView.getText().toString().equals(this.lockList.get(j))){
                        isExist = true;
                        break;
                    }
                }
                if(isExist){
                    textView.setTextColor(getResources().getColor(R.color.orange));
                } else {
                    textView.setTextColor(getResources().getColor(R.color.gray));
                }

            }
        } else {
            for(int i = 0 ; i < this.getChildCount(); i ++){
                TagView parent = (TagView)this.getChildAt(i);
                TextView textView = (TextView)parent.getChildAt(0);
                textView.setTextColor(getResources().getColor(R.color.gray));
            }
        }
    }

    public void getUnLock() {
        String aa = "";
        for (int i = 0; i < this.lockList.size(); i++) {
            aa = aa + this.lockList.get(i);
        }
        Log.i("WATER", "setUnLoc==>>   " + aa+"   数据集大小==》》"+lockList.size());
    }

    private void useSelected(TagView child, int position) {
        if (mAutoSelectEffect) {
            if (!child.isChecked()) {       //未选中
                //处理max_select=1的情况
                if (mSelectedMax == 1 && mSelectedView.size() == 1) {               //有一个选中

                    if (lockList.size() > 0) {
                        TextView textView = (TextView) child.getChildAt(0);
                        String ss = textView.getText().toString();
                        for (int i = 0; i < lockList.size(); i++) {
                            Log.i("WATER", "tagLayout__1__==>> " + lockList.get(i));
                            if (ss.equals(lockList.get(i))) {
                                Iterator<Integer> iterator = mSelectedView.iterator();
                                Integer preIndex = iterator.next();
                                TagView pre = (TagView) getChildAt(preIndex);
                                pre.setChecked(false);

                                child.setChecked(true);
                                ((TextView) pre.getChildAt(0)).setTextColor(getResources().getColor(R.color.gray));
                                ((TextView) child.getChildAt(0)).setTextColor(getResources().getColor(R.color.orange));
                                mSelectedView.remove(preIndex);
                                mSelectedView.add(position);
                            }
                        }

                    } else {
                        Iterator<Integer> iterator = mSelectedView.iterator();
                        Integer preIndex = iterator.next();
                        TagView pre = (TagView) getChildAt(preIndex);
                        pre.setChecked(false);
                        child.setChecked(true);
                        ((TextView) pre.getChildAt(0)).setTextColor(getResources().getColor(R.color.gray));
                        ((TextView) child.getChildAt(0)).setTextColor(getResources().getColor(R.color.orange));
                        mSelectedView.remove(preIndex);
                        mSelectedView.add(position);
                    }

                } else {                                                //都没有选中
                    if (mSelectedMax > 0 && mSelectedView.size() >= mSelectedMax)
                        return;
                    if (lockList.size() > 0) {
                        TextView textView = (TextView) child.getChildAt(0);
                        String ss = textView.getText().toString();
                        for (int i = 0; i < lockList.size(); i++) {
                            Log.i("WATER", "tagLayout__0_==>> " + lockList.get(i));
                            if (ss.equals(lockList.get(i))) {
                                child.setChecked(true);
                                ((TextView) child.getChildAt(0)).setTextColor(getResources().getColor(R.color.orange));
                                mSelectedView.add(position);
                            }
                        }

                    } else {
                        child.setChecked(true);
                        ((TextView) child.getChildAt(0)).setTextColor(getResources().getColor(R.color.orange));
                        mSelectedView.add(position);
                    }

                }
            } else {        //选中
                child.setChecked(false);
                ((TextView) child.getChildAt(0)).setTextColor(getResources().getColor(R.color.gray));
                mSelectedView.remove(position);
            }
            if (mOnSelectListener != null) {
                mOnSelectListener.onSelected(new HashSet<Integer>(mSelectedView));
            }

        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mMotionEvent = MotionEvent.obtain(event);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        if (mMotionEvent == null) return super.performClick();

        int x = (int) mMotionEvent.getX();
        int y = (int) mMotionEvent.getY();
        mMotionEvent = null;

        TagView child = findChild(x, y);
        int pos = findPosByView(child);
        if (child != null) {

            useSelected(child,pos);

        }
        return true;
    }

    public void setMaxSelectCount(int count) {
        if (mSelectedView.size() > count) {
            Log.w(TAG, "you has already select more than " + count + " views , so it will be clear .");
            mSelectedView.clear();
        }
        mSelectedMax = count;
    }

    public Set<Integer> getSelectedList() {
        return new HashSet<Integer>(mSelectedView);
    }

    private void doSelect(TagView child, int position) {
        if (mAutoSelectEffect) {
            if (!child.isChecked()) {
                //处理max_select=1的情况
                if (mSelectedMax == 1 && mSelectedView.size() == 1) {
                    Iterator<Integer> iterator = mSelectedView.iterator();
                    Integer preIndex = iterator.next();
                    TagView pre = (TagView) getChildAt(preIndex);
                    pre.setChecked(false);
                    child.setChecked(true);
                    mSelectedView.remove(preIndex);
                    mSelectedView.add(position);
                } else {
                    if (mSelectedMax > 0 && mSelectedView.size() >= mSelectedMax)
                        return;
                    child.setChecked(true);
                    mSelectedView.add(position);
                }
            } else {
                child.setChecked(false);
                mSelectedView.remove(position);
            }
            if (mOnSelectListener != null) {
                mOnSelectListener.onSelected(new HashSet<Integer>(mSelectedView));
            }
        }
    }

    public TagAdapter getAdapter() {
        return mTagAdapter;
    }

    public void setAdapter(TagAdapter adapter) {
        mTagAdapter = adapter;
        mTagAdapter.setOnDataChangedListener(this);
        mSelectedView.clear();
        changeAdapter();

    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DEFAULT, super.onSaveInstanceState());

        String selectPos = "";
        if (mSelectedView.size() > 0) {
            for (int key : mSelectedView) {
                selectPos += key + "|";
            }
            selectPos = selectPos.substring(0, selectPos.length() - 1);
        }
        bundle.putString(KEY_CHOOSE_POS, selectPos);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            String mSelectPos = bundle.getString(KEY_CHOOSE_POS);
            if (!TextUtils.isEmpty(mSelectPos)) {
                String[] split = mSelectPos.split("\\|");
                for (String pos : split) {
                    int index = Integer.parseInt(pos);
                    mSelectedView.add(index);

                    TagView tagView = (TagView) getChildAt(index);
                    if (tagView != null)
                        tagView.setChecked(true);
                }

            }
            super.onRestoreInstanceState(bundle.getParcelable(KEY_DEFAULT));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    private int findPosByView(View child) {
        final int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            View v = getChildAt(i);
            if (v == child) return i;
        }
        return -1;
    }

    private TagView findChild(int x, int y) {
        final int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            TagView v = (TagView) getChildAt(i);
            if (v.getVisibility() == View.GONE) continue;
            Rect outRect = new Rect();
            v.getHitRect(outRect);
            if (outRect.contains(x, y)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public void onChanged() {
        mSelectedView.clear();
        changeAdapter();
    }

    /**
     * 是否有选择
     */
    public boolean isFlowSelected() {
        if (mSelectedView.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public interface OnSelectListener {
        void onSelected(Set<Integer> selectPosSet);
    }


}
