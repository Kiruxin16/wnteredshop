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
('Молоко', 100.20, 1),
('Хлеб', 80.20, 1),
('Сыр', 90.20, 1),
('Масло', 320.00, 1),
('Бублики', 50.00, 1),
('Пирожок', 30.00, 1),
('Огурцы', 140.00, 1),
('Помидоры', 160.00, 1),
('Лимонад', 100.00, 1),
('Подсолнечное масло', 110.00, 1),
('Мороженное', 70.00, 1),
('Coca-Cola 0.9 л.', 87.00, 1);