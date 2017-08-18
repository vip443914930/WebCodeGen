package com.codegen.api;

import com.codegen.config.ProjectConfig;
import com.codegen.context.CopyFileContext;
import com.codegen.exception.GeneratorException;
import com.codegen.meta.GeneratorFile;
import freemarker.template.Configuration;

import java.io.IOException;

/**
 * 复制资源文件
 *
 */
public class CopyFileGenerator extends AbstractGenerator<CopyFileContext>{
    ProjectConfig projectConfig;

    public CopyFileGenerator(ProjectConfig projectConfig){
        this.projectConfig = projectConfig;
    }

    /** 组装模版参数 **/
    public CopyFileContext createContext() throws GeneratorException{
        CopyFileContext context = new CopyFileContext();
        context.setProjectConfig(projectConfig);
        return context;
    }

    public Configuration getFtlConfiguration() throws IOException {
        return null;
    }

    public void customerInvoke(Configuration cfg, GeneratorFile file, CopyFileContext context) {

    }

    /** 校验 **/
    public void onValid(GeneratorFile file, CopyFileContext context) throws GeneratorException{

    }
}
