package ex.hiworld.obd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import ex.hiworld.MyApp;

public abstract class OBDLayoutBase implements OnClickListener {
	
//	public static int S_T_MENU_BUTTON = 0;
//	public static int S_T_FULLSCREEN = 1;
//	 
//	protected int TYPE_FULLVIEW = S_T_MENU_BUTTON;
	
	abstract void funcClick(View v);
	abstract void in();
	abstract void out();
	abstract int getLayoutID();
	protected ViewGroup mRootView;
//	protected static OBDLayoutBase mFullLay;
//	protected static OBDLayoutBase mExpanedLay;
//	
//	public OBDLayoutBase getExpanedLay() {
//		return mExpanedLay;
//	}
//	
//	public OBDLayoutBase getFullLay() {
//		return mFullLay;
//	}

//	public int getFullViewType() {
//		return TYPE_FULLVIEW;
//	}
	
	@Override
	public void onClick(View v) { 
		funcClick(v);
	}

	public OBDLayoutBase() {
		mRootView = (ViewGroup) LayoutInflater.from(MyApp.getInstance()).inflate(getLayoutID(), null);
		initView();
	}

	abstract void initView();

	public View getView() {
		return mRootView;
	}

	public <T extends View> T viewFinder(int id) {
		T a = null;
		if (mRootView != null) {
			a = (T) mRootView.findViewById(id);
		}
		return a;
	}

	public <T extends View> T viewClicks(int id) {
		T a = null;
		if (mRootView != null) {
			a = (T) mRootView.findViewById(id);
			a.setOnClickListener(this);
		}
		return a;
	}
	
	
	public void viewChecked(View v, boolean check) {
		if (v != null)
			v.setSelected(check);
	}

}
