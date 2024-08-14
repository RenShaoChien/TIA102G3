

CREATE TABLE orderid (
	orderId int PRIMARY KEY AUTO_INCREMENT COMMENT '訂單ID',
    memberID int,  
    orderDate datetime comment '訂單日期',
    orderStatus varchar(50) comment '訂單狀態',
    totalPrice int comment '總金額'
);


CREATE TABLE orderDetails (
   ordDtlID int PRIMARY KEY AUTO_INCREMENT COMMENT '訂單明細',
  productID int comment'商品ID',
quantity int comment '產品數量',
   orderID int comment '訂單'   
);


-- 移除舊有的資料
DROP TABLE IF EXISTS rest_lfo;
DROP TABLE IF EXISTS rest_ifo;

-- 重新create table
CREATE TABLE rest_ifo(
    restLoc int PRIMARY KEY AUTO_INCREMENT,
    restName varchar(50),
    restAddress varchar(200),
    restTime varchar(100),
    restTel varchar(20),
    mapID int 
);

CREATE TABLE admin_id(
    admin_ID int PRIMARY KEY AUTO_INCREMENT,
    adminName varchar(30),
	adminUsername varchar(30),
    adminPassword varchar(30),
    adminEmail varchar(50)
);
 
-- 移除舊有的資料
DROP TABLE IF EXISTS rest_lfo;
DROP TABLE IF EXISTS rest_ifo;

-- 重新create table
CREATE TABLE rest_ifo(
    restLoc int PRIMARY KEY AUTO_INCREMENT,
    restName varchar(50),
    restAddress varchar(200),
    restTime varchar(100),
    restTel varchar(20),
    mapID int 
);

-- 移除舊有的資料
DROP TABLE IF EXISTS rest_map;
DROP TABLE IF EXISTS rest_map;

CREATE TABLE rest_map(
    mapID int PRIMARY KEY AUTO_INCREMENT,
    mapLat double,
	mapLng double
);

INSERT INTO rest_ifo (restLoc,restName,restAddress,restTime,restTel,mapID) VALUES
('1', '蛋白盒子', '台北市中山區遼寧街149號', '周一至周五 10:30~20:30', 02-2715-0020,1),
('2', '好食肌', '台北市松山區南京東路三段256巷20弄2號', '周一至周六 11:00~21:00', '02-1000-7630',2),
('3', '呷飽奶奶健康餐', '台北市中山區遼寧街112號', '周一至周五 10:00~20:00', '02-2717-0008', 3);

INSERT INTO rest_map (mapID,mapLat,mapLng) VALUES
('1',25.052564, 121.542211),
('2',25.050732, 121.544996),
('3',25.052855, 121.541815);
