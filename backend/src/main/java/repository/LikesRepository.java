package repository;

import javax.ejb.EJB;
import javax.persistence.NoResultException;

import model.Like;
import model.Tweet;
import model.Usuario;
import repository.base.AbstractCrudRepository;

public class LikesRepository extends AbstractCrudRepository<Like>{
	
	@EJB
	TweetRepository tweetRepository;
	
	@EJB
	UsuarioRepository usuarioRepository;
	
	public boolean usuarioCurtiuTweet(Tweet tweet, Usuario usuario) {
		Long likes;
		boolean likeTweet = false;
		
		try {
			 likes = super.em.createQuery("select count(tweet.id) from likes where tweet.id = :tweet and usuario.id = :usuario", Long.class)
					.setParameter("tweet", tweet.getId())
					.setParameter("usuario", usuario.getId())
					.getSingleResult();
			 
			 int likeEmTweet = Integer.parseInt(likes.toString());
			 
			 if (likeEmTweet > 0) {
				 likeTweet = true;
			 }
		}
		catch (NoResultException ex) {
			return false;
		}
		
		return likeTweet;
	}
	
	public void remover(Tweet tweet, Usuario usuario) {
		try {
			super.em.createQuery("delete from likes where tweet.id = :tweet and usuario.id = :usuario")
			.setParameter("tweet", tweet.getId())
			.setParameter("usuario", usuario.getId())
			.executeUpdate();
		} catch (Exception e) {
			
		}
	}
	
	public void validarLikes(Tweet tweet, Usuario usuario) {
		
	}

}
