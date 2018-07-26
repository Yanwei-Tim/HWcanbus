package com.hiworld.canbus.util;

import java.util.List;

import com.hiworld.canbus.activity.MainActivity;
import com.hiworld.mycar.t11.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class ViewPagerAdapter extends PagerAdapter{

	// 界面列表
    private List<View> views;
    private Activity activity;

//    private static final String SHAREDPREFERENCES_NAME = "first_pref";

    public ViewPagerAdapter(List<View> views, Activity activity) {
        this.views = views;
        this.activity = activity;
    }

 // 销毁arg1位置的界面
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(views.get(arg1));
    }

    @Override
    public void finishUpdate(View arg0) {
    }

    // 获得当前界面数
    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }

    // 初始化arg1位置的界面
    @Override
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(views.get(arg1), 0);
        if (arg1 == views.size() - 1) {
        	Button m_BtnGoHome = (Button)arg0.findViewById(R.id.btn_help4_home);
        	//final CheckBox m_ChkHelp = (CheckBox)arg0.findViewById(R.id.check_help);
        	SharedPreferences preferences = activity.getSharedPreferences(
            		ConstData.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
            boolean isServer = preferences.getBoolean(ConstData.ISGUIDE, false);
            //m_ChkHelp.setChecked(isServer);
            if (isServer)
			{
            	//m_ChkHelp.setVisibility(View.GONE);
            	m_BtnGoHome.setVisibility(View.GONE);
			}
        	m_BtnGoHome.setOnClickListener(new OnClickListener(){
				
				@Override
				public void onClick(View v)
				{
					
					goHome();
				}
			});
        }

        return views.get(arg1);
    }



    protected void goHome(){
		
    	SharedPreferences preferences = activity.getSharedPreferences(
                ConstData.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putBoolean(ConstData.ISGUIDE, true);
        editor.commit();
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
	}



    // 判断是否由对象生成界面
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

}
