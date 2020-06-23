/*
 Navicat Premium Data Transfer

 Source Server         : MySQL(密码123)
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : cloud_music

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 23/06/2020 17:30:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `Authority` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (1, 'admin');
INSERT INTO `authority` VALUES (2, 'vip');
INSERT INTO `authority` VALUES (3, 'common');

-- ----------------------------
-- Table structure for code
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code`  (
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expire_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of code
-- ----------------------------
INSERT INTO `code` VALUES ('admin', '224812', '2020-06-18 14:45:50');
INSERT INTO `code` VALUES ('user003', '357844', '2020-06-09 21:13:42');

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `artist` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '艺术家',
  `album` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专辑',
  `duration` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '时长',
  `vip` int(255) NULL DEFAULT 0 COMMENT 'vip专属',
  `hot` int(255) NULL DEFAULT 0,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music
-- ----------------------------
INSERT INTO `music` VALUES (7, '小半', '陈粒', '小梦大半', '00:04:57', 0, 0, '陈粒 - 小半.mp3');
INSERT INTO `music` VALUES (8, 'Sternengesang', 'Cyua', '機動戦士ガンダムUC オリジナルサウンドトラック4', '00:04:40', 0, 0, 'Cyua - Sternengesang.mp3');
INSERT INTO `music` VALUES (9, 'Grenzlinie', 'Cyua', '甲鉄城のカバネリ ORIGINAL SOUNDTRACK', '00:04:56', 0, 0, 'Cyua - Grenzlinie.mp3');
INSERT INTO `music` VALUES (10, '黄金时代', '陈奕迅', '我的快乐时代', '00:04:09', 0, 0, '黄金时代_陈奕迅.mp3');
INSERT INTO `music` VALUES (11, 'Dear John', '比莉', '傻瓜就是我', '00:05:16', 0, 0, '比莉 - Dear John.mp3');
INSERT INTO `music` VALUES (13, 'song', '无', '无', '00:00:10', 0, 0, 'song.mp3');
INSERT INTO `music` VALUES (14, 'song1', '无', '无', '00:00:10', 0, 0, 'song1.mp3');
INSERT INTO `music` VALUES (15, 'song2', '无', '无', '00:00:10', 0, 0, 'song2.mp3');
INSERT INTO `music` VALUES (16, 'song3', '无', '无', '00:00:10', 0, 0, 'song3.mp3');
INSERT INTO `music` VALUES (17, 'song4', '无', '无', '00:00:10', 0, 0, 'song4.mp3');

-- ----------------------------
-- Table structure for music_fever
-- ----------------------------
DROP TABLE IF EXISTS `music_fever`;
CREATE TABLE `music_fever`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `music_id` int(255) NULL DEFAULT NULL,
  `upload_date` date NULL DEFAULT NULL,
  `week_played_times` int(255) NULL DEFAULT 0,
  `week_fever` float(255, 0) NULL DEFAULT 0,
  `month_played_times` int(255) NULL DEFAULT 0,
  `month_fever` float(255, 0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music_fever
-- ----------------------------
INSERT INTO `music_fever` VALUES (1, 7, '2020-01-01', 10, 0, 10, 0);
INSERT INTO `music_fever` VALUES (2, 8, '2020-01-01', 10000, 0, 10000000, 0);
INSERT INTO `music_fever` VALUES (3, 9, '2020-01-01', 12454, 0, 1651946499, 0);
INSERT INTO `music_fever` VALUES (4, 10, '2020-01-01', 11, 0, 1165, 0);
INSERT INTO `music_fever` VALUES (5, 11, '2020-01-01', 20, 0, 1156, 0);
INSERT INTO `music_fever` VALUES (6, 12, '2020-01-01', 500, 0, 65162, 0);
INSERT INTO `music_fever` VALUES (7, 13, '2020-01-01', 502, 0, 156981, 0);
INSERT INTO `music_fever` VALUES (8, 14, '2020-01-01', 600, 0, 16156, 0);
INSERT INTO `music_fever` VALUES (9, 15, '2020-01-01', 774, 0, 2918, 0);
INSERT INTO `music_fever` VALUES (10, 16, '2020-01-01', 6, 0, 556, 0);
INSERT INTO `music_fever` VALUES (11, 17, '2020-06-20', 1, 0, 1, 0);

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `series` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `token` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `last_used` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('admin', 'RuXG26qhO3Hm4ZLELgjGVg==', 'VtL5Y57jwVbL7SciX/dbdw==', '2020-06-20 00:08:36');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `registration_date` datetime(0) NULL DEFAULT NULL,
  `valid` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$2UabiaTEAjUVb/lUW5xGge51H0MGfaatZmpN5FV7TTQalBCctx7w2', '2871451323@qq.com', NULL, 1);
INSERT INTO `user` VALUES (2, 'vip', '$2a$10$5ooQI8dir8jv0/gCa1Six.GpzAdIPf6pMqdminZ/3ijYzivCyPlfK', '2871451323@qq.com', NULL, 1);
INSERT INTO `user` VALUES (3, 'user001', '$2a$10$5ooQI8dir8jv0/gCa1Six.GpzAdIPf6pMqdminZ/3ijYzivCyPlfK', '2871451323@qq.com', NULL, 1);
INSERT INTO `user` VALUES (4, 'user002', '$2a$10$5ooQI8dir8jv0/gCa1Six.GpzAdIPf6pMqdminZ/3ijYzivCyPlfK', '2871451323@qq.com', '2020-06-03 15:36:27', 1);
INSERT INTO `user` VALUES (5, 'user003', '$2a$10$cprVJ5WIWZjvaeylY8ZBpO1n2XmW6OaYlgNEvQOIHxWd2Bv/Ngpe6', '2871451323@qq.com', '2020-06-03 15:44:40', 1);
INSERT INTO `user` VALUES (7, 'ZhuoJ', '$2a$10$5ooQI8dir8jv0/gCa1Six.GpzAdIPf6pMqdminZ/3ijYzivCyPlfK', '2871451323@qq.com', '2020-06-03 16:27:30', 1);
INSERT INTO `user` VALUES (12, 'user005', '$2a$10$MPfdWm1YGWtQ6bH5RhMNhuLLBBjJGcTbXJ/E1hjyAcxnwpwPARO1m', '2871451323@qq.com', '2020-06-18 15:52:41', 1);

-- ----------------------------
-- Table structure for user_authority
-- ----------------------------
DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) NULL DEFAULT NULL,
  `authority_id` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_authority
-- ----------------------------
INSERT INTO `user_authority` VALUES (1, 1, 1);
INSERT INTO `user_authority` VALUES (2, 2, 2);
INSERT INTO `user_authority` VALUES (3, 3, 3);
INSERT INTO `user_authority` VALUES (4, 4, 3);
INSERT INTO `user_authority` VALUES (5, 5, 3);
INSERT INTO `user_authority` VALUES (7, 7, 3);
INSERT INTO `user_authority` VALUES (8, 12, 3);

-- ----------------------------
-- Table structure for user_music
-- ----------------------------
DROP TABLE IF EXISTS `user_music`;
CREATE TABLE `user_music`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) NULL DEFAULT NULL,
  `music_id` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_music
-- ----------------------------
INSERT INTO `user_music` VALUES (1, 1, 13);
INSERT INTO `user_music` VALUES (5, 1, 9);
INSERT INTO `user_music` VALUES (6, 1, 8);
INSERT INTO `user_music` VALUES (7, 1, 11);
INSERT INTO `user_music` VALUES (8, 1, 10);
INSERT INTO `user_music` VALUES (9, 1, 7);

SET FOREIGN_KEY_CHECKS = 1;
