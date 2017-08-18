package ${extensions['DAO_PACKAGE']};

import ${extensions['MVC_PACKAGE']}.core.generic.GenericDao;
import ${extensions['MODEL_PACKAGE']}.${tableConfig.domainObjectName};
import ${extensions['MODEL_PACKAGE']}.${tableConfig.domainObjectName}Criteria;
import org.apache.ibatis.annotations.Param;

/***
* <p>${tableConfig.domainObjectName}</p>
*  generator by WebCodeGen
*/
public interface ${tableConfig.mapperName} extends GenericDao<${tableConfig.domainObjectName}, ${tableConfig.domainObjectName}Criteria, ${tableConfig.primaryKey.javaType.baseShortName?cap_first}>{

    int updateByCriteriaSelective(@Param("${tableConfig.domainObjectName?uncap_first}") ${tableConfig.domainObjectName} record, @Param("criteria") ${tableConfig.domainObjectName}Criteria criteria);

    int updateByCriteria(@Param("${tableConfig.domainObjectName?uncap_first}") ${tableConfig.domainObjectName} ${tableConfig.domainObjectName?uncap_first}, @Param("criteria") ${tableConfig.domainObjectName}Criteria criteria);
}