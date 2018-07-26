package com.hiworld.canbus.fragment;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hiworld.canbus.activity.MainActivity;
import com.hiworld.canbus.activity.WarnBroadcastReceiver;
import com.hiworld.canbus.parse.FragmentParseData;
import com.hiworld.canbus.receiver.WarnBroadCast;
import com.hiworld.canbus.receiver.WarnBroadCast.OnWarnTipListener;
import com.hiworld.canbus.util.CarInfo;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.util.GuideInfoExtraKey;
import com.hiworld.canbus.util.GuideNaviImage;
import com.hiworld.canbus.util.TimeUtils;
import com.hiworld.canbus.view.CustomTextView;
import com.hiworld.canbus.view.MyKuangView;
import com.hiworld.canbus.view.NaviFontTextView;
import com.hiworld.canbus.view.VerticalViewPager;
import com.hiworld.canbus.view.WeatherFontTextView;
import com.hiworld.mycar.t11.R;

//首页fragment
public class HomeFragment extends Fragment implements OnClickListener, OnWarnTipListener{
	
	private final static int MSG_WARNTIP = 1;

	private ImageButton mIbMainCarpc, mIbMainTpms;
	private ImageView img_basicCar,img_right_circle,iv_main_meter;
	private ImageView 
	    /*mIvMainSpeedHigh, mIvMainSpeedMiddle, mIvMainSpeedLow,*/
	    mIvMainGear, mIvMainShake, mIcoMainTpms, mIcoMainLeftlight, mIcoMainRightlight, mIcoMainDoublelight,
	    /*mIvMainTpms,*/ mIvMainLeftlight, mIvMainRightlight, mMainLfdoor, mMainRfdoor, mMainLrdoor, mMainRrdoor,
	    mMainHood, mMainTaixbox, mMainLfwindow, mMainRfwindow, mMainLrwindow, mMainRrwindow,
	    mMainLfdoorOpen, mMainRfdoorOpen, mMainLrdoorOpen, mMainRrdoorOpen,
	    mMainNearlylight, mMainFarlight, mMainWidelight, mMainStoplight, mIcoIll, mIcoFarlight, mIcoBattery,
	    mIcoSafebelt, mIcoWeather,mIconOil;
	private TextView mTvMainTrip, mTvMainMileage,text_speed;
	private boolean bRunnable, bSafeBelt;
	private int doublelight_num, mSafeNum;
	private Activity activity;
	//高德导航
//    boolean bInNavi = false;
    private int mIcon;
	private TextView mTvRemainTime,mTvNextUnit,mTvNaviTytle,mTvNaviRoad,mTvRemainDis;
	private ImageView mNaviIconIv;
	private NaviFontTextView mTvNaviDis;
	
	private View /*mIncludeWeather,*/ mIncludeNavi;
	private CustomTextView /*mTvEngineSpeed,*/mTvAverageSpeed,mTvDrivingTime,mTvRestOil,mTvBattery;
	/*private TextView mTvRpm;*/
    
	//天气
	private WeatherFontTextView mTvTemp;
	private TextView mTvDay, mTvWeather,mTvFxWeather;
	private ImageView mWeatherIv;
	
//	private ImageView iv_main_circle;
	private boolean isPort = false;
	
//	private TextView mTvWarn;
	private ArrayList<String> warnList = new ArrayList<String>();
	private Time time = new Time();
	//滑动仪表
	private VerticalViewPager mViewPager;

	//private String[] unitValue = {"rpm","V"/*,"%"*/,"℃","min","km/h","km"};
	//private String[] titleValue ;

	
	private ArrayList<String> unitLists = new ArrayList<String>();
	private ArrayList<String> titleLists = new ArrayList<String>();
	private ArrayList<View> rightViews = new ArrayList<View>();
	private View rpmView,dianyaView,oilView,coolanttempView,drivingTimeView
			,averageSpeedView,drivingMileageView,weatherView;
	private boolean isFirst = false;
	//CustomTextView mCustomTextView;
	
	private int currentItem;
	private VerticalAdapter mAdapter;
	private List<CustomTextView> customTextViews = new ArrayList<CustomTextView>();
	private String tq,wd;
	private RelativeLayout rl_backGround;
	private ImageView iv_mileage_bac;
	private MyKuangView mKuangView;
	//APP肤色图片资源集合
	private int[] skinColourList;
	
	@SuppressLint("HandlerLeak") 
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
//			case ConstData.MSG_GET_BLUESTATE:
//				initSetCarPc(CarPcInfo.getInstance(), CarInfo.getInstance());
//				break;
//			case ConstData.MSG_GET_CARPCCHANGE:
//				initSetCarPc(CarPcInfo.getInstance(), CarInfo.getInstance());
//				break;
//			case ConstData.MSG_VOICE_WARN:
////				int voiceId = (int) msg.obj;
////				mPlayer = MediaPlayer.create(MainActivity.this, ConstData.VOICE_WARN[voiceId]);
////				mPlayer.start();
//				break;	 
			case ConstData.MESSAGE_WARNTIPS:
				setWarnTips((int[]) msg.obj);
				break;
			case ConstData.HANDLER_MESSAGE_CARPC:
				CarPcInfo hudInfo = (CarPcInfo) msg.obj;
				initSetCarPc(hudInfo);
				break;
			case ConstData.MESSAGE_TIRE_FAILURE://轮胎报警
				CarInfo carInfo = (CarInfo) msg.obj;
				initSetCar(carInfo);
				break;
			case ConstData.MSG_GET_DOUBLELIGHT:
				doublelight_num++;
				doublelight_num %= 2;
				if (doublelight_num == 0) {
					hideDoubleLight();
				} else {
					if (CarPcInfo.getInstance().getM_iDoubleLight() == 1) {
//						mIcoMainDoublelight.setVisibility(View.VISIBLE);
						mIvMainLeftlight.setVisibility(View.VISIBLE);
						mIvMainRightlight.setVisibility(View.VISIBLE);
						mIcoMainLeftlight.setVisibility(View.VISIBLE);
						mIcoMainRightlight.setVisibility(View.VISIBLE);
					}
                    if (CarPcInfo.getInstance().getM_iLeftTrunLight() == 1) {
						mIvMainLeftlight.setVisibility(View.VISIBLE);
						mIcoMainLeftlight.setVisibility(View.VISIBLE);
					}
					if (CarPcInfo.getInstance().getM_iRightTrunLight() == 1) {
						mIvMainRightlight.setVisibility(View.VISIBLE);
						mIcoMainRightlight.setVisibility(View.VISIBLE);
					}
				}
				bRunnable = true;
				mHandler.removeMessages(ConstData.MSG_GET_DOUBLELIGHT);
				mHandler.sendEmptyMessageDelayed(ConstData.MSG_GET_DOUBLELIGHT, 400);
				break;
			case ConstData.MSG_GET_SAFEBELT:
				mSafeNum++;
				switch (mSafeNum %= 2) {
				case 0:
					mIcoSafebelt.setVisibility(View.INVISIBLE);
					break;
                case 1:
                	mIcoSafebelt.setVisibility(View.VISIBLE);
					break;
				default:
					break;
				}
//				mMainHandler.removeMessages(ConstData.MSG_GET_SAFEBELT);
				mHandler.sendEmptyMessageDelayed(ConstData.MSG_GET_SAFEBELT, 400);
				break;
			case ConstData.MSG_GAODE_NAVI://高德信息
				Bundle bundle = (Bundle) msg.obj;
				setNaviInfo(bundle);
				break;
			case ConstData.MSG_SHOW_NAVI:
				showInclueView();
				break;
			case 666:
				int flag =  (Integer) msg.obj;
				if (flag == 0) {
					warnList.remove(activity.getResources().getString(R.string.warn_noTiredDriving));
					if (warnList.size() > 0) {
						if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_tire))) {
							setWarnKuang(warnList.get(warnList.size()-1), getTextColor(), true, getWarnLevel(), View.VISIBLE);
						}else {
							setWarnKuang(warnList.get(warnList.size()-1), "#66C6FCFF", false, getWarnLevel(), View.VISIBLE);
						}
//						mTvWarn.setVisibility(View.VISIBLE);
//						mTvWarn.setText(warnList.get(warnList.size()-1));
					}else {
//						mTvWarn.setVisibility(View.GONE);
						setWarnKuang("", "#66C6FCFF", false, 0, View.GONE);
					}
					Message message = mHandler.obtainMessage(666);
					message.obj = 1;
					mHandler.sendMessageDelayed(message, 20*60*1000);
				}else {
					warnList.add(activity.getResources().getString(R.string.warn_noTiredDriving));
					setWarnKuang(activity.getResources().getString(R.string.warn_noTiredDriving), "#66C6FCFF", false, 0, View.VISIBLE);
//					mTvWarn.setVisibility(View.VISIBLE);
//					mTvWarn.setText(warnList.get(warnList.size()-1));
					Message message = mHandler.obtainMessage(666);
					message.obj = 0;
					mHandler.sendMessageDelayed(message, 15*1000);
				}
				break;
			case 88:
