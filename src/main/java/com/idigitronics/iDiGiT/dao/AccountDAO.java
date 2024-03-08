/**
 * 
 */
package com.idigitronics.iDiGiT.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;
import com.idigitronics.iDiGiT.constants.DataBaseConstants;
import com.idigitronics.iDiGiT.constants.ExtraConstants;
import com.idigitronics.iDiGiT.exceptions.BusinessException;
import com.idigitronics.iDiGiT.request.vo.CheckOutRequestVO;
import com.idigitronics.iDiGiT.request.vo.CommandGroupRequestVO;
import com.idigitronics.iDiGiT.request.vo.ConfigurationRequestVO;
import com.idigitronics.iDiGiT.request.vo.DataRequestVO;
import com.idigitronics.iDiGiT.request.vo.PayBillRequestVO;
import com.idigitronics.iDiGiT.request.vo.RazorPayOrderVO;
import com.idigitronics.iDiGiT.request.vo.RazorpayRequestVO;
import com.idigitronics.iDiGiT.request.vo.RestCallVO;
import com.idigitronics.iDiGiT.request.vo.SMSRequestVO;
import com.idigitronics.iDiGiT.request.vo.TopUpRequestVO;
import com.idigitronics.iDiGiT.response.vo.BillingResponseVO;
import com.idigitronics.iDiGiT.response.vo.CheckoutDetails;
import com.idigitronics.iDiGiT.response.vo.CommandGroupResponseVO;
import com.idigitronics.iDiGiT.response.vo.ConfigurationResponseVO;
import com.idigitronics.iDiGiT.response.vo.ConfigurationStatusResponseVO;
import com.idigitronics.iDiGiT.response.vo.IndividualBillingResponseVO;
import com.idigitronics.iDiGiT.response.vo.Notes;
import com.idigitronics.iDiGiT.response.vo.Prefill;
import com.idigitronics.iDiGiT.response.vo.RazorPayResponseVO;
import com.idigitronics.iDiGiT.response.vo.ResponseVO;
import com.idigitronics.iDiGiT.response.vo.RestCallResponseVO;
import com.idigitronics.iDiGiT.response.vo.StatusResponseVO;
import com.idigitronics.iDiGiT.response.vo.Theme;
import com.idigitronics.iDiGiT.utils.Signature;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

/**
 * @author K VimaL Kumar
 * 
 */
public class AccountDAO {

	Gson gson = new Gson();
	
	private static final Logger logger = Logger.getLogger(AccountDAO.class);

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL, DataBaseConstants.USER_NAME,
				DataBaseConstants.PASSWORD);
		return connection;
	}

	/* TopUp */

	public ResponseVO addtopup(TopUpRequestVO topUpRequestVO) throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		Connection con = null;
		Gson gson = new Gson();
		ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
		ResponseVO responsevo = new ResponseVO();
		RazorPayOrderVO razorPayOrderVO = new RazorPayOrderVO();
		CheckoutDetails checkoutDetails = new CheckoutDetails();
		RazorpayRequestVO razorpayRequestVO = new RazorpayRequestVO();
		Prefill prefill = new Prefill();
		Notes notes = new Notes();
		Theme theme = new Theme();
		
