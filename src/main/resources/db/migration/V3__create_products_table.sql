CREATE TABLE  products(
  id SERIAL PRIMARY KEY,
  entity_id INTEGER NOT NULL,
  sold BOOLEAN DEFAULT FALSE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_product_entity_id FOREIGN KEY (entity_id) REFERENCES product_entities(id) ON DELETE CASCADE
)
