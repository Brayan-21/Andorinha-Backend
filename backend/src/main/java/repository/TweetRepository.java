package repository;

import java.util.List;

import javax.ejb.Stateless;

import model.Tweet;
import model.dto.TweetDTO;
import model.seletor.TweetSeletor;
import repository.base.AbstractCrudRepository;

@Stateless
public class TweetRepository extends AbstractCrudRepository<Tweet> {
	
	public Tweet consultar(int id) {
		
		Tweet t = super.consultar(id);
				
		popularAtributosTransientes(t);
		
		return t; 
	}
	
	private void popularAtributosTransientes(Tweet t) {
			
		t.getLikes().size();
//		t.getDeslikes().size();
	}

	public List<Tweet> pesquisar(TweetSeletor seletor) {
		
		List<Tweet> tweets;
		
		tweets =  super.createEntityQuery() 
				.innerJoinFetch("usuario")  
				.equal("id", seletor.getId())
				.equal("usuario.id", seletor.getIdUsuario())
				.like("conteudo", seletor.getConteudo())
				.equal("data", seletor.getData())
				.setFirstResult(seletor.getOffset())
				.setMaxResults(seletor.getLimite())
				.list(); 
		
		for(Tweet t : tweets) {
			
			popularAtributosTransientes(t);
		}
		
		return tweets;
		
	} 
	
	public List<TweetDTO> pesquisarDTO(TweetSeletor seletor) {
		return super.createTupleQuery()
				.select("id", "conteudo", "data", "usuario.id as idUsuario", "usuario.nome as nomeUsuario")
				.join("usuario")
				.equal("id", seletor.getId()).like("conteudo", seletor.getConteudo()).equal("data", seletor.getData())
				.equal("usuario.id", seletor.getIdUsuario())
				.setFirstResult(seletor.getOffset()).setMaxResults(seletor.getLimite())
				.list(TweetDTO.class);    
	}
	
	
	public Long contar(TweetSeletor seletor) {
		return super.createCountQuery()
				.innerJoinFetch("usuario")
				.equal("id", seletor.getId())
				.equal("usuario.id", seletor.getIdUsuario())
				.like("conteudo", seletor.getConteudo())
				.equal("data", seletor.getData())
				.count();
	}

}
