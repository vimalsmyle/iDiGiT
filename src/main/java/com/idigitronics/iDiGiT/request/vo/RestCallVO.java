package com.idigitronics.iDiGiT.request.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 * 
 */
public class RestCallVO {
	
	private String miuID;
	private long transaction_id;
	private int parameter_id;
	private String value;
	private float emergency_credit;
	private float credit;
	private String gatewayIP;
	private int gatewayPort;
	
	private List<CommandGroupRequestVO> parameters;
	private String urlExtension;
	
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}
	public long getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getParameter_id() {
		return parameter_id;
	}
	public void setParameter_id(int parameter_id) {
		this.parameter_id = parameter_id;
	}
	public String getUrlExtension() {
		return urlExtension;
	}
	public void setUrlExtension(String urlExtension) {
		this.urlExtension = urlExtension;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<CommandGroupRequestVO> getParameters() {
		return parameters;
	}
	public void setParameters(List<CommandGroupRequestVO> parameters) {
		this.parameters = parameters;
	}
	public float getEmergency_credit() {
		return emergency_credit;
	}
	public void setEmergency_credit(float emergency_credit) {
		this.emergency_credit = emergency_credit;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public String getGatewayIP() {
		return gatewayIP;
	}
	public void setGatewayIP(String gatewayIP) {
		this.gatewayIP = gatewayIP;
	}
	public int getGatewayPort() {
		return gatewayPort;
	}
	public void setGatewayPort(int gatewayPort) {
		this.gatewayPort = gatewayPort;
	}
	
}
