CREATE DATABASE online_store;

USE online_store;

CREATE TABLE `users` (
                         `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         `first_name` VARCHAR(40) NOT NULL,
                         `last_name` VARCHAR(40) NOT NULL,
                         `username` VARCHAR(40) NOT NULL UNIQUE,
                         `email` VARCHAR(100) NOT NULL UNIQUE,
                         `password` VARCHAR(64) NOT NULL,
                         `address` VARCHAR(200) NOT NULL,
                         `phone_number` VARCHAR(15) NOT NULL UNIQUE,
                         `role` VARCHAR(15) NOT NULL DEFAULT 'ROLE_USER',
                         `enabled` TINYINT NOT NULL DEFAULT 1);

CREATE TABLE `products` (
                            `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            `title` VARCHAR(200) NOT NULL UNIQUE,
                            `description` VARCHAR(3000) NOT NULL,
                            `image` VARCHAR(200) NOT NULL,
                            `producer` VARCHAR(80) NOT NULL,
                            `price` FLOAT NOT NULL);

CREATE TABLE `orders` (
                          `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          `total` FLOAT NOT NULL,
                          `date` DATE NOT NULL,
                          `user_id` BIGINT NOT NULL,
                          KEY `FK_user_id_idx` (`user_id`),
                          CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `cart_items` (
                              `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              `quantity` INT NOT NULL DEFAULT 1,
                              `user_id` BIGINT NOT NULL,
                              `product_id` BIGINT NOT NULL,
                              KEY `FK_user_id_idx` (`user_id`),
                              KEY `FK_product_id_idx` (`product_id`),
                              CONSTRAINT `FK_user_id_cart` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                              CONSTRAINT `FK_product_id_cart` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`));

CREATE TABLE `order_details` (
                                 `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 `quantity` TINYINT NOT NULL,
                                 `unit_price` FLOAT NOT NULL,
                                 `subtotal` FLOAT AS (unit_price * quantity),
                                 `product_id` BIGINT NOT NULL,
                                 `order_id` BIGINT NOT NULL,
                                 KEY `FK_product_id_order_idx` (`product_id`),
                                 KEY `FK_order_id_idx` (`order_id`) ,
                                 CONSTRAINT `FK_product_id_order` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
                                 CONSTRAINT `FK_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`));