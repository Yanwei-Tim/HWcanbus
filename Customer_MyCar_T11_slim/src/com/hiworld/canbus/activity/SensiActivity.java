package com.hiworld.canbus.activity;

import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.util.PopSpliner;
import com.hiworld.mycar.t11.R;
import com.youzi.customer.connect.ConnConnect;
import com.youzi.customer.util.ConstUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SensiActivity extends BaseActivity implements OnClickListener
{

	private String [] mStringArray;
	//private String [] mStringArray2;

	
	private TextView textSensiactivity;
	//private TextView textTraffic;
	
	private PopSpliner popMenu1;
	//private PopSpliner popMenu2;

	private Button m_BtnAgree;
	private Button m_BtnUnAgree;
	public static boolean bSennisi = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_htpms_general);
		
		init();
	}

	private void init(){
		
		textSensiactivity = (TextView)findViewById(R.id.text_Sensitivity);
		//textTraffic = (TextView)findViewById(R.id.text_Traffic);
		textSensiactivity.setOnClickListener(this);
		//textTraffic.setOnClickListener(this);
		
		//mStringArray = getResources().getStringArray(R.array.plantes);
		mStringArray = getResources().getStringArray(R.array.Traffic);
		//mStringArray2 = getResources().getStringArray(R.array.Traffic);
		
		popMenu1 = new PopSpliner(textSensiactivity.getContext()); 
		//popMenu2 = new PopSpliner(textTraffic.getContext()); 
	
		popMenu1.addItems(mStringArray);
		//popMenu2.addItems(mStringArray2);
		
		// 菜单项点击监听器
		popMenu1.setOnItemClickListener(popmenuItemClickListener);
//		popMenu2.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				
//				mHandler.obtainMessage(2, position).sendToTarget();
//				popMenu2.dismiss();
//			}
//		});

		
		SharedPreferences preferences = getSharedPreferences(
        		ConstData.SHAREDPREFERENCES_NAME, MODE_PRIVATE);
	
		mHandler.obtainMessage(1, preferences.getInt(ConstData.INSENITIVITY, 1)).sendToTarget();
		//mHandler.obtainMessage(2, preferences.getInt(ConstData.INTRAFFIC, 0)).sendToTarget();
		
		m_BtnAgree = (Button)findViewById(R.id.btn_general_agree);
		m_BtnUnAgree = (Button)findViewById(R.id.btn_general_unagree);
		m_BtnAgree.setOnClickListener(this);
		m_BtnUnAgree.setOnClickListener(this);
		
	}
	
	OnItemClickListener popmenuItemClickListener = new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> arent, View view, int position,
				long id) {
			
			//Log.d("popmenuItemClickListener", "position ="+position);
			mHandler.obtainMessage(1, position).sendToTarget();
			popMenu1.dismiss();
		}
		
	};
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				int value = (Integer) msg.obj;
				switch (value) {
				case 0:
					textSensiactivity.setText(R.string.text_CityRoad);
					break;
				case 1:
					textSensiactivity.setText(R.string.tetx_Suburbanroad);
					break;
				case 2:
					textSensiactivity.setText(R.string.tetx_Complexroad);
					break;
				default:
					break;
				}
				break;
//			case 2:
//				int traffic = (Integer)msg.obj;
//				switch (traffic) {
//				case 0:
//					textTraffic.setText(R.string.text_CityRoad);
//					break;
//				case 1:
//					textTraffic.setText(R.string.tetx_Suburbanroad);
//					break;
//				case 2:
//					textTraffic.setText(R.string.tetx_Complexroad);
//					break;
//				default:
//					break;
//				}
//				break;
			default:
				break;
			}
		}
		
	};

	@Override
	protected void onDestroy()
	{
		
		super.onDestroy();
	}

	@Override
	protected void onPause()
	{
		
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		
		super.onResume();
	}

	@Override
	public void onClick(View v)
	{
		
		switch (v.getId())
		{
		case R.id.btn_general_agree:
			int iSenivity = 2;
			if (textSensiactivity.getText().equals(getString(R.string.text_CityRoad))) {
				iSenivity =0 ;
			}else if (textSensiactivity.getText().equals(getString(R.string.tetx_Suburbanroad))) {
				iSenivity =1 ;
			}else if (textSensiactivity.getText().equals(getString(R.string.tetx_Complexroad))) {
				iSenivity =2 ;
			}
//			int iTraffic = 0;
//			if (textTraffic.getText().equals(getString(R.string.text_CityRoad))) {
//				iTraffic =0 ;
//			}else if (textTraffic.getText().equals(getString(R.string.tetx_Suburbanroad))) {
//				iTraffic =1 ;
//			}else if (textTraffic.getText().equals(getString(R.string.tetx_Complexroad))) {
//				iTraffic =2 ;
//			}

		    int[] sendCmd = new int[4];
			sendCmd[0] = 0x02;
			sendCmd[1] = 0x4d;
			sendCmd[2] = 0x02;
		    switch (iSenivity){
			case 0:
				sendCmd[3] = 0x05;
				break;
            case 1:
            	sendCmd[3] = 0x03;
				break;
            case 2:
            	sendCmd[3] = 0x01;
				break;
			default:
				break;
			}
		    writeBuf(sendCmd, sendCmd.length);
		    bSennisi = true;
		    SharedPreferences preferences = getSharedPreferences(
	        		ConstData.SHAREDPREFERENCES_NAME, MODE_PRIVATE);
		    Editor editor = preferences.edit();
		    editor.putInt(ConstData.INSENITIVITY, iSenivity);
		    //editor.putInt(ConstData.INTRAFFIC, iTraffic);
		    editor.commit();
		    
			finish();
			break;
        case R.id.btn_general_unagree:
			finish();
			break;
        case R.id.text_Sensitivity:
        	popMenu1.showAsDropDown(v);
        	break;
//        case R.id.text_Traffic:
//        	popMenu2.showAsDropDown(v);
//        	break;
		default:
			break;
		}
	}

	private void writeBuf(int[] ints, int length)
	{
		
		try {
			ConnConnect.getInstance().getRemoteProxy().sendCmd(ConstUtil.APP2SERVER_OTHER, ints);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
