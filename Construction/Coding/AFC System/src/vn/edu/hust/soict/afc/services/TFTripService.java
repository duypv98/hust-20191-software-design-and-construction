package vn.edu.hust.soict.afc.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import vn.edu.hust.soict.afc.common.BaseDataClient;
import vn.edu.hust.soict.afc.entities.TwentyFourTrip;

public class TFTripService {

	private static BaseDataClient client = new BaseDataClient();

	/**
	 * 
	 * @param ticketId
	 * @return TwentyFourTrip
	 * @throws SQLException
	 */
	public static TwentyFourTrip getTripInfo(String ticketId) {
		TwentyFourTrip twentyFourTrip = null;
		String sql = "SELECT id, income_station_id, income_time, outcome_station_id, outcome_time, ontrip FROM tf_trip WHERE ticket_id = ? AND ontrip = 1";
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
		} catch (SQLException e) {
			/* Ignore */
		}
		return twentyFourTrip;
	}

	public static void createTrip(String ticketId, int incomeStationId, Timestamp incomeTime, boolean onTrip) {
		int isOnTrip = onTrip ? 1 : 0;

		String sql = "INSERT INTO twentyfour_trip (ticket_id, income_station_id, income_time, ontrip)"
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
			/* Ignore */
		}
	}

	public static void updateTrip(int id, int outcomeStationId, Timestamp outcomeTime, boolean onTrip) {
		int isOnTrip = onTrip ? 1 : 0;

		String sql = "UPDATE twentyfour_trip SET outcome_station_id = ?, outcome_time = ?, ontrip = ?"
				+ " WHERE id=? AND ontrip = ?";

		try {
			client.open();
			PreparedStatement ps = client.getConnection().prepareStatement(sql);
			ps.setInt(1, outcomeStationId);
			ps.setTimestamp(2, outcomeTime);
			ps.setInt(3, isOnTrip);
			ps.setInt(4, id);
			ps.setBoolean(5, false);

			ps.executeUpdate();
		} catch (SQLException e) {
			/* Ignore */
		}
	}
}
