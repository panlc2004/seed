/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : seed

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2017-06-05 18:39:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOGIC_DEL` int(11) NOT NULL DEFAULT '1' COMMENT '逻辑删除状态：1：未删除；2：删除',
  `CODE` varchar(100) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `MEMO` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_dict_item`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `ID` bigint(20) NOT NULL DEFAULT '0',
  `LOGIC_DEL` int(11) NOT NULL DEFAULT '1' COMMENT '逻辑删除状态：1：未删除；2：删除',
  `PARENT_ID` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级ID，默认值为0，表示无父级数据',
  `SYS_DICT_CODE` varchar(100) NOT NULL,
  `ITEM_CODE` varchar(100) NOT NULL,
  `VALUE` varchar(300) NOT NULL,
  `MEMO` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('1', '0', 'CZY', '春之翼', null);
INSERT INTO `sys_org` VALUES ('2', '1', 'depart1', '研发一部', null);
INSERT INTO `sys_org` VALUES ('3', '1', '123123', '1231231', '23123123');
INSERT INTO `sys_org` VALUES ('10', '3', '99', '99', '99');

-- ----------------------------
-- Table structure for `sys_param`
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(300) NOT NULL COMMENT '配置项编码',
  `NAME` varchar(50) NOT NULL COMMENT '配置项名称',
  `VALUE` varchar(500) NOT NULL COMMENT '配置项值',
  `ACTIVE` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否激活：1：激活；0：不激活',
  `MEMO` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_param
-- ----------------------------
INSERT INTO `sys_param` VALUES ('1', 'default_password', '用户默认密码', '000000', '0', '管理员新建用户账号，系统生成的默认密码');
INSERT INTO `sys_param` VALUES ('2', 'session_time_out', '登陆超时时间', '30', '1', '用户登陆系统连续无任何操作时，登陆失效的时间');
INSERT INTO `sys_param` VALUES ('3', '3斯塔奔斯塔奔', '1阿斯蒂芬基材阿斯蒂芬阿斯蒂芬 ', '阿斯蒂芬阿斯蒂芬苛', '1', '基材基材日勘查土；韩茜ldfja;lksdjf;lakdsjf;奈斯载');
INSERT INTO `sys_param` VALUES ('4', '4', '1', '1', '1', '基材基材日勘查土；韩茜ldfja;lksdjf;lakdsjf;奈斯载');
INSERT INTO `sys_param` VALUES ('5', '5', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('6', '6', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('7', '7', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('8', '8', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('9', '9', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('10', '10', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('11', '11', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('12', '12', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('13', '13', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('14', '14', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('15', '15', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('16', '16', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('17', '17', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('18', '18', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('19', '19', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('20', '20', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('21', '21', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('22', '22', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('23', '23', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('24', '24', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('25', '25', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('26', '26', '1', '1', '1', null);
INSERT INTO `sys_param` VALUES ('27', '88', '88', '88', '1', '88');
INSERT INTO `sys_param` VALUES ('28', '2312312', '31231231', '1231', '1', '基材基材日勘查土；韩茜ldfja;lksdjf;lakdsjf;奈斯载');

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '0', '1', 'sys', '系统管理', '');
INSERT INTO `sys_resource` VALUES ('2', '1', '1', 'sys_org', '组织机构管理', 'sys/org/index');
INSERT INTO `sys_resource` VALUES ('3', '1', '1', 'sys_param', '系统参数设置', 'sys/param/index');
INSERT INTO `sys_resource` VALUES ('4', '1', '1', 'sys_resource', '菜单管理', 'sys/resource/index');
INSERT INTO `sys_resource` VALUES ('5', '0', '1', '1234', '1234', '1234');
INSERT INTO `sys_resource` VALUES ('6', '0', '1', '1234', '1234', '1234');
INSERT INTO `sys_resource` VALUES ('7', '0', '1', '1234', '1234', '1234');
INSERT INTO `sys_resource` VALUES ('8', '0', '1', '456', '456', '456');
INSERT INTO `sys_resource` VALUES ('9', '0', '1', '476', '4567', '4567');
INSERT INTO `sys_resource` VALUES ('10', '9', '1', '2345', '45', '234523');
INSERT INTO `sys_resource` VALUES ('11', '10', '1', '12341', '12341234', '23412341');
INSERT INTO `sys_resource` VALUES ('12', '11', '1', '23452', '2345', '34523452');
INSERT INTO `sys_resource` VALUES ('13', '0', '1', '88', '88', '88');
INSERT INTO `sys_resource` VALUES ('14', '0', '1', '99', '99', '9');

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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'SEEDER', 'admin', 'admin', 'admin@czy.inner.com', '1', '1', null, null);
INSERT INTO `sys_user` VALUES ('2', '1123456', '12346666', '1', '1123123', '1', '1', null, null);
INSERT INTO `sys_user` VALUES ('10', '11', '11', '11', '11123123', '1', '1', null, null);
INSERT INTO `sys_user` VALUES ('13', '11', '11', '11', '11', '1', '1', null, null);
INSERT INTO `sys_user` VALUES ('20', '17', '17', '17', '17', '1', '1', null, null);
INSERT INTO `sys_user` VALUES ('21', '18', '18', '18', '18', '1', '1', null, null);
INSERT INTO `sys_user` VALUES ('22', '19', '19', '19', '19', '1', '1', null, null);
INSERT INTO `sys_user` VALUES ('23', '21', '21', '21', '21', '1', '1', null, null);
INSERT INTO `sys_user` VALUES ('24', '22', '22', '22', '22', '1', '1', null, null);
INSERT INTO `sys_user` VALUES ('27', '55', '55', '55', '55', '0', '0', null, null);
INSERT INTO `sys_user` VALUES ('28', '66', '66', '66', '66', '0', '0', null, null);
INSERT INTO `sys_user` VALUES ('29', '77', '77', '77', '77', '0', '0', null, null);
INSERT INTO `sys_user` VALUES ('30', '88', '88', '88', '88', '0', '0', null, null);

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
