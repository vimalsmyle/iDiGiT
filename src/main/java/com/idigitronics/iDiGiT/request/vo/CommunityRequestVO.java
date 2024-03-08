/**
 * 
 */
package com.idigitronics.iDiGiT.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class CommunityRequestVO {
	
	
	private String communityName;
	private String email;
	private String mobileNumber;
	private String address;
	private int tariffID;
	private int communityID;

	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getmobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCommunityID() {
		return communityID;
	}
	public void setCommunityID(int communityID) {
		this.communityID = communityID;
	}
	public int getTariffID() {
		return tariffID;
	}
	public void setTariffID(int tariffID) {
		this.tariffID = tariffID;
	}
	

}
