CREATE TABLE products (
           id bigserial PRIMARY KEY,
           title VARCHAR(255),
           price INT
);

INSERT INTO products (title,price) VALUES
('Milk',80),
('Bread',25),
('Cheese',300);