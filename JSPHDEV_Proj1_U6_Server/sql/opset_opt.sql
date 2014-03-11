CREATE TABLE IF NOT EXISTS `opset_opt` (
  `opset_id` int(11) NOT NULL,
  `opt_id` int(11) NOT NULL,
  PRIMARY KEY (`opset_id`,`opt_id`),
  KEY `fk_opset_opt_2_idx` (`opt_id`),
  CONSTRAINT `fk_opset_opt_1` FOREIGN KEY (`opset_id`) REFERENCES `opset` (`opset_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_opset_opt_2` FOREIGN KEY (`opt_id`) REFERENCES `opt` (`opt_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
