package com.linli.consumer.ui.main;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.NumericWheelAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.UpdateBirthday;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.iface.OnWheelScrollListener;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.widget.WheelView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerActivity extends BaseActivity implements View.OnClickListener {
    private String finalDate;

    private LayoutInflater inflater = null;
    private int mYear;
    private int mMonth;
    private int mDay;
    private WheelView year;
    private WheelView month;
    private WheelView day;
    private String[] birthday_len;
    private LinearLayout ll_timepicker;
    private AppContext appContext;
    private User user;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_date_picker;
    }

    @Override
    protected void initView() {
        String itDate = getIntent().getStringExtra("currentday").trim();
        if (itDate.contains("-")){
            birthday_len = itDate.split("-");//分割出来的字符数组
            mYear = Integer.parseInt(birthday_len[0]);
            mMonth = Integer.parseInt(birthday_len[1]);
            mDay = Integer.parseInt(birthday_len[2]);
            finalDate = itDate;
        }else {
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH) + 1;
            mDay = c.get(Calendar.DAY_OF_MONTH);
            finalDate = mYear + "-" + mMonth + "-" + mDay;
        }
        findViewById(R.id.tv_submit).setOnClickListener(this);
        inflateDatePicker();
    }

    @Override
    protected void initData() {
       dismiss();
    }

    private void inflateDatePicker() {
        ll_timepicker = (LinearLayout) findViewById(R.id.ll_timepicker);
        inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        ll_timepicker.addView(getDataPick());
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    public void onHDClick(View view) {
        request_resetbirthday();
    }

    private void request_resetbirthday() {
//        if ()//成功
        showDialog();
        IntrestBuyNet.updateUserbirthday(user.getId(),finalDate+ " 00:00:00", new HandleSuccess<UpdateBirthday>() {//是否缺少date
            @Override
            public void success(UpdateBirthday s) {
                dismiss();
                if (s.getStatus() == 1){
                    user.setBirthday(finalDate);
                }else {
                    Toast.makeText(DatePickerActivity.this,"网络繁忙，请稍后再试",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    private View getDataPick() {

        Calendar c = Calendar.getInstance();
        int norYear = c.get(Calendar.YEAR);
//		int curMonth = c.get(Calendar.MONTH) + 1;//通过Calendar算出的月数要+1
//		int curDate = c.get(Calendar.DATE);

        int curYear = mYear;
        int curMonth = mMonth;
        int curDate = mDay;

        final View view = inflater.inflate(R.layout.wheel_date_picker, null);

        year = (WheelView) view.findViewById(R.id.year);
        year.setAdapter(new NumericWheelAdapter(1950, norYear));
        year.setLabel("年");
        year.setCyclic(true);
        year.addScrollingListener(scrollListener);

        month = (WheelView) view.findViewById(R.id.month);
        month.setAdapter(new NumericWheelAdapter(1, 12, "%02d"));
        month.setLabel("月");
        month.setCyclic(true);
        month.addScrollingListener(scrollListener);

        day = (WheelView) view.findViewById(R.id.day);
        initDay(curYear, curMonth);
        day.setLabel("日");
        day.setCyclic(true);
        day.addScrollingListener(scrollListener);

        year.setCurrentItem(curYear - 1950);
        month.setCurrentItem(curMonth - 1);
        day.setCurrentItem(curDate - 1);
        return view;

    }
    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            int n_year = year.getCurrentItem() + 1950;//
            int n_month = month.getCurrentItem() + 1;//
            initDay(n_year, n_month);
            String birthday = new StringBuilder().append((year.getCurrentItem() + 1950)).append("-").append((month.getCurrentItem() + 1) < 10 ? "0" + (month.getCurrentItem() + 1) : (month.getCurrentItem() + 1)).append("-").append(((day.getCurrentItem() + 1) < 10) ? "0" + (day.getCurrentItem() + 1) : (day.getCurrentItem() + 1)).toString();

            birthday_len = birthday.split("-");//分割出来的字符数组
            int year_g = Integer.parseInt(birthday_len[0]);
            int month_g = Integer.parseInt(birthday_len[1]);
            int day_g = Integer.parseInt(birthday_len[2]);
            finalDate = birthday;
        }
    };
    /**
     */
    private void initDay(int arg1, int arg2) {
        day.setAdapter(new NumericWheelAdapter(1, getDay(arg1, arg2), "%02d"));
    }
    private int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }

    private String calculateDatePoor(String birthday) {//根据出生年月算出年龄
        try {
            if (TextUtils.isEmpty(birthday))
                return "0";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthdayDate = sdf.parse(birthday);
            String currTimeStr = sdf.format(new Date());
            Date currDate = sdf.parse(currTimeStr);
            if (birthdayDate.getTime() > currDate.getTime()) {
                return "0";
            }
            long age = (currDate.getTime() - birthdayDate.getTime())
                    / (24 * 60 * 60 * 1000) + 1;
            String year = new DecimalFormat("0.00").format(age / 365f);
            if (TextUtils.isEmpty(year))
                return "0";
            return String.valueOf(new Double(year).intValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "0";
    }
}
