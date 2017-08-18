package com.codegen.config;

/**
 * Created by chenxiaojun on 2017/8/2.
 */
public class ProjectConfig {
    /** 静态资源路径 **/
    private String resourcePath;
    private String projectName;
    private String projectPath;
    private String basePackagePath;
    private String overWrite = "false";

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getBasePackagePath() {
        return basePackagePath;
    }

    public void setBasePackagePath(String basePackagePath) {
        this.basePackagePath = basePackagePath;
    }

    public String getOverWrite() {
        return overWrite;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public void setOverWrite(String overWrite) {
        this.overWrite = overWrite;
    }
}
