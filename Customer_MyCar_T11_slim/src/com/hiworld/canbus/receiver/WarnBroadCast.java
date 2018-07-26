package com.hiworld.canbus.receiver;

import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.util.UtilityClass;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;

public class WarnBroadCast extends BroadcastReceiver {

	protected static final String TAG = "WarnBroadCast";
	private final static WarnBroadCast OBJ = new WarnBroadCast();
	private MediaPlayer mPlayer;

	public static boolean bInNavi = false;
	// private static SoundPool sp = null;// 声明一个SoundPool的引用
	// private static SparseArray<Integer> saSoundlist;// 声明一个HashMap来存放声音资源
	// private int currentStreamId;// 当前播放的StreamId
	// private static boolean isFinishedLoad = false;// 查看音乐文件是否加载完毕
	// private boolean isPausePlay = false;// 是否暂停播放
	// private static boolean isInit = false;
	private Context mContext;

	public static WarnBroadCast getOBJ() {
		return OBJ;
	}

	public void initSoundPool(Context context) {
		mContext = context;
		// System.out.println("+initSoundPool+");
		// sp = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);// 创建SoundPool对象
		// sp.setOnLoadCompleteListener(new OnLoadCompleteListener() {
		//
		// @Override
		// public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
		//
		// // System.out.println("setOnLoadCompleteListener");
		// }
		// });
		// saSoundlist = new SparseArray<Integer>();// 创建HashMap对象
		// for (int i = 0; i < ConstData.VOICE_WARN.length; i++) {
		// saSoundlist.put(i, sp.load(context, ConstData.VOICE_WARN[i], 0));
		// }
		// hm.put(1, sp.load(this, R.raw.bb, 0));
		// hm.put(2, sp.load(this, R.raw.aa, 0));//加载dudu声音文件并设置为1号文件放入hm
		// hm.put(3, sp.load(this, R.raw.left, 0));//加载musictest声音文件并设置为2号文件放入hm
		// System.out.println("-initSoundPool-");
	}

	public interface OnWarnTipListener {
		void updateWarnTips(int[] arrays);
	}

	OnWarnTipListener listener;
	private AudioManager mAudioMananger;

	public void setOnWarnTipListener(OnWarnTipListener ls) {
		this.listener = ls;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (ConstData.ACTION_VOICE_WARN.equals(action)) {
			int[] array = intent.getIntArrayExtra("warnArray");
			if (array.length == 3) {
				if (listener != null)
					listener.updateWarnTips(array);
				switch (array[0]) {
				case 0:// 车门
					if (array[2] == 1) {// 语音报警
						if (UtilityClass.getPrefrenceBoolean(mContext, "pcDoorWarn")) {
							if (array[1] == 0) {// 左前
								dmPlaySound(ConstData.VOICE_WARN[5]);
							} else if (array[1] == 1) {// 左后
								dmPlaySound(ConstData.VOICE_WARN[7]);
							} else if (array[1] == 2) {// 右前
								dmPlaySound(ConstData.VOICE_WARN[6]);
							} else if (array[1] == 3) {// 右后
								dmPlaySound(ConstData.VOICE_WARN[8]);
							}
						}
					}
					break;
				case 1:// 手刹
					if (array[2] == 1) {
						if (UtilityClass.getPrefrenceBoolean(mContext, "pcHandBrake")) {
							dmPlaySound(ConstData.VOICE_WARN[9]);
						}
					}
					break;
				case 2:// 安全带
					if (UtilityClass.getPrefrenceBoolean(mContext, "pcSafeBelt")) {
						if (array[2] == 1) {
							dmPlaySound(ConstData.VOICE_WARN[4]);
						}
					}
					break;
				case 3:// 后备箱
					if (UtilityClass.getPrefrenceBoolean(mContext, "pcDoorWarn")) {
						if (array[2] == 1) {
							dmPlaySound(ConstData.VOICE_WARN[13]);
						}
					}
					break;
				case 4:// 引擎盖
					if (UtilityClass.getPrefrenceBoolean(mContext, "pcDoorWarn")) {
						if (array[2] == 1) {
							dmPlaySound(ConstData.VOICE_WARN[14]);
						}
					}
					break;
				case 5:// 电池电压
					if (array[2] == 1) {
						// mPlayer = MediaPlayer.create(mContext,
						// ConstData.VOICE_WARN[4]);
						// mPlayer.start();
					}
					break;
				case 6:// 疲劳驾驶
					if (UtilityClass.getPrefrenceBoolean(mContext, "pcTiredDrive")) {
						if (array[2] == 1) {
							// mTvWarn.setText("您已连续驾驶超过四小时，请停车休息");
							dmPlaySound(ConstData.VOICE_WARN[12]);
						}
					}
					break;
				case 7:// 胎压
					if (array[2] == 1) {// 语音报警
						if (array[1] == 0) {// 左前
							dmPlaySound(ConstData.VOICE_WARN[0]);
						} else if (array[1] == 1) {// 左
							dmPlaySound(ConstData.VOICE_WARN[2]);
						} else if (array[1] == 2) {// 右前
							dmPlaySound(ConstData.VOICE_WARN[1]);
						} else if (array[1] == 3) {// 右后
							dmPlaySound(ConstData.VOICE_WARN[3]);
						}
					}
					break;
				case 8:// 冷却液温度
						// if (array[2] == 1) {
						// mPlayer = MediaPlayer.create(mContext,
						// ConstData.VOICE_WARN[11]);
						// mPlayer.start();
						// }
					break;
				case 9:// 剩余油量
					if (UtilityClass.getPrefrenceBoolean(mContext, "pcOilnWarn")) {
						if (array[2] == 1) {
							dmPlaySound(ConstData.VOICE_WARN[10]);
						}
					}
					break;
				case 10:
					if (UtilityClass.getPrefrenceBoolean(mContext, "pcTurnWarn")) {
						if (array[2] == 1) {
							dmPlaySound(ConstData.VOICE_WARN[15]);
						}
					}
					break;
				case 11:
					if (UtilityClass.getPrefrenceBoolean(mContext, "pcTurnWarn")) {
						if (array[2] == 1) {
							dmPlaySound(ConstData.VOICE_WARN[16]);
						}
					}
					break;
				default:
					break;
				}
			}
		}

	}

