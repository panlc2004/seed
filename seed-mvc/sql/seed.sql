/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : seed

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2017-12-04 19:03:59
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_dept
-- ----------------------------
INSERT INTO `seed_sys_dept` VALUES ('1', '0', '2017-09-10 16:30:18', '1', '2017-11-14 11:20:20', '0', '0', '1', 'CZY', '春之翼', '13241234');
INSERT INTO `seed_sys_dept` VALUES ('2', '0', '2017-09-10 16:30:21', '1', '2017-11-14 11:41:06', '1', '1', '1', 'depart_fir', '研发一部1', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_dict
-- ----------------------------
INSERT INTO `seed_sys_dict` VALUES ('4', '1', '2017-06-13 16:02:29', '1', '2017-06-14 13:32:05', '9', '0', '1', 'sex', '性别', '354365', '0', null);
INSERT INTO `seed_sys_dict` VALUES ('5', '1', '2017-12-04 15:16:43', null, null, '1', '1', '1', '1234', '123', '41234', '0', '0');
INSERT INTO `seed_sys_dict` VALUES ('7', '1', '2017-12-04 15:23:23', null, null, '1', '1', '1', '134', '234', '234', '5', '1');

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
  `VALUE` varchar(300) NOT NULL COMMENT '字典项值',
  `NAME` varchar(100) NOT NULL COMMENT '字典项名称',
  `MEMO` varchar(2000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_dict_item
-- ----------------------------
INSERT INTO `seed_sys_dict_item` VALUES ('3', '1', '2017-06-13 18:53:41', '1', '2017-06-14 13:34:25', '2', '0', '1', '0', '1324', '', '1341');
INSERT INTO `seed_sys_dict_item` VALUES ('10', '1', '2017-06-13 19:08:01', '1', '2017-06-14 13:32:16', '3', '0', '1', '0', '123412341234', '', '23412341234');
INSERT INTO `seed_sys_dict_item` VALUES ('13', '1', '2017-06-14 13:34:34', null, null, '1', '0', '1', '0', '1234', '', '1234');
INSERT INTO `seed_sys_dict_item` VALUES ('14', '1', '2017-12-04 15:04:45', '1', '2017-12-04 15:16:21', '1', '4', '1', '0', '12341', '1234', '123');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_org
-- ----------------------------
INSERT INTO `seed_sys_org` VALUES ('1', '1', '2017-08-16 17:22:01', '1', '2017-09-07 22:07:21', 'SU_ORG', '超级管理员组', '超级管理员组');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_param
-- ----------------------------
INSERT INTO `seed_sys_param` VALUES ('1', 'default_password', '用户默认密码', '000000', '1', '管理员新建用户账号，系统生成的默认密码');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_resource
-- ----------------------------
INSERT INTO `seed_sys_resource` VALUES ('1', '0', '0', '0', '2017-09-10 16:28:51', null, null, '2', 'sys', '系统管理', '', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('2', '0', '1', '0', '2017-09-10 16:28:55', null, null, '1', 'sys_org', '机构管理', 'sys/org/org-index.html', '999', null, 'el-icon-star-off');
INSERT INTO `seed_sys_resource` VALUES ('3', '0', '1', '0', '2017-09-10 16:28:57', null, null, '1', 'sys_param', '系统参数设置', 'sys/param/param-index.html', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('4', '0', '1', '0', '2017-09-10 16:28:59', null, null, '1', 'sys_resource', '菜单管理', 'sys/resource/resource-index.html', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('6', '0', '1', '1', '2017-09-11 21:41:12', null, null, '1', 'sys_user', '用户管理', 'sys/user/user-index.html', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('7', '0', '1', '0', '2017-09-10 16:29:13', null, null, '1', 'sys_role', '角色管理', 'sys/role/role-index.html', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('8', '0', '1', '0', '2017-09-10 16:29:17', null, null, '1', 'sys_dict', '数据字典', 'sys/dict/dict-index.html', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('9', '0', '1', '0', '2017-09-10 16:29:21', null, null, '1', 'sys_attachment', '组织部门管理', 'sys/dept/dept-index.html', '999', null, null);
INSERT INTO `seed_sys_resource` VALUES ('10', '1', '6', '1', '2017-11-20 12:02:22', null, null, '3', 'addTest', '新增', 'a', '999', '1', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_role
-- ----------------------------
INSERT INTO `seed_sys_role` VALUES ('1', '0', '2017-09-29 14:05:12', '1', '2017-11-14 11:07:47', '0', '0', 'seed_admin', '超级系统管理员', '超级系统管理员');

-- ----------------------------
-- Table structure for `seed_sys_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_role_resource`;
CREATE TABLE `seed_sys_role_resource` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SYS_ROLE_ID` bigint(20) NOT NULL,
  `SYS_RESOURCE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=222 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_role_resource
-- ----------------------------
INSERT INTO `seed_sys_role_resource` VALUES ('214', '1', '1');
INSERT INTO `seed_sys_role_resource` VALUES ('215', '1', '2');
INSERT INTO `seed_sys_role_resource` VALUES ('216', '1', '3');
INSERT INTO `seed_sys_role_resource` VALUES ('217', '1', '4');
INSERT INTO `seed_sys_role_resource` VALUES ('218', '1', '6');
INSERT INTO `seed_sys_role_resource` VALUES ('219', '1', '7');
INSERT INTO `seed_sys_role_resource` VALUES ('220', '1', '8');
INSERT INTO `seed_sys_role_resource` VALUES ('221', '1', '9');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_user
-- ----------------------------
INSERT INTO `seed_sys_user` VALUES ('1', '1', '2017-09-11 23:07:33', '1', '2017-09-12 22:49:40', '1', '1', '超级管理员', 'seedadmin', '$2a$10$6FsiWKQJ7TUmZD9o9tHxve3QqDOvY87FdHVIg49VbtCeIJbbHcR5i', '1', null, 'admin@seed.com', '0', null, null, '0');

-- ----------------------------
-- Table structure for `seed_sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `seed_sys_user_role`;
CREATE TABLE `seed_sys_user_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATE_BY` bigint(20) NOT NULL,
  `CREATE_DT` datetime NOT NULL,
  `SYS_ORG_ID` bigint(20) NOT NULL COMMENT '组织机构id',
  `SYS_USER_ID` bigint(20) NOT NULL COMMENT '用户id',
  `SYS_ROLE_ID` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seed_sys_user_role
-- ----------------------------
INSERT INTO `seed_sys_user_role` VALUES ('37', '1', '2017-12-04 14:34:42', '1', '1', '1');
