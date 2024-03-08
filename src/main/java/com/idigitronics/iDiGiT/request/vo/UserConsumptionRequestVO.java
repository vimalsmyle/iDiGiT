/**
 * 
 */
package com.idigitronics.iDiGiT.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class UserConsumptionRequestVO {

	private String customerUniqueID;
	private String fromDate;
	private String toDate;
	private int year;
	private int fromMonth;
	private int toMonth;
	
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
	public int getFromMonth() {
		return fromMonth;
	}
	public void setFromMonth(int fromMonth) {
		this.fromMonth = fromMonth;
	}
	public int getToMonth() {
		return toMonth;
	}
	public void setToMonth(int toMonth) {
		this.toMonth = toMonth;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
		
}
