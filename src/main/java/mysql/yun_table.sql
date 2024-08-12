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
   PRIMARY KEY ( shoppingCartID )
);

create TABLE orderID(
   orderID INT AUTO_INCREMENT,   
   memberID INT NOT NULL,
   orderDate DATETIME NOT NULL,
   status VARCHAR(20) NOT NULL,
   totalPrice INT NOT NULL,
   PRIMARY KEY ( orderID )
);

create TABLE order_details(
   ordDtIID INT(11) AUTO_INCREMENT,   
   productID INT NOT NULL,
   quantity INT NOT NULL,
   orderID INT NOT NULL,
   PRIMARY KEY ( ordDtIID )
);