//				mViewPager.setCurrentItem(0);
				if(rightViews.size() > 0){
					mAdapter.notifyDataSetChanged();
					if(rightViews.size() ==1){
						mViewPager.setCurrentItem(0);
					}else{
						mViewPager.setCurrentItem((rightViews.size()*100)-1);
					}
				}
				break;
			default:
				break;
			}
		}
	};
	
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		this.activity = activity;
		FragmentParseData.getInstance().setmHandlerHomeFregment(mHandler);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
		 ViewGroup container,  Bundle savedInstanceState) {
		
		Configuration mConfiguration = activity.getResources().getConfiguration(); //获取设置的配
		int ori = mConfiguration.orientation ; //获取屏幕方向
		if (ori == Configuration.ORIENTATION_LANDSCAPE) {//横屏
			isPort = false;
		}else {
			isPort = true;
		}
		View view = inflater.inflate(R.layout.activity_main_new, container,false);
		initView(view);
		//获取皮肤图片资源 add by jiaqing.liu 
		skinColourList = TimeUtils.getInstance().getAllSkin(activity);
		showInclueView();
//		VissCmdParse.getInstance().setHomeHandler(mHandler);
//		initSetCarPc(CarPcInfo.getInstance(),  CarInfo.getInstance());
		registerInterfilter();
//		if (((MainActivity)activity).getNetWorkState()) {
//			mViewPager.setCurrentItem(1);
//			mViewPager.setCurrentItem((rightViews.size()*100));
//		}
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				initSetCarPc(CarPcInfo.getInstance());
			}
		});
		return view;
	}
	
	private void registerInterfilter() {
		
		IntentFilter infiter = new IntentFilter();
		infiter.addAction(ConstData.ACTION_GAODE_INFO);
		infiter.addAction(ConstData.ACTION_ILL);
		activity.registerReceiver(mReceiver, infiter);
	}
	
	@Override
	public void onDestroyView() {
		
		super.onDestroyView();
		activity.unregisterReceiver(mReceiver);
	}
	
	private void initView(View view) {
		
		//初始化列表
		initUnitLists();
//		titleValue = new String[]{activity.getResources().getString(R.string.text_engineSpeed),
//				activity.getResources().getString(R.string.text_batteryvoltage),
//				/*activity.getResources().getString(R.string.text_restoil),*/
//				activity.getResources().getString(R.string.text_coolanttemp),
//				activity.getResources().getString(R.string.text_thisDrivingTime),
//				activity.getResources().getString(R.string.text_thisAverageSpeed),
//				activity.getResources().getString(R.string.text_thisDrivingMileage)};
		mIbMainCarpc = (ImageButton) view.findViewById(R.id.ib_main_carpc);
		mIbMainTpms = (ImageButton) view.findViewById(R.id.ib_main_tpms);
		img_basicCar = (ImageView) view.findViewById(R.id.iv_main_basecar);
		img_right_circle = (ImageView) view.findViewById(R.id.iv_main_circle);
		iv_main_meter = (ImageView) view.findViewById(R.id.iv_main_meter);
		mIbMainCarpc.setOnClickListener(this);
		mIbMainTpms.setOnClickListener(this);
		img_basicCar.setOnClickListener(this);
		img_right_circle.setOnClickListener(this);
		
		rl_backGround = (RelativeLayout) view.findViewById(R.id.rl_home_background);
		iv_mileage_bac = (ImageView) view.findViewById(R.id.iv_port_mileage);
		
		mTvMainTrip = (TextView) view.findViewById(R.id.tv_main_trip);
		mTvMainMileage = (TextView) view.findViewById(R.id.tv_main_mileage);
		
//		mIvMainSpeedHigh = (ImageView) view.findViewById(R.id.iv_main_speed_hundred);
//		mIvMainSpeedMiddle = (ImageView) view.findViewById(R.id.iv_main_speed_ten);
//		mIvMainSpeedLow = (ImageView) view.findViewById(R.id.iv_main_speed_digit);
		
		mIvMainGear = (ImageView) view.findViewById(R.id.iv_main_gear);
		mIvMainShake = (ImageView) view.findViewById(R.id.iv_main_shake);
		mIcoMainTpms = (ImageView) view.findViewById(R.id.iv_main_icotpms);
		mIcoMainLeftlight = (ImageView) view.findViewById(R.id.iv_main_icoleftlight);
		mIcoMainRightlight = (ImageView) view.findViewById(R.id.iv_main_icorightlight);
		
//		mIvMainTpms = (ImageView) view.findViewById(R.id.iv_main_tire);
		mIvMainLeftlight = (ImageView) view.findViewById(R.id.iv_main_leftlight);
		mIvMainRightlight = (ImageView) view.findViewById(R.id.iv_main_rightlight);
		mIcoMainDoublelight = (ImageView) view.findViewById(R.id.iv_main_icodoublelight);
		
		mMainLfdoorOpen = (ImageView) view.findViewById(R.id.iv_main_lfdoor_open);
		mMainRfdoorOpen = (ImageView) view.findViewById(R.id.iv_main_rfdoor_open);
		mMainLrdoorOpen = (ImageView) view.findViewById(R.id.iv_main_lrdoor_open);
		mMainRrdoorOpen = (ImageView) view.findViewById(R.id.iv_main_rrdoor_open);
		
		mMainLfdoor = (ImageView) view.findViewById(R.id.iv_main_lfdoor);
		mMainRfdoor = (ImageView) view.findViewById(R.id.iv_main_rfdoor);
		mMainLrdoor = (ImageView) view.findViewById(R.id.iv_main_lrdoor);
		mMainRrdoor = (ImageView) view.findViewById(R.id.iv_main_rrdoor);
		mMainHood = (ImageView) view.findViewById(R.id.iv_main_hood);
		mMainTaixbox = (ImageView) view.findViewById(R.id.iv_main_taxibox);
		mMainLfwindow = (ImageView) view.findViewById(R.id.iv_main_lfwindow);
		mMainLrwindow = (ImageView) view.findViewById(R.id.iv_main_lrwindow);
		mMainRfwindow = (ImageView) view.findViewById(R.id.iv_main_rfwindow);
		mMainRrwindow = (ImageView) view.findViewById(R.id.iv_main_rrwindow);
		mMainNearlylight = (ImageView) view.findViewById(R.id.iv_main_nearlylight);
		mMainFarlight = (ImageView) view.findViewById(R.id.iv_main_farlight);
		mMainWidelight = (ImageView) view.findViewById(R.id.iv_main_widelight);
		mMainStoplight = (ImageView) view.findViewById(R.id.iv_main_stoplight);
		mIcoIll = (ImageView) view.findViewById(R.id.iv_main_icoill);
		mIcoFarlight = (ImageView) view.findViewById(R.id.iv_main_icofarlight);
		mIcoBattery = (ImageView) view.findViewById(R.id.iv_main_icobattery);
		mIcoSafebelt = (ImageView) view.findViewById(R.id.iv_main_icosafebelt);
		mIcoWeather = (ImageView) view.findViewById(R.id.iv_main_icoweather);
		mIconOil = (ImageView) view.findViewById(R.id.iv_main_iconOil);
		
		text_speed = (TextView) view.findViewById(R.id.text_speed);
		
		mTvRemainDis = (TextView) view.findViewById(R.id.tv_remain_dis);
		mTvRemainTime = (TextView) view.findViewById(R.id.tv_remain_time);
		//mTvFxNavi = (TextView) this.findViewById(R.id.tv_fxnavi);
		mTvNaviRoad = (TextView) view.findViewById(R.id.tv_navi_nextroad);
		mTvNaviDis = (NaviFontTextView) view.findViewById(R.id.tv_next_dis);
		mNaviIconIv = (ImageView) view.findViewById(R.id.iv_navi_icon);
		mTvNextUnit = (TextView) view.findViewById(R.id.tv_navi_unit);
		mTvNaviTytle = (TextView) view.findViewById(R.id.tv_navi_tytle);
		 
//		mTvEngineSpeed = (CustomTextView) view.findViewById(R.id.engine_speed);
//		mTvRpm = (TextView) view.findViewById(R.id.text_rpm);
		
//		mIncludeWeather = view.findViewById(R.id.layout_weather);
		mIncludeNavi = view.findViewById(R.id.layout_navi);
		
//		iv_main_circle = (ImageView) view.findViewById(R.id.iv_main_circle);
//		mWeatherIv = (ImageView) view.findViewById(R.id.iv_weather);
//		mTvTemp = (WeatherFontTextView) view.findViewById(R.id.tv_temp);
//		mTvDay = (TextView) view.findViewById(R.id.tv_day);
////		mTvWeekDay = (TextView) view.findViewById(R.id.tv_weekday);
//		mTvWeather = (TextView) view.findViewById(R.id.tv_weather);
//		mTvFxWeather = (TextView) view.findViewById(R.id.tv_fxweather);
		
		mTvAverageSpeed = (CustomTextView) view.findViewById(R.id.tv_prot_averageSpeed);
		mTvDrivingTime = (CustomTextView) view.findViewById(R.id.tv_port_drivingTime);
		mTvRestOil = (CustomTextView) view.findViewById(R.id.tv_port_restOil);
		mTvBattery = (CustomTextView) view.findViewById(R.id.tv_port_battery);
		
//		mTvWarn = (TextView) view.findViewById(R.id.text_warn);
		
		mKuangView = (MyKuangView) view.findViewById(R.id.warnKuang);
		
		time.setToNow();
		/*mTvDay.setText((time.month+1)+"/"+time.monthDay);
		String weekDay = null;
		switch (time.weekDay) {
		case 0:
			weekDay = "星期日";
			break;
		case 1:
			weekDay = "星期一";
			break;
		case 2:
			weekDay = "星期二";
			break;
		case 3:
			weekDay = "星期三";
			break;
		case 4:
			weekDay = "星期四";
			break;
		case 5:
			weekDay = "星期五";
			break;
		case 6:
			weekDay = "星期六";
			break;
		default:
			break;
		}
		mTvFxWeather.setText(weekDay);*/
		
		mViewPager = (VerticalViewPager) view.findViewById(R.id.viewPager_home_right);
		/*View engineSpeedView = View.inflate(activity, R.layout.item_vertical_enginespeed, null);
		
		mTvEngineSpeed = (CustomTextView) engineSpeedView.findViewById(R.id.engine_speed);
		mTvRpm = (TextView) engineSpeedView.findViewById(R.id.text_rpm);
		
		View weatherView = View.inflate(activity, R.layout.activity_main_weather, null);
		mWeatherIv = (ImageView) weatherView.findViewById(R.id.iv_weather);
		mTvTemp = (WeatherFontTextView) weatherView.findViewById(R.id.tv_temp);
		mTvDay = (TextView) weatherView.findViewById(R.id.tv_day);
//		mTvWeekDay = (TextView) view.findViewById(R.id.tv_weekday);
		mTvWeather = (TextView) weatherView.findViewById(R.id.tv_weather);
		mTvFxWeather = (TextView) weatherView.findViewById(R.id.tv_fxweather);
		
		View batteryView = View.inflate(activity, R.layout.item_vertical_battery, null);
		text_value = (CustomTextView) batteryView.findViewById(R.id.vertical_battery);
		text_unit = (TextView) batteryView.findViewById(R.id.vertical_battery_unit);
		text_title = (TextView) batteryView.findViewById(R.id.vertical_battery_title);
		
		rightViews[0] = engineSpeedView;
		rightViews[1] = weatherView;
		rightViews[2] = batteryView;
		rightViews[3] = batteryView;*/
		
		mAdapter = new VerticalAdapter();
//		mViewPager.setOffscreenPageLimit(5);
		mViewPager.setAdapter(mAdapter);
//		mTvDay.setText((time.month+1)+"/"+time.monthDay);
		
//		int itemCount;
//		if (((Main)activity).getNetWorkState()) {
//			itemCount = 8;
//		}else {
//			itemCount = 7;
//		}
//		for (int i = 0; i < itemCount; i++) {
//			customTextViews.add(new CustomTextView(activity));
//		}
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int index) {
				
							
//				if (((Main)activity).getNetWorkState()) {
//					if (currentItem == 1 || currentItem == 5) {
//						netWorkable = true;
//					}
//				}else {
//					if (currentItem == 0 || currentItem == 4) {
//						netWorkable = true;
//					}
//				}
//				if (((MainActivity)activity).getNetWorkState()) {
//					currentItem = index-1;
//				}else{
//					currentItem = index;
//				}
				if(rightViews!=null&&rightViews.size() == 1){
					currentItem=index;
				}else{
					currentItem=index%rightViews.size();
				}
				
				if (currentItem >= 0) {
					System.out.println("currentItem =="+currentItem);
					boolean netWorkable = false;
//					String str = titleLists.get(currentItem);
//					if(activity.getString(R.string.text_engineSpeed).equals(str) ||
//							activity.getString(R.string.text_thisAverageSpeed).equals(str)){
//						netWorkable = true;
//					}
					if((rpmView != null && rightViews.get(currentItem) == rpmView)
							|| (averageSpeedView != null && rightViews.get(currentItem) == averageSpeedView)){
						netWorkable = true;
					}
//					boolean bound = VissCmdParse.getInstance().bBound;
//					boolean connect = VissCmdParse.getInstance().bConnect;
					CarPcInfo mCarPc = CarPcInfo.getInstance();
					/////ILL背光
//					if (bound && connect && mCarPc.getM_iILLAble() == 1) {
						if (mCarPc.getM_iILL() == 1) {
							if (isPort) {
								if (netWorkable) {
									img_right_circle.setImageResource(skinColourList[62]);//main_yibiaopan_port
								}else {
									img_right_circle.setImageResource(skinColourList[63]);//main_rightcircle_port
								}
							}else {
								if (netWorkable) {
									img_right_circle.setImageResource(skinColourList[64]);//main_yibiaopan
								}else {
									img_right_circle.setImageResource(skinColourList[65]);//main_rightcircle
								}
							}
						}else {
							if (isPort) {
								if (netWorkable) {
									img_right_circle.setImageResource(skinColourList[14]);//main_yibiaopan_port
								}else {
									img_right_circle.setImageResource(skinColourList[15]);//main_rightcircle_port
								}
							}else {
								if (netWorkable) {
									img_right_circle.setImageResource(skinColourList[16]);//main_yibiaopan
								}else {
									img_right_circle.setImageResource(skinColourList[17]);//main_rightcircle
								}
							}
						}
//					}else {
//						if (isPort) {
//							if (netWorkable) {
//								img_right_circle.setImageResource(skinColourList[14]);//main_yibiaopan_port
//							}else {
//								img_right_circle.setImageResource(skinColourList[15]);//main_rightcircle_port
//							}
//						}else {
//							if (netWorkable) {
//								img_right_circle.setImageResource(skinColourList[16]);//main_yibiaopan
//							}else {
//								img_right_circle.setImageResource(skinColourList[17]);//main_rightcircle
//							}
//						}
//					}
					
					mAdapter.notifyDataSetChanged();
					initSetCarPc(CarPcInfo.getInstance());
					initSetCar(CarInfo.getInstance());
				}
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
				if (arg0 == 0) {
					if (1-10*arg1 > 0 && 1-10*arg1 < 1) {
//						rightViews[arg0].setAlpha(1-10*arg1);
//						mAdapter.notifyDataSetChanged();
//						iv_main_circle.setAlpha(1-5*arg1);
//						mTvEngineSpeed.setAlpha(1-10*arg1);
//						mTvEngineSpeed.setAlpha(1-10*arg1);
//						mTvRpm.setAlpha(1-10*arg1);
					}
				}
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
				
			}
		});
	}

	private void initUnitLists() {
		
		//"rpm","V"/*,"%"*/,"℃","min","km/h","km"
		unitLists.clear();
		unitLists.add("rpm");
		unitLists.add("V");
		unitLists.add("%");
		unitLists.add("℃");
		unitLists.add("min");
		unitLists.add("km/h");
		unitLists.add("km");
			
		titleLists.clear();
		titleLists.add(activity.getString(R.string.text_engineSpeed));
		titleLists.add(activity.getString(R.string.text_batteryvoltage));
		titleLists.add(activity.getString(R.string.text_restoil));
		titleLists.add(activity.getString(R.string.text_coolanttemp));
		titleLists.add(activity.getString(R.string.text_thisDrivingTime));
		titleLists.add(activity.getString(R.string.text_thisAverageSpeed));
		titleLists.add(activity.getString(R.string.text_thisDrivingMileage));
		
		customTextViews.clear();
		for (int i = 0; i < unitLists.size(); i++) {
			customTextViews.add(new CustomTextView(activity));
		}
		
		
	}

	@Override
	public void onResume() {
		
		super.onResume();
		
		//获取皮肤图片资源 add by jiaqing.liu 
		skinColourList = TimeUtils.getInstance().getAllSkin(activity);
	}
	
	
	protected void setNaviInfo(Bundle bundle) {
		
		int time =  bundle.getInt("RemainTime");
		int h = time/3600;
		int m = (time-h*3600)/60;
		if ((time%60) >= 30) {
			m +=1;
		}
		
		mTvRemainTime.setText(String.format("%02d:%02d", h, m));
		mNaviIconIv.setImageResource(GuideNaviImage.naviImages[bundle.getInt("ICON")]);
		int nextdis = bundle.getInt("NextDis");
		if (nextdis < 1000) {
			mTvNaviDis.setText(nextdis+"");
			mTvNextUnit.setText(activity.getResources().getString(R.string.gaode_meter));
		} else {
			float nextDis = (float) ((nextdis/100)*0.1);
			mTvNaviDis.setText(nextDis+"");
			mTvNextUnit.setText(activity.getResources().getString(R.string.gaode_km));
		}
		
		if (nextdis < 2000) {
			mTvNaviTytle.setText(activity.getResources().getString(R.string.gaode_into));
			String strNextRoad = bundle.getString("NextRoad");
			mTvNaviRoad.setText(strNextRoad);
		} else {
			mTvNaviTytle.setText(activity.getResources().getString(R.string.gaode_along));
			String strCurRoad = bundle.getString("CurRoad");
			mTvNaviRoad.setText(strCurRoad);
		}
		
		
		int distance = bundle.getInt("Distance");
		if (distance < 1000) {
			mTvRemainDis.setText(distance+" m");
		} else {
			float mileage = (float) ((distance/100)*0.1);
			mTvRemainDis.setText(Float.toString(mileage)+" km");
		}
		
//		String strCurRoad = bundle.getString("CurRoad");
//		if (strCurRoad.equals(strNextRoad)) {
//			mTvNaviRoad.setText("进入"+strNextRoad);
//		} else {
//			mTvNaviRoad.setText("从"+strCurRoad+"\n\r"+"进入"+strNextRoad);
//		}
	}
	
	public void showInclueView() {
		
//		Log.i(TAG, "bInNavi ="+bInNavi+", bShowLimit ="+bShowLimit);
		if(WarnBroadcastReceiver.bInNavi){
//			mIncludeWeather.setVisibility(View.GONE);
			mIncludeNavi.setVisibility(View.VISIBLE);
			if (isPort) {
				img_right_circle.setImageResource(skinColourList[15]);//main_rightcircle_port
			}else {
				img_right_circle.setImageResource(skinColourList[17]);//main_rightcircle
			}
			mViewPager.setVisibility(View.GONE);
			//mTvFxNavi.setVisibility(View.VISIBLE);
		}else {
			mIncludeNavi.setVisibility(View.GONE);
			mViewPager.setVisibility(View.VISIBLE);
		}
		/*else if(UtilityClass.getPrefrenceBoolean(activity, "weather")){
			mIncludeNavi.setVisibility(View.GONE);
			mTvEngineSpeed.setVisibility(View.GONE);
			mTvRpm.setVisibility(View.GONE);
			//mTvFxNavi.setVisibility(View.GONE);
//			mIncludeWeather.setVisibility(View.VISIBLE);
			if (isPort) {
				iv_main_circle.setImageResource(R.drawable.main_rightcircle_port);
			}else {
				iv_main_circle.setImageResource(R.drawable.main_rightcircle);
			}
			System.out.println("=======设置天气");
		}else{
//			mIncludeWeather.setVisibility(View.GONE);
			mIncludeNavi.setVisibility(View.GONE);
			//mTvFxNavi.setVisibility(View.GONE);
			mTvEngineSpeed.setVisibility(View.VISIBLE);
			mTvRpm.setVisibility(View.VISIBLE);
			if (isPort) {
				iv_main_circle.setImageResource(R.drawable.main_yibiaopan_port);
			}else {
				iv_main_circle.setImageResource(R.drawable.main_yibiaopan);
			}
		}*/
	}
	
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.iv_main_basecar:
		case R.id.ib_main_carpc://行车电脑
			((MainActivity)activity).jumpTo(1);  
	    	   break;
       case R.id.ib_main_tpms://胎压 
		   ((MainActivity)activity).jumpTo(2);
    	   break;
       case R.id.iv_main_circle:
    	   if (WarnBroadcastReceiver.bInNavi) {
    		   //返回高德地圖
    		   Intent intent = new Intent();
    		   intent.setAction(ConstData.ACTION_GAODE_SEND);
    		   intent.putExtra("KEY_TYPE", 10034);
    		   intent.putExtra("SOURCE_APP", activity.getString(R.string.app_name));
    		   activity.sendBroadcast(intent);
    		   /*if (UtilityClass.getPrefrenceBoolean(activity, "weather")) {
					if (mTvEngineSpeed.getVisibility() == View.VISIBLE) {
						mIncludeNavi.setVisibility(View.GONE);
						mTvEngineSpeed.setVisibility(View.GONE);
						mTvRpm.setVisibility(View.GONE);
						//mTvFxNavi.setVisibility(View.GONE);
//						mIncludeWeather.setVisibility(View.VISIBLE);
						if (isPort) {
							iv_main_circle.setImageResource(R.drawable.main_rightcircle_port);
						}else {
							iv_main_circle.setImageResource(R.drawable.main_rightcircle);
						}
					}else {
//						mIncludeWeather.setVisibility(View.GONE);
						mIncludeNavi.setVisibility(View.GONE);
						//mTvFxNavi.setVisibility(View.GONE);
//						mTvEngineSpeed.setVisibility(View.VISIBLE);
//						mTvRpm.setVisibility(View.VISIBLE);
						if (isPort) {
							iv_main_circle.setImageResource(R.drawable.main_yibiaopan_port);
						}else {
							iv_main_circle.setImageResource(R.drawable.main_yibiaopan);
						}
					}
				}*/
    	   }
    	   break;
		default:
			break;
		}
	}
	
	private void initSetCarPc(final CarPcInfo mCarPc) {
		
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				setMainCarPc(mCarPc);
			}
		});
	}
	private void initSetCar(final CarInfo mCarInfo) {
		
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				setMainCarInfo(mCarInfo);
			}
		});
	}
	
	protected void setMainCarInfo(CarInfo mCarInfo) {
		
//		boolean bound = VissCmdParse.getInstance().bBound;
//		boolean connect = VissCmdParse.getInstance().bConnect;
		
//		if (bound && connect) {
			boolean bTpms = false;//
			switch (mCarInfo.getWarnLevel()) {
			case 1:
				if (mCarInfo.getLeftFront() == 1) {
					bTpms = true;
//					mIvMainTpms.setImageResource(R.drawable.main_red_lftire);
				}else if (mCarInfo.getRightFront() == 1) {
					bTpms = true;
//					mIvMainTpms.setImageResource(R.drawable.main_red_rftire);
				}else if (mCarInfo.getLeftRear() == 1) {
					bTpms = true;
//					mIvMainTpms.setImageResource(R.drawable.main_red_lrtire);
				}else if (mCarInfo.getRightRear() == 1) {
					bTpms = true;
//					mIvMainTpms.setImageResource(R.drawable.main_red_rrtire);
				}
				break;
			case 0:
				if (mCarInfo.getLeftFront() == 1) {
					bTpms = true;
//					mIvMainTpms.setImageResource(R.drawable.main_yellow_lftire);
				}else if (mCarInfo.getRightFront() == 1) {
					bTpms = true;
//					mIvMainTpms.setImageResource(R.drawable.main_yellow_rftire);
				}else if (mCarInfo.getLeftRear() == 1) {
					bTpms = true;
//					mIvMainTpms.setImageResource(R.drawable.main_yellow_lrtire);
				}else if (mCarInfo.getRightRear() == 1) {
					bTpms = true;
//					mIvMainTpms.setImageResource(R.drawable.main_yellow_rrtire);
				}
				break;
			default:
				break;
			}
			
			if (bTpms) {
//				mIvMainTpms.setVisibility(View.VISIBLE);
				mIcoMainTpms.setVisibility(View.VISIBLE);
			} else {
//				mIvMainTpms.setVisibility(View.INVISIBLE);
				mIcoMainTpms.setVisibility(View.INVISIBLE);
			}
//		} else {
////			mIvMainTpms.setVisibility(View.INVISIBLE);
//			mIcoMainTpms.setVisibility(View.INVISIBLE);
//		}
	}
	
	
	protected void setMainCarPc(CarPcInfo mCarPc) {//
		
//		boolean bound = VissCmdParse.getInstance().bBound;
//		boolean connect = VissCmdParse.getInstance().bConnect;
		boolean netWorkable = false;
		if(currentItem >= 0 && currentItem < titleLists.size()) {
			String str = titleLists.get(currentItem);
			if(activity.getString(R.string.text_engineSpeed).equals(str) ||
					activity.getString(R.string.text_thisAverageSpeed).equals(str)){
				netWorkable = true;
			}
		}
		
		/////ILL背光
		if (mCarPc.getM_iILLAble() == 1) {//bound && connect && 
			if (mCarPc.getM_iILL() == 1) {
				
				iv_mileage_bac.setImageResource(skinColourList[0]);//mileage_port_ill
				if (isPort) {
					rl_backGround.setBackgroundResource(skinColourList[1]);//bg_port_ill
					mIbMainCarpc.setImageResource(skinColourList[2]);//port_carpc_ill_selector
					mIbMainTpms.setImageResource(skinColourList[3]);//port_tpms_ill_selector
					iv_main_meter.setImageResource(skinColourList[62]);
					if (netWorkable) {
						img_right_circle.setImageResource(skinColourList[62]);//main_yibiaopan_port
					}else {
						img_right_circle.setImageResource(skinColourList[63]);//main_rightcircle_port
					}
				}else {
					mIbMainCarpc.setImageResource(skinColourList[4]);//main_carpc_ill_selector
					mIbMainTpms.setImageResource(skinColourList[5]);//main_tpms_ill_selector
					rl_backGround.setBackgroundResource(skinColourList[6]);//home_background_ill
					iv_main_meter.setImageResource(skinColourList[64]);
					if (netWorkable) {
						img_right_circle.setImageResource(skinColourList[64]);//main_yibiaopan
					}else {
						img_right_circle.setImageResource(skinColourList[65]);//main_rightcircle
					}
				}
			}else {
				iv_mileage_bac.setImageResource(skinColourList[7]);//mileage_port
				if (isPort) {
					rl_backGround.setBackgroundResource(skinColourList[8]);//bg_port
					mIbMainCarpc.setImageResource(skinColourList[9]);//port_carpc_selector
					mIbMainTpms.setImageResource(skinColourList[10]);//port_tpms_selector
					iv_main_meter.setImageResource(skinColourList[14]);
					if (netWorkable) {
						img_right_circle.setImageResource(skinColourList[14]);//main_yibiaopan_port
					}else {
						img_right_circle.setImageResource(skinColourList[15]);//main_rightcircle_port
					}
				}else {
					rl_backGround.setBackgroundResource(skinColourList[11]);//home_background
					mIbMainCarpc.setImageResource(skinColourList[12]);//main_carpc_selector
					mIbMainTpms.setImageResource(skinColourList[13]);//main_tpms_selector
					iv_main_meter.setImageResource(skinColourList[16]);
					if (netWorkable) {
						img_right_circle.setImageResource(skinColourList[16]);//main_yibiaopan
					}else {
						img_right_circle.setImageResource(skinColourList[17]);//main_rightcircle
					}
				}
			}
		}else {
			iv_mileage_bac.setImageResource(skinColourList[7]);//mileage_port
			if (isPort) {
				rl_backGround.setBackgroundResource(skinColourList[8]);//bg_port
				mIbMainCarpc.setImageResource(skinColourList[9]);//port_carpc_selector
				mIbMainTpms.setImageResource(skinColourList[10]);//port_tpms_selector
				iv_main_meter.setImageResource(skinColourList[14]);
				if (netWorkable) {
					img_right_circle.setImageResource(skinColourList[14]);//main_yibiaopan_port
				}else {
					img_right_circle.setImageResource(skinColourList[15]);//main_rightcircle_port
				}
			}else {
				rl_backGround.setBackgroundResource(skinColourList[11]);//home_background
				mIbMainCarpc.setImageResource(skinColourList[12]);//main_carpc_selector
				mIbMainTpms.setImageResource(skinColourList[13]);//main_tpms_selector
				iv_main_meter.setImageResource(skinColourList[16]);
				if (netWorkable) {
					img_right_circle.setImageResource(skinColourList[16]);//main_yibiaopan
				}else {
					img_right_circle.setImageResource(skinColourList[17]);//main_rightcircle
				}
			}
		}
		
		//20171129 modify 不判断连接状态
		//里程
		if (mCarPc.getM_iTotalMileageAble() == 1){//&& connect && bound  
			mTvMainTrip.setText(activity.getString(R.string.main_trip)+mCarPc.getM_iSelfstart_mileage()+" km");
			mTvMainMileage.setText(activity.getString(R.string.main_mieage)+mCarPc.getM_iTotalMileage()+" km");
		}else{
			mTvMainTrip.setText(activity.getString(R.string.main_trip)+" - -");
			mTvMainMileage.setText(activity.getString(R.string.main_mieage)+" - -");
		}
		
		int temp = 0;
		//小计里程-平均车速
		if (mCarPc.getM_iLittleTripAble() == 1) {//&& connect && bound 
			temp = mCarPc.getM_fTripmeter_avgspeed();
			mTvAverageSpeed.setText(temp+" ");
		}else{
			mTvAverageSpeed.setText("- -");
		}
		
		
		float ff = 0;
		System.out.println("=======初始化电池电压");
		//电池电压
		if (mCarPc.getM_iBatteryVolAble() == 1) {//&& connect && bound 
			ff = mCarPc.getM_fBatteryVol();
			mTvBattery.setText(Float.toString(ff)+" ");
		}else{
			mTvBattery.setText("- -");
		}
		
		//剩余油量
		System.out.println("=======初始化剩余油量");
		if (mCarPc.getM_iResidualOilAble() == 1) {//&& connect && bound 
			temp = mCarPc.getM_iResidualOil();
			mTvRestOil.setText(temp+"%");
			if (temp > 15) {
				mTvRestOil.setTextColor(Color.parseColor("#FFFFFF"));
				mIconOil.setVisibility(View.INVISIBLE);
			}else{
				mTvRestOil.setTextColor(Color.parseColor("#ECBE11"));
				mIconOil.setVisibility(View.VISIBLE);
			}
		}else {
			mIconOil.setVisibility(View.INVISIBLE);
			mTvRestOil.setTextColor(Color.parseColor("#FFFFFF"));
			mTvRestOil.setText("- -");
		}
		
		//行驶时长
		if (mCarPc.getM_iLittleTripAble() == 1) {// && connect && bound
			temp = mCarPc.getM_iTripmeter_drivertime();
			mTvDrivingTime.setText(temp+"min");
		}else {
			mTvDrivingTime.setText("- -");
		}
		//车速
		if (mCarPc.getM_iSpeedAble() == 1){//bound && connect && 
			int iSpeed = (int) mCarPc.getM_fInstantSpeed();
			if (mCarPc.getM_iACC() == 0 || iSpeed > 240 || iSpeed < 0) {
				iSpeed = 0;
			}
			setSpeedImage(iSpeed);
		}else{
			setSpeedImage(0);
		}
		
//		mAdapter.notifyDataSetChanged();
		/*if (bound && connect && mCarPc.getM_iEnigineSpeedAble() == 1) {
			mTvEngineSpeed.setText(mCarPc.getM_iEngineSpeed()+"");
		}else {
			mTvEngineSpeed.setText(0+"");
		}*/
		//左侧仪表小图标
		if (mCarPc.getM_iGearAble() == 1) {//bound && connect && 
			switch (mCarPc.getM_iGear()) {
			case 1:
				mIvMainGear.setImageResource(R.drawable.main_gearp);
				mIvMainGear.setVisibility(View.VISIBLE);
				break;
			case 2:
				mIvMainGear.setImageResource(R.drawable.main_gearn);
				mIvMainGear.setVisibility(View.VISIBLE);
				break;
			case 3:
				mIvMainGear.setImageResource(R.drawable.main_gearr);
				mIvMainGear.setVisibility(View.VISIBLE);
				break;
			case 4:
				mIvMainGear.setImageResource(R.drawable.main_geard);
				mIvMainGear.setVisibility(View.VISIBLE);
				break;
			case 5:
				mIvMainGear.setImageResource(R.drawable.main_gears);
				mIvMainGear.setVisibility(View.VISIBLE);
				break;
			case 6:
				mIvMainGear.setImageResource(R.drawable.main_gearl);
				mIvMainGear.setVisibility(View.VISIBLE);
				break;
			default:
				break;
			}
		}else{
			mIvMainGear.setVisibility(View.INVISIBLE);
		}
		
		if (mCarPc.getM_iHandbrakeAble() == 1 && mCarPc.getM_iHanderBrake() == 1) {//bound && connect && 
			mIvMainShake.setVisibility(View.VISIBLE);
		} else {
			mIvMainShake.setVisibility(View.INVISIBLE);
		}
		
		//左右转和双闪
//		if (bound && connect) {
			if (mCarPc.getM_iDoubleLight() == 1) {
				mIcoMainDoublelight.setVisibility(View.VISIBLE);
			}else {
				mIcoMainDoublelight.setVisibility(View.INVISIBLE);
			}
			if (!bRunnable && (mCarPc.getM_iDoubleLight() == 0x01||mCarPc.getM_iLeftTrunLight() == 0x01
				||mCarPc.getM_iRightTrunLight() == 0x01)) {
				bRunnable = true;
				mHandler.removeMessages(ConstData.MSG_GET_DOUBLELIGHT);
				mHandler.sendEmptyMessage(ConstData.MSG_GET_DOUBLELIGHT);
			} else if (mCarPc.getM_iDoubleLight() == 0x00 &&mCarPc.getM_iLeftTrunLight() == 0x00
					&&mCarPc.getM_iRightTrunLight() == 0x00 && bRunnable){
				
				bRunnable = false;
				mHandler.removeMessages(ConstData.MSG_GET_DOUBLELIGHT);
				hideDoubleLight();
			}
//		} else {
//			bRunnable = false;
//			mHandler.removeMessages(ConstData.MSG_GET_DOUBLELIGHT);
//			hideDoubleLight();
//			mIcoMainDoublelight.setVisibility(View.INVISIBLE);
//		}
		
		//车门
		if (mCarPc.getM_iDoorAble() == 1) {//bound && connect && 
			if (mCarPc.getM_iLFDoor() == 1) {
				//mMainLfdoor.setImageResource(R.drawable.main_lfdoor_open);
				mMainLfdoor.setVisibility(View.INVISIBLE);
				mMainLfdoorOpen.setVisibility(View.VISIBLE);
			} else {
				//mMainLfdoor.setImageResource(R.drawable.main_lfdoor_close);
				mMainLfdoor.setVisibility(View.VISIBLE);
				mMainLfdoorOpen.setVisibility(View.INVISIBLE);
			}
			if (mCarPc.getM_iLRDoor() == 1) {
				//mMainLfdoor.setImageResource(R.drawable.main_lrdoor_open);
				mMainLrdoor.setVisibility(View.INVISIBLE);
				mMainLrdoorOpen.setVisibility(View.VISIBLE);
			} else {
				//mMainLfdoor.setImageResource(R.drawable.main_lrdoor_close);
				mMainLrdoor.setVisibility(View.VISIBLE);
				mMainLrdoorOpen.setVisibility(View.INVISIBLE);
			}
			if (mCarPc.getM_iRFDoor() == 1) {
				//mMainLfdoor.setImageResource(R.drawable.main_rfdoor_open);
				mMainRfdoor.setVisibility(View.INVISIBLE);
				mMainRfdoorOpen.setVisibility(View.VISIBLE);
			} else {
				//mMainLfdoor.setImageResource(R.drawable.main_rfdoor_close);
				mMainRfdoor.setVisibility(View.VISIBLE);
				mMainRfdoorOpen.setVisibility(View.INVISIBLE);
			}
			if (mCarPc.getM_iRRDoor() == 1) {
				//mMainLfdoor.setImageResource(R.drawable.main_rrdoor_open);
				mMainRrdoor.setVisibility(View.INVISIBLE);
				mMainRrdoorOpen.setVisibility(View.VISIBLE);
			} else {
				//mMainLfdoor.setImageResource(R.drawable.main_rrdoor_close);
				mMainRrdoor.setVisibility(View.VISIBLE);
				mMainRrdoorOpen.setVisibility(View.INVISIBLE);
			}
			if (mCarPc.getM_iHood() == 1) {
				mMainHood.setVisibility(View.VISIBLE);
			} else {
				mMainHood.setVisibility(View.INVISIBLE);
			}
			if (mCarPc.getM_iTailBox() == 1) {
				mMainTaixbox.setVisibility(View.VISIBLE);
			} else {
				mMainTaixbox.setVisibility(View.INVISIBLE);
			}
		} else {
//			mMainLfdoor.setImageResource(R.drawable.main_lfdoor_close);
//			mMainLrdoor.setImageResource(R.drawable.main_lrdoor_close);
//			mMainRfdoor.setImageResource(R.drawable.main_rfdoor_close);
//			mMainRrdoor.setImageResource(R.drawable.main_rrdoor_close);
			mMainLfdoor.setVisibility(View.VISIBLE);
			mMainLfdoorOpen.setVisibility(View.INVISIBLE);
			mMainLrdoor.setVisibility(View.VISIBLE);
			mMainLrdoorOpen.setVisibility(View.INVISIBLE);
			mMainRfdoor.setVisibility(View.VISIBLE);
			mMainRfdoorOpen.setVisibility(View.INVISIBLE);
			mMainRrdoor.setVisibility(View.VISIBLE);
			mMainRrdoorOpen.setVisibility(View.INVISIBLE);
			
			mMainHood.setVisibility(View.INVISIBLE);
			mMainTaixbox.setVisibility(View.INVISIBLE);
		}
		
		//车窗
		if (mCarPc.getM_iWindowAble() == 1) {//bound && connect && 
			if (mCarPc.getM_iLFWindow() == 1 && mCarPc.getM_iLFDoor() == 0) {
				mMainLfwindow.setVisibility(View.VISIBLE);
			} else {
				mMainLfwindow.setVisibility(View.INVISIBLE);
			}
			if (mCarPc.getM_iRFWindow() == 1 && mCarPc.getM_iRFDoor() == 0) {
				mMainRfwindow.setVisibility(View.VISIBLE);
			} else {
				mMainRfwindow.setVisibility(View.INVISIBLE);
			}
			if (mCarPc.getM_iLRWindow() == 1 && mCarPc.getM_iLRDoor() == 0) {
				mMainLrwindow.setVisibility(View.VISIBLE);
			} else {
				mMainLrwindow.setVisibility(View.INVISIBLE);
			}
			if (mCarPc.getM_iRRWindow() == 1 && mCarPc.getM_iRRDoor() == 0) {
				mMainRrwindow.setVisibility(View.VISIBLE);
			} else {
				mMainRrwindow.setVisibility(View.INVISIBLE);
			}
		} else {
			mMainLfwindow.setVisibility(View.INVISIBLE);
			mMainRfwindow.setVisibility(View.INVISIBLE);
			mMainLrwindow.setVisibility(View.INVISIBLE);
			mMainRrwindow.setVisibility(View.INVISIBLE);
		}
		
		//车灯
		if (mCarPc.getM_iOutLightAble() == 1) {//bound && connect && 
			if (mCarPc.getM_iFarLight() == 1) {
				mMainFarlight.setVisibility(View.VISIBLE);
				mIcoFarlight.setVisibility(View.VISIBLE);
			} else {
				mMainFarlight.setVisibility(View.INVISIBLE);
				mIcoFarlight.setVisibility(View.INVISIBLE);
			}
			if (mCarPc.getM_iNearLight() == 1) {
				mMainNearlylight.setVisibility(View.VISIBLE);
			} else {
				mMainNearlylight.setVisibility(View.INVISIBLE);
			}
			if (mCarPc.getM_iWideLight() == 1) {
				mMainWidelight.setVisibility(View.VISIBLE);
			} else {
				mMainWidelight.setVisibility(View.INVISIBLE);
			}
			if (mCarPc.getM_iStopLight() == 1) {
				mMainStoplight.setVisibility(View.VISIBLE);
			} else {
				mMainStoplight.setVisibility(View.INVISIBLE);
			}
		} else {
			mMainFarlight.setVisibility(View.INVISIBLE);
			mIcoFarlight.setVisibility(View.INVISIBLE);
			mMainNearlylight.setVisibility(View.INVISIBLE);
			mMainWidelight.setVisibility(View.INVISIBLE);
			mMainStoplight.setVisibility(View.INVISIBLE);
		}
		//ill
		if (mCarPc.getM_iILLAble() == 1 && mCarPc.getM_iILL() == 1) {//bound && connect && 
			mIcoIll.setVisibility(View.VISIBLE);
		} else {
			mIcoIll.setVisibility(View.INVISIBLE);
		}
		//安全带
		if (mCarPc.getM_iLFSafebeltAble() == 1) {//bound && connect && 
			//System.out.println( "mCarPc.getM_iACC() = "+mCarPc.getM_iACC()+", bSafeBelt ="+bSafeBelt);
			if (mCarPc.getM_iLFSafebelt() == 0 && mCarPc.getM_iACC() == 1) {
				//System.out.println( "mCarPc.getM_fInstantSpeed() = "+mCarPc.getM_fInstantSpeed()+", mCarPc.getM_iSpeedAble() ="+mCarPc.getM_iSpeedAble());
				if (mCarPc.getM_fInstantSpeed() > 15 && mCarPc.getM_iSpeedAble() == 1) {
					 if ( !bSafeBelt) {
						bSafeBelt = true;
						mHandler.removeMessages(ConstData.MSG_GET_SAFEBELT);
						mHandler.sendEmptyMessage(ConstData.MSG_GET_SAFEBELT);
					 }
				} else {
					mHandler.removeMessages(ConstData.MSG_GET_SAFEBELT);
					mIcoSafebelt.setVisibility(View.VISIBLE);
					bSafeBelt = false;
				}
			} else {
				mHandler.removeMessages(ConstData.MSG_GET_SAFEBELT);
				mIcoSafebelt.setVisibility(View.INVISIBLE);
				bSafeBelt = false;
			}
		} else {
			mHandler.removeMessages(ConstData.MSG_GET_SAFEBELT);
			mIcoSafebelt.setVisibility(View.INVISIBLE);
			bSafeBelt = false;
		}
		
		//电池电压和水温
//		if (bound && connect) {
			if (mCarPc.getM_iBatteryVolAble() == 1 && mCarPc.getM_iEngineSpeed() ==0 &&
					mCarPc.getM_iACC() == 1) {
				mIcoBattery.setVisibility(View.VISIBLE);
			} else {
				mIcoBattery.setVisibility(View.INVISIBLE);
			}
			if (mCarPc.getM_iCooltempAble() == 1 && mCarPc.getM_iCooltemp() > 110) {
				mIcoWeather.setVisibility(View.VISIBLE);
			} else {
				mIcoWeather.setVisibility(View.INVISIBLE);
			}
//		} else {
//			mIcoBattery.setVisibility(View.INVISIBLE);
//			mIcoWeather.setVisibility(View.INVISIBLE);
//		}
		
//		if (!connect || !bound ) {
			if (mKuangView != null) {
				setWarnKuang("","#66C6FCFF", false, 0, View.GONE);
			}
//		}
	
		//发动机转速
		if (mCarPc.getM_iEnigineSpeedAble() == 1) {
			if (!unitLists.contains("rpm")) {
				unitLists.add(0, "rpm");
				titleLists.add(0, activity.getString(R.string.text_engineSpeed));
			}
			if(!rightViews.contains(rpmView)){
				rpmView = View.inflate(activity, R.layout.item_vertical_battery, null);
				((TextView)rpmView.findViewById(R.id.vertical_battery_unit)).setText("rpm");
				((TextView)rpmView.findViewById(R.id.vertical_battery_title)).setText(R.string.text_engineSpeed);
				((CustomTextView)rpmView.findViewById(R.id.vertical_battery)).setText(mCarPc.getM_iEngineSpeed()+"");
				rightViews.add(rpmView);
			}else{
				((TextView)rpmView.findViewById(R.id.vertical_battery_unit)).setText("rpm");
				((TextView)rpmView.findViewById(R.id.vertical_battery_title)).setText(R.string.text_engineSpeed);
				((CustomTextView)rpmView.findViewById(R.id.vertical_battery)).setText(mCarPc.getM_iEngineSpeed()+"");
			}
		}else{
			unitLists.remove("rpm");
			titleLists.remove(activity.getString(R.string.text_engineSpeed));
			
			if(rightViews.contains(rpmView)){
				rightViews.remove(rpmView);
			}
		}
		//电池电压
		if (mCarPc.getM_iBatteryVolAble() == 1) {
			if (!unitLists.contains("V")) {
				unitLists.add("V");
				titleLists.add(activity.getString(R.string.text_batteryvoltage));
			}
			
			if(!rightViews.contains(dianyaView)){
				dianyaView = View.inflate(activity, R.layout.item_vertical_battery, null);
				((TextView)dianyaView.findViewById(R.id.vertical_battery_unit)).setText("V");
				((TextView)dianyaView.findViewById(R.id.vertical_battery_title)).setText(R.string.text_batteryvoltage);
				((CustomTextView)dianyaView.findViewById(R.id.vertical_battery)).setText(mCarPc.getM_fBatteryVol()+"");
				rightViews.add(dianyaView);
			}else{
				((TextView)dianyaView.findViewById(R.id.vertical_battery_unit)).setText("V");
				((TextView)dianyaView.findViewById(R.id.vertical_battery_title)).setText(R.string.text_batteryvoltage);
				((CustomTextView)dianyaView.findViewById(R.id.vertical_battery)).setText(mCarPc.getM_fBatteryVol()+"");
			}
		}else{
			unitLists.remove("V");
			titleLists.remove(activity.getString(R.string.text_batteryvoltage));
			if(rightViews.contains(dianyaView)){
				rightViews.remove(dianyaView);
			}
		}
		//油量信息
		if (mCarPc.getM_iResidualOilAble() == 1) {
			if (!unitLists.contains("%")) {
				unitLists.add("%");
				titleLists.add(activity.getString(R.string.text_restoil));
			}
			if(!rightViews.contains(oilView)){
				oilView = View.inflate(activity, R.layout.item_vertical_battery, null);
				((TextView)oilView.findViewById(R.id.vertical_battery_unit)).setText("%");
				((TextView)oilView.findViewById(R.id.vertical_battery_title)).setText(R.string.text_restoil);
				((CustomTextView)oilView.findViewById(R.id.vertical_battery)).setText(mCarPc.getM_iResidualOil()+"");
				rightViews.add(oilView);
			}else{
				((TextView)oilView.findViewById(R.id.vertical_battery_unit)).setText("%");
				((TextView)oilView.findViewById(R.id.vertical_battery_title)).setText(R.string.text_restoil);
				((CustomTextView)oilView.findViewById(R.id.vertical_battery)).setText(mCarPc.getM_iResidualOil()+"");
			}	
		}else{
			unitLists.remove("%");
			titleLists.remove(activity.getString(R.string.text_restoil));
			if(rightViews.contains(oilView)){
				rightViews.remove(oilView);
			}
		}
		//水温
		if (mCarPc.getM_iCooltempAble() == 1) {
			if (!unitLists.contains("℃")) {
				unitLists.add("℃");
				titleLists.add(activity.getString(R.string.text_coolanttemp));
			}
			if(!rightViews.contains(coolanttempView)){
				coolanttempView = View.inflate(activity, R.layout.item_vertical_battery, null);
				((TextView)coolanttempView.findViewById(R.id.vertical_battery_unit)).setText("℃");
				((TextView)coolanttempView.findViewById(R.id.vertical_battery_title)).setText(R.string.text_coolanttemp);
				((CustomTextView)coolanttempView.findViewById(R.id.vertical_battery)).setText(mCarPc.getM_iCooltemp()+"");
				rightViews.add(coolanttempView);
			}else{
				((TextView)coolanttempView.findViewById(R.id.vertical_battery_unit)).setText("℃");
				((TextView)coolanttempView.findViewById(R.id.vertical_battery_title)).setText(R.string.text_coolanttemp);
				((CustomTextView)coolanttempView.findViewById(R.id.vertical_battery)).setText(mCarPc.getM_iCooltemp()+"");
			}	
		}else{
			unitLists.remove("℃");
			titleLists.remove(activity.getString(R.string.text_coolanttemp));
			if(rightViews.contains(coolanttempView)){
				rightViews.remove(coolanttempView);
			}
		}
		//本次行程
		if (mCarPc.getM_iSelfStartMileageAble() == 1) {
			if(!rightViews.contains(drivingTimeView)){
				drivingTimeView = View.inflate(activity, R.layout.item_vertical_battery, null);
				((TextView)drivingTimeView.findViewById(R.id.vertical_battery_unit)).setText("min");
				((TextView)drivingTimeView.findViewById(R.id.vertical_battery_title)).setText(R.string.text_thisDrivingTime);
				((CustomTextView)drivingTimeView.findViewById(R.id.vertical_battery)).setText(mCarPc.getM_iSelfstart_drivertime()+"");
				rightViews.add(drivingTimeView);
			}else{
				((TextView)drivingTimeView.findViewById(R.id.vertical_battery_unit)).setText("min");
				((TextView)drivingTimeView.findViewById(R.id.vertical_battery_title)).setText(R.string.text_thisDrivingTime);
				((CustomTextView)drivingTimeView.findViewById(R.id.vertical_battery)).setText(mCarPc.getM_iSelfstart_drivertime()+"");
			}
			
			if(!rightViews.contains(averageSpeedView)){
				averageSpeedView = View.inflate(activity, R.layout.item_vertical_battery, null);
				((TextView)averageSpeedView.findViewById(R.id.vertical_battery_unit)).setText("km/h");
				((TextView)averageSpeedView.findViewById(R.id.vertical_battery_title)).setText(R.string.text_thisAverageSpeed);
				((CustomTextView)averageSpeedView.findViewById(R.id.vertical_battery)).setText(mCarPc.getM_fSelfstart_avgspeed()+"");
				rightViews.add(averageSpeedView);
			}else{
				((TextView)averageSpeedView.findViewById(R.id.vertical_battery_unit)).setText("km/h");
				((TextView)averageSpeedView.findViewById(R.id.vertical_battery_title)).setText(R.string.text_thisAverageSpeed);
				((CustomTextView)averageSpeedView.findViewById(R.id.vertical_battery)).setText(mCarPc.getM_fSelfstart_avgspeed()+"");
			}
			
			if(!rightViews.contains(drivingMileageView)){
				drivingMileageView = View.inflate(activity, R.layout.item_vertical_battery, null);
				((TextView)drivingMileageView.findViewById(R.id.vertical_battery_unit)).setText("km");
				((TextView)drivingMileageView.findViewById(R.id.vertical_battery_title)).setText(R.string.text_thisDrivingMileage);
				((CustomTextView)drivingMileageView.findViewById(R.id.vertical_battery)).setText(mCarPc.getM_iSelfstart_mileage()+"");
				rightViews.add(drivingMileageView);
			}else{
				((TextView)drivingMileageView.findViewById(R.id.vertical_battery_unit)).setText("km");
				((TextView)drivingMileageView.findViewById(R.id.vertical_battery_title)).setText(R.string.text_thisDrivingMileage);
				((CustomTextView)drivingMileageView.findViewById(R.id.vertical_battery)).setText(mCarPc.getM_iSelfstart_mileage()+"");
			}
			
			
			if (!unitLists.contains("min")) {
				unitLists.add("min");
				titleLists.add(activity.getString(R.string.text_thisDrivingTime));
			}
			if (!unitLists.contains("km/h")) {
				unitLists.add("km/h");
				titleLists.add(activity.getString(R.string.text_thisAverageSpeed));
			}
			if (!unitLists.contains("km")) {
				unitLists.add("km");
				titleLists.add(activity.getString(R.string.text_thisDrivingMileage));
			}
		}else{
			unitLists.remove("min");
			titleLists.remove(activity.getString(R.string.text_thisDrivingTime));
			
			unitLists.remove("km/h");
			titleLists.remove(activity.getString(R.string.text_thisAverageSpeed));
			
			unitLists.remove("km");
			titleLists.remove(activity.getString(R.string.text_thisDrivingMileage));
			
			if(rightViews.contains(drivingTimeView)){
				rightViews.remove(drivingTimeView);
			}
			if(rightViews.contains(averageSpeedView)){
				rightViews.remove(averageSpeedView);
			}
			if(rightViews.contains(drivingMileageView)){
				rightViews.remove(drivingMileageView);
			}
		}
		if(!isFirst){
			isFirst = true;
			mViewPager.setCurrentItem(300);
		}
		mAdapter.notifyDataSetChanged();
		
//		if ( bound) {//connect &&
			
			System.out.println("currentItem ="+currentItem+", size ="+titleLists.size());
			if (currentItem >= 0 && titleLists.size() > 0 && (currentItem < titleLists.size())) {
				String titleName = titleLists.get(currentItem);
				if (activity.getString(R.string.text_engineSpeed).equals(titleName)) {
					customTextViews.get(currentItem).setText(mCarPc.getM_iEngineSpeed()+"");
				}else if (activity.getString(R.string.text_batteryvoltage).equals(titleName)) {
					customTextViews.get(currentItem).setText(mCarPc.getM_fBatteryVol()+"");
				}else if (activity.getString(R.string.text_restoil).equals(titleName)) {
					customTextViews.get(currentItem).setText(mCarPc.getM_iResidualOil()+"");
				}else if (activity.getString(R.string.text_coolanttemp).equals(titleName)) {
					customTextViews.get(currentItem).setText(mCarPc.getM_iCooltemp()+"");
				}else if (activity.getString(R.string.text_thisDrivingTime).equals(titleName)) {
					customTextViews.get(currentItem).setText(mCarPc.getM_iSelfstart_drivertime()+"");
				}else if (activity.getString(R.string.text_thisAverageSpeed).equals(titleName)) {
					customTextViews.get(currentItem).setText(mCarPc.getM_fSelfstart_avgspeed()+"");
				}else if (activity.getString(R.string.text_thisDrivingMileage).equals(titleName)) {
					customTextViews.get(currentItem).setText(mCarPc.getM_iSelfstart_mileage()+"");
				}
			}

//		}else{
//			if (currentItem >= 0 && titleLists.size() > 0) {
//				customTextViews.get(currentItem).setText("- -");
//			}
//			
//		}
		
		
//		boolean netWorkable = ((Main)activity).getNetWorkState();
//		String value = "- -";
//		boolean flag = false;
//		switch (currentItem) {
//		case 0:
//			if (!netWorkable) {
//				if (mCarPc.getM_iEnigineSpeedAble() == 1) {
//					value = mCarPc.getM_iEngineSpeed()+"";
//					flag = true;
//				}
//			}
//			break;
//		case 1:
//			if (netWorkable) {
//				if (mCarPc.getM_iEnigineSpeedAble() == 1) {
//					value = mCarPc.getM_iEngineSpeed()+"";
//					flag = true;
//				}
//			}else {
//				if (mCarPc.getM_iBatteryVolAble() == 1) {
//					float ff2 = mCarPc.getM_fBatteryVol();
//					value = ff2+"";
//					flag = true;
//				}
//			}
//			break;
//		case 2:
//			if (netWorkable) {
//				if (mCarPc.getM_iBatteryVolAble() == 1) {
//					float ff1 = mCarPc.getM_fBatteryVol();
//					value = ff1+"";
//					flag = true;
//				}
//			}else {
//				if (mCarPc.getM_iCooltempAble() == 1) {
//					value = mCarPc.getM_iCooltemp()+"";
//					flag = true;
//				}
//			}
//			break;
//		/*case 3:
//			if (netWorkable) {
//				if (mCarPc.getM_iResidualOilAble() == 1) {
//					value = mCarPc.getM_iResidualOil()+"";
//					flag = true;
//				}
//			}else {
//				if (mCarPc.getM_iCooltempAble() == 1) {
//					value = mCarPc.getM_iCooltemp()+"";
//					flag = true;
//				}
//			}
//			break;*/
//		case 3:
//			if (netWorkable) {
//				if (mCarPc.getM_iCooltempAble() == 1) {
//					value = mCarPc.getM_iCooltemp()+"";
//					flag = true;
//				}
//			}else {
//				if (mCarPc.getM_iSelfStartMileageAble() == 1) {
//					value = mCarPc.getM_iSelfstart_drivertime()+"";
//					flag = true;
//				}
//			}
//			break;
//		case 4:
//			if (netWorkable ) {
//				if (mCarPc.getM_iSelfStartMileageAble() == 1) {
//					value = mCarPc.getM_iSelfstart_drivertime()+"";
//					flag = true;
//				}
//			}else {
//				if (mCarPc.getM_iSelfStartMileageAble() == 1) {
//					value = mCarPc.getM_fSelfstart_avgspeed()+"";
//					flag = true;
//				}
//			}
//			break;
//		case 5:
//			if (netWorkable) {
//				if (mCarPc.getM_iSelfStartMileageAble() == 1) {
//					value = mCarPc.getM_fSelfstart_avgspeed()+"";
//					flag = true;
//				}
//			}else {
//				if (mCarPc.getM_iSelfStartMileageAble() == 1) {
//					value = mCarPc.getM_iSelfstart_mileage()+"";
//					flag = true;
//				}
//			}
//			break;
//		case 6:
//			if (netWorkable) {
//				if (mCarPc.getM_iSelfStartMileageAble() == 1) {
//					value = mCarPc.getM_iSelfstart_mileage()+"";
//					flag = true;
//				}
//			}
//			break;
//		default:
//			break;
//		}
//		setCustomTextValue(flag, value, currentItem);
	}
	
