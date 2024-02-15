
INSERT INTO videos (video_id, autor, descricao, nome, selo_temporal) VALUES (uuid(), 'Vlogueiro Viajante', 'Viagem a Paris', 'Passeio na Cidade Luz', '2023-11-11 14:47:36.789000+0000');
INSERT INTO videos (video_id, autor, descricao, nome, selo_temporal) VALUES (uuid(), 'eu', 'MEDO', 'FNAF', '2023-11-09 12:00:00.000000+0000');
INSERT INTO videos (video_id, autor, descricao, nome, selo_temporal) VALUES (uuid(), 'Vlogueiro Viajante', 'Viagem a Paris', 'Passeio na Cidade Luz', '2023-11-11 14:47:36.789000+0000');
INSERT INTO videos (video_id, autor, descricao, nome, selo_temporal) VALUES (uuid(), 'Chef de Cozinha', 'Receitas Italianas', 'Sabores da Itália', '2023-11-12 10:05:48.256000+0000');
INSERT INTO videos (video_id, autor, descricao, nome, selo_temporal) VALUES (uuid(), 'Criador de Conteúdo', 'Aventura na Selva', 'Explorando a Natureza', '2023-11-10 08:30:22.123000+0000');
INSERT INTO videos (video_id, autor, descricao, nome, selo_temporal) VALUES (uuid(), 'Amante da Natureza', 'Documentário sobre Florestas Tropicais', 'Segredos da Selva', '2023-11-18 11:11:11.111000+0000');
INSERT INTO videos (video_id, autor, descricao, nome, selo_temporal) VALUES (uuid(), 'Artista Digital', 'Tutorial de Arte Digital', 'Criando com Cores', '2023-11-19 12:12:12.122000+0000');
INSERT INTO videos (video_id, autor, descricao, nome, selo_temporal) VALUES (uuid(), 'Cientista Amador', 'Experimentos de Física em Casa', 'Ciência Divertida', '2023-11-20 13:13:13.133000+0000');
INSERT INTO videos (video_id, autor, descricao, nome, selo_temporal) VALUES (uuid(), 'Músico Independente', 'Composição de Músicas Indie', 'Melodias do Coração', '2023-11-21 14:14:14.144000+0000');
INSERT INTO videos (video_id, autor, descricao, nome, selo_temporal) VALUES (uuid(), 'Instrutor de Ioga', 'Sessões de Ioga para Iniciantes', 'Equilíbrio e Paz', '2023-11-22 15:15:15.155000+0000');



INSERT INTO video_gestao (autor, timestamp_upload, video_id, descricao, nome_video, tags) VALUES ('Ana Torres', '2023-11-27 10:00:00+0000', uuid(), 'Explorando paisagens de Portugal', 'Aventuras em Portugal', {'viagem', 'natureza', 'Portugal'});

INSERT INTO video_gestao (autor, timestamp_upload, video_id, descricao, nome_video, tags) VALUES ('Carlos Pereira', '2023-11-25 15:30:00+0000', uuid(), 'Rotinas de treino em casa', 'Fitness em Casa', {'saúde', 'exercício', 'fitness'});

INSERT INTO video_gestao (autor, timestamp_upload, video_id, descricao, nome_video, tags) VALUES ('Luísa Fernandes', '2023-11-24 20:00:00+0000', uuid(), 'Introdução à composição musical clássica', 'Composições Clássicas', {'música', 'clássica', 'educação'});

INSERT INTO video_gestao (autor, timestamp_upload, video_id, descricao, nome_video, tags) VALUES ('Pedro Martins', '2023-11-23 18:45:00+0000', uuid(), 'Estratégias avançadas de xadrez', 'Domínio do Xadrez', {'jogos', 'estratégia', 'xadrez'});

INSERT INTO video_gestao (autor, timestamp_upload, video_id, descricao, nome_video, tags) VALUES ('Sofia Ribeiro', '2023-11-22 13:00:00+0000', uuid(), 'Descobrindo a culinária japonesa', 'Arte Culinária Japonesa', {'cozinha', 'cultura', 'Japão'});

