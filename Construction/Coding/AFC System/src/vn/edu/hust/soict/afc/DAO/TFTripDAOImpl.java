package vn.edu.hust.soict.afc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.edu.hust.soict.afc.entities.TwentyFourTrip;
import vn.edu.hust.soict.afc.utils.ConnectionUtils;

public class TFTripDAOImpl implements TFTripDAO {

	/**
	 * 
	 * @param ticketId
	 * @return TwentyFourTrip
	 * @throws SQLException
	 */
	@Override
	public TwentyFourTrip findByTicketIdAndOnTrip(String ticketId, boolean onTrip) {
		TwentyFourTrip twentyFourTrip = null;
		String sql = "SELECT * FROM tf_trip WHERE ticket_id = ? AND ontrip = ?";
		try {
			Connection conn = ConnectionUtils.getMyConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ticketId);
			ps.setBoolean(2, onTrip);

			ResultSet rs = ps.executeQuery();

			if (rs.first()) {
				twentyFourTrip = new TwentyFourTrip();
				twentyFourTrip.setId(rs.getInt("id"));
				twentyFourTrip.setTicketId(rs.getString("ticket_id"));
				twentyFourTrip.setIncomeStationId(rs.getInt("income_station_id"));
				twentyFourTrip.setIncomeTime(rs.getTimestamp("income_time"));
				twentyFourTrip.setOutcomeStationId(rs.getInt("outcome_station_id"));
				twentyFourTrip.setOutcomeTime(rs.getTimestamp("outcome_time"));
				twentyFourTrip.setOnTrip(rs.getBoolean("ontrip"));
			}
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return twentyFourTrip;
	}

	/**
	 * 
	 * @param twentyFourTrip
	 * @return
	 */
	@Override
	public boolean save(TwentyFourTrip twentyFourTrip) {
		String sql = "INSERT INTO twentyfour_trip (ticket_id, income_station_id, income_time, outcome_station_id, outcome_time, ontrip)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";

		try {
			Connection conn = ConnectionUtils.getMyConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			if (twentyFourTrip.getTicketId() == null) {
				ps.setNull(1, java.sql.Types.VARCHAR);
			} else {
				ps.setString(1, twentyFourTrip.getTicketId());
			}
			
			if (twentyFourTrip.getIncomeStationId() == 0) {
				ps.setNull(2, java.sql.Types.INTEGER);
			} else {
				ps.setInt(2, twentyFourTrip.getIncomeStationId());
			}

			if (twentyFourTrip.getIncomeTime() == null) {
				ps.setNull(3, java.sql.Types.TIMESTAMP);
			} else {
				ps.setTimestamp(3, twentyFourTrip.getIncomeTime());
			}

			if (twentyFourTrip.getOutcomeStationId() == 0) {
				ps.setNull(4, java.sql.Types.INTEGER);
			} else {
				ps.setInt(4, twentyFourTrip.getOutcomeStationId());
			}
			if (twentyFourTrip.getOutcomeTime() == null) {
				ps.setNull(5, java.sql.Types.TIMESTAMP);
			} else {
				ps.setTimestamp(5, twentyFourTrip.getOutcomeTime());
			}
			
			ps.setBoolean(6, twentyFourTrip.isOnTrip());

			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param twentyFourTrip
	 * @return
	 */
	@Override
	public boolean update(TwentyFourTrip twentyFourTrip) {
		
		String sql = "UPDATE twentyfour_trip SET ticket_id = ?, income_station_id = ?, income_time = ?, outcome_station_id = ?, outcome_time = ?, ontrip = ?"
				+ " WHERE id=?";

		try {
			Connection conn = ConnectionUtils.getMyConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			if (twentyFourTrip.getTicketId() == null) {
				ps.setNull(1, java.sql.Types.VARCHAR);
			} else {
				ps.setString(1, twentyFourTrip.getTicketId());
			}
			
			if (twentyFourTrip.getIncomeStationId() == 0) {
				ps.setNull(2, java.sql.Types.INTEGER);
			} else {
				ps.setInt(2, twentyFourTrip.getIncomeStationId());
			}

			if (twentyFourTrip.getIncomeTime() == null) {
				ps.setNull(3, java.sql.Types.TIMESTAMP);
			} else {
				ps.setTimestamp(3, twentyFourTrip.getIncomeTime());
			}

			if (twentyFourTrip.getOutcomeStationId() == 0) {
				ps.setNull(4, java.sql.Types.INTEGER);
			} else {
				ps.setInt(4, twentyFourTrip.getOutcomeStationId());
			}
			if (twentyFourTrip.getOutcomeTime() == null) {
				ps.setNull(5, java.sql.Types.TIMESTAMP);
			} else {
				ps.setTimestamp(5, twentyFourTrip.getOutcomeTime());
			}
			
			ps.setBoolean(6, twentyFourTrip.isOnTrip());
			ps.setInt(7, twentyFourTrip.getId());
			
			if (ps.executeUpdate() > 0) {
				return true;
			}
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
}
