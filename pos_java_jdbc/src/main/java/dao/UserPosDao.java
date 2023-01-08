package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserPosDao {
	//objeto da classe connection
	private Connection connection;
	
	//contrutor que quando Ã© chamada a classe UserPosDao ele jÃ¡ carrega a conexÃ£o
	public UserPosDao() {
		//objeto da classe Conneciton recebe a classe de conexÃ£o que chama o mÃ©todo que pega a conexÃ£o com o banco
		connection = SingleConnection.getConnection();
	}
	
	//funÃ§Ã£o insert
	public void salvar (Userposjava userposjava) {
		try {
			//usando o comando de autoincremento  no banco nÃ£o preciso mais setar o id 
			//String sql = "insert into userposjava (id, nome, email) values(?,?,?)";
			String sql = "insert into userposjava (nome, email) values(?,?)";
			//preparedStatement vai fazer a preparaÃ§Ã£o desse comando sql com o banco
			PreparedStatement insert = connection.prepareStatement(sql);
			//passamos os parametros
			//insert.setLong(1, 4);
			//usando o comando de autoincremento  no banco nÃ£o preciso mais setar o id
			//insert.setLong(1, userposjava.getId());
			//insert.setString(2, "EgÃ­dio salvar dao");
			insert.setString(1, userposjava.getNome());
			//insert.setString(3, "egidio@live.com");
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			//salvo no banco com o commit
			connection.commit();
			//@@@@@@ vou para a classe de teste
		} catch (SQLException e) {
			// caso dÃª problema precisamos de outr try catch com um rollback
			try {
				//reverte a operaÃ§Ã£o
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	//mÃ©todo q vai retornar uma lista que serÃ¡ 1 ou vÃ¡rios
	public List<Userposjava> listar(){
		//instancia a lista q retorna resultado ou nada
		List<Userposjava> list = new ArrayList<Userposjava>();
		//montado o sql
		String sql = "select * from userposjava";
		try {
			//prepara o sql
			PreparedStatement statement = connection.prepareStatement(sql);
			//executamos esse resultado
			ResultSet resultado = statement.executeQuery();
			//interamos
			//enquanto houver resultado vai continuar pegando do banco e colocando em uma lista
			while(resultado.next()) {
				//instancio a model
				Userposjava userposjava = new Userposjava();
				//objeto Ã© alterado por (set) o atributo que foi pego (get) 
				userposjava.setId(resultado.getLong("id"));
				userposjava.setNome(resultado.getString("nome"));
				userposjava.setEmail(resultado.getString("email"));
				
				list.add(userposjava);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public Userposjava buscar (Long id) throws Exception {
		Userposjava retorno = new Userposjava();
		String sql = "select * from userposjava where id = " + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
		}
		return retorno;
	}
	//parÃ¢metro do tipo Userposjava que Ã© a classe model q representa as entidades do banco de dados
	public void atualizar (Userposjava userposjava) {
		
		try {
			//comando que serÃ¡ enviado ao banco
			//atualize a tabela tal no conjunto nome e email onde o id Ã©... userposjava pega esse id que queremos atualizar
			String sql = "update userposjava set nome = ?, email = ? where id = " + userposjava.getId();
			//preparo a declaraÃ§Ã£o q serÃ¡ enviada ao banco atravÃ©s da conexÃ£o = connection
			PreparedStatement stament = connection.prepareStatement(sql);
			//seto(altero) o nome na coluna 1 e o email na coluna dois
			//altere a string (setString) na coluna 1, classe userposjava getNome(pega o nome) para ser alterado
			stament.setString(1, userposjava.getNome());
			stament.setString(2, userposjava.getEmail());
			//execute essa declaraÃ§Ã£o
			stament.execute();
			//confirme essa conexÃ£o
			connection.commit();
		} catch (Exception e) {
			try {
				//caso de algum problema retorne ao Ãºltimo estado
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}	
	}
	
	public void deletar(Long id) {
		try {
			String sql = "delete from userposjava where id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			e.printStackTrace();
		}
	}
	
	/*
	 * @ para uma tabela ter uma chave estrangeira foreign key, a pai precisa ter uma chave unique que seguirÃ¡ a alteraÃ§Ã£o
	 * @ crio a tabela telefone user que terÃ¡ relaÃ§Ã£o de 1 pra muitos
	 * @ a tabela telefone terÃ¡ a coluna usuariopessoa que serÃ¡ a chave estrangeira que apontarÃ¡ para o pai que no caso Ã© userposjava na coluna id
	 * como refecÃªncia 
	 * 
	 * @ crio uma sequÃªncia no id da tabela usuariopessoa
	  CREATE SEQUENCE user_telefone_seq
	  INCREMENT 1
	  MINVALUE 1
	  MAXVALUE 9223372036854775807
	  START 1
	  CACHE 1;
	  ALTER TABLE user_telefone_seq
	  OWNER TO postgres; 
	 *
	 * @ ALTER TABLE public.userposjava ALTER COLUMN id SET DEFAULT nextval('user_telefone'::regclass);
	 * o nextval segue a sequÃªncia e retorna o proximo valor
	 * @ Constraints (restriÃ§Ãµes) mantÃ©m os dados do usuÃ¡rio restritos, e assim evitam que dados invÃ¡lidos sejam inseridos no banco. A mera definiÃ§Ã£o
	 * do tipo de dado para uma coluna Ã© por si sÃ³ um constraint. Por exemplo, uma coluna de tipo DATE restringe o conteÃºdo da mesma para datas vÃ¡lidas.
	 * */
	
	//inserindo dados na tabela relacionada com o userposjava
	//crio antes a model telefone
	public void salvarTelefone(Telefone telefone) {
		
		try {
			String sql = "insert into telefoneuser(numero,tipo,usuariopessoa) values(?,?,?)";
			PreparedStatement prepared = connection.prepareStatement(sql);
			prepared.setString(1, telefone.getNumero());
			prepared.setString(2, telefone.getTipo());
			prepared.setLong(3, telefone.getUsuariopessoa());
			
			prepared.execute();
			//stement nÃ£o tem commit o commit tem na connection
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			e.printStackTrace();
		}
		
	}
	/*Inner join : junÃ§Ã£o de dados entre tabelas Ã© a mesma coisa que buscar por id */
	//quando Ã© grande usamos um objeto para guardar todo esse sql criando uma classe de nome BeanUserFone
	//quero uma lista 
	public List<BeanUserFone> listaBeanUserFones (Long idUser){
		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();
		//efetuamos o inner join
		String sql = " select nome, numero, email from telefoneuser as fone ";
		sql += " inner join userposjava as userp ";
		sql += " on fone.usuariopessoa = userp.id ";
		sql += " where userp.id = " + idUser;
		
		try {
			//faz a consulta no banco
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			//agora vamos varrer o resultSet com while
			//pra cada linha instancia um objeto no banco
			while(resultSet.next()) {
				BeanUserFone userFone = new BeanUserFone();
				//vai alterar os dados (setar)
				userFone.setEmail(resultSet.getString("email"));
				userFone.setNome(resultSet.getString("nome"));
				userFone.setNumero(resultSet.getString("numero"));
				//e colocar na minha lista
				beanUserFones.add(userFone);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//quando terminar o java retorna a nossa lista
		return beanUserFones;
	}
	/*para deletarmos em uma tabela relacional precisamos deletar primeira os itens da filha para depois deletar do pai*/
	public void deleteFonesPorUser(Long idUser) {
		try {
			String sqlFone = "delete from telefoneuser where usuariopessoa = " + idUser;
			String sqlUser = "delete from userposjava where id = " + idUser;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlFone);
			preparedStatement.executeUpdate();
			connection.commit();
			
			preparedStatement = connection.prepareStatement(sqlUser);
			preparedStatement.executeUpdate();
			connection.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
	
//fim da classe
}
