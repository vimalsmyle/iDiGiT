/**
 * 
 */
package com.idigitronics.iDiGiT.request.vo;

/**
 * @author VmL
 *
 */
public class PayBillRequestVO {
	
	private long customerBillingID;
	private long customerID;
	private String customerUniqueID;
	private float totalamount;
	private float taxAmount;
	private float billAmount;
	private float previousDues;
	private int lateFee;
	private String source;
	private String modeOfPayment;
	private int paymentStatus;
	private int status;
	private String razorPayOrderID;
	private String razorPayPaymentID;
	private String razorPaySignature;
	private int transactedByID;
	private int transactedByRoleID;
	
	private long transactionID;
	
	public long getCustomerBillingID() {
		return customerBillingID;
	}
	public void setCustomerBillingID(long customerBillingID) {
		this.customerBillingID = customerBillingID;
	}
	public long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}
	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	public String getCustomerUniqueID() {
		return customerUniqueID;
	}
	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}
	public float getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(float totalamount) {
		this.totalamount = totalamount;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public int getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public String getRazorPaySignature() {
		return razorPaySignature;
	}
	public void setRazorPaySignature(String razorPaySignature) {
		this.razorPaySignature = razorPaySignature;
	}
	public int getTransactedByID() {
		return transactedByID;
	}
	public void setTransactedByID(int transactedByID) {
		this.transactedByID = transactedByID;
	}
	public int getTransactedByRoleID() {
		return transactedByRoleID;
	}
	public void setTransactedByRoleID(int transactedByRoleID) {
		this.transactedByRoleID = transactedByRoleID;
	}
	public float getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(float taxAmount) {
		this.taxAmount = taxAmount;
	}
	public int getLateFee() {
		return lateFee;
	}
	public void setLateFee(int lateFee) {
		this.lateFee = lateFee;
	}
	public float getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
	}
	public float getPreviousDues() {
		return previousDues;
	}
	public void setPreviousDues(float previousDues) {
		this.previousDues = previousDues;
	}

}