INSERT INTO video_gestao (autor, timestamp_upload, video_id, descricao, nome_video, tags) VALUES ('Ricardo Gomes', '2023-11-21 09:30:00+0000', uuid(), 'Tendências recentes em tecnologia e inovação', 'Tendências Tecnológicas', {'tecnologia', 'inovação', 'notícias'});

INSERT INTO video_gestao (autor, timestamp_upload, video_id, descricao, nome_video, tags) VALUES ('João Silva', '2023-11-20 14:15:00+0000', uuid(), 'Explorando a floresta Amazônica', 'Aventuras na Amazônia', {'viagem', 'natureza', 'aventura'});

INSERT INTO video_gestao (autor, timestamp_upload, video_id, descricao, nome_video, tags) VALUES ('Maria Oliveira', '2023-11-19 16:00:00+0000', uuid(), 'Receitas veganas rápidas e saudáveis', 'Delícias Veganas', {'cozinha', 'saúde', 'vegano'});

INSERT INTO video_gestao (autor, timestamp_upload, video_id, descricao, nome_video, tags) VALUES ('António Sousa', '2023-11-18 11:00:00+0000', uuid(), 'O intrigante mundo da física quântica', 'Mistérios Quânticos', {'ciência', 'educação', 'física'});

INSERT INTO video_gestao (autor, timestamp_upload, video_id, descricao, nome_video, tags) VALUES ('Clara Machado', '2023-11-17 12:00:00+0000', uuid(), 'Dicas de jardinagem para iniciantes', 'Jardinagem 101', {'hobbies', 'natureza', 'jardinagem'});





INSERT INTO videos.comentarios (comment_id, timestamp, author_username, comment_text) VALUES (uuid(), '2023-11-09 12:32:13.332000+0000', 'Ana', '  Gostei');
INSERT INTO videos.comentarios (comment_id, timestamp, author_username, comment_text) VALUES (uuid(), '2023-11-10 10:10:10.101000+0000', 'Carlos', 'Incrível vídeo, muito informativo!');
INSERT INTO videos.comentarios (comment_id, timestamp, author_username, comment_text) VALUES (uuid(), '2023-11-10 11:11:11.111000+0000', 'Julia', 'Adorei a edição, bem feita!');
INSERT INTO videos.comentarios (comment_id, timestamp, author_username, comment_text) VALUES (uuid(), '2023-11-10 12:12:12.121000+0000', 'Pedro', 'Esperando a próxima parte ansiosamente!');
INSERT INTO videos.comentarios (comment_id, timestamp, author_username, comment_text) VALUES (uuid(), '2023-11-10 13:13:13.131000+0000', 'Sofia', 'Muito bem explicado, obrigada!');
INSERT INTO videos.comentarios (comment_id, timestamp, author_username, comment_text) VALUES (uuid(), '2023-11-10 14:14:14.141000+0000', 'Lucas', 'Que vídeo legal, aprendi muito!');
INSERT INTO videos.comentarios (comment_id, timestamp, author_username, comment_text) VALUES (uuid(), '2023-11-10 15:15:15.151000+0000', 'Mariana', 'Fantástico! Aguardo mais vídeos como este.');
INSERT INTO videos.comentarios (comment_id, timestamp, author_username, comment_text) VALUES (uuid(), '2023-11-10 16:16:16.161000+0000', 'Rafael', 'Bom trabalho, continue assim!');
INSERT INTO videos.comentarios (comment_id, timestamp, author_username, comment_text) VALUES (uuid(), '2023-11-10 17:17:17.171000+0000', 'Clara', 'Melhor vídeo que vi essa semana!');
INSERT INTO videos.comentarios (comment_id, timestamp, author_username, comment_text) VALUES (uuid(), '2023-11-10 18:18:18.181000+0000', 'Gabriel', 'Este vídeo é muito útil, obrigado!');


