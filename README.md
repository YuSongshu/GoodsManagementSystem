# GoodsManagementSystem 简易商品管理系统

## 一、项目简介

这是一个基于 Java Web（Servlet + JSP）+ MySQL 的简易商品管理系统，商品列表、添加商品（含图片上传）、编辑商品、删除商品等功能，用于练习 MVC 分层架构、文件上传与 JDBC 数据库操作。

## 二、在线预览 / 效果截图

| 登录页 | 商品列表 | 添加商品 |
|--------|----------|----------|
| 登录卡片，错误提示 | 表格展示，图片缩略图 | 表单上传商品信息 |
| `login.jsp` | `adminHome.jsp` | `add_goods.jsp` |

*（可将运行截图放到 `docs/img/` 目录，再替换本节为 `![登录](docs/img/login.png)` 形式）*

## 三、功能清单

- 登录失败提示（URL 参数 + JSTL 条件渲染）
- 商品列表展示（名称、价格、库存、封面图）
- 添加商品（文本信息 + 图片上传，文件名防重名）
- 编辑商品（表单回填，支持更换图片）
- 更换图片时自动删除服务器旧图片文件
- 删除商品（物理删除，删除前二次确认）
- 空数据状态展示
- 文件上传工具封装（普通字段 / 文件字段 / 未选文件统一处理）

## 四、技术栈

- 后端：Java、Servlet、JSP、JSTL、EL
- 数据库：MySQL、JDBC、DbUtils 工具封装
- 文件上传：Apache Commons FileUpload、Commons IO
- 服务器：Tomcat 9
- 开发工具：IntelliJ IDEA

## 五、项目亮点 ⭐

- 严格按照 **MVC 分层**组织代码（bean / controller / service / dao / tools），职责清晰，Controller 只做接值与调度
- 封装 `DbUtils`，查询结果统一为 `List<HashMap>`，降低 JDBC 样板代码
- 封装 `FileUpload.getFromValues()`，一次解析表单全部字段；未选文件用哨兵值 `"没有上传文件"` 与业务解耦
- 实现**图片替换联动删除**：更新商品换图时，自动删除服务器上的旧图片，避免冗余文件
- 文件名采用 `时间戳 + 随机数` 重命名，解决同名覆盖问题
- 视图层用 JSTL（`c:choose` / `c:forEach` / `c:if`）替代 JSP 脚本，页面更干净
- 区分 **转发 / 重定向** 场景：带 Java 对象用 `forward`，成功后跳转用 `sendRedirect` 防重复提交

## 六、目录结构说明

```text
GoodsManagementSystem/
├── src/
│   ├── db.properties              # 数据库配置
│   └── mvc/
│       ├── bean/                  # 实体类：Goods、User
│       │   ├── Goods.java         #   商品实体（id/name/price/cnt/pic）
│       │   └── User.java          #   用户实体（id/username/password/role/...）
│       ├── controller/            # 控制层：接收请求、调业务、选视图
│       │   ├── Login.java         #   登录
│       │   ├── QueryGoodsAll.java #   商品列表
│       │   ├── QueryGoodsById.java#   查单条（编辑回填）
│       │   ├── AddGoods.java      #   添加商品
│       │   ├── UpdateGoodsById.java#  修改商品
│       │   └── DeleteGoods.java   #   删除商品
│       ├── service/               # 业务层：业务规则、实体装配
│       │   ├── UserService.java   #   登录业务
│       │   └── GoodsService.java  #   商品增删改查业务
│       ├── dao/                   # 数据访问层：SQL
│       │   ├── UserDao.java
│       │   └── GoodsDao.java
│       └── tools/                 # 工具类
│           ├── DbUtils.java       #   JDBC 封装（查询/更新/预处理）
│           └── FileUpload.java    #   文件上传解析工具
└── web/                           # 视图与静态资源
    ├── index.jsp                  # 首页
    ├── login.jsp                  # 登录页
    ├── adminHome.jsp              # 后台商品列表
    ├── add_goods.jsp              # 添加商品
    ├── update_goods.jsp           # 编辑商品
    ├── css/style.css              # 全局样式
    ├── img/                       # 上传图片保存目录
    └── WEB-INF/
        ├── web.xml
        └── lib/                   # 依赖 jar（fileupload、io、mysql 驱动等）
```

## 七、运行步骤

1. 克隆项目

   ```bash
   git clone <仓库地址>
   ```

