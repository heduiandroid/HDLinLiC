package com.linli.consumer.utils;

import java.io.File;
import java.util.Calendar;
import java.util.Locale;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
import android.text.format.DateFormat;

public class TalkNetManager {
	/** ¼�������� */
	private RecordManger recordManger = new RecordManger();
	/** �����ļ���������ַ */
	private String downloadFileServerUrl;
	/** �����ļ���������ַ */
	private String uploadFileServerUrl;

	private OnStateListener uploadFileStateListener;
	private OnStateListener downloadFileFileStateListener;
	private Context context;

	/** ����¼�������������ϴ� */
	public void startRecord() {
		try {
			recordManger.startRecordCreateFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** ֹͣ¼�� */
	public File stopRecord() {
		try {
			return recordManger.stopRecord();// ֹͣ¼��
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/** ֹͣ¼�����ϴ� */
	public File stopRecordAndUpload(Context context) {
		this.context = context;
		try {
			File file = recordManger.stopRecord();// ֹͣ¼��
			if (file == null || !file.exists() || file.length() == 0) {
				if (uploadFileStateListener != null)
					uploadFileStateListener.onState(-1, "文件不存在或已经损坏");
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		new UpLoadecordFile().execute();// �����첽����
		return recordManger.getFile();
	}

	/** �첽����-¼���ϴ� */
	public class UpLoadecordFile extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... parameters) {
			// TODO Auto-generated method stub11
			return UploadUtil.uploadFile(recordManger.getFile(),
					uploadFileServerUrl,context);

		}

		@Override
		protected void onPostExecute(String result) {
//			if (result == null) {
//				if (uploadFileStateListener != null)
//					uploadFileStateListener.onState(-2, "上传文件失败");
//				return;
//			}
//
//			if (result != null)
				if (uploadFileStateListener != null)
					uploadFileStateListener.onState(0, "上传文件成功");
		}

	}

	/** ���غ󲥷� */
	public void downloadFileAndPlay(String uploadFilename) {
		new DownloadRecordFile().execute(uploadFilename);
	}

	/** �첽����-���غ󲥷� */
	public class DownloadRecordFile extends AsyncTask<String, Integer, File> {

		@Override
		protected File doInBackground(String... parameters) {
			// TODO Auto-generated method stub11
			try {
				String filename = new DateFormat().format("yyyyMMdd_HHmmss",
						Calendar.getInstance(Locale.CHINA)) + ".amr";
				return FileHelper.DownloadFromUrlToData(downloadFileServerUrl
						+ parameters[0], filename, context);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		}

		@Override
		protected void onPostExecute(File result) {

//			if (result == null || !result.exists() || result.length() == 0) {
//				if (downloadFileFileStateListener != null) {
//					downloadFileFileStateListener.onState(-1, "下载文件失败");
//					return;
//				}
//			}
			MediaPlayer mediaPlayer = new MediaPlayer();
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mediaPlayer) {
					// TODO Auto-generated method stub
					mediaPlayer.release();
				}
			});
			try {
				mediaPlayer.setDataSource(result.getPath());
				mediaPlayer.prepare();
				mediaPlayer.start();
				if (downloadFileFileStateListener != null) {
					downloadFileFileStateListener.onState(0, "成功");
					return;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public RecordManger getRecordManger() {
		return recordManger;
	}

	public void setRecordManger(RecordManger recordManger) {
		this.recordManger = recordManger;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public void setDownloadFileServerUrl(String downloadFileServerUrl) {
		this.downloadFileServerUrl = downloadFileServerUrl;
	}

	public String getUploadFileServerUrl() {
		return uploadFileServerUrl;
	}

	public void setUploadFileServerUrl(String uploadFileServerUrl) {
		this.uploadFileServerUrl = uploadFileServerUrl;
	}

	public OnStateListener getUploadFileStateListener() {
		return uploadFileStateListener;
	}

	public void setUploadFileStateListener(
			OnStateListener uploadFileStateListener) {
		this.uploadFileStateListener = uploadFileStateListener;
	}

	public OnStateListener getDownloadFileFileStateListener() {
		return downloadFileFileStateListener;
	}

	public void setDownloadFileFileStateListener(
			OnStateListener downloadFileFileStateListener) {
		this.downloadFileFileStateListener = downloadFileFileStateListener;
	}

	public String getDownloadFileServerUrl() {
		return downloadFileServerUrl;
	}
}
