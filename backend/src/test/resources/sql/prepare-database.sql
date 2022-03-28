
delete from comentario;
delete from tweet;
delete from usuario;

INSERT INTO usuario(id, nome) VALUES (1, 'Usuario 1');
INSERT INTO usuario(id, nome) VALUES (2, 'Usuario 2');
INSERT INTO usuario(id, nome) VALUES (3, 'Usuario 3');
INSERT INTO usuario(id, nome) VALUES (4, 'Usuario 4');
INSERT INTO usuario(id, nome) VALUES (5, 'Usuario 5');

INSERT INTO tweet(id, id_usuario, conteudo, data_postagem) VALUES (1, 1, 'Minha postagem de teste', '2020-04-08 15:45:20');
INSERT INTO tweet(id, id_usuario, conteudo, data_postagem) VALUES (2, 2, 'Minha postagem de teste 2', '2020-05-09 09:10:00');
INSERT INTO tweet(id, id_usuario, conteudo, data_postagem) VALUES (3, 3, 'Minha postagem de teste 3', '2020-01-01 12:55:15');

INSERT INTO comentario(id, id_usuario, id_tweet, conteudo, data_postagem) VALUES ( 1, 4, 1,  'Comentario 1', '2020-06-01 15:45:20');
INSERT INTO comentario(id, id_usuario, id_tweet, conteudo, data_postagem) VALUES ( 2, 4, 1,  'Comentario 2', '2020-06-02 15:45:20');
INSERT INTO comentario(id, id_usuario, id_tweet, conteudo, data_postagem) VALUES ( 3, 3, 1,  'Comentario 3', '2020-06-03 15:45:20');
INSERT INTO comentario(id, id_usuario, id_tweet, conteudo, data_postagem) VALUES ( 4, 2, 1,  'Comentario 4', '2020-06-04 15:45:20');
INSERT INTO comentario(id, id_usuario, id_tweet, conteudo, data_postagem) VALUES ( 5, 1, 2,  'Comentario 5', '2020-06-05 15:45:20');
INSERT INTO comentario(id, id_usuario, id_tweet, conteudo, data_postagem) VALUES ( 6, 4, 2,  'Comentario 6', '2020-06-06 15:45:20');
INSERT INTO comentario(id, id_usuario, id_tweet, conteudo, data_postagem) VALUES ( 7, 4, 3,  'Comentario 7', '2020-06-07 15:45:20');
INSERT INTO comentario(id, id_usuario, id_tweet, conteudo, data_postagem) VALUES ( 8, 3, 3,  'Comentario 8', '2020-06-08 15:45:20');
INSERT INTO comentario(id, id_usuario, id_tweet, conteudo, data_postagem) VALUES ( 9, 2, 3,  'Comentario 9', '2020-06-09 15:45:20');
INSERT INTO comentario(id, id_usuario, id_tweet, conteudo, data_postagem) VALUES (10, 1, 3,  'Comentario 10', '2020-06-10 15:45:20');

select setval('seq_usuario', (select max(id) from usuario) );
select setval('seq_tweet', (select max(id) from tweet) );
select setval('seq_comentario', (select max(id) from comentario) );