//	private void setCustomTextValue(boolean flag,String value,int position){
//		if (position >= 0 && position <= customTextViewLists.size()) {
//			if (customTextViewLists.get(position) != null) {
//				if (flag && VissCmdParse.getInstance().bBound && VissCmdParse.getInstance().bConnect) {
//					customTextViewLists.get(position).setText(value);
//				}else {
//					customTextViewLists.get(position).setText("- -");
//				}
//			}
//		}
//	}
	
	private void hideDoubleLight() {
		
		//mIcoMainDoublelight.setVisibility(View.INVISIBLE);
		mIcoMainLeftlight.setVisibility(View.INVISIBLE);
		mIcoMainRightlight.setVisibility(View.INVISIBLE);
		mIvMainLeftlight.setVisibility(View.INVISIBLE);
		mIvMainRightlight.setVisibility(View.INVISIBLE);
	}
	
	private void setSpeedImage(int speed) {
		
		if (speed > 120) {
			text_speed.setTextColor(Color.parseColor("#FF0000"));
		}else {
			text_speed.setTextColor(Color.parseColor("#FFFFFF"));
		}
		text_speed.setText(speed+"");
		/*if (speed < 10) {
			mIvMainSpeedHigh.setVisibility(View.GONE);
			mIvMainSpeedMiddle.setVisibility(View.GONE);
			mIvMainSpeedLow.setVisibility(View.VISIBLE);
			setSingleSpeedImage(mIvMainSpeedLow, speed);
		}else if (speed >= 100){
			mIvMainSpeedHigh.setVisibility(View.VISIBLE);
			mIvMainSpeedMiddle.setVisibility(View.VISIBLE);
			mIvMainSpeedLow.setVisibility(View.VISIBLE);
			setSingleSpeedImage(mIvMainSpeedHigh, speed/100);
			setSingleSpeedImage(mIvMainSpeedMiddle, (speed%100)/10);
			setSingleSpeedImage(mIvMainSpeedLow, speed%10);
		}else{
			mIvMainSpeedHigh.setVisibility(View.GONE);
			mIvMainSpeedMiddle.setVisibility(View.VISIBLE);
			mIvMainSpeedLow.setVisibility(View.VISIBLE);
			setSingleSpeedImage(mIvMainSpeedMiddle, speed/10);
			setSingleSpeedImage(mIvMainSpeedLow, speed%10);
		}*/
	}
	
