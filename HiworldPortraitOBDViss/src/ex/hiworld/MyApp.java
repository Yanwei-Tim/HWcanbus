package ex.hiworld;

import com.youzi.customer.connect.TheApp;

import android.content.Intent;
import ex.hiworld.obd.OBDServer;
import tools.Ticks;
import tools.spUtils;

public class MyApp  extends TheApp {

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
//		DataCanbus.sCanbusId = spUtils.get(FinalCanbus.U_CANBUS_ID, 0);
//		LogsUtils.i("app read " + DataCanbus.sCanbusId);
//		BackCarUtils.getInstance().init(this);
	}

	private void connectServer() {
//		TaskServerConnect.connect(this);
//		selfServer();
		selfOBDServer();
	}

	private void selfOBDServer() {
		Intent intent = new Intent();
		intent.setClass(this, OBDServer.class);
		startService(intent);
		
	}

	public static MyApp getInstance() {
		return OBJ;
	}

//	private void selfServer() {
//		Intent intent = new Intent();
//		intent.setClass(this, BackServer.class);
//		startService(intent);
//	}
}
