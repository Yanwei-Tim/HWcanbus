package com.hiworld.canbus.carpc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import tools.LogsUtils;
import android.widget.RadioGroup.OnCheckedChangeListener;
import java.util.Calendar;

import com.ex.canbus.DataCanbus;
import com.ex.canbus.FinalCanbus;
import com.ex.hiworld.aidl.task.Task_HandlerCanbus;
import com.hiworld.canbus.parsedata.PsaToyotaParseData;
import com.hiworld.carcomputer.R;
import com.hiworld.constant.Constant;
import com.hiworld.constant.ConstantCar;
import com.hiworld.fragment.gm.GmFuleMileageFragment;
import com.hiworld.fragment.golf.Golf7FromAddOilFragment;
import com.hiworld.fragment.golf.Golf7FromStartFragment;
import com.hiworld.fragment.golf.Golf7LongTimeFragment;
import com.hiworld.fragment.golf.VMRadarFragment;
import com.hiworld.fragment.golf.VMUIFragment;
import com.hiworld.fragment.honda.HondaCurrentFragment;
import com.hiworld.fragment.honda.HondaHistoryFragment;
import com.hiworld.fragment.psa.PsaTrip0Fragment;
import com.hiworld.fragment.psa.PsaTrip1Fragment;
import com.hiworld.fragment.psa.PsaTrip2Fragment;
import com.hiworld.fragment.saic.SaicFuelFragment;
import com.hiworld.fragment.toyota.ToyotaHistroyFragment;
import com.hiworld.fragment.toyota.ToyotaPerMinuteFragment;

public class ActivityCarPC extends Activity implements FragmentCallBack {
	private static final String TAG = ActivityCarPC.class.getSimpleName();

	public static int CARTYPE_MODE = ConstantCar.CARTYPE_VWF0;// 品牌
	public static int CAR_NUM = ConstantCar.VWFO_GOLF7;// 车型

	// private RemoteProxy REMOTE_PROXY = null;

	private Fragment[] mFragments;
	private FragmentManager fragmentManager;
	// private FragmentTransaction fragmentTransaction;
	// PSA
	public static int indexfragment = 0;
	private RadioGroup m_RadioGroup;
	private RadioButton m_BtnInstant;
	private RadioButton m_BtnTrip1;
	private RadioButton m_BtnTrip2;

	private RadioGroup m_RadioMainGroup;

	private RadioGroup m_HondaGroup;

	private int containerViewId;// 记录该布局的ID;
	private RelativeLayout framelayout;

	public static boolean isActive = false;
	private TextView tv_carbody_golf7, tv_title1_golf7, tv_title2_golf7, tv_title3_golf7, tv_time_golf7;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// // 获取车型
		// getCarTypeAndNum();

		fragmentManager = getFragmentManager();

		DataCanbus.sCanbusId = ConstantCar.CARTYPE_VWF0;
		DataCanbus.sCanbusIdOffset = ConstantCar.VWFO_GOLF7;

