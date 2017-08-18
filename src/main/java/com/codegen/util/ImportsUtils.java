package com.codegen.util;

import com.codegen.meta.TableColumn;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImportsUtils {

    public static Set<String> columnImport(List<TableColumn> columns){
        if(columns == null){
            return null;
        }
        Set<String> imports = new HashSet<String>();
        for (TableColumn column : columns) {
            imports.add(column.getJavaType().getBaseQualifiedName());
        }
        return imports;
    }
}
