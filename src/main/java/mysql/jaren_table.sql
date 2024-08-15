CREATE DATABASE IF NOT EXISTS tia102g3;

USE tia102g3;


CREATE TABLE coach_course_pic
(
    id int AUTO_INCREMENT PRIMARY KEY,
    coachCourseID    int NOT NULL,
    pic              LONGBLOB
) comment ='教練課程圖片表';

CREATE TABLE coach_course
(
    id              INT AUTO_INCREMENT PRIMARY KEY COMMENT '教練課程ID。主鍵，自動遞增',
    cMemberID       INT  NOT NULL COMMENT '教練會員ID。外鍵，關連到coach_member表的cMemberID',
    courseName      VARCHAR(50) COMMENT '課程名稱',
    courseLevel     INT COMMENT '課程等級',
    courseStartDate DATE COMMENT '開課時間',
    courseEndDate   DATE COMMENT '結訓時間',
    noOfClasses     INT COMMENT '課程堂数',
    maxCap          INT COMMENT '上限人数',
    status          INT COMMENT '是否通過審核。1=通過,0=未審核,-1=未通過',
    sportEventName  VARCHAR(100) COMMENT '運動項目名稱',
    sportTypes      VARCHAR(100) COMMENT '運動類型',
    sportEquipment  VARCHAR(100) COMMENT '運動器材',
    coursePrice     INT COMMENT '課程價格',
    classStartTime  time NOT NULL comment "上課時間",
    classEndTime    time NOT NULL comment "下課時間",
    illustrate      varchar(10000) comment "課程說明",
    courseIntro           varchar(100) comment "課程簡介"
) COMMENT ='教練課程表';

CREATE TABLE customized_course
(
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '客製化課程ID。主鍵，自動遞增',
    systemCourseID    INT NOT NULL COMMENT '系統課程ID。外鍵，關連到system_course表的systemCourseID',
    memberID          INT NOT NULL COMMENT '會員ID。外鍵，關連到member表的memberID'
) COMMENT ='客製化課程表';

CREATE TABLE system_course
(
    systemCourseID   INT AUTO_INCREMENT PRIMARY KEY COMMENT '系統課程ID。主鍵，自動遞增',
    courseName       VARCHAR(50) NOT NULL COMMENT '課程名稱',
    sportEventName   varchar(100),
    sportTypes       varchar(100),
    sportEquipment   VARCHAR(100),
    courseLevel      INT COMMENT '課程等級',
    burnCalories     INT COMMENT '消耗熱量',
    rps              INT COMMENT '每組做幾下',
    eachExerciseTime varchar(100) COMMENT '每組運動時間',
    sportTime        varchar(100) COMMENT '運動總時間',
    swp              INT COMMENT '每次做幾組',
    illustrate       VARCHAR(10000) COMMENT '說明',
    video            varchar(500) COMMENT '影片連結'
) COMMENT ='系統課程表';

CREATE TABLE system_course_pic
(
    systemCoursePicID INT AUTO_INCREMENT PRIMARY KEY COMMENT '系統課程照片ID。主鍵，自動遞增',
    systemCourseID    INT NOT NULL COMMENT '系統課程ID。外鍵，關聯到system_course表的systemCourseID',
    pic               LONGBLOB COMMENT '圖片。存儲與系統課程相關的圖片'
) COMMENT ='系統課程照片表';



CREATE TABLE coach_specialty
(
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '教練運動強項ID。主鍵，自動遞增',
    cMemberID        INT NOT NULL COMMENT '教練會員ID。外鍵，關聯到coach_member表的cMemberID',
    sportEventID     INT NOT NULL COMMENT '運動項目ID。外鍵，關聯到sport_event表的sportEventID'
) COMMENT ='教練專長表。存儲教練的專長運動項目';

CREATE TABLE coach_certificate
(
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '教練證照ID。主鍵，自動遞增',
    cMemberID          INT NOT NULL COMMENT '教練會員ID。外鍵，關聯到coach_member表的cMemberID',
    certificateName    VARCHAR(200) COMMENT '證照名稱',
    certificatePic     LONGBLOB COMMENT '證照照片'
) COMMENT ='教練證照表。存儲教練的證照信息，包括證照名稱和照片';



