package com.hiworld.canbus.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hiworld.canbus.parse.FragmentParseData;
import com.hiworld.canbus.util.CarInfo;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.util.GuideInfoExtraKey;
import com.hiworld.canbus.util.GuideNaviImage;
import com.hiworld.canbus.util.HttpService;
import com.hiworld.canbus.util.ImeRecode;
import com.hiworld.canbus.view.CustomTextView;
import com.hiworld.canbus.view.NaviFontTextView;
import com.hiworld.canbus.view.WeatherFontTextView;
import com.hiworld.mycar.t11.R;
import com.spreadwin.radar.service.PointEntity;
import com.spreadwin.radar.service.RomoteRadarInfoManager;
import com.youzi.customer.connect.ConnConnect;
import com.youzi.customer.util.ConstUtil;
//import com.baidu.location.BDLocation;
//import com.baidu.location.BDLocationListener;
//import com.hiworld.canbus.bdlocation.LocationService;


public class MainFragment extends Fragment {
	private static final String TAG = "MainFragment";
	
	private Activity activity;
	private Context mContext;
	//系统时间
	private int time_dot = 0, doublelight_num = 0, mSafeNum = 0;
	private CustomTextView mTxSystemtime, mTxSystemtimeDot;
	//小图标报警
	private ImageView mFarlightIco, mIllIco, mDoubleIco, mLeftlightIco, mRightlightIco,
	                              mSafeBeltIco, mTpmsIco, mBatteryIco, mLowfuelIco, mCooltempIco,
	                              mBreakerIco;
	//汽车图标
	private ImageView mCarFarlight, mCarNearlylight, mCarLeftlight, mCarRightlight,
	                              mCarLfdoor,mCarTpms, mCarTaixbox, mCarHood,//, mBasecar,
	                              mCarRfdoor, mCarLrdoor, mCarRrdoor, mCarLfWindow, mCarRfWindow,
	                              mCarLrWindow, mCarRrWindow, mCarWidelight, mCarStoplight;
	//速度
	private ImageView mSpeedHundred, mSpeedTen, mSpeedDigit;
	//里程
	private TextView mTxAutoMileage, mTxTotalMileage;
	                           //mTxAutoName, mTxMileageName;
	//指北针
	private ImageView mTireddriver, mLimitSpeed,mZhibeizhenDipan,mZhizhen; 
	
	private ImageView  mBasecar;//, mAnimaSpeed;mTransparent,
	
	private int num = 0;
	private Timer timer; // 时间
	private float maxDegree = 0.0f;
	private float degree = 0.0f; // 旋转
	private RotateAnimation animation;
	private boolean flag = true;
	
	private boolean bRunnable = false, bSafeBelt = false;
	//档位
	private RelativeLayout mMainHudll, mTankuanRed, mTankunGreen;
	private LinearLayout mGearll;
	private ImageView mGearP, mGearR, mGearN, mGearD, mGearS;
	//弹框
	private TextView mTxTankuanRed, mTxTankuanGreen;
	private ArrayList<String> mRedStrs = new ArrayList<String>();
	private ArrayList<String> mGreenStrs = new ArrayList<String>();
	//限速
	private int mLimit = 0, mGpsSpeed = 0;
	private boolean bLimit = false, bShowLimit = false;
	private int mAngle = 0, mThreeZero =0;
	//中断数据，限速报警
	private boolean mbNoData = false, mBroadLimit = false;
	//疲劳驾驶
	private int mTired = 0;
	//胎压
	private boolean lfTyreFlag,rfTyreFlag,lrTyreFlag,rrTyreFlag;//四个轮胎 的标志位
	//防止燃油不足数据错误, 取30次平均值
    private int[] mFuels = new int[30];
    private boolean mAcc20On = false;//acc刚启动30s内延时报警
    //百度定位，天气
//    BMapManager mBMapMan = null;
//    LocationListener mLocationListener = null;
//    MKSearch mSearch = null;
    boolean  bFlag = true, bGpsLocation = false, bGetweather = false;
    String cityName,provinceName;
    private ImageView mWeatherIv;
    private WeatherFontTextView mTvTemp;
    private TextView mTvDay, mTvWeekDay, mTvWeather,mTvFxWeather;
    //高德导航
    boolean bInNavi = false;
    boolean bzhinanzhen = false;
    private int mIcon;
    private TextView mTvRemainDis, mTvRemainTime, mTvNaviRoad, mTvNextUnit, mTvNaviTytle;
    private NaviFontTextView mTvNaviDis;
    private ImageView mNaviIconIv, mNaviLimitIv;
    private View mIncludeRight, mIncludeWeather, mIncludeNavi,mIncludezhinanzhen;
    
//    private View mSlideInclude;
    private RomoteRadarInfoManager romoteRadarInfoManager;
    private boolean dogStatus = false;
    private ConnectivityManager manager;
    private float direction;//方向
//    private LocationService locationService;
    private LocationManager locationManager;
   // int test = 0;
    
    public void onAttach(android.app.Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
		mContext = getActivity();
		FragmentParseData.getInstance().setMainFregmentHandler(mHandler);
		romoteRadarInfoManager = new RomoteRadarInfoManager(mContext);
		romoteRadarInfoManager.onCreate();
//		
		Intent i = new Intent(ConstData.ACTION_EDOG_STATUS_REQUEST);
		mContext.sendBroadcast(i);

	};
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	
    	View view = inflater.inflate(R.layout.activity_main, container,false);
		//初始化控件
		initCtrl(view);
		initWeather(view);
		initNavi(view);
		manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				setCarpcInfo(CarPcInfo.getInstance());
			}
		});
		//初始化百度地图定位
//		initBaiDuMap();
//		//获取方向
		formListenerGetLocation();
//		//高德地图导航信息
		registerInterfilter();
//		//时间
		mHandler.sendEmptyMessage(ConstData.MSG_GET_SYSTEMTIME);
		return view;
    }
    
	
	private void initNavi(View view) {
		
		mTvRemainDis = (TextView) view.findViewById(R.id.tv_remain_dis);
		mTvRemainTime = (TextView) view.findViewById(R.id.tv_remain_time);
		//mTvFxNavi = (TextView) view.findViewById(R.id.tv_fxnavi);
		mTvNaviRoad = (TextView) view.findViewById(R.id.tv_navi_nextroad);
		mTvNaviDis = (NaviFontTextView) view.findViewById(R.id.tv_next_dis);
		mNaviIconIv = (ImageView) view.findViewById(R.id.iv_navi_icon);
		mTvNextUnit = (TextView) view.findViewById(R.id.tv_navi_unit);
		mTvNaviTytle = (TextView) view.findViewById(R.id.tv_navi_tytle);
		mNaviLimitIv = (ImageView) view.findViewById(R.id.iv_navi_limit);
		//
		mIncludeRight = view.findViewById(R.id.include_limitSpeed);
		mIncludeWeather = view.findViewById(R.id.include_weather);
		mIncludeNavi = view.findViewById(R.id.include_navi);
		mIncludezhinanzhen = view.findViewById(R.id.include_zhinanzhen);
	}

	private void initWeather(View view) {
		
		mWeatherIv = (ImageView) view.findViewById(R.id.iv_weather);
		mTvTemp = (WeatherFontTextView) view.findViewById(R.id.tv_temp);
		mTvDay = (TextView) view.findViewById(R.id.tv_day);
		mTvWeekDay = (TextView) view.findViewById(R.id.tv_weekday);
		mTvWeather = (TextView) view.findViewById(R.id.tv_weather);
		mTvFxWeather = (TextView) view.findViewById(R.id.tv_fxweather);
	}

	
	@Override
	public void onResume() {
		
		super.onResume();
		bFlag = true;
		if(cityName != null && !"".equals(cityName)){
			QueryAsyncTask asyncTask = new QueryAsyncTask();
			asyncTask.execute("");
		}
//		mBMapMan.getLocationManager().requestLocationUpdates(mLocationListener);
//		mBMapMan.getLocationManager().enableProvider(MKLocationManager.MK_GPS_PROVIDER);
//		mBMapMan.start();
		//档位
//		GetGearInfo();
		//预览
//		setScreenMode(mSlideMode);
		//版本
//		sendVerInfo();
	}
	
	/**
	 * Youzi_HUD
	 * @title: initCtrl 
	 * @describe: 初始化控件
	 * @return: void 
	 * @Copyright: Hiworld®
	 * @Author: Hardy.lai
	 * @Date: Aug 17, 2016
	 */
	private void initCtrl(View view) {
		
		mTxSystemtime = (CustomTextView) view.findViewById(R.id.tv_time);
		mTxSystemtimeDot = (CustomTextView) view.findViewById(R.id.tv_time_dot);
		//ICO
		mFarlightIco = (ImageView) view.findViewById(R.id.iv_farlight);
		mIllIco = (ImageView) view.findViewById(R.id.iv_ill);
		mDoubleIco = (ImageView) view.findViewById(R.id.iv_doublelight);
		mLeftlightIco = (ImageView) view.findViewById(R.id.iv_leftlight);
		mRightlightIco = (ImageView) view.findViewById(R.id.iv_rightlight);
		mSafeBeltIco = (ImageView) view.findViewById(R.id.iv_safebelt);
		mTpmsIco = (ImageView) view.findViewById(R.id.iv_tpms);
		mBatteryIco = (ImageView) view.findViewById(R.id.iv_battery);
		mLowfuelIco = (ImageView) view.findViewById(R.id.iv_lowfuel);
		mCooltempIco = (ImageView) view.findViewById(R.id.iv_coolwater);
		mBreakerIco = (ImageView) view.findViewById(R.id.iv_handbreak);
		
		//汽车
		mCarFarlight = (ImageView) view.findViewById(R.id.iv_car_farlight);
		mCarNearlylight = (ImageView) view.findViewById(R.id.iv_car_nearlylight);
		mCarLeftlight= (ImageView) view.findViewById(R.id.iv_car_leftlight);
		mCarRightlight = (ImageView) view.findViewById(R.id.iv_car_rightlight);
		mCarTpms = (ImageView) view.findViewById(R.id.iv_car_tire);
		mCarTaixbox = (ImageView) view.findViewById(R.id.iv_taixbox);
		mCarHood = (ImageView) view.findViewById(R.id.iv_hood);
		mCarLfdoor = (ImageView) view.findViewById(R.id.iv_lfdoor);
		mCarRfdoor = (ImageView) view.findViewById(R.id.iv_rfdoor);
		mCarLrdoor = (ImageView) view.findViewById(R.id.iv_lrdoor);
		mCarRrdoor = (ImageView) view.findViewById(R.id.iv_rrdoor);
		mCarLfWindow = (ImageView) view.findViewById(R.id.iv_lfwindow);
		mCarRfWindow = (ImageView) view.findViewById(R.id.iv_rfwindow);
		mCarLrWindow = (ImageView) view.findViewById(R.id.iv_lrwindow);
		mCarRrWindow = (ImageView) view.findViewById(R.id.iv_rrwindow);
		mCarWidelight = (ImageView) view.findViewById(R.id.iv_car_widelight);
		mCarStoplight = (ImageView) view.findViewById(R.id.iv_car_stoplight);

		//速度
		mSpeedHundred = (ImageView) view.findViewById(R.id.iv_speed_hundred);
		mSpeedTen = (ImageView) view.findViewById(R.id.iv_speed_ten);
		mSpeedDigit = (ImageView) view.findViewById(R.id.iv_speed_digit);
		
		//里程
		mTxAutoMileage = (TextView) view.findViewById(R.id.iv_auto_mileage);
		mTxTotalMileage = (TextView) view.findViewById(R.id.iv_total_mileage);
		
		//疲劳驾驶
		mTireddriver = (ImageView) view.findViewById(R.id.iv_tire_coffie);
		mLimitSpeed = (ImageView) view.findViewById(R.id.iv_limit_speed);
		//指北针
		mZhibeizhenDipan = (ImageView) view.findViewById(R.id.compass_logo);
		mZhizhen = (ImageView) view.findViewById(R.id.compass_zhizhen);
		
		mMainHudll = (RelativeLayout) view.findViewById(R.id.main_hud_ll);
		mGearll = (LinearLayout) view.findViewById(R.id.id_gear_ll);
		mGearP = (ImageView) view.findViewById(R.id.iv_gear_p);
		mGearR = (ImageView) view.findViewById(R.id.iv_gear_r);
		mGearN = (ImageView) view.findViewById(R.id.iv_gear_n);
		mGearD = (ImageView) view.findViewById(R.id.iv_gear_d);
		mGearS = (ImageView) view.findViewById(R.id.iv_gear_s);
		
		mTankuanRed = (RelativeLayout) view.findViewById(R.id.id_red_warn_ll);
		mTankunGreen = (RelativeLayout) view.findViewById(R.id.id_green_warn_ll);
		mTxTankuanRed = (TextView) view.findViewById(R.id.tv_tankuan_red);
		mTxTankuanGreen = (TextView) view.findViewById(R.id.tv_tankuan_green);
		//mAnimaSpeed = (ImageView) this.findViewById(R.id.iv_speed_anima);
		
		//mTransparent = (ImageView) this.findViewById(R.id.iv_tranparent);
		
		mBasecar = (ImageView) view.findViewById(R.id.iv_basecar);
//		mBasecar.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//				Intent intent = new Intent(activity, CarStateActivity.class);
//				startActivity(intent);
////				Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
//			}
//		});
		
		//进入胎压
		mTankuanRed.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				String text = mTxTankuanRed.getText().toString();
				if (!TextUtils.isEmpty(text) && text.contains("轮胎压异常")) {
					try {
						Intent intent = new Intent();
						ComponentName cn = new ComponentName("com.hiworld.youzi.htpms", "com.hiworld.htpms.MainActivity");
						intent.setComponent(cn);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
						startActivity(intent);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			}
		});
	}
	
