INSERT INTO categories (id, name) VALUES (1, 'hydraulic');
INSERT INTO categories (id, name) VALUES (2, 'pneumatic');
INSERT INTO categories (id, name) VALUES (3, 'service');
INSERT INTO categories (id, name) VALUES (4, 'spare');

INSERT INTO products (id,categories_id,created_at,description,name,price,status,stock)
VALUES (10,1,CURRENT_TIMESTAMP,'AXDV-C1 METERING VALVE','C1',1500.15,'AVAILABLE', 10);
INSERT INTO products (id, categories_id,created_at,description,name,price,status,stock)
VALUES (11,1,CURRENT_TIMESTAMP,'AXDV-C2 METERING VALVE','C2',1500.15,'AVAILABLE', 10);
INSERT INTO products (id, categories_id,created_at,description,name,price,status,stock)
VALUES (12,2,CURRENT_TIMESTAMP,'FTMO-A5','FTMO',40,'AVAILABLE', 5);