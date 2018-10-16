package com.linli.consumer.utils;

import com.easimote.sdk.beacon;
import com.easimote.sdk.basicObjType.Hashcode;
import com.easimote.sdk.util.AESMath;
import com.easimote.sdk.util.utils.BeaconParserInteface;

import android.bluetooth.BluetoothDevice;
import android.util.Log;

public class BeaconParser implements BeaconParserInteface {
	private static final String TAG = "wifi_parser";
	/**
	 * beacon parser
	 */
	@Override
	public beacon beaconParser(BluetoothDevice device, int rssi, byte scanRecord[], boolean isEncrypted) {
		//byte[]转化为String后每个字节代表两位
		
		final int CharOfByte = 2;
				//16进制
				final int radix = 16;
				//转为String后包中各个字段的终止位置
				int headerEnd = 5 * CharOfByte;
				int companyIdBegin = headerEnd ;
				int companyIdEnd = 2 * CharOfByte + headerEnd;
				int typeEnd = 1 * CharOfByte + companyIdEnd;
				int companyId2End = 2 * CharOfByte + typeEnd;
				
				//wifi字段字节流中的起始位置及长度
				int companyIDByteStart = 5;
				int comIDlen = 2;
				int companyIDByte2Start = 8;
				int comID2len = 2;
				int wifiSsidByteStart = 10;
				int wifiPasswordByteStart = 22;
				boolean isnew = true;
				
				//临时数组，存储四个变量的值
				byte[] wifiInfoByte = new byte[25];
				int gindx = 0;
				for(int i=companyIDByteStart;i<companyIDByteStart+comIDlen;i++){
					wifiInfoByte[gindx] = scanRecord[i];
					gindx++;
				}
				for(int i=companyIDByte2Start;i<companyIDByte2Start+comID2len;i++){
					wifiInfoByte[gindx] = scanRecord[i];
					gindx++;
				}
				for(int i = wifiSsidByteStart;i<wifiSsidByteStart+12;i++){
					wifiInfoByte[gindx] = scanRecord[i];
					gindx++;
				}
				for(int i = wifiPasswordByteStart;i<wifiPasswordByteStart+9;i++){
					wifiInfoByte[gindx] = scanRecord[i];
					gindx++;
				}
				
//				
				String scanRecordAsHex = Hashcode.fromBytes(scanRecord).toString();
				int wifiSsidLen = Integer.parseInt(scanRecordAsHex.substring(companyIdBegin, companyIdBegin+2),radix);
				Log.e(TAG,""+wifiSsidLen);
				if(wifiSsidLen>24||wifiSsidLen == 0){
					isnew = false;
				}
				
				int wifiPasswordLen; 
				if(isnew){
					wifiPasswordLen = 24-wifiSsidLen;
				}else{
					wifiSsidLen = 12;
					wifiPasswordLen = 9;
				}
				//需要加解密字段位置及长度  type到minor字段 以及password字段
				int typeByteStart = 7;
				int typeToMinorLen = 3;
				//Log.e("scanRecord",new String(scanRecord));
				
			
				String companyId = null;
				String companyId2 = null;
				
				int type;
				String wifiSsid = null;
				String wifiPassword = null;
				if (scanRecord.length > 9) {
					if (!isEncrypted) 
					{
						if(isnew){
							type = Integer.parseInt(scanRecordAsHex.substring(companyIdEnd, typeEnd), radix);
							wifiSsid = wifiSsidParse(wifiInfoByte, 1, wifiSsidLen);
							wifiPassword = wifiPasswordParse(wifiInfoByte, wifiSsidLen+1, wifiPasswordLen);
							
							// return beacon();
						}else{
							type = Integer.parseInt(scanRecordAsHex.substring(companyIdEnd, typeEnd), radix);
							companyId2 = scanRecordAsHex.substring(typeEnd, companyId2End); 
							wifiPassword = wifiPasswordParsepre(scanRecord, wifiPasswordByteStart, wifiPasswordLen);
							companyId = scanRecordAsHex.substring(headerEnd, companyIdEnd);
							wifiSsid = wifiSsidParsepre(scanRecord, wifiSsidByteStart, wifiSsidLen);
						}
						
					} else {
						/*
						 * 加密数据包含16个字节，6个字段
						 * type字段1字节
						 * measuredPower字段1字节
						 * major字段2字节
						 * minor字段2字节
						 * password字段10字节
						 * 前6个字段为scanRecord中的第8到第13字节，后10个字节为第22到第31字节
						 */
						String key = "0123456789abcdef";
						
						byte[] encryptedBytes = new byte[typeToMinorLen + wifiPasswordLen];
						int j = 0;
						for (int i = 0; i < typeToMinorLen; i++) {
							encryptedBytes[j++] = scanRecord[i + typeByteStart];
						}
						
						for (int i = 0; i < wifiPasswordLen; i++) {
							encryptedBytes[j++] = scanRecord[i + wifiPasswordByteStart];
						}

						byte[] DecryptBytes = AESMath.decrypt(encryptedBytes, key);
						String tmp = Hashcode.fromBytes(DecryptBytes).toString();

						type = Integer.parseInt(tmp.substring(0, typeEnd - companyIdEnd), radix);
						wifiPassword = wifiPasswordParse(DecryptBytes, typeToMinorLen, wifiPasswordLen);
					}
					Log.e("info",wifiSsid+" "+wifiPassword);
					return new beacon(companyId, type, companyId2, 0, 0, 0, wifiSsid, wifiPassword, rssi);
				}
				return null;
	}
	
