package repository;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import model.Comentario;
import model.seletor.ComentarioSeletor;

@Stateless
public class ComentarioRepository extends AbstractCrudRepository {

	public void inserir(Comentario comentario){
		comentario.setData(Calendar.getInstance());
		super.em.persist(comentario);
	}

	public void atualizar(Comentario comentario){
		comentario.setData(Calendar.getInstance());
		super.em.merge(comentario);
	}

	public void remover(int id){
		Comentario c = this.consultar(id);
		super.em.remove(c);
	}

	public Comentario consultar(int id){
		
		return super.em.find(Comentario.class, id);
	}

	public List<Comentario> pesquisar(ComentarioSeletor seletor){
	
		StringBuilder jpql = new StringBuilder();
		jpql.append("select c from Comentario c");
		
		this.criarFiltro(jpql, seletor);
		
		Query query = super.em.createQuery(jpql.toString());
		
		this.adicionarParametros(query, seletor);

		return query.getResultList();
	}
	
	public void criarFiltro(StringBuilder jpql, ComentarioSeletor seletor) {
		
		if(seletor.possuiFiltro()) {
			
			jpql.append("where ");
			Boolean primeiro = true;
			if(seletor.getId() != null) {
				jpql.append("c.id = :id");
			}
			
			if(seletor.getConteudo() != null && !seletor.getConteudo().trim().isEmpty()) {
				if(!primeiro) {
					jpql.append("and ");
				}
				jpql.append("c.conteudo like :conteudo");
			}
		}
	}
	
	public void adicionarParametros(Query query, ComentarioSeletor seletor) {
		
		if(seletor.possuiFiltro()) {
			if(seletor.getId() != null) {
				query.setParameter("id", seletor.getId());
			}
			
			if(seletor.getConteudo() != null && !seletor.getConteudo().trim().isEmpty()) {
				query.setParameter("conteudo", String.format("%%%s%%", seletor.getConteudo()));
			}
		}
		
	}

	public Long contar(ComentarioSeletor seletor){
	
		StringBuilder jpql = new StringBuilder();
		jpql.append("select count(c) from Comentario c");
		
		this.criarFiltro(jpql, seletor);
		
		Query query = super.em.createQuery(jpql.toString());
		
		this.adicionarParametros(query, seletor);

		return (Long)query.getSingleResult();
	}

	public List<Comentario> listarTodos(){
	
		return this.pesquisar(new ComentarioSeletor());
	}
}
