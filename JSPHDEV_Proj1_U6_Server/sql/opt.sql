CREATE TABLE IF NOT EXISTS `opt` (
  `opt_id` int(11) NOT NULL AUTO_INCREMENT,
  `opt_name` varchar(45) DEFAULT NULL,
  `opt_price` float DEFAULT NULL,
  PRIMARY KEY (`opt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