	/**
	 * 从字节流中解析出wifi ssid
	 * @param scanRecord
	 * @param start
	 * @param len
	 * @return
	 */
	private static String wifiSsidParse(byte[] scanRecord, int start, int len)
	{	
		int i = start;
		String wifiSsid = "";
//		while(i < start + len && scanRecord[i] != 0x17)
//		{
//			wifiSsid += (char)scanRecord[i];
//			i++;
//		}
		// Log.e("ssid len", ""+len);
		byte[] ssidinfo = new byte[len];
		for(i=start;i<start+len;i++){
			if(scanRecord[i] == 0x17)break;
			ssidinfo[i-start] = scanRecord[i];
		}
		wifiSsid = new String(ssidinfo);
		// Log.e("ssid",wifiSsid);
		
		return wifiSsid;
	}

	/**
	 * 从字节流中解析出wifi密码
	 * @param scanRecord
	 * @param start
	 * @param len
	 * @return
	 */
	private static String wifiPasswordParse(byte[] scanRecord, int start, int len){
		//密码长度为10个字节
		int i = start;
		String wifiPassword = "";
//		while(i < start + len && scanRecord[i] != 0x17){
//			wifiPassword += (char)scanRecord[i];
//			i++;
//		}
		
		byte[] pwinfo = new byte[len];
		for(i=start;i<start+len-1;i++){
			// Log.e("info",""+scanRecord[i]);
			if(scanRecord[i]== 0x17)break;
			pwinfo[i-start] = scanRecord[i];
		}
		wifiPassword = new String(pwinfo);
		// Log.e("wifipwlen", ""+(i-start));
		wifiPassword = wifiPassword.substring(0, i-start);
		// Log.e("password",wifiPassword);
		return wifiPassword;
	}
	
	/**
	 * 从字节流中解析出wifi ssid：定长SSID
	 * @param scanRecord
	 * @param start
	 * @param len
	 * @return
	 */
	private static String wifiSsidParsepre(byte[] scanRecord, int start, int len)
	{	
		int i = start;
		String wifiSsid = "";
		while(i < start + len && scanRecord[i] != 0x17)
		{
			wifiSsid += (char)scanRecord[i];
			i++;
		}
		
		return wifiSsid;
	}

	/**
	 * 从字节流中解析出wifi密码：定长密码
	 * @param scanRecord
	 * @param start
	 * @param len
	 * @return
	 */
	private static String wifiPasswordParsepre(byte[] scanRecord, int start, int len){
		//密码长度为10个字节
		int i = start;
		String wifiPassword = "";
		while(i < start + len && scanRecord[i] != 0x17){
			wifiPassword += (char)scanRecord[i];
			i++;
		}
		
		return wifiPassword;
	}
	
}
