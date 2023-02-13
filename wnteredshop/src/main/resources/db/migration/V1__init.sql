CREATE TABLE products (
           id bigserial PRIMARY KEY,
           title VARCHAR(255),
           price INT,
           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO products (title,price) VALUES
('Milk',80),
('Bread',25),
('Cheese',300);