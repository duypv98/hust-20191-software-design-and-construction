package vn.edu.hust.soict.afc.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.edu.hust.soict.afc.common.BaseDataClient;
import vn.edu.hust.soict.afc.entities.PrepaidCard;

public class PPCardService {
    private static BaseDataClient client = new BaseDataClient();
    
    public static String getCardId(String cardCode) {
    	String id = null;
    	String sql = "SELECT id FROM prepaid_card WHERE card_code = ?";
    	
    	try {
			client.open();
			PreparedStatement ps = client.getConnection().prepareStatement(sql);
			ps.setString(1, cardCode);
			
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				id = rs.getString("id");
			}
		} catch (SQLException e) {
			/* Ignore */
		}
    	return id;
    }

    public static PrepaidCard getPrepaidCardInfo(String cardId) {
        String sql = "SELECT id, balance, checked_in FROM prepaid_card WHERE id = ?";
        PrepaidCard prepaidCard = null;
        try {
        	client.open();
            PreparedStatement ps = client.getConnection().prepareStatement(sql);
            ps.setString(1, cardId);

            ResultSet rs = ps.executeQuery();

            if (rs.first()) {
                prepaidCard = new PrepaidCard();
                prepaidCard.setId(cardId);
                prepaidCard.setBalance(rs.getDouble("balance"));
                prepaidCard.setCheckedIn(rs.getBoolean("checked_in"));
            }
        } catch (SQLException e) {
			/* Ignore */
		}
        return prepaidCard;
    }

    public static void updateCard(String card_id, boolean checkedIn, double newBalance) {
        String sql = "UPDATE prepaid_card SET checked_in = ?, balance = ? WHERE id = ?";
        try {
        	client.open();
        	PreparedStatement ps = client.getConnection().prepareStatement(sql);
        	ps.setBoolean(1, checkedIn);
        	ps.setDouble(2, newBalance);
        	ps.setString(3, card_id);
        	
        	ps.executeUpdate();
        } catch (SQLException e) {
			// TODO: handle exception
		}
    }
}