/*	private void setSingleSpeedImage(ImageView iv, int single) {
		
		switch (single) {
		case 0:
			iv.setImageResource(R.drawable.main_meter_speed0);
			break;
		case 1:
			iv.setImageResource(R.drawable.main_meter_speed1);
			break;
		case 2:
			iv.setImageResource(R.drawable.main_meter_speed2);
			break;
		case 3:
			iv.setImageResource(R.drawable.main_meter_speed3);
			break;
		case 4:
			iv.setImageResource(R.drawable.main_meter_speed4);
			break;
		case 5:
			iv.setImageResource(R.drawable.main_meter_speed5);
			break;
		case 6:
			iv.setImageResource(R.drawable.main_meter_speed6);
			break;
		case 7:
			iv.setImageResource(R.drawable.main_meter_speed7);
			break;
		case 8:
			iv.setImageResource(R.drawable.main_meter_speed8);
			break;
		case 9:
			iv.setImageResource(R.drawable.main_meter_speed9);
			break;
		default:
			break;
		}
	}*/
	
	private BroadcastReceiver mReceiver = new BroadcastReceiver(){
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(ConstData.ACTION_GAODE_INFO)) {
				int KEY_TYPE = intent.getIntExtra("KEY_TYPE", 0);
//				Log.i(TAG, "KEY_TYPE ="+KEY_TYPE);
				switch (KEY_TYPE) {
				case 10001://引导信息
					if (!WarnBroadcastReceiver.bInNavi) {
						break;
					}
					int ICON = intent.getIntExtra(GuideInfoExtraKey.ICON, -1);//导航转向图标
					if (ICON > 0 && ICON < 17) {
						String strCurRoad;
						strCurRoad = intent.getStringExtra(GuideInfoExtraKey.CUR_ROAD_NAME);//当前道路名
						String strNextRoad;
						strNextRoad = intent.getStringExtra(GuideInfoExtraKey.NEXT_ROAD_NAME);//下一个道路名
						int mDistance = intent.getIntExtra(GuideInfoExtraKey.ROUTE_REMAIN_DIS, -1);//路径剩余距离，单位米
						int mRemainTime = intent.getIntExtra(GuideInfoExtraKey.ROUTE_REMAIN_TIME, -1);//路径剩余时间，单位秒
						int mNextDis = intent.getIntExtra(GuideInfoExtraKey.SEG_REMAIN_DIS, -1);
//						图标名称值
//						自车图标 1
//						左转图标 2
//						右转图标 3
//						左前方图标 4
//						右前方图标 5
//						左后方图标 6
//						右后方图标 7
//						左转掉头图标 8
//						直行图标 9
//						到达途经点图标 10
//						进入环岛图标 11
//						驶出环岛图标 12
//						到达服务区图标 13
//						到达收费站图标 14
//						到达目的地图标 15
//						进入隧道图标 16
//						Log.i(TAG, "ICON ="+ICON+", strCurRoad ===" +strCurRoad+", strNextRoad ="+strNextRoad+", mRemainTime="+mRemainTime);
						if (strCurRoad != null && strNextRoad != null) {
							if (!strCurRoad.isEmpty() && !strNextRoad.isEmpty()) {
								if (mIcon == 15) {
									break;
								}
								Bundle bundle = new Bundle();
								bundle.putInt("ICON", ICON);
								bundle.putString("CurRoad", strCurRoad);
								bundle.putString("NextRoad", strNextRoad);
								bundle.putInt("Distance", mDistance);
								bundle.putInt("RemainTime", mRemainTime);
								bundle.putInt("NextDis", mNextDis);
								mHandler.obtainMessage(ConstData.MSG_GAODE_NAVI, bundle).sendToTarget();
//								Log.i(TAG, "send gaode date!");
								//最后才赋值
								if (mIcon != ICON) {
									mIcon = ICON;
								}
								
							}
						}
					}

					break;
				case 10019://导航状态通知
					int mState = intent.getIntExtra("EXTRA_STATE", -1);
//					开始运行，Application启动即为开始运行 0
//					初始化完成，每次创建地图完成通知 1
//					运行结束，退出程序 2
//					进入前台，OnStart函数中调用 3
//					进入后台，OnStop函数中调用 4
//					开始算路 5
//					算路完成，成功 6
//					算路完成，失败 7
//					开始导航 8
//					结束导航 9
//					开始模拟导航 10
//					暂停模拟导航 11
//					停止模拟导航 12
//					开始TTS播报 13
//					停止TTS播报 14
//					Log.i(TAG, "mState ==="+mState);
					if (mState == 8 || mState == 10) {
						WarnBroadcastReceiver.bInNavi = true;
						mIcon = -1;
						mHandler.sendEmptyMessage(ConstData.MSG_SHOW_NAVI);
					}else if (mState == 9 || mState == 12){
						WarnBroadcastReceiver.bInNavi = false;
						mIcon = -1;
						//mTvNaviRoad.setText("本次导航结束");
						mHandler.sendEmptyMessage(ConstData.MSG_SHOW_NAVI);
					}else if (mState == 2) {
						WarnBroadcastReceiver.bInNavi = false;
						//mTvNaviRoad.setText("本次导航结束");
						mHandler.sendEmptyMessage(ConstData.MSG_SHOW_NAVI);
					}/*else {
						bInNavi = false;
						mHandler.sendEmptyMessage(ConstData.MSG_SHOW_NAVI);
					}*/
					break;
				default:
					break;
				}
			}/*else if (ConstData.ACTION_ILL.equals(action)) {
				int ill = intent.getIntExtra("ill", 0);
				if (ill == 0) {
					if (isPort) {
						mIbMainCarpc.setImageResource(R.drawable.port_carpc_selector);
						mIbMainTpms.setImageResource(R.drawable.port_tpms_selector);
						rl_backGround.setBackgroundResource(R.drawable.bg_port);
					}else {
						mIbMainCarpc.setImageResource(R.drawable.main_carpc_selector);
						mIbMainTpms.setImageResource(R.drawable.main_tpms_selector);
						rl_backGround.setBackgroundResource(R.drawable.home_background);
					}
				}else {
					rl_backGround.setBackgroundResource(R.drawable.home_background_ill);
					if (isPort) {
						mIbMainCarpc.setImageResource(R.drawable.port_carpc_ill_selector);
						mIbMainTpms.setImageResource(R.drawable.port_tpms_ill_selector);
						rl_backGround.setBackgroundResource(R.drawable.bg_port_ill);
					}else {
						mIbMainCarpc.setImageResource(R.drawable.main_carpc_ill_selector);
						mIbMainTpms.setImageResource(R.drawable.main_tpms_ill_selector);
						rl_backGround.setBackgroundResource(R.drawable.home_background_ill);
					}
				}
			}*/
		}
	};
	
	public void setWeather(String wd,String tq){
		this.tq = tq;
		this.wd = wd;
		setDate();
//		showInclueView();
		time.setToNow();
		String weekDay = null;
		switch (time.weekDay) {
		case 0:
			weekDay = activity.getResources().getString(R.string.week_seven);
			break;
		case 1:
			weekDay = activity.getResources().getString(R.string.week_one);
			break;
		case 2:
			weekDay = activity.getResources().getString(R.string.week_two);
			break;
		case 3:
			weekDay = activity.getResources().getString(R.string.week_three);
			break;
		case 4:
			weekDay = activity.getResources().getString(R.string.week_four);
			break;
		case 5:
			weekDay = activity.getResources().getString(R.string.week_five);
			break;
		case 6:
			weekDay = activity.getResources().getString(R.string.week_six);
			break;
		default:
			break;
		}
		if(!rightViews.contains(weatherView)){
			weatherView = View.inflate(activity, R.layout.activity_main_weather, null);
			((WeatherFontTextView)weatherView.findViewById(R.id.tv_temp)).setText(wd);
			((TextView)weatherView.findViewById(R.id.tv_day)).setText((time.month+1)+"/"+time.monthDay);
			((TextView)weatherView.findViewById(R.id.tv_weather)).setText(tq);
			((TextView)weatherView.findViewById(R.id.tv_fxweather)).setText(weekDay);
			((ImageView)weatherView.findViewById(R.id.iv_weather)).setImageResource(imageResoId(tq));;
			rightViews.add(weatherView);
		}else{
			((WeatherFontTextView)weatherView.findViewById(R.id.tv_temp)).setText(wd);
			((TextView)weatherView.findViewById(R.id.tv_day)).setText((time.month+1)+"/"+time.monthDay);
			((TextView)weatherView.findViewById(R.id.tv_weather)).setText(tq);
			((TextView)weatherView.findViewById(R.id.tv_fxweather)).setText(weekDay);
			((ImageView)weatherView.findViewById(R.id.iv_weather)).setImageResource(imageResoId(tq));;
		}
		mHandler.sendEmptyMessageDelayed(88, 2000);
	}
	
	private void setDate(){
		//wd 气温
		if (mTvTemp != null) {
			mTvTemp.setText(wd);
		}
		//tq 天气情况
		if (mTvWeather != null) {
			mTvWeather.setText(tq);
		}
		//fx 风向
		//图像
		if(mWeatherIv != null){
			mWeatherIv.setImageResource(imageResoId(tq));
		}
		time.setToNow();
		if (mTvDay != null) {
			mTvDay.setText((time.month+1)+"/"+time.monthDay);
		}
		
		String weekDay = null;
		switch (time.weekDay) {
		case 0:
			weekDay = activity.getResources().getString(R.string.week_seven);
			break;
		case 1:
			weekDay = activity.getResources().getString(R.string.week_one);
			break;
		case 2:
			weekDay = activity.getResources().getString(R.string.week_two);
			break;
		case 3:
			weekDay = activity.getResources().getString(R.string.week_three);
			break;
		case 4:
			weekDay = activity.getResources().getString(R.string.week_four);
			break;
		case 5:
			weekDay = activity.getResources().getString(R.string.week_five);
			break;
		case 6:
			weekDay = activity.getResources().getString(R.string.week_six);
			break;
		default:
			break;
		}
		if (mTvFxWeather != null) {
			mTvFxWeather.setText(weekDay);
		}
	}
	
	private int imageResoId(String weather) {
		
		int resoid=R.drawable.ic_weather_default;
		if (!TextUtils.isEmpty(weather)) {
			if(weather.indexOf("多云")!=-1||weather.indexOf("晴")!=-1){//多云转晴，以下类同 indexOf:包含字串
	            resoid=R.drawable.ic_weather_cloudy;}
	        else if(weather.indexOf("多云")!=-1&&weather.indexOf("阴")!=-1){
	            resoid=R.drawable.ic_weather_default;}
	        else if(weather.indexOf("阴")!=-1&&weather.indexOf("雨")!=-1){
	            resoid=R.drawable.ic_weather_lightrain;}
	        else if(weather.indexOf("晴")!=-1&&weather.indexOf("雨")!=-1){
	            resoid=R.drawable.ic_weather_fogs;}
	        else if(weather.indexOf("晴")!=-1&&weather.indexOf("雾")!=-1){
	            resoid=R.drawable.ic_weather_fogs;}
	        else if(weather.indexOf("晴")!=-1){resoid=R.drawable.ic_weather_sunny;}
	        else if(weather.indexOf("多云")!=-1){resoid=R.drawable.ic_weather_default;}
	        else if(weather.indexOf("阵雨")!=-1){resoid=R.drawable.ic_weather_lightrain;}
	        else if(weather.indexOf("小雨")!=-1){resoid=R.drawable.ic_weather_lightrain;}
	        else if(weather.indexOf("中雨")!=-1){resoid=R.drawable.ic_weather_moderaterain;}
	        else if(weather.indexOf("大雨")!=-1){resoid=R.drawable.ic_weather_heavyrain;}
	        else if(weather.indexOf("暴雨")!=-1){resoid=R.drawable.ic_weather_heavyrain;}
	        else if(weather.indexOf("冰雹")!=-1){resoid=R.drawable.ic_weather_sandstorm;}
	        else if(weather.indexOf("雷阵雨")!=-1){resoid=R.drawable.ic_weather_thundershower;}
	        else if(weather.indexOf("小雪")!=-1){resoid=R.drawable.ic_weather_lightsnow;}
	        else if(weather.indexOf("中雪")!=-1){resoid=R.drawable.ic_weather_moderatesnow;}
	        else if(weather.indexOf("大雪")!=-1){resoid=R.drawable.ic_weather_heavysnow;}
	        else if(weather.indexOf("暴雪")!=-1){resoid=R.drawable.ic_weather_heavysnow;}
	        else if(weather.indexOf("扬沙")!=-1){resoid=R.drawable.ic_weather_haze;}
	        else if(weather.indexOf("沙尘")!=-1){resoid=R.drawable.ic_weather_haze;}
	        else if(weather.indexOf("雾")!=-1){resoid=R.drawable.ic_weather_fogs;}
		}
		return resoid;
	}
	
	public void setWarnTips(int[] array){
		if (array.length == 3) {
			switch (array[0]) {
			case 0://车门
				if (array[2] == 1) {//语音报警
						if (array[1] == 0) {//左前
							warnList.add(activity.getResources().getString(R.string.warn_lfDoor_open));
							/*mTvWarn.setText("左前门已打开");
							mTvWarn.setVisibility(View.VISIBLE);*/
							setWarnKuang(activity.getResources().getString(R.string.warn_lfDoor_open), "#66FF0000", false, getWarnLevel(), View.VISIBLE);
							System.out.println("=====弹====左前门");
						}else if (array[1] == 1) {//左后
							warnList.add(activity.getResources().getString(R.string.warn_lrDoor_open));
							setWarnKuang(activity.getResources().getString(R.string.warn_lrDoor_open), "#66FF0000", false, getWarnLevel(), View.VISIBLE);
							System.out.println("=====弹====左后门");
//							mTvWarn.setText("左后门已打开");
//							mTvWarn.setVisibility(View.VISIBLE);
						}else if (array[1] == 2) {//右前
							warnList.add(activity.getResources().getString(R.string.warn_rfDoor_open));
							setWarnKuang(activity.getResources().getString(R.string.warn_rfDoor_open), "#66FF0000", false, getWarnLevel(), View.VISIBLE);
							System.out.println("=====弹====右前门");
//							mTvWarn.setText("右前门已打开");
//							mTvWarn.setVisibility(View.VISIBLE);
						}else if (array[1] == 3) {//右后
							warnList.add(activity.getResources().getString(R.string.warn_rrDoor_open));
							setWarnKuang(activity.getResources().getString(R.string.warn_rrDoor_open), "#66FF0000", false, getWarnLevel(), View.VISIBLE);
							System.out.println("=====弹====右后门");
//							mTvWarn.setText("右后门已打开");
//							mTvWarn.setVisibility(View.VISIBLE);
						}
				}else {
					if (array[1] == 0) {//左前
						warnList.remove(activity.getResources().getString(R.string.warn_lfDoor_open));
					}else if (array[1] == 1) {//左后
						warnList.remove(activity.getResources().getString(R.string.warn_lrDoor_open));
					}else if (array[1] == 2) {//右前
						warnList.remove(activity.getResources().getString(R.string.warn_rfDoor_open));
					}else if (array[1] == 3) {//右后
						warnList.remove(activity.getResources().getString(R.string.warn_rrDoor_open));
					}
					if (warnList.size() > 0) {
						if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_tire))) {
							setWarnKuang(warnList.get(warnList.size()-1), getTextColor(), true, getWarnLevel(), View.VISIBLE);
						}else if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_door)) || 
								warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_handBrake))) {
							setWarnKuang(warnList.get(warnList.size()-1), "#66FF0000", true, getWarnLevel(), View.VISIBLE);
						}else{
							setWarnKuang(warnList.get(warnList.size()-1), "#66C6FCFF", false, getWarnLevel(), View.VISIBLE);
						}
