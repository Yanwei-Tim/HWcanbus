package ex.hiworld;

import android.app.Application;
import android.content.Intent;
import ex.hiworld.backcar.BackCarUtils;
import ex.hiworld.car.TaskServerConnect;
import ex.hiworld.constants.DataCanbus;
import ex.hiworld.constants.FinalCanbus;
import tools.LogsUtils;
import tools.Ticks;
import tools.spUtils;

public class MyApp extends Application {

	private static MyApp OBJ;

	@Override
	public void onCreate() {
		super.onCreate();
		initStatic();
		connectServer();
	}

	private void initStatic() {
		OBJ = this;
		new Ticks().run();
		spUtils.init(getPackageName(), this);
		DataCanbus.sCanbusId = spUtils.get(FinalCanbus.U_CANBUS_ID, 0);
		LogsUtils.i("app read " + DataCanbus.sCanbusId);
		BackCarUtils.getInstance().init(this);
	}

	private void connectServer() {
		TaskServerConnect.connect(this);
		selfServer();
	}

	public static MyApp getInstance() {
		return OBJ;
	}

	private void selfServer() {
		Intent intent = new Intent();
		intent.setClass(this, BackServer.class);
		startService(intent);
	}
}
