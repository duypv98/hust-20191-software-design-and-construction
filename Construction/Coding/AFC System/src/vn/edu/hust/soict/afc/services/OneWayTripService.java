package vn.edu.hust.soict.afc.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import vn.edu.hust.soict.afc.common.BaseDataClient;
import vn.edu.hust.soict.afc.entities.OneWayTrip;

public class OneWayTripService {

	private static BaseDataClient client = new BaseDataClient();
	
	/**
	 * 
	 * @param ticketId
	 * @return OneWayTrip
	 * @throws SQLException
	 */
	public static OneWayTrip getTripInfo(String ticketId) throws SQLException {
		OneWayTrip oneWayTrip = null;
		String sql = "SELECT id, income_station_id, income_time, outcome_station_id, outcome_time, real_fare, ontrip FROM oneway_trip WHERE ticket_id = ?";

		client.open();
		PreparedStatement ps = client.getConnection().prepareStatement(sql);
		ps.setString(1, ticketId);

		ResultSet rs = ps.executeQuery();

		if (rs.first()) {
			oneWayTrip = new OneWayTrip();
			oneWayTrip.setId(rs.getInt("id"));
			oneWayTrip.setTicketId(ticketId);
			oneWayTrip.setIncomeStationId(rs.getInt("income_station_id"));
			oneWayTrip.setIncomeTime(rs.getTimestamp("income_time"));
			oneWayTrip.setOutcomeStationId(rs.getInt("outcome_station_id"));
			oneWayTrip.setOutcomeTime(rs.getTimestamp("outcome_time"));
			oneWayTrip.setRealFare(rs.getDouble("real_fare"));
			oneWayTrip.setOnTrip(rs.getBoolean("ontrip"));
		}
//		client.close();
		return oneWayTrip;
	}
	
	public static void insertTrip(String ticketId, int incomeStationId, Timestamp incomeTime, int outcomeStationId,
			Timestamp outcomeTime, double realFare, boolean onTrip) throws SQLException {
		int onTripInt = 0;
		
		if(onTrip) {
			onTripInt = 1;
		}
		
        String sql = "INSERT INTO oneway_trip (ticket_id, income_station_id, income_time, outcome_station_id, outcome_time, real_fare, ontrip)"
        		+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        client.open();
		PreparedStatement ps = client.getConnection().prepareStatement(sql);
		ps.setString(1, ticketId);
		ps.setInt(2, incomeStationId);
		ps.setTimestamp(3, incomeTime);
		ps.setInt(4, outcomeStationId);
		ps.setTimestamp(5, outcomeTime);
		ps.setDouble(6, realFare);
		ps.setInt(7, onTripInt);
		
		ps.executeUpdate();
    }
	
	public static void updateTrip(int id, int outcomeStationId, Timestamp outcomeTime, double realFare, boolean onTrip) 
			throws SQLException {
		int onTripInt = 0;
		
		if(onTrip) {
			onTripInt = 1;
		}
		
        String sql = "UPDATE oneway_trip SET " 
	    		+ "outcome_station_id=?, "
	    		+ "outcome_time=?, "
	    		+ "real_fare=?, "
	    		+ "ontrip=?" + " WHERE "
	    		+ "id=?";
        
        client.open();
		PreparedStatement ps = client.getConnection().prepareStatement(sql);
		ps.setInt(1, outcomeStationId);
		ps.setTimestamp(2, outcomeTime);
		ps.setDouble(3, realFare);
		ps.setInt(4, onTripInt);
		ps.setInt(5, id);
		
		ps.executeUpdate();
    }
	
	public static void main(String[] args) throws SQLException {
//		Timestamp incomeTime = new Timestamp(new Date().getTime());
//		Timestamp outcomeTime = new Timestamp(new Date().getTime() + 3600000);
//		
//		insertTrip("OW201910300001", 1, incomeTime, 2, outcomeTime, 5.0, true);
		OneWayTrip trip = getTripInfo("OW201910300001");
//		updateTrip(2, trip.getOutcomeStationId(), trip.getOutcomeTime(), 8.5, false);
		System.out.println(trip.getId());
	}

}
