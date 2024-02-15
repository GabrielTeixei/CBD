CREATE KEYSPACE IF NOT EXISTS bookstore
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE bookstore;

CREATE TABLE IF NOT EXISTS authors (
  author_id UUID PRIMARY KEY,
  name TEXT,
  nationality TEXT,
  birth_year INT
);

CREATE TABLE IF NOT EXISTS books (
  book_id UUID PRIMARY KEY,
  title TEXT,
  author_id UUID,
  publication_year INT,
  genres SET<TEXT>,
  price DECIMAL
);

CREATE TABLE IF NOT EXISTS orders (
  order_id TEXT PRIMARY KEY,
  user_id TEXT,
  book_ids SET<TEXT>,
  order_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS reviews (
  review_id TEXT PRIMARY KEY,
  book_id TEXT,
  user_id TEXT,
  rating INT,
  comment TEXT
);



CREATE INDEX IF NOT EXISTS idx_author_nationality ON authors (nationality);
CREATE INDEX IF NOT EXISTS idx_book_genres ON books (genres);


INSERT INTO books (book_id, title, author_id, publication_year, genres, price) VALUES('book1', 'Livro 1', 'author1', 2000, {'Aventura', 'Ficção'}, 20.99),
INSERT INTO books (book_id, title, author_id, publication_year, genres, price) VALUES('book2', 'Livro 2', 'author2', 1995, {'Drama', 'Romance'}, 15.99);
INSERT INTO books (book_id, title, author_id, publication_year, genres, price) VALUES('book3', 'Livro 3', 'author3', 2022, {'Ficção Científica', 'Aventura'}, 24.99);
INSERT INTO books (book_id, title, author_id, publication_year, genres, price) VALUES('book4', 'Livro 4', 'author4', 2015, {'Mistério', 'Suspense'}, 19.50);
INSERT INTO books (book_id, title, author_id, publication_year, genres, price) VALUES('book5', 'Livro 5', 'author5', 2010, {'Fantasia', 'Aventura'}, 21.00);
INSERT INTO books (book_id, title, author_id, publication_year, genres, price) VALUES('book9', 'A História da Ciência', 'author6', 2017, {'Não Ficção', 'História'}, 25.00);
INSERT INTO books (book_id, title, author_id, publication_year, genres, price) VALUES('book10', 'A Saga dos Reinos', 'author7', 2020, {'Fantasia', 'Aventura'}, 23.75);
INSERT INTO books (book_id, title, author_id, publication_year, genres, price) VALUES('book11', 'Em Busca da Felicidade', 'author8', 2016, {'Autoajuda', 'Psicologia'}, 17.99);
INSERT INTO books (book_id, title, author_id, publication_year, genres, price) VALUES('book12', 'Vida de um Gênio', 'author9', 2022, {'Biografia', 'História'}, 19.99);
INSERT INTO books (book_id, title, author_id, publication_year, genres, price) VALUES('book12', 'Vida de um Gênio', 'author9', 2022, {'Biografia', 'História'}, 19.99);
INSERT INTO books (book_id, title, author_id, publication_year, genres, price) VALUES('book10', 'A Saga dos Reinos', 'author7', 2020, {'Fantasia', 'Aventura'}, 23.75);




INSERT INTO authors (author_id, name, nationality, birth_year) VALUES ('author1', 'Autor 1', 'Nacionalidade 1', 1980);
INSERT INTO authors (author_id, name, nationality, birth_year) VALUES ('author2', 'Autor 2', 'Nacionalidade 2', 1975);
INSERT INTO authors (author_id, name, nationality, birth_year) VALUES ('author3', 'Autor 3', 'Nacionalidade 3', 1990);
INSERT INTO authors (author_id, name, nationality, birth_year) VALUES ('author4', 'Autor 4', 'Nacionalidade 4', 1985);
INSERT INTO authors (author_id, name, nationality, birth_year) VALUES ('author5', 'Autor 5', 'Nacionalidade 5', 2000);
INSERT INTO authors (author_id, name, nationality, birth_year) VALUES ('author6', 'Autor de Contos Sci-Fi', 'Nacionalidade 6', 1985);
INSERT INTO authors (author_id, name, nationality, birth_year) VALUES ('author7', 'Autor de Poesia', 'Nacionalidade 7', 1992);
INSERT INTO authors (author_id, name, nationality, birth_year) VALUES ('author8', 'Autor de Livros Infantis', 'Nacionalidade 8', 1978);
INSERT INTO authors (author_id, name, nationality, birth_year) VALUES ('author9', 'Autor de Terror', 'Nacionalidade 9', 1967);
INSERT INTO authors (author_id, name, nationality, birth_year) VALUES ('author10', 'Autor de Romances Históricos', 'Nacionalidade 10', 1990);
INSERT INTO authors (author_id, name, nationality, birth_year) VALUES ('author11', 'Autor de Livros de Viagens', 'Nacionalidade 11', 1982);
INSERT INTO authors (author_id, name, nationality, birth_year) VALUES ('author12', 'Autor de Ficção Policial', 'Nacionalidade 12', 1973); 
INSERT INTO authors (author_id, name, nationality, birth_year) VALUES ('author13', 'Autor de Autoajuda', 'Nacionalidade 13', 1989);


INSERT INTO orders (order_id, user_id, book_ids, order_date) VALUES('order1', 'user1', {'book1', 'book2', 'book3'}, toTimestamp(now()));
INSERT INTO orders (order_id, user_id, book_ids, order_date) VALUES('order2', 'user2', {'book4', 'book5'}, toTimestamp(now()));
INSERT INTO orders (order_id, user_id, book_ids, order_date) VALUES('order3', 'user3', {'book1', 'book3', 'book5'}, toTimestamp(now()));
INSERT INTO orders (order_id, user_id, book_ids, order_date) VALUES('order4', 'user4', {'book2', 'book4'}, toTimestamp(now()));
INSERT INTO orders (order_id, user_id, book_ids, order_date) VALUES('order5', 'user5', {'book1', 'book2', 'book5'}, toTimestamp(now()));
INSERT INTO orders (order_id, user_id, book_ids, order_date) VALUES('order6', 'user6', {'book1', 'book2', 'book3', 'book4'}, toTimestamp(now()));
INSERT INTO orders (order_id, user_id, book_ids, order_date) VALUES('order7', 'user7', {'book2', 'book4'}, toTimestamp(now()));
INSERT INTO orders (order_id, user_id, book_ids, order_date) VALUES('order8', 'user8', {'book1', 'book3', 'book5'}, toTimestamp(now()));
INSERT INTO orders (order_id, user_id, book_ids, order_date) VALUES('order9', 'user9', {'book4'}, toTimestamp(now()));
INSERT INTO orders (order_id, user_id, book_ids, order_date) VALUES('order10', 'user10', {'book1', 'book2', 'book3', 'book4', 'book5'}, toTimestamp(now()));
INSERT INTO orders (order_id, user_id, book_ids, order_date) VALUES('order11', 'user11', {'book1', 'book5'}, toTimestamp(now()));
INSERT INTO orders (order_id, user_id, book_ids, order_date) VALUES('order12', 'user12', {'book2', 'book3', 'book4'}, toTimestamp(now()));
INSERT INTO orders (order_id, user_id, book_ids, order_date) VALUES('order13', 'user13', {'book1', 'book2', 'book4', 'book5'}, toTimestamp(now()));





INSERT INTO reviews (review_id, book_id, user_id, rating, comment) VALUES('review1', 'book1', 'user1', 4, 'Ótimo livro!');
INSERT INTO reviews (review_id, book_id, user_id, rating, comment) VALUES('review2', 'book2', 'user2', 5, 'Muito bom.');
INSERT INTO reviews (review_id, book_id, user_id, rating, comment) VALUES ('review3', 'book3', 'user3', 4, 'Recomendo.');
INSERT INTO reviews (review_id, book_id, user_id, rating, comment) VALUES ('review4', 'book4', 'user4', 3, 'Interessante.');
INSERT INTO reviews (review_id, book_id, user_id, rating, comment) VALUES ('review5', 'book5', 'user5', 5, 'Excelente livro.');
INSERT INTO reviews (review_id, book_id, user_id, rating, comment) VALUES ('review6', 'book6', 'user6', 4, 'Uma leitura surpreendente! Recomendo a todos.');
INSERT INTO reviews (review_id, book_id, user_id, rating, comment) VALUES ('review7', 'book7', 'user7', 5, 'Este livro mudou minha vida. Incrível!');
INSERT INTO reviews (review_id, book_id, user_id, rating, comment) VALUES ('review8', 'book8', 'user8', 3, 'Mais ou menos.');
INSERT INTO reviews (review_id, book_id, user_id, rating, comment) VALUES ('review9', 'book9', 'user9', 4, 'Uma trama intrigante que me manteve envolvido até o fim.');
INSERT INTO reviews (review_id, book_id, user_id, rating, comment) VALUES ('review10', 'book10', 'user10', 5, 'Uma obra-prima da literatura.');
INSERT INTO reviews (review_id, book_id, user_id, rating, comment) VALUES ('review11', 'book11', 'user11', 2, 'Não gostei muito deste livro. Esperava mais.');
INSERT INTO reviews (review_id, book_id, user_id, rating, comment) VALUES ('review12', 'book12', 'user12', 5, 'Absolutamente fantástico! O melhor livro que já li.');
INSERT INTO reviews (review_id, book_id, user_id, rating, comment) VALUES ('review13', 'book13', 'user13', 4, 'Uma história que me fez refletir sobre a vida.');


//1-Listar todos os autores:

SELECT * FROM authors;

//2-Listar todos os livros de um determinado gênero (por exemplo, "Aventura"):


SELECT * FROM books WHERE genres CONTAINS 'Aventura';


//3-Encontrar todos os livros publicados após o ano de 2000:

SELECT * FROM books WHERE publication_year > 2000 ALLOW FILTERING ;


//4-Listar todas as avaliações de livros com classificação 5:

SELECT * FROM reviews WHERE rating = 5 ALLOW FILTERING ;


//5-Encontrar o autor de um livro específico (substitua 'book1' pelo ID do livro desejado):

SELECT author_id FROM books WHERE book_id = 'book1';


//6-Encontrar todos os livros de um determinado autor (substitua 'author1' pelo ID do autor desejado):

SELECT * FROM books WHERE author_id = 'author1' ALLOW FILTERING ;


//7-Encontrar todos os livros de um determinado autor, publicados após o ano de 2000:

SELECT * FROM books WHERE author_id = 'author1' AND publication_year > 2000 ALLOW FILTERING ;


//8-Listar todos os autores de uma nacionalidade específica:

SELECT * FROM authors WHERE nationality = 'Nacionalidade 1';


//9-Listar todos os livros de um determinado gênero (Aventura):
SELECT * FROM books WHERE genres CONTAINS 'Aventura';

//10-Listar todos os autores:

SELECT * FROM authors;



///Utilização de, pelo menos, 5 updates e 5 deletes de dados:  ____----------____

//Updates: ____----------____

//Atualizar o nome de um autor

UPDATE authors SET name = 'Novo Nome' WHERE author_id = 'author1';


//Atualizar o preço de um livro:


UPDATE books SET price = 25.99 WHERE book_id = 'book1';


//Atualizar o comentário de uma avaliação:

UPDATE reviews SET comment = 'Comentário Atualizado' WHERE review_id = 'review1';

//Adicionar um novo livro ao conjunto de IDs de livros de um pedido:


UPDATE orders SET book_ids = book_ids + {'book6'} WHERE order_id = 'order1';




///Deletes: ____----------____


//Excluir um autor e todos os seus livros associados:


DELETE FROM authors WHERE author_id = 'author2';

//Excluir um livro específico:

DELETE FROM books WHERE book_id = 'book3';

//Remover um livro do conjunto de IDs de livros de um pedido:

UPDATE orders SET book_ids = book_ids - {'book2'} WHERE order_id = 'order1';

//Excluir um pedido com base no ID:

DELETE FROM orders WHERE order_id = 'order5';