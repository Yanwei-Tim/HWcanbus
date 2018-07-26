package com.hiworld.canbus.util;

import java.util.ArrayList;

import com.hiworld.mycar.t11.R;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


public class PopMenu {
	private ArrayList<String> itemList;
	private ArrayList<Bitmap> icons = null;
	private Context context;
	private PopupWindow popupWindow ;
	private ListView listView;
	//private OnItemClickListener listener;
	

	@SuppressWarnings("deprecation")
	public PopMenu(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;

		itemList = new ArrayList<String>(4);
		icons = new ArrayList<Bitmap>(4);
		
		View view = LayoutInflater.from(context).inflate(R.layout.popmenu, null);
        
        //���� listview
        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(new PopAdapter());
        listView.setFocusableInTouchMode(true);
        listView.setFocusable(true);

        
        popupWindow = new PopupWindow(view, 100, LayoutParams.WRAP_CONTENT);
        popupWindow = new PopupWindow(view, 
        		context.getResources().getDimensionPixelSize(R.dimen.popmenu_width), 
        		LayoutParams.WRAP_CONTENT);
        
        // �����Ϊ�˵��������Back��Ҳ��ʹ����ʧ�����Ҳ�����Ӱ����ı�����������ģ�
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
	}

	//���ò˵�����������
	public void setOnItemClickListener(OnItemClickListener listener) {
		//this.listener = listener;
		listView.setOnItemClickListener(listener);
	}

	//������Ӳ˵���
	public void addItems(String[] items, ArrayList<Object> iconRes) {
		for (String s : items)
			itemList.add(s);
		// �����Դ�е�ͼƬ��ΪҪ��ʾ��ͼ��
	    Resources res = context.getResources();
		for (int i = 0; i < iconRes.size(); i++){
			Bitmap icon = BitmapFactory.decodeResource(res, (Integer) iconRes.get(i));
	        icons.add(icon);
		}
	}

	//������Ӳ˵���
	public void addItem(String item) {
		itemList.add(item);
	}


	public void showAsDropDown(View parent) {
		popupWindow.showAsDropDown(parent, 10, 
				context.getResources().getDimensionPixelSize(R.dimen.popmenu_yoff));
		
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
	}
	
	//���ز˵�
	public void dismiss() {
		popupWindow.dismiss();
	}

	// ������
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
				convertView = LayoutInflater.from(context).inflate(R.layout.pomenu_item, null);
				holder = new ViewHolder();

				convertView.setTag(holder);

				holder.groupItem = (TextView) convertView.findViewById(R.id.textView);
				holder.imgView = (ImageView) convertView.findViewById(R.id.imageView1);
				//holder.layout = (LinearLayout) convertView.findViewById(R.id.layout);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.groupItem.setText(itemList.get(position));
			holder.imgView.setImageBitmap(icons.get(position));

			return convertView;
		}

		private final class ViewHolder {
			TextView groupItem;
			ImageView imgView;
			//LinearLayout layout;
		}
	}
}
