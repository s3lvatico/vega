
DROP TABLE IF EXISTS `report_line`;

DROP TABLE IF EXISTS `allergen`;

DROP TABLE IF EXISTS `report`;

DROP TABLE IF EXISTS `category`;



-- ************************************** `report`

CREATE TABLE `report`
(
 `id`            CHAR(36) NOT NULL ,
 `subject_name`  VARCHAR(100) NOT NULL ,
 `date_creation` DATETIME NOT NULL ,

PRIMARY KEY (`id`)
);





-- ************************************** `category`

CREATE TABLE `category`
(
 `id`      CHAR(36) NOT NULL ,
 `e_name`  VARCHAR(100) NOT NULL ,
 `deleted` TINYINT(1) NOT NULL DEFAULT 0 ,

PRIMARY KEY (`id`)
-- KEY `Ind_55`)
);





-- ************************************** `allergen`

CREATE TABLE `allergen`
(
 `id`          CHAR(36) NOT NULL ,
 `e_name`      VARCHAR(50) NOT NULL ,
 `deleted`     TINYINT(1) NOT NULL DEFAULT 0 ,
 `id_category` CHAR(36) NOT NULL ,

PRIMARY KEY (`id`),
KEY `fkIdx_75` (`id_category`),
CONSTRAINT `FK_75` FOREIGN KEY `fkIdx_75` (`id_category`) REFERENCES `category` (`id`),
KEY `idx_category` (`id_category`)
);





-- ************************************** `report_line`

CREATE TABLE `report_line`
(
 `id_allergen` CHAR(36) NOT NULL ,
 `id_report`   CHAR(36) NOT NULL ,
 `toxicity`    DECIMAL(4,2) NOT NULL ,

PRIMARY KEY (`id_allergen`, `id_report`),
KEY `fkIdx_109` (`id_allergen`),
CONSTRAINT `FK_109` FOREIGN KEY `fkIdx_109` (`id_allergen`) REFERENCES `allergen` (`id`),
KEY `fkIdx_113` (`id_report`),
CONSTRAINT `FK_113` FOREIGN KEY `fkIdx_113` (`id_report`) REFERENCES `report` (`id`)
);




