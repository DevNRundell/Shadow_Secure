package com.shadowsecure.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shadowsecure.database.DBConnect;
import com.shadowsecure.database.DBUtils;

import controllers.DialogController;

public class AccountValidation extends Account {
	
	private static final int ACTIVE_ACCOUNT = 1;

	public boolean doesAccountExist() {
		
		String query = "select login, active, password from account where login = ? and active = ? and password = ?";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = DBConnect.getConnection();
			
			if(connection != null) {
				
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, userName);
				preparedStatement.setInt(2, ACTIVE_ACCOUNT);
				preparedStatement.setString(3, password);
				resultSet = preparedStatement.executeQuery();
			
				if(resultSet.next()) {	
					return true;
				}			
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();			
			DialogController.showDatabaseError();
		} finally {			
			DBUtils.closeStatement(preparedStatement);
			DBUtils.closeResultSet(resultSet);
			DBUtils.closeConn(connection);
		}
		
		return false;
	}
}
