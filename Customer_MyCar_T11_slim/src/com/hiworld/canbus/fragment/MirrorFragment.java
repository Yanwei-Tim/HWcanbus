package com.hiworld.canbus.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hiworld.canbus.parse.FragmentParseData;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.view.CustomTextView;
import com.hiworld.canbus.view.MyNumTextView;
import com.hiworld.canbus.view.NumberPickerView;
import com.hiworld.canbus.view.NumberPickerView.OnValueChangeListener;
import com.hiworld.mycar.t11.R;
//import android.widget.CompoundButton;
//import android.widget.CompoundButton.OnCheckedChangeListener;

public class MirrorFragment extends Fragment{
	private Activity activity;
//	private CheckBox check_mirror;
//	private LinearLayout layout_mirror;
	private Button btn_left,btn_right,btn_bottom;
	private TextView unit_left,unit_right,unit_botom;
	private MyNumTextView num_left,num_right,num_bottom;
//	private ImageView img_setting;
	private RelativeLayout hudLayout,/*head_hud,*/layout_picker;
//	private boolean isMirror = false;
//	private boolean isOut = false;
	private NumberPickerView mNumberPickerView;
	public static boolean isChecked = false;
	
	private String[] spinnerList = {"电压V","车速Km/h","转速RPM","剩余油量%","冷却液温度℃","本次行驶时间Min","本次行驶里程Km","本次平均车速Km/h"};
	private String[] unitList = {"电压","车速","转速","剩余油量","冷却液温度","本次行驶时间","本次行驶里程","本次平均车速"};
	
	//选择的某个模块内的某一条信息，对应关系：   ----- 电压-->0,车速-->1,转速-->2,剩余油量-->3，冷却液温度-->4,本次行驶时间-->5,本次行驶里程-->6,本次平均车速-->7
	int speed ,engineSpeed,restOil,coolWater,drivingTimeSinceStart,mileageSinceStart,averageSinceStart;//车速    1
	float battery ;//电池电压    0
	
	private SharedPreferences preferences;//用来保存每个模块选择的是什么
	private int item1,item2,item3;
	private int currentItem = 0;//当前点击的是哪个模块
	private int currentChildItem;//判断当前是哪个子模块
	
	private Time time = new Time();
	private String hourText,minuteText;
	private CustomTextView timeText;
	
//	private boolean bDog = false;//是否有电子狗信息
	private int mLimit = 0;//限速
	
	/*//上下边进出动画
	Animation topOut,topIn,bottomOut,bottomIn;*/
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 101:
				time.setToNow();
				int hour = time.hour;
				int minute = time.minute;
				if (hour < 10) {
					hourText = "0"+hour;
				}else {
					hourText = ""+hour;
				}
				if (minute < 10) {
					minuteText = "0"+minute;
				}else {
					minuteText = ""+minute;
				}
				timeText.setText(hourText+" : "+minuteText);
				mHandler.sendEmptyMessageDelayed(101, 15000);
				break;
//			case 800:
//				initCarInforData(new CarInfo());
//				break;
			case ConstData.HANDLER_MESSAGE_CARPC:
				CarPcInfo hudInfo = (CarPcInfo) msg.obj;
				initCarInforData(hudInfo);
				break;
			case 500:
				layout_picker.setVisibility(View.GONE);
//				System.out.println("========当前Value"+currentChildItem);
				setItemInfor(currentItem,(currentChildItem));
				if (currentItem == 1) {
					setTextUnit(unit_left,(currentChildItem));
					preferences.edit().putInt("item1", (currentChildItem)).commit();
//					System.out.println("=======获取存储item1="+currentChildItem);
				}else if (currentItem == 2) {
					setTextUnit(unit_right,(currentChildItem));
					preferences.edit().putInt("item2", (currentChildItem)).commit();
//					System.out.println("=======获取存储item2="+currentChildItem);
				}else if (currentItem == 3) {
					setTextUnit(unit_botom,(currentChildItem));
					preferences.edit().putInt("item3", (currentChildItem)).commit();
//					System.out.println("=======获取存储item3="+currentChildItem);
				}
				break;
			case 66:
				int[] edog_info = (int[]) msg.obj;
				if (edog_info.length < 8 ) {
					break;
				}
				//距离，单位米
				int mileage = edog_info[3];
				mLimit = edog_info[4];
				if (mileage <= 0) {
					return;
				}
				break;
			default:
				break;
			}
		}
	};
	
	public void onAttach(android.app.Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
		FragmentParseData.getInstance().setMirrorHandler(mHandler);
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_hud_new, container, false);
		initView(view);
		setListener();
