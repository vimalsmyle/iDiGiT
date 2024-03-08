/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

import java.util.List;

import com.idigitronics.iDiGiT.request.vo.MeterRequestVO;

/**
 * @author K VimaL Kumar
 *
 */
public class CustomerResponseVO {
	
	private long customerID;
	private String communityName;
	private String blockName;
	private String houseNumber;
	private String CustomerUniqueID;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String date;
	private String createdByUserName;
	private String createdByRoleDescription;
	private String userID;
	private int requestID;
	private boolean action;
	private MeterRequestVO meterDetails;
	
	
	private List<MeterRequestVO> meters;
	private List<CustomerResponseVO> data;
	
	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCreatedByUserName() {
		return createdByUserName;
	}
	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}
	public String getCreatedByRoleDescription() {
		return createdByRoleDescription;
	}
	public void setCreatedByRoleDescription(String createdByRoleDescription) {
		this.createdByRoleDescription = createdByRoleDescription;
	}
	public List<CustomerResponseVO> getData() {
		return data;
	}
	public void setData(List<CustomerResponseVO> data) {
		this.data = data;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public String getCustomerUniqueID() {
		return CustomerUniqueID;
	}
	public void setCustomerUniqueID(String customerUniqueID) {
		CustomerUniqueID = customerUniqueID;
	}
	public List<MeterRequestVO> getMeters() {
		return meters;
	}
	public void setMeters(List<MeterRequestVO> meters) {
		this.meters = meters;
	}
	public boolean isAction() {
		return action;
	}
	public void setAction(boolean action) {
		this.action = action;
	}
	public MeterRequestVO getMeterDetails() {
		return meterDetails;
	}
	public void setMeterDetails(MeterRequestVO meterDetails) {
		this.meterDetails = meterDetails;
	}
	
}