//	private void initBaiDuMap() {
//		
//		NetworkInfo mNetworkInfo = manager.getActiveNetworkInfo();  
//		if (mNetworkInfo != null) {
//			if (mNetworkInfo.isAvailable()) {
////				locationService  = new LocationService(mContext);
////				//获取locationservice实例
////				locationService.registerListener(mListener);
////				locationService.setLocationOption(locationService.getDefaultLocationClientOption());
////				locationService.start();// 定位SDK
//				mBMapMan = new BMapManager(getActivity());
//		        mBMapMan.init("DaKjWjjsGtGYM99oT6T42zXzDcReEROn", null);
//		        mBMapMan.start();
//		        initMyLocation();
//			}else {
//				Toast.makeText(getActivity(),"请检查当前网络状态", Toast.LENGTH_SHORT).show();
//			}
//		}else {
//			Toast.makeText(getActivity(),"请检查当前网络状态", Toast.LENGTH_SHORT).show();
//		}  
//	}
	
//	private void initMyLocation() {
//		
//		mLocationListener = new LocationListener() {
//			@Override
//			public void onLocationChanged(Location location) {
//				
//				if(location != null){
//					Log.i(TAG, "定位成功！");
//					bGpsLocation = true;
//					if (bFlag) {
//						bFlag = false;
//						GeoPoint myPt = new GeoPoint((int)(location.getLatitude()*1e6),
//								(int)(location.getLongitude()*1e6));
//						initMapSerach();
//						//将当前坐标转化为地址获取当前城市名称
//						mSearch.reverseGeocode(myPt);
//					}
//				}else{
//					bGpsLocation = false;
//					Log.i(TAG, "定位失败！");
//				}
//			}
//		};
//	}
	
//	protected void initMapSerach() {
//		
//		// 初始化搜索模块，注册事件监听
//		mSearch = new MKSearch();
//		 mSearch.init(mBMapMan, new MKSearchListener() {
//			
//			@Override
//			public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {
//			}
//			
//			@Override
//			public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {
//			}
//			
//			@Override
//			public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {
//			}
//			
//			@Override
//			public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1) {
//			}
//			
//			@Override
//			public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
//			}
//			
//			@Override
//			public void onGetAddrResult(MKAddrInfo res, int error) {
//				
//				if (error != 0 || res == null) {
//				}else{
//					String city = res.addressComponents.city;
//					String pro = res.addressComponents.province;
//					if(city!=null){
//						provinceName = pro.substring(0, pro.length()-1);
//						cityName =  city.substring(0, city.length()-1);
//						Log.i(TAG, "provinceName ="+provinceName+", cityName ="+cityName);
//						QueryAsyncTask asyncTask = new QueryAsyncTask();
//						asyncTask.execute("");
//					}else{
//						Log.i(TAG, "定位不到当前城市!");
//					}
//				}
//			}
//		});
//		
//	}
	
