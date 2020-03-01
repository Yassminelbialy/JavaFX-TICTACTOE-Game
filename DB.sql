create database if not exists tectactoc;
use tectactoc;
SELECT * FROM tectactoc.players;
CREATE TABLE `tectactoc`.`players` (
  `id` INT NOT NULL auto_increment,
  `playerName` VARCHAR(50) NOT NULL,
  `password` VARCHAR(191) NOT NULL,
  `gender` ENUM('0', '1') NOT NULL,
  `active` ENUM('0', '1') NOT NULL default '0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
  );
  INSERT INTO `tectactoc`.`players` (`id`, `playerName`, `password`, `gender`, `active`) VALUES ('1', 'mohamed', '123456', '1', '0');
  INSERT INTO `tectactoc`.`players` (`id`, `playerName`, `password`, `gender`, `active`) VALUES ('2', 'ahmed', '123456', '1', '1');
  INSERT INTO `tectactoc`.`players` (`id`, `playerName`, `password`, `gender`, `active`) VALUES ('3', 'asmaa', '123456', '0', '0');

