package com.spreadwin.radar.service;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Administrator Kevin
 * 
 */
public class PointEntity implements Serializable ,Comparable<PointEntity>,Parcelable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String id;
	public String latitude;
	public String longitude;
	public String direction;
	public String type;
	public String typeMeaning;
	public String speed;
	public String mDirection;
	public String gps2d;
	public double distance;
	public String imei;
	public int pType;//是否本地采集的电子狗数据,1:bendi 0:luke 2:anan
	public int play_distance;
	
	public PointEntity(){
		this.id = "";
		this.latitude = "";
		this.longitude = "";
		this.direction = "";
		this.type = "";
		this.typeMeaning = "";
		this.speed = "";
		this.mDirection = "";
		this.gps2d = "";
		this.distance = 0;
		this.imei = "";
		this.pType = 0;
		this.play_distance = 0;
	}
	
	@Override
	public String toString() {
		return "电子狗纬度:" + latitude + ",电子狗经度" + longitude + "\n 电子狗类型:" + type//
				+ ",电子狗限速:" + speed + "\n 电子狗方位角:" + direction + ",查询时我的方位角:"//
				+ mDirection + "\n 两点方位角:" + gps2d + "\n s经纬度:"//
				+ "\n两点距离:" + distance +"\n imei:"+imei;//
	}
	
	@Override
	public boolean equals(Object o) {
		PointEntity pe = (PointEntity)o;
		if(this.latitude == pe.latitude && this.longitude == pe.longitude)
			return true;
		return false;
	}

	@Override
	public int compareTo(PointEntity pe) {
		return this.distance - pe.distance> 0 ? 1: -1;
	}

	@Override
	public int describeContents() {
		
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeString(id);
		dest.writeString(latitude);
		dest.writeString(longitude);
		dest.writeString(direction);
		dest.writeString(type);
		dest.writeString(typeMeaning);
		dest.writeString(speed);
		dest.writeString(mDirection);
		dest.writeString(gps2d);
		dest.writeDouble(distance);
		dest.writeString(imei);
		dest.writeInt(pType);
		dest.writeInt(play_distance);
	}
	
	public static final Creator<PointEntity> CREATOR = new Creator<PointEntity>() {
		
		@Override
		public PointEntity[] newArray(int size) {
			
			return new PointEntity[size];
		}
		
		@Override
		public PointEntity createFromParcel(Parcel source) {
			
			return new PointEntity(source);
		}
	};

	private PointEntity(Parcel in){
		id = in.readString();
		latitude = in.readString();
		longitude = in.readString();
		direction = in.readString();
		type = in.readString();
		typeMeaning = in.readString();
		speed = in.readString();
		mDirection = in.readString();
		gps2d = in.readString();
		distance = in.readDouble();
		imei = in.readString();
		pType = in.readInt();
		play_distance = in.readInt();
	}
}
