/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : seed

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2017-11-06 18:11:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `seed_sys_account`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_account`;
CREATE TABLE `seed_sys_account` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_BY` bigint(20) NOT NULL,
  `CREATE_DT` datetime NOT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL,
  `UPDATE_DT` datetime DEFAULT NULL,
  `SYS_ORG_ID` bigint(20) NOT NULL COMMENT '组织机构ID',
  `SYS_USER_ID` bigint(20) NOT NULL COMMENT '关联用户信息',
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `NON_LOCKED` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账号是否锁定',
  `CREDENTIALS_EXPIRED_TIME` datetime DEFAULT NULL COMMENT '密码过期时间',
  `ACCOUNT_EXPIRED_TIME` datetime DEFAULT NULL COMMENT '账号过期时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_account
-- ----------------------------
INSERT INTO `seed_sys_account` VALUES ('1', '0', '0000-00-00 00:00:00', null, null, '1', '1', 'suadmin', 'admin', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('2', '0', '0000-00-00 00:00:00', null, null, '0', '0', '12346666', '1', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('10', '0', '0000-00-00 00:00:00', null, null, '0', '0', '11', '11', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('13', '0', '0000-00-00 00:00:00', null, null, '0', '0', '11', '11', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('20', '0', '0000-00-00 00:00:00', null, null, '0', '0', '17', '17', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('21', '0', '0000-00-00 00:00:00', null, null, '0', '0', '18', '18', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('22', '0', '0000-00-00 00:00:00', null, null, '0', '0', '19', '19', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('23', '0', '0000-00-00 00:00:00', null, null, '0', '0', '21', '21', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('24', '0', '0000-00-00 00:00:00', null, null, '0', '0', '22', '22', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('27', '0', '0000-00-00 00:00:00', null, null, '0', '0', '55', '55', '0', null, null);
INSERT INTO `seed_sys_account` VALUES ('28', '0', '0000-00-00 00:00:00', null, null, '0', '0', '66', '66', '0', null, null);
INSERT INTO `seed_sys_account` VALUES ('29', '0', '0000-00-00 00:00:00', null, null, '0', '0', '77', '77', '0', null, null);
INSERT INTO `seed_sys_account` VALUES ('30', '0', '0000-00-00 00:00:00', null, null, '0', '0', '88', '88', '0', null, null);
INSERT INTO `seed_sys_account` VALUES ('31', '0', '0000-00-00 00:00:00', null, null, '0', '0', '1324', '1234', '0', null, null);

-- ----------------------------
-- Table structure for `seed_sys_attachment`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_attachment`;
CREATE TABLE `seed_sys_attachment` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_BY` bigint(20) NOT NULL,
  `CREATE_DT` datetime NOT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL,
  `UPDATE_DT` datetime DEFAULT NULL,
  `SYS_ORG_ID` bigint(20) NOT NULL COMMENT '组织机构ID',
  `SYS_DEPT_ID` bigint(20) NOT NULL COMMENT '部门id',
  `IS_TEMP` tinyint(4) NOT NULL DEFAULT '1' COMMENT '附件是否为临时附件：1.临时附件;2.正式附件',
  `TABLE_NAME` varchar(100) DEFAULT NULL COMMENT '附件业务数据表名称',
  `TABLE_ID` bigint(20) DEFAULT NULL COMMENT '附件业务数据id',
  `SAVE_PATH` varchar(300) NOT NULL COMMENT '附件保存路径',
  `SAVE_NAME` varchar(300) NOT NULL COMMENT '附件保存名称',
  `SHOW_NAME` varchar(300) NOT NULL COMMENT '附件展示名称',
  `FILE_SUFFIX` varchar(50) NOT NULL COMMENT '附件后缀',
  `BUSINESS_CODE` varchar(50) NOT NULL DEFAULT 'DEFAULT' COMMENT '附件业务类型',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for `seed_sys_data_rule`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_data_rule`;
CREATE TABLE `seed_sys_data_rule` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_BY` bigint(20) NOT NULL,
  `CREATE_DT` datetime NOT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL,
  `UPDATE_DT` datetime DEFAULT NULL,
  `RULE_NAME` varchar(300) NOT NULL COMMENT '规则名称',
  `RULE_OWNER_TYPE` tinyint(4) NOT NULL COMMENT '规则类型：1.针对个人的数据访问规则。2.针对部门的数据访问规则。3.针对组织的数据访问规则。4.针对角色的数据访问规则',
  `RULE_OWNER` varchar(300) NOT NULL COMMENT '规则适用对象（为用户/组织/部门的id或角色编码）,多个对象间用","分隔',
  `RULE_CONTENT_TYPE` tinyint(4) DEFAULT NULL COMMENT '规则类型：1.针对个人的数据访问规则。2.针对部门的数据访问规则。3.针对组织的数据访问规则。4.针对角色的数据访问规则',
  `RULE_CONTENT` varchar(300) NOT NULL COMMENT '规则内容:值可能为用户/组织/部门的id,多个对象间用","分隔',
  `DESCRIPTION` varchar(1000) NOT NULL COMMENT '规则描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_data_rule
-- ----------------------------

-- ----------------------------
-- Table structure for `seed_sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_dept`;
CREATE TABLE `seed_sys_dept` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_BY` bigint(20) NOT NULL,
  `CREATE_DT` datetime NOT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL,
  `UPDATE_DT` datetime DEFAULT NULL,
  `DEPTH` tinyint(4) NOT NULL COMMENT '级别深度，如父级节点，深度为0，其f子节点，深度则为1',
  `PARENT_ID` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级部门id，为0时表示最高级部门',
  `SYS_ORG_ID` bigint(20) NOT NULL COMMENT '组织机构ID',
  `CODE` varchar(40) NOT NULL,
  `NAME` varchar(60) NOT NULL,
  `MEMO` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_dept
-- ----------------------------
INSERT INTO `seed_sys_dept` VALUES ('1', '0', '2017-09-10 16:30:18', null, null, '0', '0', '1', 'CZY', '春之翼', null);
INSERT INTO `seed_sys_dept` VALUES ('2', '0', '2017-09-10 16:30:21', null, null, '1', '1', '1', 'depart_fir', '研发一部', null);
INSERT INTO `seed_sys_dept` VALUES ('3', '0', '2017-09-10 16:30:24', null, null, '1', '1', '1', 'depart_sec', '研发二部', '');
INSERT INTO `seed_sys_dept` VALUES ('4', '1', '2017-09-10 18:38:33', null, null, '2', '10', '1', 'test', 'test', 'test');
INSERT INTO `seed_sys_dept` VALUES ('10', '0', '2017-09-10 16:30:27', null, null, '1', '1', '1', '99', '99', '99');

-- ----------------------------
-- Table structure for `seed_sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_dict`;
CREATE TABLE `seed_sys_dict` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_BY` bigint(20) NOT NULL,
  `CREATE_DT` datetime NOT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL COMMENT '更新人',
  `UPDATE_DT` datetime DEFAULT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '1' COMMENT '逻辑删除状态：1.未删除；2.删除',
  `SYS_ORG_ID` bigint(20) NOT NULL,
  `LOGIC_DEL` int(11) NOT NULL DEFAULT '1' COMMENT '逻辑删除状态：1：未删除；2：删除',
  `CODE` varchar(100) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `MEMO` varchar(2000) DEFAULT NULL,
  `PARENT_ID` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级字典项ID,0表示没有父级字典项',
  `DEPTH` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_dict
-- ----------------------------
INSERT INTO `seed_sys_dict` VALUES ('3', '0', '2017-06-13 13:34:06', '1', '2017-06-23 14:25:25', '11', '0', '1', 'test', 'test', 'test123123', '0', null);
INSERT INTO `seed_sys_dict` VALUES ('4', '1', '2017-06-13 16:02:29', '1', '2017-06-14 13:32:05', '9', '0', '1', 'sex', '性别', '354365', '0', null);

-- ----------------------------
-- Table structure for `seed_sys_dict_item`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_dict_item`;
CREATE TABLE `seed_sys_dict_item` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_BY` bigint(20) NOT NULL,
  `CREATE_DT` datetime NOT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL,
  `UPDATE_DT` datetime DEFAULT NULL,
  `VERSION` int(11) NOT NULL DEFAULT '1',
  `SYS_DICT_ID` bigint(20) NOT NULL,
  `LOGIC_DEL` int(11) NOT NULL DEFAULT '1' COMMENT '逻辑删除状态：1：未删除；2：删除',
  `PARENT_ID` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级数据值项ID，默认值为0，表示无父级数据',
  `ITEM_CODE` varchar(100) NOT NULL,
  `VALUE` varchar(300) NOT NULL,
  `MEMO` varchar(2000) DEFAULT NULL,
  `NAME` varchar(500) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_dict_item
-- ----------------------------
INSERT INTO `seed_sys_dict_item` VALUES ('3', '1', '2017-06-13 18:53:41', '1', '2017-06-14 13:34:25', '2', '0', '1', '0', '1234', '1324', '1341', '');
INSERT INTO `seed_sys_dict_item` VALUES ('10', '1', '2017-06-13 19:08:01', '1', '2017-06-14 13:32:16', '3', '0', '1', '0', '1341', '123412341234', '23412341234', '');
INSERT INTO `seed_sys_dict_item` VALUES ('13', '1', '2017-06-14 13:34:34', null, null, '1', '0', '1', '0', '1234', '1234', '1234', '');

-- ----------------------------
-- Table structure for `seed_sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_log`;
CREATE TABLE `seed_sys_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_BY` bigint(20) NOT NULL,
  `CREATE_DT` datetime NOT NULL,
  `SYS_ORG_ID` bigint(20) NOT NULL COMMENT '组织机构ID',
  `SYS_DEPT_ID` bigint(20) NOT NULL COMMENT '部门id',
  `OPERATION` varchar(500) NOT NULL COMMENT '操作内容',
  `METHOD` varchar(200) NOT NULL COMMENT '调用方法',
  `PARAMS` varchar(5000) NOT NULL COMMENT '调用参数',
  `IP` varchar(30) NOT NULL COMMENT '用户IP',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_log
-- ----------------------------
INSERT INTO `seed_sys_log` VALUES ('2', '0', '0000-00-00 00:00:00', '0', '0', '保存', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497341701000,\"version\":2}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('3', '0', '0000-00-00 00:00:00', '0', '0', '保存', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497353746000,\"version\":3}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('4', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('5', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('6', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('7', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('8', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('9', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('10', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('11', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('12', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('13', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('14', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"sex\",\"createBy\":1,\"createDt\":1497340949000,\"id\":4,\"logicDel\":1,\"memo\":\"354365\",\"name\":\"性别\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497405526000,\"version\":8}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('15', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"sex\",\"createBy\":1,\"createDt\":1497340949000,\"id\":4,\"logicDel\":1,\"memo\":\"354365\",\"name\":\"性别\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497405526000,\"version\":8}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('16', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"sex\",\"createBy\":1,\"createDt\":1497340949000,\"id\":4,\"logicDel\":1,\"memo\":\"354365\",\"name\":\"性别\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497405526000,\"version\":8}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('17', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497353864000,\"version\":4}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('18', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"134\",\"memo\":\"134\",\"name\":\"1234\"}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('19', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497418328000,\"version\":5}', '10.131.11.53');
INSERT INTO `seed_sys_log` VALUES ('20', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497429270000,\"version\":6}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('21', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497590064000,\"version\":7}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('22', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1498117677000,\"version\":8}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('23', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1498117682000,\"version\":9}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('24', '0', '0000-00-00 00:00:00', '0', '0', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1498118060000,\"version\":10}', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for `seed_sys_org`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_org`;
CREATE TABLE `seed_sys_org` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_BY` bigint(20) NOT NULL,
  `CREATE_DT` datetime NOT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL,
  `UPDATE_DT` datetime DEFAULT NULL,
  `ORG_CODE` varchar(200) NOT NULL,
  `ORG_NAME` varchar(200) NOT NULL,
  `MEMO` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_org
-- ----------------------------
INSERT INTO `seed_sys_org` VALUES ('1', '1', '2017-08-16 17:22:01', '1', '2017-09-07 22:07:21', 'SU_ORG', '超级管理员组', '1212122');
INSERT INTO `seed_sys_org` VALUES ('2', '1', '2017-09-02 21:43:54', null, null, 'test', 'test', 'test');
INSERT INTO `seed_sys_org` VALUES ('3', '1', '2017-09-25 13:09:58', null, null, 'test2', 'test2', 'test2');

-- ----------------------------
-- Table structure for `seed_sys_param`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_param`;
CREATE TABLE `seed_sys_param` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(300) NOT NULL COMMENT '配置项编码',
  `NAME` varchar(50) NOT NULL COMMENT '配置项名称',
  `VALUE` varchar(500) NOT NULL COMMENT '配置项值',
  `ACTIVE` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否激活：1：激活；0：不激活',
  `MEMO` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_param
-- ----------------------------
INSERT INTO `seed_sys_param` VALUES ('1', 'default_password', '用户默认密码', '000000', '0', '管理员新建用户账号，系统生成的默认密码');
INSERT INTO `seed_sys_param` VALUES ('2', 'session_time_out', '登陆超时时间', '30', '1', '用户登陆系统连续无任何操作时，登陆失效的时间');
INSERT INTO `seed_sys_param` VALUES ('3', '3斯塔奔斯塔奔', '1阿斯蒂芬基材阿斯蒂芬阿斯蒂芬 ', '阿斯蒂芬阿斯蒂芬苛', '1', '基材基材日勘查土；韩茜ldfja;lksdjf;lakdsjf;奈斯载');
INSERT INTO `seed_sys_param` VALUES ('5', '51234', '1134', '11234', '1', null);
INSERT INTO `seed_sys_param` VALUES ('6', '6', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('7', '7', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('8', '8', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('9', '9', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('10', '10', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('15', '15', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('16', '16', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('17', '17', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('18', '18', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('19', '19', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('20', '20', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('21', '21', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('22', '22', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('23', '23', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('24', '24', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('25', '25', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('26', '26', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('27', '88', '88', '88', '1', '88');
INSERT INTO `seed_sys_param` VALUES ('28', '2312312', '31231231', '1231', '1', '基材基材日勘查土；韩茜ldfja;lksdjf;lakdsjf;奈斯载');

-- ----------------------------
-- Table structure for `seed_sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_resource`;
CREATE TABLE `seed_sys_resource` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SYS_ORG_ID` bigint(20) NOT NULL COMMENT '组织ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '父级资源id',
  `CREATE_BY` bigint(20) NOT NULL COMMENT '数据创建人',
  `CREATE_DT` datetime NOT NULL COMMENT '数据创建日期',
  `UPDATE_BY` bigint(20) DEFAULT NULL COMMENT '数据修改人',
  `UPDATE_DT` datetime DEFAULT NULL COMMENT '数据修改日期',
  `TYPES` int(11) NOT NULL DEFAULT '1' COMMENT '资源类型：1.菜单、2.目录、3.按钮',
  `CODE` varchar(30) NOT NULL COMMENT '资源编码',
  `NAME` varchar(50) NOT NULL COMMENT '资源名称',
  `URL` varchar(50) NOT NULL COMMENT '资源路径',
  `ORDER_BY` int(11) NOT NULL DEFAULT '999' COMMENT '排序号',
  `DEPTH` int(11) DEFAULT NULL,
  `ICON` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_resource
-- ----------------------------
INSERT INTO `seed_sys_resource` VALUES ('1', '0', '0', '0', '2017-09-10 16:28:51', null, null, '2', 'sys', '系统管理', '', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('2', '0', '1', '0', '2017-09-10 16:28:55', null, null, '1', 'sys_org', '机构管理', 'sys/org/org-index.html', '999', null, 'el-icon-star-off');
INSERT INTO `seed_sys_resource` VALUES ('3', '0', '1', '0', '2017-09-10 16:28:57', null, null, '1', 'sys_param', '系统参数设置', 'sys/param/param-index.html', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('4', '0', '1', '0', '2017-09-10 16:28:59', null, null, '1', 'sys_resource', '菜单管理', 'sys/resource/resource-index.html', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('5', '0', '0', '0', '2017-09-10 16:29:02', null, null, '2', 'process', '流程管理', '', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('6', '0', '1', '1', '2017-09-11 21:41:12', null, null, '1', 'sys_user', '用户管理', 'sys/user/user-index.html', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('9', '0', '0', '0', '2017-09-10 16:29:05', null, null, '2', '476', '4567', '4567', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('10', '0', '9', '0', '2017-09-10 16:29:07', null, null, '2', '2345', '45', '', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('11', '0', '10', '0', '2017-09-10 16:29:10', null, null, '2', '12341', '12341234', '', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('15', '0', '1', '0', '2017-09-10 16:29:13', null, null, '1', 'sys_role', '角色管理', 'sys/role/role-index.html', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('16', '0', '5', '0', '2017-09-10 16:29:15', null, null, '1', 'deploy', '流程发布', 'process/deploy/index', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('17', '0', '1', '0', '2017-09-10 16:29:17', null, null, '1', 'sys_dict', '数据字典', 'sys/dict/dict-index.html', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('18', '0', '1', '0', '2017-09-10 16:29:21', null, null, '1', 'sys_attachment', '组织部门管理', 'sys/dept/dept-index.html', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('19', '0', '11', '0', '2017-09-10 16:29:23', null, null, '1', '2345', '2345', '23452', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('20', '0', '10', '0', '2017-09-10 16:29:26', null, null, '2', '134188', '13488', '234188', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('23', '0', '20', '0', '2017-09-10 16:29:28', null, null, '1', '412341234', '134132', '12341234', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('24', '0', '20', '0', '2017-09-10 16:29:31', null, null, '1', '2341324', '12341', '1234134', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('25', '0', '20', '0', '2017-09-10 16:29:33', null, null, '1', '13241234', '134', '1324', '999', null, null);

-- ----------------------------
-- Table structure for `seed_sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_role`;
CREATE TABLE `seed_sys_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_BY` bigint(20) NOT NULL,
  `CREATE_DT` datetime NOT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL,
  `UPDATE_DT` datetime DEFAULT NULL,
  `SYS_ORG_ID` bigint(20) NOT NULL COMMENT '组织机构id',
  `SYS_DEPT_ID` bigint(20) NOT NULL COMMENT '部门id',
  `CODE` varchar(200) NOT NULL COMMENT '角色编码',
  `NAME` varchar(100) NOT NULL COMMENT '角色名称',
  `MEMO` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_role
-- ----------------------------
INSERT INTO `seed_sys_role` VALUES ('1', '0', '2017-09-29 14:05:12', null, null, '0', '0', 'admin', '系统管理员', '系统管理员');
INSERT INTO `seed_sys_role` VALUES ('2', '0', '2017-09-29 14:05:16', null, null, '0', '0', '134', '1341234234234', '123123');
INSERT INTO `seed_sys_role` VALUES ('3', '0', '2017-09-29 14:05:19', null, null, '0', '0', '3123', '12234234', '123');
INSERT INTO `seed_sys_role` VALUES ('4', '0', '2017-09-29 14:05:22', null, null, '0', '0', '123', '123345', '123');
INSERT INTO `seed_sys_role` VALUES ('8', '0', '2017-09-29 14:05:24', null, null, '0', '0', 'tyuiasdfasdf', 'tui', 'tui');
INSERT INTO `seed_sys_role` VALUES ('9', '0', '2017-09-29 14:05:27', null, null, '0', '0', 'fh', 'fhj', 'jfhjfhj');
INSERT INTO `seed_sys_role` VALUES ('11', '0', '2017-09-29 14:05:30', null, null, '0', '0', 'adf', '12341', 'adf');

-- ----------------------------
-- Table structure for `seed_sys_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_role_resource`;
CREATE TABLE `seed_sys_role_resource` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SYS_ROLE_ID` bigint(20) NOT NULL,
  `SYS_RESOURCE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_role_resource
-- ----------------------------
INSERT INTO `seed_sys_role_resource` VALUES ('1', '1', '6');
INSERT INTO `seed_sys_role_resource` VALUES ('42', '2', '3');
INSERT INTO `seed_sys_role_resource` VALUES ('43', '2', '4');
INSERT INTO `seed_sys_role_resource` VALUES ('44', '2', '5');
INSERT INTO `seed_sys_role_resource` VALUES ('45', '2', '15');
INSERT INTO `seed_sys_role_resource` VALUES ('52', '13', '3');
INSERT INTO `seed_sys_role_resource` VALUES ('56', '11', '9');
INSERT INTO `seed_sys_role_resource` VALUES ('57', '11', '10');
INSERT INTO `seed_sys_role_resource` VALUES ('58', '11', '11');
INSERT INTO `seed_sys_role_resource` VALUES ('59', '11', '19');
INSERT INTO `seed_sys_role_resource` VALUES ('60', '11', '20');
INSERT INTO `seed_sys_role_resource` VALUES ('97', '4', '9');
INSERT INTO `seed_sys_role_resource` VALUES ('98', '4', '10');
INSERT INTO `seed_sys_role_resource` VALUES ('99', '4', '11');
INSERT INTO `seed_sys_role_resource` VALUES ('100', '4', '19');
INSERT INTO `seed_sys_role_resource` VALUES ('101', '4', '20');
INSERT INTO `seed_sys_role_resource` VALUES ('104', '8', '5');
INSERT INTO `seed_sys_role_resource` VALUES ('105', '8', '16');
INSERT INTO `seed_sys_role_resource` VALUES ('180', '3', '5');
INSERT INTO `seed_sys_role_resource` VALUES ('181', '3', '9');
INSERT INTO `seed_sys_role_resource` VALUES ('182', '3', '10');
INSERT INTO `seed_sys_role_resource` VALUES ('183', '3', '11');
INSERT INTO `seed_sys_role_resource` VALUES ('184', '3', '16');
INSERT INTO `seed_sys_role_resource` VALUES ('185', '3', '19');
INSERT INTO `seed_sys_role_resource` VALUES ('186', '3', '20');
INSERT INTO `seed_sys_role_resource` VALUES ('187', '3', '23');
INSERT INTO `seed_sys_role_resource` VALUES ('188', '3', '24');
INSERT INTO `seed_sys_role_resource` VALUES ('205', '1', '1');
INSERT INTO `seed_sys_role_resource` VALUES ('206', '1', '2');
INSERT INTO `seed_sys_role_resource` VALUES ('207', '1', '3');
INSERT INTO `seed_sys_role_resource` VALUES ('208', '1', '4');
INSERT INTO `seed_sys_role_resource` VALUES ('209', '1', '5');
INSERT INTO `seed_sys_role_resource` VALUES ('210', '1', '15');
INSERT INTO `seed_sys_role_resource` VALUES ('211', '1', '16');
INSERT INTO `seed_sys_role_resource` VALUES ('212', '1', '17');
INSERT INTO `seed_sys_role_resource` VALUES ('213', '1', '18');

-- ----------------------------
-- Table structure for `seed_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_user`;
CREATE TABLE `seed_sys_user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_BY` bigint(20) NOT NULL,
  `CREATE_DT` datetime NOT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL,
  `UPDATE_DT` datetime DEFAULT NULL,
  `SYS_ORG_ID` bigint(20) NOT NULL COMMENT '组织机构id',
  `SYS_DEPT_ID` bigint(20) NOT NULL COMMENT '部门id',
  `NAME` varchar(200) NOT NULL,
  `USERNAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(200) NOT NULL,
  `SEX` tinyint(1) NOT NULL COMMENT '性别',
  `TELEPHONE` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `NON_LOCKED` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账号是否锁定；1:未锁定; 2:锁定',
  `CREDENTIALS_EXPIRED_TIME` datetime DEFAULT NULL COMMENT '密码过期时间',
  `ACCOUNT_EXPIRED_TIME` datetime DEFAULT NULL COMMENT '账号过期时间',
  `ENABLED` tinyint(1) DEFAULT NULL COMMENT '账号是否可用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_user
-- ----------------------------
INSERT INTO `seed_sys_user` VALUES ('1', '1', '2017-09-11 23:07:33', '1', '2017-09-12 22:49:40', '1', '1', '超级管理员', 'suadmin', '$2a$10$6FsiWKQJ7TUmZD9o9tHxve3QqDOvY87FdHVIg49VbtCeIJbbHcR5i', '4', '123145', 'test@seed.com', '0', null, null, '0');
INSERT INTO `seed_sys_user` VALUES ('2', '1', '2017-09-11 22:40:41', '1', '2017-09-12 22:49:48', '1', '1', '123', '123456123123', '$2a$10$6FsiWKQJ7TUmZD9o9tHxve3QqDOvY87FdHVIg49VbtCeIJbbHcR5i', '123', '13412342341', '123', '0', null, null, '0');
INSERT INTO `seed_sys_user` VALUES ('3', '1', '2017-09-11 22:41:11', '1', '2017-09-12 22:36:01', '1', '1', '35', '345', 'd705499792ca451640ad824cbdd3abf5', '1', '1324123414', '345', '0', null, null, '0');
INSERT INTO `seed_sys_user` VALUES ('5', '1', '2017-09-16 23:57:45', '1', '2017-09-17 22:38:54', '1', '2', 'test', 'test123', '$2a$10$Bq7iicFHywA49X6TdzZ7ae5oX2sJby9ArcO3fhRzEQ7OL.A7EcVCa', '1', '113', 'test@seec.com', '0', null, null, '0');
INSERT INTO `seed_sys_user` VALUES ('6', '1', '2017-09-16 23:57:45', null, null, '1', '2', 'test', 'test123', '$2a$10$M9vQA/otCGEc4hFBAMk41eMBsFykYYXjLiL8m1f34bWZZDlMv97pu', '1', '11', 'test@seec.com', '0', null, null, '0');
INSERT INTO `seed_sys_user` VALUES ('7', '1', '2017-09-16 23:57:45', null, null, '1', '2', 'test', 'test123', '$2a$10$fcOeDMyg.bEcOdRHBL/f6.FSUsA6QKcMitFiVOYY3Bcl//xlOlwcC', '1', '11', 'test@seec.com', '0', null, null, '0');
INSERT INTO `seed_sys_user` VALUES ('8', '1', '2017-09-16 23:58:10', null, null, '1', '3', 'test2', 'test12345', '$2a$10$3aLjtyItYpNbtSniazLf1ehWMr13Ec.Bj0IIb7.HO7d9QAl0q.jYW', '2', '11', 'test@t.com', '0', null, null, '0');
INSERT INTO `seed_sys_user` VALUES ('9', '1', '2017-09-16 23:58:10', null, null, '1', '3', 'test2', 'test12345', '$2a$10$lqRvECR/TaU1wEEhjph2GOB.mqcVY6CT9m87QZMzZrvXvgqnV/6AS', '2', '11', 'test@t.com', '0', null, null, '0');
INSERT INTO `seed_sys_user` VALUES ('10', '1', '2017-09-16 23:58:10', null, null, '1', '3', 'test2', 'test12345', '$2a$10$DcfhcAgQ0kYxQDYx7Hase.ogJ2AXyvdiJPtWFM/CNPOoWuVe1Rq7y', '2', '11', 'test@t.com', '0', null, null, '0');

-- ----------------------------
-- Table structure for `seed_sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_user_role`;
CREATE TABLE `seed_sys_user_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_BY` bigint(20) NOT NULL,
  `CREATE_DT` datetime NOT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL,
  `UPDATE_DT` datetime DEFAULT NULL,
  `SYS_ORG_ID` bigint(20) NOT NULL COMMENT '组织机构id',
  `SYS_DEPT_ID` bigint(20) NOT NULL COMMENT '部门id',
  `SYS_USER_ID` bigint(20) NOT NULL COMMENT '用户id',
  `SYS_ROLE_ID` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_user_role
-- ----------------------------
INSERT INTO `seed_sys_user_role` VALUES ('6', '0', '2017-09-17 21:11:41', null, null, '0', '0', '22', '2');
INSERT INTO `seed_sys_user_role` VALUES ('7', '0', '2017-09-17 21:11:44', null, null, '0', '0', '10', '1');
INSERT INTO `seed_sys_user_role` VALUES ('8', '0', '2017-09-17 21:11:41', null, null, '0', '0', '10', '3');
INSERT INTO `seed_sys_user_role` VALUES ('15', '0', '2017-09-17 21:11:41', null, null, '0', '0', '13', '1');
INSERT INTO `seed_sys_user_role` VALUES ('16', '0', '2017-09-17 21:11:41', null, null, '0', '0', '13', '2');
INSERT INTO `seed_sys_user_role` VALUES ('17', '0', '2017-09-17 21:11:41', null, null, '0', '0', '2', '1');
INSERT INTO `seed_sys_user_role` VALUES ('18', '0', '2017-09-17 21:11:41', null, null, '0', '0', '2', '2');
INSERT INTO `seed_sys_user_role` VALUES ('19', '0', '2017-09-17 21:11:41', null, null, '0', '0', '21', '1');
INSERT INTO `seed_sys_user_role` VALUES ('20', '0', '2017-09-17 21:11:41', null, null, '0', '0', '21', '2');
INSERT INTO `seed_sys_user_role` VALUES ('21', '0', '2017-09-17 21:11:41', null, null, '0', '0', '24', '4');
INSERT INTO `seed_sys_user_role` VALUES ('22', '0', '2017-09-17 21:11:41', null, null, '0', '0', '24', '8');
INSERT INTO `seed_sys_user_role` VALUES ('23', '0', '2017-09-17 21:11:41', null, null, '0', '0', '24', '9');
INSERT INTO `seed_sys_user_role` VALUES ('24', '0', '2017-09-17 21:11:41', null, null, '0', '0', '23', '3');
INSERT INTO `seed_sys_user_role` VALUES ('25', '0', '2017-09-17 21:11:41', null, null, '0', '0', '23', '4');
INSERT INTO `seed_sys_user_role` VALUES ('29', '0', '2017-09-17 21:11:41', null, null, '0', '0', '1', '1');
