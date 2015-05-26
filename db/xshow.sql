/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : xshow

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2015-05-22 17:01:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` varchar(40) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `clickRate` int(11) DEFAULT NULL,
  `content` text,
  `coverImgUrl` varchar(255) DEFAULT NULL,
  `issuedDate` datetime DEFAULT NULL,
  `pathNumber` int(11) DEFAULT NULL,
  `qrCodeUrl` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `summary` text,
  `title` varchar(255) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `menubar_Id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mjnibpio12cbq5ukjqqlnvy70` (`menubar_Id`),
  CONSTRAINT `FK_mjnibpio12cbq5ukjqqlnvy70` FOREIGN KEY (`menubar_Id`) REFERENCES `menubar` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('2c9087204d69bb2e014d69d675200001', '曹', '0', '<ul class=\"r-content list-paddingleft-2\" style=\"list-style-type: none;\"><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; padding: 0px; border: 0px; outline: 0px; vertical-align: baseline; text-indent: 2em; background: transparent;\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: 0px; outline: 0px; vertical-align: baseline; letter-spacing: 0px; line-height: 1.75em; font-family: 宋体, SimSun; color: rgb(0, 0, 0); background: transparent;\">4月15日，四川博览局与省委农工委举行座谈会，专题研究第三届四川农业博览会暨成都国际都市现代农业博览会（以下简称“农博会”）筹备工作。四川博览局局长郑莉、副局长杨庆龙与省委农工委副主任杨秀彬、副主任刘铁及两家单位相关处室负责同志参会。</span></p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; padding: 0px; border: 0px; outline: 0px; vertical-align: baseline; text-indent: 2em; background: transparent;\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: 0px; outline: 0px; vertical-align: baseline; letter-spacing: 0px; line-height: 1.75em; font-family: 宋体, SimSun; color: rgb(0, 0, 0); background: transparent;\">四川农博会是在国家清理和规范庆典研讨会论坛展会工作后，经国家批准，与中国西部国际博览会、中国（绵阳）科技城国际科技博览会一并作为我省继续保留举办 的政府主导性3项展会活动之一。四川农博会今年首次与成都国际都市现代农业博览会合并举办，进一步彰显农博会服务现代农业、促进开放合作的强大平台功能。 座谈会上，双方就农博会总体方案、工作分工、经费预算及管理方式等重要事宜进行了商谈，基本达成共识，为有序推进后期工作奠定了良好基础。&nbsp;</span></p><p style=\"box-sizing: border-box; margin-top: 0px; margin-bottom: 10px; padding: 0px; border: 0px; outline: 0px; vertical-align: baseline; text-indent: 2em; background: transparent;\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: 0px; outline: 0px; vertical-align: baseline; letter-spacing: 0px; line-height: 1.75em; font-family: 宋体, SimSun; color: rgb(0, 0, 0); background: transparent;\">本届农博会由四川省人民政府主办，省委农工委、四川博览局、成都市人民政府等单位共同承办，将于11月19日—23日在成都世纪城国际会展中心举行，拟设新型农业经营主体馆、农业合作馆等7大展馆（展览面积10万平方米），并配套举办相关活动。</span></p></ul><p><br/></p>', null, '2015-05-19 09:42:51', '10010', null, 'df', '<p>asdf</p>', '公司简介', '2015-05-19 09:42:51', '2c9087204d69bb2e014d69d6025b0000');
INSERT INTO `article` VALUES ('2c9087204d6b31b5014d6b3340590000', '', '14', '<p style=\"margin-top: 15px; margin-bottom: 15px; padding: 0px; line-height: 2em; font-family: &#39;Microsoft YaHei&#39;, u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53; white-space: normal; text-align: center;\"><img alt=\"台媒:中泰拟建运河绕开马六甲海峡可破美封锁\" src=\"/upload/image/20150519/1432022629657071976.png\" style=\"margin: 0px; padding: 0px; border: none;\"/></p><p style=\"margin-top: 15px; margin-bottom: 15px; padding: 0px; line-height: 2em; font-family: &#39;Microsoft YaHei&#39;, u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53; white-space: normal; text-align: center;\"><span style=\"font-size: 12px;\">克拉运河位置示意图</span></p><p style=\"margin-top: 15px; margin-bottom: 15px; padding: 0px; line-height: 2em; font-family: &#39;Microsoft YaHei&#39;, u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53; white-space: normal;\">　　参考消息网5月19日报道 台媒称，继中巴经济走廊、中俄高铁等项目后，大陆推动“一带一路”布局又有新动作。据《南方日报》14日报道，中泰两国在广州签署“克拉运河”合作备忘录，让这项延宕10年的世纪大工程向正式开工跨出一大步。初步预估，该项目需耗时10年、投资总额280亿美元，一旦开通，大陆的“马六甲困局”也将迎刃而解。</p><p style=\"margin-top: 15px; margin-bottom: 15px; padding: 0px; line-height: 2em; font-family: &#39;Microsoft YaHei&#39;, u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53; white-space: normal;\">　　据台湾中时电子报5月18日报道，“克拉运河”是指从泰国克拉地峡区域，挖掘一条沟通太平洋的泰国湾与印度洋的安达曼海的运河，修建完成后，国际海运线将不必绕过新加坡、取道马六甲海峡，航程至少缩短约1200公里，可省2至5天航运时间；以10万吨油轮来算，单次能省下35万美元的运费。</p><p style=\"margin-top: 15px; margin-bottom: 15px; padding: 0px; line-height: 2em; font-family: &#39;Microsoft YaHei&#39;, u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53; white-space: normal;\">　　报道援引港媒消息称，在中泰签署合作备忘录后，这条全长102公里的双向航道运河将可正式开建，初估需投入10年时间和280亿美元，若动用核能技术等非常规施工方法，则可望7年完工，但是投资总额将暴增至360亿美元。</p><p style=\"margin-top: 15px; margin-bottom: 15px; padding: 0px; line-height: 2em; font-family: &#39;Microsoft YaHei&#39;, u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53; white-space: normal;\">　　马六甲海峡是世界上最繁忙的海峡之一，每年约有8万艘船只、装载价值约5000亿美元的货物，通过这条海峡，将来“克拉运河”建成后，控制咽喉位置的泰国，将是最大获利者之一。不过，早前调查显示，泰国只有3成民众支持兴建此运河，至少4成反对，主要是担心该工程可能造成泰国政治动荡，包含破坏生态和政府贪污。</p><p><br/></p>', null, '2015-05-19 16:03:50', '10011', null, '', '<p>阿道夫更好的</p>', '中泰拟建克拉运河', '2015-05-22 09:01:05', '2c9087204d6a23cc014d6ab08ed10000');
INSERT INTO `article` VALUES ('2c9087204d6b31b5014d6b33de6c0001', '', '6', '<p style=\"text-align:center;padding-bottom: 10px; color: rgb(255, 255, 255); font-family: 宋体; font-size: 14px; line-height: 25px; white-space: normal; background-color: rgb(51, 51, 51);\"><img src=\"/upload/image/20150519/1432022669853003758.jpg\" style=\"border: 0px;\"/></p><p><br style=\"color: rgb(255, 255, 255); font-family: 宋体; font-size: 14px; line-height: 25px; white-space: normal; background-color: rgb(51, 51, 51);\"/><br style=\"color: rgb(255, 255, 255); font-family: 宋体; font-size: 14px; line-height: 25px; white-space: normal; background-color: rgb(51, 51, 51);\"/><span style=\"color: rgb(255, 255, 255); font-family: 宋体; font-size: 14px; line-height: 25px; background-color: rgb(51, 51, 51);\">新华社照片，东莞，2015年5月16日 （苏迪曼杯）（19）羽毛球——半决赛：韩国战日本 5月16日，日本选手松友美佐纪/高桥礼华（左）在比赛中，她们在女双比赛中以2比0战胜韩国选手张艺娜/郑景银。 当日，在广东东莞市篮球中心举行的2015苏迪曼杯世界羽毛球混合团体锦标赛半决赛中，韩国队迎战日本队。 新华社记者梁旭摄</span></p><p><br/></p>', null, '2015-05-19 16:04:30', '10012', null, '', null, '苏迪曼杯半决赛', '2015-05-22 14:10:44', '2c9087204d6a23cc014d6ab08ed10000');
INSERT INTO `article` VALUES ('2c9087204d742d17014d74305ea80000', '的地方', '4', null, '/upload/image/20150521/1432173435550053622.jpg', '2015-05-21 09:57:16', '10014', null, 'sdg', null, '成功案例1', '2015-05-22 09:09:44', '2c9087204d6f2bf8014d6f2cfbff0000');
INSERT INTO `article` VALUES ('2c9087204d791c22014d7969bf690000', '', '0', '<p>asdasdf</p>', '/upload/image/20150522/1432261082317011724.jpg', '2015-05-22 10:18:02', '10015', null, 'df', '<p>asd</p>', 'dg', '2015-05-22 10:18:02', '2c9087204d6b31b5014d6b35a5540002');
INSERT INTO `article` VALUES ('2c9087204d791c22014d7969e4cc0001', '', '0', null, '/upload/image/20150522/1432261091598052461.jpg', '2015-05-22 10:18:12', '10016', null, '', null, 'ghdgh', '2015-05-22 10:18:12', '2c9087204d6b31b5014d6b35a5540002');
INSERT INTO `article` VALUES ('2c9087204d791c22014d796a07c20002', '', '0', null, '/upload/image/20150522/1432261100637003684.jpg', '2015-05-22 10:18:21', '10017', null, '', null, 'gsdg', '2015-05-22 10:18:21', '2c9087204d6b31b5014d6b35a5540002');
INSERT INTO `article` VALUES ('2c9087204d791c22014d796a2a810003', '', '0', null, '/upload/image/20150522/1432261109579004129.jpg', '2015-05-22 10:18:30', '10018', null, '', null, 'dfghdgh', '2015-05-22 10:18:30', '2c9087204d6b31b5014d6b35a5540002');
INSERT INTO `article` VALUES ('2c9087204d791c22014d796a791d0004', '', '0', null, '/upload/image/20150522/1432261129529050751.jpg', '2015-05-22 10:18:50', '10019', null, '', null, 'fghjfgj', '2015-05-22 10:18:50', '2c9087204d6b31b5014d6b35a5540002');
INSERT INTO `article` VALUES ('2c9087204d791c22014d796ae8470005', '', '0', null, '/upload/image/20150522/1432261158277092554.jpg', '2015-05-22 10:19:18', '10020', null, '', null, 'sdgfgd', '2015-05-22 10:19:18', '2c9087204d6b31b5014d6b35a5540002');
INSERT INTO `article` VALUES ('2c9087204d791c22014d796b05b20006', '', '0', null, '/upload/image/20150522/1432279010675064655.jpg', null, null, null, '', null, 'a1', '2015-05-22 15:16:51', '2c9087204d6b31b5014d6b35a5540002');
INSERT INTO `article` VALUES ('2c9087204d791c22014d796b21d20007', '', '0', null, '/upload/image/20150522/1432261173148026965.jpg', '2015-05-22 10:19:33', '10022', null, '', null, 'sdfg', '2015-05-22 10:19:33', '2c9087204d6b31b5014d6b35a5540002');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` varchar(40) NOT NULL,
  `imgUrl` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES ('2c9087204d73ebe5014d7407c1e10000', '/upload/image/20150522/1432278839006061522.jpg', '		               		               		               		               		               \r\n		             \r\n		             \r\n		             \r\n		             \r\n		             ');
