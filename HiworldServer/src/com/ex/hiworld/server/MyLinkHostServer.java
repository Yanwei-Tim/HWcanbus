package com.ex.hiworld.server;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import com.ex.hiworld.aidl.car.EventNotify;
import com.ex.hiworld.aidl.car.HandlerTaskCanbus;
import com.ex.hiworld.aidl.car.TaskCarRemote;
import com.ex.hiworld.server.canbus.DataCanbus;
import com.ex.hiworld.server.canbus.FinalCanbus;
import com.ex.hiworld.server.syu.DataHost;
import com.ex.hiworld.server.syu.FinalBt;
import com.ex.hiworld.server.syu.FinalMain;
import com.ex.hiworld.server.syu.FinalRadio;
import com.ex.hiworld.server.syu.FinalSyuModule;
import com.ex.hiworld.server.syu.SendFunc;
import com.ex.hiworld.server.tools.FormatSerialData;
import com.ex.hiworld.server.tools.FormatSerialData.OnHandlerListener;
import com.ex.hiworld.server.tools.LogsUtils;
import com.ex.hiworld.server.tools.PrintScreenView;
import com.ex.hiworld.server.tools.Utils;
import com.syu.remote.ConnEvent;
import com.syu.remote.Message;
import com.syu.remote.Remote;

import java.util.Locale;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by APP03 on 2018/6/8.
 */

public class MyLinkHostServer extends Service {

    private Remote autoTools;
    private Handler handler = new Handler(Looper.getMainLooper());

    public Remote getAutoTools() {
        return autoTools;
    }

    public static final int U_CANBUS_DATA = 1000 + 19; // get data from remote server (ps: from fyt server
    public static final int U_GPS_ANGLE = 1000 + 33;// GPS角度
  
    public static final int U_HOST_VOL = 2;// 音量值
    public static final int U_HOST_MUTE = 3;// 静音开关
    
    

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        autoTools = Remote.getAutoTools(this);
        SendFunc.setAutoTools(autoTools);
        LogsUtils.i("Srv onCreate");
        refrehLocalLanguage();
        registerLocaleReceiver();

