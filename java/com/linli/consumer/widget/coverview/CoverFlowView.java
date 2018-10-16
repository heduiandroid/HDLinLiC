package com.linli.consumer.widget.coverview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;

/**
 * Created by yichao on 16/2/25.
 *
 * 第三方控件，用于在美食详情展示美食
 * 是一个coverView
 */
public class CoverFlowView extends RecyclerView {

    private Handler handler = new Handler(Looper.getMainLooper());
    private static final String TAG = "CoverFlowView";

    /**
     * Whether set item angle
     */
    private boolean isTilted = true;
    /**
     * Item tilted factor
     */
    private static final int TILTED_FACTOR = 15;

    /**
     * LayoutManager orientation
     */
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 2;

    private int last_position = 0;
    private int current_position = 0;
    private int left_border_position = 0;
    private int right_border_position = 0;

    private int orientation = 0;

    private boolean flag = false;

    private CoverFlowItemListener coverFlowListener;
    private LinearLayoutManager layoutManager;

    private final Camera mCamera = new Camera();
    private final Matrix mMatrix = new Matrix();
    /**
     * Paint object to draw with
     */
    private final Paint mPaint = new Paint(Paint.FILTER_BITMAP_FLAG);


    public CoverFlowView(Context context) {
        super(context);
        init();
    }

