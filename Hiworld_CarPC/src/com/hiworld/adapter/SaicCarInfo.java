package com.hiworld.adapter;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class SaicCarInfo implements Parcelable{
	
	private int m_iMileage;//总里程
	private int m_iAvgFuel1;//平均油耗1
	private int m_iAvgFuel2;//平均油耗2
	private int m_iAvgFuel3;//平均油耗3
	private int m_iUnitMileage;//里程单位
	private int m_iUnitFuel;//油耗单位
	private int m_iInstantFuel;//瞬时油耗
	private int m_iDitanseMileage;//续航里程
	private int m_iLittleMileage1;//小计里程1
	private int m_iLittleMileage2;//小计里程2
	private int m_iLittleMileage3;//小计里程3
	
	
	public int getM_iInstantFuel() {
		return m_iInstantFuel;
	}

	public void setM_iInstantFuel(int m_iInstantFuel) {
		this.m_iInstantFuel = m_iInstantFuel;
	}

	public int getM_iDitanseMileage() {
		return m_iDitanseMileage;
	}

	public void setM_iDitanseMileage(int m_iDitanseMileage) {
		this.m_iDitanseMileage = m_iDitanseMileage;
	}

	public int getM_iLittleMileage1() {
		return m_iLittleMileage1;
	}

	public void setM_iLittleMileage1(int m_iLittleMileage1) {
		this.m_iLittleMileage1 = m_iLittleMileage1;
	}

	public int getM_iLittleMileage2() {
		return m_iLittleMileage2;
	}

	public void setM_iLittleMileage2(int m_iLittleMileage2) {
		this.m_iLittleMileage2 = m_iLittleMileage2;
	}

	public int getM_iLittleMileage3() {
		return m_iLittleMileage3;
	}

	public void setM_iLittleMileage3(int m_iLittleMileage3) {
		this.m_iLittleMileage3 = m_iLittleMileage3;
	}

	public int getM_iMileage() {
		return m_iMileage;
	}

	public void setM_iMileage(int m_iMileage) {
		this.m_iMileage = m_iMileage;
	}

	public int getM_iAvgFuel1() {
		return m_iAvgFuel1;
	}

	public void setM_iAvgFuel1(int m_iAvgFuel1) {
		this.m_iAvgFuel1 = m_iAvgFuel1;
	}

	public int getM_iAvgFuel2() {
		return m_iAvgFuel2;
	}

	public void setM_iAvgFuel2(int m_iAvgFuel2) {
		this.m_iAvgFuel2 = m_iAvgFuel2;
	}

	public int getM_iAvgFuel3() {
		return m_iAvgFuel3;
	}

	public void setM_iAvgFuel3(int m_iAvgFuel3) {
		this.m_iAvgFuel3 = m_iAvgFuel3;
	}

	public int getM_iUnitMileage() {
		return m_iUnitMileage;
	}

	public void setM_iUnitMileage(int m_iUnitMileage) {
		this.m_iUnitMileage = m_iUnitMileage;
	}

	public int getM_iUnitFuel() {
		return m_iUnitFuel;
	}

	public void setM_iUnitFuel(int m_iUnitFuel) {
		this.m_iUnitFuel = m_iUnitFuel;
	}


	private static final SaicCarInfo INSTANCE = new SaicCarInfo();
	
	public static SaicCarInfo getInstance(){
		return INSTANCE;
	}
	

	public static final Parcelable.Creator<SaicCarInfo> CREATOR = new Creator<SaicCarInfo>(){

		@Override
		public SaicCarInfo createFromParcel(Parcel source) {
			
			INSTANCE.setM_iMileage(source.readInt());
			INSTANCE.setM_iAvgFuel1(source.readInt());
			INSTANCE.setM_iAvgFuel2(source.readInt());
			INSTANCE.setM_iAvgFuel3(source.readInt());
			INSTANCE.setM_iUnitMileage(source.readInt());
			INSTANCE.setM_iUnitFuel(source.readInt());
			INSTANCE.setM_iInstantFuel(source.readInt());
			INSTANCE.setM_iDitanseMileage(source.readInt());
			INSTANCE.setM_iLittleMileage1(source.readInt());
			INSTANCE.setM_iLittleMileage2(source.readInt());
			INSTANCE.setM_iLittleMileage3(source.readInt());
			return INSTANCE;
		}

		@Override
		public SaicCarInfo[] newArray(int size) {
			
			return new SaicCarInfo[size];
		}
		
	};
	
	@Override
	public int describeContents() {
		
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		
		out.writeInt(m_iMileage);
		out.writeInt(m_iAvgFuel1);
		out.writeInt(m_iAvgFuel2);
		out.writeInt(m_iAvgFuel3);
		out.writeInt(m_iUnitMileage);
		out.writeInt(m_iUnitFuel);
		out.writeInt(m_iInstantFuel);
		out.writeInt(m_iDitanseMileage);
		out.writeInt(m_iLittleMileage1);
		out.writeInt(m_iLittleMileage2);
		out.writeInt(m_iLittleMileage3);
		
	}

}
