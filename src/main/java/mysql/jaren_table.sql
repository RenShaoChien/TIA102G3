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

insert into system_course(courseName, sportEventName, sportTypes, sportEquipment, courseLevel, burnCalories, rps, eachExerciseTime, sportTime, swp, illustrate, video)
values ('跪式伏地挺身', '伏地挺身', '重量運動', '徒手', 1, 80, 15, null, null, 4, '主要鍛煉胸肌、三頭肌和肩部肌肉
    幫助增強上肢力量，改善核心穩定性，提高肌肉耐力', 'https://www.youtube.com/embed/vAALjNpx2uQ?si=HBxrjqQMqwqC4GPm'),
    ('俯臥撐', '伏地挺身', '重量運動', '徒手', 2, 100, 15, null, null, 4, '主要鍛煉胸肌、三頭肌和肩部肌肉
    幫助增強上肢力量，改善核心穩定性，提高肌肉耐力', 'https://www.youtube.com/embed/lfBh7TMLwg0?si=_abiqQaT91M4Ac0R'),
    ('基本深蹲', '深蹲', '重量運動', '徒手', 1, 90, 15, null, null, 4, '主要鍛煉大腿、臀部和核心肌群
幫助增強下半身力量，提高腿部肌肉質量，改善核心穩定性', 'https://www.youtube.com/embed/aIG28K4yvqg?si=uMWxuI737eKB3Xau'),
    ('彈力繩深蹲', '深蹲', '重量運動', '彈力繩', 3, 200, 30, null, null, 4, '主要鍛煉大腿、臀部和核心肌群
幫助增強下半身力量，提高腿部肌肉質量，改善核心穩定性', 'https://www.youtube.com/embed/EYQfeJe4ZJc?si=PWi496Grvcsw_NVx'),
    ('地獄深蹲', '深蹲', '重量運動', '槓鈴', 4, 200, 12, null, null, 4, '主要鍛煉大腿、臀部和核心肌群
幫助增強下半身力量，提高腿部肌肉質量，改善核心穩定性', 'https://www.youtube.com/embed/JjDKzJHqoAs?si=p2XbQ5eDfw4g_x0p'),
    ('皮拉提斯', '皮拉提斯', '重量運動', '徒手', 1, 150, null, '12分鐘', '1小時', null, '幫助增強核心力
量、改善姿勢、增加柔軟度，並且促進全身肌肉的協調與平衡。', 'https://www.youtube.com/embed/3jn09b63yMg?si=SbUZg3vHeLQ5hnhw'),
    ('彈力繩皮拉提斯', '皮拉提斯', '重量運動', '彈力繩', 3, 200, null, '12分鐘', '1小時', null, '主要鍛鍊部位：核心肌群、背部、臀部、腿部及手臂。
運動效益：結合皮拉提斯和彈力繩的訓練可以增強核心力量、改善姿勢和柔軟度，同時提高全身的肌肉耐力與張力。彈力繩的阻力可以增加運動的強度，有助於塑造更緊實的肌肉線條。', 'https://www.youtube.com/embed/bHL8kHE8_zE?si=7qmnb-Z2JyMKOGdq'),
    ('弓步', '弓步', '重量運動', '徒手', 1, 80, 15, null, null, 4, '主要鍛煉大腿、臀部和核心肌群
幫助增強下半身力量，提高腿部穩定性和平衡能力', 'https://www.youtube.com/embed/M_pEaqqFpLI?si=ouRXKcohKw2-HFR6'),
    ('初級跑步', '跑步', '心肺運動', '徒手', 1, 200, null, '30分鐘', '1小時', null, '主要鍛煉心臟和肺部功能。', 'https://www.youtube.com/embed/q3v5EmXV4B8?si=FAHLMpJhgvz3pkJI'),
    ('中級跑步', '跑步', '心肺運動', '跑步機', 2, 250, null, '30分鐘', '1小時', null, '肌肉訓練：主要鍛煉下半身肌肉，包括大腿、小腿和臀部肌肉
幫助：提升心肺功能，增強下半身肌肉，有助於減重和燃燒脂肪','https://www.youtube.com/embed/PVRxR0-nlR0?si=AK1TwmTh-P42b16X');


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
       ('深蹲', '重量運動', '彈力繩'),
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
       ("拳擊", "心肺運動", "拳套"),
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
