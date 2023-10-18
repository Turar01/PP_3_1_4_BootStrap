INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'USER');

INSERT INTO users (username, age, password) VALUES ('admin', 30, '$2a$10$fdW3PN2lANRUILHnuO9Qvuvp62f9nmykStbe3meGNf58g3K5zmdWG');
INSERT INTO users (username, age, password) VALUES ('user', 25, '$2a$10$82lOWwxJ2cE9E5Y5KalC1.LvtL23VblLAbNuME5XArZrscU7Os/M.');


INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);