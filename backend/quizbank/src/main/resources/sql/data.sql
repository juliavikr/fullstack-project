
-- Legger til brukere
INSERT INTO users (username, password) VALUES ('Julia', '$2a$12$3hlFvj.oY9u6vDFbqv54eOo5q5RtXc6YzfRz0s0Z8nEc6yDi7ohDW');
INSERT INTO users (username, password) VALUES ('Andrea', '$2a$12$GkRnhca63t92ktDGWugix.8E8qdDT3.7KfdQMD/6xVzrJsxaLgOTS');

-- Henter bruker-IDer
SET @user_id_Julia = (SELECT id FROM users WHERE username = 'Julia');
SET @user_id_Andrea = (SELECT id FROM users WHERE username = 'Andrea');

-- Legger til quizer
INSERT INTO quizzes (title, category, difficulty, user_id) VALUES ('Matte Quiz', 'Matematikk', 'Enkel', @user_id_Julia);
INSERT INTO quizzes (title, category, difficulty, user_id) VALUES ('Historie Quiz', 'Historie', 'Middels', @user_id_Andrea);
INSERT INTO quizzes (title, category, difficulty, user_id) VALUES ('Geografi Quiz', 'Geografi', 'Vanskelig', @user_id_Andrea);
INSERT INTO quizzes (title, category, difficulty, user_id) VALUES ('Vitenskap Quiz', 'Naturvitenskap', 'Middels', @user_id_Julia);

-- Henter quiz-IDer
SET @quiz_id_matte = (SELECT id FROM quizzes WHERE title = 'Matte Quiz' AND user_id = @user_id_Julia LIMIT 1);
SET @quiz_id_historie = (SELECT id FROM quizzes WHERE title = 'Historie Quiz' AND user_id = @user_id_Andrea LIMIT 1);
SET @quiz_id_geografi = (SELECT id FROM quizzes WHERE title = 'Geografi Quiz' AND user_id = @user_id_Andrea LIMIT 1);
SET @quiz_id_vitenskap = (SELECT id FROM quizzes WHERE title = 'Vitenskap Quiz' AND user_id = @user_id_Julia LIMIT 1);

-- Legger til spørsmål
INSERT INTO questions (question_text, answer, quiz_id) VALUES ('Hva er 2+2?', '4', @quiz_id_matte);
INSERT INTO questions (question_text, answer, quiz_id) VALUES ('Hva er roten av 9?', '3', @quiz_id_matte);
-- Historie
INSERT INTO questions (question_text, answer, quiz_id) VALUES ('Hvem oppdaget Amerika?', 'Columbus', @quiz_id_historie);
INSERT INTO questions (question_text, answer, quiz_id) VALUES ('Hvilket år startet den første verdenskrig?', '1914', @quiz_id_historie);

