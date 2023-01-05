package pos_java_jdbc.pos_java_jdbc;

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
		userposjava.setId(7L);
		userposjava.setNome("maria");
		userposjava.setEmail("maria@live.com");
		
		//seto o objeto para ele ter dados
		userposdao.salvar(userposjava);
		
	}
}
