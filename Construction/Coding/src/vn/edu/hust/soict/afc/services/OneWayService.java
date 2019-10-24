/**
* @author Professor
* @created_at 24 Oct 2019
* @project_name AFC_System
* @lecturer Nguyen Thi Thu Trang
* @class_id 111589
*
* @description Java Project for Automated Fare Collection Simulation
*/
package vn.edu.hust.soict.afc.services;

import java.sql.SQLException;

import vn.edu.hust.soict.afc.common.BaseDBConnector;
import vn.edu.hust.soict.afc.entities.OneWayTicket;

/**
 * @author Professor
 *
 */
public class OneWayService {
	/**
	 * 
	 */
	public static BaseDBConnector baseSQLClient;
	/**
	 * @throws ClassNotFoundException 
	 * 
	 */
	public OneWayService() throws ClassNotFoundException {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param ticketId
	 * @return
	 * @throws SQLException
	 */
	public OneWayTicket getTicketInfo(String ticketId) throws SQLException {
		try {
			baseSQLClient.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return null;
	}
	
}

