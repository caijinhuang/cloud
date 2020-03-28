# 自定义脚手架
## 脚本说明
- ### 编译构建脚手架
```
mvn -s "D:\devtool\maven\apache-maven\conf\settings.xml" archetype:create-from-project

解释：
-s "url" : 是maven配置文件的存放地址。
```
- ### 进入脚手架编译目录，然后将脚手架打包安装到本地的maven库中
```
cd target\generated-sources\archetype
mvn clean install
```

## 快速使用
- 执行构建脚本
```
  sh create_archetype.sh
```
- idea配置
> - 右键项目 >> NEW >> Module >> Maven >> 勾选 Create from archetype >> Add archetype  
> - 找到target\generated-sources\archetype\pom.xml
> - 找到pom文件中的一下节点，将节点内容填入框中，例如下：
> ```xml
>  <groupId>com.cjh.microserver</groupId>
>  <artifactId>redis-archetype</artifactId>
>  <version>1.0-SNAPSHOT</version>
>  <packaging>maven-archetype</packaging>
> ```
> - OK 之后就可以从create from archetype中创建自定义的好的工程脚手架了。

