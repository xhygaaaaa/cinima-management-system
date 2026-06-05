🎬 Cinema Management System

📖 项目简介
Cinema Management System 是一个企业级的影院综合管理平台，涵盖影院管理、电影信息、排片调度、订单处理、用户反馈等完整业务流程。系统采用前后端分离架构，基于若依（RuoYi）快速开发框架进行二次开发，实现了高可用、高并发的业务场景。

✨ 核心特性
🎯 完整的业务闭环：从影院信息管理到订单交易，覆盖影院运营全流程
🔐 安全可靠：基于 Spring Security + JWT 的权限认证体系，支持细粒度权限控制
⚡ 高性能架构：Redis 缓存加速，数据库连接池优化，支持高并发访问
📊 数据可视化：ECharts 图表展示，实时监控运营数据
🛠️ 开箱即用：集成代码生成器、接口文档、Excel 导入导出等开发利器
📱 响应式设计：适配桌面端和移动端，提供良好的用户体验
🏗️ 技术架构
后端技术栈
技术 版本 说明
Spring Boot 2.5.15 核心框架
Spring Security 5.7.14 安全认证框架
MyBatis 3.5.x ORM 持久化框架
Druid 1.2.23 数据库连接池
Redis Latest 分布式缓存
JWT 0.9.1 Token 生成与验证
Swagger 3.0.0 API 文档生成
PageHelper 1.4.7 分页插件
Fastjson2 2.0.58 JSON 处理
POI 4.1.2 Excel 导入导出
前端技术栈
技术 版本 说明
Vue.js 2.6.12 渐进式 JavaScript 框架
Element UI 2.15.14 UI 组件库
Vuex 3.6.0 状态管理
Vue Router 3.4.9 路由管理
Axios 0.28.1 HTTP 客户端
ECharts 5.4.0 数据可视化
Quill 2.0.2 富文本编辑器
🎯 核心功能模块
1️⃣ 影院管理 (Cinemas)
影院信息的增删改查
影院地址、联系方式、营业时间管理
设施信息维护（IMAX、3D 眼镜、停车场等）
多影院连锁管理支持
2️⃣ 电影管理 (Movies)
电影基本信息录入（片名、导演、主演、时长等）
电影分类标签管理
上映状态控制（即将上映/正在热映/已下线）
电影海报与宣传素材管理
3️⃣ 影厅管理 (Halls)
影厅信息配置（厅号、类型、座位数）
座位布局可视化管理
影厅设备信息维护
影厅与影院关联管理
4️⃣ 排片管理 (Schedules)
智能排片调度系统
场次时间与影厅分配
票价动态配置
排片冲突检测与提醒
5️⃣ 订单管理 (Orders)
电影票在线预订
订单状态跟踪（待支付/已支付/已取消/已完成）
订单详情查询与统计
退票与改签处理
6️⃣ 用户反馈 (Feedbacks)
用户评价与评分系统
反馈内容审核管理
评分数据统计分析
用户满意度监控
7️⃣ 系统管理
👥 用户管理：用户信息、角色分配、权限控制
🔑 角色管理：角色权限配置、数据权限管理
📋 菜单管理：动态菜单路由、按钮权限控制
📊 操作日志：用户操作审计、系统访问记录
🖥️ 系统监控：服务器状态、缓存监控、在线用户
核心表结构
cinemas: 影院信息（影院名称、地址、联系方式、营业时间、设施信息）
movies: 电影信息（片名、类型、导演、主演、时长、简介、海报）
halls: 影厅信息（影厅名称、类型、座位数、所属影院）
schedules: 排片信息（电影、影厅、放映时间、票价）
orders: 订单信息（用户、电影、场次、座位、订单状态、支付信息）
feedbacks: 反馈信息（用户、影院、电影、评分、评价内容）
🚀 快速开始
环境要求
JDK 1.8+
MySQL 5.7+
Redis 3.0+
Node.js 8.9+
Maven 3.6+
后端部署
# 1. 克隆项目
git clone https://github.com/yourusername/cinema-management-system.git
cd cinema-management-system

