/**
 * 
 */
package vn.edu.hust.soict.afc.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.edu.hust.soict.afc.common.BaseDataClient;
import vn.edu.hust.soict.afc.entities.OneWayTicket;

/**
 * @author Professor
 *
 */
public class OWTicketService {

	/**
	 * 
	 */
	private static BaseDataClient client = new BaseDataClient();

	public static OneWayTicket getTicketInfo(String ticketCode) throws SQLException {
		OneWayTicket owt = null;
		String sql = "SELECT id, embarkation_id, disembarkation_id, checked_in, fare, activated FROM oneway_ticket WHERE ticket_code = ?";

		client.open();
		PreparedStatement ps = client.getConnection().prepareStatement(sql);
		ps.setString(1, ticketCode);

		ResultSet rs = ps.executeQuery();

		if (rs.first()) {
			owt = new OneWayTicket();
			owt.setId(rs.getString("id"));
			owt.setEmbarkationId(rs.getInt("embarkation_id"));
			owt.setDisembarkationId(rs.getInt("disembarkation_id"));
			owt.setCheckedIn(rs.getBoolean("checked_in"));
			owt.setFare(rs.getDouble("fare"));
			owt.setActivated(rs.getBoolean("activated"));
		}

		return owt;
	}

}
