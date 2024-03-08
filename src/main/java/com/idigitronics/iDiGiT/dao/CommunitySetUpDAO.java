/**
 * 
 */
package com.idigitronics.iDiGiT.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.idigitronics.iDiGiT.request.vo.MeterRequestVO;
import com.idigitronics.iDiGiT.request.vo.MeterSizeRequestVO;
import com.idigitronics.iDiGiT.constants.DataBaseConstants;
import com.idigitronics.iDiGiT.constants.ExtraConstants;
import com.idigitronics.iDiGiT.request.vo.BlockRequestVO;
import com.idigitronics.iDiGiT.request.vo.CommunityRequestVO;
import com.idigitronics.iDiGiT.request.vo.CustomerRequestVO;
import com.idigitronics.iDiGiT.request.vo.GatewayRequestVO;
import com.idigitronics.iDiGiT.request.vo.MailRequestVO;
import com.idigitronics.iDiGiT.request.vo.SMSRequestVO;
import com.idigitronics.iDiGiT.request.vo.TariffRequestVO;
import com.idigitronics.iDiGiT.request.vo.UserManagementRequestVO;
import com.idigitronics.iDiGiT.response.vo.BlockResponseVO;
import com.idigitronics.iDiGiT.response.vo.CommunityResponseVO;
import com.idigitronics.iDiGiT.response.vo.CustomerResponseVO;
import com.idigitronics.iDiGiT.response.vo.GatewayResponseVO;
import com.idigitronics.iDiGiT.response.vo.MeterSizeResponseVO;
import com.idigitronics.iDiGiT.response.vo.ResponseVO;
import com.idigitronics.iDiGiT.response.vo.TariffResponseVO;
import com.idigitronics.iDiGiT.utils.Encryptor;
import com.idigitronics.iDiGiT.dao.ManagementSettingsDAO;

/**
 * @author K VimaL Kumar
 * 
 */
