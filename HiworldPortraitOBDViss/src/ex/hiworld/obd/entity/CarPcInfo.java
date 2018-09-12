package ex.hiworld.obd.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class CarPcInfo implements Parcelable{

	//车辆基本信息使能
	private int m_iVINAble = 1;//VIN码使能
	private int m_iMadeTimeAble;//制造日期使能
	private int m_iModelAble;//车型使能
	private int m_iBrandAble;//品牌使能
	//车辆状态信息使能
	private int m_iGearAble;//档位使能
	private int m_iLightBlueAble;//灯光亮度使能
	private int m_iInnerLightAble;//车内灯光使能
	private int m_iOutLightAble;//车外灯光使能
	private int m_iHandbrakeAble;//手刹使能
	private int m_iBaseStateAble;//基本状态使能
	private int m_iWindowAble;//车窗使能
	private int m_iDoorAble;//车门使能
	//车辆行驶信息使能
	private int m_iLRTrunAble;//左右转向灯使能
	private int m_iDamperAble;//节气门位置
	private int m_iBaroPressureAble;//大气压力
	private int m_iInstantFuelAble;//瞬时油耗使能
	private int m_iCooltempAble;//冷却液温度
	private int m_iBatteryVolAble;//电池电压使能
	private int m_iEnigineSpeedAble;//发动机转速使能
	private int m_iSpeedAble;//车速使能
	//车辆倒车信息使能
	private int m_iReverseGearAble;//倒档信息使能
	private int m_iSteerAngleAble;//方向盘转角
	//娱乐信息使能
	//故障报警信息使能
	private int m_iFaultWarnAble;//故障报警使能
	//雷达信息使能
	private int m_iFrontRadarAble;//前雷达
	private int m_iRearRadarAble;//后雷达
	private int m_iHaveRadarAble;//雷达有效使能
	//里程信息使能
	private int m_iSelfStartMileageAble;//自启动后里程使能
	private int m_iLittleTripAble;//小计里程使能
	private int m_iMileageAble;//续航里程使能
	private int m_iResidualOilAble;//剩余油位
	private int m_iTotalMileageAble;//总里程使能
	//门锁状态
	private int m_iLFGatelockAble;//左前门锁使能
	private int m_iRFGatelockAble;//右前门锁使能
	private int m_iLRGatelockAble;//左后门锁使能
	private int m_iRRGatelockAble;//右后门锁使能
	private int m_iRemoteoutlockAble;//遥控车外锁车使能
	private int m_iInnerlockAble;//车内锁车使能
	//安全带
	private int m_iLFSafebeltAble;//左前安全带使能
	private int m_iRFSafebeltAble;//右前安全带使能
	private int m_iLRSafebeltAble;//左后安全带使能
	private int m_iRRSafebeltAble;//右后安全带使能
	private int m_iRMRSafebeltAble;//右中后安全带使能
	//基本状态
	private int m_iFootbrakeAble;//脚刹使能
	private int m_iILLAble;//ILL使能
	private int m_iACCAble;//ACC使能
	private int m_iFireAble;//点火使能
	private int m_iLockAble;//锁车使能
	
	//车辆基本信息
	private String m_strBrand = "--";//品牌
	private String m_strModel = "--";//车型
	private String m_strMadeTime = "--";//制造日期
	private String m_strVIN = "--";//VIN码
	//车辆状态信息
	private int m_iHood;//前盖
	private int m_iTailBox;//尾箱
	private int m_iRRDoor;//右后门
	private int m_iLRDoor;//左后门
	private int m_iRFDoor;//右前门
	private int m_iLFDoor;//左前门
	private int m_iDormer;//天窗
	private int m_iRRWindow;//右后窗
	private int m_iLRWindow;//左后窗
	private int m_iRFWindow;//右前窗
	private int m_iLFWindow;//左前窗
	private int m_iFootBrake;//脚刹
	private int m_iILL;//ILL
	private int m_iACC;//ACC
	private int m_iFire;//点火
	private int m_iHanderBrake;//手刹
	private int m_iLock;//锁车
	private int m_iDayLight;//日间行车灯
	private int m_iRevLight;//倒车灯
	private int m_iStopLight;//刹车灯
	private int m_iRearflogLight;//后雾灯
	private int m_iFrontflogLight;//前雾灯
	private int m_iWideLight;//示宽灯
	private int m_iFarLight;//远光灯
	private int m_iNearLight;//近光灯
	private int m_iDoubleLight;//双闪灯
	private int m_iLeftTrunLight;//左转向灯
	private int m_iRightTrunLight;//右转向灯
	private int m_iLeftTrunFlogLight;//左转向补光灯
	private int m_iRightTrunFlogLight;//右转向补光灯
	private int m_iLightData;//灯光亮度
	private int m_iGear;//档位
	private int m_iLFSafebelt = 1;//左前安全带
	private int m_iRFSafebelt = 1;//右前安全带
	private int m_iLRSafebelt = 1;//左后安全带
	private int m_iRRSafebelt = 1;//右后安全带
	private int m_iRMRSafebelt = 1;//右中后安全带
	private int m_iLFDoorlock;//左前门锁
	private int m_iRFDoorlock;//右前门锁
	private int m_iLRDoorLock;//左后门锁
	private int m_iRRDoorlock;//右后门锁
	private int m_iRemotelock;//遥控车锁
	private int m_iInnerlock;//车内锁车
	//车辆行驶信息
	private float m_fInstantSpeed;//瞬时车速
	private int m_iEngineSpeed;//发动机转速
	private float m_fBatteryVol;//电池电压
	private int m_iFuelUnit;//油耗单位
	private int m_iCooltemp;//冷却液温度
	private float m_fInstantFuel;//瞬时油耗
	private int m_iBaroPressure;//大气压力
	private int m_iDamper;//节气门位置
	//车辆倒车信息
	private int m_iSWAAngle;//方向盘转角
	private int m_iRev;//倒档状态
	//雷达信息
	private int m_iHaveRadar;//雷达有效
	private int m_iRLRadar;//后左雷达
	private int m_iRMLRadar;//后中左雷达
	private int m_iRMRRadar;//后中右雷达
	private int m_iRRRadar;//后右雷达
	private int m_iFLRadar;//前左雷达
	private int m_iFMLRadar;//前中左雷达
	private int m_iFMRRadar;//前中右雷达
	private int m_iFRRadar;//前右雷达
	//里程信息
	private int m_iTotalMileage;//总里程
	private int m_iResidualOil;//剩余油位
	private int m_iMileage;//续航里程
	private int m_iTripmeter_mileage;//小计里程-里程
	private int m_fTripmeter_avgspeed;//小计里程-平均车速
	private int m_iTripmeter_drivertime;//小计里程-行驶时长
	private float m_fTripmeter_avgfuel;//小计里程-平均油耗
	private int m_iSelfstart_mileage;//自启动后里程-里程
	private int m_fSelfstart_avgspeed;//自启动后里程-平均车速
	private int m_iSelfstart_drivertime;//自启动后里程-行驶时长
	private float m_fSelfstart_avgfuel;//自启动后里程-平均油耗
	
	private int m_iHtpmsWarn;//胎压是否报警
	
	private static final CarPcInfo INSTANCE = new CarPcInfo();
	
	public static CarPcInfo getInstance(){
		return INSTANCE;
	}
	
	

	public int getM_iVINAble() {
		return m_iVINAble;
	}



	public void setM_iVINAble(int m_iVINAble) {
		this.m_iVINAble = m_iVINAble;
	}



	public int getM_iMadeTimeAble() {
		return m_iMadeTimeAble;
	}



	public void setM_iMadeTimeAble(int m_iMadeTimeAble) {
		this.m_iMadeTimeAble = m_iMadeTimeAble;
	}



	public int getM_iModelAble() {
		return m_iModelAble;
	}



	public void setM_iModelAble(int m_iModelAble) {
		this.m_iModelAble = m_iModelAble;
	}



	public int getM_iBrandAble() {
		return m_iBrandAble;
	}



	public void setM_iBrandAble(int m_iBrandAble) {
		this.m_iBrandAble = m_iBrandAble;
	}



	public int getM_iGearAble() {
		return m_iGearAble;
	}



	public void setM_iGearAble(int m_iGearAble) {
		this.m_iGearAble = m_iGearAble;
	}



	public int getM_iLightBlueAble() {
		return m_iLightBlueAble;
	}



	public void setM_iLightBlueAble(int m_iLightBlueAble) {
		this.m_iLightBlueAble = m_iLightBlueAble;
	}



	public int getM_iInnerLightAble() {
		return m_iInnerLightAble;
	}



	public void setM_iInnerLightAble(int m_iInnerLightAble) {
		this.m_iInnerLightAble = m_iInnerLightAble;
	}



	public int getM_iOutLightAble() {
		return m_iOutLightAble;
	}



	public void setM_iOutLightAble(int m_iOutLightAble) {
		this.m_iOutLightAble = m_iOutLightAble;
	}



	public int getM_iHandbrakeAble() {
		return m_iHandbrakeAble;
	}



	public void setM_iHandbrakeAble(int m_iHandbrakeAble) {
		this.m_iHandbrakeAble = m_iHandbrakeAble;
	}



	public int getM_iBaseStateAble() {
		return m_iBaseStateAble;
	}



	public void setM_iBaseStateAble(int m_iBaseStateAble) {
		this.m_iBaseStateAble = m_iBaseStateAble;
	}



	public int getM_iWindowAble() {
		return m_iWindowAble;
	}



	public void setM_iWindowAble(int m_iWindowAble) {
		this.m_iWindowAble = m_iWindowAble;
	}



	public int getM_iDoorAble() {
		return m_iDoorAble;
	}



	public void setM_iDoorAble(int m_iDoorAble) {
		this.m_iDoorAble = m_iDoorAble;
	}



	public int getM_iLRTrunAble() {
		return m_iLRTrunAble;
	}



	public void setM_iLRTrunAble(int m_iLRTrunAble) {
		this.m_iLRTrunAble = m_iLRTrunAble;
	}



	public int getM_iDamperAble() {
		return m_iDamperAble;
	}



	public void setM_iDamperAble(int m_iDamperAble) {
		this.m_iDamperAble = m_iDamperAble;
	}



	public int getM_iBaroPressureAble() {
		return m_iBaroPressureAble;
	}



	public void setM_iBaroPressureAble(int m_iBaroPressureAble) {
		this.m_iBaroPressureAble = m_iBaroPressureAble;
	}



	public int getM_iInstantFuelAble() {
		return m_iInstantFuelAble;
	}



	public void setM_iInstantFuelAble(int m_iInstantFuelAble) {
		this.m_iInstantFuelAble = m_iInstantFuelAble;
	}



	public int getM_iCooltempAble() {
		return m_iCooltempAble;
	}



	public void setM_iCooltempAble(int m_iCooltempAble) {
		this.m_iCooltempAble = m_iCooltempAble;
	}



	public int getM_iBatteryVolAble() {
		return m_iBatteryVolAble;
	}



	public void setM_iBatteryVolAble(int m_iBatteryVolAble) {
		this.m_iBatteryVolAble = m_iBatteryVolAble;
	}



	public int getM_iEnigineSpeedAble() {
		return m_iEnigineSpeedAble;
	}



	public void setM_iEnigineSpeedAble(int m_iEnigineSpeedAble) {
		this.m_iEnigineSpeedAble = m_iEnigineSpeedAble;
	}



	public int getM_iSpeedAble() {
		return m_iSpeedAble;
	}



	public void setM_iSpeedAble(int m_iSpeedAble) {
		this.m_iSpeedAble = m_iSpeedAble;
	}



	public int getM_iReverseGearAble() {
		return m_iReverseGearAble;
	}



	public void setM_iReverseGearAble(int m_iReverseGearAble) {
		this.m_iReverseGearAble = m_iReverseGearAble;
	}



	public int getM_iSteerAngleAble() {
		return m_iSteerAngleAble;
	}



	public void setM_iSteerAngleAble(int m_iSteerAngleAble) {
		this.m_iSteerAngleAble = m_iSteerAngleAble;
	}



	public int getM_iFaultWarnAble() {
		return m_iFaultWarnAble;
	}



	public void setM_iFaultWarnAble(int m_iFaultWarnAble) {
		this.m_iFaultWarnAble = m_iFaultWarnAble;
	}



	public int getM_iFrontRadarAble() {
		return m_iFrontRadarAble;
	}



	public void setM_iFrontRadarAble(int m_iFrontRadarAble) {
		this.m_iFrontRadarAble = m_iFrontRadarAble;
	}



	public int getM_iRearRadarAble() {
		return m_iRearRadarAble;
	}



	public void setM_iRearRadarAble(int m_iRearRadarAble) {
		this.m_iRearRadarAble = m_iRearRadarAble;
	}



	public int getM_iHaveRadarAble() {
		return m_iHaveRadarAble;
	}



	public void setM_iHaveRadarAble(int m_iHaveRadarAble) {
		this.m_iHaveRadarAble = m_iHaveRadarAble;
	}



	public int getM_iSelfStartMileageAble() {
		return m_iSelfStartMileageAble;
	}



	public void setM_iSelfStartMileageAble(int m_iSelfStartMileageAble) {
		this.m_iSelfStartMileageAble = m_iSelfStartMileageAble;
	}



	public int getM_iLittleTripAble() {
		return m_iLittleTripAble;
	}



	public void setM_iLittleTripAble(int m_iLittleTripAble) {
		this.m_iLittleTripAble = m_iLittleTripAble;
	}



	public int getM_iMileageAble() {
		return m_iMileageAble;
	}



	public void setM_iMileageAble(int m_iMileageAble) {
		this.m_iMileageAble = m_iMileageAble;
	}



	public int getM_iResidualOilAble() {
		return m_iResidualOilAble;
	}



	public void setM_iResidualOilAble(int m_iResidualOilAble) {
		this.m_iResidualOilAble = m_iResidualOilAble;
	}



	public int getM_iTotalMileageAble() {
		return m_iTotalMileageAble;
	}



	public void setM_iTotalMileageAble(int m_iTotalMileageAble) {
		this.m_iTotalMileageAble = m_iTotalMileageAble;
	}



	public int getM_iLFGatelockAble() {
		return m_iLFGatelockAble;
	}



	public void setM_iLFGatelockAble(int m_iLFGatelockAble) {
		this.m_iLFGatelockAble = m_iLFGatelockAble;
	}



	public int getM_iRFGatelockAble() {
		return m_iRFGatelockAble;
	}



	public void setM_iRFGatelockAble(int m_iRFGatelockAble) {
		this.m_iRFGatelockAble = m_iRFGatelockAble;
	}



	public int getM_iLRGatelockAble() {
		return m_iLRGatelockAble;
	}



	public void setM_iLRGatelockAble(int m_iLRGatelockAble) {
		this.m_iLRGatelockAble = m_iLRGatelockAble;
	}



	public int getM_iRRGatelockAble() {
		return m_iRRGatelockAble;
	}



	public void setM_iRRGatelockAble(int m_iRRGatelockAble) {
		this.m_iRRGatelockAble = m_iRRGatelockAble;
	}



	public int getM_iRemoteoutlockAble() {
		return m_iRemoteoutlockAble;
	}



	public void setM_iRemoteoutlockAble(int m_iRemoteoutlockAble) {
		this.m_iRemoteoutlockAble = m_iRemoteoutlockAble;
	}



	public int getM_iInnerlockAble() {
		return m_iInnerlockAble;
	}



	public void setM_iInnerlockAble(int m_iInnerlockAble) {
		this.m_iInnerlockAble = m_iInnerlockAble;
	}



	public int getM_iLFSafebeltAble() {
		return m_iLFSafebeltAble;
	}



	public void setM_iLFSafebeltAble(int m_iLFSafebeltAble) {
		this.m_iLFSafebeltAble = m_iLFSafebeltAble;
	}



	public int getM_iRFSafebeltAble() {
		return m_iRFSafebeltAble;
	}



	public void setM_iRFSafebeltAble(int m_iRFSafebeltAble) {
		this.m_iRFSafebeltAble = m_iRFSafebeltAble;
	}



	public int getM_iLRSafebeltAble() {
		return m_iLRSafebeltAble;
	}



	public void setM_iLRSafebeltAble(int m_iLRSafebeltAble) {
		this.m_iLRSafebeltAble = m_iLRSafebeltAble;
	}



	public int getM_iRRSafebeltAble() {
		return m_iRRSafebeltAble;
	}



	public void setM_iRRSafebeltAble(int m_iRRSafebeltAble) {
		this.m_iRRSafebeltAble = m_iRRSafebeltAble;
	}



	public int getM_iRMRSafebeltAble() {
		return m_iRMRSafebeltAble;
	}



	public void setM_iRMRSafebeltAble(int m_iRMRSafebeltAble) {
		this.m_iRMRSafebeltAble = m_iRMRSafebeltAble;
	}



	public int getM_iFootbrakeAble() {
		return m_iFootbrakeAble;
	}



	public void setM_iFootbrakeAble(int m_iFootbrakeAble) {
		this.m_iFootbrakeAble = m_iFootbrakeAble;
	}



	public int getM_iILLAble() {
		return m_iILLAble;
	}



	public void setM_iILLAble(int m_iILLAble) {
		this.m_iILLAble = m_iILLAble;
	}



	public int getM_iACCAble() {
		return m_iACCAble;
	}



	public void setM_iACCAble(int m_iACCAble) {
		this.m_iACCAble = m_iACCAble;
	}



	public int getM_iFireAble() {
		return m_iFireAble;
	}



	public void setM_iFireAble(int m_iFireAble) {
		this.m_iFireAble = m_iFireAble;
	}



	public int getM_iLockAble() {
		return m_iLockAble;
	}



	public void setM_iLockAble(int m_iLockAble) {
		this.m_iLockAble = m_iLockAble;
	}



	public String getM_strBrand() {
		return m_strBrand;
	}



	public void setM_strBrand(String m_strBrand) {
		this.m_strBrand = m_strBrand;
	}



	public String getM_strModel() {
		return m_strModel;
	}



	public void setM_strModel(String m_strModel) {
		this.m_strModel = m_strModel;
	}



	public String getM_strMadeTime() {
		return m_strMadeTime;
	}



	public void setM_strMadeTime(String m_strMadeTime) {
		this.m_strMadeTime = m_strMadeTime;
	}



	public String getM_strVIN() {
		return m_strVIN;
	}



	public void setM_strVIN(String m_strVIN) {
		this.m_strVIN = m_strVIN;
	}



	public int getM_iHood() {
		return m_iHood;
	}



	public void setM_iHood(int m_iHood) {
		this.m_iHood = m_iHood;
	}



	public int getM_iTailBox() {
		return m_iTailBox;
	}



	public void setM_iTailBox(int m_iTailBox) {
		this.m_iTailBox = m_iTailBox;
	}



	public int getM_iRRDoor() {
		return m_iRRDoor;
	}



	public void setM_iRRDoor(int m_iRRDoor) {
		this.m_iRRDoor = m_iRRDoor;
	}



	public int getM_iLRDoor() {
		return m_iLRDoor;
	}



	public void setM_iLRDoor(int m_iLRDoor) {
		this.m_iLRDoor = m_iLRDoor;
	}



	public int getM_iRFDoor() {
		return m_iRFDoor;
	}



	public void setM_iRFDoor(int m_iRFDoor) {
		this.m_iRFDoor = m_iRFDoor;
	}



	public int getM_iLFDoor() {
		return m_iLFDoor;
	}



	public void setM_iLFDoor(int m_iLFDoor) {
		this.m_iLFDoor = m_iLFDoor;
	}



	public int getM_iDormer() {
		return m_iDormer;
	}



	public void setM_iDormer(int m_iDormer) {
		this.m_iDormer = m_iDormer;
	}



	public int getM_iRRWindow() {
		return m_iRRWindow;
	}



	public void setM_iRRWindow(int m_iRRWindow) {
		this.m_iRRWindow = m_iRRWindow;
	}



	public int getM_iLRWindow() {
		return m_iLRWindow;
	}



	public void setM_iLRWindow(int m_iLRWindow) {
		this.m_iLRWindow = m_iLRWindow;
	}



	public int getM_iRFWindow() {
		return m_iRFWindow;
	}



	public void setM_iRFWindow(int m_iRFWindow) {
		this.m_iRFWindow = m_iRFWindow;
	}



	public int getM_iLFWindow() {
		return m_iLFWindow;
	}



	public void setM_iLFWindow(int m_iLFWindow) {
		this.m_iLFWindow = m_iLFWindow;
	}



	public int getM_iFootBrake() {
		return m_iFootBrake;
	}



	public void setM_iFootBrake(int m_iFootBrake) {
		this.m_iFootBrake = m_iFootBrake;
	}



	public int getM_iILL() {
		return m_iILL;
	}



	public void setM_iILL(int m_iILL) {
		this.m_iILL = m_iILL;
	}



	public int getM_iACC() {
		return m_iACC;
	}



	public void setM_iACC(int m_iACC) {
		this.m_iACC = m_iACC;
	}



	public int getM_iFire() {
		return m_iFire;
	}



	public void setM_iFire(int m_iFire) {
		this.m_iFire = m_iFire;
	}



	public int getM_iHanderBrake() {
		return m_iHanderBrake;
	}



	public void setM_iHanderBrake(int m_iHanderBrake) {
		this.m_iHanderBrake = m_iHanderBrake;
	}



	public int getM_iLock() {
		return m_iLock;
	}



	public void setM_iLock(int m_iLock) {
		this.m_iLock = m_iLock;
	}



	public int getM_iDayLight() {
		return m_iDayLight;
	}



	public void setM_iDayLight(int m_iDayLight) {
		this.m_iDayLight = m_iDayLight;
	}



	public int getM_iRevLight() {
		return m_iRevLight;
	}



	public void setM_iRevLight(int m_iRevLight) {
		this.m_iRevLight = m_iRevLight;
	}



	public int getM_iStopLight() {
		return m_iStopLight;
	}



	public void setM_iStopLight(int m_iStopLight) {
		this.m_iStopLight = m_iStopLight;
	}



	public int getM_iRearflogLight() {
		return m_iRearflogLight;
	}



	public void setM_iRearflogLight(int m_iRearflogLight) {
		this.m_iRearflogLight = m_iRearflogLight;
	}



	public int getM_iFrontflogLight() {
		return m_iFrontflogLight;
	}



	public void setM_iFrontflogLight(int m_iFrontflogLight) {
		this.m_iFrontflogLight = m_iFrontflogLight;
	}



	public int getM_iWideLight() {
		return m_iWideLight;
	}



	public void setM_iWideLight(int m_iWideLight) {
		this.m_iWideLight = m_iWideLight;
	}



	public int getM_iFarLight() {
		return m_iFarLight;
	}



	public void setM_iFarLight(int m_iFarLight) {
		this.m_iFarLight = m_iFarLight;
	}



	public int getM_iNearLight() {
		return m_iNearLight;
	}



	public void setM_iNearLight(int m_iNearLight) {
		this.m_iNearLight = m_iNearLight;
	}



	public int getM_iDoubleLight() {
		return m_iDoubleLight;
	}



	public void setM_iDoubleLight(int m_iDoubleLight) {
		this.m_iDoubleLight = m_iDoubleLight;
	}



	public int getM_iLeftTrunLight() {
		return m_iLeftTrunLight;
	}



	public void setM_iLeftTrunLight(int m_iLeftTrunLight) {
		this.m_iLeftTrunLight = m_iLeftTrunLight;
	}



	public int getM_iRightTrunLight() {
		return m_iRightTrunLight;
	}



	public void setM_iRightTrunLight(int m_iRightTrunLight) {
		this.m_iRightTrunLight = m_iRightTrunLight;
	}



	public int getM_iLeftTrunFlogLight() {
		return m_iLeftTrunFlogLight;
	}



	public void setM_iLeftTrunFlogLight(int m_iLeftTrunFlogLight) {
		this.m_iLeftTrunFlogLight = m_iLeftTrunFlogLight;
	}



	public int getM_iRightTrunFlogLight() {
		return m_iRightTrunFlogLight;
	}



	public void setM_iRightTrunFlogLight(int m_iRightTrunFlogLight) {
		this.m_iRightTrunFlogLight = m_iRightTrunFlogLight;
	}



	public int getM_iLightData() {
		return m_iLightData;
	}



	public void setM_iLightData(int m_iLightData) {
		this.m_iLightData = m_iLightData;
	}



	public int getM_iGear() {
		return m_iGear;
	}



	public void setM_iGear(int m_iGear) {
		this.m_iGear = m_iGear;
	}



	public int getM_iLFSafebelt() {
		return m_iLFSafebelt;
	}



	public void setM_iLFSafebelt(int m_iLFSafebelt) {
		this.m_iLFSafebelt = m_iLFSafebelt;
	}



	public int getM_iRFSafebelt() {
		return m_iRFSafebelt;
	}



	public void setM_iRFSafebelt(int m_iRFSafebelt) {
		this.m_iRFSafebelt = m_iRFSafebelt;
	}



	public int getM_iLRSafebelt() {
		return m_iLRSafebelt;
	}



	public void setM_iLRSafebelt(int m_iLRSafebelt) {
		this.m_iLRSafebelt = m_iLRSafebelt;
	}



	public int getM_iRRSafebelt() {
		return m_iRRSafebelt;
	}



	public void setM_iRRSafebelt(int m_iRRSafebelt) {
		this.m_iRRSafebelt = m_iRRSafebelt;
	}



	public int getM_iRMRSafebelt() {
		return m_iRMRSafebelt;
	}



	public void setM_iRMRSafebelt(int m_iRMRSafebelt) {
		this.m_iRMRSafebelt = m_iRMRSafebelt;
	}



	public int getM_iLFDoorlock() {
		return m_iLFDoorlock;
	}



	public void setM_iLFDoorlock(int m_iLFDoorlock) {
		this.m_iLFDoorlock = m_iLFDoorlock;
	}



	public int getM_iRFDoorlock() {
		return m_iRFDoorlock;
	}



	public void setM_iRFDoorlock(int m_iRFDoorlock) {
		this.m_iRFDoorlock = m_iRFDoorlock;
	}



	public int getM_iLRDoorLock() {
		return m_iLRDoorLock;
	}



	public void setM_iLRDoorLock(int m_iLRDoorLock) {
		this.m_iLRDoorLock = m_iLRDoorLock;
	}



	public int getM_iRRDoorlock() {
		return m_iRRDoorlock;
	}



	public void setM_iRRDoorlock(int m_iRRDoorlock) {
		this.m_iRRDoorlock = m_iRRDoorlock;
	}



	public int getM_iRemotelock() {
		return m_iRemotelock;
	}



	public void setM_iRemotelock(int m_iRemotelock) {
		this.m_iRemotelock = m_iRemotelock;
	}



	public int getM_iInnerlock() {
		return m_iInnerlock;
	}



	public void setM_iInnerlock(int m_iInnerlock) {
		this.m_iInnerlock = m_iInnerlock;
	}



	public float getM_fInstantSpeed() {
		return m_fInstantSpeed;
	}



	public void setM_fInstantSpeed(float m_fInstantSpeed) {
		this.m_fInstantSpeed = m_fInstantSpeed;
	}



	public int getM_iEngineSpeed() {
		return m_iEngineSpeed;
	}



	public void setM_iEngineSpeed(int m_iEngineSpeed) {
		this.m_iEngineSpeed = m_iEngineSpeed;
	}



	public float getM_fBatteryVol() {
		return m_fBatteryVol;
	}



	public void setM_fBatteryVol(float m_fBatteryVol) {
		this.m_fBatteryVol = m_fBatteryVol;
	}



	public int getM_iFuelUnit() {
		return m_iFuelUnit;
	}



	public void setM_iFuelUnit(int m_iFuelUnit) {
		this.m_iFuelUnit = m_iFuelUnit;
	}



	public int getM_iCooltemp() {
		return m_iCooltemp;
	}



	public void setM_iCooltemp(int m_iCooltemp) {
		this.m_iCooltemp = m_iCooltemp;
	}



	public float getM_fInstantFuel() {
		return m_fInstantFuel;
	}



	public void setM_fInstantFuel(float m_fInstantFuel) {
		this.m_fInstantFuel = m_fInstantFuel;
	}



	public int getM_iBaroPressure() {
		return m_iBaroPressure;
	}



	public void setM_iBaroPressure(int m_iBaroPressure) {
		this.m_iBaroPressure = m_iBaroPressure;
	}



	public int getM_iDamper() {
		return m_iDamper;
	}



	public void setM_iDamper(int m_iDamper) {
		this.m_iDamper = m_iDamper;
	}



	public int getM_iSWAAngle() {
		return m_iSWAAngle;
	}



	public void setM_iSWAAngle(int m_iSWAAngle) {
		this.m_iSWAAngle = m_iSWAAngle;
	}



	public int getM_iRev() {
		return m_iRev;
	}



	public void setM_iRev(int m_iRev) {
		this.m_iRev = m_iRev;
	}



	public int getM_iHaveRadar() {
		return m_iHaveRadar;
	}



	public void setM_iHaveRadar(int m_iHaveRadar) {
		this.m_iHaveRadar = m_iHaveRadar;
	}



	public int getM_iRLRadar() {
		return m_iRLRadar;
	}



	public void setM_iRLRadar(int m_iRLRadar) {
		this.m_iRLRadar = m_iRLRadar;
	}



	public int getM_iRMLRadar() {
		return m_iRMLRadar;
	}



	public void setM_iRMLRadar(int m_iRMLRadar) {
		this.m_iRMLRadar = m_iRMLRadar;
	}



	public int getM_iRMRRadar() {
		return m_iRMRRadar;
	}



	public void setM_iRMRRadar(int m_iRMRRadar) {
		this.m_iRMRRadar = m_iRMRRadar;
	}



	public int getM_iRRRadar() {
		return m_iRRRadar;
	}



	public void setM_iRRRadar(int m_iRRRadar) {
		this.m_iRRRadar = m_iRRRadar;
	}



	public int getM_iFLRadar() {
		return m_iFLRadar;
	}



	public void setM_iFLRadar(int m_iFLRadar) {
		this.m_iFLRadar = m_iFLRadar;
	}



	public int getM_iFMLRadar() {
		return m_iFMLRadar;
	}



	public void setM_iFMLRadar(int m_iFMLRadar) {
		this.m_iFMLRadar = m_iFMLRadar;
	}



	public int getM_iFMRRadar() {
		return m_iFMRRadar;
	}



	public void setM_iFMRRadar(int m_iFMRRadar) {
		this.m_iFMRRadar = m_iFMRRadar;
	}



	public int getM_iFRRadar() {
		return m_iFRRadar;
	}



	public void setM_iFRRadar(int m_iFRRadar) {
		this.m_iFRRadar = m_iFRRadar;
	}



	public int getM_iTotalMileage() {
		return m_iTotalMileage;
	}



	public void setM_iTotalMileage(int m_iTotalMileage) {
		this.m_iTotalMileage = m_iTotalMileage;
	}



	public int getM_iResidualOil() {
		return m_iResidualOil;
	}



	public void setM_iResidualOil(int m_iResidualOil) {
		this.m_iResidualOil = m_iResidualOil;
	}



	public int getM_iMileage() {
		return m_iMileage;
	}



	public void setM_iMileage(int m_iMileage) {
		this.m_iMileage = m_iMileage;
	}



	public int getM_iTripmeter_mileage() {
		return m_iTripmeter_mileage;
	}



	public void setM_iTripmeter_mileage(int m_iTripmeter_mileage) {
		this.m_iTripmeter_mileage = m_iTripmeter_mileage;
	}



	public int getM_fTripmeter_avgspeed() {
		return m_fTripmeter_avgspeed;
	}



	public void setM_fTripmeter_avgspeed(int m_fTripmeter_avgspeed) {
		this.m_fTripmeter_avgspeed = m_fTripmeter_avgspeed;
	}



	public int getM_iTripmeter_drivertime() {
		return m_iTripmeter_drivertime;
	}



	public void setM_iTripmeter_drivertime(int m_iTripmeter_drivertime) {
		this.m_iTripmeter_drivertime = m_iTripmeter_drivertime;
	}



	public float getM_fTripmeter_avgfuel() {
		return m_fTripmeter_avgfuel;
	}



	public void setM_fTripmeter_avgfuel(float m_fTripmeter_avgfuel) {
		this.m_fTripmeter_avgfuel = m_fTripmeter_avgfuel;
	}



	public int getM_iSelfstart_mileage() {
		return m_iSelfstart_mileage;
	}



	public void setM_iSelfstart_mileage(int m_iSelfstart_mileage) {
		this.m_iSelfstart_mileage = m_iSelfstart_mileage;
	}



	public int getM_fSelfstart_avgspeed() {
		return m_fSelfstart_avgspeed;
	}



	public void setM_fSelfstart_avgspeed(int m_fSelfstart_avgspeed) {
		this.m_fSelfstart_avgspeed = m_fSelfstart_avgspeed;
	}



	public int getM_iSelfstart_drivertime() {
		return m_iSelfstart_drivertime;
	}



	public void setM_iSelfstart_drivertime(int m_iSelfstart_drivertime) {
		this.m_iSelfstart_drivertime = m_iSelfstart_drivertime;
	}



	public float getM_fSelfstart_avgfuel() {
		return m_fSelfstart_avgfuel;
	}



	public void setM_fSelfstart_avgfuel(float m_fSelfstart_avgfuel) {
		this.m_fSelfstart_avgfuel = m_fSelfstart_avgfuel;
	}



	public int getM_iHtpmsWarn() {
		return m_iHtpmsWarn;
	}



	public void setM_iHtpmsWarn(int m_iHtpmsWarn) {
		this.m_iHtpmsWarn = m_iHtpmsWarn;
	}



	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
		out.writeInt(m_iVINAble);
		out.writeInt(m_iMadeTimeAble);
		out.writeInt(m_iModelAble);
		out.writeInt(m_iBrandAble);
		out.writeInt(m_iGearAble);
		out.writeInt(m_iLightBlueAble);
		out.writeInt(m_iInnerLightAble);
		out.writeInt(m_iOutLightAble);
		out.writeInt(m_iHandbrakeAble);
		out.writeInt(m_iBaseStateAble);
		out.writeInt(m_iWindowAble);
		out.writeInt(m_iDoorAble);
		
		out.writeInt(m_iLRTrunAble);
		out.writeInt(m_iDamperAble);
		out.writeInt(m_iBaroPressureAble);
		out.writeInt(m_iInstantFuelAble);
		out.writeInt(m_iCooltempAble);
		out.writeInt(m_iBatteryVolAble);
		out.writeInt(m_iEnigineSpeedAble);
		out.writeInt(m_iSpeedAble);
		
		out.writeInt(m_iReverseGearAble);
		out.writeInt(m_iSteerAngleAble);
		
		out.writeInt(m_iFaultWarnAble);
		
		out.writeInt(m_iFrontRadarAble);
		out.writeInt(m_iRearRadarAble);
		out.writeInt(m_iHaveRadarAble);
		
		out.writeInt(m_iSelfStartMileageAble);
		out.writeInt(m_iLittleTripAble);
		out.writeInt(m_iMileageAble);
		out.writeInt(m_iResidualOilAble);
		out.writeInt(m_iTotalMileageAble);
		
		out.writeInt(m_iLFGatelockAble);
		out.writeInt(m_iRFGatelockAble);
		out.writeInt(m_iLRGatelockAble);
		out.writeInt(m_iRRGatelockAble);
		out.writeInt(m_iRemoteoutlockAble);
		out.writeInt(m_iInnerlockAble);
		
		out.writeInt(m_iLFSafebeltAble);
		out.writeInt(m_iRFSafebeltAble);
		out.writeInt(m_iLRSafebeltAble);
		out.writeInt(m_iRRSafebeltAble);
		out.writeInt(m_iRMRSafebeltAble);
		
		out.writeInt(m_iFootbrakeAble);
		out.writeInt(m_iILLAble);
		out.writeInt(m_iACCAble);
		out.writeInt(m_iFireAble);
		out.writeInt(m_iLockAble);
		
		out.writeString(m_strBrand);
		out.writeString(m_strModel);
		out.writeString(m_strMadeTime);
		out.writeString(m_strVIN);
		
		out.writeInt(m_iHood);
		out.writeInt(m_iTailBox);
		out.writeInt(m_iRRDoor);
		out.writeInt(m_iLRDoor);
		out.writeInt(m_iRFDoor);
		out.writeInt(m_iLFDoor);
		out.writeInt(m_iDormer);
		out.writeInt(m_iRRWindow);
		out.writeInt(m_iLRWindow);
		out.writeInt(m_iRFWindow);
		out.writeInt(m_iLFWindow);
		out.writeInt(m_iFootBrake);
		out.writeInt(m_iILL);
		out.writeInt(m_iACC);
		out.writeInt(m_iFire);
		out.writeInt(m_iHanderBrake);
		out.writeInt(m_iLock);
		out.writeInt(m_iDayLight);
		out.writeInt(m_iRevLight);
		out.writeInt(m_iStopLight);
		out.writeInt(m_iRearflogLight);
		out.writeInt(m_iFrontflogLight);
		out.writeInt(m_iWideLight);
		out.writeInt(m_iFarLight);
		out.writeInt(m_iNearLight);
		out.writeInt(m_iDoubleLight);
		out.writeInt(m_iLeftTrunLight);
		out.writeInt(m_iRightTrunLight);
		out.writeInt(m_iLeftTrunFlogLight);
		out.writeInt(m_iRightTrunFlogLight);
		out.writeInt(m_iLightData);
		out.writeInt(m_iGear);
		out.writeInt(m_iLFSafebelt);
		out.writeInt(m_iRFSafebelt);
		out.writeInt(m_iLRSafebelt);
		out.writeInt(m_iRRSafebelt);
		out.writeInt(m_iRMRSafebelt);
		out.writeInt(m_iLFDoorlock);
		out.writeInt(m_iRFDoorlock);
		out.writeInt(m_iLRDoorLock);
		out.writeInt(m_iRRDoorlock);
		out.writeInt(m_iRemotelock);
		out.writeInt(m_iInnerlock);
		
		out.writeFloat(m_fInstantSpeed);
		out.writeInt(m_iEngineSpeed);
		out.writeFloat(m_fBatteryVol);
		out.writeInt(m_iFuelUnit);
		out.writeInt(m_iCooltemp);
		out.writeFloat(m_fInstantFuel);
		out.writeInt(m_iBaroPressure);
		out.writeInt(m_iDamper);
		
		out.writeInt(m_iSWAAngle);
		out.writeInt(m_iRev);
		
		out.writeInt(m_iHaveRadar);
		out.writeInt(m_iRLRadar);
		out.writeInt(m_iRMLRadar);
		out.writeInt(m_iRMRRadar);
		out.writeInt(m_iRRRadar);
		out.writeInt(m_iFLRadar);
		out.writeInt(m_iFMLRadar);
		out.writeInt(m_iFMRRadar);
		out.writeInt(m_iFRRadar);
		
		out.writeInt(m_iTotalMileage);
		out.writeInt(m_iResidualOil);
		out.writeInt(m_iMileage);
		out.writeInt(m_iTripmeter_mileage);
		out.writeInt(m_fTripmeter_avgspeed);
		out.writeInt(m_iTripmeter_drivertime);
		out.writeFloat(m_fTripmeter_avgfuel);
		out.writeInt(m_iSelfstart_mileage);
		out.writeInt(m_fSelfstart_avgspeed);
		out.writeInt(m_iSelfstart_drivertime);
		out.writeFloat(m_fSelfstart_avgfuel);
		
		out.writeInt(m_iHtpmsWarn);
	}
	
	public static final Parcelable.Creator<CarPcInfo> CREATOR = new Creator<CarPcInfo>(){

		@Override
		public CarPcInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			INSTANCE.setM_iVINAble(source.readInt());
			INSTANCE.setM_iMadeTimeAble(source.readInt());
			INSTANCE.setM_iModelAble(source.readInt());
			INSTANCE.setM_iBrandAble(source.readInt());
			
			INSTANCE.setM_iGearAble(source.readInt());
			INSTANCE.setM_iLightBlueAble(source.readInt());
			INSTANCE.setM_iInnerLightAble(source.readInt());
			INSTANCE.setM_iOutLightAble(source.readInt());
			INSTANCE.setM_iHandbrakeAble(source.readInt());
			INSTANCE.setM_iBaseStateAble(source.readInt());
			INSTANCE.setM_iWindowAble(source.readInt());
			INSTANCE.setM_iDoorAble(source.readInt());
			
			INSTANCE.setM_iLRTrunAble(source.readInt());
			INSTANCE.setM_iDamperAble(source.readInt());
			INSTANCE.setM_iBaroPressureAble(source.readInt());
			INSTANCE.setM_iInstantFuelAble(source.readInt());
			INSTANCE.setM_iCooltempAble(source.readInt());
			INSTANCE.setM_iBatteryVolAble(source.readInt());
			INSTANCE.setM_iEnigineSpeedAble(source.readInt());
			INSTANCE.setM_iSpeedAble(source.readInt());
			
			INSTANCE.setM_iReverseGearAble(source.readInt());
			INSTANCE.setM_iSteerAngleAble(source.readInt());
			
			INSTANCE.setM_iFaultWarnAble(source.readInt());
			
			INSTANCE.setM_iFrontRadarAble(source.readInt());
			INSTANCE.setM_iRearRadarAble(source.readInt());
			INSTANCE.setM_iHaveRadarAble(source.readInt());
			
			INSTANCE.setM_iSelfStartMileageAble(source.readInt());
			INSTANCE.setM_iLittleTripAble(source.readInt());
			INSTANCE.setM_iMileageAble(source.readInt());
			INSTANCE.setM_iResidualOilAble(source.readInt());
			INSTANCE.setM_iTotalMileageAble(source.readInt());
			
			INSTANCE.setM_iLFGatelockAble(source.readInt());
			INSTANCE.setM_iRFGatelockAble(source.readInt());
			INSTANCE.setM_iLRGatelockAble(source.readInt());
			INSTANCE.setM_iRRGatelockAble(source.readInt());
			INSTANCE.setM_iRemoteoutlockAble(source.readInt());
			INSTANCE.setM_iInnerlockAble(source.readInt());
			
			INSTANCE.setM_iLFSafebeltAble(source.readInt());
			INSTANCE.setM_iRFSafebeltAble(source.readInt());
			INSTANCE.setM_iLRSafebeltAble(source.readInt());
			INSTANCE.setM_iRRSafebeltAble(source.readInt());
			INSTANCE.setM_iRMRSafebeltAble(source.readInt());
			
			INSTANCE.setM_iFootbrakeAble(source.readInt());
			INSTANCE.setM_iILLAble(source.readInt());
			INSTANCE.setM_iACCAble(source.readInt());
			INSTANCE.setM_iFireAble(source.readInt());
			INSTANCE.setM_iLockAble(source.readInt());
			
			INSTANCE.setM_strBrand(source.readString());
			INSTANCE.setM_strModel(source.readString());
			INSTANCE.setM_strMadeTime(source.readString());
			INSTANCE.setM_strVIN(source.readString());
			
			INSTANCE.setM_iHood(source.readInt());
			INSTANCE.setM_iTailBox(source.readInt());
			INSTANCE.setM_iRRDoor(source.readInt());
			INSTANCE.setM_iLRDoor(source.readInt());
			INSTANCE.setM_iRFDoor(source.readInt());
			INSTANCE.setM_iLFDoor(source.readInt());
			
			INSTANCE.setM_iDormer(source.readInt());
			INSTANCE.setM_iRRWindow(source.readInt());
			INSTANCE.setM_iLRWindow(source.readInt());
			INSTANCE.setM_iRFWindow(source.readInt());
			INSTANCE.setM_iLFWindow(source.readInt());
			
			INSTANCE.setM_iFootBrake(source.readInt());
			INSTANCE.setM_iILL(source.readInt());
			INSTANCE.setM_iACC(source.readInt());
			INSTANCE.setM_iFire(source.readInt());
			INSTANCE.setM_iHanderBrake(source.readInt());
			INSTANCE.setM_iLock(source.readInt());
			
			INSTANCE.setM_iDayLight(source.readInt());
			INSTANCE.setM_iRevLight(source.readInt());
			INSTANCE.setM_iStopLight(source.readInt());
			INSTANCE.setM_iRearflogLight(source.readInt());
			INSTANCE.setM_iFrontflogLight(source.readInt());
			INSTANCE.setM_iWideLight(source.readInt());
			INSTANCE.setM_iFarLight(source.readInt());
			INSTANCE.setM_iNearLight(source.readInt());
			INSTANCE.setM_iDoubleLight(source.readInt());
			INSTANCE.setM_iLeftTrunLight(source.readInt());
			INSTANCE.setM_iRightTrunLight(source.readInt());
			INSTANCE.setM_iLeftTrunFlogLight(source.readInt());
			INSTANCE.setM_iRightTrunFlogLight(source.readInt());
			
			INSTANCE.setM_iLightData(source.readInt());
			INSTANCE.setM_iGear(source.readInt());
			INSTANCE.setM_iLFSafebelt(source.readInt());
			INSTANCE.setM_iRFSafebelt(source.readInt());
			INSTANCE.setM_iLRSafebelt(source.readInt());
			INSTANCE.setM_iRRSafebelt(source.readInt());
			INSTANCE.setM_iRMRSafebelt(source.readInt());
			
			INSTANCE.setM_iLFDoorlock(source.readInt());
			INSTANCE.setM_iRFDoorlock(source.readInt());
			INSTANCE.setM_iLRDoorLock(source.readInt());
			INSTANCE.setM_iRRDoorlock(source.readInt());
			INSTANCE.setM_iRemotelock(source.readInt());
			INSTANCE.setM_iInnerlock(source.readInt());
			
			INSTANCE.setM_fInstantSpeed(source.readFloat());
			INSTANCE.setM_iEngineSpeed(source.readInt());
			INSTANCE.setM_fBatteryVol(source.readFloat());
			INSTANCE.setM_iFuelUnit(source.readInt());
			INSTANCE.setM_iCooltemp(source.readInt());
			INSTANCE.setM_fInstantFuel(source.readFloat());
			INSTANCE.setM_iBaroPressure(source.readInt());
			
			INSTANCE.setM_iDamper(source.readInt());
			INSTANCE.setM_iSWAAngle(source.readInt());
			INSTANCE.setM_iRev(source.readInt());
			
			INSTANCE.setM_iHaveRadar(source.readInt());
			INSTANCE.setM_iRLRadar(source.readInt());
			INSTANCE.setM_iRMLRadar(source.readInt());
			INSTANCE.setM_iRMRRadar(source.readInt());
			INSTANCE.setM_iRRRadar(source.readInt());
			INSTANCE.setM_iFLRadar(source.readInt());
			INSTANCE.setM_iFMLRadar(source.readInt());
			INSTANCE.setM_iFMRRadar(source.readInt());
			INSTANCE.setM_iFRRadar(source.readInt());
			
			INSTANCE.setM_iTotalMileage(source.readInt());
			INSTANCE.setM_iResidualOil(source.readInt());
			INSTANCE.setM_iMileage(source.readInt());
			INSTANCE.setM_iTripmeter_mileage(source.readInt());
			INSTANCE.setM_fTripmeter_avgspeed(source.readInt());
			INSTANCE.setM_iTripmeter_drivertime(source.readInt());
			INSTANCE.setM_fTripmeter_avgfuel(source.readFloat());
			INSTANCE.setM_iSelfstart_mileage(source.readInt());
			INSTANCE.setM_fSelfstart_avgspeed(source.readInt());
			INSTANCE.setM_iSelfstart_drivertime(source.readInt());
			INSTANCE.setM_fSelfstart_avgfuel(source.readFloat());
			
			INSTANCE.setM_iHtpmsWarn(source.readInt());

			return INSTANCE;
		}

		@Override
		public CarPcInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new CarPcInfo[size];
		}
		
	};

}
