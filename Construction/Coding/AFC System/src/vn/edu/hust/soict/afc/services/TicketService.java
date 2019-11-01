/**
 * 
 */
package vn.edu.hust.soict.afc.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.edu.hust.soict.afc.common.BaseDataClient;

/**
 * @author Professor
 *
 */
public class TicketService {

	public static BaseDataClient client = new BaseDataClient();

	/**
	 * 
	 * @param ticketCode
	 * @return ticketId
	 */
	public static String getTicketId(String ticketCode) {
		String ticketId = null;
		String sql = "(SELECT id FROM oneway_ticket WHERE ticket_code = ?) " + "UNION"
				+ "(SELECT id FROM tf_ticket WHERE ticket_code = ?)";
		try {
			client.open();
			PreparedStatement ps = client.connection.prepareStatement(sql);
			ps.setString(1, ticketCode);
			ps.setString(2, ticketCode);

			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				ticketId = rs.getString("id");
			}
		} catch (SQLException e) {
			/* Ignore */
		}
		return ticketId;
	}

}
