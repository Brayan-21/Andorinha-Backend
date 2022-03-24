package repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import model.Usuario;
import model.exceptions.ErroAoConectarNaBaseException;
import model.exceptions.ErroAoConsultarBaseException;
import runner.AndorinhaTestRunner;

@RunWith(AndorinhaTestRunner.class)
public class TestUsuarioRepository {
	
	private static final int ID_USUARIO_CONSULTA = 1;
	private static final int ID_USUARIO_SEM_TWEET = 5;
	
	private UsuarioRepository usuarioRepository;
	
	@Before
	public void setUp() {
		this.usuarioRepository = new UsuarioRepository();
	}
	
	@Test
	public void testa_se_usuario_foi_inserido() throws ErroAoConectarNaBaseException, ErroAoConsultarBaseException {
		Usuario user = new Usuario();
		user.setNome("Usuario 1");
		this.usuarioRepository.inserir(user);
		
		Usuario inserido = this.usuarioRepository.consultar(user.getId());
		
		assertThat( user.getId() ).isGreaterThan(0);
		
		assertThat( inserido ).isNotNull();
		assertThat( inserido.getNome() ).isEqualTo(user.getNome());
		assertThat( inserido.getId() ).isEqualTo(user.getId());
	}
	
	@Test
	public void testa_consultar_usuario() throws ErroAoConectarNaBaseException, ErroAoConsultarBaseException {
		Usuario user = this.usuarioRepository.consultar(ID_USUARIO_CONSULTA);
		
		assertThat( user ).isNotNull();
		assertThat( user.getNome() ).isEqualTo("Usuario 1");
		assertThat( user.getId() ).isEqualTo(ID_USUARIO_CONSULTA);
	}
	
	@Test
	public void testa_alterar_usuario() throws ErroAoConectarNaBaseException, ErroAoConsultarBaseException {
		Usuario user = this.usuarioRepository.consultar(ID_USUARIO_CONSULTA);
		user.setNome("Usuario 1");
		
		this.usuarioRepository.atualizar(user);
		
		Usuario alterado = this.usuarioRepository.consultar(ID_USUARIO_CONSULTA);
		
		assertThat( alterado ).isEqualToComparingFieldByField(user);
	}
	
	@Test
	public void testa_remover_usuario() throws ErroAoConectarNaBaseException, ErroAoConsultarBaseException {
		Usuario user = this.usuarioRepository.consultar(ID_USUARIO_SEM_TWEET);
		assertThat( user ).isNotNull();
		
		this.usuarioRepository.remover(ID_USUARIO_SEM_TWEET);
		
		Usuario removido = this.usuarioRepository.consultar(ID_USUARIO_SEM_TWEET);
		assertThat( removido ).isNull();
	}
	
	@Test
	public void testa_remover_usuario_com_tweet() throws ErroAoConectarNaBaseException, ErroAoConsultarBaseException {
		assertThatThrownBy(() -> { this.usuarioRepository.remover(ID_USUARIO_CONSULTA); })
			.isInstanceOf(ErroAoConsultarBaseException.class)
        	.hasMessageContaining("Ocorreu um erro ao remover o usuario");
	}
	
	@Test
	public void testa_listar_todos_os_usuarios() throws ErroAoConectarNaBaseException, ErroAoConsultarBaseException {
		List<Usuario> usuarios = this.usuarioRepository.listarTodos();
		
		assertThat( usuarios ).isNotNull()
							.isNotEmpty()
							.hasSize(5)
							.extracting("nome")
							.containsExactlyInAnyOrder("Usuario 1", "Usuario 2",
			                        "Usuario 3", "Usuario 4", "Usuario 5");
	}
}
