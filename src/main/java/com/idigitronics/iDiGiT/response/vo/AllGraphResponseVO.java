/**
 * 
 */
package com.idigitronics.iDiGiT.response.vo;

import java.util.List;

/**
 * @author VmL
 *
 */
public class AllGraphResponseVO {
	
	private List<String> xAxis;
	private List<Series> series;
	
	public List<String> getxAxis() {
		return xAxis;
	}
	public void setxAxis(List<String> xAxis) {
		this.xAxis = xAxis;
	}
	public List<Series> getSeries() {
		return series;
	}
	public void setSeries(List<Series> series) {
		this.series = series;
	}
	
}
