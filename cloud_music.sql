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

 Date: 28/06/2020 19:01:17
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
INSERT INTO `code` VALUES ('ZhuoJ', '330006', '2020-06-25 21:14:56');

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `artist` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '艺术家',
  `album` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专辑',
  `duration` time(0) NULL DEFAULT NULL COMMENT '时长',
  `vip` int(255) NULL DEFAULT 0 COMMENT 'vip专属',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
  PRIMARY KEY (`id`, `username`, `email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$/FBYnJMiXkwUgYYyMv.XGOQc.fsEjLa6J6ypkn4JURIpHfXaPWeUm', '2871451323@qq.com', NULL, 1);
INSERT INTO `user` VALUES (2, 'vip', '$2a$10$5ooQI8dir8jv0/gCa1Six.GpzAdIPf6pMqdminZ/3ijYzivCyPlfK', '945920777@qq.com', NULL, 1);
INSERT INTO `user` VALUES (3, 'user001', '$2a$10$5ooQI8dir8jv0/gCa1Six.GpzAdIPf6pMqdminZ/3ijYzivCyPlfK', '', NULL, 0);
INSERT INTO `user` VALUES (4, 'user002', '$2a$10$5ooQI8dir8jv0/gCa1Six.GpzAdIPf6pMqdminZ/3ijYzivCyPlfK', '', '2020-06-03 15:36:27', 1);
INSERT INTO `user` VALUES (5, 'user003', '$2a$10$cprVJ5WIWZjvaeylY8ZBpO1n2XmW6OaYlgNEvQOIHxWd2Bv/Ngpe6', '', '2020-06-03 15:44:40', 1);
INSERT INTO `user` VALUES (6, 'user006', '$2a$10$kdhA7gsoxm62J0ucabvV5OQIx9k75gOV8qs37Uu8OoWEhWMtigS9y', 'user006@qq.com', '2020-06-26 22:34:42', 1);
INSERT INTO `user` VALUES (7, 'user007', '$2a$10$c/Fb7C/TO9gksgCYmDqHtuy9QNb6Tv/n7JCAr7MKk1GYx/bEcAXpO', 'user007@qq.com', '2020-06-26 22:35:15', 1);
INSERT INTO `user` VALUES (8, 'user010', '$2a$10$GIxDlMFCXq3rxDI1E2loOOoDHw07ooXXb5G5x6KEwgqJ6XiRznJS6', 'user010@qq.com', '2020-06-26 22:43:25', 1);
INSERT INTO `user` VALUES (9, 'user011', '$2a$10$x0nSQkQgLqovfIURLCh/ee4vOCq1INIwYRkA3LCpijeayKepsxZkK', 'user011@qq.com', '2020-06-26 22:43:25', 1);
INSERT INTO `user` VALUES (10, 'user012', '$2a$10$YZzLY8Y6Rku0oaF/XKHf7.bu1eKRAwjX6takiIVMxUMp4TfFCJh9y', 'user012@qq.com', '2020-06-26 22:43:26', 1);
INSERT INTO `user` VALUES (11, 'user013', '$2a$10$5HLhd9BQ/ZUAZ3Mv93NZm.fW8npiXpObYWsODReCKCX/KAQmqe//C', 'user013@qq.com', '2020-06-26 22:43:26', 1);
INSERT INTO `user` VALUES (12, 'user014', '$2a$10$jZgy9NTNIwAMr/mHXvdSE.aC66JSn4x81kuhoKBs9/UbNwoVA15De', 'user014@qq.com', '2020-06-26 22:43:26', 1);
INSERT INTO `user` VALUES (13, 'user015', '$2a$10$mbfjSnAZSmW7RmKQx1xmKOnlf5Ecr5cfybeKoibKKzOmDG/ggO3zW', 'user015@qq.com', '2020-06-26 22:43:26', 1);
INSERT INTO `user` VALUES (14, 'user016', '$2a$10$rx5PHleGzYkZkckzWPvu0e5F4qwuCXmgVi6XoMx2M.X3w6aUngQ.S', 'user016@qq.com', '2020-06-26 22:43:27', 1);
INSERT INTO `user` VALUES (15, 'user017', '$2a$10$tMCttTKgFLhiAo7gDaVjA.VXzpGCa31q89MMGwJJyE00DQAfHk1na', 'user017@qq.com', '2020-06-26 22:43:27', 1);
INSERT INTO `user` VALUES (16, 'user018', '$2a$10$iMl5ASavWPkmDyM47Cnfj./zgqKelCiOB18M5YZzAdz9jFQmaiMje', 'user018@qq.com', '2020-06-26 22:43:27', 1);
INSERT INTO `user` VALUES (17, 'user019', '$2a$10$L8dc0E3C.3D8tpaYoVNqNeC20xZjz3hv7Q7mRpF8fH7QWURURSHJK', 'user019@qq.com', '2020-06-26 22:43:27', 1);
INSERT INTO `user` VALUES (18, 'user020', '$2a$10$5.0opeGnLSSb8jR5hYjFh.3Ul/V5H1wMW8vcELuzqDlK5mup3XL7y', 'user020@qq.com', '2020-06-26 22:43:27', 1);
INSERT INTO `user` VALUES (19, 'user021', '$2a$10$5XkbFAw6O.7uwMJ/WgoW6.W6Jg/KULlXmVn9uQNeYrUuWjQ19COcq', 'user021@qq.com', '2020-06-26 22:43:27', 1);
INSERT INTO `user` VALUES (20, 'user022', '$2a$10$Akxt03HcX5C2fccX34/vKuhYfvgrjrZrGkXRrKqJ/B1yWFlY3yCte', 'user022@qq.com', '2020-06-26 22:43:28', 1);
INSERT INTO `user` VALUES (21, 'user023', '$2a$10$W8/uJOiT9sCUD2fg0TnCSO9NJ3kru/Y7teOp.Xe57yMr.isf7cGmu', 'user023@qq.com', '2020-06-26 22:43:28', 1);
INSERT INTO `user` VALUES (22, 'user024', '$2a$10$/26oc5Kc/JlcGUcU4Az2F.5/F2NuMkZvFO6KR6coB0jM0PDG4hVEi', 'user024@qq.com', '2020-06-26 22:43:28', 1);
INSERT INTO `user` VALUES (23, 'user025', '$2a$10$DV7rjUAQmy1KmfVj4hm5eueXqoZmMYjPYlaSm2HqXD2ouewMzJxmu', 'user025@qq.com', '2020-06-26 22:43:28', 1);
INSERT INTO `user` VALUES (24, 'user026', '$2a$10$2Ri/lD3tzQv3PXphiJyKDuoB0aDPBaY5GUcVoY04Gg2EuEAdUIuGG', 'user026@qq.com', '2020-06-26 22:43:28', 1);
INSERT INTO `user` VALUES (25, 'user027', '$2a$10$P0vUnxWS6m6KpcUtk.NsDOtYE6ykbKySVj91TiyePCkde9d436Pr.', 'user027@qq.com', '2020-06-26 22:43:28', 1);
INSERT INTO `user` VALUES (26, 'user028', '$2a$10$GagqTarnB8FOUcZugY5tOe8nXeToJonq/7jwdeOMFxBoUeWjHrauy', 'user028@qq.com', '2020-06-26 22:43:29', 1);
INSERT INTO `user` VALUES (27, 'user029', '$2a$10$wLFxi1WKGtvTlxmYLx.Q0.gX6vCFh5C/ZUf7Kgft7IL9.30lvv3y.', 'user029@qq.com', '2020-06-26 22:43:29', 1);
INSERT INTO `user` VALUES (28, 'user030', '$2a$10$OzQDOrrmzTNsWJ5T2cNU.uLDy7xV7ia2b6pEMth3GamyBLFDGXfdy', 'user030@qq.com', '2020-06-26 22:43:29', 1);
INSERT INTO `user` VALUES (29, 'user031', '$2a$10$s6rCB87nhkwnuYl4zy7vwekthQeG5Pl0OCjs27LOZgaN8wKz3n6ey', 'user031@qq.com', '2020-06-26 22:43:29', 1);
INSERT INTO `user` VALUES (30, 'user032', '$2a$10$rsZ22r1P4IPu2ADPabUg3e5PuOrw0FBfk0DaUWWc9siLv4o0HSQw2', 'user032@qq.com', '2020-06-26 22:43:29', 1);
INSERT INTO `user` VALUES (31, 'user033', '$2a$10$Fzz1ShLk8jocuMGDoj48JekHNYx26vwq3HDPHzDMkuRP4CFu8eC5e', 'user033@qq.com', '2020-06-26 22:43:29', 1);
INSERT INTO `user` VALUES (32, 'user034', '$2a$10$5rTm2niEuH46M4OgoH8TeuvyrO7YvZ1wt2vGH32HexFr/09KGAVUe', 'user034@qq.com', '2020-06-26 22:43:30', 1);
INSERT INTO `user` VALUES (33, 'user035', '$2a$10$WbJKJ2DCrzMIKm1gtMW0aO9jbYM2gjxUI6g7bPCtWlFZa/OdHehqq', 'user035@qq.com', '2020-06-26 22:43:30', 1);
INSERT INTO `user` VALUES (34, 'user036', '$2a$10$oXCQlLCF5SjRn/SXNMASNOFEM2U6acstacSWJmMIrgRGpiuXx3Kjm', 'user036@qq.com', '2020-06-26 22:43:30', 1);
INSERT INTO `user` VALUES (35, 'user037', '$2a$10$BVf78B8c9ivWzB.wZ548jOiefnq.Xvr.UHjbERQcdcWRNPnA2.15e', 'user037@qq.com', '2020-06-26 22:43:30', 1);
INSERT INTO `user` VALUES (36, 'user038', '$2a$10$Ix1B5oYm7bET.RIVdz05/.Dg3y7K5yzEFhmIA.GCIlquku.vJ4OpO', 'user038@qq.com', '2020-06-26 22:43:30', 1);
INSERT INTO `user` VALUES (37, 'user039', '$2a$10$8wn.d9.1ESTh1g2VE75f9e9pU0v8tS6PpITETIJLCPyawYuSGPllm', 'user039@qq.com', '2020-06-26 22:43:31', 1);
INSERT INTO `user` VALUES (38, 'user040', '$2a$10$0qy/MjloxLxJjaovci7/nOcym3iYaA/AVkn8o4.kzJ6ZBSdwwn1M6', 'user040@qq.com', '2020-06-26 22:43:31', 1);
INSERT INTO `user` VALUES (39, 'user041', '$2a$10$rV84bmO7/xlUDRkNmwAGDu8jJmYKyQ4cKE4G3ZOvSdnzEq9WXhjE2', 'user041@qq.com', '2020-06-26 22:43:31', 1);
INSERT INTO `user` VALUES (40, 'user042', '$2a$10$qEOFzHRRVlBZC7jyZbnqQeqxah7MZ7JSJCDTCN/dl7h5IbtLeZZLa', 'user042@qq.com', '2020-06-26 22:43:31', 1);
INSERT INTO `user` VALUES (41, 'user043', '$2a$10$KX6DwxjrI6DHiTgYMB2Pm./kb2WWqrTa47KOgUuzWDW/Ionkcr13m', 'user043@qq.com', '2020-06-26 22:43:31', 1);
INSERT INTO `user` VALUES (42, 'user044', '$2a$10$MdFTcPWgHEQXt.mReHJbquj.NTg2xnEFJpXNw7woqfpUzKuk5rlFa', 'user044@qq.com', '2020-06-26 22:43:31', 1);
INSERT INTO `user` VALUES (43, 'user045', '$2a$10$H2JW/.SUvN0HF1m92NIXzuapnXG/oT.FMIBr6RjCvBBBV0E.Jgz7q', 'user045@qq.com', '2020-06-26 22:43:32', 1);
INSERT INTO `user` VALUES (44, 'user046', '$2a$10$BFz8fiv39/h61f56kmq47uobUe4lMEKMiNXoWGrMvypw5neHkIkdq', 'user046@qq.com', '2020-06-26 22:43:32', 1);
INSERT INTO `user` VALUES (45, 'user047', '$2a$10$8fYFoYU0awPLRtLfd59DNOoSUpHNV99u5ZxI0y8PhDI4z3pp8UILC', 'user047@qq.com', '2020-06-26 22:43:32', 1);
INSERT INTO `user` VALUES (46, 'user048', '$2a$10$7.jW1.JNkk7.pnkT1nf/aO4fBin/UyjcaMC8ETo7l.uMC0Ew4U.Lq', 'user048@qq.com', '2020-06-26 22:43:32', 1);
INSERT INTO `user` VALUES (47, 'user049', '$2a$10$YtaLvS3yVbtvKjpRjTjQ.e7lCuaEuZ0CjQ0WBho.KiFt0Yqla83Iu', 'user049@qq.com', '2020-06-26 22:43:32', 1);
INSERT INTO `user` VALUES (48, 'user101', '$2a$10$GvCgHHb3v3DcXeSOW78DUucYZXibY7B4h.f9D3JvnvQcnpgaV6RtK', 'user101@qq.com', '2020-06-27 21:32:53', 1);
INSERT INTO `user` VALUES (49, 'user102', '$2a$10$uyD5wQps86dg07qiCzIGBeUC55TaF4E43Wh.YxcnvBrb8WKQOYU5u', 'user102@qq.com', '2020-06-27 21:38:54', 1);
INSERT INTO `user` VALUES (50, 'user103', '$2a$10$C5O3/f04ZaOGMUc45ZYv8.1co80OVOyX4ds3QFzZSUq77RWlJa0R6', 'user103@qq.com', '2020-06-27 21:51:09', 1);
INSERT INTO `user` VALUES (51, 'user103', '$2a$10$8p8nEyz/OlHY0FxSny6bZ.0AkmQejPK7G7azttv/VE1pAu3ytFFZK', 'user103@qq.com', '2020-06-27 21:51:14', 1);
INSERT INTO `user` VALUES (52, 'user103', '$2a$10$43M7kFM9q1ww7S1x2IQuNOqZ6D8YTXfvVxPOoq3jGElUgnQ825FLa', 'user103@qq.com', '2020-06-27 21:51:19', 1);
INSERT INTO `user` VALUES (53, 'user104', '$2a$10$P7PVr40812kURUZQItO7uO72oPgJRcFBrQ5KJm/h9UExxCoc9oR3u', 'user104@qq.com', '2020-06-27 21:53:00', 1);
INSERT INTO `user` VALUES (54, 'user105', '$2a$10$ak9l6tabKoR89lZ4bx50D.NSMOTBVQ0jOGg/Kwsa7IX1zNqIJBpmi', 'user105@qq.com', '2020-06-27 21:54:28', 1);

-- ----------------------------
-- Table structure for user_authority
-- ----------------------------
DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) NULL DEFAULT NULL,
  `authority_id` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_authority
-- ----------------------------
INSERT INTO `user_authority` VALUES (1, 1, 1);
INSERT INTO `user_authority` VALUES (2, 2, 2);
INSERT INTO `user_authority` VALUES (3, 3, 3);
INSERT INTO `user_authority` VALUES (4, 4, 3);
INSERT INTO `user_authority` VALUES (5, 5, 3);
INSERT INTO `user_authority` VALUES (7, 7, 3);
INSERT INTO `user_authority` VALUES (9, 6, 3);
INSERT INTO `user_authority` VALUES (11, 8, 3);
INSERT INTO `user_authority` VALUES (12, 9, 3);
INSERT INTO `user_authority` VALUES (13, 10, 3);
INSERT INTO `user_authority` VALUES (14, 11, 3);
INSERT INTO `user_authority` VALUES (15, 12, 3);
INSERT INTO `user_authority` VALUES (16, 13, 3);
INSERT INTO `user_authority` VALUES (17, 14, 3);
INSERT INTO `user_authority` VALUES (18, 15, 3);
INSERT INTO `user_authority` VALUES (19, 16, 3);
INSERT INTO `user_authority` VALUES (20, 17, 3);
INSERT INTO `user_authority` VALUES (21, 18, 3);
INSERT INTO `user_authority` VALUES (22, 19, 3);
INSERT INTO `user_authority` VALUES (23, 20, 3);
INSERT INTO `user_authority` VALUES (24, 21, 3);
INSERT INTO `user_authority` VALUES (25, 22, 3);
INSERT INTO `user_authority` VALUES (26, 23, 3);
INSERT INTO `user_authority` VALUES (27, 24, 3);
INSERT INTO `user_authority` VALUES (28, 25, 3);
INSERT INTO `user_authority` VALUES (29, 26, 3);
INSERT INTO `user_authority` VALUES (30, 27, 3);
INSERT INTO `user_authority` VALUES (31, 28, 3);
INSERT INTO `user_authority` VALUES (32, 29, 3);
INSERT INTO `user_authority` VALUES (33, 30, 3);
INSERT INTO `user_authority` VALUES (34, 31, 3);
INSERT INTO `user_authority` VALUES (35, 32, 3);
INSERT INTO `user_authority` VALUES (36, 33, 3);
INSERT INTO `user_authority` VALUES (37, 34, 3);
INSERT INTO `user_authority` VALUES (38, 35, 3);
INSERT INTO `user_authority` VALUES (39, 36, 3);
INSERT INTO `user_authority` VALUES (40, 37, 3);
INSERT INTO `user_authority` VALUES (41, 38, 3);
INSERT INTO `user_authority` VALUES (42, 39, 3);
INSERT INTO `user_authority` VALUES (43, 40, 3);
INSERT INTO `user_authority` VALUES (44, 41, 3);
INSERT INTO `user_authority` VALUES (45, 42, 3);
INSERT INTO `user_authority` VALUES (46, 43, 3);
INSERT INTO `user_authority` VALUES (47, 44, 3);
INSERT INTO `user_authority` VALUES (48, 45, 3);
INSERT INTO `user_authority` VALUES (49, 46, 3);
INSERT INTO `user_authority` VALUES (50, 47, 3);
INSERT INTO `user_authority` VALUES (51, 48, 3);
INSERT INTO `user_authority` VALUES (52, 49, 3);
INSERT INTO `user_authority` VALUES (53, 50, 3);
INSERT INTO `user_authority` VALUES (54, 53, 3);
INSERT INTO `user_authority` VALUES (55, 54, 3);

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
