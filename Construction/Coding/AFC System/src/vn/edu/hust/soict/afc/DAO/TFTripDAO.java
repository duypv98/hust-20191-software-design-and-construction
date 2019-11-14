package vn.edu.hust.soict.afc.DAO;

import java.sql.SQLException;

import vn.edu.hust.soict.afc.entities.TwentyFourTrip;

public interface TFTripDAO {

	/**
	 * 
	 * @param ticketId
	 * @return TwentyFourTrip
	 * @throws SQLException
	 */
	TwentyFourTrip findByTicketIdAndOnTrip(String ticketId, boolean onTrip);

	/**
	 * 
	 * @param twentyFourTrip
	 * @return
	 */
	boolean save(TwentyFourTrip twentyFourTrip);

	/**
	 * 
	 * @param twentyFourTrip
	 * @return
	 */
	boolean update(TwentyFourTrip twentyFourTrip);

}