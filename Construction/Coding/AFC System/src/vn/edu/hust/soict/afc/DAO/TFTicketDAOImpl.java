package vn.edu.hust.soict.afc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.edu.hust.soict.afc.entities.TwentyFourTicket;
import vn.edu.hust.soict.afc.utils.ConnectionUtils;


public class TFTicketDAOImpl implements TFTicketDAO {
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public TwentyFourTicket findById(String id) {
		TwentyFourTicket tft = null;
		String sql = "SELECT id, ticket_code, valid_time, checked_in FROM tf_ticket WHERE id = ?";
		try {
			Connection conn = ConnectionUtils.getMyConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();

			if (rs.first()) {
				tft = mapToTwentyFourTicket(rs);
			}
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return tft;
	}



	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private TwentyFourTicket mapToTwentyFourTicket(ResultSet rs) throws SQLException {
		TwentyFourTicket tft;
		tft = new TwentyFourTicket();
		tft.setId(rs.getString("id"));
		tft.setTicketCode(rs.getString("ticket_code"));
		tft.setValidTime(rs.getTimestamp("valid_time"));
		tft.setCheckedIn(rs.getBoolean("checked_in"));
		return tft;
	}
	
	

	@Override
	public boolean update(TwentyFourTicket twentyFourTicket) {
		String sql = "UPDATE tf_ticket SET ticket_code = ?, valid_time = ?, checked_in = ? WHERE id = ?";
		try {
			Connection conn = ConnectionUtils.getMyConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, twentyFourTicket.getTicketCode());
			ps.setTimestamp(2, twentyFourTicket.getValidTime());
			ps.setBoolean(3, twentyFourTicket.isCheckedIn());
			ps.setString(4, twentyFourTicket.getId());

			if (ps.executeUpdate() > 0) {
				return true;
			};
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param ticketCode
	 * @return
	 */
	@Override
	public TwentyFourTicket findByTicketCode(String ticketCode) {
		String sql = "SELECT id, ticket_code, valid_time, checked_in FROM tf_ticket WHERE ticket_code = ?";
		TwentyFourTicket twentyFourTicket = null;
		Connection conn = null;
		try {
			conn = ConnectionUtils.getMyConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ticketCode);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				twentyFourTicket = mapToTwentyFourTicket(rs);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return twentyFourTicket;
	}
}
