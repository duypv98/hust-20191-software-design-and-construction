/**
 *
 */
package vn.edu.hust.soict.afc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.hust.soict.afc.entities.Station;
import vn.edu.hust.soict.afc.utils.ConnectionUtils;

/**
 * @author Professor
 *
 */
public class StationDAOImpl implements StationDAO {

	@Override
	public Station findById(int id) {
        String sql = "SELECT id, station_name, distance FROM station WHERE id = ?";
        Station station = null;
        try {
            Connection conn = ConnectionUtils.getMyConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
            	station = new Station();
				station.setId(rs.getInt("id"));
				station.setStationName(rs.getString("station_name"));
				station.setDistance(rs.getDouble("distance"));
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return station;
    }

	@Override
	public List<Station> findAll() {
        String sql = "SELECT id, station_name, distance FROM station";
        List<Station> rList = new ArrayList<>();
        try {
            Connection conn = ConnectionUtils.getMyConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
				Station st = new Station();
				st.setId(rs.getInt("id"));
				st.setStationName(rs.getString("station_name"));
				st.setDistance(rs.getDouble("distance"));

				rList.add(st);
			}
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return rList;
    }
}
