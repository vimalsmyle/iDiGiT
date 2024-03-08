/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class UserConsumptionReportsResponseVO {
	
	private String customerUniqueID;
	private String miuID;
	private float reading;
	private float reading1;
	private float reading2;
	private int consumption;
	private float balance;
	private float battery;
	private float tariff;
	private float alarmCredit;
	private float emergencyCredit;
	private String dateTime;
	private String sizeMeter;
	
	private List<UserConsumptionReportsResponseVO> data;
	
	public String getCustomerUniqueID() {
		return customerUniqueID;
	}
	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}
	public float getReading() {
		return reading;
	}
	public void setReading(float reading) {
		this.reading = reading;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public float getBattery() {
		return battery;
	}
	public void setBattery(float battery) {
		this.battery = battery;
	}
	public float getTariff() {
		return tariff;
	}
	public void setTariff(float tariff) {
		this.tariff = tariff;
	}
	public float getAlarmCredit() {
		return alarmCredit;
	}
	public void setAlarmCredit(float alarmCredit) {
		this.alarmCredit = alarmCredit;
	}
	public float getEmergencyCredit() {
		return emergencyCredit;
	}
	public void setEmergencyCredit(float emergencyCredit) {
		this.emergencyCredit = emergencyCredit;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public List<UserConsumptionReportsResponseVO> getData() {
		return data;
	}
	public void setData(List<UserConsumptionReportsResponseVO> data) {
		this.data = data;
	}
	public int getConsumption() {
		return consumption;
	}
	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}
	public String getSizeMeter() {
		return sizeMeter;
	}
	public void setSizeMeter(String sizeMeter) {
		this.sizeMeter = sizeMeter;
	}
	public float getReading1() {
		return reading1;
	}
	public void setReading1(float reading1) {
		this.reading1 = reading1;
	}
	public float getReading2() {
		return reading2;
	}
	public void setReading2(float reading2) {
		this.reading2 = reading2;
	}
	
}
