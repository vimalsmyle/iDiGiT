/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

/**
 * @author K Vimal Kumar
 *
 */
public class IndividualAlarmsResponseVO {
	
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
