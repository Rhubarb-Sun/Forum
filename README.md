# Forum
Rhubarb's Spring Boot 2 learning project.

## 10月6日 
- 创建仓库，初始化。
- [BootStrap 前端样式  ](https://v3.bootcss.com)
- [github OAuth](https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app)

## 10月7日 
- [发送Post请求](https://square.github.io/okhttp/)
- h2 / mysql数据库
- [flyway管理数据库](https://flywaydb.org/documentation/getstarted/firststeps/maven)

## 10月8号、10月9号
分页

## 10月10号
问题详情页面。

## 10月11号
问题编辑

## 10月12号
- [Mybatis Generator](https://mybatis.org/generator/running/runningWithMaven.html)
```shell script
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```

### 常用 git 命令
```shell script
git commit --amend --no-edit  #效果是追加commit，而不作为一次单独的commit。
```

### 常用 shell 命令
```shell script
ssh -T git@github.com  #测试本机ssh是否可以连接到github。
```

### 常用快捷键
shift + F6 全局修改变量名。
Command + E	显示最近打开的文件记录列表
Control + Option + O 优化导包


## 异常、错误 产生以及解决

- 白页  javax.servlet.ServletException: Circular view path [hello]: 
would dispatch back to the current handler URL [/hello] again. 
Check your ViewResolver setup! (Hint: This may be the result 
of an unspecified view, due to default view name generation.)

Cause / Solution: 缺少 thymeleaf 依赖

- 无法打开首页
Cause / Solution: 拦截器拦截。

- Mybatis Generator 生成别的表
Cause / Solution: 
MySql不能正确支持SQL catalogs和schema。如果 在MySql中运行create schema命令，它实际上会创建一个数据库 - 并且JDBC驱动程序将其作为catalogs报告回来。但是MySql语法不支持标准的catalog…table SQL语法。
因此，最好不要在generator 配置中指定catalog或schema。只需指定表名并在JDBC URL中指定数据库即可。
如果您使用的是mysql-connector-java的8.x版，生成器可能会尝试为MySql information schemas中的表生成代码。要禁用此行为，请将属性“nullCatalogMeansCurrent = true”添加到JDBC URL。

[点此查看原文](https://blog.csdn.net/hhy107107/article/details/90702077)
