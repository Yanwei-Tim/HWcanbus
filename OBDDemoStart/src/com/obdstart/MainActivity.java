package com.obdstart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import com.tool.mailbox.MailBox;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
	}

	EditText tvInfo;

	private void initViews() {
		findViewById(R.id.btn_hide).setOnClickListener(mClick);
		findViewById(R.id.btn_show).setOnClickListener(mClick);
		findViewById(R.id.btn_sendmail).setOnClickListener(mClick);
		tvInfo = (EditText) findViewById(R.id.tv_text);
	}

	private OnClickListener mClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_hide: {
				sendShowSrv(false);
				break;
			}
			case R.id.btn_show: {
				sendShowSrv(true);
				break;
			}
			case R.id.btn_sendmail: {
				 sendMail();
				File filePath = Environment.getExternalStorageDirectory();
				System.out.println("file path " + filePath);
				File[] listFiles = filePath.listFiles();
				System.out.println("fils " + listFiles.length);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd-HH:mm:ss");
				Date date = new Date(System.currentTimeMillis());
				String time = format.format(date);
				String saveFilesPath = filePath.getAbsolutePath() + "/visslog/logs-" + time + ".txt";
				LogsSave.getObj().saveLogs(saveFilesPath);

				File iFile = new File(saveFilesPath);
				tvInfo.setText("");
				try {
					BufferedReader fReader = new BufferedReader(new FileReader(iFile));
					StringBuffer sb = new StringBuffer();
					String str;
					while ((str = fReader.readLine()) != null) {
						sb.append(str + "\n");
					}
					fReader.close();
					tvInfo.append(sb.toString());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				break;
			}
			default:
				break;
			}
		}

		String obdPKG = "ex.hiworld.obd";

		private void sendShowSrv(boolean b) {
			Intent ii = new Intent(obdPKG);
			Bundle bundle = new Bundle();
			bundle.putInt("SHOW", b ? 1 : 0);
			ii.putExtras(bundle);
			ii.setPackage(obdPKG);
			startService(ii);
		}
	};

	protected void sendMail() {
		sendMessage();
	}

	private void sendMessage() {
		System.out.println("send start");
		String user = "normanwill@126.com";
		String body = "body ........... khasdhkjsd";
		String subject = "subject ........... khkasdjkiuuqiueiuh12891782378182y3g12";
		String[] recipients = { "normanwill@126.com" };
		SendEmailAsyncTask email = new SendEmailAsyncTask();
		email.m = new MailBox("normanwill@126.com", "yanxuan2018");
		email.m.set_from(user);
		email.m.setBody(body);
		email.m.set_to(recipients);
		email.m.set_subject(subject);
		email.execute();
	}

	class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
		MailBox m;

		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				if (m.send()) {
					System.out.println(" send ok ");
				} else {
					System.out.println("Email failed to send.");
				}

				return true;
			} catch (AuthenticationFailedException e) {
				e.printStackTrace();
				System.out.println("Authentication failed.");
				return false;
			} catch (MessagingException e) {
				e.printStackTrace();
				System.out.println("Email failed to send.");
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Unexpected error occured.");
				return false;
			}
		}
	}
}
