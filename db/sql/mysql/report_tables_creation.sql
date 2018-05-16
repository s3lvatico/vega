CREATE TABLE `vg_report` (
  `id` char(36) NOT NULL,
  `data_creazione` date NOT NULL,
  `nome_soggetto` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `vg_report_data` (
  `id_report` char(36) NOT NULL,
  `id_allergene` char(36) NOT NULL,
  `tox` decimal(3,2) NOT NULL,
  PRIMARY KEY (`id_report`,`id_allergene`),
  KEY `fk_report_data__id_allergene_idx` (`id_allergene`),
  CONSTRAINT `fk_report_data__id_allergene` FOREIGN KEY (`id_allergene`) REFERENCES `vg_allergene` (`id`) ON UPDATE NO ACTION,
  CONSTRAINT `fk_report_data__id_report` FOREIGN KEY (`id_report`) REFERENCES `vg_report` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
