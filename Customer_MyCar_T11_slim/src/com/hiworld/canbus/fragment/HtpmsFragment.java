package com.hiworld.canbus.fragment;


import com.hiworld.canbus.activity.CailbrationActivity;
import com.hiworld.canbus.activity.PrintActivity;
import com.hiworld.canbus.activity.SensiActivity;
import com.hiworld.canbus.parse.FragmentParseData;
import com.hiworld.canbus.util.CarInfo;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.mycar.t11.R;
import com.youzi.customer.connect.ConnConnect;
import com.youzi.customer.util.ConstUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HtpmsFragment extends Fragment {
	
	private RelativeLayout  layout;
	private ImageView m_imgCarWarn,m_imgCarRun, m_imgHiworld;
	private ImageView m_imgLevel;
	private TextView m_textWarninfo;
	private TextView m_TxConnect;
	private TextView m_textWarnLink;
	
	//点击次数
    private int iClickCount;
    //轮速,四个柱状图
    private TextView tx_lfwheel_schedule,tx_rfwheel_schedule,tx_lrwheel_schedule,tx_rrwheel_schedule;
    private int iTask;//动画计数
    private boolean bRunTask = false;
   
    private int iTimerMill;//转速的定时器时长
    private int iGetZeroCount ;
    

    private int iWrongCount = 0;//错误帧总数
    
    private Activity activity;
    private View mContentView; 
    private RelativeLayout main_bg;
    
    
    private ImageView mIvSpeedHundred, mIvSpeedTen, mIvSpeedFloat;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		//Log.i("aaa", "onCreate !");
		runConnect();
	}
	

	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		this.activity = activity;
		FragmentParseData.getInstance().setHtpmsHandler(mHandler);
		//Log.i("aaa", "onAttach !");
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		/** 
         * 防止Fragment多次切换时调用onCreateView重新加载View 
         */  
        if (null == mContentView) {
        	mContentView = inflater.inflate(R.layout.fragment_htpms, container,false);
        	initView(mContentView);
    		writeSennis();
    		activity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					
					setCarPc(CarPcInfo.getInstance());
				}
			});
        }else  {  
            /** 
             * 缓存的rootView需要判断是否已经被加过parent， 
             * 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。 
             */  
            ViewGroup parent = (ViewGroup) mContentView.getParent();  
            if (parent != null)  {  
                parent.removeView(mContentView);  
            }  
        }  
		return mContentView;
	}

	protected void setCarPc(CarPcInfo instance) {
		
		int iSpeed = (int) instance.getM_fInstantSpeed();
		if (iSpeed == 0xffff) {
			iSpeed = 0;
		}
		int Single = iSpeed%10;//个位
		int Ten = (iSpeed/10)%10;//十位
		int Hundred = (iSpeed/100)%10;//百位
		
		mIvSpeedTen.setVisibility(View.VISIBLE);
		mIvSpeedHundred.setVisibility(View.VISIBLE);
		mIvSpeedFloat.setVisibility(View.VISIBLE);
		if (iSpeed  < 10) {
			 mIvSpeedHundred.setVisibility(View.GONE);
			 mIvSpeedTen.setVisibility(View.GONE);
		     mIvSpeedFloat.setImageResource(R.drawable.big_num0+Single);
		}else if (iSpeed >= 100) {
			mIvSpeedHundred.setImageResource(R.drawable.big_num0+Hundred);
			mIvSpeedTen.setImageResource(R.drawable.big_num0+Ten);
			mIvSpeedFloat.setImageResource(R.drawable.big_num0+Single);
		}else{
			mIvSpeedHundred.setVisibility(View.GONE);
			mIvSpeedTen.setImageResource(R.drawable.big_num0+Ten);
			mIvSpeedFloat.setImageResource(R.drawable.big_num0+Single);
		}
//		if (iSpeed == 0) {
//			mIvSpeedHundred.setVisibility(View.GONE);
//			mIvSpeedTen.setVisibility(View.GONE);
//		}else{
//			mIvSpeedHundred.setVisibility(View.VISIBLE);
//			mIvSpeedTen.setVisibility(View.VISIBLE);
//		}
//		
//		mIvSpeedHundred.setImageResource(R.drawable.big_num0+Hundred);
//		mIvSpeedTen.setImageResource(R.drawable.big_num0+Ten);
//		mIvSpeedFloat.setImageResource(R.drawable.big_num0+Single);
//		if (instance.getM_iILLAble() == 1 && instance.getM_iILL() == 1) {
//			main_bg.setBackgroundResource(R.drawable.background_red);
//		}else{
//			main_bg.setBackgroundResource(R.drawable.background);
//		}
	}


	
	private void runConnect(){
		
		mHandler.removeMessages(0x401);
		mHandler.sendEmptyMessageDelayed(0x401, 10*1000);
	}


	private void initView(View view) {
		
		m_imgHiworld = (ImageView)view.findViewById(R.id.img_main_hiworld);
		m_imgHiworld.setOnClickListener(onViewClick);
		
		layout = (RelativeLayout)view.findViewById(R.id.layout);
		m_TxConnect = (TextView)view.findViewById(R.id.text_connect);
		m_imgCarWarn = (ImageView)view.findViewById(R.id.img_car_warn);
		m_imgCarRun = (ImageView)view.findViewById(R.id.img_carinfo);
		m_imgLevel  = (ImageView)view.findViewById(R.id.img_level);
		m_textWarninfo  = (TextView)view.findViewById(R.id.text_warninfo);
		m_textWarnLink =  (TextView)view.findViewById(R.id.text_warninfo2);
		
		tx_lfwheel_schedule = (TextView)view.findViewById(R.id.text_lf_wheel);
		tx_rfwheel_schedule = (TextView)view.findViewById(R.id.text_rf_wheel);
		tx_lrwheel_schedule = (TextView)view.findViewById(R.id.text_lr_wheel);
		tx_rrwheel_schedule = (TextView)view.findViewById(R.id.text_rr_wheel);
		
		mIvSpeedHundred  = (ImageView)view.findViewById(R.id.iv_main_speed_hundred);
		mIvSpeedTen  = (ImageView)view.findViewById(R.id.iv_main_speed_ten);
		mIvSpeedFloat = (ImageView)view.findViewById(R.id.iv_main_speed_float);
		main_bg = (RelativeLayout) view.findViewById(R.id.main_bg);
	}

	// 按钮监听器
		OnClickListener onViewClick = new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				if (v.getId() == R.id.img_main_hiworld) {
					iClickCount++;
					//Log.d(TAG, "iClickCount =="+iClickCount);
					mHandler.removeMessages(0x400);
					mHandler.sendEmptyMessageDelayed(0x400, 8000);
					//Log.d(TAG, "iClickCount2 =="+iClickCount);
					if (iClickCount == 8) {
						iClickCount = 0;
						Intent intent = new Intent(activity, PrintActivity.class);
						startActivity(intent);
					}
				}
			}
			
		};
		
		@SuppressLint("HandlerLeak")
		private final Handler mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				
				super.handleMessage(msg);
				switch (msg.what) {
				case ConstData.HANDLER_MESSAGE_CARPC:
					CarPcInfo hudInfo = (CarPcInfo) msg.obj;
					setCarPc(hudInfo);
					break;
				case ConstData.MESSAGE_TIRE_FAILURE://轮胎报警
					CarInfo carInfo = (CarInfo) msg.obj;
					setTPMSAlarm(carInfo);
					break;
				case ConstData.MESSAGE_TPMS_SERIAL_HIGHSPEED://连接状态
					CarInfo carInfo1 = (CarInfo) msg.obj;
					setTPMSLink(carInfo1);
					break;
				case ConstData.MESSAGE_TPMS_SWHEEL:
					CarInfo carInfo2 = (CarInfo) msg.obj;
					float iLFWheelSp = carInfo2.getiLFWheelSp();
					float iRFWheelSp = carInfo2.getiRFWheelSp();
					float iLRWheelSp = carInfo2.getiLRWheelSp();
					float iRRWheelSp = carInfo2.getiRRWheelSp();
					setWheelAnima((iLFWheelSp+iRFWheelSp+iLRWheelSp+iRRWheelSp)/4);
					break;
				case ConstData.MESSAGE_TPMS_CONNECT:
					runConnect();
					break;
				case 0x400:
					iClickCount = 0;
					break;
				case 0x401:
					m_TxConnect.setText(R.string.WarnSwitch_unconnect);
					m_textWarnLink.setText(R.string.WarnSwitch_unconnect);
					if (bRunTask) {
						bRunTask = false;
					    mHandler.removeCallbacks(runTask);
					    setWheelShow(false);
			    	}
					break;
				case 0x403:
					iTask++;
					switch (iTask = iTask%3) {
					case 0:
						m_imgCarRun.setImageResource(R.drawable.img_car_normal1);
						break;
					case 1:
						m_imgCarRun.setImageResource(R.drawable.img_car_normal2);
						break;
					case 2:
						m_imgCarRun.setImageResource(R.drawable.img_car_normal3);
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}
			}
			
		};
		
		
		/**
		* 设置四个轮胎轮速柱状显示
		* @param iWheel：哪个轮胎，iLevel：和平均值相差值
		* @return 
		* Copyright: 
	    * Author: Hardy.lai
	    * Date: 03/05/2016
		*/
		private void setWheelLevel(int iWheel, float f) {
			
			int index = 0;
			float absLevel = Math.abs(f);
			if (absLevel==0) {
				index=0;
			}
			else if (absLevel > 0 && absLevel < 0.5) {
				index=1;
			}
			else if (absLevel >= 0.5 && absLevel < ConstData.WHEEL_SCHEDULE_LEVEL1) {
				index=2;
			}
			else if (absLevel >= ConstData.WHEEL_SCHEDULE_LEVEL1 && absLevel < 1.5) {
				index=3;
			}
			else if (absLevel >= 1.5 && absLevel < ConstData.WHEEL_SCHEDULE_LEVEL2) {
				index=4;
			}
			else if (absLevel >= ConstData.WHEEL_SCHEDULE_LEVEL2 && absLevel < ConstData.WHEEL_SCHEDULE_LEVEL3) {
				index=5;
			}
			else if (absLevel >= ConstData.WHEEL_SCHEDULE_LEVEL3) {
				index=6;
			}
			//轮胎柱状图  左前、右前、左后、右后 分别 1,2,3,4
			switch (iWheel) {
			case 1://左前
				if (f>=0) {
					Drawable drawable = null;
					if (CarInfo.getInstance().getState() == 1) {//学习中
						drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17+index);
					} else {
						drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17+index);
					}
					if (drawable != null) {
						/// 这一步必须要做,否则不会显示.
						drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
						tx_lfwheel_schedule.setCompoundDrawables(null,drawable,null,null);
					}
					
				} else {
					Drawable drawable = null;
					if (CarInfo.getInstance().getState() == 1) {
						drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17-index);
					} else {
						drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17-index);
					}
					if (drawable != null) {
						/// 这一步必须要做,否则不会显示.
						drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
						tx_lfwheel_schedule.setCompoundDrawables(null,drawable,null,null);
					}
					
				}
				break;
			case 2://右前
				if (f>=0) {
					Drawable drawable = null;
					if (CarInfo.getInstance().getState() == 1) {//学习中
						drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17+index);
					} else {
						drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17+index);
					}
					if (drawable != null) {
						/// 这一步必须要做,否则不会显示.
						drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
						tx_rfwheel_schedule.setCompoundDrawables(null,drawable,null,null);
					}
				} else {
					Drawable drawable = null;
					if (CarInfo.getInstance().getState() == 1) {
						drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17-index);
					} else {
						drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17-index);
					}
					if (drawable != null) {
						/// 这一步必须要做,否则不会显示.
						drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
						tx_rfwheel_schedule.setCompoundDrawables(null,drawable,null,null);
					}
				}
				break;
			case 3://左后
				if (f>=0) {
					Drawable drawable = null;
					if (CarInfo.getInstance().getState() == 1) {//学习中
						drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17+index);
					} else {
						drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17+index);
					}
					if (drawable != null) {
						/// 这一步必须要做,否则不会显示.
						drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
						tx_lrwheel_schedule.setCompoundDrawables(null,drawable,null,null);
					}
				} else {
					Drawable drawable = null;
					if (CarInfo.getInstance().getState() == 1) {
						drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17-index);
					} else {
						drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17-index);
					}
					if (drawable != null) {
						/// 这一步必须要做,否则不会显示.
						drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
						tx_lrwheel_schedule.setCompoundDrawables(null,drawable,null,null);
					}
				}
				break;
			case 4://右后
				if (f>=0) {
					Drawable drawable = null;
					if (CarInfo.getInstance().getState() == 1) {//学习中
						drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17+index);
					} else {
						drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17+index);
					}
					if (drawable != null) {
						/// 这一步必须要做,否则不会显示.
						drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
						tx_rrwheel_schedule.setCompoundDrawables(null,drawable,null,null);
					}
				} else {
					Drawable drawable = null;
					if (CarInfo.getInstance().getState() == 1) {
						drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17-index);
					} else {
						drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17-index);
					}
					if (drawable != null) {
						/// 这一步必须要做,否则不会显示.
						drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
						tx_rrwheel_schedule.setCompoundDrawables(null,drawable,null,null);
					}
				}
				break;
			default:
				break;
			}
		}
		

		/**
		*设置轮胎动画
		* @param iAvgSp：平均轮速
		* @return 
		* Copyright: 
	    * Author: Hardy.lai
	    * Date: 03/05/2016
		*/
		protected void setWheelAnima(float iAvgSp) {
			
//			Log.d("Wheel", "iAvgSp ="+iAvgSp+",iAvgWheelSp="+iAvgWheelSp);
			if (iAvgSp ==0) {
				iGetZeroCount++;
			} else {
				iGetZeroCount =0;
			}
			//轮速为0时，不显示柱状图
			setWheelShow(iAvgSp>0);
			
			if (CarInfo.getInstance().getiAvgWheelSp() != iAvgSp) {
				CarInfo.getInstance().setiAvgWheelSp(iAvgSp);
				
				//设置轮胎动画
				if (CarInfo.getInstance().getiAvgWheelSp() <= 0) {//转速为0
					if (bRunTask) {
						bRunTask = false;
					    mHandler.removeCallbacks(runTask);
			    	}
				}
				else if (CarInfo.getInstance().getiAvgWheelSp() > 0 && CarInfo.getInstance().getiAvgWheelSp() <= ConstData.WHEEL_SPEED_LEVEL1) {//0-10
					iTimerMill=300;//400mS
					if (!bRunTask) {
						mHandler.postDelayed(runTask, iTimerMill);
					}
				}
				else if (CarInfo.getInstance().getiAvgWheelSp() > ConstData.WHEEL_SPEED_LEVEL4) {
					iTimerMill=10;//10mS
					if (!bRunTask) {
						mHandler.postDelayed(runTask, iTimerMill);
					}
				}
				else{
					iTimerMill=(int) (CarInfo.getInstance().getiAvgWheelSp()*(-3)+250);
					iTimerMill = Math.abs(iTimerMill);
					if (!bRunTask) {
						mHandler.postDelayed(runTask, iTimerMill);
					}
				}
			}
			//左前、右前、左后、右后 分别 1,2,3,4
			//左前
			//Log.d("Wheel", "iLFWheelSp-iAvgWheelSp ="+(iLFWheelSp-iAvgWheelSp)+",iAvgWheelSp="+iAvgWheelSp);
			setWheelLevel(1,CarInfo.getInstance().getiLFWheelSp()-CarInfo.getInstance().getiAvgWheelSp());
			//右前
			//Log.d("Wheel", "iRFWheelSp-iAvgWheelSp ="+(iRFWheelSp-iAvgWheelSp)+",iAvgWheelSp="+iAvgWheelSp);
			setWheelLevel(2,CarInfo.getInstance().getiRFWheelSp()-CarInfo.getInstance().getiAvgWheelSp());
			//左后
			//Log.d("Wheel", "iLRWheelSp-iAvgWheelSp ="+(iLRWheelSp-iAvgWheelSp)+",iAvgWheelSp="+iAvgWheelSp);
			setWheelLevel(3,CarInfo.getInstance().getiLRWheelSp()-CarInfo.getInstance().getiAvgWheelSp());
			//右后
			//Log.d("Wheel", "iRRWheelSp-iAvgWheelSp ="+(iRRWheelSp-iAvgWheelSp)+",iAvgWheelSp="+iAvgWheelSp);
			setWheelLevel(4,CarInfo.getInstance().getiRRWheelSp()-CarInfo.getInstance().getiAvgWheelSp());
		}
		
		
		/**
		* 是否显示四个柱状图
		* @param 
		* @return 
		* Copyright: 
	    * Author: Hardy.lai
	    * Date: 03/05/2016
		*/
		private void setWheelShow(boolean bShow) {
			
			if (bShow) {
				tx_lfwheel_schedule.setVisibility(View.VISIBLE);
				tx_rfwheel_schedule.setVisibility(View.VISIBLE);
				tx_lrwheel_schedule.setVisibility(View.VISIBLE);
				tx_rrwheel_schedule.setVisibility(View.VISIBLE);
			} else if (!bShow && iGetZeroCount >= 10){
				tx_lfwheel_schedule.setVisibility(View.GONE);
				tx_rfwheel_schedule.setVisibility(View.GONE);
				tx_lrwheel_schedule.setVisibility(View.GONE);
				tx_rrwheel_schedule.setVisibility(View.GONE);
			}
		}
		
		
		private Runnable runTask = new Runnable() {
			
			@Override
			public void run() {
				
				bRunTask = true;
				mHandler.sendEmptyMessage(0x403);
				mHandler.postDelayed(this, iTimerMill);
			}
		};
		
		/**
		* 轮胎报警更新
		* Copyright: Hiworld
		* Author: Hardy.lai
		* Date: 10/22/2015
		 * @param m_stInfo 
		*/
		protected void setTPMSAlarm(CarInfo m_stInfo) {
				
			//tx正常
			if (m_stInfo.getAccState() == 0x01) {
				if (m_stInfo.getbLinkTx() != 1) {
					if (m_stInfo.getbCanUnlink() != 1) {
						//两路CAN中有一路正常,其中can是0是异常，有问题
						if (m_stInfo.getTpmsLink1() != 0 || m_stInfo.getTpmsLink2() != 0) {
							//车型支持,1为支持
							if (m_stInfo.getAgreeTpms() != 0  ){
								m_textWarnLink.setText("");
								m_TxConnect.setText(R.string.WarnSwitch_start);

							}
							
						}
					}
					
				}
			}
			else if(m_stInfo.getAccState() == 0x00){
				mHandler.removeCallbacks(runnable6);
				m_textWarnLink.setText("");
				m_TxConnect.setText(R.string.WarnSwitch_start);

			}
			

			//点击保存胎压，但收到扔是空闲状态，重发保存命令
			if (CailbrationActivity.bReat && m_stInfo.getState() == 0) {
				CailbrationActivity.bReat = false;
				mHandler.removeCallbacks(runnable4);
				mHandler.postDelayed(runnable4, 500);//500ms
			}else if (m_stInfo.getState() == 1) {
				CailbrationActivity.bReat = false;
				mHandler.removeCallbacks(runnable4);
			}
			//获取灵敏度值
			int iSenis = 0;
			SharedPreferences preferences = activity.getSharedPreferences(
	        		ConstData.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
			switch (preferences.getInt(ConstData.INSENITIVITY, 1)) {
			case 0:
				iSenis = 1;
				break;
	        case 1:
	        	iSenis = 3;
				break;
	        case 2:
	        	iSenis = 5;
				break;
			default:
				break;
			}
			//设置灵敏度
			if (SensiActivity.bSennisi && m_stInfo.getSenis() != iSenis) {
				SensiActivity.bSennisi = false;
				mHandler.removeCallbacks(runnable5);
				mHandler.postDelayed(runnable5, 500);//500ms
			}else if (m_stInfo.getSenis() == iSenis) {
				SensiActivity.bSennisi = false;
				mHandler.removeCallbacks(runnable5);
			}
			
			
			//报警级别
			if (m_stInfo.getWarnLevel() == 0x01)	{
				m_imgLevel.setImageResource(R.drawable.img_level_red);
				m_textWarninfo.setTextColor(Color.rgb(255, 0, 0));
				m_textWarnLink.setTextColor(Color.rgb(255, 0, 0));
				
				//胎压报警
				if (m_stInfo.getRightRear() == 0x01)//右后轮胎报警
				{
					m_imgCarWarn.setImageResource(R.drawable.ico_red_rr);
					m_textWarninfo.setText(R.string.right_rear_tpms);
					m_imgCarWarn.setVisibility(View.VISIBLE);
					layout.setVisibility(View.VISIBLE);
					m_TxConnect.setVisibility(View.GONE);
				}
				else if (m_stInfo.getLeftRear() == 0x01)//左后轮胎
				{
					m_imgCarWarn.setImageResource(R.drawable.ico_red_lr);
					m_textWarninfo.setText(R.string.left_rear_tpms);
					m_imgCarWarn.setVisibility(View.VISIBLE);
					layout.setVisibility(View.VISIBLE);
					m_TxConnect.setVisibility(View.GONE);
				}
				else if (m_stInfo.getRightFront() == 0x01)//右前轮胎
				{
					m_imgCarWarn.setImageResource(R.drawable.ico_red_rf);
					m_textWarninfo.setText(R.string.right_front_tpms);
					m_imgCarWarn.setVisibility(View.VISIBLE);
					layout.setVisibility(View.VISIBLE);
					m_TxConnect.setVisibility(View.GONE);
				}
				else if (m_stInfo.getLeftFront() == 0x01)//左前轮胎
				{
					m_imgCarWarn.setImageResource(R.drawable.ico_red_lf);
					m_textWarninfo.setText(R.string.left_front_tpms);
					m_imgCarWarn.setVisibility(View.VISIBLE);
					layout.setVisibility(View.VISIBLE);
					m_TxConnect.setVisibility(View.GONE);
				}
				else 
				{
					m_imgCarWarn.setVisibility(View.GONE);
					layout.setVisibility(View.GONE);
					m_TxConnect.setVisibility(View.VISIBLE);

				}
			} else{
				m_imgLevel.setImageResource(R.drawable.img_level_yellow);
				m_textWarninfo.setTextColor(Color.rgb(255, 255, 0));
				m_textWarnLink.setTextColor(Color.rgb(255, 255, 0));
				
				//胎压报警
				if (m_stInfo.getRightRear() == 0x01)//右后轮胎报警
				{
					m_imgCarWarn.setImageResource(R.drawable.ico_yellow_rr);
					m_textWarninfo.setText(R.string.right_rear_tpms);
					m_imgCarWarn.setVisibility(View.VISIBLE);
					layout.setVisibility(View.VISIBLE);
					m_TxConnect.setVisibility(View.GONE);
				}
				else if (m_stInfo.getLeftRear() == 0x01)//左后轮胎
				{
					m_imgCarWarn.setImageResource(R.drawable.ico_yellow_lr);
					m_textWarninfo.setText(R.string.left_rear_tpms);
					m_imgCarWarn.setVisibility(View.VISIBLE);
					layout.setVisibility(View.VISIBLE);
					m_TxConnect.setVisibility(View.GONE);
				}
				else if (m_stInfo.getRightFront() == 0x01)//右前轮胎
				{
					m_imgCarWarn.setImageResource(R.drawable.ico_yellow_rf);
					m_textWarninfo.setText(R.string.right_front_tpms);
					m_imgCarWarn.setVisibility(View.VISIBLE);
					layout.setVisibility(View.VISIBLE);
					m_TxConnect.setVisibility(View.GONE);
				}
				else if (m_stInfo.getLeftFront() == 0x01)//左前轮胎
				{
					m_imgCarWarn.setImageResource(R.drawable.ico_yellow_lf);
					m_textWarninfo.setText(R.string.left_front_tpms);
					m_imgCarWarn.setVisibility(View.VISIBLE);
					layout.setVisibility(View.VISIBLE);
					m_TxConnect.setVisibility(View.GONE);
				}
				else 
				{
					m_imgCarWarn.setVisibility(View.GONE);
					layout.setVisibility(View.GONE);
					m_TxConnect.setVisibility(View.VISIBLE);

				}
			}

		}
		
		
		/**
		*通讯出错提示功能
		* @param 
		* @return 
		* Copyright: 
	    * Author: Hardy.lai
	    * Date: 03/05/2016
		*/
		protected void setTPMSLink(CarInfo m_stCarInfo) {
			
			
			if (m_stCarInfo != null) {
				//Log.d(TAG, "bCanUnlink ="+bCanUnlink);
				//TX不正常bit1 //优先级最高1
				if (m_stCarInfo.getbLinkTx() == 1) {
					m_textWarnLink.setText(R.string.WarnSwitch_unconnect);
					m_TxConnect.setText(R.string.WarnSwitch_unconnect);
				}
				else if (m_stCarInfo.getAgreeTpms() == 0){
					m_textWarnLink.setText(R.string.WarnSwitch_unpose);
					m_TxConnect.setText(R.string.WarnSwitch_unpose);
				}
				else if (m_stCarInfo.getbCanUnlink() == 1){
					mHandler.removeCallbacks(runnable6);
					m_textWarnLink.setText(R.string.WarnSwitch_unconnect);
					m_TxConnect.setText(R.string.WarnSwitch_unconnect);
				}
				else if (m_stCarInfo.getTpmsLink1() == 0x00 && m_stCarInfo.getTpmsLink2() == 0x00) {
					mHandler.removeCallbacks(runnable6);
					mHandler.postDelayed(runnable6, 5000);
				}
				else{
					mHandler.removeCallbacks(runnable6);
					m_textWarnLink.setText("");
					m_TxConnect.setText(R.string.WarnSwitch_start);
				}
				
			}
		}
		
		private void writeSennis() {
			
			SharedPreferences preferences = activity.getSharedPreferences(
	        		ConstData.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
			
		    int[] sendCmd = new int[4];
			sendCmd[0] = 0x02;
			sendCmd[1] = 0x4d;
			sendCmd[2] = 0x02;
		    switch (preferences.getInt(ConstData.INSENITIVITY, 1)){
			case 0:
				sendCmd[3] = 0x01;
				break;
	        case 1:
	        	sendCmd[3] = 0x03;
				break;
	        case 2:
	        	sendCmd[3] = 0x05;
				break;
			default:
				break;
			}
		    writeBuf(sendCmd, 4);
		}
		
		private void writeBuf(int[] ints, int size) {
			
			try {
				ConnConnect.getInstance().getRemoteProxy().sendCmd(ConstUtil.APP2SERVER_OTHER, ints);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private Runnable runnable4 = new Runnable(){

			@Override
			public void run()
			{
				
				writeBuf(new int[] {0x02,0x4d,0x01,0x00}, 4);
				mHandler.postDelayed(runnable4, 500);
			}
			
		};

		private Runnable runnable5 = new Runnable(){

			@Override
			public void run()
			{
				
				writeSennis();
				mHandler.postDelayed(runnable5, 500);
			}
			
		};
		
		private Runnable runnable6 = new Runnable(){

			@Override
			public void run(){
				
				m_textWarnLink.setText(R.string.WarnSwitch_unconnect);
				m_TxConnect.setText(R.string.WarnSwitch_unconnect);
			}
			
		};

		
	@Override
	public void onDestroy() {
		
		super.onDestroy();
	}

}
