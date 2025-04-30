CREATE TABLE IF NOT EXISTS "order" (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    due_date TIMESTAMP,
    amount FLOAT,
    status VARCHAR(50),
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);
