/**
 * 
 */
package com.idigitronics.iDiGiT.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class AlarmRequestVO {
	
	private String customerUniqueID;
	private String fromDate;
	private String toDate;
	
	public String getCustomerUniqueID() {
		return customerUniqueID;
	}
	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
}
