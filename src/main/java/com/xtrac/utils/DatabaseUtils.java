package com.xtrac.utils;
import java.sql.*;

public class DatabaseUtils {

	/**
	 * insert new user
	 * 
	 * @param emailId
	 * @param firstName
	 * @param lastName
	 * @param telephoneNumber
	 * @return
	 * @throws Exception
	 */
	public static String insertUser(String email, String firstName, String lastName, String telephoneNumber) throws Exception {

		// local variables
		int telNo = -1;
		int newUserStatus = -1;
		String insertNewUserQuery = null;
		Statement stmnt = null;

		try{
			telNo = Integer.parseInt(telephoneNumber);
			insertNewUserQuery = " INSERT INTO USER_LOGIN VALUES('"+email+"', '" + firstName + "', '" + lastName + "' , " + telNo + ")";
			stmnt = getStatementObject();
			newUserStatus = stmnt.executeUpdate(insertNewUserQuery);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			stmnt.close();
		}

		if(0 == newUserStatus){

			return "New user insertion failed";
		}
		return "New User inserted successfully";
	}

	/**
	 * search user 
	 * 
	 * @param emailId
	 * @return
	 * @throws Exception
	 */
	public static String searchUser(String emailId) throws Exception {

		// local variables
		String fname = null;
		String lname = null;
		String telno = null;
		String searchUserQuery = null;
		Statement stmnt = null;
		ResultSet rs = null;

		try{
			searchUserQuery = " SELECT EMAIL_ID, FIRST_NAME, LAST_NAME, TELEPHONE_NO FROM USER_LOGIN WHERE EMAIL_ID = '" + emailId  + "'";
			stmnt = getStatementObject();
			rs = stmnt.executeQuery(searchUserQuery);

			while(rs.next()) {
				fname = rs.getString("FIRST_NAME");
				lname = rs.getString("LAST_NAME");
				telno = rs.getString("TELEPHONE_NO");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			rs.close();
			stmnt.close();
		}

		return "{"
		+ " firstName: "+ fname 
		+ " lastName: "+ lname
		+ " telephone number: "+ telno +
		"}";
	}

	/**
	 * update user
	 * 
	 * @param emailId
	 * @param firstName
	 * @param lastName
	 * @param telephoneNumber
	 * @return
	 * @throws Exception
	 */
	public static String updateUser(String emailId, String firstName, String lastName, String telephoneNumber) throws Exception {

		int updateUserStatus = -1;
		int telNo = Integer.parseInt(telephoneNumber);
		String updateUserQuery = null;
		Statement stmnt = null;

		try{
			updateUserQuery = " UPDATE USER_LOGIN SET FIRST_NAME = '" + firstName + "', LAST_NAME = '" + lastName + "' , TELEPHONE_NO = " + telNo + 
					" WHERE EMAIL_ID = '" + emailId + "'";
			stmnt = getStatementObject();
			updateUserStatus = stmnt.executeUpdate(updateUserQuery);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			stmnt.close();
		}

		if(0 == updateUserStatus){

			return "Update user failed";
		}
		return "Successfully updated user";
	}

	/**
	 * delete user
	 * 
	 * @param emailId
	 * @return
	 * @throws Exception
	 */
	public static String deleteUser(String emailId) throws Exception {

		// local variables
		int deleteUserStatus = -1;
		String deleteUserQuery = null;
		Statement stmnt = null;

		try{
			deleteUserQuery = " DELETE FROM USER_LOGIN WHERE EMAIL_ID = '" + emailId  + "'";
			stmnt = getStatementObject();
			deleteUserStatus = stmnt.executeUpdate(deleteUserQuery);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			stmnt.close();
		}

		if(0 == deleteUserStatus){

			return "Delete user failed";
		}
		return "Successfully deleted user";
	}

	/**
	 * method to register app
	 * 
	 * @param emailId
	 * @param appName
	 * @param appDesc
	 * @return
	 * @throws Exception
	 */
	public static int registerApp(String emailId, String appName, String appDesc) throws Exception {

		// local variables
		int regId = -1;
		int app_id = Helper.randomNumberGeneration();
		String app_secret = Helper.randomTextGeneration();
		String registerAppQuery = null;
		String retrieveRegIdQuery = null;
		Statement stmnt = null;
		ResultSet rs = null;

		try{
			// register app for user
			registerAppQuery = "INSERT INTO USER_APP(EMAIL_ID, APP_NAME, APP_DESC, APP_ID, APP_SECRET) "
					+ " VALUES('"+emailId+"', '"+ appName +"', '"+appDesc+"', " + app_id +",'"+app_secret+"')";
			stmnt = getStatementObject();
			int regAppStatus = stmnt.executeUpdate(registerAppQuery);

			if(0 != regAppStatus) {

				// retrieve registered id
				retrieveRegIdQuery = "SELECT REG_ID FROM USER_APP "
						+ " WHERE EMAIL_ID = '" + emailId  + "' AND APP_NAME = '" + appName +"' AND APP_DESC = '" + appDesc + "' "
						+ "AND APP_ID = " + app_id + " AND APP_SECRET = '" + app_secret + "'";
				rs = stmnt.executeQuery(retrieveRegIdQuery);

				while(rs.next()) {
					regId = rs.getInt("REG_ID");
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			rs.close();
			stmnt.close();
		}

		return regId;
	}

	/**
	 * get all user app list
	 * 
	 * @param emailId
	 * @return
	 * @throws Exception
	 */
	public static String getUserAppList(String emailId) throws Exception {

		// local variables
		String appName = null;
		String appId = null;
		String finalList = "";
		String userListSqlQuery = null;
		Statement stmnt = null;
		ResultSet rs = null;

		try{

			userListSqlQuery = " SELECT APP_NAME, APP_ID FROM USER_APP WHERE EMAIL_ID = '" + emailId  + "'";
			stmnt = getStatementObject();
			rs = stmnt.executeQuery(userListSqlQuery);

			while(rs.next()) {

				appName = rs.getString("APP_NAME");
				appId = rs.getString("APP_ID");
				finalList += "AppName:" + appName + " AppId:" + appId + " | ";
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			rs.close();
			stmnt.close();
		}

		return finalList;
	}

	/**
	 * mysql database connection
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {

		// local variables
		Connection conn = null;
		String db_url = "jdbc:mysql://localhost:3306/xtrac-app";
		String username = "root";
		String password = "";

		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(db_url, username, password);
		return conn;
	}

	/**
	 * mysql database connection
	 * @return
	 * @throws Exception
	 */
	public static Statement getStatementObject() throws Exception {

		Connection conn = null;
		Statement stmnt = null;

		try{
			conn = getConnection();
			stmnt = conn.createStatement();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return stmnt;
	}
}