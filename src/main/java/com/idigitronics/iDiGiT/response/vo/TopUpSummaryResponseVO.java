/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class TopUpSummaryResponseVO {

	private String houseNumber;
	private String customerUniqueID;
	private String miuID;
	private String firstName;
	private String lastName;
	private long transactionID;
	private int rechargeAmount;
	private String status;
	private String modeOfPayment;
	private String razorPayOrderID;
	private String razorPayPaymentID;
	private String RazorPayPaymentStatus;
	private String razorPayRefundID;
	private String RazorPayRefundStatus;
	private String paymentStatus;
	private String transactedByUserName;
	private String transactedByRoleDescription;
	private String dateTime;
	
	private List<TopUpSummaryResponseVO> data;

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
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

	public long getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}

	public int getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(int rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getTransactedByUserName() {
		return transactedByUserName;
	}

	public void setTransactedByUserName(String transactedByUserName) {
		this.transactedByUserName = transactedByUserName;
	}

	public String getTransactedByRoleDescription() {
		return transactedByRoleDescription;
	}

	public void setTransactedByRoleDescription(String transactedByRoleDescription) {
		this.transactedByRoleDescription = transactedByRoleDescription;
	}

	public List<TopUpSummaryResponseVO> getData() {
		return data;
	}

	public void setData(List<TopUpSummaryResponseVO> data) {
		this.data = data;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getRazorPayOrderID() {
		return razorPayOrderID;
	}

	public void setRazorPayOrderID(String razorPayOrderID) {
		this.razorPayOrderID = razorPayOrderID;
	}

	public String getRazorPayPaymentID() {
		return razorPayPaymentID;
	}

	public void setRazorPayPaymentID(String razorPayPaymentID) {
		this.razorPayPaymentID = razorPayPaymentID;
	}

	public String getRazorPayPaymentStatus() {
		return RazorPayPaymentStatus;
	}

	public void setRazorPayPaymentStatus(String razorPayPaymentStatus) {
		RazorPayPaymentStatus = razorPayPaymentStatus;
	}

	public String getRazorPayRefundID() {
		return razorPayRefundID;
	}

	public void setRazorPayRefundID(String razorPayRefundID) {
		this.razorPayRefundID = razorPayRefundID;
	}

	public String getRazorPayRefundStatus() {
		return RazorPayRefundStatus;
	}

	public void setRazorPayRefundStatus(String razorPayRefundStatus) {
		RazorPayRefundStatus = razorPayRefundStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
}