//
//	private BDLocationListener mListener = new BDLocationListener() {
//
//		@Override
//		public void onConnectHotSpotMessage(String arg0, int arg1) {
//			
//		}
//
//		@Override
//		public void onReceiveLocation(BDLocation location) {
//			
//			if (null != location && location.getLocType() != BDLocation.TypeServerError ) {
//				Log.i(TAG, "定位成功！");
//				bGpsLocation = true;
////				bzhinanzhen = true;
//				mGpsSpeed = (int) location.getSpeed();
//            	direction = location.getDirection();
//            	cityName = location.getCity();
//				if (bFlag && (!"".equals(cityName) && null != cityName)) {
//					bFlag = false;
//					QueryAsyncTask asyncTask = new QueryAsyncTask();
//					asyncTask.execute("");
//				}
//			}else{
//				bGpsLocation = false;
//				Log.i(TAG, "定位失败！");
//			}
//		}
//		
//	};
	
	
	
	/** 
     * 通过LocationListener来获取Location信息 
     */  
    public void formListenerGetLocation(){
    	locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    	if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//          Toast.makeText(getActivity(), "请开启GPS导航...", Toast.LENGTH_SHORT).show();
          //返回开启GPS导航设置界面
          Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);   
          startActivityForResult(intent,0);
          return;
      }
     // Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
      //制定定位算法
		Criteria criteria = new Criteria();
	    criteria.setAccuracy(Criteria.ACCURACY_FINE);//设置为最大精度
	    criteria.setAltitudeRequired(false);//是否要求海拔信息
	    criteria.setBearingRequired(false);//不要求Bearing信息（类似地轴线的东西）
	    criteria.setCostAllowed(false);//是否允许付费
	    criteria.setPowerRequirement(Criteria.POWER_LOW);//耗电量
	    
	    String provider = locationManager.getBestProvider(criteria, true);
	    
	    //一秒或者10米请求一次
	    locationManager.requestLocationUpdates(provider, 1000, 10, locationListener);
    } 

    private android.location.LocationListener locationListener = new android.location.LocationListener() {  
  	  
        @Override  
        public void onLocationChanged(Location location) { 
        	if (location != null) { 
        		//位置信息变化时触发  
        		bGpsLocation = true;
        		bzhinanzhen = true;
        		mGpsSpeed = (int) location.getSpeed();
        		direction = location.getBearing();
        		Log.e("ljq", "系统速度="+location.getSpeed()+"，系统方向="+location.getBearing());
        		setAngleFxp((int)location.getBearing());
//        	showInclueView();
        	}
        }  

        @Override  
        public void onStatusChanged(String provider, int status,Bundle extras) {  
            //GPS状态变化时触发  
        }  

        @Override  
        public void onProviderEnabled(String provider) {  
            //GPS禁用时触发  
        }  

        @Override  
        public void onProviderDisabled(String provider) {  
            //GPS开启时触发   
        }  
    }; 

	
	private class QueryAsyncTask extends AsyncTask {
		@Override
		protected void onPostExecute(Object result) {
			if(result!=null){
				String weatherResult = (String)result;
				if(weatherResult.split(";").length>1){
					String a  = weatherResult.split(";")[1];
					if(a.split("=").length>1){
						bGetweather = true;
						String b = a.split("=")[1];
						String c = b.substring(1,b.length()-1);
						String[] resultArr = c.split("\\}");
						if(resultArr.length>0){
							todayParse(resultArr[0]);
						}
						Log.i(TAG, "天气信息!");
					}else{
						bGetweather = false;
						Log.i(TAG, "查无天气信息!");
					}
				}else{
					bGetweather = false;
					Log.i(TAG, "查无天气信息!");
				}
			}else{
				bGetweather = false;
				Log.i(TAG, "查无天气信息!");
			}
			
			showInclueView();
			
			super.onPostExecute(result);			
		}
			
		@Override
		protected Object doInBackground(Object... params) {
			return HttpService.getWeather(cityName);
		}
	}


	public void showInclueView() {
		
		Log.i(TAG, "bInNavi ="+bInNavi+", bShowLimit ="+bShowLimit);
		if(bInNavi){
			mIncludeWeather.setVisibility(View.GONE);
			mIncludeRight.setVisibility(View.GONE);
			mIncludezhinanzhen.setVisibility(View.GONE);
			mIncludeNavi.setVisibility(View.VISIBLE);
			if (mTankunGreen.getVisibility() == View.GONE) {
				mTvRemainDis.setVisibility(View.VISIBLE);
				mTvRemainTime.setVisibility(View.VISIBLE);
			}else{
				mTvRemainDis.setVisibility(View.GONE);
				mTvRemainTime.setVisibility(View.GONE);
			}
				
			if (bShowLimit) {
				mNaviLimitIv.setVisibility(View.VISIBLE);
			} else {
				mNaviLimitIv.setVisibility(View.GONE);
			}
		}else if (bShowLimit) {
			mIncludeNavi.setVisibility(View.GONE);
			mIncludeWeather.setVisibility(View.GONE);
			mIncludezhinanzhen.setVisibility(View.GONE);
			mIncludeRight.setVisibility(View.VISIBLE);
			mLimitSpeed.setVisibility(View.VISIBLE);
		}
		else if(bzhinanzhen){
			mIncludeNavi.setVisibility(View.GONE);
			mIncludeRight.setVisibility(View.GONE);
			mIncludeWeather.setVisibility(View.GONE);
			mIncludezhinanzhen.setVisibility(View.VISIBLE);
		}
		else if(bGetweather){
			mIncludeNavi.setVisibility(View.GONE);
			mIncludeRight.setVisibility(View.GONE);
			mIncludezhinanzhen.setVisibility(View.GONE);
			mIncludeWeather.setVisibility(View.VISIBLE);
		}else{
//			mLimitSpeed.setVisibility(View.GONE);
//			mIncludeWeather.setVisibility(View.GONE);
//			mIncludeNavi.setVisibility(View.GONE);
//			mIncludezhinanzhen.setVisibility(View.GONE);
//			mIncludeRight.setVisibility(View.GONE);
			mIncludeNavi.setVisibility(View.GONE);
			mIncludeRight.setVisibility(View.GONE);
			mIncludeWeather.setVisibility(View.GONE);
			mIncludezhinanzhen.setVisibility(View.VISIBLE);
		}
	}

	public void todayParse(String weather) {
		
		String temp = weather.replace("'", "");
		String[] tempArr = temp.split(",");
		String wd="";
		String tq="";
		String fx="";
		if(tempArr.length>0){
			for(int i=0;i<tempArr.length;i++){
				if(tempArr[i].indexOf("t1:")!=-1){
					wd=tempArr[i].substring(3,tempArr[i].length())+"°";
				}else if(tempArr[i].indexOf("t2:")!=-1){
					wd=tempArr[i].substring(3,tempArr[i].length())+"°"+"~"+wd;
				}else if(tempArr[i].indexOf("d1:")!=-1){
					fx=tempArr[i].substring(3,tempArr[i].length());
				}else if(tempArr[i].indexOf("s1:")!=-1){
					tq=tempArr[i].substring(4,tempArr[i].length());
				}
			}
			
			Log.i(TAG, "wd ="+wd+", tq ="+tq);
			//wd 气温
			mTvTemp.setText(wd);
			//tq 天气情况
			mTvWeather.setText(tq);
			//fx 风向
			//图像
			mWeatherIv.setImageResource(imageResoId(tq));
		}
	}


	private int imageResoId(String weather) {
		
		int resoid=R.drawable.ic_weather_default;
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
		return resoid;
	}

	private void SetGearInfo(int mGear){
		SharedPreferences preferences = mContext.getSharedPreferences("Youzi_Hud", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt("gear", mGear);
		editor.commit();
	}

	private void setInstranSpeed(int iSpeed, int Single, int Ten, int Hundred) {
		
//		Log.i(TAG, "ImeRecode ="+ImeRecode.getRecord(getApplicationContext(), "edog", 1));
		Log.i(TAG, "mLimit =="+mLimit+", bShowLimit ="+bShowLimit);
		if (bShowLimit && mLimit != 0 ) {//有限速
			//关闭电子狗，速度值大于限速
			if (ImeRecode.getRecord(mContext.getApplicationContext(), "edog", 1) == 0 && iSpeed > mLimit && !mBroadLimit) {
				mBroadLimit = true;
				ImeRecode.speakWarnTip(mContext.getApplicationContext(), "您已超速，请小心驾驶");
			}
			
			//当大于110%
			if (iSpeed >= (int)(mLimit*1.1)) {
				if (!bLimit) {
					sendLimitWarn(1);
				}
				sendSpeedImage(iSpeed, Hundred, Ten, Single, 1);
			}else if (iSpeed <= mLimit){//当小于等于100%
				if (bLimit) {
					sendLimitWarn(0);
				}
				if (CarPcInfo.getInstance().getM_iILLAble() == 1 && CarPcInfo.getInstance().getM_iILL() == 1){
					sendSpeedImage(iSpeed, Hundred, Ten, Single, 2);
				}else{
					sendSpeedImage(iSpeed, Hundred, Ten, Single, 3);
				}	
			 }else{//限速和大于1.1之间
				 Log.i(TAG, "bLimit == "+bLimit);
				 if (bLimit) {
					    sendSpeedImage(iSpeed, Hundred, Ten, Single, 1);
					 } else  {
						if (CarPcInfo.getInstance().getM_iILLAble() == 1 && CarPcInfo.getInstance().getM_iILL() == 1){
							sendSpeedImage(iSpeed, Hundred, Ten, Single, 2);
						}else{
							 sendSpeedImage(iSpeed, Hundred, Ten, Single, 3);
						}
					}
			 }
		}else{//无限速
			mBroadLimit = false;
			if (bLimit) {
				sendLimitWarn(0);
			}
			if (CarPcInfo.getInstance().getM_iILLAble() == 1 && CarPcInfo.getInstance().getM_iILL() == 1){
				sendSpeedImage(iSpeed, Hundred, Ten, Single, 2);
			}else{
				 sendSpeedImage(iSpeed, Hundred, Ten, Single, 3);
			}
		}
	}

	private void getSystemTime() {
		
		ContentResolver cv = mContext.getApplicationContext().getContentResolver();
        String strTimeFormat = android.provider.Settings.System.getString(cv,
                                           android.provider.Settings.System.TIME_12_24);
         //非24
        Calendar c = Calendar.getInstance();  
		int hour = c.get(Calendar.HOUR_OF_DAY);  
		int minute = c.get(Calendar.MINUTE);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		mTvDay.setText(month+"/"+day);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		switch (weekday) {
		case 1:
			mTvWeekDay.setText("星期天");
			break;
		case 2:
			mTvWeekDay.setText("星期一");
			break;
		case 3:
			mTvWeekDay.setText("星期二");
			break;
		case 4:
			mTvWeekDay.setText("星期三");
			break;
		case 5:
			mTvWeekDay.setText("星期四");
			break;
		case 6:
			mTvWeekDay.setText("星期五");
			break;
		case 7:
			mTvWeekDay.setText("星期六");
			break;
		default:
			break;
		}
        if(!TextUtils.isEmpty(strTimeFormat) ){
        	if ( !strTimeFormat.equals("24")) {
        		if (hour > 12) {
            		hour -= 12;
    			}
			}
        	
        	//设置下午6点到早上6点换皮肤
        	int apm = c.get(Calendar.AM_PM);//0上午，1下午
        	if (apm == 0 && hour >=0 && hour < 6) {//上午
        		CarPcInfo.getInstance().setM_iILLAble(1);
        		CarPcInfo.getInstance().setM_iILL(1);
			}else if (apm == 1 && hour >= 6) {
				CarPcInfo.getInstance().setM_iILLAble(1);
        		CarPcInfo.getInstance().setM_iILL(1);
			}else{
				CarPcInfo.getInstance().setM_iILLAble(0);
        		CarPcInfo.getInstance().setM_iILL(0);
			}

        }
        time_dot++;
        if ((time_dot = time_dot%2) == 0) {
        	mTxSystemtimeDot.setVisibility(View.GONE);
		}else{
			mTxSystemtimeDot.setVisibility(View.VISIBLE);
		}
		if(minute<10){
			mTxSystemtime.setText(hour+" : "+"0"+minute);
		}else{
			mTxSystemtime.setText(hour+" : "+minute);
		}
	}
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			
			switch (msg.what) {
//			case 1111:
//				if (test == 0) {
//					test = 50;
//				}else{
//					test = 0;
//				}
//				mHandler.sendEmptyMessageDelayed(1111, 60*1000);
//				break;
//			case 1://关acc
//				for (int i = 0; i < mFuels.length; i++) {
//					mFuels[i] = 0;
//				}
//				bInNavi = false;
//				break;
			case 2:
//				if (mGreenStrs.indexOf("油量不足") != -1){
//					sendLowfuelTankuan(0);
//				}
				if (!rfTyreFlag && !lfTyreFlag && !rrTyreFlag && !lrTyreFlag) {
					mAcc20On = true;
					mHandler.removeMessages(3);
					mHandler.sendEmptyMessageDelayed(3, 40*1000);
				}else{
					try {
						ConnConnect.getInstance().getRemoteProxy().sendCmd(ConstUtil.APP2SERVER_OTHER, new int[] {0x03,0x6a,0x05,0x01,0x48});
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 3:
				mAcc20On = false;
				break;
			case ConstData.MSG_UART_NODATA:
				mbNoData = true;//通信中断
				resetLinkWrong();
				break;
			case ConstData.MSG_GET_TRIED://10s显示
				Log.i(TAG, "MSG_GET_TRIED");
				mTireddriver.setVisibility(View.GONE);
				hideGreenTankuan();
				mHandler.removeMessages(ConstData.MSG_GET_TRIED);
				break;
			case ConstData.MSG_GET_COFFIE://疲劳驾驶
				Log.i(TAG, "MSG_GET_COFFIE");
				mTxTankuanGreen.setText("请勿疲劳驾驶");
				mTireddriver.setVisibility(View.VISIBLE);
				showGreenTankuan();
				mHandler.removeMessages(ConstData.MSG_GET_TRIED);
				mHandler.sendEmptyMessageDelayed(ConstData.MSG_GET_TRIED, 15*1000);
//				mHandler.removeMessages(ConstData.MSG_GET_COFFIE);
//				mHandler.sendEmptyMessageDelayed(ConstData.MSG_GET_COFFIE, 1000*60*15);
				break;
			case ConstData.MSG_GET_WARNINFO://报警信息
				int[] warn_info = (int[]) msg.obj;
				Log.i(TAG, "warn_info[0] =="+warn_info[0]+", warn_info[1]="+warn_info[1]);
				//报警类型
				switch (warn_info[0]) {
				case 0://车门报警
					sendDoorTankuan(warn_info[1], warn_info[2]);
					break;
				case 1://手刹报警
					sendHandbreakeTankuan(warn_info[2]);
					break;
				case 2://安全带报警
					sendSafebeltTankuan(warn_info[2]);
					break;
				case 3://后备箱报警
					sendTaxiboxTankuan(warn_info[2]);
					break;
				case 4://引擎盖报警
					sendHoodTankuan(warn_info[2]);
					break;
				case 5://电池电压报警
					sendBatteryTankuan(warn_info[2]);
					break;
				case 6://疲劳驾驶报警
					sendTiredTankuan(warn_info[2]);
					break;
				case 7://胎压报警
					sendHtpmsTankuan(warn_info[1], warn_info[2]);
					break;
				case 8://冷却液温度报警
					sendCooltempTankuan(warn_info[2]);
					break;
				case 9://油量提醒
					sendLowfuelTankuan(warn_info[2]);
					break;
				case 10://左转向灯
					sendTrunLeft(warn_info[2]);
					break;
				case 11://右转向灯
					sendTrunRight(warn_info[2]);
					break;
				default:
					break;
				}
				break;
			case ConstData.MSG_GET_DOG://电子狗数据
				if(!dogStatus){
					return;
				}
				//电子狗
				int[] edog_info = (int[]) msg.obj;
//				if (edog_info.length < 8 ) {
//					break;
//				}

//				Log.i(TAG, "edog_info[5] ="+edog_info[5]);
				PointEntity pointEntity = romoteRadarInfoManager.getEdogPointEntity();
				//距离，单位米
				int mileage = (int) pointEntity.distance;
				//速度限制
				mLimit = edog_info[1];
				//Log.i(TAG, "mLimit ="+mLimit+", edog_info[4] ="+edog_info[4]+", test ="+test);
				setLimit(mileage);
				
				if (CarPcInfo.getInstance().getM_iSpeedAble() == 1 && CarPcInfo.getInstance().getM_fInstantSpeed() > 0) {
					if(direction != -1){
						setAngleFxp((int)direction);
					}
				}else if (CarPcInfo.getInstance().getM_iSpeedAble() == 0 && mGpsSpeed > 0) {
					if(direction != -1){
						setAngleFxp((int)direction);
					}
				}
				
				if (CarPcInfo.getInstance().getM_iSpeedAble() == 0 || (mbNoData && bGpsLocation)){
//					mGpsSpeed =  edog_info[5];
					if (mGpsSpeed == 0) {
						mThreeZero++;
					} else {
						mThreeZero=0;
					}
					int Single1 = mGpsSpeed%10;//个位
					int Ten1 = (mGpsSpeed/10)%10;//十位
					int Hundred1 = (mGpsSpeed/100)%10;//百位
					Log.i(TAG, "gps mGpsSpeed ="+mGpsSpeed);
					if (mGpsSpeed != 0) {
						setInstranSpeed(mGpsSpeed, Single1, Ten1, Hundred1);
						
						if (CarPcInfo.getInstance().getM_iGearAble() == 1){
							//P或S档，改为D档，车速大于10
							if (mGpsSpeed > 10) {
								if (mGpsSpeed > 20) {//修改大于20
									//if (CarPcInfo.getInstance().getM_iGear() == 3)
									{
										//切回非倒车模式
										resetReverseModle(0, false);
										
										if (CarPcInfo.getInstance().getM_iILLAble() == 1 && CarPcInfo.getInstance().getM_iILL() == 1) {
											mGearD.setImageResource(R.drawable.gear_red_d);
//											mSlideGearD.setImageResource(R.drawable.gear_red_d);
										}else{
											mGearD.setImageResource(R.drawable.gear_big_d);
//											mSlideGearD.setImageResource(R.drawable.gear_big_d);
										}
									}
								}
								
								if (CarPcInfo.getInstance().getM_iGear() == 1 || CarPcInfo.getInstance().getM_iGear() == 2) {
									//恢复默认档位设置
									resetGearState();
									
									if (CarPcInfo.getInstance().getM_iILLAble() == 1 && CarPcInfo.getInstance().getM_iILL() == 1) {
										mGearD.setImageResource(R.drawable.gear_red_d);
//										mSlideGearD.setImageResource(R.drawable.gear_red_d);
									}else{
										mGearD.setImageResource(R.drawable.gear_big_d);
//										mSlideGearD.setImageResource(R.drawable.gear_big_d);
									}
									
								}
							}

						}else if (CarPcInfo.getInstance().getM_iReverseGearAble() == 1) {
							if (mGpsSpeed > 20) {//修改大于20
								//切回非倒车模式
								resetReverseModle(0, false);
							}
						}
						
						
					} else {
						if (mThreeZero >= 3) {
							setInstranSpeed(mGpsSpeed, Single1, Ten1, Hundred1);
						}
					}
					
				}
				
				break;
			case ConstData.MSG_GET_SAFEBELT://安全带效果
				mSafeNum++;
				switch (mSafeNum %=2) {
				case 0:
					mSafeBeltIco.setVisibility(View.GONE);
//					mSlideSafeBelt.setVisibility(View.GONE);
					break;
                case 1:
                	mSafeBeltIco.setVisibility(View.VISIBLE);
//                	mSlideSafeBelt.setVisibility(View.VISIBLE);
					break;
				default:
					break;
				}
				mHandler.removeMessages(ConstData.MSG_GET_SAFEBELT);
				mHandler.sendEmptyMessageDelayed(ConstData.MSG_GET_SAFEBELT, 400);
				break;
			case ConstData.MSG_GET_DOUBLELIGHT://双闪效果
				doublelight_num++;
				doublelight_num %= 2;
				if (doublelight_num == 0) {
					mCarLeftlight.setVisibility(View.GONE);
					mCarRightlight.setVisibility(View.GONE);
					mDoubleIco.setVisibility(View.GONE);
					mLeftlightIco.setVisibility(View.GONE);
					mRightlightIco.setVisibility(View.GONE);
				}else {
                    if (CarPcInfo.getInstance().getM_iDoubleLight() == 1) {
                    	mDoubleIco.setVisibility(View.VISIBLE);
                    	mCarLeftlight.setVisibility(View.VISIBLE);
                    	mCarRightlight.setVisibility(View.VISIBLE);
					} 
                    
                    if (CarPcInfo.getInstance().getM_iLeftTrunLight() == 1) {
						mCarLeftlight.setVisibility(View.VISIBLE);
						mLeftlightIco.setVisibility(View.VISIBLE);
					}
                    
					if (CarPcInfo.getInstance().getM_iRightTrunLight() == 1) {
						mCarRightlight.setVisibility(View.VISIBLE);
						mRightlightIco.setVisibility(View.VISIBLE);
					}
				}
				
				bRunnable = true;
				mHandler.removeMessages(ConstData.MSG_GET_DOUBLELIGHT);
				mHandler.sendEmptyMessageDelayed(ConstData.MSG_GET_DOUBLELIGHT, 400);
				break;
			case ConstData.MSG_GET_SYSTEMTIME://系统时间
				getSystemTime();
				mHandler.removeMessages(ConstData.MSG_GET_SYSTEMTIME);
				mHandler.sendEmptyMessageDelayed(ConstData.MSG_GET_SYSTEMTIME, 500);
				break;
			case ConstData.MSG_GAODE_NAVI://高德信息
				Bundle bundle = (Bundle) msg.obj;
				setNaviInfo(bundle);
				break;
			case ConstData.MSG_SHOW_NAVI:
				showInclueView();
				break;
			case ConstData.HANDLER_MESSAGE_CARPC:
				CarPcInfo hudInfo = (CarPcInfo) msg.obj;
				setCarpcInfo(hudInfo);
				break;
			case ConstData.MESSAGE_TIRE_FAILURE://轮胎报警
				CarInfo carInfo = (CarInfo) msg.obj;
				setTpmsInfo(carInfo);
				break;
			default:
				break;
			}
		}
		
	};
	
	
	protected void setCarpcInfo(final CarPcInfo carpcInfo) {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				//车外灯光有效
				if (carpcInfo.getM_iOutLightAble() == 1) {
					//Log.i(TAG, "远光灯 carpcInfo.getM_iFarLight() ="+carpcInfo.getM_iFarLight());
					//远光灯
					if (carpcInfo.getM_iFarLight() == 1) {
						mCarFarlight.setVisibility(View.VISIBLE);
						mFarlightIco.setVisibility(View.VISIBLE);
//						mSlideFarlight.setVisibility(View.VISIBLE);
					}else{
						mCarFarlight.setVisibility(View.GONE);
						mFarlightIco.setVisibility(View.GONE);
//						mSlideFarlight.setVisibility(View.GONE);
					}
					//近光灯
					if (carpcInfo.getM_iNearLight() == 1) {
						mCarNearlylight.setVisibility(View.VISIBLE);
					} else {
						mCarNearlylight.setVisibility(View.GONE);
					}
					//示宽灯
					if (carpcInfo.getM_iWideLight() == 1) {
						mCarWidelight.setVisibility(View.VISIBLE);
					} else {
						mCarWidelight.setVisibility(View.GONE);
					}
					//刹车灯
					if (carpcInfo.getM_iStopLight() == 1) {
						mCarStoplight.setVisibility(View.VISIBLE);
					} else {
						mCarStoplight.setVisibility(View.GONE);
					}
				}
				
				//Log.i(TAG, "carpcInfo.getM_iILL() ="+carpcInfo.getM_iILL());
				//ill
				if (carpcInfo.getM_iILLAble() == 1 && carpcInfo.getM_iILL() == 1) {
					mIllIco.setVisibility(View.VISIBLE);
//					mSlideIll.setVisibility(View.VISIBLE);
				}else{
					mIllIco.setVisibility(View.GONE);
//					mSlideIll.setVisibility(View.GONE);
				}
				
				//Log.i(TAG, "carpcInfo.getM_iSpeedAble() == "+carpcInfo.getM_iSpeedAble());
				//Log.i(TAG, "speed == "+carpcInfo.getM_fInstantSpeed());
				//速度
				if (carpcInfo.getM_iSpeedAble() == 1) {
					int iSpeed = (int) carpcInfo.getM_fInstantSpeed();
					
					//关acc清零
					if (carpcInfo.getM_iACC() == 0) {
						iSpeed = 0;
					}
					//过滤大于280的速度
					if (iSpeed > 280) {
						iSpeed = 0;
					}
					
					int Single = iSpeed%10;//个位
					int Ten = (iSpeed/10)%10;//十位
					int Hundred = (iSpeed/100)%10;//百位

					setInstranSpeed(iSpeed, Single, Ten, Hundred);

				}
				
				//左右闪和双闪
				if ((carpcInfo.getM_iDoubleLight() == 0x01||carpcInfo.getM_iLeftTrunLight() == 0x01
						||carpcInfo.getM_iRightTrunLight() == 0x01) && !bRunnable) {//双闪灯
					bRunnable = true;
					mHandler.removeMessages(ConstData.MSG_GET_DOUBLELIGHT);
					mHandler.sendEmptyMessageDelayed(ConstData.MSG_GET_DOUBLELIGHT, 400);
				}else if (carpcInfo.getM_iDoubleLight() == 0x00 &&carpcInfo.getM_iLeftTrunLight() == 0x00
						&&carpcInfo.getM_iRightTrunLight() == 0x00 && bRunnable){
					bRunnable = false;
					mHandler.removeMessages(ConstData.MSG_GET_DOUBLELIGHT);
					mDoubleIco.setVisibility(View.GONE);
					mLeftlightIco.setVisibility(View.GONE);
					mRightlightIco.setVisibility(View.GONE);
					mCarLeftlight.setVisibility(View.GONE);
					mCarRightlight.setVisibility(View.GONE);
				}
				
				//安全带
				if (carpcInfo.getM_iLFSafebeltAble() == 1 ) {
					if (carpcInfo.getM_iLFSafebelt() == 0 && carpcInfo.getM_iACC() == 1) {
						//Log.i(TAG, "bSafeBelt ="+bSafeBelt);
						if (carpcInfo.getM_fInstantSpeed() > 15 && carpcInfo.getM_iSpeedAble() == 1) {
							//Log.i(TAG, "carpcInfo.getM_fInstantSpeed() ="+carpcInfo.getM_fInstantSpeed());
						    if ( !bSafeBelt) {
						    	if (mGreenStrs.indexOf("请系好安全带") == -1){
						    		sendSafebeltTankuan(1);
								}
						    	bSafeBelt = true;
								mHandler.removeMessages(ConstData.MSG_GET_SAFEBELT);
								mHandler.sendEmptyMessageDelayed(ConstData.MSG_GET_SAFEBELT, 400);
							}
							
						}else{
							//Log.i(TAG, "speed zero");
							mSafeBeltIco.setVisibility(View.VISIBLE);
							bSafeBelt = false;
							mHandler.removeMessages(ConstData.MSG_GET_SAFEBELT);
						}
					} else {
						bSafeBelt = false;
						if (mGreenStrs.indexOf("请系好安全带") != -1){
				    		sendSafebeltTankuan(0);
						}
						mSafeBeltIco.setVisibility(View.GONE);
						mHandler.removeMessages(ConstData.MSG_GET_SAFEBELT);
					}
				}
				
				//电池电压
				if (carpcInfo.getM_iBatteryVolAble() == 1) {
					if (carpcInfo.getM_iEnigineSpeedAble() == 1 && carpcInfo.getM_iEngineSpeed() ==0 && carpcInfo.getM_iACC() == 1) {
						mBatteryIco.setVisibility(View.VISIBLE);
					}else{
						mBatteryIco.setVisibility(View.GONE);
					}
				}
				
				//冷却液温度
				if (carpcInfo.getM_iCooltempAble() == 1 && carpcInfo.getM_iCooltemp() > 110) {
					mCooltempIco.setVisibility(View.VISIBLE);
				} else {
					mCooltempIco.setVisibility(View.GONE);
				}
				//boolean bDoor = false;
				
				//车门
				if (carpcInfo.getM_iDoorAble() == 1) {
					if (carpcInfo.getM_iTailBox() == 1) {
						mCarTaixbox.setVisibility(View.VISIBLE);
					}else{
						mCarTaixbox.setVisibility(View.GONE);
					}
					if (carpcInfo.getM_iHood() == 1) {
						mCarHood.setVisibility(View.VISIBLE);
					}else{
						mCarHood.setVisibility(View.GONE);
					}
					if (carpcInfo.getM_iLFDoor() == 1) {
						//bDoor = true;
						mCarLfdoor.setImageResource(R.drawable.chemen_l1);
					}else{
						mCarLfdoor.setImageResource(R.drawable.guanmen_l1);
					}
					if (carpcInfo.getM_iRFDoor() == 1) {
						//bDoor = true;
						mCarRfdoor.setImageResource(R.drawable.chemen_r1);
					}else{
						mCarRfdoor.setImageResource(R.drawable.guanmen_r1);
					}
					if (carpcInfo.getM_iLRDoor() == 1) {
						//bDoor = true;
						mCarLrdoor.setImageResource(R.drawable.chemen_l2);
					}else{
						mCarLrdoor.setImageResource(R.drawable.guanmen_l2);
					}
					if (carpcInfo.getM_iRRDoor() == 1) {
						//bDoor = true;
						mCarRrdoor.setImageResource(R.drawable.chemen_r2);
					}else{
						mCarRrdoor.setImageResource(R.drawable.guanmen_r2);
					}
					
//							if (bDoor) {
//								mBasecar.	(R.drawable.car_2);
//							} else {
//								mBasecar.setImageResource(R.drawable.car);
//							}
				}
				
				//车窗
				if (carpcInfo.getM_iWindowAble() == 1) {
					if (carpcInfo.getM_iLFWindow() == 1 && carpcInfo.getM_iLFDoor() == 0) {
						mCarLfWindow.setVisibility(View.VISIBLE);
					}else{
						mCarLfWindow.setVisibility(View.GONE);
					}
					if (carpcInfo.getM_iRFWindow() == 1 && carpcInfo.getM_iRFDoor() == 0) {
						mCarRfWindow.setVisibility(View.VISIBLE);
					}else{
						mCarRfWindow.setVisibility(View.GONE);
					}
					if (carpcInfo.getM_iLRWindow() == 1 && carpcInfo.getM_iLRDoor() == 0) {
						mCarLrWindow.setVisibility(View.VISIBLE);
					}else{
						mCarLrWindow.setVisibility(View.GONE);
					}
					if (carpcInfo.getM_iRRWindow() == 1 && carpcInfo.getM_iRRDoor() == 0) {
						mCarRrWindow.setVisibility(View.VISIBLE);
					}else{
						mCarRrWindow.setVisibility(View.GONE);
					}
				}else{
					mCarLfWindow.setVisibility(View.GONE);
					mCarRfWindow.setVisibility(View.GONE);
					mCarLrWindow.setVisibility(View.GONE);
					mCarRrWindow.setVisibility(View.GONE);
				}
				
				//里程
				if (carpcInfo.getM_iTotalMileageAble() == 1) {
					mTxAutoMileage.setText("单次里程："+carpcInfo.getM_iSelfstart_mileage()+" km");
					
					//Log.i(TAG, "carpcInfo.getM_iSelfstart_drivertime() ="+carpcInfo.getM_iSelfstart_drivertime());
					int time  = carpcInfo.getM_iSelfstart_drivertime();
					int chushu = (time -240)/15;
					if (time > 240 && ((time -240)%15) == 0 && chushu != mTired ) {
						Log.i(TAG, "getM_iSelfstart_drivertime");
						mTired = chushu;
						mHandler.removeMessages(ConstData.MSG_GET_COFFIE);
						mHandler.sendEmptyMessage(ConstData.MSG_GET_COFFIE);
					}else if (time < 240) {
						if (mGreenStrs.indexOf("请勿疲劳驾驶") != -1) {
							sendTiredTankuan(0);
						}
					}
					
				}
//				else{
////							mTxAutoName.setVisibility(View.GONE);
//					mTxAutoMileage.setVisibility(View.GONE);
//				}
				if (carpcInfo.getM_iTotalMileageAble() == 1) {
//					mTxTotalMileage.setVisibility(View.VISIBLE);
//							mTxMileageName.setVisibility(View.VISIBLE);
					mTxTotalMileage.setText("总里程："+carpcInfo.getM_iTotalMileage()+" km");
				}
//				else{
//					mTxTotalMileage.setVisibility(View.GONE);
////							mTxMileageName.setVisibility(View.GONE);
//				}
				
				//手刹
				if (carpcInfo.getM_iHandbrakeAble() == 1) {
					if (carpcInfo.getM_iHanderBrake() == 1) {
						mBreakerIco.setVisibility(View.VISIBLE);
//						mSlideHand.setVisibility(View.VISIBLE);
						//2016/09/22 修改车速大于5Km/h
						if (carpcInfo.getM_fInstantSpeed() > 5 && carpcInfo.getM_iACC() == 1) {// && VissCmdParse.getInstance().bSpeedTrend
							if (mRedStrs.indexOf("请松开手刹") == -1){
								sendHandbreakeTankuan(1);
							}
						}else if (carpcInfo.getM_fInstantSpeed() == 0 && carpcInfo.getM_iACC() == 1) {
							if (mRedStrs.indexOf("请松开手刹") != -1){
								sendHandbreakeTankuan(0);
							}
						}
					} else {
						mBreakerIco.setVisibility(View.GONE);
//						mSlideHand.setVisibility(View.GONE);
						if (mRedStrs.indexOf("请松开手刹") != -1){
							sendHandbreakeTankuan(0);
						}
					}
				}
				
				//档位
				if (carpcInfo.getM_iGearAble() == 1) {
//					mGearll.setVisibility(View.VISIBLE);
//					mSlideGearll.setVisibility(View.VISIBLE);
					//恢复默认档位设置
					resetGearState();
					
					//数据正常，档位非R档切出倒车
					if (!mbNoData) {
						sendGearReverse(carpcInfo.getM_iGear());
					}
					
					if (carpcInfo.getM_iILLAble() == 1 && carpcInfo.getM_iILL() == 1) {
						
						//20160919 ADD 增加L档
						if (carpcInfo.getM_iGear() >= 6) {
							mGearS.setImageResource(R.drawable.l_red);
//							mSlideGearS.setImageResource(R.drawable.l_red);
							SetGearInfo(carpcInfo.getM_iGear());
						}
						
						switch (carpcInfo.getM_iGear()) {
						case 1:
							mGearP.setImageResource(R.drawable.gear_red_p);
							break;
						case 2:
							mGearN.setImageResource(R.drawable.gear_red_n);
							break;
						case 3:
							mGearR.setImageResource(R.drawable.gear_red_r);
							break;
						case 4:
							mGearD.setImageResource(R.drawable.gear_red_d);
							break;
						case 5:
							mGearS.setImageResource(R.drawable.gear_red_s);
							break;
						default:
							break;
						}
					} else {
						//20160919 ADD 增加L档
						if (carpcInfo.getM_iGear() >= 6) {
							mGearS.setImageResource(R.drawable.l_blue);
							SetGearInfo(carpcInfo.getM_iGear());
						}
						
						switch (carpcInfo.getM_iGear()) {
						case 1:
							mGearP.setImageResource(R.drawable.gear_big_p);
							break;
						case 2:
							mGearN.setImageResource(R.drawable.gear_big_n);
							break;
						case 3:
							mGearR.setImageResource(R.drawable.gear_big_r);
							break;
						case 4:
							mGearD.setImageResource(R.drawable.gear_big_d);
							break;
						case 5:
							mGearS.setImageResource(R.drawable.gear_big_s);
							break;
						default:
							break;
						}
					}
					
				} 
//				else {
//					mGearll.setVisibility(View.GONE);
//					mSlideGearll.setVisibility(View.GONE);
//				}
				
//				if (carpcInfo.getM_iILLAble() == 1 && carpcInfo.getM_iILL() == 1){
//					mMainHudll.setBackgroundResource(R.drawable.background_red);
//					mSlideInclude.setBackgroundResource(R.drawable.background_red);
//				}else{
//					mMainHudll.setBackgroundResource(R.drawable.background);
//					mSlideInclude.setBackgroundResource(R.drawable.background);
//				}
				
//						if (carpcInfo.getM_iILLAble() == 1 && carpcInfo.getM_iILL() == 1) {
//							mZhibeizhenDipan.setBackgroundResource(R.drawable.zhinanzhen_red);
//						} else {
//							mZhibeizhenDipan.setBackgroundResource(R.drawable.zhinanzhen_blue);
//						}
				
//						if (mIll != carpcInfo.getM_iILL()) {
//							setAngleFxp(mZhinanzhen);
//							mIll = carpcInfo.getM_iILL();
//						}
				
				//油量不足，acc on
				//Log.i(TAG, "bAccOff =="+bAccOff);
				if (carpcInfo.getM_iResidualOilAble() == 1 && carpcInfo.getM_iACC() == 1 && carpcInfo.getM_iResidualOil() >0) {
					//取30次平均值
					int mFuel =getAvgFuel(carpcInfo.getM_iResidualOil());
					if ( mFuel< 15 && mFuel > 0 ){
						mLowfuelIco.setVisibility(View.VISIBLE);
					}
					else if (mFuel > 25 ){
						//bAccOff = false;
						if (mGreenStrs.indexOf("油量不足") != -1){
							sendLowfuelTankuan(0);
						}
						mLowfuelIco.setVisibility(View.GONE);
					}
				}
			}
		});
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
			mTvNextUnit.setText("米");
		} else {
			float nextDis = (float) ((nextdis/100)*0.1);
			mTvNaviDis.setText(nextDis+"");
			mTvNextUnit.setText("公里");
		}
		
		if (nextdis < 2000) {
			mTvNaviTytle.setText("进入");
			String strNextRoad = bundle.getString("NextRoad");
			mTvNaviRoad.setText(strNextRoad);
		} else {
			mTvNaviTytle.setText("沿");
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

	private void sendGearReverse(int m_iGear) {
		
		if (m_iGear > 0 && m_iGear != 3) {
			switch (m_iGear) {
			case 1://P档
				if (CarPcInfo.getInstance().getM_iHanderBrake()==1 && CarPcInfo.getInstance().getM_iHandbrakeAble() ==1) {
					resetReverseModle(0, false);
				} else {
					resetReverseModle(1500, false);
				}
				break;
			default://N档//D档 //S档//L档
				if ( CarPcInfo.getInstance().getM_iSpeedAble() == 0x01 && 
						CarPcInfo.getInstance().getM_fInstantSpeed() > 15){
					resetReverseModle(0, false);
				}else{
					resetReverseModle(5500, false);
				}
				break;
			}
		}
	}


	protected void resetReverseModle(int time, boolean bReverse) {
		
		Intent intent = new Intent();
		intent.setAction(ConstData.ACTION_EXIT_REVERSE);
		intent.putExtra("values", bReverse);
		intent.putExtra("time", time);
		mContext.sendBroadcast(intent);
	}


	protected void resetGearState() {
		
		mGearP.setImageResource(R.drawable.p);
		mGearR.setImageResource(R.drawable.r);
		mGearN.setImageResource(R.drawable.n);
		mGearD.setImageResource(R.drawable.d);
	}


	//通信中断时，左右转向灯显示为关. 所有门都为关闭、手刹为放下的、双闪为关闭。
	protected void resetLinkWrong() {
		
		bRunnable = false;
		CarPcInfo.getInstance().setM_iDoubleLight(0);
		mDoubleIco.setVisibility(View.GONE);
		CarPcInfo.getInstance().setM_iLeftTrunLight(0);
		mLeftlightIco.setVisibility(View.GONE);
		CarPcInfo.getInstance().setM_iRightTrunLight(0);
		mRightlightIco.setVisibility(View.GONE);
		mCarLeftlight.setVisibility(View.GONE);
		mCarRightlight.setVisibility(View.GONE);
		
		mHandler.removeMessages(ConstData.MSG_GET_DOUBLELIGHT);
		
		CarPcInfo.getInstance().setM_iStopLight(0);
		//刹车灯
		mCarStoplight.setVisibility(View.GONE);
		
		CarPcInfo.getInstance().setM_iTailBox(0);
		CarPcInfo.getInstance().setM_iHood(0);
		CarPcInfo.getInstance().setM_iLFDoor(0);
		CarPcInfo.getInstance().setM_iRFDoor(0);
		CarPcInfo.getInstance().setM_iLRDoor(0);
		CarPcInfo.getInstance().setM_iRRDoor(0);
		mCarTaixbox.setVisibility(View.GONE);
		mCarHood.setVisibility(View.GONE);
		mCarLfdoor.setImageResource(R.drawable.guanmen_l1);
		mCarRfdoor.setImageResource(R.drawable.guanmen_r1);
		mCarLrdoor.setImageResource(R.drawable.guanmen_l2);
		mCarRrdoor.setImageResource(R.drawable.guanmen_r2);
		
		CarPcInfo.getInstance().setM_iHanderBrake(0);
		mBreakerIco.setVisibility(View.GONE);
	}


	private int getAvgFuel(int temp) {
		
		int mAvg = 0;
		for (int i = (mFuels.length-1); i > 0; i--) {
			mFuels[i] = mFuels[i-1];
		}
		mFuels[0] = temp;
		//最后一位不为0
		if (mFuels[mFuels.length-1] != 0) {
			int mNum = 0;
			for (int i = 0; i < mFuels.length; i++) {
				mNum += mFuels[i];
			}
			
			mAvg = mNum/mFuels.length;
		}
		Log.i(TAG, "mAvg ==="+mAvg);
		return mAvg;
	}


	protected void setLimit(int mileage) {
		
		if (mileage > mLimit) {//当前速度大于限制速度
			switch (mLimit) {
			case 30:
				bShowLimit = true;
				mLimitSpeed.setImageResource(R.drawable.limit_30);
				mNaviLimitIv.setImageResource(R.drawable.navi_limit_30);
				break;
			case 35:
				bShowLimit = true;
				mLimitSpeed.setImageResource(R.drawable.limit_35);
				mNaviLimitIv.setImageResource(R.drawable.navi_limit_35);
				break;
			case 40:
				bShowLimit = true;
				mLimitSpeed.setImageResource(R.drawable.limit_40);
				mNaviLimitIv.setImageResource(R.drawable.navi_limit_40);
				break;
			case 45:
				bShowLimit = true;
				mLimitSpeed.setImageResource(R.drawable.limit_45);
				mNaviLimitIv.setImageResource(R.drawable.navi_limit_45);
				break;
			case 50:
				bShowLimit = true;
				mLimitSpeed.setImageResource(R.drawable.limit_50);
				mNaviLimitIv.setImageResource(R.drawable.navi_limit_50);
				break;
			case 60:
				bShowLimit = true;
				mLimitSpeed.setImageResource(R.drawable.limit_60);
				mNaviLimitIv.setImageResource(R.drawable.navi_limit_60);
				break;
			case 70:
				bShowLimit = true;
				mLimitSpeed.setImageResource(R.drawable.limit_70);
				mNaviLimitIv.setImageResource(R.drawable.navi_limit_70);
				break;
			case 80:
				bShowLimit = true;
				mLimitSpeed.setImageResource(R.drawable.limit_80);
				mNaviLimitIv.setImageResource(R.drawable.navi_limit_80);
				break;
			case 90:
				bShowLimit = true;
				mLimitSpeed.setImageResource(R.drawable.limit_90);
				mNaviLimitIv.setImageResource(R.drawable.navi_limit_90);
				break;
			case 100:
				bShowLimit = true;
				mLimitSpeed.setImageResource(R.drawable.limit_100);
				mNaviLimitIv.setImageResource(R.drawable.navi_limit_100);
				break;
			case 110:
				bShowLimit = true;
				mLimitSpeed.setImageResource(R.drawable.limit_110);
				mNaviLimitIv.setImageResource(R.drawable.navi_limit_110);
				break;
			case 120:
				bShowLimit = true;
				mLimitSpeed.setImageResource(R.drawable.limit_120);
				mNaviLimitIv.setImageResource(R.drawable.navi_limit_120);
				break;
			default://这些限速之外，应该进行隐藏，20161008 add
				mLimitSpeed.setVisibility(View.GONE);
				mNaviLimitIv.setVisibility(View.GONE);
				bShowLimit = false;
				break;
			}
		}else{
			bShowLimit = false;
			mLimitSpeed.setVisibility(View.GONE);
			mNaviLimitIv.setVisibility(View.GONE);
		}
		
		Log.i(TAG, "bShowLimit =="+bShowLimit+", mileage ="+mileage);
		setCarpcInfo(CarPcInfo.getInstance());
		mHandler.sendEmptyMessage(ConstData.MSG_SHOW_NAVI);
		
	}

	protected void setAngleFxp(int iFx) {
		
		//角度
		int angle = (iFx*2)/45;
		int mTemp =0;
		switch (angle) {
		case 0://正北
		case 15:
			if (mAngle >=1 && mAngle <= 6) {
				mTemp = 360;
			} else {
				mTemp = 0;
			}
			mTvFxWeather.setText("北");
			//mTvFxNavi.setText("北");
			break;
		case 1://东北
		case 2:
			mTemp = 315;
			mTvFxWeather.setText("东北");
			//mTvFxNavi.setText("东北");
			break;
		case 3://正东
		case 4:
			mTemp = 270;
			mTvFxWeather.setText("东");
			//mTvFxNavi.setText("东");
			break;
		case 5://东南
		case 6:
			mTemp = 225;
			mTvFxWeather.setText("东南");
			//mTvFxNavi.setText("东南");
			break;
		case 7://正南
		case 8:
			mTemp = 180;
			mTvFxWeather.setText("南");
			//mTvFxNavi.setText("南");
			break;
		case 9://西南
		case 10:
			mTemp = 135;
			mTvFxWeather.setText("西南");
			//mTvFxNavi.setText("西南");
			break;
		case 11://正西
		case 12:
			mTemp = 90;
			mTvFxWeather.setText("西");
			//mTvFxNavi.setText("西");
			break;
		case 13://西北
		case 14:
			mTemp = 45;
			mTvFxWeather.setText("西北");
			//mTvFxNavi.setText("西北");
			break;
		default:
			break;
		}
		
		mAngle = angle;
		if (maxDegree != mTemp) {
			maxDegree = mTemp;
			// 开始转动
			timer = new Timer();
			// 设置每5毫秒转动一下
			timer.schedule(new NeedleTask(), 0, 5);
			flag = true;
		}
		
		if (degree == 360) {
			degree = 0;
		}
	}

	private void sendSpeedImage(int iSpeed, int hundred, int ten, int single, int src) {
		
		if (iSpeed < 10) {
			mSpeedHundred.setVisibility(View.GONE);
			mSpeedTen.setVisibility(View.GONE);
			setSpeedImage(mSpeedDigit, single, src);
			mSpeedDigit.setVisibility(View.VISIBLE);
		}else if (iSpeed >= 100){
			setSpeedImage(mSpeedHundred, hundred, src);
			mSpeedHundred.setVisibility(View.VISIBLE);
			setSpeedImage(mSpeedTen, ten, src);
			mSpeedTen.setVisibility(View.VISIBLE);
			setSpeedImage(mSpeedDigit, single, src);
			mSpeedDigit.setVisibility(View.VISIBLE);
		}else{
			mSpeedHundred.setVisibility(View.GONE);
			setSpeedImage(mSpeedTen, ten, src);
			mSpeedTen.setVisibility(View.VISIBLE);
			setSpeedImage(mSpeedDigit, single, src);
			mSpeedDigit.setVisibility(View.VISIBLE);
		}
	}

	private void setSpeedImage(ImageView mSpeed, int number, int src) {
		
		switch (number) {
		case 0:
			switch (src) {
			case 1:
				mSpeed.setImageResource(R.drawable.limit_num0);
				break;
			case 2:
				mSpeed.setImageResource(R.drawable.speed_red_num0);
				break;
			case 3:
				mSpeed.setImageResource(R.drawable.speed_num0);
				break;
			default:
				break;
			}
			
			break;
		case 1:
			switch (src) {
			case 1:
				mSpeed.setImageResource(R.drawable.limit_num1);
				break;
			case 2:
				mSpeed.setImageResource(R.drawable.speed_red_num1);
				break;
			case 3:
				mSpeed.setImageResource(R.drawable.speed_num1);
				break;
			default:
				break;
			}
			break;
		case 2:
			switch (src) {
			case 1:
				mSpeed.setImageResource(R.drawable.limit_num2);
				break;
			case 2:
				mSpeed.setImageResource(R.drawable.speed_red_num2);
				break;
			case 3:
				mSpeed.setImageResource(R.drawable.speed_num2);
				break;
			default:
				break;
			}
			break;
		case 3:
			switch (src) {
			case 1:
				mSpeed.setImageResource(R.drawable.limit_num3);
				break;
			case 2:
				mSpeed.setImageResource(R.drawable.speed_red_num3);
				break;
			case 3:
				mSpeed.setImageResource(R.drawable.speed_num3);
				break;
			default:
				break;
			}
			break;
		case 4:
			switch (src) {
			case 1:
				mSpeed.setImageResource(R.drawable.limit_num4);
				break;
			case 2:
				mSpeed.setImageResource(R.drawable.speed_red_num4);
				break;
			case 3:
				mSpeed.setImageResource(R.drawable.speed_num4);
				break;
			default:
				break;
			}
			break;
		case 5:
			switch (src) {
			case 1:
				mSpeed.setImageResource(R.drawable.limit_num5);
				break;
			case 2:
				mSpeed.setImageResource(R.drawable.speed_red_num5);
				break;
			case 3:
				mSpeed.setImageResource(R.drawable.speed_num5);
				break;
			default:
				break;
			}
			break;
		case 6:
			switch (src) {
			case 1:
				mSpeed.setImageResource(R.drawable.limit_num6);
				break;
			case 2:
				mSpeed.setImageResource(R.drawable.speed_red_num6);
				break;
			case 3:
				mSpeed.setImageResource(R.drawable.speed_num6);
				break;
			default:
				break;
			}
			break;
		case 7:
			switch (src) {
			case 1:
				mSpeed.setImageResource(R.drawable.limit_num7);
				break;
			case 2:
				mSpeed.setImageResource(R.drawable.speed_red_num7);
				break;
			case 3:
				mSpeed.setImageResource(R.drawable.speed_num7);
				break;
			default:
				break;
			}
			break;
		case 8:
			switch (src) {
			case 1:
				mSpeed.setImageResource(R.drawable.limit_num8);
				break;
			case 2:
				mSpeed.setImageResource(R.drawable.speed_red_num8);
				break;
			case 3:
				mSpeed.setImageResource(R.drawable.speed_num8);
				break;
			default:
				break;
			}
			break;
		case 9:
			switch (src) {
			case 1:
				mSpeed.setImageResource(R.drawable.limit_num9);
				break;
			case 2:
				mSpeed.setImageResource(R.drawable.speed_red_num9);
				break;
			case 3:
				mSpeed.setImageResource(R.drawable.speed_num9);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}

	private void sendLimitWarn(int warn_info2) {
		
		switch (warn_info2) {
		case 0:
			bLimit = false;
			removeGreenArrayStrs("您已超速");
			hideGreenTankuan();
			Log.i(TAG, "hideGreenTankuan 超速");
			break;
		case 1:
			bLimit = true;
			addGreenArrayStrs("您已超速");
			mTxTankuanGreen.setText("您已超速");
			showGreenTankuan();
			break;
		default:
			break;
		}
	}

	protected void sendTrunRight(int warn_info2) {
		
		switch (warn_info2) {
		case 0:
			removeGreenArrayStrs("右转向灯已打开");
			Log.i(TAG, "hideGreenTankuan 右转向");
			hideGreenTankuan();
			break;
		case 1:
			addGreenArrayStrs("右转向灯已打开");
			mTxTankuanGreen.setText("右转向灯已打开");
			showGreenTankuan();
			break;
		default:
			break;
		}
	}

	protected void sendTrunLeft(int warn_info2) {
		
		switch (warn_info2) {
		case 0:
			removeGreenArrayStrs("左转向灯已打开");
			hideGreenTankuan();
			Log.i(TAG, "hideGreenTankuan 左转向");
			break;
		case 1:
			addGreenArrayStrs("左转向灯已打开");
			mTxTankuanGreen.setText("左转向灯已打开");
			showGreenTankuan();
			break;
		default:
			break;
		}
	}

	protected void sendLowfuelTankuan(int warn_info2) {
		
		switch (warn_info2) {
		case 0:
			mLowfuelIco.setVisibility(View.GONE);
			removeGreenArrayStrs("油量不足");
			hideGreenTankuan();
			Log.i(TAG, "hideGreenTankuan 油量");
			break;
		case 1:
			//bAccOff = false;
			mLowfuelIco.setVisibility(View.VISIBLE);
			addGreenArrayStrs("油量不足");
			mTxTankuanGreen.setText("油量不足");
			showGreenTankuan();
			break;
		default:
			break;
		}
	}


	protected void sendCooltempTankuan(int warn_info2) {
		
		switch (warn_info2) {
		case 0:
			removeRedArrayStrs("冷却液温度过高");
			hideRedTankuan();
			break;
		case 1:
			addRedArrayStrs("冷却液温度过高");
			mTxTankuanRed.setText("冷却液温度过高");
			showRedTankuan();
			break;
		default:
			break;
		}
	}

	protected void sendHtpmsTankuan(int warn_info1, int warn_info2) {
		
		Log.i(TAG, "warn_info1 ="+warn_info1+",warn_info2 ="+warn_info2);
		switch (warn_info2) {
		case 0://报警消失
			switch (warn_info1) {
			case 0:
				if (lfTyreFlag) {
					lfTyreFlag = false;
					mTpmsIco.setVisibility(View.GONE);
					mCarTpms.setVisibility(View.GONE);
				}
				removeRedArrayStrs("左前轮胎压异常");
				hideRedTankuan();
				break;
			case 1:
				if (lrTyreFlag) {
					lrTyreFlag = false;
					mTpmsIco.setVisibility(View.GONE);
					mCarTpms.setVisibility(View.GONE);
				}
				removeRedArrayStrs("左后轮胎压异常");
				hideRedTankuan();
				break;
			case 2:
				if (rfTyreFlag) {
					rfTyreFlag = false;
					mTpmsIco.setVisibility(View.GONE);
					mCarTpms.setVisibility(View.GONE);
				}
				removeRedArrayStrs("右前轮胎压异常");
				hideRedTankuan();
				break;
			case 3:
				if (rrTyreFlag) {
					rrTyreFlag = false;
					mTpmsIco.setVisibility(View.GONE);
					mCarTpms.setVisibility(View.GONE);
				}
				removeRedArrayStrs("右后轮胎压异常");
				hideRedTankuan();
				break;
			default:
				break;
			}
			break;
		case 1://报警出现
			//具体报警
			try {
				ConnConnect.getInstance().getRemoteProxy().sendCmd(ConstUtil.APP2SERVER_OTHER, new int[] {0x03,0x6a,0x05,0x01,0x48});
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mAcc20On = false;
			mHandler.removeMessages(3);
			
			switch (warn_info1) {
			case 0:
				lfTyreFlag = true;
				mTxTankuanRed.setText("左前轮胎压异常");
				addRedArrayStrs("左前轮胎压异常");
				showRedTankuan();
				break;
			case 1:
				lrTyreFlag = true;
				mTxTankuanRed.setText("左后轮胎压异常");
				addRedArrayStrs("左后轮胎压异常");
				showRedTankuan();
				break;
			case 2:
				rfTyreFlag = true;
				mTxTankuanRed.setText("右前轮胎压异常");
				addRedArrayStrs("右前轮胎压异常");
				showRedTankuan();
				break;
			case 3:
				rrTyreFlag = true;
				mTxTankuanRed.setText("右后轮胎压异常");
				addRedArrayStrs("右后轮胎压异常");
				showRedTankuan();
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}

	protected void sendTiredTankuan(int warn_info2) {
		
		Log.i(TAG, "warn_info2 =="+warn_info2);
		switch (warn_info2) {
		case 0:
			removeGreenArrayStrs("请勿疲劳驾驶");
			hideGreenTankuan();
			Log.i(TAG, "hideGreenTankuan 疲劳");
			mTireddriver.setVisibility(View.GONE);
			mHandler.removeMessages(ConstData.MSG_GET_COFFIE);
			mHandler.removeMessages(ConstData.MSG_GET_TRIED);
			break;
		case 1:
			addGreenArrayStrs("请勿疲劳驾驶");
			mTxTankuanGreen.setText("请勿疲劳驾驶");
			
			mHandler.removeMessages(ConstData.MSG_GET_COFFIE);
			mHandler.sendEmptyMessage(ConstData.MSG_GET_COFFIE);
			break;
		default:
			break;
		}
	}

	protected void sendBatteryTankuan(int warn_info2) {
		
		switch (warn_info2) {
		case 0:
			removeGreenArrayStrs("电池电压过低");
			hideGreenTankuan();
			Log.i(TAG, "hideGreenTankuan 电压");
			break;
		case 1:
			addGreenArrayStrs("电池电压过低");
			mTxTankuanGreen.setText("电池电压过低");
			showGreenTankuan();
			break;
		default:
			break;
		}
	}

	protected void sendHoodTankuan(int warn_info2) {
		
		switch (warn_info2) {
		case 0:
			removeRedArrayStrs("引擎盖已打开");
			hideRedTankuan();
			break;
		case 1:
			addRedArrayStrs("引擎盖已打开");
			mTxTankuanRed.setText("引擎盖已打开");
			showRedTankuan();
			break;
		default:
			break;
		}
	}

	protected void sendTaxiboxTankuan(int warn_info2) {
		
		switch (warn_info2) {
		case 0:
			removeGreenArrayStrs("后备箱已打开");
			hideGreenTankuan();
			Log.i(TAG, "hideGreenTankuan 后备箱");
			break;
		case 1:
			addGreenArrayStrs("后备箱已打开");
			mTxTankuanGreen.setText("后备箱已打开");
			showGreenTankuan();
			break;
		default:
			break;
		}
	}

	protected void sendSafebeltTankuan(int warn_info2) {
		
		switch (warn_info2) {
		case 0:
			removeGreenArrayStrs("请系好安全带");
			hideGreenTankuan();
			Log.i(TAG, "hideGreenTankuan 安全带");
			break;
		case 1:
			addGreenArrayStrs("请系好安全带");
			mTxTankuanGreen.setText("请系好安全带");
			showGreenTankuan();
			break;
		default:
			break;
		}
	}

	protected void sendHandbreakeTankuan( int warn_info2) {
		
		switch (warn_info2) {
		case 0:
			removeRedArrayStrs("请松开手刹");
			hideRedTankuan();
			break;
		case 1:
			addRedArrayStrs("请松开手刹");
			mTxTankuanRed.setText("请松开手刹");
			showRedTankuan();
			break;
		default:
			break;
		}
	}

	protected void sendDoorTankuan(int warn_info1, int warn_info2) {
		
		Log.i(TAG, "warn_info1 ="+warn_info1+", warn_info2="+warn_info2);
		switch (warn_info2) {
		case 0://报警消失
			switch (warn_info1) {
			case 0:
				removeRedArrayStrs("左前门已打开");
				hideRedTankuan();
				break;
			case 1:
				removeRedArrayStrs("左后门已打开");
				hideRedTankuan();
				break;
			case 2:
				removeRedArrayStrs("右前门已打开");
				hideRedTankuan();
				break;
			case 3:
				removeRedArrayStrs("右后门已打开");
				hideRedTankuan();
				break;
			default:
				break;
			}
			break;
		case 1://报警出现
			//具体报警
			switch (warn_info1) {
			case 0:
				mTxTankuanRed.setText("左前门已打开");
				addRedArrayStrs("左前门已打开");
				showRedTankuan();
				break;
			case 1:
				mTxTankuanRed.setText("左后门已打开");
				addRedArrayStrs("左后门已打开");
				showRedTankuan();
				break;
			case 2:
				mTxTankuanRed.setText("右前门已打开");
				addRedArrayStrs("右前门已打开");
				showRedTankuan();
				break;
			case 3:
				mTxTankuanRed.setText("右后门已打开");
				addRedArrayStrs("右后门已打开");
				showRedTankuan();
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}

	protected void hideRedTankuan() {
		
		Log.i(TAG, "mTankuanRed.getVisibility() ="+mTankuanRed.getVisibility());
		if (mTankuanRed.getVisibility() == View.VISIBLE){
			Log.i(TAG, "mRedStrs.size() ="+mRedStrs.size());
			if (mRedStrs.size() > 0) {
				int index = mRedStrs.size()-1;
				mTxTankuanRed.setText(mRedStrs.get(index));
			} else {
				Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anima_left_out);
				mTankuanRed.startAnimation(animation);
				mTankuanRed.setVisibility(View.GONE);
			}
		}
	}
	
	protected void hideGreenTankuan() {
		
		Log.i(TAG, "hideGreenTankuan");
		if (mTankunGreen.getVisibility() == View.VISIBLE){
			if (mGreenStrs.size() > 0) {
				int index = mGreenStrs.size()-1;
				if (mGreenStrs.get(index).equals("请勿疲劳驾驶")) {
					if (index > 0) {
						index -= 1;
						mTxTankuanGreen.setText(mGreenStrs.get(index));
					} else {
						Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anima_right_out);
						mTankunGreen.startAnimation(animation);
						mTankunGreen.setVisibility(View.GONE);
						if (bInNavi) {
							mTvRemainDis.setVisibility(View.VISIBLE);
							mTvRemainTime.setVisibility(View.VISIBLE);
						}
					}
				} else {
					mTxTankuanGreen.setText(mGreenStrs.get(index));
				}
			} else {
				Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anima_right_out);
				mTankunGreen.startAnimation(animation);
				mTankunGreen.setVisibility(View.GONE);
				if (bInNavi) {
					mTvRemainDis.setVisibility(View.VISIBLE);
					mTvRemainTime.setVisibility(View.VISIBLE);
				}
			}
		}
	}

	protected void removeRedArrayStrs(String text) {
		
		if (mRedStrs.indexOf(text) != -1) {
			mRedStrs.remove(text);
		}
	}
	
	protected void removeGreenArrayStrs(String text) {
		
		if (mGreenStrs.indexOf(text) != -1) {
			mGreenStrs.remove(text);
		}
	}

	protected void showRedTankuan() {
		
		if (mTankuanRed.getVisibility() == View.GONE) {
			Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anima_left_in);
			mTankuanRed.startAnimation(animation);
			mTankuanRed.setVisibility(View.VISIBLE);
			
		}
	}
	
	protected void showGreenTankuan() {
		
		if (mTankunGreen.getVisibility() == View.GONE) {
			Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anima_right_in);
			mTankunGreen.startAnimation(animation);
			mTankunGreen.setVisibility(View.VISIBLE);
			if (bInNavi) {
				mTvRemainDis.setVisibility(View.GONE);
				mTvRemainTime.setVisibility(View.GONE);
			}
		}
	}

	//记忆报警链表
	protected void addRedArrayStrs(String text) {
		
		if (mRedStrs.indexOf(text) == -1) {
			mRedStrs.add(text);
		}
	}
	
	protected void addGreenArrayStrs(String text) {
		
		if (mGreenStrs.indexOf(text) == -1) {
			mGreenStrs.add(text);
		}
	}

	protected void setTpmsInfo(CarInfo tpmsInfo) {
		
		Log.i(TAG, "mAcc20On =="+mAcc20On);
		if (!mAcc20On && CarPcInfo.getInstance().getM_iACC() == 1) {
		
		boolean bTpms = false;
		
		if (tpmsInfo.getWarnLevel() == 1) {	
			if (tpmsInfo.getLeftFront() == 1) {
				mCarTpms.setImageResource(R.drawable.taiya_l1);
				bTpms = true;
				lfTyreFlag = true;
			}else if (tpmsInfo.getRightFront() == 1) {
				mCarTpms.setImageResource(R.drawable.taiya_r1);
				bTpms = true;
				rfTyreFlag = true;
			}else if (tpmsInfo.getLeftRear() == 1) {
				mCarTpms.setImageResource(R.drawable.taiya_l2);
				bTpms = true;
				lrTyreFlag = true;
			}else if (tpmsInfo.getRightRear() == 1) {
				mCarTpms.setImageResource(R.drawable.taiya_r2);
				bTpms = true;
				rrTyreFlag = true;
			}
		} else {
			if (tpmsInfo.getLeftFront() == 1) {
				mCarTpms.setImageResource(R.drawable.taiya_yellow_l1);
				bTpms = true;
				lfTyreFlag = true;
			}else if (tpmsInfo.getRightFront() == 1) {
				mCarTpms.setImageResource(R.drawable.taiya_yellow_r1);
				bTpms = true;
				rfTyreFlag = true;
			}else if (tpmsInfo.getLeftRear() == 1) {
				mCarTpms.setImageResource(R.drawable.taiya_yellow_l2);
				bTpms = true;
				lrTyreFlag = true;
			}else if (tpmsInfo.getRightRear() == 1) {
				mCarTpms.setImageResource(R.drawable.taiya_yellow_r2);
				bTpms = true;
				rrTyreFlag = true;
			}
		}
		
		if (bTpms) {
			mCarTpms.setVisibility(View.VISIBLE);
			mTpmsIco.setVisibility(View.VISIBLE);
			if (lfTyreFlag) {
				if (mRedStrs.indexOf("左前轮胎压异常") == -1){
					sendHtpmsTankuan(0, 1);
				}
			}
			
			if (rfTyreFlag) {
				if (mRedStrs.indexOf("右前轮胎压异常") == -1){
					sendHtpmsTankuan(2, 1);
				}
			}
			
			if (lrTyreFlag) {
				if (mRedStrs.indexOf("左后轮胎压异常") == -1){
					sendHtpmsTankuan(1, 1);
				}
			}
			
			if (rrTyreFlag) {
				if (mRedStrs.indexOf("右后轮胎压异常") == -1){
					sendHtpmsTankuan(3, 1);
				}
			}
			
		}else{
			mTpmsIco.setVisibility(View.GONE);
			mCarTpms.setVisibility(View.GONE);
			if (lfTyreFlag) {
				lfTyreFlag = false;
				if (mRedStrs.indexOf("左前轮胎压异常") != -1){
					sendHtpmsTankuan(0, 0);
				}
			}
			
			if (rfTyreFlag) {
				rfTyreFlag = false;
				if (mRedStrs.indexOf("右前轮胎压异常") != -1){
					sendHtpmsTankuan(2, 0);
				}
			}
			
			if (lrTyreFlag) {
				lrTyreFlag = false;
				if (mRedStrs.indexOf("左后轮胎压异常") != -1){
					sendHtpmsTankuan(1, 0);
				}
			}
			
			if (rrTyreFlag) {
				rrTyreFlag = false;
				if (mRedStrs.indexOf("右后轮胎压异常") != -1){
					sendHtpmsTankuan(3, 0);
				}
			}
		}
		}
		
	}
	
	private class NeedleTask extends TimerTask {
		@Override
		public void run() {
			if (degree <= maxDegree * (360 / 360.0f)) {
				handler1.sendEmptyMessage(0);
			}
			if (degree > maxDegree * (360 / 360.0f) && flag == true) {
				handler2.sendEmptyMessage(0);
			}
		}
	}
	
	@SuppressLint("HandlerLeak")
	private Handler handler1 = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (degree >= maxDegree * (360 / 360.0f)) {
				if (timer != null) {
					timer.cancel();
					timer = null;
				}
			}else{
			degree += 1.0f;
			animation = new RotateAnimation(degree, maxDegree * (360 / 360.0f),
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			}
			// 设置动画时间1秒
			if (animation != null) {
				animation.setDuration(100);
				animation.setFillAfter(true);
				mZhibeizhenDipan.startAnimation(animation);
			}
			flag = false;
		}
	};
	
	@SuppressLint("HandlerLeak")
	private Handler handler2 = new Handler() {
		@Override
		public void handleMessage(Message msg) { 
			if (degree <= maxDegree * (360 / 360.0f)) {
				if (timer != null) {
					timer.cancel();
					timer = null;
				}
			}else{
			degree += -1.0f;
			animation = new RotateAnimation(degree, maxDegree * (360 / 360.0f),
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			}
			// 设置动画时间5毫秒
			if (animation != null) {
				animation.setDuration(100);
				animation.setFillAfter(true);
				mZhibeizhenDipan.startAnimation(animation);
			}
			flag = true;
		}
	};


	private void registerInterfilter() {
		
		IntentFilter infiter = new IntentFilter();
		infiter.addAction(ConstData.ACTION_GAODE_INFO);
		infiter.addAction(ConstData.MACHINE_SETTING);
		infiter.addAction(ConstData.ACTION_EDOG_INFO);
		infiter.addAction(ConstData.RECORDER_PREVIEW_SLIDE_MODE);
		infiter.addAction(ConstData.WARN_TO＿APP);
		infiter.addAction(ConstData.ACTION_EDOG_STATUS);
		infiter.addAction("com.youzi.disconnect");
		infiter.addAction("com.youzi.connect");
		infiter.addAction("com.update");
		infiter.addAction("com.hiworld.cityname");
		infiter.addAction("com.intent.action.flyaudiosky.SERVICE.TOLAUNCHER");
		mContext.registerReceiver(mReciver, infiter);
	}

	private BroadcastReceiver mReciver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent intent) {
			
			String action = intent.getAction();
			if (action.equals(ConstData.ACTION_EDOG_INFO)) {
				dogStatus = true;
				int[] edog_info = new int[2];
				edog_info[0] = Integer.valueOf(intent.getStringExtra("EXTRA_CUR_SPEED"));
				edog_info[1] = Integer.valueOf(intent.getStringExtra("EXTRA_LIMIT_SPEED"));
				if (mHandler != null) {
					mHandler.obtainMessage(ConstData.MSG_GET_DOG, edog_info).sendToTarget();
				}
			}
//			else if (action.equals(ConstData.RECORDER_PREVIEW_SLIDE_MODE)) {//摄像头预览
//				mSlideMode= intent.getIntExtra("mode", ConstData.RIGHT);//默认隐藏
//				setScreenMode(mSlideMode);
//			}
			else if (action.equals(ConstData.WARN_TO＿APP)) {
				int[] warn_info = intent.getIntArrayExtra("warnArray");
				if (mHandler != null) {
					mHandler.obtainMessage(ConstData.MSG_GET_WARNINFO, warn_info).sendToTarget();
				}
			}else if (action.equals(ConstData.MACHINE_SETTING)) {
				String operation = intent.getStringExtra("operation");
				String name = intent.getStringExtra("name");
				Log.i(TAG, "operation ="+operation+", name="+name);
				if (!operation.isEmpty() && !name.isEmpty()) {
					if (operation.endsWith("stop") && name.equals("navi")) {
						bInNavi = false;
						mIcon = -1;
						mHandler.sendEmptyMessage(ConstData.MSG_SHOW_NAVI);
					}
				}
			}else if (action.equals(ConstData.ACTION_GAODE_INFO)) {
				int KEY_TYPE = intent.getIntExtra("KEY_TYPE", 0);
				Log.i(TAG, "KEY_TYPE ="+KEY_TYPE);
				switch (KEY_TYPE) {
				case 10001://引导信息
					if (!bInNavi) {
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
						Log.i(TAG, "ICON ="+ICON+", strCurRoad ===" +strCurRoad+", strNextRoad ="+strNextRoad+", mRemainTime="+mRemainTime);
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
								Log.i(TAG, "send gaode date!");
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
					Log.i(TAG, "mState ==="+mState);
					if (mState == 8 || mState == 10) {
						bInNavi = true;
						mIcon = -1;
						mHandler.sendEmptyMessage(ConstData.MSG_SHOW_NAVI);
					}else if (mState == 9 || mState == 12){
						bInNavi = false;
						mIcon = -1;
						//mTvNaviRoad.setText("本次导航结束");
						mHandler.sendEmptyMessage(ConstData.MSG_SHOW_NAVI);
					}else if (mState == 2) {
						bInNavi = false;
						//mTvNaviRoad.setText("本次导航结束");
						mHandler.sendEmptyMessage(ConstData.MSG_SHOW_NAVI);
					}
					
					break;
				default:
					break;
				}
			}else if(action.equals(ConstData.ACTION_EDOG_STATUS)){
				dogStatus = intent.getBooleanExtra("status ", false);
				String str = "";
				if(dogStatus){
					str = "电子狗已开启";
				}else{
					str = "电子狗已关闭";
				}
				Toast.makeText(mContext, str, 1000).show();
			}
			else if (intent.getAction().equals("com.intent.action.flyaudiosky.SERVICE.TOLAUNCHER")) {
				try {
					String city = intent.getStringExtra("city");//城市
					String temp = intent.getStringExtra("temp");//温度
					String info = intent.getStringExtra("info");//小雨，晴
					String todayHeight = intent.getStringExtra("todayHeight");//最高温度
					String todayLow = intent.getStringExtra("todayLow");//最低温度
					if(todayHeight==null||todayLow==null){
						todayHeight = intent.getStringExtra("todayH");
						todayLow = intent.getStringExtra("todayL");
					}
					Log.i("WeatherView", "city="+city+",temp="+temp+",info="+info+",todayH="+todayHeight+",todayLow="+todayLow);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(intent.getAction().equals("com.hiworld.cityname")){
				String city = intent.getStringExtra("cityName");
				if(city == null && "".equals(city)){
					return;
				}
				if(cityName == null || "".equals(cityName) || !city.equals(cityName)){
					Log.e("ljq", "get cityName "+cityName);
					cityName = city;//城市
					QueryAsyncTask asyncTask = new QueryAsyncTask();
					asyncTask.execute("");
				}
			}
		}
		
	};

	private void sendVerInfo() {
		
		try {
			String pkName = mContext.getPackageName();
 			String versionName = mContext.getPackageManager().getPackageInfo(
 					pkName, 0).versionName;
 			
 			Intent intent = new Intent(ConstData.HUD_VERSION_NAME);
 			intent.putExtra("name", versionName);
 			Log.i(TAG, "versionName =="+versionName);
 			mContext.sendBroadcast(intent);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void onPause() {
		
		super.onPause();
		bFlag = false;
//		mBMapMan.getLocationManager().removeUpdates(mLocationListener);
//		mBMapMan.stop();
	}

	@Override
	public void onDestroyView() {
		
		if(!getActivity().isFinishing()){
			mContext.unregisterReceiver(mReciver);
//			romoteRadarInfoManager.onDestroy();
			if (locationManager != null) {
				locationManager.removeUpdates(locationListener);
			}
		}
		super.onDestroy();
	}
	
}