//		topup status = 0 = passed; 10 = pending; 11 = failed, 12 = retry

		try {
			con = getConnection();
			
					PreparedStatement pstmt1 = con.prepareStatement("SELECT tr.EmergencyCredit, tr.AlarmCredit, tr.FixedCharges, tr.TariffID, tr.Tariff, CONCAT(cd.FirstName, ' ', cd.LastName) AS CustomerName, cd.Email, cd.MobileNumber, cd.CustomerUniqueID, cd.HouseNumber, cmd.CustomerMeterID, g.GatewayIP, g.GatewayPort FROM customerdetails AS cd LEFT JOIN customermeterdetails AS cmd on cmd.customerID = cd.CustomerID LEFT JOIN tariff AS tr ON tr.TariffID = cmd.TariffID LEFT JOIN gateway AS g ON g.GatewayID = cmd.GatewayID WHERE cd.CustomerUniqueID = '"
									+ topUpRequestVO.getCustomerUniqueID() + "' AND CustomerMeterID = " + topUpRequestVO.getCustomerMeterID());
					ResultSet rs1 = pstmt1.executeQuery();
					if (rs1.next()) {

						topUpRequestVO.setAlarmCredit(rs1.getFloat("AlarmCredit"));
						topUpRequestVO.setEmergencyCredit(rs1.getFloat("EmergencyCredit"));
						topUpRequestVO.setTariff(rs1.getFloat("Tariff"));
						topUpRequestVO.setGatewayIP(rs1.getString("GatewayIP"));
						topUpRequestVO.setGatewayPort(rs1.getInt("GatewayPort"));

						LocalDateTime dateTime = LocalDateTime.now();

						PreparedStatement pstmt = con.prepareStatement("SELECT MONTH(TransactionDate) AS previoustopupmonth from topup WHERE Status = 0 and CustomerUniqueID = '"
										+ topUpRequestVO.getCustomerUniqueID() + "' AND CustomerMeterID = " + topUpRequestVO.getCustomerMeterID() + " ORDER BY TransactionID DESC LIMIT 0,1");
						ResultSet rs = pstmt.executeQuery();

						if (rs.next()) {
							if (rs.getInt("previoustopupmonth") != dateTime.getMonthValue()) {
								topUpRequestVO.setFixedCharges((rs1.getInt("FixedCharges")
										* (dateTime.getMonthValue() - rs.getInt("previoustopupmonth"))));
							}

						} else {
							topUpRequestVO.setFixedCharges(rs1.getInt("FixedCharges"));
						}

						/*PreparedStatement pstmt2 = con.prepareStatement("SELECT al.ReconnectionCharges, al.ReconnectionChargeDays, dbl.Minutes FROM displaybalancelog AS dbl JOIN alertsettings AS al WHERE dbl.CustomerUniqueID = '"
										+ topUpRequestVO.getCustomerUniqueID() + "' AND dbl.CustomerMeterID = " + topUpRequestVO.getCustomerMeterID());
						ResultSet rs2 = pstmt2.executeQuery();

						if (rs2.next()) {
							if (rs2.getInt("Minutes") > rs2.getInt("ReconnectionChargeDays")) {
								topUpRequestVO.setReconnectionCharges(rs2.getInt("ReconnectionCharges"));
							}
						}

						if (topUpRequestVO.getAmount() <= topUpRequestVO.getFixedCharges()
								|| topUpRequestVO.getAmount() <= topUpRequestVO.getReconnectionCharges()) {
							throw new BusinessException("RECHARGE AMOUNT MUST BE GREATER THAN FIXED CHARGES & RECONNECTION CHARGES");
						}*/

						if (topUpRequestVO.getModeOfPayment().equalsIgnoreCase("Online")) {

							// creating transactionID for payment process

							long transactionID = inserttopup(topUpRequestVO);

							if (transactionID != 0) {

								// creating order in razor pay

								razorPayOrderVO.setAmount(topUpRequestVO.getAmount() * 100);
								razorPayOrderVO.setCurrency("INR");
								razorPayOrderVO.setPayment_capture(1);

								razorpayRequestVO.setApi("orders");
								String rzpRestCallResponse = extramethodsdao.razorpaypost(razorPayOrderVO, razorpayRequestVO);

								RazorPayResponseVO razorPayResponseVO = gson.fromJson(rzpRestCallResponse, RazorPayResponseVO.class);

								topUpRequestVO.setRazorPayOrderID(razorPayResponseVO.getId());

								PreparedStatement pstmt4 = con.prepareStatement("UPDATE topup SET RazorPayOrderID = ? WHERE TransactionID = " + transactionID);
								pstmt4.setString(1, topUpRequestVO.getRazorPayOrderID());
								if (pstmt4.executeUpdate() > 0) {

									checkoutDetails.setKey(ExtraConstants.RZPKeyID);
									checkoutDetails.setAmount(topUpRequestVO.getAmount() * 100);
									checkoutDetails.setCurrency(ExtraConstants.PaymentCurrency);
									checkoutDetails.setOrder_id(topUpRequestVO.getRazorPayOrderID());
									checkoutDetails.setButtonText(ExtraConstants.PaymentButtonText);
									checkoutDetails.setName(ExtraConstants.CompanyName);
									checkoutDetails.setDescription("Recharge of INR " + topUpRequestVO.getAmount()
											+ "/- for CRN/UAN: " + rs1.getString("CustomerUniqueID") + ".");
									checkoutDetails.setImage(ExtraConstants.IDIGIIMAGEURL);

									prefill.setName(rs1.getString("CustomerName"));
									prefill.setEmail(rs1.getString("Email"));
									prefill.setContact(rs1.getString("MobileNumber"));
									checkoutDetails.setPrefill(prefill);

									theme.setColor(ExtraConstants.PaymentThemeColor);
									checkoutDetails.setTheme(theme);

									notes.setAddress(rs1.getString("HouseNumber"));
									checkoutDetails.setTransactionID(transactionID);

									responsevo.setCheckoutDetails(checkoutDetails);

									responsevo.setPaymentMode("Online");
									responsevo.setPayType("Prepaid");
									responsevo.setResult("Success");
									responsevo.setMessage("Order Created Successfully. Proceed to CheckOut");
								} else {
									responsevo.setResult("Failure");
									responsevo.setMessage("Order Creation Failed. Please Try After Sometime");
								}
							} else {

								responsevo.setResult("Failure");
								responsevo.setMessage("Order Creation Failed. Please Try After Sometime");
							}
						} else {
							topUpRequestVO.setPaymentStatus(1);
							responsevo.setPaymentMode("Cash");
							RestCallResponseVO restCallResponseVO = sendPayLoadToGateway(topUpRequestVO);
							
								if(restCallResponseVO.getRestcallresponse() == 200) {
									responsevo.setTransactionID(restCallResponseVO.getTransactionID());
									responsevo.setTariff(rs1.getString("Tariff"));
									responsevo.setEmergencyCredit(rs1.getString("EmergencyCredit"));
									responsevo.setResult("Success");
									responsevo.setMessage("Topup Request Submitted/Raised Successfully");	
								} else {
									responsevo.setTransactionID(restCallResponseVO.getTransactionID());
									responsevo.setResult("Failure");
									responsevo.setMessage("Topup Request Failed. Please Try After Sometime");
								}
								
						}
					}
				 
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			// pstmt.close();
			// ps.close();
			con.close();
		}

		return responsevo;
	}

	/*public long inserttopuponline(TopUpRequestVO topUpRequestVO) {

		Connection con = null;
		PreparedStatement ps = null;
		long transactionID = 0;

		try {
			con = getConnection();

			PreparedStatement pstmt = con.prepareStatement("SELECT cd.CommunityID, cd.BlockID, cd.CustomerID, cmd.MIUID, cmd.CustomerMeterID, cmd.TariffID FROM customerdetails AS cd LEFT JOIN customermeterdetails AS cmd ON cd.CustomerID = CMD.CustomerID WHERE cd.CustomerUniqueID = ? AND cmd.CustomerMeterID = " + topUpRequestVO.getCustomerMeterID());
			pstmt.setString(1, topUpRequestVO.getCustomerUniqueID());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				ps = con.prepareStatement("INSERT INTO topup (CommunityID, BlockID, CustomerID, MIUID, CustomerMeterID, TariffID, Amount, Status, FixedCharges, ReconnectionCharges, Source, ModeOfPayment, CreatedByID, CreatedByRoleID, CustomerUniqueID, AcknowledgeDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");

				ps.setInt(1, rs.getInt("CommunityID"));
				ps.setInt(2, rs.getInt("BlockID"));
				ps.setInt(3, rs.getInt("CustomerID"));
				ps.setString(4, rs.getString("MeterID"));
				ps.setLong(5, topUpRequestVO.getCustomerMeterID());
				ps.setInt(6, rs.getInt("TariffID"));
				ps.setFloat(7, topUpRequestVO.getAmount());
				ps.setInt(8, 10);
				ps.setInt(9, topUpRequestVO.getFixedCharges());
				ps.setFloat(10, topUpRequestVO.getReconnectionCharges());
				ps.setString(11, topUpRequestVO.getSource());
				ps.setString(12, topUpRequestVO.getModeOfPayment());
				ps.setInt(13, topUpRequestVO.getTransactedByID());
				ps.setInt(14, topUpRequestVO.getTransactedByRoleID());
				ps.setString(15, topUpRequestVO.getCustomerUniqueID());

				if (ps.executeUpdate() > 0) {

					PreparedStatement pstmt1 = con.prepareStatement(
							"SELECT TransactionID FROM topup WHERE CustomerUniqueID = ? AND Source = ? AND ModeOfPayment = 'Online' AND STATUS = 10 AND PaymentStatus = 0 ORDER BY TransactionID DESC LIMIT 0,1");
					pstmt1.setString(1, topUpRequestVO.getCustomerUniqueID());
					pstmt1.setString(2, topUpRequestVO.getSource());
					ResultSet rs1 = pstmt1.executeQuery();
					if (rs1.next()) {
						transactionID = rs1.getInt("TransactionID");
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return transactionID;
	}*/

	public ResponseVO updatepayment(CheckOutRequestVO checkOutRequestVO) throws SQLException, ClassNotFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		ResponseVO responseVO = new ResponseVO();
		TopUpRequestVO topUpRequestVO = null;

		try {
			con = getConnection();

			String generated_signature = Signature.calculateRFC2104HMAC(
					checkOutRequestVO.getRazorpay_order_id() + "|" + checkOutRequestVO.getRazorpay_payment_id(),
					ExtraConstants.RZPKeySecret);
			
			if(checkOutRequestVO.getPayType().equalsIgnoreCase("Prepaid")) {
				
			if (generated_signature.equalsIgnoreCase(checkOutRequestVO.getRazorpay_signature())) {

				ps = con.prepareStatement("UPDATE topup SET PaymentStatus = 1, RazorPayPaymentID = ?, RazorPaySignature = ? WHERE RazorPayOrderID = ? AND TransactionID = ?");

				ps.setString(1, checkOutRequestVO.getRazorpay_payment_id());
				ps.setString(2, checkOutRequestVO.getRazorpay_signature());
				ps.setString(3, checkOutRequestVO.getRazorpay_order_id());
				ps.setLong(4, checkOutRequestVO.getTransactionID());

				if (ps.executeUpdate() > 0) {

					PreparedStatement pstmt = con.prepareStatement(
							"SELECT t.TransactionID, t.MIUID, t.Amount, t.Source, t.FixedCharges, t.ReconnectionCharges, tr.Tariff, tr.AlarmCredit, tr.EmergencyCredit FROM topup AS t LEFT JOIN tariff AS tr ON tr.TariffID = t.TariffID WHERE t.RazorPayOrderID = '"
									+ checkOutRequestVO.getRazorpay_order_id() + "' AND t.TransactionID = "
									+ checkOutRequestVO.getTransactionID());

					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {

						topUpRequestVO = new TopUpRequestVO();

						topUpRequestVO.setTransactionID(checkOutRequestVO.getTransactionID());
						topUpRequestVO.setAmount(rs.getInt("Amount"));
						topUpRequestVO.setMiuID(rs.getString("MIUID"));
						topUpRequestVO.setFixedCharges(rs.getInt("FixedCharges"));
						topUpRequestVO.setReconnectionCharges(rs.getInt("ReconnectionCharges"));
						topUpRequestVO.setAlarmCredit(rs.getFloat("AlarmCredit"));
						topUpRequestVO.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
						topUpRequestVO.setTariff(rs.getFloat("Tariff"));
						topUpRequestVO.setModeOfPayment("Online");

						if (rs.getString("Source").equalsIgnoreCase("Mobile")) {
							responseVO.setResult("Success");
							responseVO.setMessage("Payment Captured Successfully");
						} else {
							if (sendPayLoadToGateway(topUpRequestVO).getRestcallresponse() == 200) {
								logger.debug("Payload sent to gateway at " + LocalDateTime.now());
								responseVO.setResult("Success");
								responseVO.setMessage("Payment Captured & Topup Request Submitted Successfully");
								
							} else {
								ps = con.prepareStatement("UPDATE topup SET Status = 12 WHERE RazorPayOrderID = ? AND TransactionID = ?");

								ps.setString(1, checkOutRequestVO.getRazorpay_order_id());
								ps.setLong(2, checkOutRequestVO.getTransactionID());
								
								if(ps.executeUpdate() > 0) {
									responseVO.setResult("Failure");
									responseVO.setMessage("Payment Captured retry Topup Request From Status Page After Sometime");
								}
							}
						}

					}

				}

			} else {
				ps = con.prepareStatement("UPDATE topup SET PaymentStatus = 2, Status = 11, RazorPayPaymentID = ?, ErrorResponse = ? WHERE RazorPayOrderID = ? AND TransactionID = ?");

				ps.setString(1, checkOutRequestVO.getError().getMetadata().getPaymentId());
				ps.setString(2, checkOutRequestVO.getError().toString());
				ps.setString(3, checkOutRequestVO.getError().getMetadata().getOrderId());
				ps.setLong(4, checkOutRequestVO.getTransactionID());

				if (ps.executeUpdate() > 0) {
					responseVO.setResult("Success");
					responseVO.setMessage("Payment Failed");

				}

			}
		} else {
			
			if (generated_signature.equalsIgnoreCase(checkOutRequestVO.getRazorpay_signature())) {
				ps = con.prepareStatement("UPDATE billingpaymentdetails SET PaymentStatus = 1, RazorPayPaymentID = ?, RazorPaySignature = ? WHERE RazorPayOrderID = ? AND TransactionID = ?");

				ps.setString(1, checkOutRequestVO.getRazorpay_payment_id());
				ps.setString(2, checkOutRequestVO.getRazorpay_signature());
				ps.setString(3, checkOutRequestVO.getRazorpay_order_id());
				ps.setLong(4, checkOutRequestVO.getTransactionID());

				if (ps.executeUpdate() > 0) {
					responseVO.setResult("Success");
					responseVO.setMessage("Payment Captured Successfully");
					
				} else {
					ps = con.prepareStatement("UPDATE billingpaymentdetails SET PaymentStatus = 2, RazorPayPaymentID = ?, ErrorResponse = ? WHERE RazorPayOrderID = ? AND TransactionID = ?");

					ps.setString(1, checkOutRequestVO.getError().getMetadata().getPaymentId());
					ps.setString(2, checkOutRequestVO.getError().toString());
					ps.setString(3, checkOutRequestVO.getError().getMetadata().getOrderId());
					ps.setLong(4, checkOutRequestVO.getTransactionID());

					if (ps.executeUpdate() > 0) {
						responseVO.setResult("Success");
						responseVO.setMessage("Payment Failed. Please Try After Sometime");

					}
				}
			}
			
		}
		
		} catch (Exception e) {
			e.printStackTrace();

		}
		return responseVO;
	}

	public RestCallResponseVO sendPayLoadToGateway(TopUpRequestVO topUpRequestVO) throws SQLException {

		RestCallVO restcallvo = new RestCallVO();
		ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
		RestCallResponseVO restCallResponseVO = new RestCallResponseVO();
		Connection con = null;

		try {
			
			con = getConnection();
			
			restcallvo.setMiuID(topUpRequestVO.getMiuID().toLowerCase());
			restcallvo.setEmergency_credit(topUpRequestVO.getEmergencyCredit());
			restcallvo.setCredit(topUpRequestVO.getAmount()	- (topUpRequestVO.getFixedCharges() + topUpRequestVO.getReconnectionCharges()));
			restcallvo.setGatewayIP(topUpRequestVO.getGatewayIP());
			restcallvo.setGatewayPort(topUpRequestVO.getGatewayPort());
			restcallvo.setUrlExtension("/topup");
			
			// creating transaction id in topup
			if(topUpRequestVO.getModeOfPayment().equalsIgnoreCase("Cash")) {
				
				restcallvo.setTransaction_id(inserttopup(topUpRequestVO));
				restCallResponseVO.setTransactionID(restcallvo.getTransaction_id());
				
				if(topUpRequestVO.getSource().equalsIgnoreCase("web")) {
					
					restCallResponseVO.setRestcallresponse(extramethodsdao.postdata(restcallvo));
					
					if(restCallResponseVO.getRestcallresponse() != 200) {
						PreparedStatement ps = con.prepareStatement("UPDATE topup SET Status = 11 WHERE TransactionID = ?");

						ps.setLong(1, restcallvo.getTransaction_id());
						
						ps.executeUpdate();
					}
					
				} else {
					restCallResponseVO.setRestcallresponse(200);
				}
				
			} else {
				restcallvo.setTransaction_id(topUpRequestVO.getTransactionID());
				restCallResponseVO.setRestcallresponse(extramethodsdao.postdata(restcallvo));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return restCallResponseVO;
	}

	public int inserttopup(TopUpRequestVO topUpRequestVO) {

		Connection con = null;
		PreparedStatement ps = null;
		int transactionID = 0;

		try {
			con = getConnection();

			PreparedStatement pstmt = con.prepareStatement("SELECT cd.CommunityID, cd.BlockID, cd.CustomerID, cmd.MIUID, cmd.CustomerMeterID, cmd.TariffID FROM customerdetails AS cd LEFT JOIN customermeterdetails AS cmd ON cd.CustomerID = CMD.CustomerID WHERE cd.CustomerUniqueID = ? AND cmd.CustomerMeterID = " + topUpRequestVO.getCustomerMeterID());
			pstmt.setString(1, topUpRequestVO.getCustomerUniqueID());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				String sql = "INSERT INTO topup (CommunityID, BlockID, CustomerID, MIUID, CustomerMeterID, TariffID, Amount, EmergencyCredit, Status, FixedCharges, ReconnectionCharges, Source, ModeOfPayment, PaymentStatus, RazorPayOrderID, RazorPayPaymentID, RazorPaySignature, CreatedByID, CreatedByRoleID, CustomerUniqueID, AcknowledgeDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
				ps = con.prepareStatement(sql);

				ps.setInt(1, rs.getInt("CommunityID"));
				ps.setInt(2, rs.getInt("BlockID"));
				ps.setInt(3, rs.getInt("CustomerID"));
				ps.setString(4, rs.getString("MIUID"));
				ps.setLong(5, topUpRequestVO.getCustomerMeterID());
				ps.setInt(6, rs.getInt("TariffID"));
				ps.setFloat(7, topUpRequestVO.getAmount());
				ps.setFloat(8, topUpRequestVO.getEmergencyCredit());
				ps.setInt(9, 10);
				ps.setInt(10, topUpRequestVO.getFixedCharges());
				ps.setFloat(11, topUpRequestVO.getReconnectionCharges());
				ps.setString(12, topUpRequestVO.getSource());
				ps.setString(13, topUpRequestVO.getModeOfPayment());
				ps.setInt(14, topUpRequestVO.getPaymentStatus());
				ps.setString(15, topUpRequestVO.getRazorPayOrderID());
				ps.setString(16, topUpRequestVO.getRazorPayPaymentID());
				ps.setString(17, topUpRequestVO.getRazorPaySignature());
				ps.setInt(18, topUpRequestVO.getTransactedByID());
				ps.setInt(19, topUpRequestVO.getTransactedByRoleID());
				ps.setString(20, topUpRequestVO.getCustomerUniqueID());

				if (ps.executeUpdate() > 0) {
					PreparedStatement ps1 = con.prepareStatement("SELECT MAX(TransactionID) as TransactionID from topup");
					ResultSet rs1 = ps1.executeQuery();
					
					if(rs1.next()) {
						transactionID = rs1.getInt("TransactionID");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return transactionID;
	}

	public ResponseVO updatetopupstatus(DataRequestVO dataRequestVO, String miuID) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement ps = null;
		ResponseVO responsevo = new ResponseVO();
		DashboardDAO dashboarddao = new DashboardDAO();

		try {
			con = getConnection();
			
			//ALTER TABLE `idigitest`.`topup` ADD COLUMN `Reading` DECIMAL(10,2) NULL DEFAULT 0 AFTER `Amount`;
			ps = con.prepareStatement("UPDATE topup SET AcknowledgeDate = NOW(), Status = "+ dataRequestVO.getCmd_status() + ", Reading = "+dataRequestVO.getReading() +" WHERE TransactionID = "+ dataRequestVO.getTransaction_id());
			
			if(ps.executeUpdate() > 0) {
				
					dataRequestVO.setTopupSMS(true); 
					dataRequestVO.setTopupStatus(dataRequestVO.getCmd_status() == 0 ? "Success" : dataRequestVO.getCmd_status() == 10 ? "Pending" : "Failure");
					responsevo = dashboarddao.postDashboarddetails(dataRequestVO, miuID);
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			ps.close();
			con.close();
		}

		return responsevo;
	}
	
	/* Status */

	public List<StatusResponseVO> getStatusdetails(int roleid, String id, int filterCid, int day) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List<StatusResponseVO> statuslist = null;
		StatusResponseVO statusvo = null;
		try {
			con = getConnection();
			statuslist = new LinkedList<StatusResponseVO>();

			String query = "SELECT 	DISTINCT t.TransactionID, c.CommunityName, b.BlockName, cd.FirstName, cd.HouseNumber, t.CreatedByID, cd.LastName, cd.CustomerUniqueID, t.MIUID, t.CustomerMeterID, t.Amount, tr.AlarmCredit, tr.EmergencyCredit, t.Status, t.ModeOfPayment, t.PaymentStatus, t.RazorPayOrderID, t.RazorPayPaymentID, t.RazorPayRefundID, t.RazorPayRefundStatus, t.TransactionDate, t.AcknowledgeDate FROM topup AS t \r\n"
					+ "LEFT JOIN community AS c ON t.CommunityID = c.CommunityID LEFT JOIN block AS b ON t.BlockID = b.BlockID LEFT JOIN tariff AS tr ON tr.TariffID = t.tariffID \r\n"
					+ "LEFT JOIN customerdetails AS cd ON t.CustomerUniqueID = cd.CustomerUniqueID LEFT JOIN customermeterdetails as cmd ON cd.CustomerID = cmd.CustomerID WHERE t.TransactionDate BETWEEN CONCAT(CURDATE() <day>, ' 00:00:00') AND CONCAT(CURDATE(), ' 23:59:59') AND t.PaymentStatus !=0 <change>";
			query = query.replaceAll("<day>", (day == 1) ? "" : "- INTERVAL 90 DAY");
			pstmt = con.prepareStatement(query.replaceAll("<change>",
					((roleid == 1 || roleid == 4) && (filterCid == -1)) ? "ORDER BY t.TransactionDate DESC"
							: ((roleid == 1 || roleid == 4) && (filterCid != -1))
									? " AND t.CommunityID = " + filterCid + " ORDER BY t.TransactionDate DESC"
									: (roleid == 2 || roleid == 5)
											? "AND t.BlockID = " + id + " ORDER BY t.TransactionDate DESC"
											: (roleid == 3)
													? "AND t.CustomerUniqueID = '" + id + "' ORDER BY t.TransactionDate DESC"
													: ""));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				statusvo = new StatusResponseVO();
				statusvo.setTransactionID(rs.getInt("TransactionID"));
				statusvo.setCommunityName(rs.getString("CommunityName"));
				statusvo.setBlockName(rs.getString("BlockName"));
				statusvo.setFirstName(rs.getString("FirstName"));
				statusvo.setLastName(rs.getString("LastName"));
				statusvo.setHouseNumber(rs.getString("HouseNumber"));
				statusvo.setCustomerUniqueID(rs.getString("CustomerUniqueID"));
				statusvo.setMiuID(rs.getString("MIUID"));
				statusvo.setAmount(rs.getString("Amount"));
				statusvo.setModeOfPayment(rs.getString("ModeOfPayment"));
				statusvo.setRazorPayOrderID(rs.getString("ModeOfPayment").equalsIgnoreCase("Cash") ? "---"
						: rs.getString("RazorPayOrderID"));
				statusvo.setRazorPayPaymentID(rs.getString("ModeOfPayment").equalsIgnoreCase("Cash") ? "---"
						: rs.getString("RazorPayPaymentID"));
				statusvo.setRazorPayRefundID(
						(rs.getInt("PaymentStatus") == 3 ? rs.getString("RazorPayRefundID") : "---"));
				statusvo.setRazorPayRefundStatus(
						(rs.getInt("PaymentStatus") == 3 ? rs.getString("RazorPayRefundStatus") : "---"));
				statusvo.setPaymentStatus((rs.getInt("PaymentStatus") == 1 ? "PAID"
						: (rs.getInt("PaymentStatus") == 2) ? "FAILED"
								: (rs.getInt("PaymentStatus") == 3) ? "REFUND INITITATED" : "NOT PAID"));
				statusvo.setAlarmCredit(rs.getString("AlarmCredit"));
				statusvo.setEmergencyCredit(rs.getString("EmergencyCredit"));
				statusvo.setTransactionDate(ExtraMethodsDAO.datetimeformatter(rs.getString("TransactionDate")));
				statusvo.setStatus(rs.getInt("Status") == 0 ? "Passed"	:  rs.getInt("Status") == 10 ? "Pending" : rs.getInt("Status") == 12 ? "Retry" : "Failed");

				pstmt1 = con.prepareStatement("SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "+ rs.getInt("CreatedByID"));
				rs1 = pstmt1.executeQuery();
				if (rs1.next()) {
					statusvo.setTransactedByUserName(rs1.getString("UserName"));
					statusvo.setTransactedByRoleDescription(rs1.getString("RoleDescription"));
				}

				statuslist.add(statusvo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();

			con.close();
		}
		return statuslist;
	}
	
	public ResponseVO retryTopup(long transactionID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();

			pstmt = con.prepareStatement("SELECT * FROM topup WHERE Status = 12 AND TransactionID = " + transactionID);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				TopUpRequestVO topUpRequestVO = new TopUpRequestVO();
				
				topUpRequestVO.setTransactionID(transactionID);
				topUpRequestVO.setMiuID(rs.getString("MIUID"));
				topUpRequestVO.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
				topUpRequestVO.setFixedCharges(rs.getInt("FixedCharges"));
				topUpRequestVO.setReconnectionCharges(rs.getInt("ReconnectionCharges"));
				topUpRequestVO.setModeOfPayment("Online");
				
				PreparedStatement ps = con.prepareStatement("SELECT GatewayIP, GatewayPort FROM gateway WHERE GatewayID = (SELECT GatewayID FROM customermeterdetails WHERE CustomerMeterID = "+rs.getLong("CustomerMeterID") +")");
				ResultSet rs1 = ps.executeQuery();
				if(rs1.next()) {
					topUpRequestVO.setGatewayIP(rs1.getString("GatewayIP"));
					topUpRequestVO.setGatewayPort(rs1.getInt("GatewayPort"));
				} 
				
				if(sendPayLoadToGateway(topUpRequestVO).getRestcallresponse() == 200) {
					responsevo.setResult("Success");
					responsevo.setMessage("Retry Request raised Successfully");
				} else {
					responsevo.setResult("Failed");
					responsevo.setMessage("Retry Request Failed. Please Try After Sometime");
				}
				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");

		} finally {
			pstmt.close();
			con.close();
		}

		return responsevo;
	}

	public ResponseVO deletestatus(long transactionID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();

			pstmt = con.prepareStatement("DELETE FROM topup where TransactionID = " + transactionID);

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Deleted Successfully");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");

		} finally {
			pstmt.close();
			con.close();
		}

		return responsevo;
	}

	public ResponseVO printreceipt(int transactionID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResponseVO responsevo = new ResponseVO();
		String drivename = "D:/TopupReceipts";

		try {
			con = getConnection();

			String query = "SELECT t.TransactionID, c.CommunityName, b.BlockName, cd.FirstName, cd.HouseNumber, cd.CreatedByID, cd.LastName, cd.CustomerUniqueID, t.MIUID, t.CustomerMeterID, t.Amount, t.Reading, tr.AlarmCredit, t.FixedCharges, t.ReconnectionCharges, tr.EmergencyCredit, tr.Tariff, t.Status, t.ModeOfPayment, t.PaymentStatus, t.RazorPayOrderID, t.RazorPayPaymentID, t.RazorPayRefundID, t.RazorPayRefundStatus, t.TransactionDate, t.AcknowledgeDate FROM topup AS t \r\n"
					+ "LEFT JOIN community AS c ON t.CommunityID = c.CommunityID LEFT JOIN block AS b ON t.BlockID = b.BlockID LEFT JOIN tariff AS tr ON tr.TariffID = t.tariffID \r\n"
					+ "LEFT JOIN customerdetails AS cd ON t.CustomerUniqueID = cd.CustomerUniqueID LEFT JOIN customermeterdetails as cmd ON cd.CustomerID = cmd.CustomerID WHERE t.TransactionID = "+ transactionID;

			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pstmt1 = con.prepareStatement("SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "+ rs.getInt("CreatedByID"));
				rs1 = pstmt1.executeQuery();
				if (rs1.next()) {

					File directory = new File(drivename); 
					if (!directory.exists()) {
						directory.mkdir();
					}

					PdfWriter writer = new PdfWriter(drivename + transactionID + ".pdf");
					PdfDocument pdfDocument = new PdfDocument(writer);
					pdfDocument.addNewPage();
					Document document = new Document(pdfDocument);
					Paragraph newLine = new Paragraph("\n");
					Paragraph head = new Paragraph("Gas Bill Receipt");
					Paragraph disclaimer = new Paragraph(ExtraConstants.Disclaimer);
					Paragraph copyRight = new Paragraph(
							"------------------------------------All  rights reserved by IDigitronics Hyderabad----------------------------------");
					PdfFont font = new PdfFontFactory().createFont(FontConstants.TIMES_BOLD);

					// change according to the image directory

					URL idigiurl = new URL(ExtraConstants.IDIGIIMAGEURL);
					URL clienturl = new URL(ExtraConstants.CLIENTIMAGEURL);
					Image idigi = new Image(ImageDataFactory.create(idigiurl));
					Image client = new Image(ImageDataFactory.create(clienturl));
					// Image technology = new
					// Image(ImageDataFactory.create("C:/TopupReceipts/lorawan.png"));
					// Image mode = new
					// Image(ImageDataFactory.create("C:/TopupReceipts/bluetooth.png"));

					float[] headingWidths = { 200F, 130F, 200F };

					Table headTable = new Table(headingWidths);

					Cell headtable1 = new Cell();
					headtable1.add(idigi);
					headtable1.setTextAlignment(TextAlignment.LEFT);

					Cell headtable2 = new Cell();
					headtable2.add(head.setFontSize(17));
					headtable2.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE)
							.setBold().setUnderline().setFont(font);

					Cell headtable3 = new Cell();
					headtable3.add(client);
					headtable3.setTextAlignment(TextAlignment.RIGHT);

					headTable.addCell(headtable1.setBorder(Border.NO_BORDER));
					headTable.addCell(headtable2.setBorder(Border.NO_BORDER));
					headTable.addCell(headtable3.setBorder(Border.NO_BORDER));

					document.add(headTable);
					document.add(newLine);

					float[] headerWidths = { 200F, 180F, 170F };

					Table table1 = new Table(headerWidths);

					Cell table1cell1 = new Cell();
					table1cell1.add("MIU ID: " + rs.getString("MIUID"));
					table1cell1.setTextAlignment(TextAlignment.LEFT);

					Cell table1cell2 = new Cell();
					table1cell2.add("CRN/UAN Number: " + rs.getString("CustomerUniqueID"));
					table1cell2.setTextAlignment(TextAlignment.CENTER);

					Cell table1cell3 = new Cell();
					table1cell3.add("Invoice No. : " + transactionID);
					table1cell3.setTextAlignment(TextAlignment.RIGHT);
					
					Cell table1cell4 = new Cell();
					table1cell4.add("Community: " + rs.getString("CommunityName"));
					table1cell4.setTextAlignment(TextAlignment.LEFT);

					Cell table1cell5 = new Cell();
					table1cell5.add("Block: " + rs.getString("BlockName"));
					table1cell5.setTextAlignment(TextAlignment.CENTER);

					Cell table1cell6 = new Cell();
					table1cell6.add("House No. : " + rs.getString("HouseNumber"));
					table1cell6.setTextAlignment(TextAlignment.RIGHT);

					table1.addCell(table1cell1.setBorder(Border.NO_BORDER));
					table1.addCell(table1cell2.setBorder(Border.NO_BORDER));
					table1.addCell(table1cell3.setBorder(Border.NO_BORDER));
					table1.startNewRow();
					table1.addCell(table1cell4.setBorder(Border.NO_BORDER));
					table1.addCell(table1cell5.setBorder(Border.NO_BORDER));
					table1.addCell(table1cell6.setBorder(Border.NO_BORDER));

					document.add(table1.setHorizontalAlignment(HorizontalAlignment.CENTER));
					document.add(newLine);

					float[] columnWidths = { 400F, 150F };

					Table datatable = new Table(columnWidths);

					Cell cell1 = new Cell();
					cell1.add("Customer Name: ");
					cell1.setTextAlignment(TextAlignment.CENTER);

					Cell customerName = new Cell();
					customerName.add(rs.getString("FirstName") + " " + rs.getString("LastName"));
					customerName.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell1);
					datatable.addCell(customerName);
					datatable.startNewRow();

					Cell cell2 = new Cell();
					cell2.add("Recharge Amount: ");
					cell2.setTextAlignment(TextAlignment.CENTER);

					Cell Amount = new Cell();
					Amount.add(rs.getString("Amount"));
					Amount.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell2);
					datatable.addCell(Amount);
					datatable.startNewRow();

					Cell cell3 = new Cell();
					cell3.add("Service Charges(if any): ");
					cell3.setTextAlignment(TextAlignment.CENTER);

					Cell fixedCharges = new Cell();
					fixedCharges.add(rs.getString("FixedCharges"));
					fixedCharges.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell3);
					datatable.addCell(fixedCharges);
					datatable.startNewRow();

					Cell cell4 = new Cell();
					cell4.add("Reconnection Charges(if any): ");
					cell4.setTextAlignment(TextAlignment.CENTER);

					Cell reconnectionCharges = new Cell();
					reconnectionCharges.add(rs.getString("ReconnectionCharges"));
					reconnectionCharges.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell4);
					datatable.addCell(reconnectionCharges);
					datatable.startNewRow();

					Cell cell5 = new Cell();
					cell5.add("Available Balance Amount: ");
					cell5.setTextAlignment(TextAlignment.CENTER);

					Cell finalAmount = new Cell();
					finalAmount.add(Integer.toString(
							(rs.getInt("Amount") - (rs.getInt("FixedCharges") + rs.getInt("ReconnectionCharges")))));
					finalAmount.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell5);
					datatable.addCell(finalAmount);
					datatable.startNewRow();
					
					Cell cell6 = new Cell();
					cell6.add("Emergency Amount: ");
					cell6.setTextAlignment(TextAlignment.CENTER);

					Cell emergencyAmount = new Cell();
					emergencyAmount.add(rs.getString("EmergencyCredit"));
					emergencyAmount.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell6);
					datatable.addCell(emergencyAmount);
					datatable.startNewRow();
					
					Cell cell7 = new Cell();
					cell7.add("Tariff: ");
					cell7.setTextAlignment(TextAlignment.CENTER);

					Cell tariff = new Cell();
					tariff.add(rs.getString("Tariff"));
					tariff.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell7);
					datatable.addCell(tariff);
					datatable.startNewRow();
					
					Cell cell8 = new Cell();
					cell8.add("Previous Reading: ");
					cell8.setTextAlignment(TextAlignment.CENTER);
					
					Cell previousReading = new Cell();
					
					PreparedStatement prevReading = con.prepareStatement("SELECT Reading FROM topup where CustomerMeterID = "+rs.getLong("CustomerMeterID")+" and Status = 0 and TransactionID < "+ transactionID +" order by TransactionID desc Limit 0,1");
					ResultSet prevReadingrs = prevReading.executeQuery();
					
					if(prevReadingrs.next()) {
						previousReading.add((Objects.nonNull(prevReadingrs.getString("Reading"))) ? prevReadingrs.getString("Reading") : "---"); //fetch prev reading
					} else {
						previousReading.add("---"); 
					}
					
					previousReading.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell8);
					datatable.addCell(previousReading);
					datatable.startNewRow();

					Cell cell9 = new Cell();
					cell9.add("Present Reading: ");
					cell9.setTextAlignment(TextAlignment.CENTER);

					Cell presentReading = new Cell();
					presentReading.add((Objects.nonNull(rs.getString("Reading"))) ? rs.getString("Reading") : "---");
					presentReading.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell9);
					datatable.addCell(presentReading);
					datatable.startNewRow();
					
					Cell cell10 = new Cell();
					cell10.add("Mode of Payment: ");
					cell10.setTextAlignment(TextAlignment.CENTER);

					Cell modeOfPayment = new Cell();
					modeOfPayment.add(rs.getString("ModeOfPayment"));
					modeOfPayment.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell10);
					datatable.addCell(modeOfPayment);
					datatable.startNewRow();

					Cell cell11 = new Cell();
					cell11.add("Transaction Initiated By: ");
					cell11.setTextAlignment(TextAlignment.CENTER);

					Cell transactedBy = new Cell();
					transactedBy.add(rs1.getString("UserName"));
					transactedBy.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell11);
					datatable.addCell(transactedBy);
					datatable.startNewRow();

				/*	Cell cell8 = new Cell();
					cell8.add("Date of Transaction: ");
					cell8.setTextAlignment(TextAlignment.CENTER);

					Cell transactionDate = new Cell();
					transactionDate.add(ExtraMethodsDAO.datetimeformatter(rs.getString("TransactionDate")));
					transactionDate.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell8);
					datatable.addCell(transactionDate);
					datatable.startNewRow(); */

					Cell cell12 = new Cell();
					cell12.add("Bill Date: ");
					cell12.setTextAlignment(TextAlignment.CENTER);

					Cell acknowledgeDate = new Cell();
					acknowledgeDate.add(ExtraMethodsDAO.datetimeformatter(rs.getString("AcknowledgeDate")));
					acknowledgeDate.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell12);
					datatable.addCell(acknowledgeDate);
					datatable.startNewRow();

					Cell cell13 = new Cell();
					cell13.add("Order ID: ");
					cell13.setTextAlignment(TextAlignment.CENTER);

					Cell OrderID = new Cell();
					OrderID.add(rs.getString("RazorPayOrderID") == null ? "---" : rs.getString("RazorPayOrderID"));
					OrderID.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell13);
					datatable.addCell(OrderID);
					datatable.startNewRow();

					Cell cell14 = new Cell();
					cell14.add("Payment ID: ");
					cell14.setTextAlignment(TextAlignment.CENTER);

					Cell PaymentID = new Cell();
					PaymentID
							.add(rs.getString("RazorPayPaymentID") == null ? "---" : rs.getString("RazorPayPaymentID"));
					PaymentID.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell14);
					datatable.addCell(PaymentID);
					datatable.startNewRow();

					document.add(datatable.setHorizontalAlignment(HorizontalAlignment.CENTER));
					document.add(disclaimer.setHorizontalAlignment(HorizontalAlignment.CENTER).setFont(font));
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);

					document.add(copyRight.setHorizontalAlignment(HorizontalAlignment.CENTER).setFont(font));
					document.close();

					responsevo.setResult("Success");
					responsevo.setLocation(drivename);
					responsevo.setFileName(transactionID + ".pdf");

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setResult("Failure");
			responsevo.setMessage("INTERNAL SERVER ERROR");
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return responsevo;

	}
	
	/* Billing Details*/
	
	public List<BillingResponseVO> getbillingdetails(int roleid, String id, int filterCid) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		List<BillingResponseVO> billlist = null;
		List<IndividualBillingResponseVO> individualbills = null;
		BillingResponseVO billingresponsevo = null;
		IndividualBillingResponseVO individualBillingResponsevo = null;
		LocalDate currentdate = LocalDate.now();
		
		try {
			con = getConnection();
			String query = "SELECT c.CommunityName, b.BlockName, cd.FirstName, cd.LastName, cd.HouseNumber, cd.CustomerUniqueID, cd.CustomerID, cbd.CustomerBillingID, cbd.TotalAmount, cbd.TaxAmount, cbd.TotalConsumption, cbd.PreviousDues, cbd.DueDate, cbd.Status, cbd.BillMonth, cbd.BillYear, cbd.LogDate FROM customerdetails AS cd LEFT JOIN customerbillingdetails AS cbd ON cd.CustomerID = cbd.CustomerID LEFT JOIN community AS c ON c.CommunityID = cd.CommunityID LEFT JOIN block AS b ON b.BlockID = cd.BlockID WHERE cbd.BillMonth = "+ ((currentdate.getMonthValue() - 1) == 0 ? 12 : (currentdate.getMonthValue() - 1)) +" AND cbd.BillYear = "+ (currentdate.getMonthValue() == 1 ? (currentdate.getYear() - 1) : currentdate.getYear()) +" <change>"; 
			pstmt = con.prepareStatement(query.replaceAll("<change>", ((roleid == 1 || roleid == 4) && (filterCid == -1)) ? "ORDER BY cd.CustomerID DESC" : ((roleid == 1 || roleid == 4) && (filterCid != -1)) ? " AND cd.CommunityID = "+filterCid+" ORDER BY cd.CustomerID DESC" : (roleid == 2 || roleid == 5) ? "AND cd.BlockID = "+id+ " ORDER BY cd.CustomerID DESC" : (roleid == 3) ? "AND cd.CustomerUniqueID = '"+id+"'":""));
			rs = pstmt.executeQuery();
			
			billlist = new LinkedList<BillingResponseVO>();
			while(rs.next()) {
				
				billingresponsevo = new BillingResponseVO();
				billingresponsevo.setCustomerBillingID(rs.getLong("CustomerBillingID"));
				billingresponsevo.setCommunityName(rs.getString("CommunityName"));
				billingresponsevo.setBlockName(rs.getString("BlockName"));
				billingresponsevo.setCustomerName(rs.getString("FirstName") + " "+ rs.getString("LastName"));
				billingresponsevo.setHouseNumber(rs.getString("HouseNumber"));
				billingresponsevo.setCustomerID(rs.getLong("CustomerID"));
				billingresponsevo.setCustomerUniqueID(rs.getString("CustomerUniqueID"));
				billingresponsevo.setTotalAmount(rs.getFloat("TotalAmount") + rs.getFloat("TaxAmount"));
				billingresponsevo.setAmount(rs.getFloat("TotalAmount"));
				billingresponsevo.setTax(rs.getFloat("TaxAmount"));
				billingresponsevo.setTotalConsumption(rs.getFloat("TotalConsumption"));
				billingresponsevo.setPreviousDues(rs.getFloat("PreviousDues"));
				billingresponsevo.setDueDate(rs.getString("DueDate"));
				
				PreparedStatement ps = con.prepareStatement("SELECT bpd.PaymentStatus, bpd.ModeofPayment, bpd.TransactionDate, u.UserName FROM billingpaymentdetails AS bpd LEFT JOIN user AS u ON u.ID = bpd.CreatedByID WHERE bpd.CustomerBillingID = " + rs.getLong("CustomerBillingID") +" ORDER BY bpd.TransactionID DESC LIMIT 1");
				ResultSet rs2 = ps.executeQuery();
				if(rs2.next()) {
					billingresponsevo.setStatus((rs2.getInt("PaymentStatus") == 1) ? "Paid" : "Pending");
					billingresponsevo.setModeOfPayment(rs2.getString("ModeofPayment") != null ? rs2.getString("ModeofPayment") : "---");
					billingresponsevo.setTransactedBy(rs2.getString("UserName") != null ? rs2.getString("UserName") : "---");
					billingresponsevo.setPaidDate(rs2.getString("TransactionDate") != null ? ExtraMethodsDAO.datetimeformatter(rs2.getString("TransactionDate")) : "---");
				}else {
					billingresponsevo.setStatus("Pending");
					billingresponsevo.setModeOfPayment("---");
					billingresponsevo.setTransactedBy("---");
					billingresponsevo.setPaidDate("---");
				}

				billingresponsevo.setBillMonth(rs.getInt("BillMonth") == 1 ? "January" : rs.getInt("BillMonth") == 2 ? "February" : rs.getInt("BillMonth") == 3 ? "March" : rs.getInt("BillMonth") == 4 ? "April" : rs.getInt("BillMonth") == 5 ? "May" : rs.getInt("BillMonth") == 6 ? "June" : rs.getInt("BillMonth") == 7 ? "July" : rs.getInt("BillMonth") == 8 ? "August" : rs.getInt("BillMonth") == 9 ? "September" : rs.getInt("BillMonth") == 10 ? "October" : rs.getInt("BillMonth") == 11 ? "November" : rs.getInt("BillMonth") == 12 ? "December" : "");
				billingresponsevo.setBillYear(rs.getInt("BillYear"));
				billingresponsevo.setLogDate(rs.getString("LogDate"));
				
				pstmt1 = con.prepareStatement("SELECT * FROM billingdetails WHERE CustomerID = " + rs.getInt("CustomerID") + " AND BillMonth = "+ ((currentdate.getMonthValue() - 1) == 0 ? 12 : (currentdate.getMonthValue() - 1)) + " AND BillYear = " + (currentdate.getMonthValue() == 1 ? currentdate.getYear() - 1 : currentdate.getYear()));
				rs1 = pstmt1.executeQuery();
				individualbills = new LinkedList<IndividualBillingResponseVO>();
				while (rs1.next()) {
					
					individualBillingResponsevo = new IndividualBillingResponseVO();
					individualBillingResponsevo.setBillingID(rs1.getLong("BillingID"));
					individualBillingResponsevo.setCustomerMeterID(rs1.getLong("CustomerMeterID"));
					individualBillingResponsevo.setMiuID(rs1.getString("MIUID"));
					individualBillingResponsevo.setMeterType(rs1.getString("MeterType"));
					individualBillingResponsevo.setPreviousReading(rs1.getFloat("PreviousReading"));
					individualBillingResponsevo.setPresentReading(rs1.getFloat("PresentReading"));
					individualBillingResponsevo.setConsumption(rs1.getFloat("Consumption"));
					individualBillingResponsevo.setBillAmount(rs1.getFloat("BillAmount"));
					individualBillingResponsevo.setTariff(rs1.getFloat("Tariff"));
					individualBillingResponsevo.setBillingDate(ExtraMethodsDAO.datetimeformatter(rs1.getString("LogDate")));
					
					individualbills.add(individualBillingResponsevo);

				}
				billingresponsevo.setIndividualbills(individualbills);
				billlist.add(billingresponsevo);
				
				billlist.removeIf(e -> e.getIndividualbills().size()==0);
				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return billlist;
	}
	
	public ResponseVO billingFile(BillingResponseVO billingResponseVO) {
		// TODO Auto-generated method stub

		ResponseVO responsevo = new ResponseVO();
		ByteArrayInputStream in = null;
		
		try {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet spreadsheet = workbook.createSheet("Billing Status List");
		
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		
		XSSFRow header = spreadsheet.createRow(0);
		
		int columnCount = 0;
		int individualBillingColumnCount = 0;
        
		org.apache.poi.ss.usermodel.Cell headercell1 = header.createCell(columnCount);
        headercell1.setCellValue("Community");
        
        org.apache.poi.ss.usermodel.Cell headercell2 = header.createCell(++columnCount);
        headercell2.setCellValue("Block");
        
        org.apache.poi.ss.usermodel.Cell headercell3 = header.createCell(++columnCount);
        headercell3.setCellValue("House Number");
        
        org.apache.poi.ss.usermodel.Cell headercell4 = header.createCell(++columnCount);
        headercell4.setCellValue("Total Consumption");
        
        org.apache.poi.ss.usermodel.Cell headercell5 = header.createCell(++columnCount);
        headercell5.setCellValue("Total Amount");
        
        org.apache.poi.ss.usermodel.Cell headercell6 = header.createCell(++columnCount);
        headercell6.setCellValue("Transacted By");
        
        org.apache.poi.ss.usermodel.Cell headercell7 = header.createCell(++columnCount);
        headercell7.setCellValue("Mode of Payment");
        
        org.apache.poi.ss.usermodel.Cell headercell8 = header.createCell(++columnCount);
        headercell8.setCellValue("Paid Date");
        
        org.apache.poi.ss.usermodel.Cell headercell9 = header.createCell(++columnCount);
        headercell9.setCellValue("Bill Month");
        
        org.apache.poi.ss.usermodel.Cell headercell10 = header.createCell(++columnCount);
        headercell10.setCellValue("Bill Year");
        
        org.apache.poi.ss.usermodel.Cell headercell11 = header.createCell(++columnCount);
        headercell11.setCellValue("Log Date");
        
        org.apache.poi.ss.usermodel.Cell headercell12 = header.createCell(++columnCount);
        headercell12.setCellValue("Status");
        
        org.apache.poi.ss.usermodel.Cell headercell13 = header.createCell(++columnCount);
        headercell13.setCellValue("MIUID");
        
        org.apache.poi.ss.usermodel.Cell headercell14 = header.createCell(++columnCount);
        headercell14.setCellValue("Meter Type");
                
        org.apache.poi.ss.usermodel.Cell headercell15 = header.createCell(++columnCount);
        headercell15.setCellValue("Previous Reading");
        
        org.apache.poi.ss.usermodel.Cell headercell16 = header.createCell(++columnCount);
        headercell16.setCellValue("Present Reading");
        
        org.apache.poi.ss.usermodel.Cell headercell17 = header.createCell(++columnCount);
        headercell17.setCellValue("Consumption");
        
        org.apache.poi.ss.usermodel.Cell headercell18 = header.createCell(++columnCount);
        headercell18.setCellValue("Tariff");
        
        org.apache.poi.ss.usermodel.Cell headercell19 = header.createCell(++columnCount);
        headercell19.setCellValue("Bill Amount");
        
        for(int i = 0; i< billingResponseVO.getData().size(); i++) {
        	
        	int billingColumnCount = 0;
        	XSSFRow data = spreadsheet.createRow(spreadsheet.getLastRowNum()+1);
        	
        	org.apache.poi.ss.usermodel.Cell cell1 = data.createCell(billingColumnCount);
            cell1.setCellValue(billingResponseVO.getData().get(i).getCommunityName());
            
            org.apache.poi.ss.usermodel.Cell cell2 = data.createCell(++billingColumnCount);
            cell2.setCellValue(billingResponseVO.getData().get(i).getBlockName());
            
            org.apache.poi.ss.usermodel.Cell cell3 = data.createCell(++billingColumnCount);
            cell3.setCellValue(billingResponseVO.getData().get(i).getHouseNumber());
            
            org.apache.poi.ss.usermodel.Cell cell4 = data.createCell(++billingColumnCount);
            cell4.setCellValue(billingResponseVO.getData().get(i).getTotalConsumption());
            
            org.apache.poi.ss.usermodel.Cell cell5 = data.createCell(++billingColumnCount);
            cell5.setCellValue(billingResponseVO.getData().get(i).getTotalAmount());
            
            org.apache.poi.ss.usermodel.Cell cell6 = data.createCell(++billingColumnCount);
        	cell6.setCellValue(billingResponseVO.getData().get(i).getTransactedBy());
        	
        	org.apache.poi.ss.usermodel.Cell cell7 = data.createCell(++billingColumnCount);
        	cell7.setCellValue(billingResponseVO.getData().get(i).getModeOfPayment());
        	
        	org.apache.poi.ss.usermodel.Cell cell8 = data.createCell(++billingColumnCount);
        	cell8.setCellValue(billingResponseVO.getData().get(i).getPaidDate());
        	
        	org.apache.poi.ss.usermodel.Cell cell9 = data.createCell(++billingColumnCount);
        	cell9.setCellValue(billingResponseVO.getData().get(i).getBillMonth());
        	
        	org.apache.poi.ss.usermodel.Cell cell10 = data.createCell(++billingColumnCount);
        	cell10.setCellValue(billingResponseVO.getData().get(i).getBillYear());
        	
        	org.apache.poi.ss.usermodel.Cell cell11 = data.createCell(++billingColumnCount);
        	cell11.setCellValue(billingResponseVO.getData().get(i).getLogDate());
        	
        	org.apache.poi.ss.usermodel.Cell cell12 = data.createCell(++billingColumnCount);
        	cell12.setCellValue(billingResponseVO.getData().get(i).getStatus());
            
            for(int j = 0; j < billingResponseVO.getData().get(i).getIndividualbills().size(); j++) {
            	
            	individualBillingColumnCount = 12;
            	
            	org.apache.poi.ss.usermodel.Cell cell13 = data.createCell(individualBillingColumnCount);
            	cell13.setCellValue(billingResponseVO.getData().get(i).getIndividualbills().get(j).getMiuID());
            	
            	org.apache.poi.ss.usermodel.Cell cell14 = data.createCell(++individualBillingColumnCount);
            	cell14.setCellValue(billingResponseVO.getData().get(i).getIndividualbills().get(j).getMeterType());
            	
            	org.apache.poi.ss.usermodel.Cell cell15 = data.createCell(++individualBillingColumnCount);
            	cell15.setCellValue(billingResponseVO.getData().get(i).getIndividualbills().get(j).getPreviousReading());
            	
            	org.apache.poi.ss.usermodel.Cell cell16 = data.createCell(++individualBillingColumnCount);
            	cell16.setCellValue(billingResponseVO.getData().get(i).getIndividualbills().get(j).getPresentReading());
            	
            	org.apache.poi.ss.usermodel.Cell cell17 = data.createCell(++individualBillingColumnCount);
            	cell17.setCellValue(billingResponseVO.getData().get(i).getIndividualbills().get(j).getConsumption());
            	
            	org.apache.poi.ss.usermodel.Cell cell18 = data.createCell(++individualBillingColumnCount);
            	cell18.setCellValue(billingResponseVO.getData().get(i).getIndividualbills().get(j).getTariff());
            	
            	org.apache.poi.ss.usermodel.Cell cell19 = data.createCell(++individualBillingColumnCount);
            	cell19.setCellValue(billingResponseVO.getData().get(i).getIndividualbills().get(j).getBillAmount());
            	
            	if(j < billingResponseVO.getData().get(i).getIndividualbills().size() - 1) { data = spreadsheet.createRow(spreadsheet.getLastRowNum()+1); }
            	
            }
                       
        }
        
        workbook.write(outByteStream);
		in = new ByteArrayInputStream(outByteStream.toByteArray());
		workbook.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        responsevo.setResult("Success");
		responsevo.setByteArrayInputStream(in);
		responsevo.setFileName("Billing.xlsx");
        
		return responsevo;
		
	}
	
	public ResponseVO paybill(PayBillRequestVO paybillRequestVO) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		ResponseVO responsevo = new ResponseVO();
		ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
		RazorPayOrderVO razorPayOrderVO = new RazorPayOrderVO();
		CheckoutDetails checkoutDetails = new CheckoutDetails();
		RazorpayRequestVO razorpayRequestVO = new RazorpayRequestVO();
		Prefill prefill = new Prefill();
		Notes notes = new Notes();
		Theme theme = new Theme();

		try {
			con = getConnection();
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT CustomerID, CustomerUniqueID, FirstName, LastName, HouseNumber, MobileNumber, Email FROM customerdetails WHERE CustomerUniqueID = '" + paybillRequestVO.getCustomerUniqueID() +"'");
			ResultSet rs1 = pstmt1.executeQuery();
			
			if(rs1.next()) {
					
					PreparedStatement pstmt3 = con.prepareStatement("SELECT cbd.TotalAmount, cbd.TaxAmount, cbd.PreviousDues, al.LateFee, DATEDIFF(NOW(),cbd.DueDate) AS DueDays FROM customerbillingdetails AS cbd JOIN alertsettings AS al WHERE cbd.CustomerBillingID = "+paybillRequestVO.getCustomerBillingID());
					ResultSet rs3 = pstmt3.executeQuery();
					
					if(rs3.next()) {
						
						paybillRequestVO.setTotalamount(rs3.getFloat("TotalAmount"));
						paybillRequestVO.setPreviousDues(rs3.getFloat("PreviousDues"));
						paybillRequestVO.setTaxAmount(rs3.getFloat("TaxAmount"));
						paybillRequestVO.setLateFee(rs3.getInt("DueDays") >= 1 ? (rs3.getInt("LateFee")*rs3.getInt("DueDays")) : 0);
						
						String message = "Hi, Payment of Rs. "+ (int) (paybillRequestVO.getTotalamount() + paybillRequestVO.getTaxAmount()+ paybillRequestVO.getPreviousDues() + paybillRequestVO.getLateFee()) +" is received for your Postpaid Bill. Thank You.";
						
						SMSRequestVO smsRequestVO = new SMSRequestVO();
						
						smsRequestVO.setMessage(message);
						smsRequestVO.setToMobileNumber(rs1.getString("MobileNumber"));
						
						if(paybillRequestVO.getModeOfPayment().equalsIgnoreCase("Online")) {
							
						long transactionID = insertbillingpayment(paybillRequestVO);
						
						if (transactionID != 0) {

							// creating order in razor pay
							
							razorPayOrderVO.setAmount((int) ((paybillRequestVO.getTotalamount() + paybillRequestVO.getTaxAmount()+ paybillRequestVO.getPreviousDues() + paybillRequestVO.getLateFee()) * 100));
							razorPayOrderVO.setCurrency("INR");
							razorPayOrderVO.setPayment_capture(1);

							razorpayRequestVO.setApi("orders");
							String rzpRestCallResponse = extramethodsdao.razorpaypost(razorPayOrderVO, razorpayRequestVO);

							RazorPayResponseVO razorPayResponseVO = gson.fromJson(rzpRestCallResponse, RazorPayResponseVO.class);

							paybillRequestVO.setRazorPayOrderID(razorPayResponseVO.getId());

							PreparedStatement pstmt2 = con.prepareStatement("UPDATE billingpaymentdetails SET RazorPayOrderID = ? WHERE TransactionID = " + transactionID);
							pstmt2.setString(1, paybillRequestVO.getRazorPayOrderID());
							if (pstmt2.executeUpdate() > 0) {

								checkoutDetails.setKey(ExtraConstants.RZPKeyID);
								checkoutDetails.setAmount((paybillRequestVO.getTotalamount() + paybillRequestVO.getTaxAmount() + paybillRequestVO.getLateFee()) * 100);
								checkoutDetails.setCurrency(ExtraConstants.PaymentCurrency);
								checkoutDetails.setOrder_id(paybillRequestVO.getRazorPayOrderID());
								checkoutDetails.setButtonText(ExtraConstants.PaymentButtonText);
								checkoutDetails.setName(ExtraConstants.CompanyName);
								checkoutDetails.setDescription("Payment of INR " + (paybillRequestVO.getTotalamount() + paybillRequestVO.getTaxAmount() + paybillRequestVO.getLateFee())
										+ "/- for CRN/CAN: " + rs1.getString("CustomerUniqueID") + ".");
								checkoutDetails.setImage(ExtraConstants.IDIGIIMAGEURL);

								prefill.setName(rs1.getString("FirstName") + " " + rs1.getString("LastName"));
								prefill.setEmail(rs1.getString("Email"));
								prefill.setContact(rs1.getString("MobileNumber"));
								checkoutDetails.setPrefill(prefill);

								theme.setColor(ExtraConstants.PaymentThemeColor);
								checkoutDetails.setTheme(theme);

								notes.setAddress(rs1.getString("HouseNumber"));
								checkoutDetails.setTransactionID(transactionID);

								responsevo.setCheckoutDetails(checkoutDetails);
								responsevo.setPayType("Postpaid");
								responsevo.setPaymentMode("Online");
								responsevo.setResult("Success");
								responsevo.setMessage("Order Created Successfully. Proceed to CheckOut");
							}
						} else {

							responsevo.setResult("Failure");
							responsevo.setMessage("Order Creation Failed. Please Try After Sometime");
						}
					} else {
						paybillRequestVO.setPaymentStatus(1);
						responsevo.setPaymentMode("Cash");

						if(insertbillingpayment(paybillRequestVO) != 0) {
							responsevo.setResult("Success");
							responsevo.setMessage("Bill Paid Successfully");
							
							extramethodsdao.sendsms(smsRequestVO);
							
						} else {
							responsevo.setResult("Failure");
							responsevo.setMessage("Bill Payment Failed. Please Try After Sometime");
						}
						
					}
					
				} 
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			// pstmt.close();
			// ps.close();
			con.close();
		}

		return responsevo;
	}
	
	public long insertbillingpayment (PayBillRequestVO paybillRequestVO) throws SQLException {
		// TODO Auto-generated method stub		
		
		Connection con = null;
		long transactionID = 0;
		
		try {
			con = getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO billingpaymentdetails (CustomerBillingID, CustomerID, CustomerUniqueID, TotalAmount, LateFee, Source, ModeOfPayment, PaymentStatus, CreatedByID, CreatedByRoleID, AcknowledgeDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, Now())");
			pstmt.setLong(1, paybillRequestVO.getCustomerBillingID());
			pstmt.setLong(2, paybillRequestVO.getCustomerID());
			pstmt.setString(3, paybillRequestVO.getCustomerUniqueID());
			pstmt.setFloat(4, paybillRequestVO.getTotalamount() + paybillRequestVO.getTaxAmount() + paybillRequestVO.getPreviousDues()); // = bill amount + tax amount + late fee()
			pstmt.setInt(5, paybillRequestVO.getLateFee());
			pstmt.setString(6, paybillRequestVO.getSource());
			pstmt.setString(7, paybillRequestVO.getModeOfPayment());
			pstmt.setInt(8, paybillRequestVO.getPaymentStatus());
			pstmt.setInt(9, paybillRequestVO.getTransactedByID());
			pstmt.setInt(10, paybillRequestVO.getTransactedByRoleID());
			
			if (pstmt.executeUpdate() > 0) {
				PreparedStatement pstmt1 = con.prepareStatement("SELECT MAX(TransactionID) as TransactionID from billingpaymentdetails");
				ResultSet rs1 = pstmt1.executeQuery();
				
				if(rs1.next()) {
					transactionID = rs1.getLong("TransactionID");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			con.close();
			
		}
		
		return transactionID;
	}
	
	public ResponseVO printbillreceipt(int transactionID) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResponseVO responsevo = new ResponseVO();
		String drivename = "D:/BillReceipts/";
		
		try {
			con = getConnection();

			pstmt = con.prepareStatement(
					"SELECT c.CommunityName, b.BlockName, cd.HouseNumber, cd.CustomerUniqueID, cd.FirstName, cd.LastName, bpd.TotalAmount, bpd.LateFee, bpd.ModeOfPayment, bpd.CreatedByID, bpd.RazorPayOrderID, bpd.RazorPayPaymentID, bpd.TransactionDate, bpd.AcknowledgeDate\r\n"
							+ "FROM customerbillingdetails AS cbd LEFT JOIN billingpaymentdetails AS bpd ON cbd.CustomerBillingID = bpd.CustomerBillingID \r\n"
							+ "LEFT JOIN customerdetails AS cd ON cd.CustomerID = cbd.CustomerID LEFT JOIN community AS c ON c.CommunityID = cbd.CommunityID\r\n"
							+ "LEFT JOIN block AS b ON b.BlockID = cbd.BlockID WHERE bpd.TransactionID = "+ transactionID);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				pstmt1 = con.prepareStatement("SELECT UserName FROM user WHERE ID = " + rs.getLong("CreatedByID"));
				rs1 = pstmt1.executeQuery();

				if (rs1.next()) {

					File directory = new File(drivename);
					if (!directory.exists()) {
						directory.mkdirs();
					}

					PdfWriter writer = new PdfWriter(drivename + transactionID + ".pdf");
					PdfDocument pdfDocument = new PdfDocument(writer);
					pdfDocument.addNewPage();
					Document document = new Document(pdfDocument);
					Paragraph newLine = new Paragraph("\n");
					Paragraph head = new Paragraph("Receipt");
					Paragraph disclaimer = new Paragraph(ExtraConstants.Disclaimer);
					Paragraph copyRight = new Paragraph("----------------------------------All  rights reserved by IDigitronics � Hyderabad---------------------------------");
					PdfFont font = new PdfFontFactory().createFont(FontConstants.TIMES_BOLD);

					// change according to the image directory

					URL idigiurl = new URL(ExtraConstants.IDIGIIMAGEURL);
					URL clienturl = new URL(ExtraConstants.CLIENTIMAGEURL);
					Image idigi = new Image(ImageDataFactory.create(idigiurl));
					Image client = new Image(ImageDataFactory.create(clienturl));

					float[] headingWidths = { 200F, 130F, 200F };

					Table headTable = new Table(headingWidths);

					Cell headtable1 = new Cell();
					headtable1.add(idigi);
					headtable1.setTextAlignment(TextAlignment.LEFT);

					Cell headtable2 = new Cell();
					headtable2.add(head.setFontSize(20));
					headtable2.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE)
							.setBold().setUnderline().setFont(font);

					Cell headtable3 = new Cell();
					headtable3.add(client);
					headtable3.setTextAlignment(TextAlignment.RIGHT);

					headTable.addCell(headtable1.setBorder(Border.NO_BORDER));
					headTable.addCell(headtable2.setBorder(Border.NO_BORDER));
					headTable.addCell(headtable3.setBorder(Border.NO_BORDER));

					document.add(headTable);

					float[] headerWidths = { 130F, 150F, 150F, 100F };

					Table table1 = new Table(headerWidths);

					Cell cell1 = new Cell();
					cell1.add("Customer Name: ");
					cell1.setTextAlignment(TextAlignment.LEFT);

					Cell customerName = new Cell();
					customerName.add(rs.getString("FirstName") + " " + rs.getString("LastName"));
					customerName.setTextAlignment(TextAlignment.LEFT);

					Cell cell2 = new Cell();
					cell2.add("CAN Number: ");
					cell2.setTextAlignment(TextAlignment.RIGHT);

					Cell customerUniqueID = new Cell();
					customerUniqueID.add(rs.getString("CustomerUniqueID"));
					customerUniqueID.setTextAlignment(TextAlignment.LEFT);

					Cell cell3 = new Cell();
					cell3.add("House Number: ");
					cell3.setTextAlignment(TextAlignment.LEFT);

					Cell houseNumber = new Cell();
					houseNumber.add(rs.getString("HouseNumber"));
					houseNumber.setTextAlignment(TextAlignment.LEFT);

					Cell cell4 = new Cell();
					cell4.add("Invoice No. : ");
					cell4.setTextAlignment(TextAlignment.RIGHT);

					Cell InvoiceNumber = new Cell();
					InvoiceNumber.add("" + transactionID);
					InvoiceNumber.setTextAlignment(TextAlignment.LEFT);

					Cell cell5 = new Cell();
					cell5.add("PaidAmount: ");
					cell5.setTextAlignment(TextAlignment.LEFT);

					Cell Amount = new Cell();
					Amount.add(rs.getString("TotalAmount"));
					Amount.setTextAlignment(TextAlignment.LEFT);

					Cell cell6 = new Cell();
					cell6.add("Late Fee(if any): ");
					cell6.setTextAlignment(TextAlignment.RIGHT);

					Cell lateFee = new Cell();
					lateFee.add(rs.getString("LateFee"));
					lateFee.setTextAlignment(TextAlignment.LEFT);

					Cell cell7 = new Cell();
					cell7.add("Payment Mode: ");
					cell7.setTextAlignment(TextAlignment.LEFT);

					Cell modeOfPayment = new Cell();
					modeOfPayment.add(rs.getString("ModeOfPayment"));
					modeOfPayment.setTextAlignment(TextAlignment.LEFT);

					Cell cell8 = new Cell();
					cell8.add("Transaction Done By: ");
					cell8.setTextAlignment(TextAlignment.RIGHT);

					Cell transactedBy = new Cell();
					transactedBy.add(rs1.getString("UserName"));
					transactedBy.setTextAlignment(TextAlignment.LEFT);

					Cell cell9 = new Cell();
					cell9.add("Paid on: ");
					cell9.setTextAlignment(TextAlignment.LEFT);

					Cell transactionDate = new Cell();
					transactionDate.add(ExtraMethodsDAO.datetimeformatter(rs.getString("TransactionDate")));
					transactionDate.setTextAlignment(TextAlignment.LEFT);

					Cell cell10 = new Cell();
					cell10.add("Order ID: ");
					cell10.setTextAlignment(TextAlignment.RIGHT);

					Cell OrderID = new Cell();
					OrderID.add(rs.getString("RazorPayOrderID") == null ? "---" : rs.getString("RazorPayOrderID"));
					OrderID.setTextAlignment(TextAlignment.LEFT);

					Cell cell11 = new Cell();
					cell11.add("Payment ID: ");
					cell11.setTextAlignment(TextAlignment.LEFT);

					Cell PaymentID = new Cell();
					PaymentID
							.add(rs.getString("RazorPayPaymentID") == null ? "---" : rs.getString("RazorPayPaymentID"));
					PaymentID.setTextAlignment(TextAlignment.LEFT);

					Cell cell12 = new Cell();
					cell12.add("");
					cell12.setTextAlignment(TextAlignment.CENTER);

					Cell cell12value = new Cell();
					cell12value.add("");
					cell12value.setTextAlignment(TextAlignment.CENTER);

					table1.addCell(cell1.setBorder(Border.NO_BORDER));
					table1.addCell(customerName.setBorder(Border.NO_BORDER));
					table1.addCell(cell2.setBorder(Border.NO_BORDER));
					table1.addCell(customerUniqueID.setBorder(Border.NO_BORDER));
					table1.startNewRow();

					table1.addCell(cell3.setBorder(Border.NO_BORDER));
					table1.addCell(houseNumber.setBorder(Border.NO_BORDER));
					table1.addCell(cell4.setBorder(Border.NO_BORDER));
					table1.addCell(InvoiceNumber.setBorder(Border.NO_BORDER));
					table1.startNewRow();

					table1.addCell(cell5.setBorder(Border.NO_BORDER));
					table1.addCell(Amount.setBorder(Border.NO_BORDER));
					table1.addCell(cell6.setBorder(Border.NO_BORDER));
					table1.addCell(lateFee.setBorder(Border.NO_BORDER));
					table1.startNewRow();

					table1.addCell(cell7.setBorder(Border.NO_BORDER));
					table1.addCell(modeOfPayment.setBorder(Border.NO_BORDER));
					table1.addCell(cell8.setBorder(Border.NO_BORDER));
					table1.addCell(transactedBy.setBorder(Border.NO_BORDER));
					table1.startNewRow();

					table1.addCell(cell9.setBorder(Border.NO_BORDER));
					table1.addCell(transactionDate.setBorder(Border.NO_BORDER));
					table1.addCell(cell10.setBorder(Border.NO_BORDER));
					table1.addCell(OrderID.setBorder(Border.NO_BORDER));
					table1.startNewRow();

					table1.addCell(cell11.setBorder(Border.NO_BORDER));
					table1.addCell(PaymentID.setBorder(Border.NO_BORDER));
					table1.addCell(cell12.setBorder(Border.NO_BORDER));
					table1.addCell(cell12value.setBorder(Border.NO_BORDER));
					table1.startNewRow();

					document.add(table1.setHorizontalAlignment(HorizontalAlignment.CENTER));
					document.add(disclaimer.setHorizontalAlignment(HorizontalAlignment.CENTER).setFont(font));

					document.add(copyRight.setHorizontalAlignment(HorizontalAlignment.CENTER).setFont(font));
					document.close();

					responsevo.setResult("Success");
					responsevo.setLocation(drivename);
					responsevo.setFileName(transactionID + ".pdf");

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setResult("Failure");
			responsevo.setMessage("INTERNAL SERVER ERROR");
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return responsevo;
	}

	/* Configuration */

	public List<ConfigurationResponseVO> getConfigurationdetails(int roleid, String id, int filterCid)
			throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ConfigurationResponseVO> configurationdetailslist = null;
		List<CommandGroupResponseVO> commandslist = null;
		ConfigurationResponseVO configurationvo = null;
		CommandGroupResponseVO commands = null;
		try {
			con = getConnection();
			configurationdetailslist = new LinkedList<ConfigurationResponseVO>();

			String query = "SELECT DISTINCT cd.TransactionID, cd.CustomerUniqueID, cd.MIUID, cd.CustomerMeterID FROM command AS cd \r\n"
					+ "LEFT JOIN customerdetails AS cm ON cm.CustomerUniqueID = cd.CustomerUniqueID\r\n"
					+ "LEFT JOIN community AS c ON cm.CommunityID = c.CommunityID\r\n"
					+ "LEFT JOIN block AS b ON cm.BlockID = b.blockID <change>";

			pstmt = con.prepareStatement(query.replaceAll("<change>",
					((roleid == 1 || roleid == 4) && (filterCid == -1)) ? " ORDER BY cd.TransactionID DESC"
							: ((roleid == 1 || roleid == 4) && (filterCid != -1))
									? " WHERE cm.CommunityID = " + filterCid + " ORDER BY cd.TransactionID DESC"
									: (roleid == 2 || roleid == 5)
											? "WHERE cm.BlockID = " + id + " ORDER BY cd.TransactionID DESC"
											: (roleid == 3)
													? "WHERE cm.CustomerUniqueID = '" + id
															+ "' ORDER BY cd.TransactionID DESC"
													: ""));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				commandslist = new LinkedList<CommandGroupResponseVO>();
				configurationvo = new ConfigurationResponseVO();

				PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM commanddetails WHERE TransactionID = " + rs.getLong("TransactionID"));
				ResultSet rs1 = pstmt1.executeQuery();
				while (rs1.next()) {
					commands = new CommandGroupResponseVO();

					commands.setCommandType(rs1.getInt("CommandType") == 1 ? "Meter Reset" : rs1.getInt("CommandType") == 3 ? "Tariff" : rs1.getInt("CommandType") == 5 ? "Valve" : rs1.getInt("CommandType") == 6 ? "RTC" : rs1.getInt("CommandType") == 8 ? "Sync Interval" : rs1.getInt("CommandType") == 9 ? "Meter Reading" : rs1.getInt("CommandType") == 10 ? "PrePaidPostPaid Mode"	: rs1.getInt("CommandType") == 12 ? "Clear Tamper" : rs1.getInt("CommandType") == 13 ? "Sync Time" : rs1.getInt("CommandType") == 7 ? "Schedule Disconnect" : "");
					commands.setStatus(rs1.getInt("Status") == 0 ? "Passed"	: rs1.getInt("Status") == 1 ? "Already Executed" : rs1.getInt("Status") == 2 ? "Invalid Syntax"	: rs1.getInt("Status") == 3 ? "Invalid Parameters" : rs1.getInt("Status") == 4 ? "Value Cannot be Applied" : rs1.getInt("Status") == 5 ? "Value Not in Range" : rs1.getInt("Status") == 6 ? "Command Not Found"	: rs1.getInt("Status") == 7	? "Device Not Found" : rs1.getInt("Status") == 8 ? "Transaction Discarded" : rs1.getInt("Status") == 9 ? "Transaction not Found" : rs1.getInt("Status") == 10 ? "Pending": "Unknown Failure");
					commands.setValue(rs1.getString("Value"));
					commands.setModifiedDate(rs1.getString("ModifiedDate"));

					commandslist.add(commands);
				}
				
				configurationvo.setCommands(commandslist);
				configurationvo.setMiuID(rs.getString("MIUID"));
				configurationvo.setTransactionID(rs.getLong("TransactionID"));
				
				configurationdetailslist.add(configurationvo);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return configurationdetailslist;
	}

	public ResponseVO addconfiguration(ConfigurationRequestVO configurationvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResponseVO responsevo = new ResponseVO();
		CommandGroupRequestVO parameters = null;
		List<CommandGroupRequestVO> parameterslist;

		try {
			con = getConnection();
			
			ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
			RestCallVO restcallvo = new RestCallVO();
			
			ps = con.prepareStatement("INSERT INTO command (CustomerID, CustomerMeterID, MIUID, CustomerUniqueID) VALUES (?, ?, ?, ?)");
			ps.setLong(1, configurationvo.getCustomerID());
			ps.setLong(2, configurationvo.getCustomerMeterID());
			ps.setString(3, configurationvo.getMiuID());
			ps.setString(4, configurationvo.getCustomerUniqueID());
			
			if (ps.executeUpdate() > 0) {
			
			ps1 = con.prepareStatement("SELECT MAX(TransactionID) as TransactionID from command");
			ResultSet rs = ps1.executeQuery();
			
			if(rs.next()) {
				
				PreparedStatement pstmt = con.prepareStatement("SELECT g.GatewayIP, g.GatewayPort FROM gateway as g LEFT JOIN customermeterdetails as cmd ON g.GatewayID = cmd.GatewayID WHERE CustomerMeterID = " + configurationvo.getCustomerMeterID());
				ResultSet rs2 = pstmt.executeQuery();
				
				if(rs2.next()) {
				restcallvo.setGatewayIP(rs2.getString("GatewayIP"));
				restcallvo.setGatewayPort(rs2.getInt("GatewayPort"));
				restcallvo.setMiuID(configurationvo.getMiuID());
				restcallvo.setTransaction_id(rs.getInt("TransactionID"));
				
				parameterslist = new LinkedList<CommandGroupRequestVO>();
				
				if(configurationvo.getCommands().size() > 1) {
					
					for(int i = 0; i < configurationvo.getCommands().size(); i++) {
						
						parameters = new CommandGroupRequestVO();
						
						ps2 = con.prepareStatement("INSERT INTO commanddetails (TransactionID, CommandType, Value, ModifiedDate) VALUES (?, ?, ?, NOW() )");
						ps2.setInt(1, rs.getInt("TransactionID"));
						ps2.setInt(2, configurationvo.getCommands().get(i).getParameter_id());
						ps2.setString(3, configurationvo.getCommands().get(i).getValue());
						
						ps2.executeUpdate();
						
						parameters.setParameter_id(configurationvo.getCommands().get(i).getParameter_id());
						parameters.setValue(configurationvo.getCommands().get(i).getValue());
						parameterslist.add(parameters);
						
						}
					restcallvo.setParameters(parameterslist);
					restcallvo.setUrlExtension("/group/set");
					
				} else {
					ps2 = con.prepareStatement("INSERT INTO commanddetails (TransactionID, CommandType, Value, ModifiedDate) VALUES (?, ?, ?, NOW())");
					ps2.setInt(1, rs.getInt("TransactionID"));
					ps2.setInt(2, configurationvo.getCommands().get(0).getParameter_id());
					ps2.setString(3, configurationvo.getCommands().get(0).getValue());
					
					if (ps2.executeUpdate() > 0) {
						restcallvo.setParameter_id(configurationvo.getCommands().get(0).getParameter_id());
//						restcallvo.setValue(configurationvo.getCommands().get(0).getParameter_id() == 6 ? ExtraMethodsDAO.datetimeformatter(configurationvo.getCommands().get(0).getValue()) : configurationvo.getCommands().get(0).getValue());
						restcallvo.setValue(configurationvo.getCommands().get(0).getParameter_id() == 6 ? configurationvo.getCommands().get(0).getValue()+":00" : configurationvo.getCommands().get(0).getValue());
						restcallvo.setUrlExtension("/set");
						
						if (configurationvo.getCommands().get(0).getParameter_id() == 3) {

							PreparedStatement pstmt2 = con.prepareStatement("UPDATE customermeterdetails SET TariffID = ?, ModifiedDate = NOW() WHERE CustomerUniqueID = ? AND CustomerMeterID = ?");
							pstmt2.setInt(1, Integer.parseInt(configurationvo.getCommands().get(0).getValue()));
							pstmt2.setString(2, configurationvo.getCustomerUniqueID());
							pstmt2.setLong(3, configurationvo.getCustomerMeterID());
							pstmt2.executeUpdate();
							
							PreparedStatement pstmt3 = con.prepareStatement("SELECT Tariff FROM tariff WHERE TariffID = " + configurationvo.getCommands().get(0).getValue());
							ResultSet rs3 = pstmt3.executeQuery();
							if(rs3.next()) {
								restcallvo.setValue(rs3.getString("Tariff"));
							}
						}
					}
					
				}
			}
				}
			}
			// modify the backend fields accordingly and set values for all parameters
			if(extramethodsdao.postdata(restcallvo) == 200) {
			responsevo.setResult("Success");
			responsevo.setMessage("Command Request Submitted Successfully");
			} else {
				PreparedStatement ps3 = con.prepareStatement("DELETE FROM commanddetails WHERE TransactionID = "+ restcallvo.getTransaction_id());
				if(ps3.executeUpdate() > 0) {
					
					PreparedStatement ps4 = con.prepareStatement("DELETE FROM command WHERE TransactionID = " + restcallvo.getTransaction_id());
					
					if(ps4.executeUpdate() > 0) {
						responsevo.setResult("Failure");
						responsevo.setMessage("Command Request failed");						
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			// ps.close();
			con.close();
		}

		return responsevo;

	}
	
	public ResponseVO updateconfiguration(ConfigurationStatusResponseVO configurationstatusvo, String miuID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			
			ps = con.prepareStatement("UPDATE commanddetails SET ModifiedDate = NOW(), Status = "+ configurationstatusvo.getCmd_status() +" WHERE TransactionID = "+ configurationstatusvo.getTransaction_id());
			
			if(ps.executeUpdate() > 0) {
				
					responsevo.setResult("Success");
					responsevo.setMessage("Status Updated Successfully");
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			ps.close();
			con.close();
		}

		return responsevo;
	}

	public ResponseVO deleteconfiguration(int transactionID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM commanddetails WHERE TransactionID = "+ transactionID);
			if(ps.executeUpdate() > 0) {
				
				pstmt = con.prepareStatement("DELETE FROM command WHERE TransactionID = " + transactionID);

				if (pstmt.executeUpdate() > 0) {
					responsevo.setResult("Success");
					responsevo.setMessage("Deleted Successfully");
				}
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}

		return responsevo;
	}

	public boolean checkconfigstatus(String meterID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT cd.MIUID, cmd.Status FROM command as cd LEFT JOIN commanddetails AS cmd ON cd.TransactionID = cmd.TransactionID WHERE MIUID = ? order by cmd.TransactionID DESC LIMIT 0,1");
			pstmt.setString(1, meterID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("Status") != null && rs.getString("Status").equals("10")) {
					result = true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;
	}

	public boolean checktopup(long customerMeterId) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT transactionID, MIUID, Status FROM topup WHERE CustomerMeterID = ? AND STATUS = 10 AND PaymentStatus = 1 AND Source = 'web' ORDER BY TransactionID DESC LIMIT 0,1");
			pstmt.setLong(1, customerMeterId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;
	}

	public boolean validateamount(TopUpRequestVO topupvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT tr.EmergencyCredit, tr.Tariff, tr.TariffID, cmd.CustomerUniqueID FROM customermeterdetails as cmd LEFT JOIN tariff AS tr ON tr.TariffID = cmd.TariffID WHERE cmd.CustomerMeterID = ?");
			pstmt.setLong(1, topupvo.getCustomerMeterID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (topupvo.getAmount() < rs.getFloat("EmergencyCredit") || topupvo.getAmount() < rs.getFloat("Tariff"))

					result = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;
	}
	
	public boolean validateBalance(TopUpRequestVO topupvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT Balance FROM displaybalancelog WHERE CustomerMeterID = "+topupvo.getCustomerMeterID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (topupvo.getAmount() + rs.getFloat("Balance") >= 2000)

					result = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;
	}

	public boolean checkBillPaymentStatus(long customerBillingID) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM billingpaymentdetails WHERE CustomerBillingID = "+customerBillingID +" AND PaymentStatus = 1");
			rs = pstmt.executeQuery();
			if (rs.next()) {
					result = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;
	}

	public boolean checktypeOfMeter(long customerMeterID) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT PayType FROM customermeterdetails WHERE CustomerMeterID = "+customerMeterID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (!rs.getString("PayType").trim().equalsIgnoreCase("Prepaid")) {

					result = true;
			}
		} 
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;
	}

}
