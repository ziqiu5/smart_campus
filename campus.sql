/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : zhxy_db

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 19/04/2022 12:03:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `portrait_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 157 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES (101, 'admin', '女', '21232f297a57a5a743894a0e4a801fc3', '111111@qq.com', '13866666666', '昌平', 'upload/default.jpg');
INSERT INTO `tb_admin` VALUES (102, 'admin1', '男', '21232f297a57a5a743894a0e4a801fc3', '333333@qq.com', '13866666666', '北京', 'upload/default.jpg');
INSERT INTO `tb_admin` VALUES (103, 'admin2', '男', '21232f297a57a5a743894a0e4a801fc3', '333333@qq.com', '13866666666', '北京', 'upload/default.jpg');
INSERT INTO `tb_admin` VALUES (104, 'admin3', '男', '21232f297a57a5a743894a0e4a801fc3', '333333@qq.com', '13866666666', '宏福苑', 'upload/default.jpg');

-- ----------------------------
-- Table structure for tb_clazz
-- ----------------------------
DROP TABLE IF EXISTS `tb_clazz`;
CREATE TABLE `tb_clazz`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(3) NULL DEFAULT NULL,
  `introducation` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `headmaster` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_clazz
-- ----------------------------
INSERT INTO `tb_clazz` VALUES (1, '一年一班', 30, '大圣的一年一班好', '大圣', 'dasheng@163.com', '13866666666', '一年级');
INSERT INTO `tb_clazz` VALUES (2, '一年二班', 28, '小张的一年二班好', '小张', 'xiaozhang@163.com', '13866666666', '一年级');
INSERT INTO `tb_clazz` VALUES (3, '二年一班', 35, '小韩的二年一班好', '小韩', 'xiaohan@163.com', '13866666666', '二年级');
INSERT INTO `tb_clazz` VALUES (4, '二年二班', 30, '小强的二年二班好', '小强', 'xiaoqiang@163.com', '13866666666', '二年级');
INSERT INTO `tb_clazz` VALUES (5, '三年一班', 30, '小花的三年一班好', '小花', 'xiaohua@163.com', '13866666666', '三年级');
INSERT INTO `tb_clazz` VALUES (6, '三年二班', 30, '小赵的三年二班好', '小赵', 'xiaozhao@163.com', '13866666666', '三年级');
INSERT INTO `tb_clazz` VALUES (7, '四年一班', 30, '小赵的三年二班好', '小飞', 'xiaofei@163.com', '13866666666', '四年级');

-- ----------------------------
-- Table structure for tb_grade
-- ----------------------------
DROP TABLE IF EXISTS `tb_grade`;
CREATE TABLE `tb_grade`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `manager` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introducation` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_grade
-- ----------------------------
INSERT INTO `tb_grade` VALUES (1, '一年级', '大圣', 'dasheng@163.com', '13866666666', '大学一年级');
INSERT INTO `tb_grade` VALUES (2, '二年级', '小魏', 'xiaowei@163.com', '13866666666', '大学二年级');
INSERT INTO `tb_grade` VALUES (3, '三年级', '小李', 'xiaoli@163.com', '13666666666', '三年级,这个班级的孩子们很有才艺');
INSERT INTO `tb_grade` VALUES (4, '五年级', '小丽', 'li@123.com', '13666666666', '这个年级的同学多才多活力');
INSERT INTO `tb_grade` VALUES (5, '六年级', '小明', 'xiaoming@666.com', '13666666666', '这个年级的主任是小明');

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introducation` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `portrait_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `clazz_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES (1, '1001', '张小明', '男', 'e10adc3949ba59abbe56e057f20f883e', 'yinyufei@163.com', '13866666666', '北京天通苑', '这个学生学习好', 'upload/default.jpg', '一年一班');
INSERT INTO `tb_student` VALUES (2, '1002', '郭建超', '男', 'e10adc3949ba59abbe56e057f20f883e', 'guojianchao@163.com', '13866666666', '北京昌平', '这个学生会功夫', 'upload/default.jpg', '一年一班');
INSERT INTO `tb_student` VALUES (3, '1003', '史汶鑫', '男', 'e10adc3949ba59abbe56e057f20f883e', 'shiwenxin@163.com', '13866666666', '北京昌平', '这个学生酒量好', 'upload/default.jpg', '二年一班');
INSERT INTO `tb_student` VALUES (4, '1004', '高建军', '男', 'e10adc3949ba59abbe56e057f20f883e', 'gaojianjun@163.com', '13866666666', '北京昌平', '这个学生会做饭', 'upload/default.jpg', '二年一班');
INSERT INTO `tb_student` VALUES (5, '1005', '邹伟斌', '男', 'e10adc3949ba59abbe56e057f20f883e', 'zouweibin@163.com', '13866666666', '北京昌平', '这个学生能吃辣', 'upload/default.jpg', '三年一班');
INSERT INTO `tb_student` VALUES (6, '1006', '刘路', '男', 'e10adc3949ba59abbe56e057f20f883e', 'liulu@163.com', '13866666666', '北京昌平', '这个学生是学霸', 'upload/default.jpg', '三年二班');
INSERT INTO `tb_student` VALUES (7, '1007', '庞家仨', '女', 'e10adc3949ba59abbe56e057f20f883e', 'pangjiasan@163.com', '13866666666', '北京昌平', '这个学生海拔高', 'upload/default.jpg', '三年二班');
INSERT INTO `tb_student` VALUES (8, '1008', '谭帅333', '男', 'e10adc3949ba59abbe56e057f20f883e', 'tanshuai@163.com', '13866666666', '北京昌平', '这个学生想考研', 'upload/default.jpg', '四年一班');

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tno` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `portrait_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `clazz_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES (1, '101', '大圣', '女', 'e10adc3949ba59abbe56e057f20f883e', 'dasheng@163.com', '13866666666', '北京昌平', 'upload/default.jpg', '一年一班');
INSERT INTO `tb_teacher` VALUES (2, '102', '小张', '男', 'e10adc3949ba59abbe56e057f20f883e', 'xiaozhang@163.com', '13866666666', '北京海淀', 'upload/default.jpg', '一年二班');
INSERT INTO `tb_teacher` VALUES (3, '103', '小韩', '男', 'e10adc3949ba59abbe56e057f20f883e', 'xiaohan@163.com', '13866666666', '北京朝阳', 'upload/default.jpg', '二年一班');
INSERT INTO `tb_teacher` VALUES (4, '104', '小强', '男', 'e10adc3949ba59abbe56e057f20f883e', 'xiaoqiang@163.com', '13866666666', '北京通州', 'upload/default.jpg', '二年二班');
INSERT INTO `tb_teacher` VALUES (5, '105', '小花', '男', 'e10adc3949ba59abbe56e057f20f883e', 'xiaohua@163.com', '13866666666', '北京顺义', 'upload/default.jpg', '三年一班');
INSERT INTO `tb_teacher` VALUES (6, '106', '小赵', '男', 'e10adc3949ba59abbe56e057f20f883e', 'xiaozhao@163.com', '13866666666', '北京东城', 'upload/default.jpg', '三年二班');
INSERT INTO `tb_teacher` VALUES (7, '107', '小飞', '男', 'e10adc3949ba59abbe56e057f20f883e', 'xiaofei@163.com', '13866666666', '北京西城', 'upload/default.jpg', '四年一班');
INSERT INTO `tb_teacher` VALUES (8, '108', '秀秀2', '男', 'e10adc3949ba59abbe56e057f20f883e', '123456@123.com', '13855555555', '海淀', 'upload/2977694e8da847e2a3e6f0af9416fbba.png', '一年二班');

SET FOREIGN_KEY_CHECKS = 1;
