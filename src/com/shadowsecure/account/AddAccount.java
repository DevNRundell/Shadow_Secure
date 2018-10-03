package com.shadowsecure.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.shadowsecure.database.DBConnect;
import com.shadowsecure.database.DBUtils;

import controllers.DialogController;

public class AddAccount extends Account {

	public boolean addAccount() {
		
		String query = "insert into account(first_name, last_name, user_name, login, email, password) values(?,?,?,?,?,?)";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {

			connection = DBConnect.getConnection();
			connection.setAutoCommit(false);
				
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, userName);
			preparedStatement.setString(4, loginName);
			preparedStatement.setString(5, email);
			preparedStatement.setString(6, password);
			
			preparedStatement.execute();
			connection.commit();
				
			return true;
			
		} catch (SQLException e) {
			DialogController.showDatabaseError();
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBUtils.closeStatement(preparedStatement);
			DBUtils.closeConn(connection);
		}
		
		return false;
	}
}
