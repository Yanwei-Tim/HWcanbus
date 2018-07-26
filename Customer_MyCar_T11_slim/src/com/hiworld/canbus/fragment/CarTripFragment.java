package com.hiworld.canbus.fragment;




import java.util.ArrayList;
import java.util.List;

import com.hiworld.canbus.parse.FragmentParseData;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.mycar.t11.R;
import com.youzi.customer.connect.ConnConnect;
import com.youzi.customer.util.ConstUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;




@SuppressLint("NewApi")
public class CarTripFragment extends Fragment implements OnPageChangeListener{
	
	private List<View> views;
	private TripViewPaperAdapter vpAdapter;
	private ViewPager vp;
	// 底部小点图片
    private ImageView[] dots;
    // 记录当前选中位置
    private int currentIndex;
    
    private Activity activity;
    
    private TextView iv_tripmeter_avgfuel,iv_tripmeter_avgspeed,
    iv_tripmeter_drivertime,iv_tripmeter_count,iv_mileage_metertrip1;
    private ImageView iv_mileage_metersdu;
    private Button btn_trip_clear;
    
    private TextView iv_tripauto_avgfuel,iv_tripauto_avgspeed,
    iv_tripauto_drivertime,iv_tripauto_count,iv_mileage_autotrip1;
    private ImageView iv_mileage_autosdu;
    
