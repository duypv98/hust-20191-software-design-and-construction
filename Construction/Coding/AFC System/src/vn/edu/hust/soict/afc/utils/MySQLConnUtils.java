package vn.edu.hust.soict.afc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * SQL Connection Utils
 * @author duycv
 * @date Dec 6, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */
public class MySQLConnUtils {

	/**
	 * get SQL Connection
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
    public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        String hostName = "localhost";

        String dbName = "afc";
        String userName = "root";
        String password = "root";

        return getMySQLConnection(hostName, dbName, userName, password);
    }

    /**
     * get SQL Connection
     * @param hostName
     * @param dbName
     * @param userName
     * @param password
     * @return connection
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }

}
