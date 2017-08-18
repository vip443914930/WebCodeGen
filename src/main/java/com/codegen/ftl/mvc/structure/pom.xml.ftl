<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>${projectConfig.basePackagePath}</groupId>
    <artifactId>${projectConfig.projectName}</artifactId>
    <packaging>war</packaging>
    <version>1.0.0</version>
    <name>${projectConfig.projectName} App</name>
    <build>
        <finalName>${projectConfig.projectName}</finalName>
        <plugins>
            <!-- Maven插件 配置 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>${r'$'}{plugin.maven-compiler}</version>
                <configuration>
                    <source>${r'$'}{project.build.jdk}</source>
                    <target>${r'$'}{project.build.jdk}</target>
                    <encoding>${r'$'}{project.build.sourceEncoding}</encoding>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${r'$'}{plugin.maven-surefire}</version>
                <configuration>
                    <skipTests>${r'$'}{skipTests}</skipTests>
                </configuration>
            </plugin>
        </plugins>

        <!--配置Maven 对resource文件 过滤 -->
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>

    <properties>
        <!-- base setting -->
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.build.locales>zh_CN</project.build.locales>
        <project.build.jdk>1.7</project.build.jdk>

        <!-- plugin setting -->

        <!-- plugin versions -->
        <plugin.maven-compiler>3.1</plugin.maven-compiler>
        <plugin.maven-surefire>2.18.1</plugin.maven-surefire>
        <skipTests>true</skipTests>

        <!-- lib versions -->
        <junit.version>4.11</junit.version>
        <spring.version>4.0.2.RELEASE</spring.version>
        <mybatis.version>3.2.2</mybatis.version>
        <mybatis.spring.version>1.2.2</mybatis.spring.version>
        <mysql.connector.version>5.1.30</mysql.connector.version>
        <postgresql.version>9.1-901.jdbc4</postgresql.version>
        <slf4j.version>1.6.6</slf4j.version>
        <log4j.version>1.2.12</log4j.version>
        <httpclient.version>4.1.2</httpclient.version>
        <jackson.version>1.9.13</jackson.version>
        <c3p0.version>0.9.1.2</c3p0.version>
        <druid.version>1.0.5</druid.version>
        <tomcat.jdbc.version>7.0.53</tomcat.jdbc.version>
        <jstl.version>1.2</jstl.version>
        <google.collections.version>1.0</google.collections.version>
        <cglib.version>3.1</cglib.version>
        <shiro.version>1.2.3</shiro.version>
        <commons.fileupload.version>1.3.1</commons.fileupload.version>
        <commons.codec.version>1.9</commons.codec.version>
        <commons.net.version>3.3</commons.net.version>
        <aspectj.version>1.6.12</aspectj.version>
        <netty.version>4.0.18.Final</netty.version>
        <hibernate.validator.version>5.1.1.Final</hibernate.validator.version>
    </properties>

    <dependencies>
        <!-- junit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${r'$'}{junit.version}</version>
        </dependency>

        <!-- springframe start -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${r'$'}{spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${r'$'}{spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-oxm</artifactId>
            <version>${r'$'}{spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${r'$'}{spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${r'$'}{spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${r'$'}{spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${r'$'}{spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>${r'$'}{spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${r'$'}{spring.version}</version>
        </dependency>
        <!-- springframe end -->

        <!-- mybatis start-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>${r'$'}{mybatis.version}</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>${r'$'}{mybatis.spring.version}</version>
        </dependency>

        <!-- mybatis pagehelper -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>4.1.6</version>
        </dependency>

        <!--mybatis end-->

        <!-- mysql-connector -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${r'$'}{mysql.connector.version}</version>
        </dependency>

        <!-- DruidDataSource -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>${r'$'}{druid.version}</version>
        </dependency>

        <!-- jackson -->
        <dependency>
            <groupId>org.codehaus.jackson</groupId>
            <artifactId>jackson-mapper-asl</artifactId>
            <version>${r'$'}{jackson.version}</version>
        </dependency>

        <!-- log start -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>${r'$'}{log4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${r'$'}{slf4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>${r'$'}{slf4j.version}</version>
        </dependency>
        <!-- log end -->

        <!-- servlet api -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.0.1</version>
            <scope>provided</scope>
        </dependency>

        <!-- jstl -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>${r'$'}{jstl.version}</version>
        </dependency>

        <!-- start apache -->
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>${r'$'}{commons.fileupload.version}</version>
        </dependency>

        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>${r'$'}{httpclient.version}</version>
        </dependency>

        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
            <version>${r'$'}{commons.codec.version}</version>
        </dependency>

        <dependency>
            <groupId>commons-net</groupId>
            <artifactId>commons-net</artifactId>
            <version>${r'$'}{commons.net.version}</version>
        </dependency>

        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.1.3</version>
        </dependency>
        <dependency>
            <groupId>commons-collections</groupId>
            <artifactId>commons-collections</artifactId>
            <version>3.2.1</version>
        </dependency>

        <!-- end apache -->

        <!-- google -->
        <dependency>
            <groupId>com.google.collections</groupId>
            <artifactId>google-collections</artifactId>
            <version>${r'$'}{google.collections.version}</version>
        </dependency>

        <!-- cglib -->
        <dependency>
            <groupId>cglib</groupId>
            <artifactId>cglib-nodep</artifactId>
            <version>${r'$'}{cglib.version}</version>
        </dependency>


        <!-- shiro -->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>${r'$'}{shiro.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-ehcache</artifactId>
            <version>${r'$'}{shiro.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-core</artifactId>
            <version>${r'$'}{shiro.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-web</artifactId>
            <version>${r'$'}{shiro.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-quartz</artifactId>
            <version>${r'$'}{shiro.version}</version>
        </dependency>

        <!-- aspectjweaver -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>${r'$'}{aspectj.version}</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjrt</artifactId>
            <version>${r'$'}{aspectj.version}</version>
        </dependency>

        <!-- hibernate-validator -->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>${r'$'}{hibernate.validator.version}</version>
        </dependency>

        <!-- netty -->
        <dependency>
            <groupId>io.netty</groupId>
            <artifactId>netty-all</artifactId>
            <version>${r'$'}{netty.version}</version>
        </dependency>

        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>2.6.2</version>
            <type>jar</type>
            <scope>compile</scope>
        </dependency>

        <!-- monogodb -->
        <dependency>
            <groupId>org.mongodb</groupId>
            <artifactId>mongo-java-driver</artifactId>
            <version>3.2.2</version>
        </dependency>

    </dependencies>
</project>
