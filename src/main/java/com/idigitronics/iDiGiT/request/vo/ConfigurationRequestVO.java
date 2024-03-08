/**
 * 
 */
package com.idigitronics.iDiGiT.request.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class ConfigurationRequestVO {
	
	private String miuID;
	private int communityID;
	private int blockID;
	private long customerID;
	private String customerUniqueID;
	private long customerMeterID;
	private long transactionID;
	private int cmd_status;
	
	private List<CommandGroupRequestVO> commands;
	
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
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}
	public String getCustomerUniqueID() {
		return customerUniqueID;
	}
	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}
	public List<CommandGroupRequestVO> getCommands() {
		return commands;
	}
	public void setCommands(List<CommandGroupRequestVO> commands) {
		this.commands = commands;
	}
	public int getCmd_status() {
		return cmd_status;
	}
	public void setCmd_status(int cmd_status) {
		this.cmd_status = cmd_status;
	}
	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	public long getCustomerMeterID() {
		return customerMeterID;
	}
	public void setCustomerMeterID(long customerMeterID) {
		this.customerMeterID = customerMeterID;
	}
	public long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}
	
}
