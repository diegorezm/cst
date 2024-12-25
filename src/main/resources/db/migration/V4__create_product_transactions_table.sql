CREATE TABLE product_transactions (
    id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL,
    date TIMESTAMP NOT NULL,
    CONSTRAINT fk_products_id FOREIGN KEY (product_id) REFERENCES products(id) ON  DELETE CASCADE
);
