package com.codegen;

import com.codegen.api.CopyFileGenerator;
import com.codegen.api.MVCFileGenerator;
import com.codegen.config.ProjectConfig;
import com.codegen.config.TableConfig;
import com.codegen.config.XmlConfigParser;
import com.codegen.enums.GeneratorTypeEnum;
import com.codegen.meta.GeneratorFile;
import com.codegen.meta.TableColumn;
import com.codegen.util.DataBaseUtil;
import com.codegen.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by chenxiaojun on 2017/8/5.
 */
public class CodeGenProcessor {

    public static void invoke(XmlConfigParser parser){
        List<TableConfig> tableConfigs = parser.getTableConfigs();
        final Map<String, List<TableColumn>> columnMap = DataBaseUtil.getTableColumns(parser.getDbConfig(),tableConfigs);
        ProjectConfig projectConfig = parser.getProjectConfig();
        // 公共java文件
        MVCFileGenerator genericGenerator = new MVCFileGenerator(projectConfig);
        genericGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "generic/GenericDao.java.ftl", getJavaPath(projectConfig, "core/generic/GenericDao.java")));
        genericGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "generic/GenericService.java.ftl", getJavaPath(projectConfig, "core/generic/GenericService.java")));
        genericGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "generic/GenericServiceImpl.java.ftl", getJavaPath(projectConfig, "core/generic/GenericServiceImpl.java")));
        genericGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "CommonAction.java.ftl", getJavaPath(projectConfig, "action/CommonAction.java")));

        // 公共jsp页面相关
        genericGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "view/common.jsp.ftl", getWebappPath(projectConfig, "view/common/common.jsp")));
        genericGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "view/head.jsp.ftl", getWebappPath(projectConfig, "view/common/head.jsp")));
        genericGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "view/bottom.jsp.ftl", getWebappPath(projectConfig, "view/common/bottom.jsp")));
        genericGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "view/index.jsp.ftl", getWebappPath(projectConfig, "view/index.jsp")));

        // 项目相关文件生成
        MVCFileGenerator structureGenerator = new MVCFileGenerator(projectConfig, parser.getDbConfig());
        structureGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "structure/application.properties.ftl", getSpringPath(projectConfig) + "application.properties"));
        structureGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "structure/applicationContext.xml.ftl", getSpringPath(projectConfig) + "applicationContext.xml"));
        structureGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "structure/applicationContext-shiro.xml.ftl", getSpringPath(projectConfig) + "applicationContext-shiro.xml"));
        structureGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "structure/ehcache.xml.ftl", getSpringPath(projectConfig) + "ehcache.xml"));
        structureGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "structure/ehcache-shiro.xml.ftl", getSpringPath(projectConfig) + "ehcache-shiro.xml"));
        structureGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "structure/log4j.properties.ftl", getSpringPath(projectConfig) + "log4j.properties"));
        structureGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "structure/messages.properties.ftl", getSpringPath(projectConfig) + "messages.properties"));
        structureGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "structure/mybatis-config.xml.ftl", getSpringPath(projectConfig) + "mybatis-config.xml"));
        structureGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "structure/spring-mvc.xml.ftl", getSpringPath(projectConfig) + "spring-mvc.xml"));
        structureGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "structure/web.xml.ftl", getWebappPath(projectConfig, "WEB-INF/web.xml")));
        structureGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "structure/pom.xml.ftl", getPomPath(projectConfig) + "pom.xml"));

        // test目录
        CopyFileGenerator copyFileGenerator = new CopyFileGenerator(projectConfig);
        copyFileGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.GENERATOR_FOLDER, getTestPath(projectConfig, "java")));
        copyFileGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.GENERATOR_FOLDER, getTestPath(projectConfig, "resources")));

        // bootstrap 框架
        copyFileGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.COPY_FOLDER, projectConfig.getResourcePath(), getWebappPath(projectConfig, "htdocs/bootstrap")));

        // 自定义静态文件JS/CSS目录
        copyFileGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.GENERATOR_FOLDER, getWebappPath(projectConfig, "htdocs/app/js")));
        copyFileGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.GENERATOR_FOLDER, getWebappPath(projectConfig, "htdocs/app/css")));
        copyFileGenerator.invoke(new GeneratorFile(GeneratorTypeEnum.GENERATOR_FOLDER, getWebappPath(projectConfig, "htdocs/app/img")));

        // 业务代码
        for (TableConfig tableConfig : tableConfigs) {
            MVCFileGenerator generator = new MVCFileGenerator(projectConfig, tableConfig, columnMap.get(tableConfig.getTableName()));
            // 后台相关
            generator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "Dao.java.ftl", getJavaPath(projectConfig, "dao") + tableConfig.getMapperName() + ".java"));
            generator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "DaoMapper.xml.ftl", getJavaPath(projectConfig, "dao") + tableConfig.getMapperName() + "Mapper.xml"));
            generator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "Model.java.ftl", getJavaPath(projectConfig, "model") + tableConfig.getDomainObjectName() + ".java"));
            generator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "Criteria.java.ftl", getJavaPath(projectConfig, "model") + tableConfig.getDomainObjectName() + "Criteria.java"));
            generator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "Service.java.ftl", getJavaPath(projectConfig, "service") + tableConfig.getDomainObjectName() + "Service.java"));
            generator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "ServiceImpl.java.ftl", getJavaPath(projectConfig, "service/impl") + tableConfig.getDomainObjectName() + "ServiceImpl.java"));
            generator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "Action.java.ftl", getJavaPath(projectConfig, "action") + tableConfig.getDomainObjectName() + "Action.java"));

            // jsp模块页面相关
            generator.invoke(new GeneratorFile(GeneratorTypeEnum.FTL, "view/module.jsp.ftl", getWebappPath(projectConfig, "view/" + tableConfig.getDomainObjectName().toLowerCase()) + tableConfig.getDomainObjectName() + ".jsp"));
        }


    }

    /**
     * 获取java文件相对路径
     * @param projectConfig 项目配置
     * @return 相对路径
     */
    public static String getJavaPath(ProjectConfig projectConfig, String append){
        return String.format("%s/%s/%s/%s/%s/"
                , projectConfig.getProjectName()
                , "src/main/java"
                , projectConfig.getBasePackagePath().replaceAll("\\.","/")
                , projectConfig.getProjectName().toLowerCase()
                , append
        );
    }

    /**
     * 获取spring 文件相对路径
     * @param projectConfig 项目配置
     * @return 相对路径
     */
    public static String getSpringPath(ProjectConfig projectConfig){
        return String.format("%s/%s/"
                , projectConfig.getProjectName()
                , "src/main/resources"
        );
    }

    /**
     * 获取webapp相对路径
     * @param projectConfig 项目配置
     * @return 相对路径
     */
    public static String getWebappPath(ProjectConfig projectConfig, String append){
        return String.format("%s/%s/"
                , projectConfig.getProjectName()
                , "src/main/webapp" + (StringUtils.isNotBlank(append) ? "/" + append : "")
        );
    }

    /**
     * 获取jsp 文件相对路径
     * @param projectConfig 项目配置
     * @return 相对路径
     */
    public static String getPomPath(ProjectConfig projectConfig){
        return String.format("%s/"
                , projectConfig.getProjectName()
        );
    }

    /**
     * 获取 test 文件相对路径
     * @param projectConfig 项目配置
     * @return 相对路径
     */
    public static String getTestPath(ProjectConfig projectConfig, String append){
        return String.format("%s/%s/"
                , projectConfig.getProjectName()
                , "src/test" + (StringUtils.isNotBlank(append) ? "/" + append : "")
        );
    }

}