# 2. 创建数据库并导入 SQL
mysql -u root -p
CREATE DATABASE cinema DEFAULT CHARACTER SET utf8mb4;
USE cinema;
SOURCE sql/cinema.sql;

# 3. 修改配置文件
# 编辑 ruoyi-admin/src/main/resources/application-druid.yml
# 配置数据库连接信息、Redis 连接信息

# 4. 编译打包
mvn clean package

# 5. 启动后端服务
cd ruoyi-admin/target
java -jar ruoyi-admin.jar

# 或使用启动脚本
# Windows: ry.bat
# Linux: ./ry.sh
前端部署
# 1. 进入前端目录
cd ruoyi-ui

# 2. 安装依赖
npm install
# 或使用 pnpm
pnpm install

# 3. 启动开发服务器
npm run dev

# 4. 生产环境打包
npm run build:prod
访问系统
前端访问地址：http://localhost:80
后端接口地址：http://localhost:8080
Swagger 文档：http://localhost:8080/swagger-ui/index.html
默认账号密码：admin / admin123
📁 项目结构
cinema-management-system ├── ruoyi-admin # 管理后台模块（启动入口） │ ├── src │ │ ├── main │ │ │ ├── java │ │ │ │ └── com.ruoyi │ │ │ │ ├── system# 业务模块 │ │ │ │ │ ├── controller # 控制器层 │ │ │ │ │ ├── domain # 实体类 │ │ │ │ │ ├── mapper # 数据访问层 │ │ │ │ │ └── service # 业务逻辑层 │ │ │ │ └── web # 系统通用模块 │ │ │ └── resources │ │ │ ├── mapper # MyBatis XML 映射文件 │ │ │ └── application.yml # 配置文件 ├── ruoyi-common # 通用工具模块 │ └── src/main/java/com/ruoyi/common │ ├── annotation # 自定义注解 │ ├── config # 全局配置 │ ├── constant # 常量定义 │ ├── core # 核心组件 │ ├── exception # 异常处理 │ └── utils # 工具类 ├── ruoyi-framework # 框架核心模块 │ └── src/main/java/com/ruoyi/framework │ ├── aspectj # AOP 切面 │ ├── config # 系统配置 │ ├── interceptor # 拦截器 │ └── security # 安全认证 ├── ruoyi-system# 系统功能模块 │ └── src/main/java/com/ruoyi/system │ ├── domain # 系统实体类 │ ├── mapper # 系统数据访问 │ └── service # 系统业务逻辑 ├── ruoyi-generator # 代码生成模块 ├── ruoyi-quartz # 定时任务模块 ├── ruoyi-ui # Vue 前端项目 │ ├── src │ │ ├── api # API 接口 │ │ ├── assets # 静态资源 │ │ ├── components # 组件库 │ │ ├── directive # 自定义指令 │ │ ├── layout # 布局组件 │ │ ├── router # 路由配置 │ │ ├── store # Vuex 状态管理 │ │ ├── utils # 工具类 │ │ └── views # 页面视图 │ └── public ├── sql # 数据库脚本 │ └── cinema.sql # 数据库初始化脚本 └── pom.xml # Maven 父工程配置

🔧 核心配置
应用配置 (application.yml)
# 服务器配置server: port: 8080servlet: context-path: /# Token 配置token: header: Authorizationsecret: abcdefghijklmnopqrstuvwxyzexpireTime: 30# MyBatis 配置mybatis: typeAliasesPackage: com.ruoyi.**.domainmapperLocations: classpath*:mapper/**/*Mapper.xml# 分页插件pagehelper: helperDialect: mysql

安全配置
 JWT Token 认证：基于 Token 的无状态认证机制
 密码加密：BCrypt 加密算法
 XSS 防护：过滤脚本注入攻击
 防盗链：Referer 白名单机制
 限流保护：注解式接口限流
 重复提交防护：防止表单重复提交
