/*
Navicat MySQL Data Transfer

Source Server         : study
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : dszcrm

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2020-06-09 10:37:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dynamicmessage
-- ----------------------------
DROP TABLE IF EXISTS `t_dynamicmessage`;
CREATE TABLE `t_dynamicmessage` (
  `did` varchar(100) NOT NULL COMMENT 'UUID生成机制，主键',
  `sid` varchar(100) DEFAULT NULL COMMENT '外键，引用客户id',
  `sname` varchar(20) DEFAULT NULL COMMENT '客户名',
  `senderid` varchar(100) DEFAULT NULL COMMENT '发送信息的员工的id',
  `uid` varchar(100) DEFAULT NULL COMMENT '管理学生的咨询师的id',
  `dcontent` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `isread` varchar(255) DEFAULT NULL COMMENT '是否已读消息',
  `dcreatetime` datetime DEFAULT NULL COMMENT '消息发送时间',
  PRIMARY KEY (`did`) USING BTREE,
  KEY `F_stu_dyn` (`sid`) USING BTREE,
  KEY `F_users_dyn` (`uid`) USING BTREE,
  CONSTRAINT `F_stu_dyn` FOREIGN KEY (`sid`) REFERENCES `t_students` (`sid`),
  CONSTRAINT `F_users_dyn` FOREIGN KEY (`uid`) REFERENCES `t_users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_dynamicmessage
-- ----------------------------
INSERT INTO `t_dynamicmessage` VALUES ('1d47c0df-2f11-4838-bd32-28fd62f736ac', '5c6c4c16-a850-4a19-a953-a577868c8b86', '任彭泽', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', 'd9f6b1cd-9dae-4007-8485-35225f656505', '想倾向于学习前端', '已读', '2020-05-24 15:43:59');
INSERT INTO `t_dynamicmessage` VALUES ('35d15b73-c4a5-45a8-ac1c-a7ef70c425ea', 'd03e2865-27f5-4bf9-8ae2-4b03842c1b56', '黄石', '0935c440-4a58-4a7e-ad9b-20306bf311b2', 'd9f6b1cd-9dae-4007-8485-35225f656505', '想去男生多的班', '未读', '2020-05-25 11:10:23');
INSERT INTO `t_dynamicmessage` VALUES ('3ac8198b-c068-472f-9828-8a52acc226e1', '49c8e13b-01e4-477d-bc19-cb4078576912', '张代柔', '0af295c7-68b9-4d27-8121-f1474f3b75d7', 'd9f6b1cd-9dae-4007-8485-35225f656505', '她喜欢C++', '未读', '2020-06-08 22:25:50');
INSERT INTO `t_dynamicmessage` VALUES ('50213dea-a708-4e1c-9628-1b87a50e3f6d', '9770d7a3-2bfe-4371-87c0-a59e1caeec4b', '任白梅', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', 'd9f6b1cd-9dae-4007-8485-35225f656505', '热爱php编程', '已读', '2020-05-24 15:44:37');
INSERT INTO `t_dynamicmessage` VALUES ('5fb18dd2-bebd-42ba-855c-4842e7b94eb0', '5c6c4c16-a850-4a19-a953-a577868c8b86', '任彭泽', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', 'd9f6b1cd-9dae-4007-8485-35225f656505', '测试消息推送', '已读', '2020-05-24 15:44:12');
INSERT INTO `t_dynamicmessage` VALUES ('7cda25db-94ca-4672-b2ea-abada65f8559', 'ce419814-1ef2-47b0-8174-8090edca9dba', '胡列娜', '0935c440-4a58-4a7e-ad9b-20306bf311b2', 'd9f6b1cd-9dae-4007-8485-35225f656505', '你好啊', '已读', '2020-05-18 08:32:58');
INSERT INTO `t_dynamicmessage` VALUES ('94b17dd5-4fd9-42bc-9887-ab5c47871237', '895dc5b5-a7fc-42af-ba49-84acc743b44a', '余新林', '0935c440-4a58-4a7e-ad9b-20306bf311b2', 'd9f6b1cd-9dae-4007-8485-35225f656505', '这位学生有意向在我们这里学习', '已读', '2020-05-16 18:52:39');
INSERT INTO `t_dynamicmessage` VALUES ('a0714603-429b-460e-ae95-c0d8f475d4c6', '895dc5b5-a7fc-42af-ba49-84acc743b44a', '余新林', '0935c440-4a58-4a7e-ad9b-20306bf311b2', 'd9f6b1cd-9dae-4007-8485-35225f656505', '这位同学对网络开发十分感兴趣', '已读', '2020-05-16 18:52:02');
INSERT INTO `t_dynamicmessage` VALUES ('a5da0328-d5af-4b31-92fc-a8987580fe54', '01760231-a6d1-4b63-a81f-77b4013b47c2', '刘山河', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '74791108-e614-4f63-9278-4cbbf1ee5680', '你好', '未读', '2020-05-18 09:41:18');
INSERT INTO `t_dynamicmessage` VALUES ('a76faf94-3781-483c-bf28-272d8eb7fd13', '5c4a979d-525e-43ae-8282-4a260a5cd3e1', '常亦竹', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', 'd9f6b1cd-9dae-4007-8485-35225f656505', '热爱前端开发', '已读', '2020-05-24 15:44:50');
INSERT INTO `t_dynamicmessage` VALUES ('e5fd09b0-a0c6-4d92-ad09-e2fb8e6dc240', '01760231-a6d1-4b63-a81f-77b4013b47c2', '刘山河', '0935c440-4a58-4a7e-ad9b-20306bf311b2', 'd9f6b1cd-9dae-4007-8485-35225f656505', '这个学生对前端非常感兴趣', '已读', '2020-05-23 09:05:41');

-- ----------------------------
-- Table structure for t_follows
-- ----------------------------
DROP TABLE IF EXISTS `t_follows`;
CREATE TABLE `t_follows` (
  `fid` varchar(100) NOT NULL COMMENT 'UUID生成机制，主键',
  `sid` varchar(100) DEFAULT NULL COMMENT '外键，引用客户id',
  `uid` varchar(100) DEFAULT NULL COMMENT '员工id(访问者id)',
  `visittime` datetime DEFAULT NULL COMMENT '回访时间',
  `visitcase` varchar(255) DEFAULT NULL COMMENT '回访情况（沟通内容）',
  `trackingmode` varchar(100) DEFAULT NULL COMMENT '跟踪方式(访问方式)',
  `nexttrackingtime` datetime DEFAULT NULL COMMENT '下次跟踪时间',
  `fremark` varchar(255) DEFAULT NULL COMMENT '备注',
  `stustatus` varchar(255) DEFAULT NULL COMMENT '状态：学生状态',
  PRIMARY KEY (`fid`) USING BTREE,
  KEY `F_stu_foll` (`sid`) USING BTREE,
  KEY `F_users_foll` (`uid`) USING BTREE,
  CONSTRAINT `F_stu_foll` FOREIGN KEY (`sid`) REFERENCES `t_students` (`sid`),
  CONSTRAINT `F_users_foll` FOREIGN KEY (`uid`) REFERENCES `t_users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_follows
-- ----------------------------
INSERT INTO `t_follows` VALUES ('4d7cd8fd-ff76-4f21-af72-e7591b89055d', '895dc5b5-a7fc-42af-ba49-84acc743b44a', 'd9f6b1cd-9dae-4007-8485-35225f656505', '2020-05-18 00:00:00', '测试回访情况', 'qq', '2020-05-18 00:00:00', '很好', '梁红啊');
INSERT INTO `t_follows` VALUES ('802edbd3-7352-4d33-894e-70be4125024b', '49c8e13b-01e4-477d-bc19-cb4078576912', 'd9f6b1cd-9dae-4007-8485-35225f656505', '2020-06-08 00:00:00', '学生的学习意愿很大', '微信', '2020-06-10 00:00:00', '想尽快入班学习', '很积极');
INSERT INTO `t_follows` VALUES ('a757ef57-9883-4ca6-bae6-3488307fd5eb', '49c8e13b-01e4-477d-bc19-cb4078576912', 'd9f6b1cd-9dae-4007-8485-35225f656505', '2020-06-09 00:00:00', '期待入学', '微信', '2020-06-11 00:00:00', '开心666', '良好');
INSERT INTO `t_follows` VALUES ('a79f8555-571d-460e-96e9-295d13982fa0', '895dc5b5-a7fc-42af-ba49-84acc743b44a', 'd9f6b1cd-9dae-4007-8485-35225f656505', '2020-05-02 00:00:00', '有兴趣，想进一步学习', '微信', '2020-05-21 00:00:00', '挺好', '充满信息');
INSERT INTO `t_follows` VALUES ('d2305010-81f1-4c16-b8b3-e02d941335bd', '01760231-a6d1-4b63-a81f-77b4013b47c2', 'd9f6b1cd-9dae-4007-8485-35225f656505', '2020-05-22 00:00:00', '开心', '开心', '2020-05-22 00:00:00', '开心', '开心');
INSERT INTO `t_follows` VALUES ('e8805a71-b0b6-40a8-9ee5-a5eacfa06d06', '49c8e13b-01e4-477d-bc19-cb4078576912', 'd9f6b1cd-9dae-4007-8485-35225f656505', '2020-06-01 00:00:00', '她喜欢C++语言', '钉钉', '2020-06-08 00:00:00', '有意向', '良好');

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `meid` varchar(100) NOT NULL COMMENT 'UUID生成机制，主键',
  `senderid` varchar(100) DEFAULT NULL COMMENT '发送人id',
  `sendername` varchar(20) DEFAULT NULL COMMENT '发送人姓名',
  `recipientid` varchar(100) DEFAULT NULL COMMENT '接收人id',
  `recipientname` varchar(20) DEFAULT NULL COMMENT '接收人姓名',
  `rname` varchar(50) DEFAULT NULL COMMENT '角色名：当前',
  `mecontent` varchar(500) DEFAULT NULL COMMENT '聊天内容',
  `mecreatetime` datetime DEFAULT NULL COMMENT '消息发送时间',
  `isread` varchar(10) DEFAULT NULL COMMENT '是否读到消息',
  PRIMARY KEY (`meid`) USING BTREE,
  KEY `F_users_1` (`senderid`) USING BTREE,
  KEY `F_users_2` (`recipientid`) USING BTREE,
  CONSTRAINT `F_users_1` FOREIGN KEY (`senderid`) REFERENCES `t_users` (`uid`),
  CONSTRAINT `F_users_2` FOREIGN KEY (`recipientid`) REFERENCES `t_users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_message
-- ----------------------------

-- ----------------------------
-- Table structure for t_modules
-- ----------------------------
DROP TABLE IF EXISTS `t_modules`;
CREATE TABLE `t_modules` (
  `mid` int(11) NOT NULL AUTO_INCREMENT COMMENT '模块编号，主键，自增',
  `mname` varchar(100) DEFAULT NULL COMMENT '模块名称',
  `parentid` int(11) DEFAULT NULL COMMENT '父模块编号',
  `mpath` varchar(255) DEFAULT NULL COMMENT '模块路径',
  `mweight` int(11) DEFAULT NULL COMMENT '模块权重',
  `styleicon` varchar(100) DEFAULT NULL COMMENT '图表样式',
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_modules
-- ----------------------------
INSERT INTO `t_modules` VALUES ('1', '系统管理', '0', null, '98', 'layui-icon-windows');
INSERT INTO `t_modules` VALUES ('2', '用户管理', '1', 'admin/users/users.html', '99', null);
INSERT INTO `t_modules` VALUES ('3', '角色管理', '1', 'admin/roles/roles.html', '98', null);
INSERT INTO `t_modules` VALUES ('4', '模块管理', '1', 'admin/modules/modules.html', '97', null);
INSERT INTO `t_modules` VALUES ('5', '学生管理', '0', null, '99', 'layui-icon-user');
INSERT INTO `t_modules` VALUES ('6', '网络学生', '5', 'admin/stumanager/networtStudent.html', '98', null);
INSERT INTO `t_modules` VALUES ('7', '我的学生', '5', 'admin/stumanager/myStudent.html', '96', null);
INSERT INTO `t_modules` VALUES ('8', '网络跟踪', '5', 'admin/stumanager/networkFollows.html', '95', null);
INSERT INTO `t_modules` VALUES ('9', '管理学生', '5', 'admin/stumanager/glxs.html', '94', null);
INSERT INTO `t_modules` VALUES ('11', '分量操作', '5', 'admin/stumanager/flcz.html', '92', null);
INSERT INTO `t_modules` VALUES ('13', '回收站', '5', 'admin/stumanager/hsz.html', '80', null);
INSERT INTO `t_modules` VALUES ('14', '快捷服务', '0', null, '95', 'layui-icon-list');
INSERT INTO `t_modules` VALUES ('15', '考勤管理', '14', 'admin/kjfw/kqgl.html', '98', null);
INSERT INTO `t_modules` VALUES ('38', '权限管理', '1', 'admin/operation/qxgl.html', '21', null);
INSERT INTO `t_modules` VALUES ('39', '经理我的学生', '5', 'admin/stumanager/jlwdxs.html', '56', null);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '功能权限编号，主键，自增',
  `pname` varchar(100) DEFAULT NULL COMMENT '权限名称',
  `parentid` int(11) DEFAULT NULL COMMENT '父模块编号',
  `permissionvalue` varchar(100) DEFAULT NULL COMMENT '权限值',
  `permissionmodule` int(11) DEFAULT NULL COMMENT '模块id',
  `permissionname` varchar(255) DEFAULT NULL COMMENT '权限名称（说明）',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', '用户管理', '0', 'user', '2', '用户信息的操作');
INSERT INTO `t_permission` VALUES ('2', '添加新用户', '1', 'user:add', '2', '添加新用户');
INSERT INTO `t_permission` VALUES ('3', '编辑用户', '1', 'user:edit', '2', '修改用户信息');
INSERT INTO `t_permission` VALUES ('4', '删除用户', '1', 'user:delete', '2', '删除用户');
INSERT INTO `t_permission` VALUES ('5', '批量删除', '1', 'user:deletelist', '2', '批量删除用户');
INSERT INTO `t_permission` VALUES ('6', '角色管理', '0', 'roles', '3', '角色信息的操作');
INSERT INTO `t_permission` VALUES ('7', '新增角色', '6', 'relos:add', '3', '添加新角色');
INSERT INTO `t_permission` VALUES ('8', '设置角色', '1', 'user:setRoles', '2', '给用户设置角色');
INSERT INTO `t_permission` VALUES ('9', '重置密码', '1', 'user:resetPwd', '2', '重置用户的登录密码');
INSERT INTO `t_permission` VALUES ('10', '批量删除', '6', 'relos:deletelist', '3', '批量删除角色');
INSERT INTO `t_permission` VALUES ('11', '设置访问权限', '6', 'relos:setFwQx', '3', '设置访问页面的权限');
INSERT INTO `t_permission` VALUES ('12', '编辑角色', '6', 'relos:edit', '3', '编辑角色信息');
INSERT INTO `t_permission` VALUES ('13', '删除角色', '6', 'relos:delete', '3', '删除角色');
INSERT INTO `t_permission` VALUES ('14', '设置功能权限', '6', 'relos:gongNeng', '3', '给角色设置功能权限');
INSERT INTO `t_permission` VALUES ('17', '权限管理', '0', 'permission', '38', '权限信息的操作');
INSERT INTO `t_permission` VALUES ('18', '模块管理', '0', 'modules', '4', '模块信息的操作');
INSERT INTO `t_permission` VALUES ('19', '添加父模块', '18', 'modules:addParent', '4', '添加一个父模块');
INSERT INTO `t_permission` VALUES ('20', '添加子节点', '18', 'modules:addSon', '4', '添加一个子节点');
INSERT INTO `t_permission` VALUES ('21', '修改节点', '18', 'modules:edit', '4', '修改节点信息');
INSERT INTO `t_permission` VALUES ('22', '删除节点', '18', 'modules:del', '4', '删除节点');
INSERT INTO `t_permission` VALUES ('23', '新增权限', '17', 'permission:add', '38', '新增一个权限子节点');
INSERT INTO `t_permission` VALUES ('24', '修改节点', '17', 'permission:edit', '38', '修改子节点信息');
INSERT INTO `t_permission` VALUES ('25', '删除节点', '17', 'permission:del', '38', '删除子节点信息');
INSERT INTO `t_permission` VALUES ('26', '回收站', '0', 'hsz', '13', '回收站页面的操作');
INSERT INTO `t_permission` VALUES ('28', '回收站查看所有', '26', 'hsz:zxsjlgetAll', '13', '能查看所有人软删除到回收站的学生');
INSERT INTO `t_permission` VALUES ('29', '经理我的学生', '0', null, '39', null);
INSERT INTO `t_permission` VALUES ('30', '分量操作', '0', 'flcz', '11', '咨询经理控制新建学生信息后是否分配给咨询师角色');
INSERT INTO `t_permission` VALUES ('31', '显示所有网络咨询师', '34', 'flcz:wlzxsgetAll', '6', '显示有拥有网络咨询师角色的员工');
INSERT INTO `t_permission` VALUES ('32', '我的学生', '0', 'myStudent', '7', '我的学生页面的操作');
INSERT INTO `t_permission` VALUES ('33', '下拉单选择咨询师', '32', 'xsxx:selectRelos', '7', '下拉框的咨询师');
INSERT INTO `t_permission` VALUES ('34', '网络学生', '0', 'networtStudent', '6', '网络学生页面的操作');
INSERT INTO `t_permission` VALUES ('35', '网络跟踪', '0', 'networkFollows', '8', '网络跟踪页面的操作');
INSERT INTO `t_permission` VALUES ('36', '查看所有学生跟踪信息', '35', 'wlgz:getAllStuFollows', '8', '拥有该权限可以查看所有学生的跟踪信息');
INSERT INTO `t_permission` VALUES ('37', '查看所有网络学生', '34', 'networtStudent:getAll', '6', '拥有此权限可以查看所有网络学生');
INSERT INTO `t_permission` VALUES ('38', '录入学生', '34', 'networtStudent:add', '6', '录入学生信息');
INSERT INTO `t_permission` VALUES ('39', '批量删除学生信息', '34', 'networtStudent:delList', '6', '批量删除学生信息');
INSERT INTO `t_permission` VALUES ('40', '批量修改学生所属咨询师', '34', 'networtStudent:editStuZxs', '6', '此权限可以批量修改学生所属咨询师');
INSERT INTO `t_permission` VALUES ('41', '查看学生信息', '34', 'networtStudent:detail', '6', '此权限可以查看学生详细信息');
INSERT INTO `t_permission` VALUES ('42', '编辑学生信息', '34', 'networtStudent:edit', '6', '此权限可以修改学生信息');
INSERT INTO `t_permission` VALUES ('43', '单个删除', '34', 'networtStudent:del', '6', '此权限可以删除单个学生信息');
INSERT INTO `t_permission` VALUES ('44', '动态推送', '34', 'networtStudent:dynamicmessage', '6', '此权限可以推送消息');
INSERT INTO `t_permission` VALUES ('45', '查看所有学生信息', '32', 'myStudent:getAll', '7', '此权限可以查看所有学生信息');
INSERT INTO `t_permission` VALUES ('46', '批量删除学生信息', '32', 'myStudent:delList', '7', '此权限可以批量删除学生信息');
INSERT INTO `t_permission` VALUES ('47', '咨询师查询条件', '32', 'myStudent:selectZxs', '7', '此权限可以根据咨询师查找咨询师下的学生');
INSERT INTO `t_permission` VALUES ('48', '查看学生信息', '32', 'myStudent:detail', '7', '此权限可以查看学生详细信息');
INSERT INTO `t_permission` VALUES ('49', '编辑学生信息', '32', 'myStudent:edit', '7', '此权限可以编辑学生信息');
INSERT INTO `t_permission` VALUES ('50', '跟踪学生', '32', 'myStudent:follows', '7', '此权限可以对学生进行跟踪回访');
INSERT INTO `t_permission` VALUES ('51', '查看跟踪日志', '32', 'myStudent:followsRz', '7', '此权限可以查看某个学生的所有跟踪记录');
INSERT INTO `t_permission` VALUES ('52', '删除单个学生', '32', 'myStudent:del', '7', '此权限可以单个删除学生信息');
INSERT INTO `t_permission` VALUES ('53', '导入(上传excel)文件', '32', 'myStudent:importExcel', '7', '此权限可以上传excel文件');

-- ----------------------------
-- Table structure for t_roles
-- ----------------------------
DROP TABLE IF EXISTS `t_roles`;
CREATE TABLE `t_roles` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号，主键，自增',
  `rname` varchar(100) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_roles
-- ----------------------------
INSERT INTO `t_roles` VALUES ('1', '管理员');
INSERT INTO `t_roles` VALUES ('2', '网络咨询师');
INSERT INTO `t_roles` VALUES ('3', '咨询师');
INSERT INTO `t_roles` VALUES ('4', '咨询师经理');

-- ----------------------------
-- Table structure for t_roles_modules
-- ----------------------------
DROP TABLE IF EXISTS `t_roles_modules`;
CREATE TABLE `t_roles_modules` (
  `rid` int(11) DEFAULT NULL COMMENT '外键，引用角色id',
  `mid` int(11) DEFAULT NULL COMMENT '外键，引用模块id',
  KEY `F_roles_m` (`rid`) USING BTREE,
  KEY `F_modules` (`mid`) USING BTREE,
  CONSTRAINT `F_modules` FOREIGN KEY (`mid`) REFERENCES `t_modules` (`mid`),
  CONSTRAINT `F_roles_m` FOREIGN KEY (`rid`) REFERENCES `t_roles` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_roles_modules
-- ----------------------------
INSERT INTO `t_roles_modules` VALUES ('2', '5');
INSERT INTO `t_roles_modules` VALUES ('2', '6');
INSERT INTO `t_roles_modules` VALUES ('3', '5');
INSERT INTO `t_roles_modules` VALUES ('3', '7');
INSERT INTO `t_roles_modules` VALUES ('3', '8');
INSERT INTO `t_roles_modules` VALUES ('3', '13');
INSERT INTO `t_roles_modules` VALUES ('1', '5');
INSERT INTO `t_roles_modules` VALUES ('1', '6');
INSERT INTO `t_roles_modules` VALUES ('1', '7');
INSERT INTO `t_roles_modules` VALUES ('1', '8');
INSERT INTO `t_roles_modules` VALUES ('1', '11');
INSERT INTO `t_roles_modules` VALUES ('1', '13');
INSERT INTO `t_roles_modules` VALUES ('1', '1');
INSERT INTO `t_roles_modules` VALUES ('1', '2');
INSERT INTO `t_roles_modules` VALUES ('1', '3');
INSERT INTO `t_roles_modules` VALUES ('1', '4');
INSERT INTO `t_roles_modules` VALUES ('1', '38');
INSERT INTO `t_roles_modules` VALUES ('1', '14');
INSERT INTO `t_roles_modules` VALUES ('1', '15');
INSERT INTO `t_roles_modules` VALUES ('4', '5');
INSERT INTO `t_roles_modules` VALUES ('4', '6');
INSERT INTO `t_roles_modules` VALUES ('4', '7');
INSERT INTO `t_roles_modules` VALUES ('4', '8');
INSERT INTO `t_roles_modules` VALUES ('4', '11');
INSERT INTO `t_roles_modules` VALUES ('4', '13');
INSERT INTO `t_roles_modules` VALUES ('4', '14');
INSERT INTO `t_roles_modules` VALUES ('4', '15');

-- ----------------------------
-- Table structure for t_roles_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_roles_permission`;
CREATE TABLE `t_roles_permission` (
  `roleid` int(11) DEFAULT NULL COMMENT '外键，引用角色id',
  `permissionid` int(11) DEFAULT NULL COMMENT '外键，引用模块id',
  KEY `F_roles_p` (`roleid`) USING BTREE,
  KEY `F_permission` (`permissionid`) USING BTREE,
  CONSTRAINT `F_permission` FOREIGN KEY (`permissionid`) REFERENCES `t_permission` (`pid`),
  CONSTRAINT `F_roles_p` FOREIGN KEY (`roleid`) REFERENCES `t_roles` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_roles_permission
-- ----------------------------
INSERT INTO `t_roles_permission` VALUES ('2', '34');
INSERT INTO `t_roles_permission` VALUES ('2', '31');
INSERT INTO `t_roles_permission` VALUES ('2', '38');
INSERT INTO `t_roles_permission` VALUES ('2', '41');
INSERT INTO `t_roles_permission` VALUES ('2', '44');
INSERT INTO `t_roles_permission` VALUES ('1', '1');
INSERT INTO `t_roles_permission` VALUES ('1', '2');
INSERT INTO `t_roles_permission` VALUES ('1', '3');
INSERT INTO `t_roles_permission` VALUES ('1', '4');
INSERT INTO `t_roles_permission` VALUES ('1', '5');
INSERT INTO `t_roles_permission` VALUES ('1', '8');
INSERT INTO `t_roles_permission` VALUES ('1', '9');
INSERT INTO `t_roles_permission` VALUES ('1', '6');
INSERT INTO `t_roles_permission` VALUES ('1', '7');
INSERT INTO `t_roles_permission` VALUES ('1', '10');
INSERT INTO `t_roles_permission` VALUES ('1', '11');
INSERT INTO `t_roles_permission` VALUES ('1', '12');
INSERT INTO `t_roles_permission` VALUES ('1', '13');
INSERT INTO `t_roles_permission` VALUES ('1', '14');
INSERT INTO `t_roles_permission` VALUES ('1', '17');
INSERT INTO `t_roles_permission` VALUES ('1', '23');
INSERT INTO `t_roles_permission` VALUES ('1', '24');
INSERT INTO `t_roles_permission` VALUES ('1', '25');
INSERT INTO `t_roles_permission` VALUES ('1', '18');
INSERT INTO `t_roles_permission` VALUES ('1', '19');
INSERT INTO `t_roles_permission` VALUES ('1', '20');
INSERT INTO `t_roles_permission` VALUES ('1', '21');
INSERT INTO `t_roles_permission` VALUES ('1', '22');
INSERT INTO `t_roles_permission` VALUES ('1', '26');
INSERT INTO `t_roles_permission` VALUES ('1', '28');
INSERT INTO `t_roles_permission` VALUES ('1', '32');
INSERT INTO `t_roles_permission` VALUES ('1', '45');
INSERT INTO `t_roles_permission` VALUES ('1', '46');
INSERT INTO `t_roles_permission` VALUES ('1', '47');
INSERT INTO `t_roles_permission` VALUES ('1', '48');
INSERT INTO `t_roles_permission` VALUES ('1', '49');
INSERT INTO `t_roles_permission` VALUES ('1', '50');
INSERT INTO `t_roles_permission` VALUES ('1', '51');
INSERT INTO `t_roles_permission` VALUES ('1', '52');
INSERT INTO `t_roles_permission` VALUES ('1', '53');
INSERT INTO `t_roles_permission` VALUES ('1', '34');
INSERT INTO `t_roles_permission` VALUES ('1', '37');
INSERT INTO `t_roles_permission` VALUES ('1', '38');
INSERT INTO `t_roles_permission` VALUES ('1', '39');
INSERT INTO `t_roles_permission` VALUES ('1', '40');
INSERT INTO `t_roles_permission` VALUES ('1', '41');
INSERT INTO `t_roles_permission` VALUES ('1', '42');
INSERT INTO `t_roles_permission` VALUES ('1', '43');
INSERT INTO `t_roles_permission` VALUES ('1', '44');
INSERT INTO `t_roles_permission` VALUES ('1', '35');
INSERT INTO `t_roles_permission` VALUES ('1', '36');
INSERT INTO `t_roles_permission` VALUES ('3', '32');
INSERT INTO `t_roles_permission` VALUES ('3', '33');
INSERT INTO `t_roles_permission` VALUES ('3', '46');
INSERT INTO `t_roles_permission` VALUES ('3', '48');
INSERT INTO `t_roles_permission` VALUES ('3', '49');
INSERT INTO `t_roles_permission` VALUES ('3', '50');
INSERT INTO `t_roles_permission` VALUES ('3', '51');
INSERT INTO `t_roles_permission` VALUES ('3', '52');
INSERT INTO `t_roles_permission` VALUES ('4', '26');
INSERT INTO `t_roles_permission` VALUES ('4', '28');
INSERT INTO `t_roles_permission` VALUES ('4', '32');
INSERT INTO `t_roles_permission` VALUES ('4', '45');
INSERT INTO `t_roles_permission` VALUES ('4', '46');
INSERT INTO `t_roles_permission` VALUES ('4', '47');
INSERT INTO `t_roles_permission` VALUES ('4', '48');
INSERT INTO `t_roles_permission` VALUES ('4', '49');
INSERT INTO `t_roles_permission` VALUES ('4', '50');
INSERT INTO `t_roles_permission` VALUES ('4', '51');
INSERT INTO `t_roles_permission` VALUES ('4', '52');
INSERT INTO `t_roles_permission` VALUES ('4', '53');
INSERT INTO `t_roles_permission` VALUES ('4', '34');
INSERT INTO `t_roles_permission` VALUES ('4', '37');
INSERT INTO `t_roles_permission` VALUES ('4', '38');
INSERT INTO `t_roles_permission` VALUES ('4', '39');
INSERT INTO `t_roles_permission` VALUES ('4', '40');
INSERT INTO `t_roles_permission` VALUES ('4', '41');
INSERT INTO `t_roles_permission` VALUES ('4', '42');
INSERT INTO `t_roles_permission` VALUES ('4', '43');
INSERT INTO `t_roles_permission` VALUES ('4', '44');
INSERT INTO `t_roles_permission` VALUES ('4', '35');
INSERT INTO `t_roles_permission` VALUES ('4', '36');

-- ----------------------------
-- Table structure for t_students
-- ----------------------------
DROP TABLE IF EXISTS `t_students`;
CREATE TABLE `t_students` (
  `sid` varchar(100) NOT NULL COMMENT 'UUID生成机制，主键',
  `uid` varchar(100) DEFAULT NULL COMMENT '外键，引用员工id（网络咨询师员工id）',
  `szxsid` varchar(100) DEFAULT '未分配' COMMENT '咨询师姓名',
  `uphone` varchar(20) DEFAULT NULL COMMENT '咨询师手机号',
  `sname` varchar(50) DEFAULT NULL COMMENT '客户名称',
  `sphone` varchar(20) DEFAULT NULL COMMENT '客户手机号',
  `sage` int(11) DEFAULT NULL COMMENT '客户年龄',
  `sgender` varchar(10) DEFAULT NULL COMMENT '客户性别',
  `sourcechannel` varchar(100) DEFAULT NULL COMMENT '来源渠道：通过百度|360搜索...推荐来的',
  `sourceword` varchar(100) DEFAULT NULL COMMENT '来源关键词',
  `seducation` varchar(50) DEFAULT NULL COMMENT '客户学历',
  `sqq` varchar(20) DEFAULT NULL COMMENT '客户QQ号',
  `sstatus` varchar(50) DEFAULT NULL COMMENT '客户状态：在读|待业|在职...',
  `wechatid` varchar(50) DEFAULT NULL COMMENT '微信号',
  `sourceurl` varchar(255) DEFAULT NULL COMMENT '来源网址：职英A站、B站...',
  `ifweight` varchar(10) DEFAULT '否' COMMENT '是否自动分量',
  `screatetime` datetime DEFAULT NULL COMMENT '创建时间',
  `sremark` varchar(255) DEFAULT NULL COMMENT '备注：网络咨询人员给的备注',
  `gradecase` varchar(100) DEFAULT NULL COMMENT '打分情况：什么时候可以报名',
  `course` varchar(100) DEFAULT NULL COMMENT '课程方向：想报考学习的课程',
  `iseffect` varchar(10) DEFAULT '是' COMMENT '是否有效：是|否',
  `invalidcause` varchar(255) DEFAULT NULL COMMENT '无效原因',
  `isfirstvisit` varchar(10) DEFAULT '否' COMMENT '是否已经首次回访：是|否',
  `firstvisittime` datetime DEFAULT NULL COMMENT '首次回访时间',
  `isvisit` varchar(10) DEFAULT '否' COMMENT '是否上门：是|否',
  `visittime` datetime DEFAULT NULL COMMENT '上门时间',
  `handselmoney` decimal(10,0) DEFAULT NULL COMMENT '定金金额',
  `handsetime` datetime DEFAULT NULL COMMENT '定金时间',
  `paythefees` varchar(10) DEFAULT '否' COMMENT '是否完成缴费',
  `paythefeestime` datetime DEFAULT NULL COMMENT '完成缴费时间',
  `paymentamount` varchar(255) DEFAULT NULL COMMENT '缴费金额',
  `isoutpay` varchar(10) DEFAULT '否' COMMENT '是否退费',
  `isoutpaycause` varchar(255) DEFAULT NULL COMMENT '退费原因',
  `isoutpaytime` datetime DEFAULT NULL COMMENT '退费时间',
  `intheclass` varchar(10) DEFAULT '否' COMMENT '是否进班',
  `intheclasstime` datetime DEFAULT NULL COMMENT '进班时间',
  `intheclassremark` varchar(255) DEFAULT NULL COMMENT '进班备注',
  `rremark` varchar(255) DEFAULT NULL COMMENT '咨询师给的备注',
  `ifvalid` varchar(20) DEFAULT NULL COMMENT '是否是有效数据  如果有效就显示 无效就在回收站',
  PRIMARY KEY (`sid`) USING BTREE,
  KEY `F_users_stu` (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_students
-- ----------------------------
INSERT INTO `t_students` VALUES ('01760231-a6d1-4b63-a81f-77b4013b47c2', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '王涵兰', '18963222789', '刘山河', '18632015698', '23', '男', '线下招生', '想学', '高中', '36547201236', '待业', 'lsh18632015698', '未知', '否', '2020-05-16 11:59:31', '通过线下招生了解到的，学习心非常强', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('0af295c7-68b9-4d27-8121-f1474f3b75d7', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '宋沛文', '13123658412', '郑晓峰', '13136985423', '20', '男', '线下招生', '编程学校', '高中', '56234712305', '在读', 'zxf13136985423', '未知', '否', '2020-05-16 15:57:03', '对编程十分感兴趣，想学习', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('0bf1f646-28f2-48a5-aa8d-e49e1d0afc14', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '王涵兰', '18963222789', '赵江波', '15203666513', '20', '男', '百度', 'java学习', '大专', '52482103654', '在读', 'zjb15203666513', '职英A站', '否', '2020-05-16 12:03:14', '通过搜索了解到我们，有兴趣来学习', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('0c04cc1b-f617-474c-9aa5-60e59c5c08f8', '599e2fe7-fb0c-4adc-9741-5229a1a3e9f4', '黄健', '15603812963', '柏文漪', '15262148523', '19', '男', '线下招生', '.net编程', '高中', '5214697820', '在读', 'bwy15262148523', '职英B站', '否', '2020-05-19 11:31:56', '有意向来我们这里学习', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('13a87dd8-3c2a-422e-9b02-14e60d1774c3', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '王涵兰', '18963222789', '赵娅欣', '15632145103', '23', '女', '百度手机版', 'java学习', '本科', '3214568745', '在读', 'zyx15632145103', '职英A站', '否', '2020-05-21 21:08:03', '学习', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('3e9700e5-1b03-4cd6-a012-84f99488e173', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '黄健', '15603812963', '常景中', '15630125413', '25', '男', '线下招生', 'c++编程', '本科', '2541230123', '就业', 'cjz15630125413', '群英编程官网', '否', '2020-05-21 09:08:01', '了解更多关于编程的知识', '3', null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('49c8e13b-01e4-477d-bc19-cb4078576912', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '王涵兰', '18963222789', '张代柔', '15520147896', '23', '男', '百度手机版', '数据库学习', '本科', '5874123647', '就业', 'zdr15520147896', '职英A站', '否', '2020-05-25 09:39:34', '想学习SQLServer编程', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('49d1d0e3-5a91-4698-8967-f898758dcf59', '6d9ab61b-1bf8-4b35-b3ab-d6426c45dbc4', '未分配', null, '张佳悦', '15512301489', '20', '男', '百度手机版', 'PHP编程学习 ', '本科', '6320145213', '在读', 'zjy15512301489', '职英A站', '否', '2020-05-25 09:49:21', '想了解更多php编程的知识', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('518afcdb-683f-432e-baec-ee6a3630f607', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '未分配', null, '常竹萱', '18632015230', '19', '女', '百度手机版', 'java学习', '高中', '3214012356', '在读', 'czx18632015230', '职英A站', '否', '2020-05-23 21:09:09', '想学习java语言', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('54566fc0-fe74-42ae-ab43-29c650fb2ea7', '6d9ab61b-1bf8-4b35-b3ab-d6426c45dbc4', '王涵兰', '18963222789', '唐玉华', '15520369845', '26', '男', '百度', '数据库管理培训', '硕士', '3214785123', '在职', 'tyh15520369845', '职英B站', '否', '2020-05-19 11:41:49', '想学习更多关于数据库操作方面的知识', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('568c7753-2db8-4069-9f0e-393a92dbef79', 'a6f7fb80-710a-40e0-b445-e9db2e725773', '王涵兰', '18963222789', '毕若山', '15830145203', '26', '男', '线下招生', 'SqlServer学习', '本科', '2102546321', '就业', 'brs15830145203', '群英编程官网', '否', '2020-05-19 15:57:08', '想深入学习关于数据库的知识', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('57a331e7-ecc6-48db-9984-756eba9172af', '599e2fe7-fb0c-4adc-9741-5229a1a3e9f4', '王涵兰', '18963222789', '贾春柔', '16612302142', '23', '女', '线下招生', 'java招生', '本科', '3214560123', '就业', 'jcr16612302142', '未知', '否', '2020-05-19 11:29:05', '主动找我了解情况', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('5c4a979d-525e-43ae-8282-4a260a5cd3e1', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '王涵兰', '18963222789', '常亦竹', '15503612036', '23', '男', '百度', 'PHP学习', '本科', '5210341263', '在职', 'cyz15503612036', '职英A站', '否', '2020-05-21 09:00:17', '想了解更多关于php的知识', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('5c6c4c16-a850-4a19-a953-a577868c8b86', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '王涵兰', '18963222789', '任彭泽', '13856321453', '20', '男', '线下招生', '前端学习', '大专', '3214567820', '在读', 'rpz13856321453', '职英A站', '否', '2020-05-21 10:24:31', '想学习前端知识', '4', null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('62aaad52-cced-42a3-a5b2-c0fb740a1810', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '未分配', null, '常若彤', '15516302156', '18', '女', '百度手机版', '数据库编程', '高中', '2314561203', '在读', 'crt15516302156', '群英编程官网', '否', '2020-05-21 09:02:36', '学习数据库', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('67699c36-0969-430a-972e-cd3e30b1898a', '6d9ab61b-1bf8-4b35-b3ab-d6426c45dbc4', '未分配', null, '姜冰安', '13632014523', '25', '男', '百度手机版', '.net学习', '硕士', '3216021459', '就业', 'jba13632014523', '职英A站', '否', '2020-05-19 11:36:10', '想学习更多编程知识', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('6e2c058b-d916-44e3-aff3-8b4c168f8019', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '玉千秋', '13823656895', '程云琼', '13852647896', '19', '女', '360搜索', '编程培训', '高中', '3987412563', '在读', 'cyq13852647896', '职英A站', '否', '2020-05-16 16:08:29', '未来想学习编程', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('6ed479a0-ed14-437f-aa9b-f12e5c2c7aa7', 'a6f7fb80-710a-40e0-b445-e9db2e725773', '玉千秋', '13823656895', '蒋易梦', '15820314203', '23', '男', '360搜索', 'PHP学习', '大专', '6354123602', '在读', 'jym15820314203', '职英A站', '否', '2020-05-19 15:49:38', '想深入学习php编程语言', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('7c5ab2a0-f53f-4ffa-b1ed-7a75d500a068', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '玉千秋', '13823656895', '任天照', '15516832145', '19', '男', '线下招生', 'java学习', '高中', '2563541289', '在读', 'rtz15516832145', '职英B站', '否', '2020-05-16 15:45:02', '想了解关于编程的问题', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('7d6cd590-a4dd-47e8-a08a-e2dafd22a8a2', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '玉千秋', '13823656895', '任承文', '18632014569', '20', '男', '百度', '编程学习', '大专', '56248963210', '在读', 'rcw18632014569', '职英B站', '否', '2020-05-16 16:05:35', '热爱编程学习', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('7d7db2b0-667f-4ed8-9bd2-7526c1afed09', '6d9ab61b-1bf8-4b35-b3ab-d6426c45dbc4', '未分配', null, '庚莹琇', '15520321036', '23', '女', '百度', '数据库学习', '本科', '36201452130', '在读', 'gyx15520321036', '职英A站', '否', '2020-05-20 18:26:08', '热爱数据库学习', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('895dc5b5-a7fc-42af-ba49-84acc743b44a', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '玉千秋', '13823656895', '余新林', '15032156987', '25', '男', '线下招生', '编程培训', '高中', '2368745612', '就业', 'yxl15032156987', '未知', '否', '2020-05-16 16:11:39', '想学习，未来想加入编程行业', '3', '服务器开发', '是', '太难学不会', '否', '2020-05-16 00:00:00', '是', '2020-05-18 00:00:00', '2300', '2020-05-16 00:00:00', '是', '2020-05-17 00:00:00', '3000', '否', '太难学不会', '2020-05-17 00:00:00', '否', '2020-05-17 00:00:00', '修改测试', '修改测试', '是');
INSERT INTO `t_students` VALUES ('900644ed-b92e-4e24-a682-71c50b367389', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '未分配', null, '常文彬', '15654123541', '19', '男', '百度', 'PHP学习', '本科', '3254125634', '在读', 'cwb15654123541', '职英A站', '否', '2020-05-23 21:12:41', '想学习PHP', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('955a0364-9599-4f5e-a23a-c3e3861bf6b0', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '宋沛文', '13123658412', '王芳', '18856320147', '21', '女', '360搜索', '学习网', '大专', '25314521036', '在读', 'wf18856320147', '职英A站', '否', '2020-05-16 15:40:27', '热爱编程', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('9770d7a3-2bfe-4371-87c0-a59e1caeec4b', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '王涵兰', '18963222789', '任白梅', '15516320146', '23', '女', '线下招生', 'spring框架深入学习', '本科', '2541362014', '在读', 'rbm15516320146', '群英编程官网', '否', '2020-05-21 10:25:42', '想深入了解Spring', '4', '服务器开发', '是', null, '是', '2020-05-25 00:00:00', '是', '2020-05-25 00:00:00', '800', '2020-05-25 00:00:00', '是', '2020-05-25 00:00:00', '1200', '否', null, null, '否', null, '两天后进班', '爱学', '是');
INSERT INTO `t_students` VALUES ('9b65a786-3554-4fe6-908d-bd43a92b91c0', '0af295c7-68b9-4d27-8121-f1474f3b75d7', '王涵兰', '18963222789', '宋雨筠', '15203621453', '20', '男', '百度', '编程学习', '大专', '3654120531', '在读', 'syj15203621453', '职英A站', '否', '2020-05-19 11:25:44', '主动联系我，说想要学习', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('aa16187f-de02-48d0-86ba-121f2f9693c9', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '未分配', null, '张清佳', '13602147820', '23', '女', '百度手机版', '数据库学习', '大专', '2354712365', '在职', 'zqj13602147820', '职英A站', '否', '2020-05-25 09:24:18', '热爱数据库编程', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('b59e2775-cf45-41f2-99a0-9a27739fcf4b', '6d9ab61b-1bf8-4b35-b3ab-d6426c45dbc4', '未分配', null, '辛白秋', '18963210145', '26', '男', '线下招生', '编程学习', '未知', '3012456120', '在职', 'xqb18963210145', '未知', '否', '2020-05-20 18:33:30', '想了解更多关于编程的知识', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('c11c78bc-8364-4dbc-9798-ddacac686931', '0af295c7-68b9-4d27-8121-f1474f3b75d7', '未分配', null, '卞芝宇', '13163024630', '19', '男', '线下招生', 'Mysql管理', '大专', '53102478513', '在读', 'bzy13163024630', '未知', '否', '2020-05-19 11:39:35', '通过朋友了解到我们', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('cb6e4b2a-ff52-4fe9-ad68-f60a7e379ca6', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '未分配', null, '宋玉泉', '15516302142', '19', '男', '百度手机版', 'SqlServer学习', '大专', '3201462103', '在读', 'syq15516302142', '群英编程官网', '否', '2020-05-20 18:36:54', '热爱编程学习', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('ce419814-1ef2-47b0-8174-8090edca9dba', '0af295c7-68b9-4d27-8121-f1474f3b75d7', '黄健', '15603812963', '胡列娜', '13658455306', '23', '女', '百度', '编程学习', '其他', '2584135641', '就业', 'hln13658455306', '未知', '否', '2020-05-16 15:54:18', '想学习编程，并加入这行', '5', '服务器开发', '是', null, '否', null, '否', null, '200', null, '否', null, '500', '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('cf23e347-2112-44c7-bf7d-838ce9a22345', '6d9ab61b-1bf8-4b35-b3ab-d6426c45dbc4', '未分配', null, '卞梦容', '15320648952', '21', '女', '线下招生', '前端开发', '本科', '256314852301', '待业', 'bmr15320648952', '职英B站', '否', '2020-05-19 11:37:56', '热爱前端编程', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('d03e2865-27f5-4bf9-8ae2-4b03842c1b56', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '王涵兰', '18963222789', '黄石', '13103520146', '23', '男', '线下招生', 'java入门', '未知', '25636419632', '待业', 'hs13103520146', '未知', '否', '2020-05-16 15:47:51', '想学习编程', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('d39803bc-57ae-489f-9477-3c946c955ef6', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '未分配', null, '张奇伟', '15502341058', '19', '男', '360搜索', 'java 编程学习', '本科', '85514653114', '在读', 'zqw15502341058', '职英B站', '否', '2020-05-25 09:29:57', '热爱java开发', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('db72fd68-fac4-4448-82cb-7cf0ca733f04', '0af295c7-68b9-4d27-8121-f1474f3b75d7', '未分配', '', '王圣', '15624853214', '19', '男', '百度', 'java学习网', '高中', '2563341256', '在读', 'ws15624853214', '职英B站', '否', '2020-05-16 15:50:31', '想学习编程', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('e045e6c0-5808-4a26-9cad-7395d77752eb', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '未分配', null, '常雨灵', '15518420136', '23', '女', '百度手机版', 'php学习', '大专', '2354123650', '在职', 'cyl15518420136', '职英A站', '否', '2020-05-23 21:10:53', '想学习php语言', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('ef1e8730-5dd8-402b-b1b0-f09e2650ecee', '599e2fe7-fb0c-4adc-9741-5229a1a3e9f4', '未分配', '', '宋芷云', '18812514632', '21', '女', '360搜索', 'java培训', '高中', '1463257632', '待业', 'szy18812514632', '群英编程官网', '否', '2020-05-19 11:27:05', '从官网获取的信息', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('efeedd44-1356-4d5f-bf70-f270fecc05a1', '6d9ab61b-1bf8-4b35-b3ab-d6426c45dbc4', '王涵兰', '18963222789', '杜雨筠', '15601485123', '20', '男', '线下招生', 'java学习', '高中', '5621014520', '在读', 'dyj15601485123', '职英A站', '否', '2020-05-20 18:32:04', '有学习编程的意向', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('f12c39e9-8c8c-4c0d-a36a-82876b100d4b', '6d9ab61b-1bf8-4b35-b3ab-d6426c45dbc4', '未分配', null, '唐寻凝', '18816323541', '23', '男', '百度', '数据库学习', '本科', '3654125963', '在职', 'txn18816323541', '职英B站', '否', '2020-05-19 11:51:24', '想学习数据库TSQL编程', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');
INSERT INTO `t_students` VALUES ('f7719e67-a264-4858-81b6-f9a3e312df37', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '未分配', null, '常玮奇', '13620163214', '20', '男', '线下招生', '编程学习', '大专', '3201451203', '在读', 'cwq13620163214', '职英A站', '否', '2020-05-20 18:38:33', '想学习前端', null, null, '是', null, '否', null, '否', null, null, null, '否', null, null, '否', null, null, '否', null, null, null, '是');

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `uid` varchar(100) NOT NULL COMMENT 'UUID生成机制，主键',
  `loginname` varchar(20) DEFAULT NULL COMMENT '员工名（用户名），唯一',
  `password` varchar(100) DEFAULT NULL COMMENT '密码，必填',
  `islockout` varchar(10) DEFAULT NULL COMMENT '是否锁定',
  `lastlogintime` datetime DEFAULT NULL COMMENT '最后一次登陆时间',
  `createtime` datetime DEFAULT NULL COMMENT '账户创立时间',
  `psdwrongtime` int(11) DEFAULT NULL COMMENT '密码错误次数',
  `locktime` varchar(50) DEFAULT NULL COMMENT '被锁定时间',
  `protectemail` varchar(50) DEFAULT NULL COMMENT '密保邮箱，唯一',
  `protectmtel` varchar(20) DEFAULT NULL COMMENT '密保手机号，唯一',
  `idcard` varchar(30) DEFAULT NULL COMMENT '身份证号，唯一',
  `issign` varchar(10) DEFAULT NULL COMMENT '是否打卡',
  `uweight` int(11) DEFAULT NULL COMMENT '权重：根据权重分配学生',
  `uifweight` varchar(100) DEFAULT '否' COMMENT '是否自动分量',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('0935c440-4a58-4a7e-ad9b-20306bf311b2', '吴以冬', 'db5b3c208b3b84647037a9dae8a9a0f5', '否', '2020-06-09 10:33:07', '2020-05-04 09:52:57', '0', '', 'yth564@gmail.com', '15224938860', '411421199901198762', '否', '56', '否');
INSERT INTO `t_users` VALUES ('0af295c7-68b9-4d27-8121-f1474f3b75d7', '邓天韵', '32949c5a51f974b12303548963606999', '否', '2020-06-09 10:34:30', '2020-05-01 17:09:09', '0', '', 'bbd@163.com', '13513959750', '371602199607159124', '否', '23', '否');
INSERT INTO `t_users` VALUES ('17d9a079-86df-4187-8ce3-935adf45e2d2', '柔小舞', '7081c4dbf6ea4a686ca488c1d579e3a3', '否', '2020-06-09 10:27:21', '2020-05-07 14:23:17', '0', '', 'xw1025@yahoo.com', '17530370181', '445103199705109287', '否', '63', '否');
INSERT INTO `t_users` VALUES ('599e2fe7-fb0c-4adc-9741-5229a1a3e9f4', '张正平', 'a721dbda81a2587d2c1e579f6d903e71', '否', '2020-05-19 11:23:18', '2020-05-01 12:05:22', '0', '', 'ask@163.com', '18796325463', '210302199406215909', '否', '59', '否');
INSERT INTO `t_users` VALUES ('63ea532b-1119-4376-b665-27fe934dc4be', '杨雪羽', 'abd0973b42cf1dc8f51117970938250d', '否', '2020-06-08 21:45:27', '2020-05-01 12:03:45', '0', '', 'ts@163.com', '13103825639', '120112199003070039', '否', '89', '否');
INSERT INTO `t_users` VALUES ('6d9ab61b-1bf8-4b35-b3ab-d6426c45dbc4', '冯丽珠', '248df09433c898f739be0161f9f09869', '否', '2020-05-25 09:47:45', '2020-05-01 12:06:56', '0', '', 'zq@163.com', '15644578423', '230506198808167984', '否', '54', '否');
INSERT INTO `t_users` VALUES ('74791108-e614-4f63-9278-4cbbf1ee5680', '黄健', '38c5bae45666e050759807fb30e107ad', '否', '2020-05-21 17:21:33', '2020-05-07 14:25:08', '0', '', 'xqh88963@sohu.com', '15603812963', '360502201002092133', '否', '26', '否');
INSERT INTO `t_users` VALUES ('81dd8422-a1a0-406f-8761-3ddcd05ffa9d', '玉千秋', '647e63ab4be759fe78d6d4433c082ae7', '否', '2020-05-23 21:37:32', '2020-05-01 12:08:20', '0', '', 'nfz@163.com', '13823656895', '231226199710012233', '否', '85', '否');
INSERT INTO `t_users` VALUES ('9156d032-32e4-4b5b-861a-97b7c767b82b', '李伟泽', 'f1a25f1cb3a1e20003eafd34c0d78e83', '否', '2020-05-16 17:32:19', '2020-05-01 17:10:42', '0', '', 'yxg562@yahoo.com', '15173174746', '320922198707205535', '否', '50', '否');
INSERT INTO `t_users` VALUES ('a6f7fb80-710a-40e0-b445-e9db2e725773', '李元容', 'aefb85f3f74810add94c9246449b2eb8', '否', '2020-05-19 15:48:05', '2020-05-07 14:35:53', '0', '', 'mhj5896@qq.com', '18759632145', '340402199905187935', '否', '95', '否');
INSERT INTO `t_users` VALUES ('a7e96917-f9f3-46bb-ba58-d6e8064ac37a', '宋沛文', 'b271f6e56748604e9e5785d8debaf4db', '否', '2020-05-21 12:01:43', '2020-05-03 23:06:53', '0', '', 'zwj889@gmail.com', '13123658412', '411421199901198766', '否', '20', '否');
INSERT INTO `t_users` VALUES ('b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '程昆纶', '7ea14cb8b2adf77c9c696af064ff3f91', '否', '2020-05-25 09:22:02', '2020-05-04 09:54:45', '0', '', 'nrr564@qq.com', '18963245896', '420322199002202225', '否', '66', '否');
INSERT INTO `t_users` VALUES ('bfba35d7-6dba-4d0e-b2fc-029e79e9725c', '袁之彤', '3b6a7d4a54bfdefb1c090d7137424184', '否', '2020-05-10 15:27:58', '2020-05-07 14:34:36', '0', '', 'fld0213@163.com', '13125863987', '220106200009107439', '否', '82', '否');
INSERT INTO `t_users` VALUES ('d9f6b1cd-9dae-4007-8485-35225f656505', '王涵兰', '3d25ddf407b895d352bccea769ba76bd', '否', '2020-06-09 10:33:42', '2020-05-01 17:14:02', '0', '', 'dgb852@163.com', '18963222789', '310109199206209878', '否', '73', '否');
INSERT INTO `t_users` VALUES ('e5bad04c-12c5-4056-9453-777b0a186ee1', '苑凌菡', '4c9893b278e6169fb45cba9bc008d264', '否', '2020-05-12 21:48:16', '2020-05-01 12:09:30', '0', '', 'dmb@163.com', '15230402563', '330303199605206232', '否', '60', '否');

-- ----------------------------
-- Table structure for t_userscheck
-- ----------------------------
DROP TABLE IF EXISTS `t_userscheck`;
CREATE TABLE `t_userscheck` (
  `ucid` varchar(100) NOT NULL COMMENT 'UUID生成机制，主键',
  `uid` varchar(100) DEFAULT NULL COMMENT '外键，引用用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '员工名',
  `uphone` varchar(255) DEFAULT NULL COMMENT '员工手机号',
  `signstatus` varchar(10) DEFAULT NULL COMMENT '是否打卡  是|否',
  `signintime` datetime DEFAULT NULL COMMENT '打卡时间',
  `signout` varchar(10) DEFAULT NULL COMMENT '是否签退',
  `signouttime` datetime DEFAULT NULL COMMENT '签退时间',
  `istime` varchar(100) DEFAULT NULL COMMENT '打卡日期',
  PRIMARY KEY (`ucid`) USING BTREE,
  KEY `F_users_uc` (`uid`) USING BTREE,
  CONSTRAINT `F_users_uc` FOREIGN KEY (`uid`) REFERENCES `t_users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_userscheck
-- ----------------------------
INSERT INTO `t_userscheck` VALUES ('05321b9c-aed4-46c1-9a26-da4ea707f6df', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '吴以冬', '15224938860', '是', '2020-05-17 09:34:26', '是', '2020-05-17 10:12:37', '2020-05-17');
INSERT INTO `t_userscheck` VALUES ('0aa0025a-f687-455f-8a13-25e363ec299f', '74791108-e614-4f63-9278-4cbbf1ee5680', '黄健', '15603812963', '是', '2020-05-21 12:02:31', '否', null, '2020-05-21');
INSERT INTO `t_userscheck` VALUES ('0d536f18-4848-4c90-b383-abbd3298a88a', '63ea532b-1119-4376-b665-27fe934dc4be', '杨雪羽', '13103825639', '是', '2020-05-16 11:13:40', '是', '2020-05-16 11:54:58', '2020-05-16');
INSERT INTO `t_userscheck` VALUES ('0db2090d-d581-48cd-96c1-eed181487ed8', '63ea532b-1119-4376-b665-27fe934dc4be', '杨雪羽', '13103825639', '是', '2020-05-20 21:28:14', '否', null, '2020-05-20');
INSERT INTO `t_userscheck` VALUES ('144409a6-4012-42a2-8be4-0554532200be', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '程昆纶', '18963245896', '是', '2020-05-24 15:43:10', '否', null, '2020-05-24');
INSERT INTO `t_userscheck` VALUES ('14efcaf9-db91-4077-8171-919a07263af8', 'd9f6b1cd-9dae-4007-8485-35225f656505', '王涵兰', '18963222789', '是', '2020-05-21 12:02:11', '否', null, '2020-05-21');
INSERT INTO `t_userscheck` VALUES ('200abbf0-fffd-4c70-8837-b7eb94f575ca', '63ea532b-1119-4376-b665-27fe934dc4be', '杨雪羽', '13103825639', '是', '2020-05-21 15:29:50', '否', null, '2020-05-21');
INSERT INTO `t_userscheck` VALUES ('225ad46b-8f1a-46f3-8e6c-13fe20e2ef7d', '6d9ab61b-1bf8-4b35-b3ab-d6426c45dbc4', '冯丽珠', '15644578423', '是', '2020-05-20 18:23:46', '否', null, '2020-05-20');
INSERT INTO `t_userscheck` VALUES ('27faa914-d2fa-40c1-a31e-2103abaf99fc', '17d9a079-86df-4187-8ce3-935adf45e2d2', '柔小舞', '17530370181', '是', '2020-06-07 14:48:55', '否', null, '2020-06-07');
INSERT INTO `t_userscheck` VALUES ('28aa934a-5612-444e-affe-8f05f2e8634e', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '吴以冬', '15224938860', '是', '2020-05-23 09:03:19', '是', '2020-05-23 21:06:30', '2020-05-23');
INSERT INTO `t_userscheck` VALUES ('28c8e053-3c71-4c26-938a-31c7240d03d7', 'd9f6b1cd-9dae-4007-8485-35225f656505', '王涵兰', '18963222789', '否', null, '否', null, '2020-06-09');
INSERT INTO `t_userscheck` VALUES ('290abcb2-c0e1-4b2c-8e88-a94ba8ffc4c7', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '程昆纶', '18963245896', '是', '2020-05-20 15:34:30', '否', null, '2020-05-20');
INSERT INTO `t_userscheck` VALUES ('2d4519a2-aa18-448a-8ac6-f020ea18e9a8', 'd9f6b1cd-9dae-4007-8485-35225f656505', '王涵兰', '18963222789', '是', '2020-05-24 10:44:52', '否', null, '2020-05-24');
INSERT INTO `t_userscheck` VALUES ('2dadb089-2113-4a62-986b-6a90c04c07fe', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '吴以冬', '15224938860', '是', '2020-05-25 09:20:47', '是', '2020-05-25 10:38:46', '2020-05-25');
INSERT INTO `t_userscheck` VALUES ('2eae0d22-af12-40bf-9890-4c4a981cade6', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '吴以冬', '15224938860', '是', '2020-05-20 10:19:07', '否', null, '2020-05-20');
INSERT INTO `t_userscheck` VALUES ('3205e2d7-e8a3-4fe0-92b4-2ee427b1871c', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '吴以冬', '15224938860', '否', null, '否', null, '2020-06-09');
INSERT INTO `t_userscheck` VALUES ('32ca77d6-07a1-42ed-9b05-594a3f967f03', 'd9f6b1cd-9dae-4007-8485-35225f656505', '王涵兰', '18963222789', '是', '2020-05-20 10:53:01', '否', null, '2020-05-20');
INSERT INTO `t_userscheck` VALUES ('3904c0b2-2660-49ea-8fdb-2aa7d616184a', 'd9f6b1cd-9dae-4007-8485-35225f656505', '王涵兰', '18963222789', '是', '2020-05-25 11:11:11', '否', null, '2020-05-25');
INSERT INTO `t_userscheck` VALUES ('3b093f94-88f9-446d-9e3e-c23f5dafe511', '599e2fe7-fb0c-4adc-9741-5229a1a3e9f4', '张正平', '18796325463', '是', '2020-05-19 11:23:19', '否', null, '2020-05-19');
INSERT INTO `t_userscheck` VALUES ('3daf4790-c61f-4345-92cd-a6b21641f34f', '81dd8422-a1a0-406f-8761-3ddcd05ffa9d', '玉千秋', '13823656895', '是', '2020-05-23 21:37:34', '否', null, '2020-05-23');
INSERT INTO `t_userscheck` VALUES ('406cf9a0-5221-42f4-9197-5ea492882b9f', 'd9f6b1cd-9dae-4007-8485-35225f656505', '王涵兰', '18963222789', '是', '2020-05-19 18:38:02', '否', null, '2020-05-19');
INSERT INTO `t_userscheck` VALUES ('4188ddfd-c8fd-4f6c-867e-1f5cf768151e', '17d9a079-86df-4187-8ce3-935adf45e2d2', '柔小舞', '17530370181', '是', '2020-05-21 17:18:08', '是', '2020-05-21 20:16:43', '2020-05-21');
INSERT INTO `t_userscheck` VALUES ('54565554-0fa5-4ab4-b4ba-ab80e77fefc6', '63ea532b-1119-4376-b665-27fe934dc4be', '杨雪羽', '13103825639', '是', '2020-05-15 11:12:30', '是', '2020-05-15 11:28:36', '2020-05-15');
INSERT INTO `t_userscheck` VALUES ('55465af2-804a-455f-91de-1542c30a4954', '0af295c7-68b9-4d27-8121-f1474f3b75d7', '邓天韵', '13513959750', '否', null, '否', null, '2020-06-09');
INSERT INTO `t_userscheck` VALUES ('562ae15d-166d-4d59-bf13-e196f1419c73', '17d9a079-86df-4187-8ce3-935adf45e2d2', '柔小舞', '17530370181', '是', '2020-05-20 08:59:22', '是', '2020-05-20 15:56:26', '2020-05-20');
INSERT INTO `t_userscheck` VALUES ('5b36b3b6-1f39-4045-9d4e-27dd954248be', '0af295c7-68b9-4d27-8121-f1474f3b75d7', '邓天韵', '13513959750', '是', '2020-05-14 22:01:38', '是', '2020-05-14 22:01:15', '2020-05-14');
INSERT INTO `t_userscheck` VALUES ('6350dfe4-453e-4c3b-be9f-3f30251db1c9', '0af295c7-68b9-4d27-8121-f1474f3b75d7', '邓天韵', '13513959750', '是', '2020-05-20 10:41:09', '否', null, '2020-05-20');
INSERT INTO `t_userscheck` VALUES ('649e188c-bd21-4220-8f0d-d17323671698', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '吴以冬', '15224938860', '是', '2020-05-18 08:20:10', '是', '2020-05-18 14:07:40', '2020-05-18');
INSERT INTO `t_userscheck` VALUES ('6a56b958-d3eb-4390-8e50-140e3151c61a', 'a7e96917-f9f3-46bb-ba58-d6e8064ac37a', '宋沛文', '13123658412', '是', '2020-05-21 12:01:44', '否', null, '2020-05-21');
INSERT INTO `t_userscheck` VALUES ('6ba66598-1484-4801-9c65-00424fc5ddb8', 'a7e96917-f9f3-46bb-ba58-d6e8064ac37a', '宋沛文', '13123658412', '是', '2020-05-20 15:42:32', '否', null, '2020-05-20');
INSERT INTO `t_userscheck` VALUES ('72632561-1c32-43e7-b8f7-1ca13fed541a', '63ea532b-1119-4376-b665-27fe934dc4be', '杨雪羽', '13103825639', '是', '2020-05-14 20:31:00', '是', '2020-05-14 22:01:15', '2020-05-14');
INSERT INTO `t_userscheck` VALUES ('8251fed7-2e9e-4e0b-b45b-642253a4cad9', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '吴以冬', '15224938860', '是', '2020-05-16 11:55:08', '是', '2020-05-16 16:29:00', '2020-05-16');
INSERT INTO `t_userscheck` VALUES ('82a54087-ca6c-4002-b8ac-4601faf310e2', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '吴以冬', '15224938860', '是', '2020-05-22 09:27:03', '否', null, '2020-05-22');
INSERT INTO `t_userscheck` VALUES ('85901d4d-8157-4d6b-8842-0cb44cbbe3c0', '63ea532b-1119-4376-b665-27fe934dc4be', '杨雪羽', '13103825639', '是', '2020-05-17 08:18:49', '是', '2020-05-17 09:34:16', '2020-05-17');
INSERT INTO `t_userscheck` VALUES ('88575aa9-9d04-4e7d-a6b2-481f6db61311', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '吴以冬', '15224938860', '否', null, '否', null, '2020-06-08');
INSERT INTO `t_userscheck` VALUES ('88922f70-bd30-444c-86bf-0d6f36c75010', '0af295c7-68b9-4d27-8121-f1474f3b75d7', '邓天韵', '13513959750', '是', '2020-05-22 09:36:33', '否', null, '2020-05-22');
INSERT INTO `t_userscheck` VALUES ('899892fd-a47b-4802-b431-7f963cfb1de2', '17d9a079-86df-4187-8ce3-935adf45e2d2', '柔小舞', '17530370181', '否', null, '否', null, '2020-06-09');
INSERT INTO `t_userscheck` VALUES ('8bc273aa-f9f5-4a00-bf7f-63ff6c057630', '6d9ab61b-1bf8-4b35-b3ab-d6426c45dbc4', '冯丽珠', '15644578423', '是', '2020-05-19 11:35:03', '否', null, '2020-05-19');
INSERT INTO `t_userscheck` VALUES ('92191e44-5187-43a5-9231-253d00d4a123', '81dd8422-a1a0-406f-8761-3ddcd05ffa9d', '玉千秋', '13823656895', '是', '2020-05-22 18:09:35', '否', null, '2020-05-22');
INSERT INTO `t_userscheck` VALUES ('943095c2-d3fb-47c9-af4e-8fd42834c96d', 'd9f6b1cd-9dae-4007-8485-35225f656505', '王涵兰', '18963222789', '是', '2020-05-18 08:33:24', '是', '2020-05-18 11:47:25', '2020-05-18');
INSERT INTO `t_userscheck` VALUES ('9740b118-2073-4fe4-801c-4c20884344d9', 'd9f6b1cd-9dae-4007-8485-35225f656505', '王涵兰', '18963222789', '是', '2020-06-08 11:32:04', '否', null, '2020-06-08');
INSERT INTO `t_userscheck` VALUES ('a00b9c28-42ec-443f-a4ab-f23e7d7375ea', '17d9a079-86df-4187-8ce3-935adf45e2d2', '柔小舞', '17530370181', '是', '2020-05-25 10:21:33', '是', '2020-05-25 18:13:53', '2020-05-25');
INSERT INTO `t_userscheck` VALUES ('a182e387-c623-4a57-976e-f4c62a1b304d', '17d9a079-86df-4187-8ce3-935adf45e2d2', '柔小舞', '17530370181', '是', '2020-05-16 22:37:24', '否', null, '2020-05-16');
INSERT INTO `t_userscheck` VALUES ('a26a7156-303b-4e47-a1af-ed6e9667a0f4', '0af295c7-68b9-4d27-8121-f1474f3b75d7', '邓天韵', '13513959750', '是', '2020-05-19 18:37:29', '是', '2020-05-19 19:10:08', '2020-05-19');
INSERT INTO `t_userscheck` VALUES ('a4206f6c-aae3-41ae-a5e1-a11926149dad', '63ea532b-1119-4376-b665-27fe934dc4be', '杨雪羽', '13103825639', '是', '2020-05-18 08:19:32', '是', '2020-05-18 23:04:42', '2020-05-18');
INSERT INTO `t_userscheck` VALUES ('a699a08e-ccb6-4677-af1e-0c5ba9d85e7d', '17d9a079-86df-4187-8ce3-935adf45e2d2', '柔小舞', '17530370181', '是', '2020-05-22 15:25:11', '否', null, '2020-05-22');
INSERT INTO `t_userscheck` VALUES ('ad042788-96aa-4315-933c-322dfda4b17f', '0af295c7-68b9-4d27-8121-f1474f3b75d7', '邓天韵', '13513959750', '是', '2020-05-21 11:49:29', '否', null, '2020-05-21');
INSERT INTO `t_userscheck` VALUES ('b10c8281-2e25-44f5-8677-1ab674398ce8', '9156d032-32e4-4b5b-861a-97b7c767b82b', '李伟泽', '15173174746', '是', '2020-05-16 17:32:21', '是', '2020-05-16 17:58:04', '2020-05-16');
INSERT INTO `t_userscheck` VALUES ('b1b298b9-68fc-4597-b3c8-1121ccd770e3', '0af295c7-68b9-4d27-8121-f1474f3b75d7', '邓天韵', '13513959750', '是', '2020-05-25 10:41:22', '否', null, '2020-05-25');
INSERT INTO `t_userscheck` VALUES ('b321a1bc-4e9b-4eca-8bba-a5257371cffc', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '吴以冬', '15224938860', '是', '2020-05-19 19:08:14', '否', null, '2020-05-19');
INSERT INTO `t_userscheck` VALUES ('bc306211-0a17-4c6f-a472-63a6dbbc6f65', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '程昆纶', '18963245896', '是', '2020-05-21 17:16:32', '否', null, '2020-05-21');
INSERT INTO `t_userscheck` VALUES ('bd8465fa-9707-465e-bf32-453ee71c3b0a', '6d9ab61b-1bf8-4b35-b3ab-d6426c45dbc4', '冯丽珠', '15644578423', '是', '2020-05-25 09:47:47', '是', '2020-05-25 10:21:10', '2020-05-25');
INSERT INTO `t_userscheck` VALUES ('c40c3070-ba8f-4eba-95cd-3aafca383005', '17d9a079-86df-4187-8ce3-935adf45e2d2', '柔小舞', '17530370181', '是', '2020-06-08 11:36:28', '否', null, '2020-06-08');
INSERT INTO `t_userscheck` VALUES ('c48a15ac-e2f2-44d8-aac4-eb1bb0f06863', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '吴以冬', '15224938860', '是', '2020-05-21 16:53:20', '否', null, '2020-05-21');
INSERT INTO `t_userscheck` VALUES ('ca60fc5d-ee32-4eca-8e9b-fe9b98e1c4f9', 'a6f7fb80-710a-40e0-b445-e9db2e725773', '李元容', '18759632145', '是', '2020-05-19 15:48:09', '否', null, '2020-05-19');
INSERT INTO `t_userscheck` VALUES ('d2479441-4bd8-4b48-99d3-6b13fed65039', 'd9f6b1cd-9dae-4007-8485-35225f656505', '王涵兰', '18963222789', '是', '2020-05-17 10:13:45', '是', '2020-05-17 11:54:24', '2020-05-17');
INSERT INTO `t_userscheck` VALUES ('d2c8e863-aa72-4225-91c1-e367f08da873', '63ea532b-1119-4376-b665-27fe934dc4be', '杨雪羽', '13103825639', '是', '2020-06-08 21:45:29', '否', null, '2020-06-08');
INSERT INTO `t_userscheck` VALUES ('d2ca1d5f-0903-43b0-8f76-74ab7802b490', '0af295c7-68b9-4d27-8121-f1474f3b75d7', '邓天韵', '13513959750', '是', '2020-05-23 21:36:11', '否', null, '2020-05-23');
INSERT INTO `t_userscheck` VALUES ('d64d2442-8f24-4591-aee3-f1a73f845192', 'd9f6b1cd-9dae-4007-8485-35225f656505', '王涵兰', '18963222789', '是', '2020-05-23 21:13:35', '否', null, '2020-05-23');
INSERT INTO `t_userscheck` VALUES ('d78c1d04-55a8-45b9-84d0-c1ba0094c377', '81dd8422-a1a0-406f-8761-3ddcd05ffa9d', '玉千秋', '13823656895', '是', '2020-05-19 19:11:36', '否', null, '2020-05-19');
INSERT INTO `t_userscheck` VALUES ('da0a8d07-a10e-45a0-b35d-2d137b31e8d6', '0af295c7-68b9-4d27-8121-f1474f3b75d7', '邓天韵', '13513959750', '否', null, '否', null, '2020-06-08');
INSERT INTO `t_userscheck` VALUES ('dbd2b2d4-07a4-43b7-814b-ced63081c463', '17d9a079-86df-4187-8ce3-935adf45e2d2', '柔小舞', '17530370181', '是', '2020-05-15 14:51:23', '是', '2020-05-15 15:01:43', '2020-05-15');
INSERT INTO `t_userscheck` VALUES ('debfa1b4-f505-44d9-a2bf-94e9140b2e88', '17d9a079-86df-4187-8ce3-935adf45e2d2', '柔小舞', '17530370181', '是', '2020-05-19 20:48:13', '否', null, '2020-05-19');
INSERT INTO `t_userscheck` VALUES ('e232aae7-4c5c-4915-87ee-1be61fe7efbc', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '吴以冬', '15224938860', '是', '2020-05-15 14:50:43', '否', null, '2020-05-15');
INSERT INTO `t_userscheck` VALUES ('e6a6b6d9-bd5e-4996-b018-445f4d08469c', '63ea532b-1119-4376-b665-27fe934dc4be', '杨雪羽', '13103825639', '是', '2020-05-19 11:22:05', '是', '2020-05-19 15:47:01', '2020-05-19');
INSERT INTO `t_userscheck` VALUES ('ee2038d1-5687-4325-876d-1b154587de8f', 'b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '程昆纶', '18963245896', '是', '2020-05-25 09:22:04', '否', null, '2020-05-25');
INSERT INTO `t_userscheck` VALUES ('f6800c70-546e-4d8e-b639-f3c98e5d64d3', '0935c440-4a58-4a7e-ad9b-20306bf311b2', '吴以冬', '15224938860', '否', null, '否', null, '2020-05-26');
INSERT INTO `t_userscheck` VALUES ('f68791db-3fa9-459b-a750-08132f1f5f99', '17d9a079-86df-4187-8ce3-935adf45e2d2', '柔小舞', '17530370181', '是', '2020-05-17 21:32:37', '否', null, '2020-05-17');
INSERT INTO `t_userscheck` VALUES ('f79ff2f1-a3ae-4073-8ff3-3a04fbe8c634', '0af295c7-68b9-4d27-8121-f1474f3b75d7', '邓天韵', '13513959750', '是', '2020-05-24 19:29:38', '否', null, '2020-05-24');
INSERT INTO `t_userscheck` VALUES ('fc75fe48-7066-4477-98ea-c3d79b805f96', 'd9f6b1cd-9dae-4007-8485-35225f656505', '王涵兰', '18963222789', '是', '2020-05-22 09:45:06', '否', null, '2020-05-22');

-- ----------------------------
-- Table structure for t_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `t_user_roles`;
CREATE TABLE `t_user_roles` (
  `uid` varchar(255) DEFAULT NULL COMMENT '外键，引用用户id',
  `rid` int(11) DEFAULT NULL COMMENT '外键，引用角色id',
  KEY `F_users` (`uid`) USING BTREE,
  KEY `F_roles` (`rid`) USING BTREE,
  CONSTRAINT `F_roles` FOREIGN KEY (`rid`) REFERENCES `t_roles` (`rid`),
  CONSTRAINT `F_users` FOREIGN KEY (`uid`) REFERENCES `t_users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_user_roles
-- ----------------------------
INSERT INTO `t_user_roles` VALUES ('63ea532b-1119-4376-b665-27fe934dc4be', '1');
INSERT INTO `t_user_roles` VALUES ('0935c440-4a58-4a7e-ad9b-20306bf311b2', '2');
INSERT INTO `t_user_roles` VALUES ('17d9a079-86df-4187-8ce3-935adf45e2d2', '1');
INSERT INTO `t_user_roles` VALUES ('599e2fe7-fb0c-4adc-9741-5229a1a3e9f4', '2');
INSERT INTO `t_user_roles` VALUES ('0af295c7-68b9-4d27-8121-f1474f3b75d7', '4');
INSERT INTO `t_user_roles` VALUES ('9156d032-32e4-4b5b-861a-97b7c767b82b', '3');
INSERT INTO `t_user_roles` VALUES ('81dd8422-a1a0-406f-8761-3ddcd05ffa9d', '3');
INSERT INTO `t_user_roles` VALUES ('a6f7fb80-710a-40e0-b445-e9db2e725773', '2');
INSERT INTO `t_user_roles` VALUES ('a7e96917-f9f3-46bb-ba58-d6e8064ac37a', '3');
INSERT INTO `t_user_roles` VALUES ('b4df1312-c09d-40ab-8a2a-b5fdaa658d51', '2');
INSERT INTO `t_user_roles` VALUES ('e5bad04c-12c5-4056-9453-777b0a186ee1', '2');
INSERT INTO `t_user_roles` VALUES ('6d9ab61b-1bf8-4b35-b3ab-d6426c45dbc4', '2');
INSERT INTO `t_user_roles` VALUES ('74791108-e614-4f63-9278-4cbbf1ee5680', '3');
INSERT INTO `t_user_roles` VALUES ('bfba35d7-6dba-4d0e-b2fc-029e79e9725c', '3');
INSERT INTO `t_user_roles` VALUES ('d9f6b1cd-9dae-4007-8485-35225f656505', '3');
DROP TRIGGER IF EXISTS `tr_modules_insert`;
DELIMITER ;;
CREATE TRIGGER `tr_modules_insert` AFTER INSERT ON `t_modules` FOR EACH ROW begin 
  insert into t_permission(pname,parentid,permissionmodule) values(new.mname,0,new.mid);
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tr_modules_update`;
DELIMITER ;;
CREATE TRIGGER `tr_modules_update` AFTER UPDATE ON `t_modules` FOR EACH ROW begin 
  update t_permission set pname = new.mname where permissionmodule = new.mid and parentid = 0;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tr_modules_delete`;
DELIMITER ;;
CREATE TRIGGER `tr_modules_delete` BEFORE DELETE ON `t_modules` FOR EACH ROW begin 
  delete from t_permission where permissionmodule = old.mid and parentid = 0;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tr_roles_delete`;
DELIMITER ;;
CREATE TRIGGER `tr_roles_delete` BEFORE DELETE ON `t_roles` FOR EACH ROW begin 
  -- 删除角色权限中间表的数据
  delete from t_roles_permission where roleid = old.rid;
  -- 删除用户角色中间表的数据
  delete from t_user_roles where rid = old.rid;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tr_stu_delete`;
DELIMITER ;;
CREATE TRIGGER `tr_stu_delete` BEFORE DELETE ON `t_students` FOR EACH ROW begin 
  -- 删除动态消息
  delete from t_dynamicmessage where sid = old.sid;
  -- 删除跟踪记录
  delete from t_follows where sid = old.sid;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tr_user_phone`;
DELIMITER ;;
CREATE TRIGGER `tr_user_phone` AFTER UPDATE ON `t_users` FOR EACH ROW begin 
  update t_userscheck set uphone = new.protectmtel,username = new.loginname where uid = old.uid;
  update t_students set uphone = new.protectmtel,szxsid = new.loginname where uphone =old.protectmtel;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tr_user_delete`;
DELIMITER ;;
CREATE TRIGGER `tr_user_delete` BEFORE DELETE ON `t_users` FOR EACH ROW begin 
  -- 删除打卡记录
  delete from t_userscheck where uid = old.uid;
  -- 删除动态消息
  delete from t_dynamicmessage where uid = old.uid or senderid = old.uid;
  -- 删除跟踪记录
  delete from t_follows where uid = old.uid;
end
;;
DELIMITER ;
