package ${extensions['SERVICE_IMPL_PACKAGE']};

import ${extensions['MVC_PACKAGE']}.core.generic.GenericServiceImpl;
import ${extensions['MVC_PACKAGE']}.core.generic.GenericDao;
import ${extensions['DAO_PACKAGE']}.${tableConfig.mapperName};
import ${extensions['MODEL_PACKAGE']}.${tableConfig.domainObjectName};
import ${extensions['MODEL_PACKAGE']}.CreditConfCriteria;
import ${extensions['SERVICE_PACKAGE']}.${tableConfig.domainObjectName}Service;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("${tableConfig.domainObjectName?uncap_first}Service")
public class ${tableConfig.domainObjectName}ServiceImpl extends GenericServiceImpl<${tableConfig.domainObjectName}, ${tableConfig.domainObjectName}Criteria, ${tableConfig.primaryKey.javaType.baseShortName?cap_first}> implements ${tableConfig.domainObjectName}Service{

    @Resource(name="creditConfDao")
    private ${tableConfig.domainObjectName}Dao ${tableConfig.domainObjectName?uncap_first}Dao;

    @Override
    public GenericDao getDao() {
        return ${tableConfig.domainObjectName?uncap_first}Dao;
    }
}
