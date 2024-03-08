/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

import java.util.List;

/**
 * @author vml
 *
 */
public class SensorDashboardResponseVO {
	
	private String communityName;
	private String blockName;
	private String HouseNumber;
	private String firstName;
	private String lastName;
	private String customerUniqueID;
	private long readingID;
	private String equipment_serial_id;
	private int reading1;
	private int reading2;
	private int reading3;
	private int reading4;
	private String reader_sensor_status1;
	private String reader_sensor_status2;
	private String reader_sensor_status3;
	private String reader_sensor_status4;
	private float per_day_flow_rate1;
	private float per_day_flow_rate2;
	private float per_day_flow_rate3;
	private float per_day_flow_rate4;
	private float live_flow_rate1;
	private float live_flow_rate2;
	private float live_flow_rate3;
	private float live_flow_rate4;
	private int record_interval;
	private int sync_interval;
	private int rssi;
	private String digital_outputs1;
	private String digital_outputs2;
	private String digital_outputs3;
	private String digital_outputs4;
	private float analog_inputs1;
	private float analog_inputs2;
	private float analog_inputs3;
	private float analog_inputs4;
	private float analog_outputs1;
	private float analog_outputs2;
	private float analog_outputs3;
	private float analog_outputs4;
	private float voltage_outputs1;
	private float voltage_outputs2;
	private float voltage_outputs3;
	private float voltage_outputs4;
	private int battery_percentage;
	private String online_powersupply;
	private String gsm_status;
	private String ethernet_status;
	private String nfc_status;
	private String flash_status;
	private String nfc_memory_status;
	private String flash_memory_status;
	private String low_gsm; 
	private String low_battery;
	private String sensor_detachment;
	private String door_open_switch;
	private String magnetic_tamper; 
	private String timestamp;
	private String LogDate;
	
	private List<SensorDashboardResponseVO> data;

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getHouseNumber() {
		return HouseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		HouseNumber = houseNumber;
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

	public String getCustomerUniqueID() {
		return customerUniqueID;
	}

	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}

	public long getReadingID() {
		return readingID;
	}

	public void setReadingID(long readingID) {
		this.readingID = readingID;
	}

	public String getEquipment_serial_id() {
		return equipment_serial_id;
	}

	public void setEquipment_serial_id(String equipment_serial_id) {
		this.equipment_serial_id = equipment_serial_id;
	}

	public int getReading1() {
		return reading1;
	}

	public void setReading1(int reading1) {
		this.reading1 = reading1;
	}

	public int getReading2() {
		return reading2;
	}

	public void setReading2(int reading2) {
		this.reading2 = reading2;
	}

	public int getReading3() {
		return reading3;
	}

	public void setReading3(int reading3) {
		this.reading3 = reading3;
	}

	public int getReading4() {
		return reading4;
	}

	public void setReading4(int reading4) {
		this.reading4 = reading4;
	}

	public String getReader_sensor_status1() {
		return reader_sensor_status1;
	}

	public void setReader_sensor_status1(String reader_sensor_status1) {
		this.reader_sensor_status1 = reader_sensor_status1;
	}

	public String getReader_sensor_status2() {
		return reader_sensor_status2;
	}

	public void setReader_sensor_status2(String reader_sensor_status2) {
		this.reader_sensor_status2 = reader_sensor_status2;
	}

	public String getReader_sensor_status3() {
		return reader_sensor_status3;
	}

	public void setReader_sensor_status3(String reader_sensor_status3) {
		this.reader_sensor_status3 = reader_sensor_status3;
	}

	public String getReader_sensor_status4() {
		return reader_sensor_status4;
	}

	public void setReader_sensor_status4(String reader_sensor_status4) {
		this.reader_sensor_status4 = reader_sensor_status4;
	}

	public float getPer_day_flow_rate1() {
		return per_day_flow_rate1;
	}

	public void setPer_day_flow_rate1(float per_day_flow_rate1) {
		this.per_day_flow_rate1 = per_day_flow_rate1;
	}

	public float getPer_day_flow_rate2() {
		return per_day_flow_rate2;
	}

	public void setPer_day_flow_rate2(float per_day_flow_rate2) {
		this.per_day_flow_rate2 = per_day_flow_rate2;
	}

	public float getPer_day_flow_rate3() {
		return per_day_flow_rate3;
	}

	public void setPer_day_flow_rate3(float per_day_flow_rate3) {
		this.per_day_flow_rate3 = per_day_flow_rate3;
	}

	public float getPer_day_flow_rate4() {
		return per_day_flow_rate4;
	}

	public void setPer_day_flow_rate4(float per_day_flow_rate4) {
		this.per_day_flow_rate4 = per_day_flow_rate4;
	}

	public float getLive_flow_rate1() {
		return live_flow_rate1;
	}

	public void setLive_flow_rate1(float live_flow_rate1) {
		this.live_flow_rate1 = live_flow_rate1;
	}

	public float getLive_flow_rate2() {
		return live_flow_rate2;
	}

	public void setLive_flow_rate2(float live_flow_rate2) {
		this.live_flow_rate2 = live_flow_rate2;
	}

	public float getLive_flow_rate3() {
		return live_flow_rate3;
	}

	public void setLive_flow_rate3(float live_flow_rate3) {
		this.live_flow_rate3 = live_flow_rate3;
	}

	public float getLive_flow_rate4() {
		return live_flow_rate4;
	}

	public void setLive_flow_rate4(float live_flow_rate4) {
		this.live_flow_rate4 = live_flow_rate4;
	}

	public int getRecord_interval() {
		return record_interval;
	}

	public void setRecord_interval(int record_interval) {
		this.record_interval = record_interval;
	}

	public int getSync_interval() {
		return sync_interval;
	}