//		setCarInforDataChangedListener();
//		if (!BluetoothControl.getInstance().getConnectedState()) {
//			initCarInforData(new CarInfo());
//		}else {
//			initCarInforData(CarInfo.getInstance());
//		}
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				initCarInforData(CarPcInfo.getInstance());
			}
		});
		mHandler.sendEmptyMessage(101);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(ConstData.ACTION_EDOG_INFO);
		intentFilter.addAction("com.youzi.disconnect");
		intentFilter.addAction("com.youzi.connect");
		getActivity().registerReceiver(mReceiver, intentFilter);
		return view;
	}
	
	@Override
	public void onDestroyView() {
		
		super.onDestroyView();
		getActivity().unregisterReceiver(mReceiver);
	}

	private void initView(View view) {
		
//		check_mirror = (CheckBox) view.findViewById(R.id.check_mirror_port);
//		layout_mirror = (LinearLayout) view.findViewById(R.id.layout_mirror_port);
		btn_left = (Button) view.findViewById(R.id.spinner_left);
		btn_right = (Button) view.findViewById(R.id.spinner_right);
		btn_bottom = (Button) view.findViewById(R.id.spinner_bottom_big);
		
		unit_left = (TextView) view.findViewById(R.id.unitText_left);
		unit_right = (TextView) view.findViewById(R.id.unitText_right);
		unit_botom = (TextView) view.findViewById(R.id.unitText_bottom);
		
		num_left = (MyNumTextView) view.findViewById(R.id.numText_left);
		num_right = (MyNumTextView) view.findViewById(R.id.numText_right);
		num_bottom = (MyNumTextView) view.findViewById(R.id.numText_bottom_big);
		
		timeText = (CustomTextView) view.findViewById(R.id.timeText_port);
		
//		img_setting = (ImageView) view.findViewById(R.id.img_hud_setting);
		
		hudLayout = (RelativeLayout) view.findViewById(R.id.layout_hudBac);
//		head_hud = (RelativeLayout) view.findViewById(R.id.layout_hud_rr);
		layout_picker = (RelativeLayout) view.findViewById(R.id.layout_picker);
		
		preferences = getActivity().getSharedPreferences("youzi", Context.MODE_PRIVATE);
		item1 = preferences.getInt("item1",6 );//item1的默认选项是电压
		item2 = preferences.getInt("item2", 0);//item2的默认选项是发动机转速
		item3 = preferences.getInt("item3", 1);//item3的默认选项是车速
		
		/*topOut = AnimationUtils.loadAnimation(getActivity(), R.anim.top_out);
		topOut.setFillAfter(true);
		
		topIn = AnimationUtils.loadAnimation(getActivity(), R.anim.top_in);
		topIn.setFillAfter(true);*/
		
		mNumberPickerView = (NumberPickerView) view.findViewById(R.id.number_picker1);
		mNumberPickerView.setOnValueChangedListener(new OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {
				
				currentChildItem = newVal;
				mHandler.sendEmptyMessage(500);
			}
		});
		mNumberPickerView.refreshByNewDisplayedValues(unitList);
		
	}

	private void setListener() {
		
		/*check_mirror.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if (isChecked) {
					layout_mirror.setScaleY(-1);
					btn_left.setVisibility(View.INVISIBLE);
					btn_right.setVisibility(View.INVISIBLE);
					btn_bottom.setVisibility(View.INVISIBLE);
					unit_left.setTextSize(14);
					unit_right.setTextSize(14);
					unit_botom.setTextSize(14);
					
					head_hud.startAnimation(topOut);
//					isOut = true;
//					mDisplayListener.disPlayOrHide(true);
					check_mirror.setVisibility(View.INVISIBLE);
					
					isMirror = true;
					isChecked = true;
				}else {
					isMirror = false;
					layout_mirror.setScaleY(1);
					isChecked = false;
				}
			}
		});*/
		
		btn_left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				currentItem = 1;
				layout_picker.setVisibility(View.VISIBLE);
			}
		});
		
		btn_right.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				currentItem = 2;
				layout_picker.setVisibility(View.VISIBLE);
			}
		});
		
		btn_bottom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				currentItem = 3;
				layout_picker.setVisibility(View.VISIBLE);
			}
		});
		
		/*img_setting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (!isMirror) {
					btn_left.setVisibility(View.VISIBLE);
					btn_right.setVisibility(View.VISIBLE);
					btn_bottom.setVisibility(View.VISIBLE);
					unit_left.setTextSize(20);
					unit_right.setTextSize(20);
					unit_botom.setTextSize(20);
//					check_mirror.setVisibility(View.INVISIBLE);
				}
			}
		});*/
		
		hudLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/*if (!isOut && check_mirror.getVisibility() == View.VISIBLE ) {
//					Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.top_out);
//					animation.setFillAfter(true);
					head_hud.startAnimation(topOut);
					
					isOut = true;
					mDisplayListener.disPlayOrHide(true);
					check_mirror.setVisibility(View.INVISIBLE);
				}else if (!Main.isBottomHide) {
					head_hud.startAnimation(topOut);
					isOut = true;
					mDisplayListener.disPlayOrHide(true);
					check_mirror.setVisibility(View.INVISIBLE);
				}else if (isOut) {
//					Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.top_in);
//					animation.setFillAfter(true);
					head_hud.startAnimation(topIn);
					mDisplayListener.disPlayOrHide(false);
					isOut = false;
					check_mirror.setVisibility(View.VISIBLE);
				}
				
				if (isOut) {
					head_hud.startAnimation(topIn);
//					mDisplayListener.disPlayOrHide(false);
					isOut = false;
					check_mirror.setVisibility(View.VISIBLE);
				}else {
					head_hud.startAnimation(topOut);
					isOut = true;
//					mDisplayListener.disPlayOrHide(true);
					check_mirror.setVisibility(View.INVISIBLE);
				}*/
				
				if (btn_left.getVisibility() == View.INVISIBLE && btn_right.getVisibility() == 
						View.INVISIBLE && btn_bottom.getVisibility() == View.INVISIBLE) {
					btn_left.setVisibility(View.VISIBLE);
					btn_right.setVisibility(View.VISIBLE);
					btn_bottom.setVisibility(View.VISIBLE);
					unit_left.setTextSize(18);
					unit_right.setTextSize(18);
					unit_botom.setTextSize(18);
				}else {
					btn_left.setVisibility(View.INVISIBLE);
					btn_right.setVisibility(View.INVISIBLE);
					btn_bottom.setVisibility(View.INVISIBLE);
					unit_left.setTextSize(22);
					unit_right.setTextSize(22);
					unit_botom.setTextSize(22);
				}
				
				/*unit_left.setTextSize(22);
				unit_right.setTextSize(22);
				unit_botom.setTextSize(22);*/
