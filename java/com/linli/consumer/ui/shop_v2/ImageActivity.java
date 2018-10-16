package com.linli.consumer.ui.shop_v2;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.image.ImageInfo;
import com.linli.consumer.R;

import me.relex.photodraweeview.OnPhotoTapListener;
import me.relex.photodraweeview.PhotoDraweeView;

/**
 * Created by wds1993225 on 2016/5/15.
 */
public class ImageActivity extends Activity implements View.OnClickListener{

    private PhotoDraweeView imageView;
    private RelativeLayout layout;


    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);


        initView();
        initData();

    }
    private void initView(){
        imageView = (PhotoDraweeView)findViewById(R.id.image_pic);
        layout = (RelativeLayout)findViewById(R.id.image_ll);

        layout.setOnClickListener(this);


        imageView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                finish();
            }
        });

    }

    private void initData(){
        Intent intent = getIntent();
        url = intent.getStringExtra("imagePath");
        Uri uri = Uri.parse(url);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri).setAutoPlayAnimations(true)
                .setTapToRetryEnabled(true)
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(
                            String id,
                            ImageInfo imageInfo,
                            Animatable anim) {
                        if(anim!=null){
                            anim.start();
                        }
                        imageView.update(imageInfo.getWidth(),imageInfo.getHeight());
                    }
                }).build();

        imageView.setController(controller);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_ll:
                finish();
                break;
            default:
                break;
        }
    }

}
