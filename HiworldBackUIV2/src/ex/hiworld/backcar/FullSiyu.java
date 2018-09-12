package ex.hiworld.backcar;

import android.view.View;
import android.widget.ImageView;
import ex.hiworld.backui.R;
import ex.hiworld.constants.DataCanbus;
import ex.hiworld.constants.FinalCanbus;
import tools.IUiNotify;
import tools.LogsUtils;

//三个视角切换图标
public class FullSiyu extends LayoutBase {

	private View layExpand;
	private ImageView standard, overLook, wide, btnMenu;
	private boolean isExpand;
	@Override
	void initView() {
		standard = viewClicks(R.id.b_standard);
		overLook = viewClicks(R.id.b_overlook);
		wide = viewClicks(R.id.b_wide);
		btnMenu = viewClicks(R.id.b_hidemenu);
		layExpand = viewClicks(R.id.lay_1);
	}

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
//		LogsUtils.i("layExpand:" + mRootView.getWidth());
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
		return R.layout.fv_siyu;
	}

}
