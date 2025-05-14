CREATE TABLE IF NOT EXISTS "laundromat" (
    id SERIAL PRIMARY KEY,
    creator_id INTEGER UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    name VARCHAR(63) NOT NULL,
    address VARCHAR(63) NOT NULL
);

CREATE TABLE IF NOT EXISTS "laundromat_role" (
    id SERIAL PRIMARY KEY,
    laundromat_id INTEGER NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    name VARCHAR(63) NOT NULL,
    FOREIGN KEY (laundromat_id) REFERENCES laundromat(id)
);

CREATE TABLE IF NOT EXISTS "order" (
    id SERIAL PRIMARY KEY,
    laundromat_id INTEGER NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    status VARCHAR(15) NOT NULL,
    customer_name VARCHAR(63) NOT NULL,
    customer_contact VARCHAR(63),
    grand_total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (laundromat_id) REFERENCES laundromat(id)
);

CREATE TABLE IF NOT EXISTS "order_item" (
    id SERIAL PRIMARY KEY,
    order_id INTEGER NOT NULL,
    service_name VARCHAR(63) NOT NULL,
    service_cost DECIMAL(10, 2) NOT NULL,
    quantity INTEGER NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS "product" (
    id SERIAL PRIMARY KEY,
    laundromat_id INTEGER NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    name VARCHAR(63) NOT NULL,
    inventory_cost DECIMAL(10, 8) NOT NULL,
    usage_cost DECIMAL(10, 8) NOT NULL,
    FOREIGN KEY (laundromat_id) REFERENCES laundromat(id)
);

CREATE TABLE IF NOT EXISTS "user" (
    id SERIAL PRIMARY KEY ,
    laundromat_id INTEGER NOT NULL,
    laundromat_role_id INTEGER NOT NULL,
    user_role VARCHAR(63) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    email VARCHAR(63) NOT NULL,
    username VARCHAR(63) NOT NULL,
    first_name VARCHAR(63) NOT NULL,
    last_name VARCHAR(63) NOT NULL,
    password VARCHAR(63) NOT NULL,
    locked BOOLEAN NOT NULL,
    enabled BOOLEAN NOT NULL,
    FOREIGN KEY (laundromat_id) REFERENCES laundromat(id),
    FOREIGN KEY (laundromat_role_id) REFERENCES laundromat_role(id)
);

CREATE TABLE IF NOT EXISTS "user_role" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(63) NOT NULL
)

CREATE TABLE IF NOT EXISTS "service_type" (
    id SERIAL PRIMARY KEY,
    laundromat_id INTEGER NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    name VARCHAR(63) NOT NULL,
    description VARCHAR(255) NOT NULL,
    service_cost DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (laundromat_id) REFERENCES laundromat(id)
);

CREATE TABLE IF NOT EXISTS "optional_order_item_product" (
    order_item_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    PRIMARY KEY (order_item_id, product_id),
    FOREIGN KEY (order_item_id) REFERENCES order_item(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE IF NOT EXISTS "required_service_product" (
    service_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    PRIMARY KEY (service_id, product_id),
    FOREIGN KEY (service_id) REFERENCES service_type(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE IF NOT EXISTS "optional_service_product" (
    service_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    PRIMARY KEY (service_id, product_id),
    FOREIGN KEY (service_id) REFERENCES service_type(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);
