package com.hiworld.canbus.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EncodingUtils;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;

import android.media.AudioManager;  
import android.media.AudioManager.OnAudioFocusChangeListener;  

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;  


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearch.OnWeatherSearchListener;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.hiworld.canbus.fragment.CarStateFragmentnew;
import com.hiworld.canbus.fragment.FragmentAdapter;
import com.hiworld.canbus.fragment.HomeFragment;
import com.hiworld.canbus.fragment.TpmsFragment;
import com.hiworld.canbus.parse.FragmentParseData;
import com.hiworld.canbus.parse.RequestCmdUtil;
import com.hiworld.canbus.receiver.WarnBroadCast;
import com.hiworld.canbus.util.ChangeDataUtil;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.util.RemoteProxymanger;
import com.hiworld.canbus.util.TimeUtils;
import com.hiworld.canbus.util.UtilityClass;
import com.hiworld.canbus.view.MainViewPager;
import com.hiworld.canbus.view.MyTransFormer;
import com.hiworld.canbus.view.ViewPagerScroller;
import com.hiworld.customer.network.NetworkPathUtil;
import com.hiworld.customer.network.SubmitInfor;
import com.hiworld.mycar.t11.R;
import com.youzi.customer.aidl.CallbackTask;
import com.youzi.customer.aidl.CallbackTask.OnReciverIntsAble;
import com.youzi.customer.aidl.RemoteProxy;
import com.youzi.customer.connect.ConnConnect;
import com.youzi.customer.util.ConstUtil;

/**
 * 主页 Copyright: Hiworld Author: Hardy.lai Date: 10/22/2015
 */
// ┏┓　　　┏┓
// ┏┛┻━━━┛┻┓
// ┃　　　　　　　┃ 　
// ┃　　　━　　　┃
// ┃　┳┛　┗┳　┃
// ┃　　　　　　　┃
// ┃　　　┻　　　┃
// ┃　　　　　　　┃
// ┗━┓　　　┏━┛
// ┃　　　┃ 神兽保佑　　　　　　　　
// ┃　　　┃ 代码无BUG！
// ┃　　　┗━━━┓
// ┃　　　　　　　┣┓
// ┃　　　　　　　┏┛
// ┗┓┓┏━┳┓┏┛
// ┃┫┫　┃┫┫
// ┗┻┛　┗┻
public class MainActivity extends BaseActivity implements OnClickListener {// ,
																			// OnTouchListener

	private static final String TAG = "MainActivity";
	private RemoteProxy REMOTE_PROXY = null;
	public static String m_strService;
	private int iPoint = 0;
	private static final int handler_message4 = 103;
	private static final int handler_message5 = 104;
	private static final int handler_message6 = 105;
	private static final int handler_message7 = 106;
	private static final int handler_message8 = 107;
	private static final int handler_message9 = 108;
	private static final int handler_message10 = 109;

	private String SDCard;
	private byte[] data;
	private int lengthData = 0;
	private int index = 0;// 第几帧的索引
	private boolean bFileEixt = false;
	// private RelativeLayout m_layout;
	// private ProgressBar m_proBar;
	// private TextView m_TxTitle;
	// private TextView m_TxProgress;
	// private TextView m_TxTip;
	private boolean bUpdate = false;
	public static boolean bUpdated = true;// 升级完成
	public static boolean bClickUpdate = false;

	// private byte bLinkTx = 0;//默认TX
	// private byte bCanUnlink = 0;//默认胎压数据
	// private byte AccState = 0;//acc状态
	// private int iWrongCount = 0;//错误帧总数

	// private PopMenu popMenu;
	private Context context = MainActivity.this;
	// public static final int[] ICONS = {
	// R.drawable.ico_cailbration,R.drawable.ico_general,
	// R.drawable.ico_help,R.drawable.ico_about};

	public static final String[] iapDirStr = { "/storage/sdcard/hiworld",
			"/storage/sdcard0/hiworld", "/storage/sdcard1/hiworld",
			"/storage/sdcard2/hiworld", "/storage/extsd/hiworld",
			"/storage/extsd1/hiworld", "/storage/extsd2/hiworld",
			"/usb_storage/USB_DISK0/hiworld",
			"/usb_storage1/USB_DISK0/hiworld", "/usb_storage2/hiworld" };
	private List<String> lstFile = new ArrayList<String>(); // 结果 List

