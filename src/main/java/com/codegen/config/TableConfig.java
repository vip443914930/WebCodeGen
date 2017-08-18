package com.codegen.config;

import com.codegen.meta.TableColumn;

/**
 * Created by chenxiaojun on 2017/8/2.
 */
public  class TableConfig {
    private String tableName;
    private String domainObjectName;
    private String mapperName;
    /** 表主键 **/
    private TableColumn primaryKey;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDomainObjectName() {
        return domainObjectName;
    }

    public void setDomainObjectName(String domainObjectName) {
        this.domainObjectName = domainObjectName;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public TableColumn getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(TableColumn primaryKey) {
        this.primaryKey = primaryKey;
    }
}
