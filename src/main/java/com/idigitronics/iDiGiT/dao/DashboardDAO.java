/**
 * 
 */
package com.idigitronics.iDiGiT.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.idigitronics.iDiGiT.constants.DataBaseConstants;
import com.idigitronics.iDiGiT.request.vo.DataRequestVO;
import com.idigitronics.iDiGiT.request.vo.FilterVO;
import com.idigitronics.iDiGiT.request.vo.MailRequestVO;
import com.idigitronics.iDiGiT.request.vo.SMSRequestVO;
import com.idigitronics.iDiGiT.request.vo.SensorDataRequestVO;
import com.idigitronics.iDiGiT.response.vo.AllGraphResponseVO;
import com.idigitronics.iDiGiT.response.vo.DashboardResponseVO;
import com.idigitronics.iDiGiT.response.vo.GraphResponseVO;
import com.idigitronics.iDiGiT.response.vo.HomeResponseVO;
import com.idigitronics.iDiGiT.response.vo.IndividualDashboardResponseVO;
import com.idigitronics.iDiGiT.response.vo.ResponseVO;
import com.idigitronics.iDiGiT.response.vo.SensorDashboardResponseVO;
import com.idigitronics.iDiGiT.response.vo.Series;
import com.idigitronics.iDiGiT.response.vo.ValidateResponseVO;

/*import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;*/


/**
 * @author k VimaL Kumar
 * 
 */
public class DashboardDAO {
	
	private static final Logger logger = Logger.getLogger(DashboardDAO.class);
//	 private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}

	public List<DashboardResponseVO> getDashboarddetails(String type, String communityName, String blockName, String customerUniqueID, int filter, FilterVO filtervo)
			throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs3 = null;
		List<DashboardResponseVO> dashboard_list = null;
		List<IndividualDashboardResponseVO> individualDashboardList = null;
		IndividualDashboardResponseVO individualDashboardResponseVO = null;
		DashboardResponseVO dashboardvo = null;
		int noAMRInterval = 0;
		int communityID = 0;
		int blockID = 0;
		String mainquery = "";
		
		try {
			con = getConnection();
			dashboard_list = new LinkedList<DashboardResponseVO>();
			int nonCommunicating = 0;
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
			}
			
			if(communityName.equalsIgnoreCase("0")) {
				
				mainquery = "SELECT c.CommunityName, b.BlockName, cd.HouseNumber, cd.FirstName, cd.LastName, cd.CustomerUniqueID, cd.CustomerID FROM customerdetails AS cd LEFT JOIN community AS c ON cd.CommunityID = c.CommunityID LEFT JOIN block AS b ON b.BlockID = cd.BlockID";
				
			} else {
				
				String IDquery = "SELECT * FROM <tablename>";
				PreparedStatement pstmt4 = con.prepareStatement(IDquery.replaceAll("<tablename>", (!blockName.equalsIgnoreCase("0") ? "block WHERE BlockName = '"+blockName+"' AND CommunityID = (SELECT CommunityID FROM community WHERE CommunityName = '"+communityName+"')" : "community WHERE CommunityName = '"+communityName+"'")));
				ResultSet rs4 = pstmt4.executeQuery();
				if(rs4.next()) {
					blockID = (!blockName.equalsIgnoreCase("0") ? rs4.getInt("BlockID") : 0);
					communityID = rs4.getInt("CommunityID");
				}
				
				mainquery = "SELECT c.CommunityName, b.BlockName, cd.HouseNumber, cd.FirstName, cd.LastName, cd.CustomerUniqueID, cd.CustomerID FROM customerdetails AS cd LEFT JOIN community AS c ON cd.CommunityID = c.CommunityID LEFT JOIN block AS b ON b.BlockID = cd.BlockID <main>";
				
				mainquery = mainquery.replaceAll("<main>", (blockID == 0 && customerUniqueID.equalsIgnoreCase("0")) ?"WHERE cd.CommunityID = "+communityID : (blockID !=0 && customerUniqueID.equalsIgnoreCase("0")) ? "WHERE cd.CommunityID = "+communityID +" AND cd.BlockID = "+blockID : (blockID !=0 && !customerUniqueID.equalsIgnoreCase("0")) ? "WHERE cd.CommunityID = "+communityID +" AND cd.BlockID = "+blockID + " AND cd.CustomerUniqueID = '" + customerUniqueID +"'" : "");
				
			}
			