    public CoverFlowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CoverFlowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPaint.setAntiAlias(true);
        this.setChildrenDrawingOrderEnabled(true);
        this.addOnScrollListener(new CoverFlowScrollListener());
    }


    @Override
    public boolean drawChild(Canvas canvas, View child, long drawingTime) {
        Bitmap bitmap = getChildDrawingCache(child);
        // (top,left) is the pixel position of the child inside the list
        final int top = child.getTop();
        final int left = child.getLeft();
        // center point of child
        final int childCenterY = child.getHeight() / 2;
        final int childCenterX = child.getWidth() / 2;
        //center of list
        final int parentCenterY = getHeight() / 2;
        final int parentCenterX = getWidth() / 2;
        //center point of child relative to list
        final int absChildCenterY = child.getTop() + childCenterY;
        final int absChildCenterX = child.getLeft() + childCenterX;
        //distance of child center to the list center
        final int distanceY = parentCenterY - absChildCenterY;

        final int distanceX = parentCenterX - absChildCenterX;

        if (orientation == HORIZONTAL) {
            prepareMatrix(mMatrix, distanceX, getWidth() / 2);
        } else {
            prepareMatrix(mMatrix, distanceY, getHeight() / 2);
        }

        mMatrix.preTranslate(-childCenterX, -childCenterY);
        mMatrix.postTranslate(childCenterX, childCenterY);
        mMatrix.postTranslate(left, top);

        canvas.drawBitmap(bitmap, mMatrix, mPaint);
        return false;
    }

    private void prepareMatrix(final Matrix outMatrix, int distance, int r) {
        //clip the distance
        final int d = Math.min(r, Math.abs(distance));
        //use circle formula
        final float translateZ = (float) Math.sqrt((r * r) - (d * d));
        mCamera.save();
        float offset = r - translateZ;
        mCamera.translate(0, 0, offset);
        if (isTilted) {
            float deg = offset / TILTED_FACTOR;
            if (distance > 0) {
                mCamera.rotateY(deg);
            } else {
                mCamera.rotateY(- deg);
            }
        }
        mCamera.getMatrix(outMatrix);
        mCamera.restore();
    }

    private Bitmap getChildDrawingCache(final View child) {
        Bitmap bitmap = child.getDrawingCache();
        if (bitmap == null) {
            child.setDrawingCacheEnabled(true);
            child.buildDrawingCache();
            bitmap = child.getDrawingCache();
        }
        return bitmap;
    }
    protected int get1ChildDrawingOrder (int childCount, int i) {
        int centerChild = 0;
        //find center row
        if ((childCount % 2) == 0) { //even childCount number
            centerChild = childCount / 2; // if childCount 8 (actualy 0 - 7), then 4 and 4-1 = 3 is in centre.
            int otherCenterChild = centerChild - 1;
            //Which more in center?
            View child = this.getChildAt(centerChild);
            final int top = child.getTop();
            final int bottom = child.getBottom();
            //if this row goes through center then this
            final int absParentCenterY = getTop() + getHeight() / 2;
            //Log.i("even", i + " from " + (childCount - 1) + ", while centerChild = " + centerChild);
            if ((top < absParentCenterY) && (bottom > absParentCenterY)) {
                //this child is in center line, so it is last
                //centerChild is in center, no need to change
            } else {
                centerChild = otherCenterChild;
            }
        }
        else {//not even - done
            centerChild = childCount / 2;
            //Log.i("not even", i + " from " + (childCount - 1) + ", while centerChild = " + centerChild);
        }

        int rez = i;
        //find drawIndex by centerChild
        if (i > centerChild) {
            //below center
            rez = (childCount - 1) - i + centerChild;
        } else if (i == centerChild) {
            //center row
            //draw it last
            rez = childCount - 1;
        } else {
            //above center - draw as always
            // i < centerChild
            rez = i;
        }
        //Log.i("return", "" + rez);
        return rez;

    }
    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        int centerChild = childCount / 2;
        if (!flag) {
            ((CoverFlowAdapter) getAdapter()).setBorder_position(centerChild);
            left_border_position = centerChild;
            right_border_position = ((CoverFlowAdapter) getAdapter()).getItemCount() - centerChild - 1;
            Log.i(TAG, "left_border_position:" + left_border_position);
            Log.i(TAG, "right_border_position" + right_border_position);
            flag = true;
        }
        current_position = layoutManager.findFirstVisibleItemPosition() + centerChild;
        if (last_position != current_position) {
            last_position = current_position;
            coverFlowListener.onItemChanged(current_position);
        }

        int rez = i;
        //find drawIndex by centerChild
        if (i > centerChild) {
            //below center
            rez = (childCount - 1) - i + centerChild;
        } else if (i == centerChild) {
            //center row
            //draw it last
            rez = childCount - 1;
        } else {
            //above center - draw as always
            // i < centerChild
            rez = i;
        }
        return rez;

    }

    public interface CoverFlowItemListener {
        void onItemChanged(int position);

        void onItemSelected(int position);
    }

    public void setCoverFlowListener(CoverFlowItemListener coverFlowListener) {
        this.coverFlowListener = coverFlowListener;
    }

    public class CoverFlowScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                Log.i(TAG, "onScrollStateChanged");

                coverFlowListener.onItemSelected(current_position);
                Log.i(TAG, "current_position:" + current_position);
                if (current_position > right_border_position) {
                    scrollToCenter(right_border_position);
                    return;
                }
                if (current_position < left_border_position) {
                    scrollToCenter(left_border_position);
                    return;
                }

                int first_position = layoutManager.findFirstVisibleItemPosition();
                View centerChild = CoverFlowView.this.getChildAt(current_position - first_position);
                int[] location = new int[2];
                centerChild.getLocationInWindow(location);
                int centerItemX = location[0] + centerChild.getWidth() / 2;

                Display display = getDisplay();
                final Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int centerX = width / 2;
                CoverFlowView.this.smoothScrollBy(centerItemX - centerX, 0);
            }
        }
    }

    @TargetApi(17)
    public void scrollToCenter(int position) {
        if (position <= right_border_position && position >= left_border_position) {
            int first_position = layoutManager.findFirstVisibleItemPosition();
            int current_position = position - first_position;
            View targetChild = this.getChildAt(current_position);
            if (targetChild == null) {
                return;
            }
            int[] location = new int[2];
            targetChild.getLocationInWindow(location);
            final int targetItemX = location[0] + targetChild.getWidth() / 2;

            Display display = getDisplay();
            final Point size = new Point();
            display.getSize(size);
            int width = size.x;
            final int centerX = width / 2;

            handler.post(new Runnable() {
                @Override
                public void run() {
                    CoverFlowView.this.smoothScrollBy(targetItemX - centerX, 0);
                }
            });
        }
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
        DividerItemDecoration itemDecoration;
        if (orientation == VERTICAL) {
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            itemDecoration = new DividerItemDecoration(0, -50);
        } else {
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            itemDecoration = new DividerItemDecoration(-50, 0);
        }
        this.setLayoutManager(layoutManager);
        this.addItemDecoration(itemDecoration);
    }

    private class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private int leftPadding, topPadding;

        public DividerItemDecoration(int leftPadding, int topPadding) {
            this.leftPadding = leftPadding;
            this.topPadding = topPadding;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (view.getId() == 0) {
                return;
            }
            outRect.left = leftPadding;
            outRect.top = topPadding;
        }
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    /**
     * Whether set item angle
     * @param tilted
     */
    public void setTilted(boolean tilted) {
        isTilted = tilted;
    }


    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityX *= 0.5;
        return super.fling(velocityX, velocityY);
    }
}
