package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexaojdbc.SingleConnection;
import model.Userposjava;

public class UserPosDAO {
	
	private Connection connection;
	
	//uso o contrutor para quando chamar esse classe então será injetado o SingleConnection
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(Userposjava userposjava) {
		
		try {
			String sql = "insert into userposjava (id,nome,email) values(?,?,?)";
			//prepara o sql
			PreparedStatement stmt = connection.prepareStatement(sql);
			//passa os parâmetros de  acordo com as posições
			stmt.setLong(1, userposjava.getId());
			stmt.setString(2, userposjava.getNome());
			stmt.setString(3, userposjava.getEmail());
			//executa
			stmt.execute();
			//dá o commit que é salvar no banco
			connection.commit();
			//vou para a classe de teste
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
