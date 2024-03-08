/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class VacationResponseVO {

	private String communityName;
	private String blockName;
	private String houseNumber;
	private String customerUniqueID;
	private String firstName;
	private String lastName;
	private String miuID;
	private String startDate;
	private String endDate;
	private String startDateForEdit;
	private String endDateForEdit;
	private String registeredDate;
	private long vacationID;
	private String vacationName;
	private String source;
	private String mode;
	private String status;
	
	private List<VacationResponseVO> data;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}

	public List<VacationResponseVO> getData() {
		return data;
	}

	public void setData(List<VacationResponseVO> data) {
		this.data = data;
	}

	public String getCustomerUniqueID() {
		return customerUniqueID;
	}

	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}

	public String getMiuID() {
		return miuID;
	}

	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}

	public long getVacationID() {
		return vacationID;
	}

	public void setVacationID(long vacationID) {
		this.vacationID = vacationID;
	}

	public String getVacationName() {
		return vacationName;
	}

	public void setVacationName(String vacationName) {
		this.vacationName = vacationName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartDateForEdit() {
		return startDateForEdit;
	}

	public void setStartDateForEdit(String startDateForEdit) {
		this.startDateForEdit = startDateForEdit;
	}

	public String getEndDateForEdit() {
		return endDateForEdit;
	}

	public void setEndDateForEdit(String endDateForEdit) {
		this.endDateForEdit = endDateForEdit;
	}

}
