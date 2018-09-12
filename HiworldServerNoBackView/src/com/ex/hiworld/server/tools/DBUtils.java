package com.ex.hiworld.server.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ex.hiworld.server.R;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.enums.AssignType;

import android.content.Context;
import android.os.Environment;

public class DBUtils {

	static LiteOrm mLiteOrm;
	public static final String DB_NAME = "hwcartype.db";
	public static String DB_PATH = "";

	static DBUtils instance = new DBUtils();

	public static DBUtils getInstance() {
		return instance;
	}

	public void init(Context context) {
		DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + context.getPackageName() + "/"
				+ DB_NAME;
		writeDB(context);
		mLiteOrm = LiteOrm.newCascadeInstance(context, DB_PATH);
	}

	static boolean isStoreDb = false;
	public void writeDB(Context context) {
		FileOutputStream fout = null;
		InputStream inputStream = null;
		try {
			LogsUtils.i("writeDB start!");
			inputStream = context.getResources().openRawResource(R.raw.hwcartype);
			fout = new FileOutputStream(new File(DB_PATH));
			byte[] buffer = new byte[128];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				fout.write(buffer, 0, len);
			}
			buffer = null;
			LogsUtils.i("writeDB ok!");
			isStoreDb = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public List<CarVersion> query(String keyword) {
		if (mLiteOrm != null) {
			return mLiteOrm
					.query(new QueryBuilder<CarVersion>(CarVersion.class).where("version like ?", "%" + keyword + "%"));
		}
		return null;
	}

	public List<CarVersion> queryAll() {
		if (mLiteOrm != null) {
			return mLiteOrm.query(CarVersion.class);
		}

		return null;
	}

	// 表格式
	@Table("car")
	public static class CarVersion {

		// 指定自增，每个对象需要有一个主键
		@PrimaryKey(AssignType.AUTO_INCREMENT)
		private int id;

		// 非空字段
		@NotNull
		private String version;

		// 忽略字段，将不存储到数据库
		// @Ignore
		private String desc;
		private int data1;
		private int data2;
		private int carid;
		private int caridoffset;

		// // 默认为true，指定列名
		// @Default("true")
		// @Column("login")
		// private Boolean isLogin;

		public String getDesc() {
			return desc;
		}

		public String getVersion() {
			return version;
		}

		public int getData1() {
			return data1;
		}

		public int getData2() {
			return data2;
		}

		public int getCarid() {
			return carid;
		}

		public int getCaridoffset() {
			return caridoffset;
		}

		@Override
		public String toString() {
			return "CarVersion [version=" + version + ", desc=" + desc + ", data1=" + data1 + ", data2=" + data2
					+ ", carid=" + carid + ", caridoffset=" + caridoffset + "]";
		}

	}
}
