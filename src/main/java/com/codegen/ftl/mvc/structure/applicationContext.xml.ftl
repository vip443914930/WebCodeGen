<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx" xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util" xmlns:jdbc="http://www.springframework.org/schema/jdbc"
       xmlns:cache="http://www.springframework.org/schema/cache"
       xsi:schemaLocation="
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd
    http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/tx
    http://www.springframework.org/schema/tx/spring-tx.xsd
    http://www.springframework.org/schema/jdbc
    http://www.springframework.org/schema/jdbc/spring-jdbc.xsd
    http://www.springframework.org/schema/cache
    http://www.springframework.org/schema/cache/spring-cache.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd
    http://www.springframework.org/schema/util
    http://www.springframework.org/schema/util/spring-util.xsd">

    <!-- 自动扫描quick4j包 ,将带有注解的类 纳入spring容器管理 -->
    <context:component-scan base-package="${extensions['MVC_PACKAGE']}"></context:component-scan>

    <!-- 引入配置文件 -->
    <bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="locations">
            <list>
                <value>classpath*:application.properties</value>
            </list>
        </property>
    </bean>

    <!-- dataSource 配置 -->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
        <!-- 基本属性 url、user、password -->
        <property name="url" value="${r'$'}{jdbc.url}"/>
        <property name="username" value="${r'$'}{jdbc.username}"/>
        <property name="password" value="${r'$'}{jdbc.password}"/>

        <!-- 配置初始化大小、最小、最大 -->
        <property name="initialSize" value="${r'$'}{ds.initialSize}"/>
        <property name="minIdle" value="${r'$'}{ds.minIdle}"/>
        <property name="maxActive" value="${r'$'}{ds.maxActive}"/>

        <!-- 配置获取连接等待超时的时间 -->
        <property name="maxWait" value="${r'$'}{ds.maxWait}"/>

        <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
        <property name="timeBetweenEvictionRunsMillis" value="${r'$'}{ds.timeBetweenEvictionRunsMillis}"/>

        <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
        <property name="minEvictableIdleTimeMillis" value="${r'$'}{ds.minEvictableIdleTimeMillis}"/>

        <property name="validationQuery" value="SELECT 'x'"/>
        <property name="testWhileIdle" value="true"/>
        <property name="testOnBorrow" value="false"/>
        <property name="testOnReturn" value="false"/>

        <!-- 打开PSCache，并且指定每个连接上PSCache的大小 -->
        <property name="poolPreparedStatements" value="false"/>
        <property name="maxPoolPreparedStatementPerConnectionSize" value="20"/>

        <!-- 配置监控统计拦截的filters -->
        <property name="filters" value="stat"/>
    </bean>

    <!-- mybatis文件配置，扫描所有mapper文件 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean" p:dataSource-ref="dataSource"
          p:configLocation="classpath:mybatis-config.xml"
          p:mapperLocations="classpath:${extensions['StringUtils'].replace(extensions['DAO_PACKAGE'],'.','/')}/*.xml"/>

    <!-- spring与mybatis整合配置，扫描所有dao -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer" p:basePackage="${extensions['DAO_PACKAGE']}"
          p:sqlSessionFactoryBeanName="sqlSessionFactory"/>

    <!-- 对dataSource 数据源进行事务管理 -->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager"
          p:dataSource-ref="dataSource"/>

    <!-- 事务管理 通知 -->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <tx:attributes>
            <!-- 对insert,update,delete 开头的方法进行事务管理,只要有异常就回滚 -->
            <tx:method name="insert*" propagation="REQUIRED" rollback-for="java.lang.Throwable"/>
            <tx:method name="update*" propagation="REQUIRED" rollback-for="java.lang.Throwable"/>
            <tx:method name="delete*" propagation="REQUIRED" rollback-for="java.lang.Throwable"/>
            <!-- select,count开头的方法,开启只读,提高数据库访问性能 -->
            <tx:method name="select*" read-only="true"/>
            <tx:method name="count*" read-only="true"/>
            <!-- 对其他方法 使用默认的事务管理 -->
            <tx:method name="*"/>
        </tx:attributes>
    </tx:advice>

    <!-- 事务 aop 配置 -->
    <aop:config>
        <aop:pointcut id="serviceMethods" expression="execution(* ${extensions['SERVICE_PACKAGE']}..*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="serviceMethods"/>
    </aop:config>

    <!-- 配置使Spring采用CGLIB代理 -->
    <aop:aspectj-autoproxy proxy-target-class="true"/>

    <!-- 启用对事务注解的支持 -->
    <tx:annotation-driven transaction-manager="transactionManager"/>

    <!-- Cache配置 -->
    <cache:annotation-driven cache-manager="cacheManager"/>
    <bean id="ehCacheManagerFactory" class="org.springframework.cache.ehcache.EhCacheManagerFactoryBean"
          p:configLocation="classpath:ehcache.xml"/>
    <bean id="cacheManager" class="org.springframework.cache.ehcache.EhCacheCacheManager"
          p:cacheManager-ref="ehCacheManagerFactory"/>
</beans>
