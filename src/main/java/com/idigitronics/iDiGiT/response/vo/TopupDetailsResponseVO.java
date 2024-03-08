/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class TopupDetailsResponseVO {
	
	private String miuID;
	private String meterSerialNumber;
	private float currentBalance;
	private String tariffName;
	private float emergencyCredit;
	private float alarmCredit;
	private float tariff;
	private int tariffID;
	private String CustomerUniqueID;
	private int reconnectionCharges;
	private int fixedCharges;
	private int noOfMonths;
	private long customerMeterID;
	
	public float getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(float currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getTariffName() {
		return tariffName;
	}
	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}
	public float getEmergencyCredit() {
		return emergencyCredit;
	}
	public void setEmergencyCredit(float emergencyCredit) {
		this.emergencyCredit = emergencyCredit;
	}
	public float getAlarmCredit() {
		return alarmCredit;
	}
	public void setAlarmCredit(float alarmCredit) {
		this.alarmCredit = alarmCredit;
	}
	public float getTariff() {
		return tariff;
	}
	public void setTariff(float tariff) {
		this.tariff = tariff;
	}
	public int getTariffID() {
		return tariffID;
	}
	public void setTariffID(int tariffID) {
		this.tariffID = tariffID;
	}
	public int getReconnectionCharges() {
		return reconnectionCharges;
	}
	public void setReconnectionCharges(int reconnectionCharges) {
		this.reconnectionCharges = reconnectionCharges;
	}
	public int getFixedCharges() {
		return fixedCharges;
	}
	public void setFixedCharges(int fixedCharges) {
		this.fixedCharges = fixedCharges;
	}
	public int getNoOfMonths() {
		return noOfMonths;
	}
	public void setNoOfMonths(int noOfMonths) {
		this.noOfMonths = noOfMonths;
	}
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}
	public String getCustomerUniqueID() {
		return CustomerUniqueID;
	}
	public void setCustomerUniqueID(String customerUniqueID) {
		CustomerUniqueID = customerUniqueID;
	}
	public long getCustomerMeterID() {
		return customerMeterID;
	}
	public void setCustomerMeterID(long customerMeterID) {
		this.customerMeterID = customerMeterID;
	}
	public String getMeterSerialNumber() {
		return meterSerialNumber;
	}
	public void setMeterSerialNumber(String meterSerialNumber) {
		this.meterSerialNumber = meterSerialNumber;
	}
	
}