//               ((Main)getActivity()).sendHideArrowMessage();
			}
		});
		
		/*((Main)getActivity()).setCheckChangedListener(new checkChangedListener() {
			
			@Override
			public void onCheckChanged(int position) {
				
				if (position != 2) {
					btn_left.setVisibility(View.INVISIBLE);
					btn_right.setVisibility(View.INVISIBLE);
					btn_bottom.setVisibility(View.INVISIBLE);
					unit_left.setTextSize(14);
					unit_right.setTextSize(14);
					unit_botom.setTextSize(14);
					if (!check_mirror.isChecked()) {
						check_mirror.setVisibility(View.VISIBLE);
					}
					if (layout_picker.getVisibility() == View.VISIBLE) {
						layout_picker.setVisibility(View.GONE);
					}
				}else {
					if (check_mirror.isChecked() && check_mirror.getVisibility() == View.INVISIBLE) {
						mDisplayListener.disPlayOrHide(true);
						check_mirror.setVisibility(View.INVISIBLE);
						head_hud.startAnimation(topOut);
						isOut = true;
					}
				}
				if (position == 1 || position == 3) {
					if (!check_mirror.isChecked()) {
						head_hud.startAnimation(topIn);
						isOut = false;
					}
				}
			}
		});*/
	}
	
