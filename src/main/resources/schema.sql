CREATE TABLE IF NOT EXISTS order (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at DATETIME,
    updated_at DATETIME,
    due_date DATETIME,
    amount FLOAT,
    status VARCHAR(50),
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);
