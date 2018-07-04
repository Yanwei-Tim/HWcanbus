package com.hiworld.adapter;

import android.os.Parcel;
import android.os.Parcelable;

public class HondaCarInfo implements Parcelable{

	//当前油耗、里程信息
	private int m_drivingMileageUnit;//续航里程单位
	private int m_InstantFuelUnit;//瞬时油耗单位
	private int m_tripDistanceUnit;//里程A及历史记录里程单位
	private int m_tripAverageFuelUnit;//里程A及历史记录的平均油耗单位
	private int m_averageFuelUnit;//本次驾驶的当前/历史平均油耗单位
	private int m_fuelRange;//油耗量程
	
	private int m_nowInstantFuel;//瞬时油耗
	private int m_nowAverageFuelHigh;//平均油耗高八位
	private int m_nowAverageFuelLow;//平均油耗低八位
	private int m_hisAverageFuelHigh;//历史平均油耗高八位
	private int m_hisAverageFuelLow;//历史平均油耗低八位
	private int m_drivingMileageHigh;//续航里程高八位
	private int m_drivingMileageLow;//续航里程低八位
	
	//历史油耗、里程信息
	private int m_tripAdistanceHigh;
	private int m_tripAdistanceMedium;
	private int m_tripAdistanceLow;
	private int m_tripAaveFuelHigh;
	private int m_tripAaveFuelLow;
	
	private int m_trip1distanceHigh;
	private int m_trip1distanceMedium;
	private int m_trip1distanceLow;
	private int m_trip1aveFuelHigh;
	private int m_trip1aveFuelLow;
	
	private int m_trip2distanceHigh;
	private int m_trip2distanceMedium;
	private int m_trip2distanceLow;
	private int m_trip2aveFuelHigh;
	private int m_trip2aveFuelLow;
	
	private int m_trip3distanceHigh;
	private int m_trip3distanceMedium;
	private int m_trip3distanceLow;
	private int m_trip3aveFuelHigh;
	private int m_trip3aveFuelLow;
	
	public static final HondaCarInfo INSTANCE = new HondaCarInfo();
	
	public static HondaCarInfo getInstance(){
		return INSTANCE;
	}