2. 导入 IntelliJ IDEA（Import Project，选择项目根目录）

3. 准备 MySQL 数据库

   ```sql
   CREATE DATABASE IF NOT EXISTS goods_management_system DEFAULT CHARACTER SET utf8;
   USE goods_management_system;
   
   -- 商品表
   DROP TABLE IF EXISTS goods;
   CREATE TABLE goods (
     id INT PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR(255),
     price DECIMAL(10,0),
     cnt VARCHAR(255),
     pic VARCHAR(255)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
   
   -- 用户表
   DROP TABLE IF EXISTS user;
   CREATE TABLE user (
     id INT PRIMARY KEY AUTO_INCREMENT,
     username VARCHAR(255),
     password VARCHAR(255),
     money DECIMAL(10,0),
     create_time DATETIME,
     role INT DEFAULT 0
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
   
   INSERT INTO user(username,password,money,create_time,role) 
   VALUES ('admin','123456',10000,'2026-10-21 19:48:51',1);
   
   ```

4. 修改数据库配置：`src/db.properties`

   ```properties
   db.driver=com.mysql.jdbc.Driver
   db.url=jdbc:mysql://localhost:3306/goods_management_system?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false
   db.username=你的用户名
   db.password=你的密码
   ```

   > 端口按本机 MySQL 实际端口修改；库名 `two` 与 SQL 保持一致。

5. 配置 Tomcat 9，部署本项目，确认 `web/WEB-INF/lib` 下 jar 已加载

6. 启动 Tomcat，浏览器访问：

   ```
   http://localhost:8080/项目路径/
   ```

7. 用管理员账号登录后进入商品列表，即可增、改、删商品

## 八、接口文档

| 接口 | 方法 | 说明 |
| ---- | ---- | ---- |
| `/Login` | POST | 登录，成功按角色跳转 |
| `/QueryGoodsAll` | GET | 查全部商品，转发列表页 |
| `/QueryGoodsById` | GET | 按 id 查单条，转发编辑页 |
| `/AddGoods` | POST | 添加商品（multipart 表单） |
| `/UpdateGoodsById` | POST | 修改商品（multipart 表单） |
| `/DeleteGoods` | GET | 按 id 删除商品 |

**/Login 请求参数**

| 参数 | 类型 | 必填 | 说明 |
| ---- | ---- | ---- | ---- |
| username | String | 是 | 用户名 |
| password | String | 是 | 密码 |

**/AddGoods、/UpdateGoodsById 参数**（`enctype="multipart/form-data"`）

| 参数 | 类型 | 必填 | 说明 |
| ---- | ---- | ---- | ---- |
| name | String | 是 | 商品名称 |
| price | String | 是 | 价格 |
| cnt | String | 是 | 库存 |
| pic | File | 否 | 商品图片；修改时另含 `id`、`oldPic` |



## 九、常见问题 FAQ

**Q：数据库连接失败？**  
A：检查 `src/db.properties` 的用户名、密码、端口和库名是否正确，MySQL 服务是否已启动。

**Q：上传图片报 `IO读取报错了`？**  
A：确认部署目录下存在 `img` 文件夹；`FileOutputStream` 不会自动创建父目录，可在保存前调用 `mkdirs()`。

**Q：上传后源码 `web/img` 里没有文件？**  
A：文件写在 Tomcat 部署目录（如 `out/artifacts/.../img`），不在源码目录；换机或重新构建可能丢失，正式项目应使用固定上传路径。

**Q：JSP 里 `<c:if>` 标签报错？**  
A：需要 JSTL 依赖（`jstl.jar` + `standard.jar`），并确认页面顶部有：`<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>`。

**Q：登录失败提示不显示？**  
A：登录失败会重定向到 `login.jsp?error=500`，页面用 `param.error` 判断；请用 JSTL 的 `c:if` 渲染，不要写脚本表达式。

**Q：中文乱码？**  
A：表单用 `method="post"`，文件上传工具里已用 `utf-8` 读取文本字段；Tomcat 连接器也可配置 `URIEncoding="UTF-8"`。

## 十、未来计划

- [ ] 注册功能与密码加密存储
- [ ] 用户鉴权
- [ ] 商品分页、关键字搜索
- [ ] 下架状态（逻辑删除）与恢复上架
- [ ] 普通用户完善
- [ ] 操作日志与更完善的登录状态管理（Session）

## 注

前端由AI美化而来



本项目为学习练习项目，仅用于技术交流与面试展示。
