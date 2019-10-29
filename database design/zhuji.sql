/*
Navicat MySQL Data Transfer

Source Server         : zhuji
Source Server Version : 50527
Source Host           : 192.168.98.252:3306
Source Database       : zhuji

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-10-29 12:01:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dindan_table
-- ----------------------------
DROP TABLE IF EXISTS `dindan_table`;
CREATE TABLE `dindan_table` (
  `dingdan_ID` int(11) NOT NULL AUTO_INCREMENT,
  `dingdan_NO` varchar(35) DEFAULT NULL,
  `user_ID` int(11) DEFAULT NULL,
  `xianlu_ID` int(25) DEFAULT NULL,
  `zifu_time` varchar(255) DEFAULT NULL,
  `zifu_NO` varchar(25) DEFAULT NULL,
  `dindanzongjia` varchar(25) DEFAULT NULL,
  `zhuangtai` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`dingdan_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for guanliyue_table
-- ----------------------------
DROP TABLE IF EXISTS `guanliyue_table`;
CREATE TABLE `guanliyue_table` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `admin_name` varchar(10) DEFAULT NULL COMMENT '管理员姓名',
  `admin_pwd` varchar(255) DEFAULT NULL COMMENT '管理员密码',
  `admin_role` varchar(30) DEFAULT NULL COMMENT '管理员职务',
  `admin_tel` varchar(11) DEFAULT NULL COMMENT '管理员电话',
  `admin_email` varchar(30) DEFAULT NULL COMMENT '管理员邮件',
  `admin_photo_url` varchar(50) DEFAULT NULL COMMENT '管理员头像',
  `admin_time` varchar(20) DEFAULT NULL COMMENT '管理员加入时间',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hezuoshangjiatupian_table
-- ----------------------------
DROP TABLE IF EXISTS `hezuoshangjiatupian_table`;
CREATE TABLE `hezuoshangjiatupian_table` (
  `tupian_ID` int(11) NOT NULL AUTO_INCREMENT,
  `shangjia_ID` int(11) DEFAULT NULL,
  `tupianURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tupian_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hezuoshangjia_table
-- ----------------------------
DROP TABLE IF EXISTS `hezuoshangjia_table`;
CREATE TABLE `hezuoshangjia_table` (
  `shangjia_ID` int(11) NOT NULL AUTO_INCREMENT,
  `mingcheng` varchar(20) DEFAULT NULL,
  `leixing` varchar(15) DEFAULT NULL,
  `dianhua` varchar(11) DEFAULT NULL,
  `zhuangtai` varchar(10) DEFAULT NULL,
  `dizhi` varchar(50) DEFAULT NULL,
  `jingdian_ID` int(11) DEFAULT NULL,
  `tupianID` int(11) DEFAULT NULL,
  `jieshao` varchar(255) DEFAULT NULL,
  `hezuoshijian` varchar(55) DEFAULT NULL,
  `junjia` varchar(12) DEFAULT NULL COMMENT '均价',
  PRIMARY KEY (`shangjia_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jingdiantupian_table
-- ----------------------------
DROP TABLE IF EXISTS `jingdiantupian_table`;
CREATE TABLE `jingdiantupian_table` (
  `tupian_ID` int(11) NOT NULL,
  `jingdian_ID` int(11) DEFAULT NULL,
  `tupianURL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tupian_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jingdian_table
-- ----------------------------
DROP TABLE IF EXISTS `jingdian_table`;
CREATE TABLE `jingdian_table` (
  `jingdian_ID` int(12) NOT NULL AUTO_INCREMENT,
  `tupian_ID` int(12) DEFAULT NULL,
  `mingcheng` varchar(15) DEFAULT NULL,
  `dizhi` varchar(255) DEFAULT NULL,
  `miaosu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`jingdian_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pingjia_table
-- ----------------------------
DROP TABLE IF EXISTS `pingjia_table`;
CREATE TABLE `pingjia_table` (
  `pinjia_ID` int(11) NOT NULL AUTO_INCREMENT,
  `jingdian_ID` int(11) DEFAULT NULL,
  `xingcheng_ID` int(11) DEFAULT NULL,
  `user_ID` int(11) DEFAULT NULL,
  `pingjia` varchar(255) DEFAULT NULL,
  `pingjia_time` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`pinjia_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pinlun_tupian_table
-- ----------------------------
DROP TABLE IF EXISTS `pinlun_tupian_table`;
CREATE TABLE `pinlun_tupian_table` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `xingcheng_ID` int(11) DEFAULT NULL,
  `tupianURL` varchar(255) DEFAULT NULL,
  `zhuangtai` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `user_ID` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(11) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_mail` varchar(255) DEFAULT NULL,
  `user_telphone` varchar(11) DEFAULT NULL,
  `user_photo` varchar(30) DEFAULT NULL,
  `user_qq` varchar(13) DEFAULT NULL,
  `user_status` varchar(2) DEFAULT NULL,
  `user_remark` varchar(255) DEFAULT NULL,
  `user_createTime` varchar(15) DEFAULT NULL,
  `user_sex` varchar(2) DEFAULT NULL,
  `user_happy` varchar(100) DEFAULT NULL,
  `user_adress` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xainlu_jingdain_guanxi_table
-- ----------------------------
DROP TABLE IF EXISTS `xainlu_jingdain_guanxi_table`;
CREATE TABLE `xainlu_jingdain_guanxi_table` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `xianlu_ID` int(11) DEFAULT NULL,
  `jingdian_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xianlu_table
-- ----------------------------
DROP TABLE IF EXISTS `xianlu_table`;
CREATE TABLE `xianlu_table` (
  `xianlu_ID` int(11) NOT NULL AUTO_INCREMENT,
  `jingdian_ID` int(11) DEFAULT NULL,
  `pingjia` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`xianlu_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xingcheng_table
-- ----------------------------
DROP TABLE IF EXISTS `xingcheng_table`;
CREATE TABLE `xingcheng_table` (
  `xingcheng_ID` int(11) NOT NULL AUTO_INCREMENT,
  `xianlu_ID` int(11) DEFAULT NULL,
  `kaishi_time` varchar(255) DEFAULT NULL,
  `jiesu_time` varchar(255) DEFAULT NULL,
  `renshu` varchar(15) DEFAULT NULL,
  `zhuangtai` varchar(15) DEFAULT NULL,
  `jiage` varchar(55) DEFAULT NULL,
  `shuoming` varchar(255) DEFAULT NULL,
  `tixing` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`xingcheng_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for youji_table
-- ----------------------------
DROP TABLE IF EXISTS `youji_table`;
CREATE TABLE `youji_table` (
  `youji_ID` int(11) NOT NULL AUTO_INCREMENT,
  `youji_Adress` varchar(255) DEFAULT NULL,
  `youji_time` varchar(255) DEFAULT NULL,
  `youji_Share` longtext,
  PRIMARY KEY (`youji_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
