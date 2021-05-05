CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE customers
(
   id UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
   name VARCHAR NOT NULL,
   email VARCHAR NOT NULL,
   created_at TIMESTAMP NOT NULL,
   updated_at TIMESTAMP
);

CREATE TABLE customer_products
(
   customer_id UUID NOT NULL REFERENCES customers (id),
   product_id UUID NOT NULL,
   created_at TIMESTAMP NOT NULL,
   updated_at TIMESTAMP,
   PRIMARY KEY (customer_id, product_id)
);

-- There was no need to create indexes
