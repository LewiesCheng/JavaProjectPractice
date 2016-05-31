CREATE TABLE `student_information`.`grade` (
  `sId` VARCHAR(15) NOT NULL,
  `cId` VARCHAR(5) NOT NULL,
  `grade` INT NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `student_information`.`grade` (`sId`, `cId`, `grade`) VALUES ('20142344068', '001', '98');
INSERT INTO `student_information`.`grade` (`sId`, `cId`, `grade`) VALUES ('20142344068', '002', '99');
INSERT INTO `student_information`.`grade` (`sId`, `cId`, `grade`) VALUES ('20142344068', '003', '100');
INSERT INTO `student_information`.`grade` (`sId`, `cId`, `grade`) VALUES ('20142344068', '004', '99');
INSERT INTO `student_information`.`grade` (`sId`, `cId`, `grade`) VALUES ('20142344071', '001', '100');
INSERT INTO `student_information`.`grade` (`sId`, `cId`, `grade`) VALUES ('20142344071', '002', '99');
INSERT INTO `student_information`.`grade` (`sId`, `cId`, `grade`) VALUES ('20142344071', '003', '98');
INSERT INTO `student_information`.`grade` (`sId`, `cId`, `grade`) VALUES ('20142344071', '004', '97');


CREATE TABLE `student_information`.`student` (
  `sId` VARCHAR(15) NOT NULL,
  `sName` VARCHAR(10) NOT NULL,
  `contactway` VARCHAR(15) NULL,
  `address` VARCHAR(45) NULL,
  PRIMARY KEY (`sId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `student_information`.`student` (`sId`, `sName`, `contactway`, `address`) VALUES ('20142344066', '李庆', '18795900001', '非洲');
INSERT INTO `student_information`.`student` (`sId`, `sName`, `contactway`, `address`) VALUES ('20142344067', '林健', '18795900002', '不是江苏的');
INSERT INTO `student_information`.`student` (`sId`, `sName`, `contactway`, `address`) VALUES ('20142344068', '刘骋', '18795900003', '江苏盐城');
INSERT INTO `student_information`.`student` (`sId`, `sName`, `contactway`, `address`) VALUES ('20142344071', '沈雅', '18795900004', '不知道哪的');
INSERT INTO `student_information`.`student` (`sId`, `sName`, `contactway`, `address`) VALUES ('20142344072', '施新华', '18795900005', '江苏南通');


CREATE TABLE `student_information`.`course` (
  `cId` VARCHAR(5) NOT NULL,
  `cName` VARCHAR(45) NULL,
  PRIMARY KEY (`cId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `student_information`.`course` (`cId`, `cName`) VALUES ('001', '高等数学');
INSERT INTO `student_information`.`course` (`cId`, `cName`) VALUES ('002', '英语');
INSERT INTO `student_information`.`course` (`cId`, `cName`) VALUES ('003', '数据库');
INSERT INTO `student_information`.`course` (`cId`, `cName`) VALUES ('004', '软件工程');


CREATE TABLE `student_information`.`login` (
  `level` INT NOT NULL,
  `user` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NULL,
  PRIMARY KEY (`user`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `student_information`.`login` (`level`, `user`, `password`) VALUES ('1', 'lizhenhong', 'lizhenhong');
INSERT INTO `student_information`.`login` (`level`, `user`, `password`) VALUES ('2', '20142344068', '20142344068');
INSERT INTO `student_information`.`login` (`level`, `user`, `password`) VALUES ('2', '20142344071', '20142344071');
INSERT INTO `student_information`.`login` (`level`, `user`, `password`) VALUES ('2', '20142344072', '20142344072');