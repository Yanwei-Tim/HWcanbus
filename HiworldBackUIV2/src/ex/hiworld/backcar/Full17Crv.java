package ex.hiworld.backcar;

import android.view.View;
import android.widget.ImageView;
import ex.hiworld.backui.R;
import ex.hiworld.constants.DataCanbus;
import ex.hiworld.constants.FinalCanbus;
import ex.hiworld.view.MyCustomFullView;
import ex.hiworld.view.MyCustomFullView.ITouchEvent;
import tools.IUiNotify;
import tools.LogsUtils;

//三个视角切换图标 + 全屏幕坐标图标
public class Full17Crv extends LayoutBase {
	
	private View layExpand;
	private ImageView standard, overLook, wide, btnMenu;
	
//	private ImageView FloatBtn;
//	Point mFloatPoint;

	@Override
	void initView() {
		TYPE_FULLVIEW = S_T_FULLSCREEN;

		standard = viewClicks(R.id.b_standard);
		overLook = viewClicks(R.id.b_overlook);
		wide = viewClicks(R.id.b_wide);
		btnMenu = viewClicks(R.id.b_hidemenu);
		layExpand = viewClicks(R.id.lay_1);
		
		MyCustomFullView mFullView = (MyCustomFullView) mRootView.findViewById(R.id.b_fullscreen);
		mFullView.setTouchEvent(new ITouchEvent() {
			
			@Override
			public void touchEv(float x, float y) {
				screenClick((int)x, (int)y);
			}
		});
		

//		if(FloatBtn == null) {
//			addFloat();
//			mFloatPoint = new Point();
//		}
//		mRootView.addView(FloatBtn);
	}
	

//	private void addFloat() {
//		FloatBtn = new ImageView(MyApp.getInstance());
//		FloatBtn.setBackgroundResource(R.drawable.d_float_btn);
//		FloatBtn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				func_float_click();
//			}
//
//			int c = 0;
//			protected void func_float_click() {
//				LogsUtils.i("  func_float_click ");
//				c++;
//				c %= 10;
//				HandlerCanbus.sendCmd(FinalCanbus.C_HW_CHANGE_PANORAMA, c);
//			}
//		});
//
//		FloatBtn.setOnTouchListener(new OnTouchListener() {
//			int lastX;
//			int lastY;
//			int lastDownTime;
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				boolean ret = false;
//				switch (event.getAction()) {
//				case MotionEvent.ACTION_DOWN:
//					lastDownTime = (int) System.currentTimeMillis();
//					lastX = (int) event.getRawX();
//					lastY = (int) event.getRawY();
//					ret = true;
//					break;
//
//				case MotionEvent.ACTION_MOVE:
//					float x = event.getRawX();
//					float y = event.getRawY();
//					int ix = (int) (x - lastX);
//					int iy = (int) (y - lastY);
//					mFloatPoint.x += ix;
//					mFloatPoint.y += iy;
//					lastX = (int) x;
//					lastY = (int) y; 
//					FloatBtn.setX(mFloatPoint.x);
//					FloatBtn.setY(mFloatPoint.y);
//					break;
//				case MotionEvent.ACTION_UP:
//					int currentTime = (int) System.currentTimeMillis();
//					if (currentTime - lastDownTime < 300)
//						FloatBtn.performClick(); 
//					break;
//
//				default:
//					break;
//				}
//
//				return ret;
//			}
//		});
//	}
 
	private void screenClick(int x, int y) {
		LogsUtils.i("screenClick . " + x + " " + y);
		sendCMD(FinalCanbus.C_HW_SCREEN_TOUCH, new int[] {x, y});
	}

	boolean isExpand = false;

	@Override
	void funcClick(View view) {
		switch (view.getId()) {
		case R.id.b_wide:
			sendCMD(FinalCanbus.C_HW_CAMERA_MODE, 1);
			break;
		case R.id.b_standard: {
			sendCMD(FinalCanbus.C_HW_CAMERA_MODE, 2);
			break;
		}
		case R.id.b_overlook:
			sendCMD(FinalCanbus.C_HW_CAMERA_MODE, 3);
			break;

		case R.id.b_hidemenu:{
			expandView(!isExpand);
			break;
		}
		}
	}

	private void expandView(boolean b) {
//		LogsUtils.i("layExpand:" + layExpand);
		if (b) {
			isExpand = true;
			layExpand.setVisibility(View.VISIBLE);
			btnMenu.setImageResource(R.drawable.btn_bc_show);
		} else {
			isExpand = false;
			layExpand.setVisibility(View.GONE);
			btnMenu.setImageResource(R.drawable.btn_bc_hide);
		}
	}

	@Override
	void in() {
		expandView(isExpand);

//		if(FloatBtn != null) {
//			LogsUtils.i(" x "+ BackCarUtils.getInstance().getFloatLp().x+ " Y " + BackCarUtils.getInstance().getFloatLp().y);
//			mFloatPoint.x = BackCarUtils.getInstance().getFloatLp().x;
//			mFloatPoint.y = BackCarUtils.getInstance().getFloatLp().y;
//			FloatBtn.setX(mFloatPoint.x);
//			FloatBtn.setY(mFloatPoint.y);
//		}
		DataCanbus.NOTIFY_EVENTS[FinalCanbus.U_HW_CAMERA_MODE].addNotify(notify, 1);
	}

	@Override
	void out() {
 
		
		DataCanbus.NOTIFY_EVENTS[FinalCanbus.U_HW_CAMERA_MODE].removeNotify(notify);
	}
	
	  

	IUiNotify notify = new IUiNotify() {

		@Override
		public void onNotify(int updateCode, int[] ints, float[] flts, String[] strs) {
			int val = DataCanbus.DATA[FinalCanbus.U_HW_CAMERA_MODE] & 0xFF;
			LogsUtils.i(" full notify " + val);
			viewChecked(standard, val == 2);
			viewChecked(overLook, val == 3);
			viewChecked(wide, val == 1);
		}
	};

	@Override
	int getLayoutID() {
		return R.layout.fv_17crv;
	}


}