//						mTvWarn.setVisibility(View.VISIBLE);
//						mTvWarn.setText(warnList.get(warnList.size()-1));
					}else {
//						mTvWarn.setVisibility(View.GONE);
						setWarnKuang("", "#66C6FCFF", false, 0, View.GONE);
					}
				}
				break;
			case 1://手刹
				if (array[2] == 1) {
						warnList.add(activity.getResources().getString(R.string.warn_looseHandBrake));
						setWarnKuang(activity.getResources().getString(R.string.warn_looseHandBrake), "#66FF0000", false, 0, View.VISIBLE);
//						mTvWarn.setText("请松开手刹");
//						mTvWarn.setVisibility(View.VISIBLE);
				}else {
					warnList.remove(activity.getResources().getString(R.string.warn_looseHandBrake));
					if (warnList.size() > 0) {
						if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_tire))) {
							setWarnKuang(warnList.get(warnList.size()-1), getTextColor(), true, getWarnLevel(), View.VISIBLE);
						}else if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_door)) || 
								warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_handBrake))) {
							setWarnKuang(warnList.get(warnList.size()-1), "#66FF0000", true, getWarnLevel(), View.VISIBLE);
						}else {
							setWarnKuang(warnList.get(warnList.size()-1), "#66C6FCFF", false, getWarnLevel(), View.VISIBLE);
						}
