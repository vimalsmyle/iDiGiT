/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class FinancialReportsResponseVO {
	
	private String communityName;
	private String blockName;
	private String houseNumber;
	private String miuID;
	private float totalAmount;
	private float totalAmountForSelectedPeriod;
	private float totalUnits;
	private float totalUnitsForSelectedPeriod;
	private List<FinancialReportsResponseVO> data;

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<FinancialReportsResponseVO> getData() {
		return data;
	}

	public void setData(List<FinancialReportsResponseVO> data) {
		this.data = data;
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

	public String getMiuID() {
		return miuID;
	}

	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}

	public float getTotalAmountForSelectedPeriod() {
		return totalAmountForSelectedPeriod;
	}

	public void setTotalAmountForSelectedPeriod(float totalAmountForSelectedPeriod) {
		this.totalAmountForSelectedPeriod = totalAmountForSelectedPeriod;
	}

	public float getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(float totalUnits) {
		this.totalUnits = totalUnits;
	}

	public float getTotalUnitsForSelectedPeriod() {
		return totalUnitsForSelectedPeriod;
	}

	public void setTotalUnitsForSelectedPeriod(float totalUnitsForSelectedPeriod) {
		this.totalUnitsForSelectedPeriod = totalUnitsForSelectedPeriod;
	}
	
}
