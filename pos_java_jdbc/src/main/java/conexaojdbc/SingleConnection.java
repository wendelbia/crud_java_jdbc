package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String url = "jdbc:postgresql://localhost:5433/posjava2";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;
	//usando o static quando for chamada essa classe então automaticamente será executado o método conectar()
	static {
		conectar();
	}
	//método q connecta com o banco de dados
	private static void conectar() {
		try {
			//se a conexão for nula então haverá conexão
			if(connection == null) {
				//carregamento do driver
				Class.forName("org.postgresql.Driver");
				//para o objeto conexão vai atribuir as variáveis
				connection = DriverManager.getConnection(url, user, password);
				//conexão com setAutoCommit para decidir quando será gravado, atualizado e deletado
				connection.setAutoCommit(false);
				//para saber se conectou 
				System.out.println("Conectado");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		return connection;
	}
}
