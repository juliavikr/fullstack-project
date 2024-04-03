-- V4__insert_logical_data.sql
INSERT INTO users (username, password) VALUES ('olaNordmann', 'passord1'), ('kariNordmann', 'passord2');

INSERT INTO quizzes (title, category, difficulty, user_id) VALUES ('Film Quiz', 'Entertainment', 'intermediate', 1);

INSERT INTO questions (text, answer, quiz_id) VALUES ('Hvem spiller hovedrollen i filmen Forrest Gump?', 'Tom Hanks', 1);

INSERT INTO questions (text, answer, quiz_id) VALUES ('Hvem regisserte filmen Pulp Fiction?', 'Quentin Tarantino', 1);

INSERT INTO quizzes (title, category, difficulty, user_id) VALUES ('Hovedsteder', 'Geografi', 'Easy', 1);

INSERT INTO questions (text, answer, quiz_id) VALUES ('Hva er hovedstaden i Norge?', 'Oslo', 1);

INSERT INTO questions (text, answer, quiz_id) VALUES ('Hva er hovedstaden i Sverige?', 'Stockholm', 1);
