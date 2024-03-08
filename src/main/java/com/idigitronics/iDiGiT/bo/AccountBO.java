/**
 * 
 */
package com.idigitronics.iDiGiT.bo;

import java.sql.SQLException;

import com.idigitronics.iDiGiT.dao.AccountDAO;
import com.idigitronics.iDiGiT.dao.DropDownDAO;
import com.idigitronics.iDiGiT.exceptions.BusinessException;
import com.idigitronics.iDiGiT.request.vo.ConfigurationRequestVO;
import com.idigitronics.iDiGiT.request.vo.PayBillRequestVO;
import com.idigitronics.iDiGiT.request.vo.TopUpRequestVO;
import com.idigitronics.iDiGiT.response.vo.ResponseVO;
import com.idigitronics.iDiGiT.response.vo.TopupDetailsResponseVO;

/**
 * @author K VimaL Kumar
 *
 */
public class AccountBO {

	AccountDAO accountdao = new AccountDAO();
	
	/* TopUp */
	
	public ResponseVO addtopup(TopUpRequestVO topupvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
		DropDownDAO dropDownDAO = new DropDownDAO();
		
		TopupDetailsResponseVO topupDetailsResponseVO = dropDownDAO.gettopupdetails(topupvo.getCustomerUniqueID(), topupvo.getCustomerMeterID());
		
		if(accountdao.validateamount(topupvo)){
			throw new BusinessException("RECHARGE AMOUNT MUST BE GREATER THAN EMERGENCY CREDIT: "+ topupDetailsResponseVO.getEmergencyCredit()+" AND UNIT RATE: "+ topupDetailsResponseVO.getTariff());
		}
		
		if(accountdao.validateBalance(topupvo)){
			throw new BusinessException("SUM OF AVAILABLE BALANCE: "+topupDetailsResponseVO.getCurrentBalance()+" AND RECHARGE AMOUNT MUST BE LESS THAN Rs.2000/-");
		} else if(topupvo.getAmount() > 2000) {
			throw new BusinessException("RECHARGE AMOUNT MUST BE LESS THAN Rs.2000/-");
		}
		
		if (topupvo.getAmount() <= topupDetailsResponseVO.getFixedCharges()
				|| topupvo.getAmount() <= topupDetailsResponseVO.getReconnectionCharges()) {
			throw new BusinessException("RECHARGE AMOUNT MUST BE GREATER THAN FIXED CHARGES: "+topupDetailsResponseVO.getFixedCharges()+" & RECONNECTION CHARGES: "+topupDetailsResponseVO.getReconnectionCharges());
		}
		
		if(accountdao.checktopup(topupvo.getCustomerMeterID())) {
			throw new BusinessException("PREVIOUS TOPUP REQUEST IS PENDING");
		}
		
		if(accountdao.checktypeOfMeter(topupvo.getCustomerMeterID())) {
			throw new BusinessException("SELECTED METER IS REGISTERED AS POSTPAID. RECHARGE CANNOT BE PROCESSED");
		}
		
		return accountdao.addtopup(topupvo);
	}
	
	public ResponseVO payBill(PayBillRequestVO paybillRequestVO) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
		if(accountdao.checkBillPaymentStatus(paybillRequestVO.getCustomerBillingID())) {
			throw new BusinessException("BILL IS ALREADY PAID");
		}
		
		return accountdao.paybill(paybillRequestVO);
	}
	
	/* Configuration */
	
	public ResponseVO addconfiguration(ConfigurationRequestVO configurationvo) throws BusinessException, SQLException {
		// TODO Auto-generated method stub
		ResponseVO responsevo = new ResponseVO();
			/*if (accountdao.checkconfigstatus(configurationvo.getMiuID())) {
				throw new BusinessException("PREVIOUS COMMAND REQUEST IS PENDING");
			}*/
			
			responsevo = accountdao.addconfiguration(configurationvo);
			
		return responsevo;
	}

}
