package com.hiworld.canbus.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hiworld.canbus.parse.FragmentParseData;
import com.hiworld.canbus.util.CarInfo;
import com.hiworld.canbus.util.CarPcInfo;
import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.util.PopSpliner;
import com.hiworld.canbus.util.RemoteProxymanger;
import com.hiworld.canbus.util.TimeUtils;
import com.hiworld.mycar.t11.R;
import com.youzi.customer.connect.ConnConnect;

import com.youzi.customer.util.ConstUtil;

//胎压fragment
public class TpmsFragment extends Fragment implements OnClickListener {

	private TextView /* mTextTitle, */ m_textWarninfo, m_TxConnect, mTextWarnTip, tx_lfwheel_schedule,
			tx_rfwheel_schedule, tx_lrwheel_schedule, tx_rrwheel_schedule;
	private ImageButton /* mBack, */ mIbCheckout, mIbDevicesett;
	private ImageView m_imgCarWarn, m_imgCarRun, m_imgLevel, mTpmsSave, mtpmeKuang;
	// 轮速,左前、右前、左后、右后、平均轮速
	private float iLFWheelSp, iRFWheelSp, iLRWheelSp, iRRWheelSp, iAvgWheelSp;
	private boolean bRunTask;
	private int iTimerMill, iTask;// 转速的定时器时长
	private AlertDialog checkDialog, deviceSettDialog;
	private PopSpliner popMenu;
	private String[] mArrayString;
	private Activity activity;
	private RelativeLayout rl_tpms;
	private boolean isPort = false;

	// private boolean isTpms = false;
	int mSavaCount;

