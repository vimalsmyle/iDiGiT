/**
 * 
 */
package com.idigitronics.iDiGiT.dao;

import java.io.File;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.mail.MessagingException;

import com.idigitronics.iDiGiT.constants.DataBaseConstants;
import com.idigitronics.iDiGiT.constants.ExtraConstants;
import com.idigitronics.iDiGiT.exceptions.BusinessException;
import com.idigitronics.iDiGiT.request.vo.CommandGroupRequestVO;
import com.idigitronics.iDiGiT.request.vo.LoginVO;
import com.idigitronics.iDiGiT.request.vo.MailRequestVO;
import com.idigitronics.iDiGiT.request.vo.MeterRequestVO;
import com.idigitronics.iDiGiT.request.vo.UserManagementRequestVO;
import com.idigitronics.iDiGiT.response.vo.ResponseVO;
import com.idigitronics.iDiGiT.response.vo.UserDetails;
import com.idigitronics.iDiGiT.utils.Encryptor;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author K VimaL Kumar 
 * 
 */
public class LoginDAO {

	public Connection con = null;
	private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL, DataBaseConstants.USER_NAME,
				DataBaseConstants.PASSWORD);
		return connection;
	}

	public ResponseVO validateUser(LoginVO loginvo) throws ClassNotFoundException, BusinessException, SQLException {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet1 = null;
		ResponseVO responsevo = new ResponseVO();
		UserDetails userDetails = new UserDetails();
		MeterRequestVO metervo = null;
		List<MeterRequestVO> customer_meter_list = null;

		try {
			con = getConnection();
			
			File directory = new File("D:/Logs/IDigi");
			if (!directory.exists()) {
				directory.mkdirs();
			}
			
			pstmt = con.prepareStatement(
					"SELECT u.ID, u.UserID, u.UserName, u.UserPassword, u.RoleID, u.CommunityID, c.CommunityName, u.BlockID, u.CustomerID, u.CustomerUniqueID, b.BlockName, b.Email AS bemail, b.MobileNumber AS bmobile, cd.MobileNumber AS cmobile, cd.Email AS cemail, cd.HouseNumber FROM USER AS u LEFT JOIN community AS c ON c.CommunityID = u.CommunityID LEFT JOIN block AS b ON b.BlockID = u.BlockID LEFT JOIN customerdetails AS cd ON cd.CustomerUniqueID = u.CustomerUniqueID WHERE u.UserID = ? AND u.UserPassword = ?");
			pstmt.setString(1, loginvo.getUserID());
			pstmt.setString(2, loginvo.getPassword());
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {

				if (loginvo.getUserID().equals(resultSet.getString("UserID"))) {

					if (loginvo.getPassword().equals(resultSet.getString("UserPassword"))) {

//						if (loginvo.getSource().equalsIgnoreCase("web")) {
							
							if(resultSet.getInt("RoleID") == 6) {
								
								throw new BusinessException("USER NOT AUTHORIZED TO LOGIN");
							}

							userDetails.setRoleID(resultSet.getInt("RoleID"));
							userDetails.setBlockID(resultSet.getInt("BlockID"));
							userDetails.setEmail((userDetails.getRoleID() == 2 || userDetails.getRoleID() == 5) ? resultSet.getString("bemail") : (userDetails.getRoleID() == 3) ? resultSet.getString("cemail") : "");
							userDetails.setMobileNumber((userDetails.getRoleID() == 2 || userDetails.getRoleID() == 5) ? resultSet.getString("bmobile") : (userDetails.getRoleID() == 3) ? resultSet.getString("cmobile") : "");
							userDetails.setCustomerID(resultSet.getInt("CustomerID"));
							userDetails.setCustomerUniqueID(resultSet.getString("CustomerUniqueID"));
							userDetails.setuserName(resultSet.getString("UserName"));
							userDetails.setCommunity(resultSet.getInt("CommunityID"));
							userDetails.setCommunityName(resultSet.getString("CommunityName"));
							userDetails.setBlockName(resultSet.getString("BlockName"));
							userDetails.setHouseNo(resultSet.getString("HouseNumber"));
							userDetails.setID(resultSet.getInt("ID"));
							
							if(userDetails.getRoleID() == 3) {
								
								PreparedStatement pstmt4 = con.prepareStatement("SELECT DISTINCT cmd.MeterType FROM customerdetails AS cd LEFT JOIN customermeterdetails AS cmd ON cd.CustomerID = cmd.CustomerID WHERE cmd.MeterType IN ('Gas', 'Water', 'Energy') AND cd.CustomerID = "+userDetails.getCustomerID());
								ResultSet rs4 = pstmt4.executeQuery();
								
								while(rs4.next()) {
									
										if(rs4.getString("MeterType").equalsIgnoreCase("Gas")) {
											userDetails.setGas(true);
										} 
										if (rs4.getString("MeterType").equalsIgnoreCase("Water")) {
											userDetails.setWater(true);
										}
										if (rs4.getString("MeterType").equalsIgnoreCase("Energy")) {
											userDetails.setEnergy(true);
										}
								}
								
							}
							
							responsevo.setUserDetails(userDetails);
							responsevo.setResult("Success");
							responsevo.setMessage("Successfully Logged In");
							
							if(loginvo.getSource().equalsIgnoreCase("mobile")) {
								
								String securityKey = generateNewToken();
								
								PreparedStatement pstmt3 = con.prepareStatement("UPDATE user SET Token = '"+ securityKey + "' WHERE ID = "+userDetails.getID());
								
								if (pstmt3.executeUpdate() > 0) {
									responsevo.setToken(securityKey);
								}
								
//								responsevo.setToken(generateJwtToken(loginvo.getUserID(), ExtraConstants.JWTKey, 86400000));
								
							}

						/*} else {

							if (resultSet.getInt("RoleID") == 3 && loginvo.getSource().equalsIgnoreCase("mobile")) {
								
								userDetails.setRoleID(resultSet.getInt("RoleID"));
								userDetails.setBlockID(resultSet.getInt("BlockID"));
								userDetails.setEmail((userDetails.getRoleID() == 2 || userDetails.getRoleID() == 5)
										? resultSet.getString("bemail")
										: (userDetails.getRoleID() == 3) ? resultSet.getString("cemail") : "");
								userDetails
										.setMobileNumber((userDetails.getRoleID() == 2 || userDetails.getRoleID() == 5)
												? resultSet.getString("bmobile")
												: (userDetails.getRoleID() == 3) ? resultSet.getString("cmobile") : "");
								userDetails.setCustomerID(resultSet.getInt("CustomerID"));
								userDetails.setCustomerUniqueID(resultSet.getString("CustomerUniqueID"));
								userDetails.setuserName(resultSet.getString("UserName"));
								userDetails.setCommunity(resultSet.getInt("CommunityID"));
								userDetails.setCommunityName(resultSet.getString("CommunityName"));
								userDetails.setBlockName(resultSet.getString("BlockName"));
								
								customer_meter_list = new LinkedList<MeterRequestVO>();
								
								PreparedStatement pstmt2 = con.prepareStatement("SELECT * FROM customermeterdetails WHERE CustomerUniqueID = '" + userDetails.getCustomerUniqueID().trim() + "'");
								
								ResultSet rs2 = pstmt2.executeQuery();
								
								while (rs2.next()) {
									
									metervo = new MeterRequestVO();
									
									metervo.setMiuID(rs2.getString("MIUID"));
									metervo.setCustomerMeterID(rs2.getInt("CustomerMeterID"));
									metervo.setMeterSerialNumber(rs2.getString("MeterSerialNumber"));
									metervo.setMeterType(rs2.getString("MeterType"));
									metervo.setTariffID(rs2.getInt("TariffID"));
									metervo.setGatewayID(rs2.getInt("GatewayID"));
									
									PreparedStatement pstmt3 = con.prepareStatement("SELECT TariffName from tariff WHERE TariffID = "+ metervo.getTariffID());
									
									ResultSet rs3 = pstmt3.executeQuery();
									
									if(rs3.next()) {
										
										metervo.setTariffName(rs3.getString("TariffName"));
										
									}
									
									customer_meter_list.add(metervo);
								}
								
								userDetails.setMeters(customer_meter_list);
								
//								CommandGroupRequestVO commandGroupRequestVO = null;
//								List<CommandGroupRequestVO> pendingCommands = new LinkedList<>();
								
								userDetails.setID(resultSet.getInt("ID"));
								if (userDetails.getCustomerUniqueID() != null) {
									pstmt1 = con.prepareStatement("SELECT cd.TransactionID, cd.CommandType, cd.Value from commanddetails AS cd LEFT JOIN command AS c ON c.TransactionID = cd.TransactionID WHERE c.CustomerUniqueID = ? and cd.Status = 10");
									pstmt1.setString(1, userDetails.getCustomerUniqueID());
									resultSet1 = pstmt1.executeQuery();
									while (resultSet1.next()) {
										
										commandGroupRequestVO = new CommandGroupRequestVO();
										commandGroupRequestVO.setParameter_id(resultSet1.getInt("CommandType"));
										commandGroupRequestVO.setValue(resultSet1.getString("Value"));
										
										pendingCommands.add(commandGroupRequestVO);
										
										// check for multiple transactionID after data insertion
										
										userDetails.setPendingTransactionID(resultSet1.getInt("TransactionID"));

									}
								}

								responsevo.setUserDetails(userDetails);
								responsevo.setResult("Success");
								responsevo.setMessage("Successfully Logged In");
								
							} else if (resultSet.getInt("RoleID") == 1 && loginvo.getSource().equalsIgnoreCase("mobile")) {
								
								responsevo.setTransactedByID(resultSet.getInt("ID"));
								responsevo.setResult("Success");
								responsevo.setMessage("Successfully Logged In");
							
							} else if (resultSet.getInt("RoleID") == 2 && loginvo.getSource().equalsIgnoreCase("mobile")) {
								
								responsevo.setTransactedByID(resultSet.getInt("ID"));
								responsevo.setResult("Success");
								responsevo.setMessage("Successfully Logged In");
							
							} else {
								throw new BusinessException("USER NOT AUTHORIZED TO LOGIN");
								
							}

						}*/

					} else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Incorrect Password");
					}

				} else {
					responsevo.setResult("Failure");
					responsevo.setMessage("Invalid UserID");
				}
			} else {
				responsevo.setResult("Failure");
				responsevo.setMessage("Invalid Credentials");
			}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			pstmt.close();
			resultSet.close();
			con.close();

		}

		return responsevo;
	}

	public ResponseVO forgotpassword(String userid) throws SQLException, MessagingException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;

		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			ExtraMethodsDAO maildao = new ExtraMethodsDAO();
			MailRequestVO mailrequestvo = new MailRequestVO();
			mailrequestvo.setFileLocation("NoAttachment");
			pstmt = con.prepareStatement(
					"SELECT CustomerID, CustomerUniqueID, UserPassword, CommunityID, BlockID FROM user WHERE UserID = ?");
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				if (rs.getInt("CommunityID") != 0 && rs.getInt("BlockID") != 0) {

					if (rs.getInt("CustomerID") != 0) {

						pstmt1 = con.prepareStatement("SELECT Email FROM customerdetails WHERE CustomerID=?");
						pstmt1.setLong(1, rs.getLong("CustomerID"));

					} else {
						pstmt1 = con.prepareStatement("SELECT Email FROM block WHERE BlockID=?");
						pstmt1.setInt(1, rs.getInt("BlockID"));

					}
					rs1 = pstmt1.executeQuery();

					if (rs1.next()) {
						mailrequestvo.setToEmail(rs1.getString("Email"));
					}
				} else {
					mailrequestvo.setToEmail(ExtraConstants.fromEmail);
				}

				mailrequestvo.setUserID(userid);
				mailrequestvo.setUserPassword(
						Encryptor.decrypt(ExtraConstants.key1, ExtraConstants.key2, rs.getString("UserPassword")));
				responsevo.setResult(maildao.sendmail(mailrequestvo));

			} else {
				responsevo.setMessage("UserID is not Registered");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// pstmt.close();
			// ps.close();
			// rs.close();
			con.close();
		}

		return responsevo;

	}

	public ResponseVO changepassword(UserManagementRequestVO usermanagementvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();
		
		try {
			con = getConnection();

			pstmt = con.prepareStatement("UPDATE user SET UserPassword = ?, ModifiedDate = NOW() where UserID = ?");
			pstmt.setString(1, usermanagementvo.getNewPassword());
			pstmt.setString(2, usermanagementvo.getUserID().trim());

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Password Updated Successfully");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setResult("Failure");
			responsevo.setMessage("Password Updation Failed");
		} finally {
			pstmt.close();
			con.close();
		}

		return responsevo;
	}

	public boolean checkuserid(String userid) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			con = getConnection();

			pstmt = con.prepareStatement("SELECT UserID FROM user where UserID = ?");
			pstmt.setString(1, userid.trim());
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

	public boolean checkoldpassword(UserManagementRequestVO usermanagementvo) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			con = getConnection();

			pstmt = con.prepareStatement("SELECT UserPassword FROM user where UserID = ?");
			pstmt.setString(1, usermanagementvo.getUserID());
			rs = pstmt.executeQuery();
			if (rs.next()) {

				result = usermanagementvo.getOldPassword().toLowerCase().equalsIgnoreCase(rs.getString("UserPassword").toLowerCase());
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
	
	public static String generateNewToken() {
	    byte[] randomBytes = new byte[8];
	    secureRandom.nextBytes(randomBytes);
	    return base64Encoder.encodeToString(randomBytes);
	}
	
/*    public static String generateJwtToken(String subject, String secretKey, long expirationMillis) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMillis);
        
//        String subject = "user123";
//        String secretKey = "yourSecretKey"; // Replace with your own secret key
//        long expirationMillis = 3600000; // Token will expire in 1 hour
//
//        String jwtToken = generateJwtToken(subject, secretKey, expirationMillis);
//        System.out.println("Generated JWT: " + jwtToken);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }*/

}
