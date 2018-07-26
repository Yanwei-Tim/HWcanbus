package com.hiworld.canbus.util;

import java.io.File;

import android.os.Environment;
import android.util.Log;

public class CrashApphandler extends CrashAppLog {
	public static CrashApphandler mCrashApphandler = null;

	//定义文件存放路径
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/crashLogt11";
	// 实现单例

	private CrashApphandler() {
	};

	public static CrashApphandler getInstance() {

		if (mCrashApphandler == null)
			mCrashApphandler = new CrashApphandler();

		return mCrashApphandler;

	}

	@Override
	public void initParams(CrashAppLog crashAppLog) {

		if (crashAppLog != null) {

			// 动态的改变缓存目录和缓存文件数量
			crashAppLog.setCAHCE_CRASH_LOG(PATH);
			crashAppLog.setLIMIT_LOG_COUNT(5);
		}
	}

	@Override
	public void sendCrashLogToServer(File folder, File file) {
		// 发送服务端
		Log.e("*********",
				"文件夹:" + folder.getAbsolutePath() + " - "
						+ file.getAbsolutePath() + "");
	}
}
