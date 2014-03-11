CREATE TABLE IF NOT EXISTS `model` (
  `model` varchar(45) NOT NULL,
  `make` varchar(45) DEFAULT NULL,
  `basePrice` float DEFAULT NULL,
  PRIMARY KEY (`model`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
