package com.hiworld.adapter;

import android.os.Parcel;
import android.os.Parcelable;

public class Golf7CarInfo implements Parcelable{

	//自启动以后page1
	private int m_trip1AverageFuelHigh;//平均油耗
	private int m_trip1AverageFuelLow;//平均油耗
	private int m_trip1cruisingDistanceHigh;//续航里程
	private int m_trip1cruisingDistanceLow;//续航里程
	private int m_trip1travelDistanceHigh;//行驶里程
	private int m_trip1travelDistanceLow;//行驶里程
	private int m_trip1TravelTimeHigh;//行驶时间
	private int m_trip1TravelTimeLow;//行驶时间
	private int m_trip1AverageSpeed;//平均车速
	
	//长时间page2
	private int m_trip2AverageFuelHigh;//平均油耗
	private int m_trip2AverageFuelLow;//平均油耗
	private int m_trip2cruisingDistanceHigh;//续航里程
	private int m_trip2cruisingDistanceLow;//续航里程
	private int m_trip2travelDistanceHigh;//行驶里程
	private int m_trip2travelDistanceLow;//行驶里程
	private int m_trip2TravelTimeHigh;//行驶时间
	private int m_trip2TravelTimeLow;//行驶时间
	private int m_trip2AverageSpeed;//平均车速
	
	//自加油起
	private int m_trip3AverageFuelHigh;//平均油耗
	private int m_trip3AverageFuelLow;//平均油耗
	private int m_trip3cruisingDistanceHigh;//续航里程
	private int m_trip3cruisingDistanceLow;//续航里程
	private int m_trip3travelDistanceHigh;//行驶里程
	private int m_trip3travelDistanceLow;//行驶里程
	private int m_trip3TravelTimeHigh;//行驶时间
	private int m_trip3TravelTimeLow;//行驶时间
	private int m_trip3AverageSpeed;//平均车速
	
	
	
	public static final Golf7CarInfo INSTANCE = new Golf7CarInfo();
	
	public static Golf7CarInfo getInstance(){
		return INSTANCE;
	}



