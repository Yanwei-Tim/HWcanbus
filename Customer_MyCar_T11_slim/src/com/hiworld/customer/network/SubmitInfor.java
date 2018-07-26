package com.hiworld.customer.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.hiworld.canbus.util.UtilityClass;

public class SubmitInfor extends AsyncTask<String, Integer, String>{

//	private SharedPreferences preferences;
	private Context context;
	public SubmitInfor(Context context){
		super();
		this.context = context;
//		preferences = context.getSharedPreferences("BlueService", Context.MODE_PRIVATE);
	}
	
	@Override
	protected void onPreExecute() {
		
		super.onPreExecute();
	}
	
	@Override
	protected String doInBackground(String... params) {
		
		URL url;
		try {
			url = new URL(params[0]);
			System.out.println("=========插入Bluetooth+URLCaoInfor"+params[0]);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			if (connection.getResponseCode() == 200) {
				InputStream is = connection.getInputStream();
				byte[] buffer = new byte[512];
				int len = 0 ;
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				while((len = is.read(buffer)) != -1 ){
					bos.write(buffer, 0, len);
				}
				is.close();
				String result = bos.toString();
				if (!TextUtils.isEmpty(result)) {
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
	protected void onPostExecute(String result) {
		
		super.onPostExecute(result);
		System.out.println("=====定位返回结果"+result);
		if ("插入成功".equals(result)) {
//			System.out.println("=======插入数据成功");
//			preferences.edit().putBoolean("sendInfo", false).commit();
			UtilityClass.setPrefrenceBoolean(context, "sendInfo", false);
		}
	}
	
}
