/*
 Navicat Premium Data Transfer

 Source Server         : black
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : sjlm

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 26/04/2019 22:23:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sjlm_log
-- ----------------------------
DROP TABLE IF EXISTS `sjlm_log`;
CREATE TABLE `sjlm_log`  (
  `log_ID` int(100) NOT NULL,
  `log_code` int(3) NULL DEFAULT NULL,
  `log_text` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `log_data` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`log_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sjlm_order
-- ----------------------------
DROP TABLE IF EXISTS `sjlm_order`;
CREATE TABLE `sjlm_order`  (
  `order_orderID` int(50) NOT NULL,
  `order_shopsID` int(200) NULL DEFAULT NULL,
  `order_userID` int(10) NULL DEFAULT NULL,
  `order_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_code` int(3) NULL DEFAULT NULL,
  `order_express` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_cost` int(10) NULL DEFAULT NULL,
  `order_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_add_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`order_orderID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sjlm_order_shop
-- ----------------------------
DROP TABLE IF EXISTS `sjlm_order_shop`;
CREATE TABLE `sjlm_order_shop`  (
  `order_shopsID` int(50) NOT NULL,
  `order_shop` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`order_shopsID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sjlm_shop
-- ----------------------------
DROP TABLE IF EXISTS `sjlm_shop`;
CREATE TABLE `sjlm_shop`  (
  `shop_shopID` int(10) NOT NULL,
  `shop_shopName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `shop_PassWord` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `shop_shopType` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `shop_Introduce` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `shop_img` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `shop_brand` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `shop_add_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`shop_shopID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sjlm_user
-- ----------------------------
DROP TABLE IF EXISTS `sjlm_user`;
CREATE TABLE `sjlm_user`  (
  `user_ID` int(10) NOT NULL,
  `user_Name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_openID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_add_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sjlm_usmap
-- ----------------------------
DROP TABLE IF EXISTS `sjlm_usmap`;
CREATE TABLE `sjlm_usmap`  (
  `usmap_mapID` int(20) NOT NULL,
  `usmap_userID` int(10) NULL DEFAULT NULL,
  `usmap_shopID` int(10) NULL DEFAULT NULL,
  `usmap_add_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`usmap_mapID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
