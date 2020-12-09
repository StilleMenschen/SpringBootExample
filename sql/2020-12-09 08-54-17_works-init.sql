/*
SQLyog Trial v13.1.7 (64 bit)
MySQL - 10.5.3-MariaDB : Database - works
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`works` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `works`;

/*Table structure for table `role_authority_relationship` */

DROP TABLE IF EXISTS `role_authority_relationship`;

CREATE TABLE `role_authority_relationship` (
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色ID',
  `auth_id` smallint(6) unsigned DEFAULT NULL COMMENT '权限ID',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `sys_authority` */

DROP TABLE IF EXISTS `sys_authority`;

CREATE TABLE `sys_authority` (
  `auth_id` smallint(6) unsigned NOT NULL COMMENT '权限ID',
  `auth_name` varchar(32) DEFAULT NULL COMMENT '权限名称',
  `description` varchar(64) DEFAULT NULL COMMENT '权限描述',
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `log_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `operator` bigint(20) unsigned DEFAULT NULL COMMENT '操作者',
  `module` varchar(64) DEFAULT NULL COMMENT '操作模块',
  `features` int(10) unsigned DEFAULT NULL COMMENT '操作功能',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `sys_organization` */

DROP TABLE IF EXISTS `sys_organization`;

CREATE TABLE `sys_organization` (
  `org_id` bigint(20) unsigned NOT NULL COMMENT '机构ID',
  `top_id` bigint(20) unsigned DEFAULT 0 COMMENT '顶级机构ID',
  `parent_id` bigint(20) unsigned DEFAULT 0 COMMENT '父级机构ID',
  `org_level` tinyint(4) unsigned DEFAULT NULL COMMENT '层级(0为顶级,子级从1开始,最大5级)',
  `org_name` varchar(32) DEFAULT NULL COMMENT '机构名称',
  `enabled` tinyint(1) unsigned DEFAULT 1 COMMENT '状态(1-启用,0-禁用)',
  `deleted` tinyint(1) unsigned DEFAULT 0 COMMENT '是否删除(1-删除,0-未删除)',
  `updater` bigint(20) unsigned DEFAULT NULL,
  `creator` bigint(20) unsigned DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `org_id` bigint(20) unsigned DEFAULT NULL COMMENT '机构ID',
  `top_id` bigint(20) unsigned DEFAULT NULL COMMENT '顶级机构ID',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `deleted` tinyint(1) unsigned DEFAULT 0 COMMENT '是否删除(1-删除,0-未删除)',
  `updater` bigint(20) unsigned DEFAULT NULL,
  `creator` bigint(20) unsigned DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `org_id` bigint(20) unsigned DEFAULT NULL COMMENT '机构ID',
  `top_id` bigint(20) unsigned DEFAULT NULL COMMENT '顶级机构ID',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名称',
  `user_cipher` varchar(64) DEFAULT NULL COMMENT '密码',
  `role_id` bigint(20) unsigned DEFAULT NULL COMMENT '角色ID',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `telephone_number` varchar(16) DEFAULT NULL COMMENT '联系方式(电话,手机)',
  `user_type` tinyint(4) unsigned DEFAULT NULL COMMENT '用户类型(0-超级管理员,1-机构管理员,2-普通用户)',
  `enabled` tinyint(1) unsigned DEFAULT 1 COMMENT '状态(1-启用,0-禁用)',
  `deleted` tinyint(1) unsigned DEFAULT 0 COMMENT '是否删除(1-删除,0-未删除)',
  `password_reset_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '密码最后更新时间',
  `updater` bigint(20) unsigned DEFAULT NULL,
  `creator` bigint(20) unsigned DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `work_log` */

DROP TABLE IF EXISTS `work_log`;

CREATE TABLE `work_log` (
  `log_id` bigint(20) unsigned NOT NULL COMMENT '日志ID',
  `org_id` bigint(20) unsigned DEFAULT NULL COMMENT '机构ID',
  `top_id` bigint(20) unsigned DEFAULT NULL COMMENT '顶级机构ID',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户ID',
  `project_id` bigint(20) unsigned DEFAULT NULL COMMENT '项目ID',
  `work_time` float unsigned DEFAULT NULL COMMENT '工作时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `log_time` datetime DEFAULT NULL COMMENT '日志时间',
  `updater` bigint(20) unsigned DEFAULT NULL,
  `creator` bigint(20) unsigned DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `work_next_plan` */

DROP TABLE IF EXISTS `work_next_plan`;

CREATE TABLE `work_next_plan` (
  `plan_id` bigint(20) unsigned NOT NULL COMMENT '计划ID',
  `org_id` bigint(20) unsigned DEFAULT NULL COMMENT '机构ID',
  `top_id` bigint(20) unsigned DEFAULT NULL COMMENT '顶级机构ID',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户ID',
  `plan_text_id` bigint(20) unsigned DEFAULT NULL COMMENT '计划内容ID',
  `updater` bigint(20) unsigned DEFAULT NULL,
  `creator` bigint(20) unsigned DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `work_plan_text` */

DROP TABLE IF EXISTS `work_plan_text`;

CREATE TABLE `work_plan_text` (
  `plan_text_id` bigint(20) unsigned NOT NULL COMMENT '计划内容ID',
  `org_id` bigint(20) unsigned DEFAULT NULL COMMENT '机构ID',
  `top_id` bigint(20) unsigned DEFAULT NULL COMMENT '顶级机构ID',
  `plan` varchar(64) DEFAULT NULL COMMENT '明天计划',
  `total` smallint(6) unsigned DEFAULT 1 COMMENT '激活次数',
  `updater` bigint(20) unsigned DEFAULT NULL,
  `creator` bigint(20) unsigned DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`plan_text_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `work_project` */

DROP TABLE IF EXISTS `work_project`;

CREATE TABLE `work_project` (
  `project_id` bigint(20) unsigned NOT NULL COMMENT '项目ID',
  `org_id` bigint(20) unsigned DEFAULT NULL COMMENT '机构ID',
  `top_id` bigint(20) unsigned DEFAULT NULL COMMENT '顶级机构ID',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户ID',
  `project_name` varchar(64) DEFAULT NULL COMMENT '项目名称',
  `project_time` datetime DEFAULT NULL COMMENT '项目开始时间',
  `updater` bigint(20) unsigned DEFAULT NULL,
  `creator` bigint(20) unsigned DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `create_time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
