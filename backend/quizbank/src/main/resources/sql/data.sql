-- Opprett brukere
INSERT INTO users (username, password) VALUES ('alice', 'pass123');
INSERT INTO users (username, password) VALUES ('bob', 'pass456');

-- Hent IDene til de nylig opprettede brukerne for å sette som eiere av quizzene
-- Merk: Erstatt disse verdiene med de faktiske IDene fra databasen om nødvendig
SET @user_id_alice = (SELECT id FROM users WHERE username = 'alice');
SET @user_id_bob = (SELECT id FROM users WHERE username = 'bob');

-- Opprett quizzene tilhørende Alice
INSERT INTO quizzes (title, category, difficulty, user_id) VALUES ('Matte Quiz', 'Matematikk', 'Enkel', @user_id_alice);
INSERT INTO quizzes (title, category, difficulty, user_id) VALUES ('Historie Quiz', 'Historie', 'Middels', @user_id_alice);

-- Opprett quizzene tilhørende Bob
INSERT INTO quizzes (title, category, difficulty, user_id) VALUES ('Geografi Quiz', 'Geografi', 'Vanskelig', @user_id_bob);
INSERT INTO quizzes (title, category, difficulty, user_id) VALUES ('Vitenskap Quiz', 'Naturvitenskap', 'Middels', @user_id_bob);

-- Hent IDene til de nylig opprettede quizzene for å sette dem til spørsmålene
-- Merk: Erstatt disse verdiene med de faktiske IDene fra databasen om nødvendig
SET @quiz_id_matte = (SELECT id FROM quizzes WHERE title = 'Matte Quiz');
SET @quiz_id_historie = (SELECT id FROM quizzes WHERE title = 'Historie Quiz');
SET @quiz_id_geografi = (SELECT id FROM quizzes WHERE title = 'Geografi Quiz');
SET @quiz_id_vitenskap = (SELECT id FROM quizzes WHERE title = 'Vitenskap Quiz');

-- Opprett spørsmål for Matte Quiz
INSERT INTO questions (question_text, answer, quiz_id) VALUES ('Hva er 2+2?', '4', @quiz_id_matte);
INSERT INTO questions (question_text, answer, quiz_id) VALUES ('Hva er roten av 9?', '3', @quiz_id_matte);

-- Opprett spørsmål for Historie Quiz
INSERT INTO questions (question_text, answer, quiz_id) VALUES ('Hvem oppdaget Amerika?', 'Columbus', @quiz_id_historie);
INSERT INTO questions (question_text, answer, quiz_id) VALUES ('Hvilket år startet den første verdenskrig?', '1914', @quiz_id_historie);