	public int getM_trip1AverageFuelHigh() {
		return m_trip1AverageFuelHigh;
	}
	public void setM_trip1AverageFuelHigh(int m_trip1AverageFuelHigh) {
		this.m_trip1AverageFuelHigh = m_trip1AverageFuelHigh;
	}
	public int getM_trip1AverageFuelLow() {
		return m_trip1AverageFuelLow;
	}
	public void setM_trip1AverageFuelLow(int m_trip1AverageFuelLow) {
		this.m_trip1AverageFuelLow = m_trip1AverageFuelLow;
	}
	public int getM_trip1cruisingDistanceHigh() {
		return m_trip1cruisingDistanceHigh;
	}
	public void setM_trip1cruisingDistanceHigh(int m_trip1cruisingDistanceHigh) {
		this.m_trip1cruisingDistanceHigh = m_trip1cruisingDistanceHigh;
	}
	public int getM_trip1cruisingDistanceLow() {
		return m_trip1cruisingDistanceLow;
	}
	public void setM_trip1cruisingDistanceLow(int m_trip1cruisingDistanceLow) {
		this.m_trip1cruisingDistanceLow = m_trip1cruisingDistanceLow;
	}
	public int getM_trip1travelDistanceHigh() {
		return m_trip1travelDistanceHigh;
	}
	public void setM_trip1travelDistanceHigh(int m_trip1travelDistanceHigh) {
		this.m_trip1travelDistanceHigh = m_trip1travelDistanceHigh;
	}
	public int getM_trip1travelDistanceLow() {
		return m_trip1travelDistanceLow;
	}
	public void setM_trip1travelDistanceLow(int m_trip1travelDistanceLow) {
		this.m_trip1travelDistanceLow = m_trip1travelDistanceLow;
	}
	public int getM_trip1TravelTimeHigh() {
		return m_trip1TravelTimeHigh;
	}
	public void setM_trip1TravelTimeHigh(int m_trip1TravelTimeHigh) {
		this.m_trip1TravelTimeHigh = m_trip1TravelTimeHigh;
	}
	public int getM_trip1TravelTimeLow() {
		return m_trip1TravelTimeLow;
	}
	public void setM_trip1TravelTimeLow(int m_trip1TravelTimeLow) {
		this.m_trip1TravelTimeLow = m_trip1TravelTimeLow;
	}
	public int getM_trip1AverageSpeed() {
		return m_trip1AverageSpeed;
	}
	public void setM_trip1AverageSpeed(int m_trip1AverageSpeed) {
		this.m_trip1AverageSpeed = m_trip1AverageSpeed;
	}
	public int getM_trip2AverageFuelHigh() {
		return m_trip2AverageFuelHigh;
	}
	public void setM_trip2AverageFuelHigh(int m_trip2AverageFuelHigh) {
		this.m_trip2AverageFuelHigh = m_trip2AverageFuelHigh;
	}
	public int getM_trip2AverageFuelLow() {
		return m_trip2AverageFuelLow;
	}
	public void setM_trip2AverageFuelLow(int m_trip2AverageFuelLow) {
		this.m_trip2AverageFuelLow = m_trip2AverageFuelLow;
	}
	public int getM_trip2cruisingDistanceHigh() {
		return m_trip2cruisingDistanceHigh;
	}
	public void setM_trip2cruisingDistanceHigh(int m_trip2cruisingDistanceHigh) {
		this.m_trip2cruisingDistanceHigh = m_trip2cruisingDistanceHigh;
	}
	public int getM_trip2cruisingDistanceLow() {
		return m_trip2cruisingDistanceLow;
	}
	public void setM_trip2cruisingDistanceLow(int m_trip2cruisingDistanceLow) {
		this.m_trip2cruisingDistanceLow = m_trip2cruisingDistanceLow;
	}
	public int getM_trip2travelDistanceHigh() {
		return m_trip2travelDistanceHigh;
	}
	public void setM_trip2travelDistanceHigh(int m_trip2travelDistanceHigh) {
		this.m_trip2travelDistanceHigh = m_trip2travelDistanceHigh;
	}
	public int getM_trip2travelDistanceLow() {
		return m_trip2travelDistanceLow;
	}
	public void setM_trip2travelDistanceLow(int m_trip2travelDistanceLow) {
		this.m_trip2travelDistanceLow = m_trip2travelDistanceLow;
	}
	public int getM_trip2TravelTimeHigh() {
		return m_trip2TravelTimeHigh;
	}
	public void setM_trip2TravelTimeHigh(int m_trip2TravelTimeHigh) {
		this.m_trip2TravelTimeHigh = m_trip2TravelTimeHigh;
	}
	public int getM_trip2TravelTimeLow() {
		return m_trip2TravelTimeLow;
	}
	public void setM_trip2TravelTimeLow(int m_trip2TravelTimeLow) {
		this.m_trip2TravelTimeLow = m_trip2TravelTimeLow;
	}
	public int getM_trip2AverageSpeed() {
		return m_trip2AverageSpeed;
	}
	public void setM_trip2AverageSpeed(int m_trip2AverageSpeed) {
		this.m_trip2AverageSpeed = m_trip2AverageSpeed;
	}
	public int getM_trip3AverageFuelHigh() {
		return m_trip3AverageFuelHigh;
	}
	public void setM_trip3AverageFuelHigh(int m_trip3AverageFuelHigh) {
		this.m_trip3AverageFuelHigh = m_trip3AverageFuelHigh;
	}
	public int getM_trip3AverageFuelLow() {
		return m_trip3AverageFuelLow;
	}
	public void setM_trip3AverageFuelLow(int m_trip3AverageFuelLow) {
		this.m_trip3AverageFuelLow = m_trip3AverageFuelLow;
	}
	public int getM_trip3cruisingDistanceHigh() {
		return m_trip3cruisingDistanceHigh;
	}
	public void setM_trip3cruisingDistanceHigh(int m_trip3cruisingDistanceHigh) {
		this.m_trip3cruisingDistanceHigh = m_trip3cruisingDistanceHigh;
	}
	public int getM_trip3cruisingDistanceLow() {
		return m_trip3cruisingDistanceLow;
	}
	public void setM_trip3cruisingDistanceLow(int m_trip3cruisingDistanceLow) {
		this.m_trip3cruisingDistanceLow = m_trip3cruisingDistanceLow;
	}
	public int getM_trip3travelDistanceHigh() {
		return m_trip3travelDistanceHigh;
	}
	public void setM_trip3travelDistanceHigh(int m_trip3travelDistanceHigh) {
		this.m_trip3travelDistanceHigh = m_trip3travelDistanceHigh;
	}
	public int getM_trip3travelDistanceLow() {
		return m_trip3travelDistanceLow;
	}
	public void setM_trip3travelDistanceLow(int m_trip3travelDistanceLow) {
		this.m_trip3travelDistanceLow = m_trip3travelDistanceLow;
	}
	public int getM_trip3TravelTimeHigh() {
		return m_trip3TravelTimeHigh;
	}
	public void setM_trip3TravelTimeHigh(int m_trip3TravelTimeHigh) {
		this.m_trip3TravelTimeHigh = m_trip3TravelTimeHigh;
	}
	public int getM_trip3TravelTimeLow() {
		return m_trip3TravelTimeLow;
	}
	public void setM_trip3TravelTimeLow(int m_trip3TravelTimeLow) {
		this.m_trip3TravelTimeLow = m_trip3TravelTimeLow;
	}
	public int getM_trip3AverageSpeed() {
		return m_trip3AverageSpeed;
	}
	public void setM_trip3AverageSpeed(int m_trip3AverageSpeed) {
		this.m_trip3AverageSpeed = m_trip3AverageSpeed;
	}



