工程编码转化
===

> GB系列编码转UTF-8

### 环境
- JDK 1.9 +
- IDEA 安装插件 [Lombok](https://projectlombok.org/). [详情](https://wangwei.one/posts/917fb1e0.html)

### 下载工程
```$shell
git clone git@github.com:wangweiX/gbk2utf8.git
```

### 配置变量
- 找到 `Gbk2Utf8UtilTest`
- 设置变量 
  - `srcProjPath`: 源工程绝对路劲
  - `dstProjPath`：目标工程绝对路劲
  - `extensions`：需要进行编码转换的文件后缀  
  
### 运行
- 执行Junit

> 欢迎关注个人博客：https://wangwei.one/