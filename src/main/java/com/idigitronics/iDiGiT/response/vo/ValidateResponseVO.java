/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

/**
 * @author K Vimal Kumar
 *
 */
public class ValidateResponseVO {
	
	private boolean result;
	private long previousReading;
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public long getPreviousReading() {
		return previousReading;
	}
	public void setPreviousReading(long previousReading) {
		this.previousReading = previousReading;
	}
	
}