			pstmt = con.prepareStatement(mainquery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				dashboardvo = new DashboardResponseVO();
				individualDashboardList = new LinkedList<IndividualDashboardResponseVO>();
				
				dashboardvo.setCommunityName(rs.getString("CommunityName"));
				dashboardvo.setBlockName(rs.getString("BlockName"));
				dashboardvo.setHouseNumber(rs.getString("HouseNumber"));
				dashboardvo.setFirstName(rs.getString("FirstName"));
				dashboardvo.setLastName(rs.getString("LastName"));
				dashboardvo.setCustomerUniqueID(rs.getString("CustomerUniqueID"));
				
				String query = "SELECT dbl.ReadingID, dbl.MainBalanceLogID, dbl.CustomerMeterID, dbl.MIUID, cmd.MeterSerialNumber, cmd.PayType, cmd.MeterType, ms.MeterSize, ms.PerUnitValue, g.GatewayName, dbl.Tariff, dbl.Reading, dbl.Balance, dbl.EmergencyCredit, dbl.ValveStatus, dbl.BatteryVoltage, "
						+ "dbl.LowBattery, dbl.DoorOpenTamper, dbl.MagneticTamper, dbl.RTCFault, dbl.Vacation, dbl.LowBalance, dbl.NFCTamper, dbl.LogDate FROM displaybalancelog AS dbl LEFT JOIN customermeterdetails AS cmd ON cmd.CustomerMeterID = dbl.CustomerMeterID LEFT JOIN metersize AS ms ON ms.MeterSizeID = cmd.MeterSizeID LEFT JOIN gateway AS g ON g.GatewayID = cmd.GatewayID WHERE dbl.CustomerID = ? AND cmd.MeterType = '" + type +"'";
				
				StringBuilder stringBuilder = new StringBuilder(query);
				if(filter != 0) {
					
//					1 = valve open(active), 2 = valve close(inactive) 3 = communicating(live), 4 = non-communicating(non-live) 5 = low battery 6 = emergency credit
					
					stringBuilder.append((filter == 1 || filter == 2) ? " AND dbl.ValveStatus = "+ (filter == 1 ? 1 : 0) : (filter == 3 || filter == 4) ? (filter == 3 ? " AND dbl.LogDate >= (NOW() - INTERVAL (SELECT NoAMRInterval/(24*60) FROM alertsettings) DAY) " : " AND dbl.LogDate <= (NOW() - INTERVAL (SELECT NoAMRInterval/(24*60) FROM alertsettings) DAY) " ) :  (filter == 5) ? " AND dbl.LowBattery = "+ 1: (filter == 6) ? " AND dbl.Balance <= 0" : "");
					
				}
				
				if(filtervo != null) {
					LocalDateTime dateTime = LocalDateTime.now();  
				    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
					
					if(!filtervo.getDateFrom().equalsIgnoreCase("null") || !filtervo.getDateTo().equalsIgnoreCase("null")) {
						stringBuilder.append(" AND dbl.LogDate BETWEEN '" + filtervo.getDateFrom() + "' AND '" + (filtervo.getDateTo() != null ? filtervo.getDateTo()+"'" : "'"+dateTime.format(dateTimeFormat)+"'"));
					}
					if(filtervo.getReadingFrom() != 0 || filtervo.getReadingTo() != 0) {
						stringBuilder.append(" AND dbl.Reading BETWEEN " + (filtervo.getReadingFrom() != 0 ? filtervo.getReadingFrom() : 0) + " AND " + (filtervo.getReadingTo() != 0 ? filtervo.getReadingTo() : 9999999));
					}
					if(filtervo.getBatteryVoltageFrom() != 0 || filtervo.getBatteryVoltageTo() != 0) {
						stringBuilder.append(" AND dbl.BatteryVoltage BETWEEN " + (filtervo.getBatteryVoltageFrom() != 0 ? (filtervo.getBatteryVoltageFrom()) : 0) + " AND " + (filtervo.getBatteryVoltageTo() != 0 ? (filtervo.getBatteryVoltageTo()) : 100));
					}
					if(filtervo.getTamperType() > 0) {
						stringBuilder.append(filtervo.getTamperType() == 1 ? " AND dbl.DoorOpenTamper = 1" : filtervo.getTamperType() == 2 ? " AND dbl.MagneticTamper = 1" : filtervo.getTamperType() == 3 ? " AND dbl.DoorOpenTamper = 1 AND dbl.MagneticTamper = 1" : filtervo.getTamperType() == 4 ? " AND dbl.NFCTamper = 1" : " ");
					}
				}
				
				stringBuilder.append(" ORDER BY dbl.LogDate DESC");
				pstmt3 = con.prepareStatement(stringBuilder.toString());
				pstmt3.setInt(1, rs.getInt("CustomerID"));
				rs3 = pstmt3.executeQuery();
				
				while(rs3.next()) {
					
					individualDashboardResponseVO = new IndividualDashboardResponseVO();
					
					individualDashboardResponseVO.setMeterSerialNumber(rs3.getString("MeterSerialNumber"));
					individualDashboardResponseVO.setPayType(rs3.getString("PayType"));
					individualDashboardResponseVO.setMeterType(rs3.getString("MeterType"));
					individualDashboardResponseVO.setMiuID(rs3.getString("MIUID"));
					individualDashboardResponseVO.setCustomerMeterID(rs3.getInt("CustomerMeterID"));
					individualDashboardResponseVO.setMeterSize(rs3.getFloat("MeterSize"));
					individualDashboardResponseVO.setGatewayName(rs3.getString("GatewayName"));
					individualDashboardResponseVO.setReading(rs3.getFloat("Reading"));
					individualDashboardResponseVO.setConsumption((int) (individualDashboardResponseVO.getReading() * rs3.getFloat("PerUnitValue")));
					individualDashboardResponseVO.setBattery(rs3.getInt("BatteryVoltage"));
					individualDashboardResponseVO.setBatteryColor((rs3.getInt("LowBattery") == 1 ) ? "RED" : "GREEN");
					individualDashboardResponseVO.setDoorOpenTamper((rs3.getInt("DoorOpenTamper") == 0) ? "NO" : (rs3.getInt("DoorOpenTamper") == 1) ? "YES" : "NO");
					individualDashboardResponseVO.setDooropentamperColor((rs3.getInt("DoorOpenTamper") == 0) ? "GREEN" : "RED");
					individualDashboardResponseVO.setMagneticTamper((rs3.getInt("MagneticTamper") == 0) ? "NO" : (rs3.getInt("MagneticTamper") == 1) ? "YES" : "NO");
					individualDashboardResponseVO.setMagnetictamperColor((rs3.getInt("MagneticTamper") == 0) ? "GREEN" : "RED");
					individualDashboardResponseVO.setNfcTamper((rs3.getInt("NFCTamper") == 0 || rs3.getString("NFCTamper").equalsIgnoreCase("NULL")) ? "NO" : (rs3.getInt("NFCTamper") == 1) ? "YES" : "NO");
					individualDashboardResponseVO.setNfcTamperColor((rs3.getInt("NFCTamper") == 0 || rs3.getString("NFCTamper").equalsIgnoreCase("NULL")) ? "GREEN" : "RED");
					individualDashboardResponseVO.setTariff((rs3.getString("Tariff").equalsIgnoreCase("0.00") ? "---" : rs3.getString("Tariff")));
					individualDashboardResponseVO.setValveStatus((rs3.getInt("ValveStatus") == 1) ? "OPEN" : (rs3.getInt("ValveStatus") == 0) ? "CLOSED" : "");
					individualDashboardResponseVO.setValveStatusColor((rs3.getInt("ValveStatus") == 1) ? "GREEN" : (rs3.getInt("ValveStatus") == 0) ? "RED" : "");
					individualDashboardResponseVO.setVacationStatus(rs3.getInt("Vacation") == 1 ? "YES" : "NO");
					individualDashboardResponseVO.setVacationColor(rs3.getInt("Vacation") == 1 ? "ORANGE" : "BLACK");
					
					if(rs3.getString("PayType").equalsIgnoreCase("Prepaid")) {
						
						individualDashboardResponseVO.setBalance(rs3.getString("Balance"));
						individualDashboardResponseVO.setEmergencyCredit(rs3.getString("EmergencyCredit"));
						
					} else {
						individualDashboardResponseVO.setBalance("---");
						individualDashboardResponseVO.setEmergencyCredit("---");
						individualDashboardResponseVO.setLastTopupAmount("---");
						individualDashboardResponseVO.setLastRechargeDate("---");
					}
					
					individualDashboardResponseVO.setTimeStamp(ExtraMethodsDAO.datetimeformatter(rs3.getString("LogDate")));
					
					Date currentDateTime = new Date();
					
					long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (rs3.getTimestamp("LogDate")).getTime());

					if(minutes > noAMRInterval) {
						nonCommunicating++;
						individualDashboardResponseVO.setDateColor("RED");
						individualDashboardResponseVO.setCommunicationStatus("NO");
					}else if(minutes > 1440 && minutes < noAMRInterval) {
						individualDashboardResponseVO.setDateColor("ORANGE");
						individualDashboardResponseVO.setCommunicationStatus("YES");
					} else {
						individualDashboardResponseVO.setDateColor("GREEN");
						individualDashboardResponseVO.setCommunicationStatus("YES");
					}
					
					if(!customerUniqueID.isEmpty() && rs3.getString("PayType").equalsIgnoreCase("Prepaid")) {
						PreparedStatement pstmt2 = con.prepareStatement("SELECT Amount, TransactionDate FROM topup WHERE CustomerMeterID = "+rs3.getInt("CustomerMeterID")+" AND STATUS = 0 ORDER BY TransactionID DESC LIMIT 0,1") ;
						ResultSet rs2 = pstmt2.executeQuery();
						if(rs2.next()) {
							individualDashboardResponseVO.setLastTopupAmount(rs2.getString("Amount"));
							individualDashboardResponseVO.setLastRechargeDate(ExtraMethodsDAO.datetimeformatter(rs2.getString("TransactionDate")));
						} else {
						
						individualDashboardResponseVO.setLastTopupAmount("---");
						individualDashboardResponseVO.setLastRechargeDate("---");
						}
					}
					
					if(filtervo != null) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						
						if(!filtervo.getDateFrom().equalsIgnoreCase("null") || !filtervo.getDateTo().equalsIgnoreCase("null")) {
							
							long fromDateInMinutes = TimeUnit.MILLISECONDS.toMinutes(sdf.parse(filtervo.getDateFrom()).getTime());
							long toDateInMinutes = (!filtervo.getDateTo().equalsIgnoreCase("null") ? TimeUnit.MILLISECONDS.toMinutes(sdf.parse(filtervo.getDateTo()).getTime()) : 0);
							long responseDateInMinutes = TimeUnit.MILLISECONDS.toMinutes(sdf1.parse(individualDashboardResponseVO.getTimeStamp()).getTime());
							long currentDateInMinutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime());
							
							if((responseDateInMinutes >= fromDateInMinutes) && (responseDateInMinutes <= (toDateInMinutes != 0 ? toDateInMinutes : currentDateInMinutes))) {
								individualDashboardList.add(individualDashboardResponseVO);
							}
							
						}
						if(filtervo.getReadingFrom() != 0 || filtervo.getReadingTo() != 0) {
							
							if((individualDashboardResponseVO.getReading() >= filtervo.getReadingFrom()) && (individualDashboardResponseVO.getReading() <= (filtervo.getReadingTo() != 0 ? (filtervo.getReadingTo()) : 9999999))) {
								individualDashboardList.add(individualDashboardResponseVO);
							}
							
						}
						if(filtervo.getBatteryVoltageFrom() != 0 || filtervo.getBatteryVoltageTo() != 0) {
							
							if((individualDashboardResponseVO.getBattery() >= filtervo.getBatteryVoltageFrom()) && (individualDashboardResponseVO.getBattery() <= (filtervo.getBatteryVoltageTo() != 0 ? (filtervo.getBatteryVoltageTo()) : 100))) {
								individualDashboardList.add(individualDashboardResponseVO);
							}
						}
						if(filtervo.getTamperType() > 0) {
							if(((filtervo.getTamperType() == 1) && individualDashboardResponseVO.getMagneticTamper().equalsIgnoreCase("YES") || (filtervo.getTamperType() == 2) && individualDashboardResponseVO.getDoorOpenTamper().equalsIgnoreCase("YES")) || ((filtervo.getTamperType() == 3) && (individualDashboardResponseVO.getMagneticTamper().equalsIgnoreCase("YES")) && (individualDashboardResponseVO.getDoorOpenTamper().equalsIgnoreCase("YES")))) {
								individualDashboardList.add(individualDashboardResponseVO);
							}
							
						}
					} else {
						individualDashboardList.add(individualDashboardResponseVO);
					}
					
				}
				dashboardvo.setNonCommunicating(nonCommunicating);
				dashboardvo.setDasboarddata(individualDashboardList);
				dashboard_list.add(dashboardvo);
				
				dashboard_list.removeIf(e -> e.getDasboarddata().size()==0);
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return dashboard_list;
	}
	
	public ResponseVO dashboardFile(DashboardResponseVO dashboardResponseVO) {
		// TODO Auto-generated method stub
		
		ResponseVO responsevo = new ResponseVO();
		ByteArrayInputStream in = null;
		
		try {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet spreadsheet = workbook.createSheet("Dashboard List");
		
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		
		XSSFRow header = spreadsheet.createRow(0);
		
		int columnCount = 0;
        
        Cell headercell1 = header.createCell(columnCount);
        headercell1.setCellValue("Community");
        
        Cell headercell2 = header.createCell(++columnCount);
        headercell2.setCellValue("Block");
        
        Cell headercell3 = header.createCell(++columnCount);
        headercell3.setCellValue("CRN/CAN/CUI");
        
        Cell headercell4 = header.createCell(++columnCount);
        headercell4.setCellValue("Name");
        
        Cell headercell5 = header.createCell(++columnCount);
        headercell5.setCellValue("House Number");
        
        Cell headercell6 = header.createCell(++columnCount);
        headercell6.setCellValue("Time Stamp");
        
        Cell headercell7 = header.createCell(++columnCount);
        headercell7.setCellValue("Meter Serial Number");
        
        Cell headercell8 = header.createCell(++columnCount);
        headercell8.setCellValue("MIU ID");
        
        Cell headercell9 = header.createCell(++columnCount);
        headercell9.setCellValue("Reading");
        
        Cell headercell10 = header.createCell(++columnCount);
        headercell10.setCellValue("Consumption");
        
        Cell headercell11 = header.createCell(++columnCount);
        headercell11.setCellValue("Battery");
        
        Cell headercell12 = header.createCell(++columnCount);
        headercell12.setCellValue("Box Tamper");
        
        Cell headercell13 = header.createCell(++columnCount);
        headercell13.setCellValue("Magnetic Tamper");
        
        Cell headercell14 = header.createCell(++columnCount);
        headercell14.setCellValue("NFC Tamper");
        
        Cell headercell15 = header.createCell(++columnCount);
        headercell15.setCellValue("Balance");
        
        Cell headercell16 = header.createCell(++columnCount);
        headercell16.setCellValue("Emergency Credit");
                
        Cell headercell17 = header.createCell(++columnCount);
        headercell17.setCellValue("Pay Type");
        
        Cell headercell18 = header.createCell(++columnCount);
        headercell18.setCellValue("Tariff");
        
        Cell headercell19 = header.createCell(++columnCount);
        headercell19.setCellValue("Valve Status");
        
        Cell headercell20 = header.createCell(++columnCount);
        headercell20.setCellValue("Vacation Status");
        
        Cell headercell21 = header.createCell(++columnCount);
        headercell21.setCellValue("Last Topup Amount");
        
        for(int i = 0; i< dashboardResponseVO.getData().size(); i++) {
        	
        	
        	XSSFRow data = spreadsheet.createRow(spreadsheet.getLastRowNum()+1);
        	
        	/*Cell cell1 = data.createCell(dataColumnCount);
            cell1.setCellValue(dashboardResponseVO.getData().get(i).getCommunityName());
            
            Cell cell2 = data.createCell(++dataColumnCount);
            cell2.setCellValue(dashboardResponseVO.getData().get(i).getBlockName());
            
            Cell cell3 = data.createCell(++dataColumnCount);
            cell3.setCellValue(dashboardResponseVO.getData().get(i).getCustomerUniqueID());
            
            Cell cell4 = data.createCell(++dataColumnCount);
            cell4.setCellValue(dashboardResponseVO.getData().get(i).getFirstName()+" "+dashboardResponseVO.getData().get(i).getLastName());
            
            Cell cell5 = data.createCell(++dataColumnCount);
            cell5.setCellValue(dashboardResponseVO.getData().get(i).getHouseNumber());*/
            
            for(int j = 0; j < dashboardResponseVO.getData().get(i).getDasboarddata().size(); j++) {
            	
            	int dataColumnCount = 0;
            	
            	Cell cell1 = data.createCell(dataColumnCount);
                cell1.setCellValue(dashboardResponseVO.getData().get(i).getCommunityName());
                
                Cell cell2 = data.createCell(++dataColumnCount);
                cell2.setCellValue(dashboardResponseVO.getData().get(i).getBlockName());
                
                Cell cell3 = data.createCell(++dataColumnCount);
                cell3.setCellValue(dashboardResponseVO.getData().get(i).getCustomerUniqueID());
                
                Cell cell4 = data.createCell(++dataColumnCount);
                cell4.setCellValue(dashboardResponseVO.getData().get(i).getFirstName()+" "+dashboardResponseVO.getData().get(i).getLastName());
                
                Cell cell5 = data.createCell(++dataColumnCount);
                cell5.setCellValue(dashboardResponseVO.getData().get(i).getHouseNumber());
            	
            	int dashboarDataColumnCount = 5;
            	
            	Cell cell6 = data.createCell(dashboarDataColumnCount);
            	cell6.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getTimeStamp());
            	
            	Cell cell7 = data.createCell(++dashboarDataColumnCount);
            	cell7.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getMeterSerialNumber());
            	
            	Cell cell8 = data.createCell(++dashboarDataColumnCount);
            	cell8.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getMiuID());
            	
            	Cell cell9 = data.createCell(++dashboarDataColumnCount);
            	cell9.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getReading());
            	
            	Cell cell10 = data.createCell(++dashboarDataColumnCount);
            	cell10.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getConsumption());
            	
            	Cell cell11 = data.createCell(++dashboarDataColumnCount);
            	cell11.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getBattery());
            	
            	Cell cell12 = data.createCell(++dashboarDataColumnCount);
            	cell12.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getDoorOpenTamper());
            	
            	Cell cell13 = data.createCell(++dashboarDataColumnCount);
            	cell13.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getMagneticTamper());
            	
            	Cell cell14 = data.createCell(++dashboarDataColumnCount);
            	cell14.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getNfcTamper());
            	
            	Cell cell15 = data.createCell(++dashboarDataColumnCount);
            	cell15.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getBalance());
            	
            	Cell cell16 = data.createCell(++dashboarDataColumnCount);
            	cell16.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getEmergencyCredit());
            	
            	Cell cell17 = data.createCell(++dashboarDataColumnCount);
            	cell17.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getPayType());
            	
            	Cell cell18 = data.createCell(++dashboarDataColumnCount);
            	cell18.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getTariff());
            	
            	Cell cell19 = data.createCell(++dashboarDataColumnCount);
            	cell19.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getValveStatus());
            	
            	Cell cell20 = data.createCell(++dashboarDataColumnCount);
            	cell20.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getVacationStatus());
            	
            	Cell cell21 = data.createCell(++dashboarDataColumnCount);
            	cell21.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getLastTopupAmount());
            	
            	if(j < dashboardResponseVO.getData().get(i).getDasboarddata().size() - 1) { data = spreadsheet.createRow(spreadsheet.getLastRowNum()+1); }
            	
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
		responsevo.setFileName("Dashboard.xlsx");
		responsevo.setByteArrayInputStream(in);
        
		return responsevo;
		
	}

	public List<DashboardResponseVO> getFilterDashboarddetails(String communityName, String blockName, FilterVO filtervo, String type) throws SQLException {
		// TODO Auto-generated method stub

		return getDashboarddetails(type, communityName, blockName, "0", 0, filtervo);
	}
	
	public ResponseVO filterDashboardFile(DashboardResponseVO dashboardResponseVO) {
		// TODO Auto-generated method stub
		ResponseVO responsevo = new ResponseVO();

		ByteArrayInputStream in = null;
		
		try {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet spreadsheet = workbook.createSheet("Filter Dashboard List");
		
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		
		XSSFRow header = spreadsheet.createRow(0);
		
		int columnCount = 0;
        
        Cell headercell1 = header.createCell(columnCount);
        headercell1.setCellValue("Community");
        
        Cell headercell2 = header.createCell(++columnCount);
        headercell2.setCellValue("Block");
        
        Cell headercell3 = header.createCell(++columnCount);
        headercell3.setCellValue("CRN/CAN");
        
        Cell headercell4 = header.createCell(++columnCount);
        headercell4.setCellValue("Name");
        
        Cell headercell5 = header.createCell(++columnCount);
        headercell5.setCellValue("House Number");
        
        Cell headercell6 = header.createCell(++columnCount);
        headercell6.setCellValue("Time Stamp");
        
        Cell headercell7 = header.createCell(++columnCount);
        headercell7.setCellValue("Meter Serial Number");
        
        Cell headercell8 = header.createCell(++columnCount);
        headercell8.setCellValue("MIU ID");
        
        Cell headercell9 = header.createCell(++columnCount);
        headercell9.setCellValue("Reading");
        
        Cell headercell10 = header.createCell(++columnCount);
        headercell10.setCellValue("Consumption");
        
        Cell headercell11 = header.createCell(++columnCount);
        headercell11.setCellValue("Battery");
        
        Cell headercell12 = header.createCell(++columnCount);
        headercell12.setCellValue("Bax Tamper");
        
        Cell headercell13 = header.createCell(++columnCount);
        headercell13.setCellValue("Magnetic Tamper");
        
        Cell headercell14 = header.createCell(++columnCount);
        headercell14.setCellValue("NFC Tamper");
        
        Cell headercell15 = header.createCell(++columnCount);
        headercell15.setCellValue("Balance");
        
        Cell headercell16 = header.createCell(++columnCount);
        headercell16.setCellValue("Emergency Credit");
                
        Cell headercell17 = header.createCell(++columnCount);
        headercell17.setCellValue("Pay Type");
        
        Cell headercell18 = header.createCell(++columnCount);
        headercell18.setCellValue("Tariff");
        
        Cell headercell19 = header.createCell(++columnCount);
        headercell19.setCellValue("Valve Status");
        
        Cell headercell20 = header.createCell(++columnCount);
        headercell20.setCellValue("Vacation Status");
        
        Cell headercell21 = header.createCell(++columnCount);
        headercell21.setCellValue("Last Topup Amount");
        
        for(int i = 0; i< dashboardResponseVO.getData().size(); i++) {
        	
        	
        	XSSFRow data = spreadsheet.createRow(spreadsheet.getLastRowNum()+1);
        	
        	/*Cell cell1 = data.createCell(dataColumnCount);
            cell1.setCellValue(dashboardResponseVO.getData().get(i).getCommunityName());
            
            Cell cell2 = data.createCell(++dataColumnCount);
            cell2.setCellValue(dashboardResponseVO.getData().get(i).getBlockName());
            
            Cell cell3 = data.createCell(++dataColumnCount);
            cell3.setCellValue(dashboardResponseVO.getData().get(i).getCustomerUniqueID());
            
            Cell cell4 = data.createCell(++dataColumnCount);
            cell4.setCellValue(dashboardResponseVO.getData().get(i).getFirstName()+" "+dashboardResponseVO.getData().get(i).getLastName());
            
            Cell cell5 = data.createCell(++dataColumnCount);
            cell5.setCellValue(dashboardResponseVO.getData().get(i).getHouseNumber());*/
            
            for(int j = 0; j < dashboardResponseVO.getData().get(i).getDasboarddata().size(); j++) {
 
            	int dataColumnCount = 0;
            	
            	Cell cell1 = data.createCell(dataColumnCount);
                cell1.setCellValue(dashboardResponseVO.getData().get(i).getCommunityName());
                
                Cell cell2 = data.createCell(++dataColumnCount);
                cell2.setCellValue(dashboardResponseVO.getData().get(i).getBlockName());
                
                Cell cell3 = data.createCell(++dataColumnCount);
                cell3.setCellValue(dashboardResponseVO.getData().get(i).getCustomerUniqueID());
                
                Cell cell4 = data.createCell(++dataColumnCount);
                cell4.setCellValue(dashboardResponseVO.getData().get(i).getFirstName()+" "+dashboardResponseVO.getData().get(i).getLastName());
                
                Cell cell5 = data.createCell(++dataColumnCount);
                cell5.setCellValue(dashboardResponseVO.getData().get(i).getHouseNumber());
                
            	int dashboarDataColumnCount = 5;
            	
            	Cell cell6 = data.createCell(dashboarDataColumnCount);
            	cell6.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getTimeStamp());
            	
            	Cell cell7 = data.createCell(++dashboarDataColumnCount);
            	cell7.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getMeterSerialNumber());
            	
            	Cell cell8 = data.createCell(++dashboarDataColumnCount);
            	cell8.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getMiuID());
            	
            	Cell cell9 = data.createCell(++dashboarDataColumnCount);
            	cell9.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getReading());
            	
            	Cell cell10 = data.createCell(++dashboarDataColumnCount);
            	cell10.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getConsumption());
            	
            	Cell cell11 = data.createCell(++dashboarDataColumnCount);
            	cell11.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getBattery());
            	
            	Cell cell12 = data.createCell(++dashboarDataColumnCount);
            	cell12.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getDoorOpenTamper());
            	
            	Cell cell13 = data.createCell(++dashboarDataColumnCount);
            	cell13.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getMagneticTamper());
            	
            	Cell cell14 = data.createCell(++dashboarDataColumnCount);
            	cell14.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getNfcTamper());
            	
            	Cell cell15 = data.createCell(++dashboarDataColumnCount);
            	cell15.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getBalance());
            	
            	Cell cell16 = data.createCell(++dashboarDataColumnCount);
            	cell16.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getEmergencyCredit());
            	
            	Cell cell17 = data.createCell(++dashboarDataColumnCount);
            	cell17.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getPayType());
            	
            	Cell cell18 = data.createCell(++dashboarDataColumnCount);
            	cell18.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getTariff());
            	
            	Cell cell19 = data.createCell(++dashboarDataColumnCount);
            	cell19.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getValveStatus());
            	
            	Cell cell20 = data.createCell(++dashboarDataColumnCount);
            	cell20.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getVacationStatus());
            	
            	Cell cell21 = data.createCell(++dashboarDataColumnCount);
            	cell21.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getLastTopupAmount());
            	
            	if(j < dashboardResponseVO.getData().get(i).getDasboarddata().size() - 1) { data = spreadsheet.createRow(spreadsheet.getLastRowNum()+1); }
            	
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
		responsevo.setFileName("FilterDashboard.xlsx");
        
		return responsevo;
	}
	
	public GraphResponseVO getGraphDashboardDetails(String type, int year, int month, String communityName) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		GraphResponseVO graphResponseVO = new GraphResponseVO();
		List<String> xAxis;
		List<Integer> yAxis;
		int id = 0;
		
		try {
			con = getConnection();
			xAxis = new LinkedList<String>();
			yAxis = new LinkedList<Integer>();
			
			if(!communityName.equalsIgnoreCase("0")) {
				ResultSet rs1 = con.prepareStatement("SELECT * FROM block WHERE CommunityID = (SELECT CommunityID FROM community WHERE CommunityName = '"+communityName+"')").executeQuery();
				if(rs1.next()) { id = rs1.getInt("CommunityID"); }				
				}
			
					if(year == 0 && month == 0) {
						
						String start = "SELECT * FROM <tablename> ";
						PreparedStatement pstmt3 = con.prepareStatement(start.replaceAll("<tablename>", id != 0 ? "block WHERE CommunityID = "+id : "community"));
						ResultSet rs3 = pstmt3.executeQuery();
						
						while(rs3.next()) {
							
							int totalConsumptionPerDayMonthYear = 0;
						
						// last 30 days	
							
//						for(int i = 2; i>0; i-- ) {
							
								int customerConsumption = 0;
								
								String mainquery = "SELECT * FROM customerdetails <main>";
								
								mainquery = mainquery.replaceAll("<main>", id != 0 ? "WHERE CommunityID = "+id+" AND BlockID = "+ rs3.getInt("BlockID")+" ORDER BY CustomerID ASC" : "WHERE CommunityID = "+rs3.getInt("CommunityID"));
								
								PreparedStatement pstmt2 = con.prepareStatement(mainquery);
								ResultSet rs2 = pstmt2.executeQuery();
								while (rs2.next()) {
									PreparedStatement pstmt1 =  con.prepareStatement("SELECT CustomerMeterID, MIUID FROM Customermeterdetails WHERE CustomerID = " + rs2.getLong("CustomerID") + " AND MeterType = '"+type+"'");
									ResultSet rs1 = pstmt1.executeQuery();
									
									int individualMeterConsumption = 0;
									
									while(rs1.next()) {
										
										String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
												 		"- (SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID ASC LIMIT 0,1)) AS Units, CURDATE() - INTERVAL <day> DAY AS consumptiondate";
							
										pstmt = con.prepareStatement(query.replaceAll("<day>", ""+1));
										pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
										pstmt.setInt(2, rs1.getInt("CustomerMeterID"));
										rs = pstmt.executeQuery();
										
										if(rs.next()) {individualMeterConsumption = individualMeterConsumption + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));}
										}
									
									customerConsumption = customerConsumption +  individualMeterConsumption;
								}
								
								totalConsumptionPerDayMonthYear = totalConsumptionPerDayMonthYear + customerConsumption;
								
//							}
						
						xAxis.add(id != 0 ? rs3.getString("BlockName") : rs3.getString("CommunityName"));
						yAxis.add(totalConsumptionPerDayMonthYear);
							
						}
					} 
					else if (year != 0 &&  month == 0) {
						
						String start = "SELECT * FROM <tablename> ";
						PreparedStatement pstmt3 = con.prepareStatement(start.replaceAll("<tablename>", id != 0 ? "block  WHERE CommunityID = "+id : "community"));
						ResultSet rs3 = pstmt3.executeQuery();
						
						while(rs3.next()) {
							
							int totalConsumptionPerDayMonthYear = 0;
						
						for(int i = 1; i<=12; i++) {
							
							int customerConsumption = 0;
							
							String mainquery = "SELECT * FROM customerdetails <main>";
							
							mainquery = mainquery.replaceAll("<main>", id != 0 ? "WHERE CommunityID = "+rs3.getInt("CommunityID")+" AND BlockID = "+ id+" ORDER BY CustomerID ASC" : "WHERE CommunityID = "+rs3.getInt("CommunityID"));
							
							PreparedStatement pstmt2 = con.prepareStatement(mainquery);
							ResultSet rs2 = pstmt2.executeQuery();
							while (rs2.next()) {
								PreparedStatement pstmt1 =  con.prepareStatement("SELECT CustomerMeterID, MIUID FROM Customermeterdetails WHERE CustomerID = " + rs2.getLong("CustomerID") + " AND MeterType = '"+type+"'");
								ResultSet rs1 = pstmt1.executeQuery();
								
								int individualMeterConsumption = 0;
								
								while(rs1.next()) {
									
								String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = <month> ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
									      "-(SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = <month> ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";
					
								pstmt = con.prepareStatement(query.replaceAll("<month>", ""+i));
								pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
								pstmt.setInt(2, year);
								pstmt.setInt(3, rs1.getInt("CustomerMeterID"));
								pstmt.setInt(4, year);
								rs = pstmt.executeQuery();
								
								if(rs.next()) {individualMeterConsumption = individualMeterConsumption + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));}
								}
								
								customerConsumption = customerConsumption +  individualMeterConsumption;
							}
							
							totalConsumptionPerDayMonthYear = totalConsumptionPerDayMonthYear + customerConsumption;
						}
							
							xAxis.add(id != 0 ? rs3.getString("BlockName") : rs3.getString("CommunityName"));
							yAxis.add(totalConsumptionPerDayMonthYear);
						}
					} else if(year != 0 && month != 0) {
						
						int j = (month == 2 ? 28 : (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) ? 31 : 30);
						
						String start = "SELECT * FROM <tablename> ";
						PreparedStatement pstmt3 = con.prepareStatement(start.replaceAll("<tablename>", id != 0 ? "block  WHERE CommunityID = "+id : "community"));
						ResultSet rs3 = pstmt3.executeQuery();
						
						while(rs3.next()) {
							
							int totalConsumptionPerDayMonthYear = 0;
						
						for(int i = 1; i <= j ; i++) {
							
							int customerConsumption = 0;
							
							String mainquery = "SELECT * FROM customerdetails <main>";
							
							mainquery = mainquery.replaceAll("<main>", id != 0 ? "WHERE CommunityID = "+rs3.getInt("CommunityID")+" AND BlockID = "+ id+" ORDER BY CustomerID ASC" : "WHERE CommunityID = "+rs3.getInt("CommunityID"));
							
							PreparedStatement pstmt2 = con.prepareStatement(mainquery);
							ResultSet rs2 = pstmt2.executeQuery();
							while (rs2.next()) {
								PreparedStatement pstmt1 =  con.prepareStatement("SELECT CustomerMeterID, MIUID FROM Customermeterdetails WHERE CustomerID = " + rs2.getLong("CustomerID") + " AND MeterType = '"+type+"'");
								ResultSet rs1 = pstmt1.executeQuery();
								
								int individualMeterConsumption = 0;
								
								while(rs1.next()) {
								
								String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = ? AND DAY(LogDate) = <day> ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
										 "- (SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = ? AND DAY(LogDate) = <day> ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";
					
								pstmt = con.prepareStatement(query.replaceAll("<day>", ""+i));
								pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
								pstmt.setInt(2, year);
								pstmt.setInt(3, month);
								pstmt.setInt(4, rs1.getInt("CustomerMeterID"));
								pstmt.setInt(5, year);
								pstmt.setInt(6, month);
								rs = pstmt.executeQuery();
								
								if(rs.next()) {individualMeterConsumption = individualMeterConsumption + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));}
								}
								
								customerConsumption = customerConsumption +  individualMeterConsumption;
							}
							
							totalConsumptionPerDayMonthYear = totalConsumptionPerDayMonthYear + customerConsumption;
							
							}
						xAxis.add(id != 0 ? rs3.getString("BlockName") : rs3.getString("CommunityName"));
						yAxis.add(totalConsumptionPerDayMonthYear);
						}
					}
			graphResponseVO.setXAxis(xAxis);
			graphResponseVO.setYAxis(yAxis);

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return graphResponseVO;
	}
	
	public HomeResponseVO getHomeDashboardDetails(String type, int roleid, String id)
			throws SQLException {
		Connection con = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		
		HomeResponseVO homeResponseVO = null;
		int noAMRInterval = 0;
		int nonLive = 0;
		int live = 0;
		int active = 0;
		int inActive = 0;
		int emergency = 0;
		int lowBattery = 0;
		int amr = 0;
		int consumption = 0;
		String blockName = "0";
		String communityName = "0";
		
		try {
			
//			1 = valve open(active), 2 = valve close(inactive) 3 = communicating(live), 4 = non-communicating(non-live) 5 = low battery 6 = emergency credit
			
			con = getConnection();
			homeResponseVO = new HomeResponseVO();
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
			}
			
			if(!id.equalsIgnoreCase("0") && (roleid == 2 || roleid == 5)) {
				PreparedStatement pstmt5 = con.prepareStatement("SELECT b.BlockName, c.CommunityName FROM block AS b LEFT JOIN Community AS c ON b.CommunityID = c.CommunityID WHERE BlockID = '"+id+"'");
				ResultSet rs5 = pstmt5.executeQuery();
				if(rs5.next()) {
					blockName = rs5.getString("BlockName");
					communityName = rs5.getString("CommunityName");
				}
			}
			
			List<DashboardResponseVO> responselist = ((roleid == 1 || roleid == 4) ? getDashboarddetails(type, "0", "0", "0", 0, null) : getDashboarddetails(type, communityName, blockName, "0", 0, null));
			int size = responselist.size();
			
			for(int i = 0; i < size; i++) {
				
				for(int j = 0; j < responselist.get(i).getDasboarddata().size(); j++) {
					
					amr++;
					Date currentDateTime = new Date();
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date logDate = sdf.parse(responselist.get(i).getDasboarddata().get(j).getTimeStamp());
					long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (logDate.getTime()));
					
					if(minutes > noAMRInterval) { nonLive++; } else { live++; }
					if(responselist.get(i).getDasboarddata().get(j).getValveStatus().equalsIgnoreCase("Open")) { active++; } else { inActive++; }
					if((!responselist.get(i).getDasboarddata().get(j).getBalance().equalsIgnoreCase("---")) && (responselist.get(i).getDasboarddata().get(j).getPayType().equalsIgnoreCase("Prepaid"))) { if(Float.parseFloat((responselist.get(i).getDasboarddata().get(j).getBalance())) <= 0) { emergency++; } }
					if(responselist.get(i).getDasboarddata().get(j).getBatteryColor().equalsIgnoreCase("RED")) { lowBattery++; }
				}
			}
			
			homeResponseVO.setLive(live);
			homeResponseVO.setLivePercentage(amr == 0 ? 0 : (live*100/amr));
			homeResponseVO.setNonLive(nonLive);
			homeResponseVO.setNonLivePercentage(amr == 0 ? 0 : (nonLive*100/amr));
			homeResponseVO.setActive(active);
			homeResponseVO.setActivePercentage(amr == 0 ? 0 : (active*100/amr));
			homeResponseVO.setInActive(inActive);
			homeResponseVO.setInActivePercentage(amr == 0 ? 0 : (inActive*100/amr));
			homeResponseVO.setEmergency(emergency);
			homeResponseVO.setEmergencyPercentage(amr == 0 ? 0 : (emergency*100/amr));
			homeResponseVO.setLowBattery(lowBattery);
			homeResponseVO.setLowBatteryPercentage(amr == 0 ? 0 : (lowBattery*100/amr));
			homeResponseVO.setAmr(amr);
			homeResponseVO.setAmrPercentage(amr == 0 ? 0 : 100);
			
			String query1 = "SELECT SUM(Amount) AS topup FROM topup WHERE Status = 0 AND PaymentStatus = 1 AND TransactionDate BETWEEN CONCAT(CURDATE(), ' 00:00:00') AND CONCAT(CURDATE(), ' 23:59:59') <change>";
			pstmt2 = con.prepareStatement(query1.replaceAll("<change>", (roleid == 2 || roleid == 5) ? "AND BlockID = "+id :""));
			rs2 = pstmt2.executeQuery();
			if(rs2.next()) { homeResponseVO.setTopup(rs2.getInt("topup")); } else { homeResponseVO.setTopup(0); }
			
			String query2 = "SELECT cmd.CustomerMeterID, cd.CustomerID FROM customermeterdetails AS cmd LEFT JOIN customerdetails AS cd ON cd.CustomerID = cmd.CustomerID WHERE cmd.MeterType = '"+type+"' <change>";
			pstmt3 = con.prepareStatement(query2.replaceAll("<change>", (roleid == 2 || roleid == 5) ? "AND BlockID = "+id + " ORDER BY CustomerID ASC" :""));
			rs3 = pstmt3.executeQuery();
			while(rs3.next()) {
				
				String query3 = "SELECT ABS((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN (NOW() - INTERVAL 1 DAY) AND NOW() ORDER BY ReadingID DESC LIMIT 0,1)\r\n" + 
						"- (SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN (NOW() - INTERVAL 1 DAY) AND NOW() ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";

				pstmt4 = con.prepareStatement(query3);
				pstmt4.setInt(1, rs3.getInt("CustomerMeterID"));
				pstmt4.setInt(2, rs3.getInt("CustomerMeterID"));
				rs4 = pstmt4.executeQuery();
				if(rs4.next()) {
					consumption = rs4.getInt("Units") + consumption;
				}
			}
			
			homeResponseVO.setConsumption(consumption);
			
		}

		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			pstmt.close();
