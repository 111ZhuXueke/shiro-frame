/*
Navicat MySQL Data Transfer

Source Server         : me
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : rj_shiro

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2018-03-26 08:23:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '模块名称',
  `description` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单模块描述',
  `lable` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '标签',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 1:开启 0:关闭',
  `sorting` tinyint(20) DEFAULT NULL COMMENT '排序字段',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统模块表';

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('1', '系统设置', '系统设置', 'system', '1', '1', '2018-03-12 15:03:44', '2018-03-12 15:03:46');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限名称',
  `type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限类型',
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '资源路径',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '资源父ID',
  `permission` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限字符串',
  `available` tinyint(1) DEFAULT '0' COMMENT '是否可用 0:true 1:false',
  `module_id` bigint(20) DEFAULT NULL COMMENT '模块ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `IND_security_permission_module_id` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统权限信息表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '权限管理', 'click', '#', null, 'system:permissions:manager', '0', '1', '2018-03-16 13:09:57', '2018-03-16 13:09:54');
INSERT INTO `permission` VALUES ('2', '权限列表', 'menu', '/permission/index', '1', 'system:permissions:index', '0', '1', '2018-03-19 14:12:53', '2018-03-19 14:12:57');
INSERT INTO `permission` VALUES ('3', '增加权限', 'button', '/permission/add', '2', 'system:permissions:add', '0', '1', '2018-03-20 14:55:47', '2018-03-20 14:55:49');
INSERT INTO `permission` VALUES ('4', '修改权限', 'button', '/permission/update', '2', 'system:permissions:update', '0', '1', '2018-03-20 15:46:45', '2018-03-20 15:46:48');
INSERT INTO `permission` VALUES ('5', '分配权限管理', 'menu', '/permission/distribution', '1', 'system:permissions:distribution', '0', '1', '2018-03-23 11:25:41', '2018-03-23 11:25:41');
INSERT INTO `permission` VALUES ('6', '分配权限', 'button', '/permission/disPermission', '5', 'system:permissions:disPermission', '0', '1', '2018-03-23 15:51:18', '2018-03-23 15:51:18');

-- ----------------------------
-- Table structure for permission_assign
-- ----------------------------
DROP TABLE IF EXISTS `permission_assign`;
CREATE TABLE `permission_assign` (
  `id` tinyint(36) NOT NULL AUTO_INCREMENT COMMENT '主键 权限分配id',
  `permission_id` tinyint(36) DEFAULT NULL,
  `role_id` tinyint(36) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统权限 角色 关联授权表';

-- ----------------------------
-- Records of permission_assign
-- ----------------------------
INSERT INTO `permission_assign` VALUES ('1', '1', '1', '2018-03-16 13:10:12');
INSERT INTO `permission_assign` VALUES ('2', '2', '1', '2018-03-19 16:59:43');
INSERT INTO `permission_assign` VALUES ('3', '3', '1', '2018-03-20 15:46:07');
INSERT INTO `permission_assign` VALUES ('4', '4', '1', '2018-03-20 16:14:54');
INSERT INTO `permission_assign` VALUES ('5', '5', '1', '2018-03-23 13:37:33');
INSERT INTO `permission_assign` VALUES ('6', '6', '1', '2018-03-23 15:51:18');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `description` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色描述',
  `available` int(1) DEFAULT '0' COMMENT '是否可用 0:true 1:false',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '权限', '0', '2018-01-10 17:10:23', '2018-01-10 17:10:23');

-- ----------------------------
-- Table structure for role_assign
-- ----------------------------
DROP TABLE IF EXISTS `role_assign`;
CREATE TABLE `role_assign` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色分配id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `admin_id` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `IXFK_t_security_role_assign_t_security_admin` (`admin_id`),
  KEY `IXFK_t_security_role_assign_t_security_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统管理员角色关联授权表';

-- ----------------------------
-- Records of role_assign
-- ----------------------------
INSERT INTO `role_assign` VALUES ('1', '1', '1', '2018-01-10 17:12:23', '2018-01-10 17:12:23');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `real_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '姓名',
  `user_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '盐',
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机',
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `locked` tinyint(4) DEFAULT '0' COMMENT '是否可用 0:true 1:false',
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '最后登录ip',
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '最后修改信息时间',
  `create_admin` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_admin` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`),
  KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='管理员信息表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '超级管理员', 'admin', '123456', null, '15603998400', '15603998400@163.com', '0', null, '2018-01-10 16:58:23', null, null, '2018-01-10 16:58:23', '2018-01-10 16:58:23', null, null);
