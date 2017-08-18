package ${extensions['MODEL_PACKAGE']};

<#list extensions['importsMap'] as import>
import ${import};
</#list>

public class ${tableConfig.domainObjectName}{

<#list columns as column>
    /*** ${column.remarks} ***/
    private ${column.javaType.baseShortName?cap_first} ${column.javaProperty};

</#list>
<#list columns as column>
    public ${column.javaType.baseShortName?cap_first} get${column.javaProperty?cap_first}() {
       return this.${column.javaProperty};
    }
    public void set${column.javaProperty?cap_first}(${column.javaType.baseShortName?cap_first} ${column.javaProperty}) {
       this.${column.javaProperty} = ${column.javaProperty};
    }

</#list>

}