//			rs.close();
			con.close();
		}
		logger.debug("Total count of "+type+" AMRs in the application: " + homeResponseVO.getAmr());
		System.out.println("Total count of "+type+" AMRs in the application: " + homeResponseVO.getAmr());
		return homeResponseVO;
	}
	
	public GraphResponseVO getCustomerGraphDashboardDetails(String type, int year, int month, String customerUniqueID) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		Connection con = null;
		
		GraphResponseVO graphResponseVO = new GraphResponseVO();
		List<String> xAxis;
		List<Integer> yAxis;
		
		try {
			con = getConnection();
			xAxis = new LinkedList<String>();
			yAxis = new LinkedList<Integer>();
			
			if(year == 0 && month == 0) {
				
				for(int i = 30; i>0; i-- ) {
					
					int totalMetersConsumptionPerDay = 0;
					
					pstmt1 = con.prepareStatement("SELECT * FROM customermeterdetails WHERE CustomerUniqueID = '"+customerUniqueID+"' AND MeterType = '"+ type+"'");
					rs1 = pstmt1.executeQuery();
					while(rs1.next()) {
						String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
								 "- (SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID ASC LIMIT 0,1)) AS Units, CURDATE() - INTERVAL <day> DAY AS consumptiondate";
			
						pstmt = con.prepareStatement(query.replaceAll("<day>", "" + i));
						pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
						pstmt.setInt(2, rs1.getInt("CustomerMeterID"));
						rs = pstmt.executeQuery();

						if (rs.next()) {
							
							totalMetersConsumptionPerDay = totalMetersConsumptionPerDay + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));
						}
					}
					
					xAxis.add(rs.getString("consumptiondate"));
					yAxis.add(totalMetersConsumptionPerDay);
				}
			} else if (year != 0 &&  month == 0) {
				
				for(int i = 1; i<=12; i++ ) {
					
					int totalMetersConsumption = 0;
					
					pstmt1 = con.prepareStatement("SELECT * FROM customermeterdetails WHERE CustomerUniqueID = '"+customerUniqueID+"' AND MeterType = '"+ type+"'");
					rs1 = pstmt1.executeQuery();
					while(rs1.next()) {
						
						String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = <month> ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
							      "-(SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = <month> ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";
			
							pstmt = con.prepareStatement(query.replaceAll("<month>", ""+i));
							pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
							pstmt.setInt(2, year);
							pstmt.setInt(3, rs1.getInt("CustomerMeterID"));
							pstmt.setInt(4, year);
							rs = pstmt.executeQuery();
			
								if(rs.next()) {
									
									totalMetersConsumption = totalMetersConsumption + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));
									
									}
						}
					xAxis.add(i==1 ? "JAN-"+year : i==2 ? "FEB-"+year : i==3 ? "MAR-"+year : i==4 ? "APR-"+year : i==5 ? "MAY-"+year : i==6 ? "JUN-"+year : i==7 ? "JUL-"+year : i==8 ? "AUG-"+year : i==9 ? "SEP-"+year : i==10 ? "OCT-"+year : i==11 ? "NOV-"+year : i==12 ? "DEC-"+year : "");
					yAxis.add(totalMetersConsumption);
					
				}
				
			} else if(year != 0 && month != 0) {
				
				int j = (month == 2 ? 28 : (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) ? 31 : 30);
				
				for(int i = 1; i <= j ; i++) {
					
					int totalMetersConsumption = 0;
					
					pstmt1 = con.prepareStatement("SELECT * FROM customermeterdetails WHERE CustomerUniqueID = '"+customerUniqueID+"' AND MeterType = '"+ type+"'");
					rs1 = pstmt1.executeQuery();
					while(rs1.next()) {
						String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = ? AND DAY(LogDate) = <day> ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
								 "- (SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = ? AND DAY(LogDate) = <day> ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";
			
								pstmt = con.prepareStatement(query.replaceAll("<day>", ""+i));
								pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
								pstmt.setInt(2, year);
								pstmt.setInt(3, month);
								pstmt.setInt(4, rs1.getInt("CustomerMeterID"));
								pstmt.setInt(5, year);
								pstmt.setInt(6, month);
								rs = pstmt.executeQuery();
			
									if(rs.next()) {
										totalMetersConsumption = totalMetersConsumption + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));
										}
					}
					xAxis.add(Integer.toString(i)+"-"+month+"-"+year);
					yAxis.add(totalMetersConsumption);
					
					}
				}
			
			graphResponseVO.setXAxis(xAxis);
			graphResponseVO.setYAxis(yAxis);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return graphResponseVO;
	}
	
	public AllGraphResponseVO getCustomerAllGraphDashboardDetails(int year, int month, String customerUniqueID) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection con = null;
		
		AllGraphResponseVO allGraphResponseVO = new AllGraphResponseVO();
		Series series = null;
		List<String> xAxis;
		List<Series> seriesList;
		List<Integer> gasData;
		List<Integer> waterData;
		
		try {
			con = getConnection();
			xAxis = new LinkedList<String>();
			seriesList = new LinkedList<Series>();
			gasData = new LinkedList<Integer>();
			waterData = new LinkedList<Integer>();
			
			if(year == 0 && month == 0) {
				
				for(int i = 30; i>0; i-- ) {
					
					int totalGasMetersConsumptionPerDay = 0;
					
					series = new Series();
					series.setName("Gas");
					
					pstmt1 = con.prepareStatement("SELECT * FROM customermeterdetails WHERE CustomerUniqueID = '"+customerUniqueID+"' AND MeterType = 'Gas'");
					rs1 = pstmt1.executeQuery();
					while(rs1.next()) {
						String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
								 "- (SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID ASC LIMIT 0,1)) AS Units, (CURDATE() - INTERVAL <day> DAY) AS consumptiondate";
			
						pstmt = con.prepareStatement(query.replaceAll("<day>", "" + i));
						pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
						pstmt.setInt(2, rs1.getInt("CustomerMeterID"));
						rs = pstmt.executeQuery();

						if (rs.next()) {
							
							totalGasMetersConsumptionPerDay = totalGasMetersConsumptionPerDay + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));
							xAxis.add(rs.getString("consumptiondate"));
						}
					}
					
					gasData.add(totalGasMetersConsumptionPerDay);
				}
				
				series.setData(gasData);
				seriesList.add(series);
				
				for(int i = 30; i>0; i-- ) {
					
					int totalWaterMetersConsumptionPerDay = 0;
					gasData = new LinkedList<Integer>();
					series = new Series();
					series.setName("Water");
					
					pstmt1 = con.prepareStatement("SELECT * FROM customermeterdetails WHERE CustomerUniqueID = '"+customerUniqueID+"' AND MeterType = 'Water'");
					rs1 = pstmt1.executeQuery();
					while(rs1.next()) {
						String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
								 "- (SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID ASC LIMIT 0,1)) AS Units, CURDATE() - INTERVAL <day> DAY AS consumptiondate";
			
						pstmt = con.prepareStatement(query.replaceAll("<day>", "" + i));
						pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
						pstmt.setInt(2, rs1.getInt("CustomerMeterID"));
						rs2 = pstmt.executeQuery();

						if (rs2.next()) {
							
							totalWaterMetersConsumptionPerDay = totalWaterMetersConsumptionPerDay + (rs2.getString("Units") == null ? 0 : rs2.getInt("Units"));
							xAxis.add(rs2.getString("consumptiondate"));
						}
					}
					
					waterData.add(totalWaterMetersConsumptionPerDay);
				}
				
				series.setData(waterData);
				seriesList.add(series);

			} 
			
			allGraphResponseVO.setxAxis(xAxis);
			allGraphResponseVO.setSeries(seriesList);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return allGraphResponseVO;
	}
	
	
	public ResponseVO postDashboarddetails(DataRequestVO dataRequestVO, String miuID) throws SQLException {
		// TODO Auto-generated method stub

		ResponseVO responsevo = new ResponseVO();
		
		try {
			
			logger.debug("Device ID: "+miuID);
			
				if (dataRequestVO.getType() > 0) {
					
					logger.debug("Battery Voltage: "+dataRequestVO.getBat_volt());
					
					responsevo.setResult(insertdashboard(dataRequestVO, miuID));
					responsevo.setMessage(responsevo.getResult().equalsIgnoreCase("Success") ? "Data Inserted Successfully" : "Data Insertion Failed");
					
				} else {
					responsevo.setResult("Invalid Meter Type");
					responsevo.setMessage("Data Insertion Failed");
				}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return responsevo;
	}

	public String insertdashboard (DataRequestVO dataRequestVO, String miuID) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		String result = "Failure";
		String alertMessage = "";
		
		try {
			con = getConnection();
			
				PreparedStatement pstmt2 = con.prepareStatement("SELECT cd.CommunityID, cd.BlockID, cd.CustomerID, cmd.CustomerMeterID, cmd.TariffID, t.Tariff, cmd.MeterSerialNumber, cd.CustomerUniqueID, cmd.MeterSizeID, cmd.ThresholdMaximum, cmd.ThresholdMinimum from customerdetails as cd LEFT JOIN customermeterdetails as cmd ON cd.CustomerID = cmd.CustomerID LEFT JOIN tariff as t on t.TariffID = cmd.TariffID WHERE cmd.MIUID = ?");
				pstmt2.setString(1, miuID);
				ResultSet rs = pstmt2.executeQuery();
				if(rs.next()) {
					ValidateResponseVO validateResponseVO = validateRequest(dataRequestVO, miuID, rs.getLong("CustomerMeterID"));
					if(validateResponseVO.isResult()) {
					
					pstmt = con.prepareStatement("INSERT INTO balancelog (MIUID, CommunityID, BlockID, CustomerID, CustomerMeterID, MeterSizeID, MeterSerialNumber, CustomerUniqueID, MeterType, SyncTime, SyncInterval, PayType, BatteryVoltage, TariffID, Tariff, ValveConfiguration, ValveStatus, Balance, EmergencyCredit, Minutes, Reading, DoorOpenTamper, MagneticTamper, Vacation, RTCFault, LowBattery, LowBalance, NFCTamper, Source, ID, LogDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
					pstmt.setString(1, miuID);
					pstmt.setInt(2, rs.getInt("CommunityID"));
					pstmt.setInt(3, rs.getInt("BlockID"));
					pstmt.setInt(4, rs.getInt("CustomerID"));
					pstmt.setInt(5, rs.getInt("CustomerMeterID"));
					pstmt.setInt(6, rs.getInt("MeterSizeID"));
					pstmt.setString(7, rs.getString("MeterSerialNumber"));
					pstmt.setString(8, rs.getString("CustomerUniqueID"));
					pstmt.setString(9, dataRequestVO.getType() == 1 ? "Water" : dataRequestVO.getType() == 2 ? "Gas" : dataRequestVO.getType() == 3 ? "Energy" : "");
					pstmt.setString(10, dataRequestVO.getSync_time());
					pstmt.setInt(11, dataRequestVO.getSync_interval());
					pstmt.setString(12, dataRequestVO.getPre_post_paid() == 1 ? "Prepaid" : "Postpaid");
					pstmt.setFloat(13, dataRequestVO.getBat_volt());
					pstmt.setInt(14, rs.getInt("TariffID"));
					pstmt.setFloat(15, dataRequestVO.getTariff());
					pstmt.setInt(16, dataRequestVO.getValve_configuration());
					pstmt.setInt(17, dataRequestVO.getPre_post_paid() == 1 ? dataRequestVO.getValve_configuration() : 1);
					pstmt.setFloat(18, dataRequestVO.getCredit());
					pstmt.setFloat(19, dataRequestVO.getEmergency_credit());
					pstmt.setInt(20, dataRequestVO.getDays_elapsed_after_valve_trip());
					pstmt.setFloat(21, dataRequestVO.getReading());
					pstmt.setInt(22, dataRequestVO.getStatus().getDoor_open());
					pstmt.setInt(23, dataRequestVO.getStatus().getMagnetic());
					pstmt.setInt(24, dataRequestVO.getStatus().getSchedule_disconnect());
					pstmt.setInt(25, dataRequestVO.getStatus().getRtc_fault());
					pstmt.setInt(26, dataRequestVO.getStatus().getLow_bat());
					pstmt.setInt(27, dataRequestVO.getStatus().getLow_bal());
					pstmt.setInt(28, dataRequestVO.getStatus().getNfc_tamper()); 
					pstmt.setString(29, dataRequestVO.getSource());
					pstmt.setInt(30, dataRequestVO.getSource().equalsIgnoreCase("Mobile") ? dataRequestVO.getID() : 0);
					if (pstmt.executeUpdate() > 0) {
						
						PreparedStatement pstmt4 = con.prepareStatement("SELECT MAX(ReadingID) as ReadingID FROM balancelog WHERE MIUID = ?");
						pstmt4.setString(1, miuID);
						ResultSet rs2 = pstmt4.executeQuery();
						
						if(rs2.next()) {
							
							PreparedStatement pstmt3 = con.prepareStatement("SELECT * FROM displaybalancelog WHERE MIUID = ? AND CustomerMeterID = " + rs.getInt("CustomerMeterID"));
							pstmt3.setString(1, miuID);
							ResultSet rs1 = pstmt3.executeQuery();
							
							if(rs1.next()) {
								pstmt1 = con.prepareStatement("UPDATE displaybalancelog SET MainBalanceLogID = ?, MIUID = ?, CommunityID = ?, BlockID = ?, CustomerID = ?, CustomerMeterID = ?, MeterSizeID =?, MeterSerialNumber = ?, CustomerUniqueID = ?, MeterType = ?, SyncTime = ?, SyncInterval = ?, PayType = ?, BatteryVoltage = ?, TariffID = ?, Tariff = ?, ValveConfiguration = ?,  ValveStatus = ?, Balance = ?, EmergencyCredit = ?, Minutes = ?, Reading = ?, DoorOpenTamper = ?, MagneticTamper = ?, Vacation = ?, RTCFault = ?, LowBattery = ?, LowBalance = ?, NFCTamper = ?, Source = ?, ID = ?, LogDate = NOW() WHERE CustomerMeterID = ? ");
								pstmt1.setInt(1, rs2.getInt("ReadingID"));
								pstmt1.setString(2, miuID);
								pstmt1.setInt(3, rs.getInt("CommunityID"));
								pstmt1.setInt(4, rs.getInt("BlockID"));
								pstmt1.setInt(5, rs.getInt("CustomerID"));
								pstmt1.setInt(6, rs.getInt("CustomerMeterID"));
								pstmt1.setInt(7, rs.getInt("MeterSizeID"));
								pstmt1.setString(8, rs.getString("MeterSerialNumber"));
								pstmt1.setString(9, rs.getString("CustomerUniqueID"));
								pstmt1.setString(10, dataRequestVO.getType() == 1 ? "Water" : dataRequestVO.getType() == 2 ? "Gas" : dataRequestVO.getType() == 3 ? "Energy" : "");
								pstmt1.setString(11, dataRequestVO.getSync_time());
								pstmt1.setInt(12, dataRequestVO.getSync_interval());
								pstmt1.setString(13, dataRequestVO.getPre_post_paid() == 1 ? "Prepaid" : "Postpaid");
								pstmt1.setFloat(14, dataRequestVO.getBat_volt());
								pstmt1.setInt(15, rs.getInt("TariffID"));
								pstmt1.setFloat(16, dataRequestVO.getTariff());
								pstmt1.setInt(17, dataRequestVO.getValve_configuration());
								pstmt1.setInt(18, dataRequestVO.getPre_post_paid() == 1 ? dataRequestVO.getValve_configuration() : 1);
								pstmt1.setFloat(19, dataRequestVO.getCredit());
								pstmt1.setFloat(20, dataRequestVO.getEmergency_credit());
								pstmt1.setInt(21, dataRequestVO.getDays_elapsed_after_valve_trip());
								pstmt1.setFloat(22, dataRequestVO.getReading());
								pstmt1.setInt(23, dataRequestVO.getStatus().getDoor_open());
								pstmt1.setInt(24, dataRequestVO.getStatus().getMagnetic());
								pstmt1.setInt(25, dataRequestVO.getStatus().getSchedule_disconnect());
								pstmt1.setInt(26, dataRequestVO.getStatus().getRtc_fault());
								pstmt1.setInt(27, dataRequestVO.getStatus().getLow_bat());
								pstmt1.setInt(28, dataRequestVO.getStatus().getLow_bal());
								pstmt1.setInt(29, dataRequestVO.getStatus().getNfc_tamper()); 
								pstmt1.setString(30, dataRequestVO.getSource());
								pstmt1.setInt(31, dataRequestVO.getSource().equalsIgnoreCase("Mobile") ? dataRequestVO.getID() : 0);
								pstmt1.setInt(32, rs.getInt("CustomerMeterID"));
								
							} else {
								
									pstmt1 = con.prepareStatement("INSERT INTO displaybalancelog (MainBalanceLogID, MIUID, CommunityID, BlockID, CustomerID, CustomerMeterID, MeterSizeID, MeterSerialNumber, CustomerUniqueID, MeterType, SyncTime, SyncInterval, PayType, BatteryVoltage, TariffID, Tariff, ValveConfiguration, ValveStatus, Balance, EmergencyCredit, Minutes, Reading, DoorOpenTamper, MagneticTamper, Vacation, RTCFault, LowBattery, LowBalance, NFCTamper, Source, ID, LogDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
									pstmt1.setInt(1, rs2.getInt("ReadingID"));
									pstmt1.setString(2, miuID);
									pstmt1.setInt(3, rs.getInt("CommunityID"));
									pstmt1.setInt(4, rs.getInt("BlockID"));
									pstmt1.setInt(5, rs.getInt("CustomerID"));
									pstmt1.setInt(6, rs.getInt("CustomerMeterID"));
									pstmt1.setInt(7, rs.getInt("MeterSizeID"));
									pstmt1.setString(8, rs.getString("MeterSerialNumber"));
									pstmt1.setString(9, rs.getString("CustomerUniqueID"));
									pstmt1.setString(10, dataRequestVO.getType() == 1 ? "Water" : dataRequestVO.getType() == 2 ? "Gas" : dataRequestVO.getType() == 3 ? "Energy" : "");
									pstmt1.setString(11, dataRequestVO.getSync_time());
									pstmt1.setInt(12, dataRequestVO.getSync_interval());
									pstmt1.setString(13, dataRequestVO.getPre_post_paid() == 1 ? "Prepaid" : "Postpaid");
									pstmt1.setFloat(14, dataRequestVO.getBat_volt());
									pstmt1.setInt(15, rs.getInt("TariffID"));
									pstmt1.setFloat(16, dataRequestVO.getTariff());
									pstmt1.setInt(17, dataRequestVO.getValve_configuration());
									pstmt1.setInt(18, dataRequestVO.getPre_post_paid() == 1 ? dataRequestVO.getValve_configuration() : 1);
									pstmt1.setFloat(19, dataRequestVO.getCredit());
									pstmt1.setFloat(20, dataRequestVO.getEmergency_credit());
									pstmt1.setInt(21, dataRequestVO.getDays_elapsed_after_valve_trip());
									pstmt1.setFloat(22, dataRequestVO.getReading());
									pstmt1.setInt(23, dataRequestVO.getStatus().getDoor_open());
									pstmt1.setInt(24, dataRequestVO.getStatus().getMagnetic());
									pstmt1.setInt(25, dataRequestVO.getStatus().getSchedule_disconnect());
									pstmt1.setInt(26, dataRequestVO.getStatus().getRtc_fault());
									pstmt1.setInt(27, dataRequestVO.getStatus().getLow_bat());
									pstmt1.setInt(28, dataRequestVO.getStatus().getLow_bal());
									pstmt1.setInt(29, dataRequestVO.getStatus().getNfc_tamper()); 
									pstmt1.setString(30, dataRequestVO.getSource());
									pstmt1.setInt(31, dataRequestVO.getSource().equalsIgnoreCase("Mobile") ? dataRequestVO.getID() : 0);
							}
							
						}
						
						if(pstmt1.executeUpdate()>0) {
						result = "Success";
						}
						
						String query = "SELECT LogDate, MIUID, DoorOpenTamper, MagneticTamper, LowBattery, LowBalance, NFCTamper FROM balancelog WHERE CustomerMeterID = ? AND CustomerUniqueID = ? AND <condition> AND LogDate BETWEEN (CONCAT(CURDATE(), ' 00:00:00')) AND NOW() ORDER BY LogDate DESC";
						
						if (dataRequestVO.getStatus().getLow_bat() == 1) {
							
							ps = con.prepareStatement(query.replaceAll("<condition>", dataRequestVO.getStatus().getLow_bat() == 1 ? "LowBattery = 1" : ""));
							ps.setInt(1, rs.getInt("CustomerMeterID"));
							ps.setString(2, rs.getString("CustomerUniqueID"));
							resultSet = ps.executeQuery(); 

							int size =0;  
							if (resultSet != null)   
							{  
								resultSet.beforeFirst();  
								resultSet.last();  
							size = resultSet.getRow();
							}
							
							if(size == 1) {
								alertMessage = "The Battery in MIUID: <MIU> with CRN/CAN/UAN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block> is low.";
								alertMessage = alertMessage.replaceAll("<MIU>", resultSet.getString("MIUID"));
								sendalertmail("Low Battery Alert!!!", alertMessage, resultSet.getString("MIUID"));
								sendalertsms(0, alertMessage, resultSet.getString("MIUID"));
							}
						} 
						
						if (dataRequestVO.getStatus().getDoor_open() == 1 || dataRequestVO.getStatus().getMagnetic() == 1 || dataRequestVO.getStatus().getNfc_tamper() == 1) {  
							ps = con.prepareStatement(query.replaceAll("<condition>", dataRequestVO.getStatus().getDoor_open() == 1 ? "DoorOpenTamper = 1" : dataRequestVO.getStatus().getMagnetic() == 1 ? "MagneticTamper = 1" : dataRequestVO.getStatus().getNfc_tamper() == 1 ? "NFCTamper = 1" : ""));
							ps.setInt(1, rs.getInt("CustomerMeterID"));
							ps.setString(2, rs.getString("CustomerUniqueID"));
							resultSet = ps.executeQuery(); 
							
							int size =0;  
							if (resultSet != null)   
							{  
								resultSet.beforeFirst();  
								resultSet.last();  
							size = resultSet.getRow();
							}
							
							if(size == 1) {
								alertMessage = "There is a <tamper> Tamper at <timestamp>, in MIUID: <MIU> with CRN/CAN/UAN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block>.";
								alertMessage = alertMessage.replaceAll("<MIU>", resultSet.getString("MIUID"));
								alertMessage = alertMessage.replaceAll("<tamper>", dataRequestVO.getStatus().getDoor_open() == 1 ? "Door Open Tamper" : dataRequestVO.getStatus().getMagnetic() == 1 ? "Magnetic Tamper" : dataRequestVO.getStatus().getNfc_tamper() == 1 ? "NFC Tamper" : ""); 
								alertMessage = alertMessage.replaceAll("<timestamp>", resultSet.getString("LogDate"));
								sendalertmail("Tamper Alert!!!", alertMessage, resultSet.getString("MIUID"));
								sendalertsms(0, alertMessage, resultSet.getString("MIUID"));
							}
						}

						if(dataRequestVO.getStatus().getLow_bal() == 1) {
							
							ps = con.prepareStatement(query.replaceAll("<condition>", dataRequestVO.getStatus().getLow_bal() == 1 ? "LowBalance = 1" : ""));
							ps.setInt(1, rs.getInt("CustomerMeterID"));
							ps.setString(2, rs.getString("CustomerUniqueID"));
							resultSet = ps.executeQuery(); 

							int size = 0;  
							if (resultSet != null)   
							{  
								resultSet.beforeFirst();  
								resultSet.last();  
							size = resultSet.getRow();
							}
							
							if(size == 1) {
								alertMessage = "Balance in your MIUID: <MIU> with CRN/CAN/UAN: <CRN> is low. Please Recharge.";
								alertMessage = alertMessage.replaceAll("<MIU>", resultSet.getString("MIUID"));
								sendalertmail("Low Balance Alert!!!", alertMessage, resultSet.getString("MIUID"));
								sendalertsms(1, alertMessage, resultSet.getString("MIUID"));								
							}
							
						}
						
						if(dataRequestVO.isTopupSMS()) {
							
							if(dataRequestVO.getTopupStatus().equalsIgnoreCase("Success")) {
								
								alertMessage = "Your Recharge for MIUID: <MIU> with CRN/CAN/UAN: <CRN> is successful. Available Balance: "+dataRequestVO.getCredit()+"/- and Emergency Credit: "+dataRequestVO.getEmergency_credit()+"/-.";
								alertMessage = alertMessage.replaceAll("<MIU>", dataRequestVO.getMiuID());
								
							} else if (dataRequestVO.getTopupStatus().equalsIgnoreCase("Pending")){
								alertMessage = "Your Recharge for MIUID: <MIU> with CRN/CAN/UAN: <CRN> is pending. Please wait until further communication from the device. Available Balance: "+dataRequestVO.getCredit()+"/- and Emergency Credit: "+dataRequestVO.getEmergency_credit()+"/-.";
								alertMessage = alertMessage.replaceAll("<MIU>", dataRequestVO.getMiuID());
							} else {
								alertMessage = "Your Recharge for MIUID: <MIU> with CRN/CAN/UAN: <CRN> has failed. Please try after sometime. Available Balance: "+dataRequestVO.getCredit()+"/- and Emergency Credit: "+dataRequestVO.getEmergency_credit()+"/-.";
								alertMessage = alertMessage.replaceAll("<MIU>", dataRequestVO.getMiuID());
							}
							
							sendalertmail("Recharge Alert!!!", alertMessage, dataRequestVO.getMiuID());
							sendalertsms(1, alertMessage, dataRequestVO.getMiuID());
						
						}
						
						PreparedStatement thresholdAlert = con.prepareStatement("SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = "+ rs.getLong("CustomerMeterID")+" ORDER BY ReadingID DESC LIMIT 0,1) - (SELECT Reading FROM balancelog WHERE CustomerMeterID = "+ rs.getLong("CustomerMeterID") +" ORDER BY ReadingID DESC LIMIT 2,1)) AS Threshold");
						ResultSet thresholdResult = thresholdAlert.executeQuery();
						
						if(thresholdResult.next()) {
							
							if(thresholdResult.getFloat("Threshold") >= rs.getFloat("ThresholdMaximum")) {
								
								alertMessage = "The Consumption in MIUID: <MIU> with CRN/CAN/UAN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block> is above Threshold Value i.e. "+rs.getFloat("ThresholdMaximum")+".";
								alertMessage = alertMessage.replaceAll("<MIU>", miuID);
								sendalertmail("Maximum Threshold Alert!!!", alertMessage, miuID);
								sendalertsms(0, alertMessage, miuID);
								
							} else if(thresholdResult.getFloat("Threshold") <= rs.getFloat("ThresholdMinimum")) {
								
								alertMessage = "The Consumption in MIUID: <MIU> with CRN/CAN/UAN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block> is below Threshold Value i.e. "+rs.getFloat("ThresholdMinimum")+".";
								alertMessage = alertMessage.replaceAll("<MIU>", miuID);
								sendalertmail("Minimum Threshold Alert!!!", alertMessage, miuID);
								sendalertsms(0, alertMessage, miuID);
							}
							
						}
						
					}
					
				} else {
					
					alertMessage = "The Reading of MIUID: <MIU> with CRN/CAN/UAN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block> is less than the previous reading: "+validateResponseVO.getPreviousReading()+".";
					alertMessage = alertMessage.replaceAll("<MIU>", miuID);
					sendalertmail("Zero Reading Alert!!!", alertMessage, miuID);
					sendalertsms(0, alertMessage, miuID);
					
				}
		}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private ValidateResponseVO validateRequest(DataRequestVO dataRequestVO, String miuID, Long cutomerMeterID) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ValidateResponseVO validateResponseVO = new ValidateResponseVO();
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("SELECT * FROM displaybalancelog WHERE MIUID = ? AND CustomerMeterID = " + cutomerMeterID);
			pstmt.setString(1, miuID);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				if(dataRequestVO.getReading() < rs.getLong("Reading")) {
					validateResponseVO.setResult(false);
					validateResponseVO.setPreviousReading(rs.getLong("Reading"));
				} else {
					validateResponseVO.setResult(true);
					validateResponseVO.setPreviousReading(rs.getLong("Reading"));
				}
				
			} else {
				validateResponseVO.setResult(true);
				validateResponseVO.setPreviousReading(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return validateResponseVO;
	}

	public String sendalertsms(int i, String message, String miuID) {
		// TODO Auto-generated method stub
		ExtraMethodsDAO extraMethodsDao = new ExtraMethodsDAO();
		SMSRequestVO smsRequestVO = new SMSRequestVO();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		String result = "Failure";
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("SELECT cd.MobileNumber AS customerMobileNumber, b.MobileNumber AS adminMobileNumber, cd.HouseNumber, cd.CustomerUniqueID, b.BlockName as BlockName, c.CommunityName as CommunityName FROM customerdetails as cd LEFT JOIN customermeterdetails AS cmd ON cd.CustomerID = cmd.CustomerID LEFT JOIN block AS b ON b.BlockID = cd.BlockID LEFT JOIN community AS c ON c.CommunityID = cd.CommunityID WHERE cmd.MIUID = '"+ miuID+"'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				smsRequestVO.setToMobileNumber(i == 1 ? rs.getString("customerMobileNumber") : rs.getString("adminMobileNumber"));
				message = message.replaceAll("<CRN>", rs.getString("CustomerUniqueID"));
				if(i!=1) {
					message = message.replaceAll("<community>", rs.getString("CommunityName"));
					message = message.replaceAll("<block>", rs.getString("BlockName"));
					message = message.replaceAll("<house>", rs.getString("HouseNumber"));	
				}
				
				smsRequestVO.setMessage(i == 1 ? "Dear Customer, "+message : "Dear Admin, "+message);
				
				result = extraMethodsDao.sendsms(smsRequestVO).toString();				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public String sendalertmail(String subject, String message, String miuID) {
		// TODO Auto-generated method stub
		
		ExtraMethodsDAO extraMethodsDao = new ExtraMethodsDAO();
		MailRequestVO mailRequestVO = new MailRequestVO();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		String result = "Failure";
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("SELECT cd.Email AS customerEmail, b.Email AS adminEmail, b.BlockName as BlockName, c.CommunityName as CommunityName, cd.CustomerUniqueID, cd.HouseNumber FROM customerdetails as cd LEFT JOIN customermeterdetails AS cmd ON cd.CustomerID = cmd.CustomerID LEFT JOIN block AS b ON b.BlockID = cd.BlockID LEFT JOIN community AS c ON c.CommunityID = cd.CommunityID WHERE cmd.MIUID = '"+ miuID+"'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mailRequestVO.setFileLocation("NoAttachment");
				mailRequestVO.setToEmail(subject.equalsIgnoreCase("Low Balance Alert!!!") ? rs.getString("customerEmail") : rs.getString("adminEmail"));
				mailRequestVO.setSubject(subject);
				message = message.replaceAll("<CRN>", rs.getString("CustomerUniqueID"));
				if(!subject.equalsIgnoreCase("Low Balance Alert!!!")) {
					message = message.replaceAll("<community>", rs.getString("CommunityName"));
					message = message.replaceAll("<block>", rs.getString("BlockName"));
					message = message.replaceAll("<house>", rs.getString("HouseNumber"));	
				}
				
				mailRequestVO.setMessage(subject.equalsIgnoreCase("Low Balance Alert!!!") ? "Dear Customer, \n \n"+message : "Dear Admin, \n \n"+ message);
				
				result = extraMethodsDao.sendmail(mailRequestVO);				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			result = "Failure";
		}
		
		return result;
	}

	public boolean validateToken(DataRequestVO dataRequestVO) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		        try {
		        	con = getConnection();
		        	pstmt = con.prepareStatement("SELECT * FROM user WHERE ID = "+dataRequestVO.getID());
		        	rs = pstmt.executeQuery();
		        	
		        	if(rs.next()) {
		        		if(rs.getString("Token").equals(dataRequestVO.getToken())) {
		        		return true;
		        		}
		        	}

		        } catch (Exception e) {
		            return false; // Token validation failed
		        }
		    return false;
	}

	public ResponseVO postSensorDashboarddetails(SensorDataRequestVO sensorDataRequestVO) {
		// TODO Auto-generated method stub
		ResponseVO responsevo = new ResponseVO();
		
		try {
			
			logger.debug("Device ID: "+sensorDataRequestVO.getEquipment_serial_id());
			
					logger.debug("Battery Voltage: "+sensorDataRequestVO.getBattery_percentage());
					
					responsevo.setResult(insertSensorDashboard(sensorDataRequestVO));
					responsevo.setMessage(responsevo.getResult().equalsIgnoreCase("Success") ? "Data Inserted Successfully" : "Data Insertion Failed");
					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return responsevo;
	}

	private String insertSensorDashboard(SensorDataRequestVO sensorDataRequestVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";
		
		try {
			con = getConnection();
			
				PreparedStatement pstmt2 = con.prepareStatement("SELECT cd.CommunityID, cd.BlockID, cd.CustomerID, cd.CustomerUniqueID from customerdetails as cd LEFT JOIN customermeterdetails as cmd ON cd.CustomerID = cmd.CustomerID WHERE cd.ActiveStatus = 2 AND cmd.MIUID = ?");
				pstmt2.setString(1, sensorDataRequestVO.getEquipment_serial_id());
				ResultSet rs = pstmt2.executeQuery();
				if(rs.next()) {
					
					pstmt = con.prepareStatement("INSERT INTO sensorlog (equipment_serial_id, CommunityID, BlockID, CustomerID, CustomerUniqueID, reading1, reading2, reading3, reading4, reader_sensor_status1, reader_sensor_status2, reader_sensor_status3, reader_sensor_status4, per_day_flow_rate1, per_day_flow_rate2, per_day_flow_rate3, per_day_flow_rate4, live_flow_rate1, live_flow_rate2, live_flow_rate3, live_flow_rate4, record_interval, sync_interval, rssi, digital_outputs1, digital_outputs2, digital_outputs3, digital_outputs4, analog_inputs1, analog_inputs2, analog_inputs3, analog_inputs4, analog_outputs1, analog_outputs2, analog_outputs3, analog_outputs4, voltage_outputs1, voltage_outputs2, voltage_outputs3, voltage_outputs4, battery_percentage, online_powersupply, gsm_status, ethernet_status, nfc_status, flash_status, nfc_memory_status, flash_memory_status, low_gsm, low_battery, sensor_detachment, door_open_switch, magnetic_tamper, timestamp, LogDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
					pstmt.setString(1, sensorDataRequestVO.getEquipment_serial_id());
					pstmt.setInt(2, rs.getInt("CommunityID"));
					pstmt.setInt(3, rs.getInt("BlockID"));
					pstmt.setInt(4, rs.getInt("CustomerID"));
					pstmt.setString(5, rs.getString("CustomerUniqueID"));
					pstmt.setInt(6, sensorDataRequestVO.getReadings().get(0));
					pstmt.setInt(7, sensorDataRequestVO.getReadings().get(1));
					pstmt.setInt(8, sensorDataRequestVO.getReadings().get(2));
					pstmt.setInt(9, sensorDataRequestVO.getReadings().get(3));
					pstmt.setInt(10, sensorDataRequestVO.getReader_sensor_status().get(0));
					pstmt.setInt(11, sensorDataRequestVO.getReader_sensor_status().get(1));
					pstmt.setInt(12, sensorDataRequestVO.getReader_sensor_status().get(2));
					pstmt.setInt(13, sensorDataRequestVO.getReader_sensor_status().get(3));
					pstmt.setFloat(14, sensorDataRequestVO.getPer_day_flow_rate().get(0));
					pstmt.setFloat(15, sensorDataRequestVO.getPer_day_flow_rate().get(1));
					pstmt.setFloat(16, sensorDataRequestVO.getPer_day_flow_rate().get(2));
					pstmt.setFloat(17, sensorDataRequestVO.getPer_day_flow_rate().get(3));
					pstmt.setFloat(18, sensorDataRequestVO.getLive_flow_rate().get(0));
					pstmt.setFloat(19, sensorDataRequestVO.getLive_flow_rate().get(1));
					pstmt.setFloat(20, sensorDataRequestVO.getLive_flow_rate().get(2));
					pstmt.setFloat(21, sensorDataRequestVO.getLive_flow_rate().get(3));
					pstmt.setInt(22, sensorDataRequestVO.getRecord_interval());
					pstmt.setInt(23, sensorDataRequestVO.getSync_interval());
					pstmt.setInt(24, sensorDataRequestVO.getRssi());
					pstmt.setInt(25, sensorDataRequestVO.getDigital_outputs().get(0));
					pstmt.setInt(26, sensorDataRequestVO.getDigital_outputs().get(1));
					pstmt.setInt(27, sensorDataRequestVO.getDigital_outputs().get(2));
					pstmt.setInt(28, sensorDataRequestVO.getDigital_outputs().get(3));
					pstmt.setFloat(29, sensorDataRequestVO.getAnalog_inputs().get(0));
					pstmt.setFloat(30, sensorDataRequestVO.getAnalog_inputs().get(1));
					pstmt.setFloat(31, sensorDataRequestVO.getAnalog_inputs().get(2));
					pstmt.setFloat(32, sensorDataRequestVO.getAnalog_inputs().get(3));
					pstmt.setFloat(33, sensorDataRequestVO.getAnalog_outputs().get(0));
					pstmt.setFloat(34, sensorDataRequestVO.getAnalog_outputs().get(1));
					pstmt.setFloat(35, sensorDataRequestVO.getAnalog_outputs().get(2));
					pstmt.setFloat(36, sensorDataRequestVO.getAnalog_outputs().get(3));
					pstmt.setFloat(37, sensorDataRequestVO.getVoltage_outputs().get(0));
					pstmt.setFloat(38, sensorDataRequestVO.getVoltage_outputs().get(1));
					pstmt.setFloat(39, sensorDataRequestVO.getVoltage_outputs().get(2));
					pstmt.setFloat(40, sensorDataRequestVO.getVoltage_outputs().get(3));
					pstmt.setInt(41, sensorDataRequestVO.getBattery_percentage());
					pstmt.setInt(42, sensorDataRequestVO.getOnline_powersupply());
					pstmt.setInt(43, sensorDataRequestVO.getAlarms().getGsm_status());
					pstmt.setInt(44, sensorDataRequestVO.getAlarms().getEthernet_status());
					pstmt.setInt(45, sensorDataRequestVO.getAlarms().getNfc_status());
					pstmt.setInt(46, sensorDataRequestVO.getAlarms().getFlash_status());
					pstmt.setInt(47, sensorDataRequestVO.getAlarms().getNfc_memory_status());
					pstmt.setInt(48, sensorDataRequestVO.getAlarms().getFlash_memory_status());
					pstmt.setInt(49, sensorDataRequestVO.getAlarms().getLow_gsm());
					pstmt.setInt(50, sensorDataRequestVO.getAlarms().getLow_battery());
					pstmt.setInt(51, sensorDataRequestVO.getAlarms().getSensor_detachment());
					pstmt.setInt(52, sensorDataRequestVO.getAlarms().getDoor_open_switch());
					pstmt.setInt(53, sensorDataRequestVO.getAlarms().getMagnetic_tamper());
					
					DateFormat obj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date res = new Date(sensorDataRequestVO.getTimestamp());   
					
					pstmt.setString(54, obj.format(res));

					if (pstmt.executeUpdate() > 0) {
						
						
						
						result = "Success";
						
					}
					
		}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public List<SensorDashboardResponseVO> getSensorDashboarddetails() throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List<SensorDashboardResponseVO> sensorDashboardList = null;
		SensorDashboardResponseVO sensorDashboardResponseVO = null;
		String mainquery = "";
		
		try {
			con = getConnection();
			sensorDashboardList = new LinkedList<SensorDashboardResponseVO>();
			
			mainquery = "SELECT c.CommunityName, b.BlockName, cd.HouseNumber, cd.FirstName, cd.LastName, cd.CustomerUniqueID, cd.CustomerID, cmd.MIUID FROM customerdetails AS cd LEFT JOIN community AS c ON cd.CommunityID = c.CommunityID LEFT JOIN block AS b ON b.BlockID = cd.BlockID LEFT JOIN customermeterdetails cmd ON cmd.CustomerID = cd.CustomerID WHERE cd.ActiveStatus = 2";
				
			pstmt = con.prepareStatement(mainquery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				sensorDashboardResponseVO = new SensorDashboardResponseVO();
				
				sensorDashboardResponseVO.setCommunityName(rs.getString("CommunityName"));
				sensorDashboardResponseVO.setBlockName(rs.getString("BlockName"));
				sensorDashboardResponseVO.setHouseNumber(rs.getString("HouseNumber"));
				sensorDashboardResponseVO.setFirstName(rs.getString("FirstName"));
				sensorDashboardResponseVO.setLastName(rs.getString("LastName"));
				sensorDashboardResponseVO.setCustomerUniqueID(rs.getString("CustomerUniqueID"));
				sensorDashboardResponseVO.setEquipment_serial_id(rs.getString("MIUID"));
				
				pstmt1 = con.prepareStatement("SELECT * FROM sensorlog WHERE CustomerUniqueID = '"+ sensorDashboardResponseVO.getCustomerUniqueID() + "' AND equipment_serial_id = '"+sensorDashboardResponseVO.getEquipment_serial_id() +"' ORDER BY LogDate DESC LIMIT 0,1");
				rs1 = pstmt1.executeQuery();
				
				if(rs1.next()) {
					
					sensorDashboardResponseVO.setReadingID(rs1.getInt("ReadingID"));
					sensorDashboardResponseVO.setReading1(rs1.getInt("reading1"));
					sensorDashboardResponseVO.setReading2(rs1.getInt("reading2"));
					sensorDashboardResponseVO.setReading3(rs1.getInt("reading3"));
					sensorDashboardResponseVO.setReading4(rs1.getInt("reading4"));
					sensorDashboardResponseVO.setReader_sensor_status1(rs1.getInt("reader_sensor_status1") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setReader_sensor_status2(rs1.getInt("reader_sensor_status2") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setReader_sensor_status3(rs1.getInt("reader_sensor_status3") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setReader_sensor_status4(rs1.getInt("reader_sensor_status4") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setPer_day_flow_rate1(rs1.getFloat("per_day_flow_rate1"));
					sensorDashboardResponseVO.setPer_day_flow_rate2(rs1.getFloat("per_day_flow_rate2"));
					sensorDashboardResponseVO.setPer_day_flow_rate3(rs1.getFloat("per_day_flow_rate3"));
					sensorDashboardResponseVO.setPer_day_flow_rate4(rs1.getFloat("per_day_flow_rate4"));
					sensorDashboardResponseVO.setLive_flow_rate1(rs1.getFloat("live_flow_rate1"));
					sensorDashboardResponseVO.setLive_flow_rate2(rs1.getFloat("live_flow_rate2"));
					sensorDashboardResponseVO.setLive_flow_rate3(rs1.getFloat("live_flow_rate3"));
					sensorDashboardResponseVO.setLive_flow_rate4(rs1.getFloat("live_flow_rate4"));
					sensorDashboardResponseVO.setRecord_interval(rs1.getInt("record_interval"));
					sensorDashboardResponseVO.setSync_interval(rs1.getInt("sync_interval"));
					sensorDashboardResponseVO.setRssi(rs1.getInt("rssi"));
					sensorDashboardResponseVO.setDigital_outputs1(rs1.getInt("digital_outputs1") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setDigital_outputs2(rs1.getInt("digital_outputs2") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setDigital_outputs3(rs1.getInt("digital_outputs3") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setDigital_outputs4(rs1.getInt("digital_outputs4") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setAnalog_inputs1(rs1.getFloat("analog_inputs1"));
					sensorDashboardResponseVO.setAnalog_inputs2(rs1.getFloat("analog_inputs2"));
					sensorDashboardResponseVO.setAnalog_inputs3(rs1.getFloat("analog_inputs3"));
					sensorDashboardResponseVO.setAnalog_inputs4(rs1.getFloat("analog_inputs4"));
					sensorDashboardResponseVO.setAnalog_outputs1(rs1.getFloat("analog_outputs1"));
					sensorDashboardResponseVO.setAnalog_outputs2(rs1.getFloat("analog_outputs2"));
					sensorDashboardResponseVO.setAnalog_outputs3(rs1.getFloat("analog_outputs3"));
					sensorDashboardResponseVO.setAnalog_outputs4(rs1.getFloat("analog_outputs4"));
					sensorDashboardResponseVO.setVoltage_outputs1(rs1.getInt("voltage_outputs1"));
					sensorDashboardResponseVO.setVoltage_outputs2(rs1.getInt("voltage_outputs2"));
					sensorDashboardResponseVO.setVoltage_outputs3(rs1.getInt("voltage_outputs3"));
					sensorDashboardResponseVO.setVoltage_outputs4(rs1.getInt("voltage_outputs4"));
					sensorDashboardResponseVO.setBattery_percentage(rs1.getInt("battery_percentage"));
					sensorDashboardResponseVO.setOnline_powersupply(rs1.getInt("online_powersupply") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setGsm_status(rs1.getInt("gsm_status") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setEthernet_status(rs1.getInt("ethernet_status") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setNfc_status(rs1.getInt("nfc_status") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setFlash_status(rs1.getInt("flash_status") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setNfc_memory_status(rs1.getInt("nfc_memory_status") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setFlash_memory_status(rs1.getInt("flash_memory_status") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setLow_gsm(rs1.getInt("low_gsm") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setLow_battery(rs1.getInt("low_battery") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setSensor_detachment(rs1.getInt("sensor_detachment") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setDoor_open_switch(rs1.getInt("door_open_switch") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setMagnetic_tamper(rs1.getInt("magnetic_tamper") == 0 ? "False" : "True");
					sensorDashboardResponseVO.setTimestamp(rs1.getString("timestamp"));
					sensorDashboardResponseVO.setLogDate(ExtraMethodsDAO.datetimeformatter(rs1.getString("LogDate")));
					
				}
				sensorDashboardList.add(sensorDashboardResponseVO);
				sensorDashboardList.removeIf(e -> e.getReadingID()==0);
				
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return sensorDashboardList;
	}

}
