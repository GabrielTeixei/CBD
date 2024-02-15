// 7. Permitir a pesquisa de todos os videos de determinado autor;

SELECT * FROM videos WHERE autor = 'eu';

// 8. Permitir a pesquisa de comentarios por utilizador, ordenado inversamente pela data;

SELECT * FROM comentarios_por_usuario WHERE user_id = 63dc161d-1170-470b-939c-ea0eb1049187 ORDER BY timestamp_comment DESC;

// 9. Permitir a pesquisa de comentarios por videos, ordenado inversamente pela data;

SELECT comment_text FROM comentarios_por_video
WHERE video_id = ad2f6341-6e9c-4819-aa09-0532071a42da
ORDER BY timestamp_comment DESC;

// 10. Permitir a pesquisa do rating medio de um video e quantas vezes foi votado;

SELECT rating_avg, rating_count FROM video_rating_summary WHERE video_id = d11cd76b-5e17-4590-b9fe-1caa465b9646;

///--------------------------------------------------------------------------------------------------------------------

// 1. Os ultimos 3 comentarios introduzidos para um video;

SELECT comment_text FROM comentarios_por_video
WHERE video_id = ad2f6341-6e9c-4819-aa09-0532071a42da
ORDER BY timestamp_comment DESC
LIMIT 3;


// 2. Lista das tags de determinado video;

SELECT tags FROM video_gestao WHERE video_id = d4ca4b70-dccd-4d68-a397-811b69687e62;

// 3. Todos os videos com a tag Aveiro;(alterei para aventura)

SELECT * FROM video_gestao WHERE tags CONTAINS 'aventura' ALLOW FILTERING;

// 4. Os ultimos 5 eventos de determinado video realizados por um utilizador;

SELECT * FROM video_eventos
WHERE video_id = 2f7f6985-e67c-42b1-9e6f-b401a1328782 AND user_id = 594f3cc6-44ea-466b-a51a-f96a26ef3c17
ORDER BY event_timestamp DESC
LIMIT 5;


// 5. Videos partilhados por determinado utilizador (maria1987, por exemplo) num determinado periodo de tempo (Agosto de 2017, por exemplo);

SELECT * FROM video_gestao
WHERE autor = 'Carlos Pereira'
AND timestamp_upload >= '2023-11-01 00:00:00+0000'
AND timestamp_upload <= '2023-11-30 23:59:59+0000';


// 6. Os ultimos 10 videos, ordenado inversamente pela data da partilhada;

SELECT * FROM video_gestao  LIMIT 10;

// 7. Todos os seguidores (followers) de determinado video;

SELECT user_id FROM video_followers WHERE video_id = f02c55af-db01-42b3-ae19-27ff7952d457;

// 8. Todos os comentarios (dos videos) que determinado utilizador esta a seguir (following);

SELECT * FROM comentarios_por_video
WHERE video_id = ad2f6341-6e9c-4819-aa09-0532071a42da;


// 9. Os 5 videos com maior rating;
//Cassandra quando se tenta usar ORDER BY sem restringir a chave
// de partição.Nos so pdoemos usar ORDER BY em consultas que incluem uma cláusula WHERE restritiva na chave de partição, e neste caso nao temos uma chave de partição;
// 10. Uma query que retorne todos os videos e que mostre claramente a forma pela qual estao ordenados;
 SELECT *  FROM videos;


// 11. Lista com as Tags existentes e o numero de videos catalogados com cada uma delas;
SELECT video_id, tags
FROM video_gestao;

// Cassandra CQL não suporta operações JOIN como em sistemas de gerenciamento de banco de dados relacionais tradicionais. 
// 12.

Não  priorizam a escalabilidade e o desempenho para leituras e gravações distribuídas.
POis as operações de JOIN são usadas para combinar linhas de duas ou mais tabelas com base em colunas relacionadas.
E em cassandra é otimizada para acessar uma única partição de uma única tabela para manter a eficiência.
Logo a estrutura de dados do cassandra não suporta operações JOIN.

// 13.escreva 2 perguntas adicionais à base dados (alíneas 12 a 13), significativamente distintas das anteriores, e apresente igualmente a solução de pesquisa para cada questão

//13 Quais são os usuários que mais comentam e quantos comentários cada um fez?
SELECT user_id, COUNT(comment_id) as total_comments
FROM comentarios_por_usuario
GROUP BY user_id;

//Quais são os vídeos mais recentes de cada autor?
SELECT autor, MAX(timestamp_upload) as latest_upload
FROM video_gestao
GROUP BY autor;
