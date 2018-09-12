package com.obdstart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class LogsSave {
	//第一个是Logcat ，也就是我们想要获取的log日志
	//第二个是 -s 也就是表示过滤的意思
	//第三个就是 我们要过滤的类型 W表示warm ，我们也可以换成 D ：debug， I：info，E：error等等
	String[] running = new String[]{"logcat","-s", "adb logcat *: W"};
	
	private final static LogsSave OBJ  = new LogsSave();
	
	public static LogsSave getObj() {
		return OBJ;
	}
	
//	public void readLogs() {
//		StringBuffer readLogs = new StringBuffer();
//		StringBuffer clearLogs = new StringBuffer();
//		readLogs.append("logcat -d -v time");
//		clearLogs.append("logcat -c");
//		try {
//			Process exec = Runtime.getRuntime().exec(readLogs.toString());
//			
//			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
//			
//			int lineCnt = 0;
//			String str;
//			while ((str = bufferedReader.readLine()) != null) {
////				lineCnt++;
////				if(lineCnt> 100) {
////					break;
////				}
//			}
//			exec.destroy();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void saveLogs(String filePath) {
		File iFile = new File(filePath);
		File parentFile = iFile.getParentFile();
	
		if(!parentFile.exists()) {
			parentFile.mkdirs();
		}
		
		if (!iFile.exists()) {
			try {
				iFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(iFile);
			StringBuffer readLogs = new StringBuffer();
			StringBuffer clearLogs = new StringBuffer();
			readLogs.append("logcat -d -v time"); 
			clearLogs.append("logcat -c");
			try {
				Process exec = Runtime.getRuntime().exec(readLogs.toString());
				Runtime.getRuntime().exec(clearLogs.toString());
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));

				int lineCnt = 0;
				String str;
				while ((str = bufferedReader.readLine()) != null) {
					fos.write(str.getBytes());
					fos.write(System.getProperty("line.separator").getBytes());
					fos.flush();
					lineCnt++;
				}

				bufferedReader.close();
				exec.destroy();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(fos != null)
				try {
					fos.close();
				} catch (IOException e) {
				}
			fos = null;
		}

	}
}
