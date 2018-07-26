package com.hiworld.canbus.util;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.hiworld.canbus.activity.MainActivity;
import com.hiworld.mycar.t11.R;
import com.youzi.customer.connect.ConnConnect;
import com.youzi.customer.util.ConstUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CopyViewPagerAdapter extends PagerAdapter
{
	// 界面列表
    private List<View> views;
    private Activity activity;
    
    private List<String> lstFile = new ArrayList<String>();
    
    private TextView m_TxHwVer;
    private Button m_BtnUpdate;
    private TextView m_Textsoftver,m_TextServiceVer,
                               mTvVin, mTvCarModule;
    
    private String versionName;
    
    private static final int handler_message1 = 100;
    private static final int handler_message2 = 101;
    
    public CopyViewPagerAdapter(List<View> views, Activity activity) {
        this.views = views;
        this.activity = activity;
    }
    
    
    
	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(views.get(arg1));
    }



	@Override
	public void finishUpdate(View arg0)
	{
		
	}



	@Override
	public int getCount()
	{
		
		if (views != null) {
            return views.size();
        }
        return 0;
	}
	
	

	@Override
	public Object instantiateItem(View arg0, int arg1)
	{
		
		//Log.d("CopyrightActivity", "instantiateItem");
		((ViewPager) arg0).addView(views.get(arg1), 0);
		//Log.d("CopyrightActivity", "views.size()"+views.size());
		if (arg1 == views.size() - 1){//最后一页
			m_TxHwVer = (TextView)arg0.findViewById(R.id.text_hwver);
			m_BtnUpdate = (Button)arg0.findViewById(R.id.btn_hwupdate);
			m_Textsoftver = (TextView)arg0.findViewById(R.id.text_softver);
			m_TextServiceVer = (TextView)arg0.findViewById(R.id.text_severVer);
			mTvVin = (TextView)arg0.findViewById(R.id.tv_VIN);
			mTvCarModule = (TextView)arg0.findViewById(R.id.tv_CarModuel);
			getAppInfo();
			
			mHandler.sendEmptyMessage(handler_message1);
			

			for (int i = 0; i < MainActivity.iapDirStr.length; i++) {
            	if (fileIsExists(MainActivity.iapDirStr[i])) {
                	GetFiles(MainActivity.iapDirStr[i],"iap",false);
                	if (lstFile.size() > 0){
    	    			if (fileIsExists(lstFile.get(0))){
	    				mHandler.sendEmptyMessage(handler_message2);
	    			    }
                	}
                	break;
        		}
    		}
			
			m_BtnUpdate.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					
					writeBuf(new int[] {0x02,0xe1,0x00,0x00},4);
					
					MainActivity.bClickUpdate = true;
					Intent intent = new Intent(activity, MainActivity.class);
			        activity.startActivity(intent);
			        activity.finish();
				}
			});
			
			
		}
		return views.get(arg1);
	}



	private String getAppInfo() {
		
		try {
 			String pkName = activity.getPackageName();
 			versionName = activity.getPackageManager().getPackageInfo(
 					pkName, 0).versionName;
 			int versionCode = activity.getPackageManager()
 					.getPackageInfo(pkName, 0).versionCode;
 			return pkName + "   " + versionName + "  " + versionCode;
 		} catch (Exception e) {
 		}
 		return null;
	}



	private void GetFiles(String Path, String Extension, boolean IsIterative) {
		
		File[] files = new File(Path).listFiles();
		 
	    for (int i = 0; i < files.length; i++)
	    {
	        File f = files[i];
	        if (f.isFile())
	        {
	            if (f.getPath().substring(f.getPath().length() - Extension.length()).equals(Extension))  //判断扩展名
	                lstFile.add(f.getPath());
	 
	            if (!IsIterative)
	                break;
	        }
	        else if (f.isDirectory() && f.getPath().indexOf("/.") == -1)  //忽略点文件（隐藏文件/文件夹）
	            GetFiles(f.getPath(), Extension, IsIterative);
	    }
	}



	//判断文件是否存在 
	private boolean fileIsExists(String strFile)
	{
		
		try  
        {  
            File f = new File(strFile); 
            if(!f.exists())  
            {  
                    return false;  
            }  
  
        }  
        catch (Exception e)  
        {  
            return false;  
        }  
  
        return true;
	}




	protected void writeBuf(int[] ints, int size){
		
		try {
			ConnConnect.getInstance().getRemoteProxy().sendCmd(ConstUtil.APP2SERVER_OTHER, ints);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Intent intent = new Intent();
//		intent.setAction(ConstData.HIWORLD_INTERNAL_RECIVER);
//		intent.putExtra(ConstData.INTERNAL_RECIVER_KEY, buffer);
//		activity.sendBroadcast(intent);
	}



	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {
    }

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg)
		{
			
			super.handleMessage(msg);
			switch (msg.what)
			{
			case handler_message1:
				m_TxHwVer.setText(activity.getString(R.string.hwver_text)+CarInfo.getInstance().getVer());
				m_Textsoftver.setText(activity.getString(R.string.softver_text)+versionName);
				m_TextServiceVer.setText(activity.getString(R.string.seviceVer_text)+MainActivity.m_strService);
				mTvVin.setText(activity.getString(R.string.text_VIN)+CarPcInfo.getInstance().getM_strVIN());
				mTvCarModule.setText(activity.getString(R.string.text_Brand)+CarPcInfo.getInstance().getM_strBrand());
				break;
			case handler_message2:
				m_BtnUpdate.setVisibility(View.VISIBLE);
				break;
			default:
				break;
			}
		}
		
	};
	

}
