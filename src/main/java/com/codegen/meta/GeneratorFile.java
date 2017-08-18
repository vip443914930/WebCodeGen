package com.codegen.meta;

import com.codegen.enums.GeneratorTypeEnum;

/**
 * 生成的文件信息
 * Created by chenxiaojun on 2017/8/4.
 */
public class GeneratorFile {
    /** 生成方式 **/
    private GeneratorTypeEnum generatorTypeEnum;
    /** 文件模版 或者待复制文件，文件夹 **/
    private String source;
    /** 生成文件相对项目路径 **/
    private String[] relativePath;

    public GeneratorFile (GeneratorTypeEnum generatorTypeEnum, String relativePath){
        super();
        this.generatorTypeEnum = generatorTypeEnum;
        this.relativePath = new String[]{relativePath};
    }

    public GeneratorFile (GeneratorTypeEnum generatorTypeEnum, String source, String relativePath){
        super();
        this.generatorTypeEnum = generatorTypeEnum;
        this.source = source;
        this.relativePath = new String[]{relativePath};
    }

    public GeneratorFile(GeneratorTypeEnum generatorTypeEnum, String source, String[] relativePath) {
        super();
        this.generatorTypeEnum = generatorTypeEnum;
        this.source = source;
        this.relativePath = relativePath;
    }

    public GeneratorTypeEnum getGeneratorTypeEnum() {
        return generatorTypeEnum;
    }

    public void setGeneratorTypeEnum(GeneratorTypeEnum generatorTypeEnum) {
        this.generatorTypeEnum = generatorTypeEnum;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String[] getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String[] relativePath) {
        this.relativePath = relativePath;
    }
}
