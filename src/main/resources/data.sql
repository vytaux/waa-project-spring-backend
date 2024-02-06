-- password=123
INSERT INTO users (id, email, status, password) VALUES (91, 'admin', 'STATUS_APPROVED', '$2a$10$A97KJPG9ZCW8Gc.BWP8lbOQ2L0eOYO51BpOqtFmq8QnZ/PT.j1Spm');
INSERT INTO users (id, email, status, password) VALUES (92, 'owner', 'STATUS_WAITING_FOR_APPROVAL', '$2a$10$A97KJPG9ZCW8Gc.BWP8lbOQ2L0eOYO51BpOqtFmq8QnZ/PT.j1Spm');
INSERT INTO users (id, email, status, password) VALUES (93, 'customer', 'STATUS_WAITING_FOR_APPROVAL', '$2a$10$A97KJPG9ZCW8Gc.BWP8lbOQ2L0eOYO51BpOqtFmq8QnZ/PT.j1Spm');


INSERT INTO roles (id, roletype) VALUES (91, 'ADMIN');
INSERT INTO roles (id, roletype) VALUES (92, 'OWNER');
INSERT INTO roles (id, roletype) VALUES (93, 'CUSTOMER');


INSERT INTO users_roles (user_id, role_id) VALUES (91, 91);
INSERT INTO users_roles (user_id, role_id) VALUES (91, 92);
INSERT INTO users_roles (user_id, role_id) VALUES (91, 93);
INSERT INTO users_roles (user_id, role_id) VALUES (92, 92);
INSERT INTO users_roles (user_id, role_id) VALUES (93, 93);

INSERT INTO properties (id, slug, status, name, description, price, owner_id)
    VALUES (91, 'property-first', 'STATUS_AVAILABLE', 'Property 1', 'Description 1', 100000, 91);
INSERT INTO properties (id, slug, status, name, description, price, owner_id)
    VALUES (92, 'propetyu-2' , 'STATUS_AVAILABLE','Property 2', 'Description 2', 300000, 92);
INSERT INTO properties (id, slug, status, name, description, price, owner_id)
    VALUES (93, 'property-thre', 'STATUS_AVAILABLE', 'Property 3', 'Description 3', 250000, 92);
    VALUES (3, 'property-thre', 'STATUS_AVAILABLE', 'Property 3', 'Description 3', 250000, 2);

-- Offer

INSERT INTO offers (id, message, price, customer_id, status, property_id)
    VALUES(1, 'Hey this is my offer', 5000000.00, 1, 'OFFER_AVAILABLE', 1 );
INSERT INTO offers (id, message, price, customer_id, status, property_id)
VALUES(2, 'Hey this is Last offer', 58373000.00, 2, 'OFFER_PENDING', 2 );
INSERT INTO offers (id, message, price, customer_id, status, property_id)
VALUES(3, 'Here is the offer', 746258984.00, 3, 'OFFER_CONTINGENT', 3);