package model.seletor;

public class UsuarioSeletor extends AbstractBaseSeletor {
	
	private Integer id;
	private String nome;
	private String senha;
	private String login;
	private Integer idade;
	
	protected Integer getIdade() {
		return idade;
	}

	protected void setIdade(Integer idade) {
		this.idade = idade;
	}

	protected String getSenha() {
		return senha;
	}

	protected void setSenha(String senha) {
		this.senha = senha;
	}

	protected String getLogin() {
		return login;
	}

	protected void setLogin(String login) {
		this.login = login;
	}

	public boolean possuiFiltro() {
		return this.id != null || 
				(this.nome != null && !this.nome.trim().isEmpty() );
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}