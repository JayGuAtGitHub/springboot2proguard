# springboot2proguard

maybe the fastest out-of-the-box demo for project with tech stack of proguard + spring boot 2 + log4j2/log4j + mybatis plus with lambda and xml

可能是最全的开箱即用的proguard配置方式, 如果你的项目里面使用了
spring boot 2 - 2.3.6
log4j2
mybatis plus - 3.4.2, lambda + xml
可以大幅参考本项目的proguard配置和打包配置

同时, 项目还使用了如下依赖, 部分配置也受到下面的依赖影响变得更为宽松, 如果你没有使用下面的依赖, 可以尝试将proguard配置的更为严格
undertow
spring security
feign

主要配置方式
1. pom的build中
增加proguard-maven-plugin节点, 使用proguard7.x版本, 这个版本可以良好的兼容jdk9+, 采用4.x/5.x/6.x, 均会导致log组件无法加密
原有的spring-boot-maven-plugin节点, 增加repackage
两部分都可以直接参考pom.xml
2. proguard.cfg
配置文件需要声明在pom里面, 位于节点
<proguardInclude>${project.basedir}/proguard.cfg</proguardInclude>


其中比较重要的开启的配置如下(其他比如target, dontshrink等公开资料较多, 在此不再赘述):

##mybatis plus可以通过声明接口动态创建类, 忽略所有接口可以解决部分bean无法动态创建的问题, 但是在本项目中, 使用XML的方式, 只忽略interface不够, 采用下面的方式
##比较好的方式是参照  https://blog.csdn.net/weixin_39886238/article/details/114600811  进行目录的组织, 可见目录组织有多重要
##注意, 这一项仅在不适用xml模式下有效
# -keep interface springboot2proguard.** { *; }

##keep相关用法参照https://www.jianshu.com/p/b471db6a01af
##保留所有class名称(和member), 兼容mybatis plus, 如果没用mybatis+XML的话不推荐开启这个选项, 或者使用上面的方式, 这个方式会极大降低混淆力度, 不开这个很难读懂代码
-keepnames class com.example.springboot2proguard.** { *; }

-keepattributes InnerClasses ## 保持内部类, 防止某些反射获取类型名称出现问题, 如果项目没有滥用反射建议不要开启
-keepattributes Signature # keep generic 保持泛型, 用来兼容mybatis plus问题, 没有泛型的话mybatis plus无法动态创建了
-keepattributes SourceFile,LineNumberTable # 保留报错行号信息, 如果没有经常需要线上排错信息的话可以不开启

-keepparameternames ## 保留所有方法的参数名, 在用到@RequestAttribute等场景里面可以开启这个设置, 可以避免利用了字段名的特性, 但是不建议开启, 会降低代码混淆效果

如果此项目打包存在问题, 欢迎给我留言
