package com.linli.consumer.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.linli.consumer.R;

public class TimeCountGetCode extends CountDownTimer {
	private TextView tvGetCode;
	public TimeCountGetCode(long millisInFuture, long countDownInterval,
							TextView tvGetCode) {
		super(millisInFuture, countDownInterval);
		this.tvGetCode = tvGetCode;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		tvGetCode.setBackgroundResource(R.color.white);
		tvGetCode.setClickable(false);
		tvGetCode.setText(millisUntilFinished / 1000 + " 秒后重发");
	}

	@Override
	public void onFinish() {
		tvGetCode.setText("重发验证码");
		tvGetCode.setClickable(true);
		tvGetCode.setBackgroundResource(R.drawable.btn_main_header);
	}

}
