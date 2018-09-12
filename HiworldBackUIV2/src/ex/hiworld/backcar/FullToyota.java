package ex.hiworld.backcar;

import android.view.View;
import ex.hiworld.backui.R;
import ex.hiworld.constants.FinalCanbus;
import ex.hiworld.view.MyCustomFullView;
import ex.hiworld.view.MyCustomFullView.ITouchEvent;
import tools.LogsUtils;

// 仅发全屏幕坐标
public class FullToyota extends LayoutBase {

	@Override
	void initView() {
		TYPE_FULLVIEW = S_T_FULLSCREEN;

		MyCustomFullView mFullView = (MyCustomFullView) mRootView.findViewById(R.id.b_fullscreen);
		mFullView.setTouchEvent(new ITouchEvent() {

			@Override
			public void touchEv(float x, float y) {
				screenClick((int) x, (int) y);
			}
		});
	}

	@Override
	void funcClick(View view) {
		LogsUtils.i("v click " + view);
	}

	private void screenClick(int x, int y) {
		LogsUtils.i("screenClick . " + x + " " + y);
		sendCMD(FinalCanbus.C_HW_SCREEN_TOUCH, new int[] { x, y });
	}

	@Override
	void in() {
	}

	@Override
	void out() {
	}

	@Override
	int getLayoutID() {
		return R.layout.fv_honda;
	}

}
