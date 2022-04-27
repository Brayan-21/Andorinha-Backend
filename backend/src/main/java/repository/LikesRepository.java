package repository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Like;
import model.Tweet;
import model.Usuario;
import repository.base.AbstractCrudRepository;

@Stateless
public class LikesRepository extends AbstractCrudRepository<Like> {

	@Inject
	TweetRepository tweetRepository;

	@Inject
	UsuarioRepository usuarioRepository;
	
	public Integer consultarIdDoLike(int idTweet) {
		
		
		/*
		 * List<Like> likes = super.createEntityQuery().equal("tweet.id",
		 * idTweet).list();
		 * 
		 * return likes != null && !likes.isEmpty() ? likes.get(0) : null;
		 */
		
		
		 Query consultaLike;
		 
		 try {
		  
			 consultaLike = super.em.createQuery("select id from Like l " +
			 " where l.tweet.id = :idTweet").setParameter("idTweet", idTweet);
			 
			 Integer resultado = (Integer) consultaLike.getSingleResult();
			 
	//		 System.out.println(resultado);
			 
			 return resultado;
		 
		 }catch (NoResultException ex) { return (Integer) 0; }
	}

	public boolean usuarioCurtiuTweet(Tweet tweet, Usuario usuario) {
		Long likes;
		boolean likeTweet = false;

		try {
			likes = super.em
					.createQuery(
							"select count(id) from Like l" + " where l.tweet.id = :tweet and l.usuario.id = :usuario",
							Long.class)
					.setParameter("tweet", tweet.getId()).setParameter("usuario", usuario.getId()).getSingleResult();

			int likeEmTweet = Integer.parseInt(likes.toString());

			if (likeEmTweet > 0) {
				likeTweet = true;
			}

			return likeTweet;

		} catch (NoResultException ex) {
			return false;
		}
	}

	public Long quantidadeDeCurtidasTweet(Tweet tweet) {
		TypedQuery<Long> likes;
		try {

			likes = super.em.createQuery("select count(l) from Like l"
					+ " where l.tweet.id = :idTweet", Long.class)
					.setParameter("idTweet", tweet.getId());
			
			Long resultado = likes.getSingleResult();

			return resultado;
			
		} catch (NoResultException ex) {
			return (long) 0;
		}
	}
}
