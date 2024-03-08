/**
 * 
 */
package com.idigitronics.iDiGiT.request.vo;

import java.util.HashMap;

/**
 * @author K VimaL Kumar
 *
 */
public class MeterRequestVO {

	private long customerMeterID;
	private String miuID;
	private String meterSerialNumber;
	private String meterType;
	private float meterSize;
	private int meterSizeID;
	private Integer meterIDSize;
	private String payType;
	private int tariffID;
	private String tariff;
	private int gatewayID;
	private String gatewayName;
	private String location;
	private float thresholdMaximum;
	private float thresholdMinimum;
	private String tariffName;
	private String availableBalance;
	private String modifiedDate;
	private HashMap<Integer, Integer> gasDropdown;
	private HashMap<Integer, Integer> waterDropdown;
	private String emergencyCredit;
	
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
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
	public String getMeterType() {
		return meterType;
	}
	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public float getMeterSize() {
		return meterSize;
	}
	public void setMeterSize(float meterSize) {
		this.meterSize = meterSize;
	}
	public int getTariffID() {
		return tariffID;
	}
	public void setTariffID(int tariffID) {
		this.tariffID = tariffID;
	}
	public String getTariffName() {
		return tariffName;
	}
	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getGatewayID() {
		return gatewayID;
	}
	public void setGatewayID(int gatewayID) {
		this.gatewayID = gatewayID;
	}
	public String getGatewayName() {
		return gatewayName;
	}
	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}
	public int getMeterSizeID() {
		return meterSizeID;
	}
	public void setMeterSizeID(int meterSizeID) {
		this.meterSizeID = meterSizeID;
	}
	public float getThresholdMaximum() {
		return thresholdMaximum;
	}
	public void setThresholdMaximum(float thresholdMaximum) {
		this.thresholdMaximum = thresholdMaximum;
	}
	public float getThresholdMinimum() {
		return thresholdMinimum;
	}
	public void setThresholdMinimum(float thresholdMinimum) {
		this.thresholdMinimum = thresholdMinimum;
	}
	public HashMap<Integer, Integer> getGasDropdown() {
		return gasDropdown;
	}
	public void setGasDropdown(HashMap<Integer, Integer> gasDropdown) {
		this.gasDropdown = gasDropdown;
	}
	public HashMap<Integer, Integer> getWaterDropdown() {
		return waterDropdown;
	}
	public void setWaterDropdown(HashMap<Integer, Integer> waterDropdown) {
		this.waterDropdown = waterDropdown;
	}
	public Integer getMeterIDSize() {
		return meterIDSize;
	}
	public void setMeterIDSize(Integer meterIDSize) {
		this.meterIDSize = meterIDSize;
	}
	public String getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}
	public String getTariff() {
		return tariff;
	}
	public void setTariff(String tariff) {
		this.tariff = tariff;
	}
	public String getEmergencyCredit() {
		return emergencyCredit;
	}
	public void setEmergencyCredit(String emergencyCredit) {
		this.emergencyCredit = emergencyCredit;
	}
	
}
