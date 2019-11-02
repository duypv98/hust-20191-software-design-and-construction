package vn.edu.hust.soict.afc.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
