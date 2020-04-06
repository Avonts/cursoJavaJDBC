package apllication;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class ExTransacoes {

	public static void main(String[] args) {

		Connection conn = null;
	
		Statement st = null;

		try {

			conn = DB.getConnection();
			
			//Adicionando seguran�a de transa��o
			conn.setAutoCommit(false);
			
			st = conn.createStatement();

			int rows1 = st.executeUpdate("UPDATE seller " + "SET BaseSalary = 2090 " + "WHERE DepartmentId = 1");

			/*int x = 1;
			if (x < 2) {
				throw new SQLException("Fake Error!");
			}*/

			int rows2 = st.executeUpdate("UPDATE seller " + "SET BaseSalary = 3090 " + "WHERE DepartmentId = 2");

			conn.commit();
			
			System.out.println("Done! Rows1 Affected: " + rows1);
			System.out.println("Done! Rows2 Affected: " + rows2);

		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
