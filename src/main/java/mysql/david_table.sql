CREATE DATABASE IF NOT EXISTS tia102g3;

USE tia102g3;

DROP TABLE IF EXISTS admin_id;
DROP TABLE IF EXISTS coach_course;
DROP TABLE IF EXISTS coach_course_pic;
drop TABLE IF EXISTS coach_member;
DROP TABLE IF EXISTS coach_specialty;
DROP TABLE IF EXISTS course_order;
DROP TABLE IF EXISTS customized_course;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS health_status;
DROP TABLE IF EXISTS like_food;
DROP TABLE IF EXISTS MEMBER;
DROP TABLE IF EXISTS member_menu_relation;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS orderid;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_pic;
DROP TABLE IF EXISTS rest_lfo;
DROP TABLE IF EXISTS rest_map;
DROP TABLE IF EXISTS shopping_cart;
DROP TABLE IF EXISTS sport_course;
DROP TABLE IF EXISTS system_course;
DROP TABLE IF EXISTS system_course_pic;

CREATE TABLE member (
    memberID INT AUTO_INCREMENT PRIMARY KEY COMMENT '會員ID。主鍵，自動遞增',
    personalPhotos LONGBLOB COMMENT '個人照片',
    name VARCHAR(30) COMMENT '姓名',
    account VARCHAR(50) COMMENT '帳號',
    password VARCHAR(30) COMMENT '密碼',
    email VARCHAR(30) COMMENT '電子郵件',
    gender VARCHAR(10) COMMENT '性別',
    phone VARCHAR(20) COMMENT '手機',
    address VARCHAR(50) COMMENT '地址',
    bD DATE COMMENT '生日',
    regDate DATE COMMENT '註冊日期',
    receiver VARCHAR(50) COMMENT '收件人',
    receiverAddress VARCHAR(50) COMMENT '收件地址',
    receiverPhone VARCHAR(20) COMMENT '收件人手機',
    cardName VARCHAR(20) COMMENT '卡片名稱',
    cardNumber VARCHAR (20) COMMENT '卡片號碼',
    cardValidTime VARCHAR(10) COMMENT '卡片有效期限',
    cardLast3No VARCHAR(10) COMMENT '卡片後三碼',
    cardPhone VARCHAR(20) COMMENT '卡片綁定手機'
) COMMENT='會員';

INSERT INTO member (personalPhotos, name, account, password, email, gender, phone, address, bD, regDate, receiver, receiverAddress, receiverPhone, cardName, cardNumber, cardValidTime, cardLast3No, cardPhone)
VALUES 
(NULL, '王小明', 'xiaoming', 'password123', 'xiaoming@example.com', '男', '0912345678', '台北市中山區', '1990-01-01', '2024-08-10', '李小華', '台北市信義區', '0987654321', 'VISA', '1234567890123456', '12/25', '123', '0911223344'),
(NULL, '李小華', 'xiaohua', 'password456', 'xiaohua@example.com', '女', '0922334455', '台中市西屯區', '1992-05-20', '2024-08-10', '王小明', '台中市南屯區', '0933445566', 'MasterCard', '6543210987654321', '10/23', '321', '0922334455');

CREATE TABLE coach_member (
    cMemberID INT AUTO_INCREMENT PRIMARY KEY COMMENT '教練會員ID。主鍵，自動遞增',
    personalPhotos LONGBLOB COMMENT '個人照片',
    status INT COMMENT '是否通過審核',
    name VARCHAR(30) COMMENT '姓名',
    account VARCHAR(50) COMMENT '帳號',
    password VARCHAR(30) COMMENT '密碼',
    email VARCHAR(30) COMMENT '電子郵件',
    gender VARCHAR(10) COMMENT '性別',
    phone VARCHAR(20) COMMENT '手機',
    address VARCHAR(50) COMMENT '地址',
    bD DATE COMMENT '生日',
    regDate DATE COMMENT '註冊日期',
    receiver VARCHAR(50) COMMENT '收件人',
    receiverAddress VARCHAR(50) COMMENT '收件地址',
    receiverPhone VARCHAR(20) COMMENT '收件人手機',
    cardName VARCHAR(20) COMMENT '卡片名稱',
    cardNumber VARCHAR (20) COMMENT '卡片號碼',
    cardValidTime VARCHAR(10) COMMENT '卡片有效期限',
    cardLast3No VARCHAR(10) COMMENT '卡片後三碼',
    cardPhone VARCHAR(20) COMMENT '卡片綁定手機'
) COMMENT='教練會員表。存儲教練的個人資料和審核狀態';

