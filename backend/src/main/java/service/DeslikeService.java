package service;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Deslike;
import model.Tweet;
import model.Usuario;
import model.exceptions.ErroAoCurtirAPublicacao;
import repository.DeslikesRepository;
import repository.LikesRepository;
import repository.TweetRepository;
import repository.UsuarioRepository;

@Path("/deslikes")
public class DeslikeService {
	
	@EJB
	private LikesRepository likeRepository;

	@EJB
	private DeslikesRepository deslikeRepository;

	@EJB
	private TweetRepository tweetRepository;

	@EJB
	private UsuarioRepository usuarioRepository;

	@GET
	@Path("/{id_tweet}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void descurtirTweet(@PathParam("id_tweet") Integer idTweet) throws ErroAoCurtirAPublicacao {
		Tweet tweet = this.tweetRepository.consultar(idTweet);
		Usuario usuario = this.usuarioRepository.consultar(1);
		Integer like = this.likeRepository.consultarIdDoLike(idTweet);  

//		boolean deslike = this.deslikeRepository.usuarioDescurtiuTweet(tweet, usuario);

		try {

			Deslike deslikeTweet = new Deslike();
			deslikeTweet.setTweet(tweet);
			deslikeTweet.setUsuario(usuario);
			
			deslikeRepository.inserir(deslikeTweet);
			
			if (like != null) {  

//				System.out.println(like);
				likeRepository.remover(like);
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@POST
	@Path("/{id_tweet}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long toltalDeDeslikesTweet(@PathParam("id_tweet") Integer idTweet) throws ErroAoCurtirAPublicacao {

		Tweet tweet = this.tweetRepository.consultar(idTweet);    

		Long deslike = this.deslikeRepository.quantidadeDeDescurtidasTweet(tweet);

		try {

			if (deslike >= 0) {

				Deslike deslikeTweet = new Deslike();
						deslikeTweet.setTweet(tweet);
			}

			return deslike;

		} catch (Exception e) {
			throw e;
		}
	}

}
