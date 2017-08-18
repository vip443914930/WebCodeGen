package com.codegen.enums;

/**
 * 生成类型
 */
public enum GeneratorTypeEnum implements EnumBase{
    FTL("FTL", "模版生成"),
    CUSTOMER_INVOKE("CUSTOMER_INVOKE", "自定义执行"),
    COPY_FOLDER("COPY_FOLDER", "复制文件夹"),
    COPY_FILE("COPY_FILE", "复制文件"),
    COPY_JAR_FOLDER("COPY_JAR_FOLDER", "复制JAR包文件夹"),
    COPY_JAR_FILE("COPY_JAR_FILE", "复制JAR包文件"),
    GENERATOR_FOLDER("GENERATOR_FOLDER", "生成文件夹");
    private String code;
    private String message;

    GeneratorTypeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static GeneratorTypeEnum getByCode(String code){
        if(code == null || "".equals(code)){
            return null;
        }
        for(GeneratorTypeEnum item : values()){
            if(item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }

    public boolean equals(String code) {
        return getCode().equals(code);
    }
}