    private View mContentView; 
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		this.activity = activity;
		if (mHandler != null) {
			FragmentParseData.getInstance().setCarTripHandler(mHandler);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

	}
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case ConstData.HANDLER_MESSAGE_CARPC:
				CarPcInfo carPcInfo = (CarPcInfo) msg.obj;
				if (carPcInfo != null) {
					SetCarPcInfo(carPcInfo);
				}
				break;
			default:
				break;
			}
		}
		
	};
	
	

	@SuppressLint("InflateParams")
	private void initViews(View view) {
		
		LayoutInflater inflater = LayoutInflater.from(activity);

        views = new ArrayList<View>();
        // 初始化引导图片列表
        views.add(inflater.inflate(R.layout.fragment_trip1, null));
        views.add(inflater.inflate(R.layout.fragment_trip2, null));

        // 初始化Adapter
        vpAdapter = new TripViewPaperAdapter(views, activity);
        vp = (ViewPager) view.findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
        // 绑定回调
        vp.setOnPageChangeListener(this);
	}

	protected void SetCarPcInfo(CarPcInfo carInfo) {
		
		float ff = 0;
		int temp = 0;
		if (carInfo.getM_iLittleTripAble() == 1) {
			//小计里程-平均油耗
			ff = carInfo.getM_fTripmeter_avgfuel();
			if (ff == 0xffff) {
				iv_tripmeter_avgfuel.setText(activity.getString(R.string.text_trip1_avgfuel)+"  --  L/100km");
			} else {
				iv_tripmeter_avgfuel.setText(activity.getString(R.string.text_trip1_avgfuel)+"  "+Float.toString(ff)+"  L/100km");
			}
			//小计里程-平均车速
			ff = carInfo.getM_fTripmeter_avgspeed();
			iv_tripmeter_avgspeed.setText(activity.getString(R.string.text_trip1_avgspeed)+"  "+Float.toString(ff)+"  km/h");
			//小计里程-行驶时长
			temp = carInfo.getM_iTripmeter_drivertime();
			iv_tripmeter_drivertime.setText(activity.getString(R.string.tetx_trip1_drivertime)+"  "+Integer.toString(temp)+"  min");
			//小计里程-里程
			temp = carInfo.getM_iTripmeter_mileage();
			iv_tripmeter_count.setText(activity.getString(R.string.tetx_trip1_count)+"  "+Integer.toString(temp)+"  km");
		}
		
		if (carInfo.getM_iSelfStartMileageAble() == 1) {
			//小计里程-平均油耗
			ff = carInfo.getM_fSelfstart_avgfuel();
			if (ff == 0xffff) {
				iv_tripauto_avgfuel.setText(activity.getString(R.string.text_trip1_avgfuel)+"  --  L/100km");
			} else {
				iv_tripauto_avgfuel.setText(activity.getString(R.string.text_trip1_avgfuel)+"  "+Float.toString(ff)+"  L/100km");
			}
			//小计里程-平均车速
			ff = carInfo.getM_fSelfstart_avgspeed();
			iv_tripauto_avgspeed.setText(activity.getString(R.string.text_trip1_avgspeed)+"  "+Float.toString(ff)+"  km/h");
			//小计里程-行驶时长
			temp = carInfo.getM_iSelfstart_drivertime();
			iv_tripauto_drivertime.setText(activity.getString(R.string.tetx_trip1_drivertime)+"  "+Integer.toString(temp)+"  min");
			//小计里程-里程
			temp = carInfo.getM_iSelfstart_mileage();
			iv_tripauto_count.setText(activity.getString(R.string.tetx_trip1_count)+"  "+Integer.toString(temp)+"  km");
		}
		
//		if (carInfo.getM_iTotalMileageAble() == 1) {
//			temp = carInfo.getM_iTotalMileage();
//			//总里程
//			iv_mileage_metertrip1.setText(activity.getString(R.string.text_totalmileage)+Integer.toString(temp)+"Km");
//			iv_mileage_autotrip1.setText(activity.getString(R.string.text_totalmileage)+Integer.toString(temp)+"Km");
//		}
		
		//续航里程
		if (carInfo.getM_iMileageAble() == 1) {
			temp = carInfo.getM_iMileage();
			if (temp >= 600) {
				iv_mileage_metersdu.setImageResource(R.drawable.iv_mileage_metersdu6);
				iv_mileage_autosdu.setImageResource(R.drawable.iv_mileage_metersdu6);
			}else if (temp >= 400 && temp < 500) {
				iv_mileage_metersdu.setImageResource(R.drawable.iv_mileage_metersdu5);
				iv_mileage_autosdu.setImageResource(R.drawable.iv_mileage_metersdu5);
			}else if (temp >= 300 && temp < 400) {
				iv_mileage_metersdu.setImageResource(R.drawable.iv_mileage_metersdu4);
				iv_mileage_autosdu.setImageResource(R.drawable.iv_mileage_metersdu4);
			}else if (temp >= 200 && temp < 300) {
				iv_mileage_metersdu.setImageResource(R.drawable.iv_mileage_metersdu3);
				iv_mileage_autosdu.setImageResource(R.drawable.iv_mileage_metersdu3);
			}else if (temp >= 100 && temp < 200) {
				iv_mileage_metersdu.setImageResource(R.drawable.iv_mileage_metersdu2);
				iv_mileage_autosdu.setImageResource(R.drawable.iv_mileage_metersdu2);
			}else if (temp > 0 && temp < 100) {
				iv_mileage_metersdu.setImageResource(R.drawable.iv_mileage_metersdu1);
				iv_mileage_autosdu.setImageResource(R.drawable.iv_mileage_metersdu1);
			}
			iv_mileage_metertrip1.setText(activity.getString(R.string.text_mileage_auto)+Integer.toString(temp)+"km");
			iv_mileage_autotrip1.setText(activity.getString(R.string.text_mileage_auto)+Integer.toString(temp)+"km");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if(mContentView == null){
			mContentView = inflater.inflate(R.layout.fragment_cartrip, container,false);
			// 初始化页面
	        initViews(mContentView);
	        // 初始化底部小点
	        initDots(mContentView);
	        
	        initTrip1();
	        
	        initTrip2();
	        
	        SetCarPCHandler();
		}else{
			 ViewGroup parent = (ViewGroup) mContentView.getParent();  
	            if (parent != null)  {  
	                parent.removeView(mContentView);  
	            }  
		}
		return mContentView;
	}

	private void initTrip2() {
		
		if (views.size() > 1) {
			iv_tripauto_avgfuel = (TextView) views.get(1).findViewById(R.id.iv_tripauto_avgfuel);
			iv_tripauto_avgspeed = (TextView) views.get(1).findViewById(R.id.iv_tripauto_avgspeed);
			iv_tripauto_drivertime = (TextView) views.get(1).findViewById(R.id.iv_tripauto_drivertime);
			iv_tripauto_count = (TextView) views.get(1).findViewById(R.id.iv_tripauto_count);
			iv_mileage_autosdu = (ImageView)views.get(1).findViewById(R.id.iv_mileage_autosdu);
			iv_mileage_autotrip1 = (TextView) views.get(1).findViewById(R.id.iv_mileage_autotrip1);
		}
	}

	private void SetCarPCHandler() {
		
		mHandler.obtainMessage(ConstData.HANDLER_MESSAGE_CARPC, CarPcInfo.getInstance()).sendToTarget();
	}

	private void initTrip1() {
		
		if (views.size() > 0) {
			iv_tripmeter_avgfuel = (TextView) views.get(0).findViewById(R.id.iv_tripmeter_avgfuel);
			iv_tripmeter_avgspeed = (TextView) views.get(0).findViewById(R.id.iv_tripmeter_avgspeed);
			iv_tripmeter_drivertime = (TextView) views.get(0).findViewById(R.id.iv_tripmeter_drivertime);
			iv_tripmeter_count = (TextView) views.get(0).findViewById(R.id.iv_tripmeter_count);
			iv_mileage_metersdu = (ImageView)views.get(0).findViewById(R.id.iv_mileage_metersdu);
			iv_mileage_metertrip1 = (TextView) views.get(0).findViewById(R.id.iv_mileage_metertrip1);
			btn_trip_clear = (Button) views.get(0).findViewById(R.id.btn_trip_clear);
			btn_trip_clear.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					try {
						ConnConnect.getInstance().getRemoteProxy().sendCmd(ConstUtil.APP2SERVER_OTHER, new int[] {0x04, 0x3b, 0x06, 0x01, 0x00, 0x00});
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}

	private void initDots(View view) {
		
		LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);
        dots = new ImageView[views.size()];
        // 循环取得小点图片
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setImageResource(R.drawable.dot);
        }
        currentIndex = 0;
        // 设置为白色，即选中状态
        dots[currentIndex].setImageResource(R.drawable.dot_d);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
		
	}

	@Override
	public void onPageSelected(int position) {
		
		// 设置底部小点选中状态
        setCurrentDot(position);
	}

	private void setCurrentDot(int position) {
		
		if (position < 0 || position > views.size() - 1
                || currentIndex == position) {
            return;
        }

		dots[position].setImageResource(R.drawable.dot_d);
		dots[currentIndex].setImageResource(R.drawable.dot);

        currentIndex = position;
	}

}
