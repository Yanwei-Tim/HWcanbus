package ex.hiworld.backcar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import ex.hiworld.MyApp;
import ex.hiworld.backui.R;
import ex.hiworld.car.HandlerCanbus;
import ex.hiworld.constants.DataCanbus;
import ex.hiworld.constants.FinalCanbus;
import tools.LogsUtils;
import tools.Utils;

@SuppressLint({ "ClickableViewAccessibility", "InflateParams" })
public class BackCarUtils {

	private static Handler HANDLER_UI;
	private static Context sContext;
	private static WindowManager wManager;
	private boolean bShowFloatEnable = true;
	boolean bAddFullViewLay;

	LayoutBase mBaseLayout;

	private ImageView FloatBtn;
	static {
		HANDLER_UI = new Handler(Looper.getMainLooper());
	}

	private static BackCarUtils instance;

	public static BackCarUtils getInstance() {
		if (instance == null)
			instance = new BackCarUtils();
		return instance;
	}

	private LayoutParams lpFloat;
	private LayoutParams lpFullLayoutParams,lpAllScreen;

	public void init(Context context) {
		sContext = context;
		wManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	}

	public void initView() {
		FloatBtn = new ImageView(sContext);
		FloatBtn.setBackgroundResource(R.drawable.d_float_btn);
		FloatBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				func_float_click();
			}
		});

		FloatBtn.setOnTouchListener(new OnTouchListener() {
			WindowManager.LayoutParams params2 = getFloatLp();
			int lastX;
			int lastY;
			int lastDownTime;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				boolean ret = false;
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					lastDownTime = (int) System.currentTimeMillis();
					lastX = (int) event.getRawX();
					lastY = (int) event.getRawY();
					ret = true;
					break;

				case MotionEvent.ACTION_MOVE:
					float x = event.getRawX();
					float y = event.getRawY();
					int ix = (int) (x - lastX);
					int iy = (int) (y - lastY);
					params2.x += ix;
					params2.y += iy;
					lastX = (int) x;
					lastY = (int) y;
					wManager.updateViewLayout(FloatBtn, params2);
					break;
				case MotionEvent.ACTION_UP:
					int currentTime = (int) System.currentTimeMillis();
					if (currentTime - lastDownTime < 300)
						FloatBtn.performClick();
					lpFloat = params2;
					break;

				default:
					break;
				}

				return ret;
			}
		});
	}

	int c = 0;
	protected void func_float_click() {
		LogsUtils.i("  func_float_click ");
		c++;
		c %= 10;
		HandlerCanbus.sendCmd(FinalCanbus.C_HW_CHANGE_PANORAMA, c);
	}

	public void showFloatBtn() {
		postRunnable_Ui(true, SHOW_FLOAT);
	}

	public void hideFloatBtn() {
		postRunnable_Ui(true, HIDE_FLOAT);
	}

	public void showFullView() {
		postRunnable_Ui(true, SHOW_FULLVIEW);
	}

	public void hideFullView() {
		postRunnable_Ui(true, HIDE_FULLVIEW);
	}

	boolean isAddView = false;
	public void buildContent(LayoutBase v) {
		if(isAddView) {
			destroyContent();
		}
		
//		LogsUtils.i(" buildContent. " + v.getClass());
		mBaseLayout = v;
		if (mBaseLayout != null) {
			isAddView = true;
//			LogsUtils.i("build v " + mBaseLayout + " type: " + mBaseLayout.getFullViewType());
		}
	}

	public void destroyContent() {
		hideFullView();
		if (mBaseLayout != null) {
			LogsUtils.i(" destroyContent. ");
			isAddView = false;
			mBaseLayout = null;
		}
	}

	public void setFloatShowEnable(boolean show) {
		bShowFloatEnable = show;
		if (bShowFloatEnable) {
			showFloatBtn();
		} else {
			hideFloatBtn();
		}
	}

	private boolean isFloatShow = false;
	private Runnable SHOW_FLOAT = new Runnable() {

		@Override
		public void run() {
			if (bShowFloatEnable) {
				if (FloatBtn == null) {
					initView();
				}

				if (!isFloatShow) {
					isFloatShow = true;
					wManager.addView(FloatBtn, getFloatLp());
				}
				LogsUtils.i("float show");
			}
		}
	};

	private Runnable HIDE_FLOAT = new Runnable() {

		@Override
		public void run() {
			if (isFloatShow) {
				isFloatShow = false;
				wManager.removeView(FloatBtn);
			}
			LogsUtils.i("float hide");
		}
	};

	private Runnable SHOW_FULLVIEW = new Runnable() {

		@Override
		public void run() {
			if (bAddFullViewLay)
				return;
			bAddFullViewLay = true;
			if (mBaseLayout != null) {
				mBaseLayout.in();
 
				LayoutParams lp = getFullViewLayoutParams();
				if(mBaseLayout.getFullViewType() == LayoutBase.S_T_FULLSCREEN) {
					lp = getFullViewLayoutParamsAllScreen();
					
					if(DataCanbus.isExistFloatBtn) {
						hideFloatBtn();
					}
				}
				View view = mBaseLayout.getView();
				if(view != null) {
					if(view.getWindowToken() != null) {
						wManager.removeView(view);
					}
					wManager.addView(view, lp);
				}
			}
		}
	};

	private Runnable HIDE_FULLVIEW = new Runnable() {

		@Override
		public void run() {
			if (!bAddFullViewLay)
				return;
			bAddFullViewLay = false;

			if (mBaseLayout != null) {
				mBaseLayout.out();

				if (mBaseLayout.getFullViewType() == LayoutBase.S_T_FULLSCREEN) {
					if (DataCanbus.isExistFloatBtn) {
						showFloatBtn();
					}
				}
				
				View view = mBaseLayout.getView();
				if (view != null) {
					if (view.getWindowToken() != null) {
						wManager.removeView(view);
					}
				}
			}
		}
	};

	public void postRunnable_Ui(boolean bRemoveOther, Runnable runnable) {
		if (runnable != null) {
			if (bRemoveOther)
				removeRunnable_Ui(runnable);

			HANDLER_UI.post(runnable);
		}
	}

	public void postRunnable_Ui(boolean bRemoveOther, Runnable runnable, long delayMillis) {
		if (runnable != null) {
			if (bRemoveOther)
				removeRunnable_Ui(runnable);

			HANDLER_UI.postDelayed(runnable, delayMillis);
		}
	}

	public void removeRunnable_Ui(Runnable runnable) {
		if (runnable != null)
			HANDLER_UI.removeCallbacks(runnable);
	}

	public LayoutParams getFloatLp() {
		if (lpFloat == null) {
			lpFloat = new WindowManager.LayoutParams();
			lpFloat.type = WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW + 16; // -> TYPE_DRAG
			lpFloat.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
					| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
			lpFloat.format = PixelFormat.RGBA_8888;
			lpFloat.alpha = 1.0f;
			lpFloat.width = LayoutParams.WRAP_CONTENT;
			lpFloat.height = LayoutParams.WRAP_CONTENT;
			lpFloat.x = 0;
			lpFloat.y = Utils.dp2px(MyApp.getInstance(), 170);
			lpFloat.gravity = Gravity.TOP | Gravity.LEFT;

		}
		return lpFloat;
	}

	public LayoutParams getFullViewLayoutParams() {
		if (lpFullLayoutParams == null) {
			lpFullLayoutParams = new WindowManager.LayoutParams();
			lpFullLayoutParams.type = WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW + 26; // -> TYPE_DISPLAY_OVERLAY
			lpFullLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
					| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
			lpFullLayoutParams.format = PixelFormat.RGBA_8888;
			lpFullLayoutParams.alpha = 1.0f;
			lpFullLayoutParams.width = LayoutParams.WRAP_CONTENT;
			lpFullLayoutParams.height = LayoutParams.WRAP_CONTENT;
			lpFullLayoutParams.x = 0;
			lpFullLayoutParams.y = 0;
			lpFullLayoutParams.gravity = Gravity.RIGHT|Gravity.TOP;
		}
		return lpFullLayoutParams;
	}
	
	public LayoutParams getFullViewLayoutParamsAllScreen() {
		if (lpAllScreen == null) {
			lpAllScreen = new WindowManager.LayoutParams();
			lpAllScreen.type = WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW + 16; // -> TYPE_DISPLAY_OVERLAY
//			lpAllScreen.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
//					| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
			lpAllScreen.format = PixelFormat.RGBA_8888;
			lpAllScreen.alpha = 1.0f;
			lpAllScreen.width = LayoutParams.MATCH_PARENT;
			lpAllScreen.height = LayoutParams.MATCH_PARENT;
			lpAllScreen.x = 0;
			lpAllScreen.y = 0;
//			lpAllScreen.gravity = Gravity.TOP | Gravity.RIGHT;
		}
		return lpAllScreen;
	}
}
