package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;

public class Operator {
	
	public boolean login(String username, String password){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select password from user");
			if(rs.next()){
				String rspasswd = rs.getString("password");
				if(rspasswd.equals(password)){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
