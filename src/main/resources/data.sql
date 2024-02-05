-- password=123
INSERT INTO users (id, email, status, password) VALUES (1, 'admin', 'STATUS_APPROVED', '$2a$10$A97KJPG9ZCW8Gc.BWP8lbOQ2L0eOYO51BpOqtFmq8QnZ/PT.j1Spm');
INSERT INTO users (id, email, status, password) VALUES (2, 'owner', 'STATUS_PENDING', '$2a$10$A97KJPG9ZCW8Gc.BWP8lbOQ2L0eOYO51BpOqtFmq8QnZ/PT.j1Spm');
INSERT INTO users (id, email, status, password) VALUES (3, 'customer', 'STATUS_PENDING', '$2a$10$A97KJPG9ZCW8Gc.BWP8lbOQ2L0eOYO51BpOqtFmq8QnZ/PT.j1Spm');

INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_OWNER');
INSERT INTO roles (id, name) VALUES (3, 'ROLE_CUSTOMER');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 3);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 3);

INSERT INTO properties (id, name, description, price, owner_id) VALUES (1, 'Property 1', 'Description 1', 100000, 2);
INSERT INTO properties (id, name, description, price, owner_id) VALUES (2, 'Property 2', 'Description 2', 300000, 2);
INSERT INTO properties (id, name, description, price, owner_id) VALUES (3, 'Property 3', 'Description 3', 250000, 2);