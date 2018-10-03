package com.shadowsecure.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.shadowsecure.database.DBConnect;
import com.shadowsecure.database.DBUtils;

import controllers.DialogController;

public class AccountHash extends Account {
	
	private String accountHashValue;
	
	public boolean getAccountHashByEmail() {
		
		String query = "select login from account where email = ?";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = DBConnect.getConnection();
			
			if(connection != null) {
				
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, email);
				resultSet = preparedStatement.executeQuery();
			
				if(resultSet.next()) {	
					accountHashValue = resultSet.getString("login");
					return true;				
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();			
			DialogController.showDatabaseError();
			return false;
		} finally {
			DBUtils.closeStatement(preparedStatement);
			DBUtils.closeResultSet(resultSet);
			DBUtils.closeConn(connection);
		}		
		return false;
	}
	
	public String getAccountHashValue() {
		return accountHashValue;
	}
}
