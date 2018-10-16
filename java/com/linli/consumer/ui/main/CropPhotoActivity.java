package com.linli.consumer.ui.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.AddVoice;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.User;
import com.linli.consumer.myview.ClipImageLayout;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.ImageThumbnail;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CropPhotoActivity extends BaseActivity {
    private TextView tv_cancel;
    private TextView tv_confirm;
    private ClipImageLayout mClipImageLayout;
    private Bitmap bitMap;
    private String mytoken = null;
    private AppContext appContext;
    private User user;
    private City city;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_crop_photo;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        city = appContext.getCity();
        tv_cancel = findViewClick(R.id.tv_cancel);
        tv_confirm = findViewClick(R.id.tv_confirm);
        mClipImageLayout =  findView(R.id.id_clipImageLayout);
        String path = getIntent().getStringExtra("path");
        Log.i("path", path);
//        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/"+path);
//        mClipImageLayout.getZoomImageView()
//                    .setImageBitmap(bitmap);
        // 下面这两句是对图片按照一定的比例缩放，这样就可以完美地显示出来。
        try {
            Bitmap camorabitmap = BitmapFactory.decodeFile(path);
            int scale = ImageThumbnail.reckonThumbnail(camorabitmap.getWidth(), camorabitmap.getHeight(), 500, 600);
            bitMap = ImageThumbnail.PicZoom(camorabitmap, camorabitmap.getWidth() / scale, camorabitmap.getHeight() / scale);
            //由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
//        camorabitmap.recycle();
            mClipImageLayout.getZoomImageView()
                    .setImageBitmap(bitMap);
        }catch (Exception e){
            finish();
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_confirm:
                try {
                    bitMap = mClipImageLayout.clip();
                    showDialog();
                }catch (Exception e){
                    finish();
                    e.printStackTrace();
                }
                Toast.makeText(CropPhotoActivity.this,"正在发送需求",Toast.LENGTH_SHORT).show();
                tv_confirm.setOnClickListener(null);
                request_upload_token();

                break;
            default:
                break;
        }
    }
    //提取保存裁剪之后的图片数据
    private void setImageServerNeed(Bitmap bitmap) {
        if (bitmap != null) {
            Bitmap photo = bitmap;
//			Bitmap photo=MediaStore.Images.Media.getBitmap(intent);
            byte[] serverNeed = bitmapToBase64(photo);
            request_upload_picture(serverNeed, photo);
        }
    }
    //将裁剪后的bitmap转换成base64 准备上传到服务器
    private byte[] bitmapToBase64(Bitmap bitmap) {
        byte[] result=null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                result = baos.toByteArray();
//                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    private void request_upload_token() {
        IntrestBuyNet.getToken(null, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic generic) {
                if (generic.getStatus() == 1){
                    mytoken = generic.getData().toString();
                    if (mytoken != null){
                        setImageServerNeed(bitMap);
                    }else {
                        dismiss();
                        Toast.makeText(CropPhotoActivity.this,"网络繁忙，请稍后再试",Toast.LENGTH_SHORT).show();
                        CropPhotoActivity.this.finish();
                    }
                }else {
                    tv_confirm.setOnClickListener(CropPhotoActivity.this);
                    Toast.makeText(CropPhotoActivity.this,generic.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void request_upload_picture(byte[] serverNeed, Bitmap photo) {//存到七牛
        byte[] data = serverNeed;
        String key = null;
//                Common.IMAGE_FILE_NAME+imageName+".jpg";
        String token = mytoken;
        System.out.println("token" + token);
        UploadManager uploadManager = new UploadManager();
        uploadManager.put(data, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
                //得到返回图片名 给服务器存数据库
                if (responseInfo.isOK()) {
                    String name = null;
                    try {
                        name = jsonObject.getString("key");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (name != null) {
                        request_uploadvoice_to_myserver(name);
                    } else {
                        tv_confirm.setOnClickListener(CropPhotoActivity.this);
                        dismiss();
                        Toast.makeText(CropPhotoActivity.this, "网络繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    tv_confirm.setOnClickListener(CropPhotoActivity.this);
                    dismiss();
                    Toast.makeText(CropPhotoActivity.this, "网络繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                }
            }
        }, null);
    }
    private void request_uploadvoice_to_myserver(String name) {
        IntrestBuyNet.addUserVoice(1, user.getId(), name, new HandleSuccess<AddVoice>() {
            @Override
            public void success(AddVoice s) {
                if (s.getStatus() == 1){
                    request_submitsever_topushdata(s.getData().getId());
                }else {
                    tv_confirm.setOnClickListener(CropPhotoActivity.this);
                    dismiss();
                    Toast.makeText(CropPhotoActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                    System.out.println("语音id为空");
                }
            }
        });
    }

    private void request_submitsever_topushdata(long id) {
        IntrestBuyNet.jpush(10, 1, 3d, id, user.getId(), city.getLongitude(), city.getLatitude(),0, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1 || s.getStatus() == 7){
                    dismiss();
                    V2VoicePhotoBuyActivity.needToRefeshData = true;
                    Toast.makeText(CropPhotoActivity.this,"发送成功，请等待反馈信息",Toast.LENGTH_SHORT).show();
                    CropPhotoActivity.this.finish();
                }else{
                    tv_confirm.setOnClickListener(CropPhotoActivity.this);
                    dismiss();
                    Toast.makeText(CropPhotoActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
