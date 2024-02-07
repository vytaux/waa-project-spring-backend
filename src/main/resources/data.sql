-- password=123
INSERT INTO users (id, email, password) VALUES (1, 'admin', '$2a$10$A97KJPG9ZCW8Gc.BWP8lbOQ2L0eOYO51BpOqtFmq8QnZ/PT.j1Spm');
INSERT INTO users (id, email, password) VALUES (2, 'owner', '$2a$10$A97KJPG9ZCW8Gc.BWP8lbOQ2L0eOYO51BpOqtFmq8QnZ/PT.j1Spm');
INSERT INTO users (id, email, password) VALUES (3, 'customer', '$2a$10$A97KJPG9ZCW8Gc.BWP8lbOQ2L0eOYO51BpOqtFmq8QnZ/PT.j1Spm');
INSERT INTO users (id, email, password) VALUES (4, 'john', '$2a$10$A97KJPG9ZCW8Gc.BWP8lbOQ2L0eOYO51BpOqtFmq8QnZ/PT.j1Spm');
INSERT INTO users (id, email, password) VALUES (5, 'jane', '$2a$10$A97KJPG9ZCW8Gc.BWP8lbOQ2L0eOYO51BpOqtFmq8QnZ/PT.j1Spm');


INSERT INTO roles (id, roletype) VALUES (1, 'ADMIN');
INSERT INTO roles (id, roletype) VALUES (2, 'OWNER');
INSERT INTO roles (id, roletype) VALUES (3, 'CUSTOMER');


INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 3);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 3);
INSERT INTO users_roles (user_id, role_id) VALUES (4, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (5, 3);

INSERT INTO properties (id, name, description, price, owner_id) VALUES (1, 'Property 1', 'Description 1', 100000, 2);
INSERT INTO properties (id, name, description, price, owner_id) VALUES (2, 'Property 2', 'Description 2', 300000, 2);
INSERT INTO properties (id, name, description, price, owner_id) VALUES (3, 'Property 3', 'Description 3', 250000, 2);

INSERT INTO message_session(user_one_id, user_two_id) VALUES (2, 3);

INSERT INTO message(message, message_session_id, created_by_id, created_at) VALUES ('hi', 1, 3, '2024-02-04');
INSERT INTO message(message, message_session_id, created_by_id, created_at) VALUES ('hello', 1, 2, '2024-02-04');
INSERT INTO message(message, message_session_id, created_by_id, created_at) VALUES ('How are you?', 1, 2, '2024-02-04');
INSERT INTO message(message, message_session_id, created_by_id, created_at) VALUES ('I am Good', 1, 3, '2024-02-04');