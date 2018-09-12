package ex.hiworld.backcar;

import java.util.ArrayList;
import java.util.List;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import ex.hiworld.MyApp;
import ex.hiworld.backui.R;
import tools.HandlerUI;
import tools.LogsUtils;
import tools.Ticks;

public class LayoutMenu {

	private static ViewGroup baseFullView; 
	private View contentView;
	private int WITH_CONTENT;
	private ImageView vHideMenu;
	private boolean bShowContent = true;
	private static LayoutMenu OBJ = new LayoutMenu();

	public static LayoutMenu getInstance() {
		return OBJ;
	}

	@SuppressLint("InflateParams")
	public View getView() {
		if (baseFullView == null) {
			baseFullView = (ViewGroup) LayoutInflater.from(MyApp.getInstance()).inflate(R.layout.fv_baselayout, null); 
			vHideMenu = (ImageView) baseFullView.findViewById(R.id.b_hidemenu);
			vHideMenu.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (bShowContent) {
						HandlerUI.getInstance().post(HIDE_CONTENT);
					} else {
						HandlerUI.getInstance().post(SHOW_CONTENT);
					}
				}
			});
//			addListenerContentSize(baseFullView);
			LogsUtils.i("getView: 1 parent" + baseFullView );
		}
		return baseFullView;
	}

	@SuppressLint("NewApi")
	private void addListenerContentSize(final View view) {
		if (view != null) {
			view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

				@Override
				public void onGlobalLayout() {
					if(view == contentView)
						WITH_CONTENT = view.getWidth();
					
					if (WITH_CONTENT == 0) {
						LogsUtils.i("add WITH_CONTENT == 0 " + view.getWidth() + ", " + view.getMeasuredWidth());
						view.measure(0, 0);
					} else {
						view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
						LogsUtils.i("add final " + view.getWidth() + ", " + view.getMeasuredWidth());
					}
				}
			});
		}
	}
	
	private List<View> views = new ArrayList<View>();
	
	public void addContent(View view) {
		if (getView() != null) {
			if(views.contains(view)) {
				return;
			}
			views.add(view);
			contentView = view; 
			LogsUtils.i("getView: 1 getChildCount " + baseFullView.getChildCount());
			addListenerContentSize(contentView);
			LogsUtils.i("getView: 2 " + baseFullView);
			FrameLayout findViewById = (FrameLayout) baseFullView.findViewById(R.id.container);

			
			findViewById.addView(contentView);
			LogsUtils.i("getView: 3 " + baseFullView);
			LogsUtils.i("getView: 4 getChildCount " + baseFullView.getChildCount());

			LogsUtils.i("FullView add addContent");
		}

		Ticks.addTicks1s(AUTO_HIDE);
	}
	
	public void removeContent() {
		if (contentView != null && baseFullView != null) {
			if (views.size() > 0) {
				LogsUtils.i("removeContent end " + views.size());
				views.remove(contentView);
				restoreWith();
				FrameLayout findViewById = (FrameLayout) baseFullView.findViewById(R.id.container);
				findViewById.removeView(contentView); 
				contentView = null;
				baseFullView = null;
				vHideMenu.setImageResource(R.drawable.btn_bc_show);
			}
		}
	}

	Runnable HIDE_CONTENT = new Runnable() {
		@Override
		public void run() {
			if (contentView == null || WITH_CONTENT == 0)
				return;
			SEC = -1;
			ValueAnimator valueAnimator = createDropAnimator(contentView, WITH_CONTENT, 0, false);
			valueAnimator.setDuration(100);
			valueAnimator.start();
			bShowContent = false;
			// <<
			vHideMenu.setImageResource(R.drawable.btn_bc_hide);
			LogsUtils.i("hide :");
		}
	};

	Runnable SHOW_CONTENT = new Runnable() {
		@Override
		public void run() {
			resetAutoHide();
			if (contentView == null || WITH_CONTENT == 0)
				return;
			ValueAnimator valueAnimator = createDropAnimator(contentView, 0, WITH_CONTENT, true);
			valueAnimator.setDuration(100);
			valueAnimator.start();
			bShowContent = true;
			// >>
			vHideMenu.setImageResource(R.drawable.btn_bc_show);
			
			LogsUtils.i("show :");
		}
	};

	private int SEC = 6;

	public void resetAutoHide() {
		SEC = 6;
	}

	Runnable AUTO_HIDE = new Runnable() {

		@Override
		public void run() {
			if (SEC > 0)
				SEC--;
			if (SEC == 0) {
				SEC = -1;
				HandlerUI.getInstance().post(HIDE_CONTENT);
			}
		}
	};

	private ValueAnimator createDropAnimator(final View v, int start, int end, final boolean show) {
		ValueAnimator animator = ValueAnimator.ofInt(start, end);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				if (v == null)
					return;
				v.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
				int value = (Integer) arg0.getAnimatedValue();
				ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
				layoutParams.width = value;
				v.setLayoutParams(layoutParams);
			}
		});
		return animator;
	}

	void restoreWith() {
		if (contentView != null) {
			LogsUtils.i("restore");
			View v = contentView;
			v.setVisibility(View.VISIBLE);
			ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
			layoutParams.width = WITH_CONTENT;
			v.setLayoutParams(layoutParams);
		}
	}
}
