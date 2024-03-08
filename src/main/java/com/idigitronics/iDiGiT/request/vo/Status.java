/**
 * 
 */
package com.idigitronics.iDiGiT.request.vo;

/**
 * @author K Vimal Kumar
 *
 */
public class Status {
	
	private int door_open;
	private int magnetic;
	private int schedule_disconnect;
	private int rtc_fault;
	private int low_bat;
	private int low_bal;
	private int nfc_tamper;
	
	public int getDoor_open() {
		return door_open;
	}
	public void setDoor_open(int door_open) {
		this.door_open = door_open;
	}
	public int getMagnetic() {
		return magnetic;
	}
	public void setMagnetic(int magnetic) {
		this.magnetic = magnetic;
	}
	public int getSchedule_disconnect() {
		return schedule_disconnect;
	}
	public void setSchedule_disconnect(int schedule_disconnect) {
		this.schedule_disconnect = schedule_disconnect;
	}
	public int getRtc_fault() {
		return rtc_fault;
	}
	public void setRtc_fault(int rtc_fault) {
		this.rtc_fault = rtc_fault;
	}
	public int getLow_bat() {
		return low_bat;
	}
	public void setLow_bat(int low_bat) {
		this.low_bat = low_bat;
	}
	public int getLow_bal() {
		return low_bal;
	}
	public void setLow_bal(int low_bal) {
		this.low_bal = low_bal;
	}
	public int getNfc_tamper() {
		return nfc_tamper;
	}
	public void setNfc_tamper(int nfc_tamper) {
		this.nfc_tamper = nfc_tamper;
	}
	
}
