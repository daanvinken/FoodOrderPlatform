DROP TABLE IF EXISTS orders;
CREATE TABLE orders (orderId SERIAL NOT NULL, restaurantId INT NOT NULL, orderItems json, orderAmount FLOAT(2) NOT NULL, userId INT NOT NULL, PRIMARY KEY (orderId));

INSERT INTO orders(restaurantId, orderAmount, userId) VALUES (4, 43.32, 3);