	public void dmPlaySound(int resid) {
		if (mAudioMananger == null)
			mAudioMananger = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);

		int result = mAudioMananger.requestAudioFocus(mAudioFocusChange, AudioManager.STREAM_MUSIC,
				AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
		if (result == AudioManager.AUDIOFOCUS_GAIN) {
			try {

				if (mPlayer != null && mPlayer.isPlaying()) {
					mPlayer.stop();
					mPlayer.release();
				}
				mPlayer = MediaPlayer.create(mContext, resid);
				mPlayer.setOnPreparedListener(mPrepareLis);
				mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						// 在播放完毕被回调
						mAudioMananger.abandonAudioFocus(mAudioFocusChange);
						Log.i(TAG, "setOnCompletionListener onCompletion");
					}
				});
				mPlayer.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private OnPreparedListener mPrepareLis = new MediaPlayer.OnPreparedListener() {
		@Override
		public void onPrepared(MediaPlayer mp) {
			Log.i(TAG, "onPrepared to start");
			mPlayer.start();
		}
	};
	
	private AudioManager.OnAudioFocusChangeListener mAudioFocusChange = new AudioManager.OnAudioFocusChangeListener() {
		@Override
		public void onAudioFocusChange(int focusChange) {
			switch (focusChange) {
			case AudioManager.AUDIOFOCUS_LOSS:
				// 长时间丢失焦点,当其他应用申请的焦点为AUDIOFOCUS_GAIN时，
				// 会触发此回调事件，例如播放QQ音乐，网易云音乐等
				// 通常需要暂停音乐播放，若没有暂停播放就会出现和其他音乐同时输出声音
				Log.d(TAG, "AUDIOFOCUS_LOSS");

				// 释放焦点，该方法可根据需要来决定是否调用
				// 若焦点释放掉之后，将不会再自动获得
				mAudioMananger.abandonAudioFocus(this);
				break;
			case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
				// 短暂性丢失焦点，当其他应用申请AUDIOFOCUS_GAIN_TRANSIENT或AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE时，
				// 会触发此回调事件，例如播放短视频，拨打电话等。
				// 通常需要暂停音乐播放

				Log.d(TAG, "AUDIOFOCUS_LOSS_TRANSIENT");
				break;
			case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
				// 短暂性丢失焦点并作降音处理
				Log.d(TAG, "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
				break;
			case AudioManager.AUDIOFOCUS_GAIN:
				// 当其他应用申请焦点之后又释放焦点会触发此回调
				// 可重新播放音乐
				Log.d(TAG, "AUDIOFOCUS_GAIN");
				break;
			}
		}
	};
}
