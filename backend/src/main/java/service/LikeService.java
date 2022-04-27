package service;


import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Like;
import model.Tweet;
import model.Usuario;
import model.exceptions.ErroAoCurtirAPublicacao;
import repository.LikesRepository;
import repository.TweetRepository;
import repository.UsuarioRepository;

@Path("/likes")
public class LikeService {
	
	@EJB
	private LikesRepository likesRepository;
	
	@EJB
	private TweetRepository tweetRepository;
	
	@EJB
	private UsuarioRepository usuarioRepository;
	
	
	@GET
	@Path("/{id_tweet}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void curtirTweet (@PathParam("id_tweet") Integer idTweet) throws ErroAoCurtirAPublicacao {
		
		
		Tweet tweet = this.tweetRepository.consultar(idTweet);
		Usuario usuario = this.usuarioRepository.consultar(1);
		
		boolean like = this.likesRepository.usuarioCurtiuTweet(tweet, usuario);
		
		try {
			
			if(!like) {
				
				Like likeTweet = new Like();
					 likeTweet.setTweet(tweet);
					 likeTweet.setUsuario(usuario);
					 
				
				likesRepository.inserir(likeTweet);
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	@POST
	@Path("/{id_tweet}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long toltalDeLikesTweet(@PathParam("id_tweet") Integer idTweet)  throws ErroAoCurtirAPublicacao {
		
		Tweet tweet = this.tweetRepository.consultar(idTweet);
		
		Long like = this.likesRepository.quantidadeDeCurtidasTweet(tweet);
		
		try {
			
			if(like >= 0) {
				
				Like likeTweet = new Like();
					 likeTweet.setTweet(tweet);
			}
			
			return like; 
			
		} catch (Exception e) {
			throw e;
		}
	}
}