		switch (DataCanbus.sCanbusId) {
		case ConstantCar.CARTYPE_PSA: {
			setContentView(R.layout.psa_main);

			containerViewId = R.id.fragment_content_psa;
			framelayout = (RelativeLayout) findViewById(R.id.fragment_content_psa);

			initFragments(3);

			mFragments[0] = new PsaTrip0Fragment();
			mFragments[1] = new PsaTrip1Fragment();
			mFragments[2] = new PsaTrip2Fragment();

			indexfragment = 0;
			PsaToyotaParseData.getInstance().setContext(this);
			PsaToyotaParseData.getInstance().setMainHandler(mHandler);
			initPsaCtrl();
			break;
		}
		case ConstantCar.CARTYPE_VWF0: { // 全兼容
			switch (DataCanbus.sCanbusIdOffset) {
			case ConstantCar.VWFO_GOLF7: // 高尔夫7

				setContentView(R.layout.golf7_main);
				containerViewId = R.id.fragment_content_golf7;
				framelayout = (RelativeLayout) findViewById(R.id.fragment_content_golf7);

				initGolft7();

				break;
			case ConstantCar.VWFO_MAGOTAN:
				setContentView(R.layout.vm_main);
				containerViewId = R.id.fragment_content_vw;
				framelayout = (RelativeLayout) findViewById(R.id.fragment_content_vw);
				initFragments(2);
				mFragments[0] = new VMUIFragment();
				mFragments[1] = new VMRadarFragment();

				initVmCtrl();
				break;
			default:
				break;
			}
			break;
		}
		case ConstantCar.CARTYPE_TOYOTA: {
			setContentView(R.layout.toyota_main);

			containerViewId = R.id.fragment_content_toyota;
			framelayout = (RelativeLayout) findViewById(R.id.fragment_content_toyota);

			initFragments(2);
			mFragments[0] = new ToyotaPerMinuteFragment();
			mFragments[1] = new ToyotaHistroyFragment();

			PsaToyotaParseData.getInstance().setContext(this);
			break;
		}
		case ConstantCar.CARTYPE_SAIC: {// 上汽
			setContentView(R.layout.saic_main);

			containerViewId = R.id.fragment_content_toyota;
			framelayout = (RelativeLayout) findViewById(R.id.fragment_content_toyota);

			initFragments(2);
			mFragments[0] = new VMUIFragment();
			mFragments[1] = new SaicFuelFragment();

			initMain();

			initRadio();

			break;
		}
		case ConstantCar.CARTYPE_GM: {// gm
			setContentView(R.layout.gm_main);

			containerViewId = R.id.fragment_content_gm;
			framelayout = (RelativeLayout) findViewById(R.id.fragment_content_gm);

			initFragments(2);
			mFragments[0] = new VMUIFragment();
			mFragments[1] = new GmFuleMileageFragment();

			initMain();
			break;
		}
		case ConstantCar.CARTYPE_HONDA: {
			setContentView(R.layout.honda_main);

			containerViewId = R.id.fragment_content_honda;
			framelayout = (RelativeLayout) findViewById(R.id.fragment_content_honda);
			initFragments(2);
			mFragments[0] = new HondaCurrentFragment(ActivityCarPC.this);
			mFragments[1] = new HondaHistoryFragment();
			initHonda();
			break;
		}
		default:
			setContentView(R.layout.other_main);

			break;
		}

		if (mFragments != null && mFragments.length > 0) {
			ReplaceFragmentMethod(0);
		}

