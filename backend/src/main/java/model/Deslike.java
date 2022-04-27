package model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "deslikes")
public class Deslike {

	@Id
	@SequenceGenerator(name = "seq_deslikes", sequenceName = "seq_deslikes", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_deslikes")
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_tweet", referencedColumnName = "id")
	@JsonBackReference
	private Tweet tweet;
	
	@ManyToOne 
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
	private Usuario usuario;
	

	@Column(name = "data_deslike")
	@Temporal(TemporalType.DATE)
	private Calendar data;

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tweet getTweet() {
		return tweet;
	}

	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}
}
