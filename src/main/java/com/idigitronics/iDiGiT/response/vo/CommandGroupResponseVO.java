/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

/**
 * @author K Vimal Kumar
 *
 */
public class CommandGroupResponseVO {
	
	private String commandType;
	private String value;
	private String modifiedDate;
	private String status;
	
	public String getCommandType() {
		return commandType;
	}
	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
