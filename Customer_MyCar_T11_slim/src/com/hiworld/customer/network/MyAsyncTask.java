package com.hiworld.customer.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;

public class MyAsyncTask extends AsyncTask<String, Integer, String>{

	private Context mContext;
	private Handler mHandler;
	
	public MyAsyncTask(Context context) {
		super();
		this.mContext = context;
	}
	
	public void setResultHandler(Handler mHandler){
		this.mHandler = mHandler;
	}
	
	@Override
	protected void onPreExecute() {
		
		super.onPreExecute();
	}
	
	private static Handler unBindHandler;;
	public static void setUnBindHandler(Handler mHandler){
		unBindHandler = mHandler;
	}
	
	//private static Handler connectHandler;
//	public static void setConnectHandler(Handler mHandler){
//		connectHandler = mHandler;
//	}
	
	@Override
	protected String doInBackground(String... params) {
		
		try {
			URL url = new URL(params[0]);
//			System.out.println("=========Bluetooth+URL"+params[0]);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			if (connection.getResponseCode() == 200) {
				//System.out.println("=======请求访问成功");
				InputStream is = connection.getInputStream();
				byte[] buffer = new byte[512];
				int len = 0 ;
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				while((len = is.read(buffer)) != -1 ){
					bos.write(buffer, 0, len);
				}
				is.close();
				String result = bos.toString();
				if (result != null) {
//					System.out.println("========Bluetooth+result = "+result);
					return result;
				}else {
					return null;
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	 @Override
     protected void onProgressUpdate(Integer... values){
         super.onProgressUpdate(values);
     }
	 
	@Override
	protected void onPostExecute(String result) {
		
		//super.onPostExecute(result);
		System.out.println("========请求返回结果 result = "+result);
		if (result == null) {
			return;
		}
		//绑定界面
//		if (mContext instanceof BondActivity) {
//			//System.out.println(" result.length() =="+ result.length());
//			if (!TextUtils.isEmpty(result) && result.length() == 32) {
//				//System.out.println(" result1 =="+ result);
//				//记录下MCU
//				VissCmdParse.getInstance().sendBlueAction(BlueUtilData.ACTION_BLUETOOTH_MCU, result);
//				if (mHandler != null) {
//					mHandler.sendEmptyMessage(100);
//				}
//			}else{
//				//System.out.println(" result2 =="+ result);
//				if (mHandler != null) {
//					mHandler.obtainMessage(101, result).sendToTarget();
//				}
//				
//			}
//		}else if (mContext instanceof MyDeviceActivity) {
//			if (!TextUtils.isEmpty(result) && "解绑成功".equals(result)) {
//				if (unBindHandler != null) {
//					unBindHandler.sendEmptyMessage(100);
//				}
//				VissCmdParse.getInstance().sendBlueAction(BlueUtilData.ACTION_BLUETOOTH_BOUND, BlueUtilData.BLUE_UNBOUND);
//				VissCmdParse.getInstance().sendBlueAction(BlueUtilData.ACTION_BLUETOOTH_CONTROL, BlueUtilData.BLUE_DISCONNECT);
//			}
//		}
	}
}
