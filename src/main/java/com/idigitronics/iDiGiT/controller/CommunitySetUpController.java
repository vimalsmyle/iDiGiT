/**
 * 
 */
package com.idigitronics.iDiGiT.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idigitronics.iDiGiT.bo.CommunitySetUpBO;
import com.idigitronics.iDiGiT.dao.CommunitySetUpDAO;
import com.idigitronics.iDiGiT.exceptions.BusinessException;
import com.idigitronics.iDiGiT.request.vo.BlockRequestVO;
import com.idigitronics.iDiGiT.request.vo.CommunityRequestVO;
import com.idigitronics.iDiGiT.request.vo.CustomerRequestVO;
import com.idigitronics.iDiGiT.request.vo.GatewayRequestVO;
import com.idigitronics.iDiGiT.request.vo.MeterSizeRequestVO;
import com.idigitronics.iDiGiT.request.vo.TariffRequestVO;
import com.idigitronics.iDiGiT.response.vo.BlockResponseVO;
import com.idigitronics.iDiGiT.response.vo.CommunityResponseVO;
import com.idigitronics.iDiGiT.response.vo.CustomerResponseVO;
import com.idigitronics.iDiGiT.response.vo.GatewayResponseVO;
import com.idigitronics.iDiGiT.response.vo.MeterSizeResponseVO;
import com.idigitronics.iDiGiT.response.vo.ResponseVO;
import com.idigitronics.iDiGiT.response.vo.TariffResponseVO;

/**
 * @author VmL
 * 
 */
@Controller
public class CommunitySetUpController {
	
	CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();
	CommunitySetUpBO communitysetupbo = new CommunitySetUpBO();
	
	/* Community */

	@RequestMapping(value = "/community/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	CommunityResponseVO communitydetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id) throws SQLException {

		CommunityResponseVO communityResponsevo = new CommunityResponseVO();
		
		communityResponsevo.setData(communitysetupdao.getCommunitydetails(roleid, id));

		return communityResponsevo;
	}