        FormatSerialData.getObj().setOnHandlerListener(new OnHandlerListener() {
			@Override
			public void onHandler(int[] data, int start, int length) {
				if(data != null) {
					int[] newdata = new int[length];
					System.arraycopy(data, start, newdata, 0, length);
					parseData(newdata);
				}
			}
		});
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unregisterLocaleReceiver();
    }

    int[] idMainMsgs = new int[]{
            FinalMain.U_ACC_ON, FinalMain.U_APP_ID, FinalMain.U_PLAY_STATUS, FinalMain.U_BACKCAR
    };
    
    int[] idSoundMsgs = new int[]{
    		U_HOST_MUTE, U_HOST_VOL
    };

    int[] idCanbusMsgs = new int[]{
            U_CANBUS_DATA, U_GPS_ANGLE,FinalCanbus.U_CANBUS_VER
    };
    int[] idBtsMsgs = new int[]{
            FinalBt.U_PHONE_NUMBER, FinalBt.U_PHONE_STATE, FinalBt.U_BTAV_ID3_TITLE,
            FinalBt.U_BTAV_ID3_ARTIST, FinalBt.U_BTAV_ID3_ALBUM
    };
    int[] idRadioMsgs = new int[]{
            FinalRadio.U_BAND, FinalRadio.U_FREQ, FinalRadio.U_CHANNEL, FinalRadio.U_SEARCH_STATE,
            FinalRadio.U_SCAN
    };


    /**
     * 链接到车机服务，处理数据申请
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onConnected(ConnEvent event) {
        if ("conn".equals(event.type) && event.success) {//服务链接成共
            //关注机器声音模块的机器音量变化和静音状态。该操作能立刻获得当期状态，并在相关数据变化时获得消息通知
            LogsUtils.i("BaseCar onConnected");

            observableServer();
            // 对上
            handler.post(new ConnectClientServer());
        }
    }

    private void observableServer() {
        // fyt
        if (autoTools != null) {
            autoTools.observe(FinalSyuModule.MODULE_CODE_MAIN, idMainMsgs);
            autoTools.observe(FinalSyuModule.MODULE_CODE_CANBUS, idCanbusMsgs);
            autoTools.observe(FinalSyuModule.MODULE_CODE_RADIO, idRadioMsgs);
            autoTools.observe(FinalSyuModule.MODULE_CODE_BT, idBtsMsgs);
            autoTools.observe(FinalSyuModule.MODULE_CODE_SOUND, idSoundMsgs);
        }
    }


    /**
     * 处理通知
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageHandle(Message msg) {
        if (msg.module == FinalSyuModule.MODULE_CODE_MAIN) {
            switch (msg.code) {
                case FinalMain.U_ACC_ON:// msg.ints[0] 1为ACC开，0为ACC关
                    if (DataHost.sAccon != msg.ints[0]) {
                        DataHost.sAccon = msg.ints[0];
                        EventNotify.NE_ACCON.onNotify();
                    }
                    break;
                case FinalMain.U_APP_ID:
                    if (DataHost.sAppid != msg.ints[0]) {
                        DataHost.sAppid = msg.ints[0]; 
                        EventNotify.NE_APPID.onNotify();
                    }
                    break;
                case FinalMain.U_PLAY_STATUS:
                    parsePlayStatus(msg.ints, msg.strs);
                    break;
                case FinalMain.U_BACKCAR:
                    if (DataHost.sBackCar != msg.ints[0]) {
                        DataHost.sBackCar = msg.ints[0];
                        EventNotify.NE_BACKCAR.onNotify();
                    }
                    break;
            }
        } else if (msg.module == FinalSyuModule.MODULE_CODE_RADIO) {
            parseRadioData(msg.code, msg.ints);
        } else if (msg.module == FinalSyuModule.MODULE_CODE_BT) {
            parseBtData(msg.code, msg.ints, msg.strs);
        } else if (msg.module == FinalSyuModule.MODULE_CODE_SOUND) {
        	parseSoundData(msg.code, msg.ints, msg.strs);
        } else if (msg.module == FinalSyuModule.MODULE_CODE_CANBUS) {
            switch (msg.code) {
                case U_CANBUS_DATA:
                    if (msg.ints == null) break;
                    
                    FormatSerialData.getObj().onReceiver(msg.ints);
                    break;
                case U_GPS_ANGLE: {
                    if (DataHost.sGpsAngle != msg.ints[0]) {
                        DataHost.sGpsAngle = msg.ints[0];
                        EventNotify.NE_GPS_ANGLE.onNotify();
                    }
                    break;
                }
//                case FinalCanbus.U_CANBUS_VER:{ 
//                	if(msg.strs != null && msg.strs.length > 0) {
//	                	DataCanbus.sVersionCanbox = msg.strs[0] == null ? "" : msg.strs[0];
//	                	PrintScreenView.getMsgView().msg("get canbus ver from hostserver " + DataCanbus.sVersionCanbox);
//	                    HandlerTaskCanbus.update(FinalCanbus.U_CANBUS_VER, DataCanbus.sVersionCanbox, "");
//                	}
//                	break;
//                }
            }
        }
    }
    

    boolean isFullData = false;
    int[] tempData = new int[1024];
    int offset = 0; 

	private void parseData(int[] data) {
		if (data[3] == 0xF0) {
			String str = Utils.getStringFromInts(data, 4, data[2]);
			if (str != null && !DataCanbus.sVersionCanbox.equals(str)) {
				LogsUtils.i("canbus ver:" + str);
				PrintScreenView.getMsgView().msg(" canbus ver:  " + str);
				DataCanbus.sVersionCanbox = str;
				DataCanbus.canbusId = HandlerTaskCanbus.getCarTypeByVersion(str);
				HandlerTaskCanbus.update(FinalCanbus.U_CANBUS_VER, DataCanbus.sVersionCanbox, "");
				HandlerTaskCanbus.update(FinalCanbus.U_CANBUS_ID, DataCanbus.canbusId);
				EventNotify.NE_CANBUS_ID.onNotify();
			}
		} else {
			try {
				int[] datas = new int[data[2] + 2];
				System.arraycopy(data, 2, datas, 0, datas.length);
				HandlerTaskCanbus.parseCanbusData(datas);
			} catch (Exception e) {
				// LogsUtils.e("error At : " + e.getLocalizedMessage());
				e.printStackTrace();
				LogsUtils.e("error ?At : " + LogsUtils.toHexString(data));
			}
		}
		// }
	}

    private void parseBtData(int code, int[] ints, String[] strs) {
        switch (code) {
            case FinalBt.U_PHONE_NUMBER:
                if (strs != null) {
                    LogsUtils.i("bt number :" + strs[0]);
                    String num = strs[0];
                    if (strs[0] == null)
                        num = "";
                    if (!DataHost.sPhoneNum.equals(num)) {
                        DataHost.sPhoneNum = num;
                        EventNotify.NE_BT_PHONENUM.onNotify();
                    }
                }
                break;
            case FinalBt.U_PHONE_NAME: {
                if (strs != null) {
                    LogsUtils.i("bt U_PHONE_NAME :" + strs[0]);
                    String num = strs[0];
                    if (strs[0] == null)
                        num = "";
                    if (!DataHost.sPhoneContact.equals(num)) {
                        DataHost.sPhoneContact = num;
                        EventNotify.NE_BT_PHONENAME.onNotify();
                    }
                }
                break;
            }
            case FinalBt.U_PHONE_STATE:
                if (ints != null) {
//                    LogsUtils.i("bt U_PHONE_STATE :" + ints[0]);
                    if (DataHost.sPhoneSate != ints[0]) {
                        DataHost.sPhoneSate = ints[0];
                        EventNotify.NE_BT_STATE.onNotify();
                    }
                }
                break;
            case FinalBt.U_BTAV_ID3_TITLE:
                if (strs != null && DataHost.sAppid == FinalMain.APP_ID_BTAV) {// 仅在蓝牙音乐状态下更新信息
                    LogsUtils.i("bt U_BTAV_ID3_TITLE :" + strs[0]);
                    DataHost.sId3Title = strs[0] == null ? "" : strs[0];
                    EventNotify.NE_ID3_TITLE.onNotify();
                }
                break;
            case FinalBt.U_BTAV_ID3_ARTIST:
            case FinalBt.U_BTAV_ID3_ALBUM:
                break;
        }
    }

    private void parseRadioData(int code, int[] data) {
        switch (code) {
            case FinalRadio.U_BAND:
                if (DataHost.sRadioBand != data[0]) {
                    DataHost.sRadioBand = data[0];
                    if (DataHost.sRadioBand >= FinalRadio.CHANNEL_FM_INDEX_BEGIN && DataHost.sRadioBand <= FinalRadio.BAND_FM_INDEX_END) {
                        DataHost.isAM = false;
                    } else DataHost.isAM = true;
//                    LogsUtils.i("radio: U_BAND "  + " > " + Integer.toHexString(data[0]) + "  isAM :" + DataHost.isAM);
                    EventNotify.NE_RADIO_BAND.onNotify();
                }
                break;
            case FinalRadio.U_FREQ:
                if (DataHost.sRadioFreq != data[0]) {
                    DataHost.sRadioFreq = data[0];
//                    LogsUtils.i("radio: U_FREQ " + " > " + (data[0]));
                    EventNotify.NE_RADIO_FREQS.onNotify();
                }
                break;
            case FinalRadio.U_CHANNEL:
                DataHost.sRadioChannel = 0xFF & data[0];
//                LogsUtils.i("radio: U_CHANNEL "  + " > " + (DataHost.iRadioChannel+1));
                break;
            case FinalRadio.U_SCAN:
                DataHost.sRadioScan = data[0];
//                LogsUtils.i("radio: U_SCAN "  + " > " + (data[0]));
                break;
            case FinalRadio.U_SEARCH_STATE:
                DataHost.sRadioSearchState = data[0];
//                LogsUtils.i("radio: U_SEARCH_STATE "  + " > " + (data[0]));
                break;
        }
    }

    // PARAM: new int[]{APP_ID, TYPE} new String[]{value}
    // type:
    // 0:ID3_TITLE,
    // 1:ID3_ARTIST
    // 2:ID3_ALBUM
    // 3:PLAY_STATUS(ints[2]:PLAYER_CMD_XXX)
    // 4:曲目信息(ints[2]:curr;ints[3]:total 第一首为0)
    // 5:曲目播放时间信息(ints[2]:curr;ints[3]:total 单位秒)
    // 6:循环模式：0：不循环 1：单曲循环 2：全部循环
    // 7:随机模式：0：不随机 1：随机
    // 8:文件夹名称new int[]{APP_ID, TYPE} new String[]{value}
    // 9:播放设备类型0x01:USB 0x02:CARD 0x03:Other
    // 10:播放媒体类型0x01:音乐,0x02:视频,0x03:图片
    private void parsePlayStatus(int[] ints, String[] strs) {
        switch (ints[1]) {
            case 0:
                if (strs != null && !DataHost.sId3Title.equals(strs[0])) {
                    DataHost.sId3Title = strs[0];
                    EventNotify.NE_ID3_TITLE.onNotify();
                }
                break;
            case 1:
                if (strs != null && !DataHost.sId3Artist.equals(strs[0])) {
                    DataHost.sId3Artist = strs[0];
                    EventNotify.NE_ID3_ARTIST.onNotify();
                }
                break;
            case 2:
                if (strs != null && !DataHost.sId3Album.equals(strs[0])) {
                    DataHost.sId3Album = strs[0];
                    EventNotify.NE_ID3_ALBUM.onNotify();
                }
                break;
            case 3:
                DataHost.sPlayState = ints[2];
                break;
            case 4:
                DataHost.sCurPlayIndex = ints[2];
                DataHost.sPlayTotal = ints[3];
                break;
            case 5:
                if (DataHost.sCurPlayTime != ints[2]) {
                    DataHost.sCurPlayTime = ints[2];
                    DataHost.sTotalPlayTime = ints[3];
//                    EventNotify.NE_PLAY_TIME.onNotify();
                }
                break;
            case 6:
                DataHost.sPlayCycleMode = ints[2];
                break;
            case 7:
                DataHost.sPlayRandomMode = ints[2];
                break;
            case 8:
                break;
            case 9:
                DataHost.sPlayDeviceType = ints[2];
                break;
            case 10:
                DataHost.sPlayMediaType = ints[2];
                break;
        }
    }


	private void parseSoundData(int code, int[] ints, String[] strs) {
		switch (code) {
		case U_HOST_VOL: {
			if (ints != null && ints.length > 0) {
				if (DataHost.sVolDst != ints[0]) {
					DataHost.sVolDst = ints[0];
					EventNotify.NE_VOL_SRC.onNotify();
				}
			}
			break;
		}

		case U_HOST_MUTE: {
			if (ints != null && ints.length > 0) {
				if (DataHost.sMuteSrc != ints[0]) {
					DataHost.sMuteSrc = ints[0];
					EventNotify.NE_MUTE_SRC.onNotify();
				}
			}
			break;
		}
		default:
			break;
		}
	}

    public void refrehLocalLanguage() {
        Locale.setDefault(Locale.CHINA);
        Configuration cfg = getApplication().getResources().getConfiguration();
        String country = cfg.locale.getCountry();
        String lang = cfg.locale.getLanguage() + "-" + country;
        LogsUtils.i("当前语言： " + lang);
        parseLang(lang);
    }

    private void parseLang(String lang) {

        int ilang = FinalMain.LANG_EN;
        if (lang.startsWith("zh")) {
            ilang = FinalMain.LANG_ZH;
            if (lang.contains("TW"))
                ilang = FinalMain.LANG_TW;
        } else if (lang.startsWith("es")) {
            ilang = FinalMain.LANG_ES;
        } else if (lang.startsWith("tr")) {
            ilang = FinalMain.LANG_TR;
        } else if (lang.startsWith("it")) {
            ilang = FinalMain.LANG_IT;
        } else if (lang.startsWith("fr")) {
            ilang = FinalMain.LANG_FR;
        } else if (lang.startsWith("jp")) {
            ilang = FinalMain.LANG_JP;
        } else if (lang.startsWith("ko")) {
            ilang = FinalMain.LANG_KO;
        } else if (lang.startsWith("pl")) {
            ilang = FinalMain.LANG_PL;
        } else if (lang.startsWith("ru")) {
            ilang = FinalMain.LANG_RU;
        } else if (lang.startsWith("sv")) {
            ilang = FinalMain.LANG_SV;
        } else if (lang.startsWith("pt")) {
            ilang = FinalMain.LANG_PT;
        } else if (lang.startsWith("nb")) {
            ilang = FinalMain.LANG_NO;
        } else if (lang.startsWith("fi")) {
            ilang = FinalMain.LANG_FI;
        } else if (lang.startsWith("da")) {
            ilang = FinalMain.LANG_DA;
        } else if (lang.startsWith("ar")) {
            ilang = FinalMain.LANG_AR;
        } else if (lang.startsWith("nl")) {
            ilang = FinalMain.LANG_NL;
        }

        if (ilang != DataHost.sLang) {
            DataHost.sLang = ilang;
            EventNotify.NE_LANG.onNotify();
        }
    }


    void registerLocaleReceiver() {
        IntentFilter iif = new IntentFilter(Intent.ACTION_LOCALE_CHANGED);
        getApplication().registerReceiver(localeReceiver, iif);
    }

    void unregisterLocaleReceiver() {
        getApplication().unregisterReceiver(localeReceiver);
    }

    BroadcastReceiver localeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_LOCALE_CHANGED)) {
                LogsUtils.i("语言发生改变：：：:::: ");
                refrehLocalLanguage();
            }
        }
    };

}
