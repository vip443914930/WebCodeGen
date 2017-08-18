package com.codegen.context;

import com.alibaba.fastjson.JSONObject;
import com.codegen.config.DBConfig;
import com.codegen.config.TableConfig;
import com.codegen.meta.TableColumn;

import java.util.List;
import java.util.Map;

/**
 * context 每个table公共信息
 * @author chenxiaojun
 */
public class MvcContext extends Context<Map> {
    private TableConfig tableConfig;
    private DBConfig dbConfig;

    /** 数据库字段信息 **/
    private List<TableColumn> columns;

    public MvcContext() {
        super();
    }

    public List<TableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<TableColumn> columns) {
        this.columns = columns;
    }

    public TableConfig getTableConfig() {
        return tableConfig;
    }

    public void setTableConfig(TableConfig tableConfig) {
        this.tableConfig = tableConfig;
    }

    public DBConfig getDbConfig() {
        return dbConfig;
    }

    public void setDbConfig(DBConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
