package com.hiworld.canbus.fragment;


import java.util.ArrayList;

import com.hiworld.canbus.parse.FragmentParseData;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.util.myAdapter;
import com.hiworld.mycar.t11.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

@SuppressLint("NewApi")
public class CarInfoFragment extends Fragment{

	private Activity activity;
	private ListView m_List;
	private ArrayList<String> mListTitle = new ArrayList<String>();
	private ArrayList<String> mListStr = new ArrayList<String>();
	private myAdapter m_myAdpater;
	
	private View mContentView; 
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		this.activity = activity;
		if (mHandler != null) {
			FragmentParseData.getInstance().setCarInfoHandler(mHandler);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if(mContentView == null){
			mContentView = inflater.inflate(R.layout.fragment_carinfo, container,false);
			initList(mContentView);
			SendCarPcInfo(CarPcInfo.getInstance());
		}else{
			 ViewGroup parent = (ViewGroup) mContentView.getParent();  
	            if (parent != null)  {  
	                parent.removeView(mContentView);  
	            }  
		}
		return mContentView;
	}


	private void SendCarPcInfo(CarPcInfo instance) {
		
		mHandler.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, instance).sendToTarget();
	}

	private void initList(View view) {
		
		m_List = (ListView)view.findViewById(R.id.list);
		m_myAdpater = new myAdapter(activity, mListTitle,mListStr);
		m_List.setAdapter(m_myAdpater);
		m_List.setClickable(false);
	}
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case ConstData.HANDLER_MESSAGE_CARPC:
				CarPcInfo carPcInfo = (CarPcInfo) msg.obj;
				if (carPcInfo != null) {
					SetCarPcInfo(carPcInfo);
				}
				break;
			default:
				break;
			}
		}
		
	};

	protected void SetCarPcInfo(CarPcInfo carInfo) {
		
		mListTitle.clear();
		mListStr.clear();
		
		//品牌
		if (carInfo.getM_iBrandAble() == 1) {
			mListTitle.add(activity.getString(R.string.text_Brand));
			mListStr.add(carInfo.getM_strBrand());
		}
		//车系
		if (carInfo.getM_iModelAble() == 1) {
			mListTitle.add(activity.getString(R.string.text_Model));
			mListStr.add(carInfo.getM_strModel());
		}
		//制造日期
		if (carInfo.getM_iMadeTimeAble() == 1) {
			mListTitle.add(activity.getString(R.string.text_Madetime));
			mListStr.add(carInfo.getM_strMadeTime());
		}
		//VIN有效
		if (carInfo.getM_iVINAble() == 1) {
			mListTitle.add(activity.getString(R.string.text_VIN));
			mListStr.add(carInfo.getM_strVIN());
		}
//		//总里程
//		if (carInfo.getM_iTotalMileageAble()== 1) {
//			mListTitle.add(activity.getString(R.string.text_totalmileage));
//			mListStr.add(Integer.toString(carInfo.getM_iTotalMileage())+"Km");
//		}
		
		m_myAdpater.notifyDataSetChanged();
	}

}
