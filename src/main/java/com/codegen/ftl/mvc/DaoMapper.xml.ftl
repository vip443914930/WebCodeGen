<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${extensions['DAO_PACKAGE']}.${tableConfig.mapperName}">
    <resultMap id="BaseResultMap" type="${extensions['MODEL_PACKAGE']}.${tableConfig.domainObjectName}">
    <#list columns as column>
    <#if (column.isPrimaryKey)>
    <id column="${column.actualColumnName}" jdbcType="${column.jdbcTypeName}" property="${column.javaProperty}" />
    <#else>
        <result column="${column.actualColumnName}" jdbcType="${column.jdbcTypeName}" property="${column.javaProperty}" />
    </#if>
    </#list>
    </resultMap>
    <sql id="Criteria_Where_Clause">
        <where>
            <foreach collection="oredCriteria" item="criteria" separator="or">
                <if test="criteria.valid">
                    <trim prefix="(" prefixOverrides="and" suffix=")">
                        <foreach collection="criteria.criteria" item="criterion">
                            <choose>
                                <when test="criterion.noValue">
                                    and ${r'$'}{criterion.condition}
                                </when>
                                <when test="criterion.singleValue">
                                    and ${r'$'}{criterion.condition} ${r'#'}{criterion.value}
                                </when>
                                <when test="criterion.betweenValue">
                                    and ${r'$'}{criterion.condition} ${r'#'}{criterion.value} and ${r'#'}{criterion.secondValue}
                                </when>
                                <when test="criterion.listValue">
                                    and ${r'$'}{criterion.condition}
                                    <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">
                                    ${r'#'}{listItem}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>

    <sql id="Update_By_Criteria_Where_Clause">
        <where>
            <foreach collection="example.oredCriteria" item="criteria" separator="or">
                <if test="criteria.valid">
                    <trim prefix="(" prefixOverrides="and" suffix=")">
                        <foreach collection="criteria.criteria" item="criterion">
                            <choose>
                                <when test="criterion.noValue">
                                    and ${r'$'}{criterion.condition}
                                </when>
                                <when test="criterion.singleValue">
                                    and ${r'$'}{criterion.condition} ${r'#'}{criterion.value}
                                </when>
                                <when test="criterion.betweenValue">
                                    and ${r'$'}{criterion.condition} ${r'#'}{criterion.value} and ${r'#'}{criterion.secondValue}
                                </when>
                                <when test="criterion.listValue">
                                    and ${r'$'}{criterion.condition}
                                    <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">
                                    ${r'#'}{listItem}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>
    <sql id="Base_Column_List">
    <#list columns as column>
       ${column.actualColumnName}<#if column_has_next>,</#if>
    </#list>
    </sql>

    <select id="selectByCriteria" parameterType="${extensions['MODEL_PACKAGE']}.${tableConfig.domainObjectName}Criteria" resultMap="BaseResultMap">
        select
        <if test="distinct">
            distinct
        </if>
        <include refid="Base_Column_List" />
        from ${tableConfig.tableName}
        <if test="_parameter != null">
            <include refid="Criteria_Where_Clause" />
        </if>
        <if test="orderByClause != null">
            order by ${r'$'}{orderByClause}
        </if>
    </select>

    <select id="selectByPrimaryKey" parameterType="${tableConfig.primaryKey.javaType}" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List" />
        from ${tableConfig.tableName}
        where ${tableConfig.primaryKey.actualColumnName} = ${r'#'}{${tableConfig.primaryKey.javaProperty},jdbcType=${tableConfig.primaryKey.jdbcTypeName}}
    </select>
    <delete id="deleteByPrimaryKey" parameterType="${tableConfig.primaryKey.javaType}">
        delete from ${tableConfig.tableName}
        where ${tableConfig.primaryKey.actualColumnName} = ${r'#'}{${tableConfig.primaryKey.javaProperty},jdbcType=${tableConfig.primaryKey.jdbcTypeName}}
    </delete>

    <delete id="deleteByCriteria" parameterType="${extensions['MODEL_PACKAGE']}.${tableConfig.domainObjectName}Criteria">
        delete from ${tableConfig.tableName}
        <if test="_parameter != null">
            <include refid="Criteria_Where_Clause" />
        </if>
    </delete>

    <insert id="insert" parameterType="${extensions['MODEL_PACKAGE']}.${tableConfig.domainObjectName}">
        insert into ${tableConfig.tableName} (
        <#list columns as column>
            ${column.actualColumnName}<#if column_has_next>,</#if>
        </#list>
        )
        values (
        <#list columns as column>
            ${r'#'}{${column.javaProperty},jdbcType=${column.jdbcTypeName}}<#if column_has_next>,</#if>
        </#list>
        )
    </insert>

    <insert id="insertSelective" parameterType="${extensions['MODEL_PACKAGE']}.${tableConfig.domainObjectName}">
        insert into ${tableConfig.tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list columns as column>
            <if test="${column.javaProperty} != null">
                ${column.actualColumnName},
            </if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list columns as column>
            <if test="${column.javaProperty} != null">
                ${r'#'}{${column.javaProperty},jdbcType=${column.jdbcTypeName}},
            </if>
            </#list>
        </trim>
    </insert>

    <select id="countByCriteria" parameterType="${extensions['MODEL_PACKAGE']}.${tableConfig.domainObjectName}Criteria" resultType="java.lang.Long">
        select count(*) from ${tableConfig.tableName}
        <if test="_parameter != null">
            <include refid="Criteria_Where_Clause" />
        </if>
    </select>

    <update id="updateByCriteriaSelective" parameterType="map">
        update ${tableConfig.tableName}
        <set>
            <#list columns as column>
            <if test="${tableConfig.domainObjectName?uncap_first}.${column.javaProperty} != null">
                ${column.actualColumnName} = ${r'#'}{${tableConfig.domainObjectName?uncap_first}.${column.javaProperty},jdbcType=${column.jdbcTypeName}}<#if column_has_next>,</#if>
            </if>
            </#list>
        </set>
        <if test="_parameter != null">
            <include refid="Update_By_Criteria_Where_Clause" />
        </if>
    </update>

    <update id="updateByCriteria" parameterType="map">
        update ${tableConfig.tableName}  set
        <#list columns as column>
            ${column.actualColumnName} = ${r'#'}{${tableConfig.domainObjectName?uncap_first}.${column.javaProperty},jdbcType=${column.jdbcTypeName}}<#if column_has_next>,</#if>
        </#list>
        <if test="_parameter != null">
            <include refid="Update_By_Criteria_Where_Clause" />
        </if>
    </update>

    <update id="updateByPrimaryKeySelective" parameterType="${extensions['MODEL_PACKAGE']}.${tableConfig.domainObjectName}">
        update ${tableConfig.tableName}
        <set>
        <#list columns as column>
            <if test="${column.javaProperty} != null">
                ${column.actualColumnName} = ${r'#'}{${tableConfig.domainObjectName?uncap_first}.${column.javaProperty},jdbcType=${column.jdbcTypeName}}<#if column_has_next>,</#if>
            </if>
        </#list>
        </set>
        where
        ${tableConfig.primaryKey.actualColumnName} = ${r'#'}{${tableConfig.primaryKey.javaProperty},jdbcType=${tableConfig.primaryKey.jdbcTypeName}}
    </update>

    <update id="updateByPrimaryKey" parameterType="${extensions['MODEL_PACKAGE']}.${tableConfig.domainObjectName}">
        update ${tableConfig.tableName} set
        <#list columns as column>
            ${column.actualColumnName} = ${r'#'}{${tableConfig.domainObjectName?uncap_first}.${column.javaProperty},jdbcType=${column.jdbcTypeName}}<#if column_has_next>,</#if>
        </#list>
        where
        ${tableConfig.primaryKey.actualColumnName} = ${r'#'}{${tableConfig.primaryKey.javaProperty},jdbcType=${tableConfig.primaryKey.jdbcTypeName}}
    </update>
</mapper>