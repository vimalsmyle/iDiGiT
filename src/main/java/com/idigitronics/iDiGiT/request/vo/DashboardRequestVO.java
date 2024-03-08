/**
 * 
 */
package com.idigitronics.iDiGiT.request.vo;

/**
 * @author K Vimal Kumar
 *
 */
public class DashboardRequestVO {
	
	private String meterID;
	private float reading;
	private float balance;
	private float tariffAmount;
	private float emergencyCredit;
	private int batteryVoltage;
	private int meterType;
	private int tamperStatus;
	private int valveStatus;
	private int creditStatus;
	private int lowBattery;
	private int minutes;
	private int vacation;
	private String timeStamp;
	private String tamperTimeStamp;
	private String doorOpenTimeStamp;
	private String source; 
	
	public String getMeterID() {
		return meterID;
	}
	public void setMeterID(String meterID) {
		this.meterID = meterID;
	}
	public float getReading() {
		return reading;
	}
	public void setReading(float reading) {
		this.reading = reading;
	}
	public int getBatteryVoltage() {
		return batteryVoltage;
	}
	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}
	public int getMeterType() {
		return meterType;
	}
	public void setMeterType(int meterType) {
		this.meterType = meterType;
	}
	public int getTamperStatus() {
		return tamperStatus;
	}
	public void setTamperStatus(int tamperStatus) {
		this.tamperStatus = tamperStatus;
	}
	public int getValveStatus() {
		return valveStatus;
	}
	public void setValveStatus(int valveStatus) {
		this.valveStatus = valveStatus;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public float getTariffAmount() {
		return tariffAmount;
	}
	public void setTariffAmount(float tariffAmount) {
		this.tariffAmount = tariffAmount;
	}
	public float getEmergencyCredit() {
		return emergencyCredit;
	}
	public void setEmergencyCredit(float emergencyCredit) {
		this.emergencyCredit = emergencyCredit;
	}
	public int getCreditStatus() {
		return creditStatus;
	}
	public void setCreditStatus(int creditStatus) {
		this.creditStatus = creditStatus;
	}
	public int getLowBattery() {
		return lowBattery;
	}
	public void setLowBattery(int lowBattery) {
		this.lowBattery = lowBattery;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getVacation() {
		return vacation;
	}
	public void setVacation(int vacation) {
		this.vacation = vacation;
	}
	public String getTamperTimeStamp() {
		return tamperTimeStamp;
	}
	public void setTamperTimeStamp(String tamperTimeStamp) {
		this.tamperTimeStamp = tamperTimeStamp;
	}
	public String getDoorOpenTimeStamp() {
		return doorOpenTimeStamp;
	}
	public void setDoorOpenTimeStamp(String doorOpenTimeStamp) {
		this.doorOpenTimeStamp = doorOpenTimeStamp;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
}
