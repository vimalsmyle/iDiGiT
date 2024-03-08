/**
 * 
 */
package com.idigitronics.iDiGiT.request.vo;

/**
 * @author K Vimal Kumar
 *
 */
public class MeterSizeRequestVO {

	private int meterSizeID;
	private String meterType;
	private int meterSize;
	private float perUnitValue;
	
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
	
}
