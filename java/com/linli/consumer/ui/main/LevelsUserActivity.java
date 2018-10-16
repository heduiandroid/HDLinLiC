package com.linli.consumer.ui.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.domain.User;

public class LevelsUserActivity extends BaseActivity{
    private ProgressBar pb_level;
    private TextView tv_head_name;
    private TextView tv_head_right;
    private TextView tv_level_number;
    private TextView tv_next_level_number;
    private TextView tv_scores_accumulate;
    private TextView tv_scores_levelupneeded;
    private ImageView iv_iclevel;
    private Integer levelNumber;
    private Integer totalPoints;
    private Integer usePoints;
    private AppContext appContext;
    private User user;
    private Integer nowLevel;//当前等级

    @Override
    protected int getLayoutId() {
        return R.layout.activity_levels_user;
    }

    @Override
    protected void initView() {
        init();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
    }

    @Override
    protected void initData() {
        dismiss();
        totalPoints = user.getTotalPoints();
        usePoints = user.getUsePoints();
        if (usePoints >= 0 && usePoints < 2000){
            nowLevel = 1;
        }else if (usePoints >= 2000 && usePoints < 4000){
            nowLevel = 2;
        }else if (usePoints >= 4000 && usePoints < 6000){
            nowLevel = 3;
        }else if (usePoints >= 6000 && usePoints<8000){
            nowLevel = 4;
        }else if (usePoints >= 8000){
            nowLevel = 5;
        }
        tv_scores_accumulate.setText(totalPoints.toString());
        tv_scores_accumulate.setText(usePoints.toString());
        Integer needed = 0;
        switch (nowLevel){
            case 1:
                iv_iclevel.setImageResource(R.mipmap.v1);
                needed = 2000 - usePoints;
                break;
            case 2:
                iv_iclevel.setImageResource(R.mipmap.v2);
                needed = 4000 - usePoints;
                break;
            case 3:
                iv_iclevel.setImageResource(R.mipmap.v3);
                needed = 6000 - usePoints;
                break;
            case 4:
                iv_iclevel.setImageResource(R.mipmap.v4);
                needed = 8000 - usePoints;
                break;
            case 5:
                iv_iclevel.setImageResource(R.mipmap.v5);
                break;
            default:
                break;
        }
        tv_scores_levelupneeded.setText(needed.toString());
        tv_level_number.setText(nowLevel.toString());
        if (nowLevel == 5){
            tv_next_level_number.setText("满级。");
        }else {
            tv_next_level_number.setText((nowLevel+1)+"");
        }
        animatePbStart();
    }


    private void init() {
        findViewClick(R.id.iv_back);
        tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_right = findViewClick(R.id.tv_head_right);
        tv_head_name.setText("等级积分");
        tv_head_right.setText("");//
        tv_level_number =  findView(R.id.tv_level_number);
        tv_next_level_number =  findView(R.id.tv_next_level_number);
        tv_scores_accumulate = findView(R.id.tv_scores_accumulate);
        tv_scores_levelupneeded = findView(R.id.tv_scores_levelupneeded);
        pb_level = findView(R.id.pb_level);
        iv_iclevel = findView(R.id.iv_iclevel);
    }
    private void animatePbStart() {
        pb_level.setProgress(0);
        pb_level.setMax(10000);
//        int score = usePoints/100;//积分在进度条的百分比，当前积分除以100
//        for (int i = 0;i<usePoints;i+=10){
//            final int finalI = i;
//            new Thread() {
//                public void run() {
//                    try {
//                        Thread.sleep(2000);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    pb_level.setProgress(finalI);
//                }
//            }.start();
//        }
        new Thread() {
            public void run() {
                try {
                    int process = 0;
                    while (process < usePoints) {
                        process ++;
                        pb_level.setProgress(process);//这里就是关键的实时更新进度了！
                        Thread.currentThread().sleep(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.tv_head_right:
                Toast.makeText(LevelsUserActivity.this,"敬请留意后续版本",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
