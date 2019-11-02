package vn.edu.hust.soict.afc.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import vn.edu.hust.soict.afc.common.BaseDataClient;
import vn.edu.hust.soict.afc.entities.OneWayTrip;

public class OWTripService {

	private static BaseDataClient client = new BaseDataClient();

	/**
	 * 
	 * @param ticketId
	 * @return oneway trip from ticketId
	 */
	public static OneWayTrip getTripInfo(String ticketId) {
		OneWayTrip oneWayTrip = null;
		String sql = "SELECT id, income_station_id, income_time, outcome_station_id, outcome_time, real_fare, ontrip FROM oneway_trip WHERE ticket_id = ? AND ontrip = ?";

		try {
			client.open();
			PreparedStatement ps = client.getConnection().prepareStatement(sql);
			ps.setString(1, ticketId);
			ps.setBoolean(2, true);

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
		} catch (SQLException e) {
			/* Ignore */
		}
		return oneWayTrip;
	}

	public static void createTrip(String ticketId, int incomeStationId, Timestamp incomeTime, boolean onTrip) {
		int isOnTrip = onTrip ? 1 : 0;
		String sql = "INSERT INTO oneway_trip (ticket_id, income_station_id, income_time, ontrip)"
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
	
	/**
	 * 
	 * @param id
	 * @param outcomeStationId
	 * @param outcomeTime
	 * @param realFare
	 * @param onTrip
	 */
	public static void updateTrip(int id, int outcomeStationId, Timestamp outcomeTime, double realFare,
			boolean onTrip) {
		int isOnTrip = onTrip ? 1 : 0;

		String sql = "UPDATE oneway_trip SET " + "outcome_station_id=?, " + "outcome_time=?, " + "real_fare=?, "
				+ "ontrip=?" + " WHERE " + "id=?";

		try {
			client.open();
			PreparedStatement ps = client.getConnection().prepareStatement(sql);
			ps.setInt(1, outcomeStationId);
			ps.setTimestamp(2, outcomeTime);
			ps.setDouble(3, realFare);
			ps.setInt(4, isOnTrip);
			ps.setInt(5, id);;

			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Can't update due to SQL Error");
		}
	}
}
