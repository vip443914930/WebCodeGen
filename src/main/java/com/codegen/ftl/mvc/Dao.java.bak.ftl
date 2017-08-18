package ${extensions['DAO_PACKAGE']};

import ${extensions['MODEL_PACKAGE']}.${tableConfig.domainObjectName};
import ${extensions['MODEL_PACKAGE']}.${tableConfig.domainObjectName}Criteria;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/***
* <p>${tableConfig.domainObjectName}</p>
*  generator by WebCodeGen
*/
public interface ${tableConfig.mapperName} {
    long countByCriteria(${tableConfig.domainObjectName}Criteria criteria);

    int deleteByCriteria(${tableConfig.domainObjectName}Criteria criteria);

    int deleteByPrimaryKey(${tableConfig.primaryKey.javaType.baseShortName?cap_first} ${tableConfig.primaryKey.javaProperty});

    int insert(${tableConfig.domainObjectName} ${tableConfig.domainObjectName?uncap_first});

    int insertSelective(${tableConfig.domainObjectName} ${tableConfig.domainObjectName?uncap_first});

    List<${tableConfig.domainObjectName}> selectByCriteria(${tableConfig.domainObjectName}Criteria criteria);

    ${tableConfig.domainObjectName} selectByPrimaryKey(${tableConfig.primaryKey.javaType.baseShortName?cap_first} ${tableConfig.primaryKey.javaProperty});

    int updateByCriteriaSelective(@Param("${tableConfig.domainObjectName?uncap_first}") ${tableConfig.domainObjectName} record, @Param("criteria") ${tableConfig.domainObjectName}Criteria criteria);

    int updateByCriteria(@Param("${tableConfig.domainObjectName?uncap_first}") ${tableConfig.domainObjectName} ${tableConfig.domainObjectName?uncap_first}, @Param("criteria") ${tableConfig.domainObjectName}Criteria criteria);

    int updateByPrimaryKeySelective(${tableConfig.domainObjectName} ${tableConfig.domainObjectName?uncap_first});

    int updateByPrimaryKey(${tableConfig.domainObjectName} ${tableConfig.domainObjectName?uncap_first});
}