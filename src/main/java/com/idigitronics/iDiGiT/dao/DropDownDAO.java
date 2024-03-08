/**
 * 
 */
package com.idigitronics.iDiGiT.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import com.idigitronics.iDiGiT.constants.DataBaseConstants;
import com.idigitronics.iDiGiT.request.vo.LoginVO;
import com.idigitronics.iDiGiT.request.vo.Status;
import com.idigitronics.iDiGiT.response.vo.BillDetailsResponseVO;
import com.idigitronics.iDiGiT.response.vo.DashboardResponseVO;
import com.idigitronics.iDiGiT.response.vo.IndividualDashboardResponseVO;
import com.idigitronics.iDiGiT.response.vo.LastReadingResponseVO;
import com.idigitronics.iDiGiT.response.vo.TopupDetailsResponseVO;

/**
 * @author K VimaL Kumar
 *
 */
public class DropDownDAO {

	static LoginVO loginvo = new LoginVO();

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}
	
	public HashMap<Integer, String> getallcommunities(int roleid, String id) {
		// TODO Auto-generated method stub
		
		HashMap<Integer, String> communities = new HashMap<Integer, String>(); 
		Connection con = null;
		try {
			con = getConnection();
			

			String query = "SELECT c.CommunityID, c.CommunityName FROM community AS c <change> ";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid==2 || roleid==5) ? "LEFT JOIN block AS b ON b.CommunityID = c.CommunityID WHERE b.BlockID = "+id : (roleid == 3) ? "LEFT JOIN customerdetails AS cd ON cd.CommunityID = c.CommunityID WHERE cd.CustomerUniqueID = '"+id+"'": "ORDER BY c.CommunityID DESC"));
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				communities.put(rs.getInt("CommunityID"), rs.getString("CommunityName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return communities;
	}

	public HashMap<Integer, String> getallblocks(int communityID, int roleid, String id) {
		// TODO Auto-generated method stub

		HashMap<Integer, String> blocks = new HashMap<Integer, String>(); 
		Connection con = null;

		try {
			con = getConnection();
			String query = "SELECT BlockID, BlockName FROM block WHERE CommunityID=? <change>";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY BlockID ASC" : (roleid == 2 || roleid == 5) ? "AND BlockID = "+id+ " ORDER BY BlockID ASC" : (roleid == 3) ? "AND BlockID = (SELECT BlockID FROM customerdetails WHERE CustomerUniqueID = '"+id+"')":""));
			pstmt.setInt(1, communityID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				blocks.put(rs.getInt("BlockID"), rs.getString("BlockName"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return blocks;
	}

	public HashMap<Long, String> getallcustomers(int blockID, int roleid, String id) {
		// TODO Auto-generated method stub
		HashMap<Long, String> houses = new HashMap<Long, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			String query = "SELECT CustomerUniqueID, CustomerID from customerdetails WHERE BlockID = ? <change>";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 2 || roleid == 4 || roleid == 5) ? "ORDER BY CustomerID ASC" : (roleid == 3) ? " AND CustomerUniqueID = '"+id+"'" :""));
			pstmt.setInt(1, blockID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				houses.put(rs.getLong("CustomerID"), rs.getString("CustomerUniqueID"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return houses;
	}
	
	public HashMap<Long, String> gettotalcustomers() {
		// TODO Auto-generated method stub
		HashMap<Long, String> customers = new HashMap<Long, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT CustomerUniqueID, CustomerID from customerdetails");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				customers.put(rs.getLong("CustomerID"), rs.getString("CustomerUniqueID"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return customers;
	}
	
	public HashMap<Long, String> getallcustomersbasedontype(String type, int blockID, int roleid, String id) {
		// TODO Auto-generated method stub
		Connection con = null;
		HashMap<Long, String> customers = new HashMap<Long, String>();
		
		try {
			con = getConnection();
			String query = "SELECT CustomerUniqueID, CustomerID from customerdetails WHERE BlockID = ? AND CustomerID IN (SELECT CustomerID FROM customermeterdetails WHERE PayType = '"+type+"')  <change>";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 2 || roleid == 4 || roleid == 5) ? "ORDER BY CustomerID ASC" : (roleid == 3) ? " AND CustomerUniqueID = '"+id+"'" :""));
			pstmt.setInt(1, blockID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				customers.put(rs.getLong("CustomerID"), rs.getString("CustomerUniqueID"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return customers;
	}
	
	
	public TopupDetailsResponseVO gettopupdetails(String CustomerUniqueID, Long customerMeterID) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TopupDetailsResponseVO topupdetailsresponsevo = new TopupDetailsResponseVO();
		
		try{
			con = getConnection();
			
			topupdetailsresponsevo.setReconnectionCharges(0);
			LocalDateTime dateTime = LocalDateTime.now();

					ps = con.prepareStatement("SELECT cmd.MIUID, cmd.MeterSerialNumber, cmd.CustomerMeterID, t.TariffID, t.TariffName, t.Tariff, t.EmergencyCredit, t.AlarmCredit, t.FixedCharges FROM customermeterdetails AS cmd LEFT JOIN tariff AS t ON t.TariffID = cmd.TariffID WHERE cmd.CustomerMeterID = ?");
			        ps.setLong(1, customerMeterID);
			        rs = ps.executeQuery();
			        if (rs.next()) {
			        	topupdetailsresponsevo.setMiuID(rs.getString("MIUID"));
			        	topupdetailsresponsevo.setMeterSerialNumber(rs.getString("MeterSerialNumber"));
			        	topupdetailsresponsevo.setAlarmCredit(rs.getFloat("AlarmCredit"));
			        	topupdetailsresponsevo.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
			        	topupdetailsresponsevo.setTariffName(rs.getString("TariffName"));
			        	topupdetailsresponsevo.setTariff(rs.getFloat("Tariff"));
			        	topupdetailsresponsevo.setTariffID(rs.getInt("TariffID"));
			        	topupdetailsresponsevo.setFixedCharges(rs.getInt("FixedCharges"));
			        	topupdetailsresponsevo.setCustomerMeterID(rs.getInt("CustomerMeterID"));
			        	topupdetailsresponsevo.setNoOfMonths(1);
			                    
			                    pstmt = con.prepareStatement("SELECT dbl.LogDate, dbl.Balance, al.ReconnectionCharges, dbl.Minutes FROM displaybalanceLog AS dbl JOIN alertsettings AS al WHERE dbl.CustomerUniqueID = ? AND dbl.CustomerMeterID =" +customerMeterID);
			                    pstmt.setString(1, CustomerUniqueID);
			                    ResultSet rs1 = pstmt.executeQuery();
			                    if (rs1.next()) {
		                        	topupdetailsresponsevo.setCurrentBalance(rs1.getFloat("Balance"));
		                        	topupdetailsresponsevo.setReconnectionCharges(rs1.getInt("Minutes") != 0 ? rs1.getInt("ReconnectionCharges") : 0);
		                        	topupdetailsresponsevo.setNoOfMonths(0);
		                        	
		        					PreparedStatement pstmt2 = con.prepareStatement("SELECT MONTH(TransactionDate) AS previoustopupmonth from topup WHERE Status = 0 AND CustomerUniqueID = '"+CustomerUniqueID+"' AND CustomerMeterID = " + customerMeterID + " ORDER BY TransactionID DESC LIMIT 0,1");
		        					ResultSet rs2 = pstmt2.executeQuery();
		        					
		        					if(rs2.next()) {
		        						topupdetailsresponsevo.setNoOfMonths(dateTime.getMonthValue() - rs2.getInt("previoustopupmonth"));
		        						topupdetailsresponsevo.setFixedCharges(rs2.getInt("previoustopupmonth") != dateTime.getMonthValue() ? (rs.getInt("FixedCharges") * (dateTime.getMonthValue() - rs2.getInt("previoustopupmonth"))) : 0);
		        					}

			                    	} else {
			        					
			                        	topupdetailsresponsevo.setCurrentBalance(0);
			                        	topupdetailsresponsevo.setReconnectionCharges(0);
			                        }
			            }
					
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			pstmt.close();
	//		ps.close();
			rs.close();
			con.close();
		}
		
		return topupdetailsresponsevo;
	}
	
	public BillDetailsResponseVO getbilldetails(String customerUniqueID) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		BillDetailsResponseVO billDetailsResponseVO = new BillDetailsResponseVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LocalDate currentdate = LocalDate.now();
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT CustomerBillingID, TotalAmount, TotalConsumption, TaxAmount, PreviousDues, DueDate, DATEDIFF(NOW(),DueDate) AS DueDays, BillMonth, BillYear, LogDate, LateFee, DueDayCount FROM customerbillingdetails JOIN alertsettings WHERE CustomerUniqueID = '" + customerUniqueID + "' AND BillMonth = "+ ((currentdate.getMonthValue() - 1) == 0 ? 12 : (currentdate.getMonthValue() - 1)) + " AND BillYear = " + (currentdate.getMonthValue() == 1 ? currentdate.getYear() - 1 : currentdate.getYear()));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				billDetailsResponseVO.setCustomerBillingID(rs.getLong("CustomerBillingID"));
				billDetailsResponseVO.setTotalAmount(rs.getFloat("TotalAmount") + rs.getFloat("TaxAmount") + rs.getFloat("PreviousDues") + (rs.getInt("DueDays") >= 1 ? (rs.getInt("LateFee")*rs.getInt("DueDays")) : 0));
				billDetailsResponseVO.setTotalConsumption(rs.getInt("TotalConsumption"));
				billDetailsResponseVO.setLateFee(rs.getInt("DueDays") >= 1 ? (rs.getInt("LateFee")*rs.getInt("DueDays")) : 0);
				billDetailsResponseVO.setBillAmount(rs.getFloat("TotalAmount"));
				billDetailsResponseVO.setTaxAmount(rs.getFloat("TaxAmount"));
				billDetailsResponseVO.setPreviousDues(rs.getFloat("PreviousDues"));
				billDetailsResponseVO.setDueDate(rs.getString("DueDate"));
				billDetailsResponseVO.setBillMonth(rs.getInt("BillMonth") == 1 ? "January" : rs.getInt("BillMonth") == 2 ? "February" : rs.getInt("BillMonth") == 3 ? "March" : rs.getInt("BillMonth") == 4 ? "April" : rs.getInt("BillMonth") == 5 ? "May" : rs.getInt("BillMonth") == 6 ? "June" : rs.getInt("BillMonth") == 7 ? "July" : rs.getInt("BillMonth") == 8 ? "August" : rs.getInt("BillMonth") == 9 ? "September" : rs.getInt("BillMonth") == 10 ? "October" : rs.getInt("BillMonth") == 11 ? "November" : rs.getInt("BillMonth") == 12 ? "December" : "");
				billDetailsResponseVO.setBillYear(rs.getInt("BillYear"));
				billDetailsResponseVO.setBillingDate(rs.getString("LogDate"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		 finally {
			 	pstmt.close();
				rs.close();
				con.close();
			}
		
		return billDetailsResponseVO;
	}

	public HashMap<Integer, String> getalltariffs() throws SQLException {
		// TODO Auto-generated method stub
	
		HashMap<Integer, String> tariffs = new HashMap<Integer, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT TariffID, TariffName FROM tariff");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				tariffs.put(rs.getInt("TariffID"), rs.getString("TariffName"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			con.close();
			
		}
		return tariffs;
	}

	public HashMap<Integer, String> getallgateways() throws SQLException {
		// TODO Auto-generated method stub
	
		HashMap<Integer, String> gateways = new HashMap<Integer, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT GatewayID, GatewayName FROM gateway");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				gateways.put(rs.getInt("GatewayID"), rs.getString("GatewayName"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			con.close();
			
		}
		return gateways;
	}

	public HashMap<Long, String> getcustomermeters(String customerUniqueID, String payType) throws SQLException {
		// TODO Auto-generated method stub
		
		HashMap<Long, String> customermeters = new HashMap<Long, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			String query = "SELECT CustomerMeterID, MIUID FROM customermeterdetails WHERE CustomerUniqueID = '"+ customerUniqueID.trim() + "' <change>";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", payType.equalsIgnoreCase("Prepaid") ? "AND PayType = 'Prepaid' " : "AND PayType = 'Postpaid' "));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				customermeters.put(rs.getLong("CustomerMeterID"), rs.getString("MIUID"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			con.close();
			
		}
		return customermeters;
	}

	public HashMap<Long, String> getallcustomermeters(String customerUniqueID) throws SQLException {
		// TODO Auto-generated method stub
		
		HashMap<Long, String> customermeters = new HashMap<Long, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT CustomerMeterID, MIUID FROM customermeterdetails WHERE CustomerUniqueID = '"+ customerUniqueID.trim() + "'");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				customermeters.put(rs.getLong("CustomerMeterID"), rs.getString("MIUID"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			con.close();
			
		}
		return customermeters;
	}
	
	public HashMap<Integer, Integer> getallmetersizes(String type) throws SQLException {
		// TODO Auto-generated method stub
		HashMap<Integer, Integer> metersizes = new HashMap<Integer, Integer>();
		
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT MeterSizeID, MeterSize FROM metersize WHERE MeterType = '"+type+"'");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				metersizes.put(rs.getInt("MeterSizeID"), rs.getInt("MeterSize"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			con.close();
			
		}
		return metersizes;
	}

	public HashMap<Integer, String> getalltariffsAmount() throws SQLException {
		HashMap<Integer, String> tariffs = new HashMap<Integer, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT TariffID, TariffName FROM tariff");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				tariffs.put(rs.getInt("Tariff"), rs.getString("TariffName"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			con.close();
			
		}
		return tariffs;
	}

	public LastReadingResponseVO getLastReading(Long customerMeterID) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		LastReadingResponseVO lastReadingResponseVO = new LastReadingResponseVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM balancelog WHERE CustomerMeterID = "+customerMeterID+" ORDER BY ReadingID DESC LIMIT 0,1");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				lastReadingResponseVO.setReadingID(rs.getLong("ReadingID"));
				lastReadingResponseVO.setMiuID(rs.getString("MIUID"));
				lastReadingResponseVO.setType(rs.getString("MeterType").equalsIgnoreCase("Water") ? 1 : rs.getString("MeterType").equalsIgnoreCase("Gas") ? 2 : rs.getString("MeterType").equalsIgnoreCase("Energy") ? 3 : 0);
				lastReadingResponseVO.setSync_time(rs.getString("SyncTime"));
				lastReadingResponseVO.setSync_interval(rs.getInt("SyncInterval"));
				lastReadingResponseVO.setPre_post_paid(rs.getString("PayType").equalsIgnoreCase("Postpaid") ? 0 : 1);
				lastReadingResponseVO.setBat_volt(rs.getFloat("BatteryVoltage"));
				lastReadingResponseVO.setValve_configuration(rs.getInt("ValveConfiguration"));
				lastReadingResponseVO.setValve_live_status(rs.getInt("ValveStatus"));
				lastReadingResponseVO.setCredit(rs.getFloat("Balance"));
				lastReadingResponseVO.setTariff(rs.getFloat("Tariff"));
				lastReadingResponseVO.setEmergency_credit(rs.getLong("EmergencyCredit"));
				lastReadingResponseVO.setDays_elapsed_after_valve_trip(rs.getInt("Minutes"));
				lastReadingResponseVO.setReading(rs.getFloat("Reading"));
				
				Status status = new Status();
				
				status.setDoor_open(rs.getInt("DoorOpenTamper"));
				status.setLow_bal(rs.getInt("LowBalance"));
				status.setLow_bat(rs.getInt("LowBattery"));
				status.setMagnetic(rs.getInt("MagneticTamper"));
				status.setNfc_tamper(rs.getInt("NFCTamper"));
				status.setRtc_fault(rs.getInt("RTCFault"));
				status.setSchedule_disconnect(rs.getInt("Vacation"));
				
				lastReadingResponseVO.setStatus(status);
				lastReadingResponseVO.setLogDate(ExtraMethodsDAO.datetimeformatter(rs.getString("LogDate")));
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
			 	pstmt.close();
//				rs.close();
				con.close();
			}
		
		return lastReadingResponseVO;
	}

}
