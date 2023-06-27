package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConection();
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int l1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090.00 " +
					"WHERE departmentId = 1");
			
			/*int x =1;
			if(x < 2) {
				throw new SQLException("SIMULANDO ERRO");
			}*/
			
			int l2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090.00 " +
					"WHERE departmentId = 2");
			
			conn.commit();
			
			System.out.println("Linha 1: " + l1);
			System.out.println("Linha 2: " + l2);
			
		} 
		catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("FALHA NO SISTEMA, TRANSACAO NAO EFETUADA "
						+ e.getMessage());
				
			} catch (SQLException rB) {
				throw new DbException("ERRO EM DESFAZER TRANSACAO " + rB.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}
}