INSERT INTO videos.video_followers (video_id , user_id ) VALUES ( uuid(), uuid())  ;
INSERT INTO videos.video_followers (video_id , user_id ) VALUES ( uuid(), uuid())  ;
INSERT INTO videos.video_followers (video_id , user_id ) VALUES ( uuid(), uuid())  ;
INSERT INTO videos.video_followers (video_id , user_id ) VALUES ( uuid(), uuid())  ;
INSERT INTO videos.video_followers (video_id , user_id ) VALUES ( uuid(), uuid())  ;
INSERT INTO videos.video_followers (video_id , user_id ) VALUES ( uuid(), uuid())  ;
INSERT INTO videos.video_followers (video_id , user_id ) VALUES ( uuid(), uuid())  ;
INSERT INTO videos.video_followers (video_id , user_id ) VALUES ( uuid(), uuid())  ;
INSERT INTO videos.video_followers (video_id , user_id ) VALUES ( uuid(), uuid())  ;
INSERT INTO videos.video_followers (video_id , user_id ) VALUES ( uuid(), uuid())  ;


INSERT INTO video_eventos (video_id, user_id, event_type, event_timestamp, video_time_sec) VALUES (uuid(), uuid(), 'play', '2023-11-01 10:15:00', 45);
INSERT INTO video_eventos (video_id, user_id, event_type, event_timestamp, video_time_sec) VALUES (uuid(), uuid(), 'pause', '2023-11-01 11:20:00', 120);
INSERT INTO video_eventos (video_id, user_id, event_type, event_timestamp, video_time_sec) VALUES (uuid(), uuid(), 'stop', '2023-11-01 12:35:00', 300);
INSERT INTO video_eventos (video_id, user_id, event_type, event_timestamp, video_time_sec) VALUES (uuid(), uuid(), 'play', '2023-11-01 13:45:00', 60);
INSERT INTO video_eventos (video_id, user_id, event_type, event_timestamp, video_time_sec) VALUES (uuid(), uuid(), 'pause', '2023-11-01 14:50:00', 150);
INSERT INTO video_eventos (video_id, user_id, event_type, event_timestamp, video_time_sec) VALUES (uuid(), uuid(), 'stop', '2023-11-01 15:55:00', 210);
INSERT INTO video_eventos (video_id, user_id, event_type, event_timestamp, video_time_sec) VALUES (uuid(), uuid(), 'play', '2023-11-01 16:05:00', 75);
INSERT INTO video_eventos (video_id, user_id, event_type, event_timestamp, video_time_sec) VALUES (uuid(), uuid(), 'pause', '2023-11-01 17:10:00', 180);
INSERT INTO video_eventos (video_id, user_id, event_type, event_timestamp, video_time_sec) VALUES (uuid(), uuid(), 'stop', '2023-11-01 18:20:00', 240);
INSERT INTO video_eventos (video_id, user_id, event_type, event_timestamp, video_time_sec) VALUES (uuid(), uuid(), 'play', '2023-11-01 19:30:00', 30);



INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('João Silva', uuid(), 'Aventuras no Mar');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Maria Oliveira', uuid(), 'Receitas Saudáveis');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Carlos Pereira', uuid(), 'Fitness em Casa');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Ana Torres', uuid(), 'Viagem a Paris');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Ricardo Gomes', uuid(), 'Mundo da Tecnologia');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Sofia Ribeiro', uuid(), 'Arte Moderna Explicada');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Pedro Martins', uuid(), 'Jogos e Estratégias');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Luísa Fernandes', uuid(), 'Música Clássica para Todos');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('António Sousa', uuid(), 'História Mundial em Minutos');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Clara Machado', uuid(), 'Dicas de Jardinagem');


INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Autor1', uuid(), 'Video1');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Autor1', uuid(), 'Video1');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Autor2', uuid(), 'Video2');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Autor3', uuid(), 'Video3');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Autor4', uuid(), 'Video4');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Autor5', uuid(), 'Video5');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Autor6', uuid(), 'Video6');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Autor7', uuid(), 'Video7');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Autor8', uuid(), 'Video8');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Autor9', uuid(), 'Video9');
INSERT INTO videos_por_autor (autor, video_id, nome_video) VALUES ('Autor10', uuid(), 'Video10');



