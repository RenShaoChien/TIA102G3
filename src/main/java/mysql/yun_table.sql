create TABLE product(
   productID INT AUTO_INCREMENT,   
   prodName VARCHAR(50),
   price INT NOT NULL,
   productQuantity INT NOT NULL,
   intro VARCHAR(500) NOT NULL,
   productPic LONGBLOB,
   PRIMARY KEY ( productID )
);

INSERT INTO product (productID, prodName, price, productQuantity, intro, productPic)
VALUES 
(1, '高蛋白能量棒', 100, 1000, '由專業營養師團隊研發，專為健身人士設計，滿足高蛋白低熱量的需求。', null),
(2, '好好吃能量棒', 100, 1000, '兼具美味與營養，運動後還是可以擁有吃甜點的幸福感！。', null),
(3, '高蛋白豆腐棒', 150, 1000, '素食者的增肌好夥伴，由日本高湯滷製而成。是飢餓解饞的好選擇！', null),
(4, '營養機能食品', 300, 500, '德國進口，緩解飲食不均衡的問題。', null),
(5, '機能性表示食品', 400, 500,'日本進口，加強吸收每日所需的營養素。', null),
(6, '特定保健食品', 500, 500,'為健身人士製造，針對運動傷害所設計的保健食品。', null),
(7, '好健康雞腿棒', 100, 200, '不但要吃得健康，也要吃的開心！好健康雞腿棒讓你吃的滿足又健康。', null),
(8, '偷偷吃點心球', 100, 1000, '真的嘴饞到撐不下去時可以吃，它的熱量不會太高...', null),
(9, '瘦身茉莉花茶', 50, 500, '今天要吃大餐嗎？記得買一瓶解油去膩！', null),
(10, '瘦身蘋果醋', 100, 500, '在日本OL間盛行的喝醋減肥法，是小資女們的瘦身首選。', null),
(11, '冰涼涼運動飲料', 50, 200, '運動後滿頭大汗，冰涼涼運動飲料解暑又能補充礦物質', null),
(12, '好營養高鈣鮮奶', 200, 200, '補充蛋白質與鈣質，運用最新科技提高鈣質含量。', null);

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

INSERT INTO orderID (orderID, memberID, orderDate, status, totalPrice)
VALUES 
(1, 1, '2024-08-17 10:48:45', '處理中', 300),
(2, 2, '2024-08-03 16:19:10', '已完成', 300),
(3, 7, '2024-08-26 02:44:00', '已取消', 250),
(4, 8, '2024-08-18 03:24:23', '已出貨', 100),
(5, 3, '2024-08-28 07:24:25', '已完成', 150),
(6, 5, '2024-08-15 18:16:58', '已完成', 250),
(7, 8, '2024-08-28 17:51:48', '處理中', 300),
(8, 2, '2024-08-10 12:59:44', '已出貨', 200),
(9, 9, '2024-08-14 06:41:38', '已出貨', 100),
(10, 4, '2024-08-14 17:56:24', '已完成', 250),
(11, 3, '2024-08-20 21:13:53', '已取消', 150),
(12, 9, '2024-08-03 03:23:08', '處理中', 150),
(13, 3, '2024-08-21 07:16:50', '已完成', 200),
(14, 8, '2024-08-18 15:22:26', '處理中', 200),
(15, 9, '2024-08-10 07:55:05', '處理中', 300),
(16, 3, '2024-08-22 05:58:18', '處理中', 150),
(17, 6, '2024-08-14 15:28:18', '已取消', 250),
(18, 9, '2024-08-12 18:24:40', '已出貨', 200),
(19, 3, '2024-08-11 10:13:46', '已取消', 250),
(20, 8, '2024-08-19 05:32:06', '處理中', 200);

create TABLE order_details(
   ordDtIID INT(11) AUTO_INCREMENT,   
   productID INT NOT NULL,
   quantity INT NOT NULL,
   orderID INT NOT NULL,
   PRIMARY KEY ( ordDtIID )
);