	public static final Parcelable.Creator<Golf7CarInfo> CREATOR = new Creator<Golf7CarInfo>() {

		@Override
		public Golf7CarInfo createFromParcel(Parcel source) {
			
			Golf7CarInfo carInfo = new Golf7CarInfo();
			carInfo.setM_trip1AverageFuelHigh(source.readInt());
			carInfo.setM_trip1AverageFuelLow(source.readInt());
			carInfo.setM_trip1cruisingDistanceHigh(source.readInt());
			carInfo.setM_trip1cruisingDistanceLow(source.readInt());
			carInfo.setM_trip1travelDistanceHigh(source.readInt());
			carInfo.setM_trip1travelDistanceLow(source.readInt());
			carInfo.setM_trip1TravelTimeHigh(source.readInt());
			carInfo.setM_trip1TravelTimeLow(source.readInt());
			carInfo.setM_trip1AverageSpeed(source.readInt());
			
			carInfo.setM_trip2AverageFuelHigh(source.readInt());
			carInfo.setM_trip2AverageFuelLow(source.readInt());
			carInfo.setM_trip2cruisingDistanceHigh(source.readInt());
			carInfo.setM_trip2cruisingDistanceLow(source.readInt());
			carInfo.setM_trip2travelDistanceHigh(source.readInt());
			carInfo.setM_trip2travelDistanceLow(source.readInt());
			carInfo.setM_trip2TravelTimeHigh(source.readInt());
			carInfo.setM_trip2TravelTimeLow(source.readInt());
			carInfo.setM_trip2AverageSpeed(source.readInt());
			
			carInfo.setM_trip3AverageFuelHigh(source.readInt());
			carInfo.setM_trip3AverageFuelLow(source.readInt());
			carInfo.setM_trip3cruisingDistanceHigh(source.readInt());
			carInfo.setM_trip3cruisingDistanceLow(source.readInt());
			carInfo.setM_trip3travelDistanceHigh(source.readInt());
			carInfo.setM_trip3travelDistanceLow(source.readInt());
			carInfo.setM_trip3TravelTimeHigh(source.readInt());
			carInfo.setM_trip3TravelTimeLow(source.readInt());
			carInfo.setM_trip3AverageSpeed(source.readInt());
			return carInfo;
		}

		@Override
		public Golf7CarInfo[] newArray(int size) {
			
			return new Golf7CarInfo[size];
		}
		
	};
	@Override
	public int describeContents() {
		
		return 0;
	}
	@Override
	public void writeToParcel(Parcel out, int flags) {
		
		out.writeFloat(m_trip1AverageFuelHigh);
		out.writeFloat(m_trip1AverageFuelLow);
		out.writeInt(m_trip1cruisingDistanceHigh);
		out.writeInt(m_trip1cruisingDistanceLow);
		out.writeInt(m_trip1travelDistanceHigh);
		out.writeInt(m_trip1travelDistanceLow);
		out.writeInt(m_trip1TravelTimeHigh);
		out.writeInt(m_trip1TravelTimeLow);
		out.writeInt(m_trip1AverageSpeed);
		
		out.writeFloat(m_trip2AverageFuelHigh);
		out.writeFloat(m_trip2AverageFuelLow);
		out.writeInt(m_trip2cruisingDistanceHigh);
		out.writeInt(m_trip2cruisingDistanceLow);
		out.writeInt(m_trip2travelDistanceHigh);
		out.writeInt(m_trip2travelDistanceLow);
		out.writeInt(m_trip2TravelTimeHigh);
		out.writeInt(m_trip2TravelTimeLow);
		out.writeInt(m_trip2AverageSpeed);;
		
		out.writeFloat(m_trip3AverageFuelHigh);
		out.writeFloat(m_trip3AverageFuelLow);
		out.writeInt(m_trip3cruisingDistanceHigh);
		out.writeInt(m_trip3cruisingDistanceLow);
		out.writeInt(m_trip3travelDistanceHigh);
		out.writeInt(m_trip3travelDistanceLow);
		out.writeInt(m_trip3TravelTimeHigh);
		out.writeInt(m_trip3TravelTimeLow);
		out.writeInt(m_trip3AverageSpeed);
		
	}
	
}
