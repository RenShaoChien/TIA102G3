----table restIfo----


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
    mapID int , CONSTRAINT fk_rest_inf_mapID
        FOREIGN KEY (mapID) REFERENCES rest_map (mapID)
);


INSERT INTO rest_ifo (restLoc,restName,restAddress,restTime,restTel,mapID) VALUES
('1', 'x力盒子', '台北市xx街10號', '周一至周五 10:30~20:30', 02-2222-3333,1),
('2', '自由健康餐', '台北市xx路5號', '周一至周六 11:00~21:00', '02-1111-4444',2),
('3', '壯壯低脂餐', '新北市xx路15號', '周一至周五 10:00~20:00', '02-5555-6666', 3);