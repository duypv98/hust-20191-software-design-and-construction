package vn.edu.hust.soict.afc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.edu.hust.soict.afc.entities.OneWayTrip;
import vn.edu.hust.soict.afc.utils.ConnectionUtils;

public class OWTripDAOImpl implements OWTripDAO {

	/**
	 * 
	 * @param ticketId
	 * @return oneway trip from ticketId
	 */
	@Override
	public OneWayTrip findByTicketId(String ticketId) {
		OneWayTrip oneWayTrip = null;
		String sql = "SELECT id, income_station_id, income_time, outcome_station_id, outcome_time, real_fare, ontrip FROM oneway_trip WHERE ticket_id = ? AND ontrip = ?";

		try {
			Connection conn = ConnectionUtils.getMyConnection();;
			PreparedStatement ps = conn.prepareStatement(sql);
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
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return oneWayTrip;
	}

	@Override
	public boolean save(OneWayTrip oneWayTrip) {
		String sql = "INSERT INTO oneway_trip (ticket_id, income_station_id, income_time, outcome_station_id, outcome_time, real_fare, ontrip) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			Connection conn = ConnectionUtils.getMyConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			if (oneWayTrip.getTicketId() == null) {
				ps.setNull(1, java.sql.Types.VARCHAR);
			} else {
				ps.setString(1, oneWayTrip.getTicketId());
			}
			
			if (oneWayTrip.getIncomeStationId() == 0) {
				ps.setNull(2, java.sql.Types.INTEGER);
			} else {
				ps.setInt(2, oneWayTrip.getIncomeStationId());
			}
			
			if (oneWayTrip.getIncomeTime() == null) {
				ps.setNull(3, java.sql.Types.TIMESTAMP);
			} else {
				ps.setTimestamp(3, oneWayTrip.getIncomeTime());
			}
			
			if (oneWayTrip.getOutcomeStationId() == 0) {
				ps.setNull(4, java.sql.Types.INTEGER);
			} else {
				ps.setInt(4, oneWayTrip.getOutcomeStationId());
			}
			
			if (oneWayTrip.getOutcomeTime() == null) {
				ps.setNull(5, java.sql.Types.TIMESTAMP);
			} else {
				ps.setTimestamp(5, oneWayTrip.getOutcomeTime());
			}
			
			if (oneWayTrip.getRealFare() == 0.0) {
				ps.setNull(6, java.sql.Types.TIMESTAMP);
			} else {
				ps.setTimestamp(6, oneWayTrip.getOutcomeTime());
			}
			
			ps.setBoolean(7, oneWayTrip.isOnTrip());
			
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
	 * @param id
	 * @param outcomeStationId
	 * @param outcomeTime
	 * @param realFare
	 * @param onTrip
	 */
	@Override
	public boolean update(OneWayTrip oneWayTrip) {
					String sql = "UPDATE oneway_trip SET ticket_id = ?, income_station_id = ?, income_time = ?, outcome_station_id = ?, outcome_time = ?, real_fare = ?, ontrip = ?"
					+ " WHERE id=?";
			try {
				Connection conn = ConnectionUtils.getMyConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				if (oneWayTrip.getTicketId() == null) {
					ps.setNull(1, java.sql.Types.VARCHAR);
				} else {
					ps.setString(1, oneWayTrip.getTicketId());
				}

				if (oneWayTrip.getIncomeStationId() == 0) {
					ps.setNull(2, java.sql.Types.INTEGER);
				} else {
					ps.setInt(2, oneWayTrip.getIncomeStationId());
				}

				if (oneWayTrip.getIncomeTime() == null) {
					ps.setNull(3, java.sql.Types.TIMESTAMP);
				} else {
					ps.setTimestamp(3, oneWayTrip.getIncomeTime());
				}

				if (oneWayTrip.getOutcomeStationId() == 0) {
					ps.setNull(4, java.sql.Types.INTEGER);
				} else {
					ps.setInt(4, oneWayTrip.getOutcomeStationId());
				}
				if (oneWayTrip.getOutcomeTime() == null) {
					ps.setNull(5, java.sql.Types.TIMESTAMP);
				} else {
					ps.setTimestamp(5, oneWayTrip.getOutcomeTime());
				}
				if (oneWayTrip.getRealFare() == 0.0) {
					ps.setNull(6, java.sql.Types.DOUBLE);
				} else {
					ps.setDouble(6, oneWayTrip.getRealFare());
				}
				ps.setBoolean(7, oneWayTrip.isOnTrip());
				ps.setInt(8, oneWayTrip.getId());
				if (ps.executeUpdate() > 0) {
					return true;
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
}