//						mTvWarn.setVisibility(View.VISIBLE);
//						mTvWarn.setText(warnList.get(warnList.size()-1));
					}else {
						setWarnKuang("", "#66C6FCFF", false, 0, View.GONE);
//						mTvWarn.setVisibility(View.GONE);
					}
				}
				break;
			case 2://安全带
					if (array[2] == 1) {
						warnList.add(activity.getResources().getString(R.string.warn_fastenSafeBelt));
						setWarnKuang(activity.getResources().getString(R.string.warn_fastenSafeBelt), "#66C6FCFF", false, 0, View.VISIBLE);
//						mTvWarn.setText("请系好安全带");
//						mTvWarn.setVisibility(View.VISIBLE);
					}else {
						warnList.remove(activity.getResources().getString(R.string.warn_fastenSafeBelt));
						if (warnList.size() > 0) {
							if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_tire))) {
								setWarnKuang(warnList.get(warnList.size()-1), getTextColor(), true, getWarnLevel(), View.VISIBLE);
							}else if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_door)) || 
									warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_handBrake))) {
								setWarnKuang(warnList.get(warnList.size()-1), "#66FF0000", true, getWarnLevel(), View.VISIBLE);
							}else {
								setWarnKuang(warnList.get(warnList.size()-1), "#66C6FCFF", false, getWarnLevel(), View.VISIBLE);
							}
