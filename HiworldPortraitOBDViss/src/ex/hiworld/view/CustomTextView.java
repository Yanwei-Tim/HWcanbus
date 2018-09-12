package ex.hiworld.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView{

	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CustomTextView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		AssetManager manager = context.getAssets();
		Typeface type = Typeface.createFromAsset(manager, "fonts/impact.ttf");
		setTypeface(type);
	}

}
