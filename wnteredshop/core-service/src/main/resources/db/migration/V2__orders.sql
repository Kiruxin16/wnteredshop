CREATE TABLE orders(
                id bigserial PRIMARY KEY,
                username VARCHAR(255)  ,
                address VARCHAR(255),
                phone VARCHAR(255),
                total_price int,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE order_items(
                id bigserial PRIMARY KEY,
                product_id bigint REFERENCES products(id),
                order_id bigint REFERENCES orders(id),
                quantity int,
                price_per_product int,
                price int,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);