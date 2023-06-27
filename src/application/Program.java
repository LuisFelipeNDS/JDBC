package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = DB.getConection();
		PreparedStatement st = null;
		try {
			conn = DB.getConection();

			st = conn.prepareStatement(
					"INSERT INTO seller" + 
					"(Name, Email, BirthDate, BaseSalary, DepartmentId) " + 
					"VALUES (?, ?, ?, ?, ?)"
					, Statement.RETURN_GENERATED_KEYS);

			st.setString(1, "Luis");
			st.setString(2, "Luis@Gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("26/06/2023").getTime()));
			st.setDouble(4, 3000.00);
			st.setInt(5, 4);

			int rw = st.executeUpdate();

			if(rw > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				while(rs.next()) {
					//Indicando que ira receber os valores da primeira coluna (ID)
					int novoId = rs.getInt(1);
					
					System.out.println("Usuario cadastrado, ID: " + novoId);
				}
			}
			else {
				System.out.println("Nenhuma linha alterada");
			}
			
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}
}
