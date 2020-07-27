/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : 118.178.181.137:3306
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 27/07/2020 23:31:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for about
-- ----------------------------
DROP TABLE IF EXISTS `about`;
CREATE TABLE `about`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关于ID',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `created` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of about
-- ----------------------------
INSERT INTO `about` VALUES (1, 'https://yun.sevenit.cn/2020/07/21/bfd07b05f556b.jpg', '网行侠', '欢迎来到网行侠博客！我是Ma Ke，是一个帅气的Boy，热爱编程,专注于Javaweb开发', '<h2 style=\"background: url(http://yun.sevenit.cn/2020/07/04/0e08579dde3a6.png) no-repeat bottom right;margin: 20px 0;\r\n    font-size: 18px;width: 120px;\r\n    clear: both;\r\n    background-size: 40px;\r\n    border-bottom: 1px #000 dashed;\r\n    padding-left: 10px;\r\n    padding-bottom: 5px;\r\n    display: inline-block;\" class=\"title\">我是谁？</h2>\r\n\r\n<p style=\"text-indent: 2em;\r\n    letter-spacing: 2px;\r\n    line-height: 30px;\r\n    font-size: 14px;\">一名技术宅,精通iapp、Javascrip、python、Java、C 等语言 Hello world 的编写，曾以“小科网络”的旗号在网上发布辅助软件数款(均为iapp编写)。所发布的软件总下载量破百万！(才怪咧)</p>\r\n\r\n<h2 style=\"background: url(http://yun.sevenit.cn/2020/07/04/0e08579dde3a6.png) no-repeat bottom right;margin: 20px 0;\r\n    font-size: 18px;width: 120px;\r\n    clear: both;\r\n    background-size: 40px;\r\n    border-bottom: 1px #000 dashed;\r\n    padding-left: 10px;\r\n    padding-bottom: 5px;\r\n    display: inline-block;\" class=\"title\">关于本站</h2>\r\n\r\n<p style=\"text-indent: 2em;\r\n    letter-spacing: 2px;\r\n    line-height: 30px;\r\n    font-size: 14px;\">本博客是一个基于前后端分离博客系统。前台使用Vue + Element , 后端使用spring boot + mybatis-plus进行开发，使用 Jwt + shiro做登录验证和权限校验，使用ElasticSearch作为全文检索,支持高亮显示，文件支持上传七牛云，编辑器支持markdown语法。 从大学的时候开始，就一直想要搭建一套属于自己的博客系统，但是一直被没有去弄。 疫情期间在家呆着实在没事，又一次激起了我写博客的激情，博主结合目前流行的技术栈，使用前后端分离架构进行项目的开发，也希望自己能够将项目一步步进行完善。 小科博客大部分功能是我个人进行开发的，因能力有限，其中很多技术都是一边学习一边使用的，可以说小科博客也是一个我用来熟悉技术的项目，所以很多地方可能考虑不周， 在加上没工作经验，故有能改正的地方，还请各位大哥能够指出~</p>\r\n\r\n<p style=\"text-indent: 2em;\r\n    letter-spacing: 2px;\r\n    line-height: 30px;\r\n    font-size: 14px;\">我通过搭建博客来记录自己的学习生涯,希望借此机会可以学习更多的知识,并在这丰富多彩的互联网认识到更多志同道合的人来一起学习</p>\r\n\r\n<h2 style=\"background: url(http://yun.sevenit.cn/2020/07/04/0e08579dde3a6.png) no-repeat bottom right;margin: 20px 0;\r\n    font-size: 18px;width: 120px;\r\n    clear: both;\r\n    background-size: 40px;\r\n    border-bottom: 1px #000 dashed;\r\n    padding-left: 10px;\r\n    padding-bottom: 5px;\r\n    display: inline-block;\" class=\"title\">技术方向</h2>\r\n\r\n<ol style=\"letter-spacing: 2px;\r\n    line-height: 30px;\r\n    font-size: 14px;\">\r\n    <li>熟悉HTML、CSS、Javascript、AJAX等前端页面技术</li>\r\n	<li>熟悉主流的Vue,uniapp和BootStrap等前端框架</li>\r\n    <li>熟悉node,会使用webpack等工具开发工程化项目</li>\r\n    <li>熟练掌握JavaSE基础知识，擅长使用ssm,SpringBoot等框架的开发</li>\r\n    <li>熟练MySQL数据库，熟悉Redis，能熟练地运用SQL语句</li>\r\n    <li>掌握Linux等系统基本命令操作,Docker的部署操作</li>\r\n    <li>使用Python爬取有效数据进行数据分析</li>\r\n    <li>有良好的代码编写习惯(驼峰式编写以及注释和排版)，熟悉开发/使用文档的编写</li>\r\n    <li>其他技术正在学习中！唯一能持久的竞争优势是胜过竞争对手的学习能力</li>\r\n</ol>\r\n\r\n', '2020-07-07 00:45:51');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '轮播图ID',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '轮播图片',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'URL',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (1, 'http://yun.sevenit.cn/2020/07/05/f60ab297c4c41.jpg', 'http://blog.xiaoke6.cn');
INSERT INTO `banner` VALUES (2, 'http://yun.sevenit.cn/2020/07/05/f069b520d60bb.jpg', 'http://blog.xiaoke6.cn');
INSERT INTO `banner` VALUES (3, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595326800283&di=0d95cfcb68f612f2f7052f3976174f17&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171230%2F5d148f5ba1d44552a72dd738a9b1dbd1.jpeg', 'http://blog.xiaoke6.cn');

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '博客ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `description` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '摘要',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '博客缩略图',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `view` int(10) NOT NULL COMMENT '文章浏览量',
  `tab_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签ID',
  `sort_id` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类ID',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, 1, '最值得学习的博客项目eblog', 'eblog是一个基于Springboot2.1.2开发的博客学习项目，为了让项目融合更多的知识点，达到学习目的，编写了详细的从0到1开发文档。主要学习包括：自定义Freemarker标签，使用shiro+redis完成了会话共享，redis的zset结构完成本周热议排行榜，t-io+websocket完成即时消息通知和群聊，rabbitmq+elasticsearch完成博客内容搜索引擎等。值得学习的地方很多！', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=389917905,3016311303&fm=26&gp=0.jpg', '**推荐阅读：**\n\n[分享一套SpringBoot开发博客系统源码，以及完整开发文档！速度保存！](https://mp.weixin.qq.com/s/jz6e977xP-OyaAKNjNca8w)\n\n[Github上最值得学习的100个Java开源项目，涵盖各种技术栈！](https://mp.weixin.qq.com/s/N-U0TaEUXnBFfBsmt_OESQ)\n\n[2020年最新的常问企业面试题大全以及答案](https://mp.weixin.qq.com/s/lR5LC5GnD2Gs59ecV5R0XA)\n[小柯](http://xiaoke6.cn)', 9, '2', '2', '2020-07-22 14:21:52');
INSERT INTO `blog` VALUES (2, 1, '生活就像海洋，只有意志坚强的人才能到达彼岸', '这里是摘要哈哈哈', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2580742971,3368550671&fm=26&gp=0.jpg', '内容？？？', 10, '1', '1', '2020-07-22 14:21:54');
INSERT INTO `blog` VALUES (3, 1, 'mysql重新设置从1开始连续自增', '项目开发中，有些固定数据在数据表中，主键是从1自增的，有时候我们会删除一些数据，这种情况下，主键就会不连续。如何恢复到像第一次插入数据一样主键从1开始连续增长?', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595324420236&di=d0bd7f352d62d77c7bb81c0dcbf234d8&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fbaike%2Fpic%2Fitem%2F83025aafa40f4bfb1527549d064f78f0f6361800.jpg', '\n> ​         项目开发中，有些固定数据在数据表中，主键是从1自增的，有时候我们会删除一些数据，这种情况下，主键就会不连续。如何恢复到像第一次插入数据一样主键从1开始连续增长?\n>\n\n这里我找到一种解决方法：\n\n![](https://img2018.cnblogs.com/blog/1034738/201905/1034738-20190517181423623-1752505204.png)\n\n如上面一张表，主键id是自增的\n\n插入两条数据：\n\n![](https://img2018.cnblogs.com/blog/1034738/201905/1034738-20190517181528127-298136564.png)\n\n此时我们想让主键为1，2...\n\n首先我们取消主键，并且勾选掉，主键自增\n\n![](https://img2018.cnblogs.com/blog/1034738/201905/1034738-20190517181922375-1118680248.png)\n\n然后我们设置一个不重复字段为主键\n\n![](https://img2018.cnblogs.com/blog/1034738/201905/1034738-20190517182012414-1335861642.png)\n\n然后我们把id字段设置为null\n\n```sql\nUPDATE testh set id = NULL\n```\n\n最后恢复主键\n\n![](https://img2018.cnblogs.com/blog/1034738/201905/1034738-20190517182210997-1243985286.png)\n\n主键会自动从1开始连续增长\n\n![](https://img2018.cnblogs.com/blog/1034738/201905/1034738-20190517182323154-613954363.png)\n\n即使再插入一条数据，也是按照顺序增长下去的\n\n```sql\nINSERT INTO `interface_crm`.`testh`(`id`, `name`) VALUES (null, \'a\');\n```\n\n![](https://img2018.cnblogs.com/blog/1034738/201905/1034738-20190517183824038-374034732.png)\n', 6, '9', '4', '2020-07-19 06:52:40');
INSERT INTO `blog` VALUES (4, 1, '你真的会写单例模式吗?', '单例模式可能是代码最少的模式了，但是少不一定意味着简单，想要用好、用对单例模式，还真得费一番脑筋。本文对 Java 中常见的单例模式写法做了一个总结，如有错漏之处，恳请读者指正。', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595330526742&di=572039766e1f5d3dad79891b7ccf2e51&imgtype=0&src=http%3A%2F%2Fimg1.sycdn.imooc.com%2F5a82ed780001f64906960362.png', '> 作者：吃桔子的攻城狮 来源：http://www.tekbroaden.com/singleton-java.html\n\n\n单例模式可能是代码最少的模式了，但是少不一定意味着简单，想要用好、用对单例模式，还真得费一番脑筋。本文对 Java 中常见的单例模式写法做了一个总结，如有错漏之处，恳请读者指正。\n\n饿汉法\n===\n\n顾名思义，饿汉法就是在第一次引用该类的时候就创建对象实例，而不管实际是否需要创建。代码如下：\n\n```\npublic class Singleton {  \n    private static Singleton = new Singleton();\n    private Singleton() {}\n    public static getSignleton(){\n        return singleton;\n    }\n}\n\n```\n\n这样做的好处是编写简单，但是无法做到延迟创建对象。但是我们很多时候都希望对象可以尽可能地延迟加载，从而减小负载，所以就需要下面的懒汉法：\n', 4, '2', '3', '2020-07-22 14:22:03');
INSERT INTO `blog` VALUES (5, 1, '真正理解Mysql的四种隔离级别@', '事务是应用程序中一系列严密的操作，所有操作必须成功完成，否则在每个操作中所作的所有更改都会被撤消。也就是事务具有原子性，一个事务中的一系列的操作要么全部成功，要么一个都不做。\n\n事务的结束有两种，当事务中的所以步骤全部成功执行时，事务提交。如果其中一个步骤失败，将发生回滚操作，撤消撤消之前到事务开始时的所以操作。', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595330719654&di=f04be7ba73cf53749680259dc694d707&imgtype=0&src=http%3A%2F%2Fwww.liuhaihua.cn%2Fwp-content%2Fuploads%2F2019%2F05%2F2iyQfau.jpg', '### 什么是事务  \n\n> 事务是应用程序中一系列严密的操作，所有操作必须成功完成，否则在每个操作中所作的所有更改都会被撤消。也就是事务具有原子性，一个事务中的一系列的操作要么全部成功，要么一个都不做。\n> \n> 事务的结束有两种，当事务中的所以步骤全部成功执行时，事务提交。如果其中一个步骤失败，将发生回滚操作，撤消撤消之前到事务开始时的所以操作。\n\n**事务的 ACID**\n\n事务具有四个特征：原子性（ Atomicity ）、一致性（ Consistency ）、隔离性（ Isolation ）和持续性（ Durability ）。这四个特性简称为 ACID 特性。\n\n> 1 、原子性。事务是数据库的逻辑工作单位，事务中包含的各操作要么都做，要么都不做\n> \n> 2 、一致性。事 务执行的结果必须是使数据库从一个一致性状态变到另一个一致性状态。因此当数据库只包含成功事务提交的结果时，就说数据库处于一致性状态。如果数据库系统 运行中发生故障，有些事务尚未完成就被迫中断，这些未完成事务对数据库所做的修改有一部分已写入物理数据库，这时数据库就处于一种不正确的状态，或者说是 不一致的状态。', 7, '9', '4', '2020-07-22 14:22:05');
INSERT INTO `blog` VALUES (6, 1, '博客项目eblog讲解视频上线啦，长达17个小时！！', '1. 慕课网免费资源好久都没更新了，新教程大都付费\n2. B站上的视频繁多，通过收藏和弹幕数量通常很容易判断出视频是否优质\n3. 讲真，B站的弹幕文化，让我觉得，我不是一个在学习，自古人才出评论。哈哈哈\n4. B站视频通常广告少，up主的用心录制，通常只为了你关注他', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2692187495,213016703&fm=26&gp=0.jpg', 'ok，再回到我们的eblog项目，源码、文档、视频我都开源出来了。来些基本操作：github上给个star，B站视频给个三连支持咧。\n\neblog源码：https://github.com/MarkerHub/eblog\n\n点击这里：[10+篇完整开发文档](https://mp.weixin.qq.com/mp/homepage?__biz=MzIwODkzOTc1MQ==&hid=1&sn=8e512316c3dfe140e636d0c996951166)\n\n![](//image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/20200508/c290d945b7d24c79b172759bdb5b94e0.png)\n\n视频讲解：（记得关注我噢！）\n\nhttps://www.bilibili.com/video/BV1ri4y1x71A\n\n![](//image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/20200508/983b5abc1c934360a1a1362347a275f7.png)\n\n项目其实还很多bug的，哈哈，我还需要进行二次迭代，到时候再发迭代文档出来。\n\n关注下我的B站，作为一个自媒体的自由职业者，没有什么比涨粉更让我开心的了，嘻嘻。\n\n近期即将推出的视频教程：\n\n1. 搭建脚手架，前后端分离首秀\n2. Shiro入门到精通教程\n3. SpringBoot2.2.6最新入门教程', 8, '5', '1', '2020-07-22 14:22:06');

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '友链ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '友链名字',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '友链地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES (1, '小科博客', 'http://blog.xiaoke6.cn');
INSERT INTO `link` VALUES (2, '小科音乐', 'http://music.xiaoke6.cn');
INSERT INTO `link` VALUES (3, '小科影视', 'http://tv.xiaoke6.cn');
INSERT INTO `link` VALUES (4, '小科购物', 'http://tao.xiaoke6.cn');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `per_code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限代码字符串',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `per_code`(`per_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '查看用户', 'user:view');
INSERT INTO `permission` VALUES (2, '操作用户', 'user:edit');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (2, '游客');
INSERT INTO `role` VALUES (1, '管理员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `permission_id`(`permission_id`) USING BTREE,
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1, 1);
INSERT INTO `role_permission` VALUES (2, 1, 2);
INSERT INTO `role_permission` VALUES (3, 2, 1);

-- ----------------------------
-- Table structure for sort
-- ----------------------------
DROP TABLE IF EXISTS `sort`;
CREATE TABLE `sort`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类图片',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sort
-- ----------------------------
INSERT INTO `sort` VALUES (1, '前端', 'http://blog.shiminghui.top/category/front.png', '2020-07-01 12:09:51', '2020-07-01 12:09:53');
INSERT INTO `sort` VALUES (2, '后端', 'http://blog.shiminghui.top/category/back.png', '2020-07-01 12:10:08', '2020-07-01 12:10:11');
INSERT INTO `sort` VALUES (3, '生活', 'http://blog.shiminghui.top/category/lift.jpg', '2020-07-01 12:10:26', '2020-07-01 12:10:28');
INSERT INTO `sort` VALUES (4, '数据库', 'http://blog.shiminghui.top/category/database.png', '2020-07-01 12:10:41', '2020-07-01 12:10:43');
INSERT INTO `sort` VALUES (5, '编程语言', 'http://blog.shiminghui.top/category/language.png', '2020-07-01 12:10:57', '2020-07-01 12:10:59');

-- ----------------------------
-- Table structure for tab
-- ----------------------------
DROP TABLE IF EXISTS `tab`;
CREATE TABLE `tab`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名字',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签图片',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tab
-- ----------------------------
INSERT INTO `tab` VALUES (1, 'java', 'http://blog.shiminghui.top/tag/java.png', '2020-07-01 12:04:49', '2020-07-01 12:04:56');
INSERT INTO `tab` VALUES (2, 'Spring', 'https://yun.sevenit.cn/2020/07/04/e89e2769e3312.png', '2020-07-01 12:05:22', '2020-07-03 22:33:26');
INSERT INTO `tab` VALUES (3, 'SpringBoot', 'http://yun.sevenit.cn/2020/07/05/33d034c6c3b50.jpg', '2020-07-01 12:05:46', '2020-07-01 12:05:50');
INSERT INTO `tab` VALUES (4, 'Spring Cloud', 'http://yun.sevenit.cn/2020/07/05/2210bdedcd146.jpg', '2020-07-01 12:06:20', '2020-07-01 12:06:23');
INSERT INTO `tab` VALUES (5, 'HTML', 'http://blog.shiminghui.top/tag/html.png', '2020-07-01 12:07:11', '2020-07-01 12:07:26');
INSERT INTO `tab` VALUES (6, 'Vue', 'http://blog.shiminghui.top/tag/vue.png', '2020-07-01 12:07:45', '2020-07-01 12:07:48');
INSERT INTO `tab` VALUES (7, 'BootStrap', 'https://yun.sevenit.cn/2020/07/05/5aa432fe747bc.jpg', '2020-07-01 12:08:36', '2020-07-01 12:08:40');
INSERT INTO `tab` VALUES (8, 'Mybatis Plus', 'http://yun.sevenit.cn/2020/07/05/24959d474544a.jpg', '2020-07-01 12:08:55', '2020-07-01 12:08:58');
INSERT INTO `tab` VALUES (9, 'Mysql', 'http://blog.shiminghui.top/category/database.png', '2020-07-19 14:58:04', '2020-07-19 14:58:08');

-- ----------------------------
-- Table structure for tool
-- ----------------------------
DROP TABLE IF EXISTS `tool`;
CREATE TABLE `tool`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '工具ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工具名字',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工具描述',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工具链接',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工具图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tool
-- ----------------------------
INSERT INTO `tool` VALUES (1, '音乐随身听', '收集全网各个平台歌曲', 'http://music.xiaoke6.cn', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1979857211,653592518&fm=26&gp=0.jpg');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `iphone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1：男  2：女',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `last_login` timestamp NULL DEFAULT NULL COMMENT '最后登录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'makerhub', '网行侠', 'http://yun.sevenit.cn/2020/06/29/b80ce767bfcaf.jpg', '15083071210', '1', '702164551@qq.com', '96e79218965eb72c92a549dd5a330112', '2020-07-19 08:24:19', '2020-07-03 19:38:47');
INSERT INTO `user` VALUES (2, 'test123', '网行侠', 'http://yun.sevenit.cn/2020/06/29/b80ce767bfcaf.jpg', '13069382092', '2', '51028814@qq.com', 'cc03e747a6afbbcbf8be7668acfebee5', '2020-07-19 08:24:21', '2020-07-03 19:38:50');
INSERT INTO `user` VALUES (5, '555555', '网行侠', 'http://yun.sevenit.cn/2020/06/29/b80ce767bfcaf.jpg', '13069382092', '2', '51028814@qq.com', 'cc03e747a6afbbcbf8be7668acfebee5', '2020-07-19 08:24:22', '2020-07-18 22:03:02');
INSERT INTO `user` VALUES (6, '15083071210', '网行侠', 'http://yun.sevenit.cn/2020/06/29/b80ce767bfcaf.jpg', '15083071210', '2', '51028814@qq.com', '24747999b1fd4761e08c9647bac300e3', '2020-07-19 08:24:24', '2020-07-18 22:03:06');
INSERT INTO `user` VALUES (7, '123', '网行侠', 'http://yun.sevenit.cn/2020/06/29/b80ce767bfcaf.jpg', '13069382092', '1', '51028814@qq.com', '96e79218965eb72c92a549dd5a330112', '2020-07-19 08:24:25', '2020-07-18 22:03:09');
INSERT INTO `user` VALUES (8, '123', '网行侠', 'http://yun.sevenit.cn/2020/06/29/b80ce767bfcaf.jpg', '13069382092', '1', '51028814@qq.com', '96e79218965eb72c92a549dd5a330112', '2020-07-19 08:24:27', '2020-07-18 22:03:13');
INSERT INTO `user` VALUES (9, 'test123', '网行侠', 'http://yun.sevenit.cn/2020/06/29/b80ce767bfcaf.jpg', '13069382092', '2', '51028814@qq.com', 'cc03e747a6afbbcbf8be7668acfebee5', '2020-07-19 08:24:28', '2020-07-18 22:03:15');
INSERT INTO `user` VALUES (10, 'test123', '网行侠', 'http://yun.sevenit.cn/2020/06/29/b80ce767bfcaf.jpg', '13069382092', '2', '51028814@qq.com', 'cc03e747a6afbbcbf8be7668acfebee5', '2020-07-19 08:24:30', '2020-07-18 22:03:19');
INSERT INTO `user` VALUES (12, '1111111111', '网行侠', '1111111111', '1111111111', '1', '1111111111@qq.com', 'e11170b8cbd2d74102651cb967fa28e5', '2020-07-19 08:24:32', '2020-07-18 22:03:22');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
