package com.codegen.enums;

/**
 *
 * <p>错误代码</p>
 * @author chenxiaojun
 * @version $Id: ErrorCodeEnumEnum.java, v 0.1 2015-4-18 下午1:18:08 chenxiaojun Exp $
 */
public enum ErrorCodeEnum implements EnumBase{

    CONFIG_FILE_ERROR("CONFIG_FILE_ERROR","解析配置文件异常"),

    VALID_ERROR("VALID_ERROR","校验错误"),

    GENERATOR_FILE_ERROR("GENERATOR_FILE_ERROR","生成文件异常");

    private String code;
    private String message;

    ErrorCodeEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorCodeEnum getByCode(String code){
        if(code == null || "".equals(code)){
            return null;
        }
        for(ErrorCodeEnum item : values()){
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
