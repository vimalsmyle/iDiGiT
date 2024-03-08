/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

/**
 * @author VmL
 *
 */
public class IndividualBillingResponseVO {
	
	private long billingID;
	private long customerMeterID;
	private String miuID;
	private String meterType;
	private float previousReading;
	private float presentReading;
	private float consumption;
	private float tariff;
	private float billAmount;
	private String billingDate;
	
	public long getBillingID() {
		return billingID;
	}
	public void setBillingID(long billingID) {
		this.billingID = billingID;
	}
	public long getCustomerMeterID() {
		return customerMeterID;
	}
	public void setCustomerMeterID(long customerMeterID) {
		this.customerMeterID = customerMeterID;
	}
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}
	public float getPreviousReading() {
		return previousReading;
	}
	public void setPreviousReading(float previousReading) {
		this.previousReading = previousReading;
	}
	public float getPresentReading() {
		return presentReading;
	}
	public void setPresentReading(float presentReading) {
		this.presentReading = presentReading;
	}
	public float getConsumption() {
		return consumption;
	}
	public void setConsumption(float consumption) {
		this.consumption = consumption;
	}
	public float getTariff() {
		return tariff;
	}
	public void setTariff(float tariff) {
		this.tariff = tariff;
	}
	public float getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
	}
	public String getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}
	public String getMeterType() {
		return meterType;
	}
	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}
	
}
