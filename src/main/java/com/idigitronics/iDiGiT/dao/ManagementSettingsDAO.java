/**
 * 
 */
package com.idigitronics.iDiGiT.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.idigitronics.iDiGiT.constants.DataBaseConstants;
import com.idigitronics.iDiGiT.exceptions.BusinessException;
import com.idigitronics.iDiGiT.request.vo.AlertRequestVO;
import com.idigitronics.iDiGiT.request.vo.FeedbackRequestVO;
import com.idigitronics.iDiGiT.request.vo.VacationRequestVO;
import com.idigitronics.iDiGiT.request.vo.LoginVO;
import com.idigitronics.iDiGiT.request.vo.RestCallVO;
import com.idigitronics.iDiGiT.request.vo.UserManagementRequestVO;
import com.idigitronics.iDiGiT.response.vo.AlertResponseVO;
import com.idigitronics.iDiGiT.response.vo.FeedbackResponseVO;
import com.idigitronics.iDiGiT.response.vo.ResponseVO;
import com.idigitronics.iDiGiT.response.vo.VacationResponseVO;
import com.idigitronics.iDiGiT.response.vo.UserManagementResponseVO;

/**
 * @author K VimaL Kumar
 * 
 */
public class ManagementSettingsDAO {

	LoginVO loginvo = new LoginVO();
	Gson gson = new Gson();

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {

		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}

	/* User Management */

	public List<UserManagementResponseVO> getuserdetails(int roleid, int id) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List<UserManagementResponseVO> user_list = null;
		UserManagementResponseVO usermanagementvo = null;

		try {
			con = getConnection();
			user_list = new LinkedList<UserManagementResponseVO>();
			
			String query = "SELECT user.ID, user.UserID, user.UserName, user.MobileNumber, user.Email, userrole.RoleDescription, community.CommunityID, community.CommunityName, block.BlockID, block.BlockName, user.CreatedByID, user.ModifiedDate \r\n" + 
					"	FROM USER LEFT JOIN community ON community.CommunityID = user.CommunityID LEFT JOIN block ON block.BlockID = user.BlockID\r\n" + 
					"	LEFT JOIN userrole ON userrole.RoleID = user.RoleID <change> ";
			
			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY user.CommunityID ASC" : (roleid == 2 || roleid == 5) ? "WHERE user.BlockID = "+id+ " ORDER BY user.BlockID ASC" : ""));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				usermanagementvo = new UserManagementResponseVO();
				usermanagementvo.setUserID(rs.getString("UserID"));
				usermanagementvo.setUserName(rs.getString("UserName"));
				usermanagementvo.setRole(rs.getString("RoleDescription"));
				usermanagementvo.setID(rs.getInt("ID"));
				usermanagementvo.setCommunityName((rs.getInt("CommunityID") != 0) ? rs.getString("CommunityName") : "---");
				usermanagementvo.setBlockName((rs.getInt("BlockID") != 0) ? rs.getString("BlockName") : "---");
				usermanagementvo.setMobileNumber(rs.getString("MobileNumber") == null ? "NA" : rs.getString("MobileNumber"));
				usermanagementvo.setEmailID(rs.getString("Email") == null ? "---" : rs.getString("Email"));

				if(rs.getInt("CreatedByID")>0) {
					pstmt1 = con.prepareStatement("SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "+rs.getInt("CreatedByID"));
					rs1 = pstmt1.executeQuery();
					if(rs1.next()) {
					usermanagementvo.setCreatedByUserName(rs1.getString("UserName"));
					usermanagementvo.setCreatedByRoleDescription(rs1.getString("RoleDescription"));
					} 
				}else {
					usermanagementvo.setCreatedByUserName("---");
					usermanagementvo.setCreatedByRoleDescription("---");
				}
				
				user_list.add(usermanagementvo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
//			pstmt1.close();
			rs.close();
//			rs1.close();
			con.close();
		}
		return user_list;
	}

	public ResponseVO adduser(UserManagementRequestVO usermanagementvo)
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResponseVO responsevo = new ResponseVO(); 