//							mTvWarn.setVisibility(View.VISIBLE);
//							mTvWarn.setText(warnList.get(warnList.size()-1));
						}else {
							setWarnKuang("", "#66C6FCFF", false, 0, View.GONE);
//							mTvWarn.setVisibility(View.GONE);
						}
					}
				break;
			case 3://后备箱
					if (array[2] == 1) {
						warnList.add(activity.getResources().getString(R.string.warn_trunk_open));
						setWarnKuang(activity.getResources().getString(R.string.warn_trunk_open), "#66FF0000", false, 0, View.VISIBLE);
//						mTvWarn.setText("后备箱已打开");
//						mTvWarn.setVisibility(View.VISIBLE);
					}else {
						warnList.remove(activity.getResources().getString(R.string.warn_trunk_open));
						if (warnList.size() > 0) {
							if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_tire))) {
								setWarnKuang(warnList.get(warnList.size()-1), getTextColor(), true, getWarnLevel(), View.VISIBLE);
							}else if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_door)) || 
									warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_handBrake))) {
								setWarnKuang(warnList.get(warnList.size()-1), "#66FF0000", true, getWarnLevel(), View.VISIBLE);
							}else {
								setWarnKuang(warnList.get(warnList.size()-1), "#66C6FCFF", false, getWarnLevel(), View.VISIBLE);
							}
//							mTvWarn.setVisibility(View.VISIBLE);
//							mTvWarn.setText(warnList.get(warnList.size()-1));
						}else {
							setWarnKuang("", "#66C6FCFF", false, 0, View.GONE);
//							mTvWarn.setVisibility(View.GONE);
						}
					}
				break;
			case 4://引擎盖
					if (array[2] == 1) {
						warnList.add(activity.getResources().getString(R.string.warn_bonnet_open));
						setWarnKuang(activity.getResources().getString(R.string.warn_bonnet_open), "#66FF0000", false, 0, View.VISIBLE);
//						mTvWarn.setText("引擎盖已打开");
//						mTvWarn.setVisibility(View.VISIBLE);
					}else {
						warnList.remove(activity.getResources().getString(R.string.warn_bonnet_open));
						if (warnList.size() > 0) {
							if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_tire))) {
								setWarnKuang(warnList.get(warnList.size()-1), getTextColor(), true, getWarnLevel(), View.VISIBLE);
							}else if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_door)) || 
									warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_handBrake))) {
								setWarnKuang(warnList.get(warnList.size()-1), "#66FF0000", true, getWarnLevel(), View.VISIBLE);
							}else {
								setWarnKuang(warnList.get(warnList.size()-1), "#66C6FCFF", false, getWarnLevel(), View.VISIBLE);
							}
//							mTvWarn.setVisibility(View.VISIBLE);
//							mTvWarn.setText(warnList.get(warnList.size()-1));
						}else {
							setWarnKuang("", "#66C6FCFF", false, 0, View.GONE);
//							mTvWarn.setVisibility(View.GONE);
						}
					}
				break;
			case 5://电池电压
				if (array[2] == 1) {
//					mPlayer = MediaPlayer.create(MainActivity.this, ConstData.VOICE_WARN[4]);
//					mPlayer.start();
				}
				break;
			case 6://疲劳驾驶
					if (array[2] == 1) {
						warnList.add(activity.getResources().getString(R.string.warn_noTiredDriving));
						setWarnKuang(activity.getResources().getString(R.string.warn_noTiredDriving), "#66C6FCFF", false, 0, View.VISIBLE);
//						mTvWarn.setText("请勿疲劳驾驶");
//						mTvWarn.setVisibility(View.VISIBLE);
						Message message = mHandler.obtainMessage(666);
						message.obj = 0;
						mHandler.sendMessageDelayed(message, 15*1000);
					}else {
						mHandler.removeMessages(666);
						warnList.remove(activity.getResources().getString(R.string.warn_noTiredDriving));
						if (warnList.size() > 0) {
							if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_tire))) {
								setWarnKuang(warnList.get(warnList.size()-1), getTextColor(), true, getWarnLevel(), View.VISIBLE);
							}else if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_door)) || 
									warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_handBrake))) {
								setWarnKuang(warnList.get(warnList.size()-1), "#66FF0000", true, getWarnLevel(), View.VISIBLE);
							}else {
								setWarnKuang(warnList.get(warnList.size()-1), "#66C6FCFF", false, getWarnLevel(), View.VISIBLE);
							}