INSERT INTO `banner` VALUES ('2c9087204d73ebe5014d74113c600001', '/upload/image/20150522/1432278844933066623.jpg', '		               		               		               		               \r\n		             \r\n		             \r\n		             \r\n		             ');

-- ----------------------------
-- Table structure for menubar
-- ----------------------------
DROP TABLE IF EXISTS `menubar`;
CREATE TABLE `menubar` (
  `id` varchar(40) NOT NULL,
  `addDate` datetime DEFAULT NULL,
  `descrip` varchar(255) DEFAULT NULL,
  `enable` int(11) NOT NULL,
  `linkUrl` varchar(255) DEFAULT NULL,
  `menuLevel` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `navType` int(11) DEFAULT NULL,
  `ord` varchar(255) DEFAULT NULL,
  `menubar_id` varchar(40) DEFAULT NULL,
  `editDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_504st27x4hc2a11yyuauwndlh` (`menubar_id`),
  CONSTRAINT `FK_504st27x4hc2a11yyuauwndlh` FOREIGN KEY (`menubar_id`) REFERENCES `menubar` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menubar
-- ----------------------------
INSERT INTO `menubar` VALUES ('2c9087204ce4ffc3014ce50897f70000', null, null, '1', null, '10012', '阿斯蒂芬2', '10011', '1', '2c9087204ce4ffc3014ce50897f70000', '2015-04-23 17:45:24');
INSERT INTO `menubar` VALUES ('2c9087204ce5a549014ce5a7b4c70000', '2015-04-23 17:41:55', null, '1', null, '10012', '豆腐干地方', '10011', '2', '2c9087204ce4ffc3014ce50897f70000', '2015-04-23 17:41:55');
INSERT INTO `menubar` VALUES ('2c9087204d69bb2e014d69d6025b0000', null, null, '1', 'gsjs', '10011', '公司简介', '10011', '6', null, '2015-05-20 15:01:35');
INSERT INTO `menubar` VALUES ('2c9087204d6a23cc014d6ab08ed10000', '2015-05-19 13:41:05', null, '1', 'xwzx', '10011', '新闻中心', '10012', '2', null, '2015-05-19 13:41:05');
INSERT INTO `menubar` VALUES ('2c9087204d6b31b5014d6b35a5540002', null, null, '1', 'cpzx', '10011', '产品中心', '10013', '1', null, '2015-05-20 15:01:56');
INSERT INTO `menubar` VALUES ('2c9087204d6f2bf8014d6f2cfbff0000', '2015-05-20 10:35:28', null, '1', 'cgal', '10011', '成功案例', '10014', '5', null, '2015-05-20 10:35:28');

-- ----------------------------
-- Table structure for orginfo
-- ----------------------------
DROP TABLE IF EXISTS `orginfo`;
CREATE TABLE `orginfo` (
  `id` varchar(40) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contactPhone` varchar(255) DEFAULT NULL,
  `contactTel` varchar(255) DEFAULT NULL,
  `contacter` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `icp` varchar(255) DEFAULT NULL,
  `orgName` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orginfo
-- ----------------------------
INSERT INTO `orginfo` VALUES ('2c9087204d2da524014d2da729950000', 'ASD', '028999', '187801562', 'dkll', 'AA@WW.A', '123', 'ak6', '/upload/image/20150508/1431046750596080987.jpg');

-- ----------------------------
-- Table structure for sys_account_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_account_role`;
CREATE TABLE `sys_account_role` (
  `id` varchar(40) NOT NULL,
  `roleId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_account_role
-- ----------------------------
INSERT INTO `sys_account_role` VALUES ('2c9080ed4c606831014c6876a3d10062', '2c9080ed4c606831014c686182360039', '2c9080ed4c606831014c68693c3d0061');
INSERT INTO `sys_account_role` VALUES ('2c9080ed4c78abf7014c79090cb4000d', '2c9080ed4c78abf7014c7906adb5000a', '2c9080ed4c78abf7014c7908cb46000c');
INSERT INTO `sys_account_role` VALUES ('2c9087e34b04fa1b014b04fbd02f0002', '1', '1');

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
  `id` varchar(40) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `areaId` varchar(255) DEFAULT NULL,
  `dept` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `operationDate` datetime DEFAULT NULL,
  `operationUser` varchar(255) DEFAULT NULL,
  `parentId` varchar(255) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `submit` int(11) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `userAmount` int(11) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `userStatus` varchar(255) DEFAULT NULL,
  `userType` varchar(255) DEFAULT NULL,
  `userpwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES ('1', '中国成都', null, '网站管理部', '2hz@163.com', null, '15908158601', '管理员', null, null, null, null, null, null, '15908158601', null, null, 'admin', '1', null, '3BCBA89099EC29F8283F2E273499F4D8E878C02A14ED95CF1290C6C5');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` varchar(40) NOT NULL,
  `entryUrl` varchar(255) DEFAULT NULL,
  `menubarId` varchar(255) DEFAULT NULL,
  `operationDate` datetime DEFAULT NULL,
  `operationUser` varchar(255) DEFAULT NULL,
  `parentId` varchar(255) DEFAULT NULL,
  `parentname` varchar(255) DEFAULT NULL,
  `resourceCode` varchar(255) DEFAULT NULL,
  `resourceLabel` varchar(255) DEFAULT NULL,
  `resourceName` varchar(255) DEFAULT NULL,
  `resourceOrder` int(11) DEFAULT NULL,
  `resourcePath` varchar(255) DEFAULT NULL,
  `resourceType` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `statusname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('0580662a-0fb7-11e3-a65f-080027006cb5', null, null, null, null, 'da91c1cd-0ec6-11e3-a839-080027006cb5', null, 'del', '删除', '资源删除', '0', '/资源管理/资源删除', '2', '1', null);
INSERT INTO `sys_resource` VALUES ('080027006cb5', null, null, null, null, '9eeac284-0fb5-11e3-a65f-080027006cb5', null, 'add', '添加', '帐号添加', '0', '/帐号管理/帐号添加', '2', '1', null);
INSERT INTO `sys_resource` VALUES ('1ee77832-0fb6-11e3-a65f-080027006cb5', null, null, null, null, '8df353a1-0fb5-11e3-a65f-080027006cb5', null, 'edit', '编辑', '角色编辑', '0', '/角色管理/角色编辑', '2', '1', null);
INSERT INTO `sys_resource` VALUES ('20596588-0ec7-11e3-a839-080027006cb5', null, null, null, null, 'da91c1cd-0ec6-11e3-a839-080027006cb5', null, 'add', '添加', '资源添加', '0', '/资源管理/资源添加', '2', '1', null);
INSERT INTO `sys_resource` VALUES ('24f633ba-1075-11e3-a4c0-080027006cb5', null, null, null, null, '9eeac284-0fb5-11e3-a65f-080027006cb5', null, 'auth', '授权', '帐号授权', '0', '/帐号管理/帐号授权', '2', '1', null);
INSERT INTO `sys_resource` VALUES ('2c9087204cd9e6b0014cd9e861990000', '/view/menubar/jsp/menubar.jsp', '', null, null, '2c9087204cd9e6b0014cd9e9806d0001', null, 'lmgl', null, '栏目管理', null, null, '2', '1', null);
INSERT INTO `sys_resource` VALUES ('2c9087204cd9e6b0014cd9e9806d0001', '/view/menubar/jsp/menubar.jsp', '', null, null, '0', null, 'wzgl', null, '网站管理', null, null, '1', '1', null);
INSERT INTO `sys_resource` VALUES ('2c9087204cea0262014cea8d421f0000', '/view/article/jsp/article.jsp', '', null, null, '2c9087204cd9e6b0014cd9e9806d0001', null, 'xxgl', null, '信息管理', null, null, '2', '1', null);
INSERT INTO `sys_resource` VALUES ('2c9087204d2d705a014d2d815bac0000', '/org/getInfo', '', null, null, '2c9087204cd9e6b0014cd9e9806d0001', null, 'jgxx', null, '机构信息', null, null, '2', '1', null);
INSERT INTO `sys_resource` VALUES ('2c9087204d320d59014d320e50350000', '/view/banner/jsp/banner.jsp', '', null, null, '2c9087204cd9e6b0014cd9e9806d0001', null, 'bjtgl', null, '背景图管理', null, null, '1', '1', null);
INSERT INTO `sys_resource` VALUES ('394c1821-0fb6-11e3-a65f-080027006cb5', '', null, null, null, '8df353a1-0fb5-11e3-a65f-080027006cb5', null, 'del', '删除', '角色删除', '0', '/角色管理/角色删除', '2', '1', null);
INSERT INTO `sys_resource` VALUES ('3ee6e1e2-0eb8-11e3-a839-080027006cb5', null, null, null, null, '0', null, 'jmYnee', '系统管理', '系统管理', '3', '/系统管理', '1', '1', null);
INSERT INTO `sys_resource` VALUES ('65fd80d6-0fb6-11e3-a65f-080027006cb5', null, null, null, '1', '8df353a1-0fb5-11e3-a65f-080027006cb5', null, 'auth', '授权', '角色授权', '0', '/角色管理/角色授权', '2', '1', null);
INSERT INTO `sys_resource` VALUES ('7d6dd885-1081-11e3-a4c0-080027006cb5', '', null, null, null, '8df353a1-0fb5-11e3-a65f-080027006cb5', null, 'query', '查询', '角色查询', '0', '/角色管理/角色查询', '2', '1', null);
INSERT INTO `sys_resource` VALUES ('8df353a1-0fb5-11e3-a65f-080027006cb5', '/view/admin/jsp/role.jsp', null, null, null, '3ee6e1e2-0eb8-11e3-a839-080027006cb5', null, 'QZb6fy', '角色管理', '角色管理', '0', '/系统管理/角色管理', '1', '1', null);
INSERT INTO `sys_resource` VALUES ('97acbad5-1081-11e3-a4c0-080027006cb5', null, null, null, null, '9eeac284-0fb5-11e3-a65f-080027006cb5', null, 'query', '查询', '帐号查询', '0', '/帐号管理/帐号查询', '2', '1', null);
INSERT INTO `sys_resource` VALUES ('9eeac284-0fb5-11e3-a65f-080027006cb5', '/view/admin/jsp/admin.jsp', null, null, null, '3ee6e1e2-0eb8-11e3-a839-080027006cb5', null, 'yMfUJz', '帐号管理', '帐号管理', '0', '/系统管理/帐号管理', '1', '1', null);
INSERT INTO `sys_resource` VALUES ('a91f384d-1074-11e3-a4c0-080027006cb5', null, null, null, null, '9eeac284-0fb5-11e3-a65f-080027006cb5', null, 'edit', '编辑', '帐号编辑', '0', '/帐号管理/帐号编辑', '2', '1', null);
INSERT INTO `sys_resource` VALUES ('b0b93e02-1081-11e3-a4c0-080027006cb5', null, null, null, null, 'da91c1cd-0ec6-11e3-a839-080027006cb5', null, 'query', '查询', '资源查询', '0', '/资源管理/资源查询', '2', '1', null);
INSERT INTO `sys_resource` VALUES ('e93b9896-0fb6-11e3-a65f-080027006cb5', null, null, null, null, 'da91c1cd-0ec6-11e3-a839-080027006cb5', null, 'edit', '编辑', '资源编辑', '0', '/资源管理/资源编辑', '2', '1', null);
INSERT INTO `sys_resource` VALUES ('fd0ee537-1074-11e3-a4c0-080027006cb5', null, null, null, null, '9eeac284-0fb5-11e3-a65f-080027006cb5', null, 'del', '删除', '帐号删除', '0', '/帐号管理/帐号删除', '2', '1', null);
INSERT INTO `sys_resource` VALUES ('ff923e70-0fb5-11e3-a65f-080027006cb5', null, null, null, null, '8df353a1-0fb5-11e3-a65f-080027006cb5', null, 'add', '添加', '角色添加', '0', '/角色管理/角色添加', '2', '1', null);
INSERT INTO `sys_resource` VALUES ('ff923e70-0fb5-11e3-a65f-080027046cb5', '/view/admin/jsp/resource.jsp', null, null, null, '3ee6e1e2-0eb8-11e3-a839-080027006cb5', null, 'QZb63y', '资源管理', '资源管理', '0', '/系统管理/资源管理', '1', '1', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(40) NOT NULL,
  `operationDate` datetime DEFAULT NULL,
  `operationUser` varchar(255) DEFAULT NULL,
  `parentRoleId` varchar(255) DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', null, null, null, '超级管理员', '1');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` varchar(40) NOT NULL,
  `resourceId` varchar(255) DEFAULT NULL,
  `roleId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('2c9080ed4b48d39a014b48e26a3b3070', '2c9087e34b0b1a0c014b0b20e0350004', '2c9087e34b04f444014b04f4a8390000');
INSERT INTO `sys_role_resource` VALUES ('2c9080ed4b48d39a014b48e26a3b3071', '2c9087e34b0b1a0c014b0b21dc2c0005', '2c9087e34b04f444014b04f4a8390000');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69140001', '0580662a-0fb7-11e3-a65f-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69140002', '1ee77832-0fb6-11e3-a65f-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69140003', '20596588-0ec7-11e3-a839-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69140004', '24f633ba-1075-11e3-a4c0-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69140005', '394c1821-0fb6-11e3-a65f-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69140006', '3ee6e1e2-0eb8-11e3-a839-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69150007', '65fd80d6-0fb6-11e3-a65f-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69150008', '7d6dd885-1081-11e3-a4c0-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69150009', '8df353a1-0fb5-11e3-a65f-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e6915000a', '97acbad5-1081-11e3-a4c0-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e6915000b', '9eeac284-0fb5-11e3-a65f-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e6915000c', 'a91f384d-1074-11e3-a4c0-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e6915000d', 'b0b93e02-1081-11e3-a4c0-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e6915000e', 'e93b9896-0fb6-11e3-a65f-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e6915000f', 'fd0ee537-1074-11e3-a4c0-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69150010', 'ff923e70-0fb5-11e3-a65f-080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69150011', 'ff923e70-0fb5-11e3-a65f-080027046cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69150012', '080027006cb5', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69150013', '2c9087204cd9e6b0014cd9e861990000', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69150014', '2c9087204cd9e6b0014cd9e9806d0001', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69150015', '2c9087204cea0262014cea8d421f0000', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69150016', '2c9087204d2d705a014d2d815bac0000', '1');
INSERT INTO `sys_role_resource` VALUES ('2c9087204d320d59014d320e69160017', '2c9087204d320d59014d320e50350000', '1');