	// APP肤色图片资源集合
	private int[] skinColourList;
	private boolean ishidden = true;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			super.handleMessage(msg);
			if (msg.what == 0) {
				iTask++;
				switch (iTask = iTask % 3) {
				case 0:
					m_imgCarRun.setImageResource(skinColourList[37]);// img_car_normal1
					break;
				case 1:
					m_imgCarRun.setImageResource(skinColourList[38]);// img_car_normal2
					break;
				case 2:
					m_imgCarRun.setImageResource(skinColourList[39]);// img_car_normal3
					break;
				default:
					break;
				}
			}
		}
	};

	@SuppressLint("HandlerLeak")
	private Handler tpmsHand = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			super.handleMessage(msg);
			switch (msg.what) {
			// case ConstData.MSG_GET_BLUESTATE:
			// case ConstData.MSG_GET_CARPCCHANGE:
			// initSetTpms(CarInfo.getInstance(),CarPcInfo.getInstance());
			// break;
			case ConstData.HANDLER_MESSAGE_CARPC:
				CarPcInfo hudInfo = (CarPcInfo) msg.obj;
				initSetcarpcinfo(hudInfo);
				break;
			case ConstData.MESSAGE_TIRE_FAILURE:// 轮胎报警
				CarInfo carInfo = (CarInfo) msg.obj;
				initSetTpms(carInfo);
				break;
			case ConstData.MESSAGE_TPMS_SERIAL_HIGHSPEED:// 连接状态
				CarInfo carInfo1 = (CarInfo) msg.obj;
				initSetTpms(carInfo1);
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
		FragmentParseData.getInstance().setmHandlerTpmsFragment(tpmsHand);
	}

	@Override
	public void onResume() {

		super.onResume();
		// 获取皮肤图片资源 add by jiaqing.liu
		skinColourList = TimeUtils.getInstance().getAllSkin(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		Configuration mConfiguration = activity.getResources().getConfiguration(); // 获取设置的配
		int ori = mConfiguration.orientation; // 获取屏幕方向
		if (ori == Configuration.ORIENTATION_LANDSCAPE) {// 横屏
			isPort = false;
		} else {
			isPort = true;
		}
		View view = inflater.inflate(R.layout.activity_tpms, container, false);
		initView(view);
		// 获取皮肤图片资源 add by jiaqing.liu
		skinColourList = TimeUtils.getInstance().getAllSkin(activity);
		// initSetTpms(CarInfo.getInstance(),CarPcInfo.getInstance());
		// VissCmdParse.getInstance().setTpmsHandler(tpmsHand);
		// registerInterfilter();
		return view;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {

		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			ishidden = false;
		} else {
			ishidden = true;
		}
	}

	private void initView(View view) {

		mIbCheckout = (ImageButton) view.findViewById(R.id.ib_tpms_check);
		mIbDevicesett = (ImageButton) view.findViewById(R.id.ib_tpms_sett);
		mIbCheckout.setOnClickListener(this);
		mIbDevicesett.setOnClickListener(this);

		m_imgCarWarn = (ImageView) view.findViewById(R.id.img_car_warn);
		m_imgCarRun = (ImageView) view.findViewById(R.id.img_carinfo);
		m_imgLevel = (ImageView) view.findViewById(R.id.iv_tpms_warn);
		mtpmeKuang = (ImageView) view.findViewById(R.id.iv_tpme_kuang);
		m_textWarninfo = (TextView) view.findViewById(R.id.text_warninfo);
		m_TxConnect = (TextView) view.findViewById(R.id.text_connect);
		mTextWarnTip = (TextView) view.findViewById(R.id.text_warntip);
		tx_lfwheel_schedule = (TextView) view.findViewById(R.id.text_lf_wheel);
		tx_rfwheel_schedule = (TextView) view.findViewById(R.id.text_rf_wheel);
		tx_lrwheel_schedule = (TextView) view.findViewById(R.id.text_lr_wheel);
		tx_rrwheel_schedule = (TextView) view.findViewById(R.id.text_rr_wheel);

		rl_tpms = (RelativeLayout) view.findViewById(R.id.rl_tpms_background);

		mTpmsSave = (ImageView) view.findViewById(R.id.iv_tpms_sava);

		mArrayString = activity.getResources().getStringArray(R.array.sensitivity);
	}

	private void initSetTpms(final CarInfo instance) {

		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {

				setCarInfo(instance);
			}
		});
	}

	private void initSetcarpcinfo(final CarPcInfo carPcInfo) {

		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {

				setcarpcinfo(carPcInfo);
			}
		});
	}

	protected void setcarpcinfo(CarPcInfo carPcInfo) {
		if (carPcInfo.getM_iILLAble() == 1) {// bound && connect &&
			if (carPcInfo.getM_iILL() == 1) {
				rl_tpms.setBackgroundResource(skinColourList[20]);// main_backgrond_ill
				if (isPort) {
					mIbCheckout.setImageResource(skinColourList[40]);// tpms_check_selector_port_ill
					mIbDevicesett.setImageResource(skinColourList[41]);// tpms_sett_selector_port_ill
					mtpmeKuang.setImageResource(skinColourList[58]);// tpms_warnkuang
				} else {
					mIbCheckout.setImageResource(skinColourList[42]);// tpms_check_ill_selector
					mIbDevicesett.setImageResource(skinColourList[43]);// tpms_sett_ill_selector
					mtpmeKuang.setImageResource(skinColourList[58]);// tpms_warnkuang
				}
			} else {
				rl_tpms.setBackgroundResource(skinColourList[22]);// main_backgrond
				if (isPort) {
					mIbCheckout.setImageResource(skinColourList[44]);// tpms_check_selector_port
					mIbDevicesett.setImageResource(skinColourList[45]);// tpms_sett_selector_port
					mtpmeKuang.setImageResource(skinColourList[59]);// tpms_warnkuang
				} else {
					mIbCheckout.setImageResource(skinColourList[46]);// tpms_check_selector
					mIbDevicesett.setImageResource(skinColourList[47]);// tpms_sett_selector
					mtpmeKuang.setImageResource(skinColourList[59]);// tpms_warnkuang
				}
			}
		}
	}

	private Runnable runnable4 = new Runnable() {

		@Override
		public void run() {

			writeBuf(new int[] { 0x02, 0x4d, 0x01, 0x00 }, 4);
			mHandler.postDelayed(runnable4, 500);
		}

	};

	private Runnable runnable5 = new Runnable() {

		@Override
		public void run() {

			writeSennis();
			mHandler.postDelayed(runnable5, 500);
		}

	};

	private void writeSennis() {

		SharedPreferences preferences = activity.getSharedPreferences(ConstData.SHAREDPREFERENCES_NAME,
				Context.MODE_PRIVATE);

		int[] sendCmd = new int[4];
		sendCmd[0] = 0x02;
		sendCmd[1] = 0x4d;
		sendCmd[2] = 0x02;
		switch (preferences.getInt(ConstData.INSENITIVITY, 1)) {
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

	protected void setCarInfo(CarInfo instance) {

		// boolean bound = VissCmdParse.getInstance().bBound;
		// boolean connect = VissCmdParse.getInstance().bConnect;

		///// ILL背光
		// if (carPcInfo.getM_iILLAble() == 1) {//bound && connect &&
		// if (carPcInfo.getM_iILL() == 1) {
		// rl_tpms.setBackgroundResource(skinColourList[20]);//main_backgrond_ill
		// if (isPort) {
		// mIbCheckout.setImageResource(skinColourList[40]);//tpms_check_selector_port_ill
		// mIbDevicesett.setImageResource(skinColourList[41]);//tpms_sett_selector_port_ill
		// mtpmeKuang.setImageResource(skinColourList[58]);//tpms_warnkuang
		// }else {
		// mIbCheckout.setImageResource(skinColourList[42]);//tpms_check_ill_selector
		// mIbDevicesett.setImageResource(skinColourList[43]);//tpms_sett_ill_selector
		// mtpmeKuang.setImageResource(skinColourList[58]);//tpms_warnkuang
		// }
		// }else {
		// rl_tpms.setBackgroundResource(skinColourList[22]);//main_backgrond
		// if (isPort) {
		// mIbCheckout.setImageResource(skinColourList[44]);//tpms_check_selector_port
		// mIbDevicesett.setImageResource(skinColourList[45]);//tpms_sett_selector_port
		// mtpmeKuang.setImageResource(skinColourList[59]);//tpms_warnkuang
		// }else {
		// mIbCheckout.setImageResource(skinColourList[46]);//tpms_check_selector
		// mIbDevicesett.setImageResource(skinColourList[47]);//tpms_sett_selector
		// mtpmeKuang.setImageResource(skinColourList[59]);//tpms_warnkuang
		// }
		// }
		// }else {
		// rl_tpms.setBackgroundResource(skinColourList[22]);//main_backgrond
		// if (isPort) {
		// mIbCheckout.setImageResource(skinColourList[44]);//tpms_check_selector_port
		// mIbDevicesett.setImageResource(skinColourList[45]);//tpms_sett_selector_port
		// mtpmeKuang.setImageResource(skinColourList[59]);//tpms_warnkuang
		// }else {
		// mIbCheckout.setImageResource(skinColourList[46]);//tpms_check_selector
		// mIbDevicesett.setImageResource(skinColourList[47]);//tpms_sett_selector
		// mtpmeKuang.setImageResource(skinColourList[59]);//tpms_warnkuang
		// }
		// }
		// 点击保存胎压，但收到扔是空闲状态，重发保存命令
		if (bReat && instance.getState() == 0) {
			bReat = false;
			mHandler.removeCallbacks(runnable4);
			mHandler.postDelayed(runnable4, 500);// 500ms
		} else if (instance.getState() == 1) {
			bReat = false;
			mHandler.removeCallbacks(runnable4);
		}

		// 获取灵敏度值
		int iSenis = 0;
		SharedPreferences preferences = activity.getSharedPreferences(ConstData.SHAREDPREFERENCES_NAME,
				Context.MODE_PRIVATE);
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
		// 设置灵敏度
		if (bSennisi && instance.getSenis() != iSenis) {
			bSennisi = false;
			mHandler.removeCallbacks(runnable5);
			mHandler.postDelayed(runnable5, 500);// 500ms
		} else if (instance.getSenis() == iSenis) {
			bSennisi = false;
			mHandler.removeCallbacks(runnable5);
		}

		// if (bound && connect) {
		boolean bTpms = false;
		switch (instance.getWarnLevel()) {
		case 0:
			m_imgLevel.setImageResource(skinColourList[48]);// img_level_yellow
			m_textWarninfo.setTextColor(Color.rgb(255, 255, 0));
			if (instance.getLeftFront() == 1) {
				bTpms = true;
				m_imgCarWarn.setImageResource(skinColourList[49]);// ico_yellow_lf
				m_textWarninfo.setText(R.string.left_front_tpms);
			} else if (instance.getRightFront() == 1) {
				bTpms = true;
				m_imgCarWarn.setImageResource(skinColourList[50]);// ico_yellow_rf
				m_textWarninfo.setText(R.string.right_front_tpms);
			} else if (instance.getLeftRear() == 1) {
				bTpms = true;
				m_imgCarWarn.setImageResource(skinColourList[51]);// ico_yellow_lr
				m_textWarninfo.setText(R.string.left_rear_tpms);
			} else if (instance.getRightRear() == 1) {
				bTpms = true;
				m_imgCarWarn.setImageResource(skinColourList[52]);// ico_yellow_rr
				m_textWarninfo.setText(R.string.right_rear_tpms);
			}
			break;
		case 1:
			m_imgLevel.setImageResource(skinColourList[53]);// img_level_red
			m_textWarninfo.setTextColor(Color.rgb(255, 0, 0));
			if (instance.getLeftFront() == 1) {
				bTpms = true;
				m_imgCarWarn.setImageResource(skinColourList[54]);// ico_red_lf
				m_textWarninfo.setText(R.string.left_front_tpms);
			} else if (instance.getRightFront() == 1) {
				bTpms = true;
				m_imgCarWarn.setImageResource(skinColourList[55]);// ico_red_rf
				m_textWarninfo.setText(R.string.right_front_tpms);
			} else if (instance.getLeftRear() == 1) {
				bTpms = true;
				m_imgCarWarn.setImageResource(skinColourList[56]);// ico_red_lr
				m_textWarninfo.setText(R.string.left_rear_tpms);
			} else if (instance.getRightRear() == 1) {
				bTpms = true;
				m_imgCarWarn.setImageResource(skinColourList[57]);// ico_red_rr
				m_textWarninfo.setText(R.string.right_rear_tpms);
			}
			break;
		default:
			break;
		}

		if (ishidden) {
			mIbCheckout.setEnabled(false);
			mIbDevicesett.setEnabled(false);
		} else {
			mIbCheckout.setEnabled(true);
			mIbDevicesett.setEnabled(true);
		}

		if (instance.getAgreeTpms() == 0) {
			m_TxConnect.setText(R.string.WarnSwitch_unpose);
			// mIbCheckout.setEnabled(false);
			// mIbDevicesett.setEnabled(false);
		}
		// else if (instance.getbCanUnlink() == 1) {
		// m_TxConnect.setText(R.string.WarnSwitch_linkerror);
		// //mIbCheckout.setEnabled(false);
		// //mIbDevicesett.setEnabled(false);
		// }
		// else if (instance.getTpmsLink1() == 0 && instance.getTpmsLink1() == 0) {
		// m_TxConnect.setText(R.string.WarnSwitch_linkerror);
		// //mIbCheckout.setEnabled(false);
		// //mIbDevicesett.setEnabled(false);
		// }
		else {

			if (instance.getState() == 1) {// 学习中
				// mTpmsSave.setVisibility(View.INVISIBLE);
				mSavaCount = 0;
				m_TxConnect.setText(R.string.WarnSwitch_learn);
			} else {
				mSavaCount++;
				// mTpmsSave.setVisibility(View.VISIBLE);
				if (mSavaCount >= 30) {
					m_TxConnect.setText(R.string.WarnSwitch_start);
				} else {
					m_TxConnect.setText(R.string.WarnSwitch_learn_end);
				}

			}

		}

		if (instance.getState() == 1) {// 学习中
			mTpmsSave.setVisibility(View.INVISIBLE);
		} else {
			mTpmsSave.setVisibility(View.VISIBLE);
		}

		iLFWheelSp = instance.getiLFWheelSp();
		iRFWheelSp = instance.getiRFWheelSp();
		iLRWheelSp = instance.getiLRWheelSp();
		iRRWheelSp = instance.getiRRWheelSp();
		iAvgWheelSp = (iLFWheelSp + iRFWheelSp + iLRWheelSp + iRRWheelSp) / 4;
		if (!bTpms) {
			setWheelAnima(iAvgWheelSp);
		}

		if (bTpms) {
			showWarnTip(true);
		} else {
			showWarnTip(false);
		}

		// }
		// else {
		// mIbCheckout.setEnabled(false);
		// mIbDevicesett.setEnabled(false);
		// m_TxConnect.setText(R.string.WarnSwitch_disconnect);
		// showWarnTip(false);
		// if (bRunTask) {
		// bRunTask = false;
		// mHandler.removeCallbacks(runTask);
		// }
		// mTpmsSave.setVisibility(View.INVISIBLE);
		//// setWheelShow(false);
		// }
	}

	private Runnable runTask = new Runnable() {
		@Override
		public void run() {

			bRunTask = true;
			mHandler.sendEmptyMessage(0);
			System.out.println("=====tpms runTask");
			mHandler.postDelayed(this, iTimerMill);
		}
	};

	private void setWheelAnima(float iAvgSp) {

		// 轮速为0时，不显示柱状图
		// System.out.println("=====tpms setWheelAnima+iAvgSp="+iAvgSp);
		// System.out.println("=====tpms setWheelAnima+iAvgWheelSp="+iAvgWheelSp);
		// System.out.println("=====tpms setWheelAnima+bRunTask="+bRunTask);
		// setWheelShow(iAvgSp>0);
		// if (iAvgWheelSp != iAvgSp) {
		iAvgWheelSp = iAvgSp;

		// 设置轮胎动画
		if (iAvgWheelSp <= 0) {// 转速为0
			if (bRunTask) {
				bRunTask = false;
				mHandler.removeCallbacks(runTask);
			}
		} else if (iAvgWheelSp > 0 && iAvgWheelSp <= ConstData.WHEEL_SPEED_LEVEL1) {// 0-10
			iTimerMill = 300;// 400mS
			if (!bRunTask) {
				mHandler.postDelayed(runTask, iTimerMill);
			}
		} else if (iAvgWheelSp > ConstData.WHEEL_SPEED_LEVEL4) {
			iTimerMill = 10;// 10mS
			if (!bRunTask) {
				mHandler.postDelayed(runTask, iTimerMill);
			}
		} else {
			iTimerMill = (int) (iAvgWheelSp * (-3) + 250);
			iTimerMill = Math.abs(iTimerMill);
			if (!bRunTask) {
				mHandler.postDelayed(runTask, iTimerMill);
			}
		}
		// }

		// 左前
		setWheelLevel(1, iLFWheelSp - iAvgWheelSp);
		// 右前
		setWheelLevel(2, iRFWheelSp - iAvgWheelSp);
		// 左后
		setWheelLevel(3, iLRWheelSp - iAvgWheelSp);
		// 右后
		setWheelLevel(4, iRRWheelSp - iAvgWheelSp);
	}

	@SuppressWarnings("unused")
	private void setWheelShow(boolean bShow) {

		if (bShow) {
			tx_lfwheel_schedule.setVisibility(View.VISIBLE);
			tx_rfwheel_schedule.setVisibility(View.VISIBLE);
			tx_lrwheel_schedule.setVisibility(View.VISIBLE);
			tx_rrwheel_schedule.setVisibility(View.VISIBLE);
		} else if (!bShow) {

			tx_lfwheel_schedule.setVisibility(View.GONE);
			tx_rfwheel_schedule.setVisibility(View.GONE);
			tx_lrwheel_schedule.setVisibility(View.GONE);
			tx_rrwheel_schedule.setVisibility(View.GONE);
		}
	}

	private void setWheelLevel(int iWheel, float f) {

		int index = 0;
		float absLevel = Math.abs(f);
		if (absLevel == 0) {
			index = 0;
		} else if (absLevel > 0 && absLevel < 0.5) {
			index = 1;
		} else if (absLevel >= 0.5 && absLevel < ConstData.WHEEL_SCHEDULE_LEVEL1) {
			index = 2;
		} else if (absLevel >= ConstData.WHEEL_SCHEDULE_LEVEL1 && absLevel < 1.5) {
			index = 3;
		} else if (absLevel >= 1.5 && absLevel < ConstData.WHEEL_SCHEDULE_LEVEL2) {
			index = 4;
		} else if (absLevel >= ConstData.WHEEL_SCHEDULE_LEVEL2 && absLevel < ConstData.WHEEL_SCHEDULE_LEVEL3) {
			index = 5;
		} else if (absLevel >= ConstData.WHEEL_SCHEDULE_LEVEL3) {
			index = 6;
		}
		// 轮胎柱状图 左前、右前、左后、右后 分别 1,2,3,4
		switch (iWheel) {
		case 1:// 左前
			if (f >= 0) {
				Drawable drawable = null;
				if (CarInfo.getInstance().getState() == 1) {// 学习中
					drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17 + index);
				} else {
					drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17 + index);
				}
				if (drawable != null) {
					/// 这一步必须要做,否则不会显示.
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					tx_lfwheel_schedule.setCompoundDrawables(null, drawable, null, null);
				}

			} else {
				Drawable drawable = null;
				if (CarInfo.getInstance().getState() == 1) {
					drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17 - index);
				} else {
					drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17 - index);
				}
				if (drawable != null) {
					/// 这一步必须要做,否则不会显示.
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					tx_lfwheel_schedule.setCompoundDrawables(null, drawable, null, null);
				}

			}
			break;
		case 2:// 右前
			if (f >= 0) {
				Drawable drawable = null;
				if (CarInfo.getInstance().getState() == 1) {// 学习中
					drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17 + index);
				} else {
					drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17 + index);
				}
				if (drawable != null) {
					/// 这一步必须要做,否则不会显示.
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					tx_rfwheel_schedule.setCompoundDrawables(null, drawable, null, null);
				}
			} else {
				Drawable drawable = null;
				if (CarInfo.getInstance().getState() == 1) {
					drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17 - index);
				} else {
					drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17 - index);
				}
				if (drawable != null) {
					/// 这一步必须要做,否则不会显示.
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					tx_rfwheel_schedule.setCompoundDrawables(null, drawable, null, null);
				}
			}
			break;
		case 3:// 左后
			if (f >= 0) {
				Drawable drawable = null;
				if (CarInfo.getInstance().getState() == 1) {// 学习中
					drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17 + index);
				} else {
					drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17 + index);
				}
				if (drawable != null) {
					/// 这一步必须要做,否则不会显示.
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					tx_lrwheel_schedule.setCompoundDrawables(null, drawable, null, null);
				}
			} else {
				Drawable drawable = null;
				if (CarInfo.getInstance().getState() == 1) {
					drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17 - index);
				} else {
					drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17 - index);
				}
				if (drawable != null) {
					/// 这一步必须要做,否则不会显示.
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					tx_lrwheel_schedule.setCompoundDrawables(null, drawable, null, null);
				}
			}
			break;
		case 4:// 右后
			if (f >= 0) {
				Drawable drawable = null;
				if (CarInfo.getInstance().getState() == 1) {// 学习中
					drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17 + index);
				} else {
					drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17 + index);
				}
				if (drawable != null) {
					/// 这一步必须要做,否则不会显示.
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					tx_rrwheel_schedule.setCompoundDrawables(null, drawable, null, null);
				}
			} else {
				Drawable drawable = null;
				if (CarInfo.getInstance().getState() == 1) {
					drawable = activity.getResources().getDrawable(R.drawable.wheel_yellow_schedule17 - index);
				} else {
					drawable = activity.getResources().getDrawable(R.drawable.wheel_schedule17 - index);
				}
				if (drawable != null) {
					/// 这一步必须要做,否则不会显示.
					drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
					tx_rrwheel_schedule.setCompoundDrawables(null, drawable, null, null);
				}
			}
			break;
		default:
			break;
		}
	}

	private void showWarnTip(boolean bShow) {

		if (bShow) {
			m_TxConnect.setVisibility(View.INVISIBLE);
			m_textWarninfo.setVisibility(View.VISIBLE);
			m_imgLevel.setVisibility(View.VISIBLE);
			mTextWarnTip.setVisibility(View.VISIBLE);
			m_imgCarWarn.setVisibility(View.VISIBLE);
		} else {
			m_TxConnect.setVisibility(View.VISIBLE);
			m_textWarninfo.setVisibility(View.INVISIBLE);
			m_imgLevel.setVisibility(View.INVISIBLE);
			mTextWarnTip.setVisibility(View.INVISIBLE);
			m_imgCarWarn.setVisibility(View.INVISIBLE);
		}
	}

	public static boolean bReat = false;
	public static boolean bSennisi = false;

	private void writeBuf(int[] ints, int length) {

		try {
			ConnConnect.getInstance().getRemoteProxy().sendCmd(ConstUtil.APP2SERVER_OTHER, ints);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		bReat = true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ib_tpms_check:
			if (checkDialog == null) {
				checkDialog = new AlertDialog.Builder(activity).create();
				checkDialog.show();
				Window window = checkDialog.getWindow();
				WindowManager.LayoutParams lp = checkDialog.getWindow().getAttributes();
				lp.alpha = 0.8f;
				lp.gravity = Gravity.CENTER;
				checkDialog.getWindow().setAttributes(lp);
				checkDialog.setContentView(R.layout.activity_tpms_checkdialog);
				checkDialog.setCanceledOnTouchOutside(true);
				Button ibConfirm = (Button) window.findViewById(R.id.ib_dilag_confirm);
				Button ibCancel = (Button) window.findViewById(R.id.ib_dilag_cancel);
				ibConfirm.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						RemoteProxymanger.getInstance().writeBuf(
								new int[] { 0x02, 0x4d, 0x01, CarInfo.getInstance().getbCanUnlink() == 1 ? 1 : 0 });
						checkDialog.dismiss();
					}
				});
				ibCancel.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						checkDialog.dismiss();
					}
				});
			} else {
				checkDialog.show();
			}
			break;
		case R.id.ib_tpms_sett:
			if (deviceSettDialog == null) {
				deviceSettDialog = new AlertDialog.Builder(activity).create();
				deviceSettDialog.show();
				Window window = deviceSettDialog.getWindow();
				WindowManager.LayoutParams lp = deviceSettDialog.getWindow().getAttributes();
				lp.alpha = 0.8f;
				lp.gravity = Gravity.CENTER;
				deviceSettDialog.getWindow().setAttributes(lp);
				deviceSettDialog.setContentView(R.layout.activity_tpms_settdialog);
				deviceSettDialog.setCanceledOnTouchOutside(true);
				final TextView tv_senisity = (TextView) window.findViewById(R.id.sp_device_sett);
				switch (CarInfo.getInstance().getSenis()) {
				case 1:
					tv_senisity.setText(mArrayString[0]);
					break;
				case 3:
					tv_senisity.setText(mArrayString[1]);
					break;
				case 4:
					tv_senisity.setText(mArrayString[2]);
					break;
				default:
					break;
				}
				popMenu = new PopSpliner(tv_senisity.getContext());
				popMenu.addItems(mArrayString);
				popMenu.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

						tv_senisity.setText(mArrayString[position]);
						popMenu.dismiss();
					}
				});
				tv_senisity.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						popMenu.showAsDropDown(v);
					}
				});
				Button ibConfirm = (Button) window.findViewById(R.id.ib_dilag_settconfirm);
				Button ibCancel = (Button) window.findViewById(R.id.ib_dilag_settcancel);
				ibConfirm.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						// VissCmdParse.getInstance().sendConnBuffer(new int[] {0x02, 0x4D,0x01, 0x00});
						int iSenivity = 2;
						int[] sendCmd = new int[4];
						sendCmd[0] = 0x02;
						sendCmd[1] = 0x4d;
						sendCmd[2] = 0x02;
						if (tv_senisity.getText().toString().equals(mArrayString[0])) {
							// VissCmdParse.getInstance().sendIntsBuffer(new int[] {0x02, 0x4D,0x02, 0x01});
							sendCmd[3] = 0x01;
							iSenivity = 2;
						} else if (tv_senisity.getText().toString().equals(mArrayString[1])) {
							// VissCmdParse.getInstance().sendIntsBuffer(new int[] {0x02, 0x4D,0x02, 0x03});
							sendCmd[3] = 0x03;
							iSenivity = 1;
						} else if (tv_senisity.getText().toString().equals(mArrayString[2])) {
							// VissCmdParse.getInstance().sendIntsBuffer(new int[] {0x02, 0x4D,0x02, 0x05});
							sendCmd[3] = 0x05;
							iSenivity = 0;
						}
						writeBuf(sendCmd, sendCmd.length);
						bSennisi = true;
						getActivity();
						SharedPreferences preferences = getActivity()
								.getSharedPreferences(ConstData.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
						Editor editor = preferences.edit();
						editor.putInt(ConstData.INSENITIVITY, iSenivity);
						editor.commit();
						deviceSettDialog.dismiss();
					}
				});
				ibCancel.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						deviceSettDialog.dismiss();
					}
				});
			} else {
				deviceSettDialog.show();
			}
			break;
		default:
			break;
		}
	}

	/*
	 * private BroadcastReceiver mReceiver = new BroadcastReceiver(){
	 * 
	 * @Override public void onReceive(Context context, Intent intent) {
	 * 
	 * String action = intent.getAction(); if (ConstData.ACTION_ILL.equals(action))
	 * { int ill = intent.getIntExtra("ill", 0); if (ill == 0) {
	 * rl_tpms.setBackgroundResource(R.drawable.main_backgrond); if (isPort) {
	 * mIbCheckout.setImageResource(R.drawable.tpms_check_selector_port);
	 * mIbDevicesett.setImageResource(R.drawable.tpms_sett_selector_port); }else {
	 * mIbCheckout.setImageResource(R.drawable.tpms_check_selector);
	 * mIbDevicesett.setImageResource(R.drawable.tpms_sett_selector);
	 * 
	 * } }else { rl_tpms.setBackgroundResource(R.drawable.main_backgrond_ill); if
	 * (isPort) {
	 * mIbCheckout.setImageResource(R.drawable.tpms_check_selector_port_ill);
	 * mIbDevicesett.setImageResource(R.drawable.tpms_sett_selector_port_ill); }else
	 * { mIbCheckout.setImageResource(R.drawable.tpms_check_ill_selector);
	 * mIbDevicesett.setImageResource(R.drawable.tpms_sett_ill_selector);
	 * 
	 * } } } } };
	 */
	//
	// private void registerInterfilter() {
	//
	// IntentFilter infiter = new IntentFilter();
	// infiter.addAction(ConstData.ACTION_ILL);
	// activity.registerReceiver(mReceiver, infiter);
	// }

	@Override
	public void onDestroyView() {

		super.onDestroyView();
		// activity.unregisterReceiver(mReceiver);
	}
}
