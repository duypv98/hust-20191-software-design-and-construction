/**
* @author Professor
* @created_at 24 Oct 2019
* @project_name AFC_System
* @lecturer Nguyen Thi Thu Trang
* @class_id 111589
*
* @description Java Project for Automated Fare Collection Simulation
*/
package vn.edu.hust.soict.afc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Professor
 *
 */
public class BaseDBConnector {
	
	// JDBC Driver name and Database URL
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost:3306/afc";
	// Database Credentials
	private static String DB_USER = "";
	private static String DB_PWD = "";
	// Database Connection
	public Connection connection = null;
	
	/**
	 * @throws ClassNotFoundException 
	 * 
	 */
	public BaseDBConnector() throws ClassNotFoundException {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(DRIVER);
			System.out.println("Created DB Connection....");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
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
			} }catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
