# 创建账户
# CREATE USER 'yangjiang'@'%'
# 	IDENTIFIED BY '123';

# 授予权限
# GRANT SHOW DATABASES, EVENT, INSERT, SELECT, SHOW VIEW, UPDATE ON research.* TO 'yangjiang'@'%';
# GRANT SHOW DATABASES ON *.* TO 'yangjiang'@'%';
# 刷新权限
# FLUSH PRIVILEGES;

# 创建数据库
CREATE DATABASE research
	DEFAULT CHARACTER SET utf8
	COLLATE utf8_general_ci;
# 使用数据库
USE research;

# 用户表
CREATE TABLE research.tb_users
(
	id                 INT AUTO_INCREMENT PRIMARY KEY         NOT NULL
	COMMENT '主键，自增长',
	userid             VARCHAR(30)                            NOT NULL
	COMMENT '用户 ID, 教工号',
	name               VARCHAR(50)                            NOT NULL
	COMMENT '用户名',
	password           VARCHAR(50)                            NOT NULL
	COMMENT '密码',
	gender             ENUM ('male', 'female')                NOT NULL
	COMMENT '性别，男male|女female',
	ethnic             VARCHAR(20)                            NOT NULL
	COMMENT '民族',
	birthday           DATE                                   NOT NULL
	COMMENT '出生日期',
	address            VARCHAR(100)                           NOT NULL
	COMMENT '地址',
	unit               VARCHAR(100)                           NOT NULL
	COMMENT '工作单位',
	email              VARCHAR(50) COMMENT '电子邮件',
	position           VARCHAR(50)                            NOT NULL
	COMMENT '职务',
	professional_title VARCHAR(50) COMMENT '职称',
	phone              VARCHAR(15)                            NOT NULL
	COMMENT '手机号',
	telephone          VARCHAR(15) COMMENT '固定电话',
	education          VARCHAR(20)                            NOT NULL
	COMMENT '学历',
	profession         VARCHAR(20)                            NOT NULL
	COMMENT '专业',
	lastlogin          DATETIME                               NOT NULL
	COMMENT '最后登录时间',
	UNIQUE (userid)
)
	COMMENT '用户表';
INSERT INTO tb_users (userid, name, password, gender, ethnic, birthday, address, unit, email, position,
											professional_title, phone, telephone, education, profession, lastlogin)
	VALUE ('201666666666', '测试账户', '123456', 'male', '汉族', '2000-11-11', '广东阳江高梁路6666号', '阳江职业技术学院', '12345678@qq.com',
												 '教导主任', '一级教师', '12345678910', '2345675', '博士后', '软件技术', '2018-12-12 12:51:00');

# 课题学科分类
CREATE TABLE research.tb_subjects_classification
(
	id             INT AUTO_INCREMENT PRIMARY KEY NOT NULL
	COMMENT '主键，自增长',
	classification VARCHAR(20)                    NOT NULL
	COMMENT '学科分类'
)
	COMMENT '课题学科分类';

# 课题课题类别
CREATE TABLE research.tb_subjects_category
(
	id       INT AUTO_INCREMENT PRIMARY KEY NOT NULL
	COMMENT '主键，自增长',
	category VARCHAR(20)                    NOT NULL
	COMMENT '课题类别'
)
	COMMENT '课题学科分类';

