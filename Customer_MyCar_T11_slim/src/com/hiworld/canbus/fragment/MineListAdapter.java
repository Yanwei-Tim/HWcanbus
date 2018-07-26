package com.hiworld.canbus.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hiworld.canbus.activity.AboutActivity;
import com.hiworld.canbus.activity.DeviceSetActivity;
import com.hiworld.canbus.activity.MineActivity;
import com.hiworld.canbus.activity.ServerActivitynew;
import com.hiworld.mycar.t11.R;

public class MineListAdapter extends BaseAdapter{

	private int[] listNames;
	private int[] listIcons;
	private LayoutInflater mInflater;
	private Context context;
	//APP肤色图片资源集合
	private int[] skinColourList;
	private int skin = 0;
	private View mconvertView;
	
	@SuppressWarnings("rawtypes")
	private Class[] listActivitys = {DeviceSetActivity.class,ServerActivitynew.class,AboutActivity.class,};//BondActivity.class,MyDeviceActivity.class,
	
	public MineListAdapter(int[] listNames, int[] listIcons,Context context) {
		super();
		this.listNames = listNames;
		this.listIcons = listIcons;
		this.context = context;
		mInflater = LayoutInflater.from(context);
	}

	public void updateSkin(int[] skinColourList,int skin) {
		this.skinColourList = skinColourList;
		this.skin = skin;
	}
	
	@Override
	public int getCount() {
		
		return listNames.length;
	}

	@Override
	public Object getItem(int position) {
		
		return null;
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder;
		mconvertView = convertView;
		if (mconvertView == null) {
			viewHolder = new ViewHolder();
			mconvertView = mInflater.inflate(R.layout.item_mine_list, parent, false);
			viewHolder.text_list = (TextView) mconvertView.findViewById(R.id.text_mine_item);
			mconvertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) mconvertView.getTag();
		}
		viewHolder.text_list.setText(listNames[position]);
		viewHolder.text_list.setCompoundDrawablesWithIntrinsicBounds(context.getResources().getDrawable(listIcons[position]), null, context.getResources().getDrawable(R.drawable.mine_jiantou), null);
		mconvertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
//				if (position == 0) {
//					//是否绑定判断&是否已连接服务
//					System.out.println("VissCmdParse.getInstance().getServiceConnect() ="+VissCmdParse.getInstance().getServiceConnect());
//					if (VissCmdParse.getInstance().bBound && VissCmdParse.getInstance().getServiceConnect()) {
//						Intent intent = new Intent(context,ConnectActivity.class);
//						context.startActivity(intent);
//						((MineActivity)context).overridePendingTransition(R.anim.my_scale_action,//放大
//								R.anim.my_alpha_action);
//					}else{
//						Intent intent = new Intent(context,listActivitys[position]);
//						context.startActivity(intent);
//						((MineActivity)context).overridePendingTransition(R.anim.my_scale_action,//放大
//								R.anim.my_alpha_action);
//					}
//				}else {
					Intent intent = new Intent(context,listActivitys[position]);
					context.startActivity(intent);
					((MineActivity)context).overridePendingTransition(R.anim.my_scale_action,//放大
							R.anim.my_alpha_action);
//				}
			}
		});
		if(skin == 0) {
			viewHolder.text_list.setBackgroundResource(skinColourList[66]);//mine_bac_selector
		}else {
			viewHolder.text_list.setBackgroundResource(skinColourList[67]);//mine_bac_selector
		}
		return mconvertView;
	}
	
	public static class ViewHolder{
		private TextView text_list;
	}

}
