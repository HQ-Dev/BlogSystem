作业要求
本地配置好MySQL数据库，并且创建博客系统所需要的数据库表，
将SQL脚本放入代码仓库中。

###############################################

1. 创建存储博客系统的数据库
CREATE DATABASE `blogSystem` DEFAULT CHARACTER SET utf8 COLLATE
utf8_general_ci;  
// COLLATE utf8_general_ci 表示数据库校对规则，区分大小写。

2. 使用博客系统数据库
USE `blogSystem`;

3. 创建 user 表 
CREATE TABLE `user` (
	`userId` int(11) NOT NULL AUTO_INCREMENT,
	`userName` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	`avatar` varchar(255) NOT NULL,
	`email` varchar(255) NOT NULL,
	`description` longtext NOT NULL,
	PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

4. 创建 post 表
CREATE TABLE `post` (
	`postId` int(11) NOT NULL AUTO_INCREMENT,
	`title` varchar(255) NOT NULL,
	`content` longtext NOT NULL,
	`creator` int(11) DEFAULT NULL,
	`createdDate` datetime NOT NULL,
	PRIMARY KEY (`postId`),
	KEY `creator` (`creator`),
	CONSTRAINT `post_ibfk_1` FOREIGN KEY (`creator`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

5. 修改 user 表，增加用户注册时间
ALTER TABLE `user` ADD COLUMN createdDate datetime DEFAULT NULL;