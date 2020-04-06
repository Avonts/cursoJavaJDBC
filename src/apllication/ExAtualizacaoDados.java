package apllication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class ExAtualizacaoDados {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ? "
					+ "WHERE "
					+ "(DepartmentId = ?)"
					);
			
			st.setDouble(1, 200);
			st.setInt(2, 2);
			
			int rowsAfeccted = st.executeUpdate();
			
			System.out.println("Done! Rows affected: " + rowsAfeccted);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
