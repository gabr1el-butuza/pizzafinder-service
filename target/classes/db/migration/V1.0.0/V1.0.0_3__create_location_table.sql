CREATE TABLE IF NOT EXISTS `location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` longtext,
  `googleId` varchar(255) NOT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lgavgml2xchtvceflri9w7917` (`googleId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;