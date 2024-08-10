create TABLE product(
   productID INT AUTO_INCREMENT,   
   prodName VARCHAR(50),
   price INT NOT NULL,
   productQuantity INT NOT NULL,
   intro VARCHAR(500) NOT NULL,
   productPic LONGBLOB,
   PRIMARY KEY ( productID )
);

create TABLE shopping_cart(
   shoppingCartID INT AUTO_INCREMENT,   
   memberID INT NOT NULL,
   productID INT NOT NULL,
   quantity INT NOT NULL,
   PRIMARY KEY ( shoppingCartID ),
   CONSTRAINT fk_shopping_cart_memberID
   FOREIGN KEY (memberID) REFERENCES member (memberID),
   CONSTRAINT fk_shopping_cart_productID
   FOREIGN KEY (productID) REFERENCES product (productID)
);

create TABLE orderID(
   orderID INT AUTO_INCREMENT,   
   memberID INT NOT NULL,
   orderDate DATETIME NOT NULL,
   status VARCHAR(20) NOT NULL,
   totalPrice INT NOT NULL,
   PRIMARY KEY ( orderID ),
   CONSTRAINT fk_order_memberID
   FOREIGN KEY (memberID) REFERENCES member (memberID)
);

create TABLE order_details(
   ordDtIID INT(11) AUTO_INCREMENT,   
   productID INT NOT NULL,
   quantity INT NOT NULL,
   orderID INT NOT NULL,
   PRIMARY KEY ( ordDtIID ),
   CONSTRAINT fk_order_details_productID
   FOREIGN KEY (productID) REFERENCES product (productID),
   CONSTRAINT fk_order_details_orderID
   FOREIGN KEY (orderID) REFERENCES orderID (orderID)
);

--之前為了測試建立的臨時member table
--create TABLE member(
--   memberID INT AUTO_INCREMENT,   
--   PRIMARY KEY ( memberID )
--);