package pos_java_jdbc.pos_java_jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import junit.framework.TestCase;
import model.Userposjava;

public class TesteBancoJdbc {
	//mudando o junit para 4.12 rodará sem erro
	/**/
	@Test
	public void initBanco() {
		SingleConnection.getConnection();
	}
	
	
	@Test
	public void initSalvar() {
		UserPosDAO userposdao = new UserPosDAO();
		Userposjava userposjava = new Userposjava();
		
		//o que era estático, agora vamos passar adminâmicamente
		//aqui converte para long usando o L maiusculo no final
		userposjava.setId(8L);
		userposjava.setNome("ana maria");
		userposjava.setEmail("ana_maria@live.com");
		
		//seto o objeto para ele ter dados
		userposdao.salvar(userposjava);
	}
	
	@Test
	public void initListar() throws SQLException {
		//instancio o UserPosDAO onde tem o método que busca no banco de dados a lista
		UserPosDAO dao = new UserPosDAO();
		//crio uma lista do tipo Userposava esse objeto recebe o método que busca a lista salva no banco
		List<Userposjava> list = dao.listar();
		//faço um iteração com o for
		for(Userposjava userposjava:list) {
			//imprimo
			System.out.println(userposjava.getId());
			System.out.println(userposjava.getNome());
			System.out.println(userposjava.getEmail());
		}
	}
	@Test
	public void initBuscar() throws SQLException {
		//instancio o dao
		UserPosDAO dao = new UserPosDAO();
		Userposjava userposjava = dao.buscar(6L);
		System.out.println(userposjava.getId());
		System.out.println(userposjava.getNome());
		System.out.println(userposjava.getEmail());
	}
}












