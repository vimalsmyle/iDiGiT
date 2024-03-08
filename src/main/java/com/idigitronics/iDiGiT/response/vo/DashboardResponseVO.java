/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 * 
 */
public class DashboardResponseVO {

	private String communityName;
	private String blockName;
	private String HouseNumber;
	private String firstName;
	private String lastName;
	private String customerUniqueID;
	private int nonCommunicating;
	private int communicating;
	private int total;
	
	private List<IndividualDashboardResponseVO> dasboarddata;
	private List<DashboardResponseVO> data;

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
		return HouseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		HouseNumber = houseNumber;
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


	public List<DashboardResponseVO> getData() {
		return data;
	}

	public void setData(List<DashboardResponseVO> data) {
		this.data = data;
	}

	public int getNonCommunicating() {
		return nonCommunicating;
	}

	public void setNonCommunicating(int nonCommunicating) {
		this.nonCommunicating = nonCommunicating;
	}

	public int getCommunicating() {
		return communicating;
	}

	public void setCommunicating(int communicating) {
		this.communicating = communicating;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getCustomerUniqueID() {
		return customerUniqueID;
	}

	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}

	public List<IndividualDashboardResponseVO> getDasboarddata() {
		return dasboarddata;
	}

	public void setDasboarddata(List<IndividualDashboardResponseVO> dasboarddata) {
		this.dasboarddata = dasboarddata;
	}

}