		// initRemote();
	}

	private void initGolft7() {
		initFragments(4);
		mFragments[0] = new VMUIFragment();
		mFragments[1] = new Golf7FromStartFragment();
		mFragments[2] = new Golf7LongTimeFragment();
		mFragments[3] = new Golf7FromAddOilFragment();

		tv_carbody_golf7 = (TextView) findViewById(R.id.tv_carbody_golf7);
		tv_title1_golf7 = (TextView) findViewById(R.id.tv_title1_golf7);
		tv_title2_golf7 = (TextView) findViewById(R.id.tv_title2_golf7);
		tv_title3_golf7 = (TextView) findViewById(R.id.tv_title3_golf7);

		setTextEnable(0);

		tv_time_golf7 = (TextView) findViewById(R.id.tv_time_golf7);

		Thread mThread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					final Calendar c = Calendar.getInstance();
					ActivityCarPC.this.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							tv_time_golf7.setText(
									String.format("%02d:%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)));
						}
					});

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
			}
		});

		mThread.start();

		tv_carbody_golf7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				ReplaceFragmentMethod(0);
				setTextEnable(0);
			}
		});

		tv_title1_golf7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				ReplaceFragmentMethod(1);
				setTextEnable(1);
			}
		});

		tv_title2_golf7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				ReplaceFragmentMethod(2);
				setTextEnable(2);
			}
		});

		tv_title3_golf7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				ReplaceFragmentMethod(3);
				setTextEnable(3);
			}
		});

	}

	private void setTextEnable(final int index) {
		tv_carbody_golf7.setEnabled(index != 0);
		tv_title1_golf7.setEnabled(index != 1);
		tv_title2_golf7.setEnabled(index != 2);
		tv_title3_golf7.setEnabled(index != 3);

		tv_carbody_golf7.setBackgroundResource(index == 0 ? R.drawable.red_press_golf7 : R.drawable.red_normal_golf7);
		tv_title1_golf7.setBackgroundResource(index == 1 ? R.drawable.red_press_golf7 : R.drawable.red_normal_golf7);
		tv_title2_golf7.setBackgroundResource(index == 2 ? R.drawable.red_press_golf7 : R.drawable.red_normal_golf7);
		tv_title3_golf7.setBackgroundResource(index == 3 ? R.drawable.red_press_golf7 : R.drawable.red_normal_golf7);
	}

	private void initVmCtrl() {
		tv_carbody_golf7 = (TextView) findViewById(R.id.tv_carbody_vm);
		tv_title1_golf7 = (TextView) findViewById(R.id.tv_title1_vm);

		setVmTextEnable(0);

		tv_time_golf7 = (TextView) findViewById(R.id.tv_time_vm);

		Thread mThread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					final Calendar c = Calendar.getInstance();
					ActivityCarPC.this.runOnUiThread(new Runnable() {

						@Override
						public void run() {

							tv_time_golf7.setText(
									String.format("%02d:%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)));
						}
					});

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		mThread.start();

		tv_carbody_golf7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ReplaceFragmentMethod(0);
				setVmTextEnable(0);
			}
		});

		tv_title1_golf7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ReplaceFragmentMethod(1);
				setVmTextEnable(1);
			}
		});
	}

	private void setVmTextEnable(final int index) {

		tv_carbody_golf7.setEnabled(index != 0);
		tv_carbody_golf7.setBackgroundResource(index == 0 ? R.drawable.red_press_golf7 : R.drawable.red_normal_golf7);
		tv_title1_golf7.setEnabled(index != 1);
		tv_title1_golf7.setBackgroundResource(index == 1 ? R.drawable.red_press_golf7 : R.drawable.red_normal_golf7);
	}

	private void getCarTypeAndNum() {
		int carnum = Settings.System.getInt(getContentResolver(), "carnum", ConstantCar.NONE_CARTYPE);
		Log.d(TAG, "carnum =" + carnum);
		if (carnum != CARTYPE_MODE && carnum != 0) {
			CARTYPE_MODE = carnum;
		}
		int carmodel = Settings.System.getInt(getContentResolver(), "carmodel", ConstantCar.NONE_CARMODELS);
		if (carmodel != ActivityCarPC.CAR_NUM && carmodel != 0) {
			ActivityCarPC.CAR_NUM = carmodel;
		}
	}

	private void initRadio() {
		RadioButton radio1 = (RadioButton) findViewById(R.id.btn_perminute_fuel);
		RadioButton radio2 = (RadioButton) findViewById(R.id.btn_histroy_fuel);
		radio1.setText(R.string.rg_carBody);
		radio2.setText(R.string.title_fuelmileage);
	}

	private void initHonda() {
		m_HondaGroup = (RadioGroup) findViewById(R.id.radio_honda);
		m_HondaGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				for (int i = 0; i < m_HondaGroup.getChildCount(); i++) {
					if (((RadioButton) m_HondaGroup.getChildAt(i)).isChecked()) {
						ReplaceFragmentMethod(i);
						break;
					}
				}
			}
		});
	}

	private void ReplaceFragmentMethod(int fragIndex) {
		framelayout.post(new SwapViews(true, mFragments[fragIndex])); // 添加新的View
	}

	private final class SwapViews implements Runnable {
		private final boolean mPosition;
		private final Fragment mfragment;

		public SwapViews(boolean position, Fragment fragment) {
			mPosition = position;
			mfragment = fragment;
		}

		public void run() {
			FragmentTransaction tration = fragmentManager.beginTransaction();
			tration.replace(containerViewId, mfragment);
			tration.commitAllowingStateLoss();
		}
	}

	private void initFragments(int fragCount) {
		mFragments = new Fragment[fragCount];
	}

	// private void initRemote() {
	//
	// if (REMOTE_PROXY == null) {
	// REMOTE_PROXY = ConnConnect.getInstance().getRemoteProxy();
	// CallbackTask.getInstance().setOnReceiveCanbusListener(new
	// OnReceiveCanbusListener() {
	// @Override
	// public void onDataReceive(byte[] buffer, int size) {
	// if (CARTYPE_MODE != ConstantCar.NONE_CARMODELS) {
	// switch (MainActivity.CARTYPE_MODE) {
	// case ConstantCar.CARTYPE_PSA:
	// PsaToyotaParseData.getInstance().parsePsaCmd(buffer, size);
	// break;
	// case ConstantCar.CARTYPE_TOYOTA:
	// PsaToyotaParseData.getInstance().parseToyotaCmd(buffer, size);
	// break;
	//
	// case ConstantCar.CARTYPE_VWF0:
	// switch (CAR_NUM) {
	//
	// case ConstantCar.VWFO_GOLF7:
	//
	// BaseVechParseData.getInstance().parseCmdId(buffer, size);
	//
	// Golf7ParseData.getInstance().ParseGolf7CMD(buffer, size);
	//
	// break;
	//
	// case ConstantCar.VWFO_MAGOTAN:
	// VmParseData.getInstance().parseVwfoCmd(buffer, size);
	// break;
	// default:
	// break;
	// }
	// break;
	// case ConstantCar.CARTYPE_HONDA:
	// HondaParseData.getInstance().ParseHondaCMD(buffer, size);
	// break;
	// case ConstantCar.CARTYPE_SAIC:// 上汽
	// switch (CAR_NUM) {
	// case ConstantCar.SAIC_CARMODELS_RUITON:// 名爵锐腾
	// SaicParseData.getInstance().parseCmd(buffer, size);
	// break;
	// default:
	// break;
	// }
	// break;
	// case ConstantCar.CARTYPE_GM:
	// GMParseData.getInstance().parseGmCmd(buffer, size);
	// break;
	// default:
	// break;
	// }
	// }
	// }
	// });
	// }
	// }

	private void initMain() {
		m_RadioMainGroup = (RadioGroup) findViewById(R.id.radio_toyota_fuel);
		m_RadioMainGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				for (int i = 0; i < m_RadioMainGroup.getChildCount(); i++) {
					if (((RadioButton) m_RadioMainGroup.getChildAt(i)).isChecked()) {
						ReplaceFragmentMethod(i);
						break;
					}
				}
			}
		});
	}

	private void initPsaCtrl() {
		m_RadioGroup = (RadioGroup) findViewById(R.id.radio_computer);
		m_BtnInstant = (RadioButton) findViewById(R.id.btn_computer1);
		m_BtnTrip1 = (RadioButton) findViewById(R.id.btn_computer2);
		m_BtnTrip2 = (RadioButton) findViewById(R.id.btn_computer3);

		m_RadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int radioid) {
				for (int i = 0; i < m_RadioGroup.getChildCount(); i++) {
					if (((RadioButton) m_RadioGroup.getChildAt(i)).isChecked()) {
						indexfragment = i;
						System.out.println("indexfragment == " + indexfragment);
						ReplaceFragmentMethod(i);
						break;
					}
				}

			}
		});
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case Constant.MESSAGE_CHANGEPAGE_CARPC:
				indexfragment++;
				indexfragment = indexfragment % 3;
				System.out.println("MESSAGE_CHANGEPAGE_CARPC indexfragment ==" + indexfragment);
				switch (indexfragment) {
				case 0:
					m_BtnInstant.setChecked(true);
					break;
				case 1:
					m_BtnTrip1.setChecked(true);
					break;
				case 2:
					m_BtnTrip2.setChecked(true);
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

	// public void writeBuffer(byte[] buffer, int iLength) {
	// if (buffer != null) {
	// if (REMOTE_PROXY != null) {
	// try {
	// REMOTE_PROXY.writeBuffer(DataUtil.APP2SERVER_NEEDCHECK, buffer, iLength);
	// } catch (RemoteException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// }

	@Override
	protected void onResume() {
		isActive = true;
		super.onResume();
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

	@Override
	protected void onPause() {
		isActive = false;
		// 不在行车电脑界面
		// if (CARTYPE_MODE == ConstantCar.CARTYPE_TOYOTA) {
		// writeBuffer(new byte[] { 0x03, 0x6a, 0x04, 0x03, 0x00 }, 5);
		// }
		super.onPause();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void callbackCMD(int[] buffer, int iLength) {

		// if (buffer != null) {
		// int size = buffer.length;
		// byte buf[] = new byte[size];
		// for (int i = 0; i < buf.length; i++) {
		// buf[i] = (byte) buffer[i];
		// }
		// writeBuffer(buf, iLength);
		// }
	}

	@Override
	public void jumpFragment(int index) {

		ReplaceFragmentMethod(index);
		System.out.println("index1  ====" + index);

		if (ActivityCarPC.CARTYPE_MODE == ConstantCar.CARTYPE_TOYOTA) {
			setLittleTitle(index);
		}

	}

	private void setLittleTitle(final int index) {

		System.out.println("index  ====" + index);
		ActivityCarPC.this.runOnUiThread(new Runnable() {

			@Override
			public void run() {

				TextView tv_title = (TextView) findViewById(R.id.toyota_tv_head_title);
				switch (index) {
				case 0:
					tv_title.setText(R.string.title_perminutefuel);
					break;
				case 1:
					tv_title.setText(R.string.title_histroyfuel);
					break;
				default:
					break;
				}
			}
		});
	}

}
