CREATE TABLE categories(
            id bigserial PRIMARY KEY,
            title VARCHAR(255),
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

INSERT INTO categories (title) values('Food'),('Other');

CREATE TABLE products (
           id bigserial PRIMARY KEY,
           title VARCHAR(255),
           category_id bigint REFERENCES categories(id),
           price DECIMAL,
           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO products (title,price,category_id) VALUES
('Milk',80,1),
('Bread',25,1),
('Cheese',300,1),
('Ice cream',94,1);