//	private static DisPlayItem mDisplayListener;
	
//	public static void setDisplayListener(DisPlayItem displayListener){
//		mDisplayListener = displayListener;
//	}
	
//	private void setCarInforDataChangedListener() {
//		
//		ParseData.getInstance().setMirrorDataChangedPortListener(new CarInfoDataChengeListener() {
//			
//			@Override
//			public void onCarInfoDataChangedListener(CarInfo carInfo) {
//				
//				initCarInforData(carInfo);
//			}
//		});
//	}
	
	private void initCarInforData(final CarPcInfo carInfo){
		getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				if (carInfo.getM_iBatteryVolAble() == 1) {
					battery = carInfo.getM_fBatteryVol();//电池电压    0
				}else {
					battery = 0;
				}
				speed = (int) carInfo.getM_fInstantSpeed();//车速    1
				
				if (carInfo.getM_iEnigineSpeedAble() == 1) {
					engineSpeed = carInfo.getM_iEngineSpeed();//发动机转速    2
				}else {
					engineSpeed = 0;
				}
				if (carInfo.getM_iResidualOilAble() == 1) {
					restOil = carInfo.getM_iResidualOil();//剩余油位    3
				}else {
					restOil = 0;
				}
				if (carInfo.getM_iCooltempAble() == 1) {
					coolWater = carInfo.getM_iCooltemp();//冷却液温度    4
				}else {
					coolWater = 0;
				}
				
				if (carInfo.getM_iTotalMileageAble() == 1) {
					drivingTimeSinceStart = carInfo.getM_iSelfstart_drivertime();//本次行驶时间    5
					mileageSinceStart = carInfo.getM_iSelfstart_mileage();//本次行驶里程   6
					averageSinceStart = carInfo.getM_fSelfstart_avgspeed();//自启动后平均车速     7
				}else {
					drivingTimeSinceStart = 0;
					mileageSinceStart = 0;
					averageSinceStart = 0;
				}
			
//				System.out.println("======电池电压"+((int)(battery*10)));
				item1 = preferences.getInt("item1",6 );//item1的默认选项是电压
				item2 = preferences.getInt("item2", 0);//item2的默认选项是发动机转速
				item3 = preferences.getInt("item3", 1);//item3的默认选项是车速
//				System.out.println("=======获取item1="+item1+",item2 = "+item2+",item3="+item3);
				setItemInfor(1, item1);
				setItemInfor(2, item2);
				setItemInfor(3, item3);
//				if (carInfo.getM_iILLAble() == 1 && carInfo.getM_iILL() == 1) {
//					hudLayout.setBackgroundResource(R.drawable.background_red);
//				}else{
//					hudLayout.setBackgroundResource(R.drawable.background);
//				}
			}
		});
	}
	
	private void setTextUnit(final TextView textView, final int position){
		getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				if (position >= 0 && position <= spinnerList.length) {
					textView.setText(spinnerList[position]);
				}
			}
		});
	}
	
	private void setItemInfor(int itemPosition,int selectedPosition){
		switch (itemPosition) {
		case 1:
			setDetailCarInfor(num_left,unit_left,selectedPosition);
			break;
		case 2:
			setDetailCarInfor(num_right,unit_right,selectedPosition);
			break;
		case 3:
			setDetailCarInfor(num_bottom,unit_botom,selectedPosition);
			break;
		default:
			break;
		}
	}
	
	private void setDetailCarInfor(final TextView numText,final TextView unitText,final int selectedPositon){
		getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
//				if (!BluetoothControl.getInstance().getConnectedState()) {
//					numText.setText("--");
//				}else 
					if (selectedPositon >= 0 && selectedPositon <= spinnerList.length) {
					switch (selectedPositon) {
						//电压-->0,车速-->1,转速-->2,剩余油量-->3，冷却液温度-->4,本次行驶时间-->5,本次行驶里程-->6,本次平均车速-->7
						case 0:
							if (battery == 0) {
								if (CarPcInfo.getInstance().getM_iBatteryVolAble() == 1) {
									numText.setText("0");
								}else {
									numText.setText("--");
								}
							}else {
								numText.setText(battery+"");
							}
							break;
						case 1://车速
//							if (BluetoothControl.getInstance().getConnectedState()) {
								if (mLimit > 0) {
									if (speed >= (int)mLimit*1.1) {
										numText.setTextColor(Color.parseColor("#D8202B"));
									}else {
										numText.setTextColor(Color.parseColor("#05FDFD"));
									}
								}else {
									numText.setTextColor(Color.parseColor("#05FDFD"));
								}
								if (speed >= 0 && speed <240) {
									numText.setText(speed+"");	
								}
//							}else {
//								numText.setText("--");
//							}
							break;
						case 2:
							if (CarPcInfo.getInstance().getM_iEnigineSpeedAble() == 1) {
								numText.setText(engineSpeed+"");		
							}else {
								numText.setText("--");
							}
							break;
						case 3:
							if (CarPcInfo.getInstance().getM_iResidualOilAble() == 1) {
								numText.setText(restOil+"");
							}else {
								numText.setText("--");
							}
							break;
						case 4:
							if (CarPcInfo.getInstance().getM_iCooltempAble() == 1) {
								numText.setText(coolWater+"");
							}else {
								numText.setText("--");
							}
							break;
						case 5:
							if (CarPcInfo.getInstance().getM_iTotalMileageAble() == 1) {
								numText.setText(drivingTimeSinceStart+"");
							}else {
								numText.setText("--");
							}
							break;
						case 6:
							if (CarPcInfo.getInstance().getM_iTotalMileageAble() == 1) {
								numText.setText(mileageSinceStart+"");
							}else {
								numText.setText("--");
							}
							break;
						case 7:
							if (CarPcInfo.getInstance().getM_iTotalMileageAble() == 1) {
								numText.setText(averageSinceStart+"");
							}else {
								numText.setText("--");
							}
							break;
						default:
							break;
					}
				}
				unitText.setText(spinnerList[selectedPositon]);
			}
		});
	}
	
	private BroadcastReceiver mReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			
//			if ("com.youzi.disconnect".equals(intent.getAction())) {
//				initCarInforData(new CarInfo());
//			}else 
				if (ConstData.ACTION_EDOG_INFO.equals(intent.getAction())) {
//				bDog = true;
				int[] edog_info = intent.getIntArrayExtra("param");
				mHandler.obtainMessage(66, edog_info).sendToTarget();
			}
//				else if ("com.youzi.connect".equals(intent.getAction())) {
//				initCarInforData(CarInfo.getInstance());
//			}
		}
	};
	
}
