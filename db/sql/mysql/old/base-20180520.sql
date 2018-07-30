
-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;

DROP TABLE if exists `report_line`;


DROP TABLE if exists`allergen`;


DROP TABLE if exists`report`;


DROP TABLE if exists`category`;



-- ************************************** `report`

CREATE TABLE `report`
(
  `uuid`          CHAR(36) NOT NULL ,
  `date_creation` DATETIME NOT NULL ,
  `subject_name`  VARCHAR(100) NOT NULL ,

PRIMARY KEY (`uuid`)
);





-- ************************************** `category`

CREATE TABLE `category`
(
  `uuid`        CHAR(36) NOT NULL ,
  `e_name`      VARCHAR(45) NOT NULL ,
  `description` VARCHAR(300) ,

PRIMARY KEY (`uuid`)
);





-- ************************************** `report_line`

CREATE TABLE `report_line`
(
  `uuid`          CHAR(36) NOT NULL ,
  `id_report`     CHAR(36) NOT NULL ,
  `toxicity`      DOUBLE NOT NULL ,
  `allergen_name` VARCHAR(45) NOT NULL ,
  `allergen_id`   CHAR(36) NOT NULL ,

PRIMARY KEY (`uuid`, `id_report`),
KEY `fkIdx_33` (`id_report`),
CONSTRAINT `FK_33` FOREIGN KEY `fkIdx_33` (`id_report`) REFERENCES `report` (`uuid`)
);





-- ************************************** `allergen`

CREATE TABLE `allergen`
(
  `uuid`        CHAR(36) NOT NULL ,
  `e_name`      VARCHAR(45) NOT NULL ,
  `id_category` CHAR(36) NOT NULL ,

PRIMARY KEY (`uuid`),
KEY `fkIdx_23` (`id_category`),
CONSTRAINT `FK_23` FOREIGN KEY `fkIdx_23` (`id_category`) REFERENCES `category` (`uuid`)
);




