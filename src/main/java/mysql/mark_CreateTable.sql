USE tia102g3;

DROP TABLE IF EXISTS menu;

create table menu (
	menuNumber INT AUTO_INCREMENT NOT NULL,
    menuName char(255),
    imageNumber INT,
    menuImage LONGBLOB,
    primary key (menuNumber)
);

INSERT INTO menu (menuNumber, menuName, imageNumber)
VALUES
    (1, "日式壽喜牛便當", 1),
    (2, "水煮嫩雞胸", 2),
    (3, "泡菜豬里肌", 3),
    (4, "香滷雞腿便當", 4),
    (5, "清滷牛腱", 5),
    (6, "麻香口水雞", 6),
    (7, "菜飯餐盒", 7),
    (8, "照燒雞腿排", 8),
    (9, "蒜泥豬里肌", 9),
    (10, "韓式菇菇雞", 10),
	(11, "鷹嘴豆咖哩", 11),
    (12, "鹽烤鯖魚", 12);

    -- ====================================================
DROP TABLE IF EXISTS memberMenuList;

create table memberMenuList(
	menuListSN int primary key,
    healthSN int COMMENT '外鍵-健康參數',
    menuNumber int,
    menuDate date,
    memberID int
    -- foreign key (healthSN) references healthStatus (healthSN),
    -- foreign key (menuNumber) references menu (menuNumber)
    -- foreign key (memberID) references member (memberID)
);

insert into memberMenuList(menuListSN, healthSN, menuNumber, menuDate, memberID) values (1, 1, 1, "2024-07-04", 1);

-- ====================================================
DROP TABLE IF EXISTS food;

create table food (
	foodNumber INT auto_increment NOT NULL,
    foodTypeNumber INT,
    foodName VARCHAR(50),
    foodCalories INT,
    CONSTRAINT food_PRIMARY_KEY PRIMARY KEY (foodNumber)
);

-- INSERT INTO food (foodNumber, foodTypeNumber, foodName, foodCalories) values (1, 1, "飯", 100);
-- INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (2, "", );
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (1, "地瓜", 86);
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (1, "白米飯", 109);
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (1, "白麵包", 300);
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (1, "燕麥", 389);
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (1, "玉米", 346);

INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (2, "高麗菜", 25);
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (2, "黃豆芽", 34);
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (2, "豌豆苗", 31);
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (2, "地瓜葉", 28);
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (2, "菠菜", 24);
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (2, "龍鬚菜", 24);
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (2, "花椰菜", 23);
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (2, "空心菜", 22);
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (2, "大陸妹(福山萵苣)", 13);
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (2, "小白菜", 10);
INSERT INTO food (foodTypeNumber, foodName, foodCalories) values (2, "青江菜", 10);
INSERT INTO food (foodNumber ,foodTypeNumber, foodName, foodCalories) values (null ,99, "菜", 1000);

-- UPDATE food SET foodTypeNumber = 2, foodName = "馬鈴薯", foodCalories = 77 WHERE foodNumber = 1;

-- ====================================================

DROP TABLE IF EXISTS foodList;

create table foodList(
	foodListSN INT auto_increment,
    menuNumber INT,
    foodNumber INT,
    foodWeight INT,
    Primary key (foodListSN)
    -- Foreign key (menuNumber) references menu (menuNumber),
--     Foreign key (foodNumber) references food (foodNumber)
);

-- insert into foodList (menuNumber, foodNumber, foodWeight) values (1, 1, 95);
-- insert into foodList (menuNumber, foodNumber, foodWeight) values (2, 2, 55);
INSERT INTO foodList (menuNumber, foodNumber, foodWeight) VALUES
(5, 10, 50),
(5, 11, 60),
(5, 12, 70),
(5, 13, 80),
(5, 14, 90),

(6, 15, 100),
(6, 16, 110),
(6, 17, 120),
(6, 18, 20),
(6, 19, 30),

(7, 20, 40),
(7, 21, 50),
(7, 22, 60),
(7, 23, 70),
(7, 24, 80),

(8, 25, 90),
(8, 26, 100),
(8, 27, 110),
(8, 28, 120),
(8, 29, 30),

(9, 30, 40),
(9, 10, 50),
(9, 11, 60),
(9, 12, 70),
(9, 13, 80),

(10, 11, 35),
(10, 17, 65),
(10, 15, 120),
(10, 12, 45),
(10, 14, 110),

(11, 18, 95),
(11, 20, 20),
(11, 19, 80),
(11, 13, 60),
(11, 16, 30),

(12, 21, 70),
(12, 23, 100),
(12, 22, 40),
(12, 30, 55),
(12, 28, 115),

(13, 29, 50),
(13, 24, 105),
(13, 26, 75),
(13, 25, 25),
(13, 27, 90),

(14, 10, 85),
(14, 13, 15),
(14, 11, 105),
(14, 17, 65),
(14, 30, 40);

-- ====================================================
DROP TABLE IF EXISTS healthStatus;

create table healthStatus(
	healthSN int auto_increment primary key,
    memberID int,
    height int,
    weight int,
    bmi float,
    BMR int,
    TDEE int,
    intensity int,
    create_dt datetime default current_timestamp
);

insert into healthStatus(memberID, height, weight,bmi, BMR, TDEE, intensity) values (1, 170, 70, 24.9, 1200, 1300, 1);
insert into healthStatus(memberID, height, weight,bmi, BMR, TDEE, intensity) values (2, 165, 55, 23.9, 1000, 1500, 0);

-- ====================================================
DROP TABLE IF EXISTS likeFood;

create table likeFood(
    likeFoodSN int auto_increment,
	memberID int,
    foodNumber int,
    foodPreference boolean,
    primary key (likeFoodSN)
    -- foreign key memberID references member (memberID),
    -- foreign key (foodNumber) references food (foodNumber)
);

insert into likeFood (memberID, foodNumber, foodPreference) values (1, 1, true);
insert into likeFood (memberID, foodNumber, foodPreference) values (1, 2, false);
insert into likeFood (memberID, foodNumber, foodPreference) values (2, 11, true);
insert into likeFood (memberID, foodNumber, foodPreference) values (2, 12, false);

-- ====================================================