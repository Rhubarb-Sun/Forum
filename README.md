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

### 常用 git 命令
- git commit --amend --no-edit  效果是追加commit，而不作为一次单独的commit。

### 常用 shell 命令
- ssh -T git@github.com  测试本机ssh是否可以连接到github。

### 常用快捷键
shift + F6 全局修改变量名。
Command + E	显示最近打开的文件记录列表
Control + Option + O 优化导包


## 异常、错误 产生以及解决

- 白页  javax.servlet.ServletException: Circular view path [hello]: 
would dispatch back to the current handler URL [/hello] again. 
Check your ViewResolver setup! (Hint: This may be the result 
of an unspecified view, due to default view name generation.)

缺少 thymeleaf 依赖
