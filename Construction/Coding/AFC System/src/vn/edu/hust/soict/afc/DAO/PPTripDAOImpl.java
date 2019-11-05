/**
 *
 */
package vn.edu.hust.soict.afc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.edu.hust.soict.afc.entities.PrepaidTrip;
import vn.edu.hust.soict.afc.utils.ConnectionUtils;

/**
 * @author Professor
 *
 */
public class PPTripDAOImpl implements PPTripDAO {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * vn.edu.hust.soict.afc.DAO.PPTripDAO#findByCardIdAndOnTrip(java.lang.String,
	 * boolean)
	 */
	@Override
	public PrepaidTrip findByCardIdAndOnTrip(String cardId, boolean onTrip) {
		String sql = "SELECT * FROM prepaid_trip WHERE card_id = ? AND ontrip = ?";
		PrepaidTrip prepaidTrip = null;

		try {
			Connection conn = ConnectionUtils.getMyConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cardId);
			ps.setBoolean(2, onTrip);

			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				prepaidTrip = new PrepaidTrip();
				prepaidTrip.setId(rs.getInt("id"));
				prepaidTrip.setCardId(rs.getString("card_id"));
				prepaidTrip.setIncomeStationId(rs.getInt("income_station_id"));
				prepaidTrip.setIncomeTime(rs.getTimestamp("income_time"));
				prepaidTrip.setOutcomeStationId(rs.getInt("outcome_station_id"));
				prepaidTrip.setOutcomeTime(rs.getTimestamp("outcome_time"));
				prepaidTrip.setRealFare(rs.getDouble("real_fare"));
				prepaidTrip.setOnTrip(rs.getBoolean("ontrip"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return prepaidTrip;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see vn.edu.hust.soict.afc.DAO.PPTripDAO#save(vn.edu.hust.soict.afc.entities.
	 * PrepaidTrip)
	 */
	@Override
	public boolean save(PrepaidTrip prepaidTrip) {
		String sql = "INSERT INTO prepaid_trip (card_id, income_station_id, income_time, outcome_station_id, outcome_time, real_fare, ontrip) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			Connection conn = ConnectionUtils.getMyConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			if (prepaidTrip.getCardId() == null) {
				ps.setNull(1, java.sql.Types.VARCHAR);
			} else {
				ps.setString(1, prepaidTrip.getCardId());
			}

			if (prepaidTrip.getIncomeStationId() == 0) {
				ps.setNull(2, java.sql.Types.INTEGER);
			} else {
				ps.setInt(2, prepaidTrip.getIncomeStationId());
			}

			if (prepaidTrip.getIncomeTime() == null) {
				ps.setNull(3, java.sql.Types.TIMESTAMP);
			} else {
				ps.setTimestamp(3, prepaidTrip.getIncomeTime());
			}

			if (prepaidTrip.getOutcomeStationId() == 0) {
				ps.setNull(4, java.sql.Types.INTEGER);
			} else {
				ps.setInt(4, prepaidTrip.getOutcomeStationId());
			}
			if (prepaidTrip.getOutcomeTime() == null) {
				ps.setNull(5, java.sql.Types.TIMESTAMP);
			} else {
				ps.setTimestamp(5, prepaidTrip.getOutcomeTime());
			}
			if (prepaidTrip.getRealFare() == 0.0) {
				ps.setNull(6, java.sql.Types.DOUBLE);
			} else {
				ps.setDouble(6, prepaidTrip.getRealFare());
			}
			ps.setBoolean(7, prepaidTrip.isOnTrip());
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * vn.edu.hust.soict.afc.DAO.PPTripDAO#update(vn.edu.hust.soict.afc.entities.
	 * PrepaidTrip)
	 */
	@Override
	public boolean update(PrepaidTrip prepaidTrip) {
		String sql = "UPDATE prepaid_trip SET card_id = ?, income_station_id = ?, income_time = ?, outcome_station_id = ?, outcome_time = ?, real_fare = ?, ontrip = ?"
				+ " WHERE id=?";
		try {
			Connection conn = ConnectionUtils.getMyConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			if (prepaidTrip.getCardId() == null) {
				ps.setNull(1, java.sql.Types.VARCHAR);
			} else {
				ps.setString(1, prepaidTrip.getCardId());
			}

			if (prepaidTrip.getIncomeStationId() == 0) {
				ps.setNull(2, java.sql.Types.INTEGER);
			} else {
				ps.setInt(2, prepaidTrip.getIncomeStationId());
			}

			if (prepaidTrip.getIncomeTime() == null) {
				ps.setNull(3, java.sql.Types.TIMESTAMP);
			} else {
				ps.setTimestamp(3, prepaidTrip.getIncomeTime());
			}

			if (prepaidTrip.getOutcomeStationId() == 0) {
				ps.setNull(4, java.sql.Types.INTEGER);
			} else {
				ps.setInt(4, prepaidTrip.getOutcomeStationId());
			}
			if (prepaidTrip.getOutcomeTime() == null) {
				ps.setNull(5, java.sql.Types.TIMESTAMP);
			} else {
				ps.setTimestamp(5, prepaidTrip.getOutcomeTime());
			}
			if (prepaidTrip.getRealFare() == 0.0) {
				ps.setNull(6, java.sql.Types.DOUBLE);
			} else {
				ps.setDouble(6, prepaidTrip.getRealFare());
			}
			ps.setBoolean(7, prepaidTrip.isOnTrip());
			ps.setInt(8, prepaidTrip.getId());
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
