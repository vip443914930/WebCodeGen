package com.codegen.context;

import com.alibaba.fastjson.JSONObject;
import com.codegen.config.ProjectConfig;

/**
 * context 每个table公共信息
 * @author chenxiaojun
 */
public class Context <T> {

    private ProjectConfig projectConfig;

    /** 扩展信息 **/
    private T extensions;

    public ProjectConfig getProjectConfig() {
        return projectConfig;
    }

    public void setProjectConfig(ProjectConfig projectConfig) {
        this.projectConfig = projectConfig;
    }

    public Context() {
        super();
    }

    public T getExtensions() {
        return extensions;
    }

    public void setExtensions(T extensions) {
        this.extensions = extensions;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
