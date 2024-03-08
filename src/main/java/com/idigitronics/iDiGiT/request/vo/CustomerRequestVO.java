/**
 * 
 */
package com.idigitronics.iDiGiT.request.vo;

import java.util.List;

/**
 * @author k VimaL Kumar
 *
 */
public class CustomerRequestVO {
	
	private long customerID;
	private int communityID;
	private int blockID;
	private String houseNumber;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private List<MeterRequestVO> meters;
	private String customerUniqueID;
	private int createdByID;
	private int loggedInRoleID;
	private String loggedInUserID;
	
	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
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
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public int getCreatedByID() {
		return createdByID;
	}
	public void setCreatedByID(int createdByID) {
		this.createdByID = createdByID;
	}
	public String getCustomerUniqueID() {
		return customerUniqueID;
	}
	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}
	public int getLoggedInRoleID() {
		return loggedInRoleID;
	}
	public void setLoggedInRoleID(int loggedInRoleID) {
		this.loggedInRoleID = loggedInRoleID;
	}
	public String getLoggedInUserID() {
		return loggedInUserID;
	}
	public void setLoggedInUserID(String loggedInUserID) {
		this.loggedInUserID = loggedInUserID;
	}
	public List<MeterRequestVO> getMeters() {
		return meters;
	}
	public void setMeters(List<MeterRequestVO> meters) {
		this.meters = meters;
	}
}
