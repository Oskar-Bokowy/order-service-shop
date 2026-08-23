CREATE TABLE customer_order (
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
client_id BIGINT NOT NULL,
created_at TIMESTAMP,
total_price DECIMAL (10,2) CHECK (total_price >0),
discount DECIMAL (5,2) CHECK (discount >0),
shipping_cost DECIMAL (3,2) CHECK (shipping_cost >0),
order_status VARCHAR(25)
);


CREATE TABLE order_item (
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
product_id BIGINT NOT NULL,
product_name VARCHAR(50),
quantity INTEGER CHECK(quantity > 0),
price_at_purchase DECIMAL(10,2) CHECK (price_at_purchase > 0),
discount_item DECIMAL(2,2) CHECK (discount_item > 0),
order_id BIGINT NOT NULL
);

ALTER TABLE order_item
ADD CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES customer_order(id);
