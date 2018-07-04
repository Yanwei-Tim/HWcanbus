package com.hiworld.adapter;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class CarInfo implements Parcelable
{

	private float m_fInstantFuel =0xffff;//瞬时油耗
	private int   m_iMileage = 0xffff;//续航里程
	private float m_fTrip1AverageFuel = 0xffff;//平均油耗
	private int m_fTrip1AverageSpeed = 0xff;//平均车速
	private int m_fTrip1AccumMileage;//累计里程
	private float m_fTrip2AverageFuel = 0xffff;//平均油耗
	private int m_fTrip2AverageSpeed = 0xff;//平均车速
	private int m_fTrip2AccumMileage;//累计里程
	
	private float m_fBestFuel = 0xffff;//最佳油耗
	private int m_iDrivingTime;//行车时间
	private int m_iFuleUnit;//油耗单位0x00:MPG（标尺为60）0x01: km/L (标尺为30）0x02: L/100km(标尺为30）
	private int m_iMileageUnit;//里程单位	0x00: km； 0x01: mile
	private float m_fCurrentTripFuel;//当前行程油耗
	private float m_fTrip1Fuel;//Trip1油耗
	private float m_fTrip2Fuel;//Trip2油耗
	private float m_fTrip3Fuel;//Trip3油耗
	private float m_fTrip4Fuel;//Trip4油耗
	private float m_fTrip5Fuel;//Trip5油耗
	private int m_iTrip1FuleUnit;//油耗单位
	private float[] m_fTrip2MinuteFuel = new float[30];
	private int m_iTrip2FuleUnit;//油耗单位
	
	public static final CarInfo INSTANCE = new CarInfo();
	
	public static CarInfo getInstance(){
		return INSTANCE;
	}

	public static final Parcelable.Creator<CarInfo> CREATOR = new Creator<CarInfo>()
	{

		@Override
		public CarInfo createFromParcel(Parcel source)
		{
			
			CarInfo m_stCarInfo = new CarInfo();
			m_stCarInfo.setInstantFuel(source.readFloat());
			m_stCarInfo.setMileage(source.readInt());
			m_stCarInfo.setTrip1AverageFuel(source.readFloat());
			m_stCarInfo.setTrip1AverageSpeed(source.readInt());
			m_stCarInfo.setTrip1AccumMileage(source.readInt());
			m_stCarInfo.setTrip2AverageFuel(source.readFloat());
			m_stCarInfo.setTrip2AverageSpeed(source.readInt());
			m_stCarInfo.setTrip2AccumMileage(source.readInt());
			m_stCarInfo.setBestFuel(source.readFloat());
			m_stCarInfo.setDrivingTime(source.readInt());
			m_stCarInfo.setFuleUnit(source.readInt());
			m_stCarInfo.setMileageUnit(source.readInt());
			m_stCarInfo.setCurrentTripFuel(source.readFloat());
			m_stCarInfo.setTrip1Fuel(source.readFloat());
			m_stCarInfo.setTrip2Fuel(source.readFloat());
			m_stCarInfo.setTrip3Fuel(source.readFloat());
			m_stCarInfo.setTrip4Fuel(source.readFloat());
			m_stCarInfo.setTrip5Fuel(source.readFloat());
			m_stCarInfo.setTrip1FuleUnit(source.readInt());
			m_stCarInfo.setTrip2Minute1Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute2Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute3Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute4Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute5Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute6Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute7Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute8Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute9Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute10Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute11Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute12Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute13Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute14Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute15Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute16Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute17Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute18Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute19Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute20Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute21Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute22Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute23Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute24Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute25Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute26Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute27Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute28Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute29Fuel(source.readFloat());
			m_stCarInfo.setTrip2Minute30Fuel(source.readFloat());
			m_stCarInfo.setTrip2FuleUnit(source.readInt());

			return m_stCarInfo;
		}

		@Override
		public CarInfo[] newArray(int size)
		{
			
			return new CarInfo[size];
		}
		
	};

	//瞬时油耗
	public float getInstantFuel()
	{
		return m_fInstantFuel;
	}

	public void setInstantFuel(float value)
	{
		//Log.d("setInstantFuel", "value ="+value);
		this.m_fInstantFuel = value;
	}
	//续航里程
	public int getMileage()
	{
		return m_iMileage;
	}

	public void setMileage(int value)
	{
		this.m_iMileage = value;
	}
	//平均油耗
	public float getTrip1AverageFuel()
	{
		return m_fTrip1AverageFuel;
	}

	public void setTrip1AverageFuel(float value)
	{
		this.m_fTrip1AverageFuel = value;
	}
	// 平均车速
	public int getTrip1AverageSpeed()
	{
		return m_fTrip1AverageSpeed;
	}

	public void setTrip1AverageSpeed(int value)
	{
		this.m_fTrip1AverageSpeed = value;
	}
	// 累计里程
	public int getTrip1AccumMileage()
	{
		return m_fTrip1AccumMileage;
	}

	public void setTrip1AccumMileage(int value)
	{
		this.m_fTrip1AccumMileage = value;
	}
	//平均油耗
	public float getTrip2AverageFuel()
	{
		return m_fTrip2AverageFuel;
	}

	public void setTrip2AverageFuel(float value)
	{
		this.m_fTrip2AverageFuel = value;
	}
	// 平均车速
	public int getTrip2AverageSpeed()
	{
		return m_fTrip2AverageSpeed;
	}

	public void setTrip2AverageSpeed(int value)
	{
		this.m_fTrip2AverageSpeed = value;
	}
	// 累计里程
	public int getTrip2AccumMileage()
	{
		return m_fTrip2AccumMileage;
	}

	public void setTrip2AccumMileage(int value)
	{
		this.m_fTrip2AccumMileage = value;
	}
	//最佳油耗
	public float getBestFuel()
	{
		return m_fBestFuel;
	}

	public void setBestFuel(float value)
	{
		this.m_fBestFuel = value;
	}
	//行车时间
	public int getDrivingTime()
	{
		return m_iDrivingTime;
	}

	public void setDrivingTime(int value)
	{
		this.m_iDrivingTime = value;
	}
	//油耗单位
	public int getFuleUnit()
	{
		return m_iFuleUnit;
	}

	public void setFuleUnit(int value)
	{
		this.m_iFuleUnit = value;
	}
	//里程单位
	public int getMileageUnit()
	{
		return m_iMileageUnit;
	}

	public void setMileageUnit(int value)
	{
		this.m_iMileageUnit = value;
	}
	//当前行程油耗
	public float getCurrentTripFuel()
	{
		return m_fCurrentTripFuel;
	}

	public void setCurrentTripFuel(float value)
	{
		this.m_fCurrentTripFuel = value;
	}
	//Trip1油耗
	public float getTrip1Fuel()
	{
		return m_fTrip1Fuel;
	}

	public void setTrip1Fuel(float value)
	{
		this.m_fTrip1Fuel = value;
	}
	//Trip2油耗
	public float getTrip2Fuel()
	{
		return m_fTrip2Fuel;
	}

	public void setTrip2Fuel(float value)
	{
		this.m_fTrip2Fuel = value;
	}
	//Trip3油耗
	public float getTrip3Fuel()
	{
		return m_fTrip3Fuel;
	}

	public void setTrip3Fuel(float value)
	{
		this.m_fTrip3Fuel = value;
	}
	//Trip4油耗
	public float getTrip4Fuel()
	{
		return m_fTrip4Fuel;
	}

	public void setTrip4Fuel(float value)
	{
		this.m_fTrip4Fuel = value;
	}
	//Trip5油耗
	public float getTrip5Fuel()
	{
		return m_fTrip5Fuel;
	}

	public void setTrip5Fuel(float value)
	{
		this.m_fTrip5Fuel = value;
	}
	//油耗单位
	public int getTrip1FuleUnit()
	{
		return m_iTrip1FuleUnit;
	}

	public void setTrip1FuleUnit(int value)
	{
		this.m_iTrip1FuleUnit = value;
	}
	//30分钟油耗
	public float getTrip2Minute1Fuel()
	{
		return m_fTrip2MinuteFuel[0];
	}

	public void setTrip2Minute1Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[0] = value;
	}
	
	public float getTrip2Minute2Fuel()
	{
		return m_fTrip2MinuteFuel[1];
	}

	public void setTrip2Minute2Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[1] = value;
	}
	
	public float getTrip2Minute3Fuel()
	{
		return m_fTrip2MinuteFuel[2];
	}

	public void setTrip2Minute3Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[2] = value;
	}
	
	public float getTrip2Minute4Fuel()
	{
		return m_fTrip2MinuteFuel[3];
	}

	public void setTrip2Minute4Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[3] = value;
	}
	
	public float getTrip2Minute5Fuel()
	{
		return m_fTrip2MinuteFuel[4];
	}

	public void setTrip2Minute5Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[4] = value;
	}
	
	public float getTrip2Minute6Fuel()
	{
		return m_fTrip2MinuteFuel[5];
	}

	public void setTrip2Minute6Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[5] = value;
	}
	
	public float getTrip2Minute7Fuel()
	{
		return m_fTrip2MinuteFuel[6];
	}

	public void setTrip2Minute7Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[6] = value;
	}
	
	public float getTrip2Minute8Fuel()
	{
		return m_fTrip2MinuteFuel[7];
	}

	public void setTrip2Minute8Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[7] = value;
	}
	
	public float getTrip2Minute9Fuel()
	{
		return m_fTrip2MinuteFuel[8];
	}

	public void setTrip2Minute9Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[8] = value;
	}
	
	public float getTrip2Minute10Fuel()
	{
		return m_fTrip2MinuteFuel[9];
	}

	public void setTrip2Minute10Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[9] = value;
	}
	
	public float getTrip2Minute11Fuel()
	{
		return m_fTrip2MinuteFuel[10];
	}

	public void setTrip2Minute11Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[10] = value;
	}
	
	public float getTrip2Minute12Fuel()
	{
		return m_fTrip2MinuteFuel[11];
	}

	public void setTrip2Minute12Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[11] = value;
	}
	
	public float getTrip2Minute13Fuel()
	{
		return m_fTrip2MinuteFuel[12];
	}

	public void setTrip2Minute13Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[12] = value;
	}
	
	public float getTrip2Minute14Fuel()
	{
		return m_fTrip2MinuteFuel[13];
	}

	public void setTrip2Minute14Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[13] = value;
	}
	
	public float getTrip2Minute15Fuel()
	{
		return m_fTrip2MinuteFuel[14];
	}

	public void setTrip2Minute15Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[14] = value;
	}
	
	public float getTrip2Minute16Fuel()
	{
		return m_fTrip2MinuteFuel[15];
	}

	public void setTrip2Minute16Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[15] = value;
	}
	
	public float getTrip2Minute17Fuel()
	{
		return m_fTrip2MinuteFuel[16];
	}

	public void setTrip2Minute17Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[16] = value;
	}
	
	public float getTrip2Minute18Fuel()
	{
		return m_fTrip2MinuteFuel[17];
	}

	public void setTrip2Minute18Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[17] = value;
	}
	
	public float getTrip2Minute19Fuel()
	{
		return m_fTrip2MinuteFuel[18];
	}

	public void setTrip2Minute19Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[18] = value;
	}
	
	public float getTrip2Minute20Fuel()
	{
		return m_fTrip2MinuteFuel[19];
	}

	public void setTrip2Minute20Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[19] = value;
	}
	
	public float getTrip2Minute21Fuel()
	{
		return m_fTrip2MinuteFuel[20];
	}

	public void setTrip2Minute21Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[20] = value;
	}
	
	public float getTrip2Minute22Fuel()
	{
		return m_fTrip2MinuteFuel[21];
	}

	public void setTrip2Minute22Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[21] = value;
	}
	
	public float getTrip2Minute23Fuel()
	{
		return m_fTrip2MinuteFuel[22];
	}

	public void setTrip2Minute23Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[22] = value;
	}
	
	public float getTrip2Minute24Fuel()
	{
		return m_fTrip2MinuteFuel[23];
	}

	public void setTrip2Minute24Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[23] = value;
	}
	
	public float getTrip2Minute25Fuel()
	{
		return m_fTrip2MinuteFuel[24];
	}

	public void setTrip2Minute25Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[24] = value;
	}
	
	public float getTrip2Minute26Fuel()
	{
		return m_fTrip2MinuteFuel[25];
	}

	public void setTrip2Minute26Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[25] = value;
	}
	
	public float getTrip2Minute27Fuel()
	{
		return m_fTrip2MinuteFuel[26];
	}

	public void setTrip2Minute27Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[26] = value;
	}
	
	public float getTrip2Minute28Fuel()
	{
		return m_fTrip2MinuteFuel[27];
	}

	public void setTrip2Minute28Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[27] = value;
	}
	
	public float getTrip2Minute29Fuel()
	{
		return m_fTrip2MinuteFuel[28];
	}

	public void setTrip2Minute29Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[28] = value;
	}
	
	public float getTrip2Minute30Fuel()
	{
		return m_fTrip2MinuteFuel[29];
	}

	public void setTrip2Minute30Fuel(float value)
	{
		this.m_fTrip2MinuteFuel[29] = value;
	}
	//油耗单位
	public int getTrip2FuleUnit()
	{
		return m_iTrip2FuleUnit;
	}

	public void setTrip2FuleUnit(int value)
	{
		this.m_iTrip2FuleUnit = value;
	}
	
	@Override
	public int describeContents()
	{
		
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags)
	{
		
		out.writeFloat(m_fInstantFuel);
		out.writeInt(m_iMileage);
		out.writeFloat(m_fTrip1AverageFuel);
		out.writeInt(m_fTrip1AverageSpeed);
		out.writeInt(m_fTrip1AccumMileage);
		out.writeFloat(m_fTrip2AverageFuel);
		out.writeInt(m_fTrip2AverageSpeed);
		out.writeInt(m_fTrip2AccumMileage);
		out.writeFloat(m_fBestFuel);
		out.writeInt(m_iDrivingTime);
		out.writeInt(m_iFuleUnit);
		out.writeInt(m_iMileageUnit);
		out.writeFloat(m_fCurrentTripFuel);
		out.writeFloat(m_fTrip1Fuel);
		out.writeFloat(m_fTrip2Fuel);
		out.writeFloat(m_fTrip3Fuel);
		out.writeFloat(m_fTrip4Fuel);
		out.writeFloat(m_fTrip5Fuel);
		out.writeInt(m_iTrip1FuleUnit);
		out.writeFloatArray(m_fTrip2MinuteFuel);
		out.writeInt(m_iTrip2FuleUnit);
	}

}
