CREATE DATABASE IF NOT EXISTS tia102g3;

USE tia102g3;

DROP TABLE IF EXISTS coach_course_pic;
DROP TABLE IF EXISTS coach_course;
DROP TABLE IF EXISTS coach_specialty;
DROP TABLE IF EXISTS coach_certificate;
DROP TABLE IF EXISTS customized_course;
DROP TABLE IF EXISTS sport_course;
DROP TABLE IF EXISTS system_course;
DROP TABLE IF EXISTS system_course_pic;

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

INSERT INTO system_course (systemCourseID, courseName, sportEventName, sportTypes, sportEquipment, courseLevel, burnCalories, rps, eachExerciseTime, sportTime, swp, illustrate, video)
VALUES
    (1, '跪式伏地挺身', '伏地挺身', '重量運動', '徒手', 1, 80, 15, null, null, 4,
     '主要鍛鍊肌群：胸肌、三頭肌和肩部肌肉。
     強化這些肌群有助於增強上肢力量、改善核心穩定性並提高肌肉耐力。
     運動細節：跪式伏地挺身是一種伏地挺身的變式，專為肩部和上臂力量尚未達到標準伏地挺身要求的初學者設計。首先，跪在地板上，雙膝微微分開，腳背平放在地面上。雙手放在肩膀正下方，手指略微朝外，手臂與肩同寬。吸氣時，屈肘向外側彎曲，慢慢讓身體下降，直到胸部接近地面，注意保持核心收緊，背部挺直。然後，用力推地，將身體推回到起始位置，同時呼氣。這個動作能夠逐漸強化上肢肌肉和核心肌群，為進一步挑戰更高難度的伏地挺身做好準備。每次進行15次跪式伏地挺身，休息30秒，重複4組。隨著力量的增強，可以逐漸過渡到標準的伏地挺身。',
     'https://www.youtube.com/embed/vAALjNpx2uQ?si=HBxrjqQMqwqC4GPm'),

    (2, '俯臥撐', '伏地挺身', '重量運動', '徒手', 2, 100, 15, null, null, 4,
     '主要鍛鍊肌群：胸肌、三頭肌和肩部肌肉。
     強化這些肌群有助於增強上肢力量、改善核心穩定性並提高肌肉耐力。
     運動細節：俯臥撐是最經典的自重訓練動作之一，對胸肌、肩部和三頭肌的發展有顯著效果。開始時，俯臥在地板上，雙手放在肩膀正下方，手臂與肩同寬或稍寬。腳趾踩地，雙腳併攏，核心收緊，保持身體從頭到腳呈一直線。吸氣時，慢慢屈肘，讓身體下降，直到胸部幾乎接觸地面，然後用力推回到起始位置，同時呼氣。俯臥撐的變化很多，可以根據個人需求調整難度。例如，將雙手放在稍高的位置如椅子上，能夠減少負重，適合初學者；而腳放高或手距離更寬的變式則能增加難度。每次進行15次，休息30秒，重複4組。俯臥撐能有效地提高上半身的力量和穩定性，是一個不可或缺的基本動作。',
     'https://www.youtube.com/embed/lfBh7TMLwg0?si=_abiqQaT91M4Ac0R'),

    (3, '基本深蹲', '深蹲', '重量運動', '徒手', 1, 90, 15, null, null, 4,
     '主要鍛鍊肌群：大腿前側肌群（股四頭肌）、臀部肌群（臀大肌）和核心肌群。
     強化這些肌群有助於增強下半身力量，提高腿部肌肉質量，改善核心穩定性和身體平衡。
     運動細節：深蹲是一個全面的下肢訓練動作，能夠有效地鍛鍊腿部和臀部的肌肉。開始時，站立，雙腳與肩同寬，腳尖稍微朝外。雙手可以向前平舉，或者叉腰保持平衡。吸氣，屈膝屈髖，將臀部向後推像坐在椅子上一樣，保持背部挺直，膝蓋應該跟隨腳尖方向移動，並不超過腳尖。下蹲至大腿與地面平行或稍低於平行位置時，停止動作，確保膝蓋沒有內扣或過度向外擴張。然後，用力伸展雙腿，回到站立位置，同時呼氣。在整個過程中，保持核心緊張，避免下背部拱起或過度前傾。深蹲可以通過增加負重（如手持啞鈴或槓鈴）來提高難度。每次進行15次深蹲，休息30秒，重複4組。深蹲動作對增強下肢肌肉力量和改善運動表現至關重要。',
     'https://www.youtube.com/embed/aIG28K4yvqg?si=uMWxuI737eKB3Xau'),

    (4, '彈力繩深蹲', '深蹲', '重量運動', '彈力繩', 3, 200, 30, null, null, 4,
     '主要鍛鍊肌群：大腿前側肌群（股四頭肌）、臀部肌群（臀大肌）和核心肌群。
     強化這些肌群有助於增強下半身力量，提高腿部肌肉質量，改善核心穩定性和身體平衡。
     運動細節：彈力繩深蹲是一種利用彈力繩增加阻力的變式深蹲，適合尋求更多挑戰的運動者。開始時，站立，腳踩在彈力繩的中部，雙腳與肩同寬，手持彈力繩的兩端，將彈力繩拉至肩部高度，雙手可以放在肩膀前面。吸氣，屈膝屈髖，臀部向後推，像坐在椅子上一樣，進行深蹲動作。彈力繩會隨著你下蹲的深度增加阻力，這能更有效地鍛鍊腿部和臀部肌肉。在最低點時，保持一秒鐘，然後用力推地回到起始位置，同時呼氣。在整個動作中保持核心緊張，避免下背部拱起。每次進行30次深蹲，休息30秒，重複4組。這個變式深蹲可以幫助你更快地提升腿部力量和肌肉耐力。',
     'https://www.youtube.com/embed/EYQfeJe4ZJc?si=PWi496Grvcsw_NVx'),

    (5, '地獄深蹲', '深蹲', '重量運動', '槓鈴', 4, 200, 12, null, null, 4,
     '主要鍛鍊肌群：大腿前側肌群（股四頭肌）、臀部肌群（臀大肌）和核心肌群。
     強化這些肌群有助於增強下半身力量，提高腿部肌肉質量，改善核心穩定性和身體平衡。
     運動細節：地獄深蹲是一種極具挑戰性的深蹲變式，通常使用槓鈴來增加負重。首先，將槓鈴置於肩膀後方的斜方肌上，雙手握住槓鈴，手肘向下靠近身體。站立時雙腳與肩同寬，腳尖略微朝外。吸氣，屈膝屈髖，臀部向後推，進行深蹲動作。下蹲至大腿與地面平行甚至稍低，保持一秒鐘，然後用力伸展雙腿回到站立位置，同時呼氣。此動作要求較高的核心穩定性和腿部力量，在整個過程中，保持背部挺直，膝蓋跟隨腳尖方向，避免膝蓋內扣或外擴。地獄深蹲對增強腿部和臀部力量極為有效，但對膝蓋和下背部的壓力較大，因此適合有經驗的訓練者。每次進行12次地獄深蹲，休息1分鐘，重複4組。隨著力量的增強，可以逐漸增加槓鈴的重量。',
     'https://www.youtube.com/embed/JjDKzJHqoAs?si=p2XbQ5eDfw4g_x0p'),

    (6, '皮拉提斯', '皮拉提斯', '重量運動', '徒手', 1, 150, null, '12分鐘', '1小時', null,
     '主要鍛鍊肌群：核心肌群、背部、臀部及腿部肌群。
     強化這些肌群有助於增強核心力量、改善姿勢、提高身體平衡和靈活性。
     運動細節：皮拉提斯是一種強調核心控制與身體協調的訓練方法，旨在提升整體身體機能。開始時，仰臥在瑜伽墊上，雙腳屈膝平放地面，雙手置於身體兩側。首先進行橋式（Bridge）：吸氣，將臀部慢慢抬起，直到大腿和背部成一直線，保持核心收緊，停留3秒鐘後緩慢放下。接下來進行捲腹（Crunch）：仰臥，雙手放在頭後，吸氣，將上半身慢慢捲起，眼睛看向膝蓋，停留片刻後緩慢放下。然後進行側腿抬高（Side Leg Lift）：側臥在地面上，單側手肘支撐頭部，另一側手放在胸前，抬起上側腿到45度，然後緩慢放下。這些動作強調呼吸與動作的配合，並且需要保持核心的穩定性。整個皮拉提斯訓練持續約12分鐘，每個動作重複10-15次。皮拉提斯的持續訓練可以顯著改善身體姿態、增強核心力量，並促進全身的協調與平衡。',
     'https://www.youtube.com/embed/3jn09b63yMg?si=SbUZg3vHeLQ5hnhw'),

    (7, '彈力繩皮拉提斯', '皮拉提斯', '重量運動', '彈力繩', 3, 200, null, '12分鐘', '1小時', null,
     '主要鍛鍊肌群：核心肌群、背部、臀部、腿部及手臂肌群。
     強化這些肌群有助於增強核心力量、改善姿勢、提高身體的穩定性與柔韌性，並促進全身肌肉的協調。
     運動細節：彈力繩皮拉提斯是一種結合了傳統皮拉提斯與彈力繩訓練的綜合運動，旨在進一步增強肌肉耐力和力量。開始時，坐在瑜伽墊上，雙腳踩住彈力繩，雙手握住彈力繩的兩端。進行船式（Boat Pose）：吸氣，抬起雙腿離開地面，身體向後傾斜至45度角，保持平衡，同時將彈力繩拉緊以增加核心的負荷。然後進行側平板（Side Plank）：側臥，將彈力繩繞過腳底，進行側平板動作，同時拉緊彈力繩以增加肩部和手臂的負荷。這些動作能夠進一步提高核心肌群的強度和穩定性，並且通過彈力繩的阻力來增加運動的挑戰性。整個訓練約12分鐘，每個動作重複10-15次。彈力繩皮拉提斯有助於塑造更緊實的肌肉線條，並促進全身的協調與平衡，是提升運動強度的理想選擇。',
     'https://www.youtube.com/embed/bHL8kHE8_zE?si=7qmnb-Z2JyMKOGdq'),

    (8, '弓步', '弓步', '重量運動', '徒手', 1, 80, 15, null, null, 4,
     '主要鍛鍊肌群：大腿前側肌群（股四頭肌）、臀部肌群（臀大肌）和核心肌群。
     強化這些肌群有助於增強下半身力量，提高腿部穩定性和平衡能力，減少運動傷害風險。
     運動細節：弓步是一種有效的單腿訓練動作，主要針對大腿和臀部肌群的強化。站立，雙腳與肩同寬，雙手放在髖部或胸前。向前跨出一大步，保持背部挺直，前腿彎曲至大腿平行地面，膝蓋與腳尖對齊，後腿屈膝下降接近地面但不觸地。吸氣，將身體的重量均勻分配在雙腿之間，保持核心收緊，然後用前腳的力量推回到站立位置，換腿重複動作。弓步能有效鍛鍊雙腿的協調性和穩定性，並且可以調整步幅來增加或減少訓練強度。進行每側15次弓步，休息30秒，重複4組。弓步是改善平衡能力和加強下肢肌肉的重要動作，適合各種運動水平的人群。',
     'https://www.youtube.com/embed/M_pEaqqFpLI?si=ouRXKcohKw2-HFR6'),

    (9, '初級跑步', '跑步', '心肺運動', '徒手', 1, 200, null, '30分鐘', '1小時', null,
     '主要鍛鍊肌群：心肺系統、大腿肌群、臀部肌群及核心肌群。
     強化這些肌群和系統有助於提升心肺功能、增強下半身力量並提高整體耐力。
     運動細節：初級跑步是一項有氧運動，適合剛開始進行心肺鍛鍊的運動者。選擇平坦的道路或跑步機作為訓練場地，開始時保持慢速的跑步節奏，以便身體逐漸適應。每次跑步前進行5-10分鐘的熱身，如動態伸展或慢走，以預防運動損傷。跑步時，保持輕鬆的姿勢，頭部抬高，眼睛平視前方，雙臂自然擺動。腳跟輕輕著地，然後迅速過渡到腳掌推進，避免用前腳掌或後腳跟過度著地以防止傷害。注意呼吸節奏，保持均勻的吸氣和呼氣，避免呼吸急促。每次跑步約30分鐘，然後進行5-10分鐘的冷身運動，如慢走和靜態伸展。隨著訓練進度的提升，可以逐步增加跑步時間或速度，以挑戰自身的耐力極限。跑步是一項有效的心肺鍛鍊，能夠促進全身健康，幫助燃燒卡路里並提高體能。',
     'https://www.youtube.com/embed/q3v5EmXV4B8?si=FAHLMpJhgvz3pkJI'),

    (10, '中級跑步', '跑步', '心肺運動', '跑步機', 2, 250, null, '30分鐘', '1小時', null,
     '主要鍛鍊肌群：心肺系統、大腿肌群、臀部肌群及核心肌群。
     強化這些肌群和系統有助於提升心肺功能、增強下半身力量並提高整體耐力。
     運動細節：中級跑步適合已有跑步基礎的運動者，是提升心肺功能和下肢力量的理想選擇。建議在跑步機上進行，並根據個人能力設定適當的速度和坡度。跑步前進行至少5分鐘的動態熱身，如動態伸展和腿部活動，以提高肌肉溫度和彈性。跑步時，保持穩定的節奏和均勻的呼吸，避免過度緊張。腳步應該輕快有力，腳跟輕輕著地後迅速過渡到腳掌推進。隨著訓練進度的提高，可以逐漸增加跑步時間或坡度，以挑戰自身的耐力極限。在跑步結束後，進行5-10分鐘的冷身運動，如慢走或靜態伸展，以促進血液循環和肌肉恢復。每次訓練約30分鐘，逐步提高強度以提升心肺耐力和燃燒卡路里的效果。中級跑步是保持健康體魄和控制體重的有效方法，適合有一定跑步經驗的人群。',
     'https://www.youtube.com/embed/PVRxR0-nlR0?si=AK1TwmTh-P42b16X');


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

insert into coach_specialty (cMemberID, sportEventID)
values(1, 32) ,
      (2, 10),
      (3, 30),
      (4, 21),
      (5, 18),
      (1, 22),
      (2, 31),
      (2, 24);

CREATE TABLE coach_certificate
(
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '教練證照ID。主鍵，自動遞增',
    cMemberID          INT NOT NULL COMMENT '教練會員ID。外鍵，關聯到coach_member表的cMemberID',
    certificateName    VARCHAR(200) COMMENT '證照名稱',
    certificatePic     LONGBLOB COMMENT '證照照片'
) COMMENT ='教練證照表。存儲教練的證照信息，包括證照名稱和照片';

insert into coach_certificate (cMemberID, certificateName)
    values (1, 'C級重量訓練教練證'),
           (2, 'C級重量訓練教練證'),
           (3, 'C級重量訓練教練證'),
           (4, 'C級重量訓練教練證'),
           (5, 'C級重量訓練教練證');

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
