/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class GatewayResponseVO {
	
	private int gatewayID;
	private String gatewayName;
	private String gatewaySerialNumber;
	private String gatewayIP;
	private String gatewayPort;
	private String modifiedDate;
	
	private List<GatewayResponseVO> data;

	public int getGatewayID() {
		return gatewayID;
	}

	public void setGatewayID(int gatewayID) {
		this.gatewayID = gatewayID;
	}

	public String getGatewayName() {
		return gatewayName;
	}

	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	public String getGatewaySerialNumber() {
		return gatewaySerialNumber;
	}

	public void setGatewaySerialNumber(String gatewaySerialNumber) {
		this.gatewaySerialNumber = gatewaySerialNumber;
	}

	public String getGatewayIP() {
		return gatewayIP;
	}

	public void setGatewayIP(String gatewayIP) {
		this.gatewayIP = gatewayIP;
	}

	public String getGatewayPort() {
		return gatewayPort;
	}

	public void setGatewayPort(String gatewayPort) {
		this.gatewayPort = gatewayPort;
	}

	public List<GatewayResponseVO> getData() {
		return data;
	}

	public void setData(List<GatewayResponseVO> data) {
		this.data = data;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
