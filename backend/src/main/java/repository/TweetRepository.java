package repository;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import model.Tweet;
import model.seletor.TweetSeletor;

@Stateless
public class TweetRepository extends AbstractCrudRepository {

	public void inserir(Tweet tweet){
		tweet.setData(Calendar.getInstance());
		super.em.persist(tweet);
	}

	public void atualizar(Tweet tweet){
		tweet.setData(Calendar.getInstance());
		super.em.merge(tweet);
	}

	public void remover(int id){
		Tweet t = this.consultar(id);
		super.em.remove(t);

	}

	public Tweet consultar(int id){
		return super.em.find(Tweet.class, id);
	}
	
	public List<Tweet> pesquisar(TweetSeletor seletor){
		StringBuilder jpql = new StringBuilder();
		jpql.append("select t from Tweet t");
		
		this.criarFiltro(jpql, seletor);
		
		Query query = super.em.createQuery(jpql.toString());
		
		this.adicionarParametros(query, seletor);
		
		return query.getResultList();
		
	}
	
	public void criarFiltro(StringBuilder jpql, TweetSeletor seletor) {
		
		if(seletor.possuiFiltro()) {
			
			jpql.append("where ");
			boolean primeiro = true;
			if(seletor.getId() != null) {
				jpql.append("t.id = :id");
			}
			
			if(seletor.getConteudo() != null && !seletor.getConteudo().trim().isEmpty()) {
				if(!primeiro) {
					jpql.append("and ");
				}
				jpql.append("t.conteudo like :conteudo");
			}
		}
	}
	
	private void adicionarParametros(Query query, TweetSeletor seletor) {
		
		if(seletor.possuiFiltro()) {
			if(seletor.getId() != null) {
				query.setParameter("id", seletor.getId());
			}
			
			if(seletor.getConteudo() != null && !seletor.getConteudo().trim().isEmpty()) {
				query.setParameter("conteudo", String.format("%%%s%%", seletor.getConteudo()));
			}
		}
	}
	
	public Long contar(TweetSeletor seletor) {
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("select count(t) from Tweet t");
		
		this.criarFiltro(jpql, seletor);
		
		Query query = super.em.createQuery(jpql.toString());
		
		this.adicionarParametros(query, seletor);
		
		return (Long)query.getSingleResult();
	}

	public List<Tweet> listarTodos(){
		return this.pesquisar(new TweetSeletor());
	}
}
