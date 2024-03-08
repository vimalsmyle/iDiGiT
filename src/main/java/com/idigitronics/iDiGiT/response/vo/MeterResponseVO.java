/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class MeterResponseVO {
	
	private int meterID;
	private String miuID;
	private String meterSerialNumber;
	private String meterType;
	private int meterSize;
	private String payType;
	private String modifiedDate;
	
	private List<MeterResponseVO> data;
	
	public int getMeterID() {
		return meterID;
	}
	public void setMeterID(int meterID) {
		this.meterID = meterID;
	}
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
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
	public List<MeterResponseVO> getData() {
		return data;
	}
	public void setData(List<MeterResponseVO> data) {
		this.data = data;
	}
	public int getMeterSize() {
		return meterSize;
	}
	public void setMeterSize(int meterSize) {
		this.meterSize = meterSize;
	}
	
}