//							mTvWarn.setVisibility(View.VISIBLE);
//							mTvWarn.setText(warnList.get(warnList.size()-1));
						}else {
							setWarnKuang("", "#66C6FCFF", false, 0, View.GONE);
//							mTvWarn.setVisibility(View.GONE);
						}
					}
				break;
			case 7://胎压
				if (array[2] == 1) {//语音报警
					if (array[1] == 0) {//左前
						warnList.add(activity.getResources().getString(R.string.warn_lfTpms));
						setWarnKuang(activity.getResources().getString(R.string.warn_lfTpms), getTextColor(), true, getWarnLevel(), View.VISIBLE);
//						mTvWarn.setText("左前轮胎异常");
//						mTvWarn.setVisibility(View.VISIBLE);
					}else if (array[1] == 1) {//左
						warnList.add(activity.getResources().getString(R.string.warn_lrTpms));
						setWarnKuang(activity.getResources().getString(R.string.warn_lrTpms), getTextColor(), true, getWarnLevel(), View.VISIBLE);
//						mTvWarn.setText("左后轮胎异常");
//						mTvWarn.setVisibility(View.VISIBLE);
					}else if (array[1] == 2) {//右前
						warnList.add(activity.getResources().getString(R.string.warn_rfTpms));
						setWarnKuang(activity.getResources().getString(R.string.warn_rfTpms), getTextColor(), true, getWarnLevel(), View.VISIBLE);
//						mTvWarn.setText("右前轮胎异常");
//						mTvWarn.setVisibility(View.VISIBLE);
					}else if (array[1] == 3) {//右后
						warnList.add(activity.getResources().getString(R.string.warn_rrTpms));
						setWarnKuang(activity.getResources().getString(R.string.warn_rrTpms), getTextColor(), true, getWarnLevel(), View.VISIBLE);
//						mTvWarn.setText("右后轮胎异常");
//						mTvWarn.setVisibility(View.VISIBLE);
					}
				}else {
					if (array[1] == 0) {//左前
						warnList.remove(activity.getResources().getString(R.string.warn_lfTpms));
					}else if (array[1] == 1) {//左后
						warnList.remove(activity.getResources().getString(R.string.warn_lrTpms));
					}else if (array[1] == 2) {//右前
						warnList.remove(activity.getResources().getString(R.string.warn_rfTpms));
					}else if (array[1] == 3) {//右后
						warnList.remove(activity.getResources().getString(R.string.warn_rrTpms));
					}
					if (warnList.size() > 0) {
						if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_tire))) {
							setWarnKuang(warnList.get(warnList.size()-1), getTextColor(), true, getWarnLevel(), View.VISIBLE);
						}else if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_door)) || 
								warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_handBrake))) {
							setWarnKuang(warnList.get(warnList.size()-1), "#66FF0000", true, getWarnLevel(), View.VISIBLE);
						}else {
							setWarnKuang(warnList.get(warnList.size()-1), "#66C6FCFF", false, getWarnLevel(), View.VISIBLE);
						}
//						mTvWarn.setVisibility(View.VISIBLE);
//						mTvWarn.setText(warnList.get(warnList.size()-1));
					}else {
						setWarnKuang("", "#66FF0000", false, 1, View.GONE);
//						mTvWarn.setVisibility(View.GONE);
					}
				}
				break;
			case 8://冷却液温度
//				if (array[2] == 1) {
//					mPlayer = MediaPlayer.create(MainActivity.this, ConstData.VOICE_WARN[11]);
//					mPlayer.start();
//				}
				break;
			case 9://剩余油量
					if (array[2] == 1) {
						warnList.add(activity.getResources().getString(R.string.warn_lowOil));
						setWarnKuang(activity.getResources().getString(R.string.warn_lowOil), "#66C6FCFF", false, 0, View.VISIBLE);
//						mTvWarn.setText("油量不足");
//						mTvWarn.setVisibility(View.VISIBLE);
					}else {//右后
						warnList.remove(activity.getResources().getString(R.string.warn_lowOil));
						if (warnList.size() > 0) {
							if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_tire))) {
								setWarnKuang(warnList.get(warnList.size()-1), getTextColor(), true, getWarnLevel(), View.VISIBLE);
							}else if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_door)) || 
									warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_handBrake))) {
								setWarnKuang(warnList.get(warnList.size()-1), "#66FF0000", true, getWarnLevel(), View.VISIBLE);
							}else {
								setWarnKuang(warnList.get(warnList.size()-1), "#66C6FCFF", false, getWarnLevel(), View.VISIBLE);
							}
//							mTvWarn.setVisibility(View.VISIBLE);
//							mTvWarn.setText(warnList.get(warnList.size()-1));
						}else {
							setWarnKuang("", "#66C6FCFF", false, 0, View.GONE);
//							mTvWarn.setVisibility(View.GONE);
						}
					}
				break;
			case 10:
					if (array[2] == 1) {
						warnList.add(activity.getResources().getString(R.string.warn_turnLeft));
						setWarnKuang(activity.getResources().getString(R.string.warn_turnLeft), "#66C6FCFF", false, 0, View.VISIBLE);
//						mTvWarn.setText("左转向灯已打开");
//						mTvWarn.setVisibility(View.VISIBLE);
					}else {//右后
						warnList.remove(activity.getResources().getString(R.string.warn_turnLeft));
						if (warnList.size() > 0) {
							if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_tire))) {
								setWarnKuang(warnList.get(warnList.size()-1), getTextColor(), true, getWarnLevel(), View.VISIBLE);
							}else if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_door)) || 
									warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_handBrake))) {
								setWarnKuang(warnList.get(warnList.size()-1), "#66FF0000", true, getWarnLevel(), View.VISIBLE);
							}else {
								setWarnKuang(warnList.get(warnList.size()-1), "#66C6FCFF", false, getWarnLevel(), View.VISIBLE);
							}
//							mTvWarn.setVisibility(View.VISIBLE);
//							mTvWarn.setText(warnList.get(warnList.size()-1));
						}else {
							setWarnKuang("", "#66C6FCFF", false, 0, View.GONE);
//							mTvWarn.setVisibility(View.GONE);
						}
					}
				break;
			case 11:
					if (array[2] == 1) {
						warnList.add(activity.getResources().getString(R.string.warn_turnRight));
						setWarnKuang(activity.getResources().getString(R.string.warn_turnRight), "#66C6FCFF", false, 0, View.VISIBLE);
//						mTvWarn.setText("右转向灯已打开");
//						mTvWarn.setVisibility(View.VISIBLE);
					}else {//
						warnList.remove(activity.getResources().getString(R.string.warn_turnRight));
						if (warnList.size() > 0) {
							if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_tire))) {
								setWarnKuang(warnList.get(warnList.size()-1), getTextColor(), true, getWarnLevel(), View.VISIBLE);
							}else if (warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_door)) || 
									warnList.get(warnList.size()-1).contains(activity.getResources().getString(R.string.contain_handBrake))) {
								setWarnKuang(warnList.get(warnList.size()-1), "#66FF0000", true, getWarnLevel(), View.VISIBLE);
							}else {
								setWarnKuang(warnList.get(warnList.size()-1), "#66C6FCFF", false, getWarnLevel(), View.VISIBLE);
							}
//							mTvWarn.setVisibility(View.VISIBLE);
//							mTvWarn.setText(warnList.get(warnList.size()-1));
						}else {
							setWarnKuang("", "#66C6FCFF", false, 0, View.GONE);
//							mTvWarn.setVisibility(View.GONE);
						}
					}
				break;
			default:
				break;
			}
		}
	}
	
	/*public void hideWarnTips(){
		mTvWarn.setVisibility(View.GONE);
		
	}*/
	
	private class VerticalAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			
//			if (((MainActivity)activity).getNetWorkState()) {
//				return unitLists.size()+1;
//			}
//			return unitLists.size();
			if(rightViews!=null&&rightViews.size() == 1){
				return rightViews.size();
			}else{
				return rightViews != null && rightViews.size() > 0 ? Integer.MAX_VALUE : 0;
			}
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) { 
			
//			View view = null;
//			ViewHolder mHolder = new ViewHolder();  
//			if (position == 0 && ((MainActivity)activity).getNetWorkState()) {
//				view = View.inflate(activity, R.layout.activity_main_weather, null);
//				mHolder.mTvTemp = (WeatherFontTextView) view.findViewById(R.id.tv_temp);
//				mHolder.mTvDay = (TextView) view.findViewById(R.id.tv_day);
//				mHolder.mTvWeather = (TextView) view.findViewById(R.id.tv_weather);
//				mHolder.mTvFxWeather = (TextView) view.findViewById(R.id.tv_fxweather);
//				mHolder.mWeatherIv = (ImageView) view.findViewById(R.id.iv_weather);
//				mTvTemp = mHolder.mTvTemp;
//				mTvDay = mHolder.mTvDay;
//				mTvWeather = mHolder.mTvWeather;
//				mTvFxWeather = mHolder.mTvFxWeather;
//				mWeatherIv = mHolder.mWeatherIv;
//				setDate();
//			}else {
//				view = View.inflate(activity, R.layout.item_vertical_battery, null);
//				mHolder.text_value = (CustomTextView) view.findViewById(R.id.vertical_battery);
//				mHolder.text_unit = (TextView) view.findViewById(R.id.vertical_battery_unit);
//				mHolder.text_content = (TextView) view.findViewById(R.id.vertical_battery_title);
//				System.out.println("========初始化pager");
//				if (((MainActivity)activity).getNetWorkState()) {
//					customTextViews.set(position-1,mHolder.text_value);
//				} else {
//					customTextViews.set(position,mHolder.text_value);
//				}
//				
////				text_value = mHolder.text_value;
////				text_unit = mHolder.text_unit;
////				text_title = mHolder.text_content;
//				setData(mHolder.text_value,mHolder.text_unit,mHolder.text_content,position);
//			}
//	        container.addView(view);
//	        return view;
			
			if(rightViews!=null&&rightViews.size() == 1){
				container.addView(rightViews.get(position));
				return rightViews.get(position);
			}else{
				View v = rightViews.get(position%rightViews.size());
		        ViewGroup parent = (ViewGroup) v.getParent();  
		        if (parent != null) {  
		        	parent.removeView(v);
		        }  
				try{
					container.addView(rightViews.get(position%rightViews.size()),0);
				}catch (Exception e) {
					// TODO: handle exception;
					Log.e("ljq", "Exception="+e.toString());
				}
				return rightViews.get(position%rightViews.size());
			}
		}
		
		private void setData(CustomTextView text_value, TextView text_unit,
				TextView text_content,int position) {
			
			if (((MainActivity)activity).getNetWorkState()) {
				if (position-1 >= 0 && position - 1 <= unitLists.size() ) {
					text_unit.setText(unitLists.get(position-1));
					text_content.setText(titleLists.get(position-1));
				}
			}else {
				if (position >= 0 && position <= unitLists.size() ) {
					text_unit.setText(unitLists.get(position));
					text_content.setText(titleLists.get(position));
				}
			}
			
			
			/*if (position == 1) {
				text_unit.setText("rpm");
				text_content.setText("发动机转速");
			}else if (position == 2) {
				text_unit.setText("V");
				text_content.setText("电池电压");
			}else if (position == 3) {
				text_unit.setText("%");
				text_content.setText("剩余油量");
			}else if (position == 4) {
				text_unit.setText("℃");
				text_content.setText("冷却液温度");
			}else if (position == 5) {
				text_unit.setText("min");
				text_content.setText("本次行驶时间");
			}else if (position == 6) {
				text_unit.setText("km/h");
				text_content.setText("本次平均车速");
			}else if (position == 7) {
				text_unit.setText("km");
				text_content.setText("本次行驶里程");
			}*/
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
//			container.removeView((View) object);
//			((ViewPager) container).removeView(rightViews.get(position%rightViews.size()));
		}
		
	}
	
	private class ViewHolder{  
		private CustomTextView text_value;
		private TextView text_content,text_unit;
	    private WeatherFontTextView mTvTemp;
		private TextView mTvDay, mTvWeather,mTvFxWeather;
		private ImageView mWeatherIv;
	} 
	
	private void setWarnKuang(String text,String color,boolean isTpms,int level,int visibleState){
		if (mKuangView != null) {
			if (visibleState == View.VISIBLE) {
				mKuangView.setVisibility(visibleState);
				mKuangView.setText(text);
				mKuangView.setTextColor(color);
				mKuangView.isTpms(isTpms,level);
				mKuangView.setVisible();
			}else {
				mKuangView.setVisibility(View.GONE);
				mKuangView.setGone();
			}
		}
	}
	
	@SuppressWarnings("unused")
	private boolean getTpmsState(){
		if (CarInfo.getInstance().getRightFront() == 1 || CarInfo.getInstance().getRightRear() == 1 ||
				CarInfo.getInstance().getLeftRear() == 1 || CarInfo.getInstance().getLeftRear() == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	private int getWarnLevel(){
		return CarInfo.getInstance().getWarnLevel();
	}
	
	private String getTextColor(){
		if (CarInfo.getInstance().getWarnLevel() == 0) {
			return "#66FFFF00";
		}else {
			return "#66FF0000";
		}
	}

	@Override
	public void updateWarnTips(int[] arrays) { 
		setWarnTips(arrays);
//		Message message = mHandler.obtainMessage();
//		message.what = ConstData.MESSAGE_WARNTIPS;
//		message.obj = arrays;
//		mHandler.sendMessage(message);
		
	}
	
	
	
}
