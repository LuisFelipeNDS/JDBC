package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {
	
	public static void main(String[] args) {
		
		Connection conn = DB.getConection();
		Statement st = null; //consulta para buscartodos departamentos
		ResultSet rs = null; //resultado da consulta
		
		try {
			conn = DB.getConection();
			
			st = conn.createStatement(); // instancia objeto do tipo STATEMENT
			rs = st.executeQuery("select * from department");
			
			while(rs.next()) {
				System.out.println(rs.getInt("Id" )+ ","+ rs.getString("Name"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeResultStatement(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