INSERT INTO coach_member (personalPhotos, status, name, account, password, email, gender, phone, address, bD, regDate, receiver, receiverAddress, receiverPhone, cardName, cardNumber, cardValidTime, cardLast3No, cardPhone)
VALUES 
(NULL, '1', '陳教練', 'coachchen', 'coachpassword1', 'coachchen@example.com', '男', '0988776655', '台南市東區', '1985-03-15', '2024-08-10', '張三', '台南市南區', '0977665544', 'VISA', '9876543210123456', '09/26', '456', '0988776655'),
(NULL, '2', '林教練', 'coachlin', 'coachpassword2', 'coachlin@example.com', '女', '0911223344', '高雄市左營區', '1988-07-25', '2024-08-10', '李四', '高雄市鼓山區', '0966554433', 'MasterCard', '3216549870321654', '11/24', '654', '0911223344');

CREATE TABLE course_order (
    courseOrderID INT AUTO_INCREMENT PRIMARY KEY COMMENT '課程訂單ID。主鍵，自動遞增',
    memberID INT NOT NULL COMMENT '會員ID。外鍵，關連到member表的memberID',
    coachCourseID INT NOT NULL COMMENT '教練課程ID。外鍵，關連到coach_course表的coachCourseID',
    orderDate DATETIME COMMENT '訂單日期',
    price INT COMMENT '課程金額',
    status INT COMMENT '訂單狀態'
) COMMENT='課程訂單';

INSERT INTO course_order (memberID, coachCourseID, orderDate, price, status)
VALUES 
(1, 1, '2024-08-10 10:30:00', 3000, 1),
(2, 2, '2024-08-10 15:45:00', 2500, 0);

CREATE TABLE admin_id (
    admin_ID INT AUTO_INCREMENT PRIMARY KEY COMMENT '管理員ID。主鍵，自動遞增',
    adminName VARCHAR(50) COMMENT '管理員姓名',
    adminUsername VARCHAR(50) COMMENT '管理員帳號',
    adminPassword VARCHAR(50) COMMENT '管理員密碼',
    adminEmail VARCHAR(100) COMMENT '管理員信箱'
) COMMENT='後台管理人員表。存儲後台管理人員的資訊';

ALTER TABLE member
ADD CONSTRAINT fk_cMemberID
FOREIGN KEY (cMemberID) REFERENCES coach_member(cMemberID);
-- COMMENT '外鍵，關聯到coach_member，表示這名會員也是位教練會員';

ALTER TABLE course_order
ADD CONSTRAINT fk_course_order_memberID
FOREIGN KEY (memberID) REFERENCES member(memberID)
-- COMMENT '外鍵，關聯到member表的memberID，指定訂單的會員';

ALTER TABLE course_order
ADD CONSTRAINT fk_course_order_coachCourseID
FOREIGN KEY (coachCourseID) REFERENCES coach_course(coachCourseID)
-- COMMENT '外鍵，關聯到coach_course表的coachCourseID，指定訂單的教練課程';

ALTER TABLE coach_course
ADD CONSTRAINT fk_coach_course_cMemberID
FOREIGN KEY (cMemberID) REFERENCES coach_member(cMemberID)
-- COMMENT '外鍵，關聯到coach_member表的cMemberID，指定課程的教練會員';
