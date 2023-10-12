INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'USER');

INSERT INTO users (username, age, password) VALUES ('admin', 30, 'password123');
INSERT INTO users (username, age, password) VALUES ('user', 25, 'user');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);