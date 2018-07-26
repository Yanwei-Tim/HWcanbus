package com.hiworld.canbus.activity;




import com.hiworld.mycar.t11.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class PrintActivity extends BaseActivity implements OnClickListener
{
	//会话内容的适配器
	private ArrayAdapter<String> mConversationArrayAdapter = null;
	private ListView mConversationView;//显示内容区
	private Button mBtnClr;
	public static boolean isActivity = false;
	//本地广播
	private LocalBroadcastManager localBroadcastManager;
	private LocalReceiver localReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_htpms_print);
		init();
		registerReciver();
	}

	private void init()
	{
		
		mConversationArrayAdapter = new ArrayAdapter<String>(this, R.layout.message);
		mConversationView = (ListView)findViewById(R.id.listView1);
		mConversationView.setAdapter(mConversationArrayAdapter);
		
		mBtnClr = (Button)findViewById(R.id.button1);
		mBtnClr.setOnClickListener(this);
	}

	private void registerReciver()
	{
		
		//本地广播
		localBroadcastManager = LocalBroadcastManager.getInstance(this);
		IntentFilter filter1 = new IntentFilter("com.hiworld.print.data");
		localReceiver = new LocalReceiver();
		localBroadcastManager.registerReceiver(localReceiver, filter1);
	}
	
	class LocalReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals("com.hiworld.print.data")) {
				byte[] buffer = intent.getByteArrayExtra("com.hiworld.key.print");
				if (mConversationArrayAdapter != null) {
					if (buffer[0] == (byte)0x48) {
						byte[] buf = new byte[buffer.length+4];
						buf[0] = 0x5a;
						buf[1] = (byte) 0xa5;
						buf[2] = (byte) (buffer.length-1);
						System.arraycopy(buffer, 0, buf, 3, buffer.length);
						buf[buffer.length+3] = AddCheckSum(buffer, buffer.length);
						
						StringBuilder sb = new StringBuilder();
						for (int i = 0, end = buf.length; i < end; i++) {
							sb.append(String.format("%02X ", buf[i]));
						}

						mConversationArrayAdapter.add(sb.toString());
					}
					
				}
			}
		}

	}

	
	private  byte AddCheckSum(byte[] buffer, int iLength)
	{
		
		byte addSum = (byte) (iLength-1);
		if(buffer != null && iLength > 0)
		{
			for (int i=0; i<iLength; i++)
			{
				addSum += buffer[i];
			}
			addSum = (byte) ((addSum & 0xff) - 1);
		}
		return addSum;
	}


	@Override
	protected void onDestroy()
	{
		
		localBroadcastManager.unregisterReceiver(localReceiver);
		mConversationArrayAdapter = null;
		super.onDestroy();
	}

	@Override
	protected void onPause()
	{
		
		super.onPause();
		isActivity = false;
	}

	@Override
	protected void onResume()
	{
		
		super.onResume();
		isActivity = true;
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated metho0d stub
		switch (v.getId())
		{
		case R.id.button1:
			mConversationArrayAdapter.clear();//清空列表
			break;
		default:
			break;
		}
	}

}
