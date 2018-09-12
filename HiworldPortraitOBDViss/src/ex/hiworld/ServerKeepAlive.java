package ex.hiworld;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by APP03 on 2018/6/21.
 */

public class ServerKeepAlive extends Service {
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	IBinder binder = new Binder();
}
