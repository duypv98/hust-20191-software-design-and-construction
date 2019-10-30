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
	 * @param ticketCode
	 * @return OneWayTicket
	 * @throws SQLException
	 */
	public static OneWayTrip getTripInfo(int id) throws SQLException {
		OneWayTrip oneWayTrip = null;
		String sql = "SELECT ticket_id, income_station_id, income_time, outcome_station_id, outcome_time, real_fare, ontrip FROM oneway_trip WHERE id = " + id;

		client.open();
		PreparedStatement ps = client.getConnection().prepareStatement(sql);
//		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();

		if (rs.first()) {
			oneWayTrip = new OneWayTrip();
			oneWayTrip.setId(id);
			oneWayTrip.setTicketId(rs.getString("ticket_id"));
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
        		+ "VALUES('" 
        		+ ticketId + "', '"
        		+ incomeStationId + "', '"
        		+ incomeTime + "', '"
        		+ outcomeStationId + "', '"
        		+ outcomeTime + "', '"
        		+ realFare + "', '"
        		+ onTripInt + "')";
        
        client.open();
		PreparedStatement ps = client.getConnection().prepareStatement(sql);
		ps.executeUpdate();
    }
	
	public static void updateTrip(int id, int outcomeStationId, Timestamp outcomeTime, double realFare, boolean onTrip) 
			throws SQLException {
		int onTripInt = 0;
		
		if(onTrip) {
			onTripInt = 1;
		}
		
        String sql = "UPDATE oneway_trip SET " 
	    		+ "outcome_station_id='" + outcomeStationId + "', "
	    		+ "outcome_time='" + outcomeTime + "', "
	    		+ "real_fare='" + realFare + "', "
	    		+ "ontrip='" + onTripInt + "' WHERE "
	    		+ "id='" + id + "'";
        
        client.open();
		PreparedStatement ps = client.getConnection().prepareStatement(sql);
		ps.executeUpdate();
    }
	
	public static void main(String[] args) throws SQLException {
//		Timestamp incomeTime = new Timestamp(new Date().getTime());
//		Timestamp outcomeTime = new Timestamp(new Date().getTime() + 3600000);
//		
//		insertTrip("OW201910300000", 1, incomeTime, 3, outcomeTime, 10.0, true);
		OneWayTrip trip = getTripInfo(1);
		updateTrip(1, trip.getOutcomeStationId(), trip.getOutcomeTime(), 12.0, false);
	}

}
