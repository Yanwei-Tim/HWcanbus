package com.hiworld.canbus.activity;

import java.util.HashMap;

import com.hiworld.canbus.util.ConstData;
import com.hiworld.canbus.util.UtilityClass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;


@Deprecated
public class WarnBroadcastReceiver extends BroadcastReceiver{

	private static WarnBroadcastReceiver INSTANCE = new WarnBroadcastReceiver();
	
	public static WarnBroadcastReceiver getInstance(){
		return INSTANCE;
	}
	public static boolean bInNavi = false;
	//private MediaPlayer mPlayer;
	
	private static SoundPool sp=null;//声明一个SoundPool的引用  
    private static HashMap <Integer,Integer> hm;//声明一个HashMap来存放声音资源  
    private int currentStreamId;//当前播放的StreamId  
    private static boolean isFinishedLoad=false;//查看音乐文件是否加载完毕  
    private boolean isPausePlay=false;//是否暂停播放  
    private static boolean isInit = false;
    
	@Override
	public void onReceive(Context context, Intent intent) {
		
		String action = intent.getAction();
		System.out.println("ble=======action"+action);
		
		if (ConstData.ACTION_VOICE_WARN.equals(action)) {
			
			int[] array = intent.getIntArrayExtra("warnArray");
			if (array.length == 3) {
				switch (array[0]) {
				case 0://车门
					if (array[2] == 1) {//语音报警
						System.out.println("=======action"+"车门");
						if (UtilityClass.getPrefrenceBoolean(context, "pcDoorWarn")) {
							if (array[1] == 0) {//左前
								playSound(5, 0,context);
								System.out.println("=======action"+"左前");
//								mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[5]);
//								mPlayer.start();
							}else if (array[1] == 1) {//左后
								playSound(7, 0,context);
//								mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[7]);
//								mPlayer.start();
							}else if (array[1] == 2) {//右前
								playSound(6, 0,context);
//								mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[6]);
//								mPlayer.start();
							}else if (array[1] == 3) {//右后
								playSound(8, 0,context);
//								mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[8]);
//								mPlayer.start();
							}
						}
					}
					break;
				case 1://手刹
					if (array[2] == 1) {
						if (UtilityClass.getPrefrenceBoolean(context, "pcHandBrake")) {
							playSound(9, 0,context);
//							mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[9]);
//							mPlayer.start();
						}
					}
					break;
				case 2://安全带
					if (UtilityClass.getPrefrenceBoolean(context, "pcSafeBelt")) {
						
						if (array[2] == 1) {
							playSound(4, 0,context);
//							mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[4]);
//							mPlayer.start();
						}
					}
					break;
				case 3://后备箱
					if (UtilityClass.getPrefrenceBoolean(context, "pcDoorWarn")) {
						if (array[2] == 1) {
							playSound(13, 0,context);
//							mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[13]);
//							mPlayer.start();
						}
					}
					break;
				case 4://引擎盖
					if (UtilityClass.getPrefrenceBoolean(context, "pcDoorWarn")) {
						if (array[2] == 1) {
							playSound(14, 0,context);
//							mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[14]);
//							mPlayer.start();
						}
					}
					break;
				case 5://电池电压
					if (array[2] == 1) {
//						mPlayer = MediaPlayer.create(MainActivity.this, ConstData.VOICE_WARN[4]);
//						mPlayer.start();
					}
					break;
				case 6://疲劳驾驶
					if (UtilityClass.getPrefrenceBoolean(context, "pcTiredDrive")) {
						if (array[2] == 1) {
//							mTvWarn.setText("您已连续驾驶超过四小时，请停车休息");
//							mTvWarn.setVisibility(View.VISIBLE);
							playSound(12, 0,context);
//							mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[12]);
//							mPlayer.start();
						}
					}
					break;
				case 7://胎压
					if (array[2] == 1) {//语音报警
						if (array[1] == 0) {//左前
							playSound(0, 0,context);
//							mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[0]);
//							mPlayer.start();
						}else if (array[1] == 1) {//左
							playSound(2, 0,context);
//							mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[2]);
//							mPlayer.start();
						}else if (array[1] == 2) {//右前
							playSound(1, 0,context);
//							mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[1]);
//							mPlayer.start();
						}else if (array[1] == 3) {//右后
							playSound(3, 0,context);
//							mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[3]);
//							mPlayer.start();
						}
					}
					break;
				case 8://冷却液温度
//					if (array[2] == 1) {
//						mPlayer = MediaPlayer.create(MainActivity.this, ConstData.VOICE_WARN[11]);
//						mPlayer.start();
//					}
					break;
				case 9://剩余油量
					if (UtilityClass.getPrefrenceBoolean(context, "pcOilnWarn")) {
						if (array[2] == 1) {
							playSound(10, 0,context);
//							mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[10]);
//							mPlayer.start();
						}
					}
					break;
				case 10:
					if (UtilityClass.getPrefrenceBoolean(context, "pcTurnWarn")) {
						if (array[2] == 1) {
							playSound(15, 0,context);
//							mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[15]);
//							mPlayer.start();
						}
					}
					break;
				case 11:
					if (UtilityClass.getPrefrenceBoolean(context, "pcTurnWarn")) {
						if (array[2] == 1) {
							playSound(16, 0,context);
//							mPlayer = MediaPlayer.create(context, ConstData.VOICE_WARN[16]);
//							mPlayer.start();
						}
					}
					break;
				default:
					break;
				}
			}
		}else if (ConstData.ACTION_GAODE_INFO.equals(action)) {
			int KEY_TYPE = intent.getIntExtra("KEY_TYPE", 0);
			if (KEY_TYPE == 10019) {//导航状态通知
				int mState = intent.getIntExtra("EXTRA_STATE", -1);
				if (mState == 8 || mState == 10) {
					bInNavi = true;
				}else if (mState == 9 || mState == 12){
					bInNavi = false;
					//mTvNaviRoad.setText("本次导航结束");
				}else if (mState == 2) {
					bInNavi = false;
					//mTvNaviRoad.setText("本次导航结束");
				}
			}
		}
//		else if (BlueUtilData.ACTION_GATT_CONNECTED.equals(action)) {
//			//收到连接蓝牙也初始化
//			init(context);
//		}
	}
	