	// private ViewPager mPageVp;
	private List<Fragment> mFragmentList = new ArrayList<Fragment>();
	private Fragment mHudFragment = null;

	private FragmentAdapter mFragmentAdapter;
	// private TextView mTabHtpmsTv,mTabChatTv,// mTabContactsTv, mTabHudTv,
	// mTabFriendTv/*, mTabControlTv*/;
	// private LinearLayout mLinearHtpmsTv, mLinearChatTv,
	// //mLinearContactsTv,mLinearHudTv,
	// mLinearFriendTv/*, mLinearControlTv*/;

	// private View inlude_header,include_tab;

	private boolean bDowButton = false;
	private boolean bMoveButton = false;
	private float xTouch, yTouch;

	private MainViewPager mViewPager;
	private List<Fragment> fragments;
	private FragmentAdapter mAdapter;
	// private ImageView img_title;
	private TextView mTitleText;
	private ViewPagerScroller pagerScroller;
	private ImageView mIvMainBluestate, mIvMainUser, imgTitleBg;
	// private MediaPlayer mPlayer;

	private RelativeLayout rl_first_know;
	private ImageView ib_firstknow;

	private ConnectivityManager manager;
	private boolean flag = true;
	// private MKSearch mSearch = null;
	private String cityName = "";
	private boolean bBlueState = false;
	private int mStateBlue_num;
	private HomeFragment fragment;

	public static boolean ispostOption = false;

	private boolean hasNetWork;
	private int cuttentPosition;

	private Time time = new Time();
	String minuteText, hourText;
	// APP肤色图片资源集合
	private int[] skinColourList;
	private MediaPlayer mPlayer;
	
	private AudioManager mAudio;
	

	// 声明AMapLocationClient类对象
	public AMapLocationClient mLocationClient = null;
	public AMapLocationClientOption locationOption;
	// 声明定位回调监听器
	public AMapLocationListener mAMapLocationListener;
	private WeatherSearchQuery mquery;
	private WeatherSearch mweathersearch;
	private OnWeatherSearchListener onWeatherSearchListener;
	private LocalWeatherLive weatherlive; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viss);
		try {
			String versionName = (this.getPackageManager().getPackageInfo(this.getPackageName(), 0)).versionName;
			UtilityClass.setPrefrenceString(MainActivity.this, "appVersion",versionName);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hasNetWork = isNetworkConnected();
		registerReciver();
		init();
		// 初始化控件
		initCtrl();
		initFragment();
		initParseData();
		initRemote();
		setListener();
		requestCmd();
		initAMap();
		
	    //1初始化AudioManager对象
		mAudio =   (AudioManager) getSystemService(Context.AUDIO_SERVICE);		//2 申请焦点

		// mHandler.sendEmptyMessageDelayed(100, 1000);
		Log.d(TAG, "oncreate tpms!!!");
	}

	
