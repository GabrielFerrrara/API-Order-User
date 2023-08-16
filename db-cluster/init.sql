CREATE DATABASE IF NOT EXISTS db_microsservicos;


USE db_microsservicos;
CREATE TABLE IF NOT EXISTS `order_table` (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  item_description VARCHAR(255) NOT NULL,
  item_quantity INT NOT NULL,
  item_price DECIMAL(10, 2) NOT NULL,
  total_value DECIMAL(10, 2) NOT NULL,
  created_at DATETIME,
  updated_at DATETIME
);


CREATE TABLE IF NOT EXISTS user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  cpf VARCHAR(14) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone_number VARCHAR(20) NOT NULL,
  created_at DATETIME,
  updated_at DATETIME
);