CREATE TABLE sport_event
(
    sportEventID   INT AUTO_INCREMENT PRIMARY KEY COMMENT '運動項目ID。主鍵，自動遞增',
    sportEventName VARCHAR(100) COMMENT '運動項目名稱',
    sportTypes     VARCHAR(100) COMMENT '運動類型',
    sportEquipment VARCHAR(100) COMMENT '運動器材'
) COMMENT ='運動項目表。存儲各種運動項目的信息，包括名稱、類型和所需器材';


INSERT INTO sport_event (sportEventName, sportTypes, sportEquipment)
VALUES ("伏地挺身", "重量運動", "徒手"),
       ("平板支撐", "重量運動", "徒手"),
       ("仰臥起坐", "重量運動", "徒手"),
       ("橋式", "重量運動", "徒手"),
       ("深蹲", "重量運動", "槓鈴"),
       ("臥推", "重量運動", "啞鈴"),
       ("臥推", "重量運動", "槓鈴"),
       ("硬拉", "重量運動", "槓鈴"),
       ("啞鈴弓步", "重量運動", "啞鈴"),
       ("肩推", "重量運動", "槓鈴"),
       ("肩推", "重量運動", "肩推機"),
       ("引體向上", "重量運動", "單槓"),
       ("槓鈴划船", "重量運動", "槓鈴"),
       ("腿舉", "重量運動", "大腿推蹬訓練機"),
       ("啞鈴側平舉", "重量運動", "啞鈴"),
       ("啞鈴划船", "重量運動", "啞鈴"),
       ("跑步", "心肺運動", "跑步機"),
       ("飛輪", "心肺運動", "飛輪"),
       ("橢圓機", "心肺運動", "橢圓機"),
       ("划船機", "心肺運動", "划船機"),
       ("跳繩", "心肺運動", "跳繩"),
       ("有氧舞蹈", "心肺運動", "徒手"),
       ("有氧舞蹈", "心肺運動", "徒手"),
       ("跑步", "心肺運動", "徒手"),
       ("徒步", "心肺運動", "徒手"),
       ("高抬腿", "心肺運動", "徒手"),
       ("衝刺", "心肺運動", "徒手"),
       ("原地踏步", "心肺運動", "徒手"),
       ("跳躍深蹲", "心肺運動", "徒手"),
       ("波比跳", "心肺運動", "徒手"),
       ("跳躍開合跳", "心肺運動", "徒手");









# ALTER TABLE coach_course
#     ADD CONSTRAINT fk_coach_course_cMemberID
#         FOREIGN KEY (cMemberID) REFERENCES coach_member (cMemberID);
# -- COMMENT '外鍵，關聯到coach_member表的cMemberID，指定課程的教練會員';
#
# ALTER TABLE coach_course
#     ADD CONSTRAINT fk_coach_course_sportEventID
#         FOREIGN KEY (sportEventID) REFERENCES sport_event (sportEventID);
# -- COMMENT '外鍵，關聯到sport_event表的sportEventID，指定課程所屬的運動項目';
#
# ALTER TABLE customized_course
#     ADD CONSTRAINT fk_customized_course_systemCourseID
#         FOREIGN KEY (systemCourseID) REFERENCES system_course (systemCourseID);
# -- COMMENT '外鍵，關聯到system_course表的systemCourseID，指定課程所屬的系統課程';
#
# ALTER TABLE customized_course
#     ADD CONSTRAINT fk_customized_course_memberID
#         FOREIGN KEY (memberID) REFERENCES member (memberID);
# -- COMMENT '外鍵，關聯到member表的memberID，指定課程的會員';
#
# ALTER TABLE system_course
#     ADD CONSTRAINT fk_system_course_sportEventID
#         FOREIGN KEY (sportEventID) REFERENCES sport_event (sportEventID);
# -- COMMENT '外鍵，關聯到sport_event表的sportEventID，指定課程所屬的運動項目';
#
# ALTER TABLE system_course_pic
#     ADD CONSTRAINT fk_system_course_pic_systemCourseID
#         FOREIGN KEY (systemCourseID) REFERENCES system_course (systemCourseID);
# -- COMMENT '外鍵，關聯到system_course表的systemCourseID，指定照片所屬的系統課程';
#
# ALTER TABLE coach_course_pic
#     ADD CONSTRAINT fk_coach_course_pic_coachCourseID
#         FOREIGN KEY (coachCourseID) REFERENCES Coach_Course (id);
