/**
 * 
 */
package vn.edu.hust.soict.afc.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import vn.edu.hust.soict.afc.common.BaseDataClient;
import vn.edu.hust.soict.afc.entities.PrepaidTrip;

/**
 * @author Professor
 *
 */
public class PPTripService {
	
	private static BaseDataClient client = new BaseDataClient();
	
	public static PrepaidTrip getTripInfo(String cardId) {
		PrepaidTrip prepaidTrip = null;
		String sql = "SELECT id, income_station_id, income_time, outcome_station_id, outcome_time, real_fare, ontrip FROM prepaid_trip WHERE card_id = ? AND ontrip = 1";
		
		try {
			client.open();
			PreparedStatement ps = client.getConnection().prepareStatement(sql);
			ps.setString(1, cardId);
			
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				prepaidTrip = new PrepaidTrip();
				prepaidTrip.setId(rs.getInt("id"));
				prepaidTrip.setIncomeStationId(rs.getInt("income_station_id"));
				prepaidTrip.setIncomeTime(rs.getTimestamp("income_time"));
				prepaidTrip.setOutcomeStationId(rs.getInt("outcome_station_id"));
				prepaidTrip.setOutcomeTime(rs.getTimestamp("outcome_time"));
				prepaidTrip.setRealFare(rs.getDouble("real_fare"));
				prepaidTrip.setOnTrip(rs.getBoolean("ontrip"));
			}
		} catch (SQLException e) {
			/* Ignore */
		}
		return prepaidTrip;
	}
	
	public static void createTrip(String ticketId, int incomeStationId, Timestamp incomeTime, boolean onTrip) {
		int isOnTrip = onTrip ? 1 : 0;
		String sql = "INSERT INTO prepaid_trip (ticket_id, income_station_id, income_time, ontrip)"
				+ "VALUES(?, ?, ?, ?)";

		try {
			client.open();
			PreparedStatement ps = client.getConnection().prepareStatement(sql);
			ps.setString(1, ticketId);
			ps.setInt(2, incomeStationId);
			ps.setTimestamp(3, incomeTime);
			ps.setInt(4, isOnTrip);

			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Can't create OW Trip due to SQL Error");
		}
	}
	
	public static void updateTrip(int id, int outcomeStationId, Timestamp outcomeTime, double realFare,
			boolean onTrip) {
		int isOnTrip = onTrip ? 1 : 0;

		String sql = "UPDATE tf_trip SET " + "outcome_station_id=?, " + "outcome_time=?, " + "real_fare=?, "
				+ "ontrip=?" + " WHERE " + "id=? AND ontrip = ?";

		try {
			client.open();
			PreparedStatement ps = client.getConnection().prepareStatement(sql);
			ps.setInt(1, outcomeStationId);
			ps.setTimestamp(2, outcomeTime);
			ps.setDouble(3, realFare);
			ps.setInt(4, isOnTrip);
			ps.setInt(5, id);
			ps.setBoolean(6, true);

			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Can't update due to SQL Error");
		}
	}
}
