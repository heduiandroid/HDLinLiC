package com.linli.consumer.utils;
import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.linli.consumer.base.ActivityCollector;
import com.linli.consumer.ui.main.SpringboardActivity;
import com.linli.consumer.ui.main.V2VoicePhotoBuyActivity;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;
import org.json.JSONObject;

public class UploadUtil {
    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10*1000;   //超时时间
    private static final String CHARSET = "utf-8"; //设置编码
    private static String voiceName;//上传成功后记录刚刚上传的语音ID

    /**
     * android上传文件到服务器（七牛服务器）
     * @param file  需要上传的文件
     * @param RequestURL  请求的rul
     * @return  返回响应的内容
     */
	public static String uploadFile(File file,String RequestURL, final Context context)
    {
        final String[] result = {null};
        File data = file;
//        String token = YZXIndexActivity.mytoken;
//        System.out.println("tokentokentoken:"+ token);
//        UploadOptions options = new UploadOptions(null,"audio/m4a",false,null,null);
        UploadManager uploadManager = new UploadManager();
        uploadManager.put(data, null, "", new UpCompletionHandler() {
            @Override
            public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
                //得到路径 将路径给服务器存数据库
                System.out.println(jsonObject);
                if (responseInfo.isOK()) {
                    System.out.println(jsonObject);
                    try {
                        result[0] = jsonObject.getString("key");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    voiceName = result[0];
                    gotoNextActivity(voiceName, context);//////////////////////////////////////////////////
                }else {
                    System.out.println("七牛返回失败");
//                    Toast.makeText(context,"服务器忙，请稍后再试",Toast.LENGTH_SHORT).show();
                }
            }
        },null);
//        String  BOUNDARY =  UUID.randomUUID().toString();  //边界标识   随机生成
//        String PREFIX = "--" , LINE_END = "\r\n";
//        String CONTENT_TYPE = "multipart/form-data";   //内容类型
//
//        try {
//            URL url = new URL(RequestURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(TIME_OUT);
//            conn.setConnectTimeout(TIME_OUT);
//            conn.setDoInput(true);  //����������
//            conn.setDoOutput(true); //���������
//            conn.setUseCaches(false);  //������ʹ�û���
//            conn.setRequestMethod("POST");  //����ʽ
//            conn.setRequestProperty("Charset", CHARSET);  //���ñ���
//            conn.setRequestProperty("connection", "keep-alive");
//            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
//
//            if(file!=null)
//            {
//                /**
//                 * 当文件不为空，把文件包装并且上传
//                 */
//                DataOutputStream dos = new DataOutputStream( conn.getOutputStream());
//                StringBuffer sb = new StringBuffer();
//                sb.append(PREFIX);
//                sb.append(BOUNDARY);
//                sb.append(LINE_END);
//                /**
//                 * 这里重点注意：
//                 * name里面的值为服务器端需要key   只有这个key 才可以得到对应的文件
//                 * filename是文件的名字，包含后缀名的   比如:abc.png
//                 */
//                System.out.println(file.getName());
//                sb.append("Content-Disposition: form-data; name=\"img\"; filename=\""+file.getName()+"\""+LINE_END);
//                sb.append("Content-Type: application/octet-stream; charset="+CHARSET+LINE_END);
//                sb.append(LINE_END);
//                dos.write(sb.toString().getBytes());
//                InputStream is = new FileInputStream(file);
//                byte[] bytes = new byte[1024];
//                int len = 0;
//                while((len=is.read(bytes))!=-1)
//                {
//                    dos.write(bytes, 0, len);
//                }
//                is.close();
//                dos.write(LINE_END.getBytes());
//                byte[] end_data = (PREFIX+BOUNDARY+PREFIX+LINE_END).getBytes();
//                dos.write(end_data);
//                dos.flush();
//                /**
//                 * 获取响应码  200=成功
//                 * 当响应成功，获取响应的流
//                 */
//                int res = conn.getResponseCode();
//                Log.e(TAG, "response code:"+res);
////                if(res==200)
////                {
//                    Log.e(TAG, "request success");
//                    InputStream input =  conn.getInputStream();
//                    StringBuffer sb1= new StringBuffer();
//                    int ss ;
//                    while((ss=input.read())!=-1)
//                    {
//                        sb1.append((char)ss);
//                    }
//                    result = sb1.toString();
//                    Log.e(TAG, "result : "+ result);
////                }
////                else{
////                    Log.e(TAG, "request error");
////                }
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("result[0]result[0]result[0]"+result[0]);
        return result[0];
    }
    private static void gotoNextActivity(String voiceName,Context context) {
        if (!isExist()){
            if (voiceName != null){
                System.out.println("voiceNamevoiceNamevoiceName:" + voiceName);
                Intent intent = new Intent();
                intent.putExtra("tag",2);
                intent.putExtra("voicename",voiceName);
                intent.setClass(context, V2VoicePhotoBuyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);//去到自己的录音信息确定是否发送发送生么样的需求的界面
            }else {
                System.out.println("文件名为空");
            }
        }else {
            Intent intent = new Intent();
            intent.putExtra("voicename",voiceName);
            intent.setClass(context, SpringboardActivity.class);//不要问我为什么跳这个界面，就是为了向自己的服务器提交一下数据，方便我之前的页面调用onresume刷新数据
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.i("test","正在跳转SpringboardActivity");
            context.startActivity(intent);
        }
    }
    public static boolean isExist(){
        for(int i = 0; i< ActivityCollector.activities.size(); i++){
            if(ActivityCollector.activities.get(i) instanceof V2VoicePhotoBuyActivity){
                return true;
            }
        }
        return false;
    }
}