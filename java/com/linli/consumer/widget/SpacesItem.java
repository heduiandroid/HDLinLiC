package com.linli.consumer.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItem extends RecyclerView.ItemDecoration {

    private int space;

    public SpacesItem(int space) {
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=space;
        outRect.right=space;
        outRect.bottom=space;
        //注释这两行是为了上下间距相同
//        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=space;
//        }
    }
}