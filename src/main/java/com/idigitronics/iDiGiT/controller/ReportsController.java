package com.idigitronics.iDiGiT.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.idigitronics.iDiGiT.dao.ReportsDAO;
import com.idigitronics.iDiGiT.request.vo.AlarmRequestVO;
import com.idigitronics.iDiGiT.request.vo.BillSummaryRequestVO;
import com.idigitronics.iDiGiT.request.vo.FinancialReportsRequestVO;
import com.idigitronics.iDiGiT.request.vo.TopUpSummaryRequestVO;
import com.idigitronics.iDiGiT.request.vo.UserConsumptionRequestVO;
import com.idigitronics.iDiGiT.response.vo.AlarmsResponseVO;
import com.idigitronics.iDiGiT.response.vo.BillSummaryResponseVO;
import com.idigitronics.iDiGiT.response.vo.FinancialReportsResponseVO;
import com.idigitronics.iDiGiT.response.vo.TopUpSummaryResponseVO;
import com.idigitronics.iDiGiT.response.vo.UserConsumptionReportsResponseVO;

/**
 * @author K VimaL Kumar
 * 
 */

@Controller
public class ReportsController {

	Gson gson = new Gson();
	ReportsDAO reportsdao = new ReportsDAO();
	
	/* Financial Reports */
	
	@RequestMapping(value = "/financialreports/{roleid}/{id}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	FinancialReportsResponseVO getfinancialreports(@PathVariable("roleid") int roleid, @PathVariable("id") int id, @RequestBody FinancialReportsRequestVO financialreportsrequestvo) throws SQLException {

		FinancialReportsResponseVO financialReportsResponseVO = new FinancialReportsResponseVO();
		
		financialReportsResponseVO.setData(reportsdao.getFinancialReportsdetails(financialreportsrequestvo, roleid, id));
		financialReportsResponseVO.setTotalAmountForSelectedPeriod(financialReportsResponseVO.getData().size() == 0 ? 0 : financialReportsResponseVO.getData().get(financialReportsResponseVO.getData().size()-1).getTotalAmountForSelectedPeriod());
		financialReportsResponseVO.setTotalUnitsForSelectedPeriod(financialReportsResponseVO.getData().size() == 0 ? 0 : financialReportsResponseVO.getData().get(financialReportsResponseVO.getData().size()-1).getTotalUnitsForSelectedPeriod());

		return financialReportsResponseVO;
	}
	
	/* User Consumption Reports */
	
	@RequestMapping(value = "/userconsumptionreports/{type}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	UserConsumptionReportsResponseVO userconsumptionreports(@RequestBody UserConsumptionRequestVO userConsumptionRequestVO, @PathVariable("type") String type) throws SQLException {

		UserConsumptionReportsResponseVO userConsumptionReportsResponseVO = new UserConsumptionReportsResponseVO();
		
		userConsumptionReportsResponseVO.setData(reportsdao.getuserconsumptionreportsdetails(userConsumptionRequestVO, type));
		
		List<String> miuIdList = userConsumptionReportsResponseVO.getData().stream() 
				  .filter(distinctByKey(p -> p.getMiuID())).map(e->e.getMiuID())
				  .collect(Collectors.toList());
		
		userConsumptionReportsResponseVO.setSizeMeter(String.valueOf(miuIdList.size()));
	
		
		return userConsumptionReportsResponseVO;
	}
	
	public static <T> Predicate<T> distinctByKey(
		    Function<? super T, ?> keyExtractor) {
		  
		    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
		    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
		}

	/* TopUp Summary */
	
	@RequestMapping(value = "/topupsummary", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	TopUpSummaryResponseVO topupsummary(@RequestBody TopUpSummaryRequestVO topupSummaryRequestVO) throws SQLException {

		TopUpSummaryResponseVO topUpSummaryResponseVO = new TopUpSummaryResponseVO();
		
		topUpSummaryResponseVO.setData(reportsdao.gettopupsummarydetails(topupSummaryRequestVO));
		
		return topUpSummaryResponseVO;
	}
	
	/* Billpayment Summary */
	
	@RequestMapping(value = "/billpaymentsummary", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	BillSummaryResponseVO billpaymentsummary(@RequestBody BillSummaryRequestVO billSummaryRequestVO) throws SQLException {

		BillSummaryResponseVO billSummaryResponseVO = new BillSummaryResponseVO();
		
		billSummaryResponseVO.setData(reportsdao.getbillsummarydetails(billSummaryRequestVO));
		
		return billSummaryResponseVO;
	}
	
	/* Alarms */

	@RequestMapping(value = "/alarm/{roleid}/{id}/{filterCid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	AlarmsResponseVO alarmdetails(@PathVariable("roleid") int roleid, @PathVariable("id") int id, @PathVariable("filterCid") int filterCid) throws SQLException {

		AlarmsResponseVO alarmsResponseVO = new AlarmsResponseVO();

		alarmsResponseVO.setData(reportsdao.getAlarmdetails(roleid, id, filterCid));

		return alarmsResponseVO;
	}
	
	@RequestMapping(value = "/alarmreports", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	AlarmsResponseVO alarmreports(@RequestBody AlarmRequestVO alarmRequestVO) throws SQLException {

		AlarmsResponseVO alarmsResponseVO = new AlarmsResponseVO();

		alarmsResponseVO.setData(reportsdao.getAlarmreportsdetails(alarmRequestVO));

		return alarmsResponseVO;

	}

}
