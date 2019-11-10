package vn.edu.hust.soict.afc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.edu.hust.soict.afc.entities.PrepaidCard;
import vn.edu.hust.soict.afc.utils.ConnectionUtils;

public class PPCardDAOImpl implements PPCardDAO {
    /*
     * (non-Javadoc)
     *
     * @see vn.edu.hust.soict.afc.DAO.PPCardDAO#findById(java.lang.String)
     */
    @Override
    public PrepaidCard findById(String id) {
        String sql = "SELECT id, card_code, balance, checked_in FROM prepaid_card WHERE id = ?";
        PrepaidCard prepaidCard = null;
        try {
            Connection conn = ConnectionUtils.getMyConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                prepaidCard = mapToPrepaidCard(rs);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return prepaidCard;
    }

    private PrepaidCard mapToPrepaidCard(ResultSet rs) throws SQLException {
        PrepaidCard prepaidCard;
        prepaidCard = new PrepaidCard();
        prepaidCard.setId(rs.getString("id"));
        prepaidCard.setCardCode(rs.getString("card_code"));
        prepaidCard.setBalance(rs.getDouble("balance"));
        prepaidCard.setCheckedIn(rs.getBoolean("checked_in"));
        return prepaidCard;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * vn.edu.hust.soict.afc.DAO.PPCardDAO#update(vn.edu.hust.soict.afc.entities.
     * PrepaidCard)
     */
    @Override
    public boolean update(PrepaidCard prepaidCard) {
        String sql = "UPDATE prepaid_card SET card_code = ?, balance = ?, checked_in = ? WHERE id = ?";

        try {
            Connection conn = ConnectionUtils.getMyConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, prepaidCard.getCardCode());
            ps.setDouble(2, prepaidCard.getBalance());
            ps.setBoolean(3, prepaidCard.isCheckedIn());
            ps.setString(4, prepaidCard.getId());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PrepaidCard findByCardCode(String cardCode) {
        String sql = "SELECT id, card_code, balance, checked_in FROM prepaid_card WHERE card_code = ?";
        PrepaidCard prepaidCard = null;
        try {
            Connection conn = ConnectionUtils.getMyConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cardCode);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                prepaidCard = mapToPrepaidCard(rs);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return prepaidCard;
    }
}
