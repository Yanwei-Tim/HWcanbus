package ex.hiworld.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import ex.hiworld.constants.FinalCanbus;
import tools.LogsUtils;

public class MyCustomFullView extends FrameLayout {

	public MyCustomFullView(Context context) {
		super(context); 
	}

	public MyCustomFullView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float rawX = event.getRawX();
		float rawY = event.getRawY();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			LogsUtils.i(" ev " + rawX + " " + rawY);

			if (mTouchEvent != null) {
				mTouchEvent.touchEv(rawX, rawY);
			}
		}

		return true;
	} 
	
	ITouchEvent mTouchEvent;
	
	public void setTouchEvent(ITouchEvent mTouchEvent) {
		this.mTouchEvent = mTouchEvent;
	}
	public interface ITouchEvent{
		void touchEv(float x, float y);
	}

}
