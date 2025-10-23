INSERT INTO movies (name, date, genre, director, description, rating, image) VALUES
                                                                                             ('Oppenheimer', '2023', 'Biographical thriller', 'Christopher Nolan', 'Chronicles the life of J. Robert Oppenheimer, the physicist behind the atomic bomb.', '8.6', 'https://fwcdn.pl/fpo/28/17/10002817/8072064_2.3.jpg'),
                                                                                             ('The Godfather', '1972', 'Crime drama', 'Francis Ford Coppola', 'The transformation of Michael Corleone from family outsider to ruthless mafia boss.', '9.2', 'https://m.media-amazon.com/images/M/MV5BNGEwYjgwOGQtYjg5ZS00Njc1LTk2ZGEtM2QwZWQ2NjdhZTE5XkEyXkFqcGc@._V1_.jpg'),
                                                                                             ('Forrest Gump', '1994', 'Comedy-drama', 'Robert Zemeckis', 'A kind-hearted man with a low IQ witnesses historical events in extraordinary ways.', '8.8', 'https://m.media-amazon.com/images/M/MV5BNDYwNzVjMTItZmU5YS00YjQ5LTljYjgtMjY2NDVmYWMyNWFmXkEyXkFqcGc@._V1_.jpg'),
                                                                                             ('Inception', '2010', 'Sci-Fi thriller', 'Christopher Nolan', 'A thief who enters dreams must plant an idea in someone’s mind.', '8.8', 'https://m.media-amazon.com/images/I/81p+xe8cbnL._AC_SY679_.jpg'),
                                                                                             ('The Dark Knight', '2008', 'Action crime', 'Christopher Nolan', 'Batman faces the Joker in a battle for Gotham’s soul.', '9.0', 'https://m.media-amazon.com/images/S/pv-target-images/e9a43e647b2ca70e75a3c0af046c4dfdcd712380889779cbdc2c57d94ab63902.jpg'),
                                                                                             ('Pulp Fiction', '1994', 'Crime drama', 'Quentin Tarantino', 'The lives of hitmen, a boxer, and criminals intertwine in LA.', '8.9', 'https://m.media-amazon.com/images/I/71c05lTE03L._AC_SY679_.jpg'),
                                                                                             ('The Shawshank Redemption', '1994', 'Drama', 'Frank Darabont', 'Two imprisoned men bond over years, finding solace and redemption.', '9.3', 'https://m.media-amazon.com/images/I/51NiGlapXlL.jpg'),
                                                                                             ('Fight Club', '1999', 'Drama thriller', 'David Fincher', 'An insomniac and a soap salesman form an underground fight club.', '8.8', 'https://m.media-amazon.com/images/M/MV5BOTgyOGQ1NDItNGU3Ny00MjU3LTg2YWEtNmEyYjBiMjI1Y2M5XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg'),
                                                                                             ('Interstellar', '2014', 'Sci-Fi drama', 'Christopher Nolan', 'Explorers travel through a wormhole in space to save humanity.', '8.6', 'https://m.media-amazon.com/images/I/91vIHsL-zjL.jpg'),
                                                                                             ('The Matrix', '1999', 'Sci-Fi action', 'The Wachowskis', 'A hacker learns reality is a simulation and joins a rebellion.', '8.7', 'https://m.media-amazon.com/images/I/51EG732BV3L.jpg');
INSERT INTO actors (name) VALUES
                              ('Cillian Murphy '),
                              ('Emily Blunt '),
                              ('Marlon Brando '),
                              ('Al Pacino '),
                              ('Tom Hanks '),
                              ('Robin Wright '),
                              ('Leonardo DiCaprio '),
                              ('Joseph Gordon-Levitt '),
                              ('Christian Bale '),
                              ('Heath Ledger '),
                              ('John Travolta '),
                              ('Samuel L. Jackson '),
                              ('Tim Robbins '),
                              ('Morgan Freeman '),
                              ('Edward Norton '),
                              ('Brad Pitt '),
                              ('Matthew McConaughey '),
                              ('Anne Hathaway '),
                              ('Keanu Reeves '),
                              ('Carrie-Anne Moss ');
INSERT INTO streaming_platforms (name) VALUES
                                           ('Netflix '),
                                           ('Amazon Prime '),
                                           ('HBO Max '),
                                           ('Disney+ '),
                                           ('Apple TV+ '),
                                           ('Hulu ');
-- Oppenheimer
INSERT INTO movie_actors VALUES (1, 1), (2, 1);

-- The Godfather
INSERT INTO movie_actors VALUES (3, 2), (4, 2);

-- Forrest Gump
INSERT INTO movie_actors VALUES (5, 3), (6, 3);

-- Inception
INSERT INTO movie_actors VALUES (7, 4), (8, 4);

-- The Dark Knight
INSERT INTO movie_actors VALUES (9, 5), (10, 5);

-- Pulp Fiction
INSERT INTO movie_actors VALUES (11, 6), (12, 6);

-- The Shawshank Redemption
INSERT INTO movie_actors VALUES (13, 7), (14, 7);

-- Fight Club
INSERT INTO movie_actors VALUES (15, 8), (16, 8);

-- Interstellar
INSERT INTO movie_actors VALUES (17, 9), (18, 9);

-- The Matrix
INSERT INTO movie_actors VALUES (19, 10), (20, 10);
--Oppenheimer
-- Oppenheimer
INSERT INTO movie_platforms VALUES (1, 2), (1, 3);

-- The Godfather
INSERT INTO movie_platforms VALUES (2, 3);

-- Forrest Gump
INSERT INTO movie_platforms VALUES (3, 1), (3, 2);

-- Inception
INSERT INTO movie_platforms VALUES (4, 1), (4, 4);

-- The Dark Knight
INSERT INTO movie_platforms VALUES (5, 2), (5, 5);

-- Pulp Fiction
INSERT INTO movie_platforms VALUES (6, 1), (6, 6);

-- The Shawshank Redemption
INSERT INTO movie_platforms VALUES (7, 1), (7, 3);

-- Fight Club
INSERT INTO movie_platforms VALUES (8, 2), (8, 4);

-- Interstellar
INSERT INTO movie_platforms VALUES (9, 5);

-- The Matrix
INSERT INTO movie_platforms VALUES (10, 1), (10, 2), (10, 4);
