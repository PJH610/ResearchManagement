/*
Navicat MySQL Data Transfer

Source Server         : bookstore
Source Server Version : 50720
Source Host           : 192.168.1.138:3306
Source Database       : bookstore_ssm

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-11-16 09:16:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_category`
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES ('38', '心理/励志');
INSERT INTO `tb_category` VALUES ('39', '生活');
INSERT INTO `tb_category` VALUES ('45', '童书');
INSERT INTO `tb_category` VALUES ('47', '原版书');
INSERT INTO `tb_category` VALUES ('78', '创造');
INSERT INTO `tb_category` VALUES ('79', '嘻嘻嘻');
INSERT INTO `tb_category` VALUES ('83', 'aaaa');
INSERT INTO `tb_category` VALUES ('84', '言情');
INSERT INTO `tb_category` VALUES ('85', '都市');
INSERT INTO `tb_category` VALUES ('89', '英语书');
INSERT INTO `tb_category` VALUES ('90', '67667');
INSERT INTO `tb_category` VALUES ('92', '哲学');
INSERT INTO `tb_category` VALUES ('93', '古典');

-- ----------------------------
-- Table structure for `tb_download_file`
-- ----------------------------
DROP TABLE IF EXISTS `tb_download_file`;
CREATE TABLE `tb_download_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `filename` varchar(255) NOT NULL COMMENT '文件名',
  `file_url` varchar(255) NOT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_download_file
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_order`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_code` varchar(255) NOT NULL COMMENT '订单号',
  `address` varchar(255) NOT NULL COMMENT '地址',
  `user_message` varchar(255) NOT NULL COMMENT '用户信息',
  `receivre` varchar(100) NOT NULL COMMENT '收货人',
  `mobile` varchar(255) NOT NULL COMMENT '移动电话',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `pay_date` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_date` datetime DEFAULT NULL COMMENT '收货时间',
  `confirm_date` datetime DEFAULT NULL COMMENT '发货时间',
  `status` varchar(255) NOT NULL COMMENT '状态',
  `user_id` bigint(11) NOT NULL COMMENT '外键(指向用户表)',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `order_user` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('46', '201810271115011833', '123', '123', '123', '18475794899', '2018-10-27 11:15:02', null, null, null, '1', '81');
INSERT INTO `tb_order` VALUES ('47', '201810271115185221', '123', '123', '123', '18475794899', '2018-10-27 11:15:18', null, null, null, '1', '81');
INSERT INTO `tb_order` VALUES ('48', '201810271116108295', '123', '123', '123', '18475794899', '2018-10-27 11:16:11', null, null, null, '1', '81');
INSERT INTO `tb_order` VALUES ('49', '201810271117180213', '123', '123', '123', '18475794899', '2018-10-27 11:17:18', null, null, null, '1', '81');
INSERT INTO `tb_order` VALUES ('50', '201810282255585756', '123', '123', '123', '18475794899', '2018-10-28 22:55:59', null, null, null, '1', '81');
INSERT INTO `tb_order` VALUES ('52', '201810291043343202', '1', '1', '1', '13001111111', '2018-10-29 10:43:35', null, null, '2018-10-31 09:10:37', '4', '80');
INSERT INTO `tb_order` VALUES ('54', '201810291627417377', 'gjfkldq', 'fdsadgd', 'jgklfdnq', '13138775400', '2018-10-29 16:27:41', null, null, '2018-10-31 09:10:36', '4', '80');
INSERT INTO `tb_order` VALUES ('55', '201811062227380906', '1', '1', '1', '18475184171', '2018-11-06 22:27:38', null, null, '2018-11-07 11:03:11', '4', '81');
INSERT INTO `tb_order` VALUES ('56', '201811071135450888', '1', '1', '1', '18475184171', '2018-11-07 11:35:45', null, null, '2018-11-07 11:37:12', '4', '80');
INSERT INTO `tb_order` VALUES ('61', '201811081701304341', '1', '5', '1', '18455555555', '2018-11-08 17:01:31', null, null, null, '1', '85');
INSERT INTO `tb_order` VALUES ('62', '201811081704184526', '4', '4', '1', '18444444444', '2018-11-08 17:04:19', null, null, null, '1', '85');
INSERT INTO `tb_order` VALUES ('63', '201811090929092976', '3', '1', '3', '18475163062', '2018-11-09 09:29:09', null, null, null, '1', '85');
INSERT INTO `tb_order` VALUES ('64', '201811091054065552', '1', '1', '1', '18475163062', '2018-11-09 10:54:06', null, null, null, '1', '80');

-- ----------------------------
-- Table structure for `tb_orderitem`
-- ----------------------------
DROP TABLE IF EXISTS `tb_orderitem`;
CREATE TABLE `tb_orderitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` int(11) NOT NULL COMMENT '商品数量',
  `order_id` bigint(20) DEFAULT NULL COMMENT '外键(指向订单表)',
  `product_id` bigint(20) NOT NULL COMMENT '外键(指向产品表)',
  `user_id` bigint(20) NOT NULL COMMENT '外键(指向用户表)',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `orderItem_order` (`order_id`) USING BTREE,
  KEY `orderItem_product` (`product_id`) USING BTREE,
  KEY `orderItem_user` (`user_id`),
  CONSTRAINT `orderItem_order` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orderItem_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orderItem_user` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_orderitem
-- ----------------------------
INSERT INTO `tb_orderitem` VALUES ('35', '1', '52', '99', '80');
INSERT INTO `tb_orderitem` VALUES ('40', '1', null, '136', '80');
INSERT INTO `tb_orderitem` VALUES ('44', '2', null, '138', '80');
INSERT INTO `tb_orderitem` VALUES ('46', '1', null, '99', '80');
INSERT INTO `tb_orderitem` VALUES ('49', '4', '56', '99', '80');
INSERT INTO `tb_orderitem` VALUES ('50', '1', null, '108', '80');
INSERT INTO `tb_orderitem` VALUES ('51', '1', null, '99', '85');
INSERT INTO `tb_orderitem` VALUES ('56', '2', '61', '136', '85');
INSERT INTO `tb_orderitem` VALUES ('57', '5', '62', '136', '85');
INSERT INTO `tb_orderitem` VALUES ('58', '4', '63', '99', '85');
INSERT INTO `tb_orderitem` VALUES ('59', '4', '64', '106', '80');
INSERT INTO `tb_orderitem` VALUES ('60', '1', null, '106', '80');

-- ----------------------------
-- Table structure for `tb_product`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `subtitle` varchar(255) NOT NULL COMMENT '小标题',
  `original_price` decimal(10,2) NOT NULL COMMENT '原价格',
  `promote_price` decimal(10,2) NOT NULL COMMENT '优惠价格',
  `stock` int(11) NOT NULL COMMENT '库存',
  `sale_Count` int(11) DEFAULT NULL COMMENT '销量',
  `review_Count` int(11) DEFAULT NULL COMMENT '评价总数',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `category_id` bigint(20) NOT NULL COMMENT '外键(指向分类表)',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `product_category` (`category_id`) USING BTREE,
  CONSTRAINT `product_category` FOREIGN KEY (`category_id`) REFERENCES `tb_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES ('99', '心理学与生活', '第十九版', '128.00', '102.40', '31', '9', '1', '2018-10-23 09:46:43', '38');
INSERT INTO `tb_product` VALUES ('100', '社会心理学', '第八版', '68.00', '54.40', '100', '0', '0', '2018-10-23 09:49:20', '38');
INSERT INTO `tb_product` VALUES ('101', '少有人走的路', '心智成熟的旅程', '42.00', '21.00', '200', '0', '0', '2018-10-23 09:51:50', '38');
INSERT INTO `tb_product` VALUES ('102', '乌合之众', '大众心理研究', '36.00', '31.50', '200', '0', '0', '2018-10-23 09:55:41', '38');
INSERT INTO `tb_product` VALUES ('103', '沟通的艺术', '看入人里，看出人外', '88.00', '55.60', '100', '0', '0', '2018-10-23 09:58:02', '38');
INSERT INTO `tb_product` VALUES ('104', '巧手折一折', '纸玩偶', '26.00', '16.30', '76', '124', '0', '2018-10-23 10:01:12', '39');
INSERT INTO `tb_product` VALUES ('105', '永恒的时光之旅', '假装有小标题', '79.00', '59.30', '200', '0', '0', '2018-10-23 10:04:58', '39');
INSERT INTO `tb_product` VALUES ('106', '寻路中国', '从乡村到工厂的自驾之旅', '38.00', '29.90', '196', '4', '0', '2018-10-23 10:07:15', '39');
INSERT INTO `tb_product` VALUES ('107', '荒野之歌', '国际野生生物摄影年赛精选', '180.00', '135.00', '50', '0', '0', '2018-10-23 10:09:41', '39');
INSERT INTO `tb_product` VALUES ('108', '论摄影', '插图珍藏本', '38.00', '28.20', '66', '0', '0', '2018-10-23 10:12:19', '39');
INSERT INTO `tb_product` VALUES ('109', '被人遗忘的人', '中国精神病人生存状况', '380.00', '285.00', '5', '0', '0', '2018-10-23 10:15:25', '39');
INSERT INTO `tb_product` VALUES ('110', '雅舍谈吃', '小标题？', '79.00', '25.40', '100', '0', '0', '2018-10-23 10:18:48', '39');
INSERT INTO `tb_product` VALUES ('134', '小王子', '假装有标题', '39.80', '33.50', '99', '0', '0', '2018-10-23 16:29:17', '45');
INSERT INTO `tb_product` VALUES ('135', '夜莺与玫瑰', 'The Nightingale and the Rose', '56.00', '47.20', '123', '0', '0', '2018-10-23 16:31:19', '45');
INSERT INTO `tb_product` VALUES ('136', 'COMPUTER SYSTEMS', '深入理解计算机系统英文原版', '239.00', '227.10', '3', '7', '0', '2018-10-23 16:53:15', '47');
INSERT INTO `tb_product` VALUES ('138', 'Gone with the Wind', '飘', '99.00', '40.00', '88', '8', '0', '2018-10-23 16:57:36', '47');
INSERT INTO `tb_product` VALUES ('162', '心理学', '犯罪心理', '234.00', '33.00', '4', '0', '0', '2018-11-13 12:21:51', '38');

-- ----------------------------
-- Table structure for `tb_product_image`
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_image`;
CREATE TABLE `tb_product_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `picture` varchar(255) NOT NULL COMMENT '图片路径',
  `product_id` bigint(20) NOT NULL COMMENT '外键(指向产品表)',
  PRIMARY KEY (`id`),
  KEY `image_product` (`product_id`),
  CONSTRAINT `image_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_image
-- ----------------------------
INSERT INTO `tb_product_image` VALUES ('105', '/image/upload/20181112104521055_749054.jpg', '101');
INSERT INTO `tb_product_image` VALUES ('106', '/image/upload/20181023095631587_032144.jpg', '102');
INSERT INTO `tb_product_image` VALUES ('107', '/image/upload/20181023095848095_404733.jpg', '103');
INSERT INTO `tb_product_image` VALUES ('108', '/image/upload/20181023100209289_148859.jpg', '104');
INSERT INTO `tb_product_image` VALUES ('109', '/image/upload/20181023100544357_705394.jpg', '105');
INSERT INTO `tb_product_image` VALUES ('110', '/image/upload/20181023100804087_163160.jpg', '106');
INSERT INTO `tb_product_image` VALUES ('111', '/image/upload/20181023101035051_648230.jpg', '107');
INSERT INTO `tb_product_image` VALUES ('112', '/image/upload/20181023101308922_800869.jpg', '108');
INSERT INTO `tb_product_image` VALUES ('113', '/image/upload/20181023101608846_378596.jpg', '109');
INSERT INTO `tb_product_image` VALUES ('114', '/image/upload/20181023101919764_944058.jpg', '110');
INSERT INTO `tb_product_image` VALUES ('137', '/image/upload/20181023163332845_389741.jpg', '134');
INSERT INTO `tb_product_image` VALUES ('138', '/image/upload/20181023163344967_987827.jpg', '135');
INSERT INTO `tb_product_image` VALUES ('139', '/image/upload/20181023165333411_738415.jpg', '136');
INSERT INTO `tb_product_image` VALUES ('140', '/image/upload/20181023165753075_973580.jpg', '138');
INSERT INTO `tb_product_image` VALUES ('141', '/image/upload/20181105112324273_292503.jpg', '99');
INSERT INTO `tb_product_image` VALUES ('142', '/image/upload/20181105120031936_470631.jpg', '99');
INSERT INTO `tb_product_image` VALUES ('143', '/image/upload/20181105151014649_830707.jpg', '99');
INSERT INTO `tb_product_image` VALUES ('144', '/image/upload/20181105151027661_338453.jpg', '138');
INSERT INTO `tb_product_image` VALUES ('153', '/image/upload/20181106112108842_269198.jpg', '99');
INSERT INTO `tb_product_image` VALUES ('155', '/image/upload/20181106112316629_768255.jpg', '99');
INSERT INTO `tb_product_image` VALUES ('158', '/image/upload/20181114161836844_624441.jpg', '101');

-- ----------------------------
-- Table structure for `tb_property`
-- ----------------------------
DROP TABLE IF EXISTS `tb_property`;
CREATE TABLE `tb_property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '属性名称',
  `category_id` bigint(20) NOT NULL COMMENT '外键(指向分类表)',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `property_category` (`category_id`) USING BTREE,
  CONSTRAINT `property_category` FOREIGN KEY (`category_id`) REFERENCES `tb_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_property
-- ----------------------------
INSERT INTO `tb_property` VALUES ('154', '99', '38');
INSERT INTO `tb_property` VALUES ('155', '55', '38');
INSERT INTO `tb_property` VALUES ('156', '33', '38');
INSERT INTO `tb_property` VALUES ('157', '666', '38');
INSERT INTO `tb_property` VALUES ('158', '555', '38');
INSERT INTO `tb_property` VALUES ('161', '666', '38');

-- ----------------------------
-- Table structure for `tb_propertyvalue`
-- ----------------------------
DROP TABLE IF EXISTS `tb_propertyvalue`;
CREATE TABLE `tb_propertyvalue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `value` varchar(255) NOT NULL COMMENT '属性值',
  `product_id` bigint(11) NOT NULL COMMENT '外键(指向产品表)',
  `property_id` bigint(11) NOT NULL COMMENT '外键(指向属性表)',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `propertyvalue_product` (`product_id`) USING BTREE,
  KEY `propertyvalue_property` (`property_id`) USING BTREE,
  CONSTRAINT `propertyvalue_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `propertyvalue_property` FOREIGN KEY (`property_id`) REFERENCES `tb_property` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_propertyvalue
-- ----------------------------
INSERT INTO `tb_propertyvalue` VALUES ('31', '7767', '99', '155');
INSERT INTO `tb_propertyvalue` VALUES ('32', '5656', '99', '156');
INSERT INTO `tb_propertyvalue` VALUES ('33', 'uihib56', '99', '157');
INSERT INTO `tb_propertyvalue` VALUES ('34', '5656', '99', '158');
INSERT INTO `tb_propertyvalue` VALUES ('36', '77676', '99', '154');

-- ----------------------------
-- Table structure for `tb_review`
-- ----------------------------
DROP TABLE IF EXISTS `tb_review`;
CREATE TABLE `tb_review` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(255) NOT NULL COMMENT '评价内容',
  `create_date` datetime NOT NULL COMMENT '评价时间',
  `user_id` bigint(11) NOT NULL COMMENT '外键(指向用户表)',
  `product_id` bigint(11) NOT NULL COMMENT '外键(指向产品表)',
  PRIMARY KEY (`id`),
  KEY `review_user` (`user_id`),
  KEY `review_product` (`product_id`),
  CONSTRAINT `review_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `review_user` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_review
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_upload`
-- ----------------------------
DROP TABLE IF EXISTS `tb_upload`;
CREATE TABLE `tb_upload` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `versionCode` int(11) NOT NULL COMMENT '版本号',
  `filename` varchar(255) NOT NULL COMMENT '文件名',
  `fileurl` varchar(255) NOT NULL COMMENT '文件路径',
  `developer` varchar(10) NOT NULL COMMENT '开发者',
  `turnover_time` datetime NOT NULL COMMENT '更新的时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `devaloper` (`developer`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_upload
-- ----------------------------
INSERT INTO `tb_upload` VALUES ('34', '23', '20180920132651189_469256.apk', '/app/download/XC/20181029093713331_898584.apk', 'xc', '2018-10-29 09:37:13');
INSERT INTO `tb_upload` VALUES ('35', '1', 'app-release.apk', '/app/download/LM/20181024170952390_671525.apk', 'lm', '2018-10-24 17:09:52');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `mobile_phone` varchar(20) NOT NULL COMMENT '手机号',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `role_type` tinyint(4) NOT NULL COMMENT '角色',
  `freeze` int(4) NOT NULL COMMENT '冻结',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('67', '13138775400', '123456', '13138775400', '12345678@qq.com', '2', '1');
INSERT INTO `tb_user` VALUES ('71', '13138775401', 'f711b06860c31d425619f22dba703261', '13138775401', '', '2', '1');
INSERT INTO `tb_user` VALUES ('72', '17878787878', '04b40a7305d5591a4b71f3f18c26e5f4', '17878787878', '2104782910@qq.com', '2', '1');
INSERT INTO `tb_user` VALUES ('76', '18475184170', '603f39718cb4844653b511044bb0ef2f', '18475184170', '1@qq.com', '2', '1');
INSERT INTO `tb_user` VALUES ('77', '18475163062', 'f3ad62cb1ce8c7622eb34526ba9182d9', '18475163062', '1@qq.com', '2', '1');
INSERT INTO `tb_user` VALUES ('78', '18475184171', '67925c9357558f0847f435907c320e7d', '18475184171', '1@qq.com', '2', '1');
INSERT INTO `tb_user` VALUES ('79', '18475184172', '811fde7747103d458b4d185fded0379b', '18475184172', '1@qq.com', '2', '1');
INSERT INTO `tb_user` VALUES ('80', '17876786701', 'f5babed910a64bdd2a273b59f449c09b', '17876786701', '2104782910@qq.com', '2', '1');
INSERT INTO `tb_user` VALUES ('81', '17876781213', '5ee7dd93881d3621b51d258f522d4987', '17876781213', '2111111@qq.com', '2', '1');
INSERT INTO `tb_user` VALUES ('82', '17876789511', '32a3bad31c56dc9eb36debeb163ec686', '17876789511', '1133@qq.com', '2', '1');
INSERT INTO `tb_user` VALUES ('85', '18475794899', '85fa61b87c427cb9b61132a971522026', '18475794899', '', '1', '1');
