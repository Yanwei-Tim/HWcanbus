package com.hiworld.canbus.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.util.TimeUtils;
import com.hiworld.canbus.util.UtilityClass;
import com.hiworld.mycar.t11.R;

public class SkinSettingActivity extends BaseActivity implements OnClickListener{
	
	//APP肤色图片资源集合
	private int[] skinColourList;
	private Button btnAppclation;
	private RelativeLayout lay_skin_set;
	private ImageButton mBack;
	private TextView mTextTitle;
	//APP肤色种类集合
	public int[] skinSelect;
	public int[] skinThumbnail;
	public int[] skinThumbnailIll;
	private String[] skinTitle;
	private LinearLayout lay_skin;
	private int skin = 0;
	private List<View> viewList = new ArrayList<View>();
	private int skinNumber = 0;
	private int isskinNumber;
	
	@SuppressLint("Recycle")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skin_set);
		//获取皮肤图片资源 add by jiaqing.liu 
		skinNumber = UtilityClass.getPrefrenceInts(SkinSettingActivity.this, "skinNumber");
		if(skinNumber == -1)
			skinNumber = 0;
		
		isskinNumber= skinNumber;
		
		skinColourList = TimeUtils.getInstance().getAllSkin(SkinSettingActivity.this);
		
		TypedArray typedArray;
		if(skinSelect == null || skinSelect.length <= 0) {
			//获取全部皮肤个数集合
			typedArray = SkinSettingActivity.this.getResources().obtainTypedArray(R.array.skin_colour_select);
			skinSelect = new int[typedArray.length()];  
			for (int i = 0; i < typedArray.length(); i++) {
				skinSelect[i] = typedArray.getResourceId(i, 0);
			}
			
			typedArray = SkinSettingActivity.this.getResources().obtainTypedArray(R.array.skin_thumbnail);
			skinThumbnail = new int[skinSelect.length];  
			for (int i = 0; i < typedArray.length(); i++) {
				skinThumbnail[i] = typedArray.getResourceId(i, 0);
			}
			
			typedArray = SkinSettingActivity.this.getResources().obtainTypedArray(R.array.skin_thumbnail_ill);
			skinThumbnailIll = new int[skinSelect.length];  
			for (int i = 0; i < typedArray.length(); i++) {
				skinThumbnailIll[i]=typedArray.getResourceId(i, 0);
			}
			
			skinTitle = this.getResources().getStringArray(R.array.skin_title);
			
			if(typedArray != null) {
				typedArray.recycle();  
			}
		}
		initview();
		registerInterfilter();
	}
	
	private void initview() {
		mBack = (ImageButton) this.findViewById(R.id.iv_back);
		mBack.setOnClickListener(this);
		mTextTitle = (TextView) findViewById(R.id.iv_top_title);
		mTextTitle.setText(R.string.text_skinSet);
		
		lay_skin = (LinearLayout) findViewById(R.id.lay_skin);
		
		btnAppclation = (Button) findViewById(R.id.btn_applaction);
		lay_skin_set = (RelativeLayout) findViewById(R.id.lay_skin_set);
		
		btnAppclation.setOnClickListener(this);
		
		/////ILL背光
		//VissCmdParse.getInstance().bBound && VissCmdParse.getInstance().bConnect && 
		if (CarPcInfo.getInstance().getM_iILLAble() == 1) {
			if (CarPcInfo.getInstance().getM_iILL() == 1) {
				skin = 1;
			}else {
				skin = 0;
			}
		}else {
			skin = 0;
		}
		
		for (int i = 0; i < skinSelect.length; i++) {
			View view = getLayoutInflater().from(this).inflate(R.layout.skin_viewpager_item, null);
			viewList.add(view);
			ImageView img_select = (ImageView) view.findViewById(R.id.img_select);
			img_select.setVisibility(View.GONE);
			RelativeLayout lay_current_skin = (RelativeLayout) view.findViewById(R.id.lay_current_skin);
			lay_current_skin.setVisibility(View.GONE);
			if(i == skinNumber) {
				img_select.setVisibility(View.VISIBLE);
				lay_current_skin.setVisibility(View.VISIBLE);
			}
			if(skin == 0) {
				view.findViewById(R.id.img_item).setBackgroundResource(skinThumbnail[i]);
			}else {
				view.findViewById(R.id.img_item).setBackgroundResource(skinThumbnailIll[i]);
			}
			TextView titl = (TextView) view.findViewById(R.id.skin_title);
			titl.setText(skinTitle[i]);
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
					for (int j = 0; j < viewList.size(); j++) {
						View view = viewList.get(j);
						((ImageView) view.findViewById(R.id.img_select)).setVisibility(View.GONE);
						if(arg0 == view) {
							((ImageView) view.findViewById(R.id.img_select)).setVisibility(View.VISIBLE);
							isskinNumber = j;
						}
					}
				}
			});
			
			lay_skin.addView(view);  
		}
		
		
	}

	@Override
	protected void onResume() {
		
		super.onResume();
		
		//获取皮肤图片资源 add by jiaqing.liu 
		skinColourList = TimeUtils.getInstance().getAllSkin(SkinSettingActivity.this);
		/////ILL背光
		//VissCmdParse.getInstance().bBound && VissCmdParse.getInstance().bConnect &&
		if ( CarPcInfo.getInstance().getM_iILLAble() == 1) {
			if (CarPcInfo.getInstance().getM_iILL() == 1) {
				lay_skin_set.setBackgroundResource(skinColourList[20]);//main_backgrond_ill
			}else {
				lay_skin_set.setBackgroundResource(skinColourList[22]);//main_backgrond
			}
		}else {
			lay_skin_set.setBackgroundResource(skinColourList[22]);//main_backgrond
		}
	}
	
	
	@Override
	public void onClick(View v) {
		
		if (v != null) {
			switch (v.getId()) {
			case R.id.iv_back:
				SkinSettingActivity.this.finish();
				break;
			case R.id.btn_applaction:
				UtilityClass.setPrefrenceInts(SkinSettingActivity.this, "skinNumber", isskinNumber);
				//获取皮肤图片资源 add by jiaqing.liu 
				skinColourList = TimeUtils.getInstance().getAllSkin(SkinSettingActivity.this);
				/////ILL背光
				//VissCmdParse.getInstance().bBound && VissCmdParse.getInstance().bConnect && 
				if (CarPcInfo.getInstance().getM_iILLAble() == 1) {
					if (CarPcInfo.getInstance().getM_iILL() == 1) {
						lay_skin_set.setBackgroundResource(skinColourList[20]);//main_backgrond_ill
						skin = 1;
					}else {
						lay_skin_set.setBackgroundResource(skinColourList[22]);//main_backgrond
						skin = 0;
					}
				}else {
					lay_skin_set.setBackgroundResource(skinColourList[22]);//main_backgrond
					skin = 0;
				}
				for (int j = 0; j < viewList.size(); j++) {
					View view = viewList.get(j);
					view.findViewById(R.id.lay_current_skin).setVisibility(View.GONE);
					if(j == isskinNumber) {
						view.findViewById(R.id.lay_current_skin).setVisibility(View.VISIBLE);
					}
					if(skin == 0) {
						view.findViewById(R.id.img_item).setBackgroundResource(skinThumbnail[j]);
					}else {
						view.findViewById(R.id.img_item).setBackgroundResource(skinThumbnailIll[j]);
					}
					
				}
				Toast.makeText(SkinSettingActivity.this, R.string.application_suc, Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	}
	
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}
	
	private BroadcastReceiver mReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			
			skinColourList = TimeUtils.getInstance().getAllSkin(SkinSettingActivity.this);
			String action = intent.getAction();
			if (ConstData.ACTION_ILL.equals(action)) {
				int ill = intent.getIntExtra("ill", 0);
				if (ill == 0) {
					skin = 0;
					lay_skin_set.setBackgroundResource(skinColourList[22]);//main_backgrond
				}else {
					lay_skin_set.setBackgroundResource(skinColourList[20]);//main_backgrond_ill
					skin = 1;
				}
			}else {
				lay_skin_set.setBackgroundResource(skinColourList[22]);//main_backgrond
				skin = 0;
			}
			for (int j = 0; j < viewList.size(); j++) {
				View view = viewList.get(j);
				if(skin == 0) {
					view.findViewById(R.id.img_item).setBackgroundResource(skinThumbnail[j]);
				}else {
					view.findViewById(R.id.img_item).setBackgroundResource(skinThumbnailIll[j]);
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
