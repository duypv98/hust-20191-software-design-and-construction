/**
 *
 */
package vn.edu.hust.soict.afc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Professor
 *
 */
public class BaseDataClient {

	/**
	 *
	 */
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";

	private static String DB_URL = "jdbc:mysql://localhost:3306/afc";
	private static String DB_USER = "root";
	private static String DB_PWD = "root";

	public Connection connection = null;

	/**
	 *
	 *
	 */
	public BaseDataClient() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Driver not found !");
			e.printStackTrace();
		}
	}

	/**
	 * @throws SQLException
	 *
	 */
	public void open() throws SQLException {
		try {
			if (this.connection == null) {
				this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @throws SQLException
	 *
	 */
	public void close() throws SQLException {
		try {
			if (this.connection != null) {
				this.connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return this.connection;
	}
}
