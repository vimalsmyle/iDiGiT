/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

/**
 * @author K Vimal Kumar
 *
 */
public class ConfigurationStatusResponseVO {
	
	private long transaction_id;
	private int cmd_status;
	
	public long getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getCmd_status() {
		return cmd_status;
	}
	public void setCmd_status(int cmd_status) {
		this.cmd_status = cmd_status;
	}
	
}
