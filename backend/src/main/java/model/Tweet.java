package model;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="tweet")
public class Tweet {
	
	@Id
	@SequenceGenerator(name = "seq_tweet", sequenceName = "seq_tweet", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tweet")
	@Column(name = "id")
	private int id;
	
	@Column(name = "conteudo")
	private String conteudo;
	
	@Column(name = "data_postagem")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
	private Usuario usuario;
	
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tweet")
	@JsonManagedReference
	private Set<Like> likes;
	
	@OneToMany(fetch = FetchType.EAGER) 
	@JoinColumn(name = "id_tweet")
	@JsonManagedReference
	private Set<Deslike> deslikes;
	
	
	@PrePersist 
	@PreUpdate
	public void preencheData() {
		this.data = Calendar.getInstance();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Like> getLikes() {
		return likes;
	}

	public void setLikes(Set<Like> likes) {
		this.likes = likes;
	}

	public Set<Deslike> getDeslikes() {
		return deslikes;
	}

	public void setDeslikes(Set<Deslike> deslikes) {
		this.deslikes = deslikes;
	}
}