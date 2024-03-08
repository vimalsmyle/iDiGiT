/**
 * 
 */
package com.idigitronics.iDiGiT.request.vo;

/**
 * @author K Vimal Kumar
 *
 */
public class BillSummaryRequestVO {
	
	private int communityID;
	private int blockID;
	private String customerUniqueID;
	private int fromMonth;
	private int toMonth;
	
	public int getCommunityID() {
		return communityID;
	}
	public void setCommunityID(int communityID) {
		this.communityID = communityID;
	}
	public int getBlockID() {
		return blockID;
	}
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}
	public String getCustomerUniqueID() {
		return customerUniqueID;
	}
	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
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

}
