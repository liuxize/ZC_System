/*
MySQL Backup
Database: examination_system
Backup Time: 2019-03-11 19:31:30
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `examination_system`.`birth`;
DROP TABLE IF EXISTS `examination_system`.`classtype`;
DROP TABLE IF EXISTS `examination_system`.`daterecord`;
DROP TABLE IF EXISTS `examination_system`.`exam`;
DROP TABLE IF EXISTS `examination_system`.`excellog`;
DROP TABLE IF EXISTS `examination_system`.`grade`;
DROP TABLE IF EXISTS `examination_system`.`image`;
DROP TABLE IF EXISTS `examination_system`.`lesson`;
DROP TABLE IF EXISTS `examination_system`.`major`;
DROP TABLE IF EXISTS `examination_system`.`notedic`;
DROP TABLE IF EXISTS `examination_system`.`notetable`;
DROP TABLE IF EXISTS `examination_system`.`permission`;
DROP TABLE IF EXISTS `examination_system`.`remindpay`;
DROP TABLE IF EXISTS `examination_system`.`role`;
DROP TABLE IF EXISTS `examination_system`.`school`;
DROP TABLE IF EXISTS `examination_system`.`sign`;
DROP TABLE IF EXISTS `examination_system`.`stu`;
DROP TABLE IF EXISTS `examination_system`.`subject`;
DROP TABLE IF EXISTS `examination_system`.`textdic`;
DROP TABLE IF EXISTS `examination_system`.`userlogin`;
DROP FUNCTION IF EXISTS `examination_system`.`f_not_leap_year`;
CREATE TABLE `birth` (
  `birthID` int(11) NOT NULL AUTO_INCREMENT,
  `stuID` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `birthName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`birthID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `classtype` (
  `typeID` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`typeID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
CREATE TABLE `daterecord` (
  `dateID` int(11) NOT NULL,
  `dateHis` date DEFAULT NULL,
  PRIMARY KEY (`dateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `exam` (
  `examID` int(11) NOT NULL AUTO_INCREMENT,
  `stuID` int(10) DEFAULT NULL,
  `examData` date DEFAULT NULL,
  `updatePerson` varchar(10) DEFAULT NULL,
  `examType` varchar(10) DEFAULT NULL,
  `mathScore` int(10) DEFAULT NULL,
  `math` int(10) DEFAULT NULL,
  `chinese` int(10) DEFAULT NULL,
  `chineseScore` int(10) DEFAULT NULL,
  `english` int(10) DEFAULT NULL,
  `englishScore` int(10) DEFAULT NULL,
  `physics` int(10) DEFAULT NULL,
  `phyScore` int(10) DEFAULT NULL,
  `chemistry` int(10) DEFAULT NULL,
  `chemScore` int(10) DEFAULT NULL,
  `politics` int(10) DEFAULT NULL,
  `polScore` int(10) DEFAULT NULL,
  `history` int(10) DEFAULT NULL,
  `hisScore` int(10) DEFAULT NULL,
  `biology` int(10) DEFAULT NULL,
  `bioScore` int(10) DEFAULT NULL,
  `geography` int(10) DEFAULT NULL,
  `geoScore` int(10) DEFAULT NULL,
  `examRemark` varchar(100) DEFAULT NULL,
  `examSign` int(5) DEFAULT NULL,
  `recordDate` date DEFAULT NULL,
  PRIMARY KEY (`examID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `excellog` (
  `logID` int(10) NOT NULL AUTO_INCREMENT,
  `excelID` int(10) DEFAULT NULL,
  `stuName` varchar(20) DEFAULT NULL,
  `typeID` int(10) DEFAULT NULL,
  PRIMARY KEY (`logID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `grade` (
  `gradeID` int(10) NOT NULL,
  `gradeName` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`gradeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `image` (
  `imageID` int(11) NOT NULL AUTO_INCREMENT,
  `stuID` int(11) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `path` varchar(200) DEFAULT NULL,
  `imageSign` int(11) NOT NULL,
  PRIMARY KEY (`imageID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `lesson` (
  `lessonID` int(11) NOT NULL AUTO_INCREMENT,
  `stuID` int(11) DEFAULT NULL,
  `lessonStart` date DEFAULT NULL,
  `lessonEnd` date DEFAULT NULL,
  `operator` varchar(100) DEFAULT NULL,
  `lecturer` varchar(100) DEFAULT NULL,
  `remark` varchar(800) DEFAULT NULL,
  `lessonTime` varchar(800) DEFAULT NULL,
  `typeID` int(10) DEFAULT NULL,
  `subjectID` int(10) DEFAULT NULL,
  `schoolTime` varchar(800) DEFAULT NULL,
  `schoolDate` varchar(800) DEFAULT NULL,
  `lessonSign` int(11) DEFAULT NULL,
  `recordDate` date DEFAULT NULL,
  `dutyDate` varchar(800) DEFAULT '',
  `dutyDateHis` varchar(1000) DEFAULT '',
  PRIMARY KEY (`lessonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `major` (
  `majorID` int(11) NOT NULL AUTO_INCREMENT,
  `majorName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`majorID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
CREATE TABLE `notedic` (
  `dicID` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(10) DEFAULT NULL,
  `dicName` varchar(100) DEFAULT NULL,
  `dicType` int(10) DEFAULT NULL,
  PRIMARY KEY (`dicID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `notetable` (
  `noteID` int(11) NOT NULL AUTO_INCREMENT,
  `stuName` varchar(100) DEFAULT NULL,
  `stuSchool` varchar(110) DEFAULT NULL,
  `stuGrade` varchar(11) DEFAULT NULL,
  `stuCourse` varchar(100) DEFAULT NULL,
  `remarkText` text,
  `userName` varchar(10) DEFAULT NULL,
  `dicID` int(10) DEFAULT NULL,
  PRIMARY KEY (`noteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `permission` (
  `permissionID` int(10) NOT NULL AUTO_INCREMENT,
  `teacherName` varchar(10) DEFAULT NULL,
  `gradeID` int(10) DEFAULT NULL,
  PRIMARY KEY (`permissionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `remindpay` (
  `remindID` int(10) NOT NULL AUTO_INCREMENT,
  `lessonID` int(10) NOT NULL,
  PRIMARY KEY (`remindID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `role` (
  `roleID` int(11) NOT NULL,
  `roleName` varchar(20) NOT NULL,
  `permissions` varchar(255) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `school` (
  `schoolID` int(10) NOT NULL AUTO_INCREMENT,
  `schoolName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`schoolID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `sign` (
  `signID` int(11) NOT NULL AUTO_INCREMENT,
  `stuID` int(5) DEFAULT NULL,
  `stuNameSign` int(5) DEFAULT NULL,
  `stuSexSign` int(5) DEFAULT NULL,
  `birthSign` int(5) DEFAULT NULL,
  `majorSign` int(5) DEFAULT NULL,
  `schoolSign` int(5) DEFAULT NULL,
  `stuTelSign` int(5) DEFAULT NULL,
  `moNameSign` int(5) DEFAULT NULL,
  `motherCompanySign` int(5) DEFAULT NULL,
  `motherJobSign` int(5) DEFAULT NULL,
  `motherTelSign` int(5) DEFAULT NULL,
  `motherBirthSign` int(5) DEFAULT NULL,
  `moDegreeSign` int(5) DEFAULT NULL,
  `faNameSign` int(5) DEFAULT NULL,
  `fatherCompanySign` int(5) DEFAULT NULL,
  `fatherJobSign` int(5) DEFAULT NULL,
  `fatherTelSign` int(5) DEFAULT NULL,
  `fatherBirthSign` int(5) DEFAULT NULL,
  `faDegreeSign` int(5) DEFAULT NULL,
  `addressSign` int(5) DEFAULT NULL,
  `masterNameSign` int(5) DEFAULT NULL,
  `masterTelSign` int(5) DEFAULT NULL,
  `masterAddressSign` int(5) DEFAULT NULL,
  `masterSexSign` int(5) DEFAULT NULL,
  `masterDegreeSign` int(5) DEFAULT NULL,
  `masterBirthSign` int(5) DEFAULT NULL,
  `checkDateSign` int(5) DEFAULT NULL,
  `schoolTextSign` int(5) DEFAULT NULL,
  `familyTextSign` int(5) DEFAULT NULL,
  `studyTextSign` int(5) DEFAULT NULL,
  `educationTextSign` int(5) DEFAULT NULL,
  `supportTextSign` int(5) DEFAULT NULL,
  `improveTextSign` int(5) DEFAULT NULL,
  `leaderSign` varchar(20) DEFAULT NULL,
  `masterSign` varchar(20) DEFAULT NULL,
  `adminSignID` int(5) DEFAULT NULL,
  `masterSignID` int(5) DEFAULT NULL,
  `leaderSignID` int(11) DEFAULT NULL,
  `receiveSign` int(5) DEFAULT NULL,
  PRIMARY KEY (`signID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `stu` (
  `stuID` int(11) NOT NULL AUTO_INCREMENT,
  `stuName` varchar(10) DEFAULT NULL,
  `stuNameHis` text,
  `stuSex` varchar(10) DEFAULT NULL,
  `stuSexHis` text,
  `stutype` varchar(10) DEFAULT NULL,
  `stuBirth` date DEFAULT NULL,
  `stuBirthHis` text,
  `school` varchar(20) DEFAULT NULL,
  `schoolHis` text,
  `major` varchar(20) DEFAULT NULL,
  `majorHis` text,
  `stuTel` varchar(20) DEFAULT NULL,
  `stuTelHis` text,
  `address` varchar(50) DEFAULT NULL,
  `addressHis` text,
  `motherName` varchar(20) DEFAULT NULL,
  `motherNameHis` text,
  `motherCompany` varchar(50) DEFAULT NULL,
  `motherCompanyHis` text,
  `motherJob` varchar(50) DEFAULT NULL,
  `motherJobHis` text,
  `motherTel` varchar(20) DEFAULT NULL,
  `motherTelHis` text,
  `motherBirth` date DEFAULT NULL,
  `motherBirthHis` text,
  `motherDegree` varchar(10) DEFAULT NULL,
  `motherDegreeHis` text,
  `fatherName` varchar(10) DEFAULT NULL,
  `fatherNameHis` text,
  `fatherCompany` varchar(50) DEFAULT NULL,
  `fatherCompanyHis` text,
  `fatherJob` varchar(50) DEFAULT NULL,
  `fatherJobHis` text,
  `fatherTel` varchar(200) DEFAULT NULL,
  `fatherTelHis` text,
  `fatherBirth` date DEFAULT NULL,
  `fatherBirthHis` text,
  `fatherDegree` varchar(10) DEFAULT NULL,
  `fatherDegreeHis` text,
  `masterName` varchar(10) DEFAULT NULL,
  `masterNameHis` text,
  `masterTel` varchar(20) DEFAULT NULL,
  `masterTelHis` text,
  `masterAddress` varchar(50) DEFAULT NULL,
  `masterAddressHis` text,
  `masterSex` varchar(10) DEFAULT NULL,
  `masterSexHis` text,
  `masterDegree` varchar(50) DEFAULT NULL,
  `masterDegreeHis` text,
  `masterBirth` date DEFAULT NULL,
  `masterBirthHis` text,
  `schoolText` text,
  `schoolTextHis` text,
  `familyText` text,
  `familyTextHis` text,
  `studyText` text,
  `studyTextHis` text,
  `educationText` text,
  `educationTextHis` text,
  `supportText` text,
  `supportTextHis` text,
  `improveText` text,
  `improveTextHis` text,
  `gradeID` int(10) DEFAULT NULL,
  `recordDate` date DEFAULT NULL,
  `recordPerson` varchar(20) DEFAULT NULL,
  `checkDate` date DEFAULT NULL,
  `checkDateHis` text,
  PRIMARY KEY (`stuID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `subject` (
  `subjectID` int(11) NOT NULL AUTO_INCREMENT,
  `subjectName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`subjectID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
CREATE TABLE `textdic` (
  `textID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` text,
  `textType` int(11) DEFAULT NULL,
  PRIMARY KEY (`textID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `userlogin` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` int(11) NOT NULL DEFAULT '2' COMMENT '角色权限',
  `userBirth` date DEFAULT NULL,
  `permission` int(10) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
CREATE DEFINER=`root`@`localhost` FUNCTION `f_not_leap_year`(p_year BIGINT) RETURNS tinyint(1)
BEGIN
/*是闰年则返回0(false)，不是闰年则返回1(true)*/
DECLARE v_flag INT DEFAULT 0;
/*①、普通年能被4整除且不能被100整除的为闰年。（如2004年就是闰年,1901年不是闰年）*/
IF (p_year%4)=0 AND (p_year%100)>0 THEN
SET v_flag=0; 
/*②、世纪年能被400整除的是闰年。(如2000年是闰年，1900年不是闰年) */
ELSEIF (p_year%400)=0 THEN
SET v_flag=0; 
/*③、对于数值很大的年份,这年如果能整除3200,并且能整除172800则是闰年。如172800年是闰年，
86400年不是闰年(因为虽然能整除3200，但不能整除172800)（此按一回归年365天5h48\'45.5\'\'计算）。
*/
ELSEIF (p_year%3200)=0 AND (p_year%172800)=0 THEN
SET v_flag=0;
ELSE
SET v_flag=1;
END IF;
RETURN v_flag;
END;
BEGIN;
LOCK TABLES `examination_system`.`birth` WRITE;
DELETE FROM `examination_system`.`birth`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`classtype` WRITE;
DELETE FROM `examination_system`.`classtype`;
INSERT INTO `examination_system`.`classtype` (`typeID`,`typeName`) VALUES (1, '班课'),(2, '晚自习'),(3, '托管'),(4, '一对一'),(5, '全日制');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`daterecord` WRITE;
DELETE FROM `examination_system`.`daterecord`;
INSERT INTO `examination_system`.`daterecord` (`dateID`,`dateHis`) VALUES (0, '2018-07-18');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`exam` WRITE;
DELETE FROM `examination_system`.`exam`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`excellog` WRITE;
DELETE FROM `examination_system`.`excellog`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`grade` WRITE;
DELETE FROM `examination_system`.`grade`;
INSERT INTO `examination_system`.`grade` (`gradeID`,`gradeName`) VALUES (0, '幼小'),(1, '小一'),(2, '小二'),(3, '小三'),(4, '小四'),(5, '小五'),(6, '小六'),(7, '初一'),(8, '初二'),(9, '初三'),(10, '高一'),(11, '高二'),(12, '高三'),(13, '大一'),(14, '大二'),(15, '大三'),(16, '大四'),(17, '工作');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`image` WRITE;
DELETE FROM `examination_system`.`image`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`lesson` WRITE;
DELETE FROM `examination_system`.`lesson`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`major` WRITE;
DELETE FROM `examination_system`.`major`;
INSERT INTO `examination_system`.`major` (`majorID`,`majorName`) VALUES (1, '无'),(2, '电工作业（高压）'),(4, '电工作业（低压）'),(5, '焊接与热切割作业'),(10, '制冷与空调作业'),(11, '高处作业'),(12, '高空作业'),(13, '空调安装与维修');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`notedic` WRITE;
DELETE FROM `examination_system`.`notedic`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`notetable` WRITE;
DELETE FROM `examination_system`.`notetable`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`permission` WRITE;
DELETE FROM `examination_system`.`permission`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`remindpay` WRITE;
DELETE FROM `examination_system`.`remindpay`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`role` WRITE;
DELETE FROM `examination_system`.`role`;
INSERT INTO `examination_system`.`role` (`roleID`,`roleName`,`permissions`) VALUES (0, 'admin', '管理员'),(1, 'master', '校长'),(2, 'teacher', '教师'),(3, 'leader', '负责人');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`school` WRITE;
DELETE FROM `examination_system`.`school`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`sign` WRITE;
DELETE FROM `examination_system`.`sign`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`stu` WRITE;
DELETE FROM `examination_system`.`stu`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`subject` WRITE;
DELETE FROM `examination_system`.`subject`;
INSERT INTO `examination_system`.`subject` (`subjectID`,`subjectName`) VALUES (1, '数学'),(2, '语文'),(3, '英语'),(4, '物理'),(5, '化学'),(6, '日语'),(7, '政治'),(8, '历史'),(9, '生物'),(10, '体育'),(11, '新概念'),(12, '剑桥'),(13, '奥数'),(15, '托管'),(16, '晚自习'),(17, '音标'),(21, '硬笔书法'),(22, '幼小衔接'),(24, '阅读写作'),(26, '拼读音标');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`textdic` WRITE;
DELETE FROM `examination_system`.`textdic`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `examination_system`.`userlogin` WRITE;
DELETE FROM `examination_system`.`userlogin`;
INSERT INTO `examination_system`.`userlogin` (`userID`,`userName`,`password`,`role`,`userBirth`,`permission`) VALUES (1, '管理员', '123', 0, NULL, NULL),(2, '王', '123', 1, NULL, 2),(4, '张', '123', 3, NULL, 2),(25, '哈', '123', 2, NULL, 2);
UNLOCK TABLES;
COMMIT;
