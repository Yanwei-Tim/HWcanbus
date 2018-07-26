package com.hiworld.canbus.util;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class CarInfo implements Parcelable
{

	private byte m_byState = (byte)0x00;//胎压保存状态
	private byte m_byWarnLevel = (byte)0x00;//报警级别
	private byte m_byRightRear = (byte)0x00;//右后胎
	private byte m_byLeftRear = (byte)0x00;//左右胎
	private byte m_byRightFront = (byte)0x00;//右前胎
	private byte m_byLeftFront = (byte)0x00;//左前胎
	private byte m_bWarnOpen = (byte)0x00;//报警开关
	private byte m_bFunInfo = (byte)0x00;//功能自检信息
	private String m_strVer;
	private byte m_byAgreeTpms = (byte)0x00;
	private byte m_byTpmsLink1 = (byte)0x00;
	private byte m_byTpmsLink2 = (byte)0x00;
	private byte m_bySenis = 5;
	
	private byte bLinkTx = 0;//默认TX
    private byte bCanUnlink = 0;//默认胎压数据
    private byte AccState = 0;//acc状态
   //轮速,左前、右前、左后、右后、平均轮速
    private float iLFWheelSp;
    private float iRFWheelSp;
    private float iLRWheelSp;
    private float iRRWheelSp;
    private float iAvgWheelSp;
	
	private static final CarInfo INSTANCE = new CarInfo();
	
	public static CarInfo getInstance(){
		return INSTANCE;
	}
			
	// 1.必须实现Parcelable.Creator接口,否则在获取CarInfo数据的时候，会报错，如下：
	// 2.这个接口实现了从Percel容器读取CarInfo数据，并返回CarInfo对象给逻辑层使用
	// 3.实现Parcelable.Creator接口对象名必须为CREATOR，不如同样会报错上面所提到的错；
	// 4.在读取Parcel容器里的数据事，必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
	// 5.反序列化对象
	public static final Parcelable.Creator<CarInfo> CREATOR = new Creator<CarInfo>()
	{

		@Override
		public CarInfo createFromParcel(Parcel source)
		{
			
			INSTANCE.setState(source.readByte());
			INSTANCE.setWarnLevel(source.readByte());
			INSTANCE.setRightRear(source.readByte());
			INSTANCE.setLeftRear(source.readByte());
			INSTANCE.setRightFront(source.readByte());
			INSTANCE.setLeftFront(source.readByte());
			INSTANCE.setWarnSwitch(source.readByte());
			INSTANCE.setFunInfo(source.readByte());
			INSTANCE.setVer(source.readString());
			INSTANCE.setAgreeTpms(source.readByte());
			INSTANCE.setTpmsLink1(source.readByte());
			INSTANCE.setTpmsLink2(source.readByte());
			INSTANCE.setSenis(source.readByte());
			INSTANCE.setbLinkTx(source.readByte());
			INSTANCE.setbCanUnlink(source.readByte());
			INSTANCE.setAccState(source.readByte());
			INSTANCE.setiLFWheelSp(source.readFloat());
			INSTANCE.setiRFWheelSp(source.readFloat());
			INSTANCE.setiLRWheelSp(source.readFloat());
			INSTANCE.setiRRWheelSp(source.readFloat());
			INSTANCE.setiAvgWheelSp(source.readFloat());
			return INSTANCE;
		}

		@Override
		public CarInfo[] newArray(int size)
		{
			
			return new CarInfo[size];
		}
		
	};
	
	
	
	//胎压状态
	public byte getState()
	{
		return m_byState;
	}
	
	public void setState(byte date)
	{
		this.m_byState = date;
	}
	//报警级别
	public byte getWarnLevel()
	{
		return m_byWarnLevel;
	}
	
	public void setWarnLevel(byte date)
	{
		this.m_byWarnLevel = date;
	}
	//右后
	public byte getRightRear()
	{
		return m_byRightRear;
	}
	
	public void setRightRear(byte date)
	{
		this.m_byRightRear = date;
	}
	//左后
	public byte getLeftRear()
	{
		return m_byLeftRear;
	}
	
	public void setLeftRear(byte date)
	{
		this.m_byLeftRear = date;
	}
	//右前
	public byte getRightFront()
	{
		return m_byRightFront;
	}
	
	public void setRightFront(byte date)
	{
		this.m_byRightFront = date;
	}
	//左前
	public byte getLeftFront()
	{
		return m_byLeftFront;
	}
	
	public void setLeftFront(byte date)
	{
		this.m_byLeftFront = date;
	}
	//开关
	public byte getWarnSwitch()
	{
		return m_bWarnOpen;
	}
	
	public void setWarnSwitch(byte date)
	{
		this.m_bWarnOpen = date;
	}
	//功能自检
	public byte getFunInfo()
	{
		return m_bFunInfo;
	}
	
	public void setFunInfo(byte date)
	{
		this.m_bFunInfo = date;
	}
	
	public String getVer()
	{
		return m_strVer;
	}
	
	public void setVer(String date)
	{
		this.m_strVer = date;
	}
	
	
	public byte getAgreeTpms()
	{
		return m_byAgreeTpms;
	}
	
	public void setAgreeTpms(byte date)
	{
		this.m_byAgreeTpms = date;
	}
	
	public byte getTpmsLink1()
	{
		return m_byTpmsLink1;
	}
	
	
	public void setTpmsLink1(byte date)
	{
		this.m_byTpmsLink1 = date;
	}
	
	public byte getTpmsLink2()
	{
		return m_byTpmsLink2;
	}
	
	
	public void setTpmsLink2(byte date)
	{
		this.m_byTpmsLink2 = date;
	}
	
	public byte getSenis()
	{
		return m_bySenis;
	}
	
	
	public void setSenis(byte date)
	{
		this.m_bySenis = date;
	}
	
	
	
	
	public byte getbLinkTx() {
		return bLinkTx;
	}

	public void setbLinkTx(byte bLinkTx) {
		this.bLinkTx = bLinkTx;
	}

	public byte getbCanUnlink() {
		return bCanUnlink;
	}

	public void setbCanUnlink(byte bCanUnlink) {
		this.bCanUnlink = bCanUnlink;
	}

	public byte getAccState() {
		return AccState;
	}

	public void setAccState(byte accState) {
		AccState = accState;
	}
	
	

	public float getiLFWheelSp() {
		return iLFWheelSp;
	}

	public void setiLFWheelSp(float iLFWheelSp) {
		this.iLFWheelSp = iLFWheelSp;
	}

	public float getiRFWheelSp() {
		return iRFWheelSp;
	}

	public void setiRFWheelSp(float iRFWheelSp) {
		this.iRFWheelSp = iRFWheelSp;
	}

	public float getiLRWheelSp() {
		return iLRWheelSp;
	}

	public void setiLRWheelSp(float iLRWheelSp) {
		this.iLRWheelSp = iLRWheelSp;
	}

	public float getiRRWheelSp() {
		return iRRWheelSp;
	}

	public void setiRRWheelSp(float iRRWheelSp) {
		this.iRRWheelSp = iRRWheelSp;
	}

	public float getiAvgWheelSp() {
		return iAvgWheelSp;
	}

	public void setiAvgWheelSp(float iAvgWheelSp) {
		this.iAvgWheelSp = iAvgWheelSp;
	}

	@Override
	public int describeContents()
	{
		
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags)
	{
		
		// 1.必须按成员变量声明的顺序封装数据，不然会出现获取数据出错
		// 2.序列化对象
		
		out.writeByte(m_byState);
		out.writeByte(m_byWarnLevel);
		out.writeByte(m_byLeftRear);
		out.writeByte(m_byRightRear);
		out.writeByte(m_byLeftFront);
		out.writeByte(m_byRightFront);
		out.writeByte(m_bWarnOpen);
		out.writeByte(m_bFunInfo);
		out.writeString(m_strVer);
		out.writeByte(m_byAgreeTpms);
		out.writeByte(m_byTpmsLink1);
		out.writeByte(m_byTpmsLink2);
		out.writeByte(m_bySenis);
		out.writeByte(bLinkTx);
		out.writeByte(bCanUnlink);
		out.writeByte(AccState);
		out.writeFloat(iLFWheelSp);
		out.writeFloat(iRFWheelSp);
		out.writeFloat(iLRWheelSp);
		out.writeFloat(iRRWheelSp);
		out.writeFloat(iAvgWheelSp);
	}
	
	public void readFromParcel(Parcel in){ 
		m_byState = in.readByte();          //先读出，保持与写同顺序
		m_byWarnLevel = in.readByte();  //其次读出，保持与写同顺序
		m_byLeftRear = in.readByte();  //其次读出，保持与写同顺序
		m_byRightRear = in.readByte();  //其次读出，保持与写同顺序
		m_byLeftFront = in.readByte();  //其次读出，保持与写同顺序
		m_byRightFront = in.readByte();  //其次读出，保持与写同顺序
		m_bWarnOpen = in.readByte();  //其次读出，保持与写同顺序
		m_bFunInfo = in.readByte();  //其次读出，保持与写同顺序
		m_strVer = in.readString();
		m_byAgreeTpms = in.readByte();
		m_byTpmsLink1 = in.readByte();
		m_byTpmsLink2 = in.readByte();
		m_bySenis = in.readByte();
		bLinkTx = in.readByte();
		bCanUnlink = in.readByte();
		AccState = in.readByte();
		iLFWheelSp = in.readFloat();
		iRFWheelSp = in.readFloat();
		iLRWheelSp = in.readFloat();
		iRRWheelSp = in.readFloat();
		iAvgWheelSp = in.readFloat();
    } 

}
