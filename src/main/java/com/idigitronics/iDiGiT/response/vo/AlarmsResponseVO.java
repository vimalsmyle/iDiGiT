/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class AlarmsResponseVO {
	
	private String communityName;
	private String blockName;
	private String houseNumber;
	private String customerUniqueID;
	
	private String miuID;
	private String dateTime;
	private String doorOpenTamper;
	private String magneticTamper;
	private String nfcTamper;
	private String batteryVoltage;
	private String lowBattery;
	private String lowBalance;
	private long difference;
	private String solonideStatus;
	private String batteryColor;
	
	private List<IndividualAlarmsResponseVO> alarms;
	private List<AlarmsResponseVO> data;

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCustomerUniqueID() {
		return customerUniqueID;
	}

	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}

	public List<IndividualAlarmsResponseVO> getAlarms() {
		return alarms;
	}

	public void setAlarms(List<IndividualAlarmsResponseVO> alarms) {
		this.alarms = alarms;
	}

	public List<AlarmsResponseVO> getData() {
		return data;
	}

	public void setData(List<AlarmsResponseVO> data) {
		this.data = data;
	}

	public String getMiuID() {
		return miuID;
	}

	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getDoorOpenTamper() {
		return doorOpenTamper;
	}

	public void setDoorOpenTamper(String doorOpenTamper) {
		this.doorOpenTamper = doorOpenTamper;
	}

	public String getMagneticTamper() {
		return magneticTamper;
	}

	public void setMagneticTamper(String magneticTamper) {
		this.magneticTamper = magneticTamper;
	}

	public String getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(String batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public String getLowBattery() {
		return lowBattery;
	}

	public void setLowBattery(String lowBattery) {
		this.lowBattery = lowBattery;
	}

	public String getLowBalance() {
		return lowBalance;
	}

	public void setLowBalance(String lowBalance) {
		this.lowBalance = lowBalance;
	}

	public long getDifference() {
		return difference;
	}

	public void setDifference(long difference) {
		this.difference = difference;
	}

	public String getSolonideStatus() {
		return solonideStatus;
	}

	public void setSolonideStatus(String solonideStatus) {
		this.solonideStatus = solonideStatus;
	}

	public String getBatteryColor() {
		return batteryColor;
	}

	public void setBatteryColor(String batteryColor) {
		this.batteryColor = batteryColor;
	}

	public String getNfcTamper() {
		return nfcTamper;
	}

	public void setNfcTamper(String nfcTamper) {
		this.nfcTamper = nfcTamper;
	}

}
