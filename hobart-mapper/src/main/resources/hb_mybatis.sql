/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : hb_mybatis

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2019-05-12 19:56:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hb_user
-- ----------------------------
DROP TABLE IF EXISTS `hb_user`;
CREATE TABLE `hb_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of hb_user
-- ----------------------------
INSERT INTO `hb_user` VALUES ('1', 'tom', '23', 'tom@qq.com', 'shanghai');
INSERT INTO `hb_user` VALUES ('2', '83102', '26', null, null);
INSERT INTO `hb_user` VALUES ('3', 'f7c13', '26', null, null);
INSERT INTO `hb_user` VALUES ('5', '18093', '26', null, null);

-- ----------------------------
-- Table structure for t_hb_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_hb_employee`;
CREATE TABLE `t_hb_employee` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `gender` char(1) CHARACTER SET latin1 DEFAULT NULL,
  `email` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_hb_employee
-- ----------------------------
INSERT INTO `t_hb_employee` VALUES ('1', 'tom', '21', '1', '10086@qq.com', '3000.00', '2019-04-28 00:38:02');
INSERT INTO `t_hb_employee` VALUES ('2', 'marry', '23', '0', '110@qq.com', '3500.00', '2019-05-12 14:44:54');
INSERT INTO `t_hb_employee` VALUES ('3', 'jack', '26', '1', '110@qq.com', '4000.00', '2019-05-12 19:52:58');

--树形结构与关系数据库之闭包表:

--闭包表记录了树中所有节点的关系，不仅仅只是直接父子关系，它需要使用2张表，除了节点表本身之外，还需要使用1张表来存储节祖先点和后代节点之间的关系（同时增加一行节点指向自身）,
--并且根据需要，可以增加一个字段，表示深度。因此这种方法数据量很多。设计的表结构如下：
-- root(1)
--     |
--     a(2)
--     |___a1(3)
--     |___a2(4)
--     b(5)
--     |___b1(6)
--     |___b2(7)
--     |___c(8)
--         |___c1(9)
--         |___d(10)
--             |___e(11)

-- ----------------------------
-- Table structure for t_hb_node
-- ----------------------------
DROP TABLE IF EXISTS `t_hb_node`;
CREATE TABLE `t_hb_node` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_hb_node
-- ----------------------------
INSERT INTO `t_hb_node` VALUES ('1', 'root');
INSERT INTO `t_hb_node` VALUES ('2', 'a');
INSERT INTO `t_hb_node` VALUES ('3', 'a1');
INSERT INTO `t_hb_node` VALUES ('4', 'a2');
INSERT INTO `t_hb_node` VALUES ('5', 'b');
INSERT INTO `t_hb_node` VALUES ('6', 'b1');
INSERT INTO `t_hb_node` VALUES ('7', 'b2');
INSERT INTO `t_hb_node` VALUES ('8', 'c');
INSERT INTO `t_hb_node` VALUES ('9', 'c1');
INSERT INTO `t_hb_node` VALUES ('10', 'd');
INSERT INTO `t_hb_node` VALUES ('11', 'e');

-- ----------------------------
-- Table structure for t_hb_tree_path
-- ----------------------------
DROP TABLE IF EXISTS `t_hb_tree_path`;
CREATE TABLE `t_hb_tree_path` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `anc` int(6) NOT NULL COMMENT '祖先节点',
  `des` int(6) NOT NULL COMMENT '子孙节点',
  `pc` int(1) DEFAULT NULL COMMENT '是否为父子节点 1/0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_hb_tree_path
-- ----------------------------
INSERT INTO `t_hb_tree_path` VALUES ('1', '1', '2', '1');
INSERT INTO `t_hb_tree_path` VALUES ('2', '1', '3', '0');
INSERT INTO `t_hb_tree_path` VALUES ('3', '1', '4', '0');
INSERT INTO `t_hb_tree_path` VALUES ('4', '1', '5', '1');
INSERT INTO `t_hb_tree_path` VALUES ('5', '1', '6', '0');
INSERT INTO `t_hb_tree_path` VALUES ('6', '1', '7', '0');
INSERT INTO `t_hb_tree_path` VALUES ('7', '1', '8', '0');
INSERT INTO `t_hb_tree_path` VALUES ('8', '1', '9', '0');
INSERT INTO `t_hb_tree_path` VALUES ('9', '1', '10', '0');
INSERT INTO `t_hb_tree_path` VALUES ('10', '1', '11', '0');
INSERT INTO `t_hb_tree_path` VALUES ('11', '2', '2', '0');
INSERT INTO `t_hb_tree_path` VALUES ('12', '3', '3', '0');
INSERT INTO `t_hb_tree_path` VALUES ('13', '4', '4', '0');
INSERT INTO `t_hb_tree_path` VALUES ('14', '5', '5', '0');
INSERT INTO `t_hb_tree_path` VALUES ('15', '6', '6', '0');
INSERT INTO `t_hb_tree_path` VALUES ('16', '7', '7', '0');
INSERT INTO `t_hb_tree_path` VALUES ('17', '8', '8', '0');
INSERT INTO `t_hb_tree_path` VALUES ('18', '9', '9', '0');
INSERT INTO `t_hb_tree_path` VALUES ('19', '10', '10', '0');
INSERT INTO `t_hb_tree_path` VALUES ('20', '11', '11', '0');
INSERT INTO `t_hb_tree_path` VALUES ('21', '2', '3', '1');
INSERT INTO `t_hb_tree_path` VALUES ('22', '2', '4', '1');
INSERT INTO `t_hb_tree_path` VALUES ('23', '5', '6', '1');
INSERT INTO `t_hb_tree_path` VALUES ('24', '5', '7', '1');
INSERT INTO `t_hb_tree_path` VALUES ('25', '5', '8', '1');
INSERT INTO `t_hb_tree_path` VALUES ('26', '5', '9', '0');
INSERT INTO `t_hb_tree_path` VALUES ('27', '5', '10', '0');
INSERT INTO `t_hb_tree_path` VALUES ('28', '5', '11', '0');
INSERT INTO `t_hb_tree_path` VALUES ('29', '8', '9', '1');
INSERT INTO `t_hb_tree_path` VALUES ('30', '8', '10', '1');
INSERT INTO `t_hb_tree_path` VALUES ('31', '8', '11', '0');
INSERT INTO `t_hb_tree_path` VALUES ('32', '10', '11', '1');


--删除子树
--假设要删除子树#8
--
--delete from tree_path
--where tree_path.des in (select t.des from tree_path t where t.anc=8)
--
--移动子树
--假设我们要把子树#8从节点#5移动到节点#2
--1.分离子树,删除子树节点与其祖先的关系
--delete from tree_path where tree_path.anc=8
--
--2.将上一步分离出的子树用笛卡尔积嫁接到#2下
--select
--    super.anc,
--    sub.des,
--    case
--        when
--            super.anc=2 and sub.des=8 then 1
--        else 0
--    end pc
--from
--    tree_path super
--    cross join
--    tree_path sub
--where
--    super.des=2 and sub.anc=8
--    
--查询树形结构
--xxxxxx