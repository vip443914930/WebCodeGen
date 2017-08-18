package com.codegen.api;

import com.codegen.ShellRunner;
import com.codegen.config.DBConfig;
import com.codegen.config.ProjectConfig;
import com.codegen.config.TableConfig;
import com.codegen.context.MvcContext;
import com.codegen.exception.GeneratorException;
import com.codegen.meta.GeneratorFile;
import com.codegen.meta.TableColumn;
import com.codegen.util.ImportsUtils;
import com.codegen.util.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mvc文件生成处理器。
 * *Dao.java、*Model.java、*DaoMapper.xml、*.jsp、Criteria.java、services.java
 * Created by chenxiaojun on 2017/8/2.
 */
public class MVCFileGenerator extends AbstractGenerator<MvcContext>{

    private ProjectConfig projectConfig;
    private DBConfig dbConfig;
    private List<TableColumn> tableColumns;
    private TableConfig tableConfig;

    public MVCFileGenerator(ProjectConfig projectConfig, TableConfig tableConfig, List<TableColumn> tableColumns){
        this.projectConfig = projectConfig;
        this.tableConfig = tableConfig;
        this.tableColumns = tableColumns;
    }

    public MVCFileGenerator(ProjectConfig projectConfig){
        this.projectConfig = projectConfig;
    }

    public MVCFileGenerator(ProjectConfig projectConfig, DBConfig dbConfig){
        this.projectConfig = projectConfig;
        this.dbConfig = dbConfig;
    }

    /** 组装模版参数 **/
    public MvcContext createContext() throws GeneratorException{
        MvcContext context = new MvcContext();
        context.setProjectConfig(projectConfig);
        context.setTableConfig(tableConfig);
        context.setColumns(tableColumns);
        context.setDbConfig(dbConfig);
        Map<String, Object> pathMap = new HashMap<String, Object>();
        pathMap.put("StringUtils", new StringUtils());
        pathMap.put("importsMap", ImportsUtils.columnImport(tableColumns));
        pathMap.put("MVC_PACKAGE", String.format("%s.%s", projectConfig.getBasePackagePath(), projectConfig.getProjectName().toLowerCase()));
        pathMap.put("DAO_PACKAGE", String.format("%s.%s.%s", projectConfig.getBasePackagePath(), projectConfig.getProjectName().toLowerCase(), "dao"));
        pathMap.put("MODEL_PACKAGE", String.format("%s.%s.%s", projectConfig.getBasePackagePath(), projectConfig.getProjectName().toLowerCase(), "model"));
        pathMap.put("SERVICE_PACKAGE", String.format("%s.%s.%s", projectConfig.getBasePackagePath(), projectConfig.getProjectName().toLowerCase(), "service"));
        pathMap.put("SERVICE_IMPL_PACKAGE", String.format("%s.%s.%s", projectConfig.getBasePackagePath(), projectConfig.getProjectName().toLowerCase(), "service.impl"));
        pathMap.put("ACTION_PACKAGE", String.format("%s.%s.%s", projectConfig.getBasePackagePath(), projectConfig.getProjectName().toLowerCase(), "action"));
        pathMap.put("ENUM_PACKAGE", String.format("%s.%s.%s", projectConfig.getBasePackagePath(), projectConfig.getProjectName().toLowerCase(), "enum"));
        pathMap.put("GENERIC_PACKAGE", String.format("%s.%s.%s", projectConfig.getBasePackagePath(), projectConfig.getProjectName().toLowerCase(), "core.generic"));
        context.setExtensions(pathMap);
        return context;
    }

    public Configuration getFtlConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
       // cfg.setDirectoryForTemplateLoading(new File( "src/main/java/com/codegen/ftl/mvc")); //需要文件夹绝对路径
        cfg.setClassForTemplateLoading(ShellRunner.class, "ftl/mvc");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return cfg;
    }

    public void customerInvoke(Configuration cfg, GeneratorFile file, MvcContext context) {

    }

    /** 校验 **/
    public void onValid(GeneratorFile file, MvcContext context) throws GeneratorException{
    }
}
