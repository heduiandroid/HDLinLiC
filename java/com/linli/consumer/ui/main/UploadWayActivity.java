package com.linli.consumer.ui.main;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.common.Common;
import com.linli.consumer.domain.User;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UploadWayActivity extends Activity implements View.OnClickListener {
    private LinearLayout ll_take_picture;
    private LinearLayout ll_local_picture;
    private String picPath = null;//图片路径
    private String imageName = null;
//    private String mytoken = null;
    private AppContext appContext;
    private User user;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_way);
        init();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
    }

    private void init() {
        ll_take_picture = (LinearLayout) findViewById(R.id.ll_take_picture);
        ll_local_picture = (LinearLayout) findViewById(R.id.ll_local_picture);
        findViewById(R.id.ll_all).setOnClickListener(this);
        ll_take_picture.setOnClickListener(this);
        ll_local_picture.setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }


    @Override
    public void onClick(View v) {
//        request_upload_token();
        switch (v.getId()){
            case R.id.ll_take_picture:
                try {
                    takeANewPicture();
                }catch (Exception e){
                    Toast.makeText(this,"未授予相关访问权限",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case R.id.ll_local_picture:
                try {
                    pickImageFromLocal();
                }catch (Exception e){
                    Toast.makeText(this,"未授予相关访问权限",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void takeANewPicture() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        imageName = df.format(calendar.getTime());
        preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("imageName", imageName);
        editor.commit();
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), Common.IMAGE_FILE_NAME + imageName + ".jpg")));
            startActivityForResult(intentFromCapture, Common.CODE_CAMERA_REQUEST);
        } else {
            Toast.makeText(getApplication(), "检测到您没有SD卡", Toast.LENGTH_SHORT).show();
        }
    }

    private void pickImageFromLocal() {
        try {
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setType("image/*");
            startActivityForResult(intent, Common.CODE_GALLERY_REQUEST);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            try {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, Common.CODE_GALLERY_REQUEST);
            } catch (Exception e2) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    //判断SD卡是否可用
    private boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 用户没有有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "没有选择照片", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (requestCode) {
            case Common.CODE_GALLERY_REQUEST:
                Uri uri = intent.getData();//获取到选择的图片的路径
                try {
                    String[] pojo={MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(uri,
                            pojo, null, null, null);
                    if (cursor!=null) {
                        ContentResolver cr=this.getContentResolver();
                        int colunm_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor.moveToFirst();
                        String path=cursor.getString(colunm_index);
                        if (path.endsWith("jpg") || path.endsWith("JPG") || path.endsWith("png") || path.endsWith("PNG") || path.endsWith("jpeg") || path.endsWith("JPEG")) {
                            picPath=path;
                        }else {
                            picPath=null;
                            Toast.makeText(UploadWayActivity.this, "不支持的照片格式", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        picPath=uri.getPath();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String mlocalPath = picPath;
                Intent mittp = new Intent(UploadWayActivity.this,CropHeadPicActivity.class);
                mittp.putExtra("path",mlocalPath);
                startActivity(mittp);
                UploadWayActivity.this.finish();
//                cropRawPhoto(intent.getData());
                break;
            case Common.CODE_CAMERA_REQUEST:
                if (hasSdcard()) {
                    preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
                    imageName = preferences.getString("imageName", null);
                    if (imageName != null){
                        String localPath = Environment.getExternalStorageDirectory()+"/"+Common.IMAGE_FILE_NAME+imageName+".jpg";
                        Intent ittp = new Intent(UploadWayActivity.this,CropHeadPicActivity.class);
                        ittp.putExtra("path",localPath);
                        startActivity(ittp);
                        UploadWayActivity.this.finish();
                    }else {
                        Toast.makeText(UploadWayActivity.this,"未能获取到照片，请重试",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplication(), "没有SD卡", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }
    //    private void request_upload_headportrait(byte[] serverNeed, Bitmap photo) {////////////////////////
//        byte[] data = serverNeed;
//        String key = null;
////                Common.IMAGE_FILE_NAME+imageName+".jpg";
//        String token = mytoken;
//        System.out.println("token"+token);
//        UploadManager uploadManager = new UploadManager();
//        uploadManager.put(data, key, token, new UpCompletionHandler() {
//            @Override
//            public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
//                //得到返回图片名 给服务器存数据库
//                if (responseInfo.isOK()) {
//                    Toast.makeText(UploadWayActivity.this, "正在上传", Toast.LENGTH_SHORT).show();
//                    String name = null;
//                    try {
//                        name = jsonObject.getString("key");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    if (name!=null){
//                        request_submit_imagename(name);
//                    }else {
//                        Toast.makeText(UploadWayActivity.this, "服务器忙，请稍后再试", Toast.LENGTH_SHORT).show();
//                    }
//                }else {
//                    finish();
//                    Toast.makeText(UploadWayActivity.this, "服务器忙，请稍后再试", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, null);
//    }


//    private void request_submit_imagename(String name) {
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
//                            //成功之后 记录返回路径重新给user赋值路径
//                            Toast.makeText(UploadWayActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                            UploadWayActivity.this.finish();
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
//                System.out.println("UploadWayActivity," + volleyError.getLocalizedMessage());
//                Toast.makeText(UploadWayActivity.this, "服务器忙,请重试", Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(request);
//    }

//    private void request_upload_token() {
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
//                System.out.println("UploadWayActivity," + volleyError.getLocalizedMessage());
//                Toast.makeText(UploadWayActivity.this, "服务器忙,请重试", Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(request);
//    }
    //裁剪原始的图片
//    private void cropRawPhoto(Uri uri) {
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        //设置裁剪
//        intent.putExtra("crop", true);
//        //aspectX,aspectY:宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        //outputX,outputY:裁剪图片宽高
//        intent.putExtra("outputX", Common.output_X);
//        intent.putExtra("outputY", Common.output_Y);
//        intent.putExtra("return-data", true);
//        startActivityForResult(intent, Common.CODE_RESULT_REQUEST);
//    }
    //    //提取保存裁剪之后的图片数据
//    private void setImageServerNeed(Intent intent) {
//        Bundle extras = intent.getExtras();
//        if (extras != null) {
//            Bitmap photo = extras.getParcelable("data");
////			Bitmap photo=MediaStore.Images.Media.getBitmap(intent);
//            byte[] serverNeed = bitmapToBase64(photo);
//            request_upload_headportrait(serverNeed, photo);
//
//        }
//    }
    //将裁剪后的bitmap转换成base64 准备上传到服务器
//    private byte[] bitmapToBase64(Bitmap bitmap) {
//        byte[] result=null;
//        ByteArrayOutputStream baos = null;
//        try {
//            if (bitmap != null) {
//                baos = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//
//                baos.flush();
//                baos.close();
//
//                result = baos.toByteArray();
////                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if (baos != null) {
//                    baos.flush();
//                    baos.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
}
