package model.seletor;

public class UsuarioSeletor {
	

	private Integer id;
	private String nome;
	
	private int limite;
	private int pagina;
	
	public boolean possuiFiltro() {
		return this.id != null || 
				(this.nome != null && !this.nome.trim().isEmpty() );
	}
	
	public boolean possuiPaginacao() {
		return this.pagina > 0 && this.limite > 0;
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

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

}
