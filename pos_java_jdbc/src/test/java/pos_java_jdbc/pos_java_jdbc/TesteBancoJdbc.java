package pos_java_jdbc.pos_java_jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDao;
import junit.framework.TestCase;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class TesteBancoJdbc {
	/*
	@Test
	public void initBanco() {
		//testando a conexÃ£o do banco
		//SingleConnection.getConnection();
		
		//testando o insert da classe UserPosDao
		//Preciso do UserPosDao
		UserPosDao userPosDao = new UserPosDao();
		//preciso do objeto de modelo
		Userposjava userposjava = new Userposjava();
		//e agora chamo a funÃ§Ã£o que salvo no banco estÃ¡ticamente pois jÃ¡ foram declarados os parÃ¢metros no UserPosDao
		//userPosDao.salvar(userposjava);
		
		//agora dinÃ¢mmicamente settando os dados, para isso corrijo no UserPosDao
		//usando o comando de autoincremento  no banco nÃ£o preciso mais setar o id 
		//userposjava.setId(6L);
		userposjava.setNome("Matheus teste");
		userposjava.setEmail("matheus@live.com");
		userPosDao.salvar(userposjava);
		
	}*/
	/*
	@Test
	public void initListar() {
		UserPosDao dao = new UserPosDao();
		try {
			List<Userposjava> list = dao.listar();
			for (Userposjava userposjava : list) {
				System.out.println(userposjava);
				//posso imprimir sÃ³ o nome tambÃ©m
				System.out.println(userposjava.getNome());
				System.out.println("----------------------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	/*
	@Test
	public void initBuscar() {
		UserPosDao dao = new UserPosDao();
		try {
			Userposjava userposjava = dao.buscar(1L);
			System.out.println("initBuscar: " + userposjava);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	/*
	@Test
	public void initAtualizar() {	
		try {
			//instancio a classe UserPosDao
			UserPosDao dao = new UserPosDao();
			//preciso de um objeto e vou consultÃ¡-lo no banco
			//por que consultar? Para pegar o id que quero atualizar
			Userposjava objetoBanco = dao.buscar(5L);
			objetoBanco.setNome("nome mudado com mÃ©todo atualizar");
			objetoBanco.setEmail("novo@email.com");
			dao.atualizar(objetoBanco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	/*
	@Test
	public void initDeletar() {
		try {
			UserPosDao userposdao = new UserPosDao();
			userposdao.deletar(8L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initSalvarTelefone() {
		UserPosDao userPosDao = new UserPosDao();
		Telefone telefone = new Telefone();
		telefone.setNumero("(61) 985313749");
		telefone.setTipo("celular");
		telefone.setUsuariopessoa(9L);
		
		userPosDao.salvarTelefone(telefone);
	}
	*/
	@Test
	public void testeCarregaFoneUser () {
		UserPosDao dao = new UserPosDao();
		List<BeanUserFone> beanUserFones = dao.listaBeanUserFones(9L);
		for (BeanUserFone beanUserFone : beanUserFones) {
			System.out.println(beanUserFone);
			System.out.println("--------------------------------------------------------------");
		}
	}
	
	@Test
	public void testeDeleteUserFone() {
		UserPosDao dao = new UserPosDao();
		dao.deleteFonesPorUser(13L);
	}
	
	
	
	

}
