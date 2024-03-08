/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

import java.util.List;

/**
 * @author K Vimal Kumar
 *
 */
public class BillSummaryResponseVO {
	
	private String houseNumber;
	private String customerUniqueID;
	private String firstName;
	private String lastName;
	private long transactionID;
	private float billAmount;
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
	private String billingDate;
	private String paymentDate;
	
	private List<BillSummaryResponseVO> data;
	
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

	public float getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public List<BillSummaryResponseVO> getData() {
		return data;
	}

	public void setData(List<BillSummaryResponseVO> data) {
		this.data = data;
	}
	
}
