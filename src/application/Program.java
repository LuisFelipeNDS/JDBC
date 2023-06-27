package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConection();
			
			st = conn.prepareStatement("DELETE FROM department " +
					"WHERE " +
					"department.Id = ?");
			
			
			st.setInt(1, 2);
			
			//Execute Update retorna a quantidade de linhas afetadas
			int rw = st.executeUpdate();
			
			System.out.println("Feito, total de linhas afetadas: " + rw);
		} 
		catch(SQLException e) {
			throw new DbIntegrityException(e.getLocalizedMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}
}
