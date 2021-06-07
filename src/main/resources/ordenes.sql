DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Person;
DROP TABLE IF EXISTS Category;


CREATE TABLE Person
(
    dni VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (dni)
) ENGINE = InnoDB;

CREATE TABLE Category
(
    name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (name)
) ENGINE = InnoDB;

CREATE TABLE Product
(
    name VARCHAR(255) NOT NULL UNIQUE,
    category VARCHAR(255) NOT NULL,
    PRIMARY KEY (name),
    CONSTRAINT `fk_fruit_farmer`
    FOREIGN KEY (category) REFERENCES Category (name)
) ENGINE = InnoDB;

CREATE TABLE Orders
(
    id VARCHAR(255) NOT NULL,
    person VARCHAR(255) NOT NULL,
    product VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT `fk_person_order`
    FOREIGN KEY (person) REFERENCES Person (dni),
    CONSTRAINT `fk_product_order`
    FOREIGN KEY (product) REFERENCES Product (name)
) ENGINE = InnoDB;

INSERT INTO 
    Person (dni, name) 
VALUES
    ('000', 'Rick'),
    ('666', 'Morty');
INSERT INTO 
    Category (name) 
VALUES 
    ('ropa'),
    ('drogas');

INSERT INTO 
    Product (name,category) 
VALUES 
    ('jersey', 'ropa'),
    ('meta', 'drogas');

INSERT INTO 
    Orders (id,person,product) 
VALUES 
    ('aaa','000', 'jersey'),
    ('bbb', '666', 'meta');