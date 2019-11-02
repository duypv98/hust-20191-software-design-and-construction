package vn.edu.hust.soict.afc.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import vn.edu.hust.soict.afc.common.BaseDataClient;
import vn.edu.hust.soict.afc.entities.TwentyFourTrip;

public class TwentyFourTripService {
	
	private static BaseDataClient client = new BaseDataClient();
	
	/**
	 * 
	 * @param ticketId
	 * @return TwentyFourTrip
	 * @throws SQLException
	 */
	public static TwentyFourTrip getTripInfo(String ticketId) throws SQLException {
		TwentyFourTrip twentyFourTrip = null;
		String sql = "SELECT id, income_station_id, income_time, outcome_station_id, outcome_time, ontrip FROM twentyfour_trip WHERE ticket_id = ?";
		try {
			client.open();
			PreparedStatement ps = client.getConnection().prepareStatement(sql);
			ps.setString(1, ticketId);

			ResultSet rs = ps.executeQuery();

			if (rs.first()) {
				twentyFourTrip = new TwentyFourTrip();
				twentyFourTrip.setId(rs.getInt("id"));
				twentyFourTrip.setTicketId(ticketId);
				twentyFourTrip.setIncomeStationId(rs.getInt("income_station_id"));
				twentyFourTrip.setIncomeTime(rs.getTimestamp("income_time"));
				twentyFourTrip.setOutcomeStationId(rs.getInt("outcome_station_id"));
				twentyFourTrip.setOutcomeTime(rs.getTimestamp("outcome_time"));
				twentyFourTrip.setOnTrip(rs.getBoolean("ontrip"));
				}
			}
		catch (SQLException e) {
			/* Ignore */
		}
		return twentyFourTrip;
	}
	
	public static void insertTrip(String ticketId, int incomeStationId, Timestamp incomeTime, int outcomeStationId,
			Timestamp outcomeTime, boolean onTrip) throws SQLException {
		int onTripInt = 0;
		
		if(onTrip) {
			onTripInt = 1;
		}
		
        String sql = "INSERT INTO twentyfour_trip (ticket_id, income_station_id, income_time, outcome_station_id, outcome_time, ontrip)"
        		+ "VALUES(?, ?, ?, ?, ?, ?)";
        
        client.open();
		PreparedStatement ps = client.getConnection().prepareStatement(sql);
		ps.setString(1, ticketId);
		ps.setInt(2, incomeStationId);
		ps.setTimestamp(3, incomeTime);
		ps.setInt(4, outcomeStationId);
		ps.setTimestamp(5, outcomeTime);
		ps.setInt(6, onTripInt);
		
		ps.executeUpdate();
    }
	
	public static void updateTrip(int id, int outcomeStationId, Timestamp outcomeTime, boolean onTrip) 
			throws SQLException {
		int onTripInt = 0;
		
		if(onTrip) {
			onTripInt = 1;
		}
		
        String sql = "UPDATE twentyfour_trip SET " 
	    		+ "outcome_station_id=?, "
	    		+ "outcome_time=?, "
	    		+ "ontrip=?" + " WHERE "
	    		+ "id=?";
        
        client.open();
		PreparedStatement ps = client.getConnection().prepareStatement(sql);
		ps.setInt(1, outcomeStationId);
		ps.setTimestamp(2, outcomeTime);
		ps.setInt(3, onTripInt);
		ps.setInt(4, id);
		
		ps.executeUpdate();
    }
	
	public static void main(String[] args) throws SQLException {
//		Timestamp incomeTime = new Timestamp(new Date().getTime());
//		Timestamp outcomeTime = new Timestamp(new Date().getTime() + 3600000);
//		
//		insertTrip("TF201911020001", 1, incomeTime, 2, outcomeTime, 5.0, true);
		TwentyFourTrip trip = getTripInfo("TF201911020001");
//		updateTrip(2, trip.getOutcomeStationId(), trip.getOutcomeTime(), 8.5, false);
		System.out.println(trip.getId());
	}
}


