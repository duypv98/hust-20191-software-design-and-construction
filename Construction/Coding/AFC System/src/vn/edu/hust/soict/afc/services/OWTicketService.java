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

	private static BaseDataClient client = new BaseDataClient();

	/**
	 * 
	 * @param ticketCode
	 * @return OneWayTicket
	 * @throws SQLException
	 */
	public static OneWayTicket getTicketInfo(String ticketId) {
		OneWayTicket owt = null;
		String sql = "SELECT embarkation_id, disembarkation_id, checked_in, fare, activated FROM oneway_ticket WHERE id = ?";
		try {
			client.open();
			PreparedStatement ps = client.getConnection().prepareStatement(sql);
			ps.setString(1, ticketId);

			ResultSet rs = ps.executeQuery();

			if (rs.first()) {
				owt = new OneWayTicket();
				owt.setId(ticketId);
				owt.setEmbarkationId(rs.getInt("embarkation_id"));
				owt.setDisembarkationId(rs.getInt("disembarkation_id"));
				owt.setCheckedIn(rs.getBoolean("checked_in"));
				owt.setFare(rs.getDouble("fare"));
				owt.setActivated(rs.getBoolean("activated"));
			}
		} catch (SQLException e) {
			/* Ignore */
		}
		return owt;
	}

	public static void updateTicket(String ticketId, boolean checkedIn, boolean activated) {
		int isCheckedIn = checkedIn ? 1 : 0;
		int isActivated = activated ? 1 : 0;
		
        String sql = "UPDATE oneway_ticket SET " 
	    		+ "checked_in=?, "
	    		+ "activated=?" + " WHERE "
	    		+ "id=?";
        
        try {
        	client.open();
    		PreparedStatement ps = client.getConnection().prepareStatement(sql);
    		ps.setInt(1, isCheckedIn);
    		ps.setInt(2, isActivated);
    		ps.setString(3, ticketId);
    		
    		ps.executeUpdate();
        } catch (SQLException e) {
			System.err.println("Can't update due to SQL Error");
		}
	}
}
