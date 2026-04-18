# 基于区块链技术的职称证书共享系统

## 项目简介

本项目是一个面向毕业设计场景实现的职称证书共享与验真系统，围绕“证书管理、证书共享、监管查询、区块链存证、二维码验真”等业务需求展开设计与实现。

系统采用前后端分离程度较低的单体架构：

- 后端基于 `Spring Boot + MyBatis-Plus`
- 前端基于 `Vue + HTML + CSS + JavaScript + Layui`
- 数据存储采用 `MySQL`
- 区块链存证采用 `Hyperledger Fabric`

项目的核心目标是将职称证书的业务管理与区块链存证能力结合起来，实现证书信息的可信上链、共享流转和查询验真。

## 课题信息

- 课题名称：基于区块链技术的职称证书共享系统的设计与实现
- 项目编号：`2587`

## 功能概述

系统围绕多角色业务场景展开，主要包含以下功能：

- 用户账号管理
- 个人用户信息管理
- 用人单位信息管理
- 发证机构信息管理
- 监管部门信息管理
- 职称证书管理
- 证书共享记录管理
- 证书监管记录管理
- 区块链初始化与证书上链
- 区块链证书查询
- 证书二维码生成与验真展示

## 角色设计

系统主要包含以下角色：

- 管理员
- 个人用户
- 用人单位
- 发证机构
- 监管部门

不同角色登录后可看到不同的后台菜单与管理内容。

## 系统业务逻辑

### 1. 基础业务流程

1. 管理员或相关业务角色维护基础信息，例如用户、单位、机构、监管部门等。
2. 发证机构录入或维护职称证书信息。
3. 系统将证书数据存储到 MySQL 数据库中。
4. 管理员可触发区块链初始化，将数据库中的证书同步到 Hyperledger Fabric。
5. 系统从链上读取证书数据，并为每条证书生成二维码。
6. 用户可在证书查询页面查看证书详情、二维码及链上相关信息。

### 2. 区块链逻辑说明

项目中的区块链模块并不是完全替代数据库，而是作为“可信存证层”使用：

- `MySQL` 负责日常业务数据管理
- `Hyperledger Fabric` 负责证书数据上链与链上查询
- 前端展示时可通过链上数据生成二维码，实现证书验真展示

也就是说，本项目属于“数据库业务管理 + 区块链可信存证”的组合模式。

## 技术栈

### 后端

- Java 17
- Spring Boot 3.4.3
- MyBatis-Plus 3.5.7
- MyBatis
- PageHelper
- Maven

### 前端

- Vue
- Axios
- Layui
- HTML / CSS / JavaScript

### 数据与中间件

- MySQL
- Hyperledger Fabric

### 其他组件

- ZXing（二维码生成）
- Fastjson
- Apache HttpClient
- Jieba Analysis

## 项目结构

```text
myproject
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  └─ com.code
│  │  │     ├─ base          # 通用接口、文件上传、区块链接口
│  │  │     ├─ controller    # 业务控制器
│  │  │     ├─ entity        # 实体类
│  │  │     ├─ mapper        # 数据访问层
│  │  │     └─ utils         # 区块链工具类等
│  │  └─ resources
│  │     ├─ static          # 前端静态页面与资源
│  │     ├─ templates
│  │     ├─ application.yml
│  │     └─ application.properties
│  └─ test
├─ 数据库
│  ├─ 2587.sql
│  └─ 2587-数据库说明.doc
├─ pom.xml

```

## 数据库设计

项目主要包含以下核心表：

- `userinfo`：系统登录账号
- `individual_user`：个人用户信息
- `employer`：用人单位信息
- `issuing_agency`：发证机构信息
- `regulatory_department`：监管部门信息
- `title_certificate`：职称证书信息
- `sharing_record`：证书共享记录
- `regulatory_record`：监管记录

这些表之间通过外键关联，构成了完整的证书生命周期业务模型。

## 运行环境

建议环境如下：

- JDK 17
- Maven 3.8+
- MySQL 5.7 / 8.0
- IntelliJ IDEA
- Windows 10/11
- Ubuntu 虚拟机（用于运行 Hyperledger Fabric）

