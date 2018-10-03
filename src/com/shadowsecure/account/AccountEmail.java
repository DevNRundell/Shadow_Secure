package com.shadowsecure.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shadowsecure.database.DBConnect;
import com.shadowsecure.database.DBUtils;

import controllers.DialogController;

public class AccountEmail extends Account {

	public boolean doesEmailExist() {
		
		String query = "select email from account where email = ?";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = DBConnect.getConnection();
			
			if(!connection.isClosed()) {
				
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, email.toUpperCase());
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
