/**
 * 
 */
package com.idigitronics.iDiGiT.request.vo;

/**
 * @author vml
 *
 */
public class Alarms {
	
	private int gsm_status;
	private int ethernet_status;
	private int nfc_status;
	private int flash_status;
	private int nfc_memory_status;
	private int flash_memory_status;
	private int low_gsm;
	private int low_battery;
	private int sensor_detachment;
	private int door_open_switch;
	private int magnetic_tamper;
	
	
	public int getGsm_status() {
		return gsm_status;
	}
	public void setGsm_status(int gsm_status) {
		this.gsm_status = gsm_status;
	}
	public int getEthernet_status() {
		return ethernet_status;
	}
	public void setEthernet_status(int ethernet_status) {
		this.ethernet_status = ethernet_status;
	}
	public int getNfc_status() {
		return nfc_status;
	}
	public void setNfc_status(int nfc_status) {
		this.nfc_status = nfc_status;
	}
	public int getFlash_status() {
		return flash_status;
	}
	public void setFlash_status(int flash_status) {
		this.flash_status = flash_status;
	}
	public int getNfc_memory_status() {
		return nfc_memory_status;
	}
	public void setNfc_memory_status(int nfc_memory_status) {
		this.nfc_memory_status = nfc_memory_status;
	}
	public int getFlash_memory_status() {
		return flash_memory_status;
	}
	public void setFlash_memory_status(int flash_memory_status) {
		this.flash_memory_status = flash_memory_status;
	}
	public int getLow_gsm() {
		return low_gsm;
	}
	public void setLow_gsm(int low_gsm) {
		this.low_gsm = low_gsm;
	}
	public int getLow_battery() {
		return low_battery;
	}
	public void setLow_battery(int low_battery) {
		this.low_battery = low_battery;
	}
	public int getSensor_detachment() {
		return sensor_detachment;
	}
	public void setSensor_detachment(int sensor_detachment) {
		this.sensor_detachment = sensor_detachment;
	}
	public int getDoor_open_switch() {
		return door_open_switch;
	}
	public void setDoor_open_switch(int door_open_switch) {
		this.door_open_switch = door_open_switch;
	}
	public int getMagnetic_tamper() {
		return magnetic_tamper;
	}
	public void setMagnetic_tamper(int magnetic_tamper) {
		this.magnetic_tamper = magnetic_tamper;
	}
	

}