# 课题表
CREATE TABLE research.tb_subjects
(
	id                INT AUTO_INCREMENT PRIMARY KEY NOT NULL
	COMMENT '主键，自增长',
	name              VARCHAR(100)                   NOT NULL
	COMMENT '课题名称',
	classification    INT                            NOT NULL
	COMMENT '学科分类，关联课题学科分类',
	category          INT                            NOT NULL
	COMMENT '课题类别，关联课题类别',
	status            INT                            NOT NULL
	COMMENT '状态',
	host              INT                            NOT NULL
	COMMENT '负责人， 关联用户表',
	year              DATE                           NOT NULL
	COMMENT '年度',
	finalresult       VARCHAR(10)                    NOT NULL
	COMMENT '最终成果',
	grants            INT                            NOT NULL
	COMMENT '资助经费',
	completetime      DATE                           NOT NULL
	COMMENT '完成时间',
	subjectFinancialcategory INT                            NOT NULL
	COMMENT '经费类别',
	bankcard          VARCHAR(20)
	COMMENT '银行卡号',
	thesis_proposal   VARCHAR(300) COMMENT '开题报告，文件路径',
	medium_report     VARCHAR(300) COMMENT '中期报告，文件路径',
	final_report      VARCHAR(300) COMMENT '结题报告，文件路径',
	FOREIGN KEY (host) REFERENCES tb_users (id),
	FOREIGN KEY (classification) REFERENCES tb_subjects_classification (id),
	FOREIGN KEY (category) REFERENCES tb_subjects_category (id)
)
	COMMENT '课题表';

# 课题参与者表
CREATE TABLE research.tb_subjects_personnel
(
	id        INT AUTO_INCREMENT PRIMARY KEY NOT NULL
	COMMENT '主键，自增长',
	subject   INT                            NOT NULL
	COMMENT '课题，关联课题表',
	name      VARCHAR(20)                    NOT NULL
	COMMENT '参与者姓名',
	age       INT                            NOT NULL
	COMMENT '年龄',
	unit      VARCHAR(100)                   NOT NULL
	COMMENT '工作单位',
	task      VARCHAR(30)                    NOT NULL
	COMMENT '任务分工',
	position  VARCHAR(50)                    NOT NULL
	COMMENT '职务',
	education VARCHAR(20)                    NOT NULL
	COMMENT '学历',
	FOREIGN KEY (subject) REFERENCES tb_subjects (id)
)
	COMMENT '课题人员表';

# 课题推荐人
CREATE TABLE research.tb_subjects_recommender
(
	id         INT AUTO_INCREMENT PRIMARY KEY NOT NULL
	COMMENT '主键，自增长',
	subject    INT                            NOT NULL
	COMMENT '课题，关联课题表',
	name       VARCHAR(20)                    NOT NULL
	COMMENT '推荐人一姓名',
	position   VARCHAR(50)                    NOT NULL
	COMMENT '职务',
	profession VARCHAR(20)                    NOT NULL
	COMMENT '专业',
	unit       VARCHAR(100)                   NOT NULL
	COMMENT '工作单位',
	subname    TEXT COMMENT '主持过的课题名称',
	recommend  TEXT                           NOT NULL
	COMMENT '推荐意见'
)
	COMMENT '课题推荐人表';

# 课题论证表
CREATE TABLE research.tb_subjects_proof
(
	id             INT AUTO_INCREMENT PRIMARY KEY NOT NULL
	COMMENT '主键，自增长',
	subject        INT                            NOT NULL
	COMMENT '课题，关联课题表',
	classification VARCHAR(20)                    NOT NULL
	COMMENT '学科类别',
	name           VARCHAR(20)                    NOT NULL
	COMMENT '课题名称',
	value          TEXT                           NOT NULL
	COMMENT '研究价值',
	target         TEXT                           NOT NULL
	COMMENT '研究目标',
	method         TEXT                           NOT NULL
	COMMENT '研究方法',
	annex          VARCHAR(200)
	COMMENT '附件路径'
)
	COMMENT '课题论证表';

# 课题可行性
CREATE TABLE research.tb_subjects_feasibility
(
	id          INT AUTO_INCREMENT PRIMARY KEY NOT NULL
	COMMENT '主键，自增长',
	subject     INT                            NOT NULL
	COMMENT '课题，关联课题表',
	achievement TEXT                           NOT NULL
	COMMENT '成果',
	task        TEXT                           NOT NULL
	COMMENT '分工',
	requirement TEXT                           NOT NULL
	COMMENT '保障条件'
)
	COMMENT '课题可行性';