	public void setSync_interval(int sync_interval) {
		this.sync_interval = sync_interval;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public String getDigital_outputs1() {
		return digital_outputs1;
	}

	public void setDigital_outputs1(String digital_outputs1) {
		this.digital_outputs1 = digital_outputs1;
	}

	public String getDigital_outputs2() {
		return digital_outputs2;
	}

	public void setDigital_outputs2(String digital_outputs2) {
		this.digital_outputs2 = digital_outputs2;
	}

	public String getDigital_outputs3() {
		return digital_outputs3;
	}

	public void setDigital_outputs3(String digital_outputs3) {
		this.digital_outputs3 = digital_outputs3;
	}

	public String getDigital_outputs4() {
		return digital_outputs4;
	}

	public void setDigital_outputs4(String digital_outputs4) {
		this.digital_outputs4 = digital_outputs4;
	}

	public float getAnalog_inputs1() {
		return analog_inputs1;
	}

	public void setAnalog_inputs1(float analog_inputs1) {
		this.analog_inputs1 = analog_inputs1;
	}

	public float getAnalog_inputs2() {
		return analog_inputs2;
	}

	public void setAnalog_inputs2(float analog_inputs2) {
		this.analog_inputs2 = analog_inputs2;
	}

	public float getAnalog_inputs3() {
		return analog_inputs3;
	}

	public void setAnalog_inputs3(float analog_inputs3) {
		this.analog_inputs3 = analog_inputs3;
	}

	public float getAnalog_inputs4() {
		return analog_inputs4;
	}

	public void setAnalog_inputs4(float analog_inputs4) {
		this.analog_inputs4 = analog_inputs4;
	}

	public float getAnalog_outputs1() {
		return analog_outputs1;
	}

	public void setAnalog_outputs1(float analog_outputs1) {
		this.analog_outputs1 = analog_outputs1;
	}

	public float getAnalog_outputs2() {
		return analog_outputs2;
	}

	public void setAnalog_outputs2(float analog_outputs2) {
		this.analog_outputs2 = analog_outputs2;
	}

	public float getAnalog_outputs3() {
		return analog_outputs3;
	}

	public void setAnalog_outputs3(float analog_outputs3) {
		this.analog_outputs3 = analog_outputs3;
	}

	public float getAnalog_outputs4() {
		return analog_outputs4;
	}

	public void setAnalog_outputs4(float analog_outputs4) {
		this.analog_outputs4 = analog_outputs4;
	}

	public float getVoltage_outputs1() {
		return voltage_outputs1;
	}

	public void setVoltage_outputs1(float voltage_outputs1) {
		this.voltage_outputs1 = voltage_outputs1;
	}

	public float getVoltage_outputs2() {
		return voltage_outputs2;
	}

	public void setVoltage_outputs2(float voltage_outputs2) {
		this.voltage_outputs2 = voltage_outputs2;
	}

	public float getVoltage_outputs3() {
		return voltage_outputs3;
	}

	public void setVoltage_outputs3(float voltage_outputs3) {
		this.voltage_outputs3 = voltage_outputs3;
	}

	public float getVoltage_outputs4() {
		return voltage_outputs4;
	}

	public void setVoltage_outputs4(float voltage_outputs4) {
		this.voltage_outputs4 = voltage_outputs4;
	}

	public int getBattery_percentage() {
		return battery_percentage;
	}

	public void setBattery_percentage(int battery_percentage) {
		this.battery_percentage = battery_percentage;
	}

	public String getOnline_powersupply() {
		return online_powersupply;
	}

	public void setOnline_powersupply(String online_powersupply) {
		this.online_powersupply = online_powersupply;
	}

	public String getGsm_status() {
		return gsm_status;
	}

	public void setGsm_status(String gsm_status) {
		this.gsm_status = gsm_status;
	}

	public String getEthernet_status() {
		return ethernet_status;
	}

	public void setEthernet_status(String ethernet_status) {
		this.ethernet_status = ethernet_status;
	}

	public String getNfc_status() {
		return nfc_status;
	}

	public void setNfc_status(String nfc_status) {
		this.nfc_status = nfc_status;
	}

	public String getFlash_status() {
		return flash_status;
	}

	public void setFlash_status(String flash_status) {
		this.flash_status = flash_status;
	}

	public String getNfc_memory_status() {
		return nfc_memory_status;
	}

	public void setNfc_memory_status(String nfc_memory_status) {
		this.nfc_memory_status = nfc_memory_status;
	}

	public String getFlash_memory_status() {
		return flash_memory_status;
	}

	public void setFlash_memory_status(String flash_memory_status) {
		this.flash_memory_status = flash_memory_status;
	}

	public String getLow_gsm() {
		return low_gsm;
	}

	public void setLow_gsm(String low_gsm) {
		this.low_gsm = low_gsm;
	}

	public String getLow_battery() {
		return low_battery;
	}

	public void setLow_battery(String low_battery) {
		this.low_battery = low_battery;
	}

	public String getSensor_detachment() {
		return sensor_detachment;
	}

	public void setSensor_detachment(String sensor_detachment) {
		this.sensor_detachment = sensor_detachment;
	}

	public String getDoor_open_switch() {
		return door_open_switch;
	}

	public void setDoor_open_switch(String door_open_switch) {
		this.door_open_switch = door_open_switch;
	}

	public String getMagnetic_tamper() {
		return magnetic_tamper;
	}

	public void setMagnetic_tamper(String magnetic_tamper) {
		this.magnetic_tamper = magnetic_tamper;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getLogDate() {
		return LogDate;
	}

	public void setLogDate(String logDate) {
		LogDate = logDate;
	}

	public List<SensorDashboardResponseVO> getData() {
		return data;
	}

	public void setData(List<SensorDashboardResponseVO> data) {
		this.data = data;
	}


}
