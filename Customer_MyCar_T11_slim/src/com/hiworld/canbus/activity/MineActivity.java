package com.hiworld.canbus.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hiworld.canbus.fragment.MineListAdapter;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.util.TimeUtils;
import com.hiworld.mycar.t11.R;

//设置总界面
public class MineActivity extends BaseActivity{

	private TextView mTextTitle;
	private ListView mMineList;
	private ImageButton mBack;
	private int[] listNames = {R.string.mine_deviceset,R.string.server_coycopy,R.string.mine_about};//R.string.mine_connect,R.string.mine_mydevice,
	private int[] listIcons = {R.drawable.mine_deviceset,R.drawable.mime_myserver,R.drawable.mine_about};//R.drawable.mine_connect,R.drawable.mine_device,
	private LinearLayout rl_mine;
	//APP肤色图片资源集合
	private int[] skinColourList;
	private MineListAdapter mAdapter;
	private int skin = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mine);
		initView();
		setAdapter();
		setListener();
		registerInterfilter();
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		//获取皮肤图片资源 add by jiaqing.liu 
		skinColourList = TimeUtils.getInstance().getAllSkin(MineActivity.this);
		/////ILL背光
		//VissCmdParse.getInstance().bBound && VissCmdParse.getInstance().bConnect && 
		if (CarPcInfo.getInstance().getM_iILLAble() == 1) {
			if (CarPcInfo.getInstance().getM_iILL() == 1) {
				rl_mine.setBackgroundResource(skinColourList[20]);//main_backgrond_ill
				mAdapter.updateSkin(skinColourList, 1);
				skin = 1;
			}else {
				rl_mine.setBackgroundResource(skinColourList[22]);//main_backgrond
				mAdapter.updateSkin(skinColourList, 0);
				skin = 0;
			}
		}else {
			rl_mine.setBackgroundResource(skinColourList[22]);//main_backgrond
			mAdapter.updateSkin(skinColourList, 0);
			skin = 0;
		}
		for (int i = 0; i < mMineList.getChildCount(); i++) {
			if(skin == 1) {
				mMineList.getChildAt(i).findViewById(R.id.text_mine_item).setBackgroundResource(skinColourList[67]);
			}else {
				mMineList.getChildAt(i).findViewById(R.id.text_mine_item).setBackgroundResource(skinColourList[66]);
			}
		}
		
	}

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}

	private void initView() {
		
		mTextTitle = (TextView) findViewById(R.id.iv_top_title);
		mTextTitle.setText(R.string.mine_name);
		mMineList = (ListView) findViewById(R.id.list_mine);
		mBack = (ImageButton) this.findViewById(R.id.iv_back);
		rl_mine = (LinearLayout) findViewById(R.id.ll_mine_bac);
	}
	
	private void setAdapter() {
		
		mAdapter = new MineListAdapter(listNames, listIcons, this);
		mMineList.setAdapter(mAdapter);
	}

	private void setListener() {
		
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				MineActivity.this.finish();
			}
		});
	}

	private BroadcastReceiver mReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			
			//获取皮肤图片资源 add by jiaqing.liu 
			skinColourList = TimeUtils.getInstance().getAllSkin(MineActivity.this);
			String action = intent.getAction();
			if (ConstData.ACTION_ILL.equals(action)) {
				int ill = intent.getIntExtra("ill", 0);
				if (ill == 0) {
					rl_mine.setBackgroundResource(skinColourList[22]);//main_backgrond
					if(mAdapter!=null) {
						mAdapter.updateSkin(skinColourList, 0);
						skin = 0;
					}
				}else {
					rl_mine.setBackgroundResource(skinColourList[20]);//main_backgrond_ill
					if(mAdapter!=null) {
						mAdapter.updateSkin(skinColourList, 1);
						skin = 1;
					}
				}
			}else {
				rl_mine.setBackgroundResource(skinColourList[22]);//main_backgrond
				if(mAdapter!=null) {
					mAdapter.updateSkin(skinColourList, 0);
					skin = 0;
				}
			}
			for (int i = 0; i < mMineList.getChildCount(); i++) {
				if(skin == 1) {
					mMineList.getChildAt(i).findViewById(R.id.text_mine_item).setBackgroundResource(skinColourList[67]);
				}else {
					mMineList.getChildAt(i).findViewById(R.id.text_mine_item).setBackgroundResource(skinColourList[66]);
				}
			}
		}
	};
	
	private void registerInterfilter() {
		
		IntentFilter infiter = new IntentFilter();
		infiter.addAction(ConstData.ACTION_ILL);
		registerReceiver(mReceiver, infiter);
	}
	
}
