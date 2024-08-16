
-- Drop the menu table if it exists
DROP TABLE IF EXISTS menu;

-- Create the menu table
CREATE TABLE menu (
    menuNumber INT AUTO_INCREMENT NOT NULL,  -- Unique identifier for each menu item
    menuName CHAR(255),                      -- Name of the menu item
    imageNumber INT,                         -- Identifier for the image associated with the menu
    menuImage LONGBLOB,                      -- Binary data for the menu image
    PRIMARY KEY (menuNumber)                 -- Primary key on menuNumber
);

-- Insert data into menu
INSERT INTO menu (menuNumber, menuName, imageNumber) VALUES
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

-- Drop the memberMenuList table if it exists
DROP TABLE IF EXISTS memberMenuList;

-- Create the memberMenuList table
CREATE TABLE memberMenuList (
    menuListSN INT PRIMARY KEY,              -- Serial number for the menu list entry
    healthSN INT COMMENT 'Foreign Key - Health Status',  -- Foreign key referencing healthStatus
    menuNumber INT,                          -- Foreign key referencing menu
    menuDate DATE,                           -- Date associated with the menu
    memberID INT                            -- ID of the member
   -- FOREIGN KEY (healthSN) REFERENCES healthStatus (healthSN),
   -- FOREIGN KEY (menuNumber) REFERENCES menu (menuNumber)
   -- FOREIGN KEY (memberID) REFERENCES member (memberID)
);

-- Insert data into memberMenuList
INSERT INTO memberMenuList (menuListSN, healthSN, menuNumber, menuDate, memberID) VALUES 
    (1, 1, 1, "2024-07-04", 1),
    (2, 2, 2, "2024-07-05", 1),
    (3, 1, 3, "2024-07-06", 2),
    (4, 2, 4, "2024-07-07", 2),
    (5, 1, 5, "2024-07-08", 3),
    (6, 2, 6, "2024-07-09", 3),
    (7, 1, 7, "2024-07-10", 4),
    (8, 2, 8, "2024-07-11", 4),
    (9, 1, 9, "2024-07-12", 5),
    (10, 2, 10, "2024-07-13", 5);

-- ====================================================
-- Drop the table food if it exists
DROP TABLE IF EXISTS food;

-- Create the table food
CREATE TABLE food (
    foodNumber INT AUTO_INCREMENT NOT NULL,
    foodTypeNumber INT,
    foodName VARCHAR(50),
    foodCalories INT,
    CONSTRAINT food_PRIMARY_KEY PRIMARY KEY (foodNumber)
);

-- Insert data
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

-- INSERT INTO food (foodNumber ,foodTypeNumber, foodName, foodCalories) values (null ,99, "菜", 1000);
-- UPDATE food SET foodTypeNumber = 2, foodName = "馬鈴薯", foodCalories = 77 WHERE foodNumber = 1;

-- ====================================================

-- Drop the table foodList if it exists
DROP TABLE IF EXISTS foodList;

-- Create the table foodList
CREATE TABLE foodList (
    foodListSN INT AUTO_INCREMENT, -- Serial number for the food list
    menuNumber INT,               -- Reference to the menu table
    foodNumber INT,               -- Reference to the food table
    foodWeight INT,               -- Weight of the food
    PRIMARY KEY (foodListSN)     -- Primary key constraint
    -- FOREIGN KEY (menuNumber) REFERENCES menu (menuNumber), -- Foreign key constraint referencing menu
	-- FOREIGN KEY (foodNumber) REFERENCES food (foodNumber)  -- Foreign key constraint referencing food
);

-- Insert test data into foodList
INSERT INTO foodList (menuNumber, foodNumber, foodWeight) VALUES (1, 1, 95);
INSERT INTO foodList (menuNumber, foodNumber, foodWeight) VALUES (2, 2, 55);
INSERT INTO foodList (menuNumber, foodNumber, foodWeight) VALUES (1, 3, 80);
INSERT INTO foodList (menuNumber, foodNumber, foodWeight) VALUES (3, 4, 120);
INSERT INTO foodList (menuNumber, foodNumber, foodWeight) VALUES (1, 5, 150);
INSERT INTO foodList (menuNumber, foodNumber, foodWeight) VALUES (2, 6, 200);
INSERT INTO foodList (menuNumber, foodNumber, foodWeight) VALUES (3, 7, 130);
INSERT INTO foodList (menuNumber, foodNumber, foodWeight) VALUES (2, 8, 100);
INSERT INTO foodList (menuNumber, foodNumber, foodWeight) VALUES (3, 9, 60);
INSERT INTO foodList (menuNumber, foodNumber, foodWeight) VALUES (1, 10, 90);
INSERT INTO foodList (menuNumber, foodNumber, foodWeight) VALUES (2, 11, 75);

