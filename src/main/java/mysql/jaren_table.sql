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

INSERT INTO coach_course (cMemberID, courseName, courseLevel, courseStartDate, courseEndDate, noOfClasses, maxCap, status, sportEventName, sportTypes, sportEquipment, coursePrice, classStartTime, classEndTime, illustrate, courseIntro)
VALUES
    (1, '超派鐵拳', 4, '2024-08-15', '2024-09-14', 20, 1, 0, '拳擊', '心肺運動', '拳套', 20000, '20:00:00', '21:00:00', '超派鐵拳，先練拳再吃醋飯。一定很好吃', '我誰~我蛇丸'),
    (1, '伏地挺身課程', 1, '2024-08-15', '2024-09-14', 12, 30, 0, '伏地挺身', '重量運動', '徒手', 15000, '09:00:00', '10:00:00', '這門課程專為喜愛伏地挺身的健身愛好者設計，增強核心肌群與上肢力量，適合所有訓練水平。', '強化上肢與核心力量'),
    (2, '平板支撐課程', 2, '2024-08-16', '2024-09-15', 15, 25, 1, '平板支撐', '重量運動', '徒手', 16875, '10:00:00', '11:00:00', '專為增強核心肌群穩定性的平板支撐訓練，透過長時間的支撐動作，鍛鍊全身肌肉耐力。', '提升核心穩定性'),
    (3, '仰臥起坐課程', 3, '2024-08-17', '2024-09-16', 10, 20, 2, '仰臥起坐', '重量運動', '徒手', 13000, '11:00:00', '12:00:00', '此課程專注於仰臥起坐動作，強化腹肌群，提升整體核心力量及腰部穩定性。', '強化腹肌群'),
    (4, '橋式課程', 1, '2024-08-18', '2024-09-17', 8, 15, 0, '橋式', '重量運動', '徒手', 9600, '12:00:00', '13:00:00', '針對腰椎穩定及臀部強化設計的橋式訓練課程，適合長時間坐姿工作者。', '增強腰椎穩定性'),
    (5, '深蹲課程', 2, '2024-08-19', '2024-09-18', 20, 35, 1, '深蹲', '重量運動', '槓鈴', 32000, '13:00:00', '14:00:00', '本課程專為增強下肢力量的深蹲訓練設計，利用槓鈴增加負重，全面強化腿部及臀部肌群。', '增強下肢力量'),
    (1, '深蹲彈力繩課程', 3, '2024-08-20', '2024-09-19', 16, 30, 2, '深蹲', '重量運動', '彈力繩', 24800, '14:00:00', '15:00:00', '結合彈力繩的深蹲訓練，適合尋求更多挑戰的學員，增強腿部肌肉的爆發力與耐力。', '增強腿部爆發力'),
    (2, '臥推啞鈴課程', 4, '2024-08-21', '2024-09-20', 14, 25, 0, '臥推', '重量運動', '啞鈴', 20300, '15:00:00', '16:00:00', '使用啞鈴進行臥推訓練，著重於胸部肌群的發展，並提高肩膀的穩定性。', '強化胸部肌群'),
    (3, '臥推槓鈴課程', 1, '2024-08-22', '2024-09-21', 12, 20, 1, '臥推', '重量運動', '槓鈴', 16800, '16:00:00', '17:00:00', '本課程專注於利用槓鈴的臥推訓練，有效增強胸大肌與三頭肌力量。', '增強胸大肌與三頭肌'),
    (4, '硬拉課程', 2, '2024-08-23', '2024-09-22', 18, 35, 2, '硬拉', '重量運動', '槓鈴', 31500, '17:00:00', '18:00:00', '本課程以硬拉動作為核心，增強全身力量及肌肉耐力，尤其針對下背部及腿部。', '增強全身力量'),
    (5, '啞鈴弓步課程', 3, '2024-08-24', '2024-09-23', 14, 30, 0, '啞鈴弓步', '重量運動', '啞鈴', 18200, '18:00:00', '19:00:00', '專為鍛鍊下肢力量與平衡性的啞鈴弓步訓練，適合中高級訓練者。', '強化下肢力量與平衡'),
    (1, '肩推槓鈴課程', 4, '2024-08-25', '2024-09-24', 16, 25, 1, '肩推', '重量運動', '槓鈴', 23200, '19:00:00', '20:00:00', '通過槓鈴肩推運動，提升肩部肌肉力量，並增強肩關節的穩定性。', '提升肩部肌肉力量'),
    (2, '肩推機器課程', 2, '2024-08-26', '2024-09-25', 12, 20, 0, '肩推', '重量運動', '肩推機', 16200, '20:00:00', '21:00:00', '利用肩推機進行訓練，專注於提升肩部肌肉的力量與耐力，適合初學者。', '提升肩部力量與耐力'),
    (3, '引體向上課程', 3, '2024-08-27', '2024-09-26', 10, 15, 2, '引體向上', '重量運動', '單槓', 16000, '09:00:00', '10:00:00', '此課程針對背部與手臂肌群進行強化訓練，專為提高上肢力量設計。', '增強背部與手臂力量'),
    (4, '槓鈴划船課程', 1, '2024-08-28', '2024-09-27', 15, 25, 0, '槓鈴划船', '重量運動', '槓鈴', 24750, '10:00:00', '11:00:00', '透過槓鈴划船動作，增強背部與上肢力量，並提升核心穩定性。', '提升背部與上肢力量'),
    (5, '腿舉課程', 4, '2024-08-29', '2024-09-28', 14, 30, 1, '腿舉', '重量運動', '大腿推蹬訓練機', 21700, '11:00:00', '12:00:00', '利用腿舉機進行下肢肌群訓練，特別針對大腿肌群的力量增強。', '增強大腿肌群力量'),
    (1, '啞鈴側平舉課程', 2, '2024-08-30', '2024-09-29', 13, 20, 2, '啞鈴側平舉', '重量運動', '啞鈴', 16900, '12:00:00', '13:00:00', '專為提升肩部外展力量的啞鈴側平舉課程，適合各訓練水平。', '提升肩部外展力量'),
    (2, '啞鈴划船課程', 3, '2024-08-31', '2024-09-30', 10, 15, 0, '啞鈴划船', '重量運動', '啞鈴', 13500, '13:00:00', '14:00:00', '此課程針對背部肌群的增強訓練，專為提高背部力量與穩定性設計。', '增強背部力量'),
    (3, '跑步課程', 1, '2024-09-01', '2024-09-30', 20, 35, 1, '跑步', '心肺運動', '跑步機', 12500, '14:00:00', '15:00:00', '本課程專為提高心肺耐力設計，透過有氧跑步訓練，提升全身代謝能力。', '提高心肺耐力'),
    (4, '飛輪課程', 4, '2024-09-02', '2024-10-01', 18, 30, 2, '飛輪', '心肺運動', '飛輪', 14500, '15:00:00', '16:00:00', '專為提升腿部耐力與心肺功能的飛輪訓練課程，適合所有訓練水平。', '提升腿部耐力'),
    (5, '橢圓機課程', 3, '2024-09-03', '2024-10-02', 12, 25, 0, '橢圓機', '心肺運動', '橢圓機', 16500, '16:00:00', '17:00:00', '使用橢圓機進行低衝擊的全身心肺訓練，適合初學者和康復訓練者。', '低衝擊全身心肺訓練');

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
