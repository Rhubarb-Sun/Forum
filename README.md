# Forum
Rhubarb's Spring Boot 2 learning project.

## 10月6日 
- 创建仓库，初始化。
- BootStrap 前端样式  https://v3.bootcss.com

## 常用 git 命令
- git commit --amend --no-edit  效果是追加commit，而不作为一次单独的commit。

### 常用 shell 命令
ssh -T git@github.com  测试本机ssh是否可以连接到github。


## 异常、错误 产生以及解决

- 白页  javax.servlet.ServletException: Circular view path [hello]: 
would dispatch back to the current handler URL [/hello] again. 
Check your ViewResolver setup! (Hint: This may be the result 
of an unspecified view, due to default view name generation.)

缺少 thymeleaf 依赖