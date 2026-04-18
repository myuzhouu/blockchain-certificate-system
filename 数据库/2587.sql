/*
 Navicat Premium Data Transfer

 Source Server         : localhost3308-mysql8
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3308
 Source Schema         : 2587

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 18/04/2025 23:03:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employer
-- ----------------------------
DROP TABLE IF EXISTS `employer`;
CREATE TABLE `employer`  (
  `org_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '单位编号',
  `org_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单位名称',
  `credit_code` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '信用代码',
  `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系方式',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '关联账号',
  PRIMARY KEY (`org_id`) USING BTREE,
  UNIQUE INDEX `credit_code`(`credit_code`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `employer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '单位信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employer
-- ----------------------------
INSERT INTO `employer` VALUES (1, 'ABC 公司', '911101010000000001', '13811111111', 6);
INSERT INTO `employer` VALUES (2, 'DEF 企业', '911101020000000002', '13922222222', 7);
INSERT INTO `employer` VALUES (3, 'GHI 集团', '911101030000000003', '13733333333', 8);
INSERT INTO `employer` VALUES (4, 'JKL 工厂', '911101040000000004', '13644444444', NULL);
INSERT INTO `employer` VALUES (5, 'MNO 商行', '911101050000000005', '13555555555', NULL);
INSERT INTO `employer` VALUES (6, 'PQR 店铺', '911101060000000006', '13466666666', NULL);
INSERT INTO `employer` VALUES (7, 'STU 中心', '911101070000000007', '13377777777', NULL);
INSERT INTO `employer` VALUES (8, 'VWX 协会', '911101080000000008', '13288888888', NULL);
INSERT INTO `employer` VALUES (9, 'YZA 机构', '911101090000000009', '13199999999', NULL);
INSERT INTO `employer` VALUES (10, 'BCD 组织', '911101100000000010', '13000000000', NULL);

-- ----------------------------
-- Table structure for individual_user
-- ----------------------------
DROP TABLE IF EXISTS `individual_user`;
CREATE TABLE `individual_user`  (
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户姓名',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `id_card`(`id_card`) USING BTREE,
  CONSTRAINT `individual_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '个人用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of individual_user
-- ----------------------------
INSERT INTO `individual_user` VALUES (1, '张三', '110101199001011234', '13800138000');
INSERT INTO `individual_user` VALUES (2, '李四', '110102199102022345', '13900139000');
INSERT INTO `individual_user` VALUES (3, '王五', '110103199203033456', '13700137000');
INSERT INTO `individual_user` VALUES (4, '赵六', '110104199304044567', '13600136000');
INSERT INTO `individual_user` VALUES (5, '孙七', '110105199405055678', '13500135000');
INSERT INTO `individual_user` VALUES (6, '周八', '110106199506066789', '13400134000');
INSERT INTO `individual_user` VALUES (7, '吴九', '110107199607077890', '13300133000');
INSERT INTO `individual_user` VALUES (8, '郑十', '110108199708088901', '13200132000');
INSERT INTO `individual_user` VALUES (9, '王十一', '110109199809099012', '13100131000');
INSERT INTO `individual_user` VALUES (10, '李十二', '110110199910100123', '13000130000');

-- ----------------------------
-- Table structure for issuing_agency
-- ----------------------------
DROP TABLE IF EXISTS `issuing_agency`;
CREATE TABLE `issuing_agency`  (
  `agency_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '机构编号',
  `agency_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机构名称',
  `credit_code` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '信用代码',
  `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系方式',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '关联账号',
  PRIMARY KEY (`agency_id`) USING BTREE,
  UNIQUE INDEX `credit_code`(`credit_code`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `issuing_agency_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '机构信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of issuing_agency
-- ----------------------------
INSERT INTO `issuing_agency` VALUES (1, '科技职称发证中心', '911102010000000001', '13811112222', 9);
INSERT INTO `issuing_agency` VALUES (2, '教育职称发证机构', '911102020000000002', '13922223333', NULL);
INSERT INTO `issuing_agency` VALUES (3, '医疗职称发证处', '911102030000000003', '13733334444', NULL);
INSERT INTO `issuing_agency` VALUES (4, '工程职称发证局', '911102040000000004', '13644445555', NULL);
INSERT INTO `issuing_agency` VALUES (5, '艺术职称发证部', '911102050000000005', '13555556666', NULL);
INSERT INTO `issuing_agency` VALUES (6, '农业职称发证所', '911102060000000006', '13466667777', NULL);
INSERT INTO `issuing_agency` VALUES (7, '金融职称发证科', '911102070000000007', '13377778888', NULL);
INSERT INTO `issuing_agency` VALUES (8, '体育职称发证办', '911102080000000008', '13288889999', NULL);
INSERT INTO `issuing_agency` VALUES (9, '文学职称发证室', '911102090000000009', '13199990000', NULL);
INSERT INTO `issuing_agency` VALUES (10, '法律职称发证厅', '911102100000000010', '13000001111', NULL);

-- ----------------------------
-- Table structure for regulatory_department
-- ----------------------------
DROP TABLE IF EXISTS `regulatory_department`;
CREATE TABLE `regulatory_department`  (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门名称',
  `contact` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系方式',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '关联账号',
  PRIMARY KEY (`dept_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `regulatory_department_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '监管部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of regulatory_department
-- ----------------------------
INSERT INTO `regulatory_department` VALUES (1, '职称监管一部', '13811113333', 10);
INSERT INTO `regulatory_department` VALUES (2, '职称监管二部', '13922224444', NULL);
INSERT INTO `regulatory_department` VALUES (3, '职称监管三部', '13733335555', NULL);
INSERT INTO `regulatory_department` VALUES (4, '职称监管四部', '13644446666', NULL);
INSERT INTO `regulatory_department` VALUES (5, '职称监管五部', '13555557777', NULL);
INSERT INTO `regulatory_department` VALUES (6, '职称监管六部', '13466668888', NULL);
INSERT INTO `regulatory_department` VALUES (7, '职称监管七部', '13377779999', NULL);
INSERT INTO `regulatory_department` VALUES (8, '职称监管八部', '13288880000', NULL);
INSERT INTO `regulatory_department` VALUES (9, '职称监管九部', '13199991111', NULL);
INSERT INTO `regulatory_department` VALUES (10, '职称监管十部', '13000002222', NULL);

-- ----------------------------
-- Table structure for regulatory_record
-- ----------------------------
DROP TABLE IF EXISTS `regulatory_record`;
CREATE TABLE `regulatory_record`  (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录编号',
  `dept_id` int(11) NOT NULL COMMENT '部门编号',
  `cert_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '证书号码',
  `reg_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '监管时间',
  `reg_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '监管结果',
  `reg_remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '监管备注',
  PRIMARY KEY (`record_id`) USING BTREE,
  INDEX `dept_id`(`dept_id`) USING BTREE,
  INDEX `cert_num`(`cert_num`) USING BTREE,
  CONSTRAINT `regulatory_record_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `regulatory_department` (`dept_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `regulatory_record_ibfk_2` FOREIGN KEY (`cert_num`) REFERENCES `title_certificate` (`cert_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '监管记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of regulatory_record
-- ----------------------------
INSERT INTO `regulatory_record` VALUES (1, 1, 'CERT001', '2025-04-18 21:47:15', '合规', '证书信息无误');
INSERT INTO `regulatory_record` VALUES (2, 1, 'CERT002', '2025-04-18 21:47:15', '合规', '证书信息无误');
INSERT INTO `regulatory_record` VALUES (3, 2, 'CERT003', '2025-04-18 21:47:15', '合规', '证书信息无误');
INSERT INTO `regulatory_record` VALUES (4, 2, 'CERT004', '2025-04-18 21:47:15', '合规', '证书信息无误');
INSERT INTO `regulatory_record` VALUES (5, 3, 'CERT005', '2025-04-18 21:47:15', '合规', '证书信息无误');
INSERT INTO `regulatory_record` VALUES (6, 3, 'CERT006', '2025-04-18 21:47:15', '合规', '证书信息无误');
INSERT INTO `regulatory_record` VALUES (7, 4, 'CERT007', '2025-04-18 21:47:15', '合规', '证书信息无误');
INSERT INTO `regulatory_record` VALUES (8, 4, 'CERT008', '2025-04-18 21:47:15', '合规', '证书信息无误');
INSERT INTO `regulatory_record` VALUES (9, 5, 'CERT009', '2025-04-18 21:47:15', '合规', '证书信息无误');
INSERT INTO `regulatory_record` VALUES (10, 5, 'CERT010', '2025-04-18 21:47:15', '合规', '证书信息无误');

-- ----------------------------
-- Table structure for sharing_record
-- ----------------------------
DROP TABLE IF EXISTS `sharing_record`;
CREATE TABLE `sharing_record`  (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录编号',
  `cert_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '证书号码',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `org_id` int(11) NOT NULL COMMENT '单位编号',
  `share_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '共享时间',
  `share_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '共享状态',
  PRIMARY KEY (`record_id`) USING BTREE,
  INDEX `cert_num`(`cert_num`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `org_id`(`org_id`) USING BTREE,
  CONSTRAINT `sharing_record_ibfk_1` FOREIGN KEY (`cert_num`) REFERENCES `title_certificate` (`cert_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sharing_record_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `individual_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sharing_record_ibfk_3` FOREIGN KEY (`org_id`) REFERENCES `employer` (`org_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '证书共享记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sharing_record
-- ----------------------------
INSERT INTO `sharing_record` VALUES (1, 'CERT001', 1, 1, '2025-04-18 21:47:15', '已共享');
INSERT INTO `sharing_record` VALUES (2, 'CERT002', 2, 1, '2025-04-18 21:47:15', '已共享');
INSERT INTO `sharing_record` VALUES (3, 'CERT003', 3, 2, '2025-04-18 21:47:15', '已共享');
INSERT INTO `sharing_record` VALUES (4, 'CERT004', 4, 2, '2025-04-18 21:47:15', '已共享');
INSERT INTO `sharing_record` VALUES (5, 'CERT005', 5, 3, '2025-04-18 21:47:15', '已共享');
INSERT INTO `sharing_record` VALUES (6, 'CERT006', 6, 3, '2025-04-18 21:47:15', '已共享');
INSERT INTO `sharing_record` VALUES (7, 'CERT007', 7, 4, '2025-04-18 21:47:15', '已共享');
INSERT INTO `sharing_record` VALUES (8, 'CERT008', 8, 4, '2025-04-18 21:47:15', '已共享');
INSERT INTO `sharing_record` VALUES (9, 'CERT009', 9, 5, '2025-04-18 21:47:15', '已共享');
INSERT INTO `sharing_record` VALUES (10, 'CERT010', 10, 5, '2025-04-18 21:47:15', '已共享');

-- ----------------------------
-- Table structure for title_certificate
-- ----------------------------
DROP TABLE IF EXISTS `title_certificate`;
CREATE TABLE `title_certificate`  (
  `cert_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '证书编号',
  `cert_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '证书号码',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `agency_id` int(11) NOT NULL COMMENT '机构编号',
  `title_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '职称名称',
  `issue_date` date NOT NULL COMMENT '发证日期',
  `valid_start` date NOT NULL COMMENT '有效起始',
  `valid_end` date NOT NULL COMMENT '有效截止',
  `cert_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '证书状态',
  `block_hash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区块哈希',
  PRIMARY KEY (`cert_id`) USING BTREE,
  UNIQUE INDEX `cert_num`(`cert_num`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `agency_id`(`agency_id`) USING BTREE,
  CONSTRAINT `title_certificate_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `individual_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `title_certificate_ibfk_2` FOREIGN KEY (`agency_id`) REFERENCES `issuing_agency` (`agency_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '职称证书' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of title_certificate
-- ----------------------------
INSERT INTO `title_certificate` VALUES (1, 'CERT001', 1, 1, '初级工程师', '2020-01-01', '2020-01-01', '2025-01-01', '有效', '4ac372c91c5d11f0bb7e005056c00001');
INSERT INTO `title_certificate` VALUES (2, 'CERT002', 2, 1, '中级工程师', '2021-02-02', '2021-02-02', '2026-02-02', '有效', '4ac3b4041c5d11f0bb7e005056c00002');
INSERT INTO `title_certificate` VALUES (3, 'CERT003', 3, 2, '高级教师', '2022-03-03', '2022-03-03', '2027-03-03', '有效', '4ac3e0c01c5d11f0bb7e005056c00003');
INSERT INTO `title_certificate` VALUES (4, 'CERT004', 4, 2, '一级教师', '2023-04-04', '2023-04-04', '2028-04-04', '有效', '4ac412df1c5d11f0bb7e005056c00004');
INSERT INTO `title_certificate` VALUES (5, 'CERT005', 5, 3, '主治医师', '2024-05-05', '2024-05-05', '2029-05-05', '有效', '4ac44a3e1c5d11f0bb7e005056c00005');
INSERT INTO `title_certificate` VALUES (6, 'CERT006', 6, 3, '副主任医师', '2025-06-06', '2025-06-06', '2030-06-06', '有效', '4ac480331c5d11f0bb7e005056c00006');
INSERT INTO `title_certificate` VALUES (7, 'CERT007', 7, 4, '高级建筑师', '2026-07-07', '2026-07-07', '2031-07-07', '有效', '4ac4f0aa1c5d11f0bb7e005056c00007');
INSERT INTO `title_certificate` VALUES (8, 'CERT008', 8, 4, '二级建造师', '2027-08-08', '2027-08-08', '2032-08-08', '有效', '4ac5415b1c5d11f0bb7e005056c00008');
INSERT INTO `title_certificate` VALUES (9, 'CERT009', 9, 5, '一级画师', '2028-09-09', '2028-09-09', '2033-09-09', '有效', '4ac583981c5d11f0bb7e005056c00009');
INSERT INTO `title_certificate` VALUES (10, 'CERT010', 10, 5, '高级摄影师', '2029-10-10', '2029-10-10', '2034-10-10', '有效', '4ac5b7f11c5d11f0bb7e005056c00011');
INSERT INTO `title_certificate` VALUES (11, 'CERT011', 1, 6, '农艺师', '2020-02-15', '2020-02-15', '2025-02-15', '有效', '4ac39ba91c5d11f0bb7e005056c00012');
INSERT INTO `title_certificate` VALUES (12, 'CERT012', 2, 6, '高级农艺师', '2021-03-20', '2021-03-20', '2026-03-20', '有效', '4ac3ccbd1c5d11f0bb7e005056c00023');
INSERT INTO `title_certificate` VALUES (13, 'CERT013', 3, 7, '金融分析师', '2022-04-25', '2022-04-25', '2027-04-25', '有效', '4ac3fb151c5d11f0bb7e005056c00088');
INSERT INTO `title_certificate` VALUES (14, 'CERT014', 4, 7, '注册会计师', '2023-05-30', '2023-05-30', '2028-05-30', '有效', '4ac428ba1c5d11f0bb7e005056c00010');
INSERT INTO `title_certificate` VALUES (15, 'CERT015', 5, 8, '体育教练', '2024-06-12', '2024-06-12', '2029-06-12', '有效', '4ac465411c5d11f0bb7e005056c00022');
INSERT INTO `title_certificate` VALUES (16, 'CERT016', 6, 8, '健身教练', '2025-07-18', '2025-07-18', '2030-07-18', '有效', '4ac4a03a1c5d11f0bb7e005056c00074');
INSERT INTO `title_certificate` VALUES (17, 'CERT017', 7, 9, '作家', '2026-08-22', '2026-08-22', '2031-08-22', '有效', '4ac515951c5d11f0bb7e005056c00123');
INSERT INTO `title_certificate` VALUES (18, 'CERT018', 8, 9, '诗人', '2027-09-28', '2027-09-28', '2032-09-28', '有效', '4ac5680f1c5d11f0bb7e005056c00222');
INSERT INTO `title_certificate` VALUES (19, 'CERT019', 9, 10, '律师', '2028-10-05', '2028-10-05', '2033-10-05', '有效', '4ac59fb61c5d11f0bb7e005056c00558');
INSERT INTO `title_certificate` VALUES (20, 'CERT020', 10, 10, '法官', '2029-11-11', '2029-11-11', '2034-11-11', '有效', '4ac5d16f1c5d11f0bb7e005056c00998');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录密码',
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户角色',
  `picurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `create_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建时间',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户账号' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, 'user01', '123456', '个人用户', 'http://www.javalm.cn/img/98.png', '2025-04-18 21:47:15', '1371990460');
INSERT INTO `userinfo` VALUES (2, 'user02', '123456', '个人用户', 'http://www.javalm.cn/img/115.png', '2025-04-18 21:47:15', '1318206802');
INSERT INTO `userinfo` VALUES (3, 'user03', '123456', '个人用户', 'http://www.javalm.cn/img/82.png', '2025-04-18 21:47:15', '1345062635');
INSERT INTO `userinfo` VALUES (4, 'user04', '123456', '个人用户', 'http://www.javalm.cn/img/63.png', '2025-04-18 21:47:15', '1370692771');
INSERT INTO `userinfo` VALUES (5, 'user05', '123456', '个人用户', 'http://www.javalm.cn/img/71.png', '2025-04-18 21:47:15', '1328275952');
INSERT INTO `userinfo` VALUES (6, 'org01', '123456', '用人单位', 'http://www.javalm.cn/img/164.png', '2025-04-18 21:47:15', '1399301453');
INSERT INTO `userinfo` VALUES (7, 'org02', '123456', '用人单位', 'http://www.javalm.cn/img/9.png', '2025-04-18 21:47:15', '1341679412');
INSERT INTO `userinfo` VALUES (8, 'org03', '123456', '用人单位', 'http://www.javalm.cn/img/153.png', '2025-04-18 21:47:15', '1380492703');
INSERT INTO `userinfo` VALUES (9, 'agency01', '123456', '发证机构', 'http://www.javalm.cn/img/139.png', '2025-04-18 21:47:15', '1387425281');
INSERT INTO `userinfo` VALUES (10, 'dept01', '123456', '监管部门', 'http://www.javalm.cn/img/36.png', '2025-04-18 21:47:15', '1395648300');
INSERT INTO `userinfo` VALUES (11, 'admin', '123456', '管理员', 'http://www.javalm.cn/img/9.png', '2025-04-18 21:47:15', '1395648323');

-- ----------------------------
-- View structure for certificate_info
-- ----------------------------
DROP VIEW IF EXISTS `certificate_info`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `certificate_info` AS select `tc`.`cert_num` AS `cert_num`,`iu`.`user_name` AS `user_name`,`ia`.`agency_name` AS `agency_name`,`tc`.`title_name` AS `title_name`,`tc`.`issue_date` AS `issue_date`,`tc`.`valid_start` AS `valid_start`,`tc`.`valid_end` AS `valid_end`,`tc`.`cert_status` AS `cert_status`,`tc`.`block_hash` AS `block_hash` from ((`title_certificate` `tc` join `individual_user` `iu` on((`tc`.`user_id` = `iu`.`user_id`))) join `issuing_agency` `ia` on((`tc`.`agency_id` = `ia`.`agency_id`)));

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('CERT001', '张三', '科技职称发证中心', '初级工程师', '2020-01-01', '2020-01-01', '2025-01-01', '有效', '4ac372c91c5d11f0bb7e005056c00001');
INSERT INTO `userinfo` VALUES ('CERT011', '张三', '农业职称发证所', '农艺师', '2020-02-15', '2020-02-15', '2025-02-15', '有效', '4ac39ba91c5d11f0bb7e005056c00012');
INSERT INTO `userinfo` VALUES ('CERT002', '李四', '科技职称发证中心', '中级工程师', '2021-02-02', '2021-02-02', '2026-02-02', '有效', '4ac3b4041c5d11f0bb7e005056c00002');
INSERT INTO `userinfo` VALUES ('CERT012', '李四', '农业职称发证所', '高级农艺师', '2021-03-20', '2021-03-20', '2026-03-20', '有效', '4ac3ccbd1c5d11f0bb7e005056c00023');
INSERT INTO `userinfo` VALUES ('CERT003', '王五', '教育职称发证机构', '高级教师', '2022-03-03', '2022-03-03', '2027-03-03', '有效', '4ac3e0c01c5d11f0bb7e005056c00003');
INSERT INTO `userinfo` VALUES ('CERT013', '王五', '金融职称发证科', '金融分析师', '2022-04-25', '2022-04-25', '2027-04-25', '有效', '4ac3fb151c5d11f0bb7e005056c00088');
INSERT INTO `userinfo` VALUES ('CERT004', '赵六', '教育职称发证机构', '一级教师', '2023-04-04', '2023-04-04', '2028-04-04', '有效', '4ac412df1c5d11f0bb7e005056c00004');
INSERT INTO `userinfo` VALUES ('CERT014', '赵六', '金融职称发证科', '注册会计师', '2023-05-30', '2023-05-30', '2028-05-30', '有效', '4ac428ba1c5d11f0bb7e005056c00010');
INSERT INTO `userinfo` VALUES ('CERT005', '孙七', '医疗职称发证处', '主治医师', '2024-05-05', '2024-05-05', '2029-05-05', '有效', '4ac44a3e1c5d11f0bb7e005056c00005');
INSERT INTO `userinfo` VALUES ('CERT015', '孙七', '体育职称发证办', '体育教练', '2024-06-12', '2024-06-12', '2029-06-12', '有效', '4ac465411c5d11f0bb7e005056c00022');
INSERT INTO `userinfo` VALUES ('CERT006', '周八', '医疗职称发证处', '副主任医师', '2025-06-06', '2025-06-06', '2030-06-06', '有效', '4ac480331c5d11f0bb7e005056c00006');
INSERT INTO `userinfo` VALUES ('CERT016', '周八', '体育职称发证办', '健身教练', '2025-07-18', '2025-07-18', '2030-07-18', '有效', '4ac4a03a1c5d11f0bb7e005056c00074');
INSERT INTO `userinfo` VALUES ('CERT007', '吴九', '工程职称发证局', '高级建筑师', '2026-07-07', '2026-07-07', '2031-07-07', '有效', '4ac4f0aa1c5d11f0bb7e005056c00007');
INSERT INTO `userinfo` VALUES ('CERT017', '吴九', '文学职称发证室', '作家', '2026-08-22', '2026-08-22', '2031-08-22', '有效', '4ac515951c5d11f0bb7e005056c00123');
INSERT INTO `userinfo` VALUES ('CERT008', '郑十', '工程职称发证局', '二级建造师', '2027-08-08', '2027-08-08', '2032-08-08', '有效', '4ac5415b1c5d11f0bb7e005056c00008');
INSERT INTO `userinfo` VALUES ('CERT018', '郑十', '文学职称发证室', '诗人', '2027-09-28', '2027-09-28', '2032-09-28', '有效', '4ac5680f1c5d11f0bb7e005056c00222');
INSERT INTO `userinfo` VALUES ('CERT009', '王十一', '艺术职称发证部', '一级画师', '2028-09-09', '2028-09-09', '2033-09-09', '有效', '4ac583981c5d11f0bb7e005056c00009');
INSERT INTO `userinfo` VALUES ('CERT019', '王十一', '法律职称发证厅', '律师', '2028-10-05', '2028-10-05', '2033-10-05', '有效', '4ac59fb61c5d11f0bb7e005056c00558');
INSERT INTO `userinfo` VALUES ('CERT010', '李十二', '艺术职称发证部', '高级摄影师', '2029-10-10', '2029-10-10', '2034-10-10', '有效', '4ac5b7f11c5d11f0bb7e005056c00011');
INSERT INTO `userinfo` VALUES ('CERT020', '李十二', '法律职称发证厅', '法官', '2029-11-11', '2029-11-11', '2034-11-11', '有效', '4ac5d16f1c5d11f0bb7e005056c00998');

SET FOREIGN_KEY_CHECKS = 1;
