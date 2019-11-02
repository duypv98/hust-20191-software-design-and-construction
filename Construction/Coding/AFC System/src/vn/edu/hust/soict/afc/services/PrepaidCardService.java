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

    public static PrepaidCard getPrepaidCardInfo(String cardCode) {
        String sql = "SELECT id, balance, checked_in FROM prepaid_card WHERE card_code = ?";
        PrepaidCard prepaidCard = null;
        try {
            client.open();
            PreparedStatement ps = client.getConnection().prepareStatement(sql);
            ps.setString(1, cardCode);
            ResultSet rs = ps.executeQuery();

            if (rs.first()) {
                prepaidCard = new PrepaidCard();
                prepaidCard.setId(rs.getString("id"));
                prepaidCard.setBalance(rs.getDouble("balance"));
                prepaidCard.setCheckedIn(rs.getBoolean("checked_in"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prepaidCard;
    }

    public static boolean saveTransactionCheckIn(String card_id, int income_station_id) {
        String sql = "INSERT INTO prepaid_trip (card_id, income_station_id, income_time, ontrip)" +
                " VALUES (?, ?, ?, ?)";

        String sql1 = "UPDATE prepaid_card SET `checked_in` = '1' WHERE id = ?";

        Date date  = new Date();
        Timestamp income_time = new Timestamp(date.getTime());

        int effectRow = 0;
        int effectRow1 = 0;
        try {
            client.open();
            PreparedStatement ps = client.getConnection().prepareStatement(sql);
            ps.setString(1, card_id);
            ps.setInt(2, income_station_id);
            ps.setTimestamp(3, income_time);
            ps.setBoolean(4, true);
            effectRow = ps.executeUpdate();

            PreparedStatement ps1 = client.getConnection().prepareStatement(sql1);
            ps1.setString(1, card_id);
            effectRow1 = ps1.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (effectRow != 0 && effectRow1 != 0) {
            return true;
        } else {
            return false;
        }
    }
}
