package com.hiworld.canbus.util;

import java.util.ArrayList;

import com.hiworld.mycar.t11.R;



import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/*
 * 下拉列表弹出框
 */
public class PopSpliner {
	private ArrayList<String> itemList;
	private Context context;
	private PopupWindow popupWindow ;
	private ListView listView;
	
	@SuppressWarnings("deprecation")
	public PopSpliner(Context context){
		this.context = context;

		itemList = new ArrayList<String>(3);
		
		View view = LayoutInflater.from(context).inflate(R.layout.popspiner, null);
        
        //设置 listview
        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(new PopAdapter());
        listView.setFocusableInTouchMode(true);
        listView.setFocusable(true);
        
       // popupWindow = new PopupWindow(view, 100, LayoutParams.WRAP_CONTENT);
        popupWindow = new PopupWindow(view, 
        		context.getResources().getDimensionPixelSize(R.dimen.popspiner_width), 
        		LayoutParams.WRAP_CONTENT);
        
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景（很神奇的）
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
	}
	
	//设置菜单项点击监听器
		public void setOnItemClickListener(OnItemClickListener listener) {
			//this.listener = listener;
			listView.setOnItemClickListener(listener);
			//Log.d("popmenuItemClickListener", "listener");
		}

		//批量添加菜单项
		public void addItems(String[] items) {
			
			for (String s : items)
				itemList.add(s);
		}

		//单个添加菜单项
		public void addItem(String item) {
			itemList.add(item);
		}

		//下拉式 弹出 pop菜单 parent 右下角
		public void showAsDropDown(View parent) {
			popupWindow.showAsDropDown(parent, 0,0);
			
			// 使其聚集
	        popupWindow.setFocusable(true);
	        // 设置允许在外点击消失
	        popupWindow.setOutsideTouchable(true);
	        //刷新状态
	        popupWindow.update();
		}
		
		//隐藏菜单
		public void dismiss() {
			popupWindow.dismiss();
		}

		// 适配器
		private final class PopAdapter extends BaseAdapter {

			@Override
			public int getCount() {
				
				return itemList.size();
			}

			@Override
			public Object getItem(int position) {
				
				return itemList.get(position);
			}

			@Override
			public long getItemId(int position) {
				
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				
				ViewHolder holder;
				if (convertView == null) {
					convertView = LayoutInflater.from(context).inflate(R.layout.popspiner_item, null);
					holder = new ViewHolder();

					convertView.setTag(holder);

					holder.groupItem = (TextView) convertView.findViewById(R.id.textView);

				} else {
					holder = (ViewHolder) convertView.getTag();
				}

				holder.groupItem.setText(itemList.get(position));

				return convertView;
			}

			private final class ViewHolder {
				TextView groupItem;
			}
		}
}
