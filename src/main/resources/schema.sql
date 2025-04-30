CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(64),
    inventory_cost FLOAT,
    usage_cost FLOAT
);