-- ====================================================
-- Drop the table healthStatus if it exists
DROP TABLE IF EXISTS healthStatus;

-- Create the table healthStatus
CREATE TABLE healthStatus (
    healthSN INT AUTO_INCREMENT PRIMARY KEY, -- Serial number for health status
    memberID INT,                          -- ID of the member
    height INT,                            -- Height in centimeters
    weight INT,                            -- Weight in kilograms
    bmi FLOAT,                             -- Body Mass Index
    BMR INT,                               -- Basal Metabolic Rate
    TDEE INT,                              -- Total Daily Energy Expenditure
    intensity INT,                         -- Intensity level (1-5 low to hight)
    create_dt DATETIME DEFAULT CURRENT_TIMESTAMP -- Record creation date and time
);

-- Insert test data into healthStatus
INSERT INTO healthStatus (memberID, height, weight, bmi, BMR, TDEE, intensity) VALUES (1, 170, 70, 24.9, 1200, 1300, 1);
INSERT INTO healthStatus (memberID, height, weight, bmi, BMR, TDEE, intensity) VALUES (2, 165, 55, 23.9, 1000, 1500, 0);
INSERT INTO healthStatus (memberID, height, weight, bmi, BMR, TDEE, intensity) VALUES (3, 180, 80, 24.7, 1400, 1700, 2);
INSERT INTO healthStatus (memberID, height, weight, bmi, BMR, TDEE, intensity) VALUES (4, 160, 50, 19.5, 900, 1200, 1);
INSERT INTO healthStatus (memberID, height, weight, bmi, BMR, TDEE, intensity) VALUES (5, 175, 75, 24.5, 1300, 1600, 2);
INSERT INTO healthStatus (memberID, height, weight, bmi, BMR, TDEE, intensity) VALUES (6, 150, 45, 20.0, 800, 1100, 0);
INSERT INTO healthStatus (memberID, height, weight, bmi, BMR, TDEE, intensity) VALUES (7, 190, 85, 23.5, 1500, 1800, 3);
INSERT INTO healthStatus (memberID, height, weight, bmi, BMR, TDEE, intensity) VALUES (8, 155, 48, 21.5, 850, 1150, 1);
INSERT INTO healthStatus (memberID, height, weight, bmi, BMR, TDEE, intensity) VALUES (9, 165, 60, 22.0, 1050, 1350, 2);
INSERT INTO healthStatus (memberID, height, weight, bmi, BMR, TDEE, intensity) VALUES (10, 185, 90, 26.3, 1600, 1900, 3);

-- ====================================================
-- Drop the table likeFood if it exists
DROP TABLE IF EXISTS likeFood;

-- Create the table likeFood
CREATE TABLE likeFood (
    likeFoodSN INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Unique ID for likeFood entry',
    memberID INT COMMENT 'ID of the member',             -- ID of the member
    foodNumber INT COMMENT 'ID of the food item',        -- ID of the food item
    foodPreference BOOLEAN COMMENT 'Preference (true for liked, false for disliked)' -- Preference for the food item
    -- FOREIGN KEY (memberID) REFERENCES member (memberID),
    -- FOREIGN KEY (foodNumber) REFERENCES food (foodNumber)
) COMMENT='Table to store member food preferences';

-- Insert test data into likeFood
INSERT INTO likeFood (memberID, foodNumber, foodPreference) VALUES (1, 1, true);
INSERT INTO likeFood (memberID, foodNumber, foodPreference) VALUES (1, 2, false);
INSERT INTO likeFood (memberID, foodNumber, foodPreference) VALUES (2, 1, true);
INSERT INTO likeFood (memberID, foodNumber, foodPreference) VALUES (2, 3, true);
INSERT INTO likeFood (memberID, foodNumber, foodPreference) VALUES (3, 4, false);
INSERT INTO likeFood (memberID, foodNumber, foodPreference) VALUES (3, 5, true);
INSERT INTO likeFood (memberID, foodNumber, foodPreference) VALUES (4, 2, true);
INSERT INTO likeFood (memberID, foodNumber, foodPreference) VALUES (4, 3, false);
INSERT INTO likeFood (memberID, foodNumber, foodPreference) VALUES (5, 1, true);
INSERT INTO likeFood (memberID, foodNumber, foodPreference) VALUES (5, 5, true);
INSERT INTO likeFood (memberID, foodNumber, foodPreference) VALUES (6, 4, false);


-- ====================================================