	@RequestMapping(value = "/community/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addcommunity(@RequestBody CommunityRequestVO communityvo)
			throws ClassNotFoundException, SQLException, BusinessException {
		ResponseVO responsevo = new ResponseVO();
		try {
			responsevo = communitysetupbo.addcommunity(communityvo);

		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	@RequestMapping(value = "/community/edit/{communityID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editcommunity(@PathVariable("communityID") int communityid,
			@RequestBody CommunityRequestVO communityvo) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		communityvo.setCommunityID(communityid);

		try {
			responsevo = communitysetupbo.editcommunity(communityvo);
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	/* Gateway */

	@RequestMapping(value = "/gateway", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	GatewayResponseVO gatewaydetails() throws SQLException {

		GatewayResponseVO gatewayresponsevo = new GatewayResponseVO();

		gatewayresponsevo.setData(communitysetupdao.getGatewaydetails());

		return gatewayresponsevo;
	}

	@RequestMapping(value = "/gateway/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addgateway(@RequestBody GatewayRequestVO gatewayvo) throws ClassNotFoundException,
			SQLException, BusinessException {
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = communitysetupbo.addgateway(gatewayvo);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/gateway/edit/{gatewayID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editgateway(@RequestBody GatewayRequestVO gatewayvo, @PathVariable("gatewayID") int gatewayID) throws ClassNotFoundException,
			SQLException, BusinessException {

		gatewayvo.setGatewayID(gatewayID);
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = communitysetupbo.editgateway(gatewayvo);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/gateway/delete/{gatewayID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deletegateway(@PathVariable("gatewayID") int gatewayID) throws ClassNotFoundException,
			SQLException, BusinessException {
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = communitysetupbo.deletegateway(gatewayID);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	/* Meter Size */

	@RequestMapping(value = "/metersize", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	MeterSizeResponseVO meterSizeDetails() throws SQLException {

		MeterSizeResponseVO meterSizeResponseVO = new MeterSizeResponseVO();

		meterSizeResponseVO.setData(communitysetupdao.getMeterSizedetails());

		return meterSizeResponseVO;
	}

	@RequestMapping(value = "/metersize/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addMeterSize(@RequestBody MeterSizeRequestVO meterSizeRequestVO) throws ClassNotFoundException,
			SQLException, BusinessException {
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = communitysetupbo.addMeterSize(meterSizeRequestVO);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/metersize/edit/{metersizeID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editMeterSize(@RequestBody MeterSizeRequestVO meterSizeRequestVO, @PathVariable("metersizeID") int metersizeID) throws ClassNotFoundException,
			SQLException, BusinessException {

		meterSizeRequestVO.setMeterSizeID(metersizeID);
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = communitysetupbo.editMeterSize(meterSizeRequestVO);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/metersize/delete/{metersizeID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deleteMeterSize(@PathVariable("metersizeID") int metersizeID) throws ClassNotFoundException,
			SQLException, BusinessException {
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = communitysetupbo.deleteMeterSize(metersizeID);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	/* Block */

	@RequestMapping(value = "/block/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	BlockResponseVO blockdetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id) throws SQLException {

		BlockResponseVO blockresponsevo = new BlockResponseVO();

		blockresponsevo.setData(communitysetupdao.getBlockdetails(roleid, id));

		return blockresponsevo;
	}

	@RequestMapping(value = "/block/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addblock(@RequestBody BlockRequestVO blockvo) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		try {
			
			responsevo = communitysetupbo.addblock(blockvo);
		
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	@RequestMapping(value = "/block/edit/{blockID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editblock(@PathVariable("blockID") int blockid,
			@RequestBody BlockRequestVO blockvo) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		blockvo.setBlockID(blockid);
		try {
			responsevo = communitysetupbo.editblock(blockvo);
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	@RequestMapping(value = "/block/delete/{blockID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deleteblock(@PathVariable("blockID") int blockid)
			throws BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		try{

			responsevo = communitysetupbo.deleteblock(blockid);
		
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}
		
		return responsevo;
	}
	
	
	/* Customer */

	@RequestMapping(value = "/customer/{roleid}/{id}/{filterCid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	CustomerResponseVO customerdetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id, @PathVariable("filterCid") int filterCid) throws SQLException {

		CustomerResponseVO customerresponsevo = new CustomerResponseVO();

		customerresponsevo.setData(communitysetupdao.getCustomerdetails(roleid, id, filterCid));

		return customerresponsevo;
	}
	
	@RequestMapping(value = "/customer/{miuID}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ResponseVO getCustomerDetailsByMiuID(@PathVariable("miuID") String miuID) throws SQLException {

		return communitysetupdao.getCustomerDetailsByMiuID(miuID);
	}
	
	@RequestMapping(value = "/customer/excel", method = RequestMethod.POST, produces = "application/xlsx", consumes = "application/json")
	public ResponseEntity<InputStreamResource> customerDetailsfile(@RequestBody CustomerResponseVO customerResponseVO) throws SQLException, FileNotFoundException {

		ResponseVO responsevo = new ResponseVO();

		responsevo = communitysetupdao.customerDetailsfile(customerResponseVO);
		
		File file = new File(responsevo.getLocation() + responsevo.getFileName());
	    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
		ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(resource, HttpStatus.OK);
		
		return response;
	}

	@RequestMapping(value = "/customer/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addcustomer(@RequestBody CustomerRequestVO customervo) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		try {
			responsevo = communitysetupbo.addcustomer(customervo);
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	@RequestMapping(value = "/customer/edit/{CustomerUniqueID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editcustomer(@PathVariable("CustomerUniqueID") String CustomerUniqueID,
			@RequestBody CustomerRequestVO customervo) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		customervo.setCustomerUniqueID(CustomerUniqueID);

		try {
			responsevo = communitysetupbo.editcustomer(customervo);
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	@RequestMapping(value = "/customer/delete/{CustomerUniqueID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deletecustomer(@PathVariable("CustomerUniqueID") String CustomerUniqueID)
			throws ClassNotFoundException, BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		CustomerRequestVO customervo = new CustomerRequestVO();

		customervo.setCustomerUniqueID(CustomerUniqueID);
		
		try{
		
			responsevo = communitysetupbo.deletecustomer(customervo);

		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}
		
		return responsevo;
	}
	
	@RequestMapping(value = "/customerupdatesrequest/{blockid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	CustomerResponseVO customerupdatesrequest(@PathVariable("blockid") int blockid) throws SQLException {

		CustomerResponseVO customerresponsevo = new CustomerResponseVO();

		customerresponsevo.setData(communitysetupdao.getCustomerUpdateRequestdetails(blockid));

		return customerresponsevo;
	}
	
	@RequestMapping(value = "/approverequest/{requestID}/{action}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO approverequest(@PathVariable("requestID") int requestid, @PathVariable("action") int action) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		try {
			responsevo = communitysetupdao.approverequest(requestid, action);
		} catch (Exception e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
/* Tariff */

	@RequestMapping(value = "/tariff", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	TariffResponseVO tariffdetails() throws SQLException {

		TariffResponseVO tariffresponsevo = new TariffResponseVO();

		tariffresponsevo.setData(communitysetupdao.getTariffdetails());

		return tariffresponsevo;
	}

	@RequestMapping(value = "/tariff/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addtariff(@RequestBody TariffRequestVO tariffvo) throws ClassNotFoundException,
			SQLException, BusinessException {
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = communitysetupbo.addtariff(tariffvo);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/tariff/edit/{tariffID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO edittariff(@RequestBody TariffRequestVO tariffvo, @PathVariable("tariffID") int tariffID) throws ClassNotFoundException,
			SQLException, BusinessException {

		tariffvo.setTariffID(tariffID);
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = communitysetupbo.edittariff(tariffvo);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/tariff/delete/{tariffID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deletetariff(@PathVariable("tariffID") int tariffID) throws ClassNotFoundException,
			SQLException, BusinessException {
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = communitysetupbo.deletetariff(tariffID);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
}