INSERT INTO comentarios_por_usuario (user_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), uuid(), 'Texto do comentário', toTimestamp(now()));
INSERT INTO comentarios_por_usuario (user_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), uuid(), 'Adorei este vídeo!', toTimestamp(now()));
INSERT INTO comentarios_por_usuario (user_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), uuid(), 'Muito informativo, obrigado.', toTimestamp(now()));
INSERT INTO comentarios_por_usuario (user_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), uuid(), 'Não concordo com o ponto de vista apresentado.', toTimestamp(now()));
INSERT INTO comentarios_por_usuario (user_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), uuid(), 'Este conteúdo é muito útil para iniciantes.', toTimestamp(now()));
INSERT INTO comentarios_por_usuario (user_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), uuid(), 'Por favor, faça mais vídeos sobre este tópico!', toTimestamp(now()));
INSERT INTO comentarios_por_usuario (user_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), uuid(), 'A qualidade do vídeo poderia ser melhor.', toTimestamp(now()));
INSERT INTO comentarios_por_usuario (user_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), uuid(), 'Incrível! Aprendi muito.', toTimestamp(now()));
INSERT INTO comentarios_por_usuario (user_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), uuid(), 'Estou ansioso pelo próximo vídeo.', toTimestamp(now()));
INSERT INTO comentarios_por_usuario (user_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), uuid(), 'Você poderia cobrir outros aspectos relacionados a este assunto?', toTimestamp(now()));



INSERT INTO comentarios_por_video (video_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), 'a', 'Excelente vídeo!', toTimestamp(now()));
INSERT INTO comentarios_por_video (video_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), 'a', 'Muito informativo, obrigado!', toTimestamp(now()));
INSERT INTO comentarios_por_video (video_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), 'a', 'Não concordo com este ponto, mas bom vídeo.', toTimestamp(now()));
INSERT INTO comentarios_por_video (video_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), 'a', 'Esta parte foi especialmente interessante.', toTimestamp(now()));
INSERT INTO comentarios_por_video (video_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), 'a', 'Adorei a edição!', toTimestamp(now()));
INSERT INTO comentarios_por_video (video_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), 'a', 'Poderia ter mais informações sobre o tópico.', toTimestamp(now()));
INSERT INTO comentarios_por_video (video_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), 'a', 'Excelente qualidade de imagem.', toTimestamp(now()));
INSERT INTO comentarios_por_video (video_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), 'a', 'Narrativa envolvente, parabéns!', toTimestamp(now()));
INSERT INTO comentarios_por_video (video_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), 'a', 'Gostei, mas faltou profundidade no assunto.', toTimestamp(now()));
INSERT INTO comentarios_por_video (video_id, comment_id, comment_text, timestamp_comment) VALUES (uuid(), 'a', 'Esperando a parte 2!', toTimestamp(now()));


INSERT INTO video_rating_summary (video_id, rating_avg, rating_count) VALUES (uuid(), 4.5, 10);
INSERT INTO video_rating_summary (video_id, rating_avg, rating_count) VALUES (uuid(), 3.8, 15);
INSERT INTO video_rating_summary (video_id, rating_avg, rating_count) VALUES (uuid(), 2.7, 8);
INSERT INTO video_rating_summary (video_id, rating_avg, rating_count) VALUES (uuid(), 4.0, 20);
INSERT INTO video_rating_summary (video_id, rating_avg, rating_count) VALUES (uuid(), 3.5, 5);
INSERT INTO video_rating_summary (video_id, rating_avg, rating_count) VALUES (uuid(), 4.2, 12);
INSERT INTO video_rating_summary (video_id, rating_avg, rating_count) VALUES (uuid(), 5.0, 4);
INSERT INTO video_rating_summary (video_id, rating_avg, rating_count) VALUES (uuid(), 2.3, 9);
INSERT INTO video_rating_summary (video_id, rating_avg, rating_count) VALUES (uuid(), 3.0, 7);
INSERT INTO video_rating_summary (video_id, rating_avg, rating_count) VALUES (uuid(), 4.8, 14);