public class CommunitySetUpDAO {
	
	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}

	/* Community */

	public List<CommunityResponseVO> getCommunitydetails(int roleid, String id) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CommunityResponseVO> communitydetailslist = null;
		try {
			con = getConnection();
			communitydetailslist = new LinkedList<CommunityResponseVO>();
			
			String query = "SELECT c.CommunityID, c.CommunityName, c.Email, c.MobileNumber, c.CreatedDate, c.Address FROM community AS c <change> ";
			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid==2 || roleid==5) ? "LEFT JOIN block AS b ON b.CommunityID = c.CommunityID WHERE b.BlockID = "+id : (roleid == 3) ? "LEFT JOIN customerdetails AS cd ON cd.CommunityID = c.CommunityID WHERE cd.CRNNumber = '"+id+"'": "ORDER BY c.CommunityID DESC"));
			
			rs = pstmt.executeQuery();
			CommunityResponseVO communityvo = null;
			
			while (rs.next()) {
				communityvo = new CommunityResponseVO();
				communityvo.setCommunityName(rs.getString("CommunityName"));
				communityvo.setEmail(rs.getString("Email"));
				communityvo.setMobileNumber(rs.getString("MobileNumber"));
				communityvo.setAddress(rs.getString("Address"));
				communityvo.setCommunityID(rs.getInt("CommunityID"));
				communitydetailslist.add(communityvo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return communitydetailslist;
	}

	public ResponseVO addcommunity(CommunityRequestVO communityvo) throws SQLException{
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			pstmt = con.prepareStatement("INSERT INTO community (CommunityName, Email, MobileNumber, Address, CreatedDate) VALUES (?, ?, ?, ?, NOW())");
			pstmt.setString(1, communityvo.getCommunityName());
			pstmt.setString(2, communityvo.getEmail());
			pstmt.setString(3, communityvo.getmobileNumber());
			pstmt.setString(4, communityvo.getAddress());

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Community Added Successfully");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsevo.setMessage("SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}
		return responsevo;
	}
	
	public ResponseVO editcommunity(CommunityRequestVO communityvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE community SET CommunityName = ?, Email = ?, MobileNumber = ?, Address = ?, ModifiedDate = NOW() WHERE CommunityID = ?");
			pstmt.setString(1, communityvo.getCommunityName());
			pstmt.setString(2, communityvo.getEmail());
			pstmt.setString(3, communityvo.getmobileNumber());
			pstmt.setString(4, communityvo.getAddress());
			pstmt.setInt(5, communityvo.getCommunityID());

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Community Details Updated Successfully");
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
	
	public boolean checkIfCommunityNameExists(CommunityRequestVO communityvo, String mode) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try{
		con = getConnection();
		String query = "SELECT * from community WHERE <change> CommunityName = '"+communityvo.getCommunityName().trim()+"'";
		pstmt = con.prepareStatement(query.replaceAll("<change>", (mode.equalsIgnoreCase("add")) ? "" : "CommunityID != "+communityvo.getCommunityID() + " AND "));
		
		rs = pstmt.executeQuery();
        if (rs.next()) {
        	result = true;
        	}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		
		return result;
	}
	
/*Gateway*/
	
	public List<GatewayResponseVO> getGatewaydetails() throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GatewayResponseVO> gateway_list = null;
		GatewayResponseVO gatewayResponseVO = null;

		try {
			con = getConnection();
			gateway_list = new LinkedList<GatewayResponseVO>();
			pstmt = con.prepareStatement("SELECT GatewayID, GatewayName, GatewaySerialNumber, GatewayIP, GatewayPort, ModifiedDate FROM gateway ORDER BY GatewayID DESC");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				gatewayResponseVO = new GatewayResponseVO();
				gatewayResponseVO.setGatewayID(rs.getInt("GatewayID"));
				gatewayResponseVO.setGatewayName(rs.getString("GatewayName"));
				gatewayResponseVO.setGatewayIP(rs.getString("GatewayIP"));
				gatewayResponseVO.setGatewayPort(rs.getString("GatewayPort"));
				gatewayResponseVO.setGatewaySerialNumber(rs.getString("GatewaySerialNumber"));
				gatewayResponseVO.setModifiedDate(ExtraMethodsDAO.datetimeformatter(rs.getString("ModifiedDate")));
				gateway_list.add(gatewayResponseVO);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return gateway_list;
	}

	public ResponseVO addgateway(GatewayRequestVO gatewayvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();
		try {
			con = getConnection();
			pstmt = con.prepareStatement("INSERT INTO gateway (GatewayName, GatewaySerialNumber, GatewayIP, GatewayPort, RegisteredDate) VALUES(?, ?, ?, ?, NOW())");
			pstmt.setString(1, gatewayvo.getGatewayName());
			pstmt.setString(2, gatewayvo.getGatewaySerialNumber());
			pstmt.setString(3, gatewayvo.getGatewayIP());
			pstmt.setString(4, gatewayvo.getGatewayPort());
			
			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Gateway Added Successfully");
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

	public ResponseVO editgateway(GatewayRequestVO gatewayvo) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO(); 

		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE gateway SET GatewayName = ?, GatewaySerialNumber = ?, GatewayIP = ?, GatewayPort = ?, ModifiedDate = NOW() WHERE GatewayID = ?");
			pstmt.setString(1, gatewayvo.getGatewayName());
			pstmt.setString(2, gatewayvo.getGatewaySerialNumber());
			pstmt.setString(3, gatewayvo.getGatewayIP());
			pstmt.setString(4, gatewayvo.getGatewayPort());
			pstmt.setInt(5, gatewayvo.getGatewayID());

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Gateway Updated Successfully");
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
	
	public ResponseVO deletegateway(int gatewayID) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO(); 

		try {
			con = getConnection();
			pstmt = con.prepareStatement("DELETE FROM gateway WHERE GatewayID ="+gatewayID);

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Gateway Deleted Successfully");
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
	
	public boolean checkgateway(GatewayRequestVO gatewayvo, String mode) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			
			String query = "SELECT * FROM gateway WHERE <change> GatewaySerialNumber = '" + gatewayvo.getGatewaySerialNumber().trim() + "'";
			pstmt = con.prepareStatement(query.replaceAll("<change>", (mode.equalsIgnoreCase("add")) ? "" : "GatewayID != "+gatewayvo.getGatewayID() + " AND "));
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
	
	public boolean checkgatewayIsSetToCustomers(int gatewayID) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM customermeterdetails WHERE GatewayID = "+gatewayID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				result = true;

			} 
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}

		return result;
	}
	
/*Meter Size*/
	
	public List<MeterSizeResponseVO> getMeterSizedetails() throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MeterSizeResponseVO> meterSizeList = null;
		MeterSizeResponseVO meterSizeResponseVO = null;

		try {
			con = getConnection();
			meterSizeList = new LinkedList<MeterSizeResponseVO>();
			pstmt = con.prepareStatement("SELECT MeterSizeID, MeterSize, MeterType, PerUnitValue, ModifiedDate FROM metersize ORDER BY MeterSizeID DESC");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				meterSizeResponseVO = new MeterSizeResponseVO();
				meterSizeResponseVO.setMeterSizeID(rs.getInt("MeterSizeID"));
				meterSizeResponseVO.setMeterType(rs.getString("MeterType"));
				meterSizeResponseVO.setMeterSize(rs.getInt("MeterSize"));
				meterSizeResponseVO.setPerUnitValue(rs.getFloat("PerUnitValue"));
				meterSizeResponseVO.setModifiedDate(ExtraMethodsDAO.datetimeformatter(rs.getString("ModifiedDate")));
				meterSizeList.add(meterSizeResponseVO);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return meterSizeList;
	}

	public ResponseVO addMeterSize(MeterSizeRequestVO meterSizeRequestVO) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();
		try {
			con = getConnection();
			pstmt = con.prepareStatement("INSERT INTO metersize (MeterType, MeterSize, PerUnitValue, CreatedDate) VALUES(?, ?, ?, NOW())");
			pstmt.setString(1, meterSizeRequestVO.getMeterType());
			pstmt.setInt(2, meterSizeRequestVO.getMeterSize());
			pstmt.setFloat(3, meterSizeRequestVO.getPerUnitValue());
			
			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Meter Size Added Successfully");
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

	public ResponseVO editMeterSize(MeterSizeRequestVO meterSizeRequestVO) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO(); 

		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE metersize SET MeterType = ?, MeterSize = ?, PerUnitValue = ?, ModifiedDate = NOW() WHERE MeterSizeID = ?");
			pstmt.setString(1, meterSizeRequestVO.getMeterType());
			pstmt.setInt(2, meterSizeRequestVO.getMeterSize());
			pstmt.setFloat(3, meterSizeRequestVO.getPerUnitValue());
			pstmt.setInt(4, meterSizeRequestVO.getMeterSizeID());

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Meter Size Updated Successfully");
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
	
	public ResponseVO deleteMeterSize(int metersizeID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO(); 

		try {
			con = getConnection();
			pstmt = con.prepareStatement("DELETE FROM metersize WHERE MeterSizeID ="+metersizeID);

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Metersize Deleted Successfully");
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
	
	public boolean checkMeterSize(MeterSizeRequestVO meterSizeRequestVO, String mode) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			
			String query = "SELECT * FROM metersize WHERE <change> MeterType = '"+ meterSizeRequestVO.getMeterType()+"' AND MeterSize = " + meterSizeRequestVO.getMeterSize();
			pstmt = con.prepareStatement(query.replaceAll("<change>", (mode.equalsIgnoreCase("add")) ? "" : "MeterSizeID != "+meterSizeRequestVO.getMeterSizeID() + " AND "));
			
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

	public boolean checkMeterSizeIsSetToMeters(int metersizeID) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM customermeterdetails WHERE MeterSizeID = "+metersizeID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				result = true;

			} 
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}

		return result;
	}

	/* Block */

	public List<BlockResponseVO> getBlockdetails(int roleid, String id) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BlockResponseVO> block_list = null;

		try {
			con = getConnection();

			block_list = new LinkedList<BlockResponseVO>();
			
			String query = "SELECT block.BlockID, community.CommunityName, block.BlockName, block.Location, block.MobileNumber, block.Email, block.CreatedDate FROM block LEFT JOIN community ON community.CommunityID = Block.CommunityID <change>";
			
			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid==2 || roleid==5) ? "WHERE block.BlockID = "+id : (roleid == 3) ? "LEFT JOIN customerdetails AS cmd ON cmd.BlockID = Block.BlockID WHERE cmd.CustomerUniqueID = '"+id+"'" : " ORDER BY block.BlockID DESC"));
			rs = pstmt.executeQuery();
			BlockResponseVO blockvo = null;

			while (rs.next()) {

				blockvo = new BlockResponseVO();
				blockvo.setCommunityName(rs.getString("CommunityName"));
				blockvo.setBlockName(rs.getString("BlockName"));
				blockvo.setMobile(rs.getString("MobileNumber"));
				blockvo.setEmail(rs.getString("Email"));
				blockvo.setLocation(rs.getString("Location"));
				blockvo.setBlockID(rs.getInt("BlockID"));

				block_list.add(blockvo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
//			rs.close();
			con.close();
		}

		return block_list;
	}

	public ResponseVO addblock(BlockRequestVO blockvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();
		String communityName = "";

		try {
			con = getConnection();
			
			boolean flag;
			int i = 0;
			
			pstmt = con.prepareStatement("INSERT INTO block (BlockName, Location, MobileNumber, Email, CommunityID, CreatedByID, CreatedByRoleID, CreatedDate, ModifiedDate) VALUES (?, ?, ?, ?, ?, ?, 1, NOW(), NOW())");
			pstmt.setString(1, blockvo.getBlockName());
			pstmt.setString(2, blockvo.getLocation());
			pstmt.setString(3, blockvo.getMobileNumber());
			pstmt.setString(4, blockvo.getEmail());
			pstmt.setInt(5, blockvo.getCommunityID());
			pstmt.setInt(6, blockvo.getCreatedByID());

			if (pstmt.executeUpdate() > 0) {
				
				ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
				UserManagementRequestVO usermanagementvo = new UserManagementRequestVO();
				
				PreparedStatement pstmt1 = con.prepareStatement("SELECT MAX(block.BlockID) AS BlockID, community.CommunityName FROM block LEFT JOIN community ON community.CommunityID = block.CommunityID WHERE block.BlockName = ? AND block.CommunityID = ?");
				pstmt1.setString(1, blockvo.getBlockName());
				pstmt1.setInt(2, blockvo.getCommunityID());
				ResultSet rs = pstmt1.executeQuery();
				if(rs.next()) {
					communityName = rs.getString("CommunityName");
					usermanagementvo.setBlockID(blockvo.getBlockID());
					LoginDAO logindao = new LoginDAO();
					
					String userID = blockvo.getBlockName();
					
					do {
						 flag = logindao.checkuserid(userID);
						 i++;
						 if(flag) {
							 userID = userID+i;	
							}
						 
					} while (flag == true);
					
					if(i<=1) {
						usermanagementvo.setUserID(blockvo.getBlockName());	
					}else {
						usermanagementvo.setUserID(userID);
					}
					
				
				usermanagementvo.setUserName(blockvo.getBlockName());
				usermanagementvo.setUserPassword(Encryptor.encrypt(ExtraConstants.key1, ExtraConstants.key2, blockvo.getBlockName() + "@" + blockvo.getMobileNumber().substring(3, 7)));
				usermanagementvo.setRoleID(2);
				usermanagementvo.setCommunityID(blockvo.getCommunityID());
				usermanagementvo.setBlockID(rs.getInt("BlockID"));
				usermanagementvo.setLoggedInRoleID(1);
				usermanagementvo.setLoggedInUserID(blockvo.getLoggedInUserID());
				usermanagementvo.setCustomerUniqueID("NULL");
				usermanagementvo.setMobileNumber(blockvo.getMobileNumber());
				usermanagementvo.setEmailID(blockvo.getEmail());
				}
				
				if(managementsettingsdao.adduser(usermanagementvo).getResult().equalsIgnoreCase("Success")){
					
					ExtraMethodsDAO extraMethodsDAO = new ExtraMethodsDAO();
					MailRequestVO mailrequestvo = new MailRequestVO();
					SMSRequestVO smsRequestVO = new SMSRequestVO();
					
					mailrequestvo.setFileLocation("NoAttachment");
					mailrequestvo.setToEmail(blockvo.getEmail());
					mailrequestvo.setUserID(usermanagementvo.getUserID());
					mailrequestvo.setUserPassword(blockvo.getBlockName() + "@" + blockvo.getMobileNumber().substring(3, 7));
					mailrequestvo.setSubject("Admin Credentials For " + blockvo.getBlockName()+", "+blockvo.getLocation()+" in "+communityName+".");
					mailrequestvo.setMessage("Please Save the Credentials for further communications \n"
							+ " UserID: " + mailrequestvo.getUserID() + "\n Password: " + mailrequestvo.getUserPassword() + "\n Use URL for login : "+ ExtraConstants.ApplicationURL);
					
					smsRequestVO.setToMobileNumber(blockvo.getMobileNumber());
					smsRequestVO.setMessage("Please Save the Credentials for further communications \n" + " UserID: " + mailrequestvo.getUserID() + "\n Password: " + mailrequestvo.getUserPassword()+ "\n Use URL for login : "+ ExtraConstants.ApplicationURL);
					
					extraMethodsDAO.sendsms(smsRequestVO);
					
					if(extraMethodsDAO.sendmail(mailrequestvo).equalsIgnoreCase("Success")) {
						extraMethodsDAO.sendsms(smsRequestVO);
						responsevo.setResult("Success");
						responsevo.setMessage("Block Added Successfully and Block Admin Credentials have been sent to registered mail");
					}else {
						extraMethodsDAO.sendsms(smsRequestVO);
						responsevo.setResult("Success");
						responsevo.setMessage("Block Registered Successfully but due to internal server Error Credentials have not been sent to your registered Mail ID. Please Contact Administrator");
					}
					
				} else {
					PreparedStatement pstmt2 = con.prepareStatement("DELETE FROM block WHERE BlockID = (SELECT BlockID FROM block WHERE BlockName = ? AND CommunityID = ?)");
					pstmt2.setString(1, blockvo.getBlockName());
					pstmt2.setInt(2, blockvo.getCommunityID());
					
					if(pstmt2.executeUpdate() > 0) {
						responsevo.setResult("Failure");
						responsevo.setMessage("Block Addition Failed");
					}
				}
				
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsevo.setMessage("SERVER ERROR");
			responsevo.setResult("Failure");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}

		return responsevo;
	}

	public ResponseVO editblock(BlockRequestVO blockvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE block SET BlockName = ?, Location = ?, MobileNumber = ?, Email = ?, ModifiedDate = NOW() WHERE BlockID = ?");
			pstmt.setString(1, blockvo.getBlockName());
			pstmt.setString(2, blockvo.getLocation());
			pstmt.setString(3, blockvo.getMobileNumber());
			pstmt.setString(4, blockvo.getEmail());
			pstmt.setInt(5, blockvo.getBlockID());

			if (pstmt.executeUpdate() > 0) {
				
				pstmt1 = con.prepareStatement("UPDATE user SET MobileNumber = ?, Email = ?, ModifiedDate = NOW() WHERE RoleID = 2 AND BlockID = ?");
				pstmt1.setString(1, blockvo.getMobileNumber());
				pstmt1.setString(2, blockvo.getEmail());
				pstmt1.setInt(3, blockvo.getBlockID());
				
				if(pstmt1.executeUpdate() > 0) {
					responsevo.setResult("Success");
					responsevo.setMessage("Block Details Updated Successfully");					
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
	
	public ResponseVO deleteblock(int blockID) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();
		
		try{
		con = getConnection();
		
		pstmt = con.prepareStatement("DELETE FROM block WHERE BlockID= "+blockID);
		
        if (pstmt.executeUpdate() > 0) {
        	
        	PreparedStatement pstmt1 = con.prepareStatement("DELETE FROM user WHERE RoleID = 2 AND BlockID = "+blockID);
        	if(pstmt1.executeUpdate() > 0) {
        		responsevo.setResult("Success");
    			responsevo.setMessage("Block Deleted Successfully");        		
        	}
        	}
		}
		catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}
		
		return responsevo;
	}
	
	public boolean checkIfBlockNameExists(BlockRequestVO blockvo, String mode) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try{
		con = getConnection();
		String query = "SELECT * FROM block WHERE CommunityID = ? AND <change> BlockName = '"+blockvo.getBlockName().trim()+"'";
		pstmt = con.prepareStatement(query.replaceAll("<change>", (mode.equalsIgnoreCase("add")) ? "" : "BlockID != "+blockvo.getBlockID() + " AND "));
		pstmt.setInt(1, blockvo.getCommunityID());
		
		rs = pstmt.executeQuery();
        if (rs.next()) {
        	result = true;
        	}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		
		return result;
	}
	
	public boolean checkifhousesexist(int blockID) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try{
		con = getConnection();
		
		pstmt = con.prepareStatement("select * from customerdetails where BlockID = ?");
		pstmt.setInt(1, blockID);
		
		rs = pstmt.executeQuery();
        if (rs.next()) {
        	result = true;
        	}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		
		return result;
	}

	/* Customer */

	public List<CustomerResponseVO> getCustomerdetails(int roleid, String id, int filterCid) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List<CustomerResponseVO> customer_list = null;
		List<MeterRequestVO> customer_meter_list = null;
		CustomerResponseVO customervo = null;
		MeterRequestVO metervo = null;
		DropDownDAO dropdowndao = new DropDownDAO();
		try {
			con = getConnection();
			customer_list = new LinkedList<CustomerResponseVO>();
			
			
			HashMap<Integer, Integer> gasDropdown = dropdowndao.getallmetersizes("Gas");
			
			HashMap<Integer, Integer> waterDropdown = dropdowndao.getallmetersizes("Water");
		
			// create a view for this
			
			String query = "SELECT c.CommunityName, b.BlockName, cd.CustomerID, cd.HouseNumber, cd.FirstName, cd.LastName, cd.Email, cd.MobileNumber, cd.CustomerUniqueID, cd.CreatedByID, cd.CreatedByRoleID, cd.ModifiedDate\n" + 
							"FROM customerdetails AS cd LEFT JOIN community AS c ON cd.CommunityID = c.CommunityID LEFT JOIN block AS b ON b.BlockID = cd.BlockID <change>";
							
			pstmt = con.prepareStatement(query.replaceAll("<change>", ((roleid == 1 || roleid == 4) && (filterCid == -1)) ? "ORDER BY cd.CustomerID DESC" : ((roleid == 1 || roleid == 4) && (filterCid != -1)) ? " WHERE cd.CommunityID = "+filterCid+" ORDER BY cd.CustomerID DESC" : (roleid == 2 || roleid == 5) ? "WHERE cd.BlockID = "+id+ " ORDER BY cd.CustomerID DESC" : (roleid == 3) ? "WHERE cd.CustomerUniqueID = '"+id+"'":""));
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				customervo = new CustomerResponseVO();
				customer_meter_list = new LinkedList<MeterRequestVO>();
				
				customervo.setCommunityName(rs.getString("CommunityName"));
				customervo.setBlockName(rs.getString("BlockName"));
				customervo.setFirstName(rs.getString("FirstName"));
				customervo.setLastName(rs.getString("LastName"));
				customervo.setEmail(rs.getString("Email"));
				customervo.setMobileNumber(rs.getString("MobileNumber"));
				customervo.setHouseNumber(rs.getString("HouseNumber"));
				customervo.setCustomerUniqueID(rs.getString("CustomerUniqueID"));
				customervo.setCustomerID(rs.getLong("CustomerID"));
				customervo.setAction((roleid == 1 || roleid == 2 || roleid == 3 ? true : false));
				
				PreparedStatement pstmt2 = con.prepareStatement("SELECT cmd.CustomerMeterID, cmd.MIUID, cmd.MeterSerialNumber, cmd.MeterType, cmd.PayType, cmd.Location, cmd.TariffID, cmd.GatewayID, cmd.ThresholdMaximum, cmd.ThresholdMinimum, g.GatewayName, ms.MeterSize, ms.MeterSizeID FROM customermeterdetails AS cmd LEFT JOIN gateway AS g ON g.GatewayID = cmd.GatewayID LEFT JOIN metersize AS ms ON ms.MeterSizeID = cmd.MeterSizeID WHERE cmd.CustomerID = " + customervo.getCustomerID());
				
				ResultSet rs2 = pstmt2.executeQuery();
				
				while (rs2.next()) {
					
					metervo = new MeterRequestVO();
					
					metervo.setCustomerMeterID(rs2.getInt("CustomerMeterID"));
					metervo.setMiuID(rs2.getString("MIUID"));
					metervo.setMeterSerialNumber(rs2.getString("MeterSerialNumber"));
					metervo.setMeterType(rs2.getString("MeterType"));
					metervo.setPayType(rs2.getString("PayType"));
					metervo.setMeterSize(rs2.getFloat("MeterSize"));
					
					metervo.setMeterIDSize(rs2.getInt("MeterSizeID"));
					
					
					metervo.setLocation(rs2.getString("Location"));
					metervo.setTariffID(rs2.getInt("TariffID"));
					metervo.setGatewayID(rs2.getInt("GatewayID"));
					metervo.setGatewayName(rs2.getString("GatewayName"));
					metervo.setThresholdMaximum(rs2.getFloat("ThresholdMaximum"));
					metervo.setThresholdMinimum(rs2.getFloat("ThresholdMinimum"));
					
					PreparedStatement pstmt3 = con.prepareStatement("SELECT TariffName from tariff WHERE TariffID = "+ metervo.getTariffID());
					
					ResultSet rs3 = pstmt3.executeQuery();
					
					if(rs3.next()) {
						
						metervo.setTariffName(rs3.getString("TariffName"));
						
					}
					
					metervo.setGasDropdown(gasDropdown);
					metervo.setWaterDropdown(waterDropdown);
					
					customer_meter_list.add(metervo);
				}
				
				customervo.setMeters(customer_meter_list);
				
				pstmt1 = con.prepareStatement("SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "+rs.getInt("CreatedByID"));
				rs1 = pstmt1.executeQuery();
				if(rs1.next()) {
					customervo.setCreatedByUserName(rs1.getString("UserName"));
					customervo.setCreatedByRoleDescription(rs1.getString("RoleDescription"));
				}
				
				customervo.setDate(ExtraMethodsDAO.datetimeformatter(rs.getString("ModifiedDate")));

				customer_list.add(customervo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return customer_list;
	}
	
	public ResponseVO getCustomerDetailsByMiuID(String miuID) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		CustomerResponseVO customervo = null;
		MeterRequestVO metervo = null;
		ResponseVO responsevo = new ResponseVO();
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("SELECT cmd.CustomerMeterID, cmd.CustomerID, cmd.MeterSerialNumber, cmd.MeterType, cmd.PayType, cmd.Location, cmd.TariffID, cmd.GatewayID, cmd.ThresholdMaximum, cmd.ThresholdMinimum, g.GatewayName, ms.MeterSize, ms.MeterSizeID FROM customermeterdetails AS cmd LEFT JOIN gateway AS g ON g.GatewayID = cmd.GatewayID LEFT JOIN metersize AS ms ON ms.MeterSizeID = cmd.MeterSizeID WHERE cmd.MIUID = '" + miuID + "'");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				metervo = new MeterRequestVO();
				customervo = new CustomerResponseVO();
				
				metervo.setCustomerMeterID(rs.getInt("CustomerMeterID"));
				metervo.setMiuID(miuID);
				metervo.setMeterSerialNumber(rs.getString("MeterSerialNumber"));
				metervo.setMeterType(rs.getString("MeterType"));
				metervo.setPayType(rs.getString("PayType"));
				metervo.setMeterSize(rs.getFloat("MeterSize"));
				metervo.setMeterIDSize(rs.getInt("MeterSizeID"));
				metervo.setLocation(rs.getString("Location"));
				metervo.setTariffID(rs.getInt("TariffID"));
				metervo.setGatewayID(rs.getInt("GatewayID"));
				metervo.setGatewayName(rs.getString("GatewayName"));
				metervo.setThresholdMaximum(rs.getFloat("ThresholdMaximum"));
				metervo.setThresholdMinimum(rs.getFloat("ThresholdMinimum"));
				
				customervo.setCustomerID(rs.getLong("CustomerID"));
				
				pstmt1 = con.prepareStatement("SELECT c.CommunityName, b.BlockName, cd.CustomerID, cd.HouseNumber, cd.FirstName, cd.LastName, cd.Email, cd.MobileNumber, cd.CustomerUniqueID, cd.CreatedByID, cd.CreatedByRoleID, cd.ModifiedDate\n" + 
							"FROM customerdetails AS cd LEFT JOIN community AS c ON cd.CommunityID = c.CommunityID LEFT JOIN block AS b ON b.BlockID = cd.BlockID WHERE cd.CustomerID = " +customervo.getCustomerID());
				
				rs1 = pstmt1.executeQuery();
				
				if (rs1.next()) {
					
					customervo.setCommunityName(rs1.getString("CommunityName"));
					customervo.setBlockName(rs1.getString("BlockName"));
					customervo.setFirstName(rs1.getString("FirstName"));
					customervo.setLastName(rs1.getString("LastName"));
					customervo.setEmail(rs1.getString("Email"));
					customervo.setMobileNumber(rs1.getString("MobileNumber"));
					customervo.setHouseNumber(rs1.getString("HouseNumber"));
					customervo.setCustomerUniqueID(rs1.getString("CustomerUniqueID"));
					
					if(metervo.getPayType().equalsIgnoreCase("Prepaid")) {
						
						PreparedStatement pstmt2 = con.prepareStatement("select Balance from displaybalancelog where customerMeterID = "+metervo.getCustomerMeterID());
						
						ResultSet rs2 = pstmt2.executeQuery();
						
						if(rs2.next()) {
							
							metervo.setAvailableBalance(rs2.getString("Balance"));
							
						} else {
							metervo.setAvailableBalance("---");
						}
						
					} else {
						metervo.setAvailableBalance("---");
					}
					
					PreparedStatement pstmt3 = con.prepareStatement("SELECT TariffName, Tariff, EmergencyCredit from tariff WHERE TariffID = "+ metervo.getTariffID());
					
					ResultSet rs3 = pstmt3.executeQuery();
					
					if(rs3.next()) {
						
						metervo.setTariffName(rs3.getString("TariffName"));
						metervo.setTariff(rs3.getString("Tariff"));
						metervo.setEmergencyCredit(rs3.getString("EmergencyCredit"));
						
					}
					
				}
				
				customervo.setMeterDetails(metervo);
				
				customervo.setDate(ExtraMethodsDAO.datetimeformatter(rs1.getString("ModifiedDate")));
				
				responsevo.setCustomerDetails(customervo);
				responsevo.setResult("Success");
				responsevo.setMessage("Customer Details Retrieved Successfully");

			} else {
				responsevo.setResult("Failure");
				responsevo.setMessage("MIUID is not registered with any Customer");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return responsevo;
	}
	
	public ResponseVO addcustomer(CustomerRequestVO customervo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();

			pstmt = con.prepareStatement(
					"INSERT INTO customerdetails (CommunityID, BlockID, HouseNumber, FirstName, LastName, Email, MobileNumber, ActiveStatus, CustomerUniqueID, CreatedByID, CreatedByRoleID, ModifiedDate) VALUES (?, ?, ?, ?, ?, ?, ?, 1, ?, ?, ?, Now() )");
			pstmt.setInt(1, customervo.getCommunityID());
			pstmt.setInt(2, customervo.getBlockID());
			pstmt.setString(3, customervo.getHouseNumber());
			pstmt.setString(4, customervo.getFirstName());
			pstmt.setString(5, customervo.getLastName());
			pstmt.setString(6, customervo.getEmail());
			pstmt.setString(7, customervo.getMobileNumber());
			pstmt.setString(8, customervo.getCustomerUniqueID());
			pstmt.setInt(9, customervo.getCreatedByID());
			pstmt.setInt(10, customervo.getLoggedInRoleID());

			if (pstmt.executeUpdate() > 0) {
				
				ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
				UserManagementRequestVO usermanagementvo = new UserManagementRequestVO();
				
				pstmt1 = con.prepareStatement("SELECT CustomerID from customerdetails WHERE CustomerUniqueID = ?");
				pstmt1.setString(1, customervo.getCustomerUniqueID());
				ResultSet rs = pstmt1.executeQuery();
				if(rs.next()) {
					
					for(int i = 0; i < customervo.getMeters().size(); i++) {
						
						PreparedStatement pstmt4 = con.prepareStatement("INSERT INTO customermeterdetails (CustomerID, CustomerUniqueID, MIUID, MeterSerialNumber, MeterType, MeterSizeID, PayType, TariffID, GatewayID, Location, ThresholdMaximum, ThresholdMinimum, ModifiedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
						pstmt4.setInt(1, rs.getInt("CustomerID"));
						pstmt4.setString(2, customervo.getCustomerUniqueID());
						pstmt4.setString(3, customervo.getMeters().get(i).getMiuID().toLowerCase());
						pstmt4.setString(4, customervo.getMeters().get(i).getMeterSerialNumber());
						pstmt4.setString(5, customervo.getMeters().get(i).getMeterType());
						pstmt4.setInt(6, customervo.getMeters().get(i).getMeterSizeID());
						pstmt4.setString(7, customervo.getMeters().get(i).getPayType());
						pstmt4.setInt(8, customervo.getMeters().get(i).getTariffID());
						pstmt4.setInt(9, customervo.getMeters().get(i).getGatewayID());
						pstmt4.setString(10, customervo.getMeters().get(i).getLocation());
						pstmt4.setFloat(11, customervo.getMeters().get(i).getThresholdMaximum());
						pstmt4.setFloat(12, customervo.getMeters().get(i).getThresholdMinimum());
						
						if(pstmt4.executeUpdate() > 0) {
							responsevo.setResult("Success");
						}
						
					}
					
					usermanagementvo.setBlockID(customervo.getBlockID());
					usermanagementvo.setUserID(customervo.getCustomerUniqueID());
					usermanagementvo.setUserName(customervo.getFirstName() + " " + customervo.getLastName());
					usermanagementvo.setUserPassword(Encryptor.encrypt(ExtraConstants.key1, ExtraConstants.key2, customervo.getLastName()+"@"+ customervo.getMobileNumber().substring(3, 7)));
					usermanagementvo.setRoleID(3);
					usermanagementvo.setCommunityID(customervo.getCommunityID());
					usermanagementvo.setCustomerID(rs.getInt("CustomerID"));
					usermanagementvo.setBlockID(customervo.getBlockID());
					usermanagementvo.setCustomerUniqueID(customervo.getCustomerUniqueID());
					usermanagementvo.setLoggedInRoleID(customervo.getLoggedInRoleID());
					usermanagementvo.setLoggedInUserID(customervo.getLoggedInUserID());
					usermanagementvo.setMobileNumber(customervo.getMobileNumber());
					usermanagementvo.setEmailID(customervo.getEmail());
					
					if(managementsettingsdao.adduser(usermanagementvo).getResult().equalsIgnoreCase("Success")){
						
						ExtraMethodsDAO extraMethodsDAO = new ExtraMethodsDAO();
						MailRequestVO mailrequestvo = new MailRequestVO();
						SMSRequestVO smsRequestVO = new SMSRequestVO();
						
						mailrequestvo.setFileLocation("NoAttachment");
						mailrequestvo.setToEmail(customervo.getEmail());
						mailrequestvo.setUserID(usermanagementvo.getUserID());
						mailrequestvo.setUserPassword(customervo.getLastName()+"@"+ customervo.getMobileNumber().substring(3, 7));
						mailrequestvo.setSubject("Customer Login for CRN/CAN/UAN: " + mailrequestvo.getUserID());
						mailrequestvo.setMessage("Please Save the Credentials for further communications \n"
								+ " UserID: " + mailrequestvo.getUserID() + "\n Password: " + mailrequestvo.getUserPassword() + "\n Use URL for login : "+ ExtraConstants.ApplicationURL);
						
						smsRequestVO.setToMobileNumber(customervo.getMobileNumber());
						smsRequestVO.setMessage("Please Save the Credentials for further communications \n" + " UserID: " + mailrequestvo.getUserID() + "\n Password: " + mailrequestvo.getUserPassword()+ "\n Use URL for login : "+ ExtraConstants.ApplicationURL);
						
						String result = extraMethodsDAO.sendmail(mailrequestvo);
						
						if(result.equalsIgnoreCase("Success")) {
							extraMethodsDAO.sendsms(smsRequestVO);
							responsevo.setResult("Success");
							responsevo.setMessage("Customer Details Added Successfully");
						}else {
							extraMethodsDAO.sendsms(smsRequestVO);
							responsevo.setResult("Success");
							responsevo.setMessage("Customer Registered Successfully but due to internal server Error Credentials have not been sent to your registered Mail ID. Please Contact Administrator");
						}
						
					} else {
						PreparedStatement pstmt2 = con.prepareStatement("DELETE FROM customerdetails WHERE CustomerUniqueID = '" + customervo.getCustomerUniqueID().trim() + "'");
						
						if(pstmt2.executeUpdate() > 0) {
							
							PreparedStatement pstmt5 = con.prepareStatement("DELETE FROM customermeterdetails WHERE CustomerUniqueID = '" + customervo.getCustomerUniqueID().trim() + "'");
							
							if(pstmt5.executeUpdate() > 0) {
								responsevo.setResult("Failure");
								responsevo.setMessage("Customer Addition Failed");
							}
							
						}
					}
					
				}
				
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsevo.setMessage("SERVER ERROR");
			responsevo.setResult("Failure");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}

		return responsevo;
	}
	
	public ResponseVO editcustomer(CustomerRequestVO customervo) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			
			if(customervo.getLoggedInRoleID() == 3) {
				
				PreparedStatement pstmt1 = con.prepareStatement("SELECT CustomerID, BlockID, LastName FROM customerdetails WHERE CustomerUniqueID = ?");
				pstmt1.setString(1, customervo.getCustomerUniqueID());
				ResultSet rs = pstmt1.executeQuery();
				if(rs.next()) {
					customervo.setCustomerID(rs.getInt("CustomerID"));
					customervo.setBlockID(rs.getInt("BlockID"));
				
				pstmt = con.prepareStatement("INSERT INTO updaterequestcustomermeterdetails (BlockID, CustomerID, CustomerUniqueID, FirstName, Email, MobileNumber, ToBeApprovedByID) VALUES (?, ?, ?, ?, ?, ?, (SELECT CreatedByID FROM user WHERE CustomerUniqueID = ?))");
				pstmt.setInt(1, customervo.getBlockID());
				pstmt.setLong(2, customervo.getCustomerID());
				pstmt.setString(3, customervo.getCustomerUniqueID());
				pstmt.setString(4, customervo.getFirstName());
				pstmt.setString(5, customervo.getEmail());
				pstmt.setString(6, customervo.getMobileNumber());
				pstmt.setString(7, customervo.getCustomerUniqueID());
				
				if (pstmt.executeUpdate() > 0) {
					responsevo.setResult("Success");
					responsevo.setMessage("Request Submitted successfully and is pending for approval by Administrator");
	            	}
				
				}
				
			}else {
				pstmt = con.prepareStatement("UPDATE customerdetails SET HouseNumber=?, FirstName=?, Email=?, MobileNumber=?, ModifiedDate=NOW() WHERE CustomerUniqueID = ?");
	            
				pstmt.setString(1, customervo.getHouseNumber());
	            pstmt.setString(2, customervo.getFirstName());
	            pstmt.setString(3, customervo.getEmail());
	            pstmt.setString(4, customervo.getMobileNumber());
	            pstmt.setString(5, customervo.getCustomerUniqueID());

	            if (pstmt.executeUpdate() > 0) {
	            	
	            	for(int i = 0; i < customervo.getMeters().size(); i++) {
	            		con.prepareStatement("UPDATE customermeterdetails SET MIUID = '"+customervo.getMeters().get(i).getMiuID().trim()+"', GatewayID = " +customervo.getMeters().get(i).getGatewayID()+ ", MeterSizeID = " +customervo.getMeters().get(i).getMeterSizeID() +", ThresholdMaximum = "+customervo.getMeters().get(i).getThresholdMaximum() +", ThresholdMinimum = "+customervo.getMeters().get(i).getThresholdMinimum()+", ModifiedDate = NOW() WHERE CustomerMeterID = " + customervo.getMeters().get(i).getCustomerMeterID()).executeUpdate();
	            		con.prepareStatement("UPDATE displaybalancelog SET MIUID = '"+customervo.getMeters().get(i).getMiuID().trim()+"' WHERE CustomerMeterID = " + customervo.getMeters().get(i).getCustomerMeterID()).executeUpdate();
	            		
	            	}
	            	
	            	PreparedStatement pstmt1 = con.prepareStatement("UPDATE USER SET UserName = CONCAT (?, (SELECT LastName FROM customerdetails WHERE CustomerUniqueID = ?)), MobileNumber = ?, Email = ? WHERE CustomerUniqueID = ?");
	            	pstmt1.setString(1, customervo.getFirstName() + " ");
	            	pstmt1.setString(2, customervo.getCustomerUniqueID());
	            	pstmt1.setString(3, customervo.getMobileNumber());
	            	pstmt1.setString(4, customervo.getEmail());
	            	pstmt1.setString(5, customervo.getCustomerUniqueID());
	            	if(pstmt1.executeUpdate() > 0) {
	            		responsevo.setResult("Success");
	            		responsevo.setMessage("Customer Details Updated Successfully");
	            	}
	            	
	            }
			}
			
            
		} catch (Exception e) {
	        e.printStackTrace();
	        responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
			
	    } finally {
			pstmt.close();
			con.close();
	    }
		return responsevo;
	}
	
	public ResponseVO deletecustomer(CustomerRequestVO customervo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		
		ResponseVO responsevo = new ResponseVO();
		
		try {
			con = getConnection();

			pstmt = con.prepareStatement(
					"INSERT INTO customerdeletedetails (CustomerID, CommunityID, BlockID, HouseNumber, FirstName, LastName, Email, MobileNumber, CustomerUniqueID, CreatedByID, CreatedByRoleID, ModifiedDate)\n"
							+ "SELECT CustomerID, CommunityID, BlockID, HouseNumber, FirstName, LastName, Email, MobileNumber, CustomerUniqueID, CreatedByID, CreatedByRoleID, RegistrationDate FROM customerdetails WHERE CustomerUniqueID = ?");
			pstmt.setString(1, customervo.getCustomerUniqueID());

			if (pstmt.executeUpdate() > 0) {

				pstmt5 = con.prepareStatement("SELECT * FROM customermeterdetails WHERE CustomerUniqueID = '" + customervo.getCustomerUniqueID() + "'");

				ResultSet rs = pstmt5.executeQuery();

				while (rs.next()) {

					pstmt6 = con.prepareStatement(
							"INSERT INTO customerdeletemeter (CustomerMeterID, CustomerID, CustomerUniqueID, MIUID, MeterSerialNumber, MeterType, MeterSizeID, PayType, TariffID, Location, ThresholdMaximum, ThresholdMinimum, ModifiedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");

					pstmt6.setInt(1, rs.getInt("CustomerMeterID"));
					pstmt6.setInt(2, rs.getInt("CustomerID"));
					pstmt6.setString(3, rs.getString("CustomerUniqueID"));
					pstmt6.setString(4, rs.getString("MIUID"));
					pstmt6.setString(5, rs.getString("MeterSerialNumber"));
					pstmt6.setString(6, rs.getString("MeterType"));
					pstmt6.setInt(7, rs.getInt("MeterSizeID"));
					pstmt6.setString(8, rs.getString("PayType"));
					pstmt6.setInt(9, rs.getInt("TariffID"));
					pstmt6.setString(10, rs.getString("Location"));
					pstmt6.setFloat(11, rs.getFloat("ThresholdMaximum"));
					pstmt6.setFloat(12, rs.getFloat("ThresholdMinimum"));

					pstmt6.executeUpdate();					
				}
						pstmt2 = con.prepareStatement("DELETE FROM user WHERE CustomerUniqueID = ?");
						pstmt2.setString(1, customervo.getCustomerUniqueID());

						if (pstmt2.executeUpdate() > 0) {
							
							pstmt1 = con.prepareStatement("DELETE FROM updaterequestcustomermeterdetails where CustomerUniqueID = ?");
							pstmt1.setString(1, customervo.getCustomerUniqueID());

							pstmt1.executeUpdate();
							
							pstmt3 = con.prepareStatement("DELETE FROM customerdetails WHERE CustomerUniqueID = ?");
							pstmt3.setString(1, customervo.getCustomerUniqueID());
							if (pstmt3.executeUpdate() > 0) {

								pstmt4 = con.prepareStatement("DELETE FROM customermeterdetails where CustomerUniqueID = ?");
								pstmt4.setString(1, customervo.getCustomerUniqueID());
								
								if (pstmt4.executeUpdate() > 0) {
									responsevo.setResult("Success");
									responsevo.setMessage("Customer Deleted Successfully");
								}

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
	
	public List<CustomerResponseVO> getCustomerUpdateRequestdetails(int blockid) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CustomerResponseVO> updaterequestlist = null;
		CustomerResponseVO customerresponsevo = null;
		try{
		con = getConnection();
		updaterequestlist = new LinkedList<CustomerResponseVO>();
		
		pstmt = con.prepareStatement("SELECT RequestID, CustomerID, CustomerUniqueID, FirstName, Email, MobileNumber FROM updaterequestcustomermeterdetails WHERE BlockID = ?");
		pstmt.setInt(1, blockid);
		
		rs = pstmt.executeQuery();
        while (rs.next()) {
        	
        	customerresponsevo = new CustomerResponseVO();
        	customerresponsevo.setRequestID(rs.getInt("RequestID"));
        	customerresponsevo.setCustomerUniqueID(rs.getString("CustomerUniqueID"));
        	customerresponsevo.setFirstName(rs.getString("FirstName"));
        	customerresponsevo.setEmail(rs.getString("Email"));
        	customerresponsevo.setMobileNumber(rs.getString("MobileNumber"));
        	
        	PreparedStatement pstmt1 = con.prepareStatement("SELECT UserID FROM user WHERE CustomerUniqueID = ?");
        	pstmt1.setString(1, rs.getString("CustomerUniqueID"));
        
        	ResultSet rs1 = pstmt1.executeQuery();
        	if(rs1.next()) {
        		customerresponsevo.setUserID(rs1.getString("UserID"));
        	}
        	
			updaterequestlist.add(customerresponsevo);

        }
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		return updaterequestlist;
	}
	
	public ResponseVO approverequest(int requestid, int action) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			
			if(action == 1) {
				
				pstmt = con.prepareStatement("UPDATE customerdetails AS cd INNER JOIN updaterequestcustomermeterdetails AS urcmd ON cd.CustomerUniqueID = urcmd.CustomerUniqueID SET cd.FirstName = urcmd.FirstName, cd.Email = urcmd.Email, cd.MobileNumber = urcmd.MobileNumber, cd.ModifiedDate = NOW() WHERE cd.CustomerUniqueID = (SELECT CustomerUniqueID FROM updaterequestcustomermeterdetails WHERE RequestID = ?)");
	            pstmt.setInt(1, requestid);

	            if (pstmt.executeUpdate() > 0) {
	            	
	            	PreparedStatement pstmt2 = con.prepareStatement("SELECT * FROM updaterequestcustomermeterdetails WHERE RequestID = "+requestid);
	            	ResultSet rs = pstmt2.executeQuery();
	            	if(rs.next()) {
	            		
	            		PreparedStatement pstmt3 = con.prepareStatement("UPDATE USER SET UserName = CONCAT (?, (SELECT LastName FROM customerdetails WHERE CustomerUniqueID = ?)), MobileNumber = ?, EmailID = ? WHERE CustomerUniqueID = ?");
		            	pstmt3.setString(1, rs.getString("FirstName")+ " ");
		            	pstmt3.setString(2, rs.getString("CustomerUniqueID"));
		            	pstmt3.setString(3, rs.getString("MobileNumber"));
		            	pstmt3.setString(4, rs.getString("EmailID"));
		            	pstmt3.setString(5, rs.getString("CustomerUniqueID"));
		            	if(pstmt3.executeUpdate() > 0) {
		            		PreparedStatement pstmt1 = con.prepareStatement("DELETE FROM updaterequestcustomermeterdetails WHERE RequestID = ?");
			            	pstmt1.setInt(1, requestid);
			            	if(pstmt1.executeUpdate() > 0) {
			            		responsevo.setResult("Success");
			            		responsevo.setMessage("Approved");
			            	}
		            	}
	            	}
	            	
	            }

			}else {
				
				PreparedStatement pstmt1 = con.prepareStatement("DELETE FROM updaterequestcustomermeterdetails WHERE RequestID = ?");
            	pstmt1.setInt(1, requestid);
            	if(pstmt1.executeUpdate() > 0) {
            		responsevo.setResult("Success");
            		responsevo.setMessage("Rejected Successfully");
            	}
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
//			pstmt.close();
			con.close();
		}
		
		return responsevo;
	}
	
	public boolean checkcustomerName(CustomerRequestVO customervo) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try{
		con = getConnection();
		
		pstmt = con.prepareStatement("SELECT * from customerdetails where LastName = ? AND FirstName = ?");
		pstmt.setString(1, customervo.getLastName().trim());
		pstmt.setString(2, customervo.getFirstName().trim());
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
				result = true;
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		
		return result;
	}
	
	public boolean checkMIUID(MeterRequestVO metervo) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try{
		con = getConnection();
		pstmt = con.prepareStatement("SELECT * from customermeterdetails where MIUID = '"+metervo.getMiuID().trim()+"'");
		rs = pstmt.executeQuery();
        
		if (rs.next()) {
        	result = true;
        	}  
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		
		return result;
	}

	public boolean checkMeterSerialNumber(MeterRequestVO metervo) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try{
		con = getConnection();
		pstmt = con.prepareStatement("SELECT * from customermeterdetails where MeterSerialNumber = '"+metervo.getMeterSerialNumber().trim()+"'");
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
				result = true;
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		
		return result;
	}

	public boolean checkHouseNumber(CustomerRequestVO customervo) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try{
		con = getConnection();
		
		pstmt = con.prepareStatement("SELECT * from customerdetails where HouseNumber = ? AND BlockID = " + customervo.getBlockID());
		pstmt.setString(1, customervo.getHouseNumber().trim());
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
				result = true;
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		
		return result;
	}
	
	public boolean checkCustomerUniqueID(String CustomerUniqueID) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try{
		con = getConnection();
		
		pstmt = con.prepareStatement("SELECT * from customerdetails where CustomerUniqueID = '" + CustomerUniqueID.trim() + "'");
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
				result = true;
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		
		return result;
	}
	
	public boolean checkMIUID(String miuID, long id) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try{
		con = getConnection();
		
		pstmt = con.prepareStatement("SELECT * from customermeterdetails where MIUID = '" + miuID.trim() + "'");
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			if(id == rs.getInt("CustomerMeterID")) {result = false;	} else if (id == 0) {result = true; } else {result = true; }
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		
		return result;
	}
	
	public boolean checkpendingrequest(String CustomerUniqueID) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try{
		con = getConnection();
		
		pstmt = con.prepareStatement("SELECT CustomerUniqueID from updaterequestcustomermeterdetails where CustomerUniqueID = ?");
		pstmt.setString(1, CustomerUniqueID);
		
		rs = pstmt.executeQuery();
        if (rs.next()) {
        	result = true;
        	}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		
		return result;
	}
	
	public ResponseVO customerDetailsfile(CustomerResponseVO customerResponseVO) {
		// TODO Auto-generated method stub
		
		ResponseVO responsevo = new ResponseVO();
		String drivename = "D:/CustomerList/";
		
		File directory = new File(drivename);
		if (!directory.exists()) {
			directory.mkdir();
		}
		
		try {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet spreadsheet = workbook.createSheet("Customers List");
		
		XSSFRow header = spreadsheet.createRow(0);
		
		int columnCount = 0;
        
        Cell headercell1 = header.createCell(columnCount);
        headercell1.setCellValue("Community Name");
        
        Cell headercell2 = header.createCell(++columnCount);
        headercell2.setCellValue("Block Name");
        
        Cell headercell3 = header.createCell(++columnCount);
        headercell3.setCellValue("CRN/CAN");
        
        Cell headercell4 = header.createCell(++columnCount);
        headercell4.setCellValue("Name");
        
        Cell headercell5 = header.createCell(++columnCount);
        headercell5.setCellValue("House Number");
        
        Cell headercell6 = header.createCell(++columnCount);
        headercell6.setCellValue("MIU ID");
        
        Cell headercell7 = header.createCell(++columnCount);
        headercell7.setCellValue("Meter Serial Number");
        
        Cell headercell8 = header.createCell(++columnCount);
        headercell8.setCellValue("Meter Type");
        
        Cell headercell9 = header.createCell(++columnCount);
        headercell9.setCellValue("Meter Size");
        
        Cell headercell10 = header.createCell(++columnCount);
        headercell10.setCellValue("Pay Type");
        
        Cell headercell11 = header.createCell(++columnCount);
        headercell11.setCellValue("Tariff Name");
        
        Cell headercell12 = header.createCell(++columnCount);
        headercell12.setCellValue("Gateway Name");
        
        Cell headercell13 = header.createCell(++columnCount);
        headercell13.setCellValue("Location");
        
        Cell headercell14 = header.createCell(++columnCount);
        headercell14.setCellValue("Mobile Number");
        
        Cell headercell15 = header.createCell(++columnCount);
        headercell15.setCellValue("Email");
                
        Cell headercell16 = header.createCell(++columnCount);
        headercell16.setCellValue("Created By UserName");
        
        Cell headercell17 = header.createCell(++columnCount);
        headercell17.setCellValue("Created By Role Description");
        
        Cell headercell18 = header.createCell(++columnCount);
        headercell18.setCellValue("Date");
        
		FileOutputStream outputStream = new FileOutputStream("D:\\CustomerList\\Customers.xlsx");
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        responsevo.setResult("Success");
		responsevo.setLocation(drivename);
		
		responsevo.setFileName("Customers.xlsx");
        
		return responsevo;
	}
	
/*Tariff*/
	
	public List<TariffResponseVO> getTariffdetails() throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TariffResponseVO> tariff_list = null;
		TariffResponseVO tariffvo = null;

		try {
			con = getConnection();
			tariff_list = new LinkedList<TariffResponseVO>();
			pstmt = con.prepareStatement("SELECT TariffID, Tariff, TariffName, EmergencyCredit, AlarmCredit, FixedCharges, ModifiedDate FROM tariff");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tariffvo = new TariffResponseVO();
				tariffvo.setTariffID(rs.getInt("TariffID"));
				tariffvo.setTariff(rs.getString("Tariff"));
				tariffvo.setTariffName(rs.getString("TariffName"));
				tariffvo.setEmergencyCredit(rs.getString("EmergencyCredit"));
				tariffvo.setAlarmCredit(rs.getString("AlarmCredit"));
				tariffvo.setFixedCharges(rs.getString("FixedCharges"));
				tariffvo.setModifiedDate(ExtraMethodsDAO.datetimeformatter(rs.getString("ModifiedDate")));
				tariff_list.add(tariffvo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return tariff_list;
	}

	public ResponseVO addtariff(TariffRequestVO tariffvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();
		try {
			con = getConnection();
			pstmt = con.prepareStatement("INSERT INTO tariff (Tariff, TariffName, EmergencyCredit, AlarmCredit, FixedCharges, RegisteredDate) VALUES(?, ?, ?, ?, ?, NOW())");
			pstmt.setFloat(1, tariffvo.getTariff());
			pstmt.setString(2, tariffvo.getTariffName());
			pstmt.setFloat(3, tariffvo.getEmergencyCredit());
			pstmt.setFloat(4, tariffvo.getAlarmCredit());
			pstmt.setFloat(5, tariffvo.getFixedCharges());

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Tariff Added Successfully");
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

	public ResponseVO edittariff(TariffRequestVO tariffvo) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO(); 

		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE tariff SET Tariff = ?, TariffName = ?, EmergencyCredit = ?, AlarmCredit = ?, FixedCharges = ?, ModifiedDate = NOW() WHERE TariffID = ?");
			pstmt.setFloat(1, tariffvo.getTariff());
			pstmt.setString(2, tariffvo.getTariffName());
			pstmt.setFloat(3, tariffvo.getEmergencyCredit());
			pstmt.setFloat(4, tariffvo.getAlarmCredit());
			pstmt.setFloat(5, tariffvo.getFixedCharges());
			pstmt.setInt(6, tariffvo.getTariffID());

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Tariff Updated Successfully");
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
	
	public ResponseVO deletetariff(int tariffID) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO(); 

		try {
			con = getConnection();
			pstmt = con.prepareStatement("DELETE FROM tariff WHERE TariffID ="+tariffID);

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Tariff Deleted Successfully");
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
	
	public boolean checktariffamount(TariffRequestVO tariffvo, String mode) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			
			String query = "SELECT * FROM tariff WHERE <change> Tariff = " + tariffvo.getTariff();
			pstmt = con.prepareStatement(query.replaceAll("<change>", (mode.equalsIgnoreCase("add")) ? "" : "TariffID != "+tariffvo.getTariffID() + " AND "));
			
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
	
	public boolean checktariffIsSetToCustomers(int tariffID) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM customermeterdetails WHERE TariffID = "+tariffID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				result = true;

			} 
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}

		return result;
	}

}
