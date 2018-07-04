package com.hiworld.fragment.gm;

import com.hiworld.adapter.SaicCarInfo;
import com.hiworld.canbus.parsedata.GMParseData;
import com.hiworld.carcomputer.R;
import com.hiworld.constant.Constant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GmFuleMileageFragment extends Fragment{

	private Activity activity;
	private TextView gm_value_instantfuel,gm_value_avgfuel1, gm_value_avgfuel2,gm_value_avgfuel3;
	private TextView gm_value_mileage,gm_value_totalmileage,gm_value_littlemileage1,gm_value_littlemileage2,gm_value_littlemileage3;
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		this.activity = activity;
		if (mHandler != null) {
			GMParseData.getInstance().setFuleMileageHandler(mHandler);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.gm_fuel_fragment, container, false);
		initView(view);
		initFuleMileage(SaicCarInfo.getInstance());
		return view;
	}
	
	
	private void initView(View view) {
		
		gm_value_instantfuel = (TextView)view.findViewById(R.id.gm_value_instantfuel);
		gm_value_avgfuel1 = (TextView)view.findViewById(R.id.gm_value_avgfuel1);
		gm_value_avgfuel2 = (TextView)view.findViewById(R.id.gm_value_avgfuel2);
		gm_value_avgfuel3 = (TextView)view.findViewById(R.id.gm_value_avgfuel3);
		gm_value_mileage = (TextView)view.findViewById(R.id.gm_value_mileage);
		gm_value_totalmileage = (TextView)view.findViewById(R.id.gm_value_totalmileage);
		gm_value_littlemileage1 = (TextView)view.findViewById(R.id.gm_value_littlemileage1);
		gm_value_littlemileage2 = (TextView)view.findViewById(R.id.gm_value_littlemileage2);
		gm_value_littlemileage3 = (TextView)view.findViewById(R.id.gm_value_littlemileage3);
	}


	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case Constant.GM_MESSAGE_HANDLER:
				SaicCarInfo GmCarinfo = (SaicCarInfo) msg.obj;
				if (GmCarinfo != null) {
					initFuleMileage(GmCarinfo);
				}
				break;
			default:
				break;
			}
		}
		
	};


	protected void initFuleMileage(SaicCarInfo gmCarinfo) {
		
		int temp = gmCarinfo.getM_iInstantFuel();
		float ff =0;
		String str = "Mpg";
		switch (gmCarinfo.getM_iUnitFuel()) {
		case 0:
			str = "Mpg";
			break;
		case 1:
			str = "Km/L";
			break;
		case 2:
			str = "L/100Km";
			break;
		case 3:
			str = "L/H";
			break;
		default:
			break;
		}
		if (temp != 0xffff) {
			ff = (float) (temp*0.1);
			gm_value_instantfuel.setText(activity.getString(R.string.instant_fuel)+ff+str);
		} else {
			gm_value_instantfuel.setText(activity.getString(R.string.instant_fuel)+"--"+str);
		}
		
		temp = gmCarinfo.getM_iAvgFuel1();
		if (temp != 0xffff) {
			ff = (float) (temp*0.1);
			gm_value_avgfuel1.setText(activity.getString(R.string.saic_averagefuel1)+ff+str);
		} else {
			gm_value_avgfuel1.setText(activity.getString(R.string.saic_averagefuel1)+"--"+str);
		}
		
		temp = gmCarinfo.getM_iAvgFuel2();
		if (temp != 0xffff) {
			ff = (float) (temp*0.1);
			gm_value_avgfuel2.setText(activity.getString(R.string.saic_averagefuel2)+ff+str);
		} else {
			gm_value_avgfuel2.setText(activity.getString(R.string.saic_averagefuel2)+"--"+str);
		}
		
		temp = gmCarinfo.getM_iAvgFuel3();
		if (temp != 0xffff) {
			ff = (float) (temp*0.1);
			gm_value_avgfuel3.setText(activity.getString(R.string.saic_averagefuel3)+ff+str);
		} else {
			gm_value_avgfuel3.setText(activity.getString(R.string.saic_averagefuel3)+"--"+str);
		}
		
		switch (gmCarinfo.getM_iUnitMileage()) {
		case 0:
			str = "Km";
			break;
		case 1:
			str = "Mile";
			break;
		default:
			break;
		}
		//总里程
		temp = gmCarinfo.getM_iMileage();
		if (temp != 0xffffff) {
			ff = (float) (temp*0.1);
			gm_value_totalmileage.setText(activity.getString(R.string.accum_mileage)+ff+str);
		} else {
			gm_value_totalmileage.setText(activity.getString(R.string.accum_mileage)+"--"+str);
		}
		//续航里程
		temp = gmCarinfo.getM_iDitanseMileage();
		if (temp != 0xffff) {
			gm_value_mileage.setText(activity.getString(R.string.instant_mileage)+temp+str);
		} else {
			gm_value_mileage.setText(activity.getString(R.string.instant_mileage)+"--"+str);
		}
		//小计里程1
		temp = gmCarinfo.getM_iLittleMileage1();
		if (temp != 0xffffff) {
			ff = (float) (temp*0.1);
			gm_value_littlemileage1.setText(activity.getString(R.string.little_mileage1)+ff+str);
		} else {
			gm_value_littlemileage1.setText(activity.getString(R.string.little_mileage1)+"--"+str);
		}
		//小计里程2
		temp = gmCarinfo.getM_iLittleMileage2();
		if (temp != 0xffffff) {
			ff = (float) (temp*0.1);
			gm_value_littlemileage2.setText(activity.getString(R.string.little_mileage2)+ff+str);
		} else {
			gm_value_littlemileage2.setText(activity.getString(R.string.little_mileage2)+"--"+str);
		}
		//小计里程3
		temp = gmCarinfo.getM_iLittleMileage3();
		if (temp != 0xffffff) {
			ff = (float) (temp*0.1);
			gm_value_littlemileage3.setText(activity.getString(R.string.little_mileage3)+ff+str);
		} else {
			gm_value_littlemileage3.setText(activity.getString(R.string.little_mileage3)+"--"+str);
		}

	}

}
