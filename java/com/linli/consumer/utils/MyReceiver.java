package com.linli.consumer.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.linli.consumer.base.ActivityCollector;
import com.linli.consumer.ui.cybercafe.MainCafeActivity;
import com.linli.consumer.ui.main.MessageActivity;
import com.linli.consumer.ui.takecar.TakeCarWaitDriverAcceptActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JPush";

	@Override
	public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
		Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
		
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...
                        
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
        	Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
        	processCustomMessage(context, bundle);
        
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
        	
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
			Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
        	//打开自定义的Activity
			Integer type = 0;
			try {
				JSONObject extraMsg = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
				type = extraMsg.getInt("type");
				switch (type){
                    case 9:
                    case 10://去打车页面
                        //takecaractivity是否在栈中  如果没在栈中 则打开此activity
                        if (!isTakeCarActivityOn()){
                            Intent i = new Intent(context, TakeCarWaitDriverAcceptActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//
                            context.startActivity(i);
                        }
                        break;
					case 13:
						//messageactivity是否在栈中  如果没在栈中 则打开此activity
						if (!isMessageActivityOn()){
							Intent i = new Intent(context, MessageActivity.class);
							i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//
							context.startActivity(i);
						}
						break;
					default:
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

//			if (YZXIndexActivity.isForeground == true){
//
//			}else {
//				Intent i = new Intent(context, YZXIndexActivity.class);
//				i.putExtras(bundle);
//				i.putExtra("isNotification",true);
////        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				context.startActivity(i);
//			}


        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
        	boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
        	Log.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
        	Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
					Log.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next().toString();
						sb.append("\nkey:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
					Log.e(TAG, "Get message extra JSON error!");
				}

			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}
	
	//send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle) {
//		if (YZXIndexActivity.isForeground) {
			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
			Intent msgIntent = new Intent(MainCafeActivity.MESSAGE_RECEIVED_ACTION);
			msgIntent.putExtra(MainCafeActivity.KEY_MESSAGE, message);
			if (!ExampleUtil.isEmpty(extras)) {
				try {
					JSONObject extraJson = new JSONObject(extras);
					if (null != extraJson && extraJson.length() > 0) {
						msgIntent.putExtra(MainCafeActivity.KEY_EXTRAS, extras);
					}
				} catch (JSONException e) {

				}

			}
			context.sendBroadcast(msgIntent);
//		}
	}
	/**
	 * check messageactivity if is on
	 * @return
	 */
	private boolean isMessageActivityOn() {
		for(int i = 0; i < ActivityCollector.activities.size() ; i ++){
			if(ActivityCollector.activities.get(i) instanceof MessageActivity){
				return true;
			}
		}
		return false;
	}
    /**
     * check takecaractivity if is on
     * @return
     */
    private boolean isTakeCarActivityOn() {
        for(int i = 0; i < ActivityCollector.activities.size() ; i ++){
            if(ActivityCollector.activities.get(i) instanceof TakeCarWaitDriverAcceptActivity){
                return true;
            }
        }
        return false;
    }
}
