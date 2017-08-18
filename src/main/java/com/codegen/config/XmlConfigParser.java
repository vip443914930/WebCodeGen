package com.codegen.config;

import com.codegen.enums.ErrorCodeEnum;
import com.codegen.exception.GeneratorException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取配置文件配置类
 *
 * @author chenxiaojun
 */
public class XmlConfigParser {

    private List<TableConfig> tableConfigs;
    private DBConfig dbConfig;
    private ProjectConfig projectConfig;

    /**
     * @param xmlPath 配置文件路径
     */
    public XmlConfigParser(String xmlPath) throws GeneratorException{
        super();
        this.parser(xmlPath);
    }

    private void parser(String xmlPath) throws GeneratorException {
        try {
            // step 1:获得DOM解析器工厂
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // step 2：获得具体的dom解析器
            DocumentBuilder db = dbf.newDocumentBuilder();
            // step 3:解析一个xml文档，获得Document对象（根节点）
            Document document = db.parse(xmlPath);
            Element rootEle = document.getDocumentElement();
            NodeList childNodes = rootEle.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if (item.getNodeName().equals("jdbcConnection")) {
                    parseDBConfig(item);
                } else if (item.getNodeName().equals("project")) {
                    parseProjectConfig(item);
                } else if (item.getNodeName().equals("tables")) {
                    parseTableConfigs(item);
                }
            }
            if (dbConfig == null) {
                throw new GeneratorException(ErrorCodeEnum.CONFIG_FILE_ERROR, "配置文件节点jdbcConnection不存在");
            }
            if (projectConfig == null) {
                throw new GeneratorException(ErrorCodeEnum.CONFIG_FILE_ERROR, "配置文件节点project不存在");
            }
            if (tableConfigs == null) {
                throw new GeneratorException(ErrorCodeEnum.CONFIG_FILE_ERROR, "配置文件节点tables不存在");
            }
        } catch (GeneratorException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneratorException(ErrorCodeEnum.CONFIG_FILE_ERROR, "配置文件解析失败", e);
        }
    }

    private void parseDBConfig(Node node) throws GeneratorException {
        dbConfig = new DBConfig();
        dynamicSet(dbConfig, node);
    }

    private void parseProjectConfig(Node node) throws GeneratorException {
        projectConfig = new ProjectConfig();
        dynamicSet(projectConfig, node);
    }

    private void parseTableConfigs(Node node) throws GeneratorException {
        tableConfigs = new ArrayList<TableConfig>();
        NodeList tableNodes = node.getChildNodes();
        for (int i = 0; i < tableNodes.getLength(); i++) {
            Node tableNode = tableNodes.item(i);
            if (tableNode.getNodeName().equals("table")) {
                TableConfig config = new TableConfig();
                dynamicSet(config, tableNode);
                tableConfigs.add(config);
            }
        }
    }

    private void dynamicSet(Object object, Node node) {
        try {
            Class<?> aClass = object.getClass();
            NamedNodeMap attributes = node.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attr = attributes.item(i);
                Field field = aClass.getDeclaredField(attr.getNodeName());
                field.setAccessible(true);
                field.set(object, attr.getNodeValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTableConfigs(List<TableConfig> tableConfigs) {
        this.tableConfigs = tableConfigs;
    }

    public List<TableConfig> getTableConfigs() {
        return tableConfigs;
    }

    public DBConfig getDbConfig() {
        return dbConfig;
    }

    public void setDbConfig(DBConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public ProjectConfig getProjectConfig() {
        return projectConfig;
    }

    public void setProjectConfig(ProjectConfig projectConfig) {
        this.projectConfig = projectConfig;
    }
}