//	private AudioManager.OnAudioFocusChangeListener mAudioFocusChange = new AudioManager.OnAudioFocusChangeListener() {
//        @Override
//        public void onAudioFocusChange(int focusChange) {
//            switch (focusChange){
//                case AudioManager.AUDIOFOCUS_LOSS:
//                    //长时间丢失焦点,当其他应用申请的焦点为AUDIOFOCUS_GAIN时，
//                    //会触发此回调事件，例如播放QQ音乐，网易云音乐等
//                    //通常需要暂停音乐播放，若没有暂停播放就会出现和其他音乐同时输出声音
//                    Log.d(TAG, "AUDIOFOCUS_LOSS");
//        
//                    //释放焦点，该方法可根据需要来决定是否调用
//                    //若焦点释放掉之后，将不会再自动获得
//                    mAudio.abandonAudioFocus(mAudioFocusChange);
//                    break;
//                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
//                    //短暂性丢失焦点，当其他应用申请AUDIOFOCUS_GAIN_TRANSIENT或AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE时，
//                    //会触发此回调事件，例如播放短视频，拨打电话等。
//                    //通常需要暂停音乐播放
//                 
//                    Log.d(TAG, "AUDIOFOCUS_LOSS_TRANSIENT");
//                    break;
//                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
//                    //短暂性丢失焦点并作降音处理
//                    Log.d(TAG, "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
//                    break;
//                case AudioManager.AUDIOFOCUS_GAIN:
//                    //当其他应用申请焦点之后又释放焦点会触发此回调
//                    //可重新播放音乐
//                    Log.d(TAG, "AUDIOFOCUS_GAIN");
//                 
//                    break;
//            }
//        }
//    };
	
	
	private void initAMap() {
		manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mNetworkInfo = manager.getActiveNetworkInfo();
		if (mNetworkInfo != null) {
			if (mNetworkInfo.isAvailable()) {
				// 异步获取定位结果
				mAMapLocationListener = new AMapLocationListener() {
					@Override
					public void onLocationChanged(AMapLocation amapLocation) {
						if (amapLocation != null && amapLocation.getErrorCode() == 0) {
							String province = amapLocation.getProvince();
							String city = amapLocation.getCity();
							System.out.println("=======定位" + province+ city);
							Log.e("ljq", "=======定位" + province+ city);
							UtilityClass.setPrefrenceString(MainActivity.this,"province", province);
							UtilityClass.setPrefrenceBoolean(MainActivity.this, "hasProvince", true);
							UtilityClass.setPrefrenceString(MainActivity.this, "city", city);
							UtilityClass.setPrefrenceBoolean(MainActivity.this, "hasCity", true);
							if (!TextUtils.isEmpty(province)&& !TextUtils.isEmpty(city) && !city.equals(cityName)) {
								if (UtilityClass.getPrefrenceBoolean(MainActivity.this, "sendInfo")
										&& UtilityClass.getPrefrenceBoolean(MainActivity.this,"hasSoft")
										&& UtilityClass.getPrefrenceBoolean(MainActivity.this,"hasBrand")) {
									System.out.println("=======定位上传位置");
									try {
										String province_utf = URLEncoder.encode(province, "UTF-8");
										String city_utf = URLEncoder.encode(city, "UTF-8");
										String type_utf = URLEncoder.encode("车机", "UTF-8");
										new SubmitInfor(MainActivity.this).execute(NetworkPathUtil.PATH_W11_SUBMITINFO
												+ UtilityClass.getPrefrenceString(MainActivity.this,"SN")
												+ "&Youzi_CurrentVersion="+ UtilityClass.getPrefrenceString(MainActivity.this,"softVersion")
												+ "&Youzi_Appversion="+ type_utf+ UtilityClass.getPrefrenceString(MainActivity.this,"appVersion")
												+ "&Youzi_Vehiclebrand="+ UtilityClass.getPrefrenceInts(MainActivity.this,"carBrand")
												+ "&Youzi_Addressprovinces="+ province_utf
												+ "&Youzi_Addresscity="+ city_utf);
									} catch (UnsupportedEncodingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
							if (city != null) {
								if(!city.equals(cityName)){
									cityName = city;
									if (isNetworkAvailable(MainActivity.this)) {
										QueryWeather();
									}
								}
							} else {
								Toast.makeText(MainActivity.this,getResources().getString(R.string.tips_noLocate),Toast.LENGTH_SHORT).show();
							}
						}
					}
				};
				// 初始化定位
				mLocationClient = new AMapLocationClient(getApplicationContext());
//				locationOption = new AMapLocationClientOption();
				// 设置定位回调监听
				mLocationClient.setLocationListener(mAMapLocationListener);
				// 启动定位
				mLocationClient.startLocation();

			} else {
				Toast.makeText(MainActivity.this,getResources().getString(R.string.tips_noNet),Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(MainActivity.this,getResources().getString(R.string.tips_noNet),Toast.LENGTH_SHORT).show();
		}
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return false;
		}
		NetworkInfo networkinfo = manager.getActiveNetworkInfo();
		if (networkinfo == null || !networkinfo.isAvailable()) {
			return false;
		}
		return true;
	}

	// 获取高德地图天气
	private void QueryWeather() {
		onWeatherSearchListener = new OnWeatherSearchListener() {
			@Override
			public void onWeatherLiveSearched(
					LocalWeatherLiveResult weatherLiveResult, int rCode) {
				if (rCode == 1000) {
					if (weatherLiveResult != null&& weatherLiveResult.getLiveResult() != null) {
						weatherlive = weatherLiveResult.getLiveResult();
						String wd = "", tq = "";
						wd = weatherlive.getTemperature() + "°";
						tq = weatherlive.getWeather();
						if (fragment != null) {
							if (wd != null && tq != null) {
								fragment.setWeather(wd, tq);
							}
						}
					} else {
						Toast.makeText(context, "获取天气失败", 1000).show();
					}
				} else {
					Toast.makeText(context, "获取天气失败", 1000).show();
				}
			}

			@Override
			public void onWeatherForecastSearched(
					LocalWeatherForecastResult arg0, int arg1) {
			}
		};
		// 检索参数为城市和天气类型，实况天气为WEATHER_TYPE_LIVE、天气预报为WEATHER_TYPE_FORECAST
		mquery = new WeatherSearchQuery(cityName,WeatherSearchQuery.WEATHER_TYPE_LIVE);
		mweathersearch = new WeatherSearch(this);
		mweathersearch.setOnWeatherSearchListener(onWeatherSearchListener);
		mweathersearch.setQuery(mquery);
		mweathersearch.searchWeatherAsyn(); // 异步搜索
	}

	private void initParseData() {
		
		FragmentParseData.getInstance().SetContext(getApplicationContext());
		try {
			// 读取车的配置文件
			FragmentParseData.getInstance().ReadFileOnLine("Car.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void jumpTo(int index) {
		if (index == 2) {
			mViewPager.setCurrentItem(index, false);
		} else {
			mViewPager.setCurrentItem(index, true);
		}
	}

	private void initFragment() {
		fragment = new HomeFragment();
		mFragmentList.add(fragment);
		mFragmentList.add(new CarStateFragmentnew());
		mFragmentList.add(new TpmsFragment());
		mViewPager.setOffscreenPageLimit(4);
		mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
		mViewPager.setAdapter(mFragmentAdapter);
		mViewPager.setPageTransformer(true, new MyTransFormer());
		WarnBroadCast.getOBJ().setOnWarnTipListener(fragment);
	}

	private void setListener() {
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {

				cuttentPosition = arg0;
				switch (arg0) {
				case 0:
			
					mTitleText.setText(hourText + " : " + minuteText);
				
					break;
				case 1:
	
					mTitleText.setText(R.string.carpc_name);
			
					break;
				case 2:
					mTitleText.setText(R.string.tpms_name);
					break;
				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				

			}
		});
	}

	private void initCtrl() {
		mViewPager = (MainViewPager) this.findViewById(R.id.viewPager_main);
		pagerScroller = new ViewPagerScroller(this);
		pagerScroller.setScrollDuration(1000);// 设置时间，时间越长，速度越慢
		pagerScroller.initViewPagerScroll(mViewPager);
	}

	private void readIapFile() {
		
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				
				for (int i = 0; i < iapDirStr.length; i++) {
					if (fileIsExists(iapDirStr[i])) {
						GetFiles(iapDirStr[i], "iap", false);
						if (lstFile.size() > 0) {
							SDCard = lstFile.get(0);
							bFileEixt = fileIsExists(SDCard);
							if (bFileEixt) {
								readSDCardFile(SDCard);
							}
						}
						break;
					}
				}
			}
		});
	}

	private void initRemote() {
		
		if (REMOTE_PROXY == null) {
			REMOTE_PROXY = ConnConnect.getInstance().getRemoteProxy();
			RemoteProxymanger.getInstance().setREMOTE_PROXY(REMOTE_PROXY);
			CallbackTask.getInstance().registerIntsCallBack(ConstUtil.APP2SERVER_OTHER, vissIntsCallBack);
		}
	}

	private OnReciverIntsAble vissIntsCallBack = new OnReciverIntsAble() {

		@Override
		public int getIntsCmd(int[] ints, int size, String text) {
			
			if (PrintActivity.isActivity && size > 0) {
				final LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
				Intent intent = new Intent();
				intent.setAction("com.hiworld.print.data");
				intent.putExtra("com.hiworld.key.print",ChangeDataUtil.ints2Bytes(ints));
				localBroadcastManager.sendBroadcast(intent);
			}
			m_strService = text;
			FragmentParseData.getInstance().parseCmd(ints, size);
			FragmentParseData.getInstance().parseBuffer(ChangeDataUtil.ints2Bytes(ints), size);
			if (size > 0) {
				mHandler.sendEmptyMessage(handler_message9);
			} else {
				mHandler.sendEmptyMessage(handler_message10);
			}
			return 0;
		}

		@Override
		public int update(int[] ints, int size) {
			
			parseIapBuf(ints, size);
			return 0;
		}

	};

	private void init() {
		imgTitleBg = (ImageView) findViewById(R.id.img_title_bg);
		mViewPager = (MainViewPager) findViewById(R.id.viewPager_main);
		// img_title = (ImageView) findViewById(R.id.img_title);
		mTitleText = (TextView) findViewById(R.id.text_title_main);
		// mTitleText.setVisibility(View.INVISIBLE);
		mIvMainBluestate = (ImageView) this.findViewById(R.id.iv_main_bluestate);
		mIvMainBluestate.setOnClickListener(this);
		// mIvMainBluestate.setVisibility(View.INVISIBLE);
		mIvMainUser = (ImageView) this.findViewById(R.id.iv_main_user);
		mIvMainUser.setOnClickListener(this);
		// mTvWarn = (TextView) findViewById(R.id.text_warn);
		rl_first_know = (RelativeLayout) this.findViewById(R.id.rl_first_know);
		ib_firstknow = (ImageView) this.findViewById(R.id.ib_firstknow);
		rl_first_know.setOnTouchListener(new OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				return true;
			}
		});
		ib_firstknow.setOnClickListener(this);
		boolean bFirst = UtilityClass.getPrefrenceBoolean(this, "Frist");
		if (!bFirst) {
			rl_first_know.setVisibility(View.VISIBLE);
			UtilityClass.setPrefrenceBoolean(MainActivity.this, "pcTurnWarn",true);
			UtilityClass.setPrefrenceBoolean(MainActivity.this, "pcDoorWarn",true);
			UtilityClass.setPrefrenceBoolean(MainActivity.this, "pcOilnWarn",true);
			UtilityClass.setPrefrenceBoolean(MainActivity.this, "pcTiredDrive",true);
			/*
			 * UtilityClass.setPrefrenceBoolean(Main.this, "pcBonnetTrunk",
			 * true);
			 */
			UtilityClass.setPrefrenceBoolean(MainActivity.this, "pcHandBrake",true);
			UtilityClass.setPrefrenceBoolean(MainActivity.this, "pcSafeBelt",true);
		} else {
			rl_first_know.setVisibility(View.GONE);
		}
	}

	private long mExitTime;
	// 按钮监听器
	OnClickListener onViewClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.text_sett) {
				// popMenu.showAsDropDown(v);
			}
		}
	};

	// 弹出菜单监听器
	OnItemClickListener popmenuItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			System.out.println("下拉菜单点击" + position);
			switch (position) {
			case 0:
				Intent intent = new Intent(MainActivity.this,CailbrationActivity.class);
				startActivity(intent);
				break;
			case 1:
				Intent intent1 = new Intent(MainActivity.this,SensiActivity.class);
				startActivity(intent1);
				break;
			case 2:
				Intent intent2 = new Intent(MainActivity.this,GuideActivity.class);
				startActivity(intent2);
				break;
			case 3:
				Intent intent3 = new Intent(MainActivity.this,CopyrightActivity.class);
				startActivity(intent3);
				break;
			default:
				break;
			}
			// popMenu.dismiss();
		}
	};

	// 判断文件是否存在
	private boolean fileIsExists(String strFile) {
		
		try {
			File f = new File(strFile);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private String readSDCardFile(String sdcard) {
		
		String res = "";
		try {
			FileInputStream fin = new FileInputStream(sdcard);

			lengthData = fin.available();

			data = new byte[lengthData];
			fin.read(data);

			res = EncodingUtils.getString(data, "UTF-8");

			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 注册接收广播 Copyright: Hiworld Author: Hardy.lai Date: 10/22/2015
	 */
	private void registerReciver() {
		
		IntentFilter USBsdcardFilter = new IntentFilter();
		// USBsdcardFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
		// USBsdcardFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
		// USBsdcardFilter.addAction(Intent.ACTION_MEDIA_EJECT);
		USBsdcardFilter.addAction(ConstData.WARN_TO＿APP);
		// USBsdcardFilter.addDataScheme("file");
		registerReceiver(WarnBroadCast.getOBJ(), USBsdcardFilter);

	}

//	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
//
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			String action = intent.getAction();
//			if (ConstData.ACTION_VOICE_WARN.equals(action)) {
//				int[] array = intent.getIntArrayExtra("warnArray");
//				if (array.length == 3) {
//					if (fragment != null) {
//						fragment.setWarnTips(array);
//					}
//					switch (array[0]) {
//					case 0:// 车门
//						if (array[2] == 1) {// 语音报警
//							if (UtilityClass.getPrefrenceBoolean(MainActivity.this, "pcDoorWarn")) {
//								if (array[1] == 0) {// 左前
//									// playSound(5, 0);
//									
//								    mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[5]);
//								    mPlayer.prepareAsync();
//								    mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//								        @Override
//								        public void onPrepared(MediaPlayer mp) {
//								            // 装载完毕回调
//											mAudio.requestAudioFocus(mAudioFocusChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
//								        	mPlayer.start();
//								        }
//								    });
//								   
//								    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//								        @Override
//								        public void onCompletion(MediaPlayer mp) {
//								            // 在播放完毕被回调
//								        	mAudio.abandonAudioFocus(mAudioFocusChange);
//								        }	
//								    });
//	
//								//	mPlayer.start();
//								} else if (array[1] == 1) {// 左后
//									// playSound(7, 0);
//									mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[7]);
//									mPlayer.start();
//								} else if (array[1] == 2) {// 右前
//									// playSound(6, 0);
//									mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[6]);
//									mPlayer.start();
//								} else if (array[1] == 3) {// 右后
//									// playSound(8, 0);
//									mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[8]);
//									mPlayer.start();
//								}
//							}
//						}
//						break;
//					case 1:// 手刹
//						if (array[2] == 1) {
//							if (UtilityClass.getPrefrenceBoolean(MainActivity.this, "pcHandBrake")) {
//								// playSound(9, 0);
//								mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[9]);
//								mPlayer.start();
//							}
//						}
//						break;
//					case 2:// 安全带
//						if (UtilityClass.getPrefrenceBoolean(MainActivity.this,"pcSafeBelt")) {
//							// playSound(4, 0);
//							if (array[2] == 1) {
//								mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[4]);
//								mPlayer.start();
//							}
//						}
//						break;
//					case 3:// 后备箱
//						if (UtilityClass.getPrefrenceBoolean(MainActivity.this,"pcDoorWarn")) {
//							if (array[2] == 1) {
//								// playSound(13, 0);
//								mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[13]);
//								mPlayer.start();
//							}
//						}
//						break;
//					case 4:// 引擎盖
//						if (UtilityClass.getPrefrenceBoolean(MainActivity.this,"pcDoorWarn")) {
//							if (array[2] == 1) {
//								// playSound(14, 0);
//								mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[14]);
//								mPlayer.start();
//							}
//						}
//						break;
//					case 5:// 电池电压
//						if (array[2] == 1) {
//							// mPlayer = MediaPlayer.create(MainActivity.this,
//							// ConstData.VOICE_WARN[4]);
//							// mPlayer.start();
//						}
//						break;
//					case 6:// 疲劳驾驶
//						if (UtilityClass.getPrefrenceBoolean(MainActivity.this,"pcTiredDrive")) {
//							if (array[2] == 1) {
//								// mTvWarn.setText("您已连续驾驶超过四小时，请停车休息");
//								// mTvWarn.setVisibility(View.VISIBLE);
//								// playSound(12, 0);
//								mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[12]);
//								mPlayer.start();
//							}
//						}
//						break;
//					case 7:// 胎压
//						if (array[2] == 1) {// 语音报警
//							if (array[1] == 0) {// 左前
//								// playSound(0, 0);
//								mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[0]);
//								mPlayer.start();
//							} else if (array[1] == 1) {// 左
//								// playSound(2, 0);
//								mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[2]);
//								mPlayer.start();
//							} else if (array[1] == 2) {// 右前
//								// playSound(1, 0);
//								mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[1]);
//								mPlayer.start();
//							} else if (array[1] == 3) {// 右后
//								// playSound(3, 0);
//								mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[3]);
//								mPlayer.start();
//							}
//						}
//						break;
//					case 8:// 冷却液温度
//							// if (array[2] == 1) {
//							// mPlayer = MediaPlayer.create(MainActivity.this,
//							// ConstData.VOICE_WARN[11]);
//							// mPlayer.start();
//							// }
//						break;
//					case 9:// 剩余油量
//						if (UtilityClass.getPrefrenceBoolean(MainActivity.this,"pcOilnWarn")) {
//							if (array[2] == 1) {
//								// playSound(10, 0);
//								mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[10]);
//								mPlayer.start();
//							}
//						}
//						break;
//					case 10:
//						if (UtilityClass.getPrefrenceBoolean(MainActivity.this,"pcTurnWarn")) {
//							if (array[2] == 1) {
//								// playSound(15, 0);
//								mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[15]);
//								mPlayer.start();
//							}
//						}
//						break;
//					case 11:
//						if (UtilityClass.getPrefrenceBoolean(MainActivity.this,"pcTurnWarn")) {
//							if (array[2] == 1) {
//								// playSound(16, 0);
//								mPlayer = MediaPlayer.create(MainActivity.this,ConstData.VOICE_WARN[16]);
//								mPlayer.start();
//							}
//						}
//						break;
//					default:
//						break;
//					}
//				}
//			}
//
//		}
//
//	};

	protected void parseIapBuf(int[] buffer, int length) {
		
		if (buffer != null && length > 0) {
			switch (length) {
			case 1:
				switch (buffer[0]) {
				case 0x55:// U 升级准备
					if (bClickUpdate) {
						bClickUpdate = false;
						index = 0;
						bUpdate = true;
						RequestCmdUtil.getInstance().clearRequestArray();
						writeIapCmd(new int[] { 0x19, 0x78, 0x02, 0x17 }, 4);
					}
					break;
				case 0x52:// R 升级出错
					if (bUpdate) {
						mHandler.sendEmptyMessage(handler_message5);
						mHandler.removeCallbacks(runnable1);
						mHandler.postDelayed(runnable1, 1000);
					}
					break;
				case 0x42:
					break;
				case 0x53:// S 下一帧
					if (index == 0 && bUpdate && bFileEixt) {
						mHandler.sendEmptyMessage(handler_message4);
					}
					if (bUpdate) {
						sendIapData(index);
						index += 1;
						mHandler.removeCallbacks(runnable3);
						mHandler.postDelayed(runnable3, 5000);
					}
					break;
				case 0x45:// E 升级结束
					if (bUpdate) {
						bUpdated = false;
						index = 0;
						bUpdate = false;
						mHandler.removeCallbacks(runnable3);

						mHandler.sendEmptyMessage(handler_message6);
						mHandler.removeCallbacks(runnable2);
						mHandler.postDelayed(runnable2, 6000);

						writeIapCmd(new int[] { 0xaa, 0xaa, 0xaa, 0xaa }, 4);
						requestCmd();
					}

					break;
				default:
					break;
				}
				break;
			case 2:
				if (buffer[0] == 0x42 && buffer[1] == 0x53) { // BS
					if (data != null) {
						if (bUpdate && bFileEixt) {
							mHandler.obtainMessage(handler_message4).sendToTarget();
						}
						sendIapData(0);
						index = 1;
					}
				} else if (buffer[0] == 0x52 && buffer[1] == 0x45) {// RE
					if (bUpdate) {
						mHandler.obtainMessage(handler_message5).sendToTarget();
						mHandler.removeCallbacks(runnable1);
						mHandler.postDelayed(runnable1, 1000);
					}
				}
				break;
			default:
				break;
			}
		}
	}

	private void writeIapCmd(int[] ints, int size) {
		
		if (REMOTE_PROXY != null) {
			try {
				REMOTE_PROXY.sendUpdate(ConstUtil.APP2SERVER_OTHER, ints);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void sendIapData(int N) {
		
		// Log.d("CopyViewPagerAdapter", "lengthData ="+lengthData+",N ="+N);

		if (data != null && N < lengthData / 136) {
			byte[] iap = new byte[136];
			index = N;
			System.arraycopy(data, N * 136, iap, 0, 136);
			writeIapCmd(ChangeDataUtil.bytes2Ints(iap), iap.length);

			mHandler.obtainMessage(handler_message7).sendToTarget();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		// getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * UI更新线程 Copyright: Hiworld Author: Hardy.lai Date: 10/22/2015
	 */
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			
			super.handleMessage(msg);
			switch (msg.what) {
			case 1234:
				time.setToNow();
				int hour = time.hour;
				int minute = time.minute;

				if (hour < 10) {
					hourText = "0" + hour;
				} else {
					hourText = "" + hour;
				}

				if (minute < 10) {
					minuteText = "0" + minute;
				} else {
					minuteText = "" + minute;
				}
				if (cuttentPosition == 0) {
					mTitleText.setText(hourText + " : " + minuteText);
				}
				mHandler.sendEmptyMessageDelayed(1234, 15000);
				break;
			case handler_message9:
				if (mIvMainBluestate != null)
					mIvMainBluestate.setImageResource(R.drawable.bluetooth_connect_new);
				break;
			case handler_message10:
				if (mIvMainBluestate != null)
					mIvMainBluestate.setImageResource(R.drawable.bluetooth_disconnect_new);
				break;
			default:
				break;
			}
		}

	};

	private Runnable runnable1 = new Runnable() {
		@Override
		public void run() {
			
			index = 0;
			writeBuf(new int[] { 0x02, 0xe0, 0x00, 0x00 }, 4);
		}

	};

	private Runnable runnable2 = new Runnable() {
		@Override
		public void run() {
			
			// m_layout.setVisibility(View.GONE);
			bUpdated = true;
		}
	};

	private Runnable runnable3 = new Runnable() {
		@Override
		public void run() {
			
			index = 0;
			// m_TxTitle.setText(R.string.update_wrong);
			// m_TxTip.setText(R.string.update_wrongtip);
		}

	};

	@Override
	protected void onDestroy() {	
		unregisterReceiver(WarnBroadCast.getOBJ());
		RequestCmdUtil.getInstance().clearRequestArray();
		UtilityClass.setPrefrenceBoolean(MainActivity.this, "weather", false);
		UtilityClass.setPrefrenceBoolean(MainActivity.this, "vehicleBrand",
				false);
		ispostOption = false;
		super.onDestroy();
		if (mPlayer != null) {
			mPlayer.release();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		return false;
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		
		super.onResume();

		Intent intent = new Intent();
		intent.setAction(ConstData.ACTION_TO_HIDE_HTPMS);
		intent.putExtra("show", true);
		sendBroadcast(intent);

		mHandler.sendEmptyMessageDelayed(101, 1000);
		mHandler.removeMessages(1234);
		mHandler.sendEmptyMessage(1234);

		// 获取皮肤图片资源 add by jiaqing.liu
		skinColourList = TimeUtils.getInstance().getAllSkin(MainActivity.this);

	}

	@Override
	protected void onStart() {
		
		super.onStart();
	}

	private void requestCmd() {
		
		RequestCmdUtil.getInstance().initRequestArray(new Integer[] { 0xD9, 0xD3, 0xF3, 0x50, 0x48, 0x49, 0xD1, 0xD2,0xD8 });

	}

	public void writeBuf(int[] ints, int size) {
		
		if (REMOTE_PROXY != null) {
			try {
				REMOTE_PROXY.sendCmd(ConstUtil.APP2SERVER_OTHER, ints);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		// if (keyCode == KeyEvent.KEYCODE_BACK) {
		// if (m_layout.getVisibility() == View.VISIBLE) {
		// m_layout.setVisibility(View.GONE);
		// return true;
		// }

		// }
		return super.onKeyDown(keyCode, event);
	}

	public void GetFiles(String Path, String Extension, boolean IsIterative) // 搜索目录，扩展名，是否进入子文件夹
	{
		File[] files = new File(Path).listFiles();

		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isFile()) {
				if (f.getPath().substring(f.getPath().length() - Extension.length()).equals(Extension)) // 判断扩展名
					lstFile.add(f.getPath());
				if (!IsIterative)
					break;
			} else if (f.isDirectory() && f.getPath().indexOf("/.") == -1) // 忽略点文件（隐藏文件/文件夹）
				GetFiles(f.getPath(), Extension, IsIterative);
		}
	}

	@Override
	public void onClick(View v) {
		
		try {
			switch (v.getId()) {
			// case R.id.id_tab_hud_ll:
			// mPageVp.setCurrentItem(0);
			// break;
			// case R.id.id_tab_htpms_ll:
			// mViewPager.setCurrentItem(0);
			// break;
			// case R.id.id_tab_chat_ll:
			// Log.d("aaa", "setCurrentItem");
			// mViewPager.setCurrentItem(1);
			// break;
			// case R.id.id_tab_friend_ll:
			// mViewPager.setCurrentItem(2);
			// break;
			// case R.id.id_tab_contacts_ll:
			// mPageVp.setCurrentItem(4);
			// break;
			case R.id.iv_main_user:// 个人中心
				Intent intent2 = new Intent(this, MineActivity.class);
				startActivity(intent2);
				overridePendingTransition(R.anim.scale_translate,
						R.anim.my_alpha_action);
				break;
			case R.id.ib_firstknow:
				rl_first_know.setVisibility(View.GONE);
				UtilityClass.setPrefrenceBoolean(this, "Frist", true);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean isNetworkConnected() {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
		if (mNetworkInfo != null) {
			return mNetworkInfo.isAvailable();
		}
		return false;
	}

	public boolean getNetWorkState() {
		return hasNetWork;
	}

}
