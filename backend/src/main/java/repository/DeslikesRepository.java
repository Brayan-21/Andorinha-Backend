package repository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Deslike;
import model.Tweet;
import model.Usuario;
import repository.base.AbstractCrudRepository;

@Stateless
public class DeslikesRepository extends AbstractCrudRepository<Deslike>  {
	
	@Inject
	TweetRepository tweetRepository;

	@Inject
	UsuarioRepository usuarioRepository;

	public boolean usuarioDescurtiuTweet(Tweet tweet, Usuario usuario) {
		Long deslikes;
		boolean deslikeTweet = false;

		try {
			deslikes = super.em
					.createQuery(
							"select count(id) from Deslike l" + " where l.tweet.id = :tweet and l.usuario.id = :usuario",
							Long.class)
					.setParameter("tweet", tweet.getId()).setParameter("usuario", usuario.getId()).getSingleResult();

			int deslikeEmTweet = Integer.parseInt(deslikes.toString());
			
			System.out.println(deslikes);

			if (deslikeEmTweet > 0) {
				deslikeTweet = true;
			}

			return deslikeTweet;

		} catch (NoResultException ex) {
			return false;
		}
	}

	public Long quantidadeDeDescurtidasTweet(Tweet tweet) {
		TypedQuery<Long> deslikes;

		try {
			
			deslikes = super.em.createQuery("select count(l) from Deslike l"
					+ " where l.tweet.id = :idTweet", Long.class)
					.setParameter("idTweet", tweet.getId());
			
			Long resultado = deslikes.getSingleResult();

			return resultado;
			
		} catch (NoResultException ex) {
			return (long) 0;
		}
	}

}
