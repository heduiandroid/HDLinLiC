package com.linli.consumer.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.domain.UserNeedAndFeedback;
import com.linli.consumer.ui.main.ZoomableImageActivity;

import java.io.IOException;
import java.util.List;

import static com.linli.consumer.adapter.UserNeedAndFeedbackAdapter.mp;
/**
 * Created by hasee on 2016/12/27.
 */
public class RequermentsAdapter extends BaseAdapter {
    private List<UserNeedAndFeedback> list;
    private Context context;
    private static AnimationDrawable animationDrawable = null;
    private static HolderView lastHolder = null;
    public RequermentsAdapter(List<UserNeedAndFeedback> list, Context context) {
        this.list = list;
        this.context = context;
        mp = new MediaPlayer();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        HolderView holder = null;
        if (convertView == null){
            holder = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.requerments_item,null);
            holder.sdv_headimage = (SimpleDraweeView) convertView.findViewById(R.id.sdv_headimage);
            holder.ll_bg_voice = (RelativeLayout) convertView.findViewById(R.id.ll_bg_voice);
            holder.ll_bg_text = (RelativeLayout) convertView.findViewById(R.id.ll_bg_text);
            holder.sdv_requer_photo = (SimpleDraweeView) convertView.findViewById(R.id.sdv_requer_photo);
            holder.tv_requer_text = (TextView) convertView.findViewById(R.id.tv_requer_text);
            holder.iv_wave = (ImageView) convertView.findViewById(R.id.iv_wave);
            holder.tv_voicetime = (TextView) convertView.findViewById(R.id.tv_voicetime);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        if (list.get(position).getUserHead() != null){
            holder.sdv_headimage.setImageURI(list.get(position).getUserHead());
        }
        holder.sdv_requer_photo.setVisibility(View.GONE);
        holder.ll_bg_voice.setVisibility(View.GONE);
        holder.ll_bg_text.setVisibility(View.GONE);
        switch (list.get(position).getType()){
            case 0://语音
                holder.ll_bg_voice.setVisibility(View.VISIBLE);
                holder.tv_voicetime.setText(list.get(position).getTime());
                final HolderView nowHolder = holder;
                holder.ll_bg_voice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mp.isPlaying()){
                            resetDataView();
                        }
                        lastHolder = nowHolder;
                        mp.reset();
                        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                nowHolder.iv_wave.setImageResource(R.drawable.voice_anim);
                                animationDrawable = (AnimationDrawable) nowHolder.iv_wave.getDrawable();
                                animationDrawable.start();
                            }
                        });
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                nowHolder.ll_bg_voice.setClickable(true);
                                animationDrawable = (AnimationDrawable) nowHolder.iv_wave.getDrawable();
                                animationDrawable.stop();
                                nowHolder.iv_wave.setImageResource(R.mipmap.ic_wave3);
                            }
                        });
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                    mp.setDataSource(list.get(position).getDataPath());
                                    mp.prepare();
                                    mp.start();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                });
                break;
            case 1://图片
                holder.sdv_requer_photo.setVisibility(View.VISIBLE);
                if (list.get(position).getDataPath()!=null){
                    holder.sdv_requer_photo.setImageURI(Uri.parse(list.get(position).getDataPath()));
                }
                holder.sdv_requer_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context,ZoomableImageActivity.class).putExtra("url",list.get(position).getDataPath()));
                    }
                });
                break;
            case 2://文字
                holder.ll_bg_text.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams lp = holder.tv_requer_text.getLayoutParams();
                String text = list.get(position).getmText();
//                int length = text.getBytes().length;
//                if (length<20){
//                    lp.width = 120;
//                }else if (length>=20 && length<=30){
//                    lp.width = 180;
//                }else {
//                    lp.width = 250;
//                }
//                lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//                holder.tv_requer_text.setLayoutParams(lp);
                holder.tv_requer_text.setText(text);
                break;
            default:
                break;
        }
        return convertView;
    }
    public static void resetDataView() {
        mp.stop();
        if (lastHolder != null){
            animationDrawable = (AnimationDrawable) lastHolder.iv_wave.getDrawable();
            animationDrawable.stop();
            lastHolder.iv_wave.setImageResource(R.mipmap.ic_wave3);
        }
    }
    public class HolderView{
        SimpleDraweeView sdv_headimage;
        SimpleDraweeView sdv_requer_photo;
        RelativeLayout ll_bg_text;//包含文字的容器，是否可控制文字框大小
        TextView tv_requer_text;
        RelativeLayout ll_bg_voice;//点击播放语音
        ImageView iv_wave;//播放时声波动画载体
        TextView tv_voicetime;//显示语音时长
    }
}
