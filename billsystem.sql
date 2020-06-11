/*
Navicat MySQL Data Transfer

Source Server         : rootlin
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : billsystem

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-06-11 10:32:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('3', '日常生活', '1');
INSERT INTO `category` VALUES ('4', '餐饮聚会', '1');
INSERT INTO `category` VALUES ('7', '交通出行', '1');
INSERT INTO `category` VALUES ('9', '教育学习', '1');
INSERT INTO `category` VALUES ('10', '交通出行', '2');
INSERT INTO `category` VALUES ('12', '餐饮聚会', '2');
INSERT INTO `category` VALUES ('13', '教育出行', '2');
INSERT INTO `category` VALUES ('15', '健身运动', '4');
INSERT INTO `category` VALUES ('16', '交通出行', '4');
INSERT INTO `category` VALUES ('17', '餐饮聚会', '4');
INSERT INTO `category` VALUES ('19', '游戏充值', '1');
INSERT INTO `category` VALUES ('20', '游戏充值', '2');
INSERT INTO `category` VALUES ('21', '外设手办', '2');
INSERT INTO `category` VALUES ('22', '手办外设', '1');
INSERT INTO `category` VALUES ('23', '网购服装', '1');

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('1', '1500', '1');
INSERT INTO `config` VALUES ('2', '5000', '2');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spend` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `categoryName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_record_category` (`cid`),
  CONSTRAINT `fk_record_category` FOREIGN KEY (`cid`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('19', '9', '4', '石锅鸡蛋，外焦里嫩，特别好吃。希望下次还能来吃', '2020-05-14', '1', '餐饮聚会');
INSERT INTO `record` VALUES ('20', '69', '19', '买了一个压缩的皮肤，手感很六，感觉五杀不远了', '2020-06-03', '1', '游戏充值');
INSERT INTO `record` VALUES ('21', '12', '4', '石锅小炒肉', '2020-06-03', '1', '餐饮聚会');
INSERT INTO `record` VALUES ('22', '899', '21', '宁芝静电容键盘，非常好用，手感顺滑，续航持久，而且蓝牙信号特别好。下次推荐给朋友试试，五星好评！', '2020-06-01', '2', '外设手办');
INSERT INTO `record` VALUES ('23', '849', '21', '阿米诺机械键盘静音红轴，非常好用，手感顺滑，续航持久，而且蓝牙信号特别好。下次推荐给朋友试试，五星好评！', '2020-06-03', '2', '外设手办');
INSERT INTO `record` VALUES ('24', '899', '22', '宁芝静电容键盘', '2020-04-04', '1', '手办外设');
INSERT INTO `record` VALUES ('25', '299', '23', '一条唐狮牛仔裤', '2020-05-24', '1', '网购服装');
INSERT INTO `record` VALUES ('26', '233', '3', '买了个寂寞', '2020-03-26', '1', '日常生活');
INSERT INTO `record` VALUES ('27', '69', '3', '特仑苏牛奶', '2020-06-01', '1', '日常生活');
INSERT INTO `record` VALUES ('28', '13', '3', '奶茶', '2020-04-24', '1', '日常生活');
INSERT INTO `record` VALUES ('29', '120', '22', '买了一个罗技鼠标', '2020-06-04', '1', '手办外设');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'root', 'admin');
INSERT INTO `user` VALUES ('2', 'rootlin', 'admin');
INSERT INTO `user` VALUES ('3', 'hello', 'world');
INSERT INTO `user` VALUES ('4', 'linshanting', 'admin');
