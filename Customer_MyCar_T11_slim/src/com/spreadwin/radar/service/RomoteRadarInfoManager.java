package com.spreadwin.radar.service;

import com.hiworld.canbus.util.LogUtils;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;


/**
 * Created by wangjie on 2017/8/16.
 */

public class RomoteRadarInfoManager {
	private static final String TAG = "RomoteRadarInfoManager";
    private Context mContext;
    private RemoteRadarInfo mRemoteDogInfo;

    public RomoteRadarInfoManager(Context context){
        this.mContext = context;
    }
    /**
     * 远程服务断开回调
     * 断开后重新绑定
     */
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            LogUtils.e(TAG,"binder died!");
            mRemoteDogInfo.asBinder().unlinkToDeath(mDeathRecipient,0);
            mRemoteDogInfo = null;
            bindRemoteService();
        }
    };


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.e(TAG,"远程服务 绑定成功");
            mRemoteDogInfo = RemoteRadarInfo.Stub.asInterface(service);
            try {
                mRemoteDogInfo.asBinder().linkToDeath(mDeathRecipient,0);
            }catch (RemoteException e){
                e.printStackTrace();
                LogUtils.e(TAG,"远程服务 异常");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.e(TAG,"远程服务解绑定");
        }
    };

    /**
     * 绑定远程服务
     */
    private void bindRemoteService(){
        LogUtils.e(TAG,"远程服务 开始绑定");
        Intent intent = new Intent();
        intent.setAction("com.spreadwin.radar.remote.info");
        intent.setPackage("com.spreadwin.radar");
        boolean isbind = mContext.bindService(intent,mConnection, Service.BIND_AUTO_CREATE);
        if(isbind){
            LogUtils.e(TAG,"isbind = true");
        }else{
            LogUtils.e(TAG,"isbind = false");
        }
    }

    /**
     * 远程服务解绑定
     */
    private void unBindRemoteService(){
        LogUtils.e(TAG,"远程服务 开始解绑");
        if(mRemoteDogInfo != null) {
            mContext.unbindService(mConnection);
            LogUtils.e(TAG,"远程服务 解绑成功");
        }
    }

    public void onCreate() {
        bindRemoteService();
    }

    public void onDestroy() {
        unBindRemoteService();
    }



    public int getEDogDistance(){
        int distance=0;
        if(mRemoteDogInfo != null){
            try {
               distance=mRemoteDogInfo.getEdogDistance();
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }
        return distance;
    }

    public PointEntity getEdogPointEntity(){
        PointEntity pointEntity=null;
        if(mRemoteDogInfo != null){
            try {
                pointEntity=mRemoteDogInfo.getPointEntity();
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }
        return pointEntity;
    }

}
