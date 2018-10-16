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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.domain.UserNeedAndFeedback;
import com.linli.consumer.ui.main.FeedbackDetailActivity;
import com.linli.consumer.ui.main.ZoomableImageActivity;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.widget.HorizontalListView;

import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2016/5/5.
 */
public class UserNeedAndFeedbackAdapter extends BaseAdapter {
    private List<UserNeedAndFeedback> list;
    private Context context;
    private FeedBacksAdapter adapter;
    private static AnimationDrawable animationDrawable = null;
    public static MediaPlayer mp = null;
    private static HolderView lastHolder = null;

    public UserNeedAndFeedbackAdapter(List<UserNeedAndFeedback> list, Context context) {
        this.list = list;
        this.context = context;
        mp = new MediaPlayer();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HolderView holder = null;
        if (convertView == null){
            holder = new HolderView();
            convertView = LayoutInflater.from(context).inflate(R.layout.userneed_feedback_item,null);
            holder.niv_user_head = (SimpleDraweeView) convertView.findViewById(R.id.niv_user_head);
            holder.ll_bg_voice = (RelativeLayout) convertView.findViewById(R.id.ll_bg_voice);
            holder.ll_bg_text = (RelativeLayout) convertView.findViewById(R.id.ll_bg_text);
            holder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
            holder.iv_wave = (ImageView) convertView.findViewById(R.id.iv_wave);
            holder.niv_userneed_pic = (SimpleDraweeView) convertView.findViewById(R.id.niv_userneed_pic);
            holder.tv_create_time = (TextView) convertView.findViewById(R.id.tv_create_time);
            holder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
            holder.iv_invain_spite = (ImageView) convertView.findViewById(R.id.iv_invain_spite);
            holder.gv_feedbacks = (HorizontalListView) convertView.findViewById(R.id.gv_feedbacks);
            holder.tv_voicetime = (TextView) convertView.findViewById(R.id.tv_voicetime);
            convertView.setTag(holder);
        }else {
            holder = (HolderView) convertView.getTag();
        }
        Date releaseTime = new Date(list.get(position).getCreateTime());
        holder.tv_create_time.setText(CommonUtil.getFriendlytime(releaseTime)+"发布");
        if (list.get(position).getBackProducts() != null && list.get(position).getBackProducts().size() > 0){
            holder.tv_status.setText("已反馈\n"+list.get(position).getBackProducts().size());
            holder.gv_feedbacks.setVisibility(View.VISIBLE);
            adapter = new FeedBacksAdapter(list.get(position).getBackProducts(),context);
            holder.gv_feedbacks.setAdapter(adapter);
        }else {
            holder.tv_status.setText("进行中");
            holder.gv_feedbacks.setVisibility(View.GONE);
        }
        holder.gv_feedbacks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Intent intent = new Intent();
                intent.putExtra("voiceid",list.get(position).getId());
                intent.putExtra("shopid",list.get(position).getBackProducts().get(pos).getId());
                intent.putExtra("shopuserid",list.get(position).getBackProducts().get(pos).getShopUserId());
                intent.putExtra("areacode",list.get(position).getBackProducts().get(pos).getAreacode());
                intent.putExtra("shopname",list.get(position).getBackProducts().get(pos).getShopName());
                intent.putExtra("shoplogo",list.get(position).getBackProducts().get(pos).getLogoPath());
                intent.putExtra("shopphone",list.get(position).getBackProducts().get(pos).getShopPhone());
                intent.putExtra("category",list.get(position).getBackProducts().get(pos).getCategory());
                intent.putExtra("level",list.get(position).getBackProducts().get(pos).getLevel());
                intent.putExtra("regionid",list.get(position).getBackProducts().get(pos).getRegionId());
                intent.putExtra("address",list.get(position).getBackProducts().get(pos).getShopAddr());
                intent.setClass(context, FeedbackDetailActivity.class);
                context.startActivity(intent);
            }
        });
        switch (list.get(position).getType()){
            case 0:
                holder.ll_bg_text.setVisibility(View.GONE);
                holder.niv_userneed_pic.setVisibility(View.GONE);
                holder.ll_bg_voice.setVisibility(View.VISIBLE);
                holder.tv_voicetime.setText(list.get(position).getTime());
                final HolderView nowHolder = holder;
                holder.ll_bg_voice.setOnClickListener(new View.OnClickListener() {//114821363601151
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
                                nowHolder.iv_wave.setImageResource(R.drawable.voice_anim_white);
                                animationDrawable = (AnimationDrawable) nowHolder.iv_wave.getDrawable();
                                animationDrawable.start();

                            }
                        });
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                nowHolder.iv_wave.setImageResource(R.drawable.voice_anim_white);
                                animationDrawable = (AnimationDrawable) nowHolder.iv_wave.getDrawable();
                                animationDrawable.stop();
                                nowHolder.iv_wave.setImageResource(R.mipmap.iv_wave3_white);
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
            case 1:
                holder.ll_bg_text.setVisibility(View.GONE);
                holder.ll_bg_voice.setVisibility(View.GONE);
                holder.niv_userneed_pic.setVisibility(View.VISIBLE);
                if (list.get(position).getDataPath()!=null){
                    holder.niv_userneed_pic.setImageURI(Uri.parse(list.get(position).getDataPath()));
                }
                holder.niv_userneed_pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context,ZoomableImageActivity.class).putExtra("url",list.get(position).getDataPath()));
                    }
                });
                break;
            case 2:
                holder.ll_bg_voice.setVisibility(View.GONE);
                holder.niv_userneed_pic.setVisibility(View.GONE);
                holder.ll_bg_text.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams lp = holder.tv_text.getLayoutParams();
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
//                holder.tv_text.setLayoutParams(lp);
                holder.tv_text.setText(list.get(position).getmText());
                break;
            default:
                break;
        }
        if (list.get(position).getUserHead()!=null){
            holder.niv_user_head.setImageURI(Uri.parse(list.get(position).getUserHead()));
        }

        return convertView;
    }

    public static void resetDataView() {
        mp.stop();
        if (lastHolder != null){
            lastHolder.iv_wave.setImageResource(R.drawable.voice_anim_white);
            animationDrawable = (AnimationDrawable) lastHolder.iv_wave.getDrawable();
            animationDrawable.stop();
            lastHolder.iv_wave.setImageResource(R.mipmap.iv_wave3_white);
        }
    }

    public class HolderView{
        SimpleDraweeView niv_user_head;
        RelativeLayout ll_bg_voice;
        RelativeLayout ll_bg_text;
        TextView tv_text;
        ImageView iv_wave;
        SimpleDraweeView niv_userneed_pic;
        TextView tv_create_time;
        TextView tv_status;
        ImageView iv_invain_spite;
        HorizontalListView gv_feedbacks;
        TextView tv_voicetime;
    }
}