## 本地运行步骤

### 1. 克隆项目

```bash
git clone <your-repository-url>
cd myproject
```

### 2. 创建数据库

1. 在 MySQL 中创建数据库，例如：

```sql
CREATE DATABASE `2587` DEFAULT CHARACTER SET utf8mb4;
```

2. 执行项目自带脚本：

```text
数据库/2587.sql
```

### 3. 修改数据库配置

编辑文件：

```text
src/main/resources/application.yml
```

根据本机环境修改以下内容：

- 数据库地址
- 数据库端口
- 数据库用户名
- 数据库密码

项目默认示例配置为：

```yaml
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3308/2587?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT
    username: root
    password: root
```

### 4. 启动后端项目

在项目根目录执行：

```bash
mvn spring-boot:run
```

或直接在 IDEA 中运行启动类：

```text
src/main/java/com/code/StartApplication.java
```

### 5. 访问系统

默认访问地址：

```text
http://localhost:8088/index.html
```

## 测试账号

项目中提供了若干测试账号，可用于演示不同角色：

- 管理员：`admin / 123456`
- 个人用户：`user01 / 123456`
- 用人单位：`org01 / 123456`
- 发证机构：`agency01 / 123456`
- 监管部门：`dept01 / 123456`

如果导入数据库后账号与本地数据不一致，请以数据库中的 `userinfo` 表实际数据为准。

## 区块链模块使用说明

### 1. 功能定位

区块链模块主要用于：

- 证书数据上链
- 链上证书查询
- 证书二维码生成
- 证书验真展示

### 2. 使用流程

1. 先保证 MySQL 中已有证书业务数据。
2. 启动 Hyperledger Fabric 网络。
3. 在系统后台由管理员执行“区块链初始化”。
4. 系统调用 Fabric Gateway 将证书数据提交到链上。
5. 在证书查询页面查看链上证书与二维码信息。

### 3. 注意事项

当前项目中的区块链连接参数写在代码中，使用前通常需要根据本地环境调整：

- Fabric 证书目录路径
- Peer 节点地址
- 链上数据前缀标识

相关代码位于：

```text
src/main/java/com/code/utils/MyBlockchainUtils.java
```

### 4. Fabric 启动参考

项目根目录中附带了区块链使用说明文件：

- `区块链教客户使用.txt`

其中包含 Fabric 网络启动和链码查询的参考命令。

## 项目特点

- 采用多角色后台管理模式
- 同时支持数据库业务管理与区块链可信存证
- 支持证书共享、监管与查询
- 支持二维码验真展示
- 适合作为区块链应用方向的毕业设计示例项目

## 项目不足与说明

作为毕业设计项目，本系统更偏向教学与演示用途，当前实现中仍有一些可以继续完善的地方，例如：

- 部分接口采用了通用 SQL 传参方式，安全性和可维护性仍可优化
- 前后端分层不够彻底，部分页面逻辑写在静态页面中
- 区块链连接配置存在硬编码，部署可移植性一般
- 页面样式与交互仍有优化空间

这些问题并不影响毕业设计展示其核心思路，但如果后续用于正式项目，建议进一步重构和完善。

## 适用场景

本项目适用于以下场景：

- 本科/专科毕业设计
- 区块链应用课程设计
- Java Web 综合实训项目
- 职称证书共享与验真方向的原型系统

## 后续可扩展方向

- 增加更严格的权限校验与登录认证机制
- 对证书共享流程增加审批功能
- 将二维码内容与链上哈希绑定得更严格
- 增加证书搜索、统计分析与可视化展示
- 将区块链连接参数改为配置化管理
- 将前端重构为标准前后端分离架构

## 声明

本项目用于毕业设计学习与演示，若用于课程作业、论文展示或二次开发，请结合实际环境自行完善配置、权限控制和部署方式。

## License

如需开源发布，建议你根据自己的需要补充许可证，例如：

- MIT License
- Apache License 2.0

如果你暂时还没确定，也可以先保留本节，上传 GitHub 前再补充。
