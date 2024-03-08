/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

import java.util.List;

/**
 * @author K Vimal Kumar
 *
 */
public class MeterSizeResponseVO {
	
	private int meterSizeID;
	private String meterType;
	private int meterSize;
	private float perUnitValue;
	private String modifiedDate;
	
	private List<MeterSizeResponseVO> data;
	
	
	public int getMeterSizeID() {
		return meterSizeID;
	}
	public void setMeterSizeID(int meterSizeID) {
		this.meterSizeID = meterSizeID;
	}
	public String getMeterType() {
		return meterType;
	}
	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}
	public int getMeterSize() {
		return meterSize;
	}
	public void setMeterSize(int meterSize) {
		this.meterSize = meterSize;
	}
	public float getPerUnitValue() {
		return perUnitValue;
	}
	public void setPerUnitValue(float perUnitValue) {
		this.perUnitValue = perUnitValue;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public List<MeterSizeResponseVO> getData() {
		return data;
	}
	public void setData(List<MeterSizeResponseVO> data) {
		this.data = data;
	}
	
}
