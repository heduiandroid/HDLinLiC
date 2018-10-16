package com.linli.consumer.utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.text.format.DateFormat;


public class RecordManger {
    /**¼�����ļ�*/
	private File file; 
    /**androidý��¼����*/
	private MediaRecorder mr;
    /**����������*/
    private SoundAmplitudeListen soundAmplitudeListen;
    
    
    /**������ʱ���������*/
	private final Handler mHandler = new Handler();
	private Runnable mUpdateMicStatusTimer = new Runnable() {
		/**
		 * �ֱ��ļ��㹫ʽK=20lg(Vo/Vi) Vo��ǰ���ֵ Vi��׼ֵΪ600
		 */
		private int BASE = 600;
        private int RATIO=5;
    	private int postDelayed =200;
		public void run() {
			  // int vuSize = 10 * mMediaRecorder.getMaxAmplitude() / 32768;  
            int ratio =mr.getMaxAmplitude() / BASE;  
            int db = (int) (20 * Math.log10(Math.abs(ratio)));  
            int value=db / RATIO;
            if(value<0)value=0;
            if(soundAmplitudeListen!=null)
            	soundAmplitudeListen.amplitude(ratio, db, value);
			mHandler.postDelayed(mUpdateMicStatusTimer, postDelayed);

		}
	};

    /**����¼��������ļ�*/
	public void startRecordCreateFile() throws IOException {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			return;
		}

		file = new File("/sdcard/"
				+ "zl_"
				+ new DateFormat().format("yyyyMMdd_hhmmss",
						Calendar.getInstance(Locale.CHINA)) + ".amr");
		mr = new MediaRecorder(); // ����¼������
		mr.setAudioSource(MediaRecorder.AudioSource.DEFAULT);// ����˷�Դ����¼��
		mr.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);// ���������ʽ
		mr.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);// ���ñ����ʽ
		mr.setOutputFile(file.getAbsolutePath());// ��������ļ�

		// �����ļ�
		file.createNewFile();
		// ׼��¼��
		mr.prepare();

		// ��ʼ¼��
		mr.start();
			//�����������ʱ��
		mHandler.post(mUpdateMicStatusTimer);
	}
    /**ֹͣ¼��������¼���ļ�*/
	public File stopRecord() {

		if (mr != null) {
			try {
				mr.stop();
			}catch (IllegalStateException e){
				e.printStackTrace();
			}
			mr.release();
			mr = null;
			mHandler.removeCallbacks(mUpdateMicStatusTimer);
		}
		return file;

	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public MediaRecorder getMr() {
		return mr;
	}

	public void setMr(MediaRecorder mr) {
		this.mr = mr;
	}
	public SoundAmplitudeListen getSoundAmplitudeListen() {
		return soundAmplitudeListen;
	}

	public void setSoundAmplitudeListen(SoundAmplitudeListen soundAmplitudeListen) {
		this.soundAmplitudeListen = soundAmplitudeListen;
	}
   public interface SoundAmplitudeListen{
	   public void amplitude(int amplitude, int db, int value);
   }

}
