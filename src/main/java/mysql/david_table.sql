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
(NULL, '王小明', 'xiaoming', 'xm123', 'xiaoming@example.com', '男', '0912345678', '台北市中山區', '1990-01-01', '2024-08-10', '李小華', '台北市信義區', '0987654321', 'VISA', '1234567890123456', '12/25', '123', '0911223344'),
(NULL, '李小華', 'xiaohua', 'xh456', 'xiaohua@example.com', '女', '0922334455', '台中市西屯區', '1992-05-20', '2024-08-10', '王小明', '台中市南屯區', '0933445566', 'MasterCard', '6543210987654321', '10/23', '321', '0922334455'),
(NULL, '張大偉', 'zhang', 'zw789', 'zhang@example.com', '男', '0933221100', '高雄市三民區', '1988-08-15', '2024-08-10', '林美麗', '高雄市前鎮區', '0955112233', 'VISA', '9876543210987654', '01/27', '456', '0933221100'),
(NULL, '李美慧', 'lima', 'lm012', 'lima@example.com', '女', '0944332211', '台南市安平區', '1995-11-25', '2024-08-10', '陳志明', '台南市北區', '0966889900', 'MasterCard', '3216549870654321', '07/25', '789', '0944332211'),
(NULL, '陳建國', 'chen', 'cj345', 'chen@example.com', '男', '0922113344', '新竹市東區', '1983-12-05', '2024-08-10', '林芳瑜', '新竹市西區', '0988772233', 'VISA', '4567890123456789', '09/28', '321', '0922113344');

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
(NULL, '1', '任教練', 'ren', 'renpw', 'coachchen@example.com', '男', '0988776655', '台南市東區', '1985-03-15', '2024-08-10', '張三', '台南市南區', '0977665544', 'VISA', '9876543210123456', '09/26', '456', '0988776655'),
(NULL, '2', '劉教練', 'liu', 'liupw', 'coachlin@example.com', '男', '0911223344', '高雄市左營區', '1988-07-25', '2024-08-10', '李四', '高雄市鼓山區', '0966554433', 'MasterCard', '3216549870321654', '11/24', '654', '0911223344'),
(NULL, '3', '曾教練', 'zeng', 'zengpw', 'coachwang@example.com', '女', '0911887766', '台北市信義區', '1982-11-23', '2024-08-10', '王五', '台北市大安區', '0988991122', 'VISA', '1234567890123456', '12/25', '789', '0911887766'),
(NULL, '4', '侯教練', 'hou', 'houpw', 'coachli@example.com', '男', '0933221100', '新北市板橋區', '1990-01-18', '2024-08-10', '趙六', '新北市新店區', '0977123456', 'MasterCard', '6543210987654321', '08/23', '321', '0933221100'),
(NULL, '5', '李教練', 'li', 'lipw', 'coachhuang@example.com', '男', '0922334455', '桃園市中壢區', '1986-06-30', '2024-08-10', '孫七', '桃園市蘆竹區', '0988123456', 'VISA', '1122334455667788', '05/27', '112', '0922334455');


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
(3, 3, '2024-08-11 09:00:00', 2800, 1),
(4, 4, '2024-08-11 13:30:00', 3200, 0),
(5, 5, '2024-08-12 16:00:00', 2900, 1);

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
