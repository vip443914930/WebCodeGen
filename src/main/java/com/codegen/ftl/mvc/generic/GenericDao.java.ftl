package ${extensions['GENERIC_PACKAGE']};
import java.util.List;

/**
 * 所有自定义Dao的顶级接口, 封装常用的增删查改操作,
 * Model : 代表数据库中的表 映射的Java对象类型
 * criteria : mybatis criteria
 * PK : 代表对象的主键类型
 * @author cxj
 * @since 2014年6月9日 下午6:14:06
 */
public interface GenericDao<Model, Criteria, PK> {

    long countByCriteria(Criteria criteria);

    int deleteByCriteria(Criteria criteria);

    int deleteByPrimaryKey(PK pk);

    int insert(Model model);

    int insertSelective(Model model);

    List<Model> selectByCriteria(Criteria criteria);

    Model selectByPrimaryKey(PK pk);

    int updateByPrimaryKeySelective(Model model);

    int updateByPrimaryKey(Model model);

}
