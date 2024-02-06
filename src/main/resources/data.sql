-- password=123
INSERT INTO users (id, email, status, password) VALUES (1, 'admin', 'STATUS_APPROVED', '$2a$10$A97KJPG9ZCW8Gc.BWP8lbOQ2L0eOYO51BpOqtFmq8QnZ/PT.j1Spm');
INSERT INTO users (id, email, status, password) VALUES (2, 'owner', 'STATUS_WAITING_FOR_APPROVAL', '$2a$10$A97KJPG9ZCW8Gc.BWP8lbOQ2L0eOYO51BpOqtFmq8QnZ/PT.j1Spm');
INSERT INTO users (id, email, status, password) VALUES (3, 'customer', 'STATUS_WAITING_FOR_APPROVAL', '$2a$10$A97KJPG9ZCW8Gc.BWP8lbOQ2L0eOYO51BpOqtFmq8QnZ/PT.j1Spm');


INSERT INTO roles (id, roletype) VALUES (1, 'ADMIN');
INSERT INTO roles (id, roletype) VALUES (2, 'OWNER');
INSERT INTO roles (id, roletype) VALUES (3, 'CUSTOMER');


INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 3);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 3);

INSERT INTO properties (id, slug, status, name, description, price, owner_id)
    VALUES (1, 'property-first', 'STATUS_AVAILABLE', 'Property 1', 'Description 1', 100000, 2);
INSERT INTO properties (id, slug, status, name, description, price, owner_id)
    VALUES (2, 'propetyu-2' , 'STATUS_AVAILABLE','Property 2', 'Description 2', 300000, 2);
INSERT INTO properties (id, slug, status, name, description, price, owner_id)
    VALUES (3, 'property-thre', 'STATUS_AVAILABLE', 'Property 3', 'Description 3', 250000, 2);

-- Offer

INSERT INTO offers (id, message, price, customer_id, status, property_id)
    VALUES(1, 'Hey this is my offer', 5000000.00, 1, 'OFFER_AVAILABLE', 1 );
INSERT INTO offers (id, message, price, customer_id, status, property_id)
VALUES(2, 'Hey this is Last offer', 58373000.00, 2, 'OFFER_PENDING', 2 );
INSERT INTO offers (id, message, price, customer_id, status, property_id)
VALUES(3, 'Here is the offer', 746258984.00, 3, 'OFFER_CONTINGENT', 3);