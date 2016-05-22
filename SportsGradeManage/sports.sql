CREATE TABLE sportgrademanage.sports (
  sId VARCHAR(10) NOT NULL,
  sName VARCHAR(45) NOT NULL,
  longJump INT,
  highJump INT,
  run100 INT,
  run400 INT,
  swim INT,
  PRIMARY KEY (sId),
 )ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;