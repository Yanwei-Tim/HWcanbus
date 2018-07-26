package com.hiworld.canbus.activity;

import com.hiworld.mycar.t11.R;
import com.youzi.customer.connect.ConnConnect;
import com.youzi.customer.util.ConstUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CailbrationActivity extends BaseActivity implements OnClickListener
{
	private Button m_BtnAgree;
	private Button m_BtnUnAgree;
	public static boolean  bReat = false;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_htpms_adjust);
		
		init();
	}

	

	private void init(){
		
		m_BtnAgree = (Button)findViewById(R.id.btn_cailbration_agree);
		m_BtnUnAgree = (Button)findViewById(R.id.btn_cailbration_unagree);
		m_BtnAgree.setOnClickListener(this);
		m_BtnUnAgree.setOnClickListener(this);
	}



	@Override
	protected void onDestroy(){
		
		super.onDestroy();
	}

	@Override
	protected void onPause(){
		
		super.onPause();
	}

	@Override
	protected void onResume()	{
		
		super.onResume();
	}

	@Override
	public void onClick(View v){
		
		switch (v.getId())	{
		case R.id.btn_cailbration_agree:
			writeBuf(new int[] {0x02,0x4d,0x01,0x00}, 4);
			finish();
			break;
        case R.id.btn_cailbration_unagree:
        	finish();
			break;
		default:
			break;
		}
	}



	private void writeBuf(int[] ints, int length){
		
		try {
			ConnConnect.getInstance().getRemoteProxy().sendCmd(ConstUtil.APP2SERVER_OTHER, ints);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Intent intent = new Intent();
//		intent.setAction(ConstData.HIWORLD_INTERNAL_RECIVER);
//		intent.putExtra(ConstData.INTERNAL_RECIVER_KEY, buffer);
//		sendBroadcast(intent);
		bReat = true;
	}

}
