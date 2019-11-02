package vn.edu.hust.soict.afc.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import vn.edu.hust.soict.afc.common.BaseDataClient;
import vn.edu.hust.soict.afc.entities.PrepaidCard;

public class PrepaidCardService {
    private static BaseDataClient client = new BaseDataClient();

    public static PrepaidCard getPrepaidCardInfo(String cardCode) throws SQLException {
        String sql = "SELECT id, balance, checked_in FROM prepaid_card WHERE card_code = ?";

        client.open();
        PreparedStatement ps = client.getConnection().prepareStatement(sql);
        ps.setString(1, cardCode);

        ResultSet rs = ps.executeQuery();

        if (rs.first()) {
            PrepaidCard prepaidCard = new PrepaidCard();
            prepaidCard.setId(rs.getString("id"));
            prepaidCard.setBalance(rs.getDouble("balance"));
            prepaidCard.setCheckedIn(rs.getBoolean("checked_in"));
            return prepaidCard;
        } else {
            return null;
        }
    }

    public static boolean saveTransactionCheckIn(String card_id, int income_station_id) throws SQLException {
        String sql = "INSERT INTO prepaid_trip (card_id, income_station_id, income_time, ontrip)" +
                " VALUES (?, ?, ?, ?)";

        Date date  = new Date();
        Timestamp income_time = new Timestamp(date.getTime());

        client.open();
        PreparedStatement ps = client.getConnection().prepareStatement(sql);
        ps.setString(1, card_id);
        ps.setInt(2, income_station_id);
        ps.setTimestamp(3, income_time);
        ps.setBoolean(4, true);
        int effectRow = ps.executeUpdate();
        if(effectRow == 0) {
            return false;
        } else {
            return true;
        }
    }
}
