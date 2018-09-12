package ex.hiworld.backcar;

import ex.hiworld.constants.DataCanbus;
import ex.hiworld.constants.FinalCanbus;
import tools.LogsUtils;

public class HandlerViewUtils {
	private static LayoutBase mBase;

	public static void buildContent() {
		LogsUtils.i("HandlerCanbus buildContent: " + DataCanbus.sCanbusId);
		LayoutBase base = null;
		switch (DataCanbus.sCanbusIdBase) {
		case FinalCanbus.CAR_HONDA_ALL:
			if (DataCanbus.sCanbusId == FinalCanbus.CAR_HONDA_17Crv)
				base = new Full17Crv();
			else
				base = new FullSiyu();
			break;
		case FinalCanbus.CAR_TOYOTA_ALL:
			base = new FullToyota();
			break;
		case FinalCanbus.CAR_NISSAN_ALL:
			break;
		default:
			break;
		}

		if (mBase != base && base != null) {
			LogsUtils.i("BACKUI buildContent " + base.getClass().getSimpleName());
			BackCarUtils.getInstance().buildContent(mBase = base);
		}
	}

	public static void removeContent() {
		LogsUtils.i(" removeContent ");
		BackCarUtils.getInstance().destroyContent();
		mBase = null;
	}
}
