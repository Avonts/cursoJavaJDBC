package apllication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class ExInsercaoDados {

	public static void main(String[] args) {

		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();

			
			/*
			 * Exemplo adicionando apenas 1 registro na tabela
			 * 
			st = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES " + "(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, "Carl Purple");
			st.setString(2, "carl@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("12/09/1996").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);*/

			st = conn.prepareStatement(
					"insert into department (Name) values ('D1'),('D2')",
					Statement.RETURN_GENERATED_KEYS);
				
			int rowsAffected = st.executeUpdate();

			//System.out.println("Done! Rows affected: " + rowsAffected);
			
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id: " + id);
				}					
				
			} else {
				System.out.println("No rows affected!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		/*
		 * Não está utilizando o Simple date format
		 * 
		 * catch (ParseException e) {
			e.printStackTrace();
		}*/ 
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
