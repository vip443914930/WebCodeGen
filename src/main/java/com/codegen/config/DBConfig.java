package com.codegen.config;

/**
 * Created by chenxiaojun on 2017/8/2.
 */
public class DBConfig {
    private String classPathEntry;
    private String driverClass;
    private String connectionURL;
    private String userId;
    private String password;

    public String getClassPathEntry() {
        return classPathEntry;
    }

    public void setClassPathEntry(String classPathEntry) {
        this.classPathEntry = classPathEntry;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
