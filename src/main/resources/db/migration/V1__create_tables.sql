-- one entity
CREATE TABLE cars (
  car_id varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (car_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- one entity composed id
CREATE TABLE books (
  author varchar(100) NOT NULL,
  title varchar(100) NOT NULL,
  publisher varchar(100) NOT NULL,
  PRIMARY KEY (author, title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- one to many
CREATE TABLE shopping_cart (
  shopping_cart_id int AUTO_INCREMENT PRIMARY KEY,
  customer_name varchar(100) NOT NULL,
  created_time datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE item(
  item_id int  AUTO_INCREMENT PRIMARY KEY,
  item_name varchar(100) NOT NULL,
  shopping_cart_id int NOT NULL,
  FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart(shopping_cart_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE discount(
  discount_id int  AUTO_INCREMENT PRIMARY KEY,
  percentage float NOT NULL,
  discount_name varchar(100) NOT NULL,
  shopping_cart_id int NOT NULL,
  FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart(shopping_cart_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- audit
CREATE TABLE payments(
    payment_id int AUTO_INCREMENT PRIMARY KEY,
    amount float NOT NULL,
    created_by varchar(100) NOT NULL,
    created datetime NOT NULL,
    modified_by varchar(100),
    modified datetime,
    PRIMARY KEY(payment_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
