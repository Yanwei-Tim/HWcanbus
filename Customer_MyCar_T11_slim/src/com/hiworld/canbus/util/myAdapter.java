package com.hiworld.canbus.util;

import java.util.ArrayList;

import com.hiworld.mycar.t11.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class myAdapter extends BaseAdapter
{
	private ArrayList<String> Title;
	private ArrayList<String> Value;
	//private Context context;
	private LayoutInflater layoutInflater;
	
	public myAdapter(Context context, ArrayList<String> title, ArrayList<String> value){
		//this.context = context;
		this.Title = title;
		this.Value = value;
		this.layoutInflater = LayoutInflater.from(context);
	}
	
	public final class Zujian{
		TextView m_TextTitle;
		TextView m_TextValues;
		LinearLayout layout;
	}

	@Override
	public int getCount()
	{
		
		return Title.size();
	}

	@Override
	public Object getItem(int postion)
	{
		
		return Title.get(postion);
	}

	@Override
	public long getItemId(int postion)
	{
		
		return postion;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int postion, View convertView, ViewGroup parent)
	{
		
		Zujian zujian = null;
		if (convertView == null)
		{
			zujian = new Zujian();
			convertView = layoutInflater.inflate(R.layout.mylist, null);
			zujian.m_TextTitle = (TextView)convertView.findViewById(R.id.title);
			zujian.m_TextValues = (TextView)convertView.findViewById(R.id.values);
			zujian.layout = (LinearLayout)convertView.findViewById(R.id.layout);
			convertView.setTag(zujian);
		}
		else{
			zujian = (Zujian) convertView.getTag();
		}

		zujian.m_TextTitle.setText(Title.get(postion));
		zujian.m_TextValues.setText(Value.get(postion));
		//zujian.layout.setBackgroundResource(R.drawable.bg_list_n);
		
		return convertView;
	}

}
