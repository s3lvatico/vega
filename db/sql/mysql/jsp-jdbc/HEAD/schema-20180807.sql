CREATE TABLE `category` (
  `id` char(36) NOT NULL,
  `e_name` varchar(100) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `allergen` (
  `id` char(36) NOT NULL,
  `e_name` varchar(50) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `id_category` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_75` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `report` (
  `id` char(64) NOT NULL,
  `subject_name` varchar(100) NOT NULL,
  `date_creation` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `report_line` (
  `id_report` char(64) NOT NULL,
  `id_allergen` char(36) NOT NULL,
  `toxicity` decimal(4,2) NOT NULL,
  PRIMARY KEY (`id_report`,`id_allergen`),
  CONSTRAINT `FK_report_line__report` FOREIGN KEY (`id_report`) REFERENCES `report` (`id`),
  CONSTRAINT `FK_report_line__allergen` FOREIGN KEY (`id_allergen`) REFERENCES `allergen` (`id`)
) ENGINE=InnoDB;

ALTER TABLE `vega`.`report_line`
DROP FOREIGN KEY `FK_report_line__report`;
ALTER TABLE `vega`.`report_line`
ADD CONSTRAINT `FK_report_line__report`
FOREIGN KEY (`id_report`)
REFERENCES `vega`.`report` (`id`)
ON DELETE CASCADE;
