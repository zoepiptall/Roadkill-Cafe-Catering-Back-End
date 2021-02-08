use project2db;

DROP TABLE customer;
DROP TABLE phone_number;
DROP TABLE address;
DROP TABLE sales_order;
DROP TABLE inventory;
DROP TABLE invoice_item;

SET FOREIGN_KEY_CHECKS = 0;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE customer(
    user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(50),
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(150) UNIQUE
);
CREATE TABLE phone_number(
    phone_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    phone_number INT NOT NULL UNIQUE,
    customer_id INT NOT NULL,
    FOREIGN KEY (customer_id)
        REFERENCES customer(user_id)
);
CREATE TABLE address(
    address_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    street_address VARCHAR(255),
    city_address VARCHAR(255),
    zip_address VARCHAR(255),
    state_address VARCHAR(255),
    customer_id INT NOT NULL,
    FOREIGN KEY (customer_id)
        REFERENCES customer(user_id)
);
CREATE TABLE sales_order(
    order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    date_submitted TIMESTAMP,
    event_date TIMESTAMP,
    customer_id INT NOT NULL,
    billing_address_id INT,
    event_address_id INT,
    FOREIGN KEY (customer_id)
        REFERENCES customer(user_id),
    FOREIGN KEY (billing_address_id)
        REFERENCES address(address_id),
    FOREIGN KEY (event_address_id)
        REFERENCES address(address_id)
);
CREATE TABLE inventory(
    item_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255),
    sales_price DECIMAL(18,4) NOT NULL,
    description VARCHAR(1023),
    menu_img BLOB
);
CREATE TABLE invoice_item(
    invoice_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,

    quantity INT NOT NULL,
    inventory_id INT NOT NULL,
    order_id INT NOT NULL,
    FOREIGN KEY (inventory_id)
        REFERENCES inventory(item_id)
);

