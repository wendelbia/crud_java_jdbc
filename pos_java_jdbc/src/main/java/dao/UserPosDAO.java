package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Userposjava;

public class UserPosDAO {

	private Connection connection;

	// uso o contrutor para quando chamar esse classe então será injetado o
	// SingleConnection
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Userposjava userposjava) {

		try {
			String sql = "insert into userposjava (id,nome,email) values(?,?,?)";
			// prepara o sql
			PreparedStatement stmt = connection.prepareStatement(sql);
			// passa os parâmetros de acordo com as posições
			stmt.setLong(1, userposjava.getId());
			stmt.setString(2, userposjava.getNome());
			stmt.setString(3, userposjava.getEmail());
			// executa
			stmt.execute();
			// dá o commit que é salvar no banco
			connection.commit();
			// vou para a classe de teste
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
	//retorna uma lista
	public List<Userposjava> listar() throws SQLException {
			//instancia a lista
			List<Userposjava> list = new ArrayList<Userposjava>();
			//monta o sql
			String sql = "select * from userposjava";
			PreparedStatement stmt;
			stmt = connection.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery();
			//retorna um boolean, enquanto for true
			while(resultado.next()) {
				//enquanto tiver dados eu continuo buscando
				Userposjava userposjava = new Userposjava();
				//assim vai montando uma lista
				userposjava.setId(resultado.getLong("id"));
				userposjava.setNome(resultado.getString("nome"));
				userposjava.setEmail(resultado.getString("email"));
				//adiciona na lista
				list.add(userposjava);
			}
			//retorna a lista
		return list;
	}
	//buscando por id
	public Userposjava buscar(Long id) throws SQLException {
		//instancio a model
		Userposjava retorno = new Userposjava();
		String sql = "select * from userposjava where id = " + id;
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet resultado = stmt.executeQuery();
		while(resultado.next()) {
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
		}
		return retorno;	
	}
}