	public void init(Context context){
		if (!isInit) {
			initSoundPool(context);
		}
	}
	
//	Handler mHandler = new Handler(){
//
//		@Override
//		public void handleMessage(Message msg) {
//			
//			super.handleMessage(msg);
//			switch (msg.what) {
//			case 1:
//				if (isInit) {
//					release();
//				}
//				break;
//			default:
//				break;
//			}
//		}
//		
//	};
	
	public void initSoundPool(Context context){  
        System.out.println("+initSoundPool+");  
        sp=new SoundPool(4,AudioManager.STREAM_MUSIC,0);//创建SoundPool对象  
        sp.setOnLoadCompleteListener(new OnLoadCompleteListener() {  
              
            @Override  
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {  
                  
                isFinishedLoad=true;  
                System.out.println("setOnLoadCompleteListener");  
            }  
        });  
        hm=new HashMap<Integer,Integer>();//创建HashMap对象  
        for (int i = 0; i < ConstData.VOICE_WARN.length; i++) {
        	hm.put(i, sp.load(context, ConstData.VOICE_WARN[i], 0));
		}
//        hm.put(1, sp.load(this, R.raw.bb, 0));
//        hm.put(2, sp.load(this, R.raw.aa, 0));//加载dudu声音文件并设置为1号文件放入hm  
//        hm.put(3, sp.load(this, R.raw.left, 0));//加载musictest声音文件并设置为2号文件放入hm  
        System.out.println("-initSoundPool-");  
        isInit = true;
    }
	
	//sound hm中的第几个歌曲  
    //loop 是否循环 0不循环 -1循环  
    public void playSound(int sound,int loop,Context context){  
        //String log;  
        if(!isPausePlay){  
//            AudioManager am=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);  
 //           float currentStreamVolume=am.getStreamVolume(AudioManager.STREAM_MUSIC);  
//            float maxStreamVolume=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);  
//            float setVolume=(float)currentStreamVolume/maxStreamVolume;  
            if(isFinishedLoad){
            	
            	if (sp != null && hm.size() > 0) {
            		currentStreamId=sp.play(hm.get(sound), 1.0f, 1.0f, 1, loop, 1.0f);
            		System.out.println("==========playSound播放");
				}else {
					System.out.println("==========playSound空");
				}
            }else {
				System.out.println("========playSound未播放");
			}  
            //log="playSound currentStreamId:"+String.valueOf(currentStreamId);  
            //System.out.println(log);  
        }  
        else{  
            isPausePlay=false;  
            sp.resume(currentStreamId);  
        }  
    }
    
    public void release(){
    	if (sp != null) {
			sp.unload(currentStreamId);  
	        sp.release();  
	        isInit = false;
	        
	        if (hm.size() > 0) {
	        	hm.clear();
			}
	        hm = null;
	        isFinishedLoad = false;
	        isPausePlay = false;
	        sp = null;
		}
    }

}