# 课题进度
CREATE TABLE research.tb_subjects_schedule
(
	id        INT AUTO_INCREMENT PRIMARY KEY NOT NULL
	COMMENT '主键，自增长',
	subject   INT                            NOT NULL
	COMMENT '课题，关联课题表',
	starttime DATE                           NOT NULL
	COMMENT '开始时间',
	endtime   DATE                           NOT NULL
	COMMENT '结束时间',
	content   TEXT                           NOT NULL
	COMMENT '工作内容',
	name      VARCHAR(30)                    NOT NULL
	COMMENT '名称',
	host      VARCHAR(20)                    NOT NULL
	COMMENT '负责人'
)
	COMMENT '课题进度';

# 课题经费
CREATE TABLE research.tb_subjects_funds
(
	id             INT AUTO_INCREMENT PRIMARY KEY NOT NULL
	COMMENT '主键，自增长',
	subject        INT                            NOT NULL
	COMMENT '课题，关联课题表',
	data           DECIMAL                        NOT NULL
	COMMENT '资料费',
	travel         DECIMAL                        NOT NULL
	COMMENT '差旅费',
	meeting        DECIMAL                        NOT NULL
	COMMENT '会议费',
	equipment      DECIMAL                        NOT NULL
	COMMENT '设备费',
	service        DECIMAL                        NOT NULL
	COMMENT '劳务费',
	print          DECIMAL                        NOT NULL
	COMMENT '印刷费',
	Identification DECIMAL                        NOT NULL
	COMMENT '鉴定费',
	other          DECIMAL                        NOT NULL
	COMMENT '其他',
	Funding        DECIMAL                        NOT NULL
	COMMENT '资助经费',
	selfraised     DECIMAL                        NOT NULL
	COMMENT '自筹经费',
	total          DECIMAL                        NOT NULL
	COMMENT '经费合计'
)
	COMMENT '经费预算';

# 经费年度预算
CREATE TABLE research.tb_subjects_budget
(
	id      INT AUTO_INCREMENT PRIMARY KEY NOT NULL
	COMMENT '主键，自增长',
	subject INT                            NOT NULL
	COMMENT '课题，关联课题表',
	year    VARCHAR(10)                    NOT NULL
	COMMENT '年度',
	money   DECIMAL                        NOT NULL
	COMMENT '经费预算'
)
	COMMENT '经费年度预算';

# 备案表
CREATE TABLE research.tb_records
(
	id              INT AUTO_INCREMENT PRIMARY KEY NOT NULL
	COMMENT '主键，自增长',
	name            VARCHAR(100)                   NOT NULL
	COMMENT '备案名称',
	host            VARCHAR(20)                    NOT NULL
	COMMENT '主持人',
	unit            VARCHAR(100)                   NOT NULL
	COMMENT '工作单位',
	year            DATE                           NOT NULL
	COMMENT '年度',
	pronum          VARCHAR(30)                    NOT NULL
	COMMENT '立项编号',
	filenum         VARCHAR(30)                    NOT NULL
	COMMENT '立项文号',
	subject         VARCHAR(30)                    NOT NULL
	COMMENT '所属学科',
	classification  VARCHAR(20)                    NOT NULL
	COMMENT '学科类别',
	thesis_proposal VARCHAR(300) COMMENT '开题报告，文件路径',
	medium_report   VARCHAR(300) COMMENT '中期报告，文件路径',
	final_report    VARCHAR(300) COMMENT '结题报告，文件路径'
)
	COMMENT '备案表';

# 通知表
CREATE TABLE research.tb_notices
(
	id      INT AUTO_INCREMENT PRIMARY KEY NOT NULL
	COMMENT '主键，自增长',
	theme   VARCHAR(100)                   NOT NULL
	COMMENT '主题',
	host    INT                            NOT NULL
	COMMENT '发布人，关联用户表',
	date    DATE                           NOT NULL
	COMMENT '日期',
	click   INT                            NOT NULL
	COMMENT '点击率',
	top     BOOLEAN                        NOT NULL
	COMMENT '置顶',
	content TEXT                           NOT NULL
	COMMENT '内容',
	annex   VARCHAR(200)
	COMMENT '附件路径',
	FOREIGN KEY (host) REFERENCES tb_users (id)
)
	COMMENT '通知表';
