
DROP TABLE IF EXISTS menu;

create table menu (
	menuNumber INT AUTO_INCREMENT NOT NULL,
    imageNumber INT,
    menuImage LONGBLOB,
    primary key (menuNumber)
);

-- INSERT INTO menu (menuNumber) value (1);
-- INSERT INTO menu (menuNumber) value (2);
-- INSERT INTO menu (menuNumber) value (3);

INSERT INTO menu (menuNumber, imageNumber)
VALUES
    (1, 101),
    (2, 102),
    (3, 103),
    (4, 104),
    (5, 105),
    (6, 106),
    (7, 107),
    (8, 108),
    (9, 109),
    (10, 110);

    -- ====================================================
DROP TABLE IF EXISTS memberMenuList;

create table memberMenuList(
	menuListSN int primary key,
    healthSN int COMMENT '外鍵-健康參數',
    menuNumber int,
    menuDate date,
    memberID int,
    foreign key (healthSN) references healthStatus (healthSN),
    foreign key (menuNumber) references menu (menuNumber)
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
    Primary key (foodListSN),
    Foreign key (menuNumber) references menu (menuNumber),
    Foreign key (foodNumber) references food (foodNumber)
);

insert into foodList (menuNumber, foodNumber, foodWeight) values (1, 1, 95);
insert into foodList (menuNumber, foodNumber, foodWeight) values (2, 2, 55);

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
	memberID int,
    foodNumber int,
    foodPreference boolean,
    primary key (memberID, foodNumber)
    -- foreign key memberID references member (memberID),
    -- foreign key (foodNumber) references food (foodNumber)
);

insert into likeFood (memberID, foodNumber, foodPreference) values (1, 1, true);
insert into likeFood (memberID, foodNumber, foodPreference) values (1, 2, false);

-- ====================================================