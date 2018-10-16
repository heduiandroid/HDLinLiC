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
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.Login;
import com.linli.consumer.common.Common;
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.myview.ClipImageLayout;
import com.linli.consumer.net.CafeNet;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.ImageThumbnail;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class  CropHeadPicActivity extends BaseActivity {
    private TextView tv_cancel;
    private TextView tv_confirm;
    private ClipImageLayout mClipImageLayout;
    private Bitmap bitMap;
    private String mytoken = null;
    private AppContext appContext;
    private User user;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_crop_head_pic;
    }

    @Override
    protected void initView() {
        tv_cancel = findViewClick(R.id.tv_cancel);
        tv_confirm = findViewClick(R.id.tv_confirm);
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        mClipImageLayout = findViewClick(R.id.id_clipImageLayout);
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
                request_upload_token();
                break;
            default:
                break;
        }
    }
    private void request_upload_token() {
        IntrestBuyNet.getToken(null, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    mytoken = s.getData().toString();
                    if (mytoken != null){
                        setImageServerNeed(bitMap);
                    }else {
                        dismiss();
                        Toast.makeText(CropHeadPicActivity.this, "网络繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                        CropHeadPicActivity.this.finish();
                    }
                }else {
                    dismiss();
                    Toast.makeText(CropHeadPicActivity.this, s.getMsg().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
//        RequestQueue requestQueue = VolleyTools.getInstance(this).getQueue();
//        String url = Common.getUploadToken();
////        + URLEncoder.encode(Common.IMAGE_FILE_NAME+imageName+".jpg");/////////////////////////////////
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                if (s!=null && s.length()>0){
//                    try {
//                        JSONObject jsonObject = new JSONObject(s);
//                        System.out.println(s);
//                        if (jsonObject.getInt("code") == 0){
//                            mytoken = jsonObject.getString("data");
//                            if (mytoken != null){
//                                setImageServerNeed(bitMap);
//                            }else {
//                                dismiss();
//                                Toast.makeText(CropHeadPicActivity.this, "网络繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
//                                CropHeadPicActivity.this.finish();
//                            }
//                        }else {
//                            System.out.println("名字为空");
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                System.out.println("CropHeadPicActivity," + volleyError.getLocalizedMessage());
//                Toast.makeText(CropHeadPicActivity.this, "网络繁忙,请重试", Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(request);
    }
    //提取保存裁剪之后的图片数据
    private void setImageServerNeed(Bitmap bitmap) {
        if (bitmap != null) {
            Bitmap photo = bitmap;
//			Bitmap photo=MediaStore.Images.Media.getBitmap(intent);
            byte[] serverNeed = bitmapToBase64(photo);
            request_upload_headportrait(serverNeed, photo);
        }
    }
    private void request_upload_headportrait(byte[] serverNeed, Bitmap photo) {////////////////////////
        byte[] data = serverNeed;
        String key = null;
//                Common.IMAGE_FILE_NAME+imageName+".jpg";
        String token = mytoken;
        System.out.println("token"+token);
        UploadManager uploadManager = new UploadManager();
        uploadManager.put(data, key, token, new UpCompletionHandler() {
            @Override
            public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
                //得到返回图片名 给服务器存数据库
                if (responseInfo.isOK()) {
                    Toast.makeText(CropHeadPicActivity.this, "正在上传", Toast.LENGTH_SHORT).show();
                    String name = null;
                    try {
                        name = jsonObject.getString("key");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (name!=null){
                        request_submit_imagename(name);
                    }else {
                        Toast.makeText(CropHeadPicActivity.this, "网络繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    finish();
                    Toast.makeText(CropHeadPicActivity.this, "网络繁忙，请稍后再试", Toast.LENGTH_SHORT).show();
                }
            }
        }, null);
    }
    private void request_submit_imagename(String name) {
        IntrestBuyNet.updateUseravatar(user.getId(),name,new HandleSuccess<Login>() {//是否缺少headpath nickname email phone等
            @Override
            public void success(Login s) {
                if (s.getResponseCode().equals(CafeNet.SUCCESS)){
                    user.setHeadPath(Common.downloadVoiceUrl+s.getResponseData().getPersonalAvatar());
                    dismiss();
                    //成功之后 记录返回路径重新给user赋值路径
                    Toast.makeText(CropHeadPicActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    CropHeadPicActivity.this.finish();
                }else {
                    showToast(s.getResponseMessage());
                }
            }
        });
//        RequestQueue requestQueue = VolleyTools.getInstance(this).getQueue();
//        JSONObject jsonParams = new JSONObject();
//        try {
//            jsonParams.put("id",user.getId());
//            jsonParams.put("headPath",name);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String url = Common.submitHeadPic() + URLEncoder.encode(jsonParams.toString());/////////////////////////////////
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                if (s!=null && s.length()>0){
//                    try {
//                        JSONObject jsonObject = new JSONObject(s);
//                        System.out.println("拍照返回信息"+s);
//                        if (jsonObject.getInt("code") == 0){
//                            jsonObject = jsonObject.getJSONObject("data");
//                            user.setHeadPath(Common.downloadVoiceUrl+jsonObject.getString("headpath"));
//                            dismiss();
//                            //成功之后 记录返回路径重新给user赋值路径
//                            Toast.makeText(CropHeadPicActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                            CropHeadPicActivity.this.finish();
//                        }else {
//                            System.out.println("id为空");
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                System.out.println("CropHeadPicActivity," + volleyError.getLocalizedMessage());
//                Toast.makeText(CropHeadPicActivity.this, "网络繁忙,请重试", Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(request);
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
}
