/**
 * 
 */
package com.idigitronics.iDiGiT.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class TariffRequestVO {

	
	private int tariffID;
	private float tariff;
	private String tariffName;
	private float emergencyCredit;
	private float alarmCredit;
	private float fixedCharges;
	private String RegisteredDate;
	
	public int getTariffID() {
		return tariffID;
	}
	public void setTariffID(int tariffID) {
		this.tariffID = tariffID;
	}
	public float getTariff() {
		return tariff;
	}
	public void setTariff(float tariff) {
		this.tariff = tariff;
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
	public float getFixedCharges() {
		return fixedCharges;
	}
	public void setFixedCharges(float fixedCharges) {
		this.fixedCharges = fixedCharges;
	}
	public String getRegisteredDate() {
		return RegisteredDate;
	}
	public void setRegisteredDate(String registeredDate) {
		RegisteredDate = registeredDate;
	}
	public String getTariffName() {
		return tariffName;
	}
	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}
	

}
