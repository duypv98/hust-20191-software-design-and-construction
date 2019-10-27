/**
 * 
 */
package vn.edu.hust.soict.afc.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.edu.hust.soict.afc.common.BaseDataClient;
import vn.edu.hust.soict.afc.entities.Station;

/**
 * @author Professor
 *
 */
public class StationService {
	private static BaseDataClient client = new BaseDataClient();

	/**
	 * 
	 * @param stationId
	 * @return
	 * @throws SQLException
	 */
	public static Station getStationInfo(int stationId) throws SQLException {
		Station station = null;
		String sql = "SELECT station_name, distance FROM station WHERE id = ?";

		client.open();
		PreparedStatement ps = client.getConnection().prepareStatement(sql);
		ps.setInt(1, stationId);

		ResultSet rs = ps.executeQuery();

		if (rs.first()) {
			station = new Station();
			station.setId(stationId);
			station.setStationName(rs.getString("station_name"));
			station.setDistance(rs.getDouble("distance"));
		}

//		client.close();
		return station;
	}
}