	private static final Parcelable.Creator<HondaCarInfo> CREATOR = new Creator<HondaCarInfo>() {

		@Override
		public HondaCarInfo createFromParcel(Parcel source) {
			
			HondaCarInfo carInfo = new HondaCarInfo();
			carInfo.setM_drivingMileageUnit(source.readInt());
			carInfo.setM_InstantFuelUnit(source.readInt());
			carInfo.setM_nowInstantFuel(source.readInt());
			carInfo.setM_nowAverageFuelHigh(source.readInt());
			carInfo.setM_nowAverageFuelLow(source.readInt());
			carInfo.setM_hisAverageFuelHigh(source.readInt());
			carInfo.setM_hisAverageFuelLow(source.readInt());
			carInfo.setM_drivingMileageHigh(source.readInt());
			carInfo.setM_drivingMileageLow(source.readInt());
			carInfo.setM_tripAdistanceHigh(source.readInt());
			carInfo.setM_tripAdistanceMedium(source.readInt());
			carInfo.setM_tripAdistanceLow(source.readInt());
			carInfo.setM_tripAaveFuelHigh(source.readInt());
			carInfo.setM_tripAaveFuelLow(source.readInt());
			carInfo.setM_trip1distanceHigh(source.readInt());
			carInfo.setM_trip1distanceMedium(source.readInt());
			carInfo.setM_trip1distanceLow(source.readInt());
			carInfo.setM_trip1aveFuelHigh(source.readInt());
			carInfo.setM_trip1aveFuelLow(source.readInt());
			carInfo.setM_trip2distanceHigh(source.readInt());
			carInfo.setM_trip2distanceMedium(source.readInt());
			carInfo.setM_trip2distanceLow(source.readInt());
			carInfo.setM_trip2aveFuelHigh(source.readInt());
			carInfo.setM_trip2aveFuelLow(source.readInt());
			carInfo.setM_trip3distanceHigh(source.readInt());
			carInfo.setM_trip3distanceMedium(source.readInt());
			carInfo.setM_trip3distanceLow(source.readInt());
			carInfo.setM_trip3aveFuelHigh(source.readInt());
			carInfo.setM_trip3aveFuelLow(source.readInt());
			carInfo.setM_tripDistanceUnit(source.readInt());
			carInfo.setM_tripAverageFuelUnit(source.readInt());
			carInfo.setM_averageFuelUnit(source.readInt());
			carInfo.setM_fuelRange(source.readInt());
			return carInfo;
		}

		@Override
		public HondaCarInfo[] newArray(int size) {
			
			return new HondaCarInfo[size];
		}
	};
	
	
	public int getM_drivingMileageUnit() {
		return m_drivingMileageUnit;
	}
	public void setM_drivingMileageUnit(int m_drivingMileageUnit) {
		this.m_drivingMileageUnit = m_drivingMileageUnit;
	}
	public int getM_InstantFuelUnit() {
		return m_InstantFuelUnit;
	}
	public void setM_InstantFuelUnit(int m_InstantFuelUnit) {
		this.m_InstantFuelUnit = m_InstantFuelUnit;
	}
	public int getM_nowInstantFuel() {
		return m_nowInstantFuel;
	}
	public void setM_nowInstantFuel(int m_nowInstantFuel) {
		this.m_nowInstantFuel = m_nowInstantFuel;
	}
	public int getM_nowAverageFuelHigh() {
		return m_nowAverageFuelHigh;
	}
	public void setM_nowAverageFuelHigh(int m_nowAverageFuelHigh) {
		this.m_nowAverageFuelHigh = m_nowAverageFuelHigh;
	}
	public int getM_nowAverageFuelLow() {
		return m_nowAverageFuelLow;
	}
	public void setM_nowAverageFuelLow(int m_nowAverageFuelLow) {
		this.m_nowAverageFuelLow = m_nowAverageFuelLow;
	}
	public int getM_hisAverageFuelHigh() {
		return m_hisAverageFuelHigh;
	}
	public void setM_hisAverageFuelHigh(int m_hisAverageFuelHigh) {
		this.m_hisAverageFuelHigh = m_hisAverageFuelHigh;
	}
	public int getM_hisAverageFuelLow() {
		return m_hisAverageFuelLow;
	}
	public void setM_hisAverageFuelLow(int m_hisAverageFuelLow) {
		this.m_hisAverageFuelLow = m_hisAverageFuelLow;
	}
	public int getM_drivingMileageHigh() {
		return m_drivingMileageHigh;
	}
	public void setM_drivingMileageHigh(int m_drivingMileageHigh) {
		this.m_drivingMileageHigh = m_drivingMileageHigh;
	}
	public int getM_drivingMileageLow() {
		return m_drivingMileageLow;
	}
	public void setM_drivingMileageLow(int m_drivingMileageLow) {
		this.m_drivingMileageLow = m_drivingMileageLow;
	}
	public int getM_tripAdistanceHigh() {
		return m_tripAdistanceHigh;
	}
	public void setM_tripAdistanceHigh(int m_tripAdistanceHigh) {
		this.m_tripAdistanceHigh = m_tripAdistanceHigh;
	}
	public int getM_tripAdistanceMedium() {
		return m_tripAdistanceMedium;
	}
	public void setM_tripAdistanceMedium(int m_tripAdistanceMedium) {
		this.m_tripAdistanceMedium = m_tripAdistanceMedium;
	}
	public int getM_tripAdistanceLow() {
		return m_tripAdistanceLow;
	}
	public void setM_tripAdistanceLow(int m_tripAdistanceLow) {
		this.m_tripAdistanceLow = m_tripAdistanceLow;
	}
	public int getM_tripAaveFuelHigh() {
		return m_tripAaveFuelHigh;
	}
	public void setM_tripAaveFuelHigh(int m_tripAaveFuelHigh) {
		this.m_tripAaveFuelHigh = m_tripAaveFuelHigh;
	}
	public int getM_tripAaveFuelLow() {
		return m_tripAaveFuelLow;
	}
	public void setM_tripAaveFuelLow(int m_tripAaveFuelLow) {
		this.m_tripAaveFuelLow = m_tripAaveFuelLow;
	}
	public int getM_trip1distanceHigh() {
		return m_trip1distanceHigh;
	}
	public void setM_trip1distanceHigh(int m_trip1distanceHigh) {
		this.m_trip1distanceHigh = m_trip1distanceHigh;
	}
	public int getM_trip1distanceMedium() {
		return m_trip1distanceMedium;
	}
	public void setM_trip1distanceMedium(int m_trip1distanceMedium) {
		this.m_trip1distanceMedium = m_trip1distanceMedium;
	}
	public int getM_trip1distanceLow() {
		return m_trip1distanceLow;
	}
	public void setM_trip1distanceLow(int m_trip1distanceLow) {
		this.m_trip1distanceLow = m_trip1distanceLow;
	}
	public int getM_trip1aveFuelHigh() {
		return m_trip1aveFuelHigh;
	}
	public void setM_trip1aveFuelHigh(int m_trip1aveFuelHigh) {
		this.m_trip1aveFuelHigh = m_trip1aveFuelHigh;
	}
	public int getM_trip1aveFuelLow() {
		return m_trip1aveFuelLow;
	}
	public void setM_trip1aveFuelLow(int m_trip1aveFuelLow) {
		this.m_trip1aveFuelLow = m_trip1aveFuelLow;
	}
	public int getM_trip2distanceHigh() {
		return m_trip2distanceHigh;
	}
	public void setM_trip2distanceHigh(int m_trip2distanceHigh) {
		this.m_trip2distanceHigh = m_trip2distanceHigh;
	}
	public int getM_trip2distanceMedium() {
		return m_trip2distanceMedium;
	}
	public void setM_trip2distanceMedium(int m_trip2distanceMedium) {
		this.m_trip2distanceMedium = m_trip2distanceMedium;
	}
	public int getM_trip2distanceLow() {
		return m_trip2distanceLow;
	}
	public void setM_trip2distanceLow(int m_trip2distanceLow) {
		this.m_trip2distanceLow = m_trip2distanceLow;
	}
	public int getM_trip2aveFuelHigh() {
		return m_trip2aveFuelHigh;
	}
	public void setM_trip2aveFuelHigh(int m_trip2aveFuelHigh) {
		this.m_trip2aveFuelHigh = m_trip2aveFuelHigh;
	}
	public int getM_trip2aveFuelLow() {
		return m_trip2aveFuelLow;
	}
	public void setM_trip2aveFuelLow(int m_trip2aveFuelLow) {
		this.m_trip2aveFuelLow = m_trip2aveFuelLow;
	}
	public int getM_trip3distanceHigh() {
		return m_trip3distanceHigh;
	}
	public void setM_trip3distanceHigh(int m_trip3distanceHigh) {
		this.m_trip3distanceHigh = m_trip3distanceHigh;
	}
	public int getM_trip3distanceMedium() {
		return m_trip3distanceMedium;
	}
	public void setM_trip3distanceMedium(int m_trip3distanceMedium) {
		this.m_trip3distanceMedium = m_trip3distanceMedium;
	}
	public int getM_trip3distanceLow() {
		return m_trip3distanceLow;
	}
	public void setM_trip3distanceLow(int m_trip3distanceLow) {
		this.m_trip3distanceLow = m_trip3distanceLow;
	}
	public int getM_trip3aveFuelHigh() {
		return m_trip3aveFuelHigh;
	}
	public void setM_trip3aveFuelHigh(int m_trip3aveFuelHigh) {
		this.m_trip3aveFuelHigh = m_trip3aveFuelHigh;
	}
	public int getM_trip3aveFuelLow() {
		return m_trip3aveFuelLow;
	}
	public void setM_trip3aveFuelLow(int m_trip3aveFuelLow) {
		this.m_trip3aveFuelLow = m_trip3aveFuelLow;
	}
	public int getM_tripDistanceUnit() {
		return m_tripDistanceUnit;
	}
	public void setM_tripDistanceUnit(int m_tripDistanceUnit) {
		this.m_tripDistanceUnit = m_tripDistanceUnit;
	}
	public int getM_tripAverageFuelUnit() {
		return m_tripAverageFuelUnit;
	}
	public void setM_tripAverageFuelUnit(int m_tripAverageFuelUnit) {
		this.m_tripAverageFuelUnit = m_tripAverageFuelUnit;
	}
	public int getM_averageFuelUnit() {
		return m_averageFuelUnit;
	}
	public void setM_averageFuelUnit(int m_averageFuelUnit) {
		this.m_averageFuelUnit = m_averageFuelUnit;
	}
	public int getM_fuelRange() {
		return m_fuelRange;
	}
	public void setM_fuelRange(int m_fuelRange) {
		this.m_fuelRange = m_fuelRange;
	}
	@Override
	public int describeContents() {
		
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel out, int flags) {
		
		
		out.writeInt(m_drivingMileageUnit);
		out.writeInt(m_InstantFuelUnit);
		out.writeInt(m_nowInstantFuel);
		out.writeInt(m_nowAverageFuelHigh);
		out.writeInt(m_nowAverageFuelLow);
		out.writeInt(m_hisAverageFuelHigh);
		out.writeInt(m_hisAverageFuelLow);
		out.writeInt(m_drivingMileageHigh);
		out.writeInt(m_drivingMileageLow);
		out.writeInt(m_tripAdistanceHigh);
		out.writeInt(m_tripAdistanceMedium);
		out.writeInt(m_tripAdistanceLow);
		out.writeInt(m_tripAaveFuelHigh);
		out.writeInt(m_tripAaveFuelLow);
		out.writeInt(m_trip1distanceHigh);
		out.writeInt(m_trip1distanceMedium);
		out.writeInt(m_trip1distanceLow);
		out.writeInt(m_trip1aveFuelHigh);
		out.writeInt(m_trip1aveFuelLow);
		out.writeInt(m_trip2distanceHigh);
		out.writeInt(m_trip2distanceMedium);
		out.writeInt(m_trip2distanceLow);
		out.writeInt(m_trip2aveFuelHigh);
		out.writeInt(m_trip2aveFuelLow);
		out.writeInt(m_trip3distanceHigh);
		out.writeInt(m_trip3distanceMedium);
		out.writeInt(m_trip3distanceLow);
		out.writeInt(m_trip3aveFuelHigh);
		out.writeInt(m_trip3aveFuelLow);
		out.writeInt(m_tripDistanceUnit);
		out.writeInt(m_tripAverageFuelUnit);
		out.writeInt(m_averageFuelUnit);
		out.writeInt(m_fuelRange);
	}

}
