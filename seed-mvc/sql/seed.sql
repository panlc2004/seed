/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : seed

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2017-08-11 19:46:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `seed_sys_account`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_account`;
CREATE TABLE `seed_sys_account` (
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_account
-- ----------------------------
INSERT INTO `seed_sys_account` VALUES ('1', 'SEEDER', 'admin', 'admin', 'admin@czy.inner.com', '1', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('2', '1123456', '12346666', '1', '1123123', '1', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('10', '11`12123123444', '11', '11', '11123123', '1', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('13', '11123123', '11', '11', '11', '1', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('20', '17', '17', '17', '17', '1', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('21', '18', '18', '18', '18', '1', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('22', '19', '19', '19', '19', '1', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('23', '21123123', '21', '21', '21', '1', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('24', '22123123', '22', '22', '22', '1', '1', null, null);
INSERT INTO `seed_sys_account` VALUES ('27', '55', '55', '55', '55', '0', '0', null, null);
INSERT INTO `seed_sys_account` VALUES ('28', '66', '66', '66', '66', '0', '0', null, null);
INSERT INTO `seed_sys_account` VALUES ('29', '77', '77', '77', '77', '0', '0', null, null);
INSERT INTO `seed_sys_account` VALUES ('30', '88', '88', '88', '88', '0', '0', null, null);
INSERT INTO `seed_sys_account` VALUES ('31', '1324', '1324', '1234', '1234', '0', '0', null, null);

