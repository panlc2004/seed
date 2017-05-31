/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : seed

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2017-05-31 19:01:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_org`
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` bigint(20) NOT NULL DEFAULT '0' COMMENT '为0时表示最高级组织机构',
  `CODE` varchar(40) NOT NULL,
  `NAME` varchar(60) NOT NULL,
  `MEMO` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('1', '0', 'CZY', '春之翼', null);
INSERT INTO `sys_org` VALUES ('2', '1', 'depart1', '研发一部', null);
INSERT INTO `sys_org` VALUES ('3', '1', '123123', '1231231', '23123123');
INSERT INTO `sys_org` VALUES ('4', '1', '123123', '1231231', '23123123');
INSERT INTO `sys_org` VALUES ('5', '0', '1231', '2312', '3123123');
INSERT INTO `sys_org` VALUES ('6', '1', '52452345', '24524', '52435234');
INSERT INTO `sys_org` VALUES ('7', '2', '325', '252', '4352345');
INSERT INTO `sys_org` VALUES ('8', '7', '12341', '3241234', '13241234');
INSERT INTO `sys_org` VALUES ('9', '8', '745', '746574657', '4567467');
INSERT INTO `sys_org` VALUES ('10', '3', '99', '99', '99');
INSERT INTO `sys_org` VALUES ('11', '5', '123', '123', '123');
INSERT INTO `sys_org` VALUES ('12', '5', '123', '1231234', '23123');
INSERT INTO `sys_org` VALUES ('13', '5', '78', '78', '78');
INSERT INTO `sys_org` VALUES ('14', '6', '1341', '34132', '4134134');
INSERT INTO `sys_org` VALUES ('15', '4', '789', '789', '789');
INSERT INTO `sys_org` VALUES ('16', '5', 'qq', 'qq', 'qq');
INSERT INTO `sys_org` VALUES ('17', '0', '123456', '1231567', '123');

-- ----------------------------
-- Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` bigint(20) NOT NULL COMMENT '父级资源id',
  `TYPES` int(11) NOT NULL DEFAULT '1' COMMENT '资源类型：1.菜单、2.目录',
  `CODE` varchar(30) NOT NULL COMMENT '资源编码',
  `NAME` varchar(50) NOT NULL COMMENT '资源名称',
  `URL` varchar(50) NOT NULL COMMENT '资源路径',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '0', '1', 'sys', '系统管理', '');
INSERT INTO `sys_resource` VALUES ('2', '1', '1', 'sys_resource', '组织机构管理', 'sys/org/index');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(30) DEFAULT NULL COMMENT '角色编码',
  `NAME` varchar(50) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '系统管理员');

-- ----------------------------
-- Table structure for `sys_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `ID` bigint(20) NOT NULL DEFAULT '0',
  `SYS_ROLE_ID` bigint(20) NOT NULL,
  `SYS_RESOURCE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `ENABLE` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账号是否可用',
  `NON_LOCKED` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账号是否锁定',
  `CREDENTIALS_EXPIRED_TIME` datetime DEFAULT NULL COMMENT '密码过期时间',
  `ACCOUNT_EXPIRED_TIME` datetime DEFAULT NULL COMMENT '账号过期时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'SEEDER', 'admin', 'admin', 'admin@czy.inner.com', '1', '1', null, null);

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `ID` bigint(20) NOT NULL DEFAULT '0',
  `SYS_USER_ID` bigint(20) NOT NULL,
  `SYS_ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
