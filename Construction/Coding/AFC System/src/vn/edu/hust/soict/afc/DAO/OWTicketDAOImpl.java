/**
 * @author duypv
 * @date Nov 14, 2019
 * @project afc_application
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 *
 * @description The Automated Fare Controller sumulation program
 */
package vn.edu.hust.soict.afc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.edu.hust.soict.afc.entities.OneWayTicket;
import vn.edu.hust.soict.afc.utils.ConnectionUtils;

/**
 *
 * @author duypv, duytruong
 * @implSpec OWTicketDAO
 *
 */
public class OWTicketDAOImpl implements OWTicketDAO {

	/**
	 * 
	 * @param id
	 * @return OneWayTicket
	 */
	@Override
	public OneWayTicket findById(String id) {
		OneWayTicket owt = null;
		String sql = "SELECT id, ticket_code, embarkation_id, disembarkation_id, checked_in, fare, activated FROM oneway_ticket WHERE id = ?";
		try {
			Connection conn = ConnectionUtils.getMyConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.first()) {
				owt = mapToOneWayTicket(rs);
			}
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return owt;
	}

	/**
	 * Mapping method
	 * @param id
	 * @param rs
	 * @return OneWayTicket
	 * @throws SQLException
	 */
	private OneWayTicket mapToOneWayTicket(ResultSet rs) throws SQLException {
		OneWayTicket owt;
		owt = new OneWayTicket();
		owt.setId(rs.getString("id"));
		owt.setTicketCode(rs.getString("ticket_code"));;
		owt.setEmbarkationId(rs.getInt("embarkation_id"));
		owt.setDisembarkationId(rs.getInt("disembarkation_id"));
		owt.setCheckedIn(rs.getBoolean("checked_in"));
		owt.setFare(rs.getDouble("fare"));
		owt.setActivated(rs.getBoolean("activated"));
		return owt;
	}

	/**
	 * 
	 * @param ticketId
	 * @param checkedIn
	 * @param activated
	 */
	@Override
	public boolean update(OneWayTicket oneWayTicket) {
		String sql = "UPDATE oneway_ticket SET " 
	    		+ "ticket_code=?, "
	    		+ "embarkation_id=?, "
	    		+ "disembarkation_id=?, "
	    		+ "checked_in=?, "
	    		+ "fare=?, "
	    		+ "activated=?" + " WHERE "
	    		+ "id=?";
        
        try {
        	Connection conn = ConnectionUtils.getMyConnection();
    		PreparedStatement ps = conn.prepareStatement(sql);
 
    		ps.setString(1, oneWayTicket.getTicketCode());
    		ps.setInt(2, oneWayTicket.getEmbarkationId());
    		ps.setInt(3, oneWayTicket.getDisembarkationId());
    		ps.setBoolean(4, oneWayTicket.isCheckedIn());
    		ps.setDouble(5, oneWayTicket.getFare());
    		ps.setBoolean(6, oneWayTicket.isActivated());
    		ps.setString(7, oneWayTicket.getId());
    		
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
	 * @return {OneWayTicket}
	 */
	@Override
	public OneWayTicket findByTicketCode(String ticketCode) {
		String sql = "SELECT id, ticket_code, embarkation_id, disembarkation_id, checked_in, fare, activated FROM oneway_ticket WHERE ticket_code = ?";
		OneWayTicket oneWayTicket = null;
		Connection conn = null;
		try {
			conn = ConnectionUtils.getMyConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ticketCode);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				oneWayTicket = mapToOneWayTicket(rs);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return oneWayTicket;
	}
}
