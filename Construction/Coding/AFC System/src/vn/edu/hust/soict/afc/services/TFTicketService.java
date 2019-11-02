package vn.edu.hust.soict.afc.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import vn.edu.hust.soict.afc.common.BaseDataClient;
import vn.edu.hust.soict.afc.entities.TwentyFourTicket;


public class TFTicketService {
	
	private static BaseDataClient client = new BaseDataClient();
	
	public static TwentyFourTicket getTicketInfo(String ticketId) {
		TwentyFourTicket tft = null;
		String sql = "SELECT valid_time, checkedIn FROM twentyfour_ticket WHERE id = ?";
		try {
			client.open();
			PreparedStatement ps = client.getConnection().prepareStatement(sql);
			ps.setString(1, ticketId);
			
			ResultSet rs = ps.executeQuery();

			if (rs.first()) {
				tft = new TwentyFourTicket();
				tft.setId(ticketId);
				tft.setValidTime(rs.getTimestamp("valid_time"));
				tft.setCheckedIn(rs.getBoolean("checked_in"));
			}
		} catch (SQLException e) {
			/* Ignore */
		}
		return tft;
	}

	public static void updateFirstTime(String ticketId, Timestamp theTime, boolean checkedIn) {
		int isCheckedIn = checkedIn ? 1 : 0;
		String sql = "UPDATE tf_ticket SET valid_time = ?, checked_in = ? WHERE ticket_id = ?";
		try {
			client.open();
			PreparedStatement ps = client.getConnection().prepareStatement(sql);
			ps.setTimestamp(1, theTime);
			ps.setInt(2, isCheckedIn);
			ps.setString(3, ticketId);

			ps.executeUpdate();
		} catch (SQLException e) {
			/* Ignore */
		}
	}

	public static void updateTicket(String ticketId, boolean checkedIn) {
		int isCheckedIn = checkedIn ? 1 : 0;
		String sql = "UPDATE tf_ticket SET checked_in = ? WHERE ticket_id = ?";
		try {
			client.open();
			PreparedStatement ps = client.getConnection().prepareStatement(sql);
			ps.setInt(1, isCheckedIn);
			ps.setString(2, ticketId);

			ps.executeUpdate();
		} catch (SQLException e) {
			/* Ignore */
		}
	}
}