-- ----------------------------
-- Table structure for `seed_sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_dept`;
CREATE TABLE `seed_sys_dept` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARENT_ID` bigint(20) NOT NULL DEFAULT '0' COMMENT '为0时表示最高级组织机构',
  `CODE` varchar(40) NOT NULL,
  `NAME` varchar(60) NOT NULL,
  `MEMO` varchar(1000) DEFAULT NULL,
  `ORG_ID` bigint(20) NOT NULL COMMENT '组织ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_dept
-- ----------------------------
INSERT INTO `seed_sys_dept` VALUES ('1', '0', 'CZY', '春之翼', null, '0');
INSERT INTO `seed_sys_dept` VALUES ('2', '1', 'depart_fir', '研发一部', null, '0');
INSERT INTO `seed_sys_dept` VALUES ('3', '1', 'depart_sec', '研发二部', '', '0');
INSERT INTO `seed_sys_dept` VALUES ('10', '3', '99', '99', '99', '0');

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
  `LOGIC_DEL` int(11) NOT NULL DEFAULT '1' COMMENT '逻辑删除状态：1：未删除；2：删除',
  `CODE` varchar(100) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `MEMO` varchar(2000) DEFAULT NULL,
  `PARENT_ID` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级字典项ID,0表示没有父级字典项',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_dict
-- ----------------------------
INSERT INTO `seed_sys_dict` VALUES ('3', '0', '2017-06-13 13:34:06', '1', '2017-06-23 14:25:25', '11', '1', 'test', 'test', 'test123123', '0');
INSERT INTO `seed_sys_dict` VALUES ('4', '1', '2017-06-13 16:02:29', '1', '2017-06-14 13:32:05', '9', '1', 'sex', '性别', '354365', '0');

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
  `LOGIC_DEL` int(11) NOT NULL DEFAULT '1' COMMENT '逻辑删除状态：1：未删除；2：删除',
  `PARENT_ID` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级数据值项ID，默认值为0，表示无父级数据',
  `SYS_DICT_CODE` varchar(100) NOT NULL,
  `ITEM_CODE` varchar(100) NOT NULL,
  `VALUE` varchar(300) NOT NULL,
  `MEMO` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_dict_item
-- ----------------------------
INSERT INTO `seed_sys_dict_item` VALUES ('3', '1', '2017-06-13 18:53:41', '1', '2017-06-14 13:34:25', '2', '1', '0', 'test', '1234', '1324', '1341');
INSERT INTO `seed_sys_dict_item` VALUES ('10', '1', '2017-06-13 19:08:01', '1', '2017-06-14 13:32:16', '3', '1', '0', 'sex', '1341', '123412341234', '23412341234');
INSERT INTO `seed_sys_dict_item` VALUES ('13', '1', '2017-06-14 13:34:34', null, null, '1', '1', '0', 'test', '1234', '1234', '1234');

-- ----------------------------
-- Table structure for `seed_sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_log`;
CREATE TABLE `seed_sys_log` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `OPE_ID` bigint(20) NOT NULL COMMENT '操作人',
  `OPE_TIME` datetime NOT NULL COMMENT '操作时间',
  `OPERATION` varchar(500) NOT NULL COMMENT '操作内容',
  `METHOD` varchar(200) NOT NULL COMMENT '调用方法',
  `PARAMS` varchar(5000) NOT NULL COMMENT '调用参数',
  `IP` varchar(30) NOT NULL COMMENT '用户IP',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_log
-- ----------------------------
INSERT INTO `seed_sys_log` VALUES ('2', '1', '2017-06-13 19:35:38', '保存', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497341701000,\"version\":2}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('3', '1', '2017-06-13 19:36:59', '保存', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497353746000,\"version\":3}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('4', '1', '2017-06-14 13:24:59', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('5', '1', '2017-06-14 13:25:09', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('6', '1', '2017-06-14 13:25:32', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('7', '1', '2017-06-14 13:25:41', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('8', '1', '2017-06-14 13:26:26', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('9', '1', '2017-06-14 13:26:50', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('10', '1', '2017-06-14 13:28:33', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('11', '1', '2017-06-14 13:28:53', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('12', '1', '2017-06-14 13:30:28', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('13', '1', '2017-06-14 13:30:37', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('14', '1', '2017-06-14 13:31:23', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"sex\",\"createBy\":1,\"createDt\":1497340949000,\"id\":4,\"logicDel\":1,\"memo\":\"354365\",\"name\":\"性别\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497405526000,\"version\":8}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('15', '1', '2017-06-14 13:31:41', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"sex\",\"createBy\":1,\"createDt\":1497340949000,\"id\":4,\"logicDel\":1,\"memo\":\"354365\",\"name\":\"性别\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497405526000,\"version\":8}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('16', '1', '2017-06-14 13:32:05', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"sex\",\"createBy\":1,\"createDt\":1497340949000,\"id\":4,\"logicDel\":1,\"memo\":\"354365\",\"name\":\"性别\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497405526000,\"version\":8}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('17', '1', '2017-06-14 13:32:08', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497353864000,\"version\":4}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('18', '1', '2017-06-14 13:35:20', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"134\",\"memo\":\"134\",\"name\":\"1234\"}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('19', '1', '2017-06-14 16:34:30', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497418328000,\"version\":5}', '10.131.11.53');
INSERT INTO `seed_sys_log` VALUES ('20', '1', '2017-06-16 13:14:24', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497429270000,\"version\":6}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('21', '1', '2017-06-22 15:47:57', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1497590064000,\"version\":7}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('22', '1', '2017-06-22 15:48:02', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1498117677000,\"version\":8}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('23', '1', '2017-06-22 15:54:20', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1498117682000,\"version\":9}', '0:0:0:0:0:0:0:1');
INSERT INTO `seed_sys_log` VALUES ('24', '1', '2017-06-23 14:25:25', '新增/修改数据字典项', 'com.czy.seed.mvc.sys.controller.SysDictController.save()', '{\"code\":\"test\",\"createBy\":0,\"createDt\":1497332046000,\"id\":3,\"logicDel\":1,\"memo\":\"test123123\",\"name\":\"test\",\"parentId\":0,\"updateBy\":1,\"updateDt\":1498118060000,\"version\":10}', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for `seed_sys_org`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_org`;
CREATE TABLE `seed_sys_org` (
  `ID` bigint(20) NOT NULL,
  `CREATED_BY` bigint(20) NOT NULL,
  `CREATED_DT` datetime NOT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL,
  `UPDATE_DT` datetime DEFAULT NULL,
  `ORG_CODE` varchar(200) NOT NULL,
  `ORG_NAME` varchar(200) NOT NULL,
  `MEMO` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_org
-- ----------------------------

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
INSERT INTO `seed_sys_param` VALUES ('4', '4', '113412341234134', '1', '1', '基材基材日勘查土；韩茜ldfja;lksdjf;lakdsjf;奈斯载');
INSERT INTO `seed_sys_param` VALUES ('5', '5', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('6', '6', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('7', '7', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('8', '8', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('9', '9', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('10', '10', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('11', '11', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('12', '12', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('13', '13', '1', '1', '1', null);
INSERT INTO `seed_sys_param` VALUES ('14', '14', '1', '1', '1', null);
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
  `ORG_ID` bigint(20) NOT NULL COMMENT '组织ID',
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
  `ICON` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_resource
-- ----------------------------
INSERT INTO `seed_sys_resource` VALUES ('1', '0', '0', '0', '0000-00-00 00:00:00', null, null, '2', 'sys', '系统管理', '', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('2', '0', '1', '0', '0000-00-00 00:00:00', null, null, '1', 'sys_org', '机构用户管理', 'sys/org-index', '999', 'el-icon-star-off');
INSERT INTO `seed_sys_resource` VALUES ('3', '0', '1', '0', '0000-00-00 00:00:00', null, null, '1', 'sys_param', '系统参数设置', 'sys/param/index', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('4', '0', '1', '0', '0000-00-00 00:00:00', null, null, '1', 'sys_resource', '菜单管理', 'sys/resource/index', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('5', '0', '0', '0', '0000-00-00 00:00:00', null, null, '2', 'process', '流程管理', '', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('9', '0', '0', '0', '0000-00-00 00:00:00', null, null, '2', '476', '4567', '4567', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('10', '0', '9', '0', '0000-00-00 00:00:00', null, null, '2', '2345', '45', '', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('11', '0', '10', '0', '0000-00-00 00:00:00', null, null, '2', '12341', '12341234', '', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('15', '0', '1', '0', '0000-00-00 00:00:00', null, null, '1', 'sys_role', '角色管理', 'sys/role/index', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('16', '0', '5', '0', '0000-00-00 00:00:00', null, null, '1', 'deploy', '流程发布', 'process/deploy/index', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('17', '0', '1', '0', '0000-00-00 00:00:00', null, null, '1', 'sys_dict', '数据字典', 'sys/dict/index', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('18', '0', '1', '0', '0000-00-00 00:00:00', null, null, '1', 'sys_attachment', '附件管理', 'sys/attachment/index', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('19', '0', '11', '0', '0000-00-00 00:00:00', null, null, '1', '2345', '2345', '23452', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('20', '0', '10', '0', '0000-00-00 00:00:00', null, null, '2', '134188', '13488', '234188', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('23', '0', '20', '0', '0000-00-00 00:00:00', null, null, '1', '412341234', '134132', '12341234', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('24', '0', '20', '0', '0000-00-00 00:00:00', null, null, '1', '2341324', '12341', '1234134', '999', null);
INSERT INTO `seed_sys_resource` VALUES ('25', '0', '20', '0', '0000-00-00 00:00:00', null, null, '1', '13241234', '134', '1324', '999', null);

-- ----------------------------
-- Table structure for `seed_sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_role`;
CREATE TABLE `seed_sys_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(200) DEFAULT NULL COMMENT '角色编码',
  `NAME` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `MEMO` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_role
-- ----------------------------
INSERT INTO `seed_sys_role` VALUES ('1', 'admin', '系统管理员', '系统管理员');
INSERT INTO `seed_sys_role` VALUES ('2', '134', '1341234234234', '123123');
INSERT INTO `seed_sys_role` VALUES ('3', '3123', '12234234', '123');
INSERT INTO `seed_sys_role` VALUES ('4', '123', '123345', '123');
INSERT INTO `seed_sys_role` VALUES ('8', 'tyuiasdfasdf', 'tui', 'tui');
INSERT INTO `seed_sys_role` VALUES ('9', 'fh', 'fhj', 'jfhjfhj');
INSERT INTO `seed_sys_role` VALUES ('11', 'adf', '12341', 'adf');
INSERT INTO `seed_sys_role` VALUES ('12', '356', '3456', '3456');
INSERT INTO `seed_sys_role` VALUES ('14', 'fgs', 'kjukj', 'dfgsfg');
INSERT INTO `seed_sys_role` VALUES ('15', 'jkl;jlk;', '[pou;l', ';jl;');
INSERT INTO `seed_sys_role` VALUES ('17', '6yhn', '7uhjn', '6yhb');
INSERT INTO `seed_sys_role` VALUES ('19', '2q3wsx', '3ed', '123qwsax');
INSERT INTO `seed_sys_role` VALUES ('20', '1qwaszx', '3eds', '23wsx');
INSERT INTO `seed_sys_role` VALUES ('21', '34esdc', '34erdf', '4ed');
INSERT INTO `seed_sys_role` VALUES ('22', '7ythgn', '6yuhj', '56tygh');
INSERT INTO `seed_sys_role` VALUES ('23', '23wesd', '23we', '23wed');
INSERT INTO `seed_sys_role` VALUES ('24', '134', '1234', '134');
INSERT INTO `seed_sys_role` VALUES ('25', '1234', '1324', '1234');
INSERT INTO `seed_sys_role` VALUES ('26', '1234', '124', '1234');
INSERT INTO `seed_sys_role` VALUES ('27', null, null, null);
INSERT INTO `seed_sys_role` VALUES ('28', null, null, null);

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
  `ID` bigint(20) DEFAULT NULL,
  `CREATE_BY` bigint(20) DEFAULT NULL,
  `CREATE_DT` datetime DEFAULT NULL,
  `UPDATE_BY` bigint(20) DEFAULT NULL,
  `UPDATE_DT` datetime DEFAULT NULL,
  `NAME` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_user
-- ----------------------------

-- ----------------------------
-- Table structure for `seed_sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_user_role`;
CREATE TABLE `seed_sys_user_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SYS_USER_ID` bigint(20) NOT NULL,
  `SYS_ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_user_role
-- ----------------------------
INSERT INTO `seed_sys_user_role` VALUES ('6', '22', '2');
INSERT INTO `seed_sys_user_role` VALUES ('7', '10', '1');
INSERT INTO `seed_sys_user_role` VALUES ('8', '10', '3');
INSERT INTO `seed_sys_user_role` VALUES ('15', '13', '1');
INSERT INTO `seed_sys_user_role` VALUES ('16', '13', '2');
INSERT INTO `seed_sys_user_role` VALUES ('17', '2', '1');
INSERT INTO `seed_sys_user_role` VALUES ('18', '2', '2');
INSERT INTO `seed_sys_user_role` VALUES ('19', '21', '1');
INSERT INTO `seed_sys_user_role` VALUES ('20', '21', '2');
INSERT INTO `seed_sys_user_role` VALUES ('21', '24', '4');
INSERT INTO `seed_sys_user_role` VALUES ('22', '24', '8');
INSERT INTO `seed_sys_user_role` VALUES ('23', '24', '9');
INSERT INTO `seed_sys_user_role` VALUES ('24', '23', '3');
INSERT INTO `seed_sys_user_role` VALUES ('25', '23', '4');
INSERT INTO `seed_sys_user_role` VALUES ('29', '1', '1');
