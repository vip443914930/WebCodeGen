package com.codegen.util;

import com.codegen.config.DBConfig;
import com.codegen.config.TableConfig;
import com.codegen.meta.TableColumn;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 获取数据库字段信息
 *
 * @author chenxiaojun
 */
public class DataBaseUtil {

    public static Map<String, List<TableColumn>> getTableColumns(DBConfig dbConfig, List<TableConfig> tableConfigs) {
        Map<String, List<TableColumn>> tableColumnMap = new TreeMap<String, List<TableColumn>>();
        System.out.println("开始连接数据库!");
        Connection connection = null;
        try {
            Class.forName(dbConfig.getDriverClass());
            //获取数据库连接
            connection = DriverManager.getConnection(dbConfig.getConnectionURL(), dbConfig.getUserId(), dbConfig.getPassword());
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            for (TableConfig tableConfig : tableConfigs) {
                tableColumnMap.put(tableConfig.getTableName(), createTableColumn(databaseMetaData, tableConfig));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Connection 关闭失败");
                }
            }
        }
        return tableColumnMap;
    }

    private static List<TableColumn> createTableColumn(DatabaseMetaData databaseMetaData, TableConfig tableConfig) {
        ResultSet rs = null;
        try {
            List<TableColumn> tableColumns = new ArrayList<TableColumn>();
            //获取表结构信息
            rs = databaseMetaData.getColumns("", "", tableConfig.getTableName(), "%");
            boolean supportsIsAutoIncrement = false;
            boolean supportsIsGeneratedColumn = false;
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            for (int i = 1; i <= colCount; i++) {
                if ("IS_AUTOINCREMENT".equals(rsmd.getColumnName(i))) {
                    supportsIsAutoIncrement = true;
                }
                if ("IS_GENERATEDCOLUMN".equals(rsmd.getColumnName(i))) {
                    supportsIsGeneratedColumn = true;
                }
            }
            JavaTypeResolver javaTypeResolver = new JavaTypeResolver();
            while (rs.next()) {
                TableColumn column = new TableColumn();
                if (rs.isFirst()) {
                    column.setIsPrimaryKey(true);
                    tableConfig.setPrimaryKey(column);
                }
                column.setJdbcType(rs.getInt("DATA_TYPE"));
                column.setLength(rs.getInt("COLUMN_SIZE"));
                column.setActualColumnName(rs.getString("COLUMN_NAME"));
                column.setNullable(rs.getInt("NULLABLE") == DatabaseMetaData.columnNullable);
                column.setScale(rs.getInt("DECIMAL_DIGITS"));
                column.setRemarks(rs.getString("REMARKS"));
                column.setDefaultValue(rs.getString("COLUMN_DEF"));
                column.setJavaType(javaTypeResolver.get(column));
                column.setJdbcTypeName(javaTypeResolver.calculateJdbcTypeName(column));
                column.setJavaProperty(StringUtils.getCamelCaseString(column.getActualColumnName(), false));
                if (supportsIsAutoIncrement) {
                    column.setIsAutoIncrement("YES".equals(rs.getString("IS_AUTOINCREMENT")));
                }
                if (supportsIsGeneratedColumn) {
                    column.setIsGeneratedColumn("YES".equals(rs.getString("IS_GENERATEDCOLUMN")));
                }
                tableColumns.add(column);
            }
            return tableColumns;
        } catch (Exception e) {
            System.out.println(String.format("获取数据表[%s]信息失败, 失败原因:%s", tableConfig.getTableName(), e.getMessage()));
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("ResultSet 关闭失败");
                }
            }
        }
        return null;
    }
}
