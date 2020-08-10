CREATE TABLE `pizzalocation` (
  `pizzaId` bigint(20) NOT NULL,
  `locationId` bigint(20) NOT NULL,
  UNIQUE KEY `UKbuauyrh4d521q4dlmpg1h6tgi` (`pizzaId`,`locationId`),
  KEY `FK5dxdpvqg9j6ujq6gnyxbvaj4p` (`locationId`),
  CONSTRAINT `FK5dxdpvqg9j6ujq6gnyxbvaj4p` FOREIGN KEY (`locationId`) REFERENCES `location` (`id`),
  CONSTRAINT `FKah8iirfxdvuoc8jid9kjhhivs` FOREIGN KEY (`pizzaId`) REFERENCES `pizza` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