		try {
			con = getConnection();

				String query = " INSERT INTO user (UserID, UserName, UserPassword, RoleID, ActiveStatus, CommunityID, BlockID, CustomerID, CreatedByID, CreatedByRoleID, CustomerUniqueID, MobileNumber, Email, ModifiedDate) values(?, ?, ?, ?, 1, <change>, ?, ?, ?, ?, NOW()) ";
			
			    pstmt = con.prepareStatement(query.replaceAll("<change>", (usermanagementvo.getRoleID() == 4) ? "0, 0, 0, 0" : "?, ?, ?, ?"));
			
				pstmt.setString(1, usermanagementvo.getUserID());
				pstmt.setString(2, usermanagementvo.getUserName());
				pstmt.setString(3, usermanagementvo.getUserPassword());
				pstmt.setInt(4, usermanagementvo.getRoleID());
				
				pstmt1 = con.prepareStatement("SELECT ID FROM user WHERE UserID = ? ");
				pstmt1.setString(1, usermanagementvo.getLoggedInUserID());
				rs = pstmt1.executeQuery();
				if(rs.next()) {
					
				if(usermanagementvo.getRoleID()!=4) {
					
					if(usermanagementvo.getRoleID()==3) {
						
						pstmt.setInt(5, usermanagementvo.getCommunityID());
						pstmt.setInt(6, usermanagementvo.getBlockID());
						pstmt.setLong(7, usermanagementvo.getCustomerID());
						pstmt.setInt(8, rs.getInt("ID"));
						pstmt.setInt(9, usermanagementvo.getLoggedInRoleID());
						pstmt.setString(10, usermanagementvo.getCustomerUniqueID());
						pstmt.setString(11, usermanagementvo.getMobileNumber());
						pstmt.setString(12, usermanagementvo.getEmailID());
						
					}else {
						pstmt.setInt(5, usermanagementvo.getCommunityID());
						pstmt.setInt(6, usermanagementvo.getBlockID());
						pstmt.setInt(7, 0);
						pstmt.setInt(8, rs.getInt("ID"));
						pstmt.setInt(9, usermanagementvo.getLoggedInRoleID());
						pstmt.setString(10, usermanagementvo.getCustomerUniqueID());
						pstmt.setString(11, usermanagementvo.getMobileNumber());
						pstmt.setString(12, usermanagementvo.getEmailID());
					}
					
				} else {
					pstmt.setInt(5, rs.getInt("ID"));	
					pstmt.setInt(6, usermanagementvo.getLoggedInRoleID());
					pstmt.setString(7, usermanagementvo.getMobileNumber());
					pstmt.setString(8, usermanagementvo.getEmailID());
				}
			}	
				if (pstmt.executeUpdate() > 0) {
					responsevo.setResult("Success");
					responsevo.setMessage("User Created Successfully");
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

	/* Alert */

	public List<AlertResponseVO> getalertdetails() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AlertResponseVO> alert_settings_list = null;
		try {
			con = getConnection();
			AlertResponseVO alertvo = null;
			alert_settings_list = new LinkedList<AlertResponseVO>();
			pstmt = con.prepareStatement("SELECT AlertID, NoAMRInterval, TimeOut, ReconnectionCharges, LateFee, ReconnectionChargeDays, DueDayCount, BillGenerationDate, GST, VendorGSTNumber, CustomerGSTNumber, Remarks, ModifiedDate FROM alertsettings");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				alertvo = new AlertResponseVO();
				alertvo.setNoAMRInterval((rs.getString("NoAMRInterval")));
				alertvo.setTimeOut(rs.getString("TimeOut"));
				alertvo.setReconnectionCharges(rs.getInt("ReconnectionCharges"));
				alertvo.setReconnectionChargeDays(rs.getInt("ReconnectionChargeDays"));
				alertvo.setLateFee(rs.getInt("LateFee"));
				alertvo.setDueDayCount(rs.getInt("DueDayCount"));
				alertvo.setBillGenerationDate(rs.getString("BillGenerationDate"));
				alertvo.setGST(rs.getInt("GST"));
				alertvo.setVendorGSTNumber(rs.getString("VendorGSTNumber"));
				alertvo.setCustomerGSTNumber(rs.getString("CustomerGSTNumber"));
				alertvo.setRemarks(StringUtils.isBlank(rs.getString("Remarks"))==true?"":rs.getString("Remarks"));
				alertvo.setRegisteredDate(ExtraMethodsDAO.datetimeformatter(rs.getString("ModifiedDate")));
				alertvo.setAlertID(rs.getInt("AlertID"));
				alert_settings_list.add(alertvo);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return alert_settings_list;

	}

	public ResponseVO addalert(AlertRequestVO alertvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		ResponseVO responsevo = new ResponseVO(); 

		try {
			con = getConnection();

			ps = con.prepareStatement("INSERT INTO alertsettings (NoAMRInterval, TimeOut, ReconnectionCharges, LateFee, ReconnectionChargeDays, DueDayCount, BillGenerationDate, GST, VendorGSTNumber, CustomerGSTNumber, Remarks, RegisteredDate, ModifiedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())");
			ps.setInt(1, alertvo.getNoAMRInterval());
			ps.setInt(2, alertvo.getTimeOut());
			ps.setInt(3, alertvo.getReconnectionCharges());
			ps.setInt(4, alertvo.getLateFee());
			ps.setInt(5, alertvo.getReconnectionChargeDays());
			ps.setInt(6, alertvo.getDueDayCount());
			ps.setString(7, alertvo.getBillGenerationDate());
			ps.setInt(8, alertvo.getGST());
			ps.setString(9, alertvo.getVendorGSTNumber());
			ps.setString(10, alertvo.getCustomerGSTNumber());
			ps.setString(11, alertvo.getRemarks());

			if (ps.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Alert Settings Added Successfully");
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

	public ResponseVO editalert(AlertRequestVO alertvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		ResponseVO responsevo = new ResponseVO(); 

		try {
			con = getConnection();

			ps = con.prepareStatement("UPDATE alertsettings SET NoAMRInterval = ?, TimeOut = ?, ReconnectionCharges = ?, ReconnectionChargeDays = ?, LateFee = ?, DueDayCount = ?, GST = ?, VendorGSTNumber = ?, CustomerGSTNumber = ?, Remarks = ?, ModifiedDate = NOW() WHERE AlertID = ?");
			ps.setInt(1, alertvo.getNoAMRInterval());
			ps.setInt(2, alertvo.getTimeOut());
			ps.setInt(3, alertvo.getReconnectionCharges());
			ps.setInt(4, alertvo.getReconnectionChargeDays());
			ps.setInt(5, alertvo.getLateFee());
			ps.setInt(6, alertvo.getDueDayCount());
			ps.setInt(7, alertvo.getGST());
			ps.setString(8, alertvo.getVendorGSTNumber());
			ps.setString(9, alertvo.getCustomerGSTNumber());
			ps.setString(10, alertvo.getRemarks());
			ps.setInt(11, alertvo.getAlertID());

			if (ps.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Alert Settings Edited Successfully");
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

	public boolean checkalertsettings() throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			con = getConnection();

			pstmt1 = con.prepareStatement("select * from alertsettings");
			rs = pstmt1.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt1.close();
			con.close();
		}
		return result;
	}

	/* Vacation */

	public List<VacationResponseVO> getvacationdetails(int roleid, String id, int filterCid) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VacationResponseVO> vacationlist = null;
		VacationResponseVO vacationResponseVO = null;
		try {
			con = getConnection();
			vacationlist = new LinkedList<VacationResponseVO>();

			String query = "SELECT v.VacationID, v.VacationName, c.CommunityName, b.BlockName, cd.HouseNumber, cd.FirstName, cd.LastName, v.MIUID, v.CustomerUniqueID, v.CustomerMeterID, v.StartDate, v.EndDate, v.Source, v.Status, v.Mode, v.RegisteredDate \r\n" + 
					"FROM Vacation AS v LEFT JOIN community AS c ON c.CommunityID = v.CommunityID \r\n" + 
					"LEFT JOIN block AS b ON b.blockID = v.BlockID LEFT JOIN customerdetails AS cd ON cd.CustomerID = v.CustomerID <change>";
							
			pstmt = con.prepareStatement(query.replaceAll("<change>", ((roleid == 1 || roleid == 4) && (filterCid == -1)) ? "ORDER BY v.VacationID DESC" : 
				((roleid == 1 || roleid == 4) && (filterCid != -1)) ? " WHERE v.CommunityID = "+filterCid+" ORDER BY v.VacationID DESC" : (roleid == 2 || roleid == 5) ?
						" Where v.BlockID = "+id+ " ORDER BY v.VacationID DESC" : (roleid == 3) ? " WHERE v.CustomerUniqueID = '"+id+ "' ORDER BY v.VacationID DESC" :""));
			
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				vacationResponseVO = new VacationResponseVO();
				vacationResponseVO.setVacationID(rs.getInt("VacationID"));
				vacationResponseVO.setCommunityName(rs.getString("CommunityName"));
				vacationResponseVO.setBlockName((rs.getString("BlockName")));
				vacationResponseVO.setHouseNumber(rs.getString("HouseNumber"));
				vacationResponseVO.setFirstName(rs.getString("FirstName"));
				vacationResponseVO.setLastName(rs.getString("LastName"));
				vacationResponseVO.setCustomerUniqueID(rs.getString("CustomerUniqueID"));
				vacationResponseVO.setMiuID(rs.getString("MIUID"));
				vacationResponseVO.setVacationName(rs.getString("VacationName"));
				vacationResponseVO.setStartDate(ExtraMethodsDAO.datetimeformatter(rs.getString("StartDate")));
				vacationResponseVO.setEndDate(ExtraMethodsDAO.datetimeformatter(rs.getString("EndDate")));
				vacationResponseVO.setStartDateForEdit(rs.getString("StartDate"));
				vacationResponseVO.setEndDateForEdit(rs.getString("EndDate"));
				vacationResponseVO.setMode(rs.getString("Mode"));
				vacationResponseVO.setRegisteredDate(ExtraMethodsDAO.datetimeformatter(rs.getString("RegisteredDate")));
				vacationResponseVO.setStatus(rs.getInt("Status") == 0 ? "Passed"	: rs.getInt("Status") == 1 ? "Already Executed" : rs.getInt("Status") == 2 ? "Invalid Syntax"	: rs.getInt("Status") == 3 ? "Invalid Parameters" : rs.getInt("Status") == 4 ? "Value Cannot be Applied" : rs.getInt("Status") == 5 ? "Value Not in Range" : rs.getInt("Status") == 6 ? "Command Not Found"	: rs.getInt("Status") == 7	? "Device Not Found" : rs.getInt("Status") == 8 ? "Transaction Discarded" : rs.getInt("Status") == 9 ? "Transaction not Found" : rs.getInt("Status") == 10 ? "Pending": "Unknown Failure");
				vacationlist.add(vacationResponseVO);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return vacationlist;

	}

	public ResponseVO addvacation(VacationRequestVO vacationRequestVO) throws SQLException {
		// TODO Auto-generated method stub

		ResponseVO responsevo = new ResponseVO(); 
		PreparedStatement pstmt1 = null;
		Connection con = null;

		try {
			con = getConnection();
			pstmt1 = con.prepareStatement("SELECT cd.CommunityID, cd.BlockID, cd.CustomerID, cmd.MIUID, g.GatewayIP, g.GatewayPort FROM customerdetails AS cd LEFT JOIN customermeterdetails AS cmd on cd.CustomerID = cmd.CustomerID LEFT JOIN gateway AS g ON g.GatewayID = cmd.GatewayID WHERE CustomermeterID = '"+vacationRequestVO.getCustomerMeterID()+"'");
			ResultSet rs1 = pstmt1.executeQuery();

			if (rs1.next()) {
				vacationRequestVO.setMiuID(rs1.getString("MIUID"));
				vacationRequestVO.setCommunityID(rs1.getInt("CommunityID"));
				vacationRequestVO.setBlockID(rs1.getInt("BlockID"));
				vacationRequestVO.setCustomerID(rs1.getInt("CustomerID"));
				
				if (vacationRequestVO.getSource().equalsIgnoreCase("web")) {
					
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						LocalDateTime startDateTime = LocalDateTime.parse(vacationRequestVO.getStartDateTime()+":00", dtf);
						LocalDateTime endDateTime = LocalDateTime.parse(vacationRequestVO.getEndDateTime()+":00", dtf);

						ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
						RestCallVO restcallvo = new RestCallVO();
						
						restcallvo.setMiuID(vacationRequestVO.getMiuID());
						restcallvo.setParameter_id(7);
						restcallvo.setValue(startDateTime.toString() +";" + endDateTime.toString());
						restcallvo.setGatewayIP(rs1.getString("GatewayIP"));
						restcallvo.setGatewayPort(rs1.getInt("GatewayPort"));
						
						long transactionID = insertvacation(vacationRequestVO);
						
						restcallvo.setTransaction_id(transactionID);
						
					if (transactionID != 0) {
						
						if (extramethodsdao.postdata(restcallvo) == 200) {
							responsevo.setResult("Success");
							responsevo.setMessage("Vacation Request Submitted Successfully");
						} else {
							responsevo.setResult("Failure");
							responsevo.setMessage("Vacation Request Failed");
						}
						} else {
							responsevo.setResult("Failure");
							responsevo.setMessage("Vacation Request Failed");
						}

					} else {
						if(insertvacation(vacationRequestVO) != 0) {
							responsevo.setResult("Success");
							responsevo.setMessage("Vacation Request Inserted Successfully");
						}else {
							responsevo.setResult("Failure");
							responsevo.setMessage("Vacation Request Insertion Failed");
						}
					}
		} 
			
		}catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		}
		return responsevo;
	}
	
	public long insertvacation(VacationRequestVO vacationRequestVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		long transactionID = 0;
		
		try {
			con = getConnection();

			pstmt = con.prepareStatement(
						"INSERT INTO vacation (communityID, BlockID, CustomerID, MIUID, CustomerMeterID, VacationName, StartDate, EndDate, Source, CustomerUniqueID, Mode, ModifiedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'add', NOW())");

				pstmt.setInt(1, vacationRequestVO.getCommunityID());
				pstmt.setInt(2, vacationRequestVO.getBlockID());
				pstmt.setLong(3, vacationRequestVO.getCustomerID());
				pstmt.setString(4, vacationRequestVO.getMiuID());
				pstmt.setLong(5, vacationRequestVO.getCustomerMeterID());
				pstmt.setString(6, vacationRequestVO.getVacationName());
				pstmt.setString(7, vacationRequestVO.getStartDateTime());
				pstmt.setString(8, vacationRequestVO.getEndDateTime());
				pstmt.setString(9, vacationRequestVO.getSource());
				pstmt.setString(10, vacationRequestVO.getCustomerUniqueID());

				if (pstmt.executeUpdate() > 0) {
					PreparedStatement pstmt1 = con.prepareStatement("SELECT Max(VacationID) as VacationID FROM vacation");
					ResultSet rs = pstmt1.executeQuery();
					if(rs.next()) {
						transactionID = rs.getLong("VacationID");
					}
				}
		} catch(Exception e){
			e.printStackTrace();
		}
		return transactionID;
	}
	
	public ResponseVO editvacation(VacationRequestVO vacationRequestVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO(); 
		
		try {
			con = getConnection();

			pstmt = con.prepareStatement("SELECT v.MIUID, g.GatewayIP, g.GatewayPort FROM vacation AS v LEFT JOIN customermeterdetails AS cmd ON v.CustomerMeterID = cmd.CustomermeterID LEFT JOIN gateways AS g ON g.GatewayID = cmd.GatewayID WHERE VacationID = " + vacationRequestVO.getVacationID());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				vacationRequestVO.setMiuID(rs.getString("MIUID"));
				vacationRequestVO.setMode("edit");

				if (vacationRequestVO.getSource().equalsIgnoreCase("web")) {

					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
					LocalDateTime startDateTime = LocalDateTime.parse(vacationRequestVO.getStartDateTime()+":00", dtf);
					LocalDateTime endDateTime = LocalDateTime.parse(vacationRequestVO.getEndDateTime()+":00", dtf);

					ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
					RestCallVO restcallvo = new RestCallVO();
					restcallvo.setValue(startDateTime.toString() +";" + endDateTime.toString());
					restcallvo.setParameter_id(7);
					restcallvo.setGatewayIP(rs.getString("GatewayIP"));
					restcallvo.setGatewayPort(rs.getInt("GatewayPort"));
					restcallvo.setMiuID(vacationRequestVO.getMiuID());
					restcallvo.setTransaction_id(vacationRequestVO.getVacationID());

					if(extramethodsdao.postdata(restcallvo) == 200) {
						if (updatevacation(vacationRequestVO).equalsIgnoreCase("Success")) {
							responsevo.setResult("Success");
							responsevo.setMessage("Vacation Update request Submitted Successfully");
						} else {
							responsevo.setResult("Failure");
							responsevo.setMessage("Vacation Update request Failed");
						}
					}  else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Vacation Update request Failed");
					}

				} else {
					if (updatevacation(vacationRequestVO).equalsIgnoreCase("Success")) {
						responsevo.setResult("Success");
						responsevo.setMessage("Vacation Update request Inserted Successfully");
					} else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Vacation Update request Failed to Insert");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		}
		return responsevo;
	}
	
	public String updatevacation(VacationRequestVO vacationRequestVO) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";

		try {
			con = getConnection();

			pstmt = con.prepareStatement("UPDATE vacation SET VacationName = ?, StartDate = ?, EndDate = ?, Status = 10, Source = ?, Mode = ?, ModifiedDate = NOW() WHERE VacationID = "+ vacationRequestVO.getVacationID());

			pstmt.setString(1, vacationRequestVO.getVacationName());
			pstmt.setString(2, vacationRequestVO.getStartDateTime());
			pstmt.setString(3, vacationRequestVO.getEndDateTime());
			pstmt.setString(4, vacationRequestVO.getSource());
			pstmt.setString(5, vacationRequestVO.getMode());
			
			if(pstmt.executeUpdate() > 0) {
				result = "Success";
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public ResponseVO deletevacation(int vacationID, String source) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		VacationRequestVO vacationRequestVO = new VacationRequestVO();
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();

			pstmt = con.prepareStatement("SELECT v.MIUID, g.GatewayIP, g.GatewayPort, v.StartDate, v.EndDate FROM vacation AS v LEFT JOIN customermeterdetails AS cmd ON v.CustomerMeterID = cmd.CustomermeterID LEFT JOIN gateways AS g ON g.GatewayID = cmd.GatewayID WHERE VacationID = " + vacationID);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				vacationRequestVO.setMiuID(rs.getString("MIUID"));
				vacationRequestVO.setMode("delete");
				vacationRequestVO.setSource(source);
				vacationRequestVO.setVacationName(rs.getString("VacationName"));
				vacationRequestVO.setVacationID(vacationID);

				if (source.equalsIgnoreCase("web")) {

					ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
					RestCallVO restcallvo = new RestCallVO();

					restcallvo.setMiuID(vacationRequestVO.getMiuID());
					// set value after confirmation
					restcallvo.setValue("");
					restcallvo.setParameter_id(7);
					restcallvo.setGatewayIP(rs.getString("GatewayIP"));
					restcallvo.setGatewayPort(rs.getInt("GatewayPort"));
					restcallvo.setMiuID(vacationRequestVO.getMiuID());
					restcallvo.setTransaction_id(vacationID);

					vacationRequestVO.setStartDateTime(rs.getString("StartDate"));
					vacationRequestVO.setEndDateTime(rs.getString("EndDate"));
					
					if(extramethodsdao.postdata(restcallvo) == 200) {
						if (updatevacation(vacationRequestVO).equalsIgnoreCase("Success")) {
							responsevo.setResult("Success");
							responsevo.setMessage("Vacation Delete request Submitted Successfully");
						} else {
							responsevo.setResult("Failure");
							responsevo.setMessage("Vacation delete request Failed");
						}
					} else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Vacation delete request Failed");
					}

				} else {
					vacationRequestVO.setStatus(0);
					vacationRequestVO.setStartDateTime(rs.getString("StartDate"));
					vacationRequestVO.setEndDateTime(rs.getString("EndDate"));
					if (updatevacation(vacationRequestVO).equalsIgnoreCase("Success")) {
						responsevo.setResult("Success");
						responsevo.setMessage("Vacation Delete request Inserted Successfully");
					} else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Vacation delete request Insertion Failed");
					}
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

	public boolean checkvacationsettings(VacationRequestVO vacationRequestVO) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT MIUID, Status, StartDate, EndDate, Mode FROM vacation WHERE CustomerMeterID = ? order by VacationID DESC LIMIT 0,1");
			pstmt.setLong(1, vacationRequestVO.getCustomerMeterID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("Status").equals("10")) {
					result = true;
				} else {
					
					// continue from here
					PreparedStatement pstmt1 = con.prepareStatement("SELECT VacationID FROM vacation WHERE Status != 10 AND ? BETWEEN StartDate AND EndDate ");
					pstmt1.setString(1, vacationRequestVO.getStartDateTime());
					ResultSet rs1 = pstmt1.executeQuery();
					if(rs1.next()) {
						result = true;
					}else {
						pstmt1 = con.prepareStatement("SELECT VacationID FROM vacation WHERE Status != 10 AND ? BETWEEN StartDate AND EndDate");
						pstmt1.setString(1, vacationRequestVO.getEndDateTime());
						rs1 = pstmt1.executeQuery();
						if(rs1.next()) {
							result = true;	
						}
					}
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
	
	public boolean checkvacationpending(int vacationID, String source) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT Status FROM vacation WHERE VacationID = "+vacationID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("Status").equals("10")) {
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

	public List<FeedbackResponseVO> getfeedbackdetails(int blockid) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FeedbackResponseVO> feedbackList = null;
		FeedbackResponseVO feedbackResponseVO = null;
		try {
			con = getConnection();
			feedbackList = new LinkedList<FeedbackResponseVO>();

			pstmt = con.prepareStatement("SELECT f.FeedbackID, f.Feedback, f.Description, CONCAT(cd.FirstName, ' ', cd.LastName) AS CustomerName, cd.HouseNumber, f.CustomerUniqueID, f.RegisteredDate \n" + 
					"FROM feedback AS f LEFT JOIN customerdetails AS cd ON cd.CustomerID = f.CustomerID WHERE f.Status = 0 AND f.BlockID = "+blockid);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				feedbackResponseVO = new FeedbackResponseVO();
				
				feedbackResponseVO.setFeedbackID(rs.getInt("FeedbackID"));
				feedbackResponseVO.setFeedback(rs.getString("Feedback"));
				feedbackResponseVO.setDescription(rs.getString("Description"));
				feedbackResponseVO.setCustomerUniqueID(rs.getString("CustomerUniqueID"));
				feedbackResponseVO.setName(rs.getString("CustomerName"));
				feedbackResponseVO.setHouseNumber(rs.getString("HouseNumber"));
				feedbackResponseVO.setDate(ExtraMethodsDAO.datetimeformatter(rs.getString("RegisteredDate")));
				
				feedbackList.add(feedbackResponseVO);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return feedbackList;
	}

	public ResponseVO addfeedback(FeedbackRequestVO feedbackRequestVO) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResponseVO responsevo = new ResponseVO(); 

		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("SELECT CommunityID, BlockID, CustomerID from customerdetails WHERE CustomerUniqueID = '"+feedbackRequestVO.getCustomerUniqueID()+"'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {

			ps = con.prepareStatement("INSERT INTO feedback (Feedback, Description, CommunityID, BlockID, CustomerID, CustomerUniqueID, ModifiedDate) VALUES (?, ?, ?, ?, ?, ?, NOW())");
			ps.setString(1, feedbackRequestVO.getFeedback());
			ps.setString(2, feedbackRequestVO.getDescription());
			ps.setInt(3, rs.getInt("CommunityID"));
			ps.setInt(4, rs.getInt("BlockID"));
			ps.setInt(5, rs.getInt("CustomerID"));
			ps.setString(6, feedbackRequestVO.getCustomerUniqueID());

			if (ps.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Feedback/Complaint Submitted Successfully");
			}
			
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
	
	public ResponseVO feedbackaction(int feedbackID, int action, String remarks) throws BusinessException, SQLException {
		// TODO Auto-generated method stub
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			
			if(action == 1) {
				
				pstmt = con.prepareStatement("UPDATE feedback SET Status = 1, Remarks = ?, ModifiedDate = NOW() WHERE FeedbackID = ?");
	            pstmt.setString(1, remarks);
				pstmt.setInt(2, feedbackID);

	            if (pstmt.executeUpdate() > 0) {
	            	
	            	responsevo.setResult("Success");
            		responsevo.setMessage("Resolved Successfully");
	            	
	            }

			}else {
				
				pstmt = con.prepareStatement("UPDATE feedback SET Status = 2, Remarks = ?, ModifiedDate = NOW() WHERE FeedbackID = ?");
				pstmt.setString(1, remarks);
				pstmt.setInt(2, feedbackID);
				
            	if(pstmt.executeUpdate() > 0) {
            		responsevo.setResult("Success");
            		responsevo.setMessage("Rejected Successfully");